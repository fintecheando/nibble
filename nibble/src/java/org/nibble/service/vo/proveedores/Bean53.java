// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Bean53.java

package org.nibble.service.vo.proveedores;

import dbbeans.categoria.dbCATEGORIA;
import dbbeans.grupo.dbGRUPO;
import dbbeans.linea.dbLINEA;
import dbbeans.marca.dbMARCA;
import dbbeans.unidad.dbUNIDAD;
import java.util.Vector;

public class Bean53
{

    public Bean53()
    {
        
        vCategorias = new Vector();
        vGrupos = new Vector();
        vLineas = new Vector();
        vMarcas = new Vector();
    }

    public String getCategoriasDes(int index)
    {
        dbCATEGORIA a = (dbCATEGORIA)vCategorias.elementAt(index);
        return a.getVchdescripcion();
    }

    public int getCategoriasId(int index)
    {
        dbCATEGORIA a = (dbCATEGORIA)vCategorias.elementAt(index);
        return a.getIidcategoria();
    }

    public int getCategoriasSize()
    {
        
        return vCategorias.size();
    }

    public String getGruposDes(int index)
    {
        dbGRUPO a = (dbGRUPO)vGrupos.elementAt(index);
        return a.getVchdescripcion();
    }

    public int getGruposId(int index)
    {
        dbGRUPO a = (dbGRUPO)vGrupos.elementAt(index);
        return a.getIidgrupo();
    }

    public int getGruposIdCategoria(int index)
    {
        dbGRUPO a = (dbGRUPO)vGrupos.elementAt(index);
        return a.getIidcategoria();
    }

    public int getGruposSize()
    {
        
        return vGrupos.size();
    }

    public String getLineasDes(int index)
    {
        dbLINEA a = (dbLINEA)vLineas.elementAt(index);
        return a.getVchdescripcion();
    }

    public int getLineasId(int index)
    {
        dbLINEA a = (dbLINEA)vLineas.elementAt(index);
        return a.getIidlinea();
    }

    public int getLineasIdGrupo(int index)
    {
        dbLINEA a = (dbLINEA)vLineas.elementAt(index);
        return a.getIidgrupo();
    }

    public int getLineasSize()
    {
        
        return vLineas.size();
    }

    public String getMarcasDes(int index)
    {
        dbMARCA a = (dbMARCA)vMarcas.elementAt(index);
        return a.getVchnombre();
    }

    public int getMarcasId(int index)
    {
        dbMARCA a = (dbMARCA)vMarcas.elementAt(index);
        return a.getIidmarca();
    }

    public int getMarcasIdLinea(int index)
    {
        dbMARCA a = (dbMARCA)vMarcas.elementAt(index);
        return a.getIidlinea();
    }

    public String getUnidadsDes(int index)
    {
        dbUNIDAD a = (dbUNIDAD)vUnidads.elementAt(index);
        return a.getVchdescripcion();
    }

    public int getUnidadsId(int index)
    {
        dbUNIDAD a = (dbUNIDAD)vUnidads.elementAt(index);
        return a.getIidunidad();
    }

    public int getUnidadsSize()
    {
        return vUnidads.size();
    }

    public int getMarcasSize()
    {
        
        return vMarcas.size();
    }

    public Vector getVCategorias()
    {
        return vCategorias;
    }

    public Vector getVGrupos()
    {
        return vGrupos;
    }

    public Vector getVLineas()
    {
        return vLineas;
    }

    public Vector getVMarcas()
    {
        return vMarcas;
    }

    public Vector getVUnidads()
    {
        return vUnidads;
    }

    public void setVCategorias(Vector newVCategorias)
    {
        vCategorias = newVCategorias;
    }

    public void setVGrupos(Vector newVGrupos)
    {
        vGrupos = newVGrupos;
    }

    public void setVLineas(Vector newVLineas)
    {
        vLineas = newVLineas;
    }

    public void setVMarcas(Vector newVMarcas)
    {
        vMarcas = newVMarcas;
    }

    public void setVUnidads(Vector newVUnidads)
    {
        vUnidads = newVUnidads;
    }

    private Vector vCategorias;
    private Vector vGrupos;
    private Vector vLineas;
    private Vector vMarcas;
    private Vector vUnidads;
}
