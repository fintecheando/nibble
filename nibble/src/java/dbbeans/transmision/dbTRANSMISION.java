/** java bean entity **/
package dbbeans.transmision;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class dbTRANSMISION implements org.nibble.main.dbInterfase {
	private Connection conn;
	private ResultSet rSet;
	private PreparedStatement stmt;


	private short sitransmision;
	private String vchdescripcion;


	public void create() throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		query.append("INSERT INTO proyecto (sitransmision,vchdescripcion) ");
		query.append(" VALUES(?,?) ");
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setShort(1,sitransmision);
		stmt.setString(2,vchdescripcion);
		stmt.executeUpdate();
		rSet.close();
		stmt.close();;
	}
	public boolean load() throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		query.append("SELECT * ");
		query.append("FROM TRANSMISION ");
		query.append("WHERE sitransmision=? ");
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setShort(1,sitransmision);
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
		query.append("DELETE FROM TRANSMISION ");
		query.append("WHERE sitransmision=? ");
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setShort(1,sitransmision);
		stmt.executeUpdate();
		stmt.close();
	}
	public void store() throws SQLException {
		StringBuffer query = new StringBuffer();
		query.append("UPDATE TRANSMISION SET vchdescripcion=? ");
		query.append("WHERE sitransmision=? ");
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setString(1,vchdescripcion);
		stmt.setShort(2,sitransmision);
		stmt.executeUpdate();
		stmt.close();
	}
	public void setConnection(java.sql.Connection conn) {
		this.conn = conn;
	}
	public void setSitransmision(short sitransmision){
		this.sitransmision=sitransmision;
	}
	public short getSitransmision(){
		return this.sitransmision;
	}
	public void setVchdescripcion(String vchdescripcion){
		this.vchdescripcion=vchdescripcion;
	}
	public String getVchdescripcion(){
		return this.vchdescripcion;
	}
}
