// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Bean74b.java

package org.nibble.service.vo.pedidos;

import dbrbeans.pedidoproveedorestatus.dbrPEDIDOPROVEEDORESTATUS;
import java.util.Vector;
import org.nibble.util.Fecha;
//import util.Fecha;

// Referenced classes of package nc.beans7:
//            Bean74

public class Bean74b extends Bean74
{

    public Bean74b()
    {
    }

    public int getIidpedido(int index)
    {
        dbrPEDIDOPROVEEDORESTATUS a = (dbrPEDIDOPROVEEDORESTATUS)vPedidos.elementAt(index);
        return a.getIidpedido();
    }

    public short getSinopartcanceladas(int index)
    {
        dbrPEDIDOPROVEEDORESTATUS a = (dbrPEDIDOPROVEEDORESTATUS)vPedidos.elementAt(index);
        return a.getSinopartcanceladas();
    }

    public short getSinopartrecibidas(int index)
    {
        dbrPEDIDOPROVEEDORESTATUS a = (dbrPEDIDOPROVEEDORESTATUS)vPedidos.elementAt(index);
        return a.getSinopartrecibidas();
    }

    public short getSinopartsurtpar(int index)
    {
        dbrPEDIDOPROVEEDORESTATUS a = (dbrPEDIDOPROVEEDORESTATUS)vPedidos.elementAt(index);
        return a.getSinopartsurtpar();
    }

    public short getSinototalpartidas(int index)
    {
        dbrPEDIDOPROVEEDORESTATUS a = (dbrPEDIDOPROVEEDORESTATUS)vPedidos.elementAt(index);
        return a.getSinototalpartidas();
    }

    public short getSinototpartpedpend(int index)
    {
        dbrPEDIDOPROVEEDORESTATUS a = (dbrPEDIDOPROVEEDORESTATUS)vPedidos.elementAt(index);
        return a.getSinototpartpedpend();
    }

    public int getSize()
    {
        return vPedidos.size();
    }

    public String getTisfechelabpedido(int index)
    {
        dbrPEDIDOPROVEEDORESTATUS a = (dbrPEDIDOPROVEEDORESTATUS)vPedidos.elementAt(index);
        return Fecha.formatMySQL(a.getTisfechelabpedido());
    }

    public String getVchdescestatus(int index)
    {
        dbrPEDIDOPROVEEDORESTATUS a = (dbrPEDIDOPROVEEDORESTATUS)vPedidos.elementAt(index);
        return a.getVchdescestatus();
    }

    public String getVchrazonsocial(int index)
    {
        dbrPEDIDOPROVEEDORESTATUS a = (dbrPEDIDOPROVEEDORESTATUS)vPedidos.elementAt(index);
        return a.getVchrazonsocial();
    }

    public int getIidproveedor(int index)
    {
        dbrPEDIDOPROVEEDORESTATUS a = (dbrPEDIDOPROVEEDORESTATUS)vPedidos.elementAt(index);
        return a.getIidproveedor();
    }

    public Vector getVPedidos()
    {
        return vPedidos;
    }

    public void setVPedidos(Vector newVPedidos)
    {
        vPedidos = newVPedidos;
    }

    Vector vPedidos;
}
