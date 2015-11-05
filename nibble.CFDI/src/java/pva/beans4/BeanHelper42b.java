package pva.beans4;

/**
 * Insert the type's description here.
 * Creation date: (10/23/2002 11:16:40 AM)
 * @author: 
 */
import dbbeans.categoriacliente.*;
import dbbeans.niveldeprecio.*;
import dbbeans.viadeembarque.*;

import java.sql.Connection;
import pva.beans4.Bean42b;

public class BeanHelper42b{
	private Bean42b bean42b;
/**
 * BeaHelper52 constructor comment.
 */
public BeanHelper42b() {
	super();
	bean42b = new Bean42b();
}
/**
 * Insert the method's description here.
 * Creation date: (10/23/2002 11:19:33 AM)
 * @return nc.beans5.BeanResult52
 */
public Bean42b getBean42b() {
	return bean42b;
}
/**
 * Insert the method's description here.
 * Creation date: (10/23/2002 11:21:35 AM)
 * @param iidcliente int
 * @param con java.sql.Connection
 */
public void getData(Connection con) throws Exception{
    dbCATEGORIACLIENTEs catsclientes = new dbCATEGORIACLIENTEs();
    catsclientes.setConnection(con);
    catsclientes.find("");

    dbNIVELDEPRECIOs niveles = new dbNIVELDEPRECIOs();
    niveles.setConnection(con);
    niveles.find("");
    
    dbVIADEEMBARQUEs vias = new dbVIADEEMBARQUEs();
    vias.setConnection(con);
    vias.find("");

    bean42b.setVCategoriasCliente(catsclientes.getResult());
    bean42b.setVNiveles(niveles.getResult());
    bean42b.setVVias(vias.getResult());

}
/**
 * Insert the method's description here.
 * Creation date: (10/23/2002 11:19:33 AM)
 * @param newBeanresult52 nc.beans5.BeanResult52
 */
public void setBean42b(Bean42b newBean42b) {
	bean42b = newBean42b;
}
}
