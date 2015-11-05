/** Vector java bean entity **/
package dbbeans.fabricantemarca;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Vector;


public class dbFABRICANTEMARCAs {
	protected Connection conn;
	protected ResultSet rSet;
	protected PreparedStatement stmt;
	protected Vector result;


	public dbFABRICANTEMARCAs() {
		super();
		result = new Vector();
	}
	public void setConnection(java.sql.Connection conn) {
		this.conn = conn;
	}
	public boolean find(String where) throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		dbFABRICANTEMARCA FABRICANTEMARCA;
		query.append("SELECT * ");
		query.append("FROM FABRICANTEMARCA ");
		if (!where.equals("")){
			query.append("WHERE " + where );
		}
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		rSet = stmt.executeQuery();


		while (rSet.next()) {
			FABRICANTEMARCA= new dbFABRICANTEMARCA();
			FABRICANTEMARCA.setIidusuario(rSet.getInt("iidusuario"));
			FABRICANTEMARCA.setIidmarca(rSet.getInt("iidmarca"));
			result.addElement(FABRICANTEMARCA);
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
