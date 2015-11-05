package pva.beans8;

/**
 * Insert the type's description here.
 * Creation date: (11/25/2002 12:08:34 PM)
 * @author: 
 */
import dbrbeans.inventario.*;
 
public class Bean83b {
	private java.util.Vector vInventario;
/**
 * Bean83b constructor comment.
 */
public Bean83b() {
	super();
}
/**
 * Insert the method's description here.
 * Creation date: (11/25/2002 12:08:58 PM)
 * @return java.util.Vector
 */
public int getIcantconsumo(int index) {
	dbrINVENTARIO a = (dbrINVENTARIO) vInventario.elementAt(index);
	return a.getIcantconsumo();

}
/**
 * Insert the method's description here.
 * Creation date: (11/25/2002 12:08:58 PM)
 * @return java.util.Vector
 */
public int getIexistencia(int index) {
	dbrINVENTARIO a = (dbrINVENTARIO) vInventario.elementAt(index);
	return a.getIexistencia();

}
/**
 * Insert the method's description here.
 * Creation date: (11/25/2002 12:08:58 PM)
 * @return java.util.Vector
 */
public int getIidproveedor(int index) {
	dbrINVENTARIO a = (dbrINVENTARIO) vInventario.elementAt(index);
	return a.getIidproveedor();

}
/**
 * Insert the method's description here.
 * Creation date: (11/25/2002 12:08:58 PM)
 * @return java.util.Vector
 */
public long getLiidparte(int index) {
	dbrINVENTARIO a = (dbrINVENTARIO) vInventario.elementAt(index);
	return a.getLiidparte();

}
/**
 * Insert the method's description here.
 * Creation date: (11/25/2002 12:08:58 PM)
 * @return java.util.Vector
 */
public int getSize() {
	return vInventario.size();
}
/**
 * Insert the method's description here.
 * Creation date: (11/25/2002 12:08:58 PM)
 * @return java.util.Vector
 */
public String getVchnumparte(int index) {
	dbrINVENTARIO a = (dbrINVENTARIO) vInventario.elementAt(index);
	return a.getVchnumparte();

}
/**
 * Insert the method's description here.
 * Creation date: (11/25/2002 12:08:58 PM)
 * @return java.util.Vector
 */
public String getVchrazonsocial(int index) {
	dbrINVENTARIO a = (dbrINVENTARIO) vInventario.elementAt(index);
	return a.getVchrazonsocial();

}
/**
 * Insert the method's description here.
 * Creation date: (11/25/2002 12:08:58 PM)
 * @return java.util.Vector
 */
public String getVchubicacion(int index) {
	dbrINVENTARIO a = (dbrINVENTARIO) vInventario.elementAt(index);
	return a.getVchubicacion()==null?"":a.getVchubicacion();

}
/**
 * Insert the method's description here.
 * Creation date: (11/25/2002 12:08:58 PM)
 * @return java.util.Vector
 */
public java.util.Vector getVInventario() {
	return vInventario;
}
/**
 * Insert the method's description here.
 * Creation date: (11/25/2002 12:08:58 PM)
 * @param newVInventario java.util.Vector
 */
public void setVInventario(java.util.Vector newVInventario) {
	vInventario = newVInventario;
}
}
