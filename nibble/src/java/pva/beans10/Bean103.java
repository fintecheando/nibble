package pva.beans10;

/**
 * Insert the type's description here.
 * Creation date: (12/11/2002 02:03:56 p.m.)
 * @author: 
 */

import dbbeans.ctaxcobrar.*;
 
public class Bean103 {
	private int intIdcliente;
	private java.util.Vector vCxc;

	private boolean blnDetalle;
	private boolean blnVencido;
	private java.lang.String strFecha;
public String getBitavisoing(int index){
	
	dbCTAXCOBRARr a = (dbCTAXCOBRARr) vCxc.elementAt(index);
	if (a.isBitavisoing())
		return "SI";
	else
		return "NO";
	
}
public String getDechaber(int index){

	dbCTAXCOBRARr a = (dbCTAXCOBRARr) vCxc.elementAt(index);
	return org.nibble.util.Formato.formateoNumerico(a.getDechaber(),"##,###,##0.00");
}
/**
 * Insert the method's description here.
 * Creation date: (06/12/2002 05:29:41 p.m.)
 * @return java.lang.String
 */
public java.lang.String getFecha() {
	return strFecha;
}
public int getIidcontradocto(int index){
	
	dbCTAXCOBRARr a = (dbCTAXCOBRARr) vCxc.elementAt(index);
	return a.getIidcontradocto();
	
}
public int getIiddoctoorigen(int index){
	
	dbCTAXCOBRARr a = (dbCTAXCOBRARr) vCxc.elementAt(index);
	return a.getIiddoctoorigen();
	
}
public int getTiidtipocontradoc(int index){
	
	dbCTAXCOBRARr a = (dbCTAXCOBRARr) vCxc.elementAt(index);
	return a.getTiidtipocontradoc();
	
}
public String getTisfechamovimiento(int index){
	
	dbCTAXCOBRARr a = (dbCTAXCOBRARr) vCxc.elementAt(index);
	return org.nibble.util.Fecha.formatMySQL(a.getTisfechamovimiento());
}
public String getVchDestipodocto(int index){
	
	dbCTAXCOBRARr a = (dbCTAXCOBRARr) vCxc.elementAt(index);
	return a.getTipodocto();
	
}
public String getVchDestipopago(int index){
	
	dbCTAXCOBRARr a = (dbCTAXCOBRARr) vCxc.elementAt(index);
	return a.getVchtipopago();
	
}
/**
 * Insert the method's description here.
 * Creation date: (12/11/2002 07:14:33 p.m.)
 * @return boolean
 */
public boolean isDetalle() {
	return blnDetalle;
}
/**
 * Insert the method's description here.
 * Creation date: (12/11/2002 04:26:34 p.m.)
 * @param newCxc java.util.Vector
 */
public void setCxc(java.util.Vector newCxc) {
	vCxc = newCxc;
}
/**
 * Insert the method's description here.
 * Creation date: (12/11/2002 07:14:33 p.m.)
 * @param newDetalle boolean
 */
public void setDetalle(boolean newDetalle) {
	blnDetalle = newDetalle;
}
/**
 * Insert the method's description here.
 * Creation date: (06/12/2002 05:29:41 p.m.)
 * @param newFecha java.lang.String
 */
public void setFecha(java.lang.String newFecha) {
	strFecha = newFecha;
}
/**
 * Insert the method's description here.
 * Creation date: (12/11/2002 04:26:34 p.m.)
 * @return java.util.Vector
 */
public int sizeCxc() {
	return vCxc.size();
}
}
