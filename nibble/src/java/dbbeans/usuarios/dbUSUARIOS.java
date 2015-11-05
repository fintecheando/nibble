/** java bean entity **/
package dbbeans.usuarios;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class dbUSUARIOS implements org.nibble.main.dbInterfase {
	private Connection conn;
	private ResultSet rSet;
	private PreparedStatement stmt;


	private int iidusuario;
	private int iidsistema;
	private String vchnombreusuario;
	private String vchaliasusuario;
	private String vchdescripcion;
	private String vchpasaporte;
	private int iidgrupo;
	private String chactivo;
	private String vchpreferencias;
	private int inumticks;


	public void create() throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		query.append("INSERT INTO USUARIOS (iidsistema,vchnombreusuario,vchaliasusuario,vchdescripcion,vchpasaporte,iidgrupo,chactivo,vchpreferencias,inumticks) ");
		query.append(" VALUES(?,?,?,?,?,?,?,?,?) ");
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setInt(1,iidsistema);
		stmt.setString(2,vchnombreusuario);
		stmt.setString(3,vchaliasusuario);
		stmt.setString(4,vchdescripcion);
		stmt.setString(5,vchpasaporte);
		stmt.setInt(6,iidgrupo);
		stmt.setString(7,chactivo);
		stmt.setString(8,vchpreferencias);
		stmt.setInt(9,inumticks);
		stmt.executeUpdate();
		stmt.close();
	}
	public boolean load() throws Exception, java.sql.SQLException {

		boolean found = false;
		StringBuffer query = new StringBuffer();
		query.append("SELECT * ");
		query.append("FROM USUARIOS ");
		query.append("WHERE iidusuario=?  and iidsistema=? ");
		stmt = conn.prepareStatement(query.toString().toLowerCase().toLowerCase());
		stmt.setInt(1,iidusuario);
		stmt.setInt(2,iidsistema);
		rSet = stmt.executeQuery();


		if (rSet.next()) {
			found = true;
			this.vchnombreusuario = rSet.getString("vchnombreusuario");
			this.vchaliasusuario = rSet.getString("vchaliasusuario");
			this.vchdescripcion = rSet.getString("vchdescripcion");
			this.vchpasaporte = rSet.getString("vchpasaporte");
			this.iidgrupo = rSet.getInt("iidgrupo");
			this.chactivo = rSet.getString("chactivo");
			this.vchpreferencias = rSet.getString("vchpreferencias");
			this.inumticks = rSet.getInt("inumticks");
		}
		rSet.close();
		stmt.close();
		return found;
	}
	public void remove() throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		query.append("DELETE FROM USUARIOS ");
		query.append("WHERE iidusuario=?  and iidsistema=? ");
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setInt(1,iidusuario);
		stmt.setInt(2,iidsistema);
		stmt.executeUpdate();
		stmt.close();
	}
	public void store() throws SQLException {
		StringBuffer query = new StringBuffer();
		query.append("UPDATE USUARIOS SET vchnombreusuario=? , vchaliasusuario=? , vchdescripcion=? , vchpasaporte=? , iidgrupo=? , chactivo=? , vchpreferencias=? , inumticks=? ");
		query.append("WHERE iidusuario=?  and iidsistema=? ");
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setString(1,vchnombreusuario);
		stmt.setString(2,vchaliasusuario);
		stmt.setString(3,vchdescripcion);
		stmt.setString(4,vchpasaporte);
		stmt.setInt(5,iidgrupo);
		stmt.setString(6,chactivo);
		stmt.setString(7,vchpreferencias);
		stmt.setInt(8,inumticks);
		stmt.setInt(9,iidusuario);
		stmt.setInt(10,iidsistema);
		stmt.executeUpdate();
		stmt.close();
	}
	public void setConnection(java.sql.Connection conn) {
		this.conn = conn;
	}
	public void setIidusuario(int iidusuario){
		this.iidusuario=iidusuario;
	}
	public int getIidusuario(){
		return this.iidusuario;
	}
	public void setIidsistema(int iidsistema){
		this.iidsistema=iidsistema;
	}
	public int getIidsistema(){
		return this.iidsistema;
	}
	public void setVchnombreusuario(String vchnombreusuario){
		this.vchnombreusuario=vchnombreusuario;
	}
	public String getVchnombreusuario(){
		return this.vchnombreusuario;
	}
	public void setVchaliasusuario(String vchaliasusuario){
		this.vchaliasusuario=vchaliasusuario;
	}
	public String getVchaliasusuario(){
		return this.vchaliasusuario;
	}
	public void setVchdescripcion(String vchdescripcion){
		this.vchdescripcion=vchdescripcion;
	}
	public String getVchdescripcion(){
		return this.vchdescripcion;
	}
	public void setVchpasaporte(String vchpasaporte){
		this.vchpasaporte=vchpasaporte;
	}
	public String getVchpasaporte(){
		return this.vchpasaporte;
	}
	public void setIidgrupo(int iidgrupo){
		this.iidgrupo=iidgrupo;
	}
	public int getIidgrupo(){
		return this.iidgrupo;
	}
	public void setChactivo(String chactivo){
		this.chactivo=chactivo;
	}
	public String getChactivo(){
		return this.chactivo;
	}
	public void setVchpreferencias(String vchpreferencias){
		this.vchpreferencias=vchpreferencias;
	}
	public String getVchpreferencias(){
		return this.vchpreferencias;
	}
	public void setInumticks(int inumticks){
		this.inumticks=inumticks;
	}
	public int getInumticks(){
		return this.inumticks;
	}

	public boolean findByAlias() throws Exception, java.sql.SQLException {

		boolean found = false;
		StringBuffer query = new StringBuffer();
		query.append("SELECT * ");
		query.append("FROM USUARIOS ");
		query.append("WHERE vchaliasusuario=?  and iidsistema=? ");
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setString(1,vchaliasusuario);
		stmt.setInt(2,iidsistema);
		rSet = stmt.executeQuery();


		if (rSet.next()) {
			found = true;
			this.iidusuario = rSet.getInt("iidusuario");
			this.vchnombreusuario = rSet.getString("vchnombreusuario");
			this.vchaliasusuario = rSet.getString("vchaliasusuario");
			this.vchdescripcion = rSet.getString("vchdescripcion");
			this.vchpasaporte = rSet.getString("vchpasaporte");
			this.iidgrupo = rSet.getInt("iidgrupo");
			this.chactivo = rSet.getString("chactivo");
			this.vchpreferencias = rSet.getString("vchpreferencias");
			this.inumticks = rSet.getInt("inumticks");
		}
		rSet.close();
		stmt.close();
		return found;
	}

