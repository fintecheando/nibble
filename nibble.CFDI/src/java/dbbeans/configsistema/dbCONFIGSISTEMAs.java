/** Vector java bean entity **/
package dbbeans.configsistema;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Vector;


public class dbCONFIGSISTEMAs {
	private Connection conn;
	private ResultSet rSet;
	private PreparedStatement stmt;
	private Vector result;


	public dbCONFIGSISTEMAs() {
		super();
		result = new Vector();
	}
	public void setConnection(java.sql.Connection conn) {
		this.conn = conn;
	}
	public boolean find(String where) throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		dbCONFIGSISTEMA CONFIGSISTEMA;
		query.append("SELECT * ");
		query.append("FROM CONFIGSISTEMA ");
		if (!where.equals("")){
			query.append("WHERE " + where );
		}
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		rSet = stmt.executeQuery();


		while (rSet.next()) {
			CONFIGSISTEMA= new dbCONFIGSISTEMA();
			CONFIGSISTEMA.setIidconfsistema(rSet.getInt("iidconfsistema"));
			CONFIGSISTEMA.setDecparidaddolar(rSet.getFloat("decparidaddolar"));
			CONFIGSISTEMA.setDecivaaplicable(rSet.getFloat("decivaaplicable"));
			CONFIGSISTEMA.setBitutilsobreventa(rSet.getBoolean("bitutilsobreventa"));
			CONFIGSISTEMA.setVchdirproperties(rSet.getString("vchdirproperties"));
			CONFIGSISTEMA.setVchcredentialfile(rSet.getString("vchcredentialfile"));
			CONFIGSISTEMA.setVchuipropertifile(rSet.getString("vchuipropertifile"));
			CONFIGSISTEMA.setIctemostradorsiva(rSet.getInt("ictemostradorsiva"));
			CONFIGSISTEMA.setIctemostradorciva(rSet.getInt("ictemostradorciva"));
			CONFIGSISTEMA.setVchpaisdefault(rSet.getString("vchpaisdefault"));
			CONFIGSISTEMA.setVchestadodefault(rSet.getString("vchestadodefault"));
			CONFIGSISTEMA.setVchviaembdef(rSet.getString("vchviaembdef"));
			CONFIGSISTEMA.setVchnivelpreciodef(rSet.getString("vchnivelpreciodef"));
			CONFIGSISTEMA.setImodoredondeo(rSet.getInt("imodoredondeo"));
			CONFIGSISTEMA.setIpresicdivision(rSet.getInt("ipresicdivision"));
			CONFIGSISTEMA.setBitredondear(rSet.getBoolean("bitredondear"));
			CONFIGSISTEMA.setIprecision(rSet.getInt("iprecision"));
			result.addElement(CONFIGSISTEMA);
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
