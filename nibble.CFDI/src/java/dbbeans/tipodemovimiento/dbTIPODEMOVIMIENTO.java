/** java bean entity **/
package dbbeans.tipodemovimiento;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class dbTIPODEMOVIMIENTO implements org.nibble.main.dbInterfase {
	private Connection conn;
	private ResultSet rSet;
	private PreparedStatement stmt;


	private int titipomov;
	private String vchdesctipomov;


	public void create() throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		query.append("INSERT INTO TIPODEMOVIMIENTO (titipomov,vchdesctipomov) ");
		query.append(" VALUES(?,?) ");
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setInt(1,titipomov);
		stmt.setString(2,vchdesctipomov);
		stmt.executeUpdate();
		stmt.close();;
	}
	public boolean load() throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		query.append("SELECT * ");
		query.append("FROM TIPODEMOVIMIENTO ");
		query.append("WHERE titipomov=? ");
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setInt(1,titipomov);
		rSet = stmt.executeQuery();


		if (rSet.next()) {
			this.vchdesctipomov = rSet.getString("vchdesctipomov");
		}
		else
			throw new Exception ("No se encontro el registro");
		rSet.close();
		stmt.close();
		return false;
	}
	public void remove() throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		query.append("DELETE FROM TIPODEMOVIMIENTO ");
		query.append("WHERE titipomov=? ");
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setInt(1,titipomov);
		stmt.executeUpdate();
		stmt.close();
	}
	public void store() throws SQLException {
		StringBuffer query = new StringBuffer();
		query.append("UPDATE TIPODEMOVIMIENTO SET vchdesctipomov=? ");
		query.append("WHERE titipomov=? ");
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setString(1,vchdesctipomov);
		stmt.setInt(2,titipomov);
		stmt.executeUpdate();
		stmt.close();
	}
	public void setConnection(java.sql.Connection conn) {
		this.conn = conn;
	}
	public void setTitipomov(int titipomov){
		this.titipomov=titipomov;
	}
	public int getTitipomov(){
		return this.titipomov;
	}
	public void setVchdesctipomov(String vchdesctipomov){
		this.vchdesctipomov=vchdesctipomov;
	}
	public String getVchdesctipomov(){
		return this.vchdesctipomov;
	}
}
