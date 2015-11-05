package dbrbeans.linea;

/**
 * Insert the type's description here.
 * Creation date: (11/26/2002 7:35:56 PM)
 * @author: 
 */
import java.util.Vector;

public class dbrLINEAs extends dbbeans.linea.dbLINEAs {
/**
 * dbrLINEAs constructor comment.
 */
public dbrLINEAs() {
	super();
}
public Vector findByIidusuario(int iidusuario)
    throws Exception, java.sql.SQLException {
    StringBuffer query = new StringBuffer();
    Vector v = new Vector();

    dbrLINEA LINEA;
    query.append("SELECT a.*,c.vchdescripcion as vchcategoria,d.vchdescripcion as vchgrupo ");
    query.append("FROM LINEA a,FABRICANTELINEA b, CATEGORIA c, GRUPO d ");
    query.append("WHERE  a.iidlinea=b.iidlinea AND a.iidgrupo=d.iidgrupo AND c.iidcategoria=d.iidcategoria ");
    query.append("AND b.iidusuario=? ");

    stmt = conn.prepareStatement(query.toString().toLowerCase());
    
    stmt.setInt(1, iidusuario);

    rSet = stmt.executeQuery();

    while (rSet.next()) {
        LINEA = new dbrLINEA();
        LINEA.setIidlinea(rSet.getInt("iidlinea"));
        LINEA.setVchdescripcion(rSet.getString("vchdescripcion"));
        LINEA.setVchcategoria(rSet.getString("vchcategoria"));
        LINEA.setVchgrupo(rSet.getString("vchgrupo"));
        
        v.addElement(LINEA);
    }
    rSet.close();
    stmt.close();
    return v;
}
}
