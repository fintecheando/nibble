// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   NivelPrecio.java

package org.nibble.service.administracion.clientes;

import dbbeans.categoriacliente.dbCATEGORIACLIENTEs;
import dbbeans.niveldeprecio.dbNIVELDEPRECIO;
import dbbeans.niveldeprecio.dbNIVELDEPRECIOs;
import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.*;
import org.nibble.main.BeanError;
import org.nibble.main.BeanUtil;
import org.nibble.service.vo.clientes.Bean4;
import org.nibble.service.vo.clientes.Bean42;
//import main.BeanError;
//import main.BeanUtil;
//import nc.beans4.Bean4;
//import nc.beans4.Bean42;

public class NivelPrecio extends HttpServlet
{

    public NivelPrecio()
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
            Bean42 bean42 = new Bean42();
            bean42.setMsg("");
            dbCATEGORIACLIENTEs catclientes = new dbCATEGORIACLIENTEs();
            catclientes.setConnection(con);
            catclientes.find("");
            bean4.setVCatclientes(catclientes.getResult());
            dbNIVELDEPRECIOs niveldeprecios = new dbNIVELDEPRECIOs();
            niveldeprecios.setConnection(con);
            dbNIVELDEPRECIO niveldeprecio = new dbNIVELDEPRECIO();
            niveldeprecio.setConnection(con);
            if(request.getParameter("accion") != null && !request.getParameter("accion").equals(""))
                accion = (new Integer(request.getParameter("accion"))).intValue();
            int iidnivel = 0;
            if(request.getParameter("iidnivel") != null && !request.getParameter("iidnivel").equals(""))
                iidnivel = (new Integer(request.getParameter("iidnivel"))).intValue();
            String vchnivel = request.getParameter("vchnivel");
            int iidcatcliente = 0;
            if(request.getParameter("iidcatcliente") != null && !request.getParameter("iidcatcliente").equals(""))
                iidcatcliente = (new Integer(request.getParameter("iidcatcliente"))).intValue();
            float decutilidad = 0.0F;
            if(request.getParameter("decutilidad") != null && !request.getParameter("decutilidad").equals(""))
                decutilidad = (new Float(request.getParameter("decutilidad"))).floatValue();
            float decporccomision = 0.0F;
            if(request.getParameter("decporccomision") != null && !request.getParameter("decporccomision").equals(""))
                decporccomision = (new Float(request.getParameter("decporccomision"))).floatValue();
            boolean bitdadodebaja = false;
            if(request.getParameter("bitdadodebaja") != null)
                bitdadodebaja = true;
            niveldeprecio.setIidnivel(iidnivel);
            niveldeprecio.setVchnivel(vchnivel);
            niveldeprecio.setIidcatcliente(iidcatcliente);
            niveldeprecio.setDecutilidad(decutilidad);
            niveldeprecio.setDecporccomision(decporccomision);
            niveldeprecio.setBitdadodebaja(bitdadodebaja);
            switch(accion)
            {
            case 2: // '\002'
                if(niveldeprecio.isVchnivel(vchnivel))
                {
                    bean42.setMsg("Ya existe un nivel con ese nombre, intente con otro");
                } else
                {
                    niveldeprecio.create();
                    bean42.setMsg("Se dio de alta el nivel");
                }
                break;

            case 3: // '\003'
                niveldeprecio.load();
                niveldeprecio.setVchnivel(vchnivel);
                niveldeprecio.setDecutilidad(decutilidad);
                niveldeprecio.setDecporccomision(decporccomision);
                niveldeprecio.store();
                break;

            case 4: // '\004'
                niveldeprecio.remove();
                break;
            }
            if(iidcatcliente == 0 && bean4.getSize() > 0)
                iidcatcliente = bean4.getIidcatcliente(0);
            niveldeprecios.findByIidcatcliente(iidcatcliente);
            bean42.setVNivelesPrecio(niveldeprecios.getResult());
            bean42.setIidcatcliente(iidcatcliente);
            ses_usr.setAttribute("bean4", bean4);
            ses_usr.setAttribute("bean42", bean42);
            strAddress = "/jsp/nivelPrecio.jsp";
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
