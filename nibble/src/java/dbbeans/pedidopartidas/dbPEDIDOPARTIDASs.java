package dbbeans.pedidopartidas;

import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Vector;


public class dbPEDIDOPARTIDASs {
	protected Connection conn;
	protected ResultSet rSet;
	protected PreparedStatement stmt;
	protected Vector result;


	public dbPEDIDOPARTIDASs() {
		super();
		result = new Vector();
	}
	public boolean deleteByIidpedido(int iidpedido) throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		query.append("DELETE ");
		query.append("FROM PEDIDOPARTIDAS ");
		query.append("WHERE iidpedido=? ");

		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setInt(1,iidpedido);
		
		stmt.executeUpdate();

		stmt.close();
		return false;
	}
	public boolean find(String where) throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		dbPEDIDOPARTIDAS PEDIDOPARTIDAS;
		query.append("SELECT * ");
		query.append("FROM PEDIDOPARTIDAS ");
		if (!where.equals("")){
			query.append("WHERE " + where );
		}
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		rSet = stmt.executeQuery();


		while (rSet.next()) {
			PEDIDOPARTIDAS= new dbPEDIDOPARTIDAS();
			PEDIDOPARTIDAS.setIidpedido(rSet.getInt("iidpedido"));
			PEDIDOPARTIDAS.setLiidparte(rSet.getLong("liidparte"));
			PEDIDOPARTIDAS.setIcantpedida(rSet.getInt("icantpedida"));
			PEDIDOPARTIDAS.setTiestatus(rSet.getInt("tiestatus"));
			PEDIDOPARTIDAS.setDtfechultrecepcion(rSet.getString("dtfechultrecepcion"));
			PEDIDOPARTIDAS.setIcantrecibida(rSet.getInt("icantrecibida"));
			PEDIDOPARTIDAS.setVchfactura(rSet.getString("vchfactura"));
			PEDIDOPARTIDAS.setVchremisionproveed(rSet.getString("vchremisionproveed"));
			PEDIDOPARTIDAS.setVchcomentario(rSet.getString("vchcomentario"));
			result.addElement(PEDIDOPARTIDAS);
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
