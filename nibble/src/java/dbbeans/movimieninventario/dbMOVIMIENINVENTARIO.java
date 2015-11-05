/** java bean entity **/
package dbbeans.movimieninventario;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class dbMOVIMIENINVENTARIO implements org.nibble.main.dbInterfase {
	private Connection conn;
	private ResultSet rSet;
	private PreparedStatement stmt;


	private int iidmovinventario;
	private int iidproveedor;
	private long liidparte;
	private int titipomov;
	private String tisfechamov;
	private float deccostob100;
	private float decporciva;
	private float decprecioventa;
	private int iidreferencia;
	private int iidusuario;
	private int icantidad;


	public void create() throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		query.append("INSERT INTO MOVIMIENINVENTARIO (iidproveedor,liidparte,titipomov,tisfechamov,deccostob100,decporciva,decprecioventa,iidreferencia,iidusuario,icantidad) ");
		query.append(" VALUES(?,?,?,?,?,?,?,?,?,?) ");
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setInt(1,iidproveedor);
		stmt.setLong(2,liidparte);
		stmt.setInt(3,titipomov);
		stmt.setString(4,tisfechamov);
		stmt.setFloat(5,deccostob100);
		stmt.setFloat(6,decporciva);
		stmt.setFloat(7,decprecioventa);
		stmt.setInt(8,iidreferencia);
		stmt.setInt(9,iidusuario);
		stmt.setInt(10,icantidad);
		stmt.executeUpdate();
		stmt.close();;
	}
	public boolean load() throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		query.append("SELECT * ");
		query.append("FROM MOVIMIENINVENTARIO ");
		query.append("WHERE iidmovinventario=? ");
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setInt(1,iidmovinventario);
		rSet = stmt.executeQuery();


		if (rSet.next()) {
			this.iidproveedor = rSet.getInt("iidproveedor");
			this.liidparte = rSet.getLong("liidparte");
			this.titipomov = rSet.getInt("titipomov");
			this.tisfechamov = rSet.getString("tisfechamov");
			this.deccostob100 = rSet.getFloat("deccostob100");
			this.decporciva = rSet.getFloat("decporciva");
			this.decprecioventa = rSet.getFloat("decprecioventa");
			this.iidreferencia = rSet.getInt("iidreferencia");
			this.iidusuario = rSet.getInt("iidusuario");
			this.icantidad = rSet.getInt("icantidad");
		}
		else
			throw new Exception ("No se encontro el registro");
		rSet.close();
		stmt.close();
		return false;
	}
	public void remove() throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		query.append("DELETE FROM MOVIMIENINVENTARIO ");
		query.append("WHERE iidmovinventario=? ");
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setInt(1,iidmovinventario);
		stmt.executeUpdate();
		stmt.close();
	}
	public void store() throws SQLException {
		StringBuffer query = new StringBuffer();
		query.append("UPDATE MOVIMIENINVENTARIO SET iidproveedor=? , liidparte=? , titipomov=? , tisfechamov=? , deccostob100=? , decporciva=? , decprecioventa=? , iidreferencia=? , iidusuario=? , icantidad=? ");
		query.append("WHERE iidmovinventario=? ");
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setInt(1,iidproveedor);
		stmt.setLong(2,liidparte);
		stmt.setInt(3,titipomov);
		stmt.setString(4,tisfechamov);
		stmt.setFloat(5,deccostob100);
		stmt.setFloat(6,decporciva);
		stmt.setFloat(7,decprecioventa);
		stmt.setInt(8,iidreferencia);
		stmt.setInt(9,iidusuario);
		stmt.setInt(10,icantidad);
		stmt.setInt(11,iidmovinventario);
		stmt.executeUpdate();
		stmt.close();
	}
	public void setConnection(java.sql.Connection conn) {
		this.conn = conn;
	}
	public void setIidmovinventario(int iidmovinventario){
		this.iidmovinventario=iidmovinventario;
	}
	public int getIidmovinventario(){
		return this.iidmovinventario;
	}
	public void setIidproveedor(int iidproveedor){
		this.iidproveedor=iidproveedor;
	}
	public int getIidproveedor(){
		return this.iidproveedor;
	}
	public void setLiidparte(long liidparte){
		this.liidparte=liidparte;
	}
	public long getLiidparte(){
		return this.liidparte;
	}
	public void setTitipomov(int titipomov){
		this.titipomov=titipomov;
	}
	public int getTitipomov(){
		return this.titipomov;
	}
	public void setTisfechamov(String tisfechamov){
		this.tisfechamov=tisfechamov;
	}
	public String getTisfechamov(){
		return this.tisfechamov;
	}
	public void setDeccostob100(float deccostob100){
		this.deccostob100=deccostob100;
	}
	public float getDeccostob100(){
		return this.deccostob100;
	}
	public void setDecporciva(float decporciva){
		this.decporciva=decporciva;
	}
	public float getDecporciva(){
		return this.decporciva;
	}
	public void setDecprecioventa(float decprecioventa){
		this.decprecioventa=decprecioventa;
	}
	public float getDecprecioventa(){
		return this.decprecioventa;
	}
	public void setIidreferencia(int iidreferencia){
		this.iidreferencia=iidreferencia;
	}
	public int getIidreferencia(){
		return this.iidreferencia;
	}
	public void setIidusuario(int iidusuario){
		this.iidusuario=iidusuario;
	}
	public int getIidusuario(){
		return this.iidusuario;
	}
	public void setIcantidad(int icantidad){
		this.icantidad=icantidad;
	}
	public int getIcantidad(){
		return this.icantidad;
	}
}
