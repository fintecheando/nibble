package pva.beans4;

/**
 * Insert the type's description here.
 * Creation date: (10/22/2002 7:51:27 PM)
 * @author: 
 */
import dbbeans.niveldeprecio.*;
import org.nibble.util.Formato;

public class BeanResult42b{
	private int iidnocliente;
	private short siidviaembarque;
	private int iidnivel;
	private int iidcatcliente;
	private boolean bitdadodebaja;
	private float declimitecredito;
	private float deccreditutilizado;
	private short sidiadepago;
	private boolean bsuceptiblecredito;
	private short siplazodiaspago;
	
	private boolean mostrador;
	public java.util.Vector vNiveles;
public BeanResult42b() {
	super();
}
	public boolean getBitdadodebaja(){
		return this.bitdadodebaja;
	}
	public boolean getBsuceptiblecredito(){
		return this.bsuceptiblecredito;
	}
	public String getDeccreditutilizado(){
		return Formato.formateoNumerico(this.deccreditutilizado,"#########0.00");
	}
	public String getDeclimitecredito(){
		return Formato.formateoNumerico(this.declimitecredito,"#########0.00");
	}
	public int getIidcatcliente(){
		return this.iidcatcliente;
	}
	public int getIidnivel(){
		return this.iidnivel;
	}
	public int getIidnocliente(){
		return this.iidnocliente;
	}
/**
 * Insert the method's description here.
 * Creation date: (10/31/2002 11:11:21 AM)
 * @return int
 */
public String getNivelesDes(int index) {
	dbNIVELDEPRECIO a = (dbNIVELDEPRECIO) vNiveles.elementAt(index);
	return a.getVchnivel();
}
/**
 * Insert the method's description here.
 * Creation date: (10/31/2002 11:11:21 AM)
 * @return int
 */
public int getNivelesId(int index) {
	dbNIVELDEPRECIO a = (dbNIVELDEPRECIO) vNiveles.elementAt(index);
	return a.getIidnivel();
}
/**
 * Insert the method's description here.
 * Creation date: (10/31/2002 11:11:21 AM)
 * @return int
 */
public int getNivelesIdCat(int index) {
	dbNIVELDEPRECIO a = (dbNIVELDEPRECIO) vNiveles.elementAt(index);
	return a.getIidcatcliente();
}
/**
 * Insert the method's description here.
 * Creation date: (10/31/2002 11:11:21 AM)
 * @return int
 */
public int getNivelesSize() {
	return vNiveles.size();
}
	public short getSidiadepago(){
		return this.sidiadepago;
	}
	public short getSiidviaembarque(){
		return this.siidviaembarque;
	}
	public short getSiplazodiaspago(){
		return this.siplazodiaspago;
	}
/**
 * Insert the method's description here.
 * Creation date: (12/5/2002 1:19:02 PM)
 * @return java.util.Vector
 */
public java.util.Vector getVNiveles() {
	return vNiveles;
}
/**
 * Insert the method's description here.
 * Creation date: (12/5/2002 12:53:30 PM)
 * @return boolean
 */
public boolean isMostrador() {
	return mostrador;
}
	public void setBitdadodebaja(boolean bitdadodebaja){
		this.bitdadodebaja=bitdadodebaja;
	}
	public void setBsuceptiblecredito(boolean bsuceptiblecredito){
		this.bsuceptiblecredito=bsuceptiblecredito;
	}
	public void setDeccreditutilizado(float deccreditutilizado){
		this.deccreditutilizado=deccreditutilizado;
	}
	public void setDeclimitecredito(float declimitecredito){
		this.declimitecredito=declimitecredito;
	}
	public void setIidcatcliente(int iidcatcliente){
		this.iidcatcliente=iidcatcliente;
	}
	public void setIidnivel(int iidnivel){
		this.iidnivel=iidnivel;
	}
	public void setIidnocliente(int iidnocliente){
		this.iidnocliente=iidnocliente;
	}
/**
 * Insert the method's description here.
 * Creation date: (12/5/2002 12:53:30 PM)
 * @param newMostrador boolean
 */
public void setMostrador(boolean newMostrador) {
	mostrador = newMostrador;
}
	public void setSidiadepago(short sidiadepago){
		this.sidiadepago=sidiadepago;
	}
	public void setSiidviaembarque(short siidviaembarque){
		this.siidviaembarque=siidviaembarque;
	}
	public void setSiplazodiaspago(short siplazodiaspago){
		this.siplazodiaspago=siplazodiaspago;
	}
/**
 * Insert the method's description here.
 * Creation date: (12/5/2002 1:19:02 PM)
 * @param newVNiveles java.util.Vector
 */
public void setVNiveles(java.util.Vector newVNiveles) {
	vNiveles = newVNiveles;
}
}
