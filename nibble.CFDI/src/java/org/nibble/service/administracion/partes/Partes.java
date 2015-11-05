// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Partes.java

package org.nibble.service.administracion.partes;

import dbbeans.clasificproveedor.dbCLASIFICPROVEEDOR;
import dbbeans.clasificproveedor.dbCLASIFICPROVEEDORs;
import dbbeans.inventario.dbINVENTARIO;
import dbbeans.parte.dbPARTE;
import dbbeans.parte.dbPARTEs;
import dbbeans.unidad.dbUNIDADs;
import general.Help;
import get.BeanMsg;
import java.io.IOException;
import java.sql.Connection;
import java.util.Vector;
import javax.servlet.*;
import javax.servlet.http.*;
//import main.BeanError;
//import main.BeanUtil;
//import nc.beans6.Bean62;
//import nc.beans6.Bean62c;
//import nc.servlets5.BeanHelper53;
import org.nibble.main.BeanError;
import org.nibble.main.BeanUtil;
import org.nibble.service.administracion.proveedores.BeanHelper53;
import org.nibble.service.vo.partes.Bean62;
import org.nibble.service.vo.partes.Bean62c;
//import org.nibble.service.vo.partes.BeanHelper53;
import org.nibble.service.vo.proveedores.Bean53;

public class Partes extends HttpServlet
{
    private int idModulo;
    