//Agrego este metodo modificando el find by a un solo parametro

  /*public boolean findByAliasNombre() throws Exception, java.sql.SQLException {

		  boolean found = false;
		  StringBuffer query = new StringBuffer();
		  query.append("SELECT * ");
		  query.append("FROM USUARIOS ");
		  query.append("WHERE vchaliasusuario like '?'  ");
		  stmt = conn.prepareStatement(query.toString().toLowerCase());
		  stmt.setString(1,vchaliasusuario);
		  stmt.setInt(2,iidsistema);
		  rSet = stmt.executeQuery();


		  if (rSet.next()) {
			  found = true;
			  this.iidusuario = rSet.getInt("iidusuario");
			  this.vchnombreusuario = rSet.getString("vchnombreusuario");
			  this.vchaliasusuario = rSet.getString("vchaliasusuario");
			  this.vchdescripcion = rSet.getString("vchdescripcion");
			  this.vchpasaporte = rSet.getString("vchpasaporte");
			  this.iidgrupo = rSet.getInt("iidgrupo");
			  this.chactivo = rSet.getString("chactivo");
			  this.vchpreferencias = rSet.getString("vchpreferencias");
			  this.inumticks = rSet.getInt("inumticks");
		  }
		  rSet.close();
		  stmt.close();
		  return found;
	  }
*/

	public boolean findByUserPassword(String user, String password) throws Exception, java.sql.SQLException {
		boolean found = false;
		StringBuffer query = new StringBuffer();
		query.append("SELECT * FROM USUARIOS ");
		query.append("WHERE vchaliasusuario=? and vchpasaporte= ? and iidsistema=?");

		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setString(1,user);
		stmt.setString(2,password);
		rSet = stmt.executeQuery();

		if (rSet.next()) {
			found = true;
			this.iidusuario = rSet.getInt("iidusuario");
			this.vchnombreusuario = rSet.getString("vchnombreusuario");
			this.vchdescripcion = rSet.getString("vchdescripcion");
			this.iidgrupo = rSet.getInt("iidgrupo");
			this.chactivo = rSet.getString("chactivo");
			this.vchpreferencias = rSet.getString("vchpreferencias");
			this.inumticks = rSet.getInt("inumticks");
		}
		
		rSet.close();
		stmt.close();
		return found;
	}

	public boolean findByUserPassword(String user, String password, int sistema) throws Exception, java.sql.SQLException {
		boolean found = false;
		StringBuffer query = new StringBuffer();
		query.append("SELECT * FROM USUARIOS ");
		query.append("WHERE vchaliasusuario=? and vchpasaporte= ? and iidsistema=? and chactivo=?");

		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setString(1,user);
		stmt.setString(2,password);
		stmt.setInt(3,sistema);
		stmt.setString(4,"S");
		rSet = stmt.executeQuery();

		if (rSet.next()) {
			found = true;
			this.iidusuario = rSet.getInt("iidusuario");
			this.vchnombreusuario = rSet.getString("vchnombreusuario");
			this.vchdescripcion = rSet.getString("vchdescripcion");
			this.iidgrupo = rSet.getInt("iidgrupo");
			this.chactivo = rSet.getString("chactivo");
			this.vchpreferencias = rSet.getString("vchpreferencias");
			this.inumticks = rSet.getInt("inumticks");
		}
		
		rSet.close();
		stmt.close();
		return found;
	}
}
