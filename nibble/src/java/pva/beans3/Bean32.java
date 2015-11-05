package pva.beans3;

/**
 * Insert the type's description here.
 * Creation date: (10/16/2002 3:58:36 PM)
 * @author: 
 */

import dbbeans.categoria.*;
import dbbeans.grupo.*;
import dbbeans.linea.*;
import dbbeans.marca.*;
import dbbeans.promocion.*;

import java.util.Vector;

public class Bean32 {
	private java.util.Vector vCategorias;
	private java.util.Vector vGrupos;
	private java.util.Vector vLineas;
	private java.util.Vector vMarcas;
	private java.util.Vector vPromocion;
	private java.lang.String categoriavchdescripcion;
	private java.lang.String grupovchdescripcion;
	private java.lang.String lineavchdescripcion;
	private java.lang.String marcavchdescripcion;
	private java.lang.String strFecha;	
	
/**
 * Bean12 constructor comment.
 */
public Bean32() {
	vCategorias = new Vector();
	vGrupos = new Vector();
	vLineas = new Vector();
	vMarcas = new Vector();
	vPromocion = new Vector();
	
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
 * Creation date: (05/11/2002 06:51:24 p.m.)
 * @param newIidcategoria int
 */
public String getIidcategoria() {
	return categoriavchdescripcion;
}
/**
 * Insert the method's description here.
 * Creation date: (05/11/2002 06:51:24 p.m.)
 * @param newIidcategoria int
 */
public String getIidgrupo() {
	return grupovchdescripcion;
}
/**
 * Insert the method's description here.
 * Creation date: (05/11/2002 06:51:24 p.m.)
 * @param newIidcategoria int
 */
public String getIidlinea() {
	return lineavchdescripcion;
}
/**
 * Insert the method's description here.
 * Creation date: (05/11/2002 06:51:24 p.m.)
 * @param newIidcategoria int
 */
public String getIidmarca() {
	return marcavchdescripcion;
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
 * Creation date: (05/11/2002 06:52:16 p.m.)
 * @return int
 */
public String getLineavchdescripcion() {
	return lineavchdescripcion;
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
public String getPdecvolumencompra(int index) {
	dbPROMOCION a = (dbPROMOCION) vPromocion.elementAt(index);
	return org.nibble.util.Formato.formateoNumerico(a.getDecvolumencompra(),"##,###,##0.00");
}
/**
 * Insert the method's description here.
 * Creation date: (10/16/2002 4:30:08 PM)
 * @return int
 * @param index int
 */
public int getPiexistencias(int index) {
	dbPROMOCION a = (dbPROMOCION) vPromocion.elementAt(index);
	return a.getIexistencias();
}
/**
 * Insert the method's description here.
 * Creation date: (10/16/2002 4:30:08 PM)
 * @return int
 * @param index int
 */
public int getPiidpromocion(int index) {
	dbPROMOCION a = (dbPROMOCION) vPromocion.elementAt(index);
	return a.getIidpromocion();
}
/**
 * Insert the method's description here.
 * Creation date: (10/16/2002 4:26:47 PM)
 * @return int
 */
public int getPromocionSize() {
	return vPromocion.size();
}
/**
 * Insert the method's description here.
 * Creation date: (10/16/2002 4:30:08 PM)
 * @return int
 * @param index int
 */
public short getPsinopiezas(int index) {
	dbPROMOCION a = (dbPROMOCION) vPromocion.elementAt(index);
	return a.getSinopiezas();
}
/**
 * Insert the method's description here.
 * Creation date: (10/16/2002 4:30:08 PM)
 * @return int
 * @param index int
 */
public java.lang.String getFecha() {
	return strFecha;
}

public void setFecha(java.lang.String newFecha) {
	strFecha = newFecha;
}

public String getPtisfechafin (int index) {
	dbPROMOCION a = (dbPROMOCION) vPromocion.elementAt(index);
	return a.getTisfechafin();
}
/**
 * Insert the method's description here.
 * Creation date: (10/16/2002 4:30:08 PM)
 * @return int
 * @param index int
 */
public String getPtisfechainicio(int index) {
	dbPROMOCION a = (dbPROMOCION) vPromocion.elementAt(index);
	return a.getTisfechainicio();
	//return util.Fecha.formatMySQL(a.getTisfechainicio());
}
/**
 * Insert the method's description here.
 * Creation date: (10/16/2002 4:30:08 PM)
 * @return int
 * @param index int
 */
public String getPvchdescripcion(int index) {
	dbPROMOCION a = (dbPROMOCION) vPromocion.elementAt(index);
	return a.getVchdescripcion();
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
 * Creation date: (05/11/2002 06:51:24 p.m.)
 * @param newIidcategoria int
 */
public void setIidcategoria(String newIidcategoria) {
	categoriavchdescripcion = newIidcategoria;
}
/**
 * Insert the method's description here.
 * Creation date: (05/11/2002 06:52:00 p.m.)
 * @param newIidgrupo int
 */
public void setIidgrupo(String newIidgrupo) {
	grupovchdescripcion = newIidgrupo;
}
/**
 * Insert the method's description here.
 * Creation date: (05/11/2002 06:52:16 p.m.)
 * @param newIidlinea int
 */
public void setIidlinea(String newIidlinea) {
	lineavchdescripcion = newIidlinea;
}
/**
 * Insert the method's description here.
 * Creation date: (05/11/2002 06:53:56 p.m.)
 * @param newMarcavchdescripcion java.lang.String
 */
public void setIidmarca(java.lang.String newMarcavchdescripcion) {
	marcavchdescripcion = newMarcavchdescripcion;
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
 * Creation date: (10/16/2002 4:11:40 PM)
 * @param newVMarcas java.util.Vector
 */
public void setVPromocion(java.util.Vector newVPromocion) {
	vPromocion = newVPromocion;
}
}
