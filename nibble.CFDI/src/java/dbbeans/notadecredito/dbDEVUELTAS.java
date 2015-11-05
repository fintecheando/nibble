package dbbeans.notadecredito;

/**
 * Insert the type's description here.
 * Creation date: (11/14/2002 4:45:48 PM)
 * @author: 
 */
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class dbDEVUELTAS{
	private Connection conn;
	private ResultSet rSet;
	private PreparedStatement stmt;
	
public int getDevueltasByFacturaParte(int iidfactura, long liidparte, int iidproveedor)
    throws Exception, java.sql.SQLException {
    StringBuffer query = new StringBuffer();
    int devueltas = 0;
    query.append("SELECT SUM(ncd.icantidadproducto) ");
    query.append("FROM NOTACREDITODETALLE ncd,NOTADECREDITO nc ");
    query.append("WHERE ncd.iidnotacredito = nc.iidnotacredito ");
    query.append("AND nc.iidfactura= ? ");
    query.append("AND ncd.liidparte = ? ");
    query.append("AND ncd.iidproveedor = ? ");

    stmt = conn.prepareStatement(query.toString().toLowerCase());
    stmt.setInt(1, iidfactura);
    stmt.setLong(2, liidparte);
    stmt.setInt(3, iidproveedor);

    rSet = stmt.executeQuery();

    if (rSet.next()) {
        devueltas = rSet.getInt(1);
    }
    rSet.close();
    stmt.close();
    return devueltas;

}
public void setConnection(java.sql.Connection conn) {
	this.conn = conn;
}
}
