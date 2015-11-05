/** Vector java bean entity **/
package dbbeans.estado;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Vector;


public class dbESTADOs {
	private Connection conn;
	private ResultSet rSet;
	private PreparedStatement stmt;
	private Vector result;


	public dbESTADOs() {
		super();
		result = new Vector();
	}
	public void setConnection(java.sql.Connection conn) {
		this.conn = conn;
	}
	public boolean find(String where) throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		dbESTADO ESTADO;
		query.append("SELECT * ");
		query.append("FROM ESTADO ");
		if (!where.equals("")){
			query.append("WHERE " + where );
		}
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		rSet = stmt.executeQuery();


		while (rSet.next()) {
			ESTADO= new dbESTADO();
			ESTADO.setIidestado(rSet.getInt("iidestado"));
			ESTADO.setSiidpais(rSet.getShort("siidpais"));
			ESTADO.setVchnombre(rSet.getString("vchnombre"));
			result.addElement(ESTADO);
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

	public boolean findByPais(short siidpais) throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		dbESTADO ESTADO;
		query.append("SELECT * ");
		query.append("FROM ESTADO ");
		query.append("WHERE siidpais=?");
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setShort(1,siidpais);
		rSet = stmt.executeQuery();

		while (rSet.next()) {
			ESTADO= new dbESTADO();
			ESTADO.setIidestado(rSet.getInt("iidestado"));
			ESTADO.setSiidpais(rSet.getShort("siidpais"));
			ESTADO.setVchnombre(rSet.getString("vchnombre"));
			result.addElement(ESTADO);
		}
		rSet.close();
		stmt.close();
		return false;
	}
}
