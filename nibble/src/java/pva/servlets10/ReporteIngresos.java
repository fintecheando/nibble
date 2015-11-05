package pva.servlets10;

/**
 * Insert the type's description here.
 * Creation date: (06/12/2002 12:07:00 p.m.)
 * @author: 
 */
import org.nibble.main.BeanError;
import org.nibble.main.BeanUtil;
import java.sql.Connection;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;

import dbbeans.ctaxcobrar.*;
import pva.beans10.Bean103;

 
public class ReporteIngresos extends HttpServlet{ 
//main.MainServlet {

	private int idModulo;
	private int idtipopago;	
/**
 * Process incoming HTTP GET requests 
 * 
 * @param request Object that encapsulates the request to the servlet 
 * @param response Object that encapsulates the response from the servlet
 */
public void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, java.io.IOException {

	performTask(request, response);

}
/**
 * Process incoming HTTP POST requests 
 * 
 * @param request Object that encapsulates the request to the servlet 
 * @param response Object that encapsulates the response from the servlet
 */
public void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, java.io.IOException {

	performTask(request, response);

}
/**
 * Initializes the servlet.
 */
public void init() {
	idModulo = (getServletConfig().getInitParameter("idModulo") != null)
					? new Integer(getServletConfig().getInitParameter("idModulo")).intValue() : (-1);

	idtipopago = new Integer(getServletConfig().getInitParameter("idtipopago")).intValue();
}
/**
 * Process incoming requests for information
 * 
 * @param request Object that encapsulates the request to the servlet 
 * @param response Object that encapsulates the response from the servlet
 */
public void performTask(javax.servlet.http.HttpServletRequest request,javax.servlet.http.HttpServletResponse response) {

	Bean103 bean103 = new Bean103();
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
	  	if(!seguridad.tienePermiso(sistema,new Integer(ses_usr.getAttribute("grupo").toString()).intValue(),idModulo))throw new Exception("No tiene permiso a este m�dulo");
*/
        if (request.getParameter("accion") != null)
            accion = new Integer(request.getParameter("accion")).intValue();

        dbCTAXCOBRARs dbcxcs = new dbCTAXCOBRARs();
        dbcxcs.setConnection(con);

        switch (accion){
            case 1 :
                bean103.setDetalle(false);
          		bean103.setFecha(org.nibble.util.Fecha.getCurrentDate("dd/MM/yyyy"));
                break;
            case 2 :
            	bean103.setFecha(request.getParameter("fecha"));
                String fecha = org.nibble.util.Fecha.formatToMySQL(request.getParameter("fecha"));
                bean103.setCxc(
	                	dbcxcs.findByFechaTipoDoc(
		                	fecha+"000000", 
		                	fecha+"235959", 
		                	idtipopago
		                )
	            );
                bean103.setDetalle(true);
                break;
        }
        
      	ses_usr.setAttribute("bean103", bean103);
        strAddress = "/jsp/10_3.jsp";

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
