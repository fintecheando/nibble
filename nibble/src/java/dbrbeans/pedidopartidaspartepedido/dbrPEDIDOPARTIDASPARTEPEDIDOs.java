package dbrbeans.pedidopartidaspartepedido;

import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Vector;

public class dbrPEDIDOPARTIDASPARTEPEDIDOs {
	private Connection conn;
	private ResultSet rSet;
	private PreparedStatement stmt;
	private Vector result;

/**
 * dbrPEDIDOPARTIDASPARTEs constructor comment.
 */
public dbrPEDIDOPARTIDASPARTEPEDIDOs() {
	super();
	result = new Vector();
}
	public boolean findByPedido(int iidpedido) throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		dbrPEDIDOPARTIDASPARTEPEDIDO ppp;
		query.append("SELECT c.vchnumparte,b.tisfechelabpedido,a.icantpedida,a.iidpedido,a.liidparte ");
		query.append("FROM PEDIDOPARTIDAS a, PEDIDO b, PARTE c ");
		query.append("WHERE a.iidpedido=b.iidpedido AND a.liidparte=c.liidparte ");
		query.append("AND a.iidpedido = ? ");

		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setInt(1,iidpedido);
		
		rSet = stmt.executeQuery();


		while (rSet.next()) {
			ppp= new dbrPEDIDOPARTIDASPARTEPEDIDO();
			ppp.setIidpedido(rSet.getInt("iidpedido"));
			ppp.setLiidparte(rSet.getLong("liidparte"));
			ppp.setVchnumparte(rSet.getString("vchnumparte"));
			ppp.setTisfechelapedido(rSet.getString("tisfechelabpedido"));
			ppp.setIcantpedida(rSet.getInt("icantpedida"));
			result.addElement(ppp);
		}
		rSet.close();
		stmt.close();
		return false;
	}
public Vector getResult(){
	return this.result;
}
public void setConnection(java.sql.Connection conn) {
	this.conn = conn;
}
public void setResult (Vector result){
	this.result = result;
}
}
