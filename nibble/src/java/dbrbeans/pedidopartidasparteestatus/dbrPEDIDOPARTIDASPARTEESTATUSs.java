package dbrbeans.pedidopartidasparteestatus;

import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Vector;

public class dbrPEDIDOPARTIDASPARTEESTATUSs {
	private Connection conn;
	private ResultSet rSet;
	private PreparedStatement stmt;
	private Vector result;

/**
 * dbrPEDIDOPARTIDASPARTEs constructor comment.
 */
public dbrPEDIDOPARTIDASPARTEESTATUSs() {
	super();
	result = new Vector();
}
public boolean findByPedido(int iidpedido, int iidproveedor)
        throws Exception, SQLException
    {
        StringBuffer query = new StringBuffer();
        query.append("SELECT ped.*, est.vchdescestatus, par.vchnumparte, (par.decpreciob100 * cpro.decfactorvaluainve) as costo ");
        query.append("FROM pedidopartidas ped ");
        query.append("INNER JOIN estatuspedidos est ON ped.tiestatus=est.tiestatus ");
        query.append("INNER JOIN parte par ON ped.liidparte=par.liidparte ");
        query.append("INNER JOIN clasificproveedor cpro ON par.iidcategoria=cpro.iidcategoria and par.iidgrupo=cpro.iidgrupo and par.iidlinea=cpro.iidlinea and par.iidmarca=cpro.iidmarca ");
        query.append("WHERE ped.iidpedido = ? and cpro.iidproveedor = ? ");
        stmt = conn.prepareStatement(query.toString().toLowerCase());
        stmt.setInt(1, iidpedido);
        stmt.setInt(2, iidproveedor);
        dbrPEDIDOPARTIDASPARTEESTATUS ppp;
        for(rSet = stmt.executeQuery(); rSet.next(); result.addElement(ppp))
        {
            ppp = new dbrPEDIDOPARTIDASPARTEESTATUS();
            ppp.setIidpedido(rSet.getInt("iidpedido"));
            ppp.setLiidparte(rSet.getLong("liidparte"));
            ppp.setVchnumparte(rSet.getString("vchnumparte"));
            ppp.setTiestatus(rSet.getInt("tiestatus"));
            ppp.setVchdescestatus(rSet.getString("vchdescestatus"));
            ppp.setIcantpedida(rSet.getInt("icantpedida"));
            ppp.setIcantrecibida(rSet.getInt("icantrecibida"));
            ppp.setVchfactura(rSet.getString("vchfactura"));
            ppp.setVchremisionproveed(rSet.getString("vchremisionproveed"));
            ppp.setDtfechultrecepcion(rSet.getString("dtfechultrecepcion"));
            ppp.setVchcomentario(rSet.getString("vchcomentario"));
            ppp.setDeccosto(rSet.getFloat("costo"));
        }

        rSet.close();
        stmt.close();
        return false;
    }
	public boolean findByPedido(int iidpedido) throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		dbrPEDIDOPARTIDASPARTEESTATUS ppp;
		query.append("SELECT c.vchnumparte,b.vchdescestatus,a.* ");
		query.append("FROM PEDIDOPARTIDAS a, ESTATUSPEDIDOS b, PARTE c ");
		query.append("WHERE a.tiestatus=b.tiestatus AND a.liidparte=c.liidparte ");
		query.append("AND a.iidpedido = ? ");

		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setInt(1,iidpedido);
		
		rSet = stmt.executeQuery();


		while (rSet.next()) {
			ppp= new dbrPEDIDOPARTIDASPARTEESTATUS();
			ppp.setIidpedido(rSet.getInt("iidpedido"));
			ppp.setLiidparte(rSet.getLong("liidparte"));
			ppp.setVchnumparte(rSet.getString("vchnumparte"));
			ppp.setTiestatus(rSet.getInt("tiestatus"));
			ppp.setVchdescestatus(rSet.getString("vchdescestatus"));
			ppp.setIcantpedida(rSet.getInt("icantpedida"));
			ppp.setIcantrecibida(rSet.getInt("icantrecibida"));
			ppp.setVchfactura(rSet.getString("vchfactura"));
			ppp.setVchremisionproveed(rSet.getString("vchremisionproveed"));
			ppp.setDtfechultrecepcion(rSet.getString("dtfechultrecepcion"));
			ppp.setVchcomentario(rSet.getString("vchcomentario"));
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
