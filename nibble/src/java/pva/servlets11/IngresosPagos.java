package pva.servlets11;

import org.nibble.main.BeanError;
import org.nibble.main.BeanUtil;
import java.sql.Connection;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;

import dbbeans.avisoingreso.*;
import dbbeans.cliente.*;
import dbbeans.ctaxcobrar.*;
import dbbeans.pago.*;
import pva.beans11.Bean116b;

import java.util.Enumeration;
import java.util.StringTokenizer;

/**
 * DOCUMENT ME!
 *
 * @author $author$
 * @version $Revision$
 */
public class IngresosPagos extends HttpServlet{ 

	private int idModulo;
    private int idingreso;

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
        idingreso = new Integer(getServletConfig().getInitParameter("idingreso")).intValue();
    }

    /**
     * Process incoming requests for information
     *
     * @param request Object that encapsulates the request to the servlet
     * @param response Object that encapsulates the response from the servlet
     */
    public void performTask(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) {
        Bean116b bean116b;
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
		
			if (!bu.getSessionValues())throw new Exception("Por motivos de seguridad la sesi�n ha expirado\n Registrese nuevamente");
            int accion = 0;
            
            if (request.getParameter("accion") != null) {
                accion = new Integer(request.getParameter("accion")).intValue();
            }
			
			dbCTAXCOBRARs dbcxcs = new dbCTAXCOBRARs();
            dbcxcs.setConnection(con);

            bean116b = new Bean116b();

            switch (accion) {
                case 1: // Muestra Pagos para cliente = idcliente
                    bean116b.setIdcliente(new Integer(request.getParameter("idcliente")).intValue());
                    bean116b.setIidavisoingreso(new Integer(request.getParameter("iidavisoingreso")).intValue());
                    bean116b.setSaldoIngreso(new Float(request.getParameter("saldo")).floatValue());
                    bean116b.setCxc(
                        dbcxcs.findByClienteFecha(new Integer(request.getParameter("idcliente")).intValue(), "", "", false));
                    ses_usr.setAttribute("bean116b", bean116b);
                    strAddress = "/jsp/11_6b.jsp";

                    break;

                case 3: // Realiza pago ,afecta cuenta x cobrar


					dbPAGO dbpago = new dbPAGO();
					dbpago.setConnection(con);

					String fecha = org.nibble.util.Fecha.getCurrentDate("yyyyMMddHHmmss");
					
                    dbAVISOINGRESO dba = new dbAVISOINGRESO();
                    dba.setConnection(con);

                    dbCLIENTE dbcliente = new dbCLIENTE();
                    dbcliente.setConnection(con);

                    dba.setIidavisoingreso(new Integer(request.getParameter("idavisoingreso")).intValue());
					
					// Actualiza haber en el aviso de ingreso	
                    if (dba.load()) {
                        float haber = dba.getDechaber() + new Float(request.getParameter("total")).floatValue();
                        dba.setDechaber(haber);
                        dba.store();
                    }                   

					 //				Inserta registro pago
		 		    int titipopago = 1;
				   //int titipopago = new Integer(request.getParameter("tipopago")).intValue();

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
							dbcliente.getDeccreditutilizado() - new Float(request.getParameter("total")).floatValue());
                    dbcliente.store();

                    // Registros en cuentas x cobrar
                    Enumeration e = request.getParameterNames();
                    int i = 0;
                    while (e.hasMoreElements()) {
                        String strParam = e.nextElement().toString();
                        String strVal = request.getParameter(strParam);

                        if (strParam.substring(0, 2).equalsIgnoreCase("ab") && !strVal.equalsIgnoreCase("")) {
                            dbCTAXCOBRAR dbcxc = new dbCTAXCOBRAR();
                            dbcxc.setConnection(con);
                            i++;
                            

                            StringTokenizer st = new StringTokenizer(strParam.substring(2, strParam.length()), "_");
                            int contradocto = new Integer(st.nextToken()).intValue();
                            int consecdocto = new Integer(st.nextToken()).intValue();
                            int tipodocto = new Integer(st.nextToken()).intValue();
                            
							String nombre = "saldado"+contradocto;          

                            // Aviso de Ingreso se registra como un Pago en CxC
                            dbcxc.setIiddoctoorigen(dba.getIidavisoingreso());
							dbcxc.setTiidtipodocto(idingreso);
                            dbcxc.setTiconsecsubdocto(dbcxc.findmaxconsec()+1);                                                      
                            dbcxc.setIidnocliente(new Integer(request.getParameter("idcliente")).intValue());
                            dbcxc.setTisfechamovimiento(fecha);
                            dbcxc.setIidcontradocto(contradocto);
                            dbcxc.setTiidtipocontradoc(tipodocto);
                            dbcxc.setDechaber(new Float(strVal).floatValue());
							dbcxc.create();
							                            
                            // Actualiza haber docto.origen (factura o cargo)
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

                    strAddress = "/AplicaIngreso?accion=1";

                    break;
            }
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
