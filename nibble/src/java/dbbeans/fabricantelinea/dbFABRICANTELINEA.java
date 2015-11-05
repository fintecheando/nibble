/** java bean entity **/
package dbbeans.fabricantelinea;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class dbFABRICANTELINEA implements org.nibble.main.dbInterfase {
	private Connection conn;
	private ResultSet rSet;
	private PreparedStatement stmt;


	private int iidusuario;
	private int iidlinea;


	public void create() throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		query.append("INSERT INTO FABRICANTELINEA (iidusuario,iidlinea) ");
		query.append(" VALUES(?,?) ");
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setInt(1,iidusuario);
		stmt.setInt(2,iidlinea);
		stmt.executeUpdate();
		stmt.close();;
	}
	public boolean load() throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		query.append("SELECT * ");
		query.append("FROM FABRICANTELINEA ");
		query.append("WHERE iidusuario=?  and iidlinea=? ");
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setInt(1,iidusuario);
		stmt.setInt(2,iidlinea);
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
		query.append("DELETE FROM FABRICANTELINEA ");
		query.append("WHERE iidusuario=?  and iidlinea=? ");
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setInt(1,iidusuario);
		stmt.setInt(2,iidlinea);
		stmt.executeUpdate();
		stmt.close();
	}
	public void store() throws SQLException {
		StringBuffer query = new StringBuffer();
		query.append("UPDATE FABRICANTELINEA SET ");
		query.append("WHERE iidusuario=?  and iidlinea=? ");
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setInt(1,iidusuario);
		stmt.setInt(2,iidlinea);
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
	public void setIidlinea(int iidlinea){
		this.iidlinea=iidlinea;
	}
	public int getIidlinea(){
		return this.iidlinea;
	}
}
