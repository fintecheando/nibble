/** Vector java bean entity **/
package dbbeans.aspiracion;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Vector;


public class dbASPIRACIONs {
	private Connection conn;
	private ResultSet rSet;
	private PreparedStatement stmt;
	private Vector result;


	public dbASPIRACIONs() {
		super();
		result = new Vector();
	}
	public void setConnection(java.sql.Connection conn) {
		this.conn = conn;
	}
	public boolean find(String where) throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		dbASPIRACION ASPIRACION;
		query.append("SELECT * ");
		query.append("FROM ASPIRACION ");
		if (!where.equals("")){
			query.append("WHERE " + where );
		}
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		rSet = stmt.executeQuery();


		while (rSet.next()) {
			ASPIRACION= new dbASPIRACION();
			ASPIRACION.setSiaspiracion(rSet.getShort("siaspiracion"));
			ASPIRACION.setVchdescripcion(rSet.getString("vchdescripcion"));
			result.addElement(ASPIRACION);
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
