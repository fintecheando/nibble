// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Bean63.java

package org.nibble.service.vo.partes;

import dbbeans.parte.dbPARTE;
import general.Help;
import java.util.Vector;

public class Bean63
{

    public Bean63()
    {
    }

    public long getId(int index)
    {
        dbPARTE a = (dbPARTE)vPartes.elementAt(index);
        return a.getLiidparte();
    }

    public String getMedida(int index)
    {
        dbPARTE a = (dbPARTE)vPartes.elementAt(index);
        return Help.getMedida(a.getVchnumparteequival());
    }

    public String getNumero(int index)
    {
        dbPARTE a = (dbPARTE)vPartes.elementAt(index);
        return Help.getNumero(a.getVchnumparteequival());
    }

    public float getPrecio(int index)
    {
        dbPARTE a = (dbPARTE)vPartes.elementAt(index);
        return a.getDecprecio();
    }

    public float getPrecioB100(int index)
    {
        dbPARTE a = (dbPARTE)vPartes.elementAt(index);
        return a.getDecpreciob100();
    }

    public String getPrefijo(int index)
    {
        dbPARTE a = (dbPARTE)vPartes.elementAt(index);
        return Help.getPrefijo(a.getVchnumparteequival());
    }

    public int getSize()
    {
        return vPartes.size();
    }

    public String getSufijo(int index)
    {
        dbPARTE a = (dbPARTE)vPartes.elementAt(index);
        return Help.getSufijo(a.getVchnumparteequival());
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
    private int indexof;
}
