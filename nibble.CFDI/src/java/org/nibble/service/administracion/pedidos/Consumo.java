// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Consumo.java

package org.nibble.service.administracion.pedidos;

import dbbeans.facturadetalle.dbFACTURADETALLEs;
import dbbeans.inventario.dbINVENTARIO;
import java.sql.Connection;
import java.sql.SQLException;
import org.nibble.util.Fecha;
//import util.Fecha;

public class Consumo
{

    public Consumo()
    {
    }

    public Connection getConn()
    {
        return conn;
    }

    public int getConsumo(long liidparte, int iidproveedor)
        throws Exception, SQLException
    {
        dbFACTURADETALLEs fd = new dbFACTURADETALLEs();
        dbINVENTARIO inv = new dbINVENTARIO();
        fd.setConnection(conn);
        inv.setConnection(conn);
        String formato = "yyyyMMddHHmmss";
        String fechaini = fechaInstalacion;
        String hoy = Fecha.getCurrentDate("yyyyMMddHHmmss");
        int diff = Fecha.getdiferencia(fechaini, hoy, "yyyyMMddHHmmss");
        if(diff > 180)
            fechaini = Fecha.MinusDiasFecha(180, hoy, "yyyyMMddHHmmss");
        float consumo = fd.getConsumoByFecha(liidparte, iidproveedor, fechaini, hoy);
        
        if(diff < 180)
        {
            inv.setLiidparte(liidparte);
            inv.setIidproveedor(iidproveedor);
            inv.load();
            double histconcumo = (double)inv.getIcantconsumo() / 30D;
            consumo = (float)((double)consumo + histconcumo * (double)(180 - diff));
            
        }
        return (int)Math.ceil(consumo / 6F);
    }

    public String getFechaInstalacion()
    {
        return fechaInstalacion;
    }

    public void setConn(Connection newConn)
    {
        conn = newConn;
    }

    public void setFechaInstalacion(String newFechaInstalacion)
    {
        fechaInstalacion = newFechaInstalacion;
    }

    private Connection conn;
    private int ID_CONFIGPVA;
    private final int PERIODO = 180;
    private final int MESES = 6;
    private String fechaInstalacion;
}
