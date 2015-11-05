/** Vector java bean entity **/
package dbbeans.valecaja;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Vector;


public class dbVALECAJAs {
	private Connection conn;
	private ResultSet rSet;
	private PreparedStatement stmt;
	private Vector result;


	public dbVALECAJAs() {
		super();
		result = new Vector();
	}
	public void setConnection(java.sql.Connection conn) {
		this.conn = conn;
	}
	public boolean find(String where) throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		dbVALECAJA VALECAJA;
		query.append("SELECT * ");
		query.append("FROM VALECAJA ");
		if (!where.equals("")){
			query.append("WHERE " + where );
		}
		stmt = conn.prepareStatement(query.toString());
		rSet = stmt.executeQuery();


		while (rSet.next()) {
			VALECAJA= new dbVALECAJA();
			VALECAJA.setIidvale(rSet.getInt("iidvale"));
			VALECAJA.setIidnocliente(rSet.getInt("iidnocliente"));
			VALECAJA.setIidusuario(rSet.getInt("iidusuario"));
			VALECAJA.setIidmodulo(rSet.getInt("iidmodulo"));
			VALECAJA.setTisfecha(rSet.getString("tisfecha"));
			VALECAJA.setDecimporte(rSet.getFloat("decimporte"));
			result.addElement(VALECAJA);
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
