/** java bean entity **/
package dbbeans.notadecambio;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class dbNOTADECAMBIO implements org.nibble.main.dbInterfase {
	private Connection conn;
	private ResultSet rSet;
	private PreparedStatement stmt;


	private int iidnotacambio;
	private int iidnocliente;
	private int iidusuario;
	private int iidfactura;
	private String tisfechanotacambio;
	private float decimporte;
	private float decivaimporte;
	private int titipomov;
	private float decporcdesc;
	private float decmontodesc;


	public void create() throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		query.append("INSERT INTO NOTADECAMBIO (iidnotacambio,iidnocliente,iidusuario,iidfactura,tisfechanotacambio,decimporte,decivaimporte,titipomov,decporcdesc,decmontodesc) ");
		query.append(" VALUES(?,?,?,?,?,?,?,?,?,?) ");
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setInt(1,iidnotacambio);
		stmt.setInt(2,iidnocliente);
		stmt.setInt(3,iidusuario);
		stmt.setInt(4,iidfactura);
		stmt.setString(5,tisfechanotacambio);
		stmt.setFloat(6,decimporte);
		stmt.setFloat(7,decivaimporte);
		stmt.setInt(8,titipomov);
		stmt.setFloat(9,decporcdesc);
		stmt.setFloat(10,decmontodesc);
		stmt.executeUpdate();
		stmt.close();;
	}
	public boolean load() throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		query.append("SELECT * ");
		query.append("FROM NOTADECAMBIO ");
		query.append("WHERE iidnotacambio=? ");
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setInt(1,iidnotacambio);
		rSet = stmt.executeQuery();


		if (rSet.next()) {
			this.iidnocliente = rSet.getInt("iidnocliente");
			this.iidusuario = rSet.getInt("iidusuario");
			this.iidfactura = rSet.getInt("iidfactura");
			this.tisfechanotacambio = rSet.getString("tisfechanotacambio");
			this.decimporte = rSet.getFloat("decimporte");
			this.decivaimporte = rSet.getFloat("decivaimporte");
			this.titipomov = rSet.getInt("titipomov");
			this.decporcdesc = rSet.getFloat("decporcdesc");
			this.decmontodesc = rSet.getFloat("decmontodesc");
		}
		else
			throw new Exception ("No se encontro el registro");
		rSet.close();
		stmt.close();
		return false;
	}
	public void remove() throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		query.append("DELETE FROM NOTADECAMBIO ");
		query.append("WHERE iidnotacambio=? ");
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setInt(1,iidnotacambio);
		stmt.executeUpdate();
		stmt.close();
	}
	public void store() throws SQLException {
		StringBuffer query = new StringBuffer();
		query.append("UPDATE NOTADECAMBIO SET iidnocliente=? , iidusuario=? , iidfactura=? , tisfechanotacambio=? , decimporte=? , decivaimporte=? , titipomov=? , decporcdesc=? , decmontodesc=? ");
		query.append("WHERE iidnotacambio=? ");
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setInt(1,iidnocliente);
		stmt.setInt(2,iidusuario);
		stmt.setInt(3,iidfactura);
		stmt.setString(4,tisfechanotacambio);
		stmt.setFloat(5,decimporte);
		stmt.setFloat(6,decivaimporte);
		stmt.setInt(7,titipomov);
		stmt.setFloat(8,decporcdesc);
		stmt.setFloat(9,decmontodesc);
		stmt.setInt(10,iidnotacambio);
		stmt.executeUpdate();
		stmt.close();
	}
	public void setConnection(java.sql.Connection conn) {
		this.conn = conn;
	}
	public void setIidnotacambio(int iidnotacambio){
		this.iidnotacambio=iidnotacambio;
	}
	public int getIidnotacambio(){
		return this.iidnotacambio;
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
	public void setIidfactura(int iidfactura){
		this.iidfactura=iidfactura;
	}
	public int getIidfactura(){
		return this.iidfactura;
	}
	public void setTisfechanotacambio(String tisfechanotacambio){
		this.tisfechanotacambio=tisfechanotacambio;
	}
	public String getTisfechanotacambio(){
		return this.tisfechanotacambio;
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
	public void setTitipomov(int titipomov){
		this.titipomov=titipomov;
	}
	public int getTitipomov(){
		return this.titipomov;
	}
	public void setDecporcdesc(float decporcdesc){
		this.decporcdesc=decporcdesc;
	}
	public float getDecporcdesc(){
		return this.decporcdesc;
	}
	public void setDecmontodesc(float decmontodesc){
		this.decmontodesc=decmontodesc;
	}
	public float getDecmontodesc(){
		return this.decmontodesc;
	}
}
