// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Bean7Sugerir.java

package org.nibble.service.vo.pedidos;

import dbbeans.hisconsumo.dbHISCONSUMO;
import java.io.Serializable;
import java.util.Vector;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.nibble.util.UtilDate;
//import util.UtilDate;

public class Bean7Sugerir
    implements Serializable
{

    public Bean7Sugerir(dbHISCONSUMO dbBConsumo)
    {
        this.dbBConsumo = dbBConsumo;
    }

    public int getBackOrder()
    {
        return backOrder;
    }

    public int getDiasAlmacen()
    {
        return diasAlmacen;
    }

    public int getIndiceResurtido()
    {
        return indiceResurtido;
    }

    public int getMinimoPedido()
    {
        return minimoPedido;
    }

    public int getMultiploEmpaque()
    {
        return multiploEmpaque;
    }

    public void setBackOrder(int i)
    {
        backOrder = i;
    }

    public void setDiasAlmacen(int i)
    {
        diasAlmacen = i;
    }

    public void setMinimoPedido(int i)
    {
        minimoPedido = i;
    }

    public void setMultiploEmpaque(int i)
    {
        multiploEmpaque = i;
    }

    public int caculaSugerencia()
        throws Exception
    {
        int result = 0;
        int mesesOperacion = 0;
        int mesActual = UtilDate.getMM(UtilDate.getStrDate());
        int anioActual = UtilDate.getYY(UtilDate.getStrDate());
        double factor = 0.0D;
        consumo = 0;
        logger.debug("Calculando los meses de operacion");
        mesInicial++;
        if(mesInicial > 12)
        {
            mesInicial = 1;
            anioInicial++;
        }
        if(--mesActual <= 0)
        {
            mesActual = 12;
            anioActual--;
        }
        logger.debug("MesInicial:" + mesInicial + " anioInicial:" + anioInicial);
        logger.debug("MesActual:" + mesActual + " anioActual:" + anioActual);
        if(anioActual > anioInicial)
            mesesOperacion = (mesActual - mesInicial) + (anioActual - anioInicial) * 12;
        else
        if(anioActual == anioInicial)
            mesesOperacion = mesActual - mesInicial;
        logger.debug("Meses de Operacion:" + mesesOperacion);
        if(mesesOperacion > 6)
        {
            mesesOperacion = 6;
            if(mesActual - 5 <= 0)
                mesInicial = 12 + (mesActual - 5);
        }
        logger.debug("MesInicial:" + mesInicial);
        logger.debug("Meses de Operacion 1:" + mesesOperacion);
        int meses[] = new int[mesesOperacion];
        for(int i = 0; i < mesesOperacion; i++)
        {
            meses[i] = mesInicial;
            mesInicial++;
            if(mesInicial > 12)
                mesInicial = 1;
        }

        int anios[];
        if(anioInicial != anioActual)
        {
            anios = new int[2];
            anios[0] = anioInicial;
            anios[1] = anioActual;
        } else
        {
            anios = new int[1];
            anios[0] = anioInicial;
        }
        if(mesesOperacion == 0)
        {
            consumo = hisConsumo * 6;
        } else
        {
            consumo = dbBConsumo.calcIndiceConsumo(meses, anios);
            consumo += (6 - mesesOperacion) * hisConsumo;
        }
        logger.debug("HisConsumo:" + hisConsumo);
        logger.debug("Consumo:" + consumo);
        if(consumo == 0)
            consumo = hisConsumo * 6;
        logger.debug("Consumo recalculado:" + consumo);
        factor = (double)consumo / 180D;
        logger.debug("Factor:" + factor);
        consumo /= 6;
        logger.debug("diasAlmacen:" + diasAlmacen);
        indiceResurtido = (int)(factor * (double)diasAlmacen);
        logger.debug("Indice Resurtido:" + indiceResurtido);
        if(existencia + backOrder >= indiceResurtido)
            result = 0;
        else
            result = indiceResurtido - existencia - backOrder;
        if(result < minimoPedido)
            result = 0;
        result /= multiploEmpaque;
        result *= multiploEmpaque;
        return result;
    }

    public Vector caculaConsumo()
        throws Exception
    {
        Vector result = null;
        int mesesOperacion = 0;
        int mesActual = UtilDate.getMM(UtilDate.getStrDate());
        int anioActual = UtilDate.getYY(UtilDate.getStrDate());
        consumo = 0;
        logger.debug("Calculando los meses de operacion");
        mesInicial++;
        if(mesInicial > 12)
        {
            mesInicial = 1;
            anioInicial++;
        }
        if(--mesActual <= 0)
        {
            mesActual = 12;
            anioActual--;
        }
        logger.debug("MesInicial:" + mesInicial + " anioInicial:" + anioInicial);
        logger.debug("MesActual:" + mesActual + " anioActual:" + anioActual);
        if(anioActual > anioInicial)
            mesesOperacion = (mesActual - mesInicial) + (anioActual - anioInicial) * 12;
        else
        if(anioActual == anioInicial)
            mesesOperacion = mesActual - mesInicial;
        logger.debug("Meses de Operacion:" + mesesOperacion);
        if(mesesOperacion > 6)
        {
            mesesOperacion = 6;
            if(mesActual - 5 <= 0)
                mesInicial = 12 + (mesActual - 5);
        }
        logger.debug("MesInicial:" + mesInicial);
        logger.debug("Meses de Operacion 1:" + mesesOperacion);
        int meses[] = new int[mesesOperacion];
        for(int i = 0; i < mesesOperacion; i++)
        {
            meses[i] = mesInicial;
            mesInicial++;
            if(mesInicial > 12)
                mesInicial = 1;
        }

        int anios[];
        if(anioInicial != anioActual)
        {
            anios = new int[2];
            anios[0] = anioInicial;
            anios[1] = anioActual;
        } else
        {
            anios = new int[1];
            anios[0] = anioInicial;
        }
        if(mesesOperacion == 0)
        {
            result = new Vector();
            for(int i = 0; i < 6; i++)
                result.addElement(new Integer(hisConsumo));

        } else
        {
            result = dbBConsumo.calcConsumoMensual(meses, anios);
            logger.debug("Vector Tamano:" + result.size());
            int tamano = result.size();
            for(int i = 0; i < 6 - tamano; i++)
                result.addElement(new Integer(hisConsumo));

        }
        logger.debug("Vector Consumo:" + result);
        return result;
    }

    public dbHISCONSUMO getDbBConsumo()
    {
        return dbBConsumo;
    }

    public void setDbBConsumo(dbHISCONSUMO dbhisconsumo)
    {
        dbBConsumo = dbhisconsumo;
    }

    public int getAnioInicial()
    {
        return anioInicial;
    }

    public int getMesInicial()
    {
        return mesInicial;
    }

    public void setAnioInicial(int i)
    {
        anioInicial = i;
    }

    public void setMesInicial(int i)
    {
        mesInicial = i;
    }

    public int getExistencia()
    {
        return existencia;
    }

    public void setExistencia(int i)
    {
        existencia = i;
    }

    public int getHisConsumo()
    {
        return hisConsumo;
    }

    public void setHisConsumo(int i)
    {
        hisConsumo = i;
    }

    public int getConsumo()
    {
        return consumo;
    }

    private static Log logger;
    private int indiceResurtido;
    private int minimoPedido;
    private int diasAlmacen;
    private int multiploEmpaque;
    private int backOrder;
    private int mesInicial;
    private int anioInicial;
    private int existencia;
    private int hisConsumo;
    private dbHISCONSUMO dbBConsumo;
    private int consumo;

    static 
    {
        logger = LogFactory.getLog(Bean7Sugerir.class);
    }
}
