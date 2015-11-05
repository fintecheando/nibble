package pva.servlets4;

/**
 * Insert the type's description here.
 * Creation date: (10/22/2002 1:39:24 PM)
 * @author: 
 */
import dbbeans.pais.*;
import dbbeans.estado.*;
import dbbeans.municipio.*;

import java.sql.Connection;
import pva.beans4.Bean4;

public class BeanHelper {

	private Bean4 bean4;
/**
 * BeanHelper constructor comment.
 */
public BeanHelper() {
	super();
	bean4 = new Bean4();
}
/**
 * Insert the method's description here.
 * Creation date: (10/22/2002 3:51:58 PM)
 * @return nc.beans5.Bean5
 */
public Bean4 getBean4() {
	return bean4;
}
public void getData(Connection con) throws Exception{
    dbPAISs paiss = new dbPAISs();
    paiss.setConnection(con);
    paiss.find("");

    dbESTADOs estados = new dbESTADOs();
    estados.setConnection(con);
    estados.find("");

    dbMUNICIPIOs municipios = new dbMUNICIPIOs();
    municipios.setConnection(con);
    municipios.find("");
   
    bean4.setVPaiss(paiss.getResult());
    bean4.setVEstados(estados.getResult());
    bean4.setVMunicipios(municipios.getResult());   
}
/**
 * Insert the method's description here.
 * Creation date: (10/22/2002 3:51:58 PM)
 * @param newBean5 nc.beans5.Bean5
 */
public void setBean4(Bean4 newBean4) {
	bean4 = newBean4;
}
}
