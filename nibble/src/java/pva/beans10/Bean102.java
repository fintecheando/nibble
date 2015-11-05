package pva.beans10;

/**
 * Insert the type's description here.
 * Creation date: (19/12/2002 05:49:54 p.m.)
 * @author: 
 * 
**/

import org.nibble.vo.factura.FacturaClienteVO;
import org.nibble.dao.factura.*;
import dbbeans.notadecredito.*;
import dbbeans.notadecargo.*;
 
public class Bean102 {
	private java.util.Vector vctContado;
	private java.util.Vector vctCredito;
	private java.util.Vector vctDevolucion;
	private java.util.Vector vctCancelacion;
	private java.util.Vector vctBonificacion;
	private java.util.Vector vctCargo;
	private java.util.Vector vctIngreso;
	private boolean blnDetalle;
	private java.lang.String strFecha;
	
/**
 * Insert the method's description here.
 * Creation date: (19/12/2002 07:43:23 p.m.)
 * @return java.util.Vector
 */
public int getBonificacionsize() {
	return vctBonificacion.size();
}
/**
 * Insert the method's description here.
 * Creation date: (19/12/2002 07:43:03 p.m.)
 * @return java.util.Vector
 */
public int getCancelacionsize() {
	return vctCancelacion.size();
}
/**
 * Insert the method's description here.
 * Creation date: (19/12/2002 07:44:40 p.m.)
 * @return java.util.Vector
 */
public int getCargosize() {
	return vctCargo.size();
}
/**
 * Insert the method's description here.
 * Creation date: (19/12/2002 07:41:56 p.m.)
 * @return java.util.Vector
 */
public int getContadosize() {
	return vctContado.size();
}
/**
 * Insert the method's description here.
 * Creation date: (19/12/2002 07:42:17 p.m.)
 * @return java.util.Vector
 */
public int getCreditosize() {
	return vctCredito.size();
}
/**
 * Insert the method's description here.
 * Creation date: (19/12/2002 07:43:23 p.m.)
 * @return java.util.Vector
 */
//public String getDecdesc(int index, int partida) {
public float getDecdesc(int index, int partida) {

	float result=0;

	switch(partida){
		case 1: // Contado
				FacturaClienteVO a = (FacturaClienteVO)vctContado.elementAt(index);
				result = a.getDecmontodesc();
				break;
		case 2: // Credito
				FacturaClienteVO b = (FacturaClienteVO)vctCredito.elementAt(index);
				result = b.getDecmontodesc();
				
				break;		
		case 3: // Devoluciones
				dbNOTADECREDITOr c = (dbNOTADECREDITOr)vctDevolucion.elementAt(index);
				result = c.getDecmontodesc();
				break;		
		case 4: // Cancelaciones
				dbNOTADECREDITOr d = (dbNOTADECREDITOr)vctCancelacion.elementAt(index);
				result = d.getDecmontodesc();	
				break;		
		case 5: // Bonificaciones
				dbNOTADECREDITOr e = (dbNOTADECREDITOr)vctBonificacion.elementAt(index);
				result = e.getDecmontodesc();		
				break;		
		case 6: // Cargos
					// No hay descuentos en nota de cargo
				break;		
		case 7: // Ingresos
				break;		
	}
	//return util.Formato.formateoNumerico(result,"##,###,##0.00");
	return result;		
}
/**
 * Insert the method's description here.
 * Creation date: (19/12/2002 07:43:23 p.m.)
 * @return java.util.Vector
 */
//public String getDecimporte(int index, int partida) {
public float getDecimporte(int index, int partida) {

	float result=0;

	switch(partida){
		case 1: // Contado
				FacturaClienteVO a = (FacturaClienteVO)vctContado.elementAt(index);
				result = a.getDecsubtotal();
				break;
		case 2: // Credito
				FacturaClienteVO b = (FacturaClienteVO)vctCredito.elementAt(index);
				result = b.getDecsubtotal();	
				break;		
		case 3: // Devoluciones
				dbNOTADECREDITOr c = (dbNOTADECREDITOr)vctDevolucion.elementAt(index);
				result = c.getDecimporte();
				break;		
		case 4: // Cancelaciones
				dbNOTADECREDITOr d = (dbNOTADECREDITOr)vctCancelacion.elementAt(index);
				result = d.getDecimporte();	
				break;		
		case 5: // Bonificaciones
				dbNOTADECREDITOr e = (dbNOTADECREDITOr)vctBonificacion.elementAt(index);
				result = e.getDecimporte();		
				break;		
		case 6: // Cargos
				dbNOTADECARGOr f = (dbNOTADECARGOr)vctCargo.elementAt(index);
				result = f.getDecimporte();
				break;		
		case 7: // Ingresos
				break;		
	}
	//return util.Formato.formateoNumerico(result,"##,###,##0.00");
	return result;		
}
/**
 * Insert the method's description here.
 * Creation date: (19/12/2002 07:43:23 p.m.)
 * @return java.util.Vector
 */
//public String getDeciva(int index, int partida) {
public float getDeciva(int index, int partida) {

	float result=0;

	switch(partida){
		case 1: // Contado
				FacturaClienteVO a = (FacturaClienteVO)vctContado.elementAt(index);
				result = a.getDecivatotal();
				break;
		case 2: // Credito
				FacturaClienteVO b = (FacturaClienteVO)vctCredito.elementAt(index);
				result = b.getDecivatotal();	
				break;		
		case 3: // Devoluciones
				dbNOTADECREDITOr c = (dbNOTADECREDITOr)vctDevolucion.elementAt(index);
				result = c.getDecivaimporte();
				break;		
		case 4: // Cancelaciones
				dbNOTADECREDITOr d = (dbNOTADECREDITOr)vctCancelacion.elementAt(index);
				result = d.getDecivaimporte();	
				break;		
		case 5: // Bonificaciones
				dbNOTADECREDITOr e = (dbNOTADECREDITOr)vctBonificacion.elementAt(index);
				result = e.getDecivaimporte();		
				break;		
		case 6: // Cargos
				dbNOTADECARGOr f = (dbNOTADECARGOr)vctCargo.elementAt(index);
				result = f.getDecivaimporte();
				break;		
		case 7: // Ingresos
				break;		
	}
	//return util.Formato.formateoNumerico(result,"##,###,##0.00");
	return result;		
}
/**
 * Insert the method's description here.
 * Creation date: (19/12/2002 07:43:23 p.m.)
 * @return java.util.Vector
 */
//public String getDectotal(int index, int partida) {
public float getDectotal(int index, int partida) {

	float result=0;

	switch(partida){
		case 1: // Contado
				FacturaClienteVO a = (FacturaClienteVO)vctContado.elementAt(index);
				result = (a.getDecsubtotal()-a.getDecmontodesc())+a.getDecivatotal();
				break;
		case 2: // Credito
				FacturaClienteVO b = (FacturaClienteVO)vctCredito.elementAt(index);
				result = (b.getDecsubtotal()-b.getDecmontodesc())+b.getDecivatotal();	
				break;		
		case 3: // Devoluciones
				dbNOTADECREDITOr c = (dbNOTADECREDITOr)vctDevolucion.elementAt(index);
				result = (c.getDecimporte()-c.getDecmontodesc())+c.getDecivaimporte();
				break;		
		case 4: // Cancelaciones
				dbNOTADECREDITOr d = (dbNOTADECREDITOr)vctCancelacion.elementAt(index);
				result = (d.getDecimporte()-d.getDecmontodesc())+d.getDecivaimporte();				
				break;		
		case 5: // Bonificaciones
				dbNOTADECREDITOr e = (dbNOTADECREDITOr)vctBonificacion.elementAt(index);
				result = (e.getDecimporte()-e.getDecmontodesc())+e.getDecivaimporte();
				break;		
		case 6: // Cargos
				dbNOTADECARGOr f = (dbNOTADECARGOr)vctCargo.elementAt(index);
				result = f.getDecimporte()+f.getDecivaimporte();				
				break;		
		case 7: // Ingresos
				break;		
	}
	//return util.Formato.formateoNumerico(result,"###,###,##0.00");
	return result;		
}
/**
 * Insert the method's description here.
 * Creation date: (19/12/2002 07:42:43 p.m.)
 * @return java.util.Vector
 */
public int getDevolucionsize() {
	return vctDevolucion.size();
}
/**
 * Insert the method's description here.
 * Creation date: (06/12/2002 05:29:41 p.m.)
 * @return java.lang.String
 */
public java.lang.String getFecha() {
	return strFecha;
}
/**
 * Insert the method's description here.
 * Creation date: (19/12/2002 07:43:23 p.m.)
 * @return java.util.Vector
 */
public int getIidcliente(int index, int partida) {

	int result=0;

	switch(partida){
		case 1: // Contado
				FacturaClienteVO a = (FacturaClienteVO)vctContado.elementAt(index);
				result = a.getIidnocliente();
				break;
		case 2: // Credito
				FacturaClienteVO b = (FacturaClienteVO)vctCredito.elementAt(index);
				result = b.getIidnocliente();	
				break;		
		case 3: // Devoluciones
				dbNOTADECREDITOr c = (dbNOTADECREDITOr)vctDevolucion.elementAt(index);
				result = c.getIidnocliente();
				break;		
		case 4: // Cancelaciones
				dbNOTADECREDITOr d = (dbNOTADECREDITOr)vctCancelacion.elementAt(index);
				result = d.getIidnocliente();	
				break;		
		case 5: // Bonificaciones
				dbNOTADECREDITOr e = (dbNOTADECREDITOr)vctBonificacion.elementAt(index);
				result = e.getIidnocliente();		
				break;		
		case 6: // Cargos
				dbNOTADECARGOr f = (dbNOTADECARGOr)vctCargo.elementAt(index);
				result = f.getIidnocliente();
				break;		
		case 7: // Ingresos
				break;		
	}
	return result;		
}
/**
 * Insert the method's description here.
 * Creation date: (19/12/2002 07:43:23 p.m.)
 * @return java.util.Vector
 */
public int getIiddocto(int index, int partida) {

	int result=0;

	switch(partida){
		case 1: // Contado
				FacturaClienteVO a = (FacturaClienteVO)vctContado.elementAt(index);
				result = a.getIidfactura();
				break;
		case 2: // Credito
				FacturaClienteVO b = (FacturaClienteVO)vctCredito.elementAt(index);
				result = b.getIidfactura();	
				break;		
		case 3: // Devoluciones
				dbNOTADECREDITOr c = (dbNOTADECREDITOr)vctDevolucion.elementAt(index);
				result = c.getIidnotacredito();
				break;		
		case 4: // Cancelaciones
				dbNOTADECREDITOr d = (dbNOTADECREDITOr)vctCancelacion.elementAt(index);
				result = d.getIidnotacredito();	
				break;		
		case 5: // Bonificaciones
				dbNOTADECREDITOr e = (dbNOTADECREDITOr)vctBonificacion.elementAt(index);
				result = e.getIidnotacredito();		
				break;		
		case 6: // Cargos
				dbNOTADECARGOr f = (dbNOTADECARGOr)vctCargo.elementAt(index);
				result = f.getIidnotadecargo();
				break;		
		case 7: // Ingresos
				break;		
	}
	return result;		
}
/**
 * Insert the method's description here.
 * Creation date: (19/12/2002 07:47:42 p.m.)
 * @return java.util.Vector
 */
public int getIngresosize() {
	return vctIngreso.size();
}
/**
 * Insert the method's description here.
 * Creation date: (19/12/2002 07:43:23 p.m.)
 * @return java.util.Vector
 */
public String getNombrecliente(int index, int partida) {

	String result = "";

	switch(partida){
		case 1: // Contado
				FacturaClienteVO a = (FacturaClienteVO)vctContado.elementAt(index);
				result = a.getNombreCliente();
				break;
		case 2: // Credito
				FacturaClienteVO b = (FacturaClienteVO)vctCredito.elementAt(index);
				result = b.getNombreCliente();	
				break;		
		case 3: // Devoluciones
				dbNOTADECREDITOr c = (dbNOTADECREDITOr)vctDevolucion.elementAt(index);
				result = c.getNombreCliente();
				break;		
		case 4: // Cancelaciones
				dbNOTADECREDITOr d = (dbNOTADECREDITOr)vctCancelacion.elementAt(index);
				result = d.getNombreCliente();	
				break;		
		case 5: // Bonificaciones
				dbNOTADECREDITOr e = (dbNOTADECREDITOr)vctBonificacion.elementAt(index);
				result = e.getNombreCliente();		
				break;		
		case 6: // Cargos
				dbNOTADECARGOr f = (dbNOTADECARGOr)vctCargo.elementAt(index);
				result = f.getNombreCliente();
				break;		
		case 7: // Ingresos
				break;		
	}
	return result;
}
/**
 * Insert the method's description here.
 * Creation date: (12/11/2002 07:14:33 p.m.)
 * @return boolean
 */
public boolean isDetalle() {
	return blnDetalle;
}
/**
 * Insert the method's description here.
 * Creation date: (19/12/2002 07:43:23 p.m.)
 * @param newBonificacion java.util.Vector
 */
public void setBonificacion(java.util.Vector newBonificacion) {
	vctBonificacion = newBonificacion;
}
/**
 * Insert the method's description here.
 * Creation date: (19/12/2002 07:43:03 p.m.)
 * @param newCancelacion java.util.Vector
 */
public void setCancelacion(java.util.Vector newCancelacion) {
	vctCancelacion = newCancelacion;
}
/**
 * Insert the method's description here.
 * Creation date: (19/12/2002 07:44:40 p.m.)
 * @param newCargo java.util.Vector
 */
public void setCargo(java.util.Vector newCargo) {
	vctCargo = newCargo;
}
/**
 * Insert the method's description here.
 * Creation date: (19/12/2002 07:41:56 p.m.)
 * @param newContado java.util.Vector
 */
public void setContado(java.util.Vector newContado) {
	vctContado = newContado;
}
/**
 * Insert the method's description here.
 * Creation date: (19/12/2002 07:42:17 p.m.)
 * @param newCredito java.util.Vector
 */
public void setCredito(java.util.Vector newCredito) {
	vctCredito = newCredito;
}
/**
 * Insert the method's description here.
 * Creation date: (12/11/2002 07:14:33 p.m.)
 * @param newDetalle boolean
 */
public void setDetalle(boolean newDetalle) {
	blnDetalle = newDetalle;
}
/**
 * Insert the method's description here.
 * Creation date: (19/12/2002 07:42:43 p.m.)
 * @param newDevolucion java.util.Vector
 */
public void setDevolucion(java.util.Vector newDevolucion) {
	vctDevolucion = newDevolucion;
}
/**
 * Insert the method's description here.
 * Creation date: (06/12/2002 05:29:41 p.m.)
 * @param newFecha java.lang.String
 */
public void setFecha(java.lang.String newFecha) {
	strFecha = newFecha;
}
/**
 * Insert the method's description here.
 * Creation date: (19/12/2002 07:47:42 p.m.)
 * @param newIngreso java.util.Vector
 */
public void setIngreso(java.util.Vector newIngreso) {
	vctIngreso = newIngreso;
}
}
