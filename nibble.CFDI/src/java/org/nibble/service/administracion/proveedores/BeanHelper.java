// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BeanHelper.java

package org.nibble.service.administracion.proveedores;

import dbbeans.estado.dbESTADOs;
import dbbeans.municipio.dbMUNICIPIOs;
import dbbeans.pais.dbPAISs;
import java.sql.Connection;
import org.nibble.service.vo.proveedores.Bean5;
//import nc.beans5.Bean5;

public class BeanHelper
{

    public BeanHelper()
    {
        bean5 = new Bean5();
    }

    public Bean5 getBean5()
    {
        return bean5;
    }

    public void getData(Connection con)
        throws Exception
    {
        dbPAISs paiss = new dbPAISs();
        paiss.setConnection(con);
        paiss.find("");
        dbESTADOs estados = new dbESTADOs();
        estados.setConnection(con);
        estados.find("");
        dbMUNICIPIOs municipios = new dbMUNICIPIOs();
        municipios.setConnection(con);
        municipios.find("");
        bean5.setVPaiss(paiss.getResult());
        bean5.setVEstados(estados.getResult());
        bean5.setVMunicipios(municipios.getResult());
    }

    public void setBean5(Bean5 newBean5)
    {
        bean5 = newBean5;
    }

    private Bean5 bean5;
}
