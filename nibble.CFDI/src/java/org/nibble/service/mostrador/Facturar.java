package org.nibble.service.mostrador;

import org.nibble.main.BeanError;
import org.nibble.main.BeanUtil;
import java.sql.Connection;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import dbbeans.aplicacion.*;
import dbbeans.categoria.*;
import dbbeans.cliente.*;
import dbbeans.configsistema.*;
import dbbeans.configuracionpva.*;
import dbbeans.ctaxcobrar.*;
import org.nibble.dao.factura.*;
import dbbeans.facturadetalle.*;
import dbbeans.hisconsumo.dbHISCONSUMO;
import dbbeans.inventario.*;
import dbbeans.movimieninventario.*;
import dbbeans.niveldeprecio.*;
import dbbeans.usuarios.*;
import dbrbeans.clientepaisestadomunicipio.*;
import dbrbeans.parteaplicacion.*;
import dbrbeans.parteclasificacion.*;
import dbrbeans.proveedorinventario.*;
import general.Help;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import pva.beans8.Bean82;
import pva.beans9.*;
import pva.servlets4.BeanHelper;
import pva.servlets8.BeanHelper82;
import org.nibble.util.UtilDate;

import java.util.Vector;
import org.nibble.util.Fecha;

/**
 * DOCUMENT ME!
 *
 * @author $author$
 * @version $Revision$
 */
public class Facturar extends HttpServlet{ 
//main.MainServlet {

	private int idModulo;
	private int MOSTRADOR_SIN_IVA;
	private int ID_CONFIG;
	private int ID_CONFIGPVA;
	private int ID_SISTEMA;
	private int ID_GRUPO_SUPERVISOR;
	private int TIPOMOV_FAC;
	private int IDCLIENTESINIVA;
	private int IDCLIENTECONIVA;
	private int ID_MOV_VENTA;
	private static Log logger = LogFactory.getLog(Facturar.class);

	/**
	 * Recupera nueva cantidad y nivel para cada partida
	 * Creation date: (11/5/2002 1:50:38 PM)
	 * @param param javax.servlet.http.HttpServletRequest
	 */
	private void actualizaPartidas(javax.servlet.http.HttpServletRequest request, Bean91 bean91) {
		Bean91Partidas partida;
		int index;
		int index2;
		String vchnivel = null;
		int iidnivel = 0;
		float descuento = 0;
		float dec = 0;
		int cantidad = 0;

		for (int i = 0; i < bean91.getVPartidasSize(); i++) {
			int nivel = 0;

			if ((request.getParameter("nivel" + i) != null) && (request.getParameter("nivel" + i).length() > 0)) {
				String temp = request.getParameter("nivel" + i);
				String temp1 = temp.substring(0, index = temp.indexOf("/"));
				String temp2 = temp.substring(index + 1, index2 = temp.indexOf("/", index + 1));
				String temp3 = temp.substring(index2 + 1, temp.length());

				iidnivel = new Integer(temp1).intValue();
				dec = Float.valueOf(temp2).floatValue();
				vchnivel = temp3;
			}

			if ((request.getParameter("cantidad" + i) != null) && (request.getParameter("cantidad" + i).length() > 0)) {
				cantidad = new Integer(request.getParameter("cantidad" + i)).intValue();
			}

			partida = (Bean91Partidas) bean91.getVPartidas().elementAt(i);
			partida.setIidnivel(iidnivel);
			partida.setVchnivel(vchnivel);
			partida.setCantidad(cantidad);
			partida.setPrecioUnitario(
				Calcula.precio(
					partida.getDecprecio(), dec, bean91.isBitutilsobreventa(), bean91.isMostradorSinIVA(), bean91.getIva()));
			partida.setImporte(partida.getPrecioUnitario() * cantidad);
		}

		if ((request.getParameter("descuento") != null) && (request.getParameter("descuento").length() > 0)) {
			descuento = new Float(request.getParameter("descuento")).floatValue();
		}

		bean91.setDescuento(descuento);
	}

	/**
	 * Recalcula subtotal, descuento y total para las partidas en Bean91
	 * Creation date: (11/5/2002 1:50:38 PM)
	 * @param param javax.servlet.http.HttpServletRequest
	 */
	private void actualizaPartidas(Bean91 bean91) {
		Bean91Partidas partida;
		float subtotal = 0;
		float montodescuento = 0;
		float total = 0;
		float montoiva = 0;

		for (int i = 0; i < bean91.getVPartidasSize(); i++) {
			partida = (Bean91Partidas) bean91.getVPartidas().elementAt(i);
			subtotal += partida.getImporte();
		}

		montodescuento = Calcula.redondeo((subtotal * bean91.getDescuento()) / 100);
		montoiva = Calcula.redondeo((subtotal - montodescuento) * (bean91.getIva() / 100));

		bean91.setSubtotal(subtotal);

		if (bean91.isMostradorSinIVA()) {
			bean91.setTotal(subtotal);
		} else {
			bean91.setMontodescuento(montodescuento);
			bean91.setMontoiva(montoiva);
			bean91.setTotal(subtotal - montodescuento + montoiva);
		}
	}

	/**
	 * Agregar partes de la ventana de aplicacion a la factura
	 * Creation date: (11/5/2002 1:50:38 PM)
	 * @param param javax.servlet.http.HttpServletRequest
	 */
	private void addPartes(javax.servlet.http.HttpServletRequest request, Bean91 bean91, HttpSession ses_usr)
			throws Exception {
		Bean91f bean91f = (Bean91f) ses_usr.getAttribute("bean91f");
		int icantidad = 0;
		int j = 0;

		dbrPARTE parte;
		Bean91Partidas partida;

		for (int i = 0; i < bean91f.getSize(); i++) {
			if (bean91f.getProveeSize(i) == 0) {
				continue;
			}

			if ((request.getParameter("iidproveedor" + i) != null) && (request.getParameter("iidproveedor" + i).length() > 0)) {
				j = Integer.valueOf(request.getParameter("iidproveedor" + i)).intValue();
			}

			icantidad = bean91f.getIcantidad(i);

			partida = new Bean91Partidas();

			if (icantidad > bean91f.getProveeIexistencia(i, j)) {
				icantidad = bean91f.getProveeIexistencia(i, j);
				partida.setExistenciaMayorCantidad(true);
			}

			partida.setLiidparte(bean91f.getLiidparte(i));

			partida.setPrefijo(Help.getPrefijo(bean91f.getVchnumparteequival(i)));
			partida.setNumero(Help.getNumero(bean91f.getVchnumparteequival(i)));
			partida.setSufijo(Help.getSufijo(bean91f.getVchnumparteequival(i)));
			partida.setMedida(Help.getMedida(bean91f.getVchnumparteequival(i)));

			partida.setVchnumparte(bean91f.getVchnumparteequival(i));

			partida.setVchdescripcion(bean91f.getVchdescripciones(i));

			partida.setIidnivel(bean91.getCliente().getIidnivel());
			partida.setVchnivel(bean91.getCliente().getVchniveldes());

			partida.setCantidad(icantidad);

			partida.setDecprecioB100(bean91f.getProveeDecprecio(i, j));
			partida.setIexistencia(bean91f.getProveeIexistencia(i, j));
			partida.setIidproveedor(bean91f.getProveeIidproveedor(i, j));
            
			partida.setDecprecio(bean91f.getProveeDecprecio(i, j));
			partida.setVchrazonsocial(bean91f.getProveeVchrazonsocial(i, j));
            
			partida.setPrecioUnitario(
				Calcula.precio(
					partida.getDecprecioB100(), bean91.getCliente().getDecutilidad(), bean91.isBitutilsobreventa(),
					bean91.isMostradorSinIVA(), bean91.getIva()));

			partida.setImporte(partida.getPrecioUnitario() * bean91f.getIcantidad(i));

			bean91.addPartida(partida);
		}
	}

