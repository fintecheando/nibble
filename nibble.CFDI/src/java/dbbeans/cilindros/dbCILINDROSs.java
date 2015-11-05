/** Vector java bean entity **/
package dbbeans.cilindros;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Vector;


public class dbCILINDROSs {
	private Connection conn;
	private ResultSet rSet;
	private PreparedStatement stmt;
	private Vector result;


	public dbCILINDROSs() {
		super();
		result = new Vector();
	}
	public void setConnection(java.sql.Connection conn) {
		this.conn = conn;
	}
	public boolean find(String where) throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		dbCILINDROS CILINDROS;
		query.append("SELECT * ");
		query.append("FROM CILINDROS ");
		if (!where.equals("")){
			query.append("WHERE " + where );
		}
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		rSet = stmt.executeQuery();


		while (rSet.next()) {
			CILINDROS= new dbCILINDROS();
			CILINDROS.setSicilindros(rSet.getShort("sicilindros"));
			CILINDROS.setVchdescripcion(rSet.getString("vchdescripcion"));
			result.addElement(CILINDROS);
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
