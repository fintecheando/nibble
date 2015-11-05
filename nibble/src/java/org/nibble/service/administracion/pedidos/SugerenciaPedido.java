// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SugerenciaPedido.java

package org.nibble.service.administracion.pedidos;

import dbbeans.clasificproveedor.dbCLASIFICPROVEEDOR;
import dbbeans.configuracionpva.dbCONFIGURACIONPVA;
import dbbeans.criteriopedido.dbCRITERIOPEDIDO;
import dbbeans.hisconsumo.dbHISCONSUMO;
import dbbeans.inventario.dbINVENTARIO;
import dbbeans.partidasolicitada.dbPARTIDASOLICITADA;
import dbbeans.partidasolicitada.dbPARTIDASOLICITADAs;
import dbbeans.pedido.dbPEDIDOs;
import dbbeans.proveedor.dbPROVEEDORs;
import dbrbeans.inventario.dbrINVENTARIO;
import dbrbeans.inventario.dbrINVENTARIOSs;
import java.io.IOException;
import java.sql.Connection;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.nibble.main.BeanError;
import org.nibble.main.BeanUtil;
import org.nibble.service.vo.pedidos.Bean71;
import org.nibble.service.vo.pedidos.Bean71Concentrado;
import org.nibble.service.vo.pedidos.Bean71Detalle;
import org.nibble.service.vo.pedidos.Bean7Sugerir;
import org.nibble.service.vo.proveedores.Bean52;
import org.nibble.util.Fecha;
import org.nibble.util.UtilDate;
//import main.BeanError;
//import main.BeanUtil;
//import nc.beans5.Bean52;
//import nc.beans7.*;
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
//import util.Fecha;
//import util.UtilDate;

public class SugerenciaPedido extends HttpServlet
{
    private class Clasificacion
    {

        public int getIidcategoria()
        {
            return iidcategoria;
        }

        public int getIidgrupo()
        {
            return iidgrupo;
        }

        public int getIidlinea()
        {
            return iidlinea;
        }

        public int getIidmarca()
        {
            return iidmarca;
        }

        public void setIidcategoria(int i)
        {
            iidcategoria = i;
        }

        public void setIidgrupo(int i)
        {
            iidgrupo = i;
        }

        public void setIidlinea(int i)
        {
            iidlinea = i;
        }

        public void setIidmarca(int i)
        {
            iidmarca = i;
        }

        private int iidcategoria;
        private int iidgrupo;
        private int iidlinea;
        private int iidmarca;

        Clasificacion()
        {
        }
    }


    public SugerenciaPedido()
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

    private String eliminar(HttpServletRequest request, Bean71 bean71)
        throws Exception
    {
        int i = 0;
        if(request.getParameter("i") != null && request.getParameter("i").length() > 0)
            i = (new Integer(request.getParameter("i"))).intValue();
        return "/jsp/7_1.jsp";
    }

    public void init()
    {
        idModulo = getServletConfig().getInitParameter("idModulo") == null ? -1 : (new Integer(getServletConfig().getInitParameter("idModulo"))).intValue();
        ID_CONFIGPVA = (new Integer(getServletConfig().getInitParameter("ID_CONFIGPVA"))).intValue();
        IDSINSURTIR = (new Integer(getServletConfig().getInitParameter("IDSINSURTIR"))).intValue();
        IDPARCIALMENTE = (new Integer(getServletConfig().getInitParameter("IDPARCIALMENTE"))).intValue();
    }

    private Clasificacion getClasificacion(HttpServletRequest request)
    {
        Clasificacion clasificacion = new Clasificacion();
        int iidcategoria = 0;
        int iidgrupo = 0;
        int iidlinea = 0;
        int iidmarca = 0;
        if(request.getParameter("iidcategoria") != null && request.getParameter("iidcategoria").length() > 0)
            iidcategoria = (new Integer(request.getParameter("iidcategoria"))).intValue();
        if(request.getParameter("iidgrupo") != null && request.getParameter("iidgrupo").length() > 0)
            iidgrupo = (new Integer(request.getParameter("iidgrupo"))).intValue();
        if(request.getParameter("iidlinea") != null && request.getParameter("iidlinea").length() > 0)
            iidlinea = (new Integer(request.getParameter("iidlinea"))).intValue();
        if(request.getParameter("iidmarca") != null && request.getParameter("iidmarca").length() > 0)
            iidmarca = (new Integer(request.getParameter("iidmarca"))).intValue();
        clasificacion.setIidcategoria(iidcategoria);
        clasificacion.setIidgrupo(iidgrupo);
        clasificacion.setIidlinea(iidlinea);
        clasificacion.setIidmarca(iidmarca);
        return clasificacion;
    }

