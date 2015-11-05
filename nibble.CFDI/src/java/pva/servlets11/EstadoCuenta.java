package pva.servlets11;

/**
 * Insert the type's description here.
 * Creation date: (06/12/2002 06:00:14 p.m.)
 * @author: 
 */
import org.nibble.main.BeanError;
import org.nibble.main.BeanUtil;
import java.sql.Connection;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;

import pva.beans4.Bean42;
import pva.beans11.Bean113;
import dbbeans.cliente.*;
import dbbeans.ctaxcobrar.*;
import dbbeans.avisoingreso.*;
 
public class EstadoCuenta extends HttpServlet{ 
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
public void performTask(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) {

	Bean42 bean42;
	Bean113 bean113;
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

   		bean113 = new Bean113();
   		
		bean42 = (Bean42)ses_usr.getAttribute("bean42");
		if (bean42 == null || accion ==1){
			bean42 = new Bean42();
   		    dbCLIENTEs clientes = new dbCLIENTEs();
        	clientes.setConnection(con);
	    	clientes.find("");
        	bean42.setVClientes(clientes.getResult());
		}else
			bean113.setIdcliente(new Integer(request.getParameter("idcliente")).intValue());

        switch(accion){
	        case 1: 
	        		bean113.setDetalle(false);
	        		break;
	        case 2:
	        		bean113.setDetalle(true);
					dbCTAXCOBRARs dbcxcs = new dbCTAXCOBRARs();
					dbCLIENTE dbcliente = new dbCLIENTE();
					dbAVISOINGRESOs dbais = new dbAVISOINGRESOs();
					dbcxcs.setConnection(con);
					dbcliente.setConnection(con);
					dbais.setConnection(con);
	        		int cliente = new Integer(request.getParameter("idcliente")).intValue();
	        		dbcliente.setIidnocliente(cliente);
	        		dbcliente.load();
	        		bean113.setCliente(dbcliente);
	        		bean113.setLimite(dbcliente.getDeclimitecredito());
	        		bean113.setDisponible(dbcliente.getDeclimitecredito()-dbcliente.getDeccreditutilizado());
	        		bean113.setIngreso(dbais.findSaldosIngresos(cliente,"",""));
	        		bean113.setCxc(dbcxcs.findByCliente(cliente));
					bean113.setSaldos(dbcxcs.findByClienteFecha(cliente,"","",false));
					break;
        }

		ses_usr.setAttribute("bean42", bean42);
		ses_usr.setAttribute("bean113", bean113);	
		strAddress = "/jsp/11_3.jsp";        

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
