package dbbeans.menu;

import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class dbMENU implements org.nibble.main.dbInterfase {
	private Connection conn;
	private ResultSet rSet;
	private PreparedStatement stmt;


	private int iidmenu;
	private short iidsistema;
	private String descripcion;
	private String url;
	private short nivel;
	private String imagen;
	private short smenu;


	private int ancho;
	public void create() throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		query.append("INSERT INTO MENU (iidmenu,iidsistema,descripcion,url,nivel,imagen,smenu,ancho) ");
		query.append(" VALUES(?,?,?,?,?,?,?,?) ");
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setInt(1,iidmenu);
		stmt.setShort(2,iidsistema);
		stmt.setString(3,descripcion);
		stmt.setString(4,url);
		stmt.setShort(5,nivel);
		stmt.setString(6,imagen);
		stmt.setShort(7,smenu);
		stmt.setInt(8,ancho);		
		stmt.executeUpdate();
		stmt.close();;
	}
/**
 * Insert the method's description here.
 * Creation date: (02/01/2003 04:12:24 p.m.)
 * @return int
 */
public int getAncho() {
	return ancho;
}
	public String getDescripcion(){
		return this.descripcion;
	}
	public int getIidmenu(){
		return this.iidmenu;
	}
	public short getIidsistema(){
		return this.iidsistema;
	}
	public String getImagen(){
		return this.imagen;
	}
	public short getNivel(){
		return this.nivel;
	}
	public short getSmenu(){
		return this.smenu;
	}
	public String getUrl(){
		return this.url;
	}
	public boolean load() throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		query.append("SELECT * ");
		query.append("FROM MENU ");
		query.append("WHERE iidmenu=?  and iidsistema=? ");
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setInt(1,iidmenu);
		stmt.setShort(2,iidsistema);
		rSet = stmt.executeQuery();


		if (rSet.next()) {
			this.descripcion = rSet.getString("descripcion");
			this.url = rSet.getString("url");
			this.nivel = rSet.getShort("nivel");
			this.imagen = rSet.getString("imagen");
			this.smenu = rSet.getShort("smenu");
			this.ancho = rSet.getInt("ancho");
		}
		else
			throw new Exception ("No se encontro el registro");
		rSet.close();
		stmt.close();
		return false;
	}
	public void remove() throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		query.append("DELETE FROM MENU ");
		query.append("WHERE iidmenu=?  and iidsistema=? ");
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setInt(1,iidmenu);
		stmt.setShort(2,iidsistema);
		stmt.executeUpdate();
		stmt.close();
	}
/**
 * Insert the method's description here.
 * Creation date: (02/01/2003 04:12:24 p.m.)
 * @param newAncho int
 */
public void setAncho(int newAncho) {
	ancho = newAncho;
}
	public void setConnection(java.sql.Connection conn) {
		this.conn = conn;
	}
	public void setDescripcion(String descripcion){
		this.descripcion=descripcion;
	}
	public void setIidmenu(int iidmenu){
		this.iidmenu=iidmenu;
	}
	public void setIidsistema(short iidsistema){
		this.iidsistema=iidsistema;
	}
	public void setImagen(String imagen){
		this.imagen=imagen;
	}
	public void setNivel(short nivel){
		this.nivel=nivel;
	}
	public void setSmenu(short smenu){
		this.smenu=smenu;
	}
	public void setUrl(String url){
		this.url=url;
	}
	public void store() throws SQLException {
		StringBuffer query = new StringBuffer();
		query.append("UPDATE MENU SET descripcion=? , url=? , nivel=? , imagen=? , smenu=? , ancho=? ");
		query.append("WHERE iidmenu=?  and iidsistema=? ");
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setString(1,descripcion);
		stmt.setString(2,url);
		stmt.setShort(3,nivel);
		stmt.setString(4,imagen);
		stmt.setShort(5,smenu);
		stmt.setInt(6,ancho);				
		stmt.setInt(7,iidmenu);
		stmt.setShort(8,iidsistema);
		stmt.executeUpdate();
		stmt.close();
	}
}
