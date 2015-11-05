/** java bean entity **/
package dbbeans.alimentacion_combustible;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class dbALIMENTACION_COMBUSTIBLE implements org.nibble.main.dbInterfase {
	private Connection conn;
	private ResultSet rSet;
	private PreparedStatement stmt;
    

	private short sialimcmbst;
	private String vchdescripcion;


	public void create() throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		query.append("INSERT INTO proyecto (sialimcmbst,vchdescripcion) ");
		query.append(" VALUES(?,?) ");
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setShort(1,sialimcmbst);
		stmt.setString(2,vchdescripcion);
		stmt.executeUpdate();
		rSet.close();
		stmt.close();;
	}
	public boolean load() throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		query.append("SELECT * ");
		query.append("FROM ALIMENTACION_COMBUSTIBLE ");
		query.append("WHERE sialimcmbst=? ");
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setShort(1,sialimcmbst);
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
		query.append("DELETE FROM ALIMENTACION_COMBUSTIBLE ");
		query.append("WHERE sialimcmbst=? ");
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setShort(1,sialimcmbst);
		stmt.executeUpdate();
		stmt.close();
	}
	public void store() throws SQLException {
		StringBuffer query = new StringBuffer();
		query.append("UPDATE ALIMENTACION_COMBUSTIBLE SET vchdescripcion=? ");
		query.append("WHERE sialimcmbst=? ");
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setString(1,vchdescripcion);
		stmt.setShort(2,sialimcmbst);
		stmt.executeUpdate();
		stmt.close();
	}
	public void setConnection(java.sql.Connection conn) {
		this.conn = conn;
	}
	public void setSialimcmbst(short sialimcmbst){
		this.sialimcmbst=sialimcmbst;
	}
	public short getSialimcmbst(){
		return this.sialimcmbst;
	}
	public void setVchdescripcion(String vchdescripcion){
		this.vchdescripcion=vchdescripcion;
	}
	public String getVchdescripcion(){
		return this.vchdescripcion;
	}
}
