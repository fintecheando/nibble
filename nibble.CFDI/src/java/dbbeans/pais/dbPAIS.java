/** java bean entity **/
package dbbeans.pais;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class dbPAIS implements org.nibble.main.dbInterfase {
	private Connection conn;
	private ResultSet rSet;
	private PreparedStatement stmt;


	private short siidpais;
	private String vchnompais;


	public void create() throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		query.append("INSERT INTO PAIS (siidpais,vchnompais) ");
		query.append(" VALUES(?,?) ");
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setShort(1,siidpais);
		stmt.setString(2,vchnompais);
		stmt.executeUpdate();
		stmt.close();;
	}
	public boolean load() throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		query.append("SELECT * ");
		query.append("FROM PAIS ");
		query.append("WHERE siidpais=? ");
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setShort(1,siidpais);
		rSet = stmt.executeQuery();


		if (rSet.next()) {
			this.vchnompais = rSet.getString("vchnompais");
		}
		rSet.close();
		stmt.close();
		return false;
	}
	public void remove() throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		query.append("DELETE FROM PAIS ");
		query.append("WHERE siidpais=? ");
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setShort(1,siidpais);
		stmt.executeUpdate();
		stmt.close();
	}
	public void store() throws SQLException {
		StringBuffer query = new StringBuffer();
		query.append("UPDATE PAIS SET vchnompais=? ");
		query.append("WHERE siidpais=? ");
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setString(1,vchnompais);
		stmt.setShort(2,siidpais);
		stmt.executeUpdate();
		stmt.close();
	}
	public void setConnection(java.sql.Connection conn) {
		this.conn = conn;
	}
	public void setSiidpais(short siidpais){
		this.siidpais=siidpais;
	}
	public short getSiidpais(){
		return this.siidpais;
	}
	public void setVchnompais(String vchnompais){
		this.vchnompais=vchnompais;
	}
	public String getVchnompais(){
		return this.vchnompais;
	}
}
