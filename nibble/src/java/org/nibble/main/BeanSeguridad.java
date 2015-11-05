package org.nibble.main;

/**
 * Insert the type's description here.
  * @author: 
 */
import dbbeans.gruposmenu.dbGRUPOSMENU;
import java.sql.Connection;

public class BeanSeguridad {
	Connection con = null;
/**
 * Insert the method's description here.
  */
public BeanSeguridad() {
}
/**
 * Insert the method's description here.
  */
public BeanSeguridad(Connection con) {
	this.con = con;
}
/**
 * Insert the method's description here.
  * @param con java.sql.Connection
 */
public void setConnection(Connection con) {
	this.con = con;
}
/**
 * Insert the method's description here.
  * @return boolean
 */
public boolean tienePermiso(int sistema,int grupo,int menu) {
	if (menu == -1) return true;
	
	boolean result = false;
	dbGRUPOSMENU a = new dbGRUPOSMENU();
	a.setConnection(con);
	a.setIidsistema(sistema);
	a.setIidgrupo(grupo);
	a.setIidmenu(menu);
	try{
		if (a.load()) result=true;
	}catch (Exception e){
	}
	return result;
}
}
