/** Vector java bean entity **/
package dbbeans.combustible;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Vector;


public class dbCOMBUSTIBLEs {
	private Connection conn;
	private ResultSet rSet;
	private PreparedStatement stmt;
	private Vector result;


	public dbCOMBUSTIBLEs() {
		super();
		result = new Vector();
	}
	public void setConnection(java.sql.Connection conn) {
		this.conn = conn;
	}
	public boolean find(String where) throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		dbCOMBUSTIBLE COMBUSTIBLE;
		query.append("SELECT * ");
		query.append("FROM COMBUSTIBLE ");
		if (!where.equals("")){
			query.append("WHERE " + where );
		}
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		rSet = stmt.executeQuery();


		while (rSet.next()) {
			COMBUSTIBLE= new dbCOMBUSTIBLE();
			COMBUSTIBLE.setSicombustible(rSet.getShort("sicombustible"));
			COMBUSTIBLE.setVchdescripcion(rSet.getString("vchdescripcion"));
			result.addElement(COMBUSTIBLE);
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
