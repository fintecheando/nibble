package pva.beans9;

/**
 * Insert the type's description here.
 * Creation date: (17/10/2002 06:25:56 p.m.)
 * @author: 
 */
import dbbeans.aplicacion.*;

public class Bean91Aplicacion {
	
	private String vchmotor;
	private short sicombustible;
	private short sitraccion;
	private short sitransmision;
	private short sialimcmbst;
	private short siaspiracion;
	private short sicilindros;
	private short sidesplazamiento;
	private String vchhprpm;
	private String chnumpuertas;
	private boolean bitdirhidraulica;
	private boolean bitaireacond;
	private boolean bitdadodebaja;

	private java.util.Vector v;
/**
 * Insert the method's description here.
 * Creation date: (11/6/2002 1:47:18 PM)
 */
public Bean91Aplicacion() {
	v = new java.util.Vector();
}
/**
 * Insert the method's description here.
 * Creation date: (17/10/2002 06:27:28 p.m.)
 * @return int
 */
public String getAA(int index) {

	dbAPLICACIONr db = (dbAPLICACIONr)v.elementAt(index);
	if (db.getBitaireacond())return "S";
	else return "N";
}
/**
 * Insert the method's description here.
 * Creation date: (17/10/2002 06:27:28 p.m.)
 * @return int
 */
public String getAlimentacion(int index) {

	dbAPLICACIONr db = (dbAPLICACIONr)v.elementAt(index);
	return db.getSialimcmbst();
}
/**
 * Insert the method's description here.
 * Creation date: (17/10/2002 06:27:28 p.m.)
 * @return int
 */
public int getAplicacion(int index) {

	dbAPLICACIONr db = (dbAPLICACIONr)v.elementAt(index);
	return db.getIidaplicacion();
}
/**
 * Insert the method's description here.
 * Creation date: (17/10/2002 06:27:28 p.m.)
 * @return int
 */
public String getAspiracion(int index) {

    dbAPLICACIONr db = (dbAPLICACIONr) v.elementAt(index);
    return db.getSiaspiracion();
}
/**
 * Insert the method's description here.
 * Creation date: (17/10/2002 06:27:28 p.m.)
 * @return int
 */
public String getCilindros(int index) {

	dbAPLICACIONr db = (dbAPLICACIONr)v.elementAt(index);
	return db.getSicilindros();
}
/**
 * Insert the method's description here.
 * Creation date: (17/10/2002 06:27:28 p.m.)
 * @return int
 */
public String getCombustible(int index) {

	dbAPLICACIONr db = (dbAPLICACIONr)v.elementAt(index);
	return db.getSicombustible();
}
/**
 * Insert the method's description here.
 * Creation date: (17/10/2002 06:27:28 p.m.)
 * @return int
 */
public String getDesplazamiento(int index) {

	dbAPLICACIONr db = (dbAPLICACIONr)v.elementAt(index);
	return db.getSidesplazamiento();
}
/**
 * Insert the method's description here.
 * Creation date: (17/10/2002 06:27:28 p.m.)
 * @return int
 */
public String getDireccion(int index) {

	dbAPLICACIONr db = (dbAPLICACIONr)v.elementAt(index);
	if (db.getBitdirhidraulica())return "S";
	else return "N";
}
/**
 * Insert the method's description here.
 * Creation date: (17/10/2002 06:27:28 p.m.)
 * @return int
 */
public String getModelo(int index) {

	dbAPLICACIONr db = (dbAPLICACIONr)v.elementAt(index);
	return db.getIidmodelo();
}
/**
 * Insert the method's description here.
 * Creation date: (17/10/2002 06:27:28 p.m.)
 * @return int
 */
public String getMotor(int index) {

	dbAPLICACIONr db = (dbAPLICACIONr)v.elementAt(index);
	return db.getVchmotor();
}
/**
 * Insert the method's description here.
 * Creation date: (17/10/2002 06:27:28 p.m.)
 * @return int
 */
public String getPlanta(int index) {

	dbAPLICACIONr db = (dbAPLICACIONr)v.elementAt(index);
	return db.getIidplanta();
}
/**
 * Insert the method's description here.
 * Creation date: (17/10/2002 06:27:28 p.m.)
 * @return int
 */
public String getPotencia(int index) {

	dbAPLICACIONr db = (dbAPLICACIONr)v.elementAt(index);
	return db.getVchhprpm();
}
/**
 * Insert the method's description here.
 * Creation date: (17/10/2002 06:27:28 p.m.)
 * @return int
 */
public String getSubmodelo(int index) {

	dbAPLICACIONr db = (dbAPLICACIONr)v.elementAt(index);
	return db.getIidsubmodelo();
}
/**
 * Insert the method's description here.
 * Creation date: (17/10/2002 06:27:28 p.m.)
 * @return int
 */
public String getTraccion(int index) {

	dbAPLICACIONr db = (dbAPLICACIONr)v.elementAt(index);
	return db.getSitraccion();
}
/**
 * Insert the method's description here.
 * Creation date: (17/10/2002 06:27:28 p.m.)
 * @return int
 */
public String getTransmision(int index) {

	dbAPLICACIONr db = (dbAPLICACIONr)v.elementAt(index);
	return db.getSitransmision();
}
/**
 * Insert the method's description here.
 * Creation date: (17/10/2002 06:31:46 p.m.)
 * @return java.util.Vector
 */
public java.util.Vector getV() {
	return v;
}
/**
 * Insert the method's description here.
 * Creation date: (17/10/2002 06:31:46 p.m.)
 * @return java.util.Vector
 */
public int getVSize() {
	return v.size();
}
/**
 * Insert the method's description here.
 * Creation date: (17/10/2002 06:27:28 p.m.)
 * @return int
 */
public short getYY(int index) {

	dbAPLICACIONr db = (dbAPLICACIONr)v.elementAt(index);
	return db.getSianio();
}
/**
 * Insert the method's description here.
 * Creation date: (17/10/2002 06:31:46 p.m.)
 * @param newV java.util.Vector
 */
public void setV(java.util.Vector newV) {
	v = newV;
}
}
