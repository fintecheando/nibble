/** java bean entity **/
package dbbeans.inventario;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class dbINVENTARIO implements org.nibble.main.dbInterfase {
	private Connection conn;
	private ResultSet rSet;
	private PreparedStatement stmt;


	private long liidparte;
	private int iidproveedor;
	private int iexistencia;
	private boolean bitsalidareciente;
	private int iindiceresurtido;
	private int icantconsumo;
	private String vchubicacion;


	public void create() throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		query.append("INSERT INTO INVENTARIO (liidparte,iidproveedor,iexistencia,bitsalidareciente,iindiceresurtido,icantconsumo,vchubicacion) ");
		query.append(" VALUES(?,?,?,?,?,?,?) ");
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setLong(1,liidparte);
		stmt.setInt(2,iidproveedor);
		stmt.setInt(3,iexistencia);
		stmt.setBoolean(4,bitsalidareciente);
		stmt.setInt(5,iindiceresurtido);
		stmt.setInt(6,icantconsumo);
		stmt.setString(7,vchubicacion);
		stmt.executeUpdate();
		stmt.close();;
	}
	public boolean load() throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		query.append("SELECT * ");
		query.append("FROM INVENTARIO ");
		query.append("WHERE liidparte=?  and iidproveedor=? ");
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setLong(1,liidparte);
		stmt.setInt(2,iidproveedor);
		rSet = stmt.executeQuery();


		if (rSet.next()) {
			this.iexistencia = rSet.getInt("iexistencia");
			this.bitsalidareciente = rSet.getBoolean("bitsalidareciente");
			this.iindiceresurtido = rSet.getInt("iindiceresurtido");
			this.icantconsumo = rSet.getInt("icantconsumo");
			this.vchubicacion = rSet.getString("vchubicacion");
		}
		else
			throw new Exception ("No se encontro el registro");
		rSet.close();
		stmt.close();
		return false;
	}
	public void remove() throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		query.append("DELETE FROM INVENTARIO ");
		query.append("WHERE liidparte=?  and iidproveedor=? ");
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setLong(1,liidparte);
		stmt.setInt(2,iidproveedor);
		stmt.executeUpdate();
		stmt.close();
	}
	public void store() throws SQLException {
		StringBuffer query = new StringBuffer();
		query.append("UPDATE INVENTARIO SET iexistencia=? , bitsalidareciente=? , iindiceresurtido=? , icantconsumo=? , vchubicacion=? ");
		query.append("WHERE liidparte=?  and iidproveedor=? ");
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setInt(1,iexistencia);
		stmt.setBoolean(2,bitsalidareciente);
		stmt.setInt(3,iindiceresurtido);
		stmt.setInt(4,icantconsumo);
		stmt.setString(5,vchubicacion);
		stmt.setLong(6,liidparte);
		stmt.setInt(7,iidproveedor);
		stmt.executeUpdate();
		stmt.close();
	}
	public void setConnection(java.sql.Connection conn) {
		this.conn = conn;
	}
	public void setLiidparte(long liidparte){
		this.liidparte=liidparte;
	}
	public long getLiidparte(){
		return this.liidparte;
	}
	public void setIidproveedor(int iidproveedor){
		this.iidproveedor=iidproveedor;
	}
	public int getIidproveedor(){
		return this.iidproveedor;
	}
	public void setIexistencia(int iexistencia){
		this.iexistencia=iexistencia;
	}
	public int getIexistencia(){
		return this.iexistencia;
	}
	public void setBitsalidareciente(boolean bitsalidareciente){
		this.bitsalidareciente=bitsalidareciente;
	}
	public boolean getBitsalidareciente(){
		return this.bitsalidareciente;
	}
	public void setIindiceresurtido(int iindiceresurtido){
		this.iindiceresurtido=iindiceresurtido;
	}
	public int getIindiceresurtido(){
		return this.iindiceresurtido;
	}
	public void setIcantconsumo(int icantconsumo){
		this.icantconsumo=icantconsumo;
	}
	public int getIcantconsumo(){
		return this.icantconsumo;
	}
	public void setVchubicacion(String vchubicacion){
		this.vchubicacion=vchubicacion;
	}
	public String getVchubicacion(){
		return this.vchubicacion;
	}
}
