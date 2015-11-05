/** Vector java bean entity **/
package dbbeans.estatuspedidos;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Vector;


public class dbESTATUSPEDIDOSs {
	private Connection conn;
	private ResultSet rSet;
	private PreparedStatement stmt;
	private Vector result;


	public dbESTATUSPEDIDOSs() {
		super();
		result = new Vector();
	}
	public void setConnection(java.sql.Connection conn) {
		this.conn = conn;
	}
	public boolean find(String where) throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		dbESTATUSPEDIDOS ESTATUSPEDIDOS;
		query.append("SELECT * ");
		query.append("FROM ESTATUSPEDIDOS ");
		if (!where.equals("")){
			query.append("WHERE " + where );
		}
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		rSet = stmt.executeQuery();


		while (rSet.next()) {
			ESTATUSPEDIDOS= new dbESTATUSPEDIDOS();
			ESTATUSPEDIDOS.setTiestatus(rSet.getInt("tiestatus"));
			ESTATUSPEDIDOS.setVchdescestatus(rSet.getString("vchdescestatus"));
			result.addElement(ESTATUSPEDIDOS);
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
