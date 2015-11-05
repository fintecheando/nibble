/** java bean entity **/
package dbbeans.partidasolicitada;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class dbPARTIDASOLICITADA implements org.nibble.main.dbInterfase {
	private Connection conn;
	private ResultSet rSet;
	private PreparedStatement stmt;


	private int iidpartidasolicit;
	private long liidparte;
	private int iidproveedor;
	private String dtfechasolicitud;
	private int icantidadpedida;


	public void create() throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		query.append("INSERT INTO PARTIDASOLICITADA (iidpartidasolicit,liidparte,iidproveedor,dtfechasolicitud,icantidadpedida) ");
		query.append(" VALUES(?,?,?,?,?) ");
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setInt(1,iidpartidasolicit);
		stmt.setLong(2,liidparte);
		stmt.setInt(3,iidproveedor);
		stmt.setString(4,dtfechasolicitud);
		stmt.setInt(5,icantidadpedida);
		stmt.executeUpdate();
		stmt.close();;
	}
	public boolean load() throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		query.append("SELECT * ");
		query.append("FROM PARTIDASOLICITADA ");
		query.append("WHERE iidpartidasolicit=? ");
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setInt(1,iidpartidasolicit);
		rSet = stmt.executeQuery();


		if (rSet.next()) {
			this.liidparte = rSet.getLong("liidparte");
			this.iidproveedor = rSet.getInt("iidproveedor");
			this.dtfechasolicitud = rSet.getString("dtfechasolicitud");
			this.icantidadpedida = rSet.getInt("icantidadpedida");
		}
		else
			throw new Exception ("No se encontro el registro");
		rSet.close();
		stmt.close();
		return false;
	}
	public void remove() throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		query.append("DELETE FROM PARTIDASOLICITADA ");
		query.append("WHERE iidpartidasolicit=? ");
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setInt(1,iidpartidasolicit);
		stmt.executeUpdate();
		stmt.close();
	}
	public void store() throws SQLException {
		StringBuffer query = new StringBuffer();
		query.append("UPDATE PARTIDASOLICITADA SET liidparte=? , iidproveedor=? , dtfechasolicitud=? , icantidadpedida=? ");
		query.append("WHERE iidpartidasolicit=? ");
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setLong(1,liidparte);
		stmt.setInt(2,iidproveedor);
		stmt.setString(3,dtfechasolicitud);
		stmt.setInt(4,icantidadpedida);
		stmt.setInt(5,iidpartidasolicit);
		stmt.executeUpdate();
		stmt.close();
	}
	public void setConnection(java.sql.Connection conn) {
		this.conn = conn;
	}
	public void setIidpartidasolicit(int iidpartidasolicit){
		this.iidpartidasolicit=iidpartidasolicit;
	}
	public int getIidpartidasolicit(){
		return this.iidpartidasolicit;
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
	public void setDtfechasolicitud(String dtfechasolicitud){
		this.dtfechasolicitud=dtfechasolicitud;
	}
	public String getDtfechasolicitud(){
		return this.dtfechasolicitud;
	}
	public void setIcantidadpedida(int icantidadpedida){
		this.icantidadpedida=icantidadpedida;
	}
	public int getIcantidadpedida(){
		return this.icantidadpedida;
	}

	public boolean loadByLiidparteIidproveedor() throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		query.append("SELECT * ");
		query.append("FROM PARTIDASOLICITADA ");
		query.append("WHERE liidparte=? AND iidproveedor=? ");
		stmt = conn.prepareStatement(query.toString().toLowerCase());	
		stmt.setLong(1,liidparte);
		stmt.setInt(2,iidproveedor);
		rSet = stmt.executeQuery();

		if (rSet.next()) {
			this.iidpartidasolicit = rSet.getInt("iidpartidasolicit");
			this.liidparte = rSet.getLong("liidparte");
			this.iidproveedor = rSet.getInt("iidproveedor");
			this.dtfechasolicitud = rSet.getString("dtfechasolicitud");
			this.icantidadpedida = rSet.getInt("icantidadpedida");
		}
		else
			throw new Exception ("No se encontro el registro");
		rSet.close();
		stmt.close();
		return false;
	}
}
