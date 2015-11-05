/** java bean entity **/
package dbbeans.categoria;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class dbCATEGORIA implements org.nibble.main.dbInterfase {
	private Connection conn;
	private ResultSet rSet;
	private PreparedStatement stmt;


	private int iidcategoria;
	private String vchdescripcion;


	public void create() throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		query.append("INSERT INTO proyecto (iidcategoria,vchdescripcion) ");
		query.append(" VALUES(?,?) ");
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setInt(1,iidcategoria);
		stmt.setString(2,vchdescripcion);
		stmt.executeUpdate();
		rSet.close();
		stmt.close();;
	}
	public boolean load() throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		query.append("SELECT * ");
		query.append("FROM CATEGORIA ");
		query.append("WHERE iidcategoria=? ");
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setInt(1,iidcategoria);
		rSet = stmt.executeQuery();


		if (rSet.next()) {
			this.vchdescripcion = rSet.getString("vchdescripcion");
		}
		rSet.close();
		stmt.close();
		return false;
	}
	public void remove() throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		query.append("DELETE FROM CATEGORIA ");
		query.append("WHERE iidcategoria=? ");
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setInt(1,iidcategoria);
		stmt.executeUpdate();
		stmt.close();
	}
	public void store() throws SQLException {
		StringBuffer query = new StringBuffer();
		query.append("UPDATE CATEGORIA SET vchdescripcion=? ");
		query.append("WHERE iidcategoria=? ");
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setString(1,vchdescripcion);
		stmt.setInt(2,iidcategoria);
		stmt.executeUpdate();
		stmt.close();
	}
	public void setConnection(java.sql.Connection conn) {
		this.conn = conn;
	}
	public void setIidcategoria(int iidcategoria){
		this.iidcategoria=iidcategoria;
	}
	public int getIidcategoria(){
		return this.iidcategoria;
	}
	public void setVchdescripcion(String vchdescripcion){
		this.vchdescripcion=vchdescripcion;
	}
	public String getVchdescripcion(){
		return this.vchdescripcion;
	}
}
