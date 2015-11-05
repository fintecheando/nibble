package dbbeans.proveedor;

import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Vector;


public class dbPROVEEDORs {
	protected Connection conn;
	protected ResultSet rSet;
	protected PreparedStatement stmt;
	protected Vector result;


	public dbPROVEEDORs() {
		super();
		result = new Vector();
	}
	public boolean find(String where) throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		dbPROVEEDOR PROVEEDOR;
		query.append("SELECT * ");
		query.append("FROM PROVEEDOR ");
		if (!where.equals("")){
			query.append("WHERE " + where );
		}
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		rSet = stmt.executeQuery();


		while (rSet.next()) {
			PROVEEDOR= new dbPROVEEDOR();
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
			result.addElement(PROVEEDOR);
		}
		rSet.close();
		stmt.close();
		return false;
	}
    public Vector findByParte(long liidparte)
        throws Exception, SQLException
    {
        StringBuffer query = new StringBuffer();
        Vector v = new Vector();
        query.append("SELECT p.* ");
        query.append("FROM proveedor p,clasificproveedor cp, parte pt ");
        query.append("WHERE cp.iidproveedor = p.iidproveedor and pt.liidparte=? ");
        query.append("AND cp.iidcategoria = pt.iidcategoria AND cp.iidgrupo=pt.iidgrupo ");
        query.append("AND cp.iidlinea = pt.iidlinea AND cp.iidmarca = pt.iidmarca ");
        stmt = conn.prepareStatement(query.toString().toLowerCase());
        stmt.clearParameters();
        stmt.setLong(1, liidparte);
        dbPROVEEDOR PROVEEDOR;
        for(rSet = stmt.executeQuery(); rSet.next(); v.addElement(PROVEEDOR))
        {
            PROVEEDOR = new dbPROVEEDOR();
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
        }

        return v;
    }
	public Vector findByClasificacion(int iidcategoria, int iidgrupo, int iidlinea, int iidmarca) throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		Vector v = new Vector();
		
		dbPROVEEDOR PROVEEDOR;
		query.append("SELECT a.* ");
		query.append("FROM PROVEEDOR a,CLASIFICPROVEEDOR b ");
		query.append("WHERE a.iidproveedor=b.iidproveedor and iidcategoria=? and iidgrupo=? and iidlinea=? and iidmarca=? ");
		
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.clearParameters();
		stmt.setInt(1,iidcategoria);
		stmt.setInt(2,iidgrupo);
		stmt.setInt(3,iidlinea);
		stmt.setInt(4,iidmarca);
		
		rSet = stmt.executeQuery();


		while (rSet.next()) {
			PROVEEDOR= new dbPROVEEDOR();
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
			v.addElement(PROVEEDOR);
		}
		rSet.close();
		stmt.close();
		return v;
	}
	public boolean findByNotClasificacion(int iidcategoria, int iidgrupo, int iidlinea, int iidmarca) throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		dbPROVEEDOR PROVEEDOR;
		query.append("SELECT a.* ");
		query.append("FROM PROVEEDOR a ");
		query.append("LEFT JOIN CLASIFICPROVEEDOR b ");
		query.append("ON a.iidproveedor=b.iidproveedor and iidcategoria=? and iidgrupo=? and iidlinea=? and iidmarca=? ");
		query.append("WHERE b.iidproveedor IS NULL");

		stmt = conn.prepareStatement(query.toString().toLowerCase());

		stmt.setInt(1,iidcategoria);
		stmt.setInt(2,iidgrupo);
		stmt.setInt(3,iidlinea);
		stmt.setInt(4,iidmarca);
		
		rSet = stmt.executeQuery();

		while (rSet.next()) {
			PROVEEDOR= new dbPROVEEDOR();
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
			result.addElement(PROVEEDOR);
		}
		rSet.close();
		stmt.close();
		return false;
	}
	public Vector findByStatusPedido(int tiestatus) throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		Vector v = new Vector();
		
		dbPROVEEDOR PROVEEDOR;
		query.append("SELECT a.* ");
		query.append("FROM PROVEEDOR a,PEDIDO b ");
		query.append("WHERE  a.iidproveedor=b.iidproveedor and tiestatus=? ");
		
		stmt = conn.prepareStatement(query.toString().toLowerCase());

		stmt.setInt(1,tiestatus);
		
		rSet = stmt.executeQuery();


		while (rSet.next()) {
			PROVEEDOR= new dbPROVEEDOR();
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
			v.addElement(PROVEEDOR);
		}
		rSet.close();
		stmt.close();
		return v;
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

	public Vector findIsPartidaSolicitada() throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		Vector v = new Vector();
		
		dbPROVEEDOR PROVEEDOR;
		query.append("SELECT a.* ");
		query.append("FROM PROVEEDOR a,PARTIDASOLICITADA b ");
		query.append("WHERE  a.iidproveedor=b.iidproveedor GROUP BY b.iidproveedor ");
		
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		
		rSet = stmt.executeQuery();


		while (rSet.next()) {
			PROVEEDOR= new dbPROVEEDOR();
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
			v.addElement(PROVEEDOR);
		}
		rSet.close();
		stmt.close();
		return v;
	}
}
