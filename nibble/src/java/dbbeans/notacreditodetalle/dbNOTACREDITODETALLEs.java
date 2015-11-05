/** Vector java bean entity **/
package dbbeans.notacreditodetalle;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Vector;


public class dbNOTACREDITODETALLEs {
	protected Connection conn;
	protected ResultSet rSet;
	protected PreparedStatement stmt;
	protected Vector result;


	public dbNOTACREDITODETALLEs() {
		super();
		result = new Vector();
	}
	public void setConnection(java.sql.Connection conn) {
		this.conn = conn;
	}
	public boolean find(String where) throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		dbNOTACREDITODETALLE NOTACREDITODETALLE;
		query.append("SELECT * ");
		query.append("FROM NOTACREDITODETALLE ");
		if (!where.equals("")){
			query.append("WHERE " + where );
		}
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		rSet = stmt.executeQuery();


		while (rSet.next()) {
			NOTACREDITODETALLE= new dbNOTACREDITODETALLE();
			NOTACREDITODETALLE.setIidnotacredito(rSet.getInt("iidnotacredito"));
			NOTACREDITODETALLE.setLiidparte(rSet.getLong("liidparte"));
			NOTACREDITODETALLE.setIidproveedor(rSet.getInt("iidproveedor"));
			NOTACREDITODETALLE.setIcantidadproducto(rSet.getInt("icantidadproducto"));
			NOTACREDITODETALLE.setDecpreciolista(rSet.getFloat("decpreciolista"));
			NOTACREDITODETALLE.setIidnivel(rSet.getInt("iidnivel"));
			NOTACREDITODETALLE.setDeccostoproducto(rSet.getFloat("deccostoproducto"));
			NOTACREDITODETALLE.setDecivaclasific(rSet.getFloat("decivaclasific"));
			NOTACREDITODETALLE.setDecimportedevol(rSet.getFloat("decimportedevol"));
			NOTACREDITODETALLE.setLiidpartecambio(rSet.getLong("liidpartecambio"));
			NOTACREDITODETALLE.setIidproveedorcambio(rSet.getInt("iidproveedorcambio"));
			result.addElement(NOTACREDITODETALLE);
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
