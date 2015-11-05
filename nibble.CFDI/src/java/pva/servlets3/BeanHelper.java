package pva.servlets3;

/**
 * Insert the type's description here.
 * Creation date: (10/16/2002 6:39:03 PM)
 * @author: 
 */

import dbbeans.categoria.*;
import dbbeans.grupo.*;
import dbbeans.linea.*;
import dbbeans.marca.*;

import java.sql.Connection;
import pva.beans3.Bean32;

public class BeanHelper {
	pva.beans3.Bean32 bean32;
/**
 * BeanHelper constructor comment.
 */
public BeanHelper(){
    super();
    bean32 = new Bean32();
}
/**
 * Insert the method's description here.
 * Creation date: (10/16/2002 6:41:41 PM)
 * @return fabricante.beans12.Bean12
 */
public pva.beans3.Bean32 getBean32() {
	return bean32;
}
/**
 * Insert the method's description here.
 * Creation date: (10/17/2002 10:58:21 AM)
 */
public void getData(Connection con) throws Exception {
    dbCATEGORIAs categorias = new dbCATEGORIAs();
    dbGRUPOs grupos = new dbGRUPOs();
    dbLINEAs lineas = new dbLINEAs();
    dbMARCAs marcas = new dbMARCAs();

    categorias.setConnection(con);
    grupos.setConnection(con);
    lineas.setConnection(con);
    marcas.setConnection(con);

    categorias.find("");
    grupos.find("");
    lineas.find("");
    marcas.find("");

    bean32.setVCategorias(categorias.getResult());
    bean32.setVGrupos(grupos.getResult());
    bean32.setVLineas(lineas.getResult());
    bean32.setVMarcas(marcas.getResult());

}
}
