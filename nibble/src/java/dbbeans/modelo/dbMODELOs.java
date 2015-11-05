/** Vector java bean entity **/
package dbbeans.modelo;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Vector;


public class dbMODELOs {
	private Connection conn;
	private ResultSet rSet;
	private PreparedStatement stmt;
	private Vector result;


	public dbMODELOs() {
		super();
		result = new Vector();
	}
	public void setConnection(java.sql.Connection conn) {
		this.conn = conn;
	}
	public boolean find(String where) throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		dbMODELO MODELO;
		query.append("SELECT * ");
		query.append("FROM MODELO ");
		if (!where.equals("")){
			query.append("WHERE " + where );
		}
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		rSet = stmt.executeQuery();


		while (rSet.next()) {
			MODELO= new dbMODELO();
			MODELO.setIidmodelo(rSet.getInt("iidmodelo"));
			MODELO.setIidplanta(rSet.getInt("iidplanta"));
			MODELO.setVchdescripcion(rSet.getString("vchdescripcion"));
			result.addElement(MODELO);
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
