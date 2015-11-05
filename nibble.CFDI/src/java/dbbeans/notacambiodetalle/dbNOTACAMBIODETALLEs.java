/** Vector java bean entity **/
package dbbeans.notacambiodetalle;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Vector;


public class dbNOTACAMBIODETALLEs {
	protected Connection conn;
	protected ResultSet rSet;
	protected PreparedStatement stmt;
	protected Vector result;


	public dbNOTACAMBIODETALLEs() {
		super();
		result = new Vector();
	}
	public void setConnection(java.sql.Connection conn) {
		this.conn = conn;
	}
	public boolean find(String where) throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		dbNOTACAMBIODETALLE NOTACAMBIODETALLE;
		query.append("SELECT * ");
		query.append("FROM NOTACAMBIODETALLE ");
		if (!where.equals("")){
			query.append("WHERE " + where );
		}
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		rSet = stmt.executeQuery();


		while (rSet.next()) {
			NOTACAMBIODETALLE= new dbNOTACAMBIODETALLE();
			NOTACAMBIODETALLE.setIidnotacambio(rSet.getInt("iidnotacambio"));
			NOTACAMBIODETALLE.setLiidparte(rSet.getLong("liidparte"));
			NOTACAMBIODETALLE.setIidproveedor(rSet.getInt("iidproveedor"));
			NOTACAMBIODETALLE.setIcantidadproducto(rSet.getInt("icantidadproducto"));
			NOTACAMBIODETALLE.setDecpreciolista(rSet.getFloat("decpreciolista"));
			NOTACAMBIODETALLE.setIidnivel(rSet.getInt("iidnivel"));
			NOTACAMBIODETALLE.setDeccostoproducto(rSet.getFloat("deccostoproducto"));
			NOTACAMBIODETALLE.setDecivaclasific(rSet.getFloat("decivaclasific"));
			NOTACAMBIODETALLE.setDecimportedevol(rSet.getFloat("decimportedevol"));
			result.addElement(NOTACAMBIODETALLE);
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
