package pva.beans11;

/**
 * Insert the type's description here.
 * Creation date: (12/11/2002 02:03:56 p.m.)
 * @author: 
 */

import dbbeans.ctaxcobrar.*;
 
public class Bean113 {
	private int intIdcliente;
	private java.util.Vector vCxc;
	private java.util.Vector vSaldos;
	
	private boolean blnDetalle;
	private boolean blnVencido;
	private float decLimite;
	private float decSaldo;
	private float decDisponible;
	private float sxvencer;
	private float sx1_7;
	private float sx8_29;
	private float sx30_45;
	private float sx46_60;
	private float sx61;
	private dbbeans.cliente.dbCLIENTE beanCliente;
	private float decIngreso;
/**
 * Insert the method's description here.
 * Creation date: (14/01/2003 07:51:39 p.m.)
 * @return dbbeans.cliente.dbCLIENTE
 */



public dbbeans.cliente.dbCLIENTE getCliente() {
	return beanCliente;
}
/**
 * Insert the method's description here.
 * Creation date: (11/1/2002 12:03:06 PM)
 * @return int
 */
public String getDatosGenerales() {
	StringBuffer datos= new StringBuffer();
	datos.append("Nombre : ");
    datos.append(beanCliente.getVchnombre()==null?"":beanCliente.getVchnombre());
    datos.append("\nRaz�n Social : ");
    datos.append(beanCliente.getVchrazonsocial()==null?"":beanCliente.getVchrazonsocial());
    datos.append("\nR.F.C. : ");
    datos.append(beanCliente.getVchrfc()==null?"":beanCliente.getVchrfc());
    datos.append("\nCalle : ");
    datos.append(beanCliente.getVchcalle()==null?"":beanCliente.getVchcalle());
    datos.append("\nColonia : ");
    datos.append(beanCliente.getVchcolonia()==null?"":beanCliente.getVchcolonia());
    return datos.toString();
}
public String getDecdebe(int index){
	dbCTAXCOBRARr a = (dbCTAXCOBRARr) vCxc.elementAt(index);
	return org.nibble.util.Formato.formateoNumerico(a.getDecdebe(),"##,###,##0.00");
}
public String getDechaber(int index){

	dbCTAXCOBRARr a = (dbCTAXCOBRARr) vCxc.elementAt(index);
	return org.nibble.util.Formato.formateoNumerico(a.getDechaber(),"##,###,##0.00");
}
public String getDecImporte(int index){

	float imp = 0;

	dbCTAXCOBRARr a = (dbCTAXCOBRARr) vCxc.elementAt(index);
	if (a.getIidcontradocto()>0)
		imp = a.getDecdebe()-a.getDechaber();
	if (a.getIidcontradocto()==0)
		imp = a.getDecdebe();
  	if (a.getIidcontradocto()== -1)
		imp = a.getDechaber()* -1;
	
	return org.nibble.util.Formato.formateoNumerico(imp,"##,###,##0.00");
}


public String getDecImporteCargo(int index){

	float imp = 0;

	dbCTAXCOBRARr a = (dbCTAXCOBRARr) vCxc.elementAt(index);
	if (a.getIidcontradocto()>0)
		imp = a.getDecdebe()-a.getDechaber();
	if (a.getIidcontradocto()==0)
		imp = a.getDecdebe();
	if (a.getIidcontradocto()== -1)
		imp = a.getDechaber()* -1;
	
	return org.nibble.util.Formato.formateoNumerico(imp,"##,###,##0.00");
}

public String getDecImporteAbono(int index){

	float imp = 0;

	dbCTAXCOBRARr a = (dbCTAXCOBRARr) vCxc.elementAt(index);
	if (a.getIidcontradocto()>0)
		imp = a.getDecdebe()-a.getDechaber();
	if (a.getIidcontradocto()==0)
		imp = a.getDecdebe();
	if (a.getIidcontradocto()== -1)
		imp = a.getDechaber()* -1;
	
	return org.nibble.util.Formato.formateoNumerico(imp,"##,###,##0.00");
}

//Inicio Se agregan dos metodos para poder calcular el Cargo y abono en el Estado de cuenta
  public String getDecImporteCargoEdo(int index){

	float imp = 0; 

  dbCTAXCOBRARr a = (dbCTAXCOBRARr) vCxc.elementAt(index);  
  
  if (a.getIidcontradocto()>0)	  
	  imp = 0;
  if (a.getIidcontradocto()==0)  
	  imp = a.getDecdebe();
  if (a.getIidcontradocto()== -1)
	  imp = a.getDechaber()* -1;
	
  return org.nibble.util.Formato.formateoNumerico(imp,"##,###,##0.00");
  }
  
