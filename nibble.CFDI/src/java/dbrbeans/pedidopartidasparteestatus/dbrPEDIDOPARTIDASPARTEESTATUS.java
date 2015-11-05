package dbrbeans.pedidopartidasparteestatus;

import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class dbrPEDIDOPARTIDASPARTEESTATUS {
	private Connection conn;
	private ResultSet rSet;
	private PreparedStatement stmt;

	private java.lang.String vchnumparte;
	private int icantpedida;
	private int iidpedido;
	private long liidparte;
	private java.lang.String vchdescestatus;
	private int icantrecibida;
	private java.lang.String vchfactura;
	private java.lang.String vchremisionproveed;
	private java.lang.String dtfechultrecepcion;
	private java.lang.String vchcomentario;
	private int tiestatus;
    private float deccosto;
/**
 * dbrPEDIDOPARTIDASPARTE constructor comment.
 */
public dbrPEDIDOPARTIDASPARTEESTATUS() {
	super();
}
/**
 * Insert the method's description here.
 * Creation date: (10/29/2002 12:24:01 PM)
 * @return java.lang.String
 */
public java.lang.String getDtfechultrecepcion() {
	return dtfechultrecepcion;
}
/**
 * Insert the method's description here.
 * Creation date: (10/28/2002 2:52:37 PM)
 * @return int
 */
public int getIcantpedida() {
	return icantpedida;
}
/**
 * Insert the method's description here.
 * Creation date: (10/29/2002 12:22:31 PM)
 * @return int
 */
public int getIcantrecibida() {
	return icantrecibida;
}
/**
 * Insert the method's description here.
 * Creation date: (10/28/2002 3:38:46 PM)
 * @return int
 */
public int getIidpedido() {
	return iidpedido;
}
/**
 * Insert the method's description here.
 * Creation date: (10/28/2002 3:42:10 PM)
 * @return long
 */
public long getLiidparte() {
	return liidparte;
}
/**
 * Insert the method's description here.
 * Creation date: (10/29/2002 5:12:22 PM)
 * @return int
 */
public int getTiestatus() {
	return tiestatus;
}
/**
 * Insert the method's description here.
 * Creation date: (10/29/2002 12:24:26 PM)
 * @return java.lang.String
 */
public java.lang.String getVchcomentario() {
	return vchcomentario;
}
/**
 * Insert the method's description here.
 * Creation date: (10/29/2002 12:21:13 PM)
 * @return java.lang.String
 */
public java.lang.String getVchdescestatus() {
	return vchdescestatus;
}
/**
 * Insert the method's description here.
 * Creation date: (10/29/2002 12:23:11 PM)
 * @return java.lang.String
 */
public java.lang.String getVchfactura() {
	return vchfactura;
}
/**
 * Insert the method's description here.
 * Creation date: (10/28/2002 2:50:53 PM)
 * @return java.lang.String
 */
public java.lang.String getVchnumparte() {
	return vchnumparte;
}
/**
 * Insert the method's description here.
 * Creation date: (10/29/2002 12:23:31 PM)
 * @return java.lang.String
 */
public java.lang.String getVchremisionproveed() {
	return vchremisionproveed;
}
/**
 * Insert the method's description here.
 * Creation date: (10/29/2002 12:24:01 PM)
 * @param newDtfechultrecepcion java.lang.String
 */
public void setDtfechultrecepcion(java.lang.String newDtfechultrecepcion) {
	dtfechultrecepcion = newDtfechultrecepcion;
}
/**
 * Insert the method's description here.
 * Creation date: (10/28/2002 2:52:37 PM)
 * @param newIcantpedida int
 */
public void setIcantpedida(int newIcantpedida) {
	icantpedida = newIcantpedida;
}
/**
 * Insert the method's description here.
 * Creation date: (10/29/2002 12:22:31 PM)
 * @param newIcantrecibida int
 */
public void setIcantrecibida(int newIcantrecibida) {
	icantrecibida = newIcantrecibida;
}
/**
 * Insert the method's description here.
 * Creation date: (10/28/2002 3:38:46 PM)
 * @param newIidpedido int
 */
public void setIidpedido(int newIidpedido) {
	iidpedido = newIidpedido;
}
/**
 * Insert the method's description here.
 * Creation date: (10/28/2002 3:42:10 PM)
 * @param newLiidparte long
 */
public void setLiidparte(long newLiidparte) {
	liidparte = newLiidparte;
}
/**
 * Insert the method's description here.
 * Creation date: (10/29/2002 5:12:22 PM)
 * @param newTiestatus int
 */
public void setTiestatus(int newTiestatus) {
	tiestatus = newTiestatus;
}
/**
 * Insert the method's description here.
 * Creation date: (10/29/2002 12:24:26 PM)
 * @param newVchcomentario java.lang.String
 */
public void setVchcomentario(java.lang.String newVchcomentario) {
	vchcomentario = newVchcomentario;
}
/**
 * Insert the method's description here.
 * Creation date: (10/29/2002 12:21:13 PM)
 * @param newVchdescestatus java.lang.String
 */
public void setVchdescestatus(java.lang.String newVchdescestatus) {
	vchdescestatus = newVchdescestatus;
}
/**
 * Insert the method's description here.
 * Creation date: (10/29/2002 12:23:11 PM)
 * @param newVchfactura java.lang.String
 */
public void setVchfactura(java.lang.String newVchfactura) {
	vchfactura = newVchfactura;
}
/**
 * Insert the method's description here.
 * Creation date: (10/28/2002 2:50:53 PM)
 * @param newVchnumparte java.lang.String
 */
public void setVchnumparte(java.lang.String newVchnumparte) {
	vchnumparte = newVchnumparte;
}
/**
 * Insert the method's description here.
 * Creation date: (10/29/2002 12:23:31 PM)
 * @param newVchremisionproveed java.lang.String
 */
public void setVchremisionproveed(java.lang.String newVchremisionproveed) {
	vchremisionproveed = newVchremisionproveed;
}
public float getDeccosto()
    {
        return deccosto;
    }

    public void setDeccosto(float f)
    {
        deccosto = f;
    }
}
