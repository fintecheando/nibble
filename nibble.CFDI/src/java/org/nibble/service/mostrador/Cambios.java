package org.nibble.service.mostrador;

import org.nibble.main.BeanError;
import org.nibble.main.BeanUtil;
import java.sql.Connection;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;

import dbbeans.configsistema.*;
import dbbeans.configuracionpva.*;
import dbbeans.ctaxcobrar.*;
import org.nibble.dao.factura.*;
import dbbeans.inventario.*;
import dbbeans.movimieninventario.*;
import dbbeans.notacambiodetalle.*;
import dbbeans.notadecambio.*;
import dbbeans.notadecredito.*;
import dbrbeans.clientepaisestadomunicipio.*;
import dbrbeans.facturadetalle.*;
import dbrbeans.parteclasificacion.*;
import dbrbeans.proveedorinventario.*;
import pva.beans9.*;
import pva.servlets8.BeanHelper82;
import java.util.Vector;

/**
 * DOCUMENT ME!
 *
 * @author $author$
 * @version $Revision$
 */
public class Cambios extends HttpServlet{ 
//main.MainServlet {
	private int idModulo;
    private int ID_CONFIG;
    private int ID_CONFIGPVA;
    private short ID_TIPOMOVIMIENTO;
    private int TIPOMOV_FAC;
    private int TIPOMOV_NCR;
    private int ID_MOV_ENTRADAS_CAMBIO;
    private int ID_MOV_SALIDAS_CAMBIO;
    private int IDCLIENTESINIVA;

