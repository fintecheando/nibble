// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Bean73c.java

package org.nibble.service.vo.pedidos;


public class Bean73c
{

    public Bean73c()
    {
    }

    public String getFecha()
    {
        return fecha;
    }

    public int getIidpedido()
    {
        return iidpedido;
    }

    public int getIidproveedor()
    {
        return iidproveedor;
    }

    public void setFecha(String newFecha)
    {
        fecha = newFecha;
    }

    public void setIidpedido(int newIidpedido)
    {
        iidpedido = newIidpedido;
    }

    public void setIidproveedor(int newIidproveedor)
    {
        iidproveedor = newIidproveedor;
    }

    private int iidpedido;
    private int iidproveedor;
    private String fecha;
}
