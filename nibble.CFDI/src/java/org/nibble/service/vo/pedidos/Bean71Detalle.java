// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Bean71Detalle.java

package org.nibble.service.vo.pedidos;


public class Bean71Detalle
{

    public Bean71Detalle()
    {
    }

    public int getBackorder()
    {
        return backorder;
    }

    public int getCantidad()
    {
        return cantidad;
    }

    public float getCosto()
    {
        return costo;
    }

    public int getDiasinventario()
    {
        return diasinventario;
    }

    public int getExistencia()
    {
        return existencia;
    }

    public int getIidproveedor()
    {
        return iidproveedor;
    }

    public int getIndiceresurtido()
    {
        return indiceresurtido;
    }

    public long getLiidparte()
    {
        return liidparte;
    }

    public int getMembarque()
    {
        return membarque;
    }

    public int getMultempaque()
    {
        return multempaque;
    }

    public int getNoenviados()
    {
        return noenviados;
    }

    public String getNoparte()
    {
        return noparte;
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

    public void setCosto(float i)
    {
        costo = i;
    }

    public void setDiasinventario(int i)
    {
        diasinventario = i;
    }

    public void setExistencia(int i)
    {
        existencia = i;
    }

    public void setIidproveedor(int i)
    {
        iidproveedor = i;
    }

    public void setIndiceresurtido(int i)
    {
        indiceresurtido = i;
    }

    public void setLiidparte(long l)
    {
        liidparte = l;
    }

    public void setMembarque(int i)
    {
        membarque = i;
    }

    public void setMultempaque(int i)
    {
        multempaque = i;
    }

    public void setNoenviados(int i)
    {
        noenviados = i;
    }

    public void setNoparte(String string)
    {
        noparte = string;
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

    public String getVchproveedor()
    {
        return vchproveedor;
    }

    public void setVchproveedor(String string)
    {
        vchproveedor = string;
    }

    private long liidparte;
    private String noparte;
    private int existencia;
    private int consumo;
    private int indiceresurtido;
    private int backorder;
    private int noenviados;
    private int sugiere;
    private int cantidad;
    private int iidproveedor;
    private String vchproveedor;
    private int diasinventario;
    private int multempaque;
    private int membarque;
    private float costo;
}
