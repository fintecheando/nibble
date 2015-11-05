/** java bean entity **/
package dbbeans.grupo;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class dbGRUPO implements org.nibble.main.dbInterfase {
	private Connection conn;
	private ResultSet rSet;
	private PreparedStatement stmt;


	private int iidgrupo;
	private String vchdescripcion;
	private int iidcategoria;


	public void create() throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		query.append("INSERT INTO proyecto (iidgrupo,vchdescripcion,iidcategoria) ");
		query.append(" VALUES(?,?,?) ");
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setInt(1,iidgrupo);
		stmt.setString(2,vchdescripcion);
		stmt.setInt(3,iidcategoria);
		stmt.executeUpdate();
		rSet.close();
		stmt.close();;
	}
	public boolean load() throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		query.append("SELECT * ");
		query.append("FROM GRUPO ");
		query.append("WHERE iidgrupo=? ");
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setInt(1,iidgrupo);
		rSet = stmt.executeQuery();


		if (rSet.next()) {
			this.vchdescripcion = rSet.getString("vchdescripcion");
			this.iidcategoria = rSet.getInt("iidcategoria");
		}
		rSet.close();
		stmt.close();
		return false;
	}
	public void remove() throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		query.append("DELETE FROM GRUPO ");
		query.append("WHERE iidgrupo=? ");
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setInt(1,iidgrupo);
		stmt.executeUpdate();
		stmt.close();
	}
	public void store() throws SQLException {
		StringBuffer query = new StringBuffer();
		query.append("UPDATE GRUPO SET vchdescripcion=? , iidcategoria=? ");
		query.append("WHERE iidgrupo=? ");
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setString(1,vchdescripcion);
		stmt.setInt(2,iidcategoria);
		stmt.setInt(3,iidgrupo);
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
	public void setVchdescripcion(String vchdescripcion){
		this.vchdescripcion=vchdescripcion;
	}
	public String getVchdescripcion(){
		return this.vchdescripcion;
	}
	public void setIidcategoria(int iidcategoria){
		this.iidcategoria=iidcategoria;
	}
	public int getIidcategoria(){
		return this.iidcategoria;
	}
}
