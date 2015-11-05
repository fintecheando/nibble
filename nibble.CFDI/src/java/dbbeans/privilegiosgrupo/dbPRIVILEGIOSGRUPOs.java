/** Vector java bean entity **/
package dbbeans.privilegiosgrupo;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Vector;


public class dbPRIVILEGIOSGRUPOs {
	private Connection conn;
	private ResultSet rSet;
	private PreparedStatement stmt;
	private Vector result;


	public dbPRIVILEGIOSGRUPOs() {
		super();
		result = new Vector();
	}
	public void setConnection(java.sql.Connection conn) {
		this.conn = conn;
	}
	public boolean find(String where) throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		dbPRIVILEGIOSGRUPO PRIVILEGIOSGRUPO;
		query.append("SELECT * ");
		query.append("FROM PRIVILEGIOSGRUPO ");
		if (!where.equals("")){
			query.append("WHERE " + where );
		}
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		rSet = stmt.executeQuery();


		while (rSet.next()) {
			PRIVILEGIOSGRUPO= new dbPRIVILEGIOSGRUPO();
			PRIVILEGIOSGRUPO.setIidgrupo(rSet.getInt("iidgrupo"));
			PRIVILEGIOSGRUPO.setIidsistema(rSet.getInt("iidsistema"));
			PRIVILEGIOSGRUPO.setVchnombregrupo(rSet.getString("vchnombregrupo"));
			PRIVILEGIOSGRUPO.setVchdescripcion(rSet.getString("vchdescripcion"));
			PRIVILEGIOSGRUPO.setVchcolor(rSet.getString("vchcolor"));
			PRIVILEGIOSGRUPO.setVchmenu(rSet.getString("vchmenu"));
			result.addElement(PRIVILEGIOSGRUPO);
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

	public Vector findBySistema(int sistema) throws Exception, java.sql.SQLException {

		Vector v = new Vector();
		StringBuffer query = new StringBuffer();
		dbPRIVILEGIOSGRUPO PRIVILEGIOSGRUPO;
		query.append("SELECT * FROM PRIVILEGIOSGRUPO ");
		query.append("WHERE IIDSISTEMA = ? " );

		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setInt(1,sistema);		
		rSet = stmt.executeQuery();


		while (rSet.next()) {
			PRIVILEGIOSGRUPO= new dbPRIVILEGIOSGRUPO();
			PRIVILEGIOSGRUPO.setIidgrupo(rSet.getInt("iidgrupo"));
			PRIVILEGIOSGRUPO.setIidsistema(rSet.getInt("iidsistema"));
			PRIVILEGIOSGRUPO.setVchnombregrupo(rSet.getString("vchnombregrupo"));
			PRIVILEGIOSGRUPO.setVchdescripcion(rSet.getString("vchdescripcion"));
			PRIVILEGIOSGRUPO.setVchcolor(rSet.getString("vchcolor"));
			PRIVILEGIOSGRUPO.setVchmenu(rSet.getString("vchmenu"));
			v.addElement(PRIVILEGIOSGRUPO);
		}
		rSet.close();
		stmt.close();
		return v;
	}
}
