/** java bean entity **/
package dbbeans.valecaja;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class dbVALECAJA implements org.nibble.main.dbInterfase {
	private Connection conn;
	private ResultSet rSet;
	private PreparedStatement stmt;


	private int iidvale;
	private int iidnocliente;
	private int iidusuario;
	private int iidmodulo;
	private String tisfecha;
	private float decimporte;


	public void create() throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		query.append("INSERT INTO VALECAJA (iidvale,iidnocliente,iidusuario,iidmodulo,tisfecha,decimporte) ");
		query.append(" VALUES(?,?,?,?,?,?) ");
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setInt(1,iidvale);
		stmt.setInt(2,iidnocliente);
		stmt.setInt(3,iidusuario);
		stmt.setInt(4,iidmodulo);
		stmt.setString(5,tisfecha);
		stmt.setFloat(6,decimporte);
		stmt.executeUpdate();
		stmt.close();;
	}
	public boolean load() throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		query.append("SELECT * ");
		query.append("FROM VALECAJA ");
		query.append("WHERE iidvale=? ");
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setInt(1,iidvale);
		rSet = stmt.executeQuery();


		if (rSet.next()) {
			this.iidnocliente = rSet.getInt("iidnocliente");
			this.iidusuario = rSet.getInt("iidusuario");
			this.iidmodulo = rSet.getInt("iidmodulo");
			this.tisfecha = rSet.getString("tisfecha");
			this.decimporte = rSet.getFloat("decimporte");
		}
		else
			throw new Exception ("No se encontro el registro");
		rSet.close();
		stmt.close();
		return false;
	}
	public void remove() throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		query.append("DELETE FROM VALECAJA ");
		query.append("WHERE iidvale=? ");
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setInt(1,iidvale);
		stmt.executeUpdate();
		stmt.close();
	}
	public void store() throws SQLException {
		StringBuffer query = new StringBuffer();
		query.append("UPDATE VALECAJA SET iidnocliente=? , iidusuario=? , iidmodulo=? , tisfecha=? , decimporte=? ");
		query.append("WHERE iidvale=? ");
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setInt(1,iidnocliente);
		stmt.setInt(2,iidusuario);
		stmt.setInt(3,iidmodulo);
		stmt.setString(4,tisfecha);
		stmt.setFloat(5,decimporte);
		stmt.setInt(6,iidvale);
		stmt.executeUpdate();
		stmt.close();
	}
	
	public int findIidvale() throws Exception, java.sql.SQLException {
		int vale = 0;
		StringBuffer query = new StringBuffer();
		
		query.append("SELECT iidvale FROM VALECAJA ");
		query.append("WHERE iidnocliente=? ");
		query.append("AND iidusuario=? ");
		query.append("AND iidmodulo=? ");
		query.append("AND tisfecha=? ");
		query.append("AND decimporte=? ");

		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setInt(1,iidnocliente);
		stmt.setInt(2,iidusuario);
		stmt.setInt(3,iidmodulo);
		stmt.setString(4,tisfecha);
		stmt.setFloat(5,decimporte);
		rSet = stmt.executeQuery();

		if (rSet.next()) {
			vale = rSet.getInt("iidvale");
		}

		rSet.close();
		stmt.close();
		return vale;
	}
		
	public void setConnection(java.sql.Connection conn) {
		this.conn = conn;
	}
	public void setIidvale(int iidvale){
		this.iidvale=iidvale;
	}
	public int getIidvale(){
		return this.iidvale;
	}
	public void setIidnocliente(int iidnocliente){
		this.iidnocliente=iidnocliente;
	}
	public int getIidnocliente(){
		return this.iidnocliente;
	}
	public void setIidusuario(int iidusuario){
		this.iidusuario=iidusuario;
	}
	public int getIidusuario(){
		return this.iidusuario;
	}
	public void setIidmodulo(int iidmodulo){
		this.iidmodulo=iidmodulo;
	}
	public int getIidmodulo(){
		return this.iidmodulo;
	}
	public void setTisfecha(String tisfecha){
		this.tisfecha=tisfecha;
	}
	public String getTisfecha(){
		return this.tisfecha;
	}
	public void setDecimporte(float decimporte){
		this.decimporte=decimporte;
	}
	public float getDecimporte(){
		return this.decimporte;
	}
}
