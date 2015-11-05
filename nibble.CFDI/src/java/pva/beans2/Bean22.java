package pva.beans2;

/**
 * Insert the type's description here.
 * Creation date: (24/10/2002 06:04:39 p.m.)
 * @author: 
 */
import dbbeans.usuarios.*;
import dbbeans.privilegiosgrupo.*;
 
public class Bean22 {
	private java.util.Vector vctResult;
	private java.util.Vector vctGrupos;	
	public String getChactivo(int index){

		dbUSUARIOS dbu = (dbUSUARIOS)vctResult.elementAt(index);
		return dbu.getChactivo();
	}
	public int getGIidgrupo(int index){

		dbPRIVILEGIOSGRUPO dbu = (dbPRIVILEGIOSGRUPO)vctGrupos.elementAt(index);
		return dbu.getIidgrupo();
	}
/**
 * Insert the method's description here.
 * Creation date: (24/10/2002 06:05:49 p.m.)
 * @return java.util.Vector
 */
public int getGruposSize() {
	return vctGrupos.size();
}
	public String getGVchdescripcion(int index){

		dbPRIVILEGIOSGRUPO dbu = (dbPRIVILEGIOSGRUPO)vctGrupos.elementAt(index);
		return dbu.getVchdescripcion();
		
	}
	public int getIidgrupo(int index){

		dbUSUARIOS dbu = (dbUSUARIOS)vctResult.elementAt(index);
		return dbu.getIidgrupo();
		
	}
	public int getIidsistema(int index){

		dbUSUARIOS dbu = (dbUSUARIOS)vctResult.elementAt(index);
		return dbu.getIidsistema();
		
	}
	public int getIidusuario(int index){

		dbUSUARIOS dbu = (dbUSUARIOS)vctResult.elementAt(index);
		return dbu.getIidusuario();

	}
/**
 * Insert the method's description here.
 * Creation date: (24/10/2002 06:05:49 p.m.)
 * @return java.util.Vector
 */
public int getUsuariosSize() {
	return vctResult.size();
}
	public String getVchaliasusuario(int index){

		dbUSUARIOS dbu = (dbUSUARIOS)vctResult.elementAt(index);
		return dbu.getVchaliasusuario();	
		
	}
	public String getVchdescripcion(int index){

		dbUSUARIOS dbu = (dbUSUARIOS)vctResult.elementAt(index);
		return dbu.getVchdescripcion();
		
	}
	public String getVchnombreusuario(int index){

		dbUSUARIOS dbu = (dbUSUARIOS)vctResult.elementAt(index);
		return dbu.getVchnombreusuario();

	}
	public String getVchpasaporte(int index){

		dbUSUARIOS dbu = (dbUSUARIOS)vctResult.elementAt(index);
		return dbu.getVchpasaporte();

	}
/**
 * Insert the method's description here.
 * Creation date: (24/10/2002 06:05:49 p.m.)
 * @param newResult java.util.Vector
 */
public void setGrupos(java.util.Vector newGrupos) {
	vctGrupos = newGrupos;
}
/**
 * Insert the method's description here.
 * Creation date: (24/10/2002 06:05:49 p.m.)
 * @param newResult java.util.Vector
 */
public void setUsuarios(java.util.Vector newResult) {
	vctResult = newResult;
}
}
