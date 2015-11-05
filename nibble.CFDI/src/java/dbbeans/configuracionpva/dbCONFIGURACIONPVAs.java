/** Vector java bean entity **/
package dbbeans.configuracionpva;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Vector;


public class dbCONFIGURACIONPVAs {
	protected Connection conn;
	protected ResultSet rSet;
	protected PreparedStatement stmt;
	protected Vector result;


	public dbCONFIGURACIONPVAs() {
		super();
		result = new Vector();
	}
	public void setConnection(java.sql.Connection conn) {
		this.conn = conn;
	}
	public boolean find(String where) throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		dbCONFIGURACIONPVA CONFIGURACIONPVA;
		query.append("SELECT * ");
		query.append("FROM CONFIGURACIONPVA ");
		if (!where.equals("")){
			query.append("WHERE " + where );
		}
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		rSet = stmt.executeQuery();


		while (rSet.next()) {
			CONFIGURACIONPVA= new dbCONFIGURACIONPVA();
			CONFIGURACIONPVA.setIidconfpva(rSet.getInt("iidconfpva"));
			CONFIGURACIONPVA.setChletrafactura(rSet.getString("chletrafactura"));
			CONFIGURACIONPVA.setInumerofactura(rSet.getInt("inumerofactura"));
			CONFIGURACIONPVA.setInumeronc(rSet.getInt("inumeronc"));
			CONFIGURACIONPVA.setInumeroncm(rSet.getInt("inumeroncm"));
			CONFIGURACIONPVA.setVchnombrepva(rSet.getString("vchnombrepva"));
			CONFIGURACIONPVA.setDtfechainstalacion(rSet.getString("dtfechainstalacion"));
			CONFIGURACIONPVA.setSinorengfac(rSet.getShort("sinorengfac"));
			CONFIGURACIONPVA.setSinorengncr(rSet.getShort("sinorengncr"));
			CONFIGURACIONPVA.setSinorengnca(rSet.getShort("sinorengnca"));
			CONFIGURACIONPVA.setSinorengavi(rSet.getShort("sinorengavi"));
			result.addElement(CONFIGURACIONPVA);
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
