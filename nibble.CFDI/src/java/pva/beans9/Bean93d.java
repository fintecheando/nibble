package pva.beans9;

/**
 * Insert the type's description here.
 * Creation date: (11/21/2002 10:41:28 AM)
 * @author: 
 */
import dbrbeans.proveedorinventario.*;

public class Bean93d {
	private java.util.Vector vProveedores;
	private dbrbeans.parteclasificacion.dbrPARTE parte;
/**
 * Bean93d constructor comment.
 */
public Bean93d() {
	super();
}
/**
 * Insert the method's description here.
 * Creation date: (11/21/2002 10:44:32 AM)
 * @return dbrbeans.parteclasificacion.dbrPARTE
 */
public dbrbeans.parteclasificacion.dbrPARTE getParte() {
	return parte;
}
/**
 * Insert the method's description here.
 * Creation date: (11/21/2002 10:42:39 AM)
 * @return java.util.Vector
 */
public boolean getProveeBitprovefavorito(int index) {
	dbrPROVEEDOR a = (dbrPROVEEDOR) vProveedores.elementAt(index);
	return a.isBitprovefavorito();
}
/**
 * Insert the method's description here.
 * Creation date: (11/21/2002 10:42:39 AM)
 * @return java.util.Vector
 */
public float getProveeDecprecio(int index) {
	dbrPROVEEDOR a = (dbrPROVEEDOR) vProveedores.elementAt(index);
	return parte.getDecpreciob100() * a.getDecfactorcostovent();
}
/**
 * Insert the method's description here.
 * Creation date: (11/21/2002 10:42:39 AM)
 * @return java.util.Vector
 */
public dbrPROVEEDOR getProveedor(int index) {
	dbrPROVEEDOR a = (dbrPROVEEDOR) vProveedores.elementAt(index);
	return a;
}
/**
 * Insert the method's description here.
 * Creation date: (11/21/2002 10:42:39 AM)
 * @return java.util.Vector
 */
public int getProveeIexistencia(int index) {
	dbrPROVEEDOR a = (dbrPROVEEDOR) vProveedores.elementAt(index);
	return a.getIexistencia();
}
/**
 * Insert the method's description here.
 * Creation date: (11/21/2002 10:42:39 AM)
 * @return java.util.Vector
 */
public int getProveeIidproveedor(int index) {
	dbrPROVEEDOR a = (dbrPROVEEDOR) vProveedores.elementAt(index);
	return a.getIidproveedor();
}
/**
 * Insert the method's description here.
 * Creation date: (11/21/2002 10:42:39 AM)
 * @return java.util.Vector
 */
public int getProveeSize() {
	return vProveedores.size();
}
/**
 * Insert the method's description here.
 * Creation date: (11/21/2002 10:42:39 AM)
 * @return java.util.Vector
 */
public String getProveeVchrazonsocial(int index) {
	dbrPROVEEDOR a = (dbrPROVEEDOR) vProveedores.elementAt(index);
	return a.getVchrazonsocial();
}
/**
 * Insert the method's description here.
 * Creation date: (11/21/2002 10:42:39 AM)
 * @return java.util.Vector
 */
public String getVchnumparteequival() {
	return parte.getVchnumparteequival();
}
/**
 * Insert the method's description here.
 * Creation date: (11/21/2002 10:42:39 AM)
 * @return java.util.Vector
 */
public java.util.Vector getVProveedores() {
	return vProveedores;
}
/**
 * Insert the method's description here.
 * Creation date: (11/21/2002 10:44:32 AM)
 * @param newParte dbrbeans.parteclasificacion.dbrPARTE
 */
public void setParte(dbrbeans.parteclasificacion.dbrPARTE newParte) {
	parte = newParte;
}
/**
 * Insert the method's description here.
 * Creation date: (11/21/2002 10:42:39 AM)
 * @param newVProveedores java.util.Vector
 */
public void setVProveedores(java.util.Vector newVProveedores) {
	vProveedores = newVProveedores;
}
}
