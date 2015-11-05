// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Pedidos.java

package org.nibble.service.administracion.pedidos;

import dbbeans.estatuspedidos.dbESTATUSPEDIDOSs;
import dbbeans.inventario.dbINVENTARIO;
import dbbeans.pedido.dbPEDIDO;
import dbbeans.pedidopartidas.dbPEDIDOPARTIDAS;
import dbbeans.proveedor.dbPROVEEDORs;
import dbrbeans.pedidopartidasparteestatus.dbrPEDIDOPARTIDASPARTEESTATUSs;
import dbrbeans.pedidoproveedorestatus.dbrPEDIDOPROVEEDORESTATUSs;
import java.io.IOException;
import java.sql.Connection;
//import javax.servlet.*;
//import javax.servlet.http.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.nibble.main.BeanError;
import org.nibble.main.BeanUtil;
import org.nibble.service.vo.pedidos.Bean74;
import org.nibble.service.vo.pedidos.Bean74b;
import org.nibble.service.vo.pedidos.Bean74c;
import org.nibble.util.Fecha;
//import main.BeanError;
//import main.BeanUtil;
//import nc.beans7.*;
//import util.Fecha;

public class Pedidos extends HttpServlet
{

    public Pedidos()
    {
    }

    private String criterios(Connection con, HttpSession ses_usr)
        throws Exception
    {
        dbESTATUSPEDIDOSs estatuspedidoss = new dbESTATUSPEDIDOSs();
        estatuspedidoss.setConnection(con);
        estatuspedidoss.find("");
        dbPROVEEDORs proveedores = new dbPROVEEDORs();
        proveedores.setConnection(con);
        proveedores.find("");
        Bean74 bean74 = new Bean74();
        bean74.setVProveedores(proveedores.getResult());
        bean74.setVEstatusPedidos(estatuspedidoss.getResult());
        ses_usr.setAttribute("bean74", bean74);
        return "/jsp/7_4.jsp";
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
        IDSINSURTIR = (new Integer(getServletConfig().getInitParameter("IDSINSURTIR"))).intValue();
        IDPARCIALMENTE = (new Integer(getServletConfig().getInitParameter("IDPARCIALMENTE"))).intValue();
        IDRECIBIDO = (new Integer(getServletConfig().getInitParameter("IDRECIBIDO"))).intValue();
        IDCANCELADO = (new Integer(getServletConfig().getInitParameter("IDCANCELADO"))).intValue();
    }

    private void modificaPartida(HttpServletRequest request, Connection con, HttpSession ses_usr)
        throws Exception
    {
        short sinsurtir = 0;
        short parciales = 0;
        short recibidas = 0;
        short canceladas = 0;
        dbPEDIDOPARTIDAS pp = new dbPEDIDOPARTIDAS();
        dbINVENTARIO inventario = new dbINVENTARIO();
        dbPEDIDO pedido = new dbPEDIDO();
        pp.setConnection(con);
        pedido.setConnection(con);
        inventario.setConnection(con);
        Bean74c bean74c = (Bean74c)ses_usr.getAttribute("bean74c");
        int iidpedido = 0;
        if(request.getParameter("iidpedido") != null && !request.getParameter("iidpedido").equals(""))
            iidpedido = (new Integer(request.getParameter("iidpedido"))).intValue();
        pedido.setIidpedido(iidpedido);
        pedido.load();
        for(int i = 0; i < bean74c.getSize(); i++)
            if(bean74c.getTiestatus(i) == IDCANCELADO)
            {
                canceladas++;
            } else
            {
                int tiestatus_i = 0;
                if(request.getParameter("tiestatus" + i) != null && !request.getParameter("tiestatus" + i).equals(""))
                    tiestatus_i = (new Integer(request.getParameter("tiestatus" + i))).intValue();
                
                int cantidad_i = 0;
                if(request.getParameter("cantidad" + i) != null && !request.getParameter("cantidad" + i).equals(""))
                    cantidad_i = (new Integer(request.getParameter("cantidad" + i))).intValue();
                
                String factura_i = request.getParameter("factura" + i);
                String remision_i = request.getParameter("remision" + i);
                String comentario_i = request.getParameter("comentario" + i);
                if(tiestatus_i == IDCANCELADO)
                    cantidad_i = 0;
                int diff = cantidad_i - bean74c.getIcantrecibida(i);
                inventario.setLiidparte(bean74c.getLiidparte(i));
                inventario.setIidproveedor(pedido.getIidproveedor());
                inventario.load();
                if(diff < 0 && inventario.getIexistencia() < Math.abs(diff))
                    throw new Exception("Imposible hacer modificaci\363n:  La existencia de la parte " + bean74c.getVchnumparte(i) + " es " + inventario.getIexistencia());
                inventario.setIexistencia(inventario.getIexistencia() + diff);
                inventario.store();
                pp.setIidpedido(bean74c.getIidpedido(i));
                pp.setLiidparte(bean74c.getLiidparte(i));
                pp.setTiestatus(tiestatus_i);
                pp.setDtfechultrecepcion(Fecha.getCurrentDate("yyyyMMdd"));
                pp.setIcantpedida(bean74c.getIcantpedida(i));
                pp.setIcantrecibida(cantidad_i);
                pp.setVchfactura(factura_i);
                pp.setVchremisionproveed(remision_i);
                pp.setVchcomentario(comentario_i);
                pp.store();
                if(IDSINSURTIR == tiestatus_i)
                    sinsurtir++;
                else
                if(IDPARCIALMENTE == tiestatus_i)
                    parciales++;
                else
                if(IDRECIBIDO == tiestatus_i)
                    recibidas++;
                else
                if(IDCANCELADO == tiestatus_i)
                    canceladas++;
            }

        pedido.setIidpedido(iidpedido);
        pedido.load();
        pedido.setSinototpartpedpend(sinsurtir);
        pedido.setSinopartsurtpar(parciales);
        pedido.setSinopartrecibidas(recibidas);
        pedido.setSinopartcanceladas(canceladas);
        if(pedido.getSinototalpartidas() == sinsurtir)
            pedido.setTiestatus(IDSINSURTIR);
        if(pedido.getSinototalpartidas() == parciales)
            pedido.setTiestatus(IDPARCIALMENTE);
        if(pedido.getSinototalpartidas() == recibidas)
            pedido.setTiestatus(IDRECIBIDO);
        if(pedido.getSinototalpartidas() == canceladas)
            pedido.setTiestatus(IDCANCELADO);
        pedido.store();
    }

