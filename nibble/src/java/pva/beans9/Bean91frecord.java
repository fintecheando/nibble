package pva.beans9;

/**
 * Insert the type's description here.
 * Creation date: (11/19/2002 12:46:12 PM)
 * @author: 
 */
public class Bean91frecord {
	private int icantidad;
	public java.util.Vector vProveedores;
	private dbrbeans.parteaplicacion.dbrPARTEAPLICACION parte;
/**
 * Insert the method's description here.
 * Creation date: (11/19/2002 1:51:47 PM)
 * @return dbrbeans.parteaplicacion.dbrPARTEAPLICACION
 */
public float getDecpreciob100() {
	return parte.getDecpreciob100();
}
/**
 * Insert the method's description here.
 * Creation date: (11/19/2002 12:47:59 PM)
 * @return int
 */
public int getIcantidad() {
	return icantidad;
}
/**
 * Insert the method's description here.
 * Creation date: (11/19/2002 1:51:47 PM)
 * @return dbrbeans.parteaplicacion.dbrPARTEAPLICACION
 */
public dbrbeans.parteaplicacion.dbrPARTEAPLICACION getParte() {
	return parte;
}
/**
 * Insert the method's description here.
 * Creation date: (11/19/2002 12:51:08 PM)
 * @return java.util.Vector
 */
public java.util.Vector getVProveedores() {
	return vProveedores;
}
/**
 * Insert the method's description here.
 * Creation date: (11/19/2002 12:47:59 PM)
 * @param newIcantidad int
 */
public void setIcantidad(int newIcantidad) {
	icantidad = newIcantidad;
}
/**
 * Insert the method's description here.
 * Creation date: (11/19/2002 1:51:47 PM)
 * @param newParte dbrbeans.parteaplicacion.dbrPARTEAPLICACION
 */
public void setParte(dbrbeans.parteaplicacion.dbrPARTEAPLICACION newParte) {
	parte = newParte;
}
/**
 * Insert the method's description here.
 * Creation date: (11/19/2002 12:51:08 PM)
 * @param newVProveedores java.util.Vector
 */
public void setVProveedores(java.util.Vector newVProveedores) {
	vProveedores = newVProveedores;
}
}
