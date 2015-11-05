package pva.beans4;

/**
 * Insert the type's description here.
 * Creation date: (10/31/2002 11:01:55 AM)
 * @author: 
 */
import dbbeans.categoriacliente.*;
import dbbeans.niveldeprecio.*;
import dbbeans.viadeembarque.*;
 
public class Bean42b {
	private java.util.Vector vCategoriasCliente;
	private java.util.Vector vNiveles;
	private java.util.Vector vVias;
/**
 * Bean42b constructor comment.
 */
public Bean42b() {
	super();
}
/**
 * Insert the method's description here.
 * Creation date: (10/31/2002 11:11:21 AM)
 * @return int
 */
public String getCategoriasClienteDes(int index) {
	dbCATEGORIACLIENTE a = (dbCATEGORIACLIENTE) vCategoriasCliente.elementAt(index);
	return a.getVchdescripcion();
}
/**
 * Insert the method's description here.
 * Creation date: (10/31/2002 11:11:21 AM)
 * @return int
 */
public int getCategoriasClienteId(int index) {
	dbCATEGORIACLIENTE a = (dbCATEGORIACLIENTE) vCategoriasCliente.elementAt(index);
	return a.getIidcatcliente();
}
/**
 * Insert the method's description here.
 * Creation date: (10/31/2002 11:11:21 AM)
 * @return int
 */
public int getCategoriasClienteSize() {
	return vCategoriasCliente.size();
}
/**
 * Insert the method's description here.
 * Creation date: (10/31/2002 11:11:21 AM)
 * @return int
 */
public String getNivelesDes(int index) {
	dbNIVELDEPRECIO a = (dbNIVELDEPRECIO) vNiveles.elementAt(index);
	return a.getVchnivel();
}
/**
 * Insert the method's description here.
 * Creation date: (10/31/2002 11:11:21 AM)
 * @return int
 */
public int getNivelesId(int index) {
	dbNIVELDEPRECIO a = (dbNIVELDEPRECIO) vNiveles.elementAt(index);
	return a.getIidnivel();
}
/**
 * Insert the method's description here.
 * Creation date: (10/31/2002 11:11:21 AM)
 * @return int
 */
public int getNivelesIdCat(int index) {
	dbNIVELDEPRECIO a = (dbNIVELDEPRECIO) vNiveles.elementAt(index);
	return a.getIidcatcliente();
}
/**
 * Insert the method's description here.
 * Creation date: (10/31/2002 11:11:21 AM)
 * @return int
 */
public int getNivelesSize() {
	return vNiveles.size();
}
/**
 * Insert the method's description here.
 * Creation date: (10/31/2002 11:02:42 AM)
 * @return java.util.Vector
 */
public java.util.Vector getVCategoriasCliente() {
	return vCategoriasCliente;
}
/**
 * Insert the method's description here.
 * Creation date: (10/31/2002 11:11:21 AM)
 * @return int
 */
public String getViasDes(int index) {
	dbVIADEEMBARQUE a = (dbVIADEEMBARQUE) vVias.elementAt(index);
	return a.getVchdescviaembarque();
}
/**
 * Insert the method's description here.
 * Creation date: (10/31/2002 11:11:21 AM)
 * @return int
 */
public short getViasId(int index) {
	dbVIADEEMBARQUE a = (dbVIADEEMBARQUE) vVias.elementAt(index);
	return a.getSiidviaembarque();
}
/**
 * Insert the method's description here.
 * Creation date: (10/31/2002 11:11:21 AM)
 * @return int
 */
public int getViasSize() {
	return vVias.size();
}
/**
 * Insert the method's description here.
 * Creation date: (10/31/2002 11:03:03 AM)
 * @return java.util.Vector
 */
public java.util.Vector getVNiveles() {
	return vNiveles;
}
/**
 * Insert the method's description here.
 * Creation date: (10/31/2002 11:03:29 AM)
 * @return java.util.Vector
 */
public java.util.Vector getVVias() {
	return vVias;
}
/**
 * Insert the method's description here.
 * Creation date: (10/31/2002 11:02:42 AM)
 * @param newVCategoriasCliente java.util.Vector
 */
public void setVCategoriasCliente(java.util.Vector newVCategoriasCliente) {
	vCategoriasCliente = newVCategoriasCliente;
}
/**
 * Insert the method's description here.
 * Creation date: (10/31/2002 11:03:03 AM)
 * @param newVNiveles java.util.Vector
 */
public void setVNiveles(java.util.Vector newVNiveles) {
	vNiveles = newVNiveles;
}
/**
 * Insert the method's description here.
 * Creation date: (10/31/2002 11:03:29 AM)
 * @param newVVias java.util.Vector
 */
public void setVVias(java.util.Vector newVVias) {
	vVias = newVVias;
}
}
