/** Vector java bean entity **/
package dbbeans.transmision;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Vector;


public class dbTRANSMISIONs {
	private Connection conn;
	private ResultSet rSet;
	private PreparedStatement stmt;
	private Vector result;


	public dbTRANSMISIONs() {
		super();
		result = new Vector();
	}
	public void setConnection(java.sql.Connection conn) {
		this.conn = conn;
	}
	public boolean find(String where) throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		dbTRANSMISION TRANSMISION;
		query.append("SELECT * ");
		query.append("FROM TRANSMISION ");
		if (!where.equals("")){
			query.append("WHERE " + where );
		}
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		rSet = stmt.executeQuery();


		while (rSet.next()) {
			TRANSMISION= new dbTRANSMISION();
			TRANSMISION.setSitransmision(rSet.getShort("sitransmision"));
			TRANSMISION.setVchdescripcion(rSet.getString("vchdescripcion"));
			result.addElement(TRANSMISION);
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
