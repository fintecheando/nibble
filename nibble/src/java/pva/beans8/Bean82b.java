package pva.beans8;

/**
 * Insert the type's description here.
 * Creation date: (10/31/2002 6:29:39 PM)
 * @author: 
 */
import dbrbeans.movimieninventario.*;
import org.nibble.util.Fecha;

public class Bean82b {
	public java.util.Vector vMovimien;
/**
 * Bean82b constructor comment.
 */
public Bean82b() {
	super();
}
/**
 * Insert the method's description here.
 * Creation date: (10/31/2002 6:30:56 PM)
 * @return int
 */
public int getCantidad(int index) {
	dbrMOVIMIENINVENTARIO a = (dbrMOVIMIENINVENTARIO) vMovimien.elementAt(index);
	return a.getCantidad();
}
/**
 * Insert the method's description here.
 * Creation date: (10/31/2002 6:30:56 PM)
 * @return int
 */
public String getFechaMovimiento(int index) {
	dbrMOVIMIENINVENTARIO a = (dbrMOVIMIENINVENTARIO) vMovimien.elementAt(index);
	return Fecha.formatMySQL(a.getFechaMovimiento());
}
/**
 * Insert the method's description here.
 * Creation date: (10/31/2002 6:30:56 PM)
 * @return int
 */
public String getNombreUsuario(int index) {
	dbrMOVIMIENINVENTARIO a = (dbrMOVIMIENINVENTARIO) vMovimien.elementAt(index);
	return a.getNombreUsuario();
}
/**
 * Insert the method's description here.
 * Creation date: (10/31/2002 6:30:56 PM)
 * @return int
 */
public String getReferencia(int index) {
	dbrMOVIMIENINVENTARIO a = (dbrMOVIMIENINVENTARIO) vMovimien.elementAt(index);
	return a.getReferencia();
}
/**
 * Insert the method's description here.
 * Creation date: (10/31/2002 6:30:56 PM)
 * @return int
 */
public int getSize() {
	return vMovimien.size();
}
/**
 * Insert the method's description here.
 * Creation date: (10/31/2002 6:30:56 PM)
 * @return int
 */
public String getTipodemovimiento(int index) {
	dbrMOVIMIENINVENTARIO a = (dbrMOVIMIENINVENTARIO) vMovimien.elementAt(index);
	return a.getTipodemovimiento();
}
/**
 * Insert the method's description here.
 * Creation date: (10/31/2002 6:30:56 PM)
 * @return int
 */
public int getTitipomov(int index) {
	dbrMOVIMIENINVENTARIO a = (dbrMOVIMIENINVENTARIO) vMovimien.elementAt(index);
	return a.getTitipomov();
}
/**
 * Insert the method's description here.
 * Creation date: (10/31/2002 6:30:56 PM)
 * @return int
 */
public String getVchnumparte(int index) {
	dbrMOVIMIENINVENTARIO a = (dbrMOVIMIENINVENTARIO) vMovimien.elementAt(index);
	return a.getVchnumparte();
}
/**
 * Insert the method's description here.
 * Creation date: (10/31/2002 6:30:56 PM)
 * @return int
 */
public String getVchrazonsocial(int index) {
	dbrMOVIMIENINVENTARIO a = (dbrMOVIMIENINVENTARIO) vMovimien.elementAt(index);
	return a.getVchrazonsocial();
}
/**
 * Insert the method's description here.
 * Creation date: (10/31/2002 6:30:05 PM)
 * @return java.util.Vector
 */
public java.util.Vector getVMovimien() {
	return vMovimien;
}
/**
 * Insert the method's description here.
 * Creation date: (10/31/2002 6:30:05 PM)
 * @param newVMovimien java.util.Vector
 */
public void setVMovimien(java.util.Vector newVMovimien) {
	vMovimien = newVMovimien;
}
}
