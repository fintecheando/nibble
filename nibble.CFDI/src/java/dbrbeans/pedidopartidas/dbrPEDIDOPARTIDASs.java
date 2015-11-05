package dbrbeans.pedidopartidas;

/**
 * Insert the type's description here.
 * Creation date: (12/18/2002 4:30:34 PM)
 * @author: 
 */
public class dbrPEDIDOPARTIDASs extends dbbeans.pedidopartidas.dbPEDIDOPARTIDASs {
/**
 * dbrPEDIDOPARTIDASs constructor comment.
 */
public dbrPEDIDOPARTIDASs() {
	super();
}
	public boolean findByPedido(int iidpedido) throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		dbrPEDIDOPARTIDAS PEDIDOPARTIDAS;
		query.append("SELECT a.*, p.vchnumparte, p.vchdescripciones, p.decpreciob100 ");
		query.append("FROM PEDIDOPARTIDAS a, PARTE p ");
		query.append("WHERE a.liidparte=p.liidparte ");
		query.append("AND a.iidpedido = ? ");

		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setInt(1,iidpedido);
		
		rSet = stmt.executeQuery();

		while (rSet.next()) {
			PEDIDOPARTIDAS= new dbrPEDIDOPARTIDAS();
			PEDIDOPARTIDAS.setIidpedido(rSet.getInt("iidpedido"));
			PEDIDOPARTIDAS.setLiidparte(rSet.getLong("liidparte"));
			PEDIDOPARTIDAS.setIcantpedida(rSet.getInt("icantpedida"));
			PEDIDOPARTIDAS.setTiestatus(rSet.getInt("tiestatus"));
			PEDIDOPARTIDAS.setDtfechultrecepcion(rSet.getString("dtfechultrecepcion"));
			PEDIDOPARTIDAS.setIcantrecibida(rSet.getInt("icantrecibida"));
			PEDIDOPARTIDAS.setVchfactura(rSet.getString("vchfactura"));
			PEDIDOPARTIDAS.setVchremisionproveed(rSet.getString("vchremisionproveed"));
			PEDIDOPARTIDAS.setVchcomentario(rSet.getString("vchcomentario"));

			PEDIDOPARTIDAS.setVchnumparte(rSet.getString("vchnumparte"));
			PEDIDOPARTIDAS.setVchdescripciones(rSet.getString("vchdescripciones"));
			PEDIDOPARTIDAS.setDecpreciob100(rSet.getFloat("decpreciob100"));
			result.addElement(PEDIDOPARTIDAS);
		}
		rSet.close();
		stmt.close();
		return false;
	}
}
