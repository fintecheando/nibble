package pva.servlets10;

/**
 * Insert the type's description here.
 * Creation date: (12/12/2002 07:34:56 p.m.)
 * @author: 
 */
import org.nibble.vo.factura.FacturaVO;
import org.nibble.main.BeanError;
import org.nibble.main.BeanUtil;
import java.sql.Connection;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;

import org.nibble.dao.factura.*;
import dbbeans.notadecredito.*;
import dbbeans.notadecargo.*;
import pva.beans10.Bean102;
 
public class CorteCaja extends HttpServlet { 
//main.MainServlet {

	private int idModulo;
	private	int tipomovbon;
	private	int tipomovdev;
	private	int tipomovcan;		
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
	tipomovbon = new Integer(getServletConfig().getInitParameter("tipomovbon")).intValue();	
	tipomovdev = new Integer(getServletConfig().getInitParameter("tipomovdev")).intValue();
	tipomovcan = new Integer(getServletConfig().getInitParameter("tipomovcan")).intValue();
}
/**
 * Process incoming requests for information
 * 
 * @param request Object that encapsulates the request to the servlet 
 * @param response Object that encapsulates the response from the servlet
 */
public void performTask(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) {

	Bean102 bean102 = new Bean102();
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

        FacturaVO dbfacs = new FacturaVO();
        dbNOTADECREDITOs dbncs = new dbNOTADECREDITOs();
        dbNOTADECARGOs dbncas = new dbNOTADECARGOs();
        
        dbfacs.setConnection(con);
        dbncs.setConnection(con);
		dbncas.setConnection(con);
		
        switch (accion){
            case 1 :
                bean102.setDetalle(false);
          		bean102.setFecha(org.nibble.util.Fecha.getCurrentDate("dd/MM/yyyy"));
                break;
            case 2 :
            	bean102.setFecha(request.getParameter("fecha"));
                String fecha = org.nibble.util.Fecha.formatToMySQL(request.getParameter("fecha"));
	            bean102.setDetalle(true);
				// Ventas de Contado
				bean102.setContado(dbfacs.findByFecha(fecha+"000000",fecha+"235959",false));
				// Ventas de Credito	
				bean102.setCredito(dbfacs.findByFecha(fecha+"000000",fecha+"235959",true));
				// Devoluciones
				bean102.setDevolucion(dbncs.findByFechaTipo(fecha+"000000",fecha+"235959",tipomovdev));
				// Cancelaciones
				bean102.setCancelacion(dbncs.findByFechaTipo(fecha+"000000",fecha+"235959",tipomovcan));
				// Bonificaciones
				bean102.setBonificacion(dbncs.findByFechaTipo(fecha+"000000",fecha+"235959",tipomovbon));
				// Cargos
				bean102.setCargo(dbncas.findByFecha(fecha+"000000",fecha+"235959"));
                break;
        }
        
        float TotalImporte = 0;
        TotalImporte = TotalImporte + bean102.getDecimporte(1,2);
        
        
		ses_usr.setAttribute("bean102",bean102);
        strAddress = "/jsp/10_2.jsp";
        
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