    public Partes()
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
        idModulo = getServletConfig().getInitParameter("idModulo") == null ? -1 : (new Integer(getServletConfig().getInitParameter("idModulo"))).intValue();
    }

    public void performTask(HttpServletRequest request, HttpServletResponse response)
    {
        Connection con = null;
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
            BeanHelper53 hlp = new BeanHelper53();
            BeanMsg beanMsg = new BeanMsg();
            dbPARTE parte = new dbPARTE();
            dbUNIDADs unidades = new dbUNIDADs();
            dbCLASIFICPROVEEDORs dbCPs = new dbCLASIFICPROVEEDORs();
            dbINVENTARIO inventario = new dbINVENTARIO();
            dbCLASIFICPROVEEDOR dbCP = new dbCLASIFICPROVEEDOR();

            parte.setConnection(con);
            unidades.setConnection(con);
            dbCPs.setConnection(con);
            dbCP.setConnection(con);
            inventario.setConnection(con);
            beanMsg.setMsg("");

            if(request.getParameter("accion") != null)
                accion = (new Integer(request.getParameter("accion"))).intValue();
            long liidparte = 0L;

            if(request.getParameter("liidparte") != null && request.getParameter("liidparte").length() > 0)
                liidparte = (new Long(request.getParameter("liidparte"))).longValue();

            if(accion == 6)
            {
                parte.setLiidparte(liidparte);
                parte.load();
            }
            if(accion == 5)
            {
                parte.setLiidparte(liidparte);
                parte.load();
            }
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
            String vchnumparte = Help.getVchnumparteEmpty(request.getParameter("vchnumparte"));

            
            if(accion == 2 || accion == 6)
            {
                float fllargo = 0.0F;
                if(request.getParameter("fllargo") != null)
                    fllargo = (new Float(request.getParameter("fllargo"))).floatValue();
                float flalto = 0.0F;
                if(request.getParameter("flalto") != null)
                    flalto = (new Float(request.getParameter("flalto"))).floatValue();
                float flancho = 0.0F;
                if(request.getParameter("flancho") != null)
                    flancho = (new Float(request.getParameter("flancho"))).floatValue();
                float flpeso = 0.0F;
                if(request.getParameter("flpeso") != null)
                    flpeso = (new Float(request.getParameter("flpeso"))).floatValue();
                int iidunidad = 0;
                if(request.getParameter("iidunidad") != null)
                    iidunidad = (new Integer(request.getParameter("iidunidad"))).intValue();
                float decpreciob100 = 0.0F;
                if(request.getParameter("decpreciob100") != null)
                    decpreciob100 = (new Float(request.getParameter("decpreciob100"))).floatValue();
                int imultiploempaque = 0;
                if(request.getParameter("imultiploempaque") != null)
                    imultiploempaque = (new Integer(request.getParameter("imultiploempaque"))).intValue();
                int iminembarque = 0;
                if(request.getParameter("iminembarque") != null)
                    iminembarque = (new Integer(request.getParameter("iminembarque"))).intValue();
                boolean bitventaunitaria = false;
                if(request.getParameter("bitventaunitaria") != null && request.getParameter("bitventaunitaria").equalsIgnoreCase("Si"))
                    bitventaunitaria = true;
                parte.setIidunidad(iidunidad);
                parte.setIidimagen(1);
                parte.setVchnumparte(vchnumparte);
                parte.setVchnumparteequival(vchnumparte);
                parte.setFllargo(fllargo);
                parte.setFlalto(flalto);
                parte.setFlancho(flancho);
                parte.setFlpeso(flpeso);
                parte.setIidunidad(iidunidad);
                parte.setDecpreciob100(decpreciob100);
                parte.setDecprecio(decpreciob100);
                parte.setBitventaunitaria(bitventaunitaria);
                parte.setBitdadodebaja(false);
                parte.setImultiploempaque(imultiploempaque);
                parte.setIminembarque(iminembarque);
                parte.setVchdescripciones(request.getParameter("vchdescripciones"));
                parte.setVchobservaciones(request.getParameter("vchobservaciones"));
                parte.setFichatecnica(request.getParameter("fichatecnica"));
                parte.setIidcategoria(new Integer(request.getParameter("iidcategoria")).intValue());
                parte.setIidgrupo(new Integer(request.getParameter("iidgrupo")).intValue());
                parte.setIidlinea(new Integer(request.getParameter("iidlinea")).intValue());
                parte.setIidmarca(new Integer(request.getParameter("iidmarca")).intValue());
                
            }
            if(accion == 2)
            {
                parte.setIidcategoria(iidcategoria);
                parte.setIidgrupo(iidgrupo);
                parte.setIidlinea(iidlinea);
                parte.setIidmarca(iidmarca);
            }
            switch(accion)
            {
            default:
                break;

            case 1: // '\001'
                
                hlp.getData(con);
                Bean53 bean53 = hlp.getBean53();
                ses_usr.setAttribute("bean53", hlp.getBean53());
                strAddress = "/jsp/6_1.jsp";
                break;

            case 2: // '\002'
                
                parte.create();
                
                    inventario.setLiidparte(parte.getLiidparte());
                    inventario.setIidproveedor(32);
                    inventario.setBitsalidareciente(true);
                    inventario.setIexistencia(1000);
                    inventario.setIindiceresurtido(0);
                    inventario.setIcantconsumo(0);
                    inventario.setVchubicacion("VIÑA");
                    inventario.create();
                    
                    dbCP.setBitprovefavorito(true);
                    dbCP.setIidproveedor(32);
                    dbCP.setIidcategoria(parte.getIidcategoria());
                    dbCP.setIidgrupo(parte.getIidgrupo());
                    dbCP.setIidlinea(parte.getIidlinea());
                    dbCP.setIidmarca(parte.getIidmarca());
                    dbCP.setDecfactorcostovent(1);
                    dbCP.setDecfactorvaluainve(1);
                    dbCP.create();
                /*dbCPs.find(iidcategoria, iidgrupo, iidlinea, iidmarca);
                Vector v = dbCPs.getResult();
                for(int i = 0; i < v.size(); i++)
                {
                    dbCLASIFICPROVEEDOR dbCP = (dbCLASIFICPROVEEDOR)v.elementAt(i);
                    inventario.setLiidparte(parte.getLiidparte());
                    inventario.setIidproveedor(dbCP.getIidproveedor());
                    inventario.setBitsalidareciente(true);
                    inventario.setIexistencia(1000);
                    inventario.setIindiceresurtido(0);
                    inventario.setIcantconsumo(0);
                    inventario.setVchubicacion("VIÑA DEL MAR");
                    inventario.create();
                }*/

                beanMsg.setMsg("Se cre\363 la parte " + parte.getVchnumparte());
                strAddress = "/jsp/6_1.jsp";
                break;

            case 3: // '\003'
                
                hlp.getData(con);
                ses_usr.setAttribute("bean53", hlp.getBean53());
                strAddress = "/jsp/6_2a.jsp";
                break;

            case 4: // '\004'
                
                dbPARTEs partes = new dbPARTEs();
                partes.setConnection(con);
                if(request.getParameter("bitbusqueda") != null && request.getParameter("bitbusqueda").equalsIgnoreCase("SKU"))
                {
                    partes.findBySKU(Help.getVchnumparteEmpty(request.getParameter("vchnumparte")), false);
                }
                else if(request.getParameter("bitbusqueda") != null && request.getParameter("bitbusqueda").equalsIgnoreCase("Clasificacion"))
                {
                    partes.findByClasificacion(iidcategoria, iidgrupo, iidlinea, iidmarca, false);
                }
                else if(request.getParameter("bitbusqueda") != null && request.getParameter("bitbusqueda").equalsIgnoreCase("Ambos"))
                {
                    partes.findByAmbos(iidcategoria, iidgrupo, iidlinea, iidmarca, Help.getVchnumparteEmpty(request.getParameter("vchnumparte")), false);
                }
                Bean62 bean62 = new Bean62();
                bean62.setVPartes(partes.getResult());
                ses_usr.setAttribute("bean62", bean62);
                strAddress = "/jsp/6_2b.jsp";
                break;

            case 6: // '\006'
                

                parte.store();
                beanMsg.setMsg("Se modific\363 la parte " + parte.getVchnumparte());
                // fall through

            case 5: // '\005'
                hlp.getData(con);
                bean53 = hlp.getBean53();
                ses_usr.setAttribute("bean53", hlp.getBean53());
                
                parte.setLiidparte(liidparte);
                parte.load();
                unidades.find("");
                Bean62c bean62c = new Bean62c();
                bean62c.setParte(parte);
                bean62c.setVUnidades(unidades.getResult());
                ses_usr.setAttribute("bean62c", bean62c);
                strAddress = "/jsp/6_2c.jsp";
                break;
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

    
}
