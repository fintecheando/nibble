/** java bean entity **/
package dbbeans.estatuspedidos;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class dbESTATUSPEDIDOS implements org.nibble.main.dbInterfase {
	private Connection conn;
	private ResultSet rSet;
	private PreparedStatement stmt;


	private int tiestatus;
	private String vchdescestatus;


	public void create() throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		query.append("INSERT INTO ESTATUSPEDIDOS (tiestatus,vchdescestatus) ");
		query.append(" VALUES(?,?) ");
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setInt(1,tiestatus);
		stmt.setString(2,vchdescestatus);
		stmt.executeUpdate();
		stmt.close();;
	}
	public boolean load() throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		query.append("SELECT * ");
		query.append("FROM ESTATUSPEDIDOS ");
		query.append("WHERE tiestatus=? ");
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setInt(1,tiestatus);
		rSet = stmt.executeQuery();


		if (rSet.next()) {
			this.vchdescestatus = rSet.getString("vchdescestatus");
		}
		else
			throw new Exception ("No se encontro el registro");
		rSet.close();
		stmt.close();
		return false;
	}
	public void remove() throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		query.append("DELETE FROM ESTATUSPEDIDOS ");
		query.append("WHERE tiestatus=? ");
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setInt(1,tiestatus);
		stmt.executeUpdate();
		stmt.close();
	}
	public void store() throws SQLException {
		StringBuffer query = new StringBuffer();
		query.append("UPDATE ESTATUSPEDIDOS SET vchdescestatus=? ");
		query.append("WHERE tiestatus=? ");
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setString(1,vchdescestatus);
		stmt.setInt(2,tiestatus);
		stmt.executeUpdate();
		stmt.close();
	}
	public void setConnection(java.sql.Connection conn) {
		this.conn = conn;
	}
	public void setTiestatus(int tiestatus){
		this.tiestatus=tiestatus;
	}
	public int getTiestatus(){
		return this.tiestatus;
	}
	public void setVchdescestatus(String vchdescestatus){
		this.vchdescestatus=vchdescestatus;
	}
	public String getVchdescestatus(){
		return this.vchdescestatus;
	}
}
