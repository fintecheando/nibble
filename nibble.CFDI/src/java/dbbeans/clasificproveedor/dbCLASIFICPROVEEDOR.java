/** java bean entity **/
package dbbeans.clasificproveedor;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class dbCLASIFICPROVEEDOR implements org.nibble.main.dbInterfase {
	private Connection conn;
	private ResultSet rSet;
	private PreparedStatement stmt;


	private int iidproveedor;
	private int iidcategoria;
	private int iidgrupo;
	private int iidlinea;
	private int iidmarca;
	private int iminimopedido;
	private boolean bitprovefavorito;
	private float decfactorvaluainve;
	private float decfactorcostovent;


	public void create() throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		query.append("INSERT INTO CLASIFICPROVEEDOR (iidproveedor,iidcategoria,iidgrupo,iidlinea,iidmarca,iminimopedido,bitprovefavorito,decfactorvaluainve,decfactorcostovent) ");
		query.append(" VALUES(?,?,?,?,?,?,?,?,?) ");
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setInt(1,iidproveedor);
		stmt.setInt(2,iidcategoria);
		stmt.setInt(3,iidgrupo);
		stmt.setInt(4,iidlinea);
		stmt.setInt(5,iidmarca);
		stmt.setInt(6,iminimopedido);
		stmt.setBoolean(7,bitprovefavorito);
		stmt.setFloat(8,decfactorvaluainve);
		stmt.setFloat(9,decfactorcostovent);
		stmt.executeUpdate();
		stmt.close();;
	}
	public boolean load() throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		query.append("SELECT * ");
		query.append("FROM CLASIFICPROVEEDOR ");
		query.append("WHERE iidproveedor=?  and iidcategoria=?  and iidgrupo=?  and iidlinea=?  and iidmarca=? ");
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setInt(1,iidproveedor);
		stmt.setInt(2,iidcategoria);
		stmt.setInt(3,iidgrupo);
		stmt.setInt(4,iidlinea);
		stmt.setInt(5,iidmarca);
		rSet = stmt.executeQuery();


		if (rSet.next()) {
			this.iminimopedido = rSet.getInt("iminimopedido");
			this.bitprovefavorito = rSet.getBoolean("bitprovefavorito");
			this.decfactorvaluainve = rSet.getFloat("decfactorvaluainve");
			this.decfactorcostovent = rSet.getFloat("decfactorcostovent");
		}
		else
			throw new Exception ("No se encontro el registro");
		rSet.close();
		stmt.close();
		return false;
	}
	public void remove() throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		query.append("DELETE FROM CLASIFICPROVEEDOR ");
		query.append("WHERE iidproveedor=?  and iidcategoria=?  and iidgrupo=?  and iidlinea=?  and iidmarca=? ");
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setInt(1,iidproveedor);
		stmt.setInt(2,iidcategoria);
		stmt.setInt(3,iidgrupo);
		stmt.setInt(4,iidlinea);
		stmt.setInt(5,iidmarca);
		stmt.executeUpdate();
		stmt.close();
	}
	public void store() throws SQLException {
		StringBuffer query = new StringBuffer();
		query.append("UPDATE CLASIFICPROVEEDOR SET iminimopedido=? , bitprovefavorito=? , decfactorvaluainve=? , decfactorcostovent=? ");
		query.append("WHERE iidproveedor=?  and iidcategoria=?  and iidgrupo=?  and iidlinea=?  and iidmarca=? ");
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setInt(1,iminimopedido);
		stmt.setBoolean(2,bitprovefavorito);
		stmt.setFloat(3,decfactorvaluainve);
		stmt.setFloat(4,decfactorcostovent);
		stmt.setInt(5,iidproveedor);
		stmt.setInt(6,iidcategoria);
		stmt.setInt(7,iidgrupo);
		stmt.setInt(8,iidlinea);
		stmt.setInt(9,iidmarca);
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
	public void setIminimopedido(int iminimopedido){
		this.iminimopedido=iminimopedido;
	}
	public int getIminimopedido(){
		return this.iminimopedido;
	}
	public void setBitprovefavorito(boolean bitprovefavorito){
		this.bitprovefavorito=bitprovefavorito;
	}
	public boolean getBitprovefavorito(){
		return this.bitprovefavorito;
	}
	public void setDecfactorvaluainve(float decfactorvaluainve){
		this.decfactorvaluainve=decfactorvaluainve;
	}
	public float getDecfactorvaluainve(){
		return this.decfactorvaluainve;
	}
	public void setDecfactorcostovent(float decfactorcostovent){
		this.decfactorcostovent=decfactorcostovent;
	}
	public float getDecfactorcostovent(){
		return this.decfactorcostovent;
	}
}
