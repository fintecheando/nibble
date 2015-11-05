package pva.servlets11;


import org.nibble.main.BeanError;
import org.nibble.main.BeanUtil;
import java.sql.Connection;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;

import dbbeans.cliente.*;
import dbbeans.configsistema.*;
import dbbeans.ctaxcobrar.*;
import dbbeans.notadecargo.*;
import pva.beans11.Bean112;
import pva.beans4.Bean42;
import dbbeans.configuracionpva.dbCONFIGURACIONPVA;

/**
 * DOCUMENT ME!
 *
 * @author $author$
 * @version $Revision$
 */
public class NotaCargo extends HttpServlet {
    //main.MainServlet {
	private int idModulo;
    private int idnotacargo;
    private int idconfsistema;

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
        idconfsistema = new Integer(getServletConfig().getInitParameter("confid")).intValue();
        idnotacargo = new Integer(getServletConfig().getInitParameter("idnotacargo")).intValue();
    }

    /**
     * Process incoming requests for information
     *
     * @param request Object that encapsulates the request to the servlet
     * @param response Object that encapsulates the response from the servlet
     */
    public void performTask(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) {
        Bean42 bean42;
        Bean112 bean112;
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
			if (!bu.getAcceso(idModulo,con))throw new Exception("No tiene permiso a este m�dulo");

            int accion = 0;
/*            
            initConnection();
            seguridad.setConnection(con);
            if (!seguridad.tienePermiso(sistema, new Integer(ses_usr.getAttribute("grupo").toString()).intValue(), idModulo)) {
                throw new Exception("No tiene permiso a este m�dulo");
            }
*/
            if (request.getParameter("accion") != null) {
                accion = new Integer(request.getParameter("accion")).intValue();
            }

            dbCONFIGSISTEMA dbcs = new dbCONFIGSISTEMA();
            dbcs.setConnection(con);
            dbcs.setIidconfsistema(idconfsistema);
            dbcs.load();
            bean112 = new Bean112();
            bean112.setIva(dbcs.getDecivaaplicable());
            bean112.setFecha(org.nibble.util.Fecha.getCurrentDate("dd/MM/yyyy"));

            bean42 = (Bean42) ses_usr.getAttribute("bean42");

            if ((bean42 == null) || (accion == 1)) {
                bean42 = new Bean42();

                dbCLIENTEs clientes = new dbCLIENTEs();
                clientes.setConnection(con);
                clientes.find("");
                bean42.setVClientes(clientes.getResult());
            } else {
                //			bean101.setIdcliente(new Integer(request.getParameter("idcliente")).intValue());
            }

            switch (accion) {
                case 1: // Indica cliente 
                    break;

                case 2: // Guarda Nota de Cargo y actualiza Cta x Cobrar

                    dbNOTADECARGO dbnc = new dbNOTADECARGO();
                    dbnc.setConnection(con);

					dbCONFIGURACIONPVA dbcpva = new dbCONFIGURACIONPVA();
					dbcpva.setConnection(con);
					dbcpva.setIidconfpva(1);
					dbcpva.load();
					
                    dbCLIENTE dbcliente = new dbCLIENTE();
                    dbcliente.setConnection(con);
					
					
					
                    String fecha = org.nibble.util.Fecha.getCurrentDate("yyyyMMddhhmmss");
					int inc = dbcpva.getInumeronca();
					
                    //Inserta nota de cargo
                    
					dbnc.setIidnotadecargo(inc);
					dbnc.setIidnocliente(new Integer(request.getParameter("idcliente")).intValue());                    
				    dbnc.setIidusuario(((Integer) ses_usr.getAttribute("iidusuario")).intValue());
                    dbnc.setIidmodulo(bu.getSistema());
                    dbnc.setTisfechanotacargo(fecha);
                    dbnc.setTisfechavencimient(request.getParameter("fven"));
                    dbnc.setVchconcepto(request.getParameter("concepto"));

                    float imp = new Float(request.getParameter("importe")).floatValue();
				/* Autor: Victor Romero  
				  * Modificacion: 
				  * Se Cambia el Nombre de la Variable "iva" por "ivalocal"							 
				  *  Fecha de Modificacion: 03-02-2004 	
				 */ 
                    float ivalocal = new Float(request.getParameter("ivalocal")).floatValue();
                    dbnc.setDecimporte(imp);
                    dbnc.setDecivaimporte(ivalocal);
                    dbnc.create();
					//Incrementa la referencia de la nota de cargo (inumeronca)de viene de dbCONFIGURACIONPVA
					//y se le manda al CONFIGURACIONPVA con el store
				
					
					dbcpva.setInumeronca(dbcpva.getInumeronca()+1);
					dbcpva.store();
                    // Actualiza credito cliente
                    dbcliente.setIidnocliente(new Integer(request.getParameter("idcliente")).intValue());
                    dbcliente.load();
                    dbcliente.setDeccreditutilizado(dbcliente.getDeccreditutilizado() + (imp + ivalocal));
                    dbcliente.store();

                    
					bean112.setNotaCargo(dbnc);
                    //Obtiene el id de la nota de cargo
                    if (dbnc.findIidnotacargo()) {
                        dbCTAXCOBRAR dbcxc = new dbCTAXCOBRAR();
                        dbcxc.setConnection(con);
                        dbcxc.setIiddoctoorigen(dbnc.getIidnotadecargo());
                        dbcxc.setTiconsecsubdocto(1);
                        dbcxc.setTiidtipodocto(idnotacargo);
                        dbcxc.setIidnocliente(new Integer(request.getParameter("idcliente")).intValue());
                        dbcxc.setTisfechamovimiento(fecha);

                        //						dbcxc.setIidcontradocto(0);
                        //						dbcxc.setTiidtipocontradoc(tipodocto);
                        dbcxc.setDecdebe(imp + ivalocal);
                        dbcxc.create();
                    }
                    
					bean112.setImprimir(true);
                    break;
            }

            ses_usr.setAttribute("bean42", bean42);
            ses_usr.setAttribute("bean112", bean112);
            strAddress = "/jsp/11_2.jsp";
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
