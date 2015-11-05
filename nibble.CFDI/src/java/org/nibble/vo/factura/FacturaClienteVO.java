package org.nibble.vo.factura;

import org.nibble.dao.factura.FacturaDAO;

/**
 * Insert the type's description here.
 * @author: 
 */
public class FacturaClienteVO extends FacturaDAO {
	private java.lang.String strNombreCliente;
/**
 * Insert the method's description here. 
 * @return java.lang.String
 */
public java.lang.String getNombreCliente() {
	return strNombreCliente;
}
/**
 * Insert the method's description here. 
 * @param newNombreCliente java.lang.String
 */
public void setNombreCliente(java.lang.String newNombreCliente) {
	strNombreCliente = newNombreCliente;
}
}
