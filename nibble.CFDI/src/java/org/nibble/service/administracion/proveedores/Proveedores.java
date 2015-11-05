// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Proveedores.java

package org.nibble.service.administracion.proveedores;

import dbbeans.proveedor.dbPROVEEDOR;
import dbbeans.proveedor.dbPROVEEDORs;
import get.BeanMsg;
import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.*;
import org.nibble.main.BeanError;
import org.nibble.main.BeanUtil;
import org.nibble.service.vo.proveedores.Bean52;
//import main.BeanError;
//import main.BeanUtil;
//import nc.beans5.Bean5;
//import nc.beans5.Bean52;

// Referenced classes of package nc.servlets5:
//            BeanHelper52, BeanHelper

public class Proveedores extends HttpServlet
{

    public Proveedores()
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
        PAIS_DEFAULT = (new Short(getServletConfig().getInitParameter("PAIS_DEFAULT"))).shortValue();
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
            BeanMsg beanMsg = new BeanMsg();
            beanMsg.setMsg("");
            BeanHelper52 hlp52 = new BeanHelper52();
            dbPROVEEDOR proveedor = new dbPROVEEDOR();
            proveedor.setConnection(con);
            if(request.getParameter("accion") != null)
                accion = (new Integer(request.getParameter("accion"))).intValue();
            int iidproveedor = 0;
            if(request.getParameter("iidproveedor") != null && !request.getParameter("iidproveedor").equals(""))
                iidproveedor = (new Integer(request.getParameter("iidproveedor"))).intValue();
            String vchnombre = request.getParameter("vchnombre");
            String vchrazonsocial = request.getParameter("vchrazonsocial");
            String vchrfc = request.getParameter("vchrfc");
            String vchcurp = request.getParameter("vchcurp");
            boolean bcredito = false;
            if(request.getParameter("bcredito") != null)
                bcredito = true;
            String vchtel1 = request.getParameter("vchtel1");
            String vchtel2 = request.getParameter("vchtel2");
            String vchfax = request.getParameter("vchfax");
            boolean bitdadodebaja = false;
            if(request.getParameter("bitdadodebaja") != null)
                bitdadodebaja = true;
            String vchmail = request.getParameter("vchmail");
            String vchcolonia = request.getParameter("vchcolonia");
            String vchcalle = request.getParameter("vchcalle");
            String vchcp = request.getParameter("vchcp");
            boolean bitaceptabackorder = false;
            if(request.getParameter("back") != null && request.getParameter("back").equalsIgnoreCase("Si"))
                bitaceptabackorder = true;
            short siidpais = 0;
            if(request.getParameter("siidpais") != null && !request.getParameter("siidpais").equals(""))
                siidpais = (new Short(request.getParameter("siidpais"))).shortValue();
            int iidestado = 0;
            if(request.getParameter("iidestado") != null && !request.getParameter("iidestado").equals(""))
                iidestado = (new Integer(request.getParameter("iidestado"))).intValue();
            int iidmunicipio = 0;
            if(request.getParameter("iidmunicipio") != null && !request.getParameter("iidmunicipio").equals(""))
                iidmunicipio = (new Integer(request.getParameter("iidmunicipio"))).intValue();
            proveedor.setIidproveedor(iidproveedor);
            proveedor.setVchnombre(vchnombre);
            proveedor.setVchrazonsocial(vchrazonsocial);
            proveedor.setVchrfc(vchrfc);
            proveedor.setVchcurp(vchcurp);
            proveedor.setBcredito(bcredito);
            proveedor.setVchtel1(vchtel1);
            proveedor.setVchtel2(vchtel2);
            proveedor.setVchfax(vchfax);
            proveedor.setBitdadodebaja(bitdadodebaja);
            proveedor.setVchmail(vchmail);
            proveedor.setBitaceptabackorder(bitaceptabackorder);
            proveedor.setVchcolonia(vchcolonia);
            proveedor.setVchcalle(vchcalle);
            proveedor.setVchcp(vchcp);
            proveedor.setSiidpais(siidpais);
            proveedor.setIidestado(iidestado);
            proveedor.setIidmunicipio(iidmunicipio);
            switch(accion)
            {
            case 1: // '\001'
            {
                BeanHelper hlp = new BeanHelper();
                hlp.getData(con);
                hlp.getBean5().setPAIS_DEFAULT(PAIS_DEFAULT);
                ses_usr.setAttribute("bean5", hlp.getBean5());
                strAddress = "/jsp/5_1.jsp";
                break;
            }

            case 2: // '\002'
            {
                proveedor.create();
                beanMsg.setMsg("Se dio de alta al proveedor " + proveedor.getVchnombre());
                strAddress = "/jsp/5_1.jsp";
                break;
            }

            case 3: // '\003'
            {
                BeanHelper hlp = new BeanHelper();
                hlp.getData(con);
                ses_usr.setAttribute("bean5", hlp.getBean5());
                Bean52 bean52 = new Bean52();
                dbPROVEEDORs proveedores = new dbPROVEEDORs();
                proveedores.setConnection(con);
                proveedores.find("");
                bean52.setVProveedores(proveedores.getResult());
                ses_usr.setAttribute("bean52", bean52);
                strAddress = "/jsp/5_2.jsp";
                break;
            }

            case 4: // '\004'
            {
                proveedor.load();
                hlp52.getData(proveedor, con);
                ses_usr.setAttribute("beanresult52", hlp52.getBeanresult52());
                strAddress = "/jsp/5_2.jsp";
                break;
            }

            case 5: // '\005'
            {
                proveedor.store();
                beanMsg.setMsg("Se Modifico al proveedor " + proveedor.getVchnombre());
                hlp52.getData(proveedor, con);
                ses_usr.setAttribute("beanresult52", hlp52.getBeanresult52());
                strAddress = "/jsp/5_2.jsp";
                break;
            }

            case 6: // '\006'
            {
                proveedor.remove();
                ses_usr.setAttribute("proveedor", proveedor);
                strAddress = "/jsp/5_2.jsp";
                break;
            }
            }
            ses_usr.setAttribute("beanMsg", beanMsg);
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

    short PAIS_DEFAULT;
}
