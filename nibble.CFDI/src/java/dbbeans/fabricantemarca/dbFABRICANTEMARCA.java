/** java bean entity **/
package dbbeans.fabricantemarca;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class dbFABRICANTEMARCA implements org.nibble.main.dbInterfase {
	private Connection conn;
	private ResultSet rSet;
	private PreparedStatement stmt;


	private int iidusuario;
	private int iidmarca;


	public void create() throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		query.append("INSERT INTO FABRICANTEMARCA (iidusuario,iidmarca) ");
		query.append(" VALUES(?,?) ");
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setInt(1,iidusuario);
		stmt.setInt(2,iidmarca);
		stmt.executeUpdate();
		stmt.close();;
	}
	public boolean load() throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		query.append("SELECT * ");
		query.append("FROM FABRICANTEMARCA ");
		query.append("WHERE iidusuario=?  and iidmarca=? ");
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setInt(1,iidusuario);
		stmt.setInt(2,iidmarca);
		rSet = stmt.executeQuery();


		if (rSet.next()) {
		}
		else
			throw new Exception ("No se encontro el registro");
		rSet.close();
		stmt.close();
		return false;
	}
	public void remove() throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		query.append("DELETE FROM FABRICANTEMARCA ");
		query.append("WHERE iidusuario=?  and iidmarca=? ");
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setInt(1,iidusuario);
		stmt.setInt(2,iidmarca);
		stmt.executeUpdate();
		stmt.close();
	}
	public void store() throws SQLException {
		StringBuffer query = new StringBuffer();
		query.append("UPDATE FABRICANTEMARCA SET ");
		query.append("WHERE iidusuario=?  and iidmarca=? ");
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setInt(1,iidusuario);
		stmt.setInt(2,iidmarca);
		stmt.executeUpdate();
		stmt.close();
	}
	public void setConnection(java.sql.Connection conn) {
		this.conn = conn;
	}
	public void setIidusuario(int iidusuario){
		this.iidusuario=iidusuario;
	}
	public int getIidusuario(){
		return this.iidusuario;
	}
	public void setIidmarca(int iidmarca){
		this.iidmarca=iidmarca;
	}
	public int getIidmarca(){
		return this.iidmarca;
	}
}
