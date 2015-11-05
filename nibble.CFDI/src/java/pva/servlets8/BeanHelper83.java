package pva.servlets8;

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
import pva.beans8.Bean83;

public class BeanHelper83 {
	Bean83 bean83;
/**
 * BeanHelper constructor comment.
 */
public BeanHelper83(){
    super();
    bean83 = new Bean83();
}
/**
 * Insert the method's description here.
 * Creation date: (10/16/2002 6:41:41 PM)
 * @return fabricante.beans12.Bean12
 */
public Bean83 getBean83() {
	return bean83;
}
/**
 * Insert the method's description here.
 * Creation date: (10/17/2002 10:58:21 AM)
 */
public void getData(Connection con) throws Exception{
    dbCATEGORIAs categorias = new dbCATEGORIAs();
    categorias.setConnection(con);
    categorias.find("");

    dbGRUPOs grupos = new dbGRUPOs();
    grupos.setConnection(con);
    grupos.find("");

    dbLINEAs lineas = new dbLINEAs();
    lineas.setConnection(con);
    lineas.find("");

    dbMARCAs marcas = new dbMARCAs();
    marcas.setConnection(con);
    marcas.find("");

    bean83.setVCategorias(categorias.getResult());
    bean83.setVGrupos(grupos.getResult());
    bean83.setVLineas(lineas.getResult());
    bean83.setVMarcas(marcas.getResult());
}
}
