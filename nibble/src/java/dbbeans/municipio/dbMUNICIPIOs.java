/** Vector java bean entity **/
package dbbeans.municipio;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Vector;


public class dbMUNICIPIOs {
	private Connection conn;
	private ResultSet rSet;
	private PreparedStatement stmt;
	private Vector result;


	public dbMUNICIPIOs() {
		super();
		result = new Vector();
	}
	public void setConnection(java.sql.Connection conn) {
		this.conn = conn;
	}
	public boolean find(String where) throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		dbMUNICIPIO MUNICIPIO;
		query.append("SELECT * ");
		query.append("FROM MUNICIPIO ");
		if (!where.equals("")){
			query.append("WHERE " + where );
		}
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		rSet = stmt.executeQuery();


		while (rSet.next()) {
			MUNICIPIO= new dbMUNICIPIO();
			MUNICIPIO.setIidmunicipio(rSet.getInt("iidmunicipio"));
			MUNICIPIO.setIidestado(rSet.getInt("iidestado"));
			MUNICIPIO.setVchnombre(rSet.getString("vchnombre"));
			result.addElement(MUNICIPIO);
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

	public boolean findByEstado(int iidestado) throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		dbMUNICIPIO MUNICIPIO;
		query.append("SELECT * ");
		query.append("FROM MUNICIPIO ");
		query.append("WHERE iidestado=? " );
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setInt(1,iidestado);
		rSet = stmt.executeQuery();

		while (rSet.next()) {
			MUNICIPIO= new dbMUNICIPIO();
			MUNICIPIO.setIidmunicipio(rSet.getInt("iidmunicipio"));
			MUNICIPIO.setIidestado(rSet.getInt("iidestado"));
			MUNICIPIO.setVchnombre(rSet.getString("vchnombre"));
			result.addElement(MUNICIPIO);
		}
		rSet.close();
		stmt.close();
		return false;
	}
}
