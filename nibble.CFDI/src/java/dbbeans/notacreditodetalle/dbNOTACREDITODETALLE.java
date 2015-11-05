/** java bean entity **/
package dbbeans.notacreditodetalle;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class dbNOTACREDITODETALLE implements org.nibble.main.dbInterfase {
	private Connection conn;
	private ResultSet rSet;
	private PreparedStatement stmt;


	private int iidnotacredito;
	private long liidparte;
	private int iidproveedor;
	private int icantidadproducto;
	private float decpreciolista;
	private int iidnivel;
	private float deccostoproducto;
	private float decivaclasific;
	private float decimportedevol;
	private long liidpartecambio;
	private int iidproveedorcambio;


	public void create() throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		query.append("INSERT INTO NOTACREDITODETALLE (iidnotacredito,liidparte,iidproveedor,icantidadproducto,decpreciolista,iidnivel,deccostoproducto,decivaclasific,decimportedevol,liidpartecambio,iidproveedorcambio) ");
		query.append(" VALUES(?,?,?,?,?,?,?,?,?,?,?) ");
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setInt(1,iidnotacredito);
		stmt.setLong(2,liidparte);
		stmt.setInt(3,iidproveedor);
		stmt.setInt(4,icantidadproducto);
		stmt.setFloat(5,decpreciolista);
		stmt.setInt(6,iidnivel);
		stmt.setFloat(7,deccostoproducto);
		stmt.setFloat(8,decivaclasific);
		stmt.setFloat(9,decimportedevol);
		stmt.setLong(10,liidpartecambio);
		stmt.setInt(11,iidproveedorcambio);
		stmt.executeUpdate();
		stmt.close();;
	}
	public boolean load() throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		query.append("SELECT * ");
		query.append("FROM NOTACREDITODETALLE ");
		query.append("WHERE iidnotacredito=?  and liidparte=?  and iidproveedor=? ");
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setInt(1,iidnotacredito);
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
			this.liidpartecambio = rSet.getLong("liidpartecambio");
			this.iidproveedorcambio = rSet.getInt("iidproveedorcambio");
		}
		else
			throw new Exception ("No se encontro el registro");
		rSet.close();
		stmt.close();
		return false;
	}
	public void remove() throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		query.append("DELETE FROM NOTACREDITODETALLE ");
		query.append("WHERE iidnotacredito=?  and liidparte=?  and iidproveedor=? ");
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setInt(1,iidnotacredito);
		stmt.setLong(2,liidparte);
		stmt.setInt(3,iidproveedor);
		stmt.executeUpdate();
		stmt.close();
	}
	public void store() throws SQLException {
		StringBuffer query = new StringBuffer();
		query.append("UPDATE NOTACREDITODETALLE SET icantidadproducto=? , decpreciolista=? , iidnivel=? , deccostoproducto=? , decivaclasific=? , decimportedevol=? , liidpartecambio=? , iidproveedorcambio=? ");
		query.append("WHERE iidnotacredito=?  and liidparte=?  and iidproveedor=? ");
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setInt(1,icantidadproducto);
		stmt.setFloat(2,decpreciolista);
		stmt.setInt(3,iidnivel);
		stmt.setFloat(4,deccostoproducto);
		stmt.setFloat(5,decivaclasific);
		stmt.setFloat(6,decimportedevol);
		stmt.setLong(7,liidpartecambio);
		stmt.setInt(8,iidproveedorcambio);
		stmt.setInt(9,iidnotacredito);
		stmt.setLong(10,liidparte);
		stmt.setInt(11,iidproveedor);
		stmt.executeUpdate();
		stmt.close();
	}
	public void setConnection(java.sql.Connection conn) {
		this.conn = conn;
	}
	public void setIidnotacredito(int iidnotacredito){
		this.iidnotacredito=iidnotacredito;
	}
	public int getIidnotacredito(){
		return this.iidnotacredito;
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
	public void setLiidpartecambio(long liidpartecambio){
		this.liidpartecambio=liidpartecambio;
	}
	public long getLiidpartecambio(){
		return this.liidpartecambio;
	}
	public void setIidproveedorcambio(int iidproveedorcambio){
		this.iidproveedorcambio=iidproveedorcambio;
	}
	public int getIidproveedorcambio(){
		return this.iidproveedorcambio;
	}
}
