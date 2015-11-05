/** Vector java bean entity **/
package dbbeans.pago;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Vector;


public class dbPAGOs {
	private Connection conn;
	private ResultSet rSet;
	private PreparedStatement stmt;
	private Vector result;


	public dbPAGOs() {
		super();
		result = new Vector();
	}
	public void setConnection(java.sql.Connection conn) {
		this.conn = conn;
	}
	public boolean find(String where) throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		dbPAGO PAGO;
		query.append("SELECT * ");
		query.append("FROM PAGO ");
		if (!where.equals("")){
			query.append("WHERE " + where );
		}
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		rSet = stmt.executeQuery();


		while (rSet.next()) {
			PAGO= new dbPAGO();
			PAGO.setIidpago(rSet.getInt("iidpago"));
			PAGO.setIidnocliente(rSet.getInt("iidnocliente"));
			PAGO.setIidusuario(rSet.getInt("iidusuario"));
			PAGO.setIidmodulo(rSet.getInt("iidmodulo"));
			PAGO.setTisfechapago(rSet.getString("tisfechapago"));
			PAGO.setTitipopago(rSet.getInt("titipopago"));
			PAGO.setDecimporte(rSet.getFloat("decimporte"));
			PAGO.setBitabono(rSet.getBoolean("bitabono"));
			PAGO.setVchletrafolio(rSet.getString("vchletrafolio"));
			PAGO.setInumerofolio(rSet.getInt("inumerofolio"));
			PAGO.setVchconcepto(rSet.getString("vchconcepto"));
			PAGO.setBitavisoing(rSet.getBoolean("bitavisoing"));
			result.addElement(PAGO);
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
