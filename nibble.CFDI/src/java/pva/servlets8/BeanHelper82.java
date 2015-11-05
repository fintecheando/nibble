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
import dbbeans.tipodemovimiento.*;

import java.sql.Connection;
import pva.beans8.Bean82;

public class BeanHelper82 {
	Bean82 bean82;
/**
 * BeanHelper constructor comment.
 */
public BeanHelper82(){
    super();
    bean82 = new Bean82();
}
/**
 * Insert the method's description here.
 * Creation date: (10/16/2002 6:41:41 PM)
 * @return fabricante.beans12.Bean12
 */
public Bean82 getBean82() {
	return bean82;
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

    dbTIPODEMOVIMIENTOs tipodemovimientos = new dbTIPODEMOVIMIENTOs();
	tipodemovimientos.setConnection(con);
	tipodemovimientos.find("");
    
    bean82.setVCategorias(categorias.getResult());
    bean82.setVGrupos(grupos.getResult());
    bean82.setVLineas(lineas.getResult());
    bean82.setVMarcas(marcas.getResult());
    bean82.setVTipoMovimiento(tipodemovimientos.getResult());
}
}
