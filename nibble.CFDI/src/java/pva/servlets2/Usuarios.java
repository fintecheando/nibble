package pva.servlets2;

/**
 * Insert the type's description here.
 * Creation date: (24/10/2002 02:15:14 p.m.)
 * @author: 
 */

import org.nibble.main.BeanError;
import org.nibble.main.BeanUtil;
import java.sql.Connection;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;

import dbbeans.usuarios.*;
import dbbeans.privilegiosgrupo.*;
import pva.beans2.Bean22;


public class Usuarios extends HttpServlet{
//main.MainServlet {
	private int idModulo;
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
}
/**
 * Process incoming requests for information
 * 
 * @param request Object that encapsulates the request to the servlet 
 * @param response Object that encapsulates the response from the servlet
 */
public void performTask(javax.servlet.http.HttpServletRequest request,javax.servlet.http.HttpServletResponse response){

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
		
		int accion = new Integer(request.getParameter("accion")).intValue();
    	/*
        initConnection();

  		seguridad.setConnection(con);
	  	if(!seguridad.tienePermiso(sistema,new Integer(ses_usr.getAttribute("grupo").toString()).intValue(),idModulo))throw new Exception("No tiene permiso a este m�dulo");       

        int accion = new Integer(request.getParameter("accion")).intValue();
        */
        dbUSUARIOSs dbus = new dbUSUARIOSs();
        dbus.setConnection(con);        

        dbPRIVILEGIOSGRUPOs dbpg = new dbPRIVILEGIOSGRUPOs();
        dbpg.setConnection(con);

        dbUSUARIOS dbu = new dbUSUARIOS();
        dbu.setConnection(con);

        Bean22 bean = new Bean22();

		switch(accion){
			case 2:  // Altas
					dbu.setIidsistema(bu.getSistema());
					dbu.setVchaliasusuario(request.getParameter("vchaliasusuario"));
					if (!dbu.findByAlias()){
						dbu.setIidgrupo(new Integer(request.getParameter("iidgrupo")).intValue()+1);
						dbu.setVchnombreusuario(request.getParameter("vchnombreusuario"));
						dbu.setVchpasaporte(request.getParameter("vchpasaporte"));
						dbu.setVchdescripcion(request.getParameter("vchdescripcion"));
						dbu.setChactivo("S");
						dbu.create();
					}else throw new Exception("Ya existe un usuario registrado con el mismo identificador\n Intente nuevamente");
					break;
			case 3:  // Cambios
					dbu.setIidsistema(bu.getSistema());
					dbu.setIidusuario(new Integer(request.getParameter("iidusuario")).intValue());
					if (dbu.load()){
						dbu.setIidgrupo(new Integer(request.getParameter("iidgrupo")).intValue()+1);
						dbu.setVchnombreusuario(request.getParameter("vchnombreusuario"));
						dbu.setVchaliasusuario(request.getParameter("vchaliasusuario"));
						dbu.setVchpasaporte(request.getParameter("vchpasaporte"));
						dbu.setVchdescripcion(request.getParameter("vchdescripcion"));						
						if (request.getParameter("habilitado")!= null)
							dbu.setChactivo("S");
						else
							dbu.setChactivo("N");
						dbu.store();
					}
					break;
			case 4:	 // Borrar
					dbu.setIidsistema(bu.getSistema());
					dbu.setIidusuario(new Integer(request.getParameter("iidusuario")).intValue());
					if (dbu.load())
						dbu.remove();
					break;
		}

        bean.setUsuarios(dbus.findBySistema(bu.getSistema()));
	    bean.setGrupos(dbpg.findBySistema(bu.getSistema()));	
		
        ses_usr.setAttribute("bean",bean);
        strAddress = "/jsp/2_2.jsp";

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
