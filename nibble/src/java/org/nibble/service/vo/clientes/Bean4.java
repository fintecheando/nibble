// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Bean4.java

package org.nibble.service.vo.clientes;

import dbbeans.categoriacliente.dbCATEGORIACLIENTE;
import java.util.Vector;

public class Bean4
{

    public Bean4()
    {
    }

    public int getIidcatcliente(int index)
    {
        dbCATEGORIACLIENTE a = (dbCATEGORIACLIENTE)vCatclientes.elementAt(index);
        return a.getIidcatcliente();
    }

    public int getSize()
    {
        return vCatclientes.size();
    }

    public Vector getVCatclientes()
    {
        return vCatclientes;
    }

    public String getVchdescripcion(int index)
    {
        dbCATEGORIACLIENTE a = (dbCATEGORIACLIENTE)vCatclientes.elementAt(index);
        return a.getVchdescripcion();
    }

    public void setVCatclientes(Vector newVCatclientes)
    {
        vCatclientes = newVCatclientes;
    }

    private Vector vCatclientes;
}
