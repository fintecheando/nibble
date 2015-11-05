package pva.servlets8;

import org.nibble.main.BeanError;
import org.nibble.main.BeanUtil;
import java.sql.Connection;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;

import dbbeans.inventario.*;
import dbrbeans.inventario.*;
import pva.beans8.*;
/**
 * DOCUMENT ME!
 *
 * @author $author$
 * @version $Revision$
 */
public class RegistroConsumo extends HttpServlet{ 
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
            dbrINVENTARIOSs inventarios = new dbrINVENTARIOSs();
            dbINVENTARIO inventario = new dbINVENTARIO();

            inventarios.setConnection(con);
            inventario.setConnection(con);

            int consumo;
            Bean83b bean83b = new Bean83b();

            Integer userInt = (Integer) ses_usr.getAttribute("iidusuario");
            int iidusuario = userInt.intValue();

            if (request.getParameter("accion") != null) {
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

            switch (accion) {
                // 8.5 Modificar consumo en ubicaci�n
                case 3:
                    bean83b = (Bean83b) ses_usr.getAttribute("bean83b");

                    for (int i = 0; i < bean83b.getSize(); i++) {
                        consumo = 0;

                        if ((request.getParameter("consumo" + i) != null) && (request.getParameter("consumo" + i).length() > 0)) {
                            consumo = new Integer(request.getParameter("consumo" + i)).intValue();
                        }

                        if (consumo > 0) {
                            inventario.setLiidparte(bean83b.getLiidparte(i));
                            inventario.setIidproveedor(bean83b.getIidproveedor(i));
                            inventario.load();
                            inventario.setIcantconsumo(consumo);
                            inventario.store();
                        }
                    }

                // 8.5 Mostrar resultados de busqueda*/
                case 2:
                    inventarios.findByClasificacion(iidcategoria, iidgrupo, iidlinea, iidmarca);
                    bean83b.setVInventario(inventarios.getResult());
                    ses_usr.setAttribute("bean83b", bean83b);
                    strAddress = "/jsp/8_5b.jsp";

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
