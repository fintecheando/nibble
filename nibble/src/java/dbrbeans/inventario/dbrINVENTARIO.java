package dbrbeans.inventario;

/**
 * Insert the type's description here.
 * Creation date: (11/25/2002 11:44:39 AM)
 * @author: 
 */
public class dbrINVENTARIO extends dbbeans.inventario.dbINVENTARIO {
	private java.lang.String vchnumparte;
	private java.lang.String vchrazonsocial;
	private java.lang.String vchdescripcion;
	private int imultiploempaque;
    private int iminempaque;
	private float decpreciob100;
/**
 * Insert the method's description here.
 * Creation date: (12/20/2002 1:13:32 PM)
 * @return float
 */
public float getDecpreciob100() {
	return decpreciob100;
}
/**
 * Insert the method's description here.
 * Creation date: (12/16/2002 12:07:26 PM)
 * @return int
 */
public int getImultiploempaque() {
	return imultiploempaque;
}
/**
 * Insert the method's description here.
 * Creation date: (12/16/2002 11:49:26 AM)
 * @return java.lang.String
 */
public java.lang.String getVchdescripcion() {
	return vchdescripcion;
}
/**
 * Insert the method's description here.
 * Creation date: (11/25/2002 11:45:07 AM)
 * @return java.lang.String
 */
public java.lang.String getVchnumparte() {
	return vchnumparte;
}
/**
 * Insert the method's description here.
 * Creation date: (11/25/2002 12:01:21 PM)
 * @return java.lang.String
 */
public java.lang.String getVchrazonsocial() {
	return vchrazonsocial;
}
/**
 * Insert the method's description here.
 * Creation date: (12/20/2002 1:13:32 PM)
 * @param newDecpreciob100 float
 */
public void setDecpreciob100(float newDecpreciob100) {
	decpreciob100 = newDecpreciob100;
}
/**
 * Insert the method's description here.
 * Creation date: (12/16/2002 12:07:26 PM)
 * @param newImultiploempaque int
 */
public void setImultiploempaque(int newImultiploempaque) {
	imultiploempaque = newImultiploempaque;
}
/**
 * Insert the method's description here.
 * Creation date: (12/16/2002 11:49:26 AM)
 * @param newVchdescripcion java.lang.String
 */
public void setVchdescripcion(java.lang.String newVchdescripcion) {
	vchdescripcion = newVchdescripcion;
}
/**
 * Insert the method's description here.
 * Creation date: (11/25/2002 11:45:07 AM)
 * @param newVchnumparte java.lang.String
 */
public void setVchnumparte(java.lang.String newVchnumparte) {
	vchnumparte = newVchnumparte;
}
/**
 * Insert the method's description here.
 * Creation date: (11/25/2002 12:01:21 PM)
 * @param newVchrazonsocial java.lang.String
 */
public void setVchrazonsocial(java.lang.String newVchrazonsocial) {
	vchrazonsocial = newVchrazonsocial;
}
public int getIminempaque()
    {
        return iminempaque;
    }

    public void setIminempaque(int i)
    {
        iminempaque = i;
    }
}
