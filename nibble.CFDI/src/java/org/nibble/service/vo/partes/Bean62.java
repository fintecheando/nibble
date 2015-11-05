// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Bean62.java

package org.nibble.service.vo.partes;

import dbbeans.parte.dbPARTE;
import java.util.Vector;

public class Bean62
{

    public Bean62()
    {
    }

    public float getDecprecio(int index)
    {
        dbPARTE a = (dbPARTE)vPartes.elementAt(index);
        return a.getDecprecio();
    }

    public float getDecpreciob100(int index)
    {
        dbPARTE a = (dbPARTE)vPartes.elementAt(index);
        return a.getDecpreciob100();
    }

    public float getFlalto(int index)
    {
        dbPARTE a = (dbPARTE)vPartes.elementAt(index);
        return a.getFlalto();
    }

    public float getFlancho(int index)
    {
        dbPARTE a = (dbPARTE)vPartes.elementAt(index);
        return a.getFlancho();
    }

    public float getFllargo(int index)
    {
        dbPARTE a = (dbPARTE)vPartes.elementAt(index);
        return a.getFllargo();
    }

    public float getFlpeso(int index)
    {
        dbPARTE a = (dbPARTE)vPartes.elementAt(index);
        return a.getFlpeso();
    }

    public long getLiidparte(int index)
    {
        dbPARTE a = (dbPARTE)vPartes.elementAt(index);
        return a.getLiidparte();
    }

    public int getPartesSize()
    {
        return vPartes.size();
    }

    public String getVchnumparte(int index)
    {
        dbPARTE a = (dbPARTE)vPartes.elementAt(index);
        return a.getVchnumparte();
    }

    public Vector getVPartes()
    {
        return vPartes;
    }

    public void setVPartes(Vector newVPartes)
    {
        vPartes = newVPartes;
    }

    private Vector vPartes;
}
