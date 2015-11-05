/** Vector java bean entity **/
package dbbeans.notadecambio;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Vector;


public class dbNOTADECAMBIOs {
	protected Connection conn;
	protected ResultSet rSet;
	protected PreparedStatement stmt;
	protected Vector result;


	public dbNOTADECAMBIOs() {
		super();
		result = new Vector();
	}
	public void setConnection(java.sql.Connection conn) {
		this.conn = conn;
	}
	public boolean find(String where) throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		dbNOTADECAMBIO NOTADECAMBIO;
		query.append("SELECT * ");
		query.append("FROM NOTADECAMBIO ");
		if (!where.equals("")){
			query.append("WHERE " + where );
		}
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		rSet = stmt.executeQuery();


		while (rSet.next()) {
			NOTADECAMBIO= new dbNOTADECAMBIO();
			NOTADECAMBIO.setIidnotacambio(rSet.getInt("iidnotacambio"));
			NOTADECAMBIO.setIidnocliente(rSet.getInt("iidnocliente"));
			NOTADECAMBIO.setIidusuario(rSet.getInt("iidusuario"));
			NOTADECAMBIO.setIidfactura(rSet.getInt("iidfactura"));
			NOTADECAMBIO.setTisfechanotacambio(rSet.getString("tisfechanotacambio"));
			NOTADECAMBIO.setDecimporte(rSet.getFloat("decimporte"));
			NOTADECAMBIO.setDecivaimporte(rSet.getFloat("decivaimporte"));
			NOTADECAMBIO.setTitipomov(rSet.getInt("titipomov"));
			NOTADECAMBIO.setDecporcdesc(rSet.getFloat("decporcdesc"));
			NOTADECAMBIO.setDecmontodesc(rSet.getFloat("decmontodesc"));
			result.addElement(NOTADECAMBIO);
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
