package dbbeans.promocion;

import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class dbPROMOCION implements org.nibble.main.dbInterfase {
	private Connection conn;
	private ResultSet rSet;
	private PreparedStatement stmt;

	private int iidpromocion;
	private int iidcategoria;
	private int iidgrupo;
	private int iidlinea;
	private int iidmarca;
	private float decvolumencompra;
	private short sinopiezas;
	private int iexistencias;
	private String vchdescripcion;
	private String tisfechainicio;
	private String tisfechafin;

	public void create() throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		query.append("INSERT INTO PROMOCION (iidcategoria,iidgrupo,iidlinea,iidmarca,decvolumencompra,sinopiezas,iexistencias,vchdescripcion,tisfechainicio,tisfechafin) ");
		query.append(" VALUES(?,?,?,?,?,?,?,?,?,?) ");
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setInt(1,iidcategoria);
		stmt.setInt(2,iidgrupo);
		stmt.setInt(3,iidlinea);
		stmt.setInt(4,iidmarca);
		stmt.setFloat(5,decvolumencompra);
		stmt.setShort(6,sinopiezas);
		stmt.setInt(7,iexistencias);
		stmt.setString(8,vchdescripcion);
		stmt.setString(9,tisfechainicio);
		stmt.setString(10,tisfechafin);
		stmt.executeUpdate();
		stmt.close();
	}
	public float getDecvolumencompra(){
		return this.decvolumencompra;
	}
	public int getIexistencias(){
		return this.iexistencias;
	}
	public int getIidcategoria(){
		return this.iidcategoria;
	}
	public int getIidgrupo(){
		return this.iidgrupo;
	}
	public int getIidlinea(){
		return this.iidlinea;
	}
	public int getIidmarca(){
		return this.iidmarca;
	}
	public int getIidpromocion(){
		return this.iidpromocion;
	}
	public short getSinopiezas(){
		return this.sinopiezas;
	}
	public String getTisfechafin(){
		return this.tisfechafin;
	}
	public String getTisfechainicio(){
		return this.tisfechainicio;
	}
	public String getVchdescripcion(){
		return this.vchdescripcion;
	}
	public boolean load() throws Exception, java.sql.SQLException {

		boolean found = false;
		StringBuffer query = new StringBuffer();
		query.append(" SELECT ");  
		query.append(" iidcategoria, ");
		query.append(" iidgrupo, ");
		query.append(" iidlinea, ");
		query.append(" iidmarca, ");
		query.append(" decvolumencompra, ");
		query.append(" sinopiezas, ");
		query.append(" iexistencias, ");
		query.append(" vchdescripcion, ");
		query.append(" DATE_FORMAT(tisfechainicio, '%d/%m/%Y') AS tisfechainicio , "); 
		query.append(" DATE_FORMAT(tisfechafin, '%d/%m/%Y') AS tisfechafin " );
		query.append(" FROM promocion ");
		query.append("WHERE iidpromocion=? ");
		/*
		 query.append("SELECT * FROM PROMOCION ");
		query.append("WHERE iidpromocion=? ");
		*/
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setInt(1,iidpromocion);
		rSet = stmt.executeQuery();


		if (rSet.next()) {
			found = true;
			this.iidcategoria = rSet.getInt("iidcategoria");
			this.iidgrupo = rSet.getInt("iidgrupo");
			this.iidlinea = rSet.getInt("iidlinea");
			this.iidmarca = rSet.getInt("iidmarca");
			this.decvolumencompra = rSet.getFloat("decvolumencompra");
			this.sinopiezas = rSet.getShort("sinopiezas");
			this.iexistencias = rSet.getInt("iexistencias");
			this.vchdescripcion = rSet.getString("vchdescripcion");
			this.tisfechainicio = rSet.getString("tisfechainicio");
			this.tisfechafin = rSet.getString("tisfechafin");
		}
		rSet.close();
		stmt.close();
		return found;
	}
	public void remove() throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		query.append("DELETE FROM PROMOCION ");
		query.append("WHERE iidpromocion=? ");
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setInt(1,iidpromocion);
		stmt.executeUpdate();
		stmt.close();
	}
	public void setConnection(java.sql.Connection conn) {
		this.conn = conn;
	}
	public void setDecvolumencompra(float decvolumencompra){
		this.decvolumencompra=decvolumencompra;
	}
	public void setIexistencias(int iexistencias){
		this.iexistencias=iexistencias;
	}
	public void setIidcategoria(int iidcategoria){
		this.iidcategoria=iidcategoria;
	}
	public void setIidgrupo(int iidgrupo){
		this.iidgrupo=iidgrupo;
	}
	public void setIidlinea(int iidlinea){
		this.iidlinea=iidlinea;
	}
	public void setIidmarca(int iidmarca){
		this.iidmarca=iidmarca;
	}
	public void setIidpromocion(int iidpromocion){
		this.iidpromocion=iidpromocion;
	}
	public void setSinopiezas(short sinopiezas){
		this.sinopiezas=sinopiezas;
	}
	public void setTisfechafin(String tisfechafin){
		this.tisfechafin=tisfechafin;
	}
	public void setTisfechainicio(String tisfechainicio){
		this.tisfechainicio=tisfechainicio;
	}	
	
	public void setVchdescripcion(String vchdescripcion){
		this.vchdescripcion=vchdescripcion;
	}
	public void store() throws SQLException {
		StringBuffer query = new StringBuffer();
		query.append("UPDATE PROMOCION SET iidcategoria=? , iidgrupo=? , iidlinea=? , iidmarca=? , decvolumencompra=? , sinopiezas=? , iexistencias=? , vchdescripcion=? , tisfechainicio=? , tisfechafin=? ");
		query.append("WHERE iidpromocion=? ");
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setInt(1,iidcategoria);
		stmt.setInt(2,iidgrupo);
		stmt.setInt(3,iidlinea);
		stmt.setInt(4,iidmarca);
		stmt.setFloat(5,decvolumencompra);
		stmt.setShort(6,sinopiezas);
		stmt.setInt(7,iexistencias);
		stmt.setString(8,vchdescripcion);
		stmt.setString(9,tisfechainicio);
		stmt.setString(10,tisfechafin);
		stmt.setInt(11,iidpromocion);
		stmt.executeUpdate();
		stmt.close();
	}
}
