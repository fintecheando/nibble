/** java bean entity **/
package dbbeans.notacambiodetalle;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class dbNOTACAMBIODETALLE implements org.nibble.main.dbInterfase {
	private Connection conn;
	private ResultSet rSet;
	private PreparedStatement stmt;


	private int iidnotacambio;
	private long liidparte;
	private int iidproveedor;
	private int icantidadproducto;
	private float decpreciolista;
	private int iidnivel;
	private float deccostoproducto;
	private float decivaclasific;
	private float decimportedevol;


	public void create() throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		query.append("INSERT INTO NOTACAMBIODETALLE (iidnotacambio,liidparte,iidproveedor,icantidadproducto,decpreciolista,iidnivel,deccostoproducto,decivaclasific,decimportedevol) ");
		query.append(" VALUES(?,?,?,?,?,?,?,?,?) ");
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setInt(1,iidnotacambio);
		stmt.setLong(2,liidparte);
		stmt.setInt(3,iidproveedor);
		stmt.setInt(4,icantidadproducto);
		stmt.setFloat(5,decpreciolista);
		stmt.setInt(6,iidnivel);
		stmt.setFloat(7,deccostoproducto);
		stmt.setFloat(8,decivaclasific);
		stmt.setFloat(9,decimportedevol);
		stmt.executeUpdate();
		stmt.close();;
	}
	public boolean load() throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		query.append("SELECT * ");
		query.append("FROM NOTACAMBIODETALLE ");
		query.append("WHERE iidnotacambio=?  and liidparte=?  and iidproveedor=? ");
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setInt(1,iidnotacambio);
		stmt.setLong(2,liidparte);
		stmt.setInt(3,iidproveedor);
		rSet = stmt.executeQuery();


		if (rSet.next()) {
			this.icantidadproducto = rSet.getInt("icantidadproducto");
			this.decpreciolista = rSet.getFloat("decpreciolista");
			this.iidnivel = rSet.getInt("iidnivel");
			this.deccostoproducto = rSet.getFloat("deccostoproducto");
			this.decivaclasific = rSet.getFloat("decivaclasific");
			this.decimportedevol = rSet.getFloat("decimportedevol");
		}
		else
			throw new Exception ("No se encontro el registro");
		rSet.close();
		stmt.close();
		return false;
	}
	public void remove() throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		query.append("DELETE FROM NOTACAMBIODETALLE ");
		query.append("WHERE iidnotacambio=?  and liidparte=?  and iidproveedor=? ");
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setInt(1,iidnotacambio);
		stmt.setLong(2,liidparte);
		stmt.setInt(3,iidproveedor);
		stmt.executeUpdate();
		stmt.close();
	}
	public void store() throws SQLException {
		StringBuffer query = new StringBuffer();
		query.append("UPDATE NOTACAMBIODETALLE SET icantidadproducto=? , decpreciolista=? , iidnivel=? , deccostoproducto=? , decivaclasific=? , decimportedevol=? ");
		query.append("WHERE iidnotacambio=?  and liidparte=?  and iidproveedor=? ");
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setInt(1,icantidadproducto);
		stmt.setFloat(2,decpreciolista);
		stmt.setInt(3,iidnivel);
		stmt.setFloat(4,deccostoproducto);
		stmt.setFloat(5,decivaclasific);
		stmt.setFloat(6,decimportedevol);
		stmt.setInt(7,iidnotacambio);
		stmt.setLong(8,liidparte);
		stmt.setInt(9,iidproveedor);
		stmt.executeUpdate();
		stmt.close();
	}
	public void setConnection(java.sql.Connection conn) {
		this.conn = conn;
	}
	public void setIidnotacambio(int iidnotacambio){
		this.iidnotacambio=iidnotacambio;
	}
	public int getIidnotacambio(){
		return this.iidnotacambio;
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
	public void setIidnivel(int iidnivel){
		this.iidnivel=iidnivel;
	}
	public int getIidnivel(){
		return this.iidnivel;
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
	public void setDecimportedevol(float decimportedevol){
		this.decimportedevol=decimportedevol;
	}
	public float getDecimportedevol(){
		return this.decimportedevol;
	}
}
