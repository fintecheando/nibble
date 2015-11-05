/** java bean entity **/
package dbbeans.tipopago;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class dbTIPOPAGO implements org.nibble.main.dbInterfase {
	private Connection conn;
	private ResultSet rSet;
	private PreparedStatement stmt;


	private int tiidtipopago;
	private String vchdesctipopago;


	public void create() throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		query.append("INSERT INTO TIPOPAGO (tiidtipopago,vchdesctipopago) ");
		query.append(" VALUES(?,?) ");
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setInt(1,tiidtipopago);
		stmt.setString(2,vchdesctipopago);
		stmt.executeUpdate();
		stmt.close();;
	}
	public boolean load() throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		query.append("SELECT * ");
		query.append("FROM TIPOPAGO ");
		query.append("WHERE tiidtipopago=? ");
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setInt(1,tiidtipopago);
		rSet = stmt.executeQuery();


		if (rSet.next()) {
			this.vchdesctipopago = rSet.getString("vchdesctipopago");
		}
		else
			throw new Exception ("No se encontro el registro");
		rSet.close();
		stmt.close();
		return false;
	}
	public void remove() throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		query.append("DELETE FROM TIPOPAGO ");
		query.append("WHERE tiidtipopago=? ");
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setInt(1,tiidtipopago);
		stmt.executeUpdate();
		stmt.close();
	}
	public void store() throws SQLException {
		StringBuffer query = new StringBuffer();
		query.append("UPDATE TIPOPAGO SET vchdesctipopago=? ");
		query.append("WHERE tiidtipopago=? ");
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setString(1,vchdesctipopago);
		stmt.setInt(2,tiidtipopago);
		stmt.executeUpdate();
		stmt.close();
	}
	public void setConnection(java.sql.Connection conn) {
		this.conn = conn;
	}
	public void setTiidtipopago(int tiidtipopago){
		this.tiidtipopago=tiidtipopago;
	}
	public int getTiidtipopago(){
		return this.tiidtipopago;
	}
	public void setVchdesctipopago(String vchdesctipopago){
		this.vchdesctipopago=vchdesctipopago;
	}
	public String getVchdesctipopago(){
		return this.vchdesctipopago;
	}
}
