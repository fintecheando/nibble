/** Vector java bean entity **/
package dbbeans.fabricantelinea;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Vector;


public class dbFABRICANTELINEAs {
	protected Connection conn;
	protected ResultSet rSet;
	protected PreparedStatement stmt;
	protected Vector result;


	public dbFABRICANTELINEAs() {
		super();
		result = new Vector();
	}
	public void setConnection(java.sql.Connection conn) {
		this.conn = conn;
	}
	public boolean find(String where) throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		dbFABRICANTELINEA FABRICANTELINEA;
		query.append("SELECT * ");
		query.append("FROM FABRICANTELINEA ");
		if (!where.equals("")){
			query.append("WHERE " + where );
		}
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		rSet = stmt.executeQuery();


		while (rSet.next()) {
			FABRICANTELINEA= new dbFABRICANTELINEA();
			FABRICANTELINEA.setIidusuario(rSet.getInt("iidusuario"));
			FABRICANTELINEA.setIidlinea(rSet.getInt("iidlinea"));
			result.addElement(FABRICANTELINEA);
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
