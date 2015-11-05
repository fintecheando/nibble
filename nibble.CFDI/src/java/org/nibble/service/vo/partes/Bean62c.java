// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Bean62c.java

package org.nibble.service.vo.partes;

import dbbeans.parte.dbPARTE;
import dbbeans.unidad.dbUNIDAD;
import general.Help;
import java.util.Vector;

public class Bean62c
{

    public Bean62c()
    {
    }

    public float getDecpreciob100()
    {
        return parte.getDecpreciob100();
    }

    public String getFichatecnica()
    {
        return parte.getFichatecnica();
    }

    public float getFlalto()
    {
        return parte.getFlalto();
    }

    public float getFlancho()
    {
        return parte.getFlancho();
    }

    public float getFllargo()
    {
        return parte.getFllargo();
    }

    public float getFlpeso()
    {
        return parte.getFlpeso();
    }

    public int getIidunidad()
    {
        return parte.getIidunidad();
    }

    public int getIidunidad(int index)
    {
        dbUNIDAD a = (dbUNIDAD)vUnidades.elementAt(index);
        return a.getIidunidad();
    }

    public int getIminembarque()
    {
        return parte.getIminembarque();
    }

    public int getImultiploempaque()
    {
        return parte.getImultiploempaque();
    }

    public long getLiidparte()
    {
        return parte.getLiidparte();
    }

    public String getMedida()
    {
        return Help.getMedida(parte.getVchnumparte());
    }

    public String getNumero()
    {
        return Help.getNumero(parte.getVchnumparte());
    }

    public dbPARTE getParte()
    {
        return parte;
    }

    public String getPrefijo()
    {
        return Help.getPrefijo(parte.getVchnumparte());
    }

    public int getSize()
    {
        return vUnidades.size();
    }

    public String getSufijo()
    {
        return Help.getSufijo(parte.getVchnumparte());
    }

    public String getVchdescripcion(int index)
    {
        dbUNIDAD a = (dbUNIDAD)vUnidades.elementAt(index);
        return a.getVchdescripcion();
    }

    public String getVchdescripciones()
    {
        return parte.getVchdescripciones();
    }

    public String getVchnumparte()
    {
        return parte.getVchnumparte();
    }

    public String getVchobservaciones()
    {
        return parte.getVchobservaciones();
    }

    public Vector getVUnidades()
    {
        return vUnidades;
    }

    public boolean isBitventaunitaria()
    {
        return parte.getBitventaunitaria();
    }

    public void setParte(dbPARTE newParte)
    {
        parte = newParte;
    }

    public void setVUnidades(Vector newVUnidades)
    {
        vUnidades = newVUnidades;
    }

    public int getIidcategoria(){
        return parte.getIidcategoria();
    }

    public int getIidgrupo(){
        return parte.getIidgrupo();
    }

    public int getIidlinea(){
        return parte.getIidlinea();
    }

    public int getIidmarca(){
        return parte.getIidmarca();
    }

    private Vector vUnidades;
    private dbPARTE parte;
}