  public double getDecImporteCargoEdodo(int index){

	  float imp = 0; 

	dbCTAXCOBRARr a = (dbCTAXCOBRARr) vCxc.elementAt(index);  
  
	if (a.getIidcontradocto()>0)	  
		imp = 0;
	if (a.getIidcontradocto()==0)  
		imp = a.getDecdebe();
	if (a.getIidcontradocto()== -1)
		imp = a.getDechaber()* -1;
	
	return imp;		
	}

  public String getDecImporteAbonoEdo(int index){

	  float imp = 0;

	  dbCTAXCOBRARr a = (dbCTAXCOBRARr) vCxc.elementAt(index);
	  if (a.getIidcontradocto()>0){	  
		  
		imp = a.getDechaber();
	  }
	  else {
				   
				   imp= 0;
	  }	  
	
	  return org.nibble.util.Formato.formateoNumerico(imp,"##,###,##0.00");
  }
  public double getDecImporteAbonoEdodo(int index){

	
	float imp = 0;

	dbCTAXCOBRARr a = (dbCTAXCOBRARr) vCxc.elementAt(index);
	if (a.getIidcontradocto()>0){	  
		  
	  imp = a.getDechaber();
	}
	else {
				   
				 imp= 0;
	}	  
	
	return imp;		
	}

//fin

public String getDecsaldo(int index){

	dbCTAXCOBRARr a = (dbCTAXCOBRARr) vCxc.elementAt(index);
	
	return org.nibble.util.Formato.formateoNumerico(a.getDecdebe()-a.getDechaber(),"##,###,##0.00");
}
public String getDecsaldoq(int index){

	dbCTAXCOBRARr a = (dbCTAXCOBRARr) vCxc.elementAt(index);
	return org.nibble.util.Formato.formateoNumerico(a.getDecdebe()-a.getDechaber(),"#######0.00");
}
/**
 * Insert the method's description here.
 * Creation date: (06/12/2002 07:46:11 p.m.)
 * @return float
 */
public String getDisponible() {
	return org.nibble.util.Formato.formateoNumerico(decDisponible,"##,###,##0.00");
}
/**
 * Insert the method's description here.
 * Creation date: (06/12/2002 07:45:50 p.m.)
 * @return float
 */
public float getDSaldo() {
	return sxvencer+sx1_7+sx8_29+sx30_45+sx46_60+sx61;
}
/**
 * Insert the method's description here.
 * Creation date: (09/12/2002 01:53:06 p.m.)
 * @return float
 */
public float getDSx1_7() {
	return sx1_7;
}
/**
 * Insert the method's description here.
 * Creation date: (09/12/2002 01:55:54 p.m.)
 * @return float
 */
public float getDSx30_45() {
	return sx30_45;	
}
/**
 * Insert the method's description here.
 * Creation date: (09/12/2002 01:57:05 p.m.)
 * @return float
 */
public float getDSx46_60() {
	return sx46_60;	
}
/**
 * Insert the method's description here.
 * Creation date: (09/12/2002 02:01:54 p.m.)
 * @return float
 */
public float getDSx61() {
	return sx61;	
}
/**
 * Insert the method's description here.
 * Creation date: (09/12/2002 01:55:25 p.m.)
 * @return float
 */
public float getDSx8_29() {
	return sx8_29;	 
}
/**
 * Insert the method's description here.
 * Creation date: (09/12/2002 01:53:06 p.m.)
 * @return float
 */
public float getDSxvencer() {
	return sxvencer;
}
/**
 * Insert the method's description here.
 * Creation date: (12/11/2002 02:04:47 p.m.)
 * @return int
 */
public int getIdcliente() {
	return intIdcliente;
}
public int getIidcontradocto(int index){
	
	dbCTAXCOBRARr a = (dbCTAXCOBRARr) vCxc.elementAt(index);
	return a.getIidcontradocto();
	
}
public int getIiddoctoorigen(int index){
	
	dbCTAXCOBRARr a = (dbCTAXCOBRARr) vCxc.elementAt(index);
	return a.getIiddoctoorigen();
	
}
/**
 * Insert the method's description here.
 * Creation date: (24/01/2003 07:40:04 p.m.)
 * @return float
 */
public String getIngreso() {
	return org.nibble.util.Formato.formateoNumerico(decIngreso,"##,###,##0.00");
}
/**
 * Insert the method's description here.
 * Creation date: (06/12/2002 07:45:36 p.m.)
 * @return float
 */
public String getLimite() {
	return org.nibble.util.Formato.formateoNumerico(decLimite,"##,###,##0.00");
}
/**
 * Insert the method's description here.
 * Creation date: (09/12/2002 01:53:06 p.m.)
 * @return float
 */
public String getPx1_7() {
	return getDSaldo() > 0 ? org.nibble.util.Formato.formateoNumerico((sx1_7/getDSaldo())*100,"##0.00"): org.nibble.util.Formato.formateoNumerico(0,"##0.00");
}
/**
 * Insert the method's description here.
 * Creation date: (09/12/2002 01:55:54 p.m.)
 * @return float
 */
public String getPx30_45() {
	return getDSaldo() > 0 ? org.nibble.util.Formato.formateoNumerico((sx30_45/getDSaldo())*100,"##0.00"): org.nibble.util.Formato.formateoNumerico(0,"##0.00");
}
/**
 * Insert the method's description here.
 * Creation date: (09/12/2002 01:57:05 p.m.)
 * @return float
 */
public String getPx46_60() {

	return getDSaldo() > 0 ? org.nibble.util.Formato.formateoNumerico((sx46_60/getDSaldo())*100,"##0.00"): org.nibble.util.Formato.formateoNumerico(0,"##0.00");

}
/**
 * Insert the method's description here.
 * Creation date: (09/12/2002 02:01:54 p.m.)
 * @return float
 */
public String getPx61() {
	return getDSaldo() > 0 ? org.nibble.util.Formato.formateoNumerico((sx61/getDSaldo())*100,"##0.00"): org.nibble.util.Formato.formateoNumerico(0,"##0.00");
}
/**
 * Insert the method's description here.
 * Creation date: (09/12/2002 01:55:25 p.m.)
 * @return float
 */
public String getPx8_29() {
	return getDSaldo() > 0 ? org.nibble.util.Formato.formateoNumerico((sx8_29/getDSaldo())*100,"##0.00"): org.nibble.util.Formato.formateoNumerico(0,"##0.00");
}
/**
 * Insert the method's description here.
 * Creation date: (09/12/2002 01:53:06 p.m.)
 * @return float
 */
public String getPxvencer() {
	return getDSaldo() > 0 ? org.nibble.util.Formato.formateoNumerico((sxvencer/getDSaldo())*100,"##0.00"): org.nibble.util.Formato.formateoNumerico(0,"##0.00");
}
/**
 * Insert the method's description here.
 * Creation date: (06/12/2002 07:45:50 p.m.)
 * @return float
 */
public String getSaldo() {
	return org.nibble.util.Formato.formateoNumerico(sxvencer+sx1_7+sx8_29+sx30_45+sx46_60+sx61,"##,###,##0.00");
}
/**
 * Insert the method's description here.
 * Creation date: (09/12/2002 01:53:06 p.m.)
 * @return float
 */
public String getSx1_7() {
	return org.nibble.util.Formato.formateoNumerico( sx1_7,"##,###,##0.00");
}
/**
 * Insert the method's description here.
 * Creation date: (09/12/2002 01:55:54 p.m.)
 * @return float
 */
public String getSx30_45() {
	return org.nibble.util.Formato.formateoNumerico(sx30_45,"##,###,##0.00");
}
/**
 * Insert the method's description here.
 * Creation date: (09/12/2002 01:57:05 p.m.)
 * @return float
 */
public String getSx46_60() {
	return org.nibble.util.Formato.formateoNumerico(sx46_60,"##,###,##0.00");
}
/**
 * Insert the method's description here.
 * Creation date: (09/12/2002 02:01:54 p.m.)
 * @return float
 */
public String getSx61() {
	return org.nibble.util.Formato.formateoNumerico(sx61,"##,###,##0.00");
}
/**
 * Insert the method's description here.
 * Creation date: (09/12/2002 01:55:25 p.m.)
 * @return float
 */
public String getSx8_29() {
	return org.nibble.util.Formato.formateoNumerico(sx8_29,"##,###,##0.00");
}
/**
 * Insert the method's description here.
 * Creation date: (09/12/2002 01:53:06 p.m.)
 * @return float
 */
public String getSxvencer() {
	return org.nibble.util.Formato.formateoNumerico(sxvencer,"##,###,##0.00");
}
public int getTiconsecsubdocto(int index){
	
	dbCTAXCOBRARr a = (dbCTAXCOBRARr) vCxc.elementAt(index);
	return a.getTiconsecsubdocto();
	
}
public int getTiidtipodocto(int index){
	
	dbCTAXCOBRARr a = (dbCTAXCOBRARr) vCxc.elementAt(index);
	return a.getTiidtipodocto();
	
}
public String getTisfechamovimiento(int index){
	
	dbCTAXCOBRARr a = (dbCTAXCOBRARr) vCxc.elementAt(index);
	/* Autor: Victor Romero
	* Modificacion: Se utiliza el metodo Fecha.formatMySQLddMMyyyy(a.getTisfechavencimient());
	* para cambiar el Formato de la fecha a dd/MM/yyyy
	* en lugar de Fecha.formatMySQL(a.getTisfechavencimient());  
	* Fecha de Modificacion: 05-02-2004
	*/ 
	return org.nibble.util.Fecha.formatMySQLddMMyyyy(a.getTisfechamovimiento());
}
public String getTisfechavencimient(int index){

	dbCTAXCOBRARr a = (dbCTAXCOBRARr) vCxc.elementAt(index);
	/* Autor: Victor Romero
	* Modificacion: Se utiliza el metodo Fecha.formatMySQLddMMyyyy(a.getTisfechavencimient());
	* para cambiar el Formato de la fecha a dd/MM/yyyy
	* en lugar de Fecha.formatMySQL(a.getTisfechavencimient());  
	* Fecha de Modificacion: 05-02-2004
	*/ 
	return org.nibble.util.Fecha.formatMySQLddMMyyyy(a.getTisfechavencimient());
}
public String getVchDestipocontradocto(int index){
	
	dbCTAXCOBRARr a = (dbCTAXCOBRARr) vCxc.elementAt(index);
	return a.getTipocontradocto()==null? "":a.getTipocontradocto();
	
}
public String getVchDestipodocto(int index){
	
	dbCTAXCOBRARr a = (dbCTAXCOBRARr) vCxc.elementAt(index);	
	return a.getTipodocto();
	
}
/**
 * Insert the method's description here.
 * Creation date: (09/12/2002 01:57:05 p.m.)
 * @return float
 */
public String getVencido() {
	return org.nibble.util.Formato.formateoNumerico(sx1_7+sx8_29+sx30_45+sx46_60+sx61,"##,###,##0.00");
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
 * Creation date: (18/11/2002 07:49:01 p.m.)
 * @return boolean
 */
public boolean isVencido(int index) {

	boolean ven = false;
	dbCTAXCOBRARr a = (dbCTAXCOBRARr) vCxc.elementAt(index);
	String fven = a.getTisfechavencimient();
	String fnow = org.nibble.util.Fecha.getCurrentDate("yyyyMMddhhmmss");
	return org.nibble.util.Fecha.mayor(fnow,fven);
}

public boolean getisVencido(int index) {

	boolean ven = false;
	dbCTAXCOBRARr a = (dbCTAXCOBRARr) vCxc.elementAt(index);
	String fven = a.getTisfechavencimient();
	String fnow = org.nibble.util.Fecha.getCurrentDate("yyyyMMddhhmmss");
	return org.nibble.util.Fecha.mayor(fnow,fven);
}
/**
 * Insert the method's description here.
 * Creation date: (14/01/2003 07:51:39 p.m.)
 * @param newCliente dbbeans.cliente.dbCLIENTE
 */


public void setCliente(dbbeans.cliente.dbCLIENTE newCliente) {
	beanCliente = newCliente;
}
/**
 * Insert the method's description here.
 * Creation date: (12/11/2002 04:26:34 p.m.)
 * @param newCxc java.util.Vector
 */
public void setCxc(java.util.Vector newCxc) {
	vCxc = newCxc;
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
 * Creation date: (06/12/2002 07:46:11 p.m.)
 * @param newDisponible float
 */
public void setDisponible(float newDisponible) {
	decDisponible = newDisponible;
}
/**
 * Insert the method's description here.
 * Creation date: (12/11/2002 02:04:47 p.m.)
 * @param newIdcliente int
 */
public void setIdcliente(int newIdcliente) {
	intIdcliente = newIdcliente;
}
/**
 * Insert the method's description here.
 * Creation date: (24/01/2003 07:40:04 p.m.)
 * @param newIngreso float
 */
public void setIngreso(float newIngreso) {
	decIngreso = newIngreso;
}
/**
 * Insert the method's description here.
 * Creation date: (06/12/2002 07:45:36 p.m.)
 * @param newLimite float
 */
public void setLimite(float newLimite) {
	decLimite = newLimite;
}
/**
 * Insert the method's description here.
 * Creation date: (06/12/2002 07:45:50 p.m.)
 * @param newSaldo float
 */
public void setSaldo(float newSaldo) {
	decSaldo = newSaldo;
}
/**
 * Insert the method's description here.
 * Creation date: (09/12/2002 12:52:29 p.m.)
 * @param newSaldos java.util.Vector
 */
public void setSaldos(java.util.Vector newSaldos) {
	vSaldos = newSaldos;
	String fven = "", fhoy= org.nibble.util.Fecha.getCurrentDate("yyyyMMdd");
	sxvencer = 0; sx1_7 = 0; sx8_29 = 0; sx30_45 = 0; sx46_60 = 0; sx61 = 0;
	
	for(int i=0; i < vSaldos.size(); i++){
		dbCTAXCOBRARr a = (dbCTAXCOBRARr) vSaldos.elementAt(i);
		fven = a.getTisfechavencimient().substring(0,8);
		if (org.nibble.util.Fecha.mayor(org.nibble.util.Fecha.getCurrentDate("yyyyMMddHHmmss"),a.getTisfechavencimient())){
			int dif = org.nibble.util.Fecha.getdiferencia(fven,fhoy);
			
			if (dif>=1 && dif<= 7)	sx1_7   = sx1_7 + (a.getDecdebe()-a.getDechaber());
			if (dif>=8 && dif<= 29)	sx8_29  = sx8_29 + (a.getDecdebe()-a.getDechaber());
			if (dif>=30 && dif<=45)	sx30_45 = sx30_45 + (a.getDecdebe()-a.getDechaber());
			if (dif>=46 && dif<=60)	sx46_60 = sx46_60 + (a.getDecdebe()-a.getDechaber());
			if (dif>=61)			sx61    = sx61 + (a.getDecdebe()-a.getDechaber());
		}else
			sxvencer = sxvencer+(a.getDecdebe()-a.getDechaber());
	}
}
/**
 * Insert the method's description here.
 * Creation date: (12/11/2002 04:26:34 p.m.)
 * @return java.util.Vector
 */
public int sizeCxc() {
	return vCxc.size();
}
}
