/** java bean entity **/
package dbbeans.privilegiosgrupo;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class dbPRIVILEGIOSGRUPO implements org.nibble.main.dbInterfase {
	private Connection conn;
	private ResultSet rSet;
	private PreparedStatement stmt;


	private int iidgrupo;
	private int iidsistema;
	private String vchnombregrupo;
	private String vchdescripcion;
	private String vchcolor;
	private String vchmenu;


	public void create() throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		query.append("INSERT INTO PRIVILEGIOSGRUPO (iidgrupo,iidsistema,vchnombregrupo,vchdescripcion,vchcolor,vchmenu) ");
		query.append(" VALUES(?,?,?,?,?,?) ");
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setInt(1,iidgrupo);
		stmt.setInt(2,iidsistema);
		stmt.setString(3,vchnombregrupo);
		stmt.setString(4,vchdescripcion);
		stmt.setString(5,vchcolor);
		stmt.setString(6,vchmenu);
		stmt.executeUpdate();
		stmt.close();;
	}
	public boolean load() throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		query.append("SELECT * ");
		query.append("FROM PRIVILEGIOSGRUPO ");
		query.append("WHERE iidgrupo=?  and iidsistema=? ");
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setInt(1,iidgrupo);
		stmt.setInt(2,iidsistema);
		rSet = stmt.executeQuery();


		if (rSet.next()) {
			this.vchnombregrupo = rSet.getString("vchnombregrupo");
			this.vchdescripcion = rSet.getString("vchdescripcion");
			this.vchcolor = rSet.getString("vchcolor");
			this.vchmenu = rSet.getString("vchmenu");
		}
		rSet.close();
		stmt.close();
		return false;
	}
	public void remove() throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		query.append("DELETE FROM PRIVILEGIOSGRUPO ");
		query.append("WHERE iidgrupo=?  and iidsistema=? ");
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setInt(1,iidgrupo);
		stmt.setInt(2,iidsistema);
		stmt.executeUpdate();
		stmt.close();
	}
	public void store() throws SQLException {
		StringBuffer query = new StringBuffer();
		query.append("UPDATE PRIVILEGIOSGRUPO SET vchnombregrupo=? , vchdescripcion=? , vchcolor=? , vchmenu=? ");
		query.append("WHERE iidgrupo=?  and iidsistema=? ");
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setString(1,vchnombregrupo);
		stmt.setString(2,vchdescripcion);
		stmt.setString(3,vchcolor);
		stmt.setString(4,vchmenu);
		stmt.setInt(5,iidgrupo);
		stmt.setInt(6,iidsistema);
		stmt.executeUpdate();
		stmt.close();
	}
	public void setConnection(java.sql.Connection conn) {
		this.conn = conn;
	}
	public void setIidgrupo(int iidgrupo){
		this.iidgrupo=iidgrupo;
	}
	public int getIidgrupo(){
		return this.iidgrupo;
	}
	public void setIidsistema(int iidsistema){
		this.iidsistema=iidsistema;
	}
	public int getIidsistema(){
		return this.iidsistema;
	}
	public void setVchnombregrupo(String vchnombregrupo){
		this.vchnombregrupo=vchnombregrupo;
	}
	public String getVchnombregrupo(){
		return this.vchnombregrupo;
	}
	public void setVchdescripcion(String vchdescripcion){
		this.vchdescripcion=vchdescripcion;
	}
	public String getVchdescripcion(){
		return this.vchdescripcion;
	}
	public void setVchcolor(String vchcolor){
		this.vchcolor=vchcolor;
	}
	public String getVchcolor(){
		return this.vchcolor;
	}
	public void setVchmenu(String vchmenu){
		this.vchmenu=vchmenu;
	}
	public String getVchmenu(){
		return this.vchmenu;
	}
}
