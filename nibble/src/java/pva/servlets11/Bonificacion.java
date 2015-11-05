package pva.servlets11;

/**
 * Insert the type's description here.
 * Creation date: (22/11/2002 06:08:30 p.m.)
 * @author: 
 */
import org.nibble.main.BeanError;
import org.nibble.main.BeanUtil;
import java.sql.Connection;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;

import pva.beans11.Bean111;
import pva.beans11.Bean111b;
import dbbeans.cliente.dbCLIENTE;
import dbbeans.configsistema.*;
import dbbeans.notadecredito.*;
import dbbeans.ctaxcobrar.*;
import dbbeans.configuracionpva.*;
 
public class Bonificacion extends HttpServlet{ 
//main.MainServlet {
	private int idModulo;
	private int idnotacredito;
	private int idconfsistema;
	private int idtiponc;
	private int idconfigpva;
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
	idconfsistema = new Integer(getServletConfig().getInitParameter("confid")).intValue();	
	idnotacredito = new Integer(getServletConfig().getInitParameter("idnotacredito")).intValue();
	idtiponc	  = new Integer(getServletConfig().getInitParameter("idtiponc")).intValue();
	idconfigpva	  = new Integer(getServletConfig().getInitParameter("idconfigpva")).intValue();
}
/**
 * Process incoming requests for information
 * 
 * @param request Object that encapsulates the request to the servlet 
 * @param response Object that encapsulates the response from the servlet
 */
public void performTask(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) {
	Bean111 bean111;
	dbCTAXCOBRAR dbcxc;
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
		if (!bu.getAcceso(idModulo,con))throw new Exception("No tiene permiso a este m�dulo");	    int accion = 0;
	    int tipofactura = 1;
	    int idfactura = 0;

        if (request.getParameter("accion") != null)
        	accion = new Integer(request.getParameter("accion")).intValue();
        
		dbCONFIGSISTEMA dbcs = new dbCONFIGSISTEMA();
		dbcs.setConnection(con);
		dbcs.setIidconfsistema(idconfsistema);
		dbcs.load();

		bean111 = new Bean111();
		float ivaap = dbcs.getDecivaaplicable();

		switch(accion){
			case 1:
					dbcxc = new dbCTAXCOBRAR();
					dbcxc.setConnection(con);
					idfactura = new Integer(request.getParameter("idfactura")).intValue();
		            // Encuentra factura en cuenta x cobrar y obtengo saldo
					dbcxc.setIiddoctoorigen(idfactura);
					dbcxc.setTiconsecsubdocto(1);
					dbcxc.setTiidtipodocto(tipofactura);
					if (dbcxc.load()) {
						bean111.setFactura(idfactura);
						bean111.setHaber(dbcxc.getDechaber());
						bean111.setDebe(dbcxc.getDecdebe());
						bean111.setTisfechamovimiento(dbcxc.getTisfechamovimiento());
						bean111.setTisfechavencimient(dbcxc.getTisfechavencimient());
					}
			  		strAddress = "/jsp/11_1.jsp";
					break;
			case 2: // Guarda Nota de Credito y actualiza Cta x Cobrar
			
					int idcliente = new Integer(request.getParameter("idcliente")).intValue();
			        
					String fecha = org.nibble.util.Fecha.getCurrentDate("yyyyMMddHHmmss");
					dbCLIENTE dbcliente = new dbCLIENTE();
					dbNOTADECREDITO dbnc = new dbNOTADECREDITO();
					dbCONFIGURACIONPVA configuracionpva = new dbCONFIGURACIONPVA();
					
					dbcliente.setConnection(con); 
					dbnc.setConnection(con);
				    configuracionpva.setConnection(con);

					//Inserta nota de credito			
					idfactura = new Integer(request.getParameter("idfactura")).intValue();

					// Obtiene de la configuracion el numero de nota de credito
				    configuracionpva.setIidconfpva(idconfigpva);
				    configuracionpva.load();
				    int notadecredito = configuracionpva.getInumeronc();			

				    dbnc.setIidnotacredito(notadecredito);
					dbnc.setIidnocliente(idcliente);
					dbnc.setIidusuario(((Integer)ses_usr.getAttribute("iidusuario")).intValue());
					dbnc.setIidfactura(idfactura);
					dbnc.setTisfechanotacredit(fecha);
					dbnc.setVchconcepto(request.getParameter("concepto"));
					float imp = new Float(request.getParameter("total")).floatValue();
					float iva = imp - (imp / (1+(ivaap/100)));
					dbnc.setTitipomov(idtiponc);
					dbnc.setDecimporte(imp-iva);
					dbnc.setDecivaimporte(iva);
					dbnc.create();

					//Obtiene el id de la nota de credito y
					//guarda el registro en cxc				
					dbcxc = new dbCTAXCOBRAR();
					dbcxc.setConnection(con);
					dbcxc.setIiddoctoorigen(notadecredito);
				    dbcxc.setTiconsecsubdocto(1);
				    int idbonificacion = 6;
				    dbcxc.setTiidtipodocto(idbonificacion);
				    dbcxc.setIidnocliente(new Integer(request.getParameter("idcliente")).intValue());
					dbcxc.setTisfechamovimiento(fecha);
					dbcxc.setIidcontradocto(idfactura);
					dbcxc.setTiidtipocontradoc(tipofactura);
					dbcxc.setDechaber(imp);
					dbcxc.create();

		            // actualiza folio en configuracionpva
  			        configuracionpva.setInumeronc(notadecredito + 1);
            		configuracionpva.store();					

		            // Actualiza haber docto.origen
					dbcxc.setIiddoctoorigen(idfactura);
					dbcxc.setTiconsecsubdocto(1);
					dbcxc.setTiidtipodocto(tipofactura);							
					if (dbcxc.load()) {
						float haber = dbcxc.getDechaber()+imp;
						dbcxc.setDechaber(haber);
						String nombre = "saldado"+idfactura;  
						dbcxc.setBitmovsaldado(new Integer(request.getParameter(nombre)).intValue());
						dbcxc.store();
					}
					
				    dbcliente.setIidnocliente(idcliente);
					dbcliente.load();
					dbcliente.setDeccreditutilizado(dbcliente.getDeccreditutilizado() - imp);
					dbcliente.store();
                    
                    Bean111b bean111b= new Bean111b();
                    bean111b.setDbcliente(dbcliente);
                    bean111b.setDbnc(dbnc);
                    bean111b.setIva(ivaap);
                    bean111b.setPopImprimir(true);
                    bean111b.setFecha(fecha);
					request.getSession().setAttribute("bean111b",bean111b);
			  		strAddress = "/jsp/11_1b.jsp";
			  		
					break;
		}

		request.setAttribute("bean111",bean111);


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
