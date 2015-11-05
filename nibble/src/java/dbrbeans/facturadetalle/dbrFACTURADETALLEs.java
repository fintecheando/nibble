package dbrbeans.facturadetalle;

/**
 * Insert the type's description here.
 * Creation date: (11/13/2002 7:43:15 PM)
 * @author: 
 */
public class dbrFACTURADETALLEs extends dbbeans.facturadetalle.dbFACTURADETALLEs {
/**
 * dbrFACTURADETALLEs constructor comment.
 */
public dbrFACTURADETALLEs() {
	super();
}
	public boolean findByIidfactura(int iidfactura) throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		dbrFACTURADETALLE FACTURADETALLE;
		query.append("SELECT f.*,p.vchnumparte,p.vchdescripciones, n.vchnivel, decutilidad, pro.vchrazonsocial, uni.vchdescripcion ");
		query.append("FROM FACTURADETALLE f,PARTE p,NIVELDEPRECIO n, PROVEEDOR pro, UNIDAD uni ");
		query.append("WHERE f.liidparte = p.liidparte AND f.iidnivel = n.iidnivel AND f.iidproveedor=pro.iidproveedor ");
		query.append("AND p.iidunidad = uni.iidunidad ");
		query.append("AND f.iidfactura= ? ");

		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setInt(1,iidfactura);

		rSet = stmt.executeQuery();
		


		while (rSet.next()) {
			FACTURADETALLE= new dbrFACTURADETALLE();
			FACTURADETALLE.setIidfactura(rSet.getInt("iidfactura"));
			FACTURADETALLE.setLiidparte(rSet.getLong("liidparte"));
			FACTURADETALLE.setIidproveedor(rSet.getInt("iidproveedor"));
			FACTURADETALLE.setIidnivel(rSet.getInt("iidnivel"));
			FACTURADETALLE.setIcantidadproducto(rSet.getInt("icantidadproducto"));
			FACTURADETALLE.setDecpreciolista(rSet.getFloat("decpreciolista"));
			FACTURADETALLE.setDeccostoproducto(rSet.getFloat("deccostoproducto"));
			FACTURADETALLE.setDecivaclasific(rSet.getFloat("decivaclasific"));
			FACTURADETALLE.setDecprecioventa(rSet.getFloat("decprecioventa"));
			FACTURADETALLE.setVchnumparte(rSet.getString("vchnumparte"));
			FACTURADETALLE.setVchnivel(rSet.getString("vchnivel"));
			FACTURADETALLE.setVchdescripcion(rSet.getString("vchdescripciones"));
			FACTURADETALLE.setVchrazonsocial(rSet.getString("vchrazonsocial"));
			FACTURADETALLE.setDecutilidad(rSet.getFloat("decutilidad"));
			FACTURADETALLE.setUnidadDescripcion(rSet.getString("vchdescripcion"));
			result.addElement(FACTURADETALLE);
		}
		rSet.close();
		stmt.close();
		return false;
	}

}