	/**
	 * Agrega una partida de la parte seleccionada
	 * Creation date: (11/5/2002 1:50:38 PM)
	 * @param param javax.servlet.http.HttpServletRequest
	 */
	private void addPartida(javax.servlet.http.HttpServletRequest request, Bean91 bean91)
			throws Exception {
		Bean91Partidas partida = new Bean91Partidas();
		int iidnivel = 0;
		float dec = 0;
		float precio = 0;
		int existencia = 0;
		int iidproveedor = 0;
		int cantidad = 0;
		String vhcrazonsocial = "";
		int i = 0;
		int index;
		int index2;
		String temp;
		String temp1;
		String temp2;
		String temp3;
		String vchnivel = null;

		partida.setLiidparte(bean91.getLiidparte());
//		partida.setPrefijo(bean91.getPrefijo());
//		partida.setNumero(bean91.getNumero());
//		partida.setSufijo(bean91.getSufijo());
//		partida.setMedida(bean91.getMedida());
		partida.setVchnumparte(bean91.getVchnumparte());
		partida.setVchdescripcion(bean91.getVchdescripciones());
		partida.setVchunidaddescripcion(bean91.getVchunidaddescripcion());
        
		if ((request.getParameter("iidnivel") != null) && (request.getParameter("iidnivel").length() > 0)) {
			temp = request.getParameter("iidnivel");
			
			temp1 = temp.substring(0, index = temp.indexOf("/"));
			
			temp2 = temp.substring(index + 1, index2 = temp.indexOf("/", index + 1));
			
			temp3 = temp.substring(index2 + 1, temp.length());
			
			iidnivel = new Integer(temp1).intValue();
			dec = Float.valueOf(temp2).floatValue();
			vchnivel = temp3;
		}

		if ((request.getParameter("iidproveedor") != null) && (request.getParameter("iidproveedor").length() > 0)) {
			temp = request.getParameter("iidproveedor");
            
			temp1 = temp.substring(0, temp.indexOf("/"));
			i = Integer.valueOf(temp1).intValue();
            
			precio = bean91.getProveeDecprecio(i);
			existencia = bean91.getProveeIexistencia(i);
			iidproveedor = bean91.getProveeIidproveedor(i);
			vhcrazonsocial = bean91.getProveeVchrazonsocial(i);
		}

		if ((request.getParameter("cantidad") != null) && (request.getParameter("cantidad").length() > 0)) {
			cantidad = new Integer(request.getParameter("cantidad")).intValue();
		}

		if (cantidad > existencia) {
			cantidad = existencia;
			partida.setExistenciaMayorCantidad(true);
		}

		partida.setIexistencia(existencia);
		partida.setIidnivel(iidnivel);
		partida.setVchnivel(vchnivel);
		partida.setCantidad(cantidad);
		partida.setDecprecioB100(bean91.getParte().getDecpreciob100());
		partida.setDecprecio(precio);
		partida.setVchrazonsocial(vhcrazonsocial);
		partida.setIidproveedor(iidproveedor);

		partida.setPrecioUnitario(
			Calcula.precio(partida.getDecprecio(), dec, bean91.isBitutilsobreventa(), bean91.isMostradorSinIVA(), bean91.getIva()));

		partida.setImporte(partida.getPrecioUnitario() * cantidad);

		bean91.addPartida(partida);
		bean91.setParte(new dbrPARTE());
		bean91.setVProveedores(new Vector());
	}

	/**
	 * Busca Aplicaciones deacuerdo a criterios
	 * Creation date: (11/5/2002 1:50:38 PM)
	 * @param param javax.servlet.http.HttpServletRequest
	 */
	private void buscarAplicaciones(javax.servlet.http.HttpServletRequest request, Bean91 bean91, Connection con, HttpSession ses_usr)
			throws Exception {
		Bean91Aplicacion bean91Aplicacion = new Bean91Aplicacion();
		dbAPLICACIONs aplicaciones = new dbAPLICACIONs();

		aplicaciones.setConnection(con);
		aplicaciones.finder(request);

		bean91Aplicacion.setV(aplicaciones.getResult());

		ses_usr.setAttribute("bean91Aplicacion", bean91Aplicacion);
	}

	/**
	 * IBusca clientes por Razon social y RFC
	 * Creation date: (11/5/2002 1:50:38 PM)
	 * @param param javax.servlet.http.HttpServletRequest
	 */
	private void buscarClientes(javax.servlet.http.HttpServletRequest request, Bean91 bean91, Connection con, HttpSession ses_usr)
			throws Exception {
		Bean91b bean91b = new Bean91b();
		dbCLIENTEs clientes = new dbCLIENTEs();
		clientes.setConnection(con);
		clientes.findByPar(request.getParameter("vchrazonsocial"), request.getParameter("vchrfc"));

		bean91b.setVClientes(clientes.getResult());
		ses_usr.setAttribute("bean91b", bean91b);
	}

