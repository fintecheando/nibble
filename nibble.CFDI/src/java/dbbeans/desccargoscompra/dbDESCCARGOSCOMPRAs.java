/** Vector java bean entity **/
package dbbeans.desccargoscompra;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Vector;


public class dbDESCCARGOSCOMPRAs {
	private Connection conn;
	private ResultSet rSet;
	private PreparedStatement stmt;
	private Vector result;


	public dbDESCCARGOSCOMPRAs() {
		super();
		result = new Vector();
	}
	public void setConnection(java.sql.Connection conn) {
		this.conn = conn;
	}
	public boolean find(String where) throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		dbDESCCARGOSCOMPRA DESCCARGOSCOMPRA;
		query.append("SELECT * ");
		query.append("FROM DESCCARGOSCOMPRA ");
		if (!where.equals("")){
			query.append("WHERE " + where );
		}
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		rSet = stmt.executeQuery();


		while (rSet.next()) {
			DESCCARGOSCOMPRA= new dbDESCCARGOSCOMPRA();
			DESCCARGOSCOMPRA.setIidproveedor(rSet.getInt("iidproveedor"));
			DESCCARGOSCOMPRA.setIidcategoria(rSet.getInt("iidcategoria"));
			DESCCARGOSCOMPRA.setIidgrupo(rSet.getInt("iidgrupo"));
			DESCCARGOSCOMPRA.setIidlinea(rSet.getInt("iidlinea"));
			DESCCARGOSCOMPRA.setIidmarca(rSet.getInt("iidmarca"));
			DESCCARGOSCOMPRA.setVchleyenda(rSet.getString("vchleyenda"));
			DESCCARGOSCOMPRA.setDecfactor(rSet.getFloat("decfactor"));
			result.addElement(DESCCARGOSCOMPRA);
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

	public boolean findByPar(int iidproveedor, int iidcategoria, int iidgrupo, int iidlinea, int iidmarca) throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		dbDESCCARGOSCOMPRA DESCCARGOSCOMPRA;
		query.append("SELECT * ");
		query.append("FROM DESCCARGOSCOMPRA ");
		query.append("WHERE iidproveedor=? and iidcategoria=? and iidgrupo=? and iidlinea=? and iidmarca=? ");

		stmt = conn.prepareStatement(query.toString().toLowerCase());

		stmt.setInt(1,iidproveedor);
		stmt.setInt(2,iidcategoria);
		stmt.setInt(3,iidgrupo);
		stmt.setInt(4,iidlinea);
		stmt.setInt(5,iidmarca);
		
		rSet = stmt.executeQuery();

		while (rSet.next()) {
			DESCCARGOSCOMPRA= new dbDESCCARGOSCOMPRA();
			DESCCARGOSCOMPRA.setIidproveedor(rSet.getInt("iidproveedor"));
			DESCCARGOSCOMPRA.setIidcategoria(rSet.getInt("iidcategoria"));
			DESCCARGOSCOMPRA.setIidgrupo(rSet.getInt("iidgrupo"));
			DESCCARGOSCOMPRA.setIidlinea(rSet.getInt("iidlinea"));
			DESCCARGOSCOMPRA.setIidmarca(rSet.getInt("iidmarca"));
			DESCCARGOSCOMPRA.setVchleyenda(rSet.getString("vchleyenda"));
			DESCCARGOSCOMPRA.setDecfactor(rSet.getFloat("decfactor"));
			result.addElement(DESCCARGOSCOMPRA);
		}
		rSet.close();
		stmt.close();
		return false;
	}
}
