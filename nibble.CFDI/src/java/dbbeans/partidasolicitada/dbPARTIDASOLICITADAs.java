/** Vector java bean entity **/
package dbbeans.partidasolicitada;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Vector;


public class dbPARTIDASOLICITADAs {
	protected Connection conn;
	protected ResultSet rSet;
	protected PreparedStatement stmt;
	protected Vector result;


	public dbPARTIDASOLICITADAs() {
		super();
		result = new Vector();
	}
	public void setConnection(java.sql.Connection conn) {
		this.conn = conn;
	}
	public boolean find(String where) throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		dbPARTIDASOLICITADA PARTIDASOLICITADA;
		query.append("SELECT * ");
		query.append("FROM PARTIDASOLICITADA ");
		if (!where.equals("")){
			query.append("WHERE " + where );
		}
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		rSet = stmt.executeQuery();


		while (rSet.next()) {
			PARTIDASOLICITADA= new dbPARTIDASOLICITADA();
			PARTIDASOLICITADA.setIidpartidasolicit(rSet.getInt("iidpartidasolicit"));
			PARTIDASOLICITADA.setLiidparte(rSet.getLong("liidparte"));
			PARTIDASOLICITADA.setIidproveedor(rSet.getInt("iidproveedor"));
			PARTIDASOLICITADA.setDtfechasolicitud(rSet.getString("dtfechasolicitud"));
			PARTIDASOLICITADA.setIcantidadpedida(rSet.getInt("icantidadpedida"));
			result.addElement(PARTIDASOLICITADA);
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

	public int getNoEnviados(long liidparte, int iidproveedor) throws Exception, java.sql.SQLException {
		int result=0;
		StringBuffer query = new StringBuffer();

		query.append("SELECT  sum(icantidadpedida) ");
		query.append("FROM PARTIDASOLICITADA ");
		query.append("WHERE liidparte=? AND iidproveedor=? ");

		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setLong(1,liidparte);
		stmt.setInt(2,iidproveedor);
		
		rSet = stmt.executeQuery();
		
		if (rSet.next()) {
			result = rSet.getInt(1);
		}
		rSet.close();
		stmt.close();
		return result;
	}
}
