package get;

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

public class GetClasificacion {
/**
 * BeanHelper constructor comment.
 */
public GetClasificacion(){
    super();
}
/**
 * Obtiene Datos de Categoria, Grupo, Linea, Marca
 * Creation date: (10/17/2002 10:58:21 AM)
 */
public BeanClasificacion getData(Connection con) throws Exception{
	BeanClasificacion bean = new BeanClasificacion();
	
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
    
    bean.setVCategorias(categorias.getResult());
    bean.setVGrupos(grupos.getResult());
    bean.setVLineas(lineas.getResult());
    bean.setVMarcas(marcas.getResult());

    return bean;
}
/**
 * Obtiene clasificacion para una usuario especifico.
 * Creation date: (10/17/2002 10:58:21 AM)
 */
public BeanClasificacion getData(Connection con,int iidusuario) throws Exception{
	BeanClasificacion bean = new BeanClasificacion();
	
    dbCATEGORIAs categorias = new dbCATEGORIAs();
    categorias.setConnection(con);

    dbGRUPOs grupos = new dbGRUPOs();
    grupos.setConnection(con);

    dbLINEAs lineas = new dbLINEAs();
    lineas.setConnection(con);

    dbMARCAs marcas = new dbMARCAs();
    marcas.setConnection(con);
   
    bean.setVCategorias(categorias.findByIidusuario(iidusuario));
    bean.setVGrupos(grupos.findByIidusuario(iidusuario));
    bean.setVLineas(lineas.findByIidusuario(iidusuario));
    bean.setVMarcas(marcas.findByIidusuario(iidusuario));

    return bean;

}
}
