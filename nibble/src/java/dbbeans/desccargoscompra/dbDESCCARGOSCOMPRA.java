/** java bean entity **/
package dbbeans.desccargoscompra;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class dbDESCCARGOSCOMPRA implements org.nibble.main.dbInterfase {
	private Connection conn;
	private ResultSet rSet;
	private PreparedStatement stmt;


	private int iidproveedor;
	private int iidcategoria;
	private int iidgrupo;
	private int iidlinea;
	private int iidmarca;
	private String vchleyenda;
	private float decfactor;


	public void create() throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		query.append("INSERT INTO DESCCARGOSCOMPRA (iidproveedor,iidcategoria,iidgrupo,iidlinea,iidmarca,vchleyenda,decfactor) ");
		query.append(" VALUES(?,?,?,?,?,?,?) ");
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setInt(1,iidproveedor);
		stmt.setInt(2,iidcategoria);
		stmt.setInt(3,iidgrupo);
		stmt.setInt(4,iidlinea);
		stmt.setInt(5,iidmarca);
		stmt.setString(6,vchleyenda);
		stmt.setFloat(7,decfactor);
		stmt.executeUpdate();
		stmt.close();;
	}
	public boolean load() throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		query.append("SELECT * ");
		query.append("FROM DESCCARGOSCOMPRA ");
		query.append("WHERE iidproveedor=?  and iidcategoria=?  and iidgrupo=?  and iidlinea=?  and iidmarca=?  and vchleyenda=? ");
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setInt(1,iidproveedor);
		stmt.setInt(2,iidcategoria);
		stmt.setInt(3,iidgrupo);
		stmt.setInt(4,iidlinea);
		stmt.setInt(5,iidmarca);
		stmt.setString(6,vchleyenda);
		rSet = stmt.executeQuery();

		if (rSet.next()) {
			this.decfactor = rSet.getFloat("decfactor");
		}
		else
			throw new java.sql.SQLException("registro no encontrado");
		rSet.close();
		stmt.close();
		return false;
	}
	public void remove() throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		query.append("DELETE FROM DESCCARGOSCOMPRA ");
		query.append("WHERE iidproveedor=?  and iidcategoria=?  and iidgrupo=?  and iidlinea=?  and iidmarca=?  and vchleyenda=? ");
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setInt(1,iidproveedor);
		stmt.setInt(2,iidcategoria);
		stmt.setInt(3,iidgrupo);
		stmt.setInt(4,iidlinea);
		stmt.setInt(5,iidmarca);
		stmt.setString(6,vchleyenda);
		stmt.executeUpdate();
		stmt.close();
	}
	public void store() throws SQLException {
		StringBuffer query = new StringBuffer();
		query.append("UPDATE DESCCARGOSCOMPRA SET decfactor=? ");
		query.append("WHERE iidproveedor=?  and iidcategoria=?  and iidgrupo=?  and iidlinea=?  and iidmarca=?  and vchleyenda=? ");
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setFloat(1,decfactor);
		stmt.setInt(2,iidproveedor);
		stmt.setInt(3,iidcategoria);
		stmt.setInt(4,iidgrupo);
		stmt.setInt(5,iidlinea);
		stmt.setInt(6,iidmarca);
		stmt.setString(7,vchleyenda);
		stmt.executeUpdate();
		stmt.close();
	}
	public void setConnection(java.sql.Connection conn) {
		this.conn = conn;
	}
	public void setIidproveedor(int iidproveedor){
		this.iidproveedor=iidproveedor;
	}
	public int getIidproveedor(){
		return this.iidproveedor;
	}
	public void setIidcategoria(int iidcategoria){
		this.iidcategoria=iidcategoria;
	}
	public int getIidcategoria(){
		return this.iidcategoria;
	}
	public void setIidgrupo(int iidgrupo){
		this.iidgrupo=iidgrupo;
	}
	public int getIidgrupo(){
		return this.iidgrupo;
	}
	public void setIidlinea(int iidlinea){
		this.iidlinea=iidlinea;
	}
	public int getIidlinea(){
		return this.iidlinea;
	}
	public void setIidmarca(int iidmarca){
		this.iidmarca=iidmarca;
	}
	public int getIidmarca(){
		return this.iidmarca;
	}
	public void setVchleyenda(String vchleyenda){
		this.vchleyenda=vchleyenda;
	}
	public String getVchleyenda(){
		return this.vchleyenda;
	}
	public void setDecfactor(float decfactor){
		this.decfactor=decfactor;
	}
	public float getDecfactor(){
		return this.decfactor;
	}
}
