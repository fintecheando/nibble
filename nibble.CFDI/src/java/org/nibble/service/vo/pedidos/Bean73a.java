// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Bean73a.java

package org.nibble.service.vo.pedidos;

import dbbeans.proveedor.dbPROVEEDOR;
import java.util.Vector;

public class Bean73a
{

    public Bean73a()
    {
    }

    public int getIidproveedor(int index)
    {
        dbPROVEEDOR a = (dbPROVEEDOR)vProveedores.elementAt(index);
        return a.getIidproveedor();
    }

    public int getSize()
    {
        return vProveedores.size();
    }

    public String getVchrazonsocial(int index)
    {
        dbPROVEEDOR a = (dbPROVEEDOR)vProveedores.elementAt(index);
        return a.getVchrazonsocial();
    }

    public Vector getVProveedores()
    {
        return vProveedores;
    }

    public void setVProveedores(Vector newVProveedores)
    {
        vProveedores = newVProveedores;
    }

    private Vector vProveedores;
}
