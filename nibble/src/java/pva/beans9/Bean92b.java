package pva.beans9;

/**
 * Insert the type's description here.
 * Creation date: (12/10/2002 11:26:19 AM)
 * @author: 
 */
import dbrbeans.facturadetalle.*;
import org.nibble.util.Formato;

public class Bean92b {
	private dbrbeans.clientepaisestadomunicipio.dbrCLIENTE cliente;
	public org.nibble.dao.factura.FacturaDAO factura;
	private java.util.Vector partidas;
	private int iid;
	private java.lang.String fecha;
	private int iidusuario;
	private int referencia;
	private java.lang.String origen;
	private int IDCLIENTESINIVA;
	private float descuento;
	private float bonificacion;
	private java.lang.String Username;
	private java.lang.String ConceptoBonificacion;
/**
 * Bean92b constructor comment.
 */
public Bean92b() {
	super();
}
/**
 * Insert the method's description here.
 * Creation date: (12/10/2002 11:27:19 AM)
 * @return dbrbeans.clientepaisestadomunicipio.dbrCLIENTE
 */
public dbrbeans.clientepaisestadomunicipio.dbrCLIENTE getCliente() {
	return cliente;
}
public String getCondiciones() {
	return cliente.getBsuceptiblecredito()?"cr�dito":"";
}

public String getClienteRazonSocial() {
	return (cliente.getVchrazonsocial() == null)
		? ""
		: cliente.getVchrazonsocial();
}

public String getClienteRFC() {
	return (cliente.getVchrfc() == null) ? "" : cliente.getVchrfc();
}

public String getClienteCalle() {
	return (cliente.getVchcalle() == null) ? "" : cliente.getVchcalle();
}

public String getClienteColonia() {
	return (cliente.getVchcolonia() == null) ? "" : cliente.getVchcolonia();
}

public String getClientePais() {
	return (cliente.getVchpaisdes() == null) ? "" : cliente.getVchpaisdes();
}

public String getClienteEstado() {
	return (cliente.getVchestadodes() == null)
		? ""
		: cliente.getVchestadodes();
}

public String getClienteMunicipio() {
	return (cliente.getVchmunicipiodes() == null)
		? ""
		: cliente.getVchmunicipiodes();
}
public String getClienteCodigoPostal() {
	return (cliente.getChcodigop() == null) ? "" : cliente.getChcodigop();
}
public String getClienteTelefono() {
	return (cliente.getVchtel1() == null) ? "" : cliente.getVchtel1();
}


public String getDatosGenerales() {
	StringBuffer datos= new StringBuffer();
	if (cliente.getIidcatcliente()==0)
		return "";
    datos.append(cliente.getVchnombre()==null?"":cliente.getVchnombre());
    datos.append(", ");
    datos.append(cliente.getVchrazonsocial()==null?"":cliente.getVchrazonsocial());
    datos.append(",\n ");
    datos.append(cliente.getVchrfc()==null?"":cliente.getVchrfc());
    datos.append(", ");
    datos.append(cliente.getVchcalle()==null?"":cliente.getVchcalle());
    datos.append(", ");
    datos.append(cliente.getVchcolonia()==null?"":cliente.getVchcolonia());
    datos.append(",\n ");
    datos.append(cliente.getVchpaisdes()==null?"":cliente.getVchpaisdes());
    datos.append(", ");
    datos.append(cliente.getVchestadodes()==null?"":cliente.getVchestadodes());
    datos.append(", ");
    datos.append(cliente.getVchmunicipiodes()==null?"":cliente.getVchmunicipiodes());
    return datos.toString();
}
public float getDecdescglobal() {
	return factura.getDecdescglobal();
}
public float getDecivaclasific() {
	if (partidas.size()>0){
		dbrFACTURADETALLE a = (dbrFACTURADETALLE) partidas.elementAt(0);
		return a.getDecivaclasific();
	}
	else
		return 0;
}
public String getDecprecioventa(int index) {
	dbrFACTURADETALLE a = (dbrFACTURADETALLE) partidas.elementAt(index);
	return Formato.formateoNumerico(a.getDecprecioventa(), "#########0.00");
}
public double getDecprecioventaD(int index) {
	dbrFACTURADETALLE a = (dbrFACTURADETALLE) partidas.elementAt(index);
	return a.getDecprecioventa();
}
public String getDevueltas(int index) {
	dbrFACTURADETALLE a = (dbrFACTURADETALLE) partidas.elementAt(index);
	return String.valueOf(a.getDevueltas());
}
public int getDevueltasD(int index) {
	dbrFACTURADETALLE a = (dbrFACTURADETALLE) partidas.elementAt(index);
	return a.getDevueltas();
}

/**
 * Insert the method's description here.
 * Creation date: (12/10/2002 11:27:44 AM)
 * @return dbbeans.factura.dbFACTURA
 */
public org.nibble.dao.factura.FacturaDAO getFactura() {
	return factura;
}
/**
 * Insert the method's description here.
 * Creation date: (12/10/2002 4:25:38 PM)
 * @return java.lang.String
 */
public java.lang.String getFecha() {
	return fecha;
}
/**
 * Insert the method's description here.
 * Creation date: (12/23/2002 4:24:45 PM)
 * @return int
 */
public int getIDCLIENTESINIVA() {
	return IDCLIENTESINIVA;
}
/**
 * Insert the method's description here.
 * Creation date: (12/10/2002 4:01:59 PM)
 * @return int
 */
public int getIid() {
	return iid;
}
/**
 * Insert the method's description here.
 * Creation date: (12/10/2002 11:27:19 AM)
 * @return dbrbeans.clientepaisestadomunicipio.dbrCLIENTE
 */
public int getIidnocliente() {
	return cliente.getIidnocliente();
}
/**
 * Insert the method's description here.
 * Creation date: (12/10/2002 4:25:51 PM)
 * @return int
 */
public int getIidusuario() {
	return iidusuario;
}
public String getImporte(int index) {
	dbrFACTURADETALLE a = (dbrFACTURADETALLE) partidas.elementAt(index);
	return Formato.formateoNumerico(a.getDecprecioventa() * a.getDevueltas(), "#########0.00");
}
public double getImporteD(int index) {
	dbrFACTURADETALLE a = (dbrFACTURADETALLE) partidas.elementAt(index);
	return a.getDecprecioventa() * a.getDevueltas();
}

public double getImporteValue(int index) {
	dbrFACTURADETALLE a = (dbrFACTURADETALLE) partidas.elementAt(index);
	return a.getDecprecioventa() * a.getDevueltas();
}
/**
 * Insert the method's description here.
 * Creation date: (12/23/2002 4:05:42 PM)
 * @return java.lang.String
 */
public java.lang.String getOrigen() {
	return origen;
}
/**
 * Insert the method's description here.
 * Creation date: (12/10/2002 11:29:22 AM)
 * @return java.util.Vector
 */
public java.util.Vector getPartidas() {
	return partidas;
}
/**
 * Insert the method's description here.
 * Creation date: (12/10/2002 4:28:01 PM)
 * @return int
 */
public String getReferencia() {
	return String.valueOf(referencia);
}



/**
 * Insert the method's description here.
 * Creation date: (12/10/2002 4:30:50 PM)
 * @return int
 */
public int getSize() {
	return partidas.size();
}
public String getTransporte() {
	return cliente.getVchviades();
}
public String getVchdescripcion(int index) {
	dbrFACTURADETALLE a = (dbrFACTURADETALLE) partidas.elementAt(index);
	return a.getVchdescripcion();
}
public String getVchnivel(int index) {
	dbrFACTURADETALLE a = (dbrFACTURADETALLE) partidas.elementAt(index);
	return a.getVchnivel();
}
public String getVchnumparte(int index) {
	dbrFACTURADETALLE a = (dbrFACTURADETALLE) partidas.elementAt(index);
	return a.getVchnumparte();
}
public String getVchunidad(int index) {
	dbrFACTURADETALLE a = (dbrFACTURADETALLE) partidas.elementAt(index);
	return a.getUnidadDescripcion();
}

/**
 * Insert the method's description here.
 * Creation date: (12/10/2002 11:27:19 AM)
 * @param newCliente dbrbeans.clientepaisestadomunicipio.dbrCLIENTE
 */
public void setCliente(dbrbeans.clientepaisestadomunicipio.dbrCLIENTE newCliente) {
	cliente = newCliente;
}
/**
 * Insert the method's description here.
 * Creation date: (12/10/2002 11:27:44 AM)
 * @param newFactura dbbeans.factura.dbFACTURA
 */
public void setFactura(org.nibble.dao.factura.FacturaDAO newFactura) {
	factura = newFactura;
}
/**
 * Insert the method's description here.
 * Creation date: (12/10/2002 4:25:38 PM)
 * @param newFecha java.lang.String
 */
public void setFecha(java.lang.String newFecha) {
	fecha = newFecha;
}
/**
 * Insert the method's description here.
 * Creation date: (12/23/2002 4:24:45 PM)
 * @param newIDCLIENTESINIVA int
 */
public void setIDCLIENTESINIVA(int newIDCLIENTESINIVA) {
	IDCLIENTESINIVA = newIDCLIENTESINIVA;
}
/**
 * Insert the method's description here.
 * Creation date: (12/10/2002 4:01:59 PM)
 * @param newIid int
 */
public void setIid(int newIid) {
	iid = newIid;
}
/**
 * Insert the method's description here.
 * Creation date: (12/10/2002 4:25:51 PM)
 * @param newIidusuario int
 */
public void setIidusuario(int newIidusuario) {
	iidusuario = newIidusuario;
}
/**
 * Insert the method's description here.
 * Creation date: (12/23/2002 4:05:42 PM)
 * @param newOrigen java.lang.String
 */
public void setOrigen(java.lang.String newOrigen) {
	origen = newOrigen;
}
/**
 * Insert the method's description here.
 * Creation date: (12/10/2002 11:29:22 AM)
 * @param newPartidas java.util.Vector
 */
public void setPartidas(java.util.Vector newPartidas) {
	partidas = newPartidas;
}
/**
 * Insert the method's description here.
 * Creation date: (12/10/2002 4:28:01 PM)
 * @param newReferencia int
 */
public void setReferencia(int newReferencia) {
	referencia = newReferencia;
}

public void setDescuento(float Descuento){
	descuento = Descuento;
}
public float getDescuento() {
	return descuento;
}

//Gets y Sets Para el conecpto de Notas de Credito (Bonificacion)
public void setBonificacion(float newBonificacion) {
	bonificacion = newBonificacion;
}

public float getBonificacion() {
	return bonificacion;
}

public void setConceptoBonificacion(java.lang.String newConceptoBonificacion) {
	ConceptoBonificacion = newConceptoBonificacion;
}
public java.lang.String getConceptoBonificacion() {
	return ConceptoBonificacion;
}
	
}
