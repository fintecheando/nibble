// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ProveedoresMinimoPedido.java

package org.nibble.service.administracion.proveedores;

import dbbeans.clasificproveedor.dbCLASIFICPROVEEDOR;
import dbbeans.proveedor.dbPROVEEDOR;
import dbbeans.proveedor.dbPROVEEDORs;
import dbrbeans.clasificproveedor.dbrCLASIFICPROVEEDORs;
import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.*;
import org.nibble.main.BeanError;
import org.nibble.main.BeanUtil;
import org.nibble.service.vo.proveedores.Bean53d;
//import main.BeanError;
//import main.BeanUtil;
//import nc.beans5.Bean53d;

public class ProveedoresMinimoPedido extends HttpServlet
{

    public ProveedoresMinimoPedido()
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
            dbPROVEEDOR proveedor = new dbPROVEEDOR();
            proveedor.setConnection(con);
            dbPROVEEDORs proveedores = new dbPROVEEDORs();
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
            case 3: // '\003'
            {
                dbCLASIFICPROVEEDOR dbCP = new dbCLASIFICPROVEEDOR();
                dbCP.setConnection(con);
                Bean53d bean53d = (Bean53d)ses_usr.getAttribute("bean53d");
                for(int i = 0; i < bean53d.getProveedoresSize(); i++)
                    if(request.getParameter("T" + bean53d.getId(i)) != null && !request.getParameter("T" + bean53d.getId(i)).equals(""))
                    {
                        int minimo = 0;
                        minimo = (new Integer(request.getParameter("T" + bean53d.getId(i)))).intValue();
                        if(minimo > 0)
                        {
                            dbCP.setIidproveedor(bean53d.getId(i));
                            dbCP.setIidcategoria(iidcategoria);
                            dbCP.setIidgrupo(iidgrupo);
                            dbCP.setIidlinea(iidlinea);
                            dbCP.setIidmarca(iidmarca);
                            dbCP.load();
                            dbCP.setIminimopedido(minimo);
                            dbCP.store();
                        }
                    }

                // fall through
            }

            case 2: // '\002'
            {
                Bean53d bean53d = new Bean53d();
                dbrCLASIFICPROVEEDORs cp = new dbrCLASIFICPROVEEDORs();
                cp.setConnection(con);
                bean53d.setVProveedores(cp.findByClasificacion(iidcategoria, iidgrupo, iidlinea, iidmarca));
                ses_usr.setAttribute("bean53d", bean53d);
                strAddress = "/jsp/5_6b.jsp";
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
