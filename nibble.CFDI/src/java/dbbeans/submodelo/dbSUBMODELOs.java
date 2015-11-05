/** Vector java bean entity **/
package dbbeans.submodelo;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Vector;


public class dbSUBMODELOs {
	private Connection conn;
	private ResultSet rSet;
	private PreparedStatement stmt;
	private Vector result;


	public dbSUBMODELOs() {
		super();
		result = new Vector();
	}
	public void setConnection(java.sql.Connection conn) {
		this.conn = conn;
	}
	public boolean find(String where) throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		dbSUBMODELO SUBMODELO;
		query.append("SELECT * ");
		query.append("FROM SUBMODELO ");
		if (!where.equals("")){
			query.append("WHERE " + where );
		}
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		rSet = stmt.executeQuery();


		while (rSet.next()) {
			SUBMODELO= new dbSUBMODELO();
			SUBMODELO.setIidmodelo(rSet.getInt("iidmodelo"));
			SUBMODELO.setIidsubmodelo(rSet.getInt("iidsubmodelo"));
			SUBMODELO.setVchdescripcion(rSet.getString("vchdescripcion"));
			result.addElement(SUBMODELO);
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
