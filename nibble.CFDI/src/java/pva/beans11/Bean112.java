package pva.beans11;

import dbbeans.notadecargo.dbNOTADECARGO;

/**
 * Insert the type's description here.
 * Creation date: (21/11/2002 07:34:22 p.m.)
 * @author: 
 */

public class Bean112 {
	private java.lang.String strFecha;
	private float decIva;
	private boolean imprimir;
	private dbNOTADECARGO nc;
/**
 * Insert the method's description here.
 * Creation date: (21/11/2002 07:35:14 p.m.)
 * @return java.lang.String
 */
public java.lang.String getFecha() {
	return strFecha;
}
/**
 * Insert the method's description here.
 * Creation date: (21/11/2002 07:35:57 p.m.)
 * @return float
 */
public float getIva() {
	return decIva;
}
public int getIdCliente(){
	return nc.getIidnocliente();
}

public int getIdUsuario(){
	return nc.getIidusuario();
}
public String getfecha(){
	return nc.getTisfechanotacargo();
}
public String getFechaVen(){
	return nc.getTisfechavencimient();
}
public String getVchConcepto(){
	return nc.getVchconcepto();
}
public float getImporte(){
	return nc.getDecimporte();
}
public float getIvaImporte(){
	return nc.getDecivaimporte();
}

public int getIdNotaCargo(){
	return nc.getIidnotadecargo();
}


/**
 * Insert the method's description here.
 * Creation date: (21/11/2002 07:35:14 p.m.)
 * @param newFecha java.lang.String
 */
public void setNotaCargo(dbNOTADECARGO dbnc){
	this.nc = new dbNOTADECARGO();
	this.nc.setDecimporte(dbnc.getDecimporte());
	this.nc.setDecivaimporte(dbnc.getDecivaimporte());
	this.nc.setIidnocliente(dbnc.getIidnocliente());
	this.nc.setIidmodulo(dbnc.getIidmodulo());
	this.nc.setIidnotadecargo(dbnc.getIidnotadecargo());
	this.nc.setIidusuario(dbnc.getIidusuario());
	
	this.nc.setInumero(dbnc.getInumero());
	this.nc.setTisfechanotacargo(dbnc.getTisfechanotacargo());
	this.nc.setTisfechavencimient(dbnc.getTisfechavencimient());
	this.nc.setVchconcepto(dbnc.getVchconcepto());
	this.nc.setVchletra(dbnc.getVchletra());
}
public void setFecha(java.lang.String newFecha) {
	strFecha = newFecha;
}
/**
 * Insert the method's description here.
 * Creation date: (21/11/2002 07:35:57 p.m.)
 * @param newIva float
 */
public void setIva(float newIva) {
	decIva = newIva;
}
	/**
	 * @return
	 */
	public boolean isImprimir() {
		return imprimir;
	}

	/**
	 * @param b
	 */
	public void setImprimir(boolean b) {
		imprimir = b;
	}

}
