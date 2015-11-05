package dbrbeans.inventario;

import java.sql.SQLException;
import java.util.Vector;

/**
 * Insert the type's description here.
 * Creation date: (11/25/2002 11:51:51 AM)
 * @author: 
 */
public class dbrINVENTARIOSs extends dbbeans.inventario.dbINVENTARIOs {
/**
 * dbrINVENTARIOSs constructor comment.
 */
public dbrINVENTARIOSs() {
    super();
}
public void findByClasificacion(
    int iidcategoria,
    int iidgrupo,
    int iidlinea,
    int iidmarca)

    throws Exception, java.sql.SQLException {

    StringBuffer query = new StringBuffer();
    dbrINVENTARIO INVENTARIO;
    
    query.append("SELECT i.*,p.vchnumparte, p.vchdescripciones, p.imultiploempaque, p.decpreciob100, pro.vchrazonsocial ");
	query.append("FROM INVENTARIO i ");
	query.append("INNER JOIN PARTE p ON i.liidparte=p.liidparte ");
	query.append("LEFT JOIN PROVEEDOR pro ON i.iidproveedor=pro.iidproveedor ");    
	query.append("WHERE ");    
    query.append("p.iidcategoria= ? AND p.iidgrupo= ? AND p.iidlinea= ? AND p.iidmarca= ? ");

	
	
    stmt = conn.prepareStatement(query.toString().toLowerCase());

    stmt.setInt(1, iidcategoria);
    stmt.setInt(2, iidgrupo);
    stmt.setInt(3, iidlinea);
    stmt.setInt(4, iidmarca);

    rSet = stmt.executeQuery();

    while (rSet.next()) {
        INVENTARIO = new dbrINVENTARIO();
        INVENTARIO.setLiidparte(rSet.getLong("liidparte"));
        INVENTARIO.setIidproveedor(rSet.getInt("iidproveedor"));
        INVENTARIO.setIexistencia(rSet.getInt("iexistencia"));
        INVENTARIO.setBitsalidareciente(rSet.getBoolean("bitsalidareciente"));
        INVENTARIO.setIindiceresurtido(rSet.getInt("iindiceresurtido"));
        INVENTARIO.setIcantconsumo(rSet.getInt("icantconsumo"));
        INVENTARIO.setVchubicacion(rSet.getString("vchubicacion"));
        INVENTARIO.setVchnumparte(rSet.getString("vchnumparte"));
        INVENTARIO.setVchrazonsocial(rSet.getString("vchrazonsocial"));
        INVENTARIO.setVchdescripcion(rSet.getString("vchdescripciones"));
        INVENTARIO.setImultiploempaque(rSet.getInt("imultiploempaque"));
        INVENTARIO.setDecpreciob100(rSet.getFloat("decpreciob100"));
        result.addElement(INVENTARIO);
    }
    rSet.close();
    stmt.close();
}
public void findByClasificacion(
    int iidcategoria,
    int iidgrupo,
    int iidlinea,
    int iidmarca,
    boolean bitsalidareciente)

    throws Exception, java.sql.SQLException {

    StringBuffer query = new StringBuffer();
    dbrINVENTARIO INVENTARIO;
    query.append("SELECT i.*,p.vchnumparte, p.vchdescripciones, p.imultiploempaque, pro.vchrazonsocial ");
	query.append("FROM INVENTARIO i ");
	query.append("INNER JOIN PARTE p ON i.liidparte=p.liidparte ");
	query.append("LEFT JOIN PROVEEDOR pro ON i.iidproveedor=pro.iidproveedor ");    
	query.append("WHERE ");    
    query.append("p.iidcategoria= ? AND p.iidgrupo= ? AND p.iidlinea= ? AND p.iidmarca= ?  AND i.bitsalidareciente=? ");

    stmt = conn.prepareStatement(query.toString().toLowerCase());

    stmt.setInt(1, iidcategoria);
    stmt.setInt(2, iidgrupo);
    stmt.setInt(3, iidlinea);
    stmt.setInt(4, iidmarca);
    stmt.setBoolean(5, bitsalidareciente);

    rSet = stmt.executeQuery();

    while (rSet.next()) {
        INVENTARIO = new dbrINVENTARIO();
        INVENTARIO.setLiidparte(rSet.getLong("liidparte"));
        INVENTARIO.setIidproveedor(rSet.getInt("iidproveedor"));
        INVENTARIO.setIexistencia(rSet.getInt("iexistencia"));
        INVENTARIO.setBitsalidareciente(rSet.getBoolean("bitsalidareciente"));
        INVENTARIO.setIindiceresurtido(rSet.getInt("iindiceresurtido"));
        INVENTARIO.setIcantconsumo(rSet.getInt("icantconsumo"));
        INVENTARIO.setVchubicacion(rSet.getString("vchubicacion"));
        INVENTARIO.setVchnumparte(rSet.getString("vchnumparte"));
        INVENTARIO.setVchrazonsocial(rSet.getString("vchrazonsocial"));
        INVENTARIO.setVchdescripcion(rSet.getString("vchdescripciones"));
        INVENTARIO.setImultiploempaque(rSet.getInt("imultiploempaque"));
        result.addElement(INVENTARIO);
    }
    rSet.close();
    stmt.close();
}
public void findByClasificacionOrderByProveedor(
    int iidcategoria,
    int iidgrupo,
    int iidlinea,
    int iidmarca)

