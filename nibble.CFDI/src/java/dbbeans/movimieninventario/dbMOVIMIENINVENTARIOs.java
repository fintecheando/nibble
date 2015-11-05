/** Vector java bean entity **/
package dbbeans.movimieninventario;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Vector;


public class dbMOVIMIENINVENTARIOs {
	protected Connection conn;
	protected ResultSet rSet;
	protected PreparedStatement stmt;
	protected Vector result;


	public dbMOVIMIENINVENTARIOs() {
		super();
		result = new Vector();
	}
	public void setConnection(java.sql.Connection conn) {
		this.conn = conn;
	}
	public boolean find(String where) throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		dbMOVIMIENINVENTARIO MOVIMIENINVENTARIO;
		query.append("SELECT * ");
		query.append("FROM MOVIMIENINVENTARIO ");
		if (!where.equals("")){
			query.append("WHERE " + where );
		}
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		rSet = stmt.executeQuery();


		while (rSet.next()) {
			MOVIMIENINVENTARIO= new dbMOVIMIENINVENTARIO();
			MOVIMIENINVENTARIO.setIidmovinventario(rSet.getInt("iidmovinventario"));
			MOVIMIENINVENTARIO.setIidproveedor(rSet.getInt("iidproveedor"));
			MOVIMIENINVENTARIO.setLiidparte(rSet.getLong("liidparte"));
			MOVIMIENINVENTARIO.setTitipomov(rSet.getInt("titipomov"));
			MOVIMIENINVENTARIO.setTisfechamov(rSet.getString("tisfechamov"));
			MOVIMIENINVENTARIO.setDeccostob100(rSet.getFloat("deccostob100"));
			MOVIMIENINVENTARIO.setDecporciva(rSet.getFloat("decporciva"));
			MOVIMIENINVENTARIO.setDecprecioventa(rSet.getFloat("decprecioventa"));
			MOVIMIENINVENTARIO.setIidreferencia(rSet.getInt("iidreferencia"));
			MOVIMIENINVENTARIO.setIidusuario(rSet.getInt("iidusuario"));
			MOVIMIENINVENTARIO.setIcantidad(rSet.getInt("icantidad"));
			result.addElement(MOVIMIENINVENTARIO);
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
