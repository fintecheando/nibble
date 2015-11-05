// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BeanHelper53.java

package org.nibble.service.administracion.proveedores;

import dbbeans.categoria.dbCATEGORIAs;
import dbbeans.grupo.dbGRUPOs;
import dbbeans.linea.dbLINEAs;
import dbbeans.marca.dbMARCAs;
import dbbeans.unidad.dbUNIDADs;
import java.sql.Connection;
import org.nibble.service.vo.proveedores.Bean53;
//import nc.beans5.Bean53;

public class BeanHelper53
{

    public BeanHelper53()
    {
        bean53 = new Bean53();
    }

    public Bean53 getBean53()
    {
        return bean53;
    }

    public void getData(Connection con)
        throws Exception
    {
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
        dbUNIDADs unidads = new dbUNIDADs();
        unidads.setConnection(con);
        unidads.find("");
        bean53.setVCategorias(categorias.getResult());
        bean53.setVGrupos(grupos.getResult());
        bean53.setVLineas(lineas.getResult());
        bean53.setVMarcas(marcas.getResult());
        bean53.setVUnidads(unidads.getResult());
    }

    Bean53 bean53;
}
