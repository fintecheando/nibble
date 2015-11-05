/** Vector java bean entity **/
package dbbeans.tipopago;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Vector;


public class dbTIPOPAGOs {
	private Connection conn;
	private ResultSet rSet;
	private PreparedStatement stmt;
	private Vector result;


	public dbTIPOPAGOs() {
		super();
		result = new Vector();
	}
	public void setConnection(java.sql.Connection conn) {
		this.conn = conn;
	}
	public boolean find(String where) throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		dbTIPOPAGO TIPOPAGO;
		query.append("SELECT * ");
		query.append("FROM TIPOPAGO ");
		if (!where.equals("")){
			query.append("WHERE " + where );
		}
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		rSet = stmt.executeQuery();


		while (rSet.next()) {
			TIPOPAGO= new dbTIPOPAGO();
			TIPOPAGO.setTiidtipopago(rSet.getInt("tiidtipopago"));
			TIPOPAGO.setVchdesctipopago(rSet.getString("vchdesctipopago"));
			result.addElement(TIPOPAGO);
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