	/**
	 * Obtiene partes por Aplicacion y Categoria.
	 * Creation date: (11/5/2002 1:50:38 PM)
	 * @param param javax.servlet.http.HttpServletRequest
	 */
	private void buscarPartes(javax.servlet.http.HttpServletRequest request, Bean91 bean91, Connection con, HttpSession ses_usr)
			throws Exception {
		int iidaplicacion = 0;
		int iidcategoria = 0;
		Bean82 bean82 = new Bean82();

		dbCATEGORIAs categorias = new dbCATEGORIAs();
		dbrPARTEAPLICACIONs parteaplicaciones = new dbrPARTEAPLICACIONs();
		dbrPROVEEDORs proveedores = new dbrPROVEEDORs();

		proveedores.setConnection(con);
		categorias.setConnection(con);
		parteaplicaciones.setConnection(con);

		if ((request.getParameter("iidaplicacion") != null) && (request.getParameter("iidaplicacion").length() > 0)) {
			iidaplicacion = new Integer(request.getParameter("iidaplicacion")).intValue();
		}

		if ((request.getParameter("iidcategoria") != null) && (request.getParameter("iidcategoria").length() > 0)) {
			iidcategoria = new Integer(request.getParameter("iidcategoria")).intValue();
		}

		categorias.find("");
		bean82.setVCategorias(categorias.getResult());
		ses_usr.setAttribute("bean82", bean82);

		// Busca partes por aplicacion
		if (iidcategoria == 0) {
			parteaplicaciones.findByAplicacion(iidaplicacion);
		} else {
			parteaplicaciones.findByAplicacion(iidaplicacion, iidcategoria);
		}

		Bean91Parte bean91Parte = new Bean91Parte();
		bean91Parte.setVPartes(parteaplicaciones.getResult());

		bean91Parte.setIidcategoria(iidcategoria);

		ses_usr.setAttribute("bean91Parte", bean91Parte);
	}