    /**
     * Aplicar cambios:
     * PARA CADA PARTIDA EN FACTURA CON PARTES A CAMBIAR
     *                Verifica que no haya conflictos con devoluciones previas
     *                Checar que la parte y partecambio tenga el precio
     *                Para la partecambio que la existencia - cantidad pedida sea mayor que 0
     *                Crea el detalle de las notas de cambio
     *                Aumentar inventario para la parte recibida
     *                Disminuir inventario patecambio
     *                Crea movimiento por ENTRADAS POR CAMBIO DE MERCANCIAS
     *                Crea movimiento por SALIDAS POR CAMBIO DE MERCANCIAS
     * FIN CADA
     * Crea la nota de cambio
     *
     * Creation date: (11/5/2002 1:50:38 PM)
     * @param param javax.servlet.http.HttpServletRequest
     */
    private void aplicarCambios(javax.servlet.http.HttpServletRequest request, Bean93 bean93, Connection con, HttpSession ses_usr)
            throws Exception {
        int[] cantidad = new int[bean93.getVPartidasSize()];

        int devueltas;
        float subtotal = 0;
        float montodescuento = 0;
        float iva = 0;
        float montoiva = 0;
        float total;
        float importe;
        int error = 0;
        int cantidadTotal = 0;
        FacturaDAO fac;

        // Datos para imprimir
        Bean93p bean93p = new Bean93p();
        Vector v = new Vector();
        Bean93Print bean93Print = new Bean93Print();

        dbDEVUELTAS de = new dbDEVUELTAS();
        dbNOTACAMBIODETALLE ncd = new dbNOTACAMBIODETALLE();
        dbNOTADECAMBIO nc = new dbNOTADECAMBIO();
        dbCTAXCOBRAR cxc = new dbCTAXCOBRAR();
        dbINVENTARIO inv = new dbINVENTARIO();
        dbCONFIGURACIONPVA configuracionpva = new dbCONFIGURACIONPVA();
        dbINVENTARIO inventario = new dbINVENTARIO();
        dbMOVIMIENINVENTARIO movimiento = new dbMOVIMIENINVENTARIO();

        de.setConnection(con);
        ncd.setConnection(con);
        nc.setConnection(con);
        cxc.setConnection(con);
        inv.setConnection(con);
        configuracionpva.setConnection(con);
        inventario.setConnection(con);
        movimiento.setConnection(con);

        configuracionpva.setIidconfpva(ID_CONFIGPVA);
        configuracionpva.load();

        String hoy = org.nibble.util.Fecha.getCurrentDate("yyyyMMddHHmmss");
        Integer userInt = (Integer) ses_usr.getAttribute("iidusuario");
        int iidusuario = userInt.intValue();

        int notadecambio = configuracionpva.getInumeroncm();

        // carga cantidades desde el request
        for (int i = 0; i < bean93.getVPartidasSize(); i++) {
            if ((request.getParameter("cantidad" + i) != null) && (request.getParameter("cantidad" + i).length() > 0)) {
                cantidad[i] = new Integer(request.getParameter("cantidad" + i)).intValue();
            }

            cantidadTotal += cantidad[i];
        }

        if (cantidadTotal == 0) {
            throw new Exception("Todas las cantidades estan en 0");
        }

        // Para  las cantidades mayores a 0 checa la cantidad devuelta
        for (int i = 0; i < bean93.getVPartidasSize(); i++) {
            if (cantidad[i] > 0) {
                devueltas = de.getDevueltasByFacturaParte(
                        bean93.getIidfactura(), bean93.getLiidparte(i), bean93.getIidproveedor(i));

                if ((bean93.getIcantidadproducto(i) - devueltas) < cantidad[i]) {
                    bean93.setDevueltas(i, devueltas);
                    error = 1;
                }
            }
        }

        // Checar que tenga una parte cambio y el precio sea el mismo 
        for (int i = 0; i < bean93.getVPartidasSize(); i++)
            if (cantidad[i] > 0) {
                if ((bean93.getLiidpartecambio(i) == 0) || (bean93.getIidproveedorcambio(i) == 0)) {
                    error = 2;
                }

                if (bean93.getDecprecioventa(i) != bean93.getDecpreciocambio(i)) {
                    error = 3;
                }

                
            }

        // Checar que la existencia - cantidad pedida sea mayor que 0
        if (error == 0) {
            for (int i = 0; i < bean93.getVPartidasSize(); i++)
                if (cantidad[i] > 0) {
                    inventario.setLiidparte(bean93.getLiidpartecambio(i));
                    inventario.setIidproveedor(bean93.getIidproveedorcambio(i));
                    inventario.load();

                    if ((inventario.getIexistencia() - cantidad[i]) < 0) {
                        throw new Exception("Imposible sutir la parte " + bean93.getVchnumparte(i));
                    }
                }
        }

        if (error == 1) {
            bean93.setMsg(
                "No es posible devolver la cantidad la cantidad de articulos indicada de las partes seleccionadas ya que existen conflictos con devoluciones previas");
        }

        if (error == 2) {
            bean93.setMsg("Debe seleccionar una parte para el cambio para cada partida con cantidad a cambiar");
        }

        if (error == 3) {
            bean93.setMsg("La parte de la partida no tiene el mismo precio que la parte de cambio");
        }

        // Procede en caso de poderse realizar la operaci�n
        if (error == 0) {
            fac = bean93.getFactura();

            for (int i = 0; i < bean93.getVPartidasSize(); i++)
                if (cantidad[i] > 0) {
                    // Crea el detalle de las notas de cambio
                    ncd.setIidnotacambio(notadecambio);
                    ncd.setLiidparte(bean93.getLiidparte(i));
                    ncd.setIidproveedor(bean93.getIidproveedor(i));
                    ncd.setIcantidadproducto(cantidad[i]);
                    ncd.setDecpreciolista(bean93.getDecprecioventa(i));
                    ncd.setIidnivel(bean93.getIidnivel(i));
                    ncd.setDeccostoproducto(bean93.getDeccostoproducto(i));
                    ncd.setDecivaclasific(bean93.getDecivaclasific(i));
                    ncd.setDecimportedevol(importe = cantidad[i] * bean93.getDecprecioventa(i));
                    ncd.create();

                    // Aumentar inventario para la parte recibida
                    inventario.setLiidparte(bean93.getLiidparte(i));
                    inventario.setIidproveedor(bean93.getIidproveedor(i));
                    inventario.load();
                    inventario.setIexistencia(inventario.getIexistencia() + cantidad[i]);
                    inventario.store();

                    // Disminuir inventario 
                    inventario.setLiidparte(bean93.getLiidpartecambio(i));
                    inventario.setIidproveedor(bean93.getIidproveedorcambio(i));
                    inventario.load();
                    inventario.setIexistencia(inventario.getIexistencia() - cantidad[i]);
                    inventario.store();

                    // Crea movimiento por ENTRADAS POR CAMBIO DE MERCANCIAS
                    movimiento.setLiidparte(bean93.getLiidparte(i));
                    movimiento.setIidproveedor(bean93.getIidproveedor(i));
                    movimiento.setTitipomov(ID_MOV_ENTRADAS_CAMBIO);
                    movimiento.setTisfechamov(hoy);
                    movimiento.setDeccostob100(bean93.getDecpreciolista(i));
                    movimiento.setDecporciva(bean93.getDecivaclasific(i));
                    movimiento.setDecprecioventa((bean93.getDecprecioventa(i) * (100 - fac.getDecdescglobal())) / 100);
                    movimiento.setIidreferencia(notadecambio);
                    movimiento.setIidusuario(iidusuario);
                    movimiento.setIcantidad(cantidad[i]);
                    movimiento.create();

                    // Crea movimiento por SALIDAS POR CAMBIO DE MERCANCIAS
                    movimiento.setLiidparte(bean93.getLiidpartecambio(i));
                    movimiento.setIidproveedor(bean93.getIidproveedorcambio(i));
                    movimiento.setTitipomov(ID_MOV_SALIDAS_CAMBIO);
                    movimiento.setTisfechamov(hoy);
                    movimiento.setDeccostob100(bean93.getDecpreciolista(i));
                    movimiento.setDecporciva(bean93.getDecivaclasific(i));
                    movimiento.setDecprecioventa((bean93.getDecprecioventa(i) * (100 - fac.getDecdescglobal())) / 100);
                    movimiento.setIidreferencia(notadecambio);
                    movimiento.setIidusuario(iidusuario);
                    movimiento.setIcantidad(cantidad[i]);
                    movimiento.create();

                    // bean para impirmir
                    bean93Print = new Bean93Print();
                    bean93Print.setVchnumparte(bean93.getVchnumparte(i));
                    bean93Print.setVchnumparteCambio(bean93.getVchnumpartecambio(i));
                    bean93Print.setVchnivel(bean93.getVchnivel(i));
                    bean93Print.setIcantidad(cantidad[i]);
                    bean93Print.setDecprecio(bean93.getDecprecioventa(i));
                    bean93Print.setDecimporte(importe);
                    v.addElement(bean93Print);

                    iva = bean93.getDecivaclasific(i);

                    subtotal += (cantidad[i] * bean93.getDecprecioventa(i));
                }

            if (bean93.isMostradorSinIVA()) {
                total = subtotal;
            } else {
                montodescuento = Calcula.redondeo(subtotal * (fac.getDecdescglobal() / 100));
                montoiva = Calcula.redondeo((subtotal - montodescuento) * (bean93.getIva() / 100));
                total = Calcula.redondeo(subtotal - montodescuento + montoiva);
            }

            // Crea la nota de cambio
            nc.setIidnotacambio(notadecambio);
            nc.setIidnocliente(fac.getIidnocliente());
            nc.setIidusuario(iidusuario);
            nc.setIidfactura(fac.getIidfactura());
            nc.setTisfechanotacambio(hoy);
            nc.setDecimporte(subtotal);
            nc.setDecivaimporte(montoiva);
            nc.setTitipomov(ID_TIPOMOVIMIENTO);
            nc.setDecporcdesc(fac.getDecdescglobal());
            nc.setDecmontodesc(montodescuento);
            nc.create();

            //Bean para imprimir
            bean93p.setCliente(bean93.getCliente());
            bean93p.setFactura(bean93.getFactura());
            bean93p.setFecha(hoy);
            bean93p.setIDCLIENTESINIVA(IDCLIENTESINIVA);
            bean93p.setReferencia(notadecambio);
            bean93p.setIidusuario(iidusuario);
            bean93p.setIva(iva);
            bean93p.setVPartidas(v);
            ses_usr.setAttribute("bean", bean93p);
            ses_usr.setAttribute("popupImprimir", new Boolean(true));

            configuracionpva.setInumeroncm(notadecambio + 1);
            configuracionpva.store();

            float ivaTemp = bean93.getIva();
            boolean bit = bean93.isBitutilsobreventa();
            String username = bean93.getUsername();
            bean93 = null;
            bean93 = new Bean93();
            bean93.setIva(ivaTemp);
            bean93.setBitutilsobreventa(bit);
            bean93.setUsername(username);
            ses_usr.setAttribute("bean93", bean93);
        }
    }

