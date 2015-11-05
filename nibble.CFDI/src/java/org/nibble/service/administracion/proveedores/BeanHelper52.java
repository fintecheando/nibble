// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BeanHelper52.java

package org.nibble.service.administracion.proveedores;

import dbbeans.estado.dbESTADOs;
import dbbeans.municipio.dbMUNICIPIOs;
import dbbeans.pais.dbPAISs;
import dbbeans.proveedor.dbPROVEEDOR;
import java.sql.Connection;
import org.nibble.service.vo.proveedores.BeanResult52;
//import nc.beans5.BeanResult52;

// Referenced classes of package nc.servlets5:
//            BeanHelper

public class BeanHelper52 extends BeanHelper
{

    public BeanHelper52()
    {
        beanresult52 = new BeanResult52();
    }

    public BeanResult52 getBeanresult52()
    {
        return beanresult52;
    }

    public void getData(dbPROVEEDOR proveedor, Connection con)
        throws Exception
    {
        beanresult52.setIidproveedor(proveedor.getIidproveedor());
        beanresult52.setVchnombre(proveedor.getVchnombre());
        beanresult52.setVchrazonsocial(proveedor.getVchrazonsocial());
        beanresult52.setVchrfc(proveedor.getVchrfc());
        beanresult52.setVchcurp(proveedor.getVchcurp());
        beanresult52.setBcredito(proveedor.getBcredito());
        beanresult52.setVchtel1(proveedor.getVchtel1());
        beanresult52.setVchtel2(proveedor.getVchtel2());
        beanresult52.setVchfax(proveedor.getVchfax());
        beanresult52.setBitdadodebaja(proveedor.getBitdadodebaja());
        beanresult52.setVchmail(proveedor.getVchmail());
        beanresult52.setBitaceptabackorder(proveedor.getBitaceptabackorder());
        beanresult52.setVchcolonia(proveedor.getVchcolonia());
        beanresult52.setVchcalle(proveedor.getVchcalle());
        beanresult52.setVchcp(proveedor.getVchcp());
        beanresult52.setSiidpais(proveedor.getSiidpais());
        beanresult52.setIidestado(proveedor.getIidestado());
        beanresult52.setIidmunicipio(proveedor.getIidmunicipio());
        dbPAISs paiss = new dbPAISs();
        paiss.setConnection(con);
        paiss.find("");
        dbESTADOs estados = new dbESTADOs();
        estados.setConnection(con);
        estados.findByPais(proveedor.getSiidpais());
        dbMUNICIPIOs municipios = new dbMUNICIPIOs();
        municipios.setConnection(con);
        municipios.findByEstado(proveedor.getIidestado());
        beanresult52.setVPaiss(paiss.getResult());
        beanresult52.setVEstados(estados.getResult());
        beanresult52.setVMunicipio(municipios.getResult());
    }

    public void setBeanresult52(BeanResult52 newBeanresult52)
    {
        beanresult52 = newBeanresult52;
    }

    private BeanResult52 beanresult52;
}
