package pva.servlets11;

/**
 * Insert the type's description here.
 * Creation date: (11/28/2002 4:02:29 PM)
 * @author: 
 */
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import pva.beans11.Bean114;
import org.nibble.util.ReportConstants;
import org.nibble.util.ReportTemplate;
import org.nibble.util.UtilDate;

public class ImprimeAntiguedadSaldos extends org.nibble.main.MainServlet {
	
	
		private static Log logger = LogFactory.getLog(ImprimeEstadoCuenta.class);
		
		private static final int CLIENTE= 0;

		/** DOCUMENT ME!  */
		private static final int SALDO = 1;

		/** DOCUMENT ME!  */
		private static final int X_VENC = 2;

		/** DOCUMENT ME!  */
		private static final int D1_7D = 3;

		/** DOCUMENT ME!  */
		private static final int D8_29D= 4;

		/** DOCUMENT ME!  */
		private static final int D30_45D= 5;

		private static final int D46_60D = 6;
		/** DOCUMENT ME!  */
		private static final int MAS_61D = 7;
		
		private static final int COLUMNS = 8;

		//Constates para los parametros del reporte

		/** DOCUMENT ME!  */
		private static final String FECHA = "FECHA";

		/** DOCUMENT ME!  */
		private static final String TSALDO = "TSALDO";

		/** DOCUMENT ME!  */
		/** DOCUMENT ME!  */
		private static final String TX_VENC = "TX_VENC";

		/** DOCUMENT ME!  */
		private static final String PX_VENC = "PX_VENC";

		/** DOCUMENT ME!  */
		private static final String T1_7D = "T1_7D";

		private static final String P1_7D = "P1_7D";

		private static final String T8_29D = "T8_29D";

		private static final String P8_29D = "P8_29D";

		private static final String T30_45D = "T30_45D";

		private static final String P30_45D = "P30_45D";

		private static final String T46_60D = "T46_60D";
		
		private static final String P46_60D = "P46_60D";

		private static final String TMAS_61D = "TMAS_61D";
		/** DOCUMENT ME!  */
		private static final String PMAS_61D = "PMAS_61D";
	
		
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
	super.init();
}
/**
 * Process incoming requests for information
 * 
 * @param request Object that encapsulates the request to the servlet 
 * @param response Object that encapsulates the response from the servlet
 */
public void performTask(javax.servlet.http.HttpServletRequest request,javax.servlet.http.HttpServletResponse response){
	String[] headers ={ 
		"CLIENTE", "SALDO", "X_VENC", "D1_7D", "D8_29D", "D30_45D", "D46_60D", "MAS_61D" };
	Object[][] data;
	ReportTemplate report;
    try {
        addressType = 0;
        initConnection();

        Bean114 bean114 = (Bean114) ses_usr.getAttribute("bean114");
		
		report = new ReportTemplate();
		report.setReportName("/antiguedad_saldos");
		report.setHeaders(headers);
		report.setReportType(ReportConstants.PDF_TYPE);
		
		report.addParam(FECHA, UtilDate.getStrDate());
		report.addParam(TSALDO, bean114.getTSaldo());
		report.addParam(TX_VENC, bean114.getTSxvencer());
		report.addParam(PX_VENC, bean114.getPSxvencer());
		report.addParam(T1_7D, bean114.getTSx1_7());
		report.addParam(P1_7D, bean114.getPSx1_7());
		report.addParam(T8_29D, bean114.getTSx8_29());
		report.addParam(P8_29D, bean114.getPSx8_29());
		report.addParam(T30_45D, bean114.getTSx30_45());
		report.addParam(P30_45D, bean114.getPSx30_45());
		report.addParam(T46_60D, bean114.getTSx46_60());
		report.addParam(P46_60D, bean114.getPSx46_60());
		report.addParam(TMAS_61D, bean114.getTSx61());
		report.addParam(PMAS_61D, bean114.getPSx61());
		
		data = new Object[bean114.Vctsize()][COLUMNS];
		for (int i = 0; i < bean114.Vctsize(); i++) {
			data[i][CLIENTE] = bean114.getNombreCliente(i);
			data[i][SALDO] = bean114.getSaldo(i);
			data[i][X_VENC] = bean114.getSxvencer(i);
			data[i][D1_7D] = bean114.getSx1_7(i);
			data[i][D8_29D] = bean114.getSx8_29(i);
			data[i][D30_45D] = bean114.getSx30_45(i);
			data[i][D46_60D] = bean114.getSx46_60(i);
			data[i][MAS_61D] = bean114.getSx61(i);
		}
		
		report.setData(data);
		request.setAttribute(ReportConstants.REPORT_REQUEST, report);


    } catch (Throwable e) {
        addressType = 0;
        e.printStackTrace();
        error.setErrorMessage(e.getMessage());
        blnHtml = false;
        strAddress = "/jsp/error.jsp";
    } finally {
        finalizeConnection();
    }
}
}
