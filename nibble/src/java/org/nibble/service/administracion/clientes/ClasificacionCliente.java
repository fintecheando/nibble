// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ClasificacionCliente.java

package org.nibble.service.administracion.clientes;

import dbbeans.categoriacliente.dbCATEGORIACLIENTE;
import dbbeans.categoriacliente.dbCATEGORIACLIENTEs;
import dbbeans.niveldeprecio.dbNIVELDEPRECIOs;
import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.*;
import org.nibble.main.BeanError;
import org.nibble.main.BeanUtil;
import org.nibble.service.vo.clientes.Bean4;
//import main.BeanError;
//import main.BeanUtil;
//import nc.beans4.Bean4;

public class ClasificacionCliente extends HttpServlet
{

    public ClasificacionCliente()
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
            Bean4 bean4 = new Bean4();
            if(request.getParameter("accion") != null)
                accion = (new Integer(request.getParameter("accion"))).intValue();
            int iidcatcliente = 0;
            if(request.getParameter("iidcatcliente") != null && !request.getParameter("iidcatcliente").equals(""))
                iidcatcliente = (new Integer(request.getParameter("iidcatcliente"))).intValue();
            String vchdescripcion = request.getParameter("vchdescripcion");
            
            dbCATEGORIACLIENTE catcliente = new dbCATEGORIACLIENTE();
            catcliente.setConnection(con);
            catcliente.setIidcatcliente(iidcatcliente);
            catcliente.setVchdescripcion(vchdescripcion);
            switch(accion)
            {
            default:
                break;

            case 2: // '\002'
                catcliente.create();
                break;

            case 3: // '\003'
                catcliente.load();
                catcliente.setVchdescripcion(vchdescripcion);
                catcliente.store();
                break;

            case 4: // '\004'
                dbNIVELDEPRECIOs niveldeprecios = new dbNIVELDEPRECIOs();
                niveldeprecios.setConnection(con);
                niveldeprecios.findByIidcatcliente(iidcatcliente);
                
                if(niveldeprecios.getResult().size() > 0)
                    throw new Exception("No se puede eliminar por la integridad referencial");
                catcliente.remove();
                break;
            }
            dbCATEGORIACLIENTEs catclientes = new dbCATEGORIACLIENTEs();
            catclientes.setConnection(con);
            catclientes.find("");
            bean4.setVCatclientes(catclientes.getResult());
            ses_usr.setAttribute("bean4", bean4);
            strAddress = "/jsp/clasificacionClientes.jsp";
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
