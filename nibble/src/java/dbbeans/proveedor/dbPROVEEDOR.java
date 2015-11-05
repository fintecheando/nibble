package dbbeans.proveedor;

import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class dbPROVEEDOR implements org.nibble.main.dbInterfase {
	protected Connection conn;
	protected ResultSet rSet;
	protected PreparedStatement stmt;


	private int iidproveedor;
	private String vchnombre;
	private String vchrazonsocial;
	private String vchrfc;
	private String vchcurp;
	private boolean bcredito;
	private String vchtel1;
	private String vchtel2;
	private String vchfax;
	private boolean bitdadodebaja;
	private String vchmail;
	private boolean bitaceptabackorder;
	private String vchcolonia;
	private String vchcalle;
	private String vchcp;
	private short siidpais;
	private int iidestado;
	private int iidmunicipio;


	public void create() throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		query.append("INSERT INTO PROVEEDOR (vchnombre,vchrazonsocial,vchrfc,vchcurp,bcredito,vchtel1,vchtel2,vchfax,bitdadodebaja,vchmail,bitaceptabackorder,vchcolonia,vchcalle,vchcp,siidpais,iidestado,iidmunicipio) ");
		query.append(" VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ");
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setString(1,vchnombre);
		stmt.setString(2,vchrazonsocial);
		stmt.setString(3,vchrfc);
		stmt.setString(4,vchcurp);
		stmt.setBoolean(5,bcredito);
		stmt.setString(6,vchtel1);
		stmt.setString(7,vchtel2);
		stmt.setString(8,vchfax);
		stmt.setBoolean(9,bitdadodebaja);
		stmt.setString(10,vchmail);
		stmt.setBoolean(11,bitaceptabackorder);
		stmt.setString(12,vchcolonia);
		stmt.setString(13,vchcalle);
		stmt.setString(14,vchcp);
		stmt.setShort(15,siidpais);
		stmt.setInt(16,iidestado);
		stmt.setInt(17,iidmunicipio);
		stmt.executeUpdate();
		stmt.close();;
	}
	public boolean getBcredito(){
		return this.bcredito;
	}
	public boolean getBitaceptabackorder(){
		return this.bitaceptabackorder;
	}
	public boolean getBitdadodebaja(){
		return this.bitdadodebaja;
	}
	public int getIidestado(){
		return this.iidestado;
	}
	public int getIidmunicipio(){
		return this.iidmunicipio;
	}
	public int getIidproveedor(){
		return this.iidproveedor;
	}
	public short getSiidpais(){
		return this.siidpais;
	}
	public String getVchcalle(){
		return this.vchcalle;
	}
	public String getVchcolonia(){
		return this.vchcolonia;
	}
	public String getVchcp(){
		return this.vchcp;
	}
	public String getVchcurp(){
		return this.vchcurp;
	}
	public String getVchfax(){
		return this.vchfax;
	}
	public String getVchmail(){
		return this.vchmail;
	}
	public String getVchnombre(){
		return this.vchnombre;
	}
	public String getVchrazonsocial(){
		return this.vchrazonsocial;
	}
	public String getVchrfc(){
		return this.vchrfc;
	}
	public String getVchtel1(){
		return this.vchtel1;
	}
	public String getVchtel2(){
		return this.vchtel2;
	}
	public boolean load() throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		query.append("SELECT * ");
		query.append("FROM PROVEEDOR ");
		query.append("WHERE iidproveedor=? ");
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setInt(1,iidproveedor);
		rSet = stmt.executeQuery();


		if (rSet.next()) {
			this.vchnombre = rSet.getString("vchnombre");
			this.vchrazonsocial = rSet.getString("vchrazonsocial");
			this.vchrfc = rSet.getString("vchrfc");
			this.vchcurp = rSet.getString("vchcurp");
			this.bcredito = rSet.getBoolean("bcredito");
			this.vchtel1 = rSet.getString("vchtel1");
			this.vchtel2 = rSet.getString("vchtel2");
			this.vchfax = rSet.getString("vchfax");
			this.bitdadodebaja = rSet.getBoolean("bitdadodebaja");
			this.vchmail = rSet.getString("vchmail");
			this.bitaceptabackorder = rSet.getBoolean("bitaceptabackorder");
			this.vchcolonia = rSet.getString("vchcolonia");
			this.vchcalle = rSet.getString("vchcalle");
			this.vchcp = rSet.getString("vchcp");
			this.siidpais = rSet.getShort("siidpais");
			this.iidestado = rSet.getInt("iidestado");
			this.iidmunicipio = rSet.getInt("iidmunicipio");
		}
		else
			throw new Exception ("No se encontro el proveedor");
		rSet.close();
		stmt.close();
		return false;
	}
	public void remove() throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		query.append("DELETE FROM PROVEEDOR ");
		query.append("WHERE iidproveedor=? ");
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setInt(1,iidproveedor);
		stmt.executeUpdate();
		stmt.close();
	}
	public void setBcredito(boolean bcredito){
		this.bcredito=bcredito;
	}
	public void setBitaceptabackorder(boolean bitaceptabackorder){
		this.bitaceptabackorder=bitaceptabackorder;
	}
	public void setBitdadodebaja(boolean bitdadodebaja){
		this.bitdadodebaja=bitdadodebaja;
	}
	public void setConnection(java.sql.Connection conn) {
		this.conn = conn;
	}
	public void setIidestado(int iidestado){
		this.iidestado=iidestado;
	}
	public void setIidmunicipio(int iidmunicipio){
		this.iidmunicipio=iidmunicipio;
	}
	public void setIidproveedor(int iidproveedor){
		this.iidproveedor=iidproveedor;
	}
	public void setSiidpais(short siidpais){
		this.siidpais=siidpais;
	}
	public void setVchcalle(String vchcalle){
		this.vchcalle=vchcalle;
	}
	public void setVchcolonia(String vchcolonia){
		this.vchcolonia=vchcolonia;
	}
	public void setVchcp(String vchcp){
		this.vchcp=vchcp;
	}
	public void setVchcurp(String vchcurp){
		this.vchcurp=vchcurp;
	}
	public void setVchfax(String vchfax){
		this.vchfax=vchfax;
	}
	public void setVchmail(String vchmail){
		this.vchmail=vchmail;
	}
	public void setVchnombre(String vchnombre){
		this.vchnombre=vchnombre;
	}
	public void setVchrazonsocial(String vchrazonsocial){
		this.vchrazonsocial=vchrazonsocial;
	}
	public void setVchrfc(String vchrfc){
		this.vchrfc=vchrfc;
	}
	public void setVchtel1(String vchtel1){
		this.vchtel1=vchtel1;
	}
	public void setVchtel2(String vchtel2){
		this.vchtel2=vchtel2;
	}
	public void store() throws SQLException {
		StringBuffer query = new StringBuffer();
		query.append("UPDATE PROVEEDOR SET vchnombre=? , vchrazonsocial=? , vchrfc=? , vchcurp=? , bcredito=? , vchtel1=? , vchtel2=? , vchfax=? , bitdadodebaja=? , vchmail=? , bitaceptabackorder=? , vchcolonia=? , vchcalle=? , vchcp=? , siidpais=? , iidestado=? , iidmunicipio=? ");
		query.append("WHERE iidproveedor=? ");
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setString(1,vchnombre);
		stmt.setString(2,vchrazonsocial);
		stmt.setString(3,vchrfc);
		stmt.setString(4,vchcurp);
		stmt.setBoolean(5,bcredito);
		stmt.setString(6,vchtel1);
		stmt.setString(7,vchtel2);
		stmt.setString(8,vchfax);
		stmt.setBoolean(9,bitdadodebaja);
		stmt.setString(10,vchmail);
		stmt.setBoolean(11,bitaceptabackorder);
		stmt.setString(12,vchcolonia);
		stmt.setString(13,vchcalle);
		stmt.setString(14,vchcp);
		stmt.setShort(15,siidpais);
		stmt.setInt(16,iidestado);
		stmt.setInt(17,iidmunicipio);
		stmt.setInt(18,iidproveedor);
		stmt.executeUpdate();
		stmt.close();
	}
}
