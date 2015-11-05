// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Bean42.java

package org.nibble.service.vo.clientes;

import dbbeans.niveldeprecio.dbNIVELDEPRECIO;
import java.util.Vector;
import org.nibble.util.Formato;
//import util.Formato;

public class Bean42
{

    public Bean42()
    {
    }

    public String getDecporccomision(int index)
    {
        dbNIVELDEPRECIO a = (dbNIVELDEPRECIO)vNivelesPrecio.elementAt(index);
        return Formato.toPercentage(a.getDecporccomision());
    }

    public String getDecutilidad(int index)
    {
        dbNIVELDEPRECIO a = (dbNIVELDEPRECIO)vNivelesPrecio.elementAt(index);
        return Formato.toPercentage(a.getDecutilidad());
    }

    public int getId(int index)
    {
        dbNIVELDEPRECIO a = (dbNIVELDEPRECIO)vNivelesPrecio.elementAt(index);
        return a.getIidnivel();
    }

    public int getIidcatcliente()
    {
        return iidcatcliente;
    }

    public String getMsg()
    {
        return msg != null ? msg : "";
    }

    public String getNivel(int index)
    {
        dbNIVELDEPRECIO a = (dbNIVELDEPRECIO)vNivelesPrecio.elementAt(index);
        return a.getVchnivel();
    }

    public int getSize()
    {
        return vNivelesPrecio.size();
    }

    public Vector getVNivelesPrecio()
    {
        return vNivelesPrecio;
    }

    public void setIidcatcliente(int newIidcatcliente)
    {
        iidcatcliente = newIidcatcliente;
    }

    public void setMsg(String newMsg)
    {
        msg = newMsg;
    }

    public void setVNivelesPrecio(Vector newVNivelesPrecio)
    {
        vNivelesPrecio = newVNivelesPrecio;
    }

    private Vector vNivelesPrecio;
    private int iidcatcliente;
    private String msg;
}
