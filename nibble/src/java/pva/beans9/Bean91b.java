package pva.beans9;

/**
 * Insert the type's description here.
 * Creation date: (11/1/2002 12:02:23 PM)
 * @author: 
 */
import dbbeans.cliente.*;

public class Bean91b {
	public java.util.Vector vClientes;
/**
 * Bean91b constructor comment.
 */
public Bean91b() {
	super();
}
/**
 * Insert the method's description here.
 * Creation date: (11/1/2002 12:03:06 PM)
 * @return int
 */
public int getClientesSize() {
	return vClientes.size();
}
/**
 * Insert the method's description here.
 * Creation date: (11/1/2002 12:03:06 PM)
 * @return int
 */
public int getIidnocliente(int index) {
	dbCLIENTE a = (dbCLIENTE) vClientes.elementAt(index);
	return a.getIidnocliente();
}
/**
 * Insert the method's description here.
 * Creation date: (11/1/2002 12:03:06 PM)
 * @return int
 */
public String getVchrazonsocial(int index) {
	dbCLIENTE a = (dbCLIENTE) vClientes.elementAt(index);
	return a.getVchrazonsocial()==null?"":a.getVchrazonsocial();
}
/**
 * Insert the method's description here.
 * Creation date: (11/1/2002 12:03:06 PM)
 * @return int
 */
public String getVchrfc(int index) {
	dbCLIENTE a = (dbCLIENTE) vClientes.elementAt(index);
	return a.getVchrfc()==null?"":a.getVchrfc();
}
/**
 * Insert the method's description here.
 * Creation date: (11/1/2002 12:02:39 PM)
 * @return java.util.Vector
 */
public java.util.Vector getVClientes() {
	return vClientes;
}
/**
 * Insert the method's description here.
 * Creation date: (11/1/2002 12:02:39 PM)
 * @param newVClientes java.util.Vector
 */
public void setVClientes(java.util.Vector newVClientes) {
	vClientes = newVClientes;
}
}
