// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Bean52.java

package org.nibble.service.vo.proveedores;

import dbbeans.proveedor.dbPROVEEDOR;
import java.io.PrintStream;
import java.util.Vector;

public class Bean52
{

    public Bean52()
    {
        vProveedores = new Vector();
        
    }

    public int getId(int index)
    {
        dbPROVEEDOR a = (dbPROVEEDOR)vProveedores.elementAt(index);
        return a.getIidproveedor();
    }

    public int getProveedoresSize()
    {
        return vProveedores.size();
    }

    public String getRazonsocial(int index)
    {
        dbPROVEEDOR a = (dbPROVEEDOR)vProveedores.elementAt(index);
        return a.getVchrazonsocial();
    }

    public String getRfc(int index)
    {
        dbPROVEEDOR a = (dbPROVEEDOR)vProveedores.elementAt(index);
        return a.getVchrfc();
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
