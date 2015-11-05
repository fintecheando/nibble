package pva.servlets;

import org.nibble.vo.factura.FacturaVO;
import org.nibble.main.BeanError;
import org.nibble.main.BeanUtil;
import java.sql.Connection;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;

import dbbeans.cliente.*;
import org.nibble.dao.factura.*;
import pva.beans.BeanBuscaFactura;
import pva.beans4.Bean42;


/**
 * DOCUMENT ME!
 *
 * @author $author$
 * @version $Revision$
 */
public class BuscaFactura extends HttpServlet {
    // main.MainServlet {

	private int idModulo;
    private String strTitulo;
    private String strImagen;
    private String strLink;

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
     * Returns the servlet info string.
     */
    public String getServletInfo() {
        return super.getServletInfo();
    }

    /**
     * Initializes the servlet.
     */
    public void init() {
		idModulo = (getServletConfig().getInitParameter("idModulo") != null)
						? new Integer(getServletConfig().getInitParameter("idModulo")).intValue() : (-1);        
        strTitulo = getServletConfig().getInitParameter("titulo");
        strImagen = getServletConfig().getInitParameter("imagen");
        strLink = getServletConfig().getInitParameter("link");
    }

    /**
     * Process incoming requests for information
     *
     * @param request Object that encapsulates the request to the servlet
     * @param response Object that encapsulates the response from the servlet
     */
    public void performTask(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) {
        Bean42 bean42;
        BeanBuscaFactura beanFac;
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
            

            beanFac = new BeanBuscaFactura();
            beanFac.setImagen(strImagen);
            beanFac.setTitulo(strTitulo);
            beanFac.setLink(strLink);
            beanFac.setFecha(org.nibble.util.Fecha.getCurrentDate("dd/MM/yyyy"));

            bean42 = (Bean42) ses_usr.getAttribute("bean42");

            if ((bean42 == null) || (accion == 1)) {
                bean42 = new Bean42();

                dbCLIENTEs clientes = new dbCLIENTEs();
                clientes.setConnection(con);
                clientes.find("");
                bean42.setVClientes(clientes.getResult());
            } else {
                beanFac.setCliente(new Integer(request.getParameter("idcliente")).intValue());
            }

            switch (accion) {
                case 1: // Indica cliente y fechas para buscar
                    beanFac.setDetalle(false);

                    break;

                case 2:

                    FacturaVO dbFac = new FacturaVO();
                    dbFac.setConnection(con);
                    beanFac.setFacturas(
                        dbFac.findByClienteFecha(
                            new Integer(request.getParameter("idcliente")).intValue(),
                            request.getParameter("fini").equalsIgnoreCase("") ? request.getParameter("fini")
                                                                              : org.nibble.util.Fecha.formatToMySQL(
                                request.getParameter("fini")),
                            request.getParameter("ffin").equalsIgnoreCase("") ? request.getParameter("ffin")
                                                                              : org.nibble.util.Fecha.formatToMySQL(
                                request.getParameter("ffin"))));
                    beanFac.setDetalle(true);

                    break;
            }

            ses_usr.setAttribute("bean42", bean42);
            ses_usr.setAttribute("beanFac", beanFac);
            strAddress = "/jsp/busca_fac.jsp";
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
