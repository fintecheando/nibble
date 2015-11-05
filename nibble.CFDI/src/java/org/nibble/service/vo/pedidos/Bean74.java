// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Bean74.java

package org.nibble.service.vo.pedidos;

import dbbeans.estatuspedidos.dbESTATUSPEDIDOS;
import dbbeans.proveedor.dbPROVEEDOR;
import java.util.Vector;

public class Bean74
{

    public Bean74()
    {
    }

    public int getEstatusPedidosSize()
    {
        return vEstatusPedidos.size();
    }

    public int getIidproveedor(int index)
    {
        dbPROVEEDOR a = (dbPROVEEDOR)vProveedores.elementAt(index);
        return a.getIidproveedor();
    }

    public int getProveedoresSize()
    {
        return vProveedores.size();
    }

    public int getTiestatus(int index)
    {
        dbESTATUSPEDIDOS a = (dbESTATUSPEDIDOS)vEstatusPedidos.elementAt(index);
        return a.getTiestatus();
    }

    public String getVchdescestatus(int index)
    {
        dbESTATUSPEDIDOS a = (dbESTATUSPEDIDOS)vEstatusPedidos.elementAt(index);
        return a.getVchdescestatus();
    }

    public String getVchrazonsocial(int index)
    {
        dbPROVEEDOR a = (dbPROVEEDOR)vProveedores.elementAt(index);
        return a.getVchrazonsocial();
    }

    public Vector getVEstatusPedidos()
    {
        return vEstatusPedidos;
    }

    public Vector getVProveedores()
    {
        return vProveedores;
    }

    public void setVEstatusPedidos(Vector newVEstatusPedidos)
    {
        vEstatusPedidos = newVEstatusPedidos;
    }

    public void setVProveedores(Vector newVProveedores)
    {
        vProveedores = newVProveedores;
    }

    private Vector vProveedores;
    private Vector vEstatusPedidos;
}
