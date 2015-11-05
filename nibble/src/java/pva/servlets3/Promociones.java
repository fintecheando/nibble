package pva.servlets3;

import org.nibble.main.BeanError;
import org.nibble.main.BeanUtil;
import java.sql.Connection;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;

import dbbeans.categoria.*;
import dbbeans.grupo.*;
import dbbeans.linea.*;
import dbbeans.marca.*;
import dbbeans.promocion.*;
import pva.beans3.Bean32;
/**
 * DOCUMENT ME!
 *
 * @author $author$
 * @version $Revision$
 */
public class Promociones extends HttpServlet{
	
	private int idModulo; 
//main.MainServlet {
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

            int iidcategoria = 0;
            int iidgrupo = 0;
            int iidlinea = 0;
            int iidmarca = 0;

/*
            initConnection();
            seguridad.setConnection(con);
            if (!seguridad.tienePermiso(sistema, new Integer(ses_usr.getAttribute("grupo").toString()).intValue(), idModulo)) {
                throw new Exception("No tiene permiso a este m�dulo");
            }
*/
            int accion = new Integer(request.getParameter("accion")).intValue();

            BeanHelper hlp = new BeanHelper();
            Bean32 bean32;
            dbPROMOCION dbP = new dbPROMOCION();
            dbPROMOCIONs dbPs = new dbPROMOCIONs();
            dbCATEGORIA dbC = new dbCATEGORIA();
            dbGRUPO dbG = new dbGRUPO();
            dbLINEA dbL = new dbLINEA();
            dbMARCA dbM = new dbMARCA();

            bean32 = (Bean32) ses_usr.getAttribute("bean32");

            if ((bean32 == null) || (accion == 0)) {
                hlp.getData(con);
                bean32 = hlp.getBean32();
            }

            if (accion >= 1) {
                dbP.setConnection(con);
                dbPs.setConnection(con);
                dbC.setConnection(con);
                dbG.setConnection(con);
                dbL.setConnection(con);
                dbM.setConnection(con);

                if (request.getParameter("categoria") != null) {
                    iidcategoria = new Integer(request.getParameter("categoria")).intValue();
                    dbC.setIidcategoria(iidcategoria);
                    dbC.load();
                    bean32.setIidcategoria(dbC.getVchdescripcion());
                }

                if (request.getParameter("grupo") != null) {
                    iidgrupo = new Integer(request.getParameter("grupo")).intValue();
                    dbG.setIidgrupo(iidgrupo);
                    dbG.load();
                    bean32.setIidgrupo(dbG.getVchdescripcion());
                }

                if (request.getParameter("linea") != null) {
                    iidlinea = new Integer(request.getParameter("linea")).intValue();
                    dbL.setIidlinea(iidlinea);
                    dbL.load();
                    bean32.setIidlinea(dbL.getVchdescripcion());
                }

                if (request.getParameter("marca") != null) {
                    iidmarca = new Integer(request.getParameter("marca")).intValue();
                    dbM.setIidmarca(iidmarca);
                    dbM.load();
                    bean32.setIidmarca(dbM.getVchnombre());
                }

                switch (accion) {
                    case 2: // Altas
                    
					String fecha = org.nibble.util.Fecha.getCurrentDate("yyyyMMddhhmmss");
                        dbP.setIidcategoria(iidcategoria);
                        dbP.setIidgrupo(iidgrupo);
                        dbP.setIidlinea(iidlinea);
                        dbP.setIidmarca(iidmarca);
                        dbP.setDecvolumencompra(new Float(request.getParameter("decvolumencompra")).floatValue());
                        dbP.setIexistencias(new Integer(request.getParameter("iexistencias")).intValue());
                        dbP.setSinopiezas(new Integer(request.getParameter("sinopiezas")).shortValue());
                        dbP.setVchdescripcion(request.getParameter("vchdescripcion"));
                        dbP.setTisfechafin(request.getParameter("tisfechafin"));
                        dbP.setTisfechainicio(request.getParameter("tisfechainicio"));
                        dbP.create();

                        break;

                    case 3: // Cambios
                        
                        dbP.setIidpromocion(new Integer(request.getParameter("iidpromocion")).intValue());

                        if (dbP.load()) {
                            dbP.setDecvolumencompra(new Float(request.getParameter("decvolumencompra")).floatValue());
                            dbP.setIexistencias(new Integer(request.getParameter("iexistencias")).intValue());
                            dbP.setSinopiezas(new Integer(request.getParameter("sinopiezas")).shortValue());
                            dbP.setVchdescripcion(request.getParameter("vchdescripcion"));
                            dbP.setTisfechafin(request.getParameter("tisfechafin"));
                            dbP.setTisfechainicio(request.getParameter("tisfechainicio"));
                            dbP.store();
                        }

                        break;

                    case 4: // Bajas
                        dbP.setIidpromocion(new Integer(request.getParameter("iidpromocion")).intValue());

                        if (dbP.load()) {
                            dbP.remove();
                        }

                        break;
                }

                bean32.setVPromocion(dbPs.findByClasificacion(iidcategoria, iidgrupo, iidlinea, iidmarca));
                ses_usr.setAttribute("bean32", bean32);
                strAddress = "/jsp/3_2a.jsp";
            } else {
                ses_usr.setAttribute("bean32", bean32);
                strAddress = "/jsp/3_2.jsp";
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
