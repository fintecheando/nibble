/** java bean entity **/
package dbbeans.partesugerencia;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class dbPARTESUGERENCIA implements org.nibble.main.dbInterfase {
	private Connection conn;
	private ResultSet rSet;
	private PreparedStatement stmt;


	private int iidpartesugerencia;
	private String tisfecha;
	private String vchdescripcion;


	public void create() throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		query.append("INSERT INTO PARTESUGERENCIA (tisfecha,vchdescripcion) ");
		query.append(" VALUES(?,?) ");
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setString(1,tisfecha);
		stmt.setString(2,vchdescripcion);
		stmt.executeUpdate();
		stmt.close();;
	}
	public boolean load() throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		query.append("SELECT * ");
		query.append("FROM PARTESUGERENCIA ");
		query.append("WHERE iidpartesugerencia=? ");
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setInt(1,iidpartesugerencia);
		rSet = stmt.executeQuery();


		if (rSet.next()) {
			this.tisfecha = rSet.getString("tisfecha");
			this.vchdescripcion = rSet.getString("vchdescripcion");
		}
		else
			throw new Exception ("No se encontro el registro");
		rSet.close();
		stmt.close();
		return false;
	}
	public void remove() throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		query.append("DELETE FROM PARTESUGERENCIA ");
		query.append("WHERE iidpartesugerencia=? ");
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setInt(1,iidpartesugerencia);
		stmt.executeUpdate();
		stmt.close();
	}
	public void store() throws SQLException {
		StringBuffer query = new StringBuffer();
		query.append("UPDATE PARTESUGERENCIA SET tisfecha=? , vchdescripcion=? ");
		query.append("WHERE iidpartesugerencia=? ");
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setString(1,tisfecha);
		stmt.setString(2,vchdescripcion);
		stmt.setInt(3,iidpartesugerencia);
		stmt.executeUpdate();
		stmt.close();
	}
	public void setConnection(java.sql.Connection conn) {
		this.conn = conn;
	}
	public void setIidpartesugerencia(int iidpartesugerencia){
		this.iidpartesugerencia=iidpartesugerencia;
	}
	public int getIidpartesugerencia(){
		return this.iidpartesugerencia;
	}
	public void setTisfecha(String tisfecha){
		this.tisfecha=tisfecha;
	}
	public String getTisfecha(){
		return this.tisfecha;
	}
	public void setVchdescripcion(String vchdescripcion){
		this.vchdescripcion=vchdescripcion;
	}
	public String getVchdescripcion(){
		return this.vchdescripcion;
	}
}
