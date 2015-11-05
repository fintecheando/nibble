/** java bean entity **/
package dbbeans.estado;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class dbESTADO implements org.nibble.main.dbInterfase {
	private Connection conn;
	private ResultSet rSet;
	private PreparedStatement stmt;


	private int iidestado;
	private short siidpais;
	private String vchnombre;


	public void create() throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		query.append("INSERT INTO ESTADO (iidestado,siidpais,vchnombre) ");
		query.append(" VALUES(?,?,?) ");
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setInt(1,iidestado);
		stmt.setShort(2,siidpais);
		stmt.setString(3,vchnombre);
		stmt.executeUpdate();
		stmt.close();;
	}
	public boolean load() throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		query.append("SELECT * ");
		query.append("FROM ESTADO ");
		query.append("WHERE iidestado=? ");
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setInt(1,iidestado);
		rSet = stmt.executeQuery();


		if (rSet.next()) {
			this.siidpais = rSet.getShort("siidpais");
			this.vchnombre = rSet.getString("vchnombre");
		}
		rSet.close();
		stmt.close();
		return false;
	}
	public void remove() throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		query.append("DELETE FROM ESTADO ");
		query.append("WHERE iidestado=? ");
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setInt(1,iidestado);
		stmt.executeUpdate();
		stmt.close();
	}
	public void store() throws SQLException {
		StringBuffer query = new StringBuffer();
		query.append("UPDATE ESTADO SET siidpais=? , vchnombre=? ");
		query.append("WHERE iidestado=? ");
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setShort(1,siidpais);
		stmt.setString(2,vchnombre);
		stmt.setInt(3,iidestado);
		stmt.executeUpdate();
		stmt.close();
	}
	public void setConnection(java.sql.Connection conn) {
		this.conn = conn;
	}
	public void setIidestado(int iidestado){
		this.iidestado=iidestado;
	}
	public int getIidestado(){
		return this.iidestado;
	}
	public void setSiidpais(short siidpais){
		this.siidpais=siidpais;
	}
	public short getSiidpais(){
		return this.siidpais;
	}
	public void setVchnombre(String vchnombre){
		this.vchnombre=vchnombre;
	}
	public String getVchnombre(){
		return this.vchnombre;
	}
}
