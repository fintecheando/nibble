/** Vector java bean entity **/
package dbbeans.criteriopedido;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Vector;


public class dbCRITERIOPEDIDOs {
	protected Connection conn;
	protected ResultSet rSet;
	protected PreparedStatement stmt;
	protected Vector result;


	public dbCRITERIOPEDIDOs() {
		super();
		result = new Vector();
	}
	public void setConnection(java.sql.Connection conn) {
		this.conn = conn;
	}
	public boolean find(String where) throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		dbCRITERIOPEDIDO CRITERIOPEDIDO;
		query.append("SELECT * ");
		query.append("FROM CRITERIOPEDIDO ");
		if (!where.equals("")){
			query.append("WHERE " + where );
		}
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		rSet = stmt.executeQuery();


		while (rSet.next()) {
			CRITERIOPEDIDO= new dbCRITERIOPEDIDO();
			CRITERIOPEDIDO.setIidcategoria(rSet.getInt("iidcategoria"));
			CRITERIOPEDIDO.setIidgrupo(rSet.getInt("iidgrupo"));
			CRITERIOPEDIDO.setIidlinea(rSet.getInt("iidlinea"));
			CRITERIOPEDIDO.setIidmarca(rSet.getInt("iidmarca"));
			CRITERIOPEDIDO.setInodiasinventario(rSet.getInt("inodiasinventario"));
			result.addElement(CRITERIOPEDIDO);
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
