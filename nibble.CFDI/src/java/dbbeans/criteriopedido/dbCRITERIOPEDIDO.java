/** java bean entity **/
package dbbeans.criteriopedido;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class dbCRITERIOPEDIDO implements org.nibble.main.dbInterfase {
	private Connection conn;
	private ResultSet rSet;
	private PreparedStatement stmt;


	private int iidcategoria;
	private int iidgrupo;
	private int iidlinea;
	private int iidmarca;
	private int inodiasinventario;


	public void create() throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		query.append("INSERT INTO CRITERIOPEDIDO (iidcategoria,iidgrupo,iidlinea,iidmarca,inodiasinventario) ");
		query.append(" VALUES(?,?,?,?,?) ");
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setInt(1,iidcategoria);
		stmt.setInt(2,iidgrupo);
		stmt.setInt(3,iidlinea);
		stmt.setInt(4,iidmarca);
		stmt.setInt(5,inodiasinventario);
		stmt.executeUpdate();
		stmt.close();;
	}
	public boolean load() throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		query.append("SELECT * ");
		query.append("FROM CRITERIOPEDIDO ");
		query.append("WHERE iidcategoria=?  and iidgrupo=?  and iidlinea=?  and iidmarca=? ");
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setInt(1,iidcategoria);
		stmt.setInt(2,iidgrupo);
		stmt.setInt(3,iidlinea);
		stmt.setInt(4,iidmarca);
		rSet = stmt.executeQuery();


		if (rSet.next()) {
			this.inodiasinventario = rSet.getInt("inodiasinventario");
		}
		
		rSet.close();
		stmt.close();
		return false;
	}
	public void remove() throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		query.append("DELETE FROM CRITERIOPEDIDO ");
		query.append("WHERE iidcategoria=?  and iidgrupo=?  and iidlinea=?  and iidmarca=? ");
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setInt(1,iidcategoria);
		stmt.setInt(2,iidgrupo);
		stmt.setInt(3,iidlinea);
		stmt.setInt(4,iidmarca);
		stmt.executeUpdate();
		stmt.close();
	}
	public void store() throws SQLException {
		StringBuffer query = new StringBuffer();
		query.append("UPDATE CRITERIOPEDIDO SET inodiasinventario=? ");
		query.append("WHERE iidcategoria=?  and iidgrupo=?  and iidlinea=?  and iidmarca=? ");
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setInt(1,inodiasinventario);
		stmt.setInt(2,iidcategoria);
		stmt.setInt(3,iidgrupo);
		stmt.setInt(4,iidlinea);
		stmt.setInt(5,iidmarca);
		stmt.executeUpdate();
		stmt.close();
	}
	public void setConnection(java.sql.Connection conn) {
		this.conn = conn;
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
	public void setInodiasinventario(int inodiasinventario){
		this.inodiasinventario=inodiasinventario;
	}
	public int getInodiasinventario(){
		return this.inodiasinventario;
	}
}
