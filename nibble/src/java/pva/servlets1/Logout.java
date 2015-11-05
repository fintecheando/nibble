package pva.servlets1;

/**
 * Insert the type's description here. 
 */

import javax.servlet.http.*;
 
public class Logout extends javax.servlet.http.HttpServlet {
/**
 * Process incoming HTTP GET requests 
 * 
 * @param request Object that encapsulates the request to the servlet 
 * @param response Object that encapsulates the response from the servlet
 * @exception javax.servlet.ServletException.
 * @exception java.io.IOException 
 */
public void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, java.io.IOException {

	performTask(request, response);

}
/**
 * Process incoming HTTP POST requests 
 * 
 * @param request Object that encapsulates the request to the servlet 
 * @param response Object that encapsulates the response from the servlet
 * @exception javax.servlet.ServletException.
 * @exception java.io.IOException 
 */
public void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, java.io.IOException {

	performTask(request, response);

}
/**
 * Initializes the servlet.
 
 */
public void init() {
	// insert code to initialize the servlet here

}
/**
 * Process incoming requests for information
 * 
 * @param request Object that encapsulates the request to the servlet 
 * @param response Object that encapsulates the response from the servlet 
 */
public void performTask(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) {

	try
	{

		System.out.println("En el logout");
		
		HttpSession ses = request.getSession(false);

		if (ses != null){
			ses.invalidate();
		}	else {
			System.out.println("Sin sesion");
		}
		response.sendRedirect("index.html");	
	}
	catch(Throwable e)
	{
		e.printStackTrace();
	}
}
}
