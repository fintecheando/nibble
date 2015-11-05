/** java bean entity **/
package dbbeans.viadeembarque;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class dbVIADEEMBARQUE implements org.nibble.main.dbInterfase {
	private Connection conn;
	private ResultSet rSet;
	private PreparedStatement stmt;


	private short siidviaembarque;
	private String vchdescviaembarque;


	public void create() throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		query.append("INSERT INTO VIADEEMBARQUE (siidviaembarque,vchdescviaembarque) ");
		query.append(" VALUES(?,?) ");
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setShort(1,siidviaembarque);
		stmt.setString(2,vchdescviaembarque);
		stmt.executeUpdate();
		stmt.close();;
	}
	public boolean load() throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		query.append("SELECT * ");
		query.append("FROM VIADEEMBARQUE ");
		query.append("WHERE siidviaembarque=? ");
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setShort(1,siidviaembarque);
		rSet = stmt.executeQuery();


		if (rSet.next()) {
			this.vchdescviaembarque = rSet.getString("vchdescviaembarque");
		}
		else
			throw new Exception ("No se encontro el registro");
		rSet.close();
		stmt.close();
		return false;
	}
	public void remove() throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		query.append("DELETE FROM VIADEEMBARQUE ");
		query.append("WHERE siidviaembarque=? ");
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setShort(1,siidviaembarque);
		stmt.executeUpdate();
		stmt.close();
	}
	public void store() throws SQLException {
		StringBuffer query = new StringBuffer();
		query.append("UPDATE VIADEEMBARQUE SET vchdescviaembarque=? ");
		query.append("WHERE siidviaembarque=? ");
		stmt = conn.prepareStatement(query.toString().toLowerCase().toLowerCase());
		stmt.setString(1,vchdescviaembarque);
		stmt.setShort(2,siidviaembarque);
		stmt.executeUpdate();
		stmt.close();
	}
	public void setConnection(java.sql.Connection conn) {
		this.conn = conn;
	}
	public void setSiidviaembarque(short siidviaembarque){
		this.siidviaembarque=siidviaembarque;
	}
	public short getSiidviaembarque(){
		return this.siidviaembarque;
	}
	public void setVchdescviaembarque(String vchdescviaembarque){
		this.vchdescviaembarque=vchdescviaembarque;
	}
	public String getVchdescviaembarque(){
		return this.vchdescviaembarque;
	}
}
