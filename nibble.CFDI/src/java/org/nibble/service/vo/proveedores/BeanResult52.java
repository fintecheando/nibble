// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BeanResult52.java

package org.nibble.service.vo.proveedores;

import dbbeans.estado.dbESTADO;
import dbbeans.municipio.dbMUNICIPIO;
import dbbeans.pais.dbPAIS;
import java.util.Vector;

public class BeanResult52
{

    public BeanResult52()
    {
        vPaiss = new Vector();
        vEstados = new Vector();
        vMunicipios = new Vector();
        vchnombre = new String();
        vchrazonsocial = new String();
        vchrfc = new String();
        vchcurp = new String();
        vchtel1 = new String();
        vchtel2 = new String();
        vchfax = new String();
        vchmail = new String();
        vchcolonia = new String();
        vchcalle = new String();
        vchcp = new String();
    }

    public boolean getBcredito()
    {
        return bcredito;
    }

    public boolean getBitaceptabackorder()
    {
        return bitaceptabackorder;
    }

    public boolean getBitdadodebaja()
    {
        return bitdadodebaja;
    }

    public String getEstadoDes(int index)
    {
        dbESTADO a = (dbESTADO)vEstados.elementAt(index);
        return a.getVchnombre();
    }

    public int getEstadoId(int index)
    {
        dbESTADO a = (dbESTADO)vEstados.elementAt(index);
        return a.getIidestado();
    }

    public int getEstadosSize()
    {
        return vEstados.size();
    }

    public int getIidestado()
    {
        return iidestado;
    }

    public int getIidmunicipio()
    {
        return iidmunicipio;
    }

    public int getIidproveedor()
    {
        return iidproveedor;
    }

    public String getMunicipioDes(int index)
    {
        dbMUNICIPIO a = (dbMUNICIPIO)vMunicipios.elementAt(index);
        return a.getVchnombre();
    }

    public int getMunicipioId(int index)
    {
        dbMUNICIPIO a = (dbMUNICIPIO)vMunicipios.elementAt(index);
        return a.getIidmunicipio();
    }

    public int getMunicipioIdEstado(int index)
    {
        dbMUNICIPIO a = (dbMUNICIPIO)vMunicipios.elementAt(index);
        return a.getIidestado();
    }

    public int getMunicipiosSize()
    {
        return vMunicipios.size();
    }

    public String getPaissDes(int index)
    {
        dbPAIS a = (dbPAIS)vPaiss.elementAt(index);
        return a.getVchnompais();
    }

    public short getPaissId(int index)
    {
        dbPAIS a = (dbPAIS)vPaiss.elementAt(index);
        return a.getSiidpais();
    }

    public int getPaissSize()
    {
        return vPaiss.size();
    }

    public short getSiidpais()
    {
        return siidpais;
    }

    public String getVchcalle()
    {
        return vchcalle;
    }

    public String getVchcolonia()
    {
        return vchcolonia;
    }

    public String getVchcp()
    {
        return vchcp;
    }

    public String getVchcurp()
    {
        return vchcurp;
    }

    public String getVchfax()
    {
        return vchfax;
    }

    public String getVchmail()
    {
        return vchmail;
    }

    public String getVchnombre()
    {
        return vchnombre;
    }

    public String getVchrazonsocial()
    {
        return vchrazonsocial;
    }

    public String getVchrfc()
    {
        return vchrfc;
    }

    public String getVchtel1()
    {
        return vchtel1;
    }

    public String getVchtel2()
    {
        return vchtel2;
    }

    public Vector getVEstados()
    {
        return vEstados;
    }

    public Vector getVMunicipio()
    {
        return vMunicipios;
    }

    public Vector getVPaiss()
    {
        return vPaiss;
    }

    public void setBcredito(boolean bcredito)
    {
        this.bcredito = bcredito;
    }

    public void setBitaceptabackorder(boolean bitaceptabackorder)
    {
        this.bitaceptabackorder = bitaceptabackorder;
    }

    public void setBitdadodebaja(boolean bitdadodebaja)
    {
        this.bitdadodebaja = bitdadodebaja;
    }

    public void setIidestado(int iidestado)
    {
        this.iidestado = iidestado;
    }

    public void setIidmunicipio(int iidmunicipio)
    {
        this.iidmunicipio = iidmunicipio;
    }

    public void setIidproveedor(int iidproveedor)
    {
        this.iidproveedor = iidproveedor;
    }

    public void setSiidpais(short siidpais)
    {
        this.siidpais = siidpais;
    }

    public void setVchcalle(String vchcalle)
    {
        this.vchcalle = vchcalle;
    }

    public void setVchcolonia(String vchcolonia)
    {
        this.vchcolonia = vchcolonia;
    }

    public void setVchcp(String vchcp)
    {
        this.vchcp = vchcp;
    }

    public void setVchcurp(String vchcurp)
    {
        this.vchcurp = vchcurp;
    }

    public void setVchfax(String vchfax)
    {
        this.vchfax = vchfax;
    }

    public void setVchmail(String vchmail)
    {
        this.vchmail = vchmail;
    }

    public void setVchnombre(String vchnombre)
    {
        this.vchnombre = vchnombre;
    }

    public void setVchrazonsocial(String vchrazonsocial)
    {
        this.vchrazonsocial = vchrazonsocial;
    }

    public void setVchrfc(String vchrfc)
    {
        this.vchrfc = vchrfc;
    }

    public void setVchtel1(String vchtel1)
    {
        this.vchtel1 = vchtel1;
    }

    public void setVchtel2(String vchtel2)
    {
        this.vchtel2 = vchtel2;
    }

    public void setVEstados(Vector newVEstados)
    {
        vEstados = newVEstados;
    }

    public void setVMunicipio(Vector newVMunicipio)
    {
        vMunicipios = newVMunicipio;
    }

    public void setVPaiss(Vector newVPaiss)
    {
        vPaiss = newVPaiss;
    }

    private int iidproveedor;
    private String vchnombre;
    private String vchrazonsocial;
    private String vchrfc;
    private String vchcurp;
    private boolean bcredito;
    private String vchtel1;
    private String vchtel2;
    private String vchfax;
    private boolean bitdadodebaja;
    private String vchmail;
    private boolean bitaceptabackorder;
    private String vchcolonia;
    private String vchcalle;
    private String vchcp;
    private short siidpais;
    private int iidestado;
    private int iidmunicipio;
    private Vector vPaiss;
    private Vector vEstados;
    public Vector vMunicipios;
}
