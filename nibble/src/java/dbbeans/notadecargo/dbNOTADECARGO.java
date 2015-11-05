/** java bean entity **/
package dbbeans.notadecargo;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class dbNOTADECARGO implements org.nibble.main.dbInterfase {
	private Connection conn;
	private ResultSet rSet;
	private PreparedStatement stmt;


	private int iidnotadecargo;
	private int iidnocliente;
	private int iidusuario;
	private int iidmodulo;
	private String vchletra;
	private int inumero;
	private String tisfechanotacargo;
	private String tisfechavencimient;
	private float decimporte;
	private float decivaimporte;
	private String vchconcepto;


	public void create() throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		query.append("INSERT INTO NOTADECARGO (iidnotadecargo,iidnocliente,iidusuario,iidmodulo,vchletra,inumero,tisfechanotacargo,tisfechavencimient,decimporte,decivaimporte,vchconcepto) ");
		query.append(" VALUES(?,?,?,?,?,?,?,?,?,?,?) ");
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setInt(1,iidnotadecargo);
		stmt.setInt(2,iidnocliente);
		stmt.setInt(3,iidusuario);
		stmt.setInt(4,iidmodulo);
		stmt.setString(5,vchletra);
		stmt.setInt(6,inumero);
		stmt.setString(7,tisfechanotacargo);
		stmt.setString(8,tisfechavencimient);
		stmt.setFloat(9,decimporte);
		stmt.setFloat(10,decivaimporte);
		stmt.setString(11,vchconcepto);
		stmt.executeUpdate();
		stmt.close();;
	}
	public boolean load() throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		query.append("SELECT * ");
		query.append("FROM NOTADECARGO ");
		query.append("WHERE iidnotadecargo=? ");
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setInt(1,iidnotadecargo);
		rSet = stmt.executeQuery();


		if (rSet.next()) {
			this.iidnocliente = rSet.getInt("iidnocliente");
			this.iidusuario = rSet.getInt("iidusuario");
			this.iidmodulo = rSet.getInt("iidmodulo");
			this.vchletra = rSet.getString("vchletra");
			this.inumero = rSet.getInt("inumero");
			this.tisfechanotacargo = rSet.getString("tisfechanotacargo");
			this.tisfechavencimient = rSet.getString("tisfechavencimient");
			this.decimporte = rSet.getFloat("decimporte");
			this.decivaimporte = rSet.getFloat("decivaimporte");
			this.vchconcepto = rSet.getString("vchconcepto");
		}
		else
			throw new Exception ("No se encontro el registro");
		rSet.close();
		stmt.close();
		return false;
	}
	public void remove() throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		query.append("DELETE FROM NOTADECARGO ");
		query.append("WHERE iidnotadecargo=? ");
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setInt(1,iidnotadecargo);
		stmt.executeUpdate();
		stmt.close();
	}
	public void store() throws SQLException {
		StringBuffer query = new StringBuffer();
		query.append("UPDATE NOTADECARGO SET iidnocliente=? , iidusuario=? , iidmodulo=? , vchletra=? , inumero=? , tisfechanotacargo=? , tisfechavencimient=? , decimporte=? , decivaimporte=? , vchconcepto=? ");
		query.append("WHERE iidnotadecargo=? ");
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setInt(1,iidnocliente);
		stmt.setInt(2,iidusuario);
		stmt.setInt(3,iidmodulo);
		stmt.setString(4,vchletra);
		stmt.setInt(5,inumero);
		stmt.setString(6,tisfechanotacargo);
		stmt.setString(7,tisfechavencimient);
		stmt.setFloat(8,decimporte);
		stmt.setFloat(9,decivaimporte);
		stmt.setString(10,vchconcepto);
		stmt.setInt(11,iidnotadecargo);
		stmt.executeUpdate();
		stmt.close();
	}
	public void setConnection(java.sql.Connection conn) {
		this.conn = conn;
	}
	public void setIidnotadecargo(int iidnotadecargo){
		this.iidnotadecargo=iidnotadecargo;
	}
	public int getIidnotadecargo(){
		return this.iidnotadecargo;
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
	public void setVchletra(String vchletra){
		this.vchletra=vchletra;
	}
	public String getVchletra(){
		return this.vchletra;
	}
	public void setInumero(int inumero){
		this.inumero=inumero;
	}
	public int getInumero(){
		return this.inumero;
	}
	public void setTisfechanotacargo(String tisfechanotacargo){
		this.tisfechanotacargo=tisfechanotacargo;
	}
	public String getTisfechanotacargo(){
		return this.tisfechanotacargo;
	}
	public void setTisfechavencimient(String tisfechavencimient){
		this.tisfechavencimient=tisfechavencimient;
	}
	public String getTisfechavencimient(){
		return this.tisfechavencimient;
	}
	public void setDecimporte(float decimporte){
		this.decimporte=decimporte;
	}
	public float getDecimporte(){
		return this.decimporte;
	}
	public void setDecivaimporte(float decivaimporte){
		this.decivaimporte=decivaimporte;
	}
	public float getDecivaimporte(){
		return this.decivaimporte;
	}
	public void setVchconcepto(String vchconcepto){
		this.vchconcepto=vchconcepto;
	}
	public String getVchconcepto(){
		return this.vchconcepto;
	}

	public boolean findIidnotacargo() throws Exception, java.sql.SQLException {
		boolean found = false;
		StringBuffer query = new StringBuffer();
		query.append("SELECT * FROM NOTADECARGO ");
		query.append("WHERE iidnocliente=? ");
		query.append("AND iidusuario=? ");
		query.append("AND iidmodulo=? ");
		query.append("AND tisfechanotacargo=? ");
		query.append("AND decimporte=? ");

		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setInt(1,iidnocliente);
		stmt.setInt(2,iidusuario);
		stmt.setInt(3,iidmodulo);
		stmt.setString(4,tisfechanotacargo);
		stmt.setFloat(5,decimporte);
		rSet = stmt.executeQuery();

		if (rSet.next()) {
			this.iidnotadecargo = rSet.getInt("iidnotadecargo");
			found = true;
		}

		rSet.close();
		stmt.close();
		return found;
	}
}
