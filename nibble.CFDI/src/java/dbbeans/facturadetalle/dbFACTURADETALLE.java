/** java bean entity **/
package dbbeans.facturadetalle;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class dbFACTURADETALLE implements org.nibble.main.dbInterfase {
	private Connection conn;
	private ResultSet rSet;
	private PreparedStatement stmt;


	private int iidfactura;
	private long liidparte;
	private int iidproveedor;
	private int iidnivel;
	private int icantidadproducto;
	private float decpreciolista;
	private float deccostoproducto;
	private float decivaclasific;
	private float decprecioventa;


	public void create() throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		query.append("INSERT INTO FACTURADETALLE (iidfactura,liidparte,iidproveedor,iidnivel,icantidadproducto,decpreciolista,deccostoproducto,decivaclasific,decprecioventa) ");
		query.append(" VALUES(?,?,?,?,?,?,?,?,?) ");
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setInt(1,iidfactura);
		stmt.setLong(2,liidparte);
		stmt.setInt(3,iidproveedor);
		stmt.setInt(4,iidnivel);
		stmt.setInt(5,icantidadproducto);
		stmt.setFloat(6,decpreciolista);
		stmt.setFloat(7,deccostoproducto);
		stmt.setFloat(8,decivaclasific);
		stmt.setFloat(9,decprecioventa);
		stmt.executeUpdate();
		stmt.close();;
	}
	public boolean load() throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		query.append("SELECT * ");
		query.append("FROM FACTURADETALLE ");
		query.append("WHERE iidfactura=?  and liidparte=?  and iidproveedor=? ");
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setInt(1,iidfactura);
		stmt.setLong(2,liidparte);
		stmt.setInt(3,iidproveedor);
		rSet = stmt.executeQuery();


		if (rSet.next()) {
			this.iidnivel = rSet.getInt("iidnivel");
			this.icantidadproducto = rSet.getInt("icantidadproducto");
			this.decpreciolista = rSet.getFloat("decpreciolista");
			this.deccostoproducto = rSet.getFloat("deccostoproducto");
			this.decivaclasific = rSet.getFloat("decivaclasific");
			this.decprecioventa = rSet.getFloat("decprecioventa");
		}
		else
			throw new Exception ("No se encontro el registro");
		rSet.close();
		stmt.close();
		return false;
	}
	public void remove() throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		query.append("DELETE FROM FACTURADETALLE ");
		query.append("WHERE iidfactura=?  and liidparte=?  and iidproveedor=? ");
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setInt(1,iidfactura);
		stmt.setLong(2,liidparte);
		stmt.setInt(3,iidproveedor);
		stmt.executeUpdate();
		stmt.close();
	}
	public void store() throws SQLException {
		StringBuffer query = new StringBuffer();
		query.append("UPDATE FACTURADETALLE SET iidnivel=? , icantidadproducto=? , decpreciolista=? , deccostoproducto=? , decivaclasific=? , decprecioventa=? ");
		query.append("WHERE iidfactura=?  and liidparte=?  and iidproveedor=? ");
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setInt(1,iidnivel);
		stmt.setInt(2,icantidadproducto);
		stmt.setFloat(3,decpreciolista);
		stmt.setFloat(4,deccostoproducto);
		stmt.setFloat(5,decivaclasific);
		stmt.setFloat(6,decprecioventa);
		stmt.setInt(7,iidfactura);
		stmt.setLong(8,liidparte);
		stmt.setInt(9,iidproveedor);
		stmt.executeUpdate();
		stmt.close();
	}
	public void setConnection(java.sql.Connection conn) {
		this.conn = conn;
	}
	public void setIidfactura(int iidfactura){
		this.iidfactura=iidfactura;
	}
	public int getIidfactura(){
		return this.iidfactura;
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
	public void setIidnivel(int iidnivel){
		this.iidnivel=iidnivel;
	}
	public int getIidnivel(){
		return this.iidnivel;
	}
	public void setIcantidadproducto(int icantidadproducto){
		this.icantidadproducto=icantidadproducto;
	}
	public int getIcantidadproducto(){
		return this.icantidadproducto;
	}
	public void setDecpreciolista(float decpreciolista){
		this.decpreciolista=decpreciolista;
	}
	public float getDecpreciolista(){
		return this.decpreciolista;
	}
	public void setDeccostoproducto(float deccostoproducto){
		this.deccostoproducto=deccostoproducto;
	}
	public float getDeccostoproducto(){
		return this.deccostoproducto;
	}
	public void setDecivaclasific(float decivaclasific){
		this.decivaclasific=decivaclasific;
	}
	public float getDecivaclasific(){
		return this.decivaclasific;
	}
	public void setDecprecioventa(float decprecioventa){
		this.decprecioventa=decprecioventa;
	}
	public float getDecprecioventa(){
		return this.decprecioventa;
	}
}
