// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PartesPrecioB100.java

package org.nibble.service.administracion.partes;

import dbbeans.parte.dbPARTE;
import dbbeans.parte.dbPARTEs;
import general.Help;
import java.io.IOException;
import java.util.Enumeration;
import javax.servlet.*;
import javax.servlet.http.*;
import org.nibble.main.BeanError;
import org.nibble.main.BeanUtil;
import org.nibble.service.vo.partes.Bean64;
//import main.BeanError;
//import main.BeanUtil;
//import nc.beans6.Bean64;

public class PartesPrecioB100 extends HttpServlet
{

    public PartesPrecioB100()
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
            Integer usuario = (Integer)ses_usr.getAttribute("iidusuario");
            int iidusuario = usuario.intValue();
            dbPARTE parte = new dbPARTE();
            dbPARTEs partes = new dbPARTEs();
            Bean64 bean64 = new Bean64();
            parte.setConnection(con);
            partes.setConnection(con);
            if(request.getParameter("accion") != null)
                accion = (new Integer(request.getParameter("accion"))).intValue();
            long liidparte = 0L;
            if(request.getParameter("liidparte") != null && request.getParameter("liidparte").length() > 0)
                liidparte = (new Long(request.getParameter("liidparte"))).longValue();
            int iidcategoria = 0;
            if(request.getParameter("iidcategoria") != null && request.getParameter("iidcategoria").length() > 0)
                iidcategoria = (new Integer(request.getParameter("iidcategoria"))).intValue();
            int iidgrupo = 0;
            if(request.getParameter("iidgrupo") != null && request.getParameter("iidgrupo").length() > 0)
                iidgrupo = (new Integer(request.getParameter("iidgrupo"))).intValue();
            int iidlinea = 0;
            if(request.getParameter("iidlinea") != null && request.getParameter("iidlinea").length() > 0)
                iidlinea = (new Integer(request.getParameter("iidlinea"))).intValue();
            int iidmarca = 0;
            if(request.getParameter("iidmarca") != null && request.getParameter("iidmarca").length() > 0)
                iidmarca = (new Integer(request.getParameter("iidmarca"))).intValue();
            String vchnumparte = Help.getVchnumparte(request.getParameter("prefijo"), request.getParameter("numero"), request.getParameter("sufijo"), request.getParameter("medida"));
            switch(accion)
            {
            case 2: // '\002'
                bean64 = (Bean64)ses_usr.getAttribute("bean64");
                for(int i = 0; i < bean64.getPartesSize(); i++)
                    if(request.getParameter("T" + bean64.getLiidparte(i)) != null && request.getParameter("T" + bean64.getLiidparte(i)).length() > 0)
                    {
                        float precio = (new Float(request.getParameter("T" + bean64.getLiidparte(i)))).floatValue();
                        parte.setLiidparte(bean64.getLiidparte(i));
                        parte.load();
                        parte.setDecpreciob100(precio);
                        parte.store();
                    }

                // fall through

            case 1: // '\001'
                partes = new dbPARTEs();
                partes.setConnection(con);
                partes.findByPar(iidcategoria, iidgrupo, iidlinea, iidmarca, vchnumparte, false);
                bean64.setVPartes(partes.getResult());
                String atributo;
                for(Enumeration e = request.getAttributeNames(); e.hasMoreElements(); request.setAttribute(atributo, request.getParameter(atributo)))
                    atributo = e.nextElement().toString();

                ses_usr.setAttribute("bean64", bean64);
                strAddress = "/jsp/6_4a.jsp";
                break;
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
