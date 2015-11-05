package pva.beans9;

import dbrbeans.parteaplicacion.*;

public class Bean91Parte {
	private java.util.Vector vPartes;
	private int iidcategoria;
/**
 * Bean91Parte constructor comment.
 */
public Bean91Parte() {
	super();
}
/**
 * Insert the method's description here.
 * Creation date: (11/7/2002 11:45:17 AM)
 * @return int
 */
public int getIidcategoria() {
	return iidcategoria;
}
/**
 * Insert the method's description here.
 * Creation date: (11/6/2002 4:57:32 PM)
 * @return int
 */
public long getLiidparte(int index) {
	dbrPARTEAPLICACION a = (dbrPARTEAPLICACION) vPartes.elementAt(index);
	return a.getLiidparte();
}
/**
 * Insert the method's description here.
 * Creation date: (11/6/2002 4:57:32 PM)
 * @return int
 */
public int getPartesSize() {
	return vPartes.size();
}
/**
 * Insert the method's description here.
 * Creation date: (11/6/2002 4:57:32 PM)
 * @return int
 */
public short getTicantidad(int index) {
	dbrPARTEAPLICACION a = (dbrPARTEAPLICACION) vPartes.elementAt(index);
	return a.getTicantidad();
}
/**
 * Insert the method's description here.
 * Creation date: (11/6/2002 4:57:32 PM)
 * @return int
 */
public String getVchdescripcion(int index) {
	dbrPARTEAPLICACION a = (dbrPARTEAPLICACION) vPartes.elementAt(index);
	return a.getVchdescripciones();
}
/**
 * Insert the method's description here.
 * Creation date: (11/6/2002 4:57:32 PM)
 * @return int
 */
public String getVchnumparte(int index) {
	dbrPARTEAPLICACION a = (dbrPARTEAPLICACION) vPartes.elementAt(index);
	return a.getVchnumparte();
}
/**
 * Insert the method's description here.
 * Creation date: (11/6/2002 4:57:32 PM)
 * @return int
 */
public String getVchnumparteequival(int index) {
	dbrPARTEAPLICACION a = (dbrPARTEAPLICACION) vPartes.elementAt(index);
	return a.getVchnumparteequival();
}
/**
 * Insert the method's description here.
 * Creation date: (11/6/2002 4:57:08 PM)
 * @return java.util.Vector
 */
public java.util.Vector getVPartes() {
	return vPartes;
}
/**
 * Insert the method's description here.
 * Creation date: (11/7/2002 11:45:17 AM)
 * @param newIidcategoria int
 */
public void setIidcategoria(int newIidcategoria) {
	iidcategoria = newIidcategoria;
}
/**
 * Insert the method's description here.
 * Creation date: (11/6/2002 4:57:08 PM)
 * @param newVPartes java.util.Vector
 */
public void setVPartes(java.util.Vector newVPartes) {
	vPartes = newVPartes;
}
}
