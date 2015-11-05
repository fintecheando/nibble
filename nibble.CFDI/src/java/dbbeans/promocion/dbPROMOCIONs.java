//** Vector java bean entity **/
package dbbeans.promocion;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Vector;


public class dbPROMOCIONs {
	private Connection conn;
	private ResultSet rSet;
	private PreparedStatement stmt;
	private Vector result;


	public dbPROMOCIONs() {
		super();
		result = new Vector();
	}
	public void setConnection(java.sql.Connection conn) {
		this.conn = conn;
	}
	public boolean find(String where) throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		dbPROMOCION PROMOCION;
		/*query.append("SELECT * ");
				query.append("FROM PROMOCION ");*/
		query.append(" SELECT ");  
				query.append(" iidcategoria, ");
				query.append(" iidgrupo, ");
				query.append(" iidlinea, ");
				query.append(" iidmarca, ");
				query.append(" decvolumencompra, ");
				query.append(" sinopiezas, ");
				query.append(" iexistencias, ");
				query.append(" vchdescripcion, ");
				query.append(" DATE_FORMAT(tisfechainicio, '%d/%m/%Y') AS tisfechainicio, "); 
				query.append(" DATE_FORMAT(tisfechafin, '%d/%m/%Y') AS tisfechafin " );
				query.append(" FROM promocion ");
		
		if (!where.equals("")){
			query.append("WHERE " + where );
		}
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		rSet = stmt.executeQuery();


		while (rSet.next()) {
			PROMOCION= new dbPROMOCION();
			PROMOCION.setIidpromocion(rSet.getInt("iidpromocion"));
			PROMOCION.setIidcategoria(rSet.getInt("iidcategoria"));
			PROMOCION.setIidgrupo(rSet.getInt("iidgrupo"));
			PROMOCION.setIidlinea(rSet.getInt("iidlinea"));
			PROMOCION.setIidmarca(rSet.getInt("iidmarca"));
			PROMOCION.setDecvolumencompra(rSet.getFloat("decvolumencompra"));
			PROMOCION.setSinopiezas(rSet.getShort("sinopiezas"));
			PROMOCION.setIexistencias(rSet.getInt("iexistencias"));
			PROMOCION.setVchdescripcion(rSet.getString("vchdescripcion"));
			PROMOCION.setTisfechainicio(rSet.getString("tisfechainicio"));
			PROMOCION.setTisfechafin(rSet.getString("tisfechafin"));
			result.addElement(PROMOCION);
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

	public Vector findByClasificacion(int cat, int gru, int lin, int mar) throws Exception, java.sql.SQLException {
		Vector v = new Vector();
		StringBuffer query = new StringBuffer();
		dbPROMOCION PROMOCION;
		query.append("SELECT * FROM PROMOCION WHERE ");
		query.append("IIDCATEGORIA = ? AND IIDGRUPO = ? AND IIDLINEA = ? AND IIDMARCA = ?");
			
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setInt(1,cat);
		stmt.setInt(2,gru);
		stmt.setInt(3,lin);
		stmt.setInt(4,mar);		
		rSet = stmt.executeQuery();

		while (rSet.next()) {
			PROMOCION= new dbPROMOCION();
			PROMOCION.setIidpromocion(rSet.getInt("iidpromocion"));
			PROMOCION.setIidcategoria(rSet.getInt("iidcategoria"));
			PROMOCION.setIidgrupo(rSet.getInt("iidgrupo"));
			PROMOCION.setIidlinea(rSet.getInt("iidlinea"));
			PROMOCION.setIidmarca(rSet.getInt("iidmarca"));
			PROMOCION.setDecvolumencompra(rSet.getFloat("decvolumencompra"));
			PROMOCION.setSinopiezas(rSet.getShort("sinopiezas"));
			PROMOCION.setIexistencias(rSet.getInt("iexistencias"));
			PROMOCION.setVchdescripcion(rSet.getString("vchdescripcion"));
			PROMOCION.setTisfechainicio(rSet.getString("tisfechainicio"));
			PROMOCION.setTisfechafin(rSet.getString("tisfechafin"));
			v.addElement(PROMOCION);
		}
		rSet.close();
		stmt.close();
		return v;
	}
}