    private String initial(HttpServletRequest request, Bean71 bean71, boolean bitsalidareciente, Connection con, HttpSession ses_usr)
        throws Exception
    {
        return "/jsp/7_1.jsp";
    }

    private void muestraProveedores(HttpServletRequest request, Connection con, Bean52 bean52)
    {
        dbPROVEEDORs proveedores = new dbPROVEEDORs();
        Clasificacion c = getClasificacion(request);
        try
        {
            proveedores.setConnection(con);
            bean52.setVProveedores(proveedores.findByClasificacion(c.getIidcategoria(), c.getIidgrupo(), c.getIidlinea(), c.getIidmarca()));
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    private Bean71 calculoXParteProveedor(HttpServletRequest request, Bean52 bean52, Bean71 bean71b, Connection con, boolean bitsalidareciente)
        throws Exception
    {
        dbCONFIGURACIONPVA cpva = new dbCONFIGURACIONPVA();
        dbCRITERIOPEDIDO criteriopedido = new dbCRITERIOPEDIDO();
        dbrINVENTARIOSs inventarios = new dbrINVENTARIOSs();
        dbPEDIDOs pedidos = new dbPEDIDOs();
        dbPARTIDASOLICITADAs partidasolicitadas = new dbPARTIDASOLICITADAs();
        dbCLASIFICPROVEEDOR dbCP = new dbCLASIFICPROVEEDOR();
        Vector vconcentrado = null;
        Vector vdetalle = null;
        Vector vpartesproveedor = null;
        Vector vproveedor = new Vector();
        Hashtable result = new Hashtable();
        Bean71 bean71 = null;
        try
        {
            Clasificacion c = getClasificacion(request);
            cpva.setConnection(con);
            criteriopedido.setConnection(con);
            inventarios.setConnection(con);
            pedidos.setConnection(con);
            partidasolicitadas.setConnection(con);
            dbCP.setConnection(con);
            cpva.setIidconfpva(ID_CONFIGPVA);
            cpva.load();
            String fechaInicial = cpva.getDtfechainstalacion();
            int mm = UtilDate.getMM(fechaInicial, "yyyy-mm-dd");
            int yy = UtilDate.getYY(fechaInicial, "yyyy-mm-dd");
            for(int i = 0; i < bean52.getProveedoresSize(); i++)
                if(request.getParameter("C" + bean52.getId(i)) != null)
                    vproveedor.addElement(new Integer(bean52.getId(i)));

            if(vproveedor.isEmpty())
                vproveedor = bean71b.getVProveedorSel();
            inventarios.findByClasificacion(c.getIidcategoria(), c.getIidgrupo(), c.getIidlinea(), c.getIidmarca(), bitsalidareciente, vproveedor);
            vpartesproveedor = inventarios.getResult();
            criteriopedido.setIidcategoria(c.getIidcategoria());
            criteriopedido.setIidgrupo(c.getIidgrupo());
            criteriopedido.setIidlinea(c.getIidlinea());
            criteriopedido.setIidmarca(c.getIidmarca());
            criteriopedido.load();
            int diasInventario = criteriopedido.getInodiasinventario();
            if(vpartesproveedor.size() > 0)
                vdetalle = new Vector();
            for(int i = 0; i < vpartesproveedor.size(); i++)
            {
                dbrINVENTARIO inventario = (dbrINVENTARIO)vpartesproveedor.elementAt(i);
                boolean flag = false;
                long liidparte = inventario.getLiidparte();
                String vchparte = inventario.getVchnumparte();
                int iidproveedor = inventario.getIidproveedor();
                String vchproveedor = inventario.getVchrazonsocial();
                int hconsumo = inventario.getIcantconsumo();
                int multiploempaque = inventario.getImultiploempaque();
                int minembarque = inventario.getIminempaque();
                int existencia = inventario.getIexistencia();
                int backorder = pedidos.getBackOrder(liidparte, iidproveedor, IDSINSURTIR, IDPARCIALMENTE);
                int noenviados = partidasolicitadas.getNoEnviados(liidparte, iidproveedor);
                float preciob100 = inventario.getDecpreciob100();
                dbPARTIDASOLICITADA partidasolicitada = new dbPARTIDASOLICITADA();
                partidasolicitada.setConnection(con);
                partidasolicitada.setLiidparte(liidparte);
                partidasolicitada.setIidproveedor(iidproveedor);
                int cantidadpedida;
                if(partidasolicitada.loadByLiidparteIidproveedor())
                    cantidadpedida = partidasolicitada.getIcantidadpedida();
                else
                    cantidadpedida = 0;
                
                dbCP.setIidproveedor(iidproveedor);
                dbCP.setIidcategoria(c.getIidcategoria());
                dbCP.setIidgrupo(c.getIidgrupo());
                dbCP.setIidlinea(c.getIidlinea());
                dbCP.setIidmarca(c.getIidmarca());
                dbCP.load();
                int minimopedido = dbCP.getIminimopedido();
                float factor = dbCP.getDecfactorvaluainve();
                dbHISCONSUMO hisconsumo = new dbHISCONSUMO();
                hisconsumo.setConnection(con);
                hisconsumo.setLiidparte(liidparte);
                hisconsumo.setIidproveedor(iidproveedor);
                Bean7Sugerir b7s = new Bean7Sugerir(hisconsumo);
                b7s.setAnioInicial(yy);
                b7s.setBackOrder(backorder);
                b7s.setDiasAlmacen(diasInventario);
                b7s.setExistencia(existencia);
                b7s.setHisConsumo(hconsumo);
                b7s.setMesInicial(mm);
                b7s.setMinimoPedido(minimopedido);
                b7s.setMultiploEmpaque(multiploempaque);
                int sugerencia = b7s.caculaSugerencia();
                int consumo = b7s.getConsumo();
                int indice = b7s.getIndiceResurtido();
                logger.debug("Sugerencia:" + sugerencia + " Consumo:" + consumo + " Indice:" + indice);
                Bean71Detalle b7d = new Bean71Detalle();
                b7d.setLiidparte(liidparte);
                b7d.setNoparte(vchparte);
                b7d.setIidproveedor(iidproveedor);
                b7d.setVchproveedor(vchproveedor);
                b7d.setDiasinventario(diasInventario);
                b7d.setMultempaque(multiploempaque);
                b7d.setMembarque(minembarque);
                b7d.setExistencia(existencia);
                b7d.setBackorder(backorder);
                b7d.setNoenviados(noenviados);
                b7d.setCantidad(cantidadpedida);
                b7d.setConsumo(consumo);
                b7d.setCosto(preciob100 * factor);
                b7d.setIndiceresurtido(indice);
                b7d.setSugiere(sugerencia);
                vdetalle.add(b7d);
                Bean71Concentrado b7c = new Bean71Concentrado();
                b7c.setLiidparte(liidparte);
                Bean71Concentrado temp;
                if((temp = (Bean71Concentrado)result.get(b7c)) != null)
                {
                    flag = true;
                    b7c = temp;
                }
                b7c.setVchparte(vchparte);
                b7c.setHconsumo(hconsumo);
                b7c.setMm(mm);
                b7c.setYy(yy);
                b7c.setExistencia(b7c.getExistencia() + existencia);
                b7c.setBackorder(b7c.getBackorder() + backorder);
                b7c.setNoenviados(b7c.getNoenviados() + noenviados);
                b7c.setCantidad(b7c.getCantidad() + cantidadpedida);
                b7c.setConsumo(b7c.getConsumo() + consumo);
                b7c.setIndiceresurtido(b7c.getIndiceresurtido() + indice);
                b7c.setSugiere(b7c.getSugiere() + sugerencia);
                if(!flag)
                    result.put(b7c, b7c);
            }

            if(!result.isEmpty())
            {
                vconcentrado = new Vector();
                Bean71Concentrado b7c;
                for(Enumeration e = result.elements(); e.hasMoreElements(); vconcentrado.add(b7c))
                    b7c = (Bean71Concentrado)e.nextElement();

                Collections.sort(vconcentrado);
            }
            bean71 = new Bean71();
            bean71.setBitsalidareciente(bitsalidareciente);
            bean71.setVConcentrado(vconcentrado);
            bean71.setVDetalle(vdetalle);
            bean71.setVProveedorSel(vproveedor);
        }
        catch(Exception e)
        {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
        return bean71;
    }

    private Bean71 partesProveedor(HttpServletRequest request, Bean71 bean71)
        throws Exception
    {
        try
        {
            if(request.getParameter("i") != null && request.getParameter("i").length() > 0)
            {
                int i = (new Integer(request.getParameter("i"))).intValue();
                long liidparte = bean71.getCliidparte(i);
                Vector vindices = new Vector();
                for(int j = 0; j < bean71.getVdetallesize(); j++)
                    if(liidparte == bean71.getDliidparte(j))
                        vindices.add(new Integer(j));

                if(!vindices.isEmpty())
                    bean71.setVIndices(vindices);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
        return bean71;
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
            Bean71 bean71 = new Bean71();
            Bean52 bean52 = new Bean52();
            if(request.getParameter("accion") != null)
                accion = (new Integer(request.getParameter("accion"))).intValue();
            switch(accion)
            {
            case 1: // '\001'
            {
                boolean bitsalidareciente = true;
                bean71.setBitsalidareciente(bitsalidareciente);
                muestraProveedores(request, con, bean52);
                ses_usr.setAttribute("bean52", bean52);
                ses_usr.setAttribute("bean71", bean71);
                strAddress = "/jsp/7_1b.jsp";
                break;
            }

            case 2: // '\002'
            {
                boolean bitsalidareciente = false;
                bean71.setBitsalidareciente(bitsalidareciente);
                muestraProveedores(request, con, bean52);
                ses_usr.setAttribute("bean52", bean52);
                ses_usr.setAttribute("bean71", bean71);
                strAddress = "/jsp/7_1b.jsp";
                break;
            }

            case 3: // '\003'
            {
                bean52 = (Bean52)ses_usr.getAttribute("bean52");
                bean71 = (Bean71)ses_usr.getAttribute("bean71");
                boolean bitsalidareciente = bean71.isBitsalidareciente();
                bean71 = calculoXParteProveedor(request, bean52, bean71, con, bitsalidareciente);
                ses_usr.setAttribute("bean71", bean71);
                strAddress = "/jsp/7_1.jsp";
                break;
            }

            case 4: // '\004'
            {
                bean71 = (Bean71)ses_usr.getAttribute("bean71");
                bean71 = partesProveedor(request, bean71);
                ses_usr.setAttribute("bean71", bean71);
                strAddress = "/jsp/7_1det.jsp";
                break;
            }

            case 5: // '\005'
            {
                bean71 = (Bean71)ses_usr.getAttribute("bean71");
                strAddress = solicitar(request, bean71, con);
                break;
            }
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
                ex.printStackTrace();
                System.out.println("FATAL:" + ex.getMessage());
            }
        }
    }

    private String solicitar(HttpServletRequest request, Bean71 bean71, Connection con)
        throws Exception
    {
        dbPARTIDASOLICITADA partidasolicitada = new dbPARTIDASOLICITADA();
        dbINVENTARIO inventario = new dbINVENTARIO();
        int i = 0;
        int cantidad = 0;
        String hoy = Fecha.getCurrentDate("yyyy-MM-dd");
        try
        {
            partidasolicitada.setConnection(con);
            inventario.setConnection(con);
            if(request.getParameter("i") != null && request.getParameter("i").length() > 0)
                i = (new Integer(request.getParameter("i"))).intValue();
            if(request.getParameter("cantidad") != null && request.getParameter("cantidad").length() > 0)
                cantidad = (new Integer(request.getParameter("cantidad"))).intValue();
            if(cantidad == 0)
                throw new Exception("La cantidad no puede ser 0 ");
            long liidparte = bean71.getDliidparte(i);
            int iidproveedor = bean71.getDiidprovedor(i);
            
            partidasolicitada.setLiidparte(liidparte);
            partidasolicitada.setIidproveedor(iidproveedor);
            if(partidasolicitada.loadByLiidparteIidproveedor())
            {
                partidasolicitada.setIcantidadpedida(cantidad + partidasolicitada.getIcantidadpedida());
                partidasolicitada.setDtfechasolicitud(hoy);
                partidasolicitada.store();
            } else
            {
                partidasolicitada.setIcantidadpedida(cantidad);
                partidasolicitada.setDtfechasolicitud(hoy);
                partidasolicitada.create();
            }
            if(bean71.isBitsalidareciente())
            {
                inventario.setLiidparte(liidparte);
                inventario.setIidproveedor(iidproveedor);
                inventario.load();
                inventario.setBitsalidareciente(false);
                inventario.store();
            }
        }
        catch(Exception e)
        {
            throw new Exception(e.getMessage());
        }
        return "/SugerenciaPedido?accion=3";
    }

    private static Log logger;
    private int idModulo;
    private int ID_CONFIGPVA;
    private int IDSINSURTIR;
    private int IDPARCIALMENTE;

    static 
    {
        logger = LogFactory.getLog(SugerenciaPedido.class);
    }
}
