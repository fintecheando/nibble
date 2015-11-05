/** Vector java bean entity **/
package dbbeans.gruposmenu;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Vector;


public class dbGRUPOSMENUs {
	private Connection conn;
	private ResultSet rSet;
	private PreparedStatement stmt;
	private Vector result;


	public dbGRUPOSMENUs() {
		super();
		result = new Vector();
	}
	public void setConnection(java.sql.Connection conn) {
		this.conn = conn;
	}
	public boolean find(String where) throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		dbGRUPOSMENU GRUPOSMENU;
		query.append("SELECT * ");
		query.append("FROM GRUPOSMENU ");
		if (!where.equals("")){
			query.append("WHERE " + where );
		}
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		rSet = stmt.executeQuery();


		while (rSet.next()) {
			GRUPOSMENU= new dbGRUPOSMENU();
			GRUPOSMENU.setIidgrupo(rSet.getInt("iidgrupo"));
			GRUPOSMENU.setIidmenu(rSet.getInt("iidmenu"));
			GRUPOSMENU.setIidsistema(rSet.getInt("iidsistema"));
			result.addElement(GRUPOSMENU);
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
