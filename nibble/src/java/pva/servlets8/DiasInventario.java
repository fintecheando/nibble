package pva.servlets8;

import org.nibble.main.BeanError;
import org.nibble.main.BeanUtil;
import java.sql.Connection;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;

import dbbeans.criteriopedido.*;
import pva.beans8.*;
/**
 * DOCUMENT ME!
 *
 * @author $author$
 * @version $Revision$
 */
public class DiasInventario extends HttpServlet{ 
//main.MainServlet {
	private int idModulo;
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

            int accion = 1;
/*
            initConnection();
            seguridad.setConnection(con);
            if (!seguridad.tienePermiso(sistema, new Integer(ses_usr.getAttribute("grupo").toString()).intValue(), idModulo)) {
                throw new Exception("No tiene permiso a este m�dulo");
            }
*/
            dbCRITERIOPEDIDO criteriopedido = new dbCRITERIOPEDIDO();
            Bean86 bean86 = new Bean86();

            criteriopedido.setConnection(con);

            if ((request.getParameter("accion") != null) && (request.getParameter("accion").length() > 0)) {
                accion = new Integer(request.getParameter("accion")).intValue();
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

            int nodiasinventario = 0;

            if ((request.getParameter("nodiasinventario") != null) && (request.getParameter("nodiasinventario").length() > 0)) {
                nodiasinventario = new Integer(request.getParameter("nodiasinventario")).intValue();
            }

            criteriopedido.setIidcategoria(iidcategoria);
            criteriopedido.setIidgrupo(iidgrupo);
            criteriopedido.setIidlinea(iidlinea);
            criteriopedido.setIidmarca(iidmarca);

            switch (accion) {
                case 2:

                    try {
                        criteriopedido.load();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    bean86.setInodiasinventario(criteriopedido.getInodiasinventario());
                    ses_usr.setAttribute("bean86", bean86);
                    strAddress = "/jsp/8_6b.jsp";

                    break;

                case 3:
                    criteriopedido.setInodiasinventario(nodiasinventario);

                    try {
                        criteriopedido.create();
                    } catch (Exception e) {
                        criteriopedido.store();
                    }

                    bean86.setInodiasinventario(criteriopedido.getInodiasinventario());
                    ses_usr.setAttribute("bean86", bean86);
                    strAddress = "/jsp/8_6b.jsp";

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
