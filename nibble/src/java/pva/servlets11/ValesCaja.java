package pva.servlets11;

import dbbeans.avisoingreso.dbAVISOINGRESO;
import dbbeans.avisoingreso.dbAVISOINGRESOs;

import dbbeans.cliente.*;

import dbbeans.valecaja.dbVALECAJA;

import dbbeans.valeingresos.dbVALEINGRESOS;

import org.nibble.main.BeanError;
import org.nibble.main.BeanUtil;

import pva.beans11.Bean117;

import pva.beans4.Bean42;

import java.sql.Connection;

import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;


/**
 * DOCUMENT ME!
 *
 * @author $jcanibe
 * @version $Revision$
 */
public class ValesCaja extends HttpServlet {
    private int idModulo;

    /**
     * Process incoming HTTP GET requests
     *
     * @param request Object that encapsulates the request to the servlet
     * @param response Object that encapsulates the response from the servlet
     */
    public void doGet(javax.servlet.http.HttpServletRequest request,
        javax.servlet.http.HttpServletResponse response)
        throws javax.servlet.ServletException, java.io.IOException {
        performTask(request, response);
    }

    /**
     * Process incoming HTTP POST requests
     *
     * @param request Object that encapsulates the request to the servlet
     * @param response Object that encapsulates the response from the servlet
     */
    public void doPost(javax.servlet.http.HttpServletRequest request,
        javax.servlet.http.HttpServletResponse response)
        throws javax.servlet.ServletException, java.io.IOException {
        performTask(request, response);
    }

    /**
     * Initializes the servlet.
     */
    public void init() {
        idModulo = (getServletConfig().getInitParameter("idModulo") != null)
            ? new Integer(getServletConfig().getInitParameter("idModulo")).intValue()
            : (-1);
    }

