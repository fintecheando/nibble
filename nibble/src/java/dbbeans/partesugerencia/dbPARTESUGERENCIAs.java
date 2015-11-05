/** Vector java bean entity **/
package dbbeans.partesugerencia;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Vector;


public class dbPARTESUGERENCIAs {
	protected Connection conn;
	protected ResultSet rSet;
	protected PreparedStatement stmt;
	protected Vector result;


	public dbPARTESUGERENCIAs() {
		super();
		result = new Vector();
	}
	public void setConnection(java.sql.Connection conn) {
		this.conn = conn;
	}
	public boolean find(String where) throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		dbPARTESUGERENCIA PARTESUGERENCIA;
		query.append("SELECT * ");
		query.append("FROM PARTESUGERENCIA ");
		if (!where.equals("")){
			query.append("WHERE " + where );
		}
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		rSet = stmt.executeQuery();


		while (rSet.next()) {
			PARTESUGERENCIA= new dbPARTESUGERENCIA();
			PARTESUGERENCIA.setIidpartesugerencia(rSet.getInt("iidpartesugerencia"));
			PARTESUGERENCIA.setTisfecha(rSet.getString("tisfecha"));
			PARTESUGERENCIA.setVchdescripcion(rSet.getString("vchdescripcion"));
			result.addElement(PARTESUGERENCIA);
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
