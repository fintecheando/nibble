// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PedidosEnvio.java

package org.nibble.service.administracion.pedidos;

import dbbeans.categoria.dbCATEGORIA;
import dbbeans.clasificproveedor.dbCLASIFICPROVEEDOR;
import dbbeans.grupo.dbGRUPO;
import dbbeans.linea.dbLINEA;
import dbbeans.marca.dbMARCA;
import dbbeans.pedido.dbPEDIDO;
import dbbeans.pedidopartidas.dbPEDIDOPARTIDAS;
import dbbeans.proveedor.dbPROVEEDORs;
import dbrbeans.partidasolicitada.dbrPARTIDASOLICITADA;
import dbrbeans.partidasolicitada.dbrPARTIDASOLICITADAs;
import java.io.IOException;
import java.sql.Connection;
import java.util.Vector;
import javax.servlet.*;
import javax.servlet.http.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.nibble.main.BeanError;
import org.nibble.main.BeanUtil;
import org.nibble.service.vo.pedidos.Bean73a;
import org.nibble.service.vo.pedidos.Bean73b;
import org.nibble.service.vo.pedidos.Bean73c;
import org.nibble.util.Fecha;
//import main.BeanError;
//import main.BeanUtil;
//import nc.beans7.*;
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
//import util.Fecha;

public class PedidosEnvio extends HttpServlet
{

    public PedidosEnvio()
    {
    }

    private String BuscarPartidas(HttpServletRequest request, Connection con, HttpSession ses_usr)
        throws Exception
    {
        dbrPARTIDASOLICITADAs pss = new dbrPARTIDASOLICITADAs();
        pss.setConnection(con);
        Bean73b bean73b = new Bean73b();
        int iidproveedor = 0;
        if(request.getParameter("iidproveedor") != null && !request.getParameter("iidproveedor").equals(""))
            iidproveedor = (new Integer(request.getParameter("iidproveedor"))).intValue();
        pss.findByIidproveedor(iidproveedor);
        bean73b.setVResult(pss.getResult());
        bean73b.iidproveedor = iidproveedor;
        ses_usr.setAttribute("bean73b", bean73b);
        return "/jsp/7_3b.jsp";
    }

    private String BuscarProveedores(HttpServletRequest request, Connection con, HttpSession ses_usr)
        throws Exception
    {
        dbPROVEEDORs proveedores = new dbPROVEEDORs();
        proveedores.setConnection(con);
        Bean73a bean73a = new Bean73a();
        bean73a.setVProveedores(proveedores.findIsPartidaSolicitada());
        ses_usr.setAttribute("bean73a", bean73a);
        return "/jsp/7_3.jsp";
    }