    /**
     * Process incoming requests for information
     *
     * @param request Object that encapsulates the request to the servlet
     * @param response Object that encapsulates the response from the servlet
     */
    public void performTask(javax.servlet.http.HttpServletRequest request,
        javax.servlet.http.HttpServletResponse response) {
        Bean42 bean42;
        Bean117 bean117;
        Connection con = null;
        HttpSession ses_usr = null;
        BeanUtil bu = null;
        RequestDispatcher dispatcher = null;
		RequestDispatcher dispatcher2 = null;
        String strAddress = null;
		String strAddress2 = null;
        int accion = 0;

        try {
            ses_usr = request.getSession(false);
            bu = new BeanUtil();
            con = bu.getConn();
            bu.setSession(ses_usr);

            if (!bu.getSessionValues()) {
                throw new Exception(
                    "Por motivos de seguridad la sesi�n ha expirado\n Registrese nuevamente");
            }

            if (!bu.getAcceso(idModulo, con)) {
                throw new Exception("No tiene permiso a este m�dulo");
            }

            if (request.getParameter("accion") != null) {
                accion = new Integer(request.getParameter("accion")).intValue();
            }

			if (accion != 1){
				bean117 = (Bean117) ses_usr.getAttribute("bean117");
			}else{
            	bean117 = new Bean117();
			}
            bean42 = (Bean42) ses_usr.getAttribute("bean42");

            if ((bean42 == null) || (accion == 1)) {
                bean42 = new Bean42();

                dbCLIENTEs clientes = new dbCLIENTEs();
                clientes.setConnection(con);
                clientes.find("");
                bean42.setVClientes(clientes.getResult());
            } else {
            	if (request.getParameter("idcliente")!=null){
					bean117.setCliente(new Integer(request.getParameter("idcliente")).intValue());
            	}                	
            }

			bean117.setWinpopup(false);
			
            switch (accion) {
            case 1: // Muestra Clientes
                bean117.setDetalle(false);
                strAddress = "/jsp/11_7.jsp";

                break;

            case 2: // Muestra Ingresos x Cliente

                dbAVISOINGRESOs dbai = new dbAVISOINGRESOs();
                dbai.setConnection(con);
                bean117.setDetalle(true);
                bean117.setIngresos(dbai.findByCliente(
                        new Integer(request.getParameter("idcliente")).intValue()));
                strAddress = "/jsp/11_7.jsp";

                break;

            case 3: // Genera Vale, decrementa saldo avisos ingresos

                String fecha = org.nibble.util.Fecha.getCurrentDate("yyyyMMddHHmmss");
                float total = new Float(request.getParameter("total")).floatValue();

                //Genera vale de caja
                dbVALECAJA dbvale = new dbVALECAJA();
                dbvale.setConnection(con);
                dbvale.setIidnocliente(new Integer(request.getParameter(
                            "idcliente")).intValue());
                dbvale.setTisfecha(fecha);
                dbvale.setIidusuario(((Integer) ses_usr.getAttribute(
                        "iidusuario")).intValue());
                dbvale.setIidmodulo(bu.getSistema());
                dbvale.setDecimporte(total);
                dbvale.create();

                int valeid = dbvale.findIidvale();

                // Actualiza saldo cliente
                /*
                 * Fecha: 25-02-2004
                 * Modificacion: Se comentan las lineas para evitar que se aumente el disponible en el cliente
                 * 
                 */
                 /*dbCLIENTE dbcliente = new dbCLIENTE();
                dbcliente.setConnection(con);
                dbcliente.setIidnocliente(new Integer(request.getParameter(
                            "idcliente")).intValue());
                dbcliente.load();
                dbcliente.setDeccreditutilizado(dbcliente.getDeccreditutilizado() -
                    total);
                dbcliente.store();
				*/
                Enumeration e = request.getParameterNames();

                //Actualiza saldo avisos de ingresos
                while (e.hasMoreElements()) {
                    String strParam = e.nextElement().toString();
                    String strVal = request.getParameter(strParam);

                    if (strParam.substring(0, 2).equalsIgnoreCase("ab") &&
                            !strVal.equalsIgnoreCase("")) {
                        int avisoingreso = new Integer(strParam.substring(2,
                                    strParam.length())).intValue();
                        dbAVISOINGRESO dba = new dbAVISOINGRESO();
                        dba.setConnection(con);
                        dba.setIidavisoingreso(avisoingreso);

                        if (dba.load()) {
                            float haber = dba.getDechaber() +
                                new Float(request.getParameter(strParam)).floatValue();
                            dba.setDechaber(haber);
                            dba.store();
                        }

                        dbVALEINGRESOS dbvi = new dbVALEINGRESOS();
                        dbvi.setConnection(con);
                        dbvi.setIidavisoingreso(avisoingreso);
                        dbvi.setIidvale(valeid);
                        dbvi.setDecimporte(new Float(request.getParameter(
                                    strParam)).floatValue());
                        dbvi.create();
                    }
                }

                bean117.setIdvale(valeid);
                bean117.setImporte(total);
                bean117.setrscliente(new String(request.getParameter("rscliente")));				
				bean117.setIdCliente(new Integer(request.getParameter("idcliente")).intValue());			
                bean117.setWinpopup(true);

                strAddress = "/jsp/11_7.jsp";

                break;

            case 4:
                strAddress = "/jsp/11_7imp.jsp";
				strAddress2 = "/jsp/11_7.jsp";
				

                break;
            }

            ses_usr.setAttribute("bean42", bean42);
            ses_usr.setAttribute("bean117", bean117);
			request.setAttribute("bean117",bean117);		
			
							
        } catch (Throwable e) {
            bu.setCommit(false);
            e.printStackTrace();

            BeanError error = new BeanError();
            error.setErrorMessage(e.getMessage());
            request.setAttribute("error", error);
            strAddress = "/jsp/error.jsp";
        } finally {
            try {
                bu.finalizeTransaction(con);				
								
                dispatcher = getServletContext().getRequestDispatcher(strAddress);
                dispatcher.forward(request, response);
                			
				
				
            } catch (Exception ex) {
                System.out.println("FATAL:" + ex.getMessage());
            }
        }
    }
}