    /**
     * ASIGNA A PARTIDA, la parte y proveedor seleccionado
     *
     * Creation date: (11/5/2002 2:52:54 PM)
     *
     */
    private void AsignarParteCambio(javax.servlet.http.HttpServletRequest request, Bean93 bean93, HttpSession ses_usr)
            throws Exception {
        int partida = 0;
        int i = 0;

        if ((request.getParameter("partida") != null) && (request.getParameter("partida").length() > 0)) {
            partida = new Integer(request.getParameter("partida")).intValue();
        }

        if ((request.getParameter("iidproveedor") != null) && (request.getParameter("iidproveedor").length() > 0)) {
            i = new Integer(request.getParameter("iidproveedor")).intValue();
        }

        Bean93d bean93d = (Bean93d) ses_usr.getAttribute("bean93d");

        bean93.parteCambio[partida].setParte(bean93d.getParte());
        bean93.parteCambio[partida].setProveedor(bean93d.getProveedor(i));
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
     * DOCUMENT ME!
     */
    public void init() {
		idModulo = (getServletConfig().getInitParameter("idModulo") != null)
						? new Integer(getServletConfig().getInitParameter("idModulo")).intValue() : (-1);        
        ID_CONFIG = new Integer(getServletConfig().getInitParameter("ID_CONFIG")).intValue();
        ID_CONFIGPVA = new Integer(getServletConfig().getInitParameter("ID_CONFIGPVA")).intValue();
        ID_TIPOMOVIMIENTO = new Short(getServletConfig().getInitParameter("ID_TIPOMOVIMIENTO")).shortValue();
        TIPOMOV_FAC = new Short(getServletConfig().getInitParameter("TIPOMOV_FAC")).shortValue();
        TIPOMOV_NCR = new Short(getServletConfig().getInitParameter("TIPOMOV_NCR")).shortValue();
        ID_MOV_ENTRADAS_CAMBIO = new Short(getServletConfig().getInitParameter("ID_MOV_ENTRADAS_CAMBIO")).shortValue();
        ID_MOV_SALIDAS_CAMBIO = new Short(getServletConfig().getInitParameter("ID_MOV_SALIDAS_CAMBIO")).shortValue();
        IDCLIENTESINIVA = new Integer(getServletConfig().getInitParameter("IDCLIENTESINIVA")).intValue();
    }

    /**
     * DOCUMENT ME!
     *
     * @param request DOCUMENT ME!
     * @param bean93 DOCUMENT ME!
     *
     * @throws Exception DOCUMENT ME!
     */
    private void initial(javax.servlet.http.HttpServletRequest request, Bean93 bean93, Connection con, HttpSession ses_usr)
            throws Exception {
        dbCONFIGSISTEMA configsistema = new dbCONFIGSISTEMA();

        configsistema.setConnection(con);

        configsistema.setIidconfsistema(ID_CONFIG);

        configsistema.load();

        // Carga a bean IVA, BIT UTILIDAD SOBRE VENTA y NOMBRE DE USUARIO
        bean93.setIva(configsistema.getDecivaaplicable());
        bean93.setBitutilsobreventa(configsistema.getBitutilsobreventa());
        bean93.setUsername((String) ses_usr.getAttribute("username"));

        configsistema = null;
    }

    /**
     * Carga Factura y datos del cliente
     *
     * Creation date: (11/5/2002 1:50:38 PM)
     * @param param javax.servlet.http.HttpServletRequest
     */
    private void loadFactura(javax.servlet.http.HttpServletRequest request, Bean93 bean93, Connection con)
            throws Exception {
        FacturaDAO factura = new FacturaDAO();
        dbrCLIENTE cliente = new dbrCLIENTE();
        dbrFACTURADETALLEs dbrfacturadetalles = new dbrFACTURADETALLEs();
        dbCTAXCOBRAR cxc = new dbCTAXCOBRAR();

        int iidfactura = 0;

        if ((request.getParameter("iidfactura") != null) && (request.getParameter("iidfactura").length() > 0)) {
            iidfactura = new Integer(request.getParameter("iidfactura")).intValue();
        }

        factura.setConnection(con);
        cliente.setConnection(con);
        cxc.setConnection(con);

        dbrfacturadetalles.setConnection(con);

        factura.setIidfactura(iidfactura);
        factura.load();

        cxc.setIiddoctoorigen(iidfactura);
        cxc.setTiidtipodocto(TIPOMOV_FAC);
        cxc.setTiconsecsubdocto(1);

        // si la factura no tiene su ctaxcobrar correspondinte es ya se cancelo
        try {
            cxc.load();
        } catch (Exception e) {
            throw new Exception("La factura ya ha sido cancelada previamente");
        }

        bean93.setFactura(factura);

        cliente.setIidnocliente(factura.getIidnocliente());
        cliente.load();

        bean93.setCliente(cliente);

        if (cliente.getIidnocliente() == IDCLIENTESINIVA) {
            bean93.setMostradorSinIVA(true);
        }

        dbrfacturadetalles.findByIidfactura(iidfactura);

        int size = dbrfacturadetalles.getResult().size();

        Bean93PartidaParte partidaparte;
        bean93.setVPartidas(dbrfacturadetalles.getResult());

        Bean93PartidaParte[] partidasParte = new Bean93PartidaParte[size];

        for (int i = 0; i < size; i++)
            partidasParte[i] = new Bean93PartidaParte();

        bean93.setParteCambio(partidasParte);
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
    private String loadParte(javax.servlet.http.HttpServletRequest request, Bean93 bean93, Connection con, HttpSession ses_usr)
            throws Exception {
		String result = null;            	
        long liidparte = 0;
        int count = 0;

        dbrPARTEs partesClasi = new dbrPARTEs();
        dbrPARTE parte = new dbrPARTE();
        dbrPROVEEDORs proveedores = new dbrPROVEEDORs();

        parte.setConnection(con);
        proveedores.setConnection(con);

        Bean93d bean93d = new Bean93d();

        if ((request.getParameter("liidparte") != null) && (request.getParameter("liidparte").length() > 0)) {
            liidparte = new Long(request.getParameter("liidparte")).longValue();
        }

        String prefijo = request.getParameter("prefijo");
        String numero = request.getParameter("numero");
        String sufijo = request.getParameter("sufijo");
        String medida = request.getParameter("medida");

        String vchnumparte = general.Help.getVchnumparte(prefijo, numero, sufijo, medida);
        String vchnumpartePattern = general.Help.getVchnumparte("", "", "", "");

        if (liidparte != 0) {
            parte.setLiidparte(liidparte);
            parte.load();

            //    bean91.setParte(parte);
        } else {
            // Consulta cuanta partes con vchnumparte
            partesClasi.setConnection(con);

            if (!vchnumparte.equals(vchnumpartePattern)) {
                count = partesClasi.getCountByVchnumparteEquival(vchnumparte.toString());
            }

            // No hay ninguna parte con un vhcnumparte
            

            if (count == 0) {
                // Carga combos de Categoria,Grupo,Linea,Marca
                BeanHelper82 hlp82 = new BeanHelper82();
                hlp82.getData(con);
                ses_usr.setAttribute("bean82", hlp82.getBean82());
                result = "/jsp/9_3d.jsp";
            }
            // load parte con el vchnumparte y Proveedores
            else if (count == 1) {
                parte.setVchnumparteequival(vchnumparte);
                parte.loadByVchnumparteEquival();
                bean93d.setParte(parte);
                bean93d.setVProveedores(
                    proveedores.findExistenciasProveedor(
                        parte.getLiidparte(), parte.getIidcategoria(), parte.getIidgrupo(), parte.getIidlinea(), parte.getIidmarca()));
                ses_usr.setAttribute("bean93d", bean93d);
                result = "/jsp/9_3c.jsp";
            } else if (count > 1) {
                // Llenar beans con resultado
                Bean91d bean91d = new Bean91d();

                partesClasi.findByPar(0, 0, 0, 0, vchnumparte, false);
                bean91d.setVPartes(partesClasi.getResult());

                ses_usr.setAttribute("bean91d", bean91d);
                result = "/jsp/9_3f.jsp";
            }
        }
        return result;
    }

    /**
     * Una vez seleccionado las partes de una aplicacion
     * Se ejecuta este metodo que carga los proveedores para cada parte
     *
     * Creation date: (11/5/2002 1:50:38 PM)
     * @param param javax.servlet.http.HttpServletRequest
     */
    private String loadParteProveedores(javax.servlet.http.HttpServletRequest request, Connection con, HttpSession ses_usr)
            throws Exception {
        long liidparte = 0;

        dbrPARTE parte = new dbrPARTE();
        dbrPROVEEDORs proveedores = new dbrPROVEEDORs();

        parte.setConnection(con);
        proveedores.setConnection(con);

        Bean93d bean93d = new Bean93d();

        if ((request.getParameter("liidparte") != null) && (request.getParameter("liidparte").length() > 0)) {
            liidparte = new Long(request.getParameter("liidparte")).longValue();
        }

        parte.setLiidparte(liidparte);
        parte.load();
        bean93d.setParte(parte);
        bean93d.setVProveedores(
            proveedores.findExistenciasProveedor(
                parte.getLiidparte(), parte.getIidcategoria(), parte.getIidgrupo(), parte.getIidlinea(), parte.getIidmarca()));
        ses_usr.setAttribute("bean93d", bean93d);
        return "/jsp/9_3c.jsp";
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
			if (!bu.getAcceso(idModulo,con))throw new Exception("No tiene permiso a este m�dulo");
        	
			String fechaini = new String();
			String fechafin = new String();
			Bean93 bean93 = new Bean93();
            int accion = 1;
/*
            initConnection();
            seguridad.setConnection(con);
            if (!seguridad.tienePermiso(sistema, new Integer(ses_usr.getAttribute("grupo").toString()).intValue(), idModulo)) {
                throw new Exception("No tiene permiso a este m�dulo");
            }
*/
            if (request.getParameter("accion") != null) {
                accion = new Integer(request.getParameter("accion")).intValue();
            }

            if (accion != 1) {
                bean93 = (Bean93) ses_usr.getAttribute("bean93");
            } else {
                ses_usr.setAttribute("bean93", bean93);
            }

            ses_usr.setAttribute("popupImprimir", new Boolean(false));

            // reset las cantidades recibidas y mensajes
            bean93.setMsg("");

            for (int i = 0; i < bean93.getVPartidasSize(); i++)
                bean93.setDevueltas(i, 0);

            switch (accion) {
                // 9.2 Mostrar patalla inicial
                case 1:
                    initial(request, bean93, con, ses_usr);
                    strAddress = "/jsp/9_3.jsp";

                    break;

                // 9.2 Cargar cliente y partidas para la factura
                case 2:
                    loadFactura(request, bean93, con);
                    strAddress = "/jsp/9_3.jsp";

                    break;

                // Obtener Cantidad devuelta para cada partida
                case 3:
                    aplicarCambios(request, bean93, con, ses_usr);
                    strAddress = "/jsp/9_3.jsp";

                    break;

                case 4:
                    strAddress = "/jsp/9_3b.jsp";

                    break;

                case 5:
					strAddress = loadParte(request, bean93, con, ses_usr);

                    break;

                case 6:
                    resultadoPartes(request, bean93, con, ses_usr);
                    strAddress = "/jsp/9_3f.jsp";

                    break;

                case 7:
					strAddress = loadParteProveedores(request, con, ses_usr);

                    break;

                case 8:
                    AsignarParteCambio(request, bean93, ses_usr);
                    strAddress = "/jsp/9_3l.jsp";

                    break;

                case 17:
                    strAddress = "/jsp/9_3.jsp";

                    break;
            }
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
     * llamado desde el POPUP de criterios: Busca partes.
     *
     * Creation date: (11/5/2002 1:50:38 PM)
     * @param param javax.servlet.http.HttpServletRequest
     */
    private void resultadoPartes(javax.servlet.http.HttpServletRequest request, Bean93 bean93, Connection con, HttpSession ses_usr)
            throws Exception {
        int iidcategoria = 0;

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
        partes.findByPar(
            iidcategoria, iidgrupo, iidlinea, iidmarca, request.getParameter("prefijo"), request.getParameter("numero"),
            request.getParameter("sufijo"), request.getParameter("medida"), false);

        Bean91d bean91d = new Bean91d();
        bean91d.setVPartes(partes.getResult());
        ses_usr.setAttribute("bean91d", bean91d);
    }
}
