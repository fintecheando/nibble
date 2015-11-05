/** Vector java bean entity **/
package dbbeans.traccion;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Vector;


public class dbTRACCIONs {
	private Connection conn;
	private ResultSet rSet;
	private PreparedStatement stmt;
	private Vector result;


	public dbTRACCIONs() {
		super();
		result = new Vector();
	}
	public void setConnection(java.sql.Connection conn) {
		this.conn = conn;
	}
	public boolean find(String where) throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		dbTRACCION TRACCION;
		query.append("SELECT * ");
		query.append("FROM TRACCION ");
		if (!where.equals("")){
			query.append("WHERE " + where );
		}
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		rSet = stmt.executeQuery();


		while (rSet.next()) {
			TRACCION= new dbTRACCION();
			TRACCION.setSitraccion(rSet.getShort("sitraccion"));
			TRACCION.setVchdescripcion(rSet.getString("vchdescripcion"));
			result.addElement(TRACCION);
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
