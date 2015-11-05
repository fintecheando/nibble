/** Vector java bean entity **/
package dbbeans.inventario;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Vector;


public class dbINVENTARIOs {
	protected Connection conn;
	protected ResultSet rSet;
	protected PreparedStatement stmt;
	protected Vector result;


	public dbINVENTARIOs() {
		super();
		result = new Vector();
	}
	public void setConnection(java.sql.Connection conn) {
		this.conn = conn;
	}
	public boolean find(String where) throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		dbINVENTARIO INVENTARIO;
		query.append("SELECT * ");
		query.append("FROM INVENTARIO ");
		if (!where.equals("")){
			query.append("WHERE " + where );
		}
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		rSet = stmt.executeQuery();


		while (rSet.next()) {
			INVENTARIO= new dbINVENTARIO();
			INVENTARIO.setLiidparte(rSet.getLong("liidparte"));
			INVENTARIO.setIidproveedor(rSet.getInt("iidproveedor"));
			INVENTARIO.setIexistencia(rSet.getInt("iexistencia"));
			INVENTARIO.setBitsalidareciente(rSet.getBoolean("bitsalidareciente"));
			INVENTARIO.setIindiceresurtido(rSet.getInt("iindiceresurtido"));
			INVENTARIO.setIcantconsumo(rSet.getInt("icantconsumo"));
			INVENTARIO.setVchubicacion(rSet.getString("vchubicacion"));
			result.addElement(INVENTARIO);
		}
		rSet.close();
		stmt.close();
		return false;
	}
	public void setResult (Vector result){
		this.result = result;
	}
	public Vector getResult(){
		return this.result;
	}
}
