package org.nibble.security.login;

/**  
 * @author: 
 */
import java.sql.Connection;

import javax.servlet.http.HttpSession;

import org.nibble.main.BeanUtil;

import org.apache.log4j.Logger;

import org.nibble.util.Menu;
import dbbeans.menu.dbMENUs;
import dbbeans.privilegiosgrupo.dbPRIVILEGIOSGRUPO;
import dbbeans.usuarios.dbUSUARIOS;
 
public class Login extends javax.servlet.http.HttpServlet {
	 private int idSistema;	 	 
      static Logger logger= Logger.getLogger(Login.class);
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
 * Returns the servlet info string.
 */
public String getServletInfo() {

	return super.getServletInfo();

}
/**
 * Initializes the servlet.
 */
public void init() {
	try {
		idSistema	= new Integer(getServletConfig().getInitParameter("sistema")).intValue();    		
	} catch (Exception e) {
		e.printStackTrace();
	}	
}
/**
 * Process incoming requests for information
 * 
 * @param request Object that encapsulates the request to the servlet 
 * @param response Object that encapsulates the response from the servlet
 */
public void performTask(javax.servlet.http.HttpServletRequest request,javax.servlet.http.HttpServletResponse response) {
	
    
    dbUSUARIOS usuario = new dbUSUARIOS();
    dbPRIVILEGIOSGRUPO grupo = new dbPRIVILEGIOSGRUPO();
    dbMENUs menus = new dbMENUs();
    Menu menu = new Menu();
	Connection con = null;
	BeanUtil bu = null;
    logger.debug("login");
    try {

		bu = new BeanUtil();
		con = bu.getConn();
		
        String user = request.getParameter("user").trim();
        String password = request.getParameter("password").trim();
        usuario.setConnection(con);
        grupo.setConnection(con);
        menus.setConnection(con);
        
        if (usuario.findByUserPassword(user, password, idSistema)) {
	        grupo.setIidgrupo(usuario.getIidgrupo());
	        grupo.setIidsistema(idSistema);
	        grupo.load();
	        menu.setOpciones(menus.findByGrupo(usuario.getIidgrupo(),idSistema));
            HttpSession sesion = request.getSession(true);
            sesion.setMaxInactiveInterval(3000); // 5 minutos por default
            sesion.setAttribute("iidusuario", new Integer(usuario.getIidusuario()));
            sesion.setAttribute("username", user);
            sesion.setAttribute("grupo", new Integer(usuario.getIidgrupo()));
            sesion.setAttribute("color", grupo.getVchcolor());
            
            sesion.setAttribute("menualterno", menu.headerToString());
            //sesion.setAttribute("menu", menu.toString());
            sesion.setAttribute("sistema",new Integer(idSistema));
            response.sendRedirect("main.jsp");
        } else
            response.sendRedirect("index.html");
    } catch (Throwable e) {
	    try{
		    response.sendRedirect("index.html");
	    }catch(java.io.IOException ioe){}
        e.printStackTrace();
    } finally {
    	try{
			bu.finalizeTransaction(con);			
    	}catch(Exception e){
    		e.printStackTrace();
    	}
        	
    }
}
}
