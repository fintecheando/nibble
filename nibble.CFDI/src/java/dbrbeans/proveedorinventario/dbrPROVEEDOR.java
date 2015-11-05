package dbrbeans.proveedorinventario;

/**
 * Insert the type's description here.
 * Creation date: (11/18/2002 5:22:31 PM)
 * @author: 
 */
public class dbrPROVEEDOR extends dbbeans.proveedor.dbPROVEEDOR {
	private int iexistencia;
	private boolean bitprovefavorito;
	private float decfactorcostovent;
/**
 * Insert the method's description here.
 * Creation date: (12/11/2002 10:59:02 AM)
 * @return float
 */
public float getDecfactorcostovent() {
	return new Float(1).floatValue();//decfactorcostovent;
}
/**
 * Insert the method's description here.
 * Creation date: (11/18/2002 5:23:01 PM)
 * @return int
 */
public int getIexistencia() {
	return iexistencia;
}
/**
 * Insert the method's description here.
 * Creation date: (11/18/2002 7:06:27 PM)
 * @return boolean
 */
public boolean isBitprovefavorito() {
	return bitprovefavorito;
}
/**
 * Insert the method's description here.
 * Creation date: (11/18/2002 7:06:27 PM)
 * @param newBitprovefavorito boolean
 */
public void setBitprovefavorito(boolean newBitprovefavorito) {
	bitprovefavorito = newBitprovefavorito;
}
/**
 * Insert the method's description here.
 * Creation date: (12/11/2002 10:59:02 AM)
 * @param newDecfactorcostovent float
 */
public void setDecfactorcostovent(float newDecfactorcostovent) {
	decfactorcostovent = newDecfactorcostovent;
}
/**
 * Insert the method's description here.
 * Creation date: (11/18/2002 5:23:01 PM)
 * @param newIexistencia int
 */
public void setIexistencia(int newIexistencia) {
	iexistencia = newIexistencia;
}
}
