/** java bean entity **/
package dbbeans.tipodocumento;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class dbTIPODOCUMENTO implements org.nibble.main.dbInterfase {
	private Connection conn;
	private ResultSet rSet;
	private PreparedStatement stmt;


	private int tiidtipodocto;
	private String vchdesctipodocto;


	public void create() throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		query.append("INSERT INTO TIPODOCUMENTO (tiidtipodocto,vchdesctipodocto) ");
		query.append(" VALUES(?,?) ");
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setInt(1,tiidtipodocto);
		stmt.setString(2,vchdesctipodocto);
		stmt.executeUpdate();
		stmt.close();;
	}
	public boolean load() throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		query.append("SELECT * ");
		query.append("FROM TIPODOCUMENTO ");
		query.append("WHERE tiidtipodocto=? ");
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setInt(1,tiidtipodocto);
		rSet = stmt.executeQuery();


		if (rSet.next()) {
			this.vchdesctipodocto = rSet.getString("vchdesctipodocto");
		}
		else
			throw new Exception ("No se encontro el registro");
		rSet.close();
		stmt.close();
		return false;
	}
	public void remove() throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		query.append("DELETE FROM TIPODOCUMENTO ");
		query.append("WHERE tiidtipodocto=? ");
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setInt(1,tiidtipodocto);
		stmt.executeUpdate();
		stmt.close();
	}
	public void store() throws SQLException {
		StringBuffer query = new StringBuffer();
		query.append("UPDATE TIPODOCUMENTO SET vchdesctipodocto=? ");
		query.append("WHERE tiidtipodocto=? ");
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setString(1,vchdesctipodocto);
		stmt.setInt(2,tiidtipodocto);
		stmt.executeUpdate();
		stmt.close();
	}
	public void setConnection(java.sql.Connection conn) {
		this.conn = conn;
	}
	public void setTiidtipodocto(int tiidtipodocto){
		this.tiidtipodocto=tiidtipodocto;
	}
	public int getTiidtipodocto(){
		return this.tiidtipodocto;
	}
	public void setVchdesctipodocto(String vchdesctipodocto){
		this.vchdesctipodocto=vchdesctipodocto;
	}
	public String getVchdesctipodocto(){
		return this.vchdesctipodocto;
	}
}
