package pva.beans8;

/**
 * Insert the type's description here.
 * Creation date: (10/16/2002 3:58:36 PM)
 * @author: 
 */

import dbbeans.categoria.*;
import dbbeans.grupo.*;
import dbbeans.linea.*;
import dbbeans.marca.*;
import dbbeans.tipodemovimiento.*;

import java.util.Vector;


public class Bean82 {
	private java.util.Vector vCategorias;
	private java.util.Vector vGrupos;
	private java.util.Vector vLineas;
	private java.util.Vector vMarcas;
	private java.util.Vector vTipoMovimiento;
/**
 * Bean12 constructor comment.
 */
public Bean82() {
	super();
	vCategorias = new Vector();
	vGrupos = new Vector();
	vLineas = new Vector();
	vMarcas = new Vector();
}
/**
 * Insert the method's description here.
 * Creation date: (10/16/2002 4:30:08 PM)
 * @return String
 * @param index int
 */
public String getCategoriasDes(int index) {
	dbCATEGORIA a = (dbCATEGORIA) vCategorias.elementAt(index);
	return a.getVchdescripcion();
}
/**
 * Insert the method's description here.
 * Creation date: (10/16/2002 4:30:08 PM)
 * @return int
 * @param index int
 */
public int getCategoriasId(int index) {
	dbCATEGORIA a = (dbCATEGORIA) vCategorias.elementAt(index);
	return a.getIidcategoria();
}
/**
 * Insert the method's description here.
 * Creation date: (10/16/2002 4:26:47 PM)
 * @return int
 */
public int getCategoriasSize() {
	return vCategorias.size();
}
/**
 * Insert the method's description here.
 * Creation date: (10/16/2002 4:30:08 PM)
 * @return String
 * @param index int
 */
public String getGruposDes(int index) {
	dbGRUPO a = (dbGRUPO) vGrupos.elementAt(index);
	return a.getVchdescripcion();
}
/**
 * Insert the method's description here.
 * Creation date: (10/16/2002 4:30:08 PM)
 * @return int
 * @param index int
 */
public int getGruposId(int index) {
	dbGRUPO a = (dbGRUPO) vGrupos.elementAt(index);
	return a.getIidgrupo();
}
/**
 * Insert the method's description here.
 * Creation date: (10/16/2002 4:30:08 PM)
 * @return int
 * @param index int
 */
public int getGruposIdCategoria(int index) {
	dbGRUPO a = (dbGRUPO) vGrupos.elementAt(index);
	return a.getIidcategoria();
}
/**
 * Insert the method's description here.
 * Creation date: (10/16/2002 4:26:47 PM)
 * @return int
 */
public int getGruposSize() {
	return vGrupos.size();
}
/**
 * Insert the method's description here.
 * Creation date: (10/16/2002 4:30:08 PM)
 * @return String
 * @param index int
 */
public String getLineasDes(int index) {
	dbLINEA a = (dbLINEA) vLineas.elementAt(index);
	return a.getVchdescripcion();
}
/**
 * Insert the method's description here.
 * Creation date: (10/16/2002 4:30:08 PM)
 * @return int
 * @param index int
 */
public int getLineasId(int index) {
	dbLINEA a = (dbLINEA) vLineas.elementAt(index);
	return a.getIidlinea();
}
/**
 * Insert the method's description here.
 * Creation date: (10/16/2002 4:30:08 PM)
 * @return int
 * @param index int
 */
public int getLineasIdGrupo(int index) {
	dbLINEA a = (dbLINEA) vLineas.elementAt(index);
	return a.getIidgrupo();
}
/**
 * Insert the method's description here.
 * Creation date: (10/16/2002 4:26:47 PM)
 * @return int
 */
public int getLineasSize() {
	return vLineas.size();
}
/**
 * Insert the method's description here.
 * Creation date: (10/16/2002 4:30:08 PM)
 * @return String
 * @param index int
 */
public String getMarcasDes(int index) {
	dbMARCA a = (dbMARCA) vMarcas.elementAt(index);
	return a.getVchnombre();
}
/**
 * Insert the method's description here.
 * Creation date: (10/16/2002 4:30:08 PM)
 * @return int
 * @param index int
 */
public int getMarcasId(int index) {
	dbMARCA a = (dbMARCA) vMarcas.elementAt(index);
	return a.getIidmarca();
}
/**
 * Insert the method's description here.
 * Creation date: (10/16/2002 4:30:08 PM)
 * @return int
 * @param index int
 */
public int getMarcasIdLinea(int index) {
	dbMARCA a = (dbMARCA) vMarcas.elementAt(index);
	return a.getIidlinea();
}
/**
 * Insert the method's description here.
 * Creation date: (10/16/2002 4:26:47 PM)
 * @return int
 */
public int getMarcasSize() {
	return vMarcas.size();
}
/**
 * Insert the method's description here.
 * Creation date: (10/16/2002 4:30:08 PM)
 * @return int
 * @param index int
 */
public String getTipoMovimientoDes(int index) {
	dbTIPODEMOVIMIENTO a = (dbTIPODEMOVIMIENTO) vTipoMovimiento.elementAt(index);
	return a.getVchdesctipomov();
}
/**
 * Insert the method's description here.
 * Creation date: (10/16/2002 4:30:08 PM)
 * @return int
 * @param index int
 */
public int getTipoMovimientoId(int index) {
	dbTIPODEMOVIMIENTO a = (dbTIPODEMOVIMIENTO) vTipoMovimiento.elementAt(index);
	return a.getTitipomov();
}
/**
 * Insert the method's description here.
 * Creation date: (10/16/2002 4:26:47 PM)
 * @return int
 */
public int getTipoMovimientoSize() {
	return vTipoMovimiento.size();
}
/**
 * Insert the method's description here.
 * Creation date: (10/16/2002 4:01:46 PM)
 * @return java.util.Vector
 */
public java.util.Vector getVCategorias() {
	return vCategorias;
}
/**
 * Insert the method's description here.
 * Creation date: (10/16/2002 4:02:25 PM)
 * @return java.util.Vector
 */
public java.util.Vector getVGrupos() {
	return vGrupos;
}
/**
 * Insert the method's description here.
 * Creation date: (10/16/2002 4:03:14 PM)
 * @return java.util.Vector
 */
public java.util.Vector getVLineas() {
	return vLineas;
}
/**
 * Insert the method's description here.
 * Creation date: (10/16/2002 4:11:40 PM)
 * @return java.util.Vector
 */
public java.util.Vector getVMarcas() {
	return vMarcas;
}
/**
 * Insert the method's description here.
 * Creation date: (10/31/2002 4:58:39 PM)
 * @return java.util.Vector
 */
public java.util.Vector getVTipoMovimiento() {
	return vTipoMovimiento;
}
/**
 * Insert the method's description here.
 * Creation date: (10/16/2002 4:01:46 PM)
 * @param newVCategorias java.util.Vector
 */
public void setVCategorias(java.util.Vector newVCategorias) {
	vCategorias = newVCategorias;
}
/**
 * Insert the method's description here.
 * Creation date: (10/16/2002 4:02:25 PM)
 * @param newVGrupos java.util.Vector
 */
public void setVGrupos(java.util.Vector newVGrupos) {
	vGrupos = newVGrupos;
}
/**
 * Insert the method's description here.
 * Creation date: (10/16/2002 4:03:14 PM)
 * @param newVLineas java.util.Vector
 */
public void setVLineas(java.util.Vector newVLineas) {
	vLineas = newVLineas;
}
/**
 * Insert the method's description here.
 * Creation date: (10/16/2002 4:11:40 PM)
 * @param newVMarcas java.util.Vector
 */
public void setVMarcas(java.util.Vector newVMarcas) {
	vMarcas = newVMarcas;
}
/**
 * Insert the method's description here.
 * Creation date: (10/31/2002 4:58:39 PM)
 * @param newVTipoMovimiento java.util.Vector
 */
public void setVTipoMovimiento(java.util.Vector newVTipoMovimiento) {
	vTipoMovimiento = newVTipoMovimiento;
}
}
