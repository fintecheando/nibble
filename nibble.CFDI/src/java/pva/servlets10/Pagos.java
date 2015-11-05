package pva.servlets10;

import dbbeans.cliente.*;

import dbbeans.ctaxcobrar.*;

import dbbeans.pago.*;

/**
 * Insert the type's description here.
 * Creation date: (07/11/2002 01:39:51 p.m.)
 * @author:
 */
import org.nibble.main.BeanError;
import org.nibble.main.BeanUtil;

import pva.beans10.Bean101;

import pva.beans4.Bean42;

import java.sql.Connection;

import java.util.Enumeration;
import java.util.StringTokenizer;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;


/**
 * DOCUMENT ME!
 *
 * @author $author$
 * @version $Revision$
 */
public class Pagos extends HttpServlet {
    //main.MainServlet {

    private int idModulo;
    private int idtipopago;

    /**
     * Process incoming HTTP GET requests
     *
     * @param request Object that encapsulates the request to the servlet
     * @param response Object that encapsulates the response from the servlet
     */
    public void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response)
            throws javax.servlet.ServletException, java.io.IOException {
        performTask(request, response);
    }

    /**
     * Process incoming HTTP POST requests
     *
     * @param request Object that encapsulates the request to the servlet
     * @param response Object that encapsulates the response from the servlet
     */
    public void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response)
            throws javax.servlet.ServletException, java.io.IOException {
        performTask(request, response);
    }

    /**
     * Initializes the servlet.
     */
    public void init() {
        idModulo = (getServletConfig().getInitParameter("idModulo") != null)
            ? new Integer(getServletConfig().getInitParameter("idModulo")).intValue() : (-1);

        idtipopago = new Integer(getServletConfig().getInitParameter("idtipopago")).intValue();
    }

    /**
     * Process incoming requests for information
     *
     * @param request Object that encapsulates the request to the servlet
     * @param response Object that encapsulates the response from the servlet
     */
    public void performTask(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) {
        Bean42 bean42;
        Bean101 bean101;

        Connection con = null;
        HttpSession ses_usr = null;
        BeanUtil bu = null;
        RequestDispatcher dispatcher = null;
        String strAddress = null;

        try {
            ses_usr = request.getSession(false);
            bu = new BeanUtil();
            con = bu.getConn();
            bu.setSession(ses_usr);

            if (!bu.getSessionValues()) {
                throw new Exception("Por motivos de seguridad la sesi�n ha expirado\n Registrese nuevamente");
            }

            if (!bu.getAcceso(idModulo, con)) {
                throw new Exception("No tiene permiso a este m�dulo");
            }

            int accion = 0;

            /*
                    initConnection();
                               seguridad.setConnection(con);
                              if(!seguridad.tienePermiso(sistema,new Integer(ses_usr.getAttribute("grupo").toString()).intValue(),idModulo))throw new Exception("No tiene permiso a este m�dulo");
            */
            if (request.getParameter("accion") != null) {
                accion = new Integer(request.getParameter("accion")).intValue();
            }

            bean42 = (Bean42) ses_usr.getAttribute("bean42");
            bean101 = new Bean101();

            if ((bean42 == null) || (accion == 1)) {
                bean42 = new Bean42();

                dbCLIENTEs clientes = new dbCLIENTEs();
                clientes.setConnection(con);
                clientes.find("");
                bean42.setVClientes(clientes.getResult());
            } else {
                bean101.setIdcliente(new Integer(request.getParameter("idcliente")).intValue());
            }

            dbCTAXCOBRARs dbcxcs = new dbCTAXCOBRARs();
            dbcxcs.setConnection(con);

            switch (accion) {
                case 1: // Indica cliente y fechas para buscar
                    bean101.setDetalle(false);
                    strAddress = "/jsp/10_1.jsp";

                    break;

                case 2: // Muestra cuentas x cobrar

                    String fini = request.getParameter("fini").equalsIgnoreCase("") ? ""
                                                                                    : (org.nibble.util.Fecha.formatToMySQL(
                            request.getParameter("fini")) + "000000");
                    String ffin = request.getParameter("ffin").equalsIgnoreCase("") ? ""
                                                                                    : (org.nibble.util.Fecha.formatToMySQL(
                            request.getParameter("ffin")) + "235959");
                    bean101.setCxc(
                        dbcxcs.findByClienteFecha(new Integer(request.getParameter("idcliente")).intValue(), fini, ffin, false));
                    bean101.setDetalle(true);
                    strAddress = "/jsp/10_1.jsp";

                    break;

                case 3: // Realiza pago y afecta cuenta x cobrar

                    dbPAGO dbpago = new dbPAGO();
                    dbpago.setConnection(con);

                    dbCLIENTE dbcliente = new dbCLIENTE();
                    dbcliente.setConnection(con);

                    String fecha = org.nibble.util.Fecha.getCurrentDate("yyyyMMddHHmmss");

                    //Inserta registro pago
                    int titipopago = new Integer(request.getParameter("tipopago")).intValue();

                    dbpago.setIidnocliente(new Integer(request.getParameter("idcliente")).intValue());
                    dbpago.setIidusuario(((Integer) ses_usr.getAttribute("iidusuario")).intValue());
                    dbpago.setIidmodulo(bu.getSistema());
                    dbpago.setTisfechapago(fecha);
                    dbpago.setTitipopago(titipopago);
                    dbpago.setDecimporte(new Float(request.getParameter("ingreso")).floatValue());
                    dbpago.setVchconcepto("");                    
                    dbpago.create();

                    // Actualiza saldo cliente
                    dbcliente.setIidnocliente(new Integer(request.getParameter("idcliente")).intValue());
                    dbcliente.load();
                    dbcliente.setDeccreditutilizado(
                        dbcliente.getDeccreditutilizado() - new Float(request.getParameter("ingreso")).floatValue());
                    dbcliente.store();

                    //Obtiene el registro del pago
                    if (dbpago.findIidpago()) {
                        // Registros en cuentas x cobrar
                        Enumeration e = request.getParameterNames();
                        int i = 0;

                        while (e.hasMoreElements()) {
                            String strParam = e.nextElement().toString();                                                        
                            String strVal = request.getParameter(strParam);
                            
							
							/*if (intParam.substring(0, 7).equalsIgnoreCase("saldado") && !intVal.equalsIgnoreCase("")) {
									StringTokenizer stint = new StringTokenizer(intParam.substring(7, intParam.length()), "");
							    	int bitNumero = new Integer(stint.nextToken()).intValue();
							}*/

                            if (strParam.substring(0, 2).equalsIgnoreCase("ab") && !strVal.equalsIgnoreCase("")) {
                            	
                                dbCTAXCOBRAR dbcxc = new dbCTAXCOBRAR();
                                dbcxc.setConnection(con);
                                i++;
                                

                                StringTokenizer st = new StringTokenizer(strParam.substring(2, strParam.length()), "_");
                                int contradocto = new Integer(st.nextToken()).intValue();
                                int consecdocto = new Integer(st.nextToken()).intValue();
                                int tipodocto = new Integer(st.nextToken()).intValue();
                                
                               
                                String nombre = "saldado"+contradocto;                               
								
                                dbcxc.setIiddoctoorigen(dbpago.getIidpago());
                                dbcxc.setTiconsecsubdocto(i);
                                dbcxc.setTiidtipodocto(idtipopago);
                                dbcxc.setIidnocliente(new Integer(request.getParameter("idcliente")).intValue());
                                dbcxc.setTisfechamovimiento(fecha);
                                dbcxc.setIidcontradocto(contradocto);
                                dbcxc.setTiidtipocontradoc(tipodocto);
                                dbcxc.setDechaber(new Float(strVal).floatValue());
								//dbcxc.setBitmovsaldado(new Integer(request.getParameter(nombre)).intValue());
								dbcxc.create();
                                // Actualiza haber docto.origen
                                dbcxc.setIiddoctoorigen(contradocto);
                                dbcxc.setTiconsecsubdocto(consecdocto);
                                dbcxc.setTiidtipodocto(tipodocto);                                

                                if (dbcxc.load()) {
                                    float haber = dbcxc.getDechaber() + new Float(strVal).floatValue();
                                    dbcxc.setDechaber(haber);
									dbcxc.setBitmovsaldado(new Integer(request.getParameter(nombre)).intValue());
                                    dbcxc.store();
                                }
                            }
                        }
                    }

                    strAddress = "/jsp/10_1.jsp";

                    break;

                case 4:

                    int docto = new Integer(request.getParameter("doc")).intValue();
                    int tipo = new Integer(request.getParameter("tipo")).intValue();
                    bean101.setCxc(dbcxcs.findByDocto(docto, tipo));
                    strAddress = "/jsp/10_1b.jsp";

                    break;
            }

            ses_usr.setAttribute("bean101", bean101);
            ses_usr.setAttribute("bean42", bean42);
        } catch (Throwable e) {
			bu.setCommit(false);
			e.printStackTrace();
			BeanError error = new BeanError();
			error.setErrorMessage(e.getMessage());
			request.setAttribute("error",error);
			strAddress =  "/jsp/error.jsp";
		} finally {
			try{
				bu.finalizeTransaction(con);
				dispatcher = getServletContext().getRequestDispatcher(strAddress);
				dispatcher.forward(request, response);    		
			}catch (Exception ex){
				System.out.println("FATAL:"+ex.getMessage());
			}
		}
    }
}
