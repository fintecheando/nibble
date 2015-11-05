/** Vector java bean entity **/
package dbbeans.categoriacliente;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Vector;


public class dbCATEGORIACLIENTEs {
	private Connection conn;
	private ResultSet rSet;
	private PreparedStatement stmt;
	private Vector result;


	public dbCATEGORIACLIENTEs() {
		super();
		result = new Vector();
	}
	public void setConnection(java.sql.Connection conn) {
		this.conn = conn;
	}
	public boolean find(String where) throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		dbCATEGORIACLIENTE CATEGORIACLIENTE;
		query.append("SELECT * ");
		query.append("FROM CATEGORIACLIENTE ");
		if (!where.equals("")){
			query.append("WHERE " + where );
		}
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		rSet = stmt.executeQuery();


		while (rSet.next()) {
			CATEGORIACLIENTE= new dbCATEGORIACLIENTE();
			CATEGORIACLIENTE.setIidcatcliente(rSet.getInt("iidcatcliente"));
			CATEGORIACLIENTE.setVchdescripcion(rSet.getString("vchdescripcion"));
			result.addElement(CATEGORIACLIENTE);
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
