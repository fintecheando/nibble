/** Vector java bean entity **/
package dbbeans.tipodemovimiento;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Vector;


public class dbTIPODEMOVIMIENTOs {
	private Connection conn;
	private ResultSet rSet;
	private PreparedStatement stmt;
	private Vector result;


	public dbTIPODEMOVIMIENTOs() {
		super();
		result = new Vector();
	}
	public void setConnection(java.sql.Connection conn) {
		this.conn = conn;
	}
	public boolean find(String where) throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		dbTIPODEMOVIMIENTO TIPODEMOVIMIENTO;
		query.append("SELECT * ");
		query.append("FROM TIPODEMOVIMIENTO ");
		if (!where.equals("")){
			query.append("WHERE " + where );
		}
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		rSet = stmt.executeQuery();


		while (rSet.next()) {
			TIPODEMOVIMIENTO= new dbTIPODEMOVIMIENTO();
			TIPODEMOVIMIENTO.setTitipomov(rSet.getInt("titipomov"));
			TIPODEMOVIMIENTO.setVchdesctipomov(rSet.getString("vchdesctipomov"));
			result.addElement(TIPODEMOVIMIENTO);
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
