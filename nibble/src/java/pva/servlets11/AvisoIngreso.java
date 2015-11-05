package pva.servlets11;

/**
 * Insert the type's description here.
 * Creation date: (10/12/2002 07:43:03 p.m.)
 * @author: 
 */

import org.nibble.main.BeanError;
import org.nibble.main.BeanUtil;
import java.sql.Connection;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;

import pva.beans4.Bean42;
import dbbeans.cliente.*;
import dbbeans.avisoingreso.*;
 
public class AvisoIngreso extends HttpServlet { 
//main.MainServlet {
	private int idModulo;
	private int idingreso;
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
	idingreso = new Integer(getServletConfig().getInitParameter("idingreso")).intValue();	
}
/**
 * Process incoming requests for information
 * 
 * @param request Object that encapsulates the request to the servlet 
 * @param response Object that encapsulates the response from the servlet
 */
public void performTask(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) {
	Bean42 bean42;

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

        if (request.getParameter("accion") != null)
            accion = new Integer(request.getParameter("accion")).intValue();
                  
		bean42 = (Bean42)ses_usr.getAttribute("bean42");

		if (bean42 == null || accion ==1){
			bean42 = new Bean42();
   		    dbCLIENTEs clientes = new dbCLIENTEs();
        	clientes.setConnection(con);
	    	clientes.find("");
        	bean42.setVClientes(clientes.getResult());
		}
		
		switch(accion){
			case 1: // Indica cliente muestra pantalla captura
					break;
			case 2:
					// Graba registro de aviso de ingreso
				dbAVISOINGRESO dbai = new dbAVISOINGRESO();
				dbai.setConnection(con);
				String fecha = org.nibble.util.Fecha.getCurrentDate("yyyyMMddHHmmss");

				int titipopago = new Integer(request.getParameter("tipopago")).intValue();

				dbai.setIidnocliente(new Integer(request.getParameter("idcliente")).intValue());					
				dbai.setIidusuario(((Integer)ses_usr.getAttribute("iidusuario")).intValue());
				dbai.setIidmodulo(bu.getSistema());
				dbai.setTisfecha(fecha);
				dbai.setTitipopago(titipopago);
				dbai.setDecdebe(new Float(request.getParameter("importe")).floatValue());
				dbai.setDechaber(0);
				dbai.setVchconcepto(request.getParameter("concepto"));
				dbai.create();
				
				if (dbai.findIidavisoingreso())
					bean42.setMensaje("Se genero el Aviso de Ingreso No."+dbai.getIidavisoingreso());
				
				/**
				 * No debe generar registro en cta x cobrar
				 * 24 / 01 / 2004
				 * 
				 * Solo al momento de ser aplicado a las facturas
				 * o notas de cargo, tampoco actualizar Saldo Cliente
				 
				dbCTAXCOBRAR dbcxc = new dbCTAXCOBRAR();					
				dbcxc.setConnection(con);
				
				if (dbai.findIidavisoingreso()){					
					dbcxc.setIiddoctoorigen(dbai.getIidavisoingreso());
				    dbcxc.setTiconsecsubdocto(1);
				    dbcxc.setTiidtipodocto(idingreso);
				    dbcxc.setIidnocliente(new Integer(request.getParameter("idcliente")).intValue());
					dbcxc.setTisfechamovimiento(fecha);
					// Se fijo el numero de contradocto para que
					// no aparezca como una cuenta x cobrar
					dbcxc.setIidcontradocto(-1);
					dbcxc.setTiidtipocontradoc(0);
					dbcxc.setDechaber(new Float(request.getParameter("importe")).floatValue());
					dbcxc.create();
				}
				
				
				// Actualiza credito cliente
				dbCLIENTE dbcliente = new dbCLIENTE();
				dbcliente.setConnection(con);
				
				dbcliente.setIidnocliente(new Integer(request.getParameter("idcliente")).intValue());
				dbcliente.load();
     			dbcliente.setDeccreditutilizado(dbcliente.getDeccreditutilizado() - new Float(request.getParameter("importe")).floatValue());
		    	dbcliente.store();
				*/	
				break;
		}

		ses_usr.setAttribute("bean42", bean42);
//		ses_usr.setAttribute("bean115",bean115);		
		strAddress = "/jsp/11_5.jsp";

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
