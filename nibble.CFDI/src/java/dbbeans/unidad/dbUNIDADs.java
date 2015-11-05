/** Vector java bean entity **/
package dbbeans.unidad;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Vector;


public class dbUNIDADs {
	private Connection conn;
	private ResultSet rSet;
	private PreparedStatement stmt;
	private Vector result;


	public dbUNIDADs() {
		super();
		result = new Vector();
	}
	public void setConnection(java.sql.Connection conn) {
		this.conn = conn;
	}
	public boolean find(String where) throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		dbUNIDAD UNIDAD;
		query.append("SELECT * ");
		query.append("FROM UNIDAD ");
		if (!where.equals("")){
			query.append("WHERE " + where );
		}
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		rSet = stmt.executeQuery();


		while (rSet.next()) {
			UNIDAD= new dbUNIDAD();
			UNIDAD.setIidunidad(rSet.getInt("iidunidad"));
			UNIDAD.setVchdescripcion(rSet.getString("vchdescripcion"));
			result.addElement(UNIDAD);
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
