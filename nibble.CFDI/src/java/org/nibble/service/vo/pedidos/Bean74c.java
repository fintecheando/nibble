// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Bean74c.java

package org.nibble.service.vo.pedidos;

import dbrbeans.pedidopartidasparteestatus.dbrPEDIDOPARTIDASPARTEESTATUS;
import java.util.Vector;
import org.nibble.util.Formato;
//import util.Formato;

public class Bean74c
{

    public Bean74c()
    {
    }

    public String getDtfechultrecepcion(int index)
    {
        dbrPEDIDOPARTIDASPARTEESTATUS a = (dbrPEDIDOPARTIDASPARTEESTATUS)vResult.elementAt(index);
        return a.getDtfechultrecepcion() != null ? a.getDtfechultrecepcion() : "";
    }

    public int getIcantpedida(int index)
    {
        dbrPEDIDOPARTIDASPARTEESTATUS a = (dbrPEDIDOPARTIDASPARTEESTATUS)vResult.elementAt(index);
        return a.getIcantpedida();
    }

    public int getIcantrecibida(int index)
    {
        dbrPEDIDOPARTIDASPARTEESTATUS a = (dbrPEDIDOPARTIDASPARTEESTATUS)vResult.elementAt(index);
        return a.getIcantrecibida();
    }

    public int getIDCANCELADO()
    {
        return IDCANCELADO;
    }

    public int getIDPARCIALMENTE()
    {
        return IDPARCIALMENTE;
    }

    public int getIDRECIBIDO()
    {
        return IDRECIBIDO;
    }

    public int getIDSINSURTIR()
    {
        return IDSINSURTIR;
    }

    public int getIidpedido(int index)
    {
        dbrPEDIDOPARTIDASPARTEESTATUS a = (dbrPEDIDOPARTIDASPARTEESTATUS)vResult.elementAt(index);
        return a.getIidpedido();
    }

    public long getLiidparte(int index)
    {
        dbrPEDIDOPARTIDASPARTEESTATUS a = (dbrPEDIDOPARTIDASPARTEESTATUS)vResult.elementAt(index);
        return a.getLiidparte();
    }

    public int getSize()
    {
        return vResult.size();
    }

    public int getTiestatus(int index)
    {
        dbrPEDIDOPARTIDASPARTEESTATUS a = (dbrPEDIDOPARTIDASPARTEESTATUS)vResult.elementAt(index);
        return a.getTiestatus();
    }

    public String getVchcomentario(int index)
    {
        dbrPEDIDOPARTIDASPARTEESTATUS a = (dbrPEDIDOPARTIDASPARTEESTATUS)vResult.elementAt(index);
        return a.getVchcomentario() != null ? a.getVchcomentario() : "";
    }

    public String getVchdescestatus(int index)
    {
        dbrPEDIDOPARTIDASPARTEESTATUS a = (dbrPEDIDOPARTIDASPARTEESTATUS)vResult.elementAt(index);
        return a.getVchdescestatus();
    }

    public String getVchfactura(int index)
    {
        dbrPEDIDOPARTIDASPARTEESTATUS a = (dbrPEDIDOPARTIDASPARTEESTATUS)vResult.elementAt(index);
        return a.getVchfactura() != null ? a.getVchfactura() : "";
    }

    public String getVchnumparte(int index)
    {
        dbrPEDIDOPARTIDASPARTEESTATUS a = (dbrPEDIDOPARTIDASPARTEESTATUS)vResult.elementAt(index);
        return a.getVchnumparte();
    }

    public String getVchremisionproveed(int index)
    {
        dbrPEDIDOPARTIDASPARTEESTATUS a = (dbrPEDIDOPARTIDASPARTEESTATUS)vResult.elementAt(index);
        return a.getVchremisionproveed() != null ? a.getVchremisionproveed() : "";
    }

    public String getVdeccosto(int index)
    {
        dbrPEDIDOPARTIDASPARTEESTATUS a = (dbrPEDIDOPARTIDASPARTEESTATUS)vResult.elementAt(index);
        return Formato.formateoNumerico(a.getDeccosto(), "#########0.00");
    }

    public float getVdeccostof(int index)
    {
        dbrPEDIDOPARTIDASPARTEESTATUS a = (dbrPEDIDOPARTIDASPARTEESTATUS)vResult.elementAt(index);
        return a.getDeccosto();
    }

    public Vector getVResult()
    {
        return vResult;
    }

    public void setIcantpedida(int index, int value)
    {
        dbrPEDIDOPARTIDASPARTEESTATUS a = (dbrPEDIDOPARTIDASPARTEESTATUS)vResult.elementAt(index);
        a.setIcantpedida(value);
    }

    public void setIDCANCELADO(int newIDCANCELADO)
    {
        IDCANCELADO = newIDCANCELADO;
    }

    public void setIDPARCIALMENTE(int newIDPARCIALMENTE)
    {
        IDPARCIALMENTE = newIDPARCIALMENTE;
    }

    public void setIDRECIBIDO(int newIDRECIBIDO)
    {
        IDRECIBIDO = newIDRECIBIDO;
    }

    public void setIDSINSURTIR(int newIDSINSURTIR)
    {
        IDSINSURTIR = newIDSINSURTIR;
    }

    public void setTiestatus(int index, int value)
    {
        dbrPEDIDOPARTIDASPARTEESTATUS a = (dbrPEDIDOPARTIDASPARTEESTATUS)vResult.elementAt(index);
        a.setTiestatus(value);
    }

    public void setVResult(Vector newVResult)
    {
        vResult = newVResult;
    }

    private Vector vResult;
    private int IDSINSURTIR;
    private int IDPARCIALMENTE;
    private int IDRECIBIDO;
    private int IDCANCELADO;
}
