package dbrbeans.grupo;

/**
 * Insert the type's description here.
 * Creation date: (11/26/2002 6:16:14 PM)
 * @author: 
 */
import java.util.Vector;
 
public class dbrGRUPOs extends dbbeans.grupo.dbGRUPOs {
/**
 * dbrGRUPOs constructor comment.
 */
public dbrGRUPOs() {
	super();
}
public Vector findByIidusuario(int iidusuario)
    throws Exception, java.sql.SQLException {
    StringBuffer query = new StringBuffer();
    Vector v = new Vector();

    dbrGRUPO GRUPO;
    query.append("SELECT a.*,c.vchdescripcion as vchcategoria ");
    query.append("FROM GRUPO a,FABRICANTEGRUPO b, CATEGORIA c ");
    query.append("WHERE  a.iidgrupo=b.iidgrupo AND a.iidcategoria=c.iidcategoria ");
    query.append("AND b.iidusuario=? ");

    stmt = conn.prepareStatement(query.toString().toLowerCase());
    
    stmt.setInt(1, iidusuario);

    rSet = stmt.executeQuery();

    while (rSet.next()) {
        GRUPO = new dbrGRUPO();
        GRUPO.setIidgrupo(rSet.getInt("iidgrupo"));
        GRUPO.setVchdescripcion(rSet.getString("vchdescripcion"));
        GRUPO.setVchcategoria(rSet.getString("vchcategoria"));
        v.addElement(GRUPO);
    }
    rSet.close();
    stmt.close();
    return v;
}
}
