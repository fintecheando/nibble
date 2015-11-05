/** Vector java bean entity **/
package dbbeans.niveldeprecio;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Vector;


public class dbNIVELDEPRECIOs {
	private Connection conn;
	private ResultSet rSet;
	private PreparedStatement stmt;
	private Vector result;


	public dbNIVELDEPRECIOs() {
		super();
		result = new Vector();
	}
	public void setConnection(java.sql.Connection conn) {
		this.conn = conn;
	}
	public boolean find(String where) throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		dbNIVELDEPRECIO NIVELDEPRECIO;
		query.append("SELECT * ");
		query.append("FROM NIVELDEPRECIO ");
		if (!where.equals("")){
			query.append("WHERE " + where );
		}
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		rSet = stmt.executeQuery();


		while (rSet.next()) {
			NIVELDEPRECIO= new dbNIVELDEPRECIO();
			NIVELDEPRECIO.setIidnivel(rSet.getInt("iidnivel"));
			NIVELDEPRECIO.setIidcatcliente(rSet.getInt("iidcatcliente"));
			NIVELDEPRECIO.setVchnivel(rSet.getString("vchnivel"));
			NIVELDEPRECIO.setDecutilidad(rSet.getFloat("decutilidad"));
			NIVELDEPRECIO.setDecporccomision(rSet.getFloat("decporccomision"));
			NIVELDEPRECIO.setBitdadodebaja(rSet.getBoolean("bitdadodebaja"));
			result.addElement(NIVELDEPRECIO);
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

	public boolean findByIidcatcliente(int iidcatcliente) throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		dbNIVELDEPRECIO NIVELDEPRECIO;
		query.append("SELECT * ");
		query.append("FROM NIVELDEPRECIO ");
		query.append("WHERE iidcatcliente = ? ");

		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setInt(1,iidcatcliente);
		
		rSet = stmt.executeQuery();


		while (rSet.next()) {
			NIVELDEPRECIO= new dbNIVELDEPRECIO();
			NIVELDEPRECIO.setIidnivel(rSet.getInt("iidnivel"));
			NIVELDEPRECIO.setIidcatcliente(rSet.getInt("iidcatcliente"));
			NIVELDEPRECIO.setVchnivel(rSet.getString("vchnivel"));
			NIVELDEPRECIO.setDecutilidad(rSet.getFloat("decutilidad"));
			NIVELDEPRECIO.setDecporccomision(rSet.getFloat("decporccomision"));
			NIVELDEPRECIO.setBitdadodebaja(rSet.getBoolean("bitdadodebaja"));
			result.addElement(NIVELDEPRECIO);
		}
		rSet.close();
		stmt.close();
		return false;
	}
}
