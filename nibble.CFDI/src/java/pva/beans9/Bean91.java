package pva.beans9;

import dbbeans.niveldeprecio.*;

import dbrbeans.clientepaisestadomunicipio.*;

import dbrbeans.parteclasificacion.*;

import dbrbeans.proveedorinventario.*;

import general.Help;
import java.util.Date;

import org.nibble.service.mostrador.Calcula;

import org.nibble.util.Formato;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class Bean91 implements Cloneable {
    
    private static Log logger = LogFactory.getLog(Bean91.class);
    
    private java.lang.String Usuario;
    private dbrCLIENTE cliente;
    private boolean popupCliente;
    private boolean popupParte;
    private boolean popupBuscarParte;
    private dbrPARTE parte;
    private boolean popupAplicacion;
    private boolean popupBuscaAplicacion;
    private float iva;
    private float descuento;
    private float subtotal;
    private float total;
    private float montodescuento;
    private java.lang.String fechaVencimiento;
    private boolean popupLogin;
    private java.lang.String msg;
    private float montoiva;
    private java.util.Vector vNiveles;
    private java.util.Vector vPartidas;
    private java.util.Vector vProveedores;
    private boolean Bitutilsobreventa;
    private boolean mostradorSinIVA;
    private boolean mostradorConIVA;
    private boolean popupClienteConIVA;
    private boolean popupImprimir;
    private Date date;
    private int folio;
    
    private String vchformapago;
    private String vchmetodopago;
    private String vchcuentapago;

    /**
     * Bean91 constructor comment.
     */
    public Bean91() {
        super();
        cliente = new dbrCLIENTE();
        parte = new dbrPARTE();
        vNiveles = new java.util.Vector();
        vPartidas = new java.util.Vector();
        vProveedores = new java.util.Vector();
        msg = new String();
    }

    /**
     * Insert the method's description here.
     * Creation date: (11/1/2002 12:03:06 PM)
     * @return int
     */
    public void addPartida(Bean91Partidas partida) {
        vPartidas.add(partida);
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    /**
     * Insert the method's description here.
     * Creation date: (11/1/2002 12:03:06 PM)
     * @return int
     */
    public String getClasificacion() {
        return (parte.getVchcategoria() == null) ? ""
                                                 : (parte.getVchcategoria() +
        "\n" + parte.getVchgrupo() + "\n" + parte.getVchlinea() + "\n" +
        parte.getVchmarca());
    }

    /**
     * Insert the method's description here.
     * Creation date: (11/1/2002 6:17:54 PM)
     * @return dbrbeans.clientepaisestadomunicipio.dbrCLIENTE
     */
    public dbrbeans.clientepaisestadomunicipio.dbrCLIENTE getCliente() {
        return cliente;
    }


    public String getCondicionesComerciales() {
        if (cliente.getIidcatcliente() == 0) {
            return "";
        }

		/* Autor: Victor Romero
		 * Modificacion: se Elimina las palabras CAT. CLIENTE 
		 * Fecha de Modificacion: 29-01-2004
		 * return "CAT. CLIENTE:" + cliente.getVchcategoriades() +
		 * "\nNIVEL DE PRECIO:" + cliente.getVchniveldes() +
         * "\nVIA DE EMBARQUE: " + cliente.getVchviades();           
		 */         
         
		return " " + cliente.getVchcategoriades();
        
    }

    /**
     * Insert the method's description here.
     * Creation date: (11/1/2002 12:03:06 PM)
     * @return int
     */
    public float getCredito() {
        return cliente.getDeclimitecredito() - cliente.getDeccreditutilizado();
    }

    /**
     * Insert the method's description here.
     * Creation date: (11/1/2002 12:03:06 PM)
     * @return int
     */
    public String getCreditoSTR() {
        return Formato.formateoNumerico(cliente.getDeclimitecredito() -
            cliente.getDeccreditutilizado(), "#########0.00");
    }

    public String getRFCCliente(){
        String rfccliente = "";
        rfccliente = (cliente.getVchrfc() == null) ? "" : cliente.getVchrfc();
        return rfccliente;
    }

    /**
     * Insert the method's description here.
     * Creation date: (11/1/2002 12:03:06 PM)
     * @return int
     */
    public String getDatosGenerales() {
        StringBuffer datos = new StringBuffer();

        if (cliente.getIidcatcliente() == 0) {
            return "";
        }

        datos.append("Nombre: ");
        datos.append((cliente.getVchnombre() == null) ? ""
                                                      : cliente.getVchnombre());
        datos.append("\nRazon social: ");
        datos.append((cliente.getVchrazonsocial() == null) ? ""
                                                           : cliente.getVchrazonsocial());
        datos.append("\nRFC: ");
        datos.append((cliente.getVchrfc() == null) ? "" : cliente.getVchrfc());
        datos.append("\nCALLE: ");
        datos.append((cliente.getVchcalle() == null) ? "" : cliente.getVchcalle());

        datos.append("\nNUM. EXT: ");
        
        datos.append((cliente.getVchnumexterior() == null) ? "" : cliente.getVchnumexterior());

        datos.append("\nNUM. INT: ");
        datos.append((cliente.getVchnuminterior() == null) ? "" : cliente.getVchnuminterior());
        datos.append("\nCOLONIA: ");
        datos.append((cliente.getVchcolonia() == null) ? ""
                                                       : cliente.getVchcolonia());
        datos.append("\nLOCALIDAD: ");
        datos.append((cliente.getVchlocalidad() == null) ? "" : cliente.getVchlocalidad());
        datos.append("\nPAIS: ");
        datos.append((cliente.getVchpaisdes() == null) ? ""
                                                       : cliente.getVchpaisdes());
        datos.append("\nESTADO: ");
        datos.append((cliente.getVchestadodes() == null) ? ""
                                                         : cliente.getVchestadodes());
        datos.append("\nMUNICIPIO: ");
        datos.append((cliente.getVchmunicipiodes() == null) ? ""
                                                            : cliente.getVchmunicipiodes());

        return datos.toString().toUpperCase();
    }

    /**
     * Insert the method's description here.
     * Creation date: (11/1/2002 12:03:06 PM)
     * @return int
     */
    public float getDeccreditutilizado() {
        return cliente.getDeccreditutilizado();
    }

    /**
     * Insert the method's description here.
     * Creation date: (11/4/2002 5:22:19 PM)
     * @param newVNiveles java.util.Vector
     */
    public float getDecporccomision(int index) {
        dbNIVELDEPRECIO a = (dbNIVELDEPRECIO) vNiveles.elementAt(index);

        return a.getDecporccomision();
    }

    /**
     * Insert the method's description here.
     * Creation date: (11/1/2002 12:03:06 PM)
     * @return int
     */
    public float getDecprecio() {
        return parte.getDecprecio();
    }

    /**
     * Insert the method's description here.
     * Creation date: (11/1/2002 12:03:06 PM)
     * @return int
     */
    public float getDecpreciob100() {
        return parte.getDecpreciob100();
    }

    /**
     * Insert the method's description here.
     * Creation date: (11/4/2002 5:22:19 PM)
     * @param newVNiveles java.util.Vector
     */
    public float getDecutilidad(int index) {
        dbNIVELDEPRECIO a = (dbNIVELDEPRECIO) vNiveles.elementAt(index);

        return a.getDecutilidad();
    }

    /**
     * Insert the method's description here.
     * Creation date: (11/7/2002 5:57:35 PM)
     * @return float
     */
    public float getDescuento() {
        return descuento;
    }

    /**
     * Insert the method's description here.
     * Creation date: (11/7/2002 5:57:35 PM)
     * @return float
     */
    public String getDescuentoSTR() {
        return Formato.formateoNumerico(descuento, "#########0.00");
    }

    /**
     * Insert the method's description here.
     * Creation date: (11/1/2002 12:03:06 PM)
     * @return int
     */
    public String getDiasDepago() throws Exception {
        String hoy = org.nibble.util.Fecha.getCurrentDate("yyyyMMddHHmmss");

        return getDiasDepago(hoy);
    }

    /**
     * Insert the method's description here.
     * Creation date: (11/11/2002 11:57:12 AM)
     * @return java.lang.String
     */
    public java.lang.String getFechaVencimiento() {
        return fechaVencimiento;
    }

    /**
     * Insert the method's description here.
     * Creation date: (11/1/2002 12:03:06 PM)
     * @return int
     */
    public String getFichatecnica() {
        return parte.getFichatecnica();
    }

    /**
     * Insert the method's description here.
     * Creation date: (11/4/2002 5:22:19 PM)
     * @param newVNiveles java.util.Vector
     */
    public int getIidnivel(int index) {
        dbNIVELDEPRECIO a = (dbNIVELDEPRECIO) vNiveles.elementAt(index);

        return a.getIidnivel();
    }

    /**
     * Insert the method's description here.
     * Creation date: (11/4/2002 5:22:19 PM)
     * @param newVNiveles java.util.Vector
     */
    public int getIidnivelCliente() {
        return cliente.getIidnivel();
    }

    /**
     * Insert the method's description here.
     * Creation date: (11/1/2002 12:03:06 PM)
     * @return int
     */
    public int getIidnocliente() {
        return cliente.getIidnocliente();
    }

    /**
     * Insert the method's description here.
     * Creation date: (11/7/2002 12:47:08 PM)
     * @return float
     */
    public float getIva() {
        return iva;
    }

    /**
     * Insert the method's description here.
     * Creation date: (11/7/2002 12:47:08 PM)
     * @return float
     */
    public String getIvaSTR() {
        return Formato.formateoNumerico(iva, "#########0.00");
    }

    /**
     * Insert the method's description here.
     * Creation date: (11/1/2002 12:03:06 PM)
     * @return int
     */
    public long getLiidparte() {
        return parte.getLiidparte();
    }

    /**
     * Insert the method's description here.
     * Creation date: (11/1/2002 12:03:06 PM)
     * @return int
     */
    public String getMedida() {
        if (parte.getVchnumparteequival() != null) {
            return Help.getMedida(parte.getVchnumparteequival());
        }

        return "";
    }

    /**
     * Insert the method's description here.
     * Creation date: (11/8/2002 1:16:29 PM)
     * @return float
     */
    public float getMontodescuento() {
        return montodescuento;
    }

    /**
     * Insert the method's description here.
     * Creation date: (11/8/2002 1:16:29 PM)
     * @return float
     */
    public String getMontodescuentoSTR() {
        return Formato.formateoNumerico(montodescuento, "#########0.00");
    }

    /**
     * Insert the method's description here.
     * Creation date: (11/13/2002 2:24:22 PM)
     * @return float
     */
    public float getMontoiva() {
        return montoiva;
    }

    /**
     * Insert the method's description here.
     * Creation date: (11/13/2002 2:24:22 PM)
     * @return float
     */
    public String getMontoivaSTR() {
        return Formato.formateoNumerico(montoiva, "#########0.00");
    }

    /**
     * Insert the method's description here.
     * Creation date: (11/13/2002 12:25:24 PM)
     * @return java.lang.String
     */
    public java.lang.String getMsg() {
        return msg;
    }

    /**
     * Insert the method's description here.
     * Creation date: (11/1/2002 12:03:06 PM)
     * @return int
     */
    public String getNumero() {
        if (parte.getVchnumparteequival() != null) {
            return Help.getNumero(parte.getVchnumparteequival());
        }

        return "";
    }

    /**
     * Insert the method's description here.
     * Creation date: (11/4/2002 1:03:09 PM)
     * @return dbrbeans.parteclasificacion.dbrPARTE
     */
    public dbrbeans.parteclasificacion.dbrPARTE getParte() {
        return parte;
    }

    /**
     * Insert the method's description here.
     * Creation date: (11/1/2002 12:03:06 PM)
     * @return int
     */
    public float getPrecio() {
        for (int i = 0; i < vNiveles.size(); i++) {
            dbNIVELDEPRECIO a = (dbNIVELDEPRECIO) vNiveles.elementAt(i);

            if (a.getIidnivel() == cliente.getIidnivel()) {
                Calcula.precio(parte.getDecprecio(), a.getDecutilidad(),
                    Bitutilsobreventa, mostradorSinIVA, iva);
            }
        }

        return 0;
    }

    /**
     * Calcula el precio de la parte deacuerdo al nivel y al proveedor favorito y la utilidad sobre costo y sobre venta.
     * Creation date: (11/1/2002 12:03:06 PM)
     * @return int
     */
    public String getPrecioSTR() {
        float precio = 0;
        String importe;

        if (getProveeSize() > 0) {
            precio = getProveeDecprecio(0);
        }
        System.out.println("EL PRECIO ES" +precio);
        for (int i = 1; i < vProveedores.size(); i++)
            if (getProveeBitprovefavorito(i)) {
                precio = getProveeDecprecio(i);
                System.out.println("EL PRECIO de PREVEEDOR FAVORITO ES" +precio);
                break;
            }

        for (int i = 0; i < vNiveles.size(); i++) {
            dbNIVELDEPRECIO a = (dbNIVELDEPRECIO) vNiveles.elementAt(i);
            
            if (a.getIidnivel() == cliente.getIidnivel()) {
                System.out.println("EL PRECIO ES NIVELES" +precio);
                return Formato.formateoNumerico(Calcula.precio(precio, a.getDecutilidad(), Bitutilsobreventa, mostradorSinIVA, iva), "#########0.00");
            }
        }

        return "0.00";
    }

    /**
     * Insert the method's description here.
     * Creation date: (11/1/2002 12:03:06 PM)
     * @return int
     */
    public String getPrefijo() {
        if (parte.getVchnumparteequival() != null) {
            return Help.getPrefijo(parte.getVchnumparteequival());
        }

        return "";
    }

    /**
     * Insert the method's description here.
     * Creation date: (11/4/2002 5:22:19 PM)
     * @param newVNiveles java.util.Vector
     */
    public boolean getProveeBitprovefavorito(int index) {
        dbrPROVEEDOR a = (dbrPROVEEDOR) vProveedores.elementAt(index);

        return a.isBitprovefavorito();
    }

    /**
     * Insert the method's description here.
     * Creation date: (11/4/2002 5:22:19 PM)
     * @param newVNiveles java.util.Vector
     */
    public float getProveeDecprecio(int index) {
        dbrPROVEEDOR a = (dbrPROVEEDOR) vProveedores.elementAt(index);
        System.out.println("PRECIO BASE 100 "+parte.getDecpreciob100() );
        System.out.println("FACTOR COSTO VENTA "+a.getDecfactorcostovent() );
        return Calcula.redondeo(parte.getDecpreciob100() * a.getDecfactorcostovent());
    }

    /**
     * Insert the method's description here.
     * Creation date: (11/4/2002 5:22:19 PM)
     * @param newVNiveles java.util.Vector
     */
    public int getProveeIexistencia(int index) {
        dbrPROVEEDOR a = (dbrPROVEEDOR) vProveedores.elementAt(index);

        return a.getIexistencia();
    }

    /**
     * Insert the method's description here.
     * Creation date: (11/4/2002 5:22:19 PM)
     * @param newVNiveles java.util.Vector
     */
    public int getProveeIidproveedor(int index) {
        dbrPROVEEDOR a = (dbrPROVEEDOR) vProveedores.elementAt(index);

        return a.getIidproveedor();
    }

    /**
     * Insert the method's description here.
     * Creation date: (11/4/2002 5:22:19 PM)
     * @param newVNiveles java.util.Vector
     */
    public int getProveeSize() {
        return vProveedores.size();
    }

    /**
     * Insert the method's description here.
     * Creation date: (11/4/2002 5:22:19 PM)
     * @param newVNiveles java.util.Vector
     */
    public String getProveeVchrazonsocial(int index) {
        dbrPROVEEDOR a = (dbrPROVEEDOR) vProveedores.elementAt(index);

        return a.getVchrazonsocial();
    }

    /**
     * Insert the method's description here.
     * Creation date: (11/7/2002 6:32:20 PM)
     * @return float
     */
    public float getSubtotal() {
        return subtotal;
    }

    /**
     * Insert the method's description here.
     * Creation date: (11/7/2002 6:32:20 PM)
     * @return float
     */
    public String getSubtotalSTR() {
        return Formato.formateoNumerico(subtotal, "#########0.00");
    }

    /**
     * Insert the method's description here.
     * Creation date: (11/1/2002 12:03:06 PM)
     * @return int
     */
    public String getSufijo() {
        if (parte.getVchnumparte() != null) {
            return Help.getSufijo(parte.getVchnumparteequival());
        }

        return "";
    }

    /**
     * Insert the method's description here.
     * Creation date: (11/7/2002 6:32:48 PM)
     * @return float
     */
    public float getTotal() {
        return total;
    }

    /**
     * Insert the method's description here.
     * Creation date: (11/7/2002 6:32:48 PM)
     * @return float
     */
    public String getTotalSTR() {
        return Formato.formateoNumerico(total, "#########0.00");
    }

    /**
     * Insert the method's description here.
     * Creation date: (11/1/2002 11:20:44 AM)
     * @return java.lang.String
     */
    public java.lang.String getUsuario() {
        return Usuario;
    }

    /**
     * Insert the method's description here.
     * Creation date: (11/1/2002 12:03:06 PM)
     * @return int
     */
    public String getVchdescripciones() {
        return (parte.getVchdescripciones() == null) ? ""
                                                     : parte.getVchdescripciones();
    }

	public String getVchunidaddescripcion() {
		return (parte.getVchunidaddesc() == null) ? ""
													 : parte.getVchunidaddesc();
	}

    /**
     * Insert the method's description here.
     * Creation date: (11/4/2002 5:22:19 PM)
     * @param newVNiveles java.util.Vector
     */
    public String getVchnivel(int index) {
        dbNIVELDEPRECIO a = (dbNIVELDEPRECIO) vNiveles.elementAt(index);

        return a.getVchnivel();
    }

    /**
     * Insert the method's description here.
     * Creation date: (11/1/2002 12:03:06 PM)
     * @return int
     */
    public String getVchnumparte() {
        return parte.getVchnumparteequival();
    }

    /**
     * Insert the method's description here.
     * Creation date: (11/1/2002 12:03:06 PM)
     * @return int
     */
    public String getVchobservaciones() {
        return (parte.getVchobservaciones() == null) ? ""
                                                     : parte.getVchobservaciones();
    }

    /**
     * Insert the method's description here.
     * Creation date: (11/4/2002 5:22:19 PM)
     * @return java.util.Vector
     */
    public java.util.Vector getVNiveles() {
        return vNiveles;
    }

    /**
     * Insert the method's description here.
     * Creation date: (11/4/2002 5:22:19 PM)
     * @param newVNiveles java.util.Vector
     */
    public int getVNivelesSize() {
        return vNiveles.size();
    }

    /**
     * Insert the method's description here.
     * Creation date: (11/4/2002 7:33:00 PM)
     * @return java.util.Vector
     */
    public java.util.Vector getVPartidas() {
        return vPartidas;
    }

    /**
     * Insert the method's description here.
     * Creation date: (11/4/2002 5:22:19 PM)
     * @param newVNiveles java.util.Vector
     */
    public int getVPartidasCantidad(int index) {
        Bean91Partidas a = (Bean91Partidas) vPartidas.elementAt(index);

        return a.getCantidad();
    }

    /**
     * Insert the method's description here.
     * Creation date: (11/4/2002 5:22:19 PM)
     * @param newVNiveles java.util.Vector
     */
    public float getVPartidasDecprecio(int index) {
        Bean91Partidas a = (Bean91Partidas) vPartidas.elementAt(index);

        return a.getDecprecio();
    }

    /**
     * Insert the method's description here.
     * Creation date: (11/4/2002 5:22:19 PM)
     * @param newVNiveles java.util.Vector
     */
    public float getVPartidasDecprecioB100(int index) {
        Bean91Partidas a = (Bean91Partidas) vPartidas.elementAt(index);

        return a.getDecprecioB100();
    }

    /**
     * Insert the method's description here.
     * Creation date: (11/4/2002 5:22:19 PM)
     * @param newVNiveles java.util.Vector
     */
    public String getVPartidasDescripcion(int index) {
        Bean91Partidas a = (Bean91Partidas) vPartidas.elementAt(index);

        return a.getVchdescripcion();
    }

    /**
     * Insert the method's description here.
     * Creation date: (11/4/2002 5:22:19 PM)
     * @param newVNiveles java.util.Vector
     */
    public int getVPartidasExistencia(int index) {
        Bean91Partidas a = (Bean91Partidas) vPartidas.elementAt(index);

        return a.getIexistencia();
    }

    /**
     * Insert the method's description here.
     * Creation date: (11/4/2002 5:22:19 PM)
     * @param newVNiveles java.util.Vector
     */
    public boolean getVPartidasExistenciaMayorCantidad(int index) {
        Bean91Partidas a = (Bean91Partidas) vPartidas.elementAt(index);

        return a.isExistenciaMayorCantidad();
    }

    /**
     * Insert the method's description here.
     * Creation date: (11/4/2002 5:22:19 PM)
     * @param newVNiveles java.util.Vector
     */
    public int getVPartidasIidproveedor(int index) {
        Bean91Partidas a = (Bean91Partidas) vPartidas.elementAt(index);

        return a.getIidproveedor();
    }

    /**
     * Insert the method's description here.
     * Creation date: (11/4/2002 5:22:19 PM)
     * @param newVNiveles java.util.Vector
     */
    public float getVPartidasImporte(int index) {
        Bean91Partidas a = (Bean91Partidas) vPartidas.elementAt(index);

        return a.getImporte();
    }

    /**
     * Insert the method's description here.
     * Creation date: (11/4/2002 5:22:19 PM)
     * @param newVNiveles java.util.Vector
     */
    public String getVPartidasImporteSTR(int index) {
        Bean91Partidas a = (Bean91Partidas) vPartidas.elementAt(index);

        return Formato.formateoNumerico(a.getImporte(), "#########0.00");
    }

    /**
     * Insert the method's description here.
     * Creation date: (11/4/2002 5:22:19 PM)
     * @param newVNiveles java.util.Vector
     */
    public long getVPartidasLiidparte(int index) {
        Bean91Partidas a = (Bean91Partidas) vPartidas.elementAt(index);

        return a.getLiidparte();
    }

    /**
     * Insert the method's description here.
     * Creation date: (11/4/2002 5:22:19 PM)
     * @param newVNiveles java.util.Vector
     */
    public String getVchnumparte(int index) {
        Bean91Partidas a = (Bean91Partidas) vPartidas.elementAt(index);
        return a.getVchnumparte();
    }
    /**
     * Insert the method's description here.
     * Creation date: (11/4/2002 5:22:19 PM)
     * @param newVNiveles java.util.Vector
     */
    public String getVPartidasMedida(int index) {
        Bean91Partidas a = (Bean91Partidas) vPartidas.elementAt(index);

        return a.getMedida();
    }

	public String getVPartidasUnidadDesc(int index) {
		Bean91Partidas a = (Bean91Partidas) vPartidas.elementAt(index);

		return a.getVchunidaddescripcion();
	}

    /**
     * Insert the method's description here.
     * Creation date: (11/4/2002 5:22:19 PM)
     * @param newVNiveles java.util.Vector
     */
    public int getVPartidasNivel(int index) {
        Bean91Partidas a = (Bean91Partidas) vPartidas.elementAt(index);

        return a.getIidnivel();
    }

    /**
     * Insert the method's description here.
     * Creation date: (11/4/2002 5:22:19 PM)
     * @param newVNiveles java.util.Vector
     */
    public String getVPartidasNumero(int index) {
        Bean91Partidas a = (Bean91Partidas) vPartidas.elementAt(index);

        return a.getNumero();
    }

    /**
     * Insert the method's description here.
     * Creation date: (11/4/2002 5:22:19 PM)
     * @param newVNiveles java.util.Vector
     */
    public float getVPartidasPrecioUnitario(int index) {
        Bean91Partidas a = (Bean91Partidas) vPartidas.elementAt(index);
        
        return a.getPrecioUnitario();
    }

    /**
     * Insert the method's description here.
     * Creation date: (11/4/2002 5:22:19 PM)
     * @param newVNiveles java.util.Vector
     */
    public String getVPartidasPrecioUnitarioSTR(int index) {
        Bean91Partidas a = (Bean91Partidas) vPartidas.elementAt(index);
        
        return Formato.formateoNumerico(a.getPrecioUnitario(), "#########0.00");
    }

    /**
     * Insert the method's description here.
     * Creation date: (11/4/2002 5:22:19 PM)
     * @param newVNiveles java.util.Vector
     */
    public String getVPartidasPrefijo(int index) {
        Bean91Partidas a = (Bean91Partidas) vPartidas.elementAt(index);

        return a.getPrefijo();
    }

    /**
     * Insert the method's description here.
     * Creation date: (11/4/2002 5:22:19 PM)
     * @param newVNiveles java.util.Vector
     */
    public int getVPartidasSize() {
        return vPartidas.size();
    }

    /**
     * Insert the method's description here.
     * Creation date: (11/4/2002 5:22:19 PM)
     * @param newVNiveles java.util.Vector
     */
    public String getVPartidasSufijo(int index) {
        Bean91Partidas a = (Bean91Partidas) vPartidas.elementAt(index);

        return a.getSufijo();
    }

    /**
     * Insert the method's description here.
     * Creation date: (11/4/2002 5:22:19 PM)
     * @param newVNiveles java.util.Vector
     */
    public String getVPartidasVchnivel(int index) {
        Bean91Partidas a = (Bean91Partidas) vPartidas.elementAt(index);

        return a.getVchnivel();
    }

    /**
     * Insert the method's description here.
     * Creation date: (11/4/2002 5:22:19 PM)
     * @param newVNiveles java.util.Vector
     */
    public String getVPartidasVchnumparte(int index) {
        Bean91Partidas a = (Bean91Partidas) vPartidas.elementAt(index);

        return a.getVchnumparte();
    }

    /**
     * Insert the method's description here.
     * Creation date: (11/4/2002 5:22:19 PM)
     * @param newVNiveles java.util.Vector
     */
    public String getVPartidasVchrazonsocial(int index) {
        Bean91Partidas a = (Bean91Partidas) vPartidas.elementAt(index);

        return a.getVchrazonsocial();
    }

    public int getVPartidasFolio(int index) {
        Bean91Partidas a = (Bean91Partidas) vPartidas.elementAt(index);

        return a.getFolio();
    }

    public void setVPartidasFolio(int index, int folio) {
        Bean91Partidas a = (Bean91Partidas) vPartidas.elementAt(index);
        a.setFolio(folio);
        vPartidas.setElementAt(a, index);
    }

    /**
     * Insert the method's description here.
     * Creation date: (11/18/2002 7:12:48 PM)
     * @return java.util.Vector
     */
    public java.util.Vector getVProveedores() {
        return vProveedores;
    }

    /**
     * Insert the method's description here.
     * Creation date: (12/5/2002 11:27:03 AM)
     * @return boolean
     */
    public boolean isBitutilsobreventa() {
        return Bitutilsobreventa;
    }

    /**
     * Insert the method's description here.
     * Creation date: (12/5/2002 3:16:09 PM)
     * @return boolean
     */
    public boolean isMostradorConIVA() {
        return mostradorConIVA;
    }

    /**
     * Insert the method's description here.
     * Creation date: (12/5/2002 3:15:47 PM)
     * @return boolean
     */
    public boolean isMostradorSinIVA() {
        return mostradorSinIVA;
    }

    /**
     * Insert the method's description here.
     * Creation date: (11/6/2002 12:51:13 PM)
     * @return boolean
     */
    public boolean isPopupAplicacion() {
        return popupAplicacion;
    }

    /**
     * Insert the method's description here.
     * Creation date: (11/6/2002 3:34:38 PM)
     * @return boolean
     */
    public boolean isPopupBuscaAplicacion() {
        return popupBuscaAplicacion;
    }

    /**
     * Insert the method's description here.
     * Creation date: (11/4/2002 12:58:52 PM)
     * @return boolean
     */
    public boolean isPopupBuscarParte() {
        return popupBuscarParte;
    }

    /**
     * Insert the method's description here.
     * Creation date: (11/1/2002 7:33:34 PM)
     * @return boolean
     */
    public boolean isPopupCliente() {
        return popupCliente;
    }

    /**
     * Insert the method's description here.
     * Creation date: (12/5/2002 4:02:55 PM)
     * @return boolean
     */
    public boolean isPopupClienteConIVA() {
        return popupClienteConIVA;
    }

    /**
     * Insert the method's description here.
     * Creation date: (12/5/2002 6:20:02 PM)
     * @return boolean
     */
    public boolean isPopupImprimir() {
        return popupImprimir;
    }

    /**
     * Insert the method's description here.
     * Creation date: (11/12/2002 1:57:33 PM)
     * @return boolean
     */
    public boolean isPopupLogin() {
        return popupLogin;
    }

    /**
     * Insert the method's description here.
     * Creation date: (11/4/2002 12:57:11 PM)
     * @return boolean
     */
    public boolean isPopupParte() {
        return popupParte;
    }

    /**
     * Insert the method's description here.
     * Creation date: (12/5/2002 11:27:03 AM)
     * @param newBitutilsobreventa boolean
     */
    public void setBitutilsobreventa(boolean newBitutilsobreventa) {
        Bitutilsobreventa = newBitutilsobreventa;
    }

    /**
     * Insert the method's description here.
     * Creation date: (11/1/2002 6:17:54 PM)
     * @param newCliente dbrbeans.clientepaisestadomunicipio.dbrCLIENTE
     */
    public void setCliente(
        dbrbeans.clientepaisestadomunicipio.dbrCLIENTE newCliente) {
        cliente = newCliente;
    }

    /**
     * Insert the method's description here.
     * Creation date: (11/7/2002 5:57:35 PM)
     * @param newDescuento float
     */
    public void setDescuento(float newDescuento) {
        descuento = newDescuento;
    }

    /**
     * Insert the method's description here.
     * Creation date: (11/11/2002 11:57:12 AM)
     * @param newFechaVencimiento java.lang.String
     */
    public void setFechaVencimiento(java.lang.String newFechaVencimiento) {
        fechaVencimiento = newFechaVencimiento;
    }

    /**
     * Insert the method's description here.
     * Creation date: (11/7/2002 12:47:08 PM)
     * @param newIva float
     */
    public void setIva(float newIva) {
        iva = newIva;
    }

    /**
     * Insert the method's description here.
     * Creation date: (11/8/2002 1:16:29 PM)
     * @param newMontodescuento float
     */
    public void setMontodescuento(float newMontodescuento) {
        montodescuento = newMontodescuento;
    }

    /**
     * Insert the method's description here.
     * Creation date: (11/13/2002 2:24:22 PM)
     * @param newMontoiva float
     */
    public void setMontoiva(float newMontoiva) {
        montoiva = newMontoiva;
    }

    /**
     * Insert the method's description here.
     * Creation date: (12/5/2002 3:16:09 PM)
     * @param newMostradorConIVA boolean
     */
    public void setMostradorConIVA(boolean newMostradorConIVA) {
        mostradorConIVA = newMostradorConIVA;
    }

    /**
     * Insert the method's description here.
     * Creation date: (12/5/2002 3:15:47 PM)
     * @param newMostradorSinIVA boolean
     */
    public void setMostradorSinIVA(boolean newMostradorSinIVA) {
        mostradorSinIVA = newMostradorSinIVA;
    }

    /**
     * Insert the method's description here.
     * Creation date: (11/13/2002 12:25:24 PM)
     * @param newMsg java.lang.String
     */
    public void setMsg(java.lang.String newMsg) {
        msg = newMsg;
    }

    /**
     * Insert the method's description here.
     * Creation date: (11/4/2002 1:03:09 PM)
     * @param newParte dbrbeans.parteclasificacion.dbrPARTE
     */
    public void setParte(dbrbeans.parteclasificacion.dbrPARTE newParte) {
        parte = newParte;
    }

    /**
     * Insert the method's description here.
     * Creation date: (11/6/2002 12:51:13 PM)
     * @param newPopupAplicacion boolean
     */
    public void setPopupAplicacion(boolean newPopupAplicacion) {
        popupAplicacion = newPopupAplicacion;
    }

    /**
     * Insert the method's description here.
     * Creation date: (11/6/2002 3:34:38 PM)
     * @param newPopupBuscaAplicacion boolean
     */
    public void setPopupBuscaAplicacion(boolean newPopupBuscaAplicacion) {
        popupBuscaAplicacion = newPopupBuscaAplicacion;
    }

    /**
     * Insert the method's description here.
     * Creation date: (11/4/2002 12:58:52 PM)
     * @param newPopupBuscarParte boolean
     */
    public void setPopupBuscarParte(boolean newPopupBuscarParte) {
        popupBuscarParte = newPopupBuscarParte;
    }

    /**
     * Insert the method's description here.
     * Creation date: (11/1/2002 7:33:34 PM)
     * @param newPopupCliente boolean
     */
    public void setPopupCliente(boolean newPopupCliente) {
        popupCliente = newPopupCliente;
    }

    /**
     * Insert the method's description here.
     * Creation date: (12/5/2002 4:02:55 PM)
     * @param newPopupClienteConIVA boolean
     */
    public void setPopupClienteConIVA(boolean newPopupClienteConIVA) {
        popupClienteConIVA = newPopupClienteConIVA;
    }

    /**
     * Insert the method's description here.
     * Creation date: (12/5/2002 6:20:02 PM)
     * @param newPopupImprimir boolean
     */
    public void setPopupImprimir(boolean newPopupImprimir) {
        popupImprimir = newPopupImprimir;
    }

    /**
     * Insert the method's description here.
     * Creation date: (11/12/2002 1:57:33 PM)
     * @param newPopupLogin boolean
     */
    public void setPopupLogin(boolean newPopupLogin) {
        popupLogin = newPopupLogin;
    }

    /**
     * Insert the method's description here.
     * Creation date: (11/4/2002 12:57:11 PM)
     * @param newPopupParte boolean
     */
    public void setPopupParte(boolean newPopupParte) {
        popupParte = newPopupParte;
    }

    /**
     * Insert the method's description here.
     * Creation date: (11/7/2002 6:32:20 PM)
     * @param newSubtotal float
     */
    public void setSubtotal(float newSubtotal) {
        subtotal = newSubtotal;
    }

    /**
     * Insert the method's description here.
     * Creation date: (11/7/2002 6:32:48 PM)
     * @param newTotal float
     */
    public void setTotal(float newTotal) {
        total = newTotal;
    }

    /**
     * Insert the method's description here.
     * Creation date: (11/1/2002 11:20:44 AM)
     * @param newUsuario java.lang.String
     */
    public void setUsuario(java.lang.String newUsuario) {
        Usuario = newUsuario;
    }

    /**
     * Insert the method's description here.
     * Creation date: (11/4/2002 5:22:19 PM)
     * @param newVNiveles java.util.Vector
     */
    public void setVNiveles(java.util.Vector newVNiveles) {
        vNiveles = newVNiveles;
    }

    /**
     * Insert the method's description here.
     * Creation date: (11/4/2002 7:33:00 PM)
     * @param newVPartidas java.util.Vector
     */
    public void setVPartidas(java.util.Vector newVPartidas) {
        vPartidas = newVPartidas;
    }

    /**
     * Insert the method's description here.
     * Creation date: (11/18/2002 7:12:48 PM)
     * @param newVProveedores java.util.Vector
     */
    public void setVProveedores(java.util.Vector newVProveedores) {
        vProveedores = newVProveedores;
    }

    /**
     * Insert the method's description here.
     * Creation date: (11/1/2002 12:03:06 PM)
     * @return int
     */
    public String getDecpreciob100STR() {
        return Formato.formateoNumerico(parte.getDecpreciob100(),
            "#########0.00");
    }

    /**
     * Insert the method's description here.
     * Creation date: (11/1/2002 12:03:06 PM)
     * @return int
     */
    public String getDiasDepago(String hoy) throws Exception {
        String fecha;

        if (cliente == null) {
            return "";
        }

        StringBuffer msg = new StringBuffer("DIA DE PAGO: ");

        if (!cliente.getBsuceptiblecredito()) {
            setFechaVencimiento(hoy);

            return "*** NO APLICA";
        }

        if (cliente.getSidiadepago() > 0) {
            msg.append(org.nibble.util.Fecha.obtenDia(cliente.getSidiadepago()));
            msg.append("\n");			
			fecha = org.nibble.util.Fecha.agregarDiasFecha(hoy, cliente.getSidiadepago(), "yyyyMMddHHmmss");
            //fecha = util.Fecha.addDiasSemanaFecha(hoy, cliente.getSidiadepago(), "yyyyMMddHHmmss");
            msg.append(org.nibble.util.Fecha.formatMySQL(fecha));
            setFechaVencimiento(fecha);

            return msg.toString();
        }
		//fecha = util.Fecha.addDiasFecha(hoy+"000000", cliente.getSiplazodiaspago(),
        fecha = org.nibble.util.Fecha.agregarDiasFecha(hoy+"000000", cliente.getSiplazodiaspago(),
                "yyyyMMddHHmmss");
        setFechaVencimiento(fecha);
        msg.append(org.nibble.util.Fecha.obtenDia(fecha, "yyyyMMddHHmmss"));
        msg.append("\n");
        msg.append(org.nibble.util.Fecha.formatMySQL(fecha));

        return msg.toString();
    }

    /**
     * Insert the method's description here.
     * Creation date: (11/4/2002 5:22:19 PM)
     *
     */
    public String getProveeDecprecioSTR(int index) {
        dbrPROVEEDOR a = (dbrPROVEEDOR) vProveedores.elementAt(index);
        System.out.println("EL PRECIO ES getProveeDecprecioSTR "+Formato.formateoNumerico(Calcula.redondeo(
                parte.getDecpreciob100()),
            "#########0.00"));
        return Formato.formateoNumerico(Calcula.redondeo(
                parte.getDecpreciob100()),
            "#########0.00");
    }

    /**
     * Insert the method's description here.
     * Creation date: (11/4/2002 5:22:19 PM)
     * @param newVNiveles java.util.Vector
     */
    public String getProveeVchnombre(int index) {
        dbrPROVEEDOR a = (dbrPROVEEDOR) vProveedores.elementAt(index);

        return a.getVchnombre();
    }

    /**
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * @return the folio
     */
    public int getFolio() {
        return folio;
    }

    /**
     * @param folio the folio to set
     */
    public void setFolio(int folio) {
        this.folio = folio;
    }

    /**
     * @return the vchformapago
     */
    public String getVchformapago() {
        return vchformapago;
    }

    /**
     * @param vchformapago the vchformapago to set
     */
    public void setVchformapago(String vchformapago) {
        this.vchformapago = vchformapago;
    }

    /**
     * @return the vchmetodopago
     */
    public String getVchmetodopago() {
        return vchmetodopago;
    }

    /**
     * @param vchmetodopago the vchmetodopago to set
     */
    public void setVchmetodopago(String vchmetodopago) {
        this.vchmetodopago = vchmetodopago;
    }

    /**
     * @return the vchcuentapago
     */
    public String getVchcuentapago() {
        return vchcuentapago;
    }

    /**
     * @param vchcuentapago the vchcuentapago to set
     */
    public void setVchcuentapago(String vchcuentapago) {
        this.vchcuentapago = vchcuentapago;
    }
}
