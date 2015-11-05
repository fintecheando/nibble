package dbrbeans.partidasolicitada;

/**
 * Insert the type's description here.
 * Creation date: (12/13/2002 2:14:39 PM)
 * @author: 
 */
public class dbrPARTIDASOLICITADA extends dbbeans.partidasolicitada.dbPARTIDASOLICITADA {
	private java.lang.String vchnumparte;
	private int iidcategoria;
	private int iidgrupo;
	private int iidlinea;
	private int iidmarca;
    private double costoUnitario;
/**
 * Insert the method's description here.
 * Creation date: (12/13/2002 4:43:56 PM)
 * @return int
 */
public int getIidcategoria() {
	return iidcategoria;
}
/**
 * Insert the method's description here.
 * Creation date: (12/13/2002 4:44:07 PM)
 * @return int
 */
public int getIidgrupo() {
	return iidgrupo;
}
/**
 * Insert the method's description here.
 * Creation date: (12/13/2002 4:44:21 PM)
 * @return int
 */
public int getIidlinea() {
	return iidlinea;
}
/**
 * Insert the method's description here.
 * Creation date: (12/13/2002 4:44:33 PM)
 * @return int
 */
public int getIidmarca() {
	return iidmarca;
}
/**
 * Insert the method's description here.
 * Creation date: (12/13/2002 3:21:55 PM)
 * @return java.lang.String
 */
public java.lang.String getVchnumparte() {
	return vchnumparte;
}
/**
 * Insert the method's description here.
 * Creation date: (12/13/2002 4:43:56 PM)
 * @param newIidcategoria int
 */
public void setIidcategoria(int newIidcategoria) {
	iidcategoria = newIidcategoria;
}
/**
 * Insert the method's description here.
 * Creation date: (12/13/2002 4:44:07 PM)
 * @param newIidgrupo int
 */
public void setIidgrupo(int newIidgrupo) {
	iidgrupo = newIidgrupo;
}
/**
 * Insert the method's description here.
 * Creation date: (12/13/2002 4:44:21 PM)
 * @param newIidlinea int
 */
public void setIidlinea(int newIidlinea) {
	iidlinea = newIidlinea;
}
/**
 * Insert the method's description here.
 * Creation date: (12/13/2002 4:44:33 PM)
 * @param newIidmarca int
 */
public void setIidmarca(int newIidmarca) {
	iidmarca = newIidmarca;
}
/**
 * Insert the method's description here.
 * Creation date: (12/13/2002 3:21:55 PM)
 * @param newVchnumparte java.lang.String
 */
public void setVchnumparte(java.lang.String newVchnumparte) {
	vchnumparte = newVchnumparte;
}
    public double getCostoUnitario()
    {
        return costoUnitario;
    }

    public void setCostoUnitario(double d)
    {
        costoUnitario = d;
    }

}
