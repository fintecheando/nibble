/** java bean entity **/
package dbbeans.pedido;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class dbPEDIDO implements org.nibble.main.dbInterfase {
	private Connection conn;
	private ResultSet rSet;
	private PreparedStatement stmt;


	private int iidpedido;
	private int iidproveedor;
	private String tisfechelabpedido;
	private short sinototalpartidas;
	private short sinototpartpedpend;
	private short sinopartsurtpar;
	private short sinopartcanceladas;
	private short sinopartrecibidas;
	private int tiestatus;


	public void create() throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		query.append("INSERT INTO PEDIDO (iidpedido,iidproveedor,tisfechelabpedido,sinototalpartidas,sinototpartpedpend,sinopartsurtpar,sinopartcanceladas,sinopartrecibidas,tiestatus) ");
		query.append(" VALUES(?,?,?,?,?,?,?,?,?) ");
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setInt(1,iidpedido);
		stmt.setInt(2,iidproveedor);
		stmt.setString(3,tisfechelabpedido);
		stmt.setShort(4,sinototalpartidas);
		stmt.setShort(5,sinototpartpedpend);
		stmt.setShort(6,sinopartsurtpar);
		stmt.setShort(7,sinopartcanceladas);
		stmt.setShort(8,sinopartrecibidas);
		stmt.setInt(9,tiestatus);
		stmt.executeUpdate();
		stmt.close();;
	}
	public boolean load() throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		query.append("SELECT * ");
		query.append("FROM PEDIDO ");
		query.append("WHERE iidpedido=? ");
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setInt(1,iidpedido);
		rSet = stmt.executeQuery();


		if (rSet.next()) {
			this.iidproveedor = rSet.getInt("iidproveedor");
			this.tisfechelabpedido = rSet.getString("tisfechelabpedido");
			this.sinototalpartidas = rSet.getShort("sinototalpartidas");
			this.sinototpartpedpend = rSet.getShort("sinototpartpedpend");
			this.sinopartsurtpar = rSet.getShort("sinopartsurtpar");
			this.sinopartcanceladas = rSet.getShort("sinopartcanceladas");
			this.sinopartrecibidas = rSet.getShort("sinopartrecibidas");
			this.tiestatus = rSet.getInt("tiestatus");
		}
		else
			throw new Exception ("No se encontro el registro");
		rSet.close();
		stmt.close();
		return false;
	}
	public void remove() throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		query.append("DELETE FROM PEDIDO ");
		query.append("WHERE iidpedido=? ");
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setInt(1,iidpedido);
		stmt.executeUpdate();
		stmt.close();
	}
	public void store() throws SQLException {
		StringBuffer query = new StringBuffer();
		query.append("UPDATE PEDIDO SET iidproveedor=? , tisfechelabpedido=? , sinototalpartidas=? , sinototpartpedpend=? , sinopartsurtpar=? , sinopartcanceladas=? , sinopartrecibidas=? , tiestatus=? ");
		query.append("WHERE iidpedido=? ");
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setInt(1,iidproveedor);
		stmt.setString(2,tisfechelabpedido);
		stmt.setShort(3,sinototalpartidas);
		stmt.setShort(4,sinototpartpedpend);
		stmt.setShort(5,sinopartsurtpar);
		stmt.setShort(6,sinopartcanceladas);
		stmt.setShort(7,sinopartrecibidas);
		stmt.setInt(8,tiestatus);
		stmt.setInt(9,iidpedido);
		stmt.executeUpdate();
		stmt.close();
	}
	public void setConnection(java.sql.Connection conn) {
		this.conn = conn;
	}
	public void setIidpedido(int iidpedido){
		this.iidpedido=iidpedido;
	}
	public int getIidpedido(){
		return this.iidpedido;
	}
	public void setIidproveedor(int iidproveedor){
		this.iidproveedor=iidproveedor;
	}
	public int getIidproveedor(){
		return this.iidproveedor;
	}
	public void setTisfechelabpedido(String tisfechelabpedido){
		this.tisfechelabpedido=tisfechelabpedido;
	}
	public String getTisfechelabpedido(){
		return this.tisfechelabpedido;
	}
	public void setSinototalpartidas(short sinototalpartidas){
		this.sinototalpartidas=sinototalpartidas;
	}
	public short getSinototalpartidas(){
		return this.sinototalpartidas;
	}
	public void setSinototpartpedpend(short sinototpartpedpend){
		this.sinototpartpedpend=sinototpartpedpend;
	}
	public short getSinototpartpedpend(){
		return this.sinototpartpedpend;
	}
	public void setSinopartsurtpar(short sinopartsurtpar){
		this.sinopartsurtpar=sinopartsurtpar;
	}
	public short getSinopartsurtpar(){
		return this.sinopartsurtpar;
	}
	public void setSinopartcanceladas(short sinopartcanceladas){
		this.sinopartcanceladas=sinopartcanceladas;
	}
	public short getSinopartcanceladas(){
		return this.sinopartcanceladas;
	}
	public void setSinopartrecibidas(short sinopartrecibidas){
		this.sinopartrecibidas=sinopartrecibidas;
	}
	public short getSinopartrecibidas(){
		return this.sinopartrecibidas;
	}
	public void setTiestatus(int tiestatus){
		this.tiestatus=tiestatus;
	}
	public int getTiestatus(){
		return this.tiestatus;
	}

	public boolean loadByFecha() throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		query.append("SELECT * ");
		query.append("FROM PEDIDO ");
		query.append("WHERE tisfechelabpedido=? ");
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		
		stmt.setString(1,tisfechelabpedido);
		rSet = stmt.executeQuery();


		if (rSet.next()) {
			this.iidpedido = rSet.getInt("iidpedido");
			this.iidproveedor = rSet.getInt("iidproveedor");
			this.tisfechelabpedido = rSet.getString("tisfechelabpedido");
			this.sinototalpartidas = rSet.getShort("sinototalpartidas");
			this.sinototpartpedpend = rSet.getShort("sinototpartpedpend");
			this.sinopartsurtpar = rSet.getShort("sinopartsurtpar");
			this.sinopartcanceladas = rSet.getShort("sinopartcanceladas");
			this.sinopartrecibidas = rSet.getShort("sinopartrecibidas");
			this.tiestatus = rSet.getInt("tiestatus");
		}
		else
			throw new Exception ("No se encontro el registro");
		rSet.close();
		stmt.close();
		return false;
	}
}
