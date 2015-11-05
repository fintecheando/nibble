/** Vector java bean entity **/
package dbbeans.fabricantecategoria;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Vector;


public class dbFABRICANTECATEGORIAs {
	protected Connection conn;
	protected ResultSet rSet;
	protected PreparedStatement stmt;
	protected Vector result;


	public dbFABRICANTECATEGORIAs() {
		super();
		result = new Vector();
	}
	public void setConnection(java.sql.Connection conn) {
		this.conn = conn;
	}
	public boolean find(String where) throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		dbFABRICANTECATEGORIA FABRICANTECATEGORIA;
		query.append("SELECT * ");
		query.append("FROM FABRICANTECATEGORIA ");
		if (!where.equals("")){
			query.append("WHERE " + where );
		}
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		rSet = stmt.executeQuery();


		while (rSet.next()) {
			FABRICANTECATEGORIA= new dbFABRICANTECATEGORIA();
			FABRICANTECATEGORIA.setIidusuario(rSet.getInt("iidusuario"));
			FABRICANTECATEGORIA.setIidcategoria(rSet.getInt("iidcategoria"));
			result.addElement(FABRICANTECATEGORIA);
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
