package pva.servlets11;

import org.nibble.main.BeanError;
import org.nibble.main.BeanUtil;
import java.sql.Connection;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;

import dbbeans.cliente.*;
import dbbeans.ctaxcobrar.*;
import pva.beans11.Bean113;
import pva.beans11.Bean114;
import pva.beans11.BeanHelper114;
import java.util.Vector;


/**
 * DOCUMENT ME!
 *
 * @author $author$
 * @version $Revision$
 */
public class AntiguedadSaldos extends HttpServlet{
//main.MainServlet {
	private int idModulo;
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
    }

    /**
     * Process incoming requests for information
     *
     * @param request Object that encapsulates the request to the servlet
     * @param response Object that encapsulates the response from the servlet
     */
    public void performTask(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) {
        Bean113 bean113;
        Bean114 bean114;
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

            bean113 = new Bean113();
            bean114 = new Bean114();
            bean114.setFecha(org.nibble.util.Fecha.getCurrentDate());

            dbCLIENTEs clientes = new dbCLIENTEs();
            clientes.setConnection(con);
            clientes.find("");

            Vector vClientes = clientes.getResult();
            Vector vRes = new Vector();

            dbCTAXCOBRARs dbcxcs = new dbCTAXCOBRARs();
            dbcxcs.setConnection(con);

            for (int i = 0; i < vClientes.size(); i++) {
                BeanHelper114 bh114 = new BeanHelper114();
                dbCLIENTE dbc = (dbCLIENTE) vClientes.elementAt(i);
                int cliente = dbc.getIidnocliente();
                String nombre = dbc.getVchrazonsocial();
                bean113.setSaldos(dbcxcs.findByClienteFecha(cliente, "", "", false));

                bh114.setIdcliente(cliente);
                bh114.setNombreCliente(nombre);
                bh114.setSxvencer(bean113.getDSxvencer());
                bh114.setSx1_7(bean113.getDSx1_7());
                bh114.setSx8_29(bean113.getDSx8_29());
                bh114.setSx30_45(bean113.getDSx30_45());
                bh114.setSx46_60(bean113.getDSx46_60());
                bh114.setSx61(bean113.getDSx61());
                vRes.addElement(bh114);
            }

            bean114.setVct(vRes);

            ses_usr.setAttribute("bean114", bean114);
            strAddress = "/jsp/11_4.jsp";
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
