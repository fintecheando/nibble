// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Bean5.java

package org.nibble.service.vo.proveedores;

import dbbeans.estado.dbESTADO;
import dbbeans.municipio.dbMUNICIPIO;
import dbbeans.pais.dbPAIS;
import java.util.Vector;

public class Bean5
{

    public Bean5()
    {
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

    public short getEstadoIdPais(int index)
    {
        dbESTADO a = (dbESTADO)vEstados.elementAt(index);
        return a.getSiidpais();
    }

    public int getEstadosSize()
    {
        return vEstados.size();
    }

    public String getMsg()
    {
        return msg;
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

    public short getPAIS_DEFAULT()
    {
        return PAIS_DEFAULT;
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

    public Vector getVEstados()
    {
        return vEstados;
    }

    public Vector getVMunicipios()
    {
        return vMunicipios;
    }

    public Vector getVPaiss()
    {
        return vPaiss;
    }

    public void setMsg(String newMsg)
    {
        msg = newMsg;
    }

    public void setPAIS_DEFAULT(short newPAIS_DEFAULT)
    {
        PAIS_DEFAULT = newPAIS_DEFAULT;
    }

    public void setVEstados(Vector newVEstados)
    {
        vEstados = newVEstados;
    }

    public void setVMunicipios(Vector newVMunicipios)
    {
        vMunicipios = newVMunicipios;
    }

    public void setVPaiss(Vector newVPaiss)
    {
        vPaiss = newVPaiss;
    }

    private Vector vPaiss;
    private Vector vEstados;
    public Vector vMunicipios;
    private short PAIS_DEFAULT;
    private String msg;
}