    throws Exception, java.sql.SQLException {

    StringBuffer query = new StringBuffer();
    dbrINVENTARIO INVENTARIO;
    query.append("SELECT i.*,p.vchnumparte, p.vchdescripciones, p.imultiploempaque, p.decpreciob100, pro.vchrazonsocial ");
    query.append("FROM INVENTARIO i ");
	query.append("INNER JOIN PARTE p ON i.liidparte=p.liidparte ");
	query.append("LEFT JOIN PROVEEDOR pro ON i.iidproveedor=pro.iidproveedor ");    
    query.append("WHERE ");
    query.append("p.iidcategoria= ? AND p.iidgrupo= ? AND p.iidlinea= ? AND p.iidmarca= ? ");
	query.append("ORDER BY i.iidproveedor,p.vchnumparte ");

	
	
    stmt = conn.prepareStatement(query.toString().toLowerCase());

    stmt.setInt(1, iidcategoria);
    stmt.setInt(2, iidgrupo);
    stmt.setInt(3, iidlinea);
    stmt.setInt(4, iidmarca);

    rSet = stmt.executeQuery();

    while (rSet.next()) {
        INVENTARIO = new dbrINVENTARIO();
        INVENTARIO.setLiidparte(rSet.getLong("liidparte"));
        INVENTARIO.setIidproveedor(rSet.getInt("iidproveedor"));
        INVENTARIO.setIexistencia(rSet.getInt("iexistencia"));
        INVENTARIO.setBitsalidareciente(rSet.getBoolean("bitsalidareciente"));
        INVENTARIO.setIindiceresurtido(rSet.getInt("iindiceresurtido"));
        INVENTARIO.setIcantconsumo(rSet.getInt("icantconsumo"));
        INVENTARIO.setVchubicacion(rSet.getString("vchubicacion"));
        INVENTARIO.setVchnumparte(rSet.getString("vchnumparte"));
        INVENTARIO.setVchrazonsocial(rSet.getString("vchrazonsocial"));
        INVENTARIO.setVchdescripcion(rSet.getString("vchdescripciones"));
        INVENTARIO.setImultiploempaque(rSet.getInt("imultiploempaque"));
        INVENTARIO.setDecpreciob100(rSet.getFloat("decpreciob100"));
        result.addElement(INVENTARIO);
    }
    rSet.close();
    stmt.close();
}
public void findByClasificacion(int iidcategoria, int iidgrupo, int iidlinea, int iidmarca, boolean bitsalidareciente, Vector vproveedores)
        throws Exception, SQLException
    {
        StringBuffer query = new StringBuffer();
        String proveedores = "";
        for(int i = 0; i < vproveedores.size(); i++)
        {
            proveedores = proveedores + vproveedores.elementAt(i).toString() + ",";
            
        }

        proveedores = proveedores.substring(0, proveedores.lastIndexOf(","));
        query.append("SELECT i.*,p.vchnumparte, p.vchdescripciones, p.decpreciob100, p.imultiploempaque, p.iminembarque, pro.vchrazonsocial ");
        query.append("FROM inventario i ");
        query.append("INNER JOIN parte p ON p.liidparte = i.liidparte ");
        query.append("INNER JOIN proveedor pro ON pro.iidproveedor = i.iidproveedor ");
        query.append("WHERE ");
        query.append("p.iidcategoria=? AND p.iidgrupo=? AND p.iidlinea=? AND p.iidmarca=? AND i.bitsalidareciente=? ");
        query.append("AND i.iidproveedor in(");
        query.append(proveedores);
        query.append(")");
        
        super.stmt = super.conn.prepareStatement(query.toString().toLowerCase());
        super.stmt.setInt(1, iidcategoria);
        super.stmt.setInt(2, iidgrupo);
        super.stmt.setInt(3, iidlinea);
        super.stmt.setInt(4, iidmarca);
        super.stmt.setInt(5, bitsalidareciente ? 1 : 0);
        dbrINVENTARIO INVENTARIO;
        for(super.rSet = super.stmt.executeQuery(); super.rSet.next(); super.result.addElement(INVENTARIO))
        {
            INVENTARIO = new dbrINVENTARIO();
            INVENTARIO.setLiidparte(super.rSet.getLong("liidparte"));
            INVENTARIO.setIidproveedor(super.rSet.getInt("iidproveedor"));
            INVENTARIO.setIexistencia(super.rSet.getInt("iexistencia"));
            INVENTARIO.setBitsalidareciente(super.rSet.getBoolean("bitsalidareciente"));
            INVENTARIO.setIindiceresurtido(super.rSet.getInt("iindiceresurtido"));
            INVENTARIO.setIcantconsumo(super.rSet.getInt("icantconsumo"));
            INVENTARIO.setVchubicacion(super.rSet.getString("vchubicacion"));
            INVENTARIO.setVchnumparte(super.rSet.getString("vchnumparte"));
            INVENTARIO.setVchrazonsocial(super.rSet.getString("vchrazonsocial"));
            INVENTARIO.setVchdescripcion(super.rSet.getString("vchdescripciones"));
            INVENTARIO.setDecpreciob100(super.rSet.getFloat("decpreciob100"));
            INVENTARIO.setIminempaque(super.rSet.getInt("iminembarque"));
            INVENTARIO.setImultiploempaque(super.rSet.getInt("imultiploempaque"));
        }

        super.rSet.close();
        super.stmt.close();
    }
}
