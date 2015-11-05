/** Vector java bean entity **/
package dbbeans.notadecredito;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Vector;


public class dbNOTADECREDITOs {
	private Connection conn;
	private ResultSet rSet;
	private PreparedStatement stmt;
	private Vector result;


	public dbNOTADECREDITOs() {
		super();
		result = new Vector();
	}
	public void setConnection(java.sql.Connection conn) {
		this.conn = conn;
	}
	public boolean find(String where) throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		dbNOTADECREDITO NOTADECREDITO;
		query.append("SELECT * ");
		query.append("FROM NOTADECREDITO ");
		if (!where.equals("")){
			query.append("WHERE " + where );
		}
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		rSet = stmt.executeQuery();


		while (rSet.next()) {
			NOTADECREDITO= new dbNOTADECREDITO();
			NOTADECREDITO.setIidnotacredito(rSet.getInt("iidnotacredito"));
			NOTADECREDITO.setIidnocliente(rSet.getInt("iidnocliente"));
			NOTADECREDITO.setIidusuario(rSet.getInt("iidusuario"));
			NOTADECREDITO.setIidfactura(rSet.getInt("iidfactura"));
			NOTADECREDITO.setVchletra(rSet.getString("vchletra"));
			NOTADECREDITO.setInumero(rSet.getInt("inumero"));
			NOTADECREDITO.setTisfechanotacredit(rSet.getString("tisfechanotacredit"));
			NOTADECREDITO.setVchconcepto(rSet.getString("vchconcepto"));
			NOTADECREDITO.setDecimporte(rSet.getFloat("decimporte"));
			NOTADECREDITO.setDecivaimporte(rSet.getFloat("decivaimporte"));
			NOTADECREDITO.setTitipomov(rSet.getInt("titipomov"));
			NOTADECREDITO.setDecporcdesc(rSet.getFloat("decporcdesc"));
			NOTADECREDITO.setDecmontodesc(rSet.getFloat("decmontodesc"));
			result.addElement(NOTADECREDITO);
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

	public boolean findByfactura(int iidfactura) throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		dbNOTADECREDITO NOTADECREDITO;
		query.append("SELECT * ");
		query.append("FROM NOTADECREDITO ");
		query.append("WHERE iidfactura=? ");

		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setInt(1,iidfactura);
		
		rSet = stmt.executeQuery();

		while (rSet.next()) {
			NOTADECREDITO= new dbNOTADECREDITO();
			NOTADECREDITO.setIidnotacredito(rSet.getInt("iidnotacredito"));
			NOTADECREDITO.setIidnocliente(rSet.getInt("iidnocliente"));
			NOTADECREDITO.setIidusuario(rSet.getInt("iidusuario"));
			NOTADECREDITO.setIidfactura(rSet.getInt("iidfactura"));
			NOTADECREDITO.setVchletra(rSet.getString("vchletra"));
			NOTADECREDITO.setInumero(rSet.getInt("inumero"));
			NOTADECREDITO.setTisfechanotacredit(rSet.getString("tisfechanotacredit"));
			NOTADECREDITO.setVchconcepto(rSet.getString("vchconcepto"));
			NOTADECREDITO.setDecimporte(rSet.getFloat("decimporte"));
			NOTADECREDITO.setDecivaimporte(rSet.getFloat("decivaimporte"));
			NOTADECREDITO.setTitipomov(rSet.getInt("titipomov"));
			NOTADECREDITO.setDecporcdesc(rSet.getFloat("decporcdesc"));
			NOTADECREDITO.setDecmontodesc(rSet.getFloat("decmontodesc"));
			result.addElement(NOTADECREDITO);
		}
		rSet.close();
		stmt.close();
		return false;
	}

	public Vector findByFechaTipo(String fini, String ffin, int tiponc) throws Exception, java.sql.SQLException {

		Vector v = new Vector();
		StringBuffer query = new StringBuffer();
		dbNOTADECREDITOr NOTADECREDITO;
		query.append("select a.*, b.vchnombre from notadecredito a ");
		query.append("left join cliente b on a.iidnocliente = b.iidnocliente ");
		query.append("where a.titipomov=? ");		
		if (!fini.equalsIgnoreCase(""))
			query.append("AND (a.tisfechanotacredit >= ? AND a.tisfechanotacredit <= ?)");
	
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		
		stmt.setInt(1,tiponc);
		if (!fini.equalsIgnoreCase("")){
			stmt.setString(2,fini);
			stmt.setString(3,ffin);		
		}
		
		rSet = stmt.executeQuery();

		while (rSet.next()) {
			NOTADECREDITO= new dbNOTADECREDITOr();
			NOTADECREDITO.setIidnotacredito(rSet.getInt("iidnotacredito"));
			NOTADECREDITO.setIidnocliente(rSet.getInt("iidnocliente"));
			NOTADECREDITO.setIidusuario(rSet.getInt("iidusuario"));
			NOTADECREDITO.setIidfactura(rSet.getInt("iidfactura"));
			NOTADECREDITO.setVchletra(rSet.getString("vchletra"));
			NOTADECREDITO.setInumero(rSet.getInt("inumero"));
			NOTADECREDITO.setTisfechanotacredit(rSet.getString("tisfechanotacredit"));
			NOTADECREDITO.setVchconcepto(rSet.getString("vchconcepto"));
			NOTADECREDITO.setDecimporte(rSet.getFloat("decimporte"));
			NOTADECREDITO.setDecivaimporte(rSet.getFloat("decivaimporte"));
			NOTADECREDITO.setTitipomov(rSet.getInt("titipomov"));
			NOTADECREDITO.setDecporcdesc(rSet.getFloat("decporcdesc"));
			NOTADECREDITO.setDecmontodesc(rSet.getFloat("decmontodesc"));
			NOTADECREDITO.setNombreCliente(rSet.getString("vchnombre"));
			v.addElement(NOTADECREDITO);
		}
		rSet.close();
		stmt.close();
		return v;
	}
}
