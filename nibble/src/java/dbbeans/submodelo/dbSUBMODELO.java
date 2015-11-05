/** java bean entity **/
package dbbeans.submodelo;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class dbSUBMODELO implements org.nibble.main.dbInterfase {
	private Connection conn;
	private ResultSet rSet;
	private PreparedStatement stmt;


	private int iidmodelo;
	private int iidsubmodelo;
	private String vchdescripcion;


	public void create() throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		query.append("INSERT INTO proyecto (iidmodelo,iidsubmodelo,vchdescripcion) ");
		query.append(" VALUES(?,?,?) ");
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setInt(1,iidmodelo);
		stmt.setInt(2,iidsubmodelo);
		stmt.setString(3,vchdescripcion);
		stmt.executeUpdate();
		rSet.close();
		stmt.close();;
	}
	public boolean load() throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		query.append("SELECT * ");
		query.append("FROM SUBMODELO ");
		query.append("WHERE ");
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		rSet = stmt.executeQuery();


		if (rSet.next()) {
			this.iidmodelo = rSet.getInt("iidmodelo");
			this.iidsubmodelo = rSet.getInt("iidsubmodelo");
			this.vchdescripcion = rSet.getString("vchdescripcion");
		}
		rSet.close();
		stmt.close();
		return false;
	}
	public void remove() throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		query.append("DELETE FROM SUBMODELO ");
		query.append("WHERE ");
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.executeUpdate();
		stmt.close();
	}
	public void store() throws SQLException {
		StringBuffer query = new StringBuffer();
		query.append("UPDATE SUBMODELO SET iidmodelo=? , iidsubmodelo=? , vchdescripcion=? ");
		query.append("WHERE ");
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setInt(1,iidmodelo);
		stmt.setInt(2,iidsubmodelo);
		stmt.setString(3,vchdescripcion);
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
	public void setIidsubmodelo(int iidsubmodelo){
		this.iidsubmodelo=iidsubmodelo;
	}
	public int getIidsubmodelo(){
		return this.iidsubmodelo;
	}
	public void setVchdescripcion(String vchdescripcion){
		this.vchdescripcion=vchdescripcion;
	}
	public String getVchdescripcion(){
		return this.vchdescripcion;
	}
}
