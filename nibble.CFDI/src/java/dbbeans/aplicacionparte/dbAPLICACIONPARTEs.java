/** Vector java bean entity **/
package dbbeans.aplicacionparte;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Vector;


public class dbAPLICACIONPARTEs {
	private Connection conn;
	private ResultSet rSet;
	private PreparedStatement stmt;
	private Vector result;


	public dbAPLICACIONPARTEs() {
		super();
		result = new Vector();
	}
	public void setConnection(java.sql.Connection conn) {
		this.conn = conn;
	}
	public boolean find(String where) throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		dbAPLICACIONPARTE APLICACIONPARTE;
		query.append("SELECT * ");
		query.append("FROM APLICACIONPARTE ");
		if (!where.equals("")){
			query.append("WHERE " + where );
		}
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		rSet = stmt.executeQuery();


		while (rSet.next()) {
			APLICACIONPARTE= new dbAPLICACIONPARTE();
			APLICACIONPARTE.setLiidparte(rSet.getLong("liidparte"));
			APLICACIONPARTE.setIidaplicacion(rSet.getInt("iidaplicacion"));
			APLICACIONPARTE.setTicantidad(rSet.getShort("ticantidad"));
			result.addElement(APLICACIONPARTE);
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
