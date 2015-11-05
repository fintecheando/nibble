package pva.beans9;

/**
 * Insert the type's description here.
 * Creation date: (12/23/2002 5:19:59 PM)
 * @author: 
 */

public class Bean93p {
	private org.nibble.dao.factura.FacturaDAO factura;
	private dbrbeans.clientepaisestadomunicipio.dbrCLIENTE cliente;
	private java.lang.String fecha;
	private int IDCLIENTESINIVA;
	private int referencia;
	private java.util.Vector vPartidas;
	private int iidusuario;
	private float iva;
/**
 * Bean93p constructor comment.
 */
public Bean93p() {
	super();
}
/**
 * Insert the method's description here.
 * Creation date: (12/23/2002 5:38:43 PM)
 * @return dbrbeans.clientepaisestadomunicipio.dbrCLIENTE
 */
public dbrbeans.clientepaisestadomunicipio.dbrCLIENTE getCliente() {
	return cliente;
}
public String getDatosGenerales() {
	StringBuffer datos= new StringBuffer();
	if (cliente.getIidcatcliente()==0)
		return "";
    datos.append(cliente.getVchnombre()==null?"":cliente.getVchnombre());
    datos.append(", ");
    datos.append(cliente.getVchrazonsocial()==null?"":cliente.getVchrazonsocial());
    datos.append(",\n ");
    datos.append(cliente.getVchrfc()==null?"":cliente.getVchrfc());
    datos.append(", ");
    datos.append(cliente.getVchcalle()==null?"":cliente.getVchcalle());
    datos.append(", ");
    datos.append(cliente.getVchcolonia()==null?"":cliente.getVchcolonia());
    datos.append(",\n ");
    datos.append(cliente.getVchpaisdes()==null?"":cliente.getVchpaisdes());
    datos.append(", ");
    datos.append(cliente.getVchestadodes()==null?"":cliente.getVchestadodes());
    datos.append(", ");
    datos.append(cliente.getVchmunicipiodes()==null?"":cliente.getVchmunicipiodes());
    return datos.toString();
}
/**
 * Insert the method's description here.
 * Creation date: (12/23/2002 5:45:38 PM)
 * @return 
 */
public float getDecdescglobal() {
	return factura.getDecdescglobal();
}
/**
 * Insert the method's description here.
 * Creation date: (12/23/2002 5:45:38 PM)
 * @return 
 */
public float getDecimporte(int index) {
	Bean93Print a = (Bean93Print) vPartidas.elementAt(index);
	return a.getDecimporte();
}
/**
 * Insert the method's description here.
 * Creation date: (12/23/2002 5:45:38 PM)
 * @return 
 */
public float getDecprecio(int index) {
	Bean93Print a = (Bean93Print) vPartidas.elementAt(index);
	return a.getDecprecio();
}
/**
 * Insert the method's description here.
 * Creation date: (12/23/2002 5:33:03 PM)
 * @return dbbeans.factura.dbFACTURA
 */
public org.nibble.dao.factura.FacturaDAO getFactura() {
	return factura;
}
/**
 * Insert the method's description here.
 * Creation date: (12/23/2002 5:39:09 PM)
 * @return java.lang.String
 */
public java.lang.String getFecha() {
	return fecha;
}
/**
 * Insert the method's description here.
 * Creation date: (12/23/2002 5:45:38 PM)
 * @return 
 */
public int getIcantidad(int index) {
	Bean93Print a = (Bean93Print) vPartidas.elementAt(index);
	return a.getIcantidad();
}
/**
 * Insert the method's description here.
 * Creation date: (12/23/2002 5:40:04 PM)
 * @return int
 */
public int getIDCLIENTESINIVA() {
	return IDCLIENTESINIVA;
}
/**
 * Insert the method's description here.
 * Creation date: (12/23/2002 5:33:03 PM)
 * @return dbbeans.factura.dbFACTURA
 */
public int getIidfactura() {
	return factura.getIidfactura();
}
/**
 * Insert the method's description here.
 * Creation date: (12/23/2002 5:38:43 PM)
 * @return dbrbeans.clientepaisestadomunicipio.dbrCLIENTE
 */
public int getIidnocliente() {
	return cliente.getIidnocliente();
}
/**
 * Insert the method's description here.
 * Creation date: (12/23/2002 5:47:06 PM)
 * @return int
 */
public int getIidusuario() {
	return iidusuario;
}
/**
 * Insert the method's description here.
 * Creation date: (12/23/2002 6:07:41 PM)
 * @return float
 */
public float getIva() {
	return iva;
}
/**
 * Insert the method's description here.
 * Creation date: (12/23/2002 5:45:21 PM)
 * @return int
 */
public int getReferencia() {
	return referencia;
}
/**
 * Insert the method's description here.
 * Creation date: (12/23/2002 5:45:38 PM)
 * @return java.util.Vector
 */
public int getSize() {
	return vPartidas.size();
}
/**
 * Insert the method's description here.
 * Creation date: (12/23/2002 5:45:38 PM)
 * @return 
 */
public String getVchnivel(int index) {
	Bean93Print a = (Bean93Print) vPartidas.elementAt(index);
	return a.getVchnivel();
}
/**
 * Insert the method's description here.
 * Creation date: (12/23/2002 5:45:38 PM)
 * @return 
 */
public String getVchnumparte(int index) {
	Bean93Print a = (Bean93Print) vPartidas.elementAt(index);
	return a.getVchnumparte();
}
/**
 * Insert the method's description here.
 * Creation date: (12/23/2002 5:45:38 PM)
 * @return 
 */
public String getVchnumparteCambio(int index) {
	Bean93Print a = (Bean93Print) vPartidas.elementAt(index);
	return a.getVchnumparteCambio();
}
/**
 * Insert the method's description here.
 * Creation date: (12/23/2002 5:38:43 PM)
 * @return dbrbeans.clientepaisestadomunicipio.dbrCLIENTE
 */
public String getVchviades() {
	return cliente.getVchviades();
}
/**
 * Insert the method's description here.
 * Creation date: (12/23/2002 5:45:38 PM)
 * @return java.util.Vector
 */
public java.util.Vector getVPartidas() {
	return vPartidas;
}
/**
 * Insert the method's description here.
 * Creation date: (12/23/2002 5:38:43 PM)
 * @param newCliente dbrbeans.clientepaisestadomunicipio.dbrCLIENTE
 */
public void setCliente(dbrbeans.clientepaisestadomunicipio.dbrCLIENTE newCliente) {
	cliente = newCliente;
}
/**
 * Insert the method's description here.
 * Creation date: (12/23/2002 5:33:03 PM)
 * @param newFactura dbbeans.factura.dbFACTURA
 */
public void setFactura(org.nibble.dao.factura.FacturaDAO newFactura) {
	factura = newFactura;
}
/**
 * Insert the method's description here.
 * Creation date: (12/23/2002 5:39:09 PM)
 * @param newFecha java.lang.String
 */
public void setFecha(java.lang.String newFecha) {
	fecha = newFecha;
}
/**
 * Insert the method's description here.
 * Creation date: (12/23/2002 5:40:04 PM)
 * @param newIDCLIENTESINIVA int
 */
public void setIDCLIENTESINIVA(int newIDCLIENTESINIVA) {
	IDCLIENTESINIVA = newIDCLIENTESINIVA;
}
/**
 * Insert the method's description here.
 * Creation date: (12/23/2002 5:47:06 PM)
 * @param newIidusuario int
 */
public void setIidusuario(int newIidusuario) {
	iidusuario = newIidusuario;
}
/**
 * Insert the method's description here.
 * Creation date: (12/23/2002 6:07:41 PM)
 * @param newIva float
 */
public void setIva(float newIva) {
	iva = newIva;
}
/**
 * Insert the method's description here.
 * Creation date: (12/23/2002 5:45:21 PM)
 * @param newReferencia int
 */
public void setReferencia(int newReferencia) {
	referencia = newReferencia;
}
/**
 * Insert the method's description here.
 * Creation date: (12/23/2002 5:45:38 PM)
 * @param newVPartidas java.util.Vector
 */
public void setVPartidas(java.util.Vector newVPartidas) {
	vPartidas = newVPartidas;
}
}
