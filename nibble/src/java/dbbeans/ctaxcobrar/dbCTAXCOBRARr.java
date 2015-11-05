package dbbeans.ctaxcobrar;

import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class dbCTAXCOBRARr implements org.nibble.main.dbInterfase {
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
	private boolean bitmovsaldado;


	private java.lang.String vchTipodocto;
	private java.lang.String vchTipocontradocto;
	private java.lang.String strVchtipopago;
	private boolean bitavisoing;
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
		stmt.setBoolean(11,bitmovsaldado);
		stmt.executeUpdate();
		stmt.close();
	}
	public boolean getBitmovsaldado(){
		return this.bitmovsaldado;
	}
	public float getDecdebe(){
		return this.decdebe;
	}
	public float getDechaber(){
		return this.dechaber;
	}
	public int getIidcontradocto(){
		return this.iidcontradocto;
	}
	public int getIiddoctoorigen(){
		return this.iiddoctoorigen;
	}
	public int getIidnocliente(){
		return this.iidnocliente;
	}
	public int getTiconsecsubdocto(){
		return this.ticonsecsubdocto;
	}
	public int getTiidtipocontradoc(){
		return this.tiidtipocontradoc;
	}
	public int getTiidtipodocto(){
		return this.tiidtipodocto;
	}
/**
 * Insert the method's description here.
 * Creation date: (20/11/2002 05:50:32 p.m.)
 * @return java.lang.String
 */
public java.lang.String getTipocontradocto() {
	return vchTipocontradocto;
}
/**
 * Insert the method's description here.
 * Creation date: (20/11/2002 04:23:33 p.m.)
 * @return java.lang.String
 */
public java.lang.String getTipodocto() {
	return vchTipodocto;
}
	public String getTisfechamovimiento(){
		return this.tisfechamovimiento;
	}
	public String getTisfechavencimient(){
		return this.tisfechavencimient;
	}
/**
 * Insert the method's description here.
 * Creation date: (06/12/2002 12:50:21 p.m.)
 * @return java.lang.String
 */
public java.lang.String getVchtipopago() {
	return strVchtipopago;
}
/**
 * Insert the method's description here.
 * Creation date: (06/12/2002 05:23:25 p.m.)
 * @return boolean
 */
public boolean isBitavisoing() {
	return bitavisoing;
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
			this.bitmovsaldado = rSet.getBoolean("bitmovsaldado");
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
/**
 * Insert the method's description here.
 * Creation date: (06/12/2002 05:23:25 p.m.)
 * @param newBitavisoing boolean
 */
public void setBitavisoing(boolean newBitavisoing) {
	bitavisoing = newBitavisoing;
}
	public void setBitmovsaldado(boolean bitmovsaldado){
		this.bitmovsaldado=bitmovsaldado;
	}
	public void setConnection(java.sql.Connection conn) {
		this.conn = conn;
	}
	public void setDecdebe(float decdebe){
		this.decdebe=decdebe;
	}
	public void setDechaber(float dechaber){
		this.dechaber=dechaber;
	}
	public void setIidcontradocto(int iidcontradocto){
		this.iidcontradocto=iidcontradocto;
	}
	public void setIiddoctoorigen(int iiddoctoorigen){
		this.iiddoctoorigen=iiddoctoorigen;
	}
	public void setIidnocliente(int iidnocliente){
		this.iidnocliente=iidnocliente;
	}
	public void setTiconsecsubdocto(int ticonsecsubdocto){
		this.ticonsecsubdocto=ticonsecsubdocto;
	}
	public void setTiidtipocontradoc(int tiidtipocontradoc){
		this.tiidtipocontradoc=tiidtipocontradoc;
	}
	public void setTiidtipodocto(int tiidtipodocto){
		this.tiidtipodocto=tiidtipodocto;
	}
/**
 * Insert the method's description here.
 * Creation date: (20/11/2002 05:50:32 p.m.)
 * @param newTipocontradocto java.lang.String
 */
public void setTipocontradocto(java.lang.String newTipocontradocto) {
	vchTipocontradocto = newTipocontradocto;
}
/**
 * Insert the method's description here.
 * Creation date: (20/11/2002 04:23:33 p.m.)
 * @param newTipodocto java.lang.String
 */
public void setTipodocto(java.lang.String newTipodocto) {
	vchTipodocto = newTipodocto;
}
	public void setTisfechamovimiento(String tisfechamovimiento){
		this.tisfechamovimiento=tisfechamovimiento;
	}
	public void setTisfechavencimient(String tisfechavencimient){
		this.tisfechavencimient=tisfechavencimient;
	}
/**
 * Insert the method's description here.
 * Creation date: (06/12/2002 12:50:21 p.m.)
 * @param newVchtipopago java.lang.String
 */
public void setVchtipopago(java.lang.String newVchtipopago) {
	strVchtipopago = newVchtipopago;
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
		stmt.setBoolean(8,bitmovsaldado);
		stmt.setInt(9,iiddoctoorigen);
		stmt.setInt(10,ticonsecsubdocto);
		stmt.setInt(11,tiidtipodocto);
		stmt.executeUpdate();
		stmt.close();
	}
}