    private String crearPedido(HttpServletRequest request, Connection con, HttpSession ses_usr)
        throws Exception
    {
        dbPEDIDO pedido = new dbPEDIDO();
        dbPEDIDOPARTIDAS pedidopartidas = new dbPEDIDOPARTIDAS();
        dbCLASIFICPROVEEDOR cp = new dbCLASIFICPROVEEDOR();
        dbCATEGORIA categoria = new dbCATEGORIA();
        dbGRUPO grupo = new dbGRUPO();
        dbLINEA linea = new dbLINEA();
        dbMARCA marca = new dbMARCA();
        pedido.setConnection(con);
        pedidopartidas.setConnection(con);
        cp.setConnection(con);
        categoria.setConnection(con);
        grupo.setConnection(con);
        linea.setConnection(con);
        marca.setConnection(con);
        Bean73b bean73b = (Bean73b)ses_usr.getAttribute("bean73b");
        Bean73c bean73c = new Bean73c();
        StringBuffer msg = new StringBuffer();
        String hoy = Fecha.getCurrentDate("yyyyMMddHHmmss");
        logger.debug("Carga nuevas cantidades del request");
        for(int i = 0; i < bean73b.getSize(); i++)
        {
            logger.debug("Cantidad Pedida(" + i + "):" + bean73b.getIcantpedida(i));
            if(request.getParameter("T" + i) != null && !request.getParameter("T" + i).equals(""))
            {
                int cantidad = (new Integer(request.getParameter("T" + i))).intValue();
                logger.debug("Cantidad(" + i + "):" + cantidad);
                bean73b.setIcantpedida(cantidad, i);
            }
        }

        int iidcategoria = 0;
        int iidgrupo = 0;
        int iidlinea = 0;
        int iidmarca = 0;
        int error = 0;
        dbrPARTIDASOLICITADA a = null;
        if(bean73b.getSize() > 0)
        {
            a = bean73b.getPARTIDASOLICITADA(0);
            iidcategoria = a.getIidcategoria();
            iidgrupo = a.getIidgrupo();
            iidlinea = a.getIidlinea();
            iidmarca = a.getIidmarca();
        }
        int sum = 0;
        for(int i = 0; i <= bean73b.getSize(); i++)
        {
            boolean flag = false;
            if(i < bean73b.getSize())
            {
                a = bean73b.getPARTIDASOLICITADA(i);
                if(iidcategoria != a.getIidcategoria() || iidlinea != a.getIidlinea() || iidgrupo != a.getIidgrupo() || iidmarca != a.getIidmarca())
                    flag = true;
                else
                    sum += a.getIcantidadpedida();
            }
            
            if(flag || i == bean73b.getSize())
            {
                cp.setIidcategoria(iidcategoria);
                cp.setIidgrupo(iidgrupo);
                cp.setIidlinea(iidlinea);
                cp.setIidmarca(iidmarca);
                cp.setIidproveedor(bean73b.iidproveedor);
                cp.load();
                categoria.setIidcategoria(iidcategoria);
                categoria.load();
                grupo.setIidgrupo(iidgrupo);
                grupo.load();
                linea.setIidlinea(iidlinea);
                linea.load();
                marca.setIidmarca(iidmarca);
                marca.load();
             
                if(sum < cp.getIminimopedido())
                {
                    msg.append("La Clasificacion " + categoria.getVchdescripcion() + " - " + grupo.getVchdescripcion() + " - " + linea.getVchdescripcion() + " - " + marca.getVchnombre() + " Minimo pedido: " + cp.getIminimopedido() + " El total de sus parte es  " + sum + " Faltan " + (cp.getIminimopedido() - sum) + "<br>");
                    error = 1;
                }
                sum = a.getIcantidadpedida();
                iidcategoria = a.getIidcategoria();
                iidgrupo = a.getIidgrupo();
                iidlinea = a.getIidlinea();
                iidmarca = a.getIidmarca();
            }
        }

        if(error == 0)
        {
            
            pedido.setIidproveedor(bean73b.iidproveedor);
            pedido.setTisfechelabpedido(hoy);
            pedido.setSinototalpartidas((short)bean73b.getSize());
            pedido.setSinototpartpedpend((short)bean73b.getSize());
            pedido.setTiestatus(IDSINSURTIR);
            pedido.create();
            pedido.loadByFecha();
            for(int i = 0; i < bean73b.getSize(); i++)
            {
                a = bean73b.getPARTIDASOLICITADA(i);
                pedidopartidas.setIidpedido(pedido.getIidpedido());
                pedidopartidas.setLiidparte(a.getLiidparte());
                pedidopartidas.setIcantpedida(a.getIcantidadpedida());
                pedidopartidas.setTiestatus(IDSINSURTIR);
                pedidopartidas.create();
            }

            bean73c.setIidpedido(pedido.getIidpedido());
            bean73c.setIidproveedor(pedido.getIidproveedor());
            bean73c.setFecha(hoy);
            ses_usr.setAttribute("bean73c", bean73c);
            bean73b.setPopupImprimir(true);
            msg.append("Se gener\363 el pedido " + pedido.getIidpedido());
            EliminarPartidasSolicitadas(con, ses_usr);
        }
        bean73b.setMsg(msg.toString());
        return "/jsp/7_3b.jsp";
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

    private String EliminarPartidasSolicitadas(Connection con, HttpSession ses_usr)
        throws Exception
    {
        dbrPARTIDASOLICITADAs pss = new dbrPARTIDASOLICITADAs();
        pss.setConnection(con);
        Bean73b bean73b = (Bean73b)ses_usr.getAttribute("bean73b");
        pss.RemoveByIidproveedor(bean73b.iidproveedor);
        bean73b.setVResult(new Vector());
        ses_usr.setAttribute("bean73b", bean73b);
        return "/jsp/7_3b.jsp";
    }

    public String getServletInfo()
    {
        return super.getServletInfo();
    }

    public void init()
    {
        idModulo = getServletConfig().getInitParameter("idModulo") == null ? -1 : (new Integer(getServletConfig().getInitParameter("idModulo"))).intValue();
        IDSINSURTIR = (new Integer(getServletConfig().getInitParameter("IDSINSURTIR"))).intValue();
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
            switch(accion)
            {
            case 1: // '\001'
                strAddress = BuscarProveedores(request, con, ses_usr);
                break;

            case 3: // '\003'
                strAddress = crearPedido(request, con, ses_usr);
                break;

            case 2: // '\002'
                strAddress = BuscarPartidas(request, con, ses_usr);
                break;

            case 4: // '\004'
                strAddress = EliminarPartidasSolicitadas(con, ses_usr);
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

    private static Log logger;
    private int idModulo;
    private int IDSINSURTIR;

    static 
    {
        logger = LogFactory.getLog(PedidosEnvio.class);
    }
}
