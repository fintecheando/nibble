// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Bean71Concentrado.java

package org.nibble.service.vo.pedidos;


public class Bean71Concentrado
    implements Comparable
{

    public Bean71Concentrado()
    {
    }

    public boolean equals(Object obj)
    {
        Bean71Concentrado vo = (Bean71Concentrado)obj;
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
        Bean71Concentrado vo = (Bean71Concentrado)o;
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

    public int getBackorder()
    {
        return backorder;
    }

    public int getCantidad()
    {
        return cantidad;
    }

    public int getIndiceresurtido()
    {
        return indiceresurtido;
    }

    public int getNoenviados()
    {
        return noenviados;
    }

    public int getSugiere()
    {
        return sugiere;
    }

    public void setBackorder(int i)
    {
        backorder = i;
    }

    public void setCantidad(int i)
    {
        cantidad = i;
    }

    public void setIndiceresurtido(int i)
    {
        indiceresurtido = i;
    }

    public void setNoenviados(int i)
    {
        noenviados = i;
    }

    public void setSugiere(int i)
    {
        sugiere = i;
    }

    public int getConsumo()
    {
        return consumo;
    }

    public void setConsumo(int i)
    {
        consumo = i;
    }

    public int getHconsumo()
    {
        return hconsumo;
    }

    public void setHconsumo(int i)
    {
        hconsumo = i;
    }

    public int getMm()
    {
        return mm;
    }

    public int getYy()
    {
        return yy;
    }

    public void setMm(int i)
    {
        mm = i;
    }

    public void setYy(int i)
    {
        yy = i;
    }

    private long liidparte;
    private int hconsumo;
    private int mm;
    private int yy;
    private String vchparte;
    private int existencia;
    private int indiceresurtido;
    private int consumo;
    private int backorder;
    private int noenviados;
    private int sugiere;
    private int cantidad;
}
