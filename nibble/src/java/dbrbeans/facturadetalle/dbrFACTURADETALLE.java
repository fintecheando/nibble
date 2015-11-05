package dbrbeans.facturadetalle;

/**
 * Insert the type's description here.
 * Creation date: (11/13/2002 7:38:42 PM)
 * @author: 
 */
public class dbrFACTURADETALLE extends dbbeans.facturadetalle.dbFACTURADETALLE{
	private java.lang.String vchnumparte;
	private java.lang.String vchnivel;
	private java.lang.String vchdescripcion;
	private int idevueltas;
	private int devueltas;
	private java.lang.String vchrazonsocial;
	private float decutilidad;
	private String unidadDescripcion;
/**
 * dbrFACTURADETALLE constructor comment.
 */
public dbrFACTURADETALLE() {
	super();
}
/**
 * Insert the method's description here.
 * Creation date: (11/21/2002 1:30:20 PM)
 * @return float
 */
public float getDecutilidad() {
	return decutilidad;
}
/**
 * Insert the method's description here.
 * Creation date: (11/14/2002 7:06:27 PM)
 * @return int
 */
public int getDevueltas() {
	return devueltas;
}
/**
 * Insert the method's description here.
 * Creation date: (11/14/2002 4:23:09 PM)
 * @return int
 */
public int getIdevueltas() {
	return idevueltas;
}
/**
 * Insert the method's description here.
 * Creation date: (11/14/2002 12:49:37 PM)
 * @return java.lang.String
 */
public java.lang.String getVchdescripcion() {
	return vchdescripcion;
}
/**
 * Insert the method's description here.
 * Creation date: (11/13/2002 7:41:10 PM)
 * @return java.lang.String
 */
public java.lang.String getVchnivel() {
	return vchnivel;
}
/**
 * Insert the method's description here.
 * Creation date: (11/13/2002 7:40:04 PM)
 * @return java.lang.String
 */
public java.lang.String getVchnumparte() {
	return vchnumparte;
}
/**
 * Insert the method's description here.
 * Creation date: (11/20/2002 12:33:13 PM)
 * @return java.lang.String
 */
public java.lang.String getVchrazonsocial() {
	return vchrazonsocial;
}
/**
 * Insert the method's description here.
 * Creation date: (11/21/2002 1:30:20 PM)
 * @param newDecutilidad float
 */
public void setDecutilidad(float newDecutilidad) {
	decutilidad = newDecutilidad;
}
/**
 * Insert the method's description here.
 * Creation date: (11/14/2002 7:06:27 PM)
 * @param newDevueltas int
 */
public void setDevueltas(int newDevueltas) {
	devueltas = newDevueltas;
}
/**
 * Insert the method's description here.
 * Creation date: (11/14/2002 4:23:09 PM)
 * @param newIdevueltas int
 */
public void setIdevueltas(int newIdevueltas) {
	idevueltas = newIdevueltas;
}
/**
 * Insert the method's description here.
 * Creation date: (11/14/2002 12:49:37 PM)
 * @param newVchdescripcion java.lang.String
 */
public void setVchdescripcion(java.lang.String newVchdescripcion) {
	vchdescripcion = newVchdescripcion;
}
/**
 * Insert the method's description here.
 * Creation date: (11/13/2002 7:41:10 PM)
 * @param newVchnivel java.lang.String
 */
public void setVchnivel(java.lang.String newVchnivel) {
	vchnivel = newVchnivel;
}
/**
 * Insert the method's description here.
 * Creation date: (11/13/2002 7:40:04 PM)
 * @param newVchnumparte java.lang.String
 */
public void setVchnumparte(java.lang.String newVchnumparte) {
	vchnumparte = newVchnumparte;
}
/**
 * Insert the method's description here.
 * Creation date: (11/20/2002 12:33:13 PM)
 * @param newVchrazonsocial java.lang.String
 */
public void setVchrazonsocial(java.lang.String newVchrazonsocial) {
	vchrazonsocial = newVchrazonsocial;
}
	/**
	 * @return
	 */
	public String getUnidadDescripcion() {
		return unidadDescripcion;
	}

	/**
	 * @param string
	 */
	public void setUnidadDescripcion(String string) {
		unidadDescripcion = string;
	}

}
