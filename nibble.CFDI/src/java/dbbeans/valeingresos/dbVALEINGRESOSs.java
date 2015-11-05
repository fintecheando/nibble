/** Vector java bean entity **/
package dbbeans.valeingresos;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Vector;


public class dbVALEINGRESOSs {
	private Connection conn;
	private ResultSet rSet;
	private PreparedStatement stmt;
	private Vector result;


	public dbVALEINGRESOSs() {
		super();
		result = new Vector();
	}
	public void setConnection(java.sql.Connection conn) {
		this.conn = conn;
	}
	public boolean find(String where) throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		dbVALEINGRESOS VALEINGRESOS;
		query.append("SELECT * ");
		query.append("FROM VALEINGRESOS ");
		if (!where.equals("")){
			query.append("WHERE " + where );
		}
		stmt = conn.prepareStatement(query.toString());
		rSet = stmt.executeQuery();


		while (rSet.next()) {
			VALEINGRESOS= new dbVALEINGRESOS();
			VALEINGRESOS.setIidvaleingresos(rSet.getInt("iidvaleingresos"));
			VALEINGRESOS.setIidvale(rSet.getInt("iidvale"));
			VALEINGRESOS.setIidavisoingreso(rSet.getInt("iidavisoingreso"));
			VALEINGRESOS.setDecimporte(rSet.getFloat("decimporte"));
			result.addElement(VALEINGRESOS);
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
