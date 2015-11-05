/** java bean entity **/
package dbbeans.pedidopartidas;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class dbPEDIDOPARTIDAS implements org.nibble.main.dbInterfase {
	private Connection conn;
	private ResultSet rSet;
	private PreparedStatement stmt;


	private int iidpedido;
	private long liidparte;
	private int icantpedida;
	private int tiestatus;
	private String dtfechultrecepcion;
	private int icantrecibida;
	private String vchfactura;
	private String vchremisionproveed;
	private String vchcomentario;


	public void create() throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		query.append("INSERT INTO PEDIDOPARTIDAS (iidpedido,liidparte,icantpedida,tiestatus,dtfechultrecepcion,icantrecibida,vchfactura,vchremisionproveed,vchcomentario) ");
		query.append(" VALUES(?,?,?,?,?,?,?,?,?) ");
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setInt(1,iidpedido);
		stmt.setLong(2,liidparte);
		stmt.setInt(3,icantpedida);
		stmt.setInt(4,tiestatus);
		stmt.setString(5,dtfechultrecepcion);
		stmt.setInt(6,icantrecibida);
		stmt.setString(7,vchfactura);
		stmt.setString(8,vchremisionproveed);
		stmt.setString(9,vchcomentario);
		stmt.executeUpdate();
		stmt.close();;
	}
	public boolean load() throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		query.append("SELECT * ");
		query.append("FROM PEDIDOPARTIDAS ");
		query.append("WHERE iidpedido=?  and liidparte=? ");
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setInt(1,iidpedido);
		stmt.setLong(2,liidparte);
		rSet = stmt.executeQuery();


		if (rSet.next()) {
			this.icantpedida = rSet.getInt("icantpedida");
			this.tiestatus = rSet.getInt("tiestatus");
			this.dtfechultrecepcion = rSet.getString("dtfechultrecepcion");
			this.icantrecibida = rSet.getInt("icantrecibida");
			this.vchfactura = rSet.getString("vchfactura");
			this.vchremisionproveed = rSet.getString("vchremisionproveed");
			this.vchcomentario = rSet.getString("vchcomentario");
		}
		else
			throw new Exception ("No se encontro el registro");
		rSet.close();
		stmt.close();
		return false;
	}
	public void remove() throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		query.append("DELETE FROM PEDIDOPARTIDAS ");
		query.append("WHERE iidpedido=?  and liidparte=? ");
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setInt(1,iidpedido);
		stmt.setLong(2,liidparte);
		stmt.executeUpdate();
		stmt.close();
	}
	public void store() throws SQLException {
		StringBuffer query = new StringBuffer();
		query.append("UPDATE PEDIDOPARTIDAS SET icantpedida=? , tiestatus=? , dtfechultrecepcion=? , icantrecibida=? , vchfactura=? , vchremisionproveed=? , vchcomentario=? ");
		query.append("WHERE iidpedido=?  and liidparte=? ");
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setInt(1,icantpedida);
		stmt.setInt(2,tiestatus);
		stmt.setString(3,dtfechultrecepcion);
		stmt.setInt(4,icantrecibida);
		stmt.setString(5,vchfactura);
		stmt.setString(6,vchremisionproveed);
		stmt.setString(7,vchcomentario);
		stmt.setInt(8,iidpedido);
		stmt.setLong(9,liidparte);
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
	public void setLiidparte(long liidparte){
		this.liidparte=liidparte;
	}
	public long getLiidparte(){
		return this.liidparte;
	}
	public void setIcantpedida(int icantpedida){
		this.icantpedida=icantpedida;
	}
	public int getIcantpedida(){
		return this.icantpedida;
	}
	public void setTiestatus(int tiestatus){
		this.tiestatus=tiestatus;
	}
	public int getTiestatus(){
		return this.tiestatus;
	}
	public void setDtfechultrecepcion(String dtfechultrecepcion){
		this.dtfechultrecepcion=dtfechultrecepcion;
	}
	public String getDtfechultrecepcion(){
		return this.dtfechultrecepcion;
	}
	public void setIcantrecibida(int icantrecibida){
		this.icantrecibida=icantrecibida;
	}
	public int getIcantrecibida(){
		return this.icantrecibida;
	}
	public void setVchfactura(String vchfactura){
		this.vchfactura=vchfactura;
	}
	public String getVchfactura(){
		return this.vchfactura;
	}
	public void setVchremisionproveed(String vchremisionproveed){
		this.vchremisionproveed=vchremisionproveed;
	}
	public String getVchremisionproveed(){
		return this.vchremisionproveed;
	}
	public void setVchcomentario(String vchcomentario){
		this.vchcomentario=vchcomentario;
	}
	public String getVchcomentario(){
		return this.vchcomentario;
	}
}
