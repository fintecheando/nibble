package pva.beans4;

/**
 * Insert the type's description here.
 * Creation date: (10/22/2002 7:51:27 PM)
 * @author: 
 */
import dbbeans.pais.*;
import dbbeans.estado.*;
import dbbeans.municipio.*;

public class BeanResult42{
	private int iidnocliente;
	private String vchnombre;
	private String vchrazonsocial;
	private String vchrfc;
	private String vchcurp;
	private String vchtel1;
	private String vchtel2;
	private String vchfax;
	private boolean bitdadodebaja;
	private String vchmail;
	private String vchcolonia;
	private String vchcalle;
	private String chcodigop;
	private short siidpais;
	private int iidestado;
	private int iidmunicipio;
	private java.util.Vector vPaiss;
	private java.util.Vector vEstados;
	public java.util.Vector vMunicipios;
	private boolean Mostrador;
        
        private String vchnumeroexterior;
        private String vchnumerointerior;
        private String vchlocalidad;

public BeanResult42() {
	super();
	vPaiss = new java.util.Vector();
	vEstados = new java.util.Vector();
	vMunicipios = new java.util.Vector();
	vchnombre = new String();
	vchrazonsocial = new String();
	vchrfc = new String();
	vchcurp = new String();
	vchtel1 = new String();
	vchtel2 = new String();
	vchfax = new String();
	vchmail = new String();
	vchcolonia = new String();
	vchcalle = new String();
	chcodigop = new String();
        vchnumeroexterior  = new String();
        vchnumerointerior  = new String();
        vchlocalidad  = new String();

}
	public boolean getBitdadodebaja(){
		return this.bitdadodebaja;
	}
	public String getChcodigop(){
		return this.chcodigop==null?"":this.chcodigop;
	}
    public String getEstadoDes(int index) {
	dbESTADO a = (dbESTADO) vEstados.elementAt(index);
	return a.getVchnombre();
    }
    public int getEstadoId(int index) {
	dbESTADO a = (dbESTADO) vEstados.elementAt(index);
	return a.getIidestado();
    }
    public int getEstadosSize() {
	return vEstados.size();
    }
    public int getIidestado(){
	return this.iidestado;
    }
    public int getIidmunicipio(){
	return this.iidmunicipio;
    }
    public int getIidnocliente(){
	return this.iidnocliente;
    }
    public String getMunicipioDes(int index) {
	dbMUNICIPIO a = (dbMUNICIPIO) vMunicipios.elementAt(index);
	return a.getVchnombre();
    }
/**
 * Insert the method's description here.
 * Creation date: (10/22/2002 3:02:02 PM)
 * @return short
 * @param index int
 */
public int getMunicipioId(int index) {
	dbMUNICIPIO a = (dbMUNICIPIO) vMunicipios.elementAt(index);
	return a.getIidmunicipio();
}
/**
 * Insert the method's description here.
 * Creation date: (10/22/2002 3:02:02 PM)
 * @return short
 * @param index int
 */
public int getMunicipioIdEstado(int index) {
	dbMUNICIPIO a = (dbMUNICIPIO) vMunicipios.elementAt(index);
	return a.getIidestado();
}
/**
 * Insert the method's description here.
 * Creation date: (10/22/2002 4:18:26 PM)
 * @return int
 */
public int getMunicipiosSize() {
	return vMunicipios.size();
}
public String getPaissDes(int index) {
	dbPAIS a = (dbPAIS) vPaiss.elementAt(index);
	return a.getVchnompais();
}
/**
 * Insert the method's description here.
 * Creation date: (10/22/2002 3:02:02 PM)
 * @return short
 * @param index int
 */
public short getPaissId(int index) {
	dbPAIS a = (dbPAIS) vPaiss.elementAt(index);
	return a.getSiidpais();
}
/**
 * Insert the method's description here.
 * Creation date: (10/22/2002 4:18:26 PM)
 * @return int
 */
public int getPaissSize() {
	return vPaiss.size();
}
	public short getSiidpais(){
		return this.siidpais;
	}
	public String getVchcalle(){
		return this.vchcalle==null?"":this.vchcalle;
	}
	public String getVchcolonia(){
		return this.vchcolonia==null?"":this.vchcolonia;
	}
	public String getVchcurp(){
		return this.vchcurp==null?"":this.vchcurp;
	}
	public String getVchfax(){
		return this.vchfax==null?"":this.vchfax;
	}
	public String getVchmail(){
		return this.vchmail==null?"":this.vchmail;
	}
	public String getVchnombre(){
		return this.vchnombre;
	}
	public String getVchrazonsocial(){
		return this.vchrazonsocial==null?"":this.vchrazonsocial;
	}
	public String getVchrfc(){
		return this.vchrfc==null?"":this.vchrfc;
	}
	public String getVchtel1(){
		return this.vchtel1==null?"":this.vchtel1;
	}
	public String getVchtel2(){
		return this.vchtel2==null?"":this.vchtel2;
	}
/**
 * Insert the method's description here.
 * Creation date: (10/22/2002 7:56:23 PM)
 * @return java.util.Vector
 */
public java.util.Vector getVEstados() {
	return vEstados;
}
/**
 * Insert the method's description here.
 * Creation date: (10/22/2002 7:57:23 PM)
 * @return java.util.Vector
 */
public java.util.Vector getVMunicipio() {
	return vMunicipios;
}
/**
 * Insert the method's description here.
 * Creation date: (10/22/2002 7:54:39 PM)
 * @return java.util.Vector
 */
public java.util.Vector getVPaiss() {
	return vPaiss;
}
/**
 * Insert the method's description here.
 * Creation date: (12/5/2002 12:39:41 PM)
 * @return boolean
 */
public boolean isMostrador() {
	return Mostrador;
}
	public void setBitdadodebaja(boolean bitdadodebaja){
		this.bitdadodebaja=bitdadodebaja;
	}
	public void setChcodigop(String chcodigop){
		this.chcodigop=chcodigop;
	}
	public void setIidestado(int iidestado){
		this.iidestado=iidestado;
	}
	public void setIidmunicipio(int iidmunicipio){
		this.iidmunicipio=iidmunicipio;
	}
	public void setIidnocliente(int iidnocliente){
		this.iidnocliente=iidnocliente;
	}
/**
 * Insert the method's description here.
 * Creation date: (12/5/2002 12:39:41 PM)
 * @param newMostrador boolean
 */
public void setMostrador(boolean newMostrador) {
	Mostrador = newMostrador;
}
	public void setSiidpais(short siidpais){
		this.siidpais=siidpais;
	}
	public void setVchcalle(String vchcalle){
		this.vchcalle=vchcalle;
	}
	public void setVchcolonia(String vchcolonia){
		this.vchcolonia=vchcolonia;
	}
	public void setVchcurp(String vchcurp){
		this.vchcurp=vchcurp;
	}
	public void setVchfax(String vchfax){
		this.vchfax=vchfax;
	}
	public void setVchmail(String vchmail){
		this.vchmail=vchmail;
	}
	public void setVchnombre(String vchnombre){
		this.vchnombre=vchnombre;
	}
	public void setVchrazonsocial(String vchrazonsocial){
		this.vchrazonsocial=vchrazonsocial;
	}
	public void setVchrfc(String vchrfc){
		this.vchrfc=vchrfc;
	}
	public void setVchtel1(String vchtel1){
		this.vchtel1=vchtel1;
	}
	public void setVchtel2(String vchtel2){
		this.vchtel2=vchtel2;
	}
/**
 * Insert the method's description here.
 * Creation date: (10/22/2002 7:56:23 PM)
 * @param newVEstados java.util.Vector
 */
public void setVEstados(java.util.Vector newVEstados) {
	vEstados = newVEstados;
}
/**
 * Insert the method's description here.
 * Creation date: (10/22/2002 7:57:23 PM)
 * @param newVMunicipio java.util.Vector
 */
public void setVMunicipio(java.util.Vector newVMunicipio) {
	vMunicipios = newVMunicipio;
}
public void setVPaiss(java.util.Vector newVPaiss) {
	vPaiss = newVPaiss;
}

    /**
     * @return the vchnumeroexterior
     */
    public String getVchnumeroexterior() {
        return this.vchnumeroexterior==null?"":this.vchnumeroexterior;
    }

    /**
     * @param vchnumeroexterior the vchnumeroexterior to set
     */
    public void setVchnumeroexterior(String vchnumeroexterior) {
        this.vchnumeroexterior = vchnumeroexterior;
    }

    /**
     * @return the vchnumerointerior
     */
    public String getVchnumerointerior() {
        return this.vchnumerointerior==null?"":this.vchnumerointerior;
    }

    /**
     * @param vchnumerointerior the vchnumerointerior to set
     */
    public void setVchnumerointerior(String vchnumerointerior) {
        this.vchnumerointerior = vchnumerointerior;
    }

    /**
     * @return the vchlocalidad
     */
    public String getVchlocalidad() {
        
        return this.vchlocalidad==null?"":this.vchlocalidad;
    }

    /**
     * @param vchlocalidad the vchlocalidad to set
     */
    public void setVchlocalidad(String vchlocalidad) {
        this.vchlocalidad = vchlocalidad;
    }
}
