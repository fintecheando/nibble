/** Vector java bean entity **/
package dbbeans.hisconsumo;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Vector;


public class dbHISCONSUMOs {
	private Connection conn;
	private ResultSet rSet;
	private PreparedStatement stmt;
	private Vector result;


	public dbHISCONSUMOs() {
		super();
		result = new Vector();
	}
	public void setConnection(java.sql.Connection conn) {
		this.conn = conn;
	}
	public boolean find(String where) throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		dbHISCONSUMO HISCONSUMO;
		query.append("SELECT * ");
		query.append("FROM HISCONSUMO ");
		if (!where.equals("")){
			query.append("WHERE " + where );
		}
		stmt = conn.prepareStatement(query.toString());
		rSet = stmt.executeQuery();


		while (rSet.next()) {
			HISCONSUMO= new dbHISCONSUMO();
			HISCONSUMO.setLiidparte(rSet.getLong("liidparte"));
			HISCONSUMO.setIidproveedor(rSet.getInt("iidproveedor"));
			HISCONSUMO.setMm(rSet.getInt("mm"));
			HISCONSUMO.setYy(rSet.getInt("yy"));
			HISCONSUMO.setCantidad(rSet.getLong("cantidad"));
			result.addElement(HISCONSUMO);
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
