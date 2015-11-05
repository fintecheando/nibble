// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Configuracion.java

package org.nibble.service.administracion.sistema;

import dbbeans.configsistema.dbCONFIGSISTEMA;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.nibble.main.BeanError;
import org.nibble.main.BeanUtil;
import org.nibble.service.vo.administracion.sistema.Bean31;
//import javax.servlet.*;
//import javax.servlet.http.*;
//import main.BeanError;
//import main.BeanUtil;
//import nc.beans3.Bean31;

public class ConfiguracionSistema extends HttpServlet
{

    public ConfiguracionSistema()
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
        idconfsistema = (new Integer(getServletConfig().getInitParameter("confid"))).intValue();
        idModulo = getServletConfig().getInitParameter("idModulo") == null ? -1 : (new Integer(getServletConfig().getInitParameter("idModulo"))).intValue();
    }

    public void performTask(HttpServletRequest request, HttpServletResponse response)
    {
        java.sql.Connection con = null;
        javax.servlet.http.HttpSession ses_usr = null;
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

            int accion = (new Integer(request.getParameter("accion"))).intValue();
            Bean31 bean = new Bean31();
            dbCONFIGSISTEMA dbcs = new dbCONFIGSISTEMA();
            dbcs.setConnection(con);
            dbcs.setIidconfsistema(idconfsistema);
            dbcs.load();
            switch(accion)
            {
            case 2: // '\002'
                int calc = (new Integer(request.getParameter("calculo"))).intValue();
                if(calc == 0)
                    dbcs.setBitutilsobreventa(false);
                else
                    dbcs.setBitutilsobreventa(true);
                dbcs.setDecivaaplicable((new Float(request.getParameter("iva"))).floatValue());
                dbcs.store();
                // fall through

            default:
                bean.setBitutilsobreventa(dbcs.getBitutilsobreventa());
                bean.setDecivaaplicable(dbcs.getDecivaaplicable());
                request.setAttribute("bean", bean);
                strAddress = "/jsp/configuracionSistema.jsp";
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

    private int idconfsistema;
    private int idModulo;
}
