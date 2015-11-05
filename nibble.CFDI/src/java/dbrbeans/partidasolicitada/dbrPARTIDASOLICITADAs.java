package dbrbeans.partidasolicitada;

/**
 * Insert the type's description here.
 * Creation date: (12/13/2002 2:15:12 PM)
 * @author: 
 */
public class dbrPARTIDASOLICITADAs extends dbbeans.partidasolicitada.dbPARTIDASOLICITADAs {
/**
 * dbrPARTIDASOLICITADAs constructor comment.
 */
public dbrPARTIDASOLICITADAs() {
	super();
}
	public boolean findByIidproveedor(int iidproveedor) throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		dbrPARTIDASOLICITADA PARTIDASOLICITADA;
		query.append("SELECT a.*,p.vchnumparte,p.iidcategoria,p.iidgrupo,p.iidlinea,p.iidmarca ");
		query.append("FROM PARTIDASOLICITADA a,PARTE p ");
		query.append("WHERE a.liidparte=p.liidparte  ");
		query.append("AND a.iidproveedor=? ");
		query.append("ORDER BY p.iidcategoria,p.iidgrupo,p.iidlinea,p.iidmarca ");

		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setInt(1,iidproveedor);
		
		rSet = stmt.executeQuery();

		while (rSet.next()) {
			PARTIDASOLICITADA= new dbrPARTIDASOLICITADA();
			PARTIDASOLICITADA.setIidpartidasolicit(rSet.getInt("iidpartidasolicit"));
			PARTIDASOLICITADA.setLiidparte(rSet.getLong("liidparte"));
			PARTIDASOLICITADA.setIidproveedor(rSet.getInt("iidproveedor"));
			PARTIDASOLICITADA.setDtfechasolicitud(rSet.getString("dtfechasolicitud"));
			PARTIDASOLICITADA.setIcantidadpedida(rSet.getInt("icantidadpedida"));
			PARTIDASOLICITADA.setVchnumparte(rSet.getString("vchnumparte"));
			PARTIDASOLICITADA.setIidcategoria(rSet.getInt("iidcategoria"));
			PARTIDASOLICITADA.setIidgrupo(rSet.getInt("iidgrupo"));
			PARTIDASOLICITADA.setIidlinea(rSet.getInt("iidlinea"));
			PARTIDASOLICITADA.setIidmarca(rSet.getInt("iidmarca"));
			result.addElement(PARTIDASOLICITADA);
		}

		rSet.close();
		stmt.close();
		return false;
	}
	public boolean RemoveByIidproveedor(int iidproveedor) throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		query.append("DELETE  ");
		query.append("FROM PARTIDASOLICITADA ");
		query.append("WHERE iidproveedor=? ");

		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setInt(1,iidproveedor);
		
		stmt.executeUpdate();
		stmt.close();
		return false;
	}
}
