package pva.beans;

/**
 * Insert the type's description here.
 * Creation date: (22/11/2002 06:52:17 p.m.)
 * @author: 
 */

import org.nibble.dao.factura.*;
 
public class BeanBuscaFactura {
	private java.lang.String strFecha;
	private boolean blnDetalle;
	private java.lang.String strTitulo;
	private java.lang.String strImagen;
	private int intCliente;
	private java.lang.String strLink;
	private java.util.Vector vctFacturas;
/**
 * Insert the method's description here.
 * Creation date: (25/11/2002 12:34:19 p.m.)
 * @return int
 */
public int getCliente() {
	return intCliente;
}
	public String getDecivatotal(int index){

		FacturaDAO a = (FacturaDAO) vctFacturas.elementAt(index);
		return org.nibble.util.Formato.formateoNumerico(a.getDecivatotal(),"##,###,##0.00");
		
	}
	public String getDecsubtotal(int index){

		FacturaDAO a = (FacturaDAO) vctFacturas.elementAt(index);
		return org.nibble.util.Formato.formateoNumerico(a.getDecsubtotal(),"##,###,##0.00");

	}
	public String getDectotal(int index){

		FacturaDAO a = (FacturaDAO) vctFacturas.elementAt(index);
		return org.nibble.util.Formato.formateoNumerico(a.getDecsubtotal()+a.getDecivatotal(),"##,###,##0.00");

	}
/**
 * Insert the method's description here.
 * Creation date: (25/11/2002 01:46:22 p.m.)
 * @return java.util.Vector
 */
public int getFacturasSize() {
	return vctFacturas.size();
}
/**
 * Insert the method's description here.
 * Creation date: (22/11/2002 06:52:40 p.m.)
 * @return java.lang.String
 */
public java.lang.String getFecha() {
	return strFecha;
}
	public int getIidfactura(int index){

		FacturaDAO a = (FacturaDAO) vctFacturas.elementAt(index);
		return a.getIidfactura();

	}
	public int getIidnocliente(int index){

		FacturaDAO a = (FacturaDAO) vctFacturas.elementAt(index);
		return a.getIidnocliente();

	}
/**
 * Insert the method's description here.
 * Creation date: (25/11/2002 12:25:56 p.m.)
 * @return java.lang.String
 */
public java.lang.String getImagen() {
	return strImagen;
}
/**
 * Insert the method's description here.
 * Creation date: (25/11/2002 12:50:01 p.m.)
 * @return java.lang.String
 */
public java.lang.String getLink() {
	return strLink;
}
	public String getTisfechafactura(int index){

		FacturaDAO a = (FacturaDAO) vctFacturas.elementAt(index);
		return org.nibble.util.Fecha.formatMySQL(a.getTisfechafactura());

	}
	public String getTisfechavencimient(int index){

		FacturaDAO a = (FacturaDAO) vctFacturas.elementAt(index);
		return org.nibble.util.Fecha.formatMySQL(a.getTisfechavencimient());

	}
/**
 * Insert the method's description here.
 * Creation date: (25/11/2002 12:25:20 p.m.)
 * @return java.lang.String
 */
public java.lang.String getTitulo() {
	return strTitulo;
}
/**
 * Insert the method's description here.
 * Creation date: (22/11/2002 06:53:26 p.m.)
 * @return boolean
 */
public boolean isDetalle() {
	return blnDetalle;
}
/**
 * Insert the method's description here.
 * Creation date: (25/11/2002 12:34:19 p.m.)
 * @param newCliente int
 */
public void setCliente(int newCliente) {
	intCliente = newCliente;
}
/**
 * Insert the method's description here.
 * Creation date: (22/11/2002 06:53:26 p.m.)
 * @param newDetalle boolean
 */
public void setDetalle(boolean newDetalle) {
	blnDetalle = newDetalle;
}
/**
 * Insert the method's description here.
 * Creation date: (25/11/2002 01:46:22 p.m.)
 * @param newFacturas java.util.Vector
 */
public void setFacturas(java.util.Vector newFacturas) {
	vctFacturas = newFacturas;
}
/**
 * Insert the method's description here.
 * Creation date: (22/11/2002 06:52:40 p.m.)
 * @param newFecha java.lang.String
 */
public void setFecha(java.lang.String newFecha) {
	strFecha = newFecha;
}
/**
 * Insert the method's description here.
 * Creation date: (25/11/2002 12:25:56 p.m.)
 * @param newImagen java.lang.String
 */
public void setImagen(java.lang.String newImagen) {
	strImagen = newImagen;
}
/**
 * Insert the method's description here.
 * Creation date: (25/11/2002 12:50:01 p.m.)
 * @param newLink java.lang.String
 */
public void setLink(java.lang.String newLink) {
	strLink = newLink;
}
/**
 * Insert the method's description here.
 * Creation date: (25/11/2002 12:25:20 p.m.)
 * @param newTitulo java.lang.String
 */
public void setTitulo(java.lang.String newTitulo) {
	strTitulo = newTitulo;
}
}
