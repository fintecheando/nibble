// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Bean73b.java

package org.nibble.service.vo.pedidos;

import dbbeans.partidasolicitada.dbPARTIDASOLICITADA;
import dbrbeans.partidasolicitada.dbrPARTIDASOLICITADA;
import java.util.Vector;

public class Bean73b
{

    public Bean73b()
    {
    }

    public String getDtfechasolicitud(int index)
    {
        dbrPARTIDASOLICITADA a = (dbrPARTIDASOLICITADA)vResult.elementAt(index);
        return a.getDtfechasolicitud();
    }

    public int getIcantpedida(int index)
    {
        dbrPARTIDASOLICITADA a = (dbrPARTIDASOLICITADA)vResult.elementAt(index);
        return a.getIcantidadpedida();
    }

    public long getLiidparte(int index)
    {
        dbrPARTIDASOLICITADA a = (dbrPARTIDASOLICITADA)vResult.elementAt(index);
        return a.getLiidparte();
    }

    public String getMsg()
    {
        return msg != null ? msg : "";
    }

    public dbrPARTIDASOLICITADA getPARTIDASOLICITADA(int index)
    {
        dbrPARTIDASOLICITADA a = (dbrPARTIDASOLICITADA)vResult.elementAt(index);
        return a;
    }

    public int getSize()
    {
        return vResult.size();
    }

    public String getVchnumparte(int index)
    {
        dbrPARTIDASOLICITADA a = (dbrPARTIDASOLICITADA)vResult.elementAt(index);
        return a.getVchnumparte();
    }

    public Vector getVResult()
    {
        return vResult;
    }

    public boolean isPopupImprimir()
    {
        return popupImprimir;
    }

    public void setIcantpedida(int value, int index)
    {
        dbrPARTIDASOLICITADA a = (dbrPARTIDASOLICITADA)vResult.elementAt(index);
        a.setIcantidadpedida(value);
    }

    public void setMsg(String newMsg)
    {
        msg = newMsg;
    }

    public void setPopupImprimir(boolean newPopupImprimir)
    {
        popupImprimir = newPopupImprimir;
    }

    public void setVResult(Vector newVResult)
    {
        vResult = newVResult;
    }

    public double getCostoUnitario(int index)
    {
        dbrPARTIDASOLICITADA a = (dbrPARTIDASOLICITADA)vResult.elementAt(index);
        return a.getCostoUnitario();
    }

    private Vector vResult;
    public int iidproveedor;
    private String msg;
    private boolean popupImprimir;
}
