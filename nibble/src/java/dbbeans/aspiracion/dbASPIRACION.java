/** java bean entity **/
package dbbeans.aspiracion;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class dbASPIRACION implements org.nibble.main.dbInterfase {
	private Connection conn;
	private ResultSet rSet;
	private PreparedStatement stmt;


	private short siaspiracion;
	private String vchdescripcion;


	public void create() throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		query.append("INSERT INTO proyecto (siaspiracion,vchdescripcion) ");
		query.append(" VALUES(?,?) ");
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setShort(1,siaspiracion);
		stmt.setString(2,vchdescripcion);
		stmt.executeUpdate();
		rSet.close();
		stmt.close();;
	}
	public boolean load() throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		query.append("SELECT * ");
		query.append("FROM ASPIRACION ");
		query.append("WHERE siaspiracion=? ");
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setShort(1,siaspiracion);
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
		query.append("DELETE FROM ASPIRACION ");
		query.append("WHERE siaspiracion=? ");
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setShort(1,siaspiracion);
		stmt.executeUpdate();
		stmt.close();
	}
	public void store() throws SQLException {
		StringBuffer query = new StringBuffer();
		query.append("UPDATE ASPIRACION SET vchdescripcion=? ");
		query.append("WHERE siaspiracion=? ");
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setString(1,vchdescripcion);
		stmt.setShort(2,siaspiracion);
		stmt.executeUpdate();
		stmt.close();
	}
	public void setConnection(java.sql.Connection conn) {
		this.conn = conn;
	}
	public void setSiaspiracion(short siaspiracion){
		this.siaspiracion=siaspiracion;
	}
	public short getSiaspiracion(){
		return this.siaspiracion;
	}
	public void setVchdescripcion(String vchdescripcion){
		this.vchdescripcion=vchdescripcion;
	}
	public String getVchdescripcion(){
		return this.vchdescripcion;
	}
}
