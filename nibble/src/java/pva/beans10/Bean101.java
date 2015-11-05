package pva.beans10;

/**
 * Insert the type's description here.
 * Creation date: (12/11/2002 02:03:56 p.m.)
 * @author: 
 */

import dbbeans.ctaxcobrar.*;
 
public class Bean101 {
	private int intIdcliente;
	private java.util.Vector vCxc;

	private boolean blnDetalle;
	private boolean blnVencido;
public String getDecdebe(int index){

	dbCTAXCOBRARr a = (dbCTAXCOBRARr) vCxc.elementAt(index);
	return org.nibble.util.Formato.formateoNumerico(a.getDecdebe(),"##,###,##0.00");
}
public String getDechaber(int index){

	dbCTAXCOBRARr a = (dbCTAXCOBRARr) vCxc.elementAt(index);
	return org.nibble.util.Formato.formateoNumerico(a.getDechaber(),"##,###,##0.00");
}
public String getDecsaldo(int index){

	dbCTAXCOBRARr a = (dbCTAXCOBRARr) vCxc.elementAt(index);
	return org.nibble.util.Formato.formateoNumerico(a.getDecdebe()-a.getDechaber(),"##,###,##0.00");
}
public String getDecsaldoq(int index){

	dbCTAXCOBRARr a = (dbCTAXCOBRARr) vCxc.elementAt(index);
	return org.nibble.util.Formato.formateoNumerico(a.getDecdebe()-a.getDechaber(),"#######0.00");
}
/**
 * Insert the method's description here.
 * Creation date: (12/11/2002 02:04:47 p.m.)
 * @return int
 */
public int getIdcliente() {
	return intIdcliente;
}
public int getIidcontradocto(int index){
	
	dbCTAXCOBRARr a = (dbCTAXCOBRARr) vCxc.elementAt(index);
	return a.getIidcontradocto();
	
}
public int getIiddoctoorigen(int index){
	
	dbCTAXCOBRARr a = (dbCTAXCOBRARr) vCxc.elementAt(index);
	return a.getIiddoctoorigen();
	
}
public int getTiconsecsubdocto(int index){
	
	dbCTAXCOBRARr a = (dbCTAXCOBRARr) vCxc.elementAt(index);
	return a.getTiconsecsubdocto();
	
}
public int getTiidtipodocto(int index){
	
	dbCTAXCOBRARr a = (dbCTAXCOBRARr) vCxc.elementAt(index);
	return a.getTiidtipodocto();
	
}
public String getTisfechamovimiento(int index){
	
	dbCTAXCOBRARr a = (dbCTAXCOBRARr) vCxc.elementAt(index);
	return org.nibble.util.Fecha.formatMySQL(a.getTisfechamovimiento());
}
public String getTisfechavencimient(int index){

	dbCTAXCOBRARr a = (dbCTAXCOBRARr) vCxc.elementAt(index);
	return org.nibble.util.Fecha.formatMySQL(a.getTisfechavencimient());
}
public String getVchDestipodocto(int index){
	
	dbCTAXCOBRARr a = (dbCTAXCOBRARr) vCxc.elementAt(index);
	return a.getTipodocto();
	
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
 * Creation date: (18/11/2002 07:49:01 p.m.)
 * @return boolean
 */
public boolean isVencido(int index) {

	boolean ven = false;
	dbCTAXCOBRARr a = (dbCTAXCOBRARr) vCxc.elementAt(index);
	String fven = a.getTisfechavencimient();
	String fnow = org.nibble.util.Fecha.getCurrentDate("yyyyMMddhhmmss");
	return org.nibble.util.Fecha.mayor(fnow,fven);
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
 * Creation date: (12/11/2002 02:04:47 p.m.)
 * @param newIdcliente int
 */
public void setIdcliente(int newIdcliente) {
	intIdcliente = newIdcliente;
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