	/**
	 * DOCUMENT ME!
	 *
	 * @param request DOCUMENT ME!
	 * @param response DOCUMENT ME!
	 *
	 * @throws javax.servlet.ServletException DOCUMENT ME!
	 * @throws java.io.IOException DOCUMENT ME!
	 */
	public void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response)
			throws javax.servlet.ServletException, java.io.IOException {
		performTask(request, response);
	}

	/**
	 * DOCUMENT ME!
	 *
	 * @param request DOCUMENT ME!
	 * @param response DOCUMENT ME!
	 *
	 * @throws javax.servlet.ServletException DOCUMENT ME!
	 * @throws java.io.IOException DOCUMENT ME!
	 */
	public void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response)
			throws javax.servlet.ServletException, java.io.IOException {
		performTask(request, response);
	}

	/**
	 * Realiza los siguientes procesos:
			 PARA CADA PARTIDA:
					Crea la FacturaDetalle
					Modifica existencia en inventario y lo modifica el bit de salida reciente
					Crea un movimiento de inventario de VENTA
			END
			Crea la factura con el folio de configuracionpva
			Crea la cuenta por cobrar
			Alterar el credito utilizado del cliente

	 * Creation date: (11/5/2002 1:50:38 PM)
	 * @param param javax.servlet.http.HttpServletRequest
	 */
	private void facturar(javax.servlet.http.HttpServletRequest request, Bean91 bean91, Connection con, HttpSession ses_usr)
			throws Exception {
		FacturaDAO factura = new FacturaDAO();
		dbFACTURADETALLE detalle = new dbFACTURADETALLE();
		dbCONFIGURACIONPVA configuracionpva = new dbCONFIGURACIONPVA();
		dbCTAXCOBRAR ctaxcobrar = new dbCTAXCOBRAR();
		dbCTAXCOBRARs cxcs = new dbCTAXCOBRARs();
		dbCLIENTE cliente = new dbCLIENTE();
		dbUSUARIOS usuario = new dbUSUARIOS();
		dbUSUARIOSs usuarios = new dbUSUARIOSs();
		dbINVENTARIO inventario = new dbINVENTARIO();
		dbMOVIMIENINVENTARIO movimiento = new dbMOVIMIENINVENTARIO();
                dbHISCONSUMO consumo = new dbHISCONSUMO();
        
		factura.setConnection(con);
		detalle.setConnection(con);
		configuracionpva.setConnection(con);
		ctaxcobrar.setConnection(con);
		cliente.setConnection(con);
		usuario.setConnection(con);
		usuarios.setConnection(con);
		inventario.setConnection(con);
		movimiento.setConnection(con);
		cxcs.setConnection(con);
                consumo.setConnection(con);
        
		configuracionpva.setIidconfpva(ID_CONFIGPVA);
		configuracionpva.load();

		Bean91P bean91p = new Bean91P();
		bean91p.setSinorengfac(configuracionpva.getSinorengfac());
		bean91p.setIDCLIENTECONIVA(IDCLIENTECONIVA);
		bean91p.setIDCLIENTESINIVA(IDCLIENTESINIVA);

		// Vector que contendr� todos los ID de las facturas generadas
		Vector v = new Vector();

		int folio = configuracionpva.getInumerofactura();
                bean91.setFolio(folio);
                
		int renglones = configuracionpva.getSinorengfac();                
                
                Date date = Fecha.formatoDateFacturaElectronica().getTime();// ((getFecha());                
                bean91.setDate(date);
		String hoy = Fecha.getCurrentDate("yyyyMMddHHmmss");
		String hoyyyyyMMdd = Fecha.getCurrentDate("yyyyMMdd");
		float totalFactura;
		float total = 0;
		float ivaMonto = 0;
		float descuentoMonto = 0;

		reorganize(bean91);

		// Checar que la existencia - cantidad pedida sea mayor que 0
		for (int i = 0; i < bean91.getVPartidasSize(); i++) {
			inventario.setLiidparte(bean91.getVPartidasLiidparte(i));
			inventario.setIidproveedor(bean91.getVPartidasIidproveedor(i));
			inventario.load();

			if ((inventario.getIexistencia() - bean91.getVPartidasCantidad(i)) < 0) {
				throw new Exception(
					"Imposible surtir la parte " + bean91.getVPartidasPrefijo(i) + "." + bean91.getVPartidasNumero(i) + "."
					+ bean91.getVPartidasSufijo(i) + "." + bean91.getVPartidasMedida(i));
			}

			if (bean91.getVPartidasCantidad(i) == 0) {
				throw new Exception("No se puede facturar con cantidad 0 ");
			}
			
			if(cxcs.getClienteDadoDeBaja(bean91.getIidnocliente() )){
									throw new Exception("No se puede facturar a un cliente dado de baja ");
			}
			
		}

		// if el cliente es IDCLIENTECONIVA Obtener datos para factura
		if ((bean91.getIidnocliente() == IDCLIENTECONIVA) && (bean91.getCliente().getSiidpais() == 0)) {
			BeanHelper hlp = new BeanHelper();
			hlp.getData(con);
			ses_usr.setAttribute("bean4", hlp.getBean4());
			bean91.setPopupClienteConIVA(true);

			return;
		}

		Integer userInt = (Integer) ses_usr.getAttribute("iidusuario");
		int iidusuario = userInt.intValue();
                
                String vchFormapago = request.getParameter("vchformapago");
                String vchMetodopago = request.getParameter("vchmetodopago");
                String vchCuentapago = request.getParameter("vchcuentapago");

                System.out.println("SERVLET " + vchFormapago + request.getParameter("vchformapago"));
                System.out.println("SERVLET " +vchMetodopago + request.getParameter("vchmetodopago"));
                System.out.println("SERVLET "+vchCuentapago + request.getParameter("vchcuentapago"));
                bean91.setVchcuentapago(vchCuentapago);
                bean91.setVchformapago(vchFormapago);
                bean91.setVchmetodopago(vchMetodopago);
                
		// En caso de que el total exceda el credito (Saldo+Fact.Actual> Limite de Credito)  o  tener cuentas vencidas no saldadas
		// pedir el password de un administrador 
		//
		//Saldo Vencido > 0
		if (( (  bean91.getTotal() > bean91.getCredito()  ) || cxcs.getCuentasVencidasNoSaldadas(bean91.getIidnocliente(), hoy))) {
		
        
			String user = request.getParameter("user");
			String password = request.getParameter("password");

			if (!(usuario.findByUserPassword(user, password, ID_SISTEMA) && (usuario.getIidgrupo() == ID_GRUPO_SUPERVISOR))) {
				bean91.setPopupLogin(true);

				return;
			} else {
				iidusuario = usuario.getIidusuario();
			}
		}

		bean91.setPopupLogin(false);

		StringBuffer msg = new StringBuffer("Factura: ");
		int count = 0;

		while (count < bean91.getVPartidasSize()) {
			totalFactura = 0;

			for (int i = count; (i < (count + renglones)) && (i < bean91.getVPartidasSize()); i++) {
				//  Crea la FacturaDetalle
				detalle.setIidfactura(folio);
				detalle.setLiidparte(bean91.getVPartidasLiidparte(i));
				detalle.setIidproveedor(bean91.getVPartidasIidproveedor(i));
				detalle.setIcantidadproducto(bean91.getVPartidasCantidad(i));
				detalle.setDecpreciolista(bean91.getVPartidasDecprecioB100(i));
				detalle.setIidnivel(bean91.getVPartidasNivel(i));
				detalle.setDeccostoproducto(bean91.getVPartidasDecprecio(i));
				detalle.setDecivaclasific(bean91.getIva());
				detalle.setDecprecioventa(bean91.getVPartidasPrecioUnitario(i));
				detalle.create();
				bean91.setVPartidasFolio(i,folio);

				// Modifica existencia en inventario y lo modifica el bit de salida reciente
				inventario.setLiidparte(bean91.getVPartidasLiidparte(i));
				inventario.setIidproveedor(bean91.getVPartidasIidproveedor(i));
				inventario.load();
				inventario.setIexistencia(inventario.getIexistencia() - bean91.getVPartidasCantidad(i));
				inventario.setBitsalidareciente(true);
				inventario.store();

				// Crea un movimiento de inventario de VENTA
				movimiento.setLiidparte(bean91.getVPartidasLiidparte(i));
				movimiento.setIidproveedor(bean91.getVPartidasIidproveedor(i));
				movimiento.setTitipomov(ID_MOV_VENTA);
				movimiento.setTisfechamov(hoy);
				movimiento.setDeccostob100(bean91.getVPartidasDecprecioB100(i));
				movimiento.setDecporciva(bean91.getIva());
				movimiento.setDecprecioventa((bean91.getVPartidasPrecioUnitario(i) * (100 - bean91.getDescuento())) / 100);
				movimiento.setIidreferencia(folio);
				movimiento.setIidusuario(iidusuario);
				movimiento.setIcantidad(bean91.getVPartidasCantidad(i));
				movimiento.create();
                
                //Creacion de historico de consumo
                int mm = UtilDate.getMM(UtilDate.getStrDate());
                int yy = UtilDate.getYY(UtilDate.getStrDate());
                consumo.setMm(mm);
                consumo.setYy(yy);
                consumo.setIidproveedor(bean91.getVPartidasIidproveedor(i));
                consumo.setLiidparte(bean91.getVPartidasLiidparte(i));
                if(consumo.load()){
                	consumo.setCantidad(consumo.getCantidad()+bean91.getVPartidasCantidad(i));
                	consumo.store();
                }
                else{
					consumo.setCantidad(bean91.getVPartidasCantidad(i));
                	consumo.create();
                }
                
				totalFactura += (bean91.getVPartidasPrecioUnitario(i) * bean91.getVPartidasCantidad(i));
			}

			if (bean91.isMostradorSinIVA()) {
				total = totalFactura;
			} else {
				descuentoMonto = Calcula.redondeo((totalFactura * bean91.getDescuento()) / 100);
				ivaMonto = Calcula.redondeo((totalFactura - descuentoMonto) * (bean91.getIva() / 100));
				total = Calcula.redondeo(totalFactura - descuentoMonto + ivaMonto);
			}

			// agrega folio a vector para imprimir
			v.addElement(new Integer(folio));

			// Actualizar fecha de vencimiento
			bean91.getDiasDepago(hoy);

			// crea la factura
			factura.setIidfactura(folio);
			factura.setIidnocliente(bean91.getIidnocliente());
			factura.setIidusuario(iidusuario);
			factura.setTisfechafactura(hoy);
			factura.setDecsubtotal(totalFactura);
			factura.setDecivatotal(ivaMonto);
			factura.setVchletra(configuracionpva.getChletrafactura());
			factura.setDecdescglobal(bean91.getDescuento());
			factura.setTisfechavencimient(bean91.getFechaVencimiento());
			factura.setBitcredito(bean91.getCliente().getBsuceptiblecredito());
			factura.setDecmontodesc(descuentoMonto);
                        
                        factura.setVchrfccliente(bean91.getRFCCliente());
                        factura.setVchserie(configuracionpva.getVchserie());
                        factura.setIfolio(folio);
                        factura.setInumaprobacion(configuracionpva.getInumaprobacion());
                        factura.setVchfechahora(Fecha.formatoReporteFacturaElectronica(date));
                        factura.setDecmontooperacion(total);
                        factura.setDecmontoimpuesto(ivaMonto);
                        factura.setVchestadocomprobante("1");
                        factura.setVchefectocomprobante("I");
                        factura.setVchpedimento("");
                        factura.setVchfechapedimento("");
                        factura.setVchaduana("");
                        //Metodos pago
                        bean91p.setVchformapago(bean91.getVchformapago());
                        bean91p.setVchmcuentapago(bean91.getVchcuentapago());
                        bean91p.setVchmetodopago(bean91.getVchmetodopago());
                        //Set Valores Factura
                        factura.setVchmetodopago(bean91.getVchmetodopago());
                        factura.setVchmcuentapago(bean91.getVchcuentapago());
                        factura.setVchformapago(bean91.getVchformapago());
                        //Insert the value on the DB
			factura.create();

			// Crea la cuenta por cobrar
			ctaxcobrar.setIiddoctoorigen(folio);
			ctaxcobrar.setTiidtipodocto(TIPOMOV_FAC);
			ctaxcobrar.setTiconsecsubdocto(1);
			ctaxcobrar.setIidnocliente(bean91.getIidnocliente());
			ctaxcobrar.setTisfechamovimiento(hoy);
			ctaxcobrar.setTisfechavencimient(bean91.getFechaVencimiento());
			ctaxcobrar.setDecdebe(total);
			ctaxcobrar.create();

			msg.append(folio + " ");

			count += renglones;
			folio++;
		}

		configuracionpva.setInumerofactura(folio);
		configuracionpva.store();

		cliente.setIidnocliente(bean91.getIidnocliente());
		cliente.load();
		cliente.setDeccreditutilizado(cliente.getDeccreditutilizado() + total);
		bean91.getCliente().setDeccreditutilizado(cliente.getDeccreditutilizado());
		cliente.store();

		// Crea una copia de bean91 para subirla a la sesion y la pueda tomar ImprimirFactura
		Bean91 bean912 = (Bean91) bean91.clone();
		bean91p.setVfacturas(v);
		bean91p.setCliente(bean91.getCliente());
		bean91p.setFechaFacturacion(hoyyyyyMMdd);
		bean91p.setFechaVencimiento(bean91.getFechaVencimiento());
                //                
                bean91p.setVchformapago(bean91.getVchformapago());
                bean91p.setVchmcuentapago(bean91.getVchcuentapago());
                bean91p.setVchmetodopago(bean91.getVchmetodopago());

		ses_usr.setAttribute("bean91p", bean91p);
		ses_usr.setAttribute("bean912", bean912);
		bean91.setPopupImprimir(true);

		// borra partidas del bean91
		bean91.setVPartidas(new Vector());

		bean91.setMsg(msg.toString());
                
		factura = null;
		detalle = null;
		configuracionpva = null;
	}

	/**
	 * Obtiene datos del cliente mostardor con iva desglozado de la forma
	 * Creation date: (11/5/2002 1:50:38 PM)
	 * @param param javax.servlet.http.HttpServletRequest
	 */
	private void getClienteConIVA(javax.servlet.http.HttpServletRequest request, Bean91 bean91)
			throws Exception {
		String vchrazonsocial = request.getParameter("vchrazonsocial");
		String vchrfc = request.getParameter("vchrfc");
		String vchcolonia = request.getParameter("vchcolonia");
		String vchcalle = request.getParameter("vchcalle");
		String chcodigop = request.getParameter("chcodigop");
		short siidpais = 0;

		if ((request.getParameter("siidpais") != null) && !request.getParameter("siidpais").equals("")) {
			siidpais = new Short(request.getParameter("siidpais")).shortValue();
		}

		int iidestado = 0;

		if ((request.getParameter("iidestado") != null) && !request.getParameter("iidestado").equals("")) {
			iidestado = new Integer(request.getParameter("iidestado")).intValue();
		}

		int iidmunicipio = 0;

		if ((request.getParameter("iidmunicipio") != null) && !request.getParameter("iidmunicipio").equals("")) {
			iidmunicipio = new Integer(request.getParameter("iidmunicipio")).intValue();
		}

		bean91.getCliente().setVchrazonsocial(vchrazonsocial);
		bean91.getCliente().setVchrfc(vchrfc);
		bean91.getCliente().setVchcolonia(vchcolonia);
		bean91.getCliente().setVchcalle(vchcalle);
		bean91.getCliente().setChcodigop(chcodigop);
		bean91.getCliente().setSiidpais(siidpais);
		bean91.getCliente().setIidestado(iidestado);
		bean91.getCliente().setIidmunicipio(iidmunicipio);
		bean91.getCliente().setVchpaisdes(request.getParameter("vchpais"));
		bean91.getCliente().setVchestadodes(request.getParameter("vchestado"));
		bean91.getCliente().setVchmunicipiodes(request.getParameter("vchmunicipio"));
	}

	/**
	 * DOCUMENT ME!
	 */
	public void init() {
		idModulo = (getServletConfig().getInitParameter("idModulo") != null)
						? new Integer(getServletConfig().getInitParameter("idModulo")).intValue() : (-1);
		MOSTRADOR_SIN_IVA = new Integer(getServletConfig().getInitParameter("MOSTRADOR_SIN_IVA")).intValue();
		ID_CONFIG = new Integer(getServletConfig().getInitParameter("ID_CONFIG")).intValue();
		ID_CONFIGPVA = new Integer(getServletConfig().getInitParameter("ID_CONFIGPVA")).intValue();
		ID_SISTEMA = new Integer(getServletConfig().getInitParameter("ID_SISTEMA")).intValue();
		ID_GRUPO_SUPERVISOR = new Integer(getServletConfig().getInitParameter("ID_GRUPO_SUPERVISOR")).intValue();
		TIPOMOV_FAC = new Integer(getServletConfig().getInitParameter("TIPOMOV_FAC")).intValue();
		IDCLIENTESINIVA = new Integer(getServletConfig().getInitParameter("IDCLIENTESINIVA")).intValue();
		IDCLIENTECONIVA = new Integer(getServletConfig().getInitParameter("IDCLIENTECONIVA")).intValue();
		ID_MOV_VENTA = new Integer(getServletConfig().getInitParameter("ID_MOV_VENTA")).intValue();
	}

	/**
	 * Carga Parametros iniciales en el Bean
	 * Creation date: (11/5/2002 1:50:38 PM)
	 * @param param javax.servlet.http.HttpServletRequest
	 */
	private void initial(javax.servlet.http.HttpServletRequest request, Bean91 bean91, Connection con, HttpSession ses_usr)
			throws Exception {
		// Obtiene nombre de usuario de sesion
		bean91.setUsuario((String) ses_usr.getAttribute("username"));

		dbCONFIGSISTEMA configsistema = new dbCONFIGSISTEMA();
		configsistema.setConnection(con);
		configsistema.setIidconfsistema(ID_CONFIG);
		configsistema.load();

		// Carga a bean IVA y el BIT de UTILIDAD SOBRE VENTA
		bean91.setIva(configsistema.getDecivaaplicable());
		bean91.setBitutilsobreventa(configsistema.getBitutilsobreventa());

		configsistema = null;
	}

	/**
	 * El usuario busca una aplicacion, Se intenta cargar la aplicacion por el
	 * iidaplicacion introducido

	 * Creation date: (11/5/2002 1:50:38 PM)
	 * @param param javax.servlet.http.HttpServletRequest
	 */
	private void loadAplicacion(javax.servlet.http.HttpServletRequest request, Bean91 bean91, Connection con, HttpSession ses_usr)
			throws Exception {
		Bean91Aplicacion bean91Aplicacion = new Bean91Aplicacion();
		dbAPLICACIONr aplicacion = new dbAPLICACIONr();
		int iidaplicacion = 0;

		if ((request.getParameter("iidaplicacion") != null) && (request.getParameter("iidaplicacion").length() > 0)) {
			iidaplicacion = new Integer(request.getParameter("iidaplicacion")).intValue();
		}

		aplicacion.setConnection(con);
		aplicacion.setIidaplicacion(iidaplicacion);

		try {
			aplicacion.load();
			bean91Aplicacion.getV().add(aplicacion);
			bean91.setPopupAplicacion(true);
			ses_usr.setAttribute("bean91Aplicacion", bean91Aplicacion);
		} catch (Exception e) {
			bean91.setPopupBuscaAplicacion(true);
		}
	}

	/**
	 * Carga a un cliente en el bean
	 * Este metodo se ejecuta despues que el cliente ha seleccionado un cliente
	 * en el popupcliente.
	 *
	 * Creation date: (11/5/2002 1:50:38 PM)
	 * @param param javax.servlet.http.HttpServletRequest
	 */
	private void loadCliente(javax.servlet.http.HttpServletRequest request, Bean91 bean91, int iidnocliente, Connection con)
			throws Exception {
		boolean found = false;            	
		dbrCLIENTE cliente = new dbrCLIENTE();

		if ((request.getParameter("iidnocliente") != null) && (request.getParameter("iidnocliente").length() > 0)) {
			iidnocliente = new Integer(request.getParameter("iidnocliente")).intValue();
		}

		cliente.setIidnocliente(iidnocliente);
		cliente.setConnection(con);

		try {
				found = cliente.load();
				dbNIVELDEPRECIOs niveldeprecios = new dbNIVELDEPRECIOs();
				niveldeprecios.setConnection(con);
				niveldeprecios.findByIidcatcliente(cliente.getIidcatcliente());
				bean91.setVNiveles(niveldeprecios.getResult());

				if (cliente.getIidnocliente() == IDCLIENTESINIVA) {
					bean91.setMostradorSinIVA(true);
				} else {
					bean91.setMostradorSinIVA(false);
				}

				if (cliente.getIidnocliente() == IDCLIENTECONIVA) {
					bean91.setMostradorConIVA(true);
				} else {
					bean91.setMostradorConIVA(false);
				}

				actualizaPartidas(request, bean91);            	
			

		} catch (Exception e) {
			
			bean91.setPopupCliente(true);
		}

		if (cliente.getIidnivel() == 0 && found) {
			throw new Exception(
				"No se ha definido el nivel de precio para el cliente, por favor modifiquela en las condiciones comerciales del cliente");
		}

		bean91.setCliente(cliente);
        
	}

	/**
	 * Busca partes de acuerdo al numero de parte.
	 * SI encuentra solo a una, carga la parte
	 * SI encuentra mas de una, manda POPUP con lista de partes
	 * SI no encuentra ningua, mada POPUP con criterios de busqueda de partes
	 *
	 * Creation date: (11/5/2002 1:50:38 PM)
	 * @param param javax.servlet.http.HttpServletRequest
	 */
	private void loadParte(javax.servlet.http.HttpServletRequest request, Bean91 bean91, Connection con, HttpSession ses_usr)
			throws Exception {
		long liidparte = 0;
		int count = 0;

		dbrPARTEs partesClasi = new dbrPARTEs();
		dbrPARTE parte = new dbrPARTE();
		dbrPROVEEDORs proveedores = new dbrPROVEEDORs();

		parte.setConnection(con);
		proveedores.setConnection(con);

		if ((request.getParameter("liidparte") != null) && (request.getParameter("liidparte").length() > 0)) {
			liidparte = new Long(request.getParameter("liidparte")).longValue();
		}

		//String vchnumparte = request.getParameter("vchnumparte");

		String vchnumparte = general.Help.getVchnumparteEmpty(request.getParameter("vchnumparte"));
		String vchnumpartePattern = general.Help.getVchnumparte("", "", "", "");

		if (liidparte != 0) {
			parte.setLiidparte(liidparte);
			parte.load();
			bean91.setParte(parte);
			bean91.setVProveedores(
				proveedores.findExistenciasProveedor(
					parte.getLiidparte(), parte.getIidcategoria(), parte.getIidgrupo(), parte.getIidlinea(), parte.getIidmarca()));
		} else {
			// Consulta cuanta partes con vchnumparte
			partesClasi.setConnection(con);

			if (!vchnumparte.equals(vchnumpartePattern)) {
				count = partesClasi.getCountByVchnumparteEquival(vchnumparte);
			}

			// No hay ninguna parte con un vhcnumparte
			if (count == 0) {
				bean91.setPopupBuscarParte(true);
			}
			// load parte con el vchnumparte y Proveedores
			else if (count == 1) {
				parte.setVchnumparteequival(vchnumparte);
				parte.loadByVchnumparteEquival();
				bean91.setParte(parte);
				bean91.setVProveedores(
					proveedores.findExistenciasProveedor(
						parte.getLiidparte(), parte.getIidcategoria(), parte.getIidgrupo(), parte.getIidlinea(), parte.getIidmarca()));
			} else if (count > 1) {
				// Llenar beans con resultado
				Bean91d bean91d = new Bean91d();

				partesClasi.findByPar(0, 0, 0, 0, vchnumparte.toString(), false);
				bean91d.setVPartes(partesClasi.getResult());

				ses_usr.setAttribute("bean91d", bean91d);
				bean91.setPopupParte(true);
			}
		}
	}

	/**
	 * Una vez seleccionado las partes de una aplicacion
	 * Se ejecuta este metodo que carga los proveedores para cada parte
	 *
	 * Creation date: (11/5/2002 1:50:38 PM)
	 * @param param javax.servlet.http.HttpServletRequest
	 */
	private void loadProveePartes(javax.servlet.http.HttpServletRequest request, Bean91 bean91, Connection con, HttpSession ses_usr)
			throws Exception {
		Bean91f bean91f = new Bean91f();
		Bean91Parte bean91Parte = (Bean91Parte) ses_usr.getAttribute("bean91Parte");
		dbrPROVEEDORs proveedores = new dbrPROVEEDORs();

		dbrPARTEAPLICACION parte;
		Bean91Partidas partida;
		Bean91frecord pr;

		proveedores.setConnection(con);

		int icantidad = 0;

		java.util.StringTokenizer st;

		for (int i = 0; i < bean91Parte.getPartesSize(); i++)
			if (
				(request.getParameter("cantidad" + bean91Parte.getLiidparte(i)) != null)
					&& (request.getParameter("cantidad" + bean91Parte.getLiidparte(i)).length() > 0)) {
				icantidad = new Integer(request.getParameter("cantidad" + bean91Parte.getLiidparte(i))).intValue();

				if (icantidad > 0) {
					parte = (dbrPARTEAPLICACION) bean91Parte.getVPartes().elementAt(i);

					pr = new Bean91frecord();

					pr.setParte(parte);
					pr.setIcantidad(icantidad);
					pr.setVProveedores(
						proveedores.findExistenciasProveedor(
							parte.getLiidparte(), parte.getIidcategoria(), parte.getIidgrupo(), parte.getIidlinea(),
							parte.getIidmarca()));
					bean91f.addRecord(pr);
				}
			}

		ses_usr.setAttribute("bean91f", bean91f);
	}

	/**
	 * DOCUMENT ME!
	 *
	 * @param request DOCUMENT ME!
	 * @param response DOCUMENT ME!
	 */
	public void performTask(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) {
		Connection con = null;
		HttpSession ses_usr = null;
		BeanUtil bu = null;
		RequestDispatcher dispatcher = null;
		String strAddress = null;
		try {
			ses_usr = request.getSession(false);
			bu = new BeanUtil();		
			con = bu.getConn();		
			bu.setSession(ses_usr);
		
			if (!bu.getSessionValues())throw new Exception("Por motivos de seguridad la sesi�n ha expirado\n Registrese nuevamente");
			//if (!bu.getAcceso(idModulo,con))throw new Exception("No tiene permiso a este m�dulo");

			int accion = 1;
/*
			initConnection();
			seguridad.setConnection(con);
			if(!seguridad.tienePermiso(sistema,new Integer(ses_usr.getAttribute("grupo").toString()).intValue(),idModulo))throw new Exception("No tiene permiso a este m�dulo");		
*/
			if (request.getParameter("accion") != null) {
				accion = new Integer(request.getParameter("accion")).intValue();
			}

			

			Bean91 bean91 = new Bean91();

			if (accion != 1) {
				bean91 = (Bean91) ses_usr.getAttribute("bean91");
			} else {
				ses_usr.setAttribute("bean91", bean91);
			}

			bean91.setPopupClienteConIVA(false);
			bean91.setPopupImprimir(false);

			// checar datos de partidas excepto para acciones de los popup          
			if (
				(bean91.getVPartidasSize() > 0)
					&& ((accion == 4) || (accion == 8) || (accion == 9) || (accion == 10) || (accion == 14) || (accion == 18))) {
				actualizaPartidas(request, bean91);
			}

			Bean91Aplicacion bean91Aplicacion;
			BeanHelper91 hlp91;

			int iidaplicacion = 0;
			int iidnocliente = 0;

			switch (accion) {
				// 9.1 Ventana para buscar un cliente.
				case 2:
					bean91.setPopupCliente(false);
					strAddress = "/jsp/9_1b.jsp";

					break;

				// 9.1 Buscar clientes 
				case 3:
					bean91.setPopupCliente(false);
					buscarClientes(request, bean91, con, ses_usr);
					strAddress = "/jsp/9_1c.jsp";

					break;

				// 9.1 Pantalla inicial, Se carga el iva, usuario
				case 1:
					initial(request, bean91, con, ses_usr);
					iidnocliente = MOSTRADOR_SIN_IVA;

				// 9.1 load cliente
				case 4:
					loadCliente(request, bean91, iidnocliente, con);
					strAddress = "/jsp/9_1.jsp";

					break;

				// 9.1 Ventana para buscar partes
				case 5:
					bean91.setPopupBuscarParte(false);

					BeanHelper82 hlp82 = new BeanHelper82();
					hlp82.getData(con);
					ses_usr.setAttribute("bean82", hlp82.getBean82());
					strAddress = "/jsp/9_1d.jsp";

					break;

				// 9.1 Resultado de busqueda de partes
				case 6:
					resultadoPartes(request, bean91, con, ses_usr);
                                        System.out.println("Buscando producto");
					strAddress = "/jsp/9_1f.jsp";

					break;

				// 9.1 Mostrar partes en caso de haber encontrado mas de una
				case 7:
					bean91.setPopupParte(false);
					strAddress = "/jsp/9_1f.jsp";

					break;

				// 9.1 load parte
				case 8:
					loadParte(request, bean91, con, ses_usr);
					strAddress = "/jsp/9_1.jsp";

					break;

				// 9.1 Agregar una partida.
				case 9:
                    
					if ((bean91.getLiidparte() != 0) && (bean91.getProveeSize() > 0)) {
                    
						addPartida(request, bean91);
					}

					strAddress = "/jsp/9_1.jsp";

					break;

				// 9.1 Eliminar una partida
				case 10:
					removePartida(request, bean91);
					strAddress = "/jsp/9_1.jsp";

					break;

				// 9.1 Ventana de criterios de busqueda para la Aplicacion
				case 11:
					hlp91 = new BeanHelper91();
					hlp91.getData(con);
					ses_usr.setAttribute("bean91e", hlp91.getBean91e());
					bean91.setPopupBuscaAplicacion(false);
					strAddress = "/jsp/9_1g.jsp";

					break;

				// 9.1 Busqueda de Aplicaciones deacuerdo a criterios
				case 12:
					buscarAplicaciones(request, bean91, con, ses_usr);
					strAddress = "/jsp/9_1h.jsp";

					break;

				// Popup Aplicaciones
				case 13:
					bean91.setPopupAplicacion(false);
					strAddress = "/jsp/9_1h.jsp";

					break;

				// 9.1 Busca aplicaci�n por Id
				case 14:
					loadAplicacion(request, bean91, con, ses_usr);
					strAddress = "/jsp/9_1.jsp";

					break;

				// Muestra las categorias y partes
				case 15:
					buscarPartes(request, bean91, con, ses_usr);
					strAddress = "/jsp/9_1i.jsp";

					break;

				// 9.1 Agregra partes
				case 16:
					loadProveePartes(request, bean91, con, ses_usr);
					strAddress = "/jsp/9_1k.jsp";

					break;

				// 9.1 refresh
				case 17:
					strAddress = "/jsp/9_1.jsp";

					break;

				// 9.1 Facturar
				case 18:
					facturar(request, bean91, con, ses_usr);
					strAddress = "/jsp/9_1.jsp";

					break;

				// Popup Login
				case 19:
					bean91.setPopupLogin(false);
					strAddress = "/jsp/9_1j.jsp";

					break;

				case 20:
					addPartes(request, bean91, ses_usr);
					strAddress = "/jsp/9_1l.jsp";

					break;

				// Popup entrar datos de cliente mostrador con iva desglozado
				case 21:
					strAddress = "/jsp/9_1m.jsp";

					break;

				// Obtener valores del PoupClienteconIVA
				case 22:
					getClienteConIVA(request, bean91);
					strAddress = "/jsp/9_1l.jsp";

					break;
			}

			// actualizar el total 
			actualizaPartidas(bean91);
		} catch (Throwable e) {
			bu.setCommit(false);
			e.printStackTrace();
			BeanError error = new BeanError();
			error.setErrorMessage(e.getMessage());
			request.setAttribute("error",error);
			strAddress =  "/jsp/error.jsp";
		} finally {
			try{
				bu.finalizeTransaction(con);
				dispatcher = getServletContext().getRequestDispatcher(strAddress);
				dispatcher.forward(request, response);    		
			}catch (Exception ex){
				System.out.println("FATAL:"+ex.getMessage());
			}
		}
	}

	/**
	 * Elimina un bean91Partida del Vector
	 * Creation date: (11/5/2002 1:50:38 PM)
	 * @param param javax.servlet.http.HttpServletRequest
	 */
	private void removePartida(javax.servlet.http.HttpServletRequest request, Bean91 bean91)
			throws Exception {
		int i = 0;

		if ((request.getParameter("i") != null) && (request.getParameter("i").length() > 0)) {
			i = new Integer(request.getParameter("i")).intValue();
		}

		bean91.getVPartidas().remove(i);
	}

	/**
	 * Elimina partidas con la misma parte uniendolas.
	 * Creation date: (11/11/2002 5:49:34 PM)
	 * @param bean91 pva.beans9.Bean91
	 */
	public void reorganize(Bean91 bean91) throws Exception {
		Vector partidas = bean91.getVPartidas();
		Vector result = new Vector();
		Bean91Partidas temp;
		Bean91Partidas partida = null;
		int find;
		int j;

		if (partidas.size() == 0) {
			throw new Exception("La factura no tiene partidas");
		}

		for (int i = 0; i < partidas.size(); i++) {
			temp = (Bean91Partidas) partidas.elementAt(i);

			find = -1;
			j = 0;

			while (j < result.size()) {
				partida = (Bean91Partidas) result.elementAt(j);

				// comparta liidparte y iidproveedor
				if ((temp.getLiidparte() == partida.getLiidparte()) && (temp.getIidproveedor() == partida.getIidproveedor())) {
					if (temp.getIidnivel() != partida.getIidnivel()) {
						throw new Exception("No se puede vender la misma parte con diferentes niveles de precio");
					}

					find = j;

					break;
				}

				j++;
			}

			// si hubo duplicado, actualiza cantidades
			if (find >= 0) {
				partida.setCantidad(temp.getCantidad() + partida.getCantidad());
			} else {
				result.addElement(temp);
			}
		}

		bean91.setVPartidas(result);
	}

	/**
	 * llamado desde el POPUP de criterios: Busca partes.
	 *
	 * Creation date: (11/5/2002 1:50:38 PM)
	 * @param param javax.servlet.http.HttpServletRequest
	 */
	private void resultadoPartes(javax.servlet.http.HttpServletRequest request, Bean91 bean91, Connection con, HttpSession ses_usr)
			throws Exception {
		int iidcategoria = 0;

                System.out.println(request.getParameter("descripcion"));
                String descripcion ="";
                
                if ((request.getParameter("descripcion") != null) && (request.getParameter("descripcion").length() > 0)) {
			descripcion = new String(request.getParameter("descripcion").toString());
		}
                
		if ((request.getParameter("iidcategoria") != null) && (request.getParameter("iidcategoria").length() > 0)) {
			iidcategoria = new Integer(request.getParameter("iidcategoria")).intValue();
		}

		int iidgrupo = 0;

		if ((request.getParameter("iidgrupo") != null) && (request.getParameter("iidgrupo").length() > 0)) {
			iidgrupo = new Integer(request.getParameter("iidgrupo")).intValue();
		}

		int iidlinea = 0;

		if ((request.getParameter("iidlinea") != null) && (request.getParameter("iidlinea").length() > 0)) {
			iidlinea = new Integer(request.getParameter("iidlinea")).intValue();
		}

		int iidmarca = 0;

		if ((request.getParameter("iidmarca") != null) && (request.getParameter("iidmarca").length() > 0)) {
			iidmarca = new Integer(request.getParameter("iidmarca")).intValue();
		}

		dbrPARTEs partes = new dbrPARTEs();
		partes.setConnection(con);
                partes.findByDescripcion(descripcion);
		//partes.findByPar(
		//	iidcategoria, iidgrupo, iidlinea, iidmarca, request.getParameter("prefijo"), request.getParameter("numero"),
		//	request.getParameter("sufijo"), request.getParameter("medida"), false);

		Bean91d bean91d = new Bean91d();
		bean91d.setVPartes(partes.getResult());
		ses_usr.setAttribute("bean91d", bean91d);
	}
}