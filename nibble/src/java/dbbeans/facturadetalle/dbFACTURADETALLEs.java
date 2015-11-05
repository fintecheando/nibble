/** Vector java bean entity **/
package dbbeans.facturadetalle;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Vector;


public class dbFACTURADETALLEs {
	protected Connection conn;
	protected ResultSet rSet;
	protected PreparedStatement stmt;
	protected Vector result;


	public dbFACTURADETALLEs() {
		super();
		result = new Vector();
	}
	public void setConnection(java.sql.Connection conn) {
		this.conn = conn;
	}
	public boolean find(String where) throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		dbFACTURADETALLE FACTURADETALLE;
		query.append("SELECT * ");
		query.append("FROM FACTURADETALLE ");
		if (!where.equals("")){
			query.append("WHERE " + where );
		}
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		rSet = stmt.executeQuery();


		while (rSet.next()) {
			FACTURADETALLE= new dbFACTURADETALLE();
			FACTURADETALLE.setIidfactura(rSet.getInt("iidfactura"));
			FACTURADETALLE.setLiidparte(rSet.getLong("liidparte"));
			FACTURADETALLE.setIidproveedor(rSet.getInt("iidproveedor"));
			FACTURADETALLE.setIidnivel(rSet.getInt("iidnivel"));
			FACTURADETALLE.setIcantidadproducto(rSet.getInt("icantidadproducto"));
			FACTURADETALLE.setDecpreciolista(rSet.getFloat("decpreciolista"));
			FACTURADETALLE.setDeccostoproducto(rSet.getFloat("deccostoproducto"));
			FACTURADETALLE.setDecivaclasific(rSet.getFloat("decivaclasific"));
			FACTURADETALLE.setDecprecioventa(rSet.getFloat("decprecioventa"));
			result.addElement(FACTURADETALLE);
		}
		rSet.close();
		stmt.close();
		return false;
	}
	public void setResult (Vector result){
		this.result = result;
	}
	public Vector getResult(){
		return this.result;
	}

	public int getConsumoByFecha(long liidparte, int iidproveedor, String fechaini, String fechafin) throws Exception, java.sql.SQLException {
		int sum=0;
		StringBuffer query = new StringBuffer();
		dbFACTURADETALLE FACTURADETALLE;
		query.append("SELECT sum(fd.icantidadproducto) ");
		query.append("FROM FACTURADETALLE fd, FACTURA f ");
		query.append("WHERE fd.iidfactura=f.iidfactura " );
		query.append("AND fd.liidparte = ? AND fd.iidproveedor=? ");
		query.append("AND f.tisfechafactura BETWEEN ? AND ? ");

		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setLong(1,liidparte);
		stmt.setInt(2,iidproveedor);
		stmt.setString(3,fechaini);
		stmt.setString(4,fechafin);
		
		rSet = stmt.executeQuery();
		if (rSet.next()) {
			sum = rSet.getInt(1);
		}
		rSet.close();
		stmt.close();
		return sum;
	}
}
