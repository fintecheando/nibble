/** Vector java bean entity **/
package dbbeans.alimentacion_combustible;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Vector;


public class dbALIMENTACION_COMBUSTIBLEs {
	private Connection conn;
	private ResultSet rSet;
	private PreparedStatement stmt;
	private Vector result;


	public dbALIMENTACION_COMBUSTIBLEs() {
		super();
		result = new Vector();
	}
	public void setConnection(java.sql.Connection conn) {
		this.conn = conn;
	}
	public boolean find(String where) throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		dbALIMENTACION_COMBUSTIBLE ALIMENTACION_COMBUSTIBLE;
		query.append("SELECT * ");
		query.append("FROM ALIMENTACION_COMBUSTIBLE ");
		if (!where.equals("")){
			query.append("WHERE " + where );
		}
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		rSet = stmt.executeQuery();


		while (rSet.next()) {
			ALIMENTACION_COMBUSTIBLE= new dbALIMENTACION_COMBUSTIBLE();
			ALIMENTACION_COMBUSTIBLE.setSialimcmbst(rSet.getShort("sialimcmbst"));
			ALIMENTACION_COMBUSTIBLE.setVchdescripcion(rSet.getString("vchdescripcion"));
			result.addElement(ALIMENTACION_COMBUSTIBLE);
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
