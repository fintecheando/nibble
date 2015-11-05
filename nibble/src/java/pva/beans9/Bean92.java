package pva.beans9;

/**
 * Insert the type's description here.
 * Creation date: (11/13/2002 7:29:43 PM)
 * @author: 
 */
import dbrbeans.clientepaisestadomunicipio.*;
import dbrbeans.facturadetalle.*;

import org.nibble.util.Formato;

public class Bean92 implements Cloneable  {
	private java.lang.String Username;
	private java.lang.String ConceptoBonificacion;
	private java.util.Vector vPartidas;
	private dbrbeans.clientepaisestadomunicipio.dbrCLIENTE cliente;
	private float iva;
	private float bonificacion;
	private org.nibble.dao.factura.FacturaDAO factura;
	//private dbbeans.factura.dbFACTURA facturaTotal;
	private java.lang.String msg;
	private boolean mostradorSinIVA;
	private int referenciabonificacion;
	private java.lang.String fechabonificacion;
/**
 * Bean92 constructor comment.
 */
public Bean92() {
	super();
	vPartidas = new java.util.Vector();
	cliente = new dbrCLIENTE();
	factura = new org.nibble.dao.factura.FacturaDAO();
	//facturaTotal = new dbbeans.factura.dbFACTURA();
}
public Object clone() throws CloneNotSupportedException {
    return super.clone();
}
/**
 * Insert the method's description here.
 * Creation date: (11/13/2002 8:02:58 PM)
 * @return dbrbeans.clientepaisestadomunicipio.dbrCLIENTE
 */
public dbrbeans.clientepaisestadomunicipio.dbrCLIENTE getCliente() {
	return cliente;
}
public String getCondicionesComerciales() {
	if (cliente.getIidcatcliente()==0)
		return "";
    return "Cat. Cliente: "
        + cliente.getVchcategoriades()
        + "\nNivel de Precio: "
        + cliente.getVchniveldes()
        + "\nVia de Embarque: "
        + cliente.getVchviades();
}
public String getDatosGenerales() {
	StringBuffer datos= new StringBuffer();
	if (cliente.getIidcatcliente()==0)
		return "";
	datos.append("Nombre: ");
    datos.append(cliente.getVchnombre()==null?"":cliente.getVchnombre());
    datos.append("\nRaz�n social: ");
    datos.append(cliente.getVchrazonsocial()==null?"":cliente.getVchrazonsocial());
    datos.append("\nR.F.C: ");
    datos.append(cliente.getVchrfc()==null?"":cliente.getVchrfc());
    datos.append("\nCalle: ");
    datos.append(cliente.getVchcalle()==null?"":cliente.getVchcalle());
    datos.append("\nColonia: ");
    datos.append(cliente.getVchcolonia()==null?"":cliente.getVchcolonia());
    datos.append("\nPais: ");
    datos.append(cliente.getVchpaisdes()==null?"":cliente.getVchpaisdes());
    datos.append("\nEstado: ");
    datos.append(cliente.getVchestadodes()==null?"":cliente.getVchestadodes());
    datos.append("\nMunicipio: ");
    datos.append(cliente.getVchmunicipiodes()==null?"":cliente.getVchmunicipiodes());
    return datos.toString();
}
/**
 * Insert the method's description here.
 * Creation date: (11/13/2002 7:49:18 PM)
 * @return java.util.Vector
 */
public float getDeccostoproducto(int index) {
	dbrFACTURADETALLE a = (dbrFACTURADETALLE) vPartidas.elementAt(index);
	return a.getDeccostoproducto();
}
/**
 * Insert the method's description here.
 * Creation date: (11/14/2002 1:43:11 PM)
 * @return float
 */
public float getDecdescglobal() {
	return factura.getDecdescglobal();
}

/*
 * 
 */
public float getTotalFactura() {
	float TotalFactura =  factura.getDecsubtotal() - factura.getDecmontodesc() + factura.getDecivatotal() ;
	 return TotalFactura;
}
 
 
/**
 * Insert the method's description here.
 * Creation date: (11/13/2002 7:49:18 PM)
 * @return java.util.Vector
 */
public float getDecivaclasific(int index) {
	dbrFACTURADETALLE a = (dbrFACTURADETALLE) vPartidas.elementAt(index);
	return a.getDecivaclasific();
}
/**
 * Insert the method's description here.
 * Creation date: (11/13/2002 7:49:18 PM)
 * @return java.util.Vector
 */
public float getDecpreciolista(int index) {
	dbrFACTURADETALLE a = (dbrFACTURADETALLE) vPartidas.elementAt(index);
	return a.getDecpreciolista();
}
/**
 * Insert the method's description here.
 * Creation date: (11/13/2002 7:49:18 PM)
 * @return java.util.Vector
 */
public float getDecprecioventa(int index) {
	dbrFACTURADETALLE a = (dbrFACTURADETALLE) vPartidas.elementAt(index);
	return a.getDecprecioventa();
}
/**
 * Insert the method's description here.
 * Creation date: (11/13/2002 7:49:18 PM)
 * @return java.util.Vector
 */
public String getDecprecioventaSTR(int index) {
	dbrFACTURADETALLE a = (dbrFACTURADETALLE) vPartidas.elementAt(index);
	return Formato.formateoNumerico(a.getDecprecioventa(),"#########0.00");
}
/**
 * Insert the method's description here.
 * Creation date: (11/13/2002 7:49:18 PM)
 * @return java.util.Vector
 */
public int getDevueltas(int index) {
	dbrFACTURADETALLE a = (dbrFACTURADETALLE) vPartidas.elementAt(index);
	return a.getDevueltas();
}
/**
 * Insert the method's description here.
 * Creation date: (11/14/2002 5:26:43 PM)
 * @return dbbeans.factura.dbFACTURA
 */
public org.nibble.dao.factura.FacturaDAO getFactura() {
	return factura;
}
/**
 * Insert the method's description here.
 * Creation date: (11/13/2002 7:49:18 PM)
 * @return java.util.Vector
 */
public int getIcantidadproducto(int index) {
	dbrFACTURADETALLE a = (dbrFACTURADETALLE) vPartidas.elementAt(index);
	return a.getIcantidadproducto();
}
/**
 * Insert the method's description here.
 * Creation date: (11/13/2002 7:49:18 PM)
 * @return java.util.Vector
 */
public int getIidfactura() {
	return factura.getIidfactura();
}
/**
 * Insert the method's description here.
 * Creation date: (11/13/2002 7:49:18 PM)
 * @return java.util.Vector
 */
public int getIidnivel(int index) {
	dbrFACTURADETALLE a = (dbrFACTURADETALLE) vPartidas.elementAt(index);
	return a.getIidnivel();
}
/**
 * Insert the method's description here.
 * Creation date: (11/13/2002 7:49:18 PM)
 * @return java.util.Vector
 */
public int getIidproveedor(int index) {
	dbrFACTURADETALLE a = (dbrFACTURADETALLE) vPartidas.elementAt(index);
	return a.getIidproveedor();
}
/**
 * Insert the method's description here.
 * Creation date: (11/14/2002 1:49:56 PM)
 * @return float
 */
public float getIva() {
	return iva;
}
/**
 * Insert the method's description here.
 * Creation date: (11/13/2002 7:49:18 PM)
 * @return java.util.Vector
 */
public long getLiidparte(int index) {
	dbrFACTURADETALLE a = (dbrFACTURADETALLE) vPartidas.elementAt(index);
	return a.getLiidparte();
}
/**
 * Insert the method's description here.
 * Creation date: (11/14/2002 8:59:16 PM)
 * @return java.lang.String
 */
public java.lang.String getMsg() {
	return (msg==null)?"":msg;
}
/**
 * Insert the method's description here.
 * Creation date: (11/13/2002 7:49:18 PM)
 * @return java.util.Vector
 */
public dbrFACTURADETALLE getPartida(int index) {
	dbrFACTURADETALLE a = (dbrFACTURADETALLE) vPartidas.elementAt(index);
	return a;
}
/**
 * Insert the method's description here.
 * Creation date: (11/13/2002 7:49:18 PM)
 * @return java.util.Vector
 */
public String getTisfechafactura() {
	return factura.getTisfechafactura()==null?"":org.nibble.util.Fecha.formatMySQL(factura.getTisfechafactura());
}
/**
 * Insert the method's description here.
 * Creation date: (11/13/2002 7:30:08 PM)
 * @return java.lang.String
 */
public java.lang.String getUsername() {
	return Username;
}
/**
 * Insert the method's description here.
 * Creation date: (11/13/2002 7:49:18 PM)
 * @return java.util.Vector
 */
public String getVchdescripcion(int index) {
	dbrFACTURADETALLE a = (dbrFACTURADETALLE) vPartidas.elementAt(index);
	return a.getVchdescripcion();
}
/**
 * Insert the method's description here.
 * Creation date: (11/13/2002 7:49:18 PM)
 * @return java.util.Vector
 */
public String getVchnivel(int index) {
	dbrFACTURADETALLE a = (dbrFACTURADETALLE) vPartidas.elementAt(index);
	return a.getVchnivel();
}
/**
 * Insert the method's description here.
 * Creation date: (11/13/2002 7:49:18 PM)
 * @return java.util.Vector
 */
public String getVchnumparte(int index) {
	dbrFACTURADETALLE a = (dbrFACTURADETALLE) vPartidas.elementAt(index);
	return a.getVchnumparte();
}
/**
 * Insert the method's description here.
 * Creation date: (11/13/2002 7:49:18 PM)
 * @return java.util.Vector
 */
public String getVchrazonsocial(int index) {
	dbrFACTURADETALLE a = (dbrFACTURADETALLE) vPartidas.elementAt(index);
	return a.getVchrazonsocial();
}
/**
 * Insert the method's description here.
 * Creation date: (11/13/2002 7:49:18 PM)
 * @return java.util.Vector
 */
public java.util.Vector getVPartidas() {
	return vPartidas;
}
/**
 * Insert the method's description here.
 * Creation date: (11/13/2002 7:49:18 PM)
 * @return java.util.Vector
 */
public int getVPartidasSize() {
	return vPartidas.size();
}
/**
 * Insert the method's description here.
 * Creation date: (1/15/2003 12:25:32 PM)
 * @return boolean
 */
public boolean isMostradorSinIVA() {
	return mostradorSinIVA;
}
/**
 * Insert the method's description here.
 * Creation date: (11/13/2002 8:02:58 PM)
 * @param newCliente dbrbeans.clientepaisestadomunicipio.dbrCLIENTE
 */
public void setCliente(dbrbeans.clientepaisestadomunicipio.dbrCLIENTE newCliente) {
	cliente = newCliente;
}
/**
 * Insert the method's description here.
 * Creation date: (11/13/2002 7:49:18 PM)
 * @return java.util.Vector
 */
public void setDevueltas(int index,int value) {
	dbrFACTURADETALLE a = (dbrFACTURADETALLE) vPartidas.elementAt(index);
	a.setDevueltas(value);
}
/**
 * Insert the method's description here.
 * Creation date: (11/14/2002 5:26:43 PM)
 * @param newFactura dbbeans.factura.dbFACTURA
 */
public void setFactura(org.nibble.dao.factura.FacturaDAO newFactura) {
	factura = newFactura;
}
/**
 * Insert the method's description here.
 * Creation date: (11/14/2002 1:49:56 PM)
 * @param newIva float
 */
public void setIva(float newIva) {
	iva = newIva;
}
/**
 * Insert the method's description here.
 * Creation date: (1/15/2003 12:25:32 PM)
 * @param newMostradorSinIVA boolean
 */
public void setMostradorSinIVA(boolean newMostradorSinIVA) {
	mostradorSinIVA = newMostradorSinIVA;
}
/**
 * Insert the method's description here.
 * Creation date: (11/14/2002 8:59:16 PM)
 * @param newMsg java.lang.String
 */
public void setMsg(java.lang.String newMsg) {
	msg = newMsg;
}
/**
 * Insert the method's description here.
 * Creation date: (11/13/2002 7:30:08 PM)
 * @param newUsername java.lang.String
 */
public void setUsername(java.lang.String newUsername) {
	Username = newUsername;
}
/**
 * Insert the method's description here.
 * Creation date: (11/13/2002 7:49:18 PM)
 * @param newVPartidas java.util.Vector
 */
public void setVPartidas(java.util.Vector newVPartidas) {
	vPartidas = newVPartidas;
}
//Gets y Sets Para el conecpto de Notas de Credito (Bonificacion)
  public void setReferenciaBonificacion(int newReferenciaBonificacion) {
	referenciabonificacion = newReferenciaBonificacion;
  }

  public int getReferenciaBonificacion() {
	  return referenciabonificacion;
  }
  
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

public java.lang.String getFechaBonificacion() {
	return fechabonificacion;
}

public void setFechaBonificacion(java.lang.String newFechaBonificacion) {
	fechabonificacion = newFechaBonificacion;
}

}
