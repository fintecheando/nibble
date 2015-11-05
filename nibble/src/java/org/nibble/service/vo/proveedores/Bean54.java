// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Bean54.java

package org.nibble.service.vo.proveedores;

import dbbeans.desccargoscompra.dbDESCCARGOSCOMPRA;
import java.util.Vector;
import org.nibble.util.Formato;
//import util.Formato;

public class Bean54
{

    public Bean54()
    {
        factorCostoAdquisicion = 1.0F;
        factorCostoValuacion = 1.0F;
        factorProntoPago = 1.0F;
    }

    public String getDecfactor(int index)
    {
        dbDESCCARGOSCOMPRA a = (dbDESCCARGOSCOMPRA)vDesccompra.elementAt(index);
        return Formato.formateoNumerico(a.getDecfactor(), "#0.0000");
    }

    public String getFactorActual()
    {
        return Formato.formateoNumerico(FactorActual, "#0.0000");
    }

    public String getFactorCostoAdquisicion()
    {
        return Formato.formateoNumerico(factorCostoAdquisicion, "#0.0000");
    }

    public String getFactorCostoValuacion()
    {
        return Formato.formateoNumerico(factorCostoValuacion, "#0.0000");
    }

    public String getFactorProntoPago()
    {
        return Formato.formateoNumerico(factorProntoPago, "#0.0000");
    }

    public String getPorcentajeActual()
    {
        return Formato.toPercentage(PorcentajeActual);
    }

    public String getPorcentajeCosotAdquisicion()
    {
        return Formato.toPercentage(porcentajeCosotAdquisicion);
    }

    public String getPorcentajeCostoValuacion()
    {
        return Formato.toPercentage(porcentajeCostoValuacion);
    }

    public String getPorcentajeProntoPago()
    {
        return Formato.toPercentage(porcentajeProntoPago);
    }

    public String getProcentaje(int index)
    {
        dbDESCCARGOSCOMPRA a = (dbDESCCARGOSCOMPRA)vDesccompra.elementAt(index);
        return Formato.toPercentage(round((1.0F - a.getDecfactor()) * 100F));
    }

    public String getVchleyenda(int index)
    {
        dbDESCCARGOSCOMPRA a = (dbDESCCARGOSCOMPRA)vDesccompra.elementAt(index);
        return a.getVchleyenda();
    }

    public Vector getVDesccompra()
    {
        return vDesccompra;
    }

    public int getVDesccompraSize()
    {
        return vDesccompra.size();
    }

    public float redondeo4Cifras(float precio)
    {
        return (float)Math.round(precio * 10000F) / 10000F;
    }

    public float round(float precio)
    {
        return (float)Math.round(precio * 100F) / 100F;
    }

    public void setFactorActual(float newFactorActual)
    {
        FactorActual = newFactorActual;
    }

    public void setFactorCostoAdquisicion(float newFactorCostoAdquisicion)
    {
        factorCostoAdquisicion = newFactorCostoAdquisicion;
    }

    public void setFactorCostoValuacion(float newFactorCostoValuacion)
    {
        factorCostoValuacion = newFactorCostoValuacion;
    }

    public void setFactorProntoPago(float newFactorProntoPago)
    {
        factorProntoPago = newFactorProntoPago;
    }

    public void setPorcentajeActual(float newPorcentajeActual)
    {
        PorcentajeActual = newPorcentajeActual;
    }

    public void setPorcentajeCosotAdquisicion(float newPorcentajeCosotAdquisicion)
    {
        porcentajeCosotAdquisicion = newPorcentajeCosotAdquisicion;
    }

    public void setPorcentajeCostoValuacion(float newPorcentajeCostoValuacion)
    {
        porcentajeCostoValuacion = newPorcentajeCostoValuacion;
    }

    public void setPorcentajeProntoPago(float newPorcentajeProntoPago)
    {
        porcentajeProntoPago = newPorcentajeProntoPago;
    }

    public void setVDesccompra(Vector newVDesccompra)
    {
        vDesccompra = newVDesccompra;
        for(int i = 0; i < vDesccompra.size(); i++)
        {
            dbDESCCARGOSCOMPRA a = (dbDESCCARGOSCOMPRA)vDesccompra.elementAt(i);
            if(a.getVchleyenda().equals("PRONTO PAGO"))
            {
                factorProntoPago = a.getDecfactor();
            } else
            {
                factorCostoValuacion *= redondeo4Cifras(a.getDecfactor());
                
            }
        }

        factorCostoAdquisicion = redondeo4Cifras(factorProntoPago * factorCostoValuacion);
        porcentajeCostoValuacion = round((1.0F - factorCostoValuacion) * 100F);
        porcentajeProntoPago = round((1.0F - factorProntoPago) * 100F);
        porcentajeCosotAdquisicion = round((1.0F - factorCostoAdquisicion) * 100F);
    }

    private Vector vDesccompra;
    private float factorCostoValuacion;
    private float porcentajeCostoValuacion;
    private float factorProntoPago;
    private float porcentajeProntoPago;
    private float factorCostoAdquisicion;
    private float porcentajeCosotAdquisicion;
    private float FactorActual;
    private final String formato4digitos = "#0.0000";
    private float PorcentajeActual;
}
