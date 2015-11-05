package pva.servlets8;

import org.nibble.main.BeanError;
import org.nibble.main.BeanUtil;
import java.sql.Connection;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;

import dbrbeans.movimieninventario.*;
import pva.beans8.*;
/**
 * DOCUMENT ME!
 *
 * @author $author$
 * @version $Revision$
 */
public class ConsultaMovimientosInventario extends HttpServlet{
	
	private int idModulo; 
//extends main.MainServlet {
    /**
     * DOCUMENT ME!
     *
     * @param request DOCUMENT ME!
     * @param response DOCUMENT ME!
     *
     * @throws javax.servlet.ServletException DOCUMENT ME!
     * @throws java.io.IOException DOCUMENT ME!
     */
    public void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response)
            throws javax.servlet.ServletException, java.io.IOException {
        performTask(request, response);
    }

    /**
     * DOCUMENT ME!
     *
     * @param request DOCUMENT ME!
     * @param response DOCUMENT ME!
     *
     * @throws javax.servlet.ServletException DOCUMENT ME!
     * @throws java.io.IOException DOCUMENT ME!
     */
    public void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response)
            throws javax.servlet.ServletException, java.io.IOException {
        performTask(request, response);
    }

    /**
     * DOCUMENT ME!
     */
    public void init() {
		idModulo = (getServletConfig().getInitParameter("idModulo") != null)
						? new Integer(getServletConfig().getInitParameter("idModulo")).intValue() : (-1);
    }

    /**
     * DOCUMENT ME!
     *
     * @param request DOCUMENT ME!
     * @param response DOCUMENT ME!
     */
    public void performTask(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) {

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

			String fechaini = new String();
			String fechafin = new String();      	
            int accion = 1;
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

            int titipomov = 0;

            if ((request.getParameter("titipomov") != null) && (request.getParameter("titipomov").length() > 0)) {
                titipomov = new Integer(request.getParameter("titipomov")).intValue();
            }

            int iidcategoria = 0;

            if ((request.getParameter("iidcategoria") != null) && (request.getParameter("iidcategoria").length() > 0)) {
                iidcategoria = new Integer(request.getParameter("iidcategoria")).intValue();
            }

            int iidgrupo = 0;

            if ((request.getParameter("iidgrupo") != null) && (request.getParameter("iidgrupo").length() > 0)) {
                iidgrupo = new Integer(request.getParameter("iidgrupo")).intValue();
            }

            int iidlinea = 0;

            if ((request.getParameter("iidlinea") != null) && (request.getParameter("iidlinea").length() > 0)) {
                iidlinea = new Integer(request.getParameter("iidlinea")).intValue();
            }

            int iidmarca = 0;

            if ((request.getParameter("iidmarca") != null) && (request.getParameter("iidmarca").length() > 0)) {
                iidmarca = new Integer(request.getParameter("iidmarca")).intValue();
            }

            switch (accion) {
                // 8.2 Criterios de busqueda
                case 1:

                    BeanHelper82 hlp82 = new BeanHelper82();
                    hlp82.getData(con);
                    ses_usr.setAttribute("bean82", hlp82.getBean82());
                    strAddress = "/jsp/8_2.jsp";

                    break;

                // 8.2 Mostrar resultados de busqueda
                case 2:

                    if ((request.getParameter("fechaini") != null) && (request.getParameter("fechaini").length() > 0)) {
                        fechaini = org.nibble.util.Fecha.formatToMySQL(request.getParameter("fechaini")) + "000000";
                        fechafin = org.nibble.util.Fecha.formatToMySQL(request.getParameter("fechafin")) + "235959";
                    }

                    dbrMOVIMIENINVENTARIOs dbrMovimientoinventario = new dbrMOVIMIENINVENTARIOs();
                    dbrMovimientoinventario.setConnection(con);
                    dbrMovimientoinventario.findByPar(
                        fechaini, fechafin, titipomov, request.getParameter("prefijo"), request.getParameter("numero"),
                        request.getParameter("sufijo"), request.getParameter("medida"), iidcategoria, iidgrupo, iidlinea, iidmarca);

                    Bean82b bean82b = new Bean82b();
                    bean82b.setVMovimien(dbrMovimientoinventario.getResult());
                    ses_usr.setAttribute("bean82b", bean82b);
                    strAddress = "/jsp/8_2b.jsp";
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
