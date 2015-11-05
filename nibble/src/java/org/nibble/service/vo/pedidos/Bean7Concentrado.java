// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Bean7Concentrado.java

package org.nibble.service.vo.pedidos;


public class Bean7Concentrado
    implements Comparable
{

    public Bean7Concentrado()
    {
    }

    public boolean equals(Object obj)
    {
        Bean7Concentrado vo = (Bean7Concentrado)obj;
        return getLiidparte() == vo.getLiidparte();
    }

    public int hashCode()
    {
        return (int)getLiidparte();
    }

    public String toString()
    {
        return super.toString();
    }

    public int compareTo(Object o)
    {
        Bean7Concentrado vo = (Bean7Concentrado)o;
        int a = (new Long(liidparte)).compareTo(new Long(vo.getLiidparte()));
        return a;
    }

    public long getLiidparte()
    {
        return liidparte;
    }

    public void setLiidparte(long l)
    {
        liidparte = l;
    }

    public int getExistencia()
    {
        return existencia;
    }

    public String getVchparte()
    {
        return vchparte;
    }

    public void setExistencia(int i)
    {
        existencia = i;
    }

    public void setVchparte(String string)
    {
        vchparte = string;
    }

    private long liidparte;
    private String vchparte;
    private int existencia;
}
