/** java bean entity **/
package dbbeans.modelo;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class dbMODELO implements org.nibble.main.dbInterfase {
	private Connection conn;
	private ResultSet rSet;
	private PreparedStatement stmt;


	private int iidmodelo;
	private int iidplanta;
	private String vchdescripcion;


	public void create() throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		query.append("INSERT INTO proyecto (iidmodelo,iidplanta,vchdescripcion) ");
		query.append(" VALUES(?,?,?) ");
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setInt(1,iidmodelo);
		stmt.setInt(2,iidplanta);
		stmt.setString(3,vchdescripcion);
		stmt.executeUpdate();
		rSet.close();
		stmt.close();;
	}
	public boolean load() throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		query.append("SELECT * ");
		query.append("FROM MODELO ");
		query.append("WHERE iidmodelo=? ");
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setInt(1,iidmodelo);
		rSet = stmt.executeQuery();


		if (rSet.next()) {
			this.iidplanta = rSet.getInt("iidplanta");
			this.vchdescripcion = rSet.getString("vchdescripcion");
		}
		rSet.close();
		stmt.close();
		return false;
	}
	public void remove() throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		query.append("DELETE FROM MODELO ");
		query.append("WHERE iidmodelo=? ");
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setInt(1,iidmodelo);
		stmt.executeUpdate();
		stmt.close();
	}
	public void store() throws SQLException {
		StringBuffer query = new StringBuffer();
		query.append("UPDATE MODELO SET iidplanta=? , vchdescripcion=? ");
		query.append("WHERE iidmodelo=? ");
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setInt(1,iidplanta);
		stmt.setString(2,vchdescripcion);
		stmt.setInt(3,iidmodelo);
		stmt.executeUpdate();
		stmt.close();
	}
	public void setConnection(java.sql.Connection conn) {
		this.conn = conn;
	}
	public void setIidmodelo(int iidmodelo){
		this.iidmodelo=iidmodelo;
	}
	public int getIidmodelo(){
		return this.iidmodelo;
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
