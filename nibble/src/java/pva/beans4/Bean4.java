package pva.beans4;

/**
 * Insert the type's description here.
 * Creation date: (10/22/2002 2:58:46 PM)
 * @author: 
 */

import dbbeans.pais.*;
import dbbeans.estado.*;
import dbbeans.municipio.*;

public class Bean4 {
	private java.util.Vector vPaiss;
	private java.util.Vector vEstados;
	public java.util.Vector vMunicipios;
	private short PAIS_DEFAULT;
/**
 * Bean5 constructor comment.
 */
public Bean4() {
	super();
}
/**
 * Insert the method's description here.
 * Creation date: (10/22/2002 3:02:02 PM)
 * @return short
 * @param index int
 */
public String getEstadoDes(int index) {
	dbESTADO a = (dbESTADO) vEstados.elementAt(index);
	return a.getVchnombre();
}
/**
 * Insert the method's description here.
 * Creation date: (10/22/2002 3:02:02 PM)
 * @return short
 * @param index int
 */
public int getEstadoId(int index) {
	dbESTADO a = (dbESTADO) vEstados.elementAt(index);
	return a.getIidestado();
}
/**
 * Insert the method's description here.
 * Creation date: (10/22/2002 3:02:02 PM)
 * @return short
 * @param index int
 */
public short getEstadoIdPais(int index) {
	dbESTADO a = (dbESTADO) vEstados.elementAt(index);
	return a.getSiidpais();
}
/**
 * Insert the method's description here.
 * Creation date: (10/22/2002 4:18:26 PM)
 * @return int
 */
public int getEstadosSize() {
	return vEstados.size();
}
/**
 * Insert the method's description here.
 * Creation date: (10/22/2002 3:02:02 PM)
 * @return short
 * @param index int
 */
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
/**
 * Insert the method's description here.
 * Creation date: (1/14/2003 6:31:14 PM)
 * @return short
 */
public short getPAIS_DEFAULT() {
	return PAIS_DEFAULT;
}
/**
 * Insert the method's description here.
 * Creation date: (10/22/2002 3:02:02 PM)
 * @return short
 * @param index int
 */
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
/**
 * Insert the method's description here.
 * Creation date: (10/22/2002 3:00:07 PM)
 * @return java.util.Vector
 */
public java.util.Vector getVEstados() {
	return vEstados;
}
/**
 * Insert the method's description here.
 * Creation date: (10/22/2002 3:00:27 PM)
 * @return java.util.Vector
 */
public java.util.Vector getVMunicipios() {
	return vMunicipios;
}
/**
 * Insert the method's description here.
 * Creation date: (10/22/2002 2:59:41 PM)
 * @return java.util.Vector
 */
public java.util.Vector getVPaiss() {
	return vPaiss;
}
/**
 * Insert the method's description here.
 * Creation date: (1/14/2003 6:31:14 PM)
 * @param newPAIS_DEFAULT short
 */
public void setPAIS_DEFAULT(short newPAIS_DEFAULT) {
	PAIS_DEFAULT = newPAIS_DEFAULT;
}
/**
 * Insert the method's description here.
 * Creation date: (10/22/2002 3:00:07 PM)
 * @param newVEstados java.util.Vector
 */
public void setVEstados(java.util.Vector newVEstados) {
	vEstados = newVEstados;
}
/**
 * Insert the method's description here.
 * Creation date: (10/22/2002 3:00:27 PM)
 * @param newVMunicipios java.util.Vector
 */
public void setVMunicipios(java.util.Vector newVMunicipios) {
	vMunicipios = newVMunicipios;
}
/**
 * Insert the method's description here.
 * Creation date: (10/22/2002 2:59:41 PM)
 * @param newVPaiss java.util.Vector
 */
public void setVPaiss(java.util.Vector newVPaiss) {
	vPaiss = newVPaiss;
}
}
