package dbrbeans.proveedorpais;

/**
 * Insert the type's description here.
 * Creation date: (11/29/2002 6:25:10 PM)
 * @author: 
 */
public class dbrPROVEEDORs extends dbbeans.proveedor.dbPROVEEDORs {
/**
 * dbrPROVEEDORs constructor comment.
 */
public dbrPROVEEDORs() {
	super();
}

public boolean findById(int delno, int alno) throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		dbrPROVEEDOR PROVEEDOR;
		query.append("SELECT p.*,pa.vchnompais as vchpais, e.vchnombre as vchestado, m.vchnombre as vchmunicipio ");
		query.append("FROM PROVEEDOR p, PAIS pa, ESTADO e, MUNICIPIO m ");
		query.append("WHERE p.siidpais=pa.siidpais AND p.iidestado=e.iidestado AND p.iidmunicipio=m.iidmunicipio ");
		query.append("AND bitdadodebaja=0 ");
		if (delno>0)
			query.append("AND p.iidproveedor BETWEEN ? AND ? ");
		query.append("ORDER BY iidproveedor ");			

		stmt = conn.prepareStatement(query.toString().toLowerCase());
		if (delno>0){
			stmt.setInt(1,delno);
			stmt.setInt(2,alno);
		}
		
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

			PROVEEDOR.setVchpais(rSet.getString("vchpais"));
			PROVEEDOR.setVchestado(rSet.getString("vchestado"));
			PROVEEDOR.setVchmunicipio(rSet.getString("vchmunicipio"));
			
			result.addElement(PROVEEDOR);
		}
		rSet.close();
		stmt.close();
		return false;
	}
}