    private String partidas(int iidpedido, int iidproveedor, Connection con, HttpSession ses_usr)
        throws Exception
    {
        dbrPEDIDOPARTIDASPARTEESTATUSs pppe = new dbrPEDIDOPARTIDASPARTEESTATUSs();
        pppe.setConnection(con);
        pppe.findByPedido(iidpedido, iidproveedor);
        Bean74c bean74c = new Bean74c();
        bean74c.setIDSINSURTIR(IDSINSURTIR);
        bean74c.setIDPARCIALMENTE(IDPARCIALMENTE);
        bean74c.setIDRECIBIDO(IDRECIBIDO);
        bean74c.setIDCANCELADO(IDCANCELADO);
        bean74c.setVResult(pppe.getResult());
        ses_usr.setAttribute("bean74c", bean74c);
        return "/jsp/7_4c.jsp";
    }

    private String pedidos(int iidpedido, int tiestatus, int iidproveedor, String fechaini, String fechafin, Connection con, HttpSession ses_usr)
        throws Exception
    {
        dbrPEDIDOPROVEEDORESTATUSs ppe = new dbrPEDIDOPROVEEDORESTATUSs();
        ppe.setConnection(con);
        if(fechaini == null)
            fechaini = new String();
        if(fechafin == null)
            fechafin = new String();
        if(fechaini.length() > 0)
            fechaini = Fecha.formatToMySQL(fechaini) + "000000";
        if(fechafin.length() > 0)
            fechafin = Fecha.formatToMySQL(fechafin) + "235959";
        Bean74b bean74b = new Bean74b();
        bean74b.setVPedidos(ppe.findByPar(iidpedido, tiestatus, iidproveedor, fechaini, fechafin));
        ses_usr.setAttribute("bean74b", bean74b);
        return "/jsp/7_4b.jsp";
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
            if(request.getParameter("accion") != null)
                accion = (new Integer(request.getParameter("accion"))).intValue();
            int i = 0;
            if(request.getParameter("i") != null && request.getParameter("i").length() > 0)
                i = (new Integer(request.getParameter("i"))).intValue();
            int iidpedido = 0;
            if(request.getParameter("iidpedido") != null && request.getParameter("iidpedido").length() > 0)
                iidpedido = (new Integer(request.getParameter("iidpedido"))).intValue();
            int iidproveedor = 0;
            if(request.getParameter("iidproveedor") != null && request.getParameter("iidproveedor").length() > 0)
                iidproveedor = (new Integer(request.getParameter("iidproveedor"))).intValue();
            int tiestatus = 0;
            if(request.getParameter("tiestatus") != null && request.getParameter("tiestatus").length() > 0)
                tiestatus = (new Integer(request.getParameter("tiestatus"))).intValue();
            long liidparte = 0L;
            if(request.getParameter("liidparte") != null && request.getParameter("liidparte").length() > 0)
                liidparte = (new Long(request.getParameter("liidparte"))).longValue();
            String fechaini = request.getParameter("fechaini");
            String fechafin = request.getParameter("fechafin");
            int icantpedida = 0;
            if(request.getParameter("icantpedida") != null && request.getParameter("icantpedida").length() > 0)
                icantpedida = (new Integer(request.getParameter("icantpedida"))).intValue();
            int icantrecibida = 0;
            if(request.getParameter("icantrecibida") != null && request.getParameter("icantrecibida").length() > 0)
                icantrecibida = (new Integer(request.getParameter("icantrecibida"))).intValue();
            String vchfactura = request.getParameter("vchfactura");
            String vchremisionproveed = request.getParameter("vchremisionproveed");
            String vchcomentario = request.getParameter("vchcomentario");
            switch(accion)
            {
            case 1: // '\001'
                strAddress = criterios(con, ses_usr);
                break;

            case 2: // '\002'
                strAddress = pedidos(iidpedido, tiestatus, iidproveedor, fechaini, fechafin, con, ses_usr);
                break;

            case 4: // '\004'
                modificaPartida(request, con, ses_usr);
                // fall through

            case 3: // '\003'
                strAddress = partidas(iidpedido, iidproveedor, con, ses_usr);
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
    private int IDSINSURTIR;
    private int IDPARCIALMENTE;
    private int IDRECIBIDO;
    private int IDCANCELADO;
}
