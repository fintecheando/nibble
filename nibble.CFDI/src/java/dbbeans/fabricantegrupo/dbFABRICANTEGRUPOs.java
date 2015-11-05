/** Vector java bean entity **/
package dbbeans.fabricantegrupo;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Vector;


public class dbFABRICANTEGRUPOs {
	protected Connection conn;
	protected ResultSet rSet;
	protected PreparedStatement stmt;
	protected Vector result;


	public dbFABRICANTEGRUPOs() {
		super();
		result = new Vector();
	}
	public void setConnection(java.sql.Connection conn) {
		this.conn = conn;
	}
	public boolean find(String where) throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		dbFABRICANTEGRUPO FABRICANTEGRUPO;
		query.append("SELECT * ");
		query.append("FROM FABRICANTEGRUPO ");
		if (!where.equals("")){
			query.append("WHERE " + where );
		}
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		rSet = stmt.executeQuery();


		while (rSet.next()) {
			FABRICANTEGRUPO= new dbFABRICANTEGRUPO();
			FABRICANTEGRUPO.setIidusuario(rSet.getInt("iidusuario"));
			FABRICANTEGRUPO.setIidgrupo(rSet.getInt("iidgrupo"));
			result.addElement(FABRICANTEGRUPO);
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
