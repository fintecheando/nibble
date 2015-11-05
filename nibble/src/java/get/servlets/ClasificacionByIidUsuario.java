package get.servlets;

/**
 * Insert the type's description here.
 * Creation date: (11/28/2002 11:00:03 AM)
 * @author: 
 */
import java.sql.Connection;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;

import org.nibble.main.BeanError;
import org.nibble.main.BeanUtil;

import get.*;

public class ClasificacionByIidUsuario extends HttpServlet{ 

	private String action;
	private String imagen;
	private String title;
	private String forward;
	private int accion;
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
	action = getServletConfig().getInitParameter("action");
	imagen = getServletConfig().getInitParameter("imagen");
	title = getServletConfig().getInitParameter("title");
	forward = getServletConfig().getInitParameter("forward");
	accion = new Integer(getServletConfig().getInitParameter("accion")).intValue();
}
/**
 * Process incoming requests for information
 * 
 * @param request Object that encapsulates the request to the servlet 
 * @param response Object that encapsulates the response from the servlet
 */
public void performTask(javax.servlet.http.HttpServletRequest request,javax.servlet.http.HttpServletResponse response) {
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

        Integer usuario = (Integer) ses_usr.getAttribute("iidusuario");
        int iidusuario = usuario.intValue();
        
        GetClasificacion bean = new GetClasificacion();
        BeanClasificacion beanClasificacion = bean.getData(con, iidusuario);

        beanClasificacion.setAction(action);
        beanClasificacion.setImagen(imagen);
        beanClasificacion.setTitle(title);
        beanClasificacion.setAccion(accion);

        ses_usr.setAttribute("beanClasificacion", beanClasificacion);
        strAddress = forward;

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
