package pva.beans11;

/**
 * Insert the type's description here.
 * Creation date: (21/11/2002 07:34:22 p.m.)
 * @author: 
 */
public class Bean111 {
	private float decIva;
	private float decDebe;
	private float decHaber;
	private float decSaldo;
	private java.lang.String strTisfechamovimiento;
	private java.lang.String strTisfechavencimient;
	private java.lang.String strCliente;
	private int intFactura;
/**
 * Insert the method's description here.
 * Creation date: (02/12/2002 09:13:20 p.m.)
 * @return java.lang.String
 */
public java.lang.String getCliente() {
	return strCliente;
}
/**
 * Insert the method's description here.
 * Creation date: (02/12/2002 08:12:52 p.m.)
 * @return float
 */
public String getDebe() {
	return org.nibble.util.Formato.formateoNumerico(decDebe,"##,###,##0.00");
}
/**
 * Insert the method's description here.
 * Creation date: (02/12/2002 09:14:00 p.m.)
 * @return int
 */
public int getFactura() {
	return intFactura;
}
/**
 * Insert the method's description here.
 * Creation date: (02/12/2002 08:13:18 p.m.)
 * @return float
 */
public String getHaber() {
	return org.nibble.util.Formato.formateoNumerico(decHaber,"##,###,##0.00");
}
/**
 * Insert the method's description here.
 * Creation date: (02/12/2002 08:14:39 p.m.)
 * @return float
 */
public String getSaldo() {
	return org.nibble.util.Formato.formateoNumerico(decDebe-decHaber,"########0.00");
}
/**
 * Insert the method's description here.
 * Creation date: (02/12/2002 08:14:39 p.m.)
 * @return float
 */
public String getSaldoq() {
	return org.nibble.util.Formato.formateoNumerico(decDebe-decHaber,"###,###,##0.00");
}
/**
 * Insert the method's description here.
 * Creation date: (02/12/2002 08:19:12 p.m.)
 * @return java.lang.String
 */
public java.lang.String getTisfechamovimiento() {
	return org.nibble.util.Fecha.formatMySQL(strTisfechamovimiento);
}
/**
 * Insert the method's description here.
 * Creation date: (02/12/2002 08:20:27 p.m.)
 * @return java.lang.String
 */
public java.lang.String getTisfechavencimient() {
	return org.nibble.util.Fecha.formatMySQL(strTisfechavencimient);
}
/**
 * Insert the method's description here.
 * Creation date: (02/12/2002 09:13:20 p.m.)
 * @param newCliente java.lang.String
 */
public void setCliente(java.lang.String newCliente) {
	strCliente = newCliente;
}
/**
 * Insert the method's description here.
 * Creation date: (02/12/2002 08:12:52 p.m.)
 * @param newDebe float
 */
public void setDebe(float newDebe) {
	decDebe = newDebe;
}
/**
 * Insert the method's description here.
 * Creation date: (02/12/2002 09:14:00 p.m.)
 * @param newFactura int
 */
public void setFactura(int newFactura) {
	intFactura = newFactura;
}
/**
 * Insert the method's description here.
 * Creation date: (02/12/2002 08:13:18 p.m.)
 * @param newHaber float
 */
public void setHaber(float newHaber) {
	decHaber = newHaber;
}
/**
 * Insert the method's description here.
 * Creation date: (21/11/2002 07:35:57 p.m.)
 * @param newIva float
 */
public void setIva(float newIva) {
	decIva = newIva;
}
/**
 * Insert the method's description here.
 * Creation date: (02/12/2002 08:14:39 p.m.)
 * @param newSaldo float
 */
public void setSaldo(float newSaldo) {
	decSaldo = newSaldo;
}
/**
 * Insert the method's description here.
 * Creation date: (02/12/2002 08:19:12 p.m.)
 * @param newTisfechamovimiento java.lang.String
 */
public void setTisfechamovimiento(java.lang.String newTisfechamovimiento) {
	strTisfechamovimiento = newTisfechamovimiento;
}
/**
 * Insert the method's description here.
 * Creation date: (02/12/2002 08:20:27 p.m.)
 * @param newTisfechavencimient java.lang.String
 */
public void setTisfechavencimient(java.lang.String newTisfechavencimient) {
	strTisfechavencimient = newTisfechavencimient;
}
}
