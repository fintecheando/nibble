/** java bean entity **/
package dbbeans.planta;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class dbPLANTA implements org.nibble.main.dbInterfase {
	private Connection conn;
	private ResultSet rSet;
	private PreparedStatement stmt;


	private int iidplanta;
	private String vchdescripcion;


	public void create() throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		query.append("INSERT INTO proyecto (iidplanta,vchdescripcion) ");
		query.append(" VALUES(?,?) ");
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setInt(1,iidplanta);
		stmt.setString(2,vchdescripcion);
		stmt.executeUpdate();
		rSet.close();
		stmt.close();;
	}
	public boolean load() throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		query.append("SELECT * ");
		query.append("FROM PLANTA ");
		query.append("WHERE iidplanta=? ");
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setInt(1,iidplanta);
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
		query.append("DELETE FROM PLANTA ");
		query.append("WHERE iidplanta=? ");
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setInt(1,iidplanta);
		stmt.executeUpdate();
		stmt.close();
	}
	public void store() throws SQLException {
		StringBuffer query = new StringBuffer();
		query.append("UPDATE PLANTA SET vchdescripcion=? ");
		query.append("WHERE iidplanta=? ");
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setString(1,vchdescripcion);
		stmt.setInt(2,iidplanta);
		stmt.executeUpdate();
		stmt.close();
	}
	public void setConnection(java.sql.Connection conn) {
		this.conn = conn;
	}
	public void setIidplanta(int iidplanta){
		this.iidplanta=iidplanta;
	}
	public int getIidplanta(){
		return this.iidplanta;
	}
	public void setVchdescripcion(String vchdescripcion){
		this.vchdescripcion=vchdescripcion;
	}
	public String getVchdescripcion(){
		return this.vchdescripcion;
	}
}
