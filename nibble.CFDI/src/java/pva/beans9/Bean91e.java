package pva.beans9;

/**
 * Insert the type's description here.
 * Creation date: (15/10/2002 01:14:16 p.m.)
 * @author: 
 */
import dbbeans.planta.*;
import dbbeans.modelo.*;
import dbbeans.submodelo.*;
import dbbeans.combustible.*;
import dbbeans.traccion.*;
import dbbeans.transmision.*;
import dbbeans.alimentacion_combustible.*;
import dbbeans.aspiracion.*;
import dbbeans.cilindros.*;
 
public class Bean91e {
	private java.util.Vector vPlantas;
	private java.util.Vector vModelos;
	private java.util.Vector vSmodelos;
	private java.util.Vector vCombustibles;
	private java.util.Vector vTraccions;
	private java.util.Vector vTransmisions;
	private java.util.Vector vAlimentacions;
	private java.util.Vector vAspiracions;
	private java.util.Vector vCilindros;	
/**
 * Insert the method's description here.
 * Creation date: (11/6/2002 3:45:46 PM)
 */
public Bean91e() {
	vPlantas = new java.util.Vector();
	vModelos = new java.util.Vector();
	vSmodelos = new java.util.Vector();
	vCombustibles = new java.util.Vector();
	vTraccions = new java.util.Vector();
	vTransmisions = new java.util.Vector();
	vAlimentacions = new java.util.Vector();
	vAspiracions = new java.util.Vector();
	vCilindros = new java.util.Vector();		
}
/**
 * Insert the method's description here.
 * Creation date: (15/10/2002 01:19:04 p.m.)
 * @return java.util.Vector
 */
public String getAlimentacionDes(int index) {

	dbALIMENTACION_COMBUSTIBLE a = (dbALIMENTACION_COMBUSTIBLE)vAlimentacions.elementAt(index);
	return a.getVchdescripcion();
}
/**
 * Insert the method's description here.
 * Creation date: (15/10/2002 01:19:04 p.m.)
 * @return java.util.Vector
 */
public int getAlimentacionId(int index) {

	dbALIMENTACION_COMBUSTIBLE a = (dbALIMENTACION_COMBUSTIBLE)vAlimentacions.elementAt(index);
	return a.getSialimcmbst();
}
/**
 * Insert the method's description here.
 * Creation date: (15/10/2002 01:19:04 p.m.)
 * @return java.util.Vector
 */
public int getAlimentacionsSize() {
	return vAlimentacions.size();
}
/**
 * Insert the method's description here.
 * Creation date: (15/10/2002 01:19:04 p.m.)
 * @return java.util.Vector
 */
public String getAspiracionDes(int index) {

	dbASPIRACION a = (dbASPIRACION)vAspiracions.elementAt(index);
	return a.getVchdescripcion();
}
/**
 * Insert the method's description here.
 * Creation date: (15/10/2002 01:19:04 p.m.)
 * @return java.util.Vector
 */
public int getAspiracionId(int index) {

	dbASPIRACION a = (dbASPIRACION)vAspiracions.elementAt(index);
	return a.getSiaspiracion();
}
/**
 * Insert the method's description here.
 * Creation date: (15/10/2002 01:19:04 p.m.)
 * @return java.util.Vector
 */
public int getAspiracionsSize() {
	return vAspiracions.size();
}
/**
 * Insert the method's description here.
 * Creation date: (15/10/2002 01:19:04 p.m.)
 * @return java.util.Vector
 */
public String getCilindroDes(int index) {

	dbCILINDROS a = (dbCILINDROS)vCilindros.elementAt(index);
	return a.getVchdescripcion();
}
/**
 * Insert the method's description here.
 * Creation date: (15/10/2002 01:19:04 p.m.)
 * @return java.util.Vector
 */
public int getCilindroId(int index) {

	dbCILINDROS a = (dbCILINDROS)vCilindros.elementAt(index);
	return a.getSicilindros();
}
/**
 * Insert the method's description here.
 * Creation date: (15/10/2002 01:17:39 p.m.)
 * @return java.util.Vector
 */
public int getCilindrosSize() {
	return vCilindros.size();
}
/**
 * Insert the method's description here.
 * Creation date: (15/10/2002 01:19:04 p.m.)
 * @return java.util.Vector
 */
public String getCombustibleDes(int index) {

	dbCOMBUSTIBLE a = (dbCOMBUSTIBLE)vCombustibles.elementAt(index);
	return a.getVchdescripcion();
}
/**
 * Insert the method's description here.
 * Creation date: (15/10/2002 01:19:04 p.m.)
 * @return java.util.Vector
 */
public int getCombustibleId(int index) {

	dbCOMBUSTIBLE a = (dbCOMBUSTIBLE)vCombustibles.elementAt(index);
	return a.getSicombustible();
}
/**
 * Insert the method's description here.
 * Creation date: (15/10/2002 01:19:04 p.m.)
 * @return java.util.Vector
 */
public int getCombustiblesSize() {
	return vCombustibles.size();
}
/**
 * Insert the method's description here.
 * Creation date: (15/10/2002 01:17:39 p.m.)
 * @return java.util.Vector
 */
public String getModeloDes(int index) {

	dbMODELO a = (dbMODELO)vModelos.elementAt(index);
	return a.getVchdescripcion();
}
/**
 * Insert the method's description here.
 * Creation date: (15/10/2002 01:17:39 p.m.)
 * @return java.util.Vector
 */
public int getModeloId(int index) {

	dbMODELO a = (dbMODELO)vModelos.elementAt(index);
	return a.getIidmodelo();
}
/**
 * Insert the method's description here.
 * Creation date: (15/10/2002 01:17:39 p.m.)
 * @return java.util.Vector
 */
public int getModeloPId(int index) {

	dbMODELO a = (dbMODELO)vModelos.elementAt(index);
	return a.getIidplanta();
}
/**
 * Insert the method's description here.
 * Creation date: (15/10/2002 01:17:39 p.m.)
 * @return java.util.Vector
 */
public int getModelosSize() {
	return vModelos.size();
}
/**
 * Insert the method's description here.
 * Creation date: (15/10/2002 01:16:59 p.m.)
 * @return java.util.Vector
 */
public String getPlantaDes(int index) {
	
	dbPLANTA a = (dbPLANTA)vPlantas.elementAt(index);
	return a.getVchdescripcion();
}
/**
 * Insert the method's description here.
 * Creation date: (15/10/2002 01:16:59 p.m.)
 * @return java.util.Vector
 */
public int getPlantaId(int index) {
	
	dbPLANTA a = (dbPLANTA)vPlantas.elementAt(index);
	return a.getIidplanta();
}
/**
 * Insert the method's description here.
 * Creation date: (15/10/2002 01:16:59 p.m.)
 * @return java.util.Vector
 */
public int getPlantasSize() {
	return vPlantas.size();
}
/**
 * Insert the method's description here.
 * Creation date: (15/10/2002 01:17:39 p.m.)
 * @return java.util.Vector
 */
public String getSmodeloDes(int index) {

	dbSUBMODELO smodelo = (dbSUBMODELO)vSmodelos.elementAt(index);
	return smodelo.getVchdescripcion();
}
/**
 * Insert the method's description here.
 * Creation date: (15/10/2002 01:17:39 p.m.)
 * @return java.util.Vector
 */
public int getSmodeloId(int index) {

	dbSUBMODELO smodelo = (dbSUBMODELO)vSmodelos.elementAt(index);
	return smodelo.getIidsubmodelo();
}
/**
 * Insert the method's description here.
 * Creation date: (15/10/2002 01:17:39 p.m.)
 * @return java.util.Vector
 */
public int getSmodeloMId(int index) {

	dbSUBMODELO smodelo = (dbSUBMODELO)vSmodelos.elementAt(index);
	return smodelo.getIidmodelo();
}
/**
 * Insert the method's description here.
 * Creation date: (15/10/2002 01:19:04 p.m.)
 * @return java.util.Vector
 */
public int getSmodelosSize() {
	return vSmodelos.size();
}
/**
 * Insert the method's description here.
 * Creation date: (15/10/2002 01:19:04 p.m.)
 * @return java.util.Vector
 */
public String getTraccionDes(int index) {

	dbTRACCION a = (dbTRACCION)vTraccions.elementAt(index);
	return a.getVchdescripcion();
}
/**
 * Insert the method's description here.
 * Creation date: (15/10/2002 01:19:04 p.m.)
 * @return java.util.Vector
 */
public int getTraccionId(int index) {

	dbTRACCION a = (dbTRACCION)vTraccions.elementAt(index);
	return a.getSitraccion();
}
/**
 * Insert the method's description here.
 * Creation date: (15/10/2002 01:19:04 p.m.)
 * @return java.util.Vector
 */
public int getTraccionsSize() {
	return vTraccions.size();
}
/**
 * Insert the method's description here.
 * Creation date: (15/10/2002 01:19:04 p.m.)
 * @return java.util.Vector
 */
public String getTransmisionDes(int index) {

	dbTRANSMISION a = (dbTRANSMISION)vTransmisions.elementAt(index);
	return a.getVchdescripcion();
}
/**
 * Insert the method's description here.
 * Creation date: (15/10/2002 01:19:04 p.m.)
 * @return java.util.Vector
 */
public int getTransmisionId(int index) {

	dbTRANSMISION a = (dbTRANSMISION)vTransmisions.elementAt(index);
	return a.getSitransmision();
}
/**
 * Insert the method's description here.
 * Creation date: (15/10/2002 01:19:04 p.m.)
 * @return java.util.Vector
 */
public int getTransmisionsSize() {
	return vTransmisions.size();
}
/**
 * Insert the method's description here.
 * Creation date: (15/10/2002 01:17:39 p.m.)
 * @param newModelos java.util.Vector
 */
public void setAlimentacions(java.util.Vector newV) {
	vAlimentacions = newV;
}
/**
 * Insert the method's description here.
 * Creation date: (15/10/2002 01:17:39 p.m.)
 * @param newModelos java.util.Vector
 */
public void setAspiracions(java.util.Vector newV) {
	vAspiracions = newV;
}
/**
 * Insert the method's description here.
 * Creation date: (15/10/2002 01:17:39 p.m.)
 * @param newModelos java.util.Vector
 */
public void setCilindros(java.util.Vector newV) {
	vCilindros = newV;
}
/**
 * Insert the method's description here.
 * Creation date: (15/10/2002 01:17:39 p.m.)
 * @param newModelos java.util.Vector
 */
public void setCombustibles(java.util.Vector newV) {
	vCombustibles = newV;
}
/**
 * Insert the method's description here.
 * Creation date: (15/10/2002 01:17:39 p.m.)
 * @param newModelos java.util.Vector
 */
public void setModelos(java.util.Vector newModelos) {
	vModelos = newModelos;
}
/**
 * Insert the method's description here.
 * Creation date: (15/10/2002 01:16:59 p.m.)
 * @param newVPlantas java.util.Vector
 */
public void setPlantas(java.util.Vector newPlantas) {
	vPlantas = newPlantas;
}
/**
 * Insert the method's description here.
 * Creation date: (15/10/2002 01:19:04 p.m.)
 * @param newSmodelos java.util.Vector
 */
public void setSmodelos(java.util.Vector newSmodelos) {
	vSmodelos = newSmodelos;
}
/**
 * Insert the method's description here.
 * Creation date: (15/10/2002 01:17:39 p.m.)
 * @param newModelos java.util.Vector
 */
public void setTraccions(java.util.Vector newV) {
	vTraccions = newV;
}
/**
 * Insert the method's description here.
 * Creation date: (15/10/2002 01:17:39 p.m.)
 * @param newModelos java.util.Vector
 */
public void setTransmisions(java.util.Vector newV) {
	vTransmisions = newV;
}
}
