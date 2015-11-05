// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   AsociacionProveedorClas.java

package org.nibble.service.administracion.proveedores;

import dbbeans.clasificproveedor.dbCLASIFICPROVEEDOR;
import dbbeans.inventario.dbINVENTARIO;
import dbbeans.parte.dbPARTE;
import dbbeans.parte.dbPARTEs;
import dbbeans.proveedor.dbPROVEEDOR;
import dbbeans.proveedor.dbPROVEEDORs;
import dbrbeans.clasificproveedor.dbrCLASIFICPROVEEDORs;
import general.Help;
import java.io.IOException;
import java.util.Vector;
import javax.servlet.*;
import javax.servlet.http.*;
import org.nibble.main.BeanError;
import org.nibble.main.BeanUtil;
import org.nibble.service.vo.proveedores.Bean52;
import org.nibble.service.vo.proveedores.Bean53d;
//import main.BeanError;
//import main.BeanUtil;
//import nc.beans5.Bean52;
//import nc.beans5.Bean53d;

// Referenced classes of package nc.servlets5:
//            BeanHelper53

public class AsociacionProveedorClas extends HttpServlet
{

    public AsociacionProveedorClas()
    {
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {
        performTask(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {
        performTask(request, response);
    }

    public void init()
    {
    }

    public void performTask(HttpServletRequest request, HttpServletResponse response)
    {
        java.sql.Connection con = null;
        HttpSession ses_usr = null;
        BeanUtil bu = null;
        RequestDispatcher dispatcher = null;
        String strAddress = null;
        try
        {
            ses_usr = request.getSession(false);
            bu = new BeanUtil();
            con = bu.getConn();
            bu.setSession(ses_usr);
            if(!bu.getSessionValues())
                throw new Exception("Por motivos de seguridad la sesi\363n ha expirado\n Registrese nuevamente");
            int accion = 1;
            BeanHelper53 hlp53 = new BeanHelper53();
            dbPROVEEDOR proveedor = new dbPROVEEDOR();
            dbPROVEEDORs proveedores = new dbPROVEEDORs();
            proveedor.setConnection(con);
            proveedores.setConnection(con);
            if(request.getParameter("accion") != null)
                accion = (new Integer(request.getParameter("accion"))).intValue();
            int iidproveedor = 0;
            if(request.getParameter("iidproveedor") != null && !request.getParameter("iidproveedor").equals(""))
                iidproveedor = (new Integer(request.getParameter("iidproveedor"))).intValue();
            int iidcategoria = 0;
            if(request.getParameter("iidcategoria") != null && !request.getParameter("iidcategoria").equals(""))
                iidcategoria = (new Integer(request.getParameter("iidcategoria"))).intValue();
            int iidgrupo = 0;
            if(request.getParameter("iidgrupo") != null && !request.getParameter("iidgrupo").equals(""))
                iidgrupo = (new Integer(request.getParameter("iidgrupo"))).intValue();
            int iidlinea = 0;
            if(request.getParameter("iidlinea") != null && !request.getParameter("iidlinea").equals(""))
                iidlinea = (new Integer(request.getParameter("iidlinea"))).intValue();
            int iidmarca = 0;
            if(request.getParameter("iidmarca") != null && !request.getParameter("iidmarca").equals(""))
                iidmarca = (new Integer(request.getParameter("iidmarca"))).intValue();
            switch(accion)
            {
            case 1: // '\001'
            {
                hlp53.getData(con);
                ses_usr.setAttribute("bean53", hlp53.getBean53());
                strAddress = "/jsp/5_3a.jsp";
                break;
            }

            case 3: // '\003'
            {
                dbCLASIFICPROVEEDOR dbCP = new dbCLASIFICPROVEEDOR();
                dbPARTEs partes = new dbPARTEs();
                dbINVENTARIO inventario = new dbINVENTARIO();
                dbCP.setConnection(con);
                partes.setConnection(con);
                inventario.setConnection(con);
                Bean52 bean52 = (Bean52)ses_usr.getAttribute("bean52a");
                for(int i = 0; i < bean52.getProveedoresSize(); i++)
                {
                    
                    if(request.getParameter("C" + bean52.getId(i)) != null)
                    {
                        
                        dbCP.setIidproveedor(bean52.getId(i));
                        dbCP.setIidcategoria(iidcategoria);
                        dbCP.setIidgrupo(iidgrupo);
                        dbCP.setIidlinea(iidlinea);
                        dbCP.setIidmarca(iidmarca);
                        dbCP.create();
                        String vchnumparte = Help.getVchnumparte(null, null, null, null);
                        partes.findByPar(iidcategoria, iidgrupo, iidlinea, iidmarca, vchnumparte, false);
                        Vector v = partes.getResult();
                        for(int j = 0; j < v.size(); j++)
                        {
                            dbPARTE parte = (dbPARTE)v.elementAt(j);
                            inventario.setLiidparte(parte.getLiidparte());
                            inventario.setIidproveedor(bean52.getId(i));
                            try
                            {
                                inventario.create();
                            }
                            catch(Exception exception) { }
                        }

                    }
                }

                // fall through
            }

            case 2: // '\002'
            {
                Bean52 bean52 = new Bean52();
                Bean52 bean = new Bean52();
                bean52.setVProveedores(proveedores.findByClasificacion(iidcategoria, iidgrupo, iidlinea, iidmarca));
                proveedores.findByNotClasificacion(iidcategoria, iidgrupo, iidlinea, iidmarca);
                bean.setVProveedores(proveedores.getResult());
                ses_usr.setAttribute("bean52", bean52);
                ses_usr.setAttribute("bean52a", bean);
                strAddress = "/jsp/5_3b.jsp";
                break;
            }

            case 5: // '\005'
            {
                dbCLASIFICPROVEEDOR dbCP = new dbCLASIFICPROVEEDOR();
                dbPARTEs partes = new dbPARTEs();
                dbINVENTARIO inventario = new dbINVENTARIO();
                dbCP.setConnection(con);
                partes.setConnection(con);
                inventario.setConnection(con);
                Bean52 bean52 = (Bean52)ses_usr.getAttribute("bean52");
                for(int i = 0; i < bean52.getProveedoresSize(); i++)
                    if(request.getParameter("C" + bean52.getId(i)) != null)
                    {
                        dbCP.setIidproveedor(bean52.getId(i));
                        dbCP.setIidcategoria(iidcategoria);
                        dbCP.setIidgrupo(iidgrupo);
                        dbCP.setIidlinea(iidlinea);
                        dbCP.setIidmarca(iidmarca);
                        dbCP.remove();
                        String vchnumparte = Help.getVchnumparte(null, null, null, null);
                        partes.findByPar(iidcategoria, iidgrupo, iidlinea, iidmarca, vchnumparte, false);
                        Vector v = partes.getResult();
                        for(int j = 0; j < v.size(); j++)
                        {
                            dbPARTE parte = (dbPARTE)v.elementAt(j);
                            inventario.setLiidparte(parte.getLiidparte());
                            inventario.setIidproveedor(bean52.getId(i));
                            try
                            {
                                inventario.load();
                            }
                            catch(Exception exception1) { }
                            if(inventario.getIexistencia() == 0)
                                try
                                {
                                    inventario.remove();
                                }
                                catch(Exception exception2) { }
                        }

                    }

                // fall through
            }

            case 4: // '\004'
            {
                Bean52 bean52 = new Bean52();
                bean52.setVProveedores(proveedores.findByClasificacion(iidcategoria, iidgrupo, iidlinea, iidmarca));
                ses_usr.setAttribute("bean52", bean52);
                strAddress = "/jsp/5_3c.jsp";
                break;
            }

            case 7: // '\007'
            {
                dbCLASIFICPROVEEDOR dbCP = new dbCLASIFICPROVEEDOR();
                dbCP.setConnection(con);
                Bean53d bean53d = (Bean53d)ses_usr.getAttribute("bean53d");
                for(int i = 0; i < bean53d.getProveedoresSize(); i++)
                {
                    dbCP.setIidproveedor(bean53d.getId(i));
                    dbCP.setIidcategoria(iidcategoria);
                    dbCP.setIidgrupo(iidgrupo);
                    dbCP.setIidlinea(iidlinea);
                    dbCP.setIidmarca(iidmarca);
                    dbCP.setBitprovefavorito(false);
                    if(iidproveedor == bean53d.getId(i))
                        dbCP.setBitprovefavorito(true);
                    dbCP.store();
                }

                // fall through
            }

            case 6: // '\006'
            {
                Bean53d bean53d = new Bean53d();
                dbrCLASIFICPROVEEDORs cp = new dbrCLASIFICPROVEEDORs();
                cp.setConnection(con);
                bean53d.setVProveedores(cp.findByClasificacion(iidcategoria, iidgrupo, iidlinea, iidmarca));
                ses_usr.setAttribute("bean53d", bean53d);
                strAddress = "/jsp/5_3d.jsp";
                break;
            }
            }
        }
        catch(Throwable e)
        {
            bu.setCommit(false);
            e.printStackTrace();
            BeanError error = new BeanError();
            error.setErrorMessage(e.getMessage());
            request.setAttribute("error", error);
            strAddress = "/jsp/error.jsp";
        }
        finally
        {
            try
            {
                bu.finalizeTransaction(con);
                dispatcher = getServletContext().getRequestDispatcher(strAddress);
                dispatcher.forward(request, response);
            }
            catch(Exception ex)
            {
                System.out.println("FATAL:" + ex.getMessage());
            }
        }
    }
}
