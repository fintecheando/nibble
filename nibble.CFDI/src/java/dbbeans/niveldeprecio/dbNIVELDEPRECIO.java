/** java bean entity **/
package dbbeans.niveldeprecio;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class dbNIVELDEPRECIO implements org.nibble.main.dbInterfase {
	private Connection conn;
	private ResultSet rSet;
	private PreparedStatement stmt;


	private int iidnivel;
	private int iidcatcliente;
	private String vchnivel;
	private float decutilidad;
	private float decporccomision;
	private boolean bitdadodebaja;


	public void create() throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		query.append("INSERT INTO NIVELDEPRECIO (iidcatcliente,vchnivel,decutilidad,decporccomision,bitdadodebaja) ");
		query.append(" VALUES(?,?,?,?,?) ");
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setInt(1,iidcatcliente);
		stmt.setString(2,vchnivel);
		stmt.setFloat(3,decutilidad);
		stmt.setFloat(4,decporccomision);
		stmt.setBoolean(5,bitdadodebaja);
		stmt.executeUpdate();
		stmt.close();;
	}
	public boolean load() throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		query.append("SELECT * ");
		query.append("FROM NIVELDEPRECIO ");
		query.append("WHERE iidnivel=? ");
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setInt(1,iidnivel);
		rSet = stmt.executeQuery();


		if (rSet.next()) {
			this.iidcatcliente = rSet.getInt("iidcatcliente");
			this.vchnivel = rSet.getString("vchnivel");
			this.decutilidad = rSet.getFloat("decutilidad");
			this.decporccomision = rSet.getFloat("decporccomision");
			this.bitdadodebaja = rSet.getBoolean("bitdadodebaja");
		}
		else
			throw new Exception ("No se encontro el registro");
		rSet.close();
		stmt.close();
		return false;
	}
	public void remove() throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		query.append("DELETE FROM NIVELDEPRECIO ");
		query.append("WHERE iidnivel=? ");
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setInt(1,iidnivel);
		stmt.executeUpdate();
		stmt.close();
	}
	public void store() throws SQLException {
		StringBuffer query = new StringBuffer();
		query.append("UPDATE NIVELDEPRECIO SET iidcatcliente=? , vchnivel=? , decutilidad=? , decporccomision=? , bitdadodebaja=? ");
		query.append("WHERE iidnivel=? ");
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setInt(1,iidcatcliente);
		stmt.setString(2,vchnivel);
		stmt.setFloat(3,decutilidad);
		stmt.setFloat(4,decporccomision);
		stmt.setBoolean(5,bitdadodebaja);
		stmt.setInt(6,iidnivel);
		stmt.executeUpdate();
		stmt.close();
	}
	public void setConnection(java.sql.Connection conn) {
		this.conn = conn;
	}
	public void setIidnivel(int iidnivel){
		this.iidnivel=iidnivel;
	}
	public int getIidnivel(){
		return this.iidnivel;
	}
	public void setIidcatcliente(int iidcatcliente){
		this.iidcatcliente=iidcatcliente;
	}
	public int getIidcatcliente(){
		return this.iidcatcliente;
	}
	public void setVchnivel(String vchnivel){
		this.vchnivel=vchnivel;
	}
	public String getVchnivel(){
		return this.vchnivel;
	}
	public void setDecutilidad(float decutilidad){
		this.decutilidad=decutilidad;
	}
	public float getDecutilidad(){
		return this.decutilidad;
	}
	public void setDecporccomision(float decporccomision){
		this.decporccomision=decporccomision;
	}
	public float getDecporccomision(){
		return this.decporccomision;
	}
	public void setBitdadodebaja(boolean bitdadodebaja){
		this.bitdadodebaja=bitdadodebaja;
	}
	public boolean getBitdadodebaja(){
		return this.bitdadodebaja;
	}

	public boolean isVchnivel(String vchnivel) throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		boolean result = false;
		
		query.append("SELECT * ");
		query.append("FROM NIVELDEPRECIO ");
		query.append("WHERE vchnivel=? ");
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setString(1,vchnivel);
		rSet = stmt.executeQuery();
		if (rSet.next()) 
			result = true;
		rSet.close();
		stmt.close();
		return result;
	}
}
