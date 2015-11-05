/** java bean entity **/
package dbbeans.notadecredito;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class dbNOTADECREDITO implements org.nibble.main.dbInterfase {
	private Connection conn;
	private ResultSet rSet;
	private PreparedStatement stmt;


	private int iidnotacredito;
	private int iidnocliente;
	private int iidusuario;
	private int iidfactura;
	private String vchletra;
	private int inumero;
	private String tisfechanotacredit;
	private String vchconcepto;
	private float decimporte;
	private float decivaimporte;
	private int titipomov;
	private float decporcdesc;
	private float decmontodesc;


	public void create() throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		query.append("INSERT INTO NOTADECREDITO (iidnotacredito,iidnocliente,iidusuario,iidfactura,vchletra,inumero,tisfechanotacredit,vchconcepto,decimporte,decivaimporte,titipomov,decporcdesc,decmontodesc) ");
		query.append(" VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?) ");
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setInt(1,iidnotacredito);
		stmt.setInt(2,iidnocliente);
		stmt.setInt(3,iidusuario);
		stmt.setInt(4,iidfactura);
		stmt.setString(5,vchletra);
		stmt.setInt(6,inumero);
		stmt.setString(7,tisfechanotacredit);
		stmt.setString(8,vchconcepto);
		stmt.setFloat(9,decimporte);
		stmt.setFloat(10,decivaimporte);
		stmt.setInt(11,titipomov);
		stmt.setFloat(12,decporcdesc);
		stmt.setFloat(13,decmontodesc);
		stmt.executeUpdate();
		stmt.close();;
	}
	public boolean load() throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		query.append("SELECT * ");
		query.append("FROM NOTADECREDITO ");
		query.append("WHERE iidnotacredito=? ");
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setInt(1,iidnotacredito);
		rSet = stmt.executeQuery();


		if (rSet.next()) {
			this.iidnocliente = rSet.getInt("iidnocliente");
			this.iidusuario = rSet.getInt("iidusuario");
			this.iidfactura = rSet.getInt("iidfactura");
			this.vchletra = rSet.getString("vchletra");
			this.inumero = rSet.getInt("inumero");
			this.tisfechanotacredit = rSet.getString("tisfechanotacredit");
			this.vchconcepto = rSet.getString("vchconcepto");
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
		query.append("DELETE FROM NOTADECREDITO ");
		query.append("WHERE iidnotacredito=? ");
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setInt(1,iidnotacredito);
		stmt.executeUpdate();
		stmt.close();
	}
	public void store() throws SQLException {
		StringBuffer query = new StringBuffer();
		query.append("UPDATE NOTADECREDITO SET iidnocliente=? , iidusuario=? , iidfactura=? , vchletra=? , inumero=? , tisfechanotacredit=? , vchconcepto=? , decimporte=? , decivaimporte=? , titipomov=? , decporcdesc=? , decmontodesc=? ");
		query.append("WHERE iidnotacredito=? ");
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setInt(1,iidnocliente);
		stmt.setInt(2,iidusuario);
		stmt.setInt(3,iidfactura);
		stmt.setString(4,vchletra);
		stmt.setInt(5,inumero);
		stmt.setString(6,tisfechanotacredit);
		stmt.setString(7,vchconcepto);
		stmt.setFloat(8,decimporte);
		stmt.setFloat(9,decivaimporte);
		stmt.setInt(10,titipomov);
		stmt.setFloat(11,decporcdesc);
		stmt.setFloat(12,decmontodesc);
		stmt.setInt(13,iidnotacredito);
		stmt.executeUpdate();
		stmt.close();
	}
	public void setConnection(java.sql.Connection conn) {
		this.conn = conn;
	}
	public void setIidnotacredito(int iidnotacredito){
		this.iidnotacredito=iidnotacredito;
	}
	public int getIidnotacredito(){
		return this.iidnotacredito;
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
	public void setTisfechanotacredit(String tisfechanotacredit){
		this.tisfechanotacredit=tisfechanotacredit;
	}
	public String getTisfechanotacredit(){
		return this.tisfechanotacredit;
	}
	public void setVchconcepto(String vchconcepto){
		this.vchconcepto=vchconcepto;
	}
	public String getVchconcepto(){
		return this.vchconcepto;
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

	public void create2() throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		query.append("INSERT INTO NOTADECREDITO (iidnocliente,iidusuario,iidfactura,vchletra,inumero,tisfechanotacredit,vchconcepto,decimporte,decivaimporte,titipomov,decporcdesc,decmontodesc) ");
		query.append(" VALUES(?,?,?,?,?,?,?,?,?,?,?,?) ");
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setInt(1,iidnocliente);
		stmt.setInt(2,iidusuario);
		stmt.setInt(3,iidfactura);
		stmt.setString(4,vchletra);
		stmt.setInt(5,inumero);
		stmt.setString(6,tisfechanotacredit);
		stmt.setString(7,vchconcepto);
		stmt.setFloat(8,decimporte);
		stmt.setFloat(9,decivaimporte);
		stmt.setInt(10,titipomov);
		stmt.setFloat(11,decporcdesc);
		stmt.setFloat(12,decmontodesc);
		stmt.executeUpdate();
		stmt.close();;
	}

	public boolean findIidnotacredito() throws Exception, java.sql.SQLException {
		boolean found = false;
		StringBuffer query = new StringBuffer();
		query.append("SELECT * FROM NOTADECREDITO ");
		query.append("WHERE iidnocliente=? ");
		query.append("AND iidusuario=? ");
		query.append("AND iidfactura=? ");
		query.append("AND tisfechanotacredit=?");
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		
		stmt.setInt(1,iidnocliente);
		stmt.setInt(2,iidusuario);
		stmt.setInt(3,iidfactura);
		stmt.setString(4,tisfechanotacredit);
		rSet = stmt.executeQuery();

		if (rSet.next()) {
			found = true;
			
			this.iidnotacredito = rSet.getInt("iidnotacredito");
			this.iidnocliente = rSet.getInt("iidnocliente");
			this.iidusuario = rSet.getInt("iidusuario");
			this.iidfactura = rSet.getInt("iidfactura");
			this.vchletra = rSet.getString("vchletra");
			this.inumero = rSet.getInt("inumero");
			this.tisfechanotacredit = rSet.getString("tisfechanotacredit");
			this.vchconcepto = rSet.getString("vchconcepto");
			this.decimporte = rSet.getFloat("decimporte");
			this.decivaimporte = rSet.getFloat("decivaimporte");
			this.titipomov = rSet.getInt("titipomov");
			this.decporcdesc = rSet.getFloat("decporcdesc");
			this.decmontodesc = rSet.getFloat("decmontodesc");
		}
		rSet.close();
		stmt.close();
		return found;
	}
}
