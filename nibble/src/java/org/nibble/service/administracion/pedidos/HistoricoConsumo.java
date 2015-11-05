// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   HistoricoConsumo.java

package org.nibble.service.administracion.pedidos;

import dbbeans.hisconsumo.dbHISCONSUMO;
import dbbeans.proveedor.dbPROVEEDOR;
import dbbeans.proveedor.dbPROVEEDORs;
import java.io.IOException;
import java.util.Vector;
//import javax.servlet.*;
//import javax.servlet.http.*;
//import main.BeanError;
//import main.BeanUtil;
//import nc.beans7.Bean71his;
//import nc.beans7.Bean7Sugerir;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.nibble.main.BeanError;
import org.nibble.main.BeanUtil;
import org.nibble.service.vo.pedidos.Bean71his;
import org.nibble.service.vo.pedidos.Bean7Sugerir;

public class HistoricoConsumo extends HttpServlet
{

    public HistoricoConsumo()
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

    private void performTask(HttpServletRequest request, HttpServletResponse response)
    {
        Vector vBean71his = null;
        java.sql.Connection con = null;
        javax.servlet.http.HttpSession ses_usr = null;
        BeanUtil bu = null;
        RequestDispatcher dispatcher = null;
        String strAddress = null;
        dbPROVEEDORs dbProveedor = null;
        Vector vProveedores = null;
        Bean7Sugerir bSugerir = null;
        dbHISCONSUMO dbBConsumo = null;
        Bean71his bHisto = null;
        try
        {
            ses_usr = request.getSession(false);
            bu = new BeanUtil();
            con = bu.getConn();
            bu.setSession(ses_usr);
            dbProveedor = new dbPROVEEDORs();
            dbProveedor.setConnection(con);
            vBean71his = new Vector();
            dbBConsumo = new dbHISCONSUMO();
            dbBConsumo.setConnection(con);
            long liidparte = (new Long(request.getParameter("liidparte"))).longValue();
            int hisconsumo = (new Integer(request.getParameter("hisconsumo"))).intValue();
            int mm = (new Integer(request.getParameter("mm"))).intValue();
            int yy = (new Integer(request.getParameter("yy"))).intValue();
            logger.debug("liidparte" + liidparte);
            logger.debug("hisconsumo" + hisconsumo);
            vProveedores = dbProveedor.findByParte(liidparte);
            for(int i = 0; i < vProveedores.size(); i++)
            {
                bHisto = new Bean71his();
                dbBConsumo.setLiidparte(liidparte);
                dbPROVEEDOR PROVEEDOR = (dbPROVEEDOR)vProveedores.elementAt(i);
                dbBConsumo.setIidproveedor(PROVEEDOR.getIidproveedor());
                bSugerir = new Bean7Sugerir(dbBConsumo);
                bSugerir.setAnioInicial(yy);
                bSugerir.setMesInicial(mm);
                bHisto.setConsumo(bSugerir.caculaConsumo());
                bHisto.setIidProveedor(PROVEEDOR.getIidproveedor());
                bHisto.setLiidparte(liidparte);
                bHisto.setVchNombre(PROVEEDOR.getVchnombre());
                vBean71his.addElement(bHisto);
            }

            strAddress = "/jsp/7_1con.jsp";
            request.setAttribute("vBean71his", vBean71his);
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
    private static Log logger;

    static 
    {
        logger = LogFactory.getLog(HistoricoConsumo.class);
    }
}
