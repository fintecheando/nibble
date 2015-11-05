// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Bean71his.java

package org.nibble.service.vo.pedidos;

import java.io.Serializable;
import java.util.Vector;

public class Bean71his
    implements Serializable
{

    public Bean71his()
    {
    }

    public Vector getConsumo()
    {
        return consumo;
    }

    public int getIidProveedor()
    {
        return iidProveedor;
    }

    public long getLiidparte()
    {
        return liidparte;
    }

    public String getVchNombre()
    {
        return vchNombre;
    }

    public void setConsumo(Vector vector)
    {
        consumo = vector;
    }

    public void setIidProveedor(int i)
    {
        iidProveedor = i;
    }

    public void setLiidparte(long l)
    {
        liidparte = l;
    }

    public void setVchNombre(String string)
    {
        vchNombre = string;
    }

    private int iidProveedor;
    private String vchNombre;
    private long liidparte;
    private Vector consumo;
}
