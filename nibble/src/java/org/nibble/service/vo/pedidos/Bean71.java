// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Bean71.java

package org.nibble.service.vo.pedidos;

import java.util.Vector;
import org.nibble.util.Formato;
//import util.Formato;

// Referenced classes of package nc.beans7:
//            Bean71Detalle, Bean71Concentrado

public class Bean71
{

    public Bean71()
    {
    }

    public boolean isBitsalidareciente()
    {
        return bitsalidareciente;
    }

    public Vector getVConcentrado()
    {
        return vConcentrado;
    }

    public Vector getVDetalle()
    {
        return vDetalle;
    }

    public int getVdetallesize()
    {
        int i = 0;
        if(vDetalle != null)
            i = vDetalle.size();
        return i;
    }

    public int getVconcentradosize()
    {
        int i = 0;
        if(vConcentrado != null)
            i = vConcentrado.size();
        return i;
    }

    public long getDliidparte(int i)
    {
        Bean71Detalle d = (Bean71Detalle)vDetalle.elementAt(i);
        return d.getLiidparte();
    }

    public int getDiidprovedor(int i)
    {
        Bean71Detalle d = (Bean71Detalle)vDetalle.elementAt(i);
        return d.getIidproveedor();
    }

    public String getDvchparte(int i)
    {
        Bean71Detalle d = (Bean71Detalle)vDetalle.elementAt(i);
        return d.getNoparte();
    }

    public String getDvchproveedor(int i)
    {
        Bean71Detalle d = (Bean71Detalle)vDetalle.elementAt(i);
        return d.getVchproveedor();
    }

    public int getDdiasinventario(int i)
    {
        Bean71Detalle d = (Bean71Detalle)vDetalle.elementAt(i);
        return d.getDiasinventario();
    }

    public int getDmultempaque(int i)
    {
        Bean71Detalle d = (Bean71Detalle)vDetalle.elementAt(i);
        return d.getMultempaque();
    }

    public int getDmembarque(int i)
    {
        Bean71Detalle d = (Bean71Detalle)vDetalle.elementAt(i);
        return d.getMembarque();
    }

    public int getDexistencia(int i)
    {
        Bean71Detalle d = (Bean71Detalle)vDetalle.elementAt(i);
        return d.getExistencia();
    }

    public int getDconsumo(int i)
    {
        Bean71Detalle d = (Bean71Detalle)vDetalle.elementAt(i);
        return d.getConsumo();
    }

    public int getDindiceresurtido(int i)
    {
        Bean71Detalle d = (Bean71Detalle)vDetalle.elementAt(i);
        return d.getIndiceresurtido();
    }

    public int getDbackorder(int i)
    {
        Bean71Detalle d = (Bean71Detalle)vDetalle.elementAt(i);
        return d.getBackorder();
    }

    public int getDnoenviados(int i)
    {
        Bean71Detalle d = (Bean71Detalle)vDetalle.elementAt(i);
        return d.getNoenviados();
    }

    public int getDsugiere(int i)
    {
        Bean71Detalle d = (Bean71Detalle)vDetalle.elementAt(i);
        return d.getSugiere();
    }

    public String getDcosto(int i)
    {
        String ret = "0.00";
        Bean71Detalle d = (Bean71Detalle)vDetalle.elementAt(i);
        if(d.getCosto() > 0.0F)
            ret = Formato.formateoNumerico(d.getCosto(), "#,###,###,##0.00");
        return ret;
    }

    public int getDcantidad(int i)
    {
        Bean71Detalle d = (Bean71Detalle)vDetalle.elementAt(i);
        return d.getCantidad();
    }

    public long getCliidparte(int i)
    {
        Bean71Concentrado c = (Bean71Concentrado)vConcentrado.elementAt(i);
        return c.getLiidparte();
    }

    public String getCvchparte(int i)
    {
        Bean71Concentrado c = (Bean71Concentrado)vConcentrado.elementAt(i);
        return c.getVchparte();
    }

    public int getCexistencia(int i)
    {
        Bean71Concentrado c = (Bean71Concentrado)vConcentrado.elementAt(i);
        return c.getExistencia();
    }

    public int getCconsumo(int i)
    {
        Bean71Concentrado c = (Bean71Concentrado)vConcentrado.elementAt(i);
        return c.getConsumo();
    }

    public int getChconsumo(int i)
    {
        Bean71Concentrado c = (Bean71Concentrado)vConcentrado.elementAt(i);
        return c.getHconsumo();
    }

    public int getCindiceresurtido(int i)
    {
        Bean71Concentrado c = (Bean71Concentrado)vConcentrado.elementAt(i);
        return c.getIndiceresurtido();
    }

    public int getCmm(int i)
    {
        Bean71Concentrado c = (Bean71Concentrado)vConcentrado.elementAt(i);
        return c.getMm();
    }

    public int getCyy(int i)
    {
        Bean71Concentrado c = (Bean71Concentrado)vConcentrado.elementAt(i);
        return c.getYy();
    }

    public int getCbackorder(int i)
    {
        Bean71Concentrado c = (Bean71Concentrado)vConcentrado.elementAt(i);
        return c.getBackorder();
    }

    public int getCnoenviados(int i)
    {
        Bean71Concentrado c = (Bean71Concentrado)vConcentrado.elementAt(i);
        return c.getNoenviados();
    }

    public int getCsugiere(int i)
    {
        Bean71Concentrado c = (Bean71Concentrado)vConcentrado.elementAt(i);
        return c.getSugiere();
    }

    public int getCcantidad(int i)
    {
        Bean71Concentrado c = (Bean71Concentrado)vConcentrado.elementAt(i);
        return c.getCantidad();
    }

    public void setBitsalidareciente(boolean b)
    {
        bitsalidareciente = b;
    }

    public void setVConcentrado(Vector vector)
    {
        vConcentrado = vector;
    }

    public void setVDetalle(Vector vector)
    {
        vDetalle = vector;
    }

    public void setVIndices(Vector v)
    {
        vIndices = v;
    }

    public int getVindicessize()
    {
        return vIndices.size();
    }

    public int getIndice(int index)
    {
        Integer i = (Integer)vIndices.elementAt(index);
        int ret;
        return ret = i.intValue();
    }

    public Vector getVProveedorSel()
    {
        return vProveedorSel;
    }

    public void setVProveedorSel(Vector vector)
    {
        vProveedorSel = vector;
    }

    private Vector vConcentrado;
    private Vector vDetalle;
    private Vector vIndices;
    private Vector vProveedorSel;
    private boolean bitsalidareciente;
}
