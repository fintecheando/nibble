/*
 * Created on 31/01/2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package org.nibble.service.mostrador;
import org.nibble.main.MainServlet;

import pva.beans11.Bean112;
import dbbeans.usuarios.*;
import org.nibble.main.BeanUtil;

import org.nibble.util.ReportConstants;
import org.nibble.util.ReportTemplate;
import dbbeans.cliente.dbCLIENTE;
import dbbeans.estado.dbESTADO;
import dbbeans.municipio.dbMUNICIPIO;

/**
 * @author davalos
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class ImprimirNotaCargo extends MainServlet {
	
	private static final int CONCEPTO = 0;
	
	private static final int PRECIO = 1;
	
	private static final int IMPORTE = 2;
	
	private static final int COLUMNS = 3;
	
	/** DOCUMENT ME!  */
		private static final String FECHA = "FECHA";

		/** DOCUMENT ME!  */
		
		private static final String FECHA_VENC = "FECHA_VENC";

		/** DOCUMENT ME!  */
		private static final String NO_CLIENTE = "NO_CLIENTE";

		/** DOCUMENT ME!  */
		private static final String VENDEDOR = "VENDEDOR";

		/** DOCUMENT ME!  */
		private static final String RAZON_SOCIAL = "RAZON_SOCIAL";
	
		/** DOCUMENT ME!  */
		
	 private static final String REFERENCIA = "REFERENCIA";
	    
		private static final String CALLE = "CALLE";
	
		private static final String COLONIA = "COLONIA";
	
		private static final String ESTADO = "ESTADO";
	
		private static final String TELEFONO = "TELEFONO";
	
		private static final String R_F_C = "R_F_C";
	
		private static final String C_P = "C_P";
	
		private static final String MUNICIPIO = "MUNICIPIO";

		/** DOCUMENT ME!  */
		private static final String F_IVA = "F_IVA";

	/**
		* Process incoming HTTP GET requests
		*
		* @param request Object that encapsulates the request to the servlet
		* @param response Object that encapsulates the response from the servlet
		*/
	   public void doGet(javax.servlet.http.HttpServletRequest request,
		   javax.servlet.http.HttpServletResponse response)
		   throws javax.servlet.ServletException, java.io.IOException {
		   performTask(request, response);
	   }

	   /**
		* Process incoming HTTP POST requests
		*
		* @param request Object that encapsulates the request to the servlet
		* @param response Object that encapsulates the response from the servlet
		*/
	   public void doPost(javax.servlet.http.HttpServletRequest request,
		   javax.servlet.http.HttpServletResponse response)
		   throws javax.servlet.ServletException, java.io.IOException {
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
			super.init();
		}

		/**
		 * Process incoming requests for information
		 *
		 * @param request Object that encapsulates the request to the servlet
		 * @param response Object that encapsulates the response from the servlet
		 */
		public void performTask(javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response) {
			String[] headers = {
				"CONCEPTO",  "PRECIO", "IMPORTE"
			};
			Object[][] data;
			ReportTemplate report;
			BeanUtil bu = null;
			try {
			addressType = 0;
			initConnection();
								
			Bean112 bean112 = (Bean112) ses_usr.getAttribute("bean112");
			dbCLIENTE dbc = new dbCLIENTE();
			dbc.setIidnocliente (bean112.getIdCliente());
			dbc.setConnection(con);
			dbc.load();
			
			dbESTADO dbe = new dbESTADO();
			dbe.setIidestado(dbc.getIidestado());
			dbe.setConnection(con);
			dbe.load();
			
			dbMUNICIPIO dbm = new dbMUNICIPIO();
			dbm.setIidmunicipio(dbc.getIidmunicipio());
			dbm.setConnection(con);
			dbm.load();			
			
			Bean112 bean1122 = (Bean112) ses_usr.getAttribute("bean112");
			dbUSUARIOS dbcli = new dbUSUARIOS();
			//dbcli.setIidusuario(((Integer) ses_usr.getAttribute("iidusuario")).intValue());
			dbcli.setIidusuario(bean1122.getIdUsuario());
			dbcli.setConnection(con);
			dbcli.load();			
			
			dbUSUARIOS dbcli2 = new dbUSUARIOS();
		    dbcli2.setIidusuario(dbcli.getIidusuario());
			dbcli2.setConnection(con);
			dbcli2.load();
			
			
			report = new ReportTemplate();
			report.setReportName("/nota_cargo_final");
			report.setHeaders(headers);
			report.setReportType(ReportConstants.PDF_TYPE);

			//incializaci�n de parametros
			report.addParam(FECHA, bean112.getFecha());
			report.addParam(FECHA_VENC, bean112.getFechaVen());
			report.addParam(REFERENCIA, new Integer(bean112.getIdNotaCargo()));
			report.addParam(NO_CLIENTE, new Integer(bean112.getIdCliente()));
			report.addParam(VENDEDOR,  new Integer(dbcli2.getIidusuario()).toString());
			/*		
			report.addParam(VENDEDOR,  new Integer(dbcli2.getIidusuario()).toString());
			report.addParam(VENDEDOR, new Integer (bean112.getIdUsuario()));			
			*/
			report.addParam(RAZON_SOCIAL, dbc.getVchrazonsocial());
			report.addParam(CALLE, dbc.getVchcalle());
			report.addParam(COLONIA, dbc.getVchcolonia());
			report.addParam(ESTADO, dbe.getVchnombre());
			report.addParam(TELEFONO, dbc.getVchtel1());
			report.addParam(R_F_C, dbc.getVchrfc());
			report.addParam(C_P, dbc.getChcodigop());
			report.addParam(MUNICIPIO, dbm.getVchnombre());			
			/* Autor: Victor Romero  
			 * Modificacion: 
			 * Se Elimina: dbnc.setDecivaimporte(ivalocal);
			 * Se agrega: bean112.getIvaImporte()
			 *  Fecha de Modificacion: 03-02-2004 	
			 */ 
			report.addParam(F_IVA, new Double (bean112.getIvaImporte()));
			data = new  Object [1][COLUMNS];			
			data [0][0]= bean112.getVchConcepto();
			data [0][1]= new Double(bean112.getImporte());
			data [0][2]= new Double(bean112.getImporte());
			

			report.setData(data);
			request.setAttribute(ReportConstants.REPORT_REQUEST, report);
			
			} catch (Throwable e) {
				addressType = 0;
				e.printStackTrace();
				error.setErrorMessage(e.getMessage());
				blnHtml = false;
				strAddress = "/jsp/error.jsp";
			}finally {
			finalizeConnection();
		}
	}	
}	
	
/*
			//INICIO
			bu = new BeanUtil();		
			con = bu.getConn();		
			bu.setSession(ses_usr);
			
			dbUSUARIOSs dbus = new dbUSUARIOSs();
			dbus.setConnection(con);
			dbUSUARIOS dbu = new dbUSUARIOS();
			dbu.setConnection(con);
			dbu.setIidsistema(bu.getSistema());			
			Bean22 bean = new Bean22();
			bean.setUsuarios(dbus.findBySistema(bu.getSistema()));
			
			//FIN
			*/