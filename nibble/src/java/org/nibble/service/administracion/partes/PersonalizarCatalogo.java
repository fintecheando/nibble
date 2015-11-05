// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PersonalizarCatalogo.java

package org.nibble.service.administracion.partes;

import dbbeans.parte.dbPARTE;
import dbbeans.parte.dbPARTEs;
import general.Help;
import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.*;
import org.nibble.main.BeanError;
import org.nibble.main.BeanUtil;
import org.nibble.service.vo.partes.Bean63;
//import main.BeanError;
//import main.BeanUtil;
//import nc.beans6.Bean63;

public class PersonalizarCatalogo extends HttpServlet
{

    public PersonalizarCatalogo()
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

    public String getServletInfo()
    {
        return super.getServletInfo();
    }

    public void init()
    {
        idModulo = getServletConfig().getInitParameter("idModulo") == null ? -1 : (new Integer(getServletConfig().getInitParameter("idModulo"))).intValue();
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
            if(!bu.getAcceso(idModulo, con))
                throw new Exception("No tiene permiso a este m\363dulo");
            int accion = 1;
            int iidcategoria = 0;
            int iidgrupo = 0;
            int iidlinea = 0;
            int iidmarca = 0;
            dbPARTE parte = new dbPARTE();
            dbPARTEs partes = new dbPARTEs();
            parte.setConnection(con);
            partes.setConnection(con);
            if(request.getParameter("accion") != null)
                accion = (new Integer(request.getParameter("accion"))).intValue();
            if(request.getParameter("vchcategoria") != null)
                iidcategoria = (new Integer(request.getParameter("iidcategoria"))).intValue();
            if(request.getParameter("vchgrupo") != null)
                iidgrupo = (new Integer(request.getParameter("iidgrupo"))).intValue();
            if(request.getParameter("vchlinea") != null)
                iidlinea = (new Integer(request.getParameter("iidlinea"))).intValue();
            if(request.getParameter("vchmarca") != null)
                iidmarca = (new Integer(request.getParameter("iidmarca"))).intValue();
            switch(accion)
            {
            case 3: // '\003'
            {
                Bean63 bean63 = (Bean63)ses_usr.getAttribute("bean63");
                for(int i = 0; i < bean63.getSize(); i++)
                {
                    parte.setLiidparte(bean63.getId(i));
                    parte.load();
                    parte.setVchnumparteequival(request.getParameter("P" + bean63.getId(i)) + "." + request.getParameter("N" + bean63.getId(i)) + "." + request.getParameter("S" + bean63.getId(i)) + "." + request.getParameter("M" + bean63.getId(i)));
                    parte.store();
                }

                // fall through
            }

            case 2: // '\002'
            {
                partes = new dbPARTEs();
                partes.setConnection(con);
                partes.findByAmbos(iidcategoria, iidgrupo, iidlinea, iidmarca, Help.getVchnumparteEmpty(request.getParameter("vchnumparte")), false);
                Bean63 bean63 = new Bean63();
                bean63.setVPartes(partes.getResult());
                ses_usr.setAttribute("bean63", bean63);
                strAddress = "/jsp/6_3a.jsp";
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

    private int idModulo;
}
