package dbrbeans.proveedorinventario;

/**
 * Insert the type's description here.
 * Creation date: (11/18/2002 5:23:35 PM)
 * @author: 
 */
import java.util.Vector;
 
public class dbrPROVEEDORs extends dbbeans.proveedor.dbPROVEEDORs {
	public Vector findExistenciasProveedor(long liidparte, int iidcategoria, int iidgrupo, int iidlinea,int iidmarca) throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		Vector v = new Vector();
		dbrPROVEEDOR PROVEEDOR;
		query.append("SELECT a.*,b.iexistencia,c.bitprovefavorito,c.decfactorcostovent ");
		query.append("FROM PROVEEDOR a, INVENTARIO b, clasificproveedor c ");
		query.append("WHERE a.iidproveedor=b.iidproveedor ");
		query.append("AND b.iidproveedor=c.iidproveedor ");
		query.append("AND b.liidparte= ? ");
		query.append("AND c.iidcategoria= ? AND c.iidgrupo= ? AND c.iidlinea= ? AND c.iidmarca= ? ");
        
		stmt = conn.prepareStatement(query.toString().toLowerCase());

		stmt.setLong(1,liidparte);
		stmt.setInt(2,iidcategoria);
		stmt.setInt(3,iidgrupo);
		stmt.setInt(4,iidlinea);
		stmt.setInt(5,iidmarca);
		
		rSet = stmt.executeQuery();

		while (rSet.next()) {
			PROVEEDOR= new dbrPROVEEDOR();
			PROVEEDOR.setIidproveedor(rSet.getInt("iidproveedor"));
			PROVEEDOR.setVchnombre(rSet.getString("vchnombre"));
			PROVEEDOR.setVchrazonsocial(rSet.getString("vchrazonsocial"));
			PROVEEDOR.setVchrfc(rSet.getString("vchrfc"));
			PROVEEDOR.setVchcurp(rSet.getString("vchcurp"));
			PROVEEDOR.setBcredito(rSet.getBoolean("bcredito"));
			PROVEEDOR.setVchtel1(rSet.getString("vchtel1"));
			PROVEEDOR.setVchtel2(rSet.getString("vchtel2"));
			PROVEEDOR.setVchfax(rSet.getString("vchfax"));
			PROVEEDOR.setBitdadodebaja(rSet.getBoolean("bitdadodebaja"));
			PROVEEDOR.setVchmail(rSet.getString("vchmail"));
			PROVEEDOR.setBitaceptabackorder(rSet.getBoolean("bitaceptabackorder"));
			PROVEEDOR.setVchcolonia(rSet.getString("vchcolonia"));
			PROVEEDOR.setVchcalle(rSet.getString("vchcalle"));
			PROVEEDOR.setVchcp(rSet.getString("vchcp"));
			PROVEEDOR.setSiidpais(rSet.getShort("siidpais"));
			PROVEEDOR.setIidestado(rSet.getInt("iidestado"));
			PROVEEDOR.setIidmunicipio(rSet.getInt("iidmunicipio"));
			PROVEEDOR.setIexistencia(rSet.getInt("iexistencia"));
			PROVEEDOR.setBitprovefavorito(rSet.getBoolean("bitprovefavorito"));
			PROVEEDOR.setDecfactorcostovent(rSet.getFloat("decfactorcostovent"));
			v.addElement(PROVEEDOR);
		}
		rSet.close();
		stmt.close();
		return v;
	}
}
