package pva.beans9;

/**
 * Insert the type's description here.
 * Creation date: (11/19/2002 12:43:58 PM)
 * @author: 
 */
import dbrbeans.proveedorinventario.*;

public class Bean91f {
	private java.util.Vector vPartes;
/**
 * Bean91f constructor comment.
 */
public Bean91f() {
	super();
	vPartes = new java.util.Vector();
}
/**
 * Insert the method's description here.
 * Creation date: (11/19/2002 12:44:16 PM)
 * @return java.util.Vector
 */
public void addRecord(Bean91frecord record) {
	vPartes.add(record);
}
/**
 * Insert the method's description here.
 * Creation date: (11/19/2002 12:44:16 PM)
 * @return java.util.Vector
 */
public float getDecpreciob100(int index) {
	Bean91frecord a = (Bean91frecord) vPartes.elementAt(index);
	return a.getParte().getDecpreciob100();

}
/**
 * Insert the method's description here.
 * Creation date: (11/19/2002 12:44:16 PM)
 * @return java.util.Vector
 */
public int getIcantidad(int index) {
	Bean91frecord a = (Bean91frecord) vPartes.elementAt(index);
	return a.getIcantidad();

}
/**
 * Insert the method's description here.
 * Creation date: (11/19/2002 12:44:16 PM)
 * @return java.util.Vector
 */
public long getLiidparte(int index) {
	Bean91frecord a = (Bean91frecord) vPartes.elementAt(index);
	return a.getParte().getLiidparte();

}
/**
 * Insert the method's description here.
 * Creation date: (11/19/2002 12:44:16 PM)
 * @return java.util.Vector
 */
public boolean getProveeBitprovefavorito(int i, int j) {
	Bean91frecord a = (Bean91frecord) vPartes.elementAt(i);
	dbrPROVEEDOR b = (dbrPROVEEDOR) a.getVProveedores().elementAt(j);
	return b.isBitprovefavorito();

}
/**
 * Insert the method's description here.
 * Creation date: (11/19/2002 12:44:16 PM)
 * @return java.util.Vector
 */
public float getProveeDecprecio(int i, int j) {
	Bean91frecord a = (Bean91frecord) vPartes.elementAt(i);
	dbrPROVEEDOR b = (dbrPROVEEDOR) a.getVProveedores().elementAt(j);
	return a.getDecpreciob100() * b.getDecfactorcostovent();

}
/**
 * Insert the method's description here.
 * Creation date: (11/19/2002 12:44:16 PM)
 * @return java.util.Vector
 */
public int getProveeIexistencia(int i, int j) {
	Bean91frecord a = (Bean91frecord) vPartes.elementAt(i);
	dbrPROVEEDOR b = (dbrPROVEEDOR) a.getVProveedores().elementAt(j);
	return b.getIexistencia();

}
/**
 * Insert the method's description here.
 * Creation date: (11/19/2002 12:44:16 PM)
 * @return java.util.Vector
 */
public int getProveeIidproveedor(int i, int j) {
	Bean91frecord a = (Bean91frecord) vPartes.elementAt(i);
	dbrPROVEEDOR b = (dbrPROVEEDOR) a.getVProveedores().elementAt(j);
	return b.getIidproveedor();

}
/**
 * Insert the method's description here.
 * Creation date: (11/19/2002 12:44:16 PM)
 * @return java.util.Vector
 */
public int getProveeSize(int index) {
	Bean91frecord a = (Bean91frecord) vPartes.elementAt(index);
	return a.getVProveedores().size();

}
/**
 * Insert the method's description here.
 * Creation date: (11/19/2002 12:44:16 PM)
 * @return java.util.Vector
 */
public String getProveeVchrazonsocial(int i, int j) {
	Bean91frecord a = (Bean91frecord) vPartes.elementAt(i);
	dbrPROVEEDOR b = (dbrPROVEEDOR) a.getVProveedores().elementAt(j);
	return b.getVchrazonsocial();

}
/**
 * Insert the method's description here.
 * Creation date: (11/19/2002 12:44:16 PM)
 * @return java.util.Vector
 */
public int getSize() {
	return vPartes.size();
}
/**
 * Insert the method's description here.
 * Creation date: (11/19/2002 12:44:16 PM)
 * @return java.util.Vector
 */
public String getVchdescripciones(int index) {
	Bean91frecord a = (Bean91frecord) vPartes.elementAt(index);
	return a.getParte().getVchdescripciones();

}
/**
 * Insert the method's description here.
 * Creation date: (11/19/2002 12:44:16 PM)
 * @return java.util.Vector
 */
public String getVchnumparte(int index) {
	Bean91frecord a = (Bean91frecord) vPartes.elementAt(index);
	return a.getParte().getVchnumparte();

}
/**
 * Insert the method's description here.
 * Creation date: (11/19/2002 12:44:16 PM)
 * @return java.util.Vector
 */
public String getVchnumparteequival(int index) {
	Bean91frecord a = (Bean91frecord) vPartes.elementAt(index);
	return a.getParte().getVchnumparteequival();

}
/**
 * Insert the method's description here.
 * Creation date: (11/19/2002 12:44:16 PM)
 * @return java.util.Vector
 */
public java.util.Vector getVPartes() {
	return vPartes;
}
/**
 * Insert the method's description here.
 * Creation date: (11/19/2002 12:44:16 PM)
 * @param newVPartes java.util.Vector
 */
public void setVPartes(java.util.Vector newVPartes) {
	vPartes = newVPartes;
}
}
