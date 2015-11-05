// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Bean53d.java

package org.nibble.service.vo.proveedores;

import dbrbeans.clasificproveedor.dbrCLASIFICPROVEEDOR;
import java.util.Vector;

public class Bean53d
{

    public Bean53d()
    {
        vProveedores = new Vector();
    }

    public int getId(int index)
    {
        dbrCLASIFICPROVEEDOR a = (dbrCLASIFICPROVEEDOR)vProveedores.elementAt(index);
        return a.getIidproveedor();
    }

    public int getIminimopedido(int index)
    {
        dbrCLASIFICPROVEEDOR a = (dbrCLASIFICPROVEEDOR)vProveedores.elementAt(index);
        return a.getIminimopedido();
    }

    public int getProveedoresSize()
    {
        return vProveedores.size();
    }

    public String getRazonsocial(int index)
    {
        dbrCLASIFICPROVEEDOR a = (dbrCLASIFICPROVEEDOR)vProveedores.elementAt(index);
        return a.getVchrazonsocial();
    }

    public Vector getVProveedores()
    {
        return vProveedores;
    }

    public boolean isBitprovefavorito(int index)
    {
        dbrCLASIFICPROVEEDOR a = (dbrCLASIFICPROVEEDOR)vProveedores.elementAt(index);
        return a.isBitprovefavorito();
    }

    public void setVProveedores(Vector newVProveedores)
    {
        vProveedores = newVProveedores;
    }

    private Vector vProveedores;
}
