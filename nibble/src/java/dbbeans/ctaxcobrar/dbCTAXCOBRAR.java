/** java bean entity **/
package dbbeans.ctaxcobrar;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class dbCTAXCOBRAR implements org.nibble.main.dbInterfase {
	private Connection conn;
	private ResultSet rSet;
	private PreparedStatement stmt;


	private int iiddoctoorigen;
	private int ticonsecsubdocto;
	private int tiidtipodocto;
	private int iidnocliente;
	private String tisfechamovimiento;
	private String tisfechavencimient;
	private int iidcontradocto;
	private int tiidtipocontradoc;
	private float decdebe;
	private float dechaber;
	//private boolean bitmovsaldado;
	private int bitmovsaldado;


	public void create() throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		query.append("INSERT INTO CTAXCOBRAR (iiddoctoorigen,ticonsecsubdocto,tiidtipodocto,iidnocliente,tisfechamovimiento,tisfechavencimient,iidcontradocto,tiidtipocontradoc,decdebe,dechaber,bitmovsaldado) ");
		query.append(" VALUES(?,?,?,?,?,?,?,?,?,?,?) ");
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setInt(1,iiddoctoorigen);
		stmt.setInt(2,ticonsecsubdocto);
		stmt.setInt(3,tiidtipodocto);
		stmt.setInt(4,iidnocliente);
		stmt.setString(5,tisfechamovimiento);
		stmt.setString(6,tisfechavencimient);
		stmt.setInt(7,iidcontradocto);
		stmt.setInt(8,tiidtipocontradoc);
		stmt.setFloat(9,decdebe);
		stmt.setFloat(10,dechaber);
		//stmt.setBoolean(11,bitmovsaldado);
		stmt.setInt(11,bitmovsaldado);
		stmt.executeUpdate();
		stmt.close();
	}
	public boolean load() throws Exception, java.sql.SQLException {
		boolean found = false;
		StringBuffer query = new StringBuffer();
		query.append("SELECT * ");
		query.append("FROM CTAXCOBRAR ");
		query.append("WHERE iiddoctoorigen=?  and ticonsecsubdocto=?  and tiidtipodocto=? ");
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setInt(1,iiddoctoorigen);
		stmt.setInt(2,ticonsecsubdocto);
		stmt.setInt(3,tiidtipodocto);
		rSet = stmt.executeQuery();


		if (rSet.next()) {
			found = true;
			this.iidnocliente = rSet.getInt("iidnocliente");
			this.tisfechamovimiento = rSet.getString("tisfechamovimiento");
			this.tisfechavencimient = rSet.getString("tisfechavencimient");
			this.iidcontradocto = rSet.getInt("iidcontradocto");
			this.tiidtipocontradoc = rSet.getInt("tiidtipocontradoc");
			this.decdebe = rSet.getFloat("decdebe");
			this.dechaber = rSet.getFloat("dechaber");
			//this.bitmovsaldado = rSet.getBoolean("bitmovsaldado");
			this.bitmovsaldado = rSet.getInt("bitmovsaldado");
		}
		else
			throw new Exception ("No se encontro el registro");
		rSet.close();
		stmt.close();
		return found;
	}
		
	public void remove() throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		query.append("DELETE FROM CTAXCOBRAR ");
		query.append("WHERE iiddoctoorigen=?  and ticonsecsubdocto=?  and tiidtipodocto=? ");
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setInt(1,iiddoctoorigen);
		stmt.setInt(2,ticonsecsubdocto);
		stmt.setInt(3,tiidtipodocto);
		stmt.executeUpdate();
		stmt.close();
	}
	
	public int findmaxconsec() throws Exception, java.sql.SQLException{
		int result = 0;
		StringBuffer query = new StringBuffer();
		query.append("SELECT MAX(ticonsecsubdocto) ");
		query.append("FROM CTAXCOBRAR ");
		query.append("WHERE iiddoctoorigen=? and tiidtipodocto=? ");
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setInt(1,iiddoctoorigen);
		stmt.setInt(2,tiidtipodocto);
		rSet = stmt.executeQuery();

		if (rSet.next()) {
			result = rSet.getInt(1);
		}
		rSet.close();
		stmt.close();
		return result;
	}
	
	public void store() throws SQLException {
		StringBuffer query = new StringBuffer();
		query.append("UPDATE CTAXCOBRAR SET iidnocliente=? , tisfechamovimiento=? , tisfechavencimient=? , iidcontradocto=? , tiidtipocontradoc=? , decdebe=? , dechaber=? , bitmovsaldado=? ");
		query.append("WHERE iiddoctoorigen=?  and ticonsecsubdocto=?  and tiidtipodocto=? ");
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setInt(1,iidnocliente);
		stmt.setString(2,tisfechamovimiento);
		stmt.setString(3,tisfechavencimient);
		stmt.setInt(4,iidcontradocto);
		stmt.setInt(5,tiidtipocontradoc);
		stmt.setFloat(6,decdebe);
		stmt.setFloat(7,dechaber);
		//stmt.setBoolean(8,bitmovsaldado);
		stmt.setInt(8,bitmovsaldado);
		stmt.setInt(9,iiddoctoorigen);
		stmt.setInt(10,ticonsecsubdocto);
		stmt.setInt(11,tiidtipodocto);
		stmt.executeUpdate();
		stmt.close();
	}
	
	
	
	public void setConnection(java.sql.Connection conn) {
		this.conn = conn;
	}
	public void setIiddoctoorigen(int iiddoctoorigen){
		this.iiddoctoorigen=iiddoctoorigen;
	}
	public int getIiddoctoorigen(){
		return this.iiddoctoorigen;
	}
	public void setTiconsecsubdocto(int ticonsecsubdocto){
		this.ticonsecsubdocto=ticonsecsubdocto;
	}
	public int getTiconsecsubdocto(){
		return this.ticonsecsubdocto;
	}
	public void setTiidtipodocto(int tiidtipodocto){
		this.tiidtipodocto=tiidtipodocto;
	}
	public int getTiidtipodocto(){
		return this.tiidtipodocto;
	}
	public void setIidnocliente(int iidnocliente){
		this.iidnocliente=iidnocliente;
	}
	public int getIidnocliente(){
		return this.iidnocliente;
	}
	public void setTisfechamovimiento(String tisfechamovimiento){
		this.tisfechamovimiento=tisfechamovimiento;
	}
	public String getTisfechamovimiento(){
		return this.tisfechamovimiento;
	}
	public void setTisfechavencimient(String tisfechavencimient){
		this.tisfechavencimient=tisfechavencimient;
	}
	public String getTisfechavencimient(){
		return this.tisfechavencimient;
	}
	public void setIidcontradocto(int iidcontradocto){
		this.iidcontradocto=iidcontradocto;
	}
	public int getIidcontradocto(){
		return this.iidcontradocto;
	}
	public void setTiidtipocontradoc(int tiidtipocontradoc){
		this.tiidtipocontradoc=tiidtipocontradoc;
	}
	public int getTiidtipocontradoc(){
		return this.tiidtipocontradoc;
	}
	public void setDecdebe(float decdebe){
		this.decdebe=decdebe;
	}
	public float getDecdebe(){
		return this.decdebe;
	}
	public void setDechaber(float dechaber){
		this.dechaber=dechaber;
	}
	public float getDechaber(){
		return this.dechaber;
	}
	public void setBitmovsaldado(int bitmovsaldado){
	//public void setBitmovsaldado(boolean bitmovsaldado){	
		this.bitmovsaldado=bitmovsaldado;
	}
	//public boolean getBitmovsaldado()
	public int getBitmovsaldado(){	
		return this.bitmovsaldado;
	}
}
