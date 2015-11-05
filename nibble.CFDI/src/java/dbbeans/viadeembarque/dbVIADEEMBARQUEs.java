/** Vector java bean entity **/
package dbbeans.viadeembarque;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Vector;


public class dbVIADEEMBARQUEs {
	private Connection conn;
	private ResultSet rSet;
	private PreparedStatement stmt;
	private Vector result;


	public dbVIADEEMBARQUEs() {
		super();
		result = new Vector();
	}
	public void setConnection(java.sql.Connection conn) {
		this.conn = conn;
	}
	public boolean find(String where) throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		dbVIADEEMBARQUE VIADEEMBARQUE;
		query.append("SELECT * ");
		query.append("FROM VIADEEMBARQUE ");
		if (!where.equals("")){
			query.append("WHERE " + where );
		}
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		rSet = stmt.executeQuery();


		while (rSet.next()) {
			VIADEEMBARQUE= new dbVIADEEMBARQUE();
			VIADEEMBARQUE.setSiidviaembarque(rSet.getShort("siidviaembarque"));
			VIADEEMBARQUE.setVchdescviaembarque(rSet.getString("vchdescviaembarque"));
			result.addElement(VIADEEMBARQUE);
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
