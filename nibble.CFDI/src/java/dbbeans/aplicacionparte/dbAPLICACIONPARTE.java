/** java bean entity **/
package dbbeans.aplicacionparte;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class dbAPLICACIONPARTE implements org.nibble.main.dbInterfase {
	private Connection conn;
	private ResultSet rSet;
	private PreparedStatement stmt;


	private long liidparte;
	private int iidaplicacion;
	private short ticantidad;


	public void create() throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		query.append("INSERT INTO APLICACIONPARTE (liidparte,iidaplicacion,ticantidad) ");
		query.append(" VALUES(?,?,?) ");
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setLong(1,liidparte);
		stmt.setInt(2,iidaplicacion);
		stmt.setShort(3,ticantidad);
		stmt.executeUpdate();
		stmt.close();;
	}
	public boolean load() throws Exception, java.sql.SQLException {
		boolean found = false;
		StringBuffer query = new StringBuffer();
		query.append("SELECT * ");
		query.append("FROM APLICACIONPARTE ");
		query.append("WHERE liidparte=?  and iidaplicacion=? ");
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setLong(1,liidparte);
		stmt.setInt(2,iidaplicacion);
		rSet = stmt.executeQuery();


		if (rSet.next()) {
			found = true;
			this.ticantidad = rSet.getShort("ticantidad");
		}
		rSet.close();
		stmt.close();
		return found;
	}
	public void remove() throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		query.append("DELETE FROM APLICACIONPARTE ");
		query.append("WHERE liidparte=?  and iidaplicacion=? ");
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setLong(1,liidparte);
		stmt.setInt(2,iidaplicacion);
		stmt.executeUpdate();
		stmt.close();
	}
	public void store() throws SQLException {
		StringBuffer query = new StringBuffer();
		query.append("UPDATE APLICACIONPARTE SET ticantidad=? ");
		query.append("WHERE liidparte=?  and iidaplicacion=? ");
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setShort(1,ticantidad);
		stmt.setLong(2,liidparte);
		stmt.setInt(3,iidaplicacion);
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
	public void setIidaplicacion(int iidaplicacion){
		this.iidaplicacion=iidaplicacion;
	}
	public int getIidaplicacion(){
		return this.iidaplicacion;
	}
	public void setTicantidad(short ticantidad){
		this.ticantidad=ticantidad;
	}
	public short getTicantidad(){
		return this.ticantidad;
	}
}
