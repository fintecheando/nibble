// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   EstructurasCompra.java

package org.nibble.service.administracion.proveedores;

import dbbeans.clasificproveedor.dbCLASIFICPROVEEDOR;
import dbbeans.desccargoscompra.dbDESCCARGOSCOMPRA;
import dbbeans.desccargoscompra.dbDESCCARGOSCOMPRAs;
import dbbeans.proveedor.dbPROVEEDORs;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.*;
import javax.servlet.http.*;
import org.nibble.main.BeanError;
import org.nibble.main.BeanUtil;
import org.nibble.service.vo.proveedores.Bean52;
import org.nibble.service.vo.proveedores.Bean54;
//import main.BeanError;
//import main.BeanUtil;
//import nc.beans5.Bean52;
//import nc.beans5.Bean54;

public class EstructurasCompra extends HttpServlet
{

    public EstructurasCompra()
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
            dbDESCCARGOSCOMPRA dbDCC = new dbDESCCARGOSCOMPRA();
            dbDCC.setConnection(con);
            dbDESCCARGOSCOMPRAs dbDCCs = new dbDESCCARGOSCOMPRAs();
            dbDCCs.setConnection(con);
            dbPROVEEDORs proveedores = new dbPROVEEDORs();
            proveedores.setConnection(con);
            if(request.getParameter("accion") != null)
                accion = (new Integer(request.getParameter("accion"))).intValue();
            String vchdescripcion = request.getParameter("vchdescripcion");
            String vchleyenda = request.getParameter("vchleyenda");
            float decfactor = 1.0F;
            float porcentaje = 0.0F;
            if(request.getParameter("porcentaje") != null && !request.getParameter("porcentaje").equals(""))
            {
                porcentaje = (new Float(request.getParameter("porcentaje"))).floatValue();
                decfactor = 1.0F - porcentaje / 100F;
                
            }
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
            float costo = 0.0F;
            if(request.getParameter("costo") != null && !request.getParameter("costo").equals(""))
                costo = (new Float(request.getParameter("costo"))).floatValue();
            float costoventa = 0.0F;
            if(request.getParameter("costoventa") != null && !request.getParameter("costoventa").equals(""))
                costoventa = (new Float(request.getParameter("costoventa"))).floatValue();
            dbDCC.setIidproveedor(iidproveedor);
            dbDCC.setIidcategoria(iidcategoria);
            dbDCC.setIidgrupo(iidgrupo);
            dbDCC.setIidlinea(iidlinea);
            dbDCC.setIidmarca(iidmarca);
            dbDCC.setVchleyenda(vchdescripcion);
            dbDCC.setDecfactor(decfactor);
            dbCLASIFICPROVEEDOR dcCP = new dbCLASIFICPROVEEDOR();
            dcCP.setConnection(con);
            dcCP.setIidproveedor(iidproveedor);
            dcCP.setIidcategoria(iidcategoria);
            dcCP.setIidgrupo(iidgrupo);
            dcCP.setIidlinea(iidlinea);
            dcCP.setIidmarca(iidmarca);
            switch(accion)
            {
            case 2: // '\002'
                Bean52 bean52 = new Bean52();
                bean52.setVProveedores(proveedores.findByClasificacion(iidcategoria, iidgrupo, iidlinea, iidmarca));
                ses_usr.setAttribute("bean52", bean52);
                strAddress = "/jsp/5_4b.jsp";
                break;

            case 4: // '\004'
                try
                {
                    dbDCC.create();
                }
                catch(Exception e)
                {
                    throw new Exception("No se pudo crear el registro");
                }
                break;

            case 5: // '\005'
                dbDCC.setVchleyenda(vchleyenda);
                dbDCC.load();
                dbDCC.setDecfactor(decfactor);
                dbDCC.store();
                break;

            case 6: // '\006'
                dbDCC.setVchleyenda(vchleyenda);
                dbDCC.remove();
                break;

            case 7: // '\007'
                dbDCC.setVchleyenda("PRONTO PAGO");
                try
                {
                    dbDCC.load();
                    dbDCC.setDecfactor(decfactor);
                    dbDCC.store();
                }
                catch(SQLException e)
                {
                    e.printStackTrace();
                    dbDCC.setDecfactor(decfactor);
                    dbDCC.create();
                }
                break;

            case 8: // '\b'
                dcCP.load();
                dcCP.setDecfactorvaluainve(costoventa);
                dcCP.setDecfactorcostovent(1.0F - costo / 100F);
                dcCP.store();
                break;
            }
            if(accion == 3 || accion == 4 || accion == 5 || accion == 6 || accion == 7 || accion == 8)
            {
                Bean54 bean54 = new Bean54();
                dbDCCs.findByPar(iidproveedor, iidcategoria, iidgrupo, iidlinea, iidmarca);
                dcCP.load();
                bean54.setVDesccompra(dbDCCs.getResult());
                bean54.setFactorActual(dcCP.getDecfactorcostovent());
                bean54.setPorcentajeActual((1.0F - dcCP.getDecfactorcostovent()) * 100F);
                ses_usr.setAttribute("bean54", bean54);
                strAddress = "/jsp/5_4c.jsp";
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
