package pva.beans9;

/**
 * Insert the type's description here.
 * Creation date: (10/16/2002 8:08:51 PM)
 * @author: 
 */

import dbrbeans.parteclasificacion.*;
 
public class Bean91d {
	private java.util.Vector vPartes;
/**
 * Bean121 constructor comment.
 */
public Bean91d() {
	super();
}
/**
 * Insert the method's description here.
 * Creation date: (10/17/2002 6:19:06 PM)
 * @return float
 */
public float getDecprecio(int index) {
	dbrPARTE a = (dbrPARTE) vPartes.elementAt(index);
	return a.getDecprecio();
}
public String getVchDescripcion(int index) {
	dbrPARTE a = (dbrPARTE) vPartes.elementAt(index);
	return a.getVchdescripciones();
}


/**
 * Insert the method's description here.
 * Creation date: (10/17/2002 6:19:06 PM)
 * @return float
 */
public float getDecpreciob100(int index) {
	dbrPARTE a = (dbrPARTE) vPartes.elementAt(index);
	return a.getDecpreciob100();
}
/**
 * Insert the method's description here.
 * Creation date: (10/17/2002 6:19:06 PM)
 * @return float
 */
public float getFlalto(int index) {
	dbrPARTE a = (dbrPARTE) vPartes.elementAt(index);
	return a.getFlalto();
}
/**
 * Insert the method's description here.
 * Creation date: (10/17/2002 6:19:06 PM)
 * @return float
 */
public float getFlancho(int index) {
	dbrPARTE a = (dbrPARTE) vPartes.elementAt(index);
	return a.getFlancho();
}
/**
 * Insert the method's description here.
 * Creation date: (10/17/2002 6:19:06 PM)
 * @return float
 */
public float getFllargo(int index) {
	dbrPARTE a = (dbrPARTE) vPartes.elementAt(index);
	return a.getFllargo();
}
/**
 * Insert the method's description here.
 * Creation date: (10/17/2002 6:19:06 PM)
 * @return float
 */
public float getFlpeso(int index) {
	dbrPARTE a = (dbrPARTE) vPartes.elementAt(index);
	return a.getFlpeso();
}
/**
 * Insert the method's description here.
 * Creation date: (10/16/2002 8:12:36 PM)
 * @return int
 */
public long getLiidparte(int index) {
	dbrPARTE a = (dbrPARTE) vPartes.elementAt(index);
	return a.getLiidparte();
}
/**
 * Insert the method's description here.
 * Creation date: (10/16/2002 8:10:49 PM)
 * @return int
 */
public int getPartesSize() {
	return vPartes.size();
}
/**
 * Insert the method's description here.
 * Creation date: (10/17/2002 6:19:06 PM)
 * @return float
 */
public String getVchcategoria(int index) {
	dbrPARTE a = (dbrPARTE) vPartes.elementAt(index);
	return a.getVchcategoria();
}
/**
 * Insert the method's description here.
 * Creation date: (10/17/2002 6:19:06 PM)
 * @return float
 */
public String getVchgrupo(int index) {
	dbrPARTE a = (dbrPARTE) vPartes.elementAt(index);
	return a.getVchgrupo();
}
/**
 * Insert the method's description here.
 * Creation date: (10/17/2002 6:19:06 PM)
 * @return float
 */
public String getVchlinea(int index) {
	dbrPARTE a = (dbrPARTE) vPartes.elementAt(index);
	return a.getVchlinea();
}
/**
 * Insert the method's description here.
 * Creation date: (10/17/2002 6:19:06 PM)
 * @return float
 */
public String getVchmarca(int index) {
	dbrPARTE a = (dbrPARTE) vPartes.elementAt(index);
	return a.getVchmarca();
}
/**
 * Insert the method's description here.
 * Creation date: (10/16/2002 8:12:36 PM)
 * @return int
 */
public String getVchnumparte(int index) {
	dbrPARTE a = (dbrPARTE) vPartes.elementAt(index);
	return a.getVchnumparte();
}
/**
 * Insert the method's description here.
 * Creation date: (10/16/2002 8:12:36 PM)
 * @return int
 */
public String getVchnumparteequival(int index) {
	dbrPARTE a = (dbrPARTE) vPartes.elementAt(index);
	return a.getVchnumparteequival();
}
/**
 * Insert the method's description here.
 * Creation date: (10/16/2002 8:09:45 PM)
 * @return java.util.Vector
 */
public java.util.Vector getVPartes() {
	return vPartes;
}
/**
 * Insert the method's description here.
 * Creation date: (10/16/2002 8:09:45 PM)
 * @param newVPartes java.util.Vector
 */
public void setVPartes(java.util.Vector newVPartes) {
	vPartes = newVPartes;
}
}
