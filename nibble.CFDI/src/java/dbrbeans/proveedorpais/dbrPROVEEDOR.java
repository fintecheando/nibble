package dbrbeans.proveedorpais;

/**
 * Insert the type's description here.
 * Creation date: (11/29/2002 6:22:57 PM)
 * @author: 
 */
public class dbrPROVEEDOR extends dbbeans.proveedor.dbPROVEEDOR {
	private java.lang.String vchpais;
	private java.lang.String vchestado;
	private java.lang.String vchmunicipio;
/**
 * Insert the method's description here.
 * Creation date: (11/29/2002 6:24:12 PM)
 * @return java.lang.String
 */
public java.lang.String getVchestado() {
	return vchestado;
}
/**
 * Insert the method's description here.
 * Creation date: (11/29/2002 6:24:42 PM)
 * @return java.lang.String
 */
public java.lang.String getVchmunicipio() {
	return vchmunicipio;
}
/**
 * Insert the method's description here.
 * Creation date: (11/29/2002 6:23:49 PM)
 * @return java.lang.String
 */
public java.lang.String getVchpais() {
	return vchpais;
}
public boolean load() throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		query.append("SELECT p.*,pa.vchnompais as vchpais, e.vchnombre as vchestado, m.vchnombre as vchmunicipio ");
		query.append("FROM PROVEEDOR p, PAIS pa, ESTADO e, MUNICIPIO m ");
		query.append("WHERE p.siidpais=pa.siidpais AND p.iidestado=e.iidestado AND p.iidmunicipio=m.iidmunicipio ");
		query.append("AND bitdadodebaja=0 AND p.iidproveedor=? ");
		
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setInt(1,getIidproveedor());
		
		rSet = stmt.executeQuery();

		if (rSet.next()) {
			this.setIidproveedor(rSet.getInt("iidproveedor"));
			this.setVchnombre(rSet.getString("vchnombre"));
			this.setVchrazonsocial(rSet.getString("vchrazonsocial"));
			this.setVchrfc(rSet.getString("vchrfc"));
			this.setVchcurp(rSet.getString("vchcurp"));
			this.setBcredito(rSet.getBoolean("bcredito"));
			this.setVchtel1(rSet.getString("vchtel1"));
			this.setVchtel2(rSet.getString("vchtel2"));
			this.setVchfax(rSet.getString("vchfax"));
			this.setBitdadodebaja(rSet.getBoolean("bitdadodebaja"));
			this.setVchmail(rSet.getString("vchmail"));
			this.setBitaceptabackorder(rSet.getBoolean("bitaceptabackorder"));
			this.setVchcolonia(rSet.getString("vchcolonia"));
			this.setVchcalle(rSet.getString("vchcalle"));
			this.setVchcp(rSet.getString("vchcp"));
			this.setSiidpais(rSet.getShort("siidpais"));
			this.setIidestado(rSet.getInt("iidestado"));
			this.setIidmunicipio(rSet.getInt("iidmunicipio"));

			this.setVchpais(rSet.getString("vchpais"));
			this.setVchestado(rSet.getString("vchestado"));
			this.setVchmunicipio(rSet.getString("vchmunicipio"));
		}
		else
			throw new Exception ("No se encontro el registro");
		rSet.close();
		stmt.close();
		return false;
	}
/**
 * Insert the method's description here.
 * Creation date: (11/29/2002 6:24:12 PM)
 * @param newVchestado java.lang.String
 */
public void setVchestado(java.lang.String newVchestado) {
	vchestado = newVchestado;
}
/**
 * Insert the method's description here.
 * Creation date: (11/29/2002 6:24:42 PM)
 * @param newVchmunicipio java.lang.String
 */
public void setVchmunicipio(java.lang.String newVchmunicipio) {
	vchmunicipio = newVchmunicipio;
}
/**
 * Insert the method's description here.
 * Creation date: (11/29/2002 6:23:49 PM)
 * @param newVchpais java.lang.String
 */
public void setVchpais(java.lang.String newVchpais) {
	vchpais = newVchpais;
}
}
