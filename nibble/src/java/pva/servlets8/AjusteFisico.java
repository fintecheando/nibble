package pva.servlets8;


import org.nibble.main.BeanError;
import org.nibble.main.BeanUtil;
import java.sql.Connection;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;

import dbbeans.inventario.*;
import dbbeans.movimieninventario.*;
import dbrbeans.inventario.*;
import pva.beans8.*;
/**
 * DOCUMENT ME!
 *
 * @author $author$
 * @version $Revision$
 */
public class AjusteFisico extends HttpServlet{ 
//main.MainServlet {
    
	private int idModulo;
    private int ID_MOV_ENTRADAS_AJUSTE;
	private int ID_MOV_SALIDAS_AJUSTE;

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
        ID_MOV_ENTRADAS_AJUSTE = new Short(getServletConfig().getInitParameter("ID_MOV_ENTRADAS_AJUSTE")).shortValue();
        ID_MOV_SALIDAS_AJUSTE = new Short(getServletConfig().getInitParameter("ID_MOV_SALIDAS_AJUSTE")).shortValue();
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

            int accion = 1;
//            initConnection();

            dbrINVENTARIOSs inventarios = new dbrINVENTARIOSs();
            dbINVENTARIO inventario = new dbINVENTARIO();
            dbMOVIMIENINVENTARIO movimiento = new dbMOVIMIENINVENTARIO();

            inventarios.setConnection(con);
            inventario.setConnection(con);
            movimiento.setConnection(con);

            String hoy = org.nibble.util.Fecha.getCurrentDate("yyyyMMddhhmmss");
            String ubicacion;
            int existencia;
            int cantidad;
            int tipo;
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
                // 8.3 Modificar la ubicacion y la existencia;
                case 3:
                    bean83b = (Bean83b) ses_usr.getAttribute("bean83b");

                    for (int i = 0; i < bean83b.getSize(); i++) {
                        ubicacion = null;
                        existencia = -1;

                        if ((request.getParameter("ubicacion" + i) != null) && (request.getParameter("ubicacion" + i).length() > 0)) {
                            ubicacion = request.getParameter("ubicacion" + i);
                        }

                        if (
                            (request.getParameter("existencia" + i) != null)
                                && (request.getParameter("existencia" + i).length() > 0)) {
                            existencia = new Integer(request.getParameter("existencia" + i)).intValue();
                        }

                        inventario.setLiidparte(bean83b.getLiidparte(i));
                        inventario.setIidproveedor(bean83b.getIidproveedor(i));
                        inventario.load();

                        if (ubicacion != null) {
                            inventario.setVchubicacion(ubicacion);
                        }

                        if (existencia > -1) {
                            inventario.setIexistencia(existencia);

                            cantidad = bean83b.getIexistencia(i) - existencia;

                            if (cantidad > 0) {
                                tipo = ID_MOV_SALIDAS_AJUSTE;
                            } else {
                                tipo = ID_MOV_ENTRADAS_AJUSTE;
                                cantidad *= -1;
                            }

                            // Crea un movimiento de inventario para la SALIDA O ENTRADA DEL AJUSTE
                            movimiento.setLiidparte(bean83b.getLiidparte(i));
                            movimiento.setIidproveedor(bean83b.getIidproveedor(i));
                            movimiento.setTitipomov(tipo);
                            movimiento.setTisfechamov(hoy);
                            movimiento.setIidusuario(iidusuario);
                            movimiento.setIcantidad(cantidad);
                            movimiento.create();
                        }

                        inventario.store();
                    }

                // 8.3 Mostrar resultados de busqueda
                case 2:
                    inventarios.findByClasificacion(iidcategoria, iidgrupo, iidlinea, iidmarca);
                    bean83b.setVInventario(inventarios.getResult());
                    ses_usr.setAttribute("bean83b", bean83b);
                    strAddress = "/jsp/8_3b.jsp";

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
