/** Vector java bean entity **/
package dbbeans.tipodocumento;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Vector;


public class dbTIPODOCUMENTOs {
	private Connection conn;
	private ResultSet rSet;
	private PreparedStatement stmt;
	private Vector result;


	public dbTIPODOCUMENTOs() {
		super();
		result = new Vector();
	}
	public void setConnection(java.sql.Connection conn) {
		this.conn = conn;
	}
	public boolean find(String where) throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		dbTIPODOCUMENTO TIPODOCUMENTO;
		query.append("SELECT * ");
		query.append("FROM TIPODOCUMENTO ");
		if (!where.equals("")){
			query.append("WHERE " + where );
		}
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		rSet = stmt.executeQuery();


		while (rSet.next()) {
			TIPODOCUMENTO= new dbTIPODOCUMENTO();
			TIPODOCUMENTO.setTiidtipodocto(rSet.getInt("tiidtipodocto"));
			TIPODOCUMENTO.setVchdesctipodocto(rSet.getString("vchdesctipodocto"));
			result.addElement(TIPODOCUMENTO);
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
