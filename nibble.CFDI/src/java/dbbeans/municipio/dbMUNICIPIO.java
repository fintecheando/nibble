/** java bean entity **/
package dbbeans.municipio;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class dbMUNICIPIO implements org.nibble.main.dbInterfase {
	private Connection conn;
	private ResultSet rSet;
	private PreparedStatement stmt;


	private int iidmunicipio;
	private int iidestado;
	private String vchnombre;


	public void create() throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		query.append("INSERT INTO MUNICIPIO (iidmunicipio,iidestado,vchnombre) ");
		query.append(" VALUES(?,?,?) ");
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setInt(1,iidmunicipio);
		stmt.setInt(2,iidestado);
		stmt.setString(3,vchnombre);
		stmt.executeUpdate();
		stmt.close();;
	}
	public boolean load() throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		query.append("SELECT * ");
		query.append("FROM MUNICIPIO ");
		query.append("WHERE iidmunicipio=? ");
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setInt(1,iidmunicipio);
		rSet = stmt.executeQuery();


		if (rSet.next()) {
			this.iidestado = rSet.getInt("iidestado");
			this.vchnombre = rSet.getString("vchnombre");
		}
		rSet.close();
		stmt.close();
		return false;
	}
	public void remove() throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		query.append("DELETE FROM MUNICIPIO ");
		query.append("WHERE iidmunicipio=? ");
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setInt(1,iidmunicipio);
		stmt.executeUpdate();
		stmt.close();
	}
	public void store() throws SQLException {
		StringBuffer query = new StringBuffer();
		query.append("UPDATE MUNICIPIO SET iidestado=? , vchnombre=? ");
		query.append("WHERE iidmunicipio=? ");
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setInt(1,iidestado);
		stmt.setString(2,vchnombre);
		stmt.setInt(3,iidmunicipio);
		stmt.executeUpdate();
		stmt.close();
	}
	public void setConnection(java.sql.Connection conn) {
		this.conn = conn;
	}
	public void setIidmunicipio(int iidmunicipio){
		this.iidmunicipio=iidmunicipio;
	}
	public int getIidmunicipio(){
		return this.iidmunicipio;
	}
	public void setIidestado(int iidestado){
		this.iidestado=iidestado;
	}
	public int getIidestado(){
		return this.iidestado;
	}
	public void setVchnombre(String vchnombre){
		this.vchnombre=vchnombre;
	}
	public String getVchnombre(){
		return this.vchnombre;
	}
}
