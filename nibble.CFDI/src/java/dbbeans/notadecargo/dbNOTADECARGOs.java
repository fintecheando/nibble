/** Vector java bean entity **/
package dbbeans.notadecargo;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Vector;


public class dbNOTADECARGOs {
	private Connection conn;
	private ResultSet rSet;
	private PreparedStatement stmt;
	private Vector result;


	public dbNOTADECARGOs() {
		super();
		result = new Vector();
	}
	public void setConnection(java.sql.Connection conn) {
		this.conn = conn;
	}
public boolean find(String where) throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		dbNOTADECARGO NOTADECARGO;
		query.append("SELECT * ");
		query.append("FROM NOTADECARGO ");
		if (!where.equals("")){
			query.append("WHERE " + where );
		}
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		rSet = stmt.executeQuery();
			
		while (rSet.next()) {
			NOTADECARGO= new dbNOTADECARGO();
			NOTADECARGO.setIidnotadecargo(rSet.getInt("iidnotadecargo"));
			NOTADECARGO.setIidnocliente(rSet.getInt("iidnocliente"));
			NOTADECARGO.setIidusuario(rSet.getInt("iidusuario"));
			NOTADECARGO.setIidmodulo(rSet.getInt("iidmodulo"));
			NOTADECARGO.setVchletra(rSet.getString("vchletra"));
			NOTADECARGO.setInumero(rSet.getInt("inumero"));
			NOTADECARGO.setTisfechanotacargo(rSet.getString("tisfechanotacargo"));
			NOTADECARGO.setTisfechavencimient(rSet.getString("tisfechavencimient"));
			NOTADECARGO.setDecimporte(rSet.getFloat("decimporte"));
			NOTADECARGO.setDecivaimporte(rSet.getFloat("decivaimporte"));
			NOTADECARGO.setVchconcepto(rSet.getString("vchconcepto"));
			result.addElement(NOTADECARGO);
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

	public Vector findByFecha(String fini, String ffin) throws Exception, java.sql.SQLException {

		Vector v = new Vector();
		StringBuffer query = new StringBuffer();
		dbNOTADECARGOr NOTADECARGO;
		query.append("select a.*, b.vchnombre from notadecargo a ");
		query.append("left join cliente b on a.iidnocliente = b.iidnocliente ");
		if (!fini.equalsIgnoreCase(""))
			query.append("WHERE (a.tisfechanotacargo >= ? AND a.tisfechanotacargo <= ?)");
	
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		
		if (!fini.equalsIgnoreCase("")){
			stmt.setString(1,fini);
			stmt.setString(2,ffin);		
		}
		
		rSet = stmt.executeQuery();
			
		while (rSet.next()) {
			NOTADECARGO= new dbNOTADECARGOr();
			NOTADECARGO.setIidnotadecargo(rSet.getInt("iidnotadecargo"));
			NOTADECARGO.setIidnocliente(rSet.getInt("iidnocliente"));
			NOTADECARGO.setIidusuario(rSet.getInt("iidusuario"));
			NOTADECARGO.setIidmodulo(rSet.getInt("iidmodulo"));
			NOTADECARGO.setVchletra(rSet.getString("vchletra"));
			NOTADECARGO.setInumero(rSet.getInt("inumero"));
			NOTADECARGO.setTisfechanotacargo(rSet.getString("tisfechanotacargo"));
			NOTADECARGO.setTisfechavencimient(rSet.getString("tisfechavencimient"));
			NOTADECARGO.setDecimporte(rSet.getFloat("decimporte"));
			NOTADECARGO.setDecivaimporte(rSet.getFloat("decivaimporte"));
			NOTADECARGO.setVchconcepto(rSet.getString("vchconcepto"));
			NOTADECARGO.setNombreCliente(rSet.getString("vchnombre"));
			v.addElement(NOTADECARGO);
		}
		rSet.close();
		stmt.close();
		return v;
	}
}
