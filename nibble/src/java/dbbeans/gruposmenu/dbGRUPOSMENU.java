/** java bean entity **/
package dbbeans.gruposmenu;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class dbGRUPOSMENU implements org.nibble.main.dbInterfase {
	private Connection conn;
	private ResultSet rSet;
	private PreparedStatement stmt;


	private int iidgrupo;
	private int iidmenu;
	private int iidsistema;


	public void create() throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		query.append("INSERT INTO GRUPOSMENU (iidgrupo,iidmenu,iidsistema) ");
		query.append(" VALUES(?,?,?) ");
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setInt(1,iidgrupo);
		stmt.setInt(2,iidmenu);
		stmt.setInt(3,iidsistema);
		stmt.executeUpdate();
		stmt.close();;
	}
	public boolean load() throws Exception, java.sql.SQLException {
		boolean result=false;
		StringBuffer query = new StringBuffer();
		query.append("SELECT * ");
		query.append("FROM GRUPOSMENU ");
		query.append("WHERE iidgrupo=?  and iidmenu=?  and iidsistema=? ");
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setInt(1,iidgrupo);
		stmt.setInt(2,iidmenu);
		stmt.setInt(3,iidsistema);
		rSet = stmt.executeQuery();


		if (rSet.next()) {
			result = true;
		}
		rSet.close();
		stmt.close();
		return result;
	}
	public void remove() throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		query.append("DELETE FROM GRUPOSMENU ");
		query.append("WHERE iidgrupo=?  and iidmenu=?  and iidsistema=? ");
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setInt(1,iidgrupo);
		stmt.setInt(2,iidmenu);
		stmt.setInt(3,iidsistema);
		stmt.executeUpdate();
		stmt.close();
	}
	public void store() throws SQLException {
		StringBuffer query = new StringBuffer();
		query.append("UPDATE GRUPOSMENU SET ");
		query.append("WHERE iidgrupo=?  and iidmenu=?  and iidsistema=? ");
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setInt(1,iidgrupo);
		stmt.setInt(2,iidmenu);
		stmt.setInt(3,iidsistema);
		stmt.executeUpdate();
		stmt.close();
	}
	public void setConnection(java.sql.Connection conn) {
		this.conn = conn;
	}
	public void setIidgrupo(int iidgrupo){
		this.iidgrupo=iidgrupo;
	}
	public int getIidgrupo(){
		return this.iidgrupo;
	}
	public void setIidmenu(int iidmenu){
		this.iidmenu=iidmenu;
	}
	public int getIidmenu(){
		return this.iidmenu;
	}
	public void setIidsistema(int iidsistema){
		this.iidsistema=iidsistema;
	}
	public int getIidsistema(){
		return this.iidsistema;
	}
}
