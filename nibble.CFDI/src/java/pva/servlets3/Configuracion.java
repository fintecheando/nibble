package pva.servlets3;


/**
 * Insert the type's description here.
 * Creation date: (25/10/2002 05:04:44 p.m.)
 * @author:
 */
import dbbeans.configuracionpva.dbCONFIGURACIONPVA;
import org.nibble.main.BeanError;
import org.nibble.main.BeanUtil;
import java.sql.Connection;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;



/**
 * DOCUMENT ME!
 *
 * @author $author$
 * @version $Revision$
 */
public class Configuracion extends HttpServlet {
    //main.MainServlet {

    private int idModulo;
    private int idconfpva;

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
        idconfpva = new Integer(getServletConfig().getInitParameter("confpva")).intValue();
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
		
			if (!bu.getSessionValues())throw new Exception("Por motivos de seguridad la sesion ha expirado\n Registrese nuevamente");
			if (!bu.getAcceso(idModulo,con))throw new Exception("No tiene permiso a este modulo");

            int accion = 0;
            int inumerofactura = 0;
            int inumerocredito = 0;
			int inumerocargo = 0;

            dbCONFIGURACIONPVA configuracionpva = new dbCONFIGURACIONPVA();
            configuracionpva.setConnection(con);

            if (request.getParameter("accion") != null) {
                accion = new Integer(request.getParameter("accion")).intValue();
            }

            configuracionpva.setIidconfpva(idconfpva);

            if (configuracionpva.load()) {
                switch (accion) {
                    case 2: //Modifica

                        String vchserie = request.getParameter("vchserie");
                        //String vchnombrepva = request.getParameter("vchnombrepva");
                        inumerofactura = new Integer(request.getParameter("inumerofactura")).intValue();
                        inumerocredito = new Integer(request.getParameter("inumerocredito")).intValue();
                        inumerocargo = new Integer(request.getParameter("inumerocargo")).intValue();

                        String vchrfc  = request.getParameter("vchrfc");
                        String vchrazonsocial  = request.getParameter("vchrazonsocial");
                        String vchcalle  = request.getParameter("vchcalle");
                        String vchnumexterior  = request.getParameter("vchnumexterior");
                        String vchnuminterior  = request.getParameter("vchnuminterior");
                        String vchcolonia  = request.getParameter("vchcolonia");
                        String vchlocalidad  = request.getParameter("vchlocalidad");
                        String vchmunicipio  = request.getParameter("vchmunicipio");
                        String vchestado  = request.getParameter("vchestado");
                        String vchpais  = request.getParameter("vchpais");
                        String vchcodigopostal  = request.getParameter("vchcodigopostal");
                        int ianoaprobacion = new Integer(request.getParameter("ianoaprobacion")).intValue();
                        int inumaprobacion = new Integer(request.getParameter("inumaprobacion")).intValue();
                        
                        //configuracionpva.setVchnombrepva(vchnombrepva);
                        configuracionpva.setVchserie(vchserie);
                        configuracionpva.setInumerofactura(inumerofactura);
                        configuracionpva.setInumeronc(inumerocredito);
                        configuracionpva.setInumeronca(inumerocargo);

                        configuracionpva.setVchrfc(vchrfc );
                        configuracionpva.setVchrazonsocial(vchrazonsocial );
                        configuracionpva.setVchcalle(vchcalle );
                        configuracionpva.setVchnumexterior(vchnumexterior );
                        configuracionpva.setVchnuminterior(vchnuminterior );
                        configuracionpva.setVchcolonia(vchcolonia);
                        configuracionpva.setVchlocalidad(vchlocalidad );
                        configuracionpva.setVchmunicipio(vchmunicipio );
                        configuracionpva.setVchestado(vchestado );
                        configuracionpva.setVchpais(vchpais );
                        configuracionpva.setVchcodigopostal(vchcodigopostal );
                        configuracionpva.setInumaprobacion(inumaprobacion );
                        configuracionpva.setIanoaprobacion(ianoaprobacion );

                        configuracionpva.store();

                        break;
                }
            } else {
                throw new Exception("Problema con la configuracion del sistema");
            }

            request.setAttribute("bean", configuracionpva);
            strAddress = "/jsp/3_1.jsp";
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
