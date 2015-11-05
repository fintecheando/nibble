package org.nibble.service.mostrador;

/**
 * Insert the type's description here.
 * Creation date: (11/28/2002 4:02:29 PM)
 * @author: 
 */
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import pva.beans9.Bean92;
import pva.beans9.Bean92b;
import org.nibble.util.CantidadALetra;
import org.nibble.util.Fecha;
import org.nibble.util.ReportConstants;
import org.nibble.util.ReportTemplate;
import dbbeans.usuarios.dbUSUARIOS;
/**
 * Declaracion de las constantes para el manejo de la tabla y 
 * para el llenado de los parametros.  
 * 
 */
public class ImprimirNotaCredito extends org.nibble.main.MainServlet {
	private static Log logger = LogFactory.getLog(ImprimirNotaCredito.class);

	private static final int CANTIDAD = 0;

	/** DOCUMENT ME!  */
	private static final int UNIDAD = 1;

	/** DOCUMENT ME!  */
	private static final int NO_PARTE = 2;

	/** DOCUMENT ME!  */
	private static final int DESCRIPCION = 3;

	/** DOCUMENT ME!  */
	private static final int PRECIO = 4;

	/** DOCUMENT ME!  */
	private static final int IMPORTE = 5;

	/** DOCUMENT ME!  */
	private static final int COLUMNS = 6;

	//Constates para los parametros del reporte

	/** DOCUMENT ME!  */
	private static final String FECHA = "FECHA";

	/** DOCUMENT ME!  */
	private static final String REFERENCIA = "REFERENCIA";

	/** DOCUMENT ME!  */
	private static final String FACTU_NO = "FACTU_NO";

	/** DOCUMENT ME!  */
	private static final String NO_CLIENTE = "NO_CLIENTE";

	/** DOCUMENT ME!  */
	private static final String VENDEDOR = "VENDEDOR";

	/** DOCUMENT ME!  */
	private final String RAZON_SOCIAL = "RAZON_SOCIAL";

	private final String CALLE = "CALLE";

	private final String COLONIA = "COLONIA";

	private final String ESTADO = "ESTADO";

	private final String TELEFONO = "TELEFONO";

	private final String R_F_C = "R_F_C";

	private final String C_P = "C_P";

	private final String MUNICIPIO = "MUNICIPIO";
	private final String CATEGORIA = "CONDICIONES";
	/** DOCUMENT ME!  */
	private final String F_IVA = "F_IVA";
	
	/** DOCUMENT ME!  */
	private static final String DESCUENTO = "DESCUENTO";

	/**
	 * Process incoming HTTP GET requests 
	 * 
	 * @param request Object that encapsulates the request to the servlet 
	 * @param response Object that encapsulates the response from the servlet
	 */
	public void doGet(
		javax.servlet.http.HttpServletRequest request,
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
	public void doPost(
		javax.servlet.http.HttpServletRequest request,
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
	public void performTask(
		javax.servlet.http.HttpServletRequest request,
		javax.servlet.http.HttpServletResponse response) {
		String[] headers =
			{
				"CANTIDAD",
				"UNIDAD",
				"NO_PARTE",
				"DESCRIPCION",
				"PRECIO",
				"IMPORTE" };
		Object[][] data;
		try {
			addressType = 0;
			initConnection();

			dbUSUARIOS usuario = new dbUSUARIOS();

			usuario.setConnection(con);

			Bean92b bean = (Bean92b) ses_usr.getAttribute("bean");
			Bean92 bean92 = (Bean92) ses_usr.getAttribute("bean92");
			CantidadALetra conver = new CantidadALetra();

			usuario.setIidusuario(bean.getIidusuario());
			usuario.setIidsistema(3);
			usuario.load();


			ReportTemplate report = new ReportTemplate();
			report.setReportName("/nota_credito");
			report.addParam(
				FECHA,
				Fecha.formatReal(bean.getFecha(), "yyyyMMddHHmmss"));
			report.addParam(
				FACTU_NO,
				new Integer(bean.getFactura().getIidfactura()));
			report.addParam(REFERENCIA, bean.getReferencia());
			report.addParam(NO_CLIENTE, String.valueOf(bean.getIidnocliente()));
			report.addParam(VENDEDOR, String.valueOf(bean.getIidusuario()));
			report.addParam(RAZON_SOCIAL, bean.getClienteRazonSocial());
			report.addParam(CALLE, bean.getClienteCalle());
			report.addParam(COLONIA, bean.getClienteColonia());
			report.addParam(ESTADO, bean.getClienteEstado());
			report.addParam(TELEFONO, bean.getClienteTelefono());
			report.addParam(R_F_C, bean.getClienteRFC());
			report.addParam(C_P, bean.getClienteCodigoPostal());
			report.addParam(MUNICIPIO, bean.getClienteMunicipio());
			report.addParam(CATEGORIA, bean.getCondiciones());
			report.addParam(F_IVA,	bean92.isMostradorSinIVA() ? new Double(0.0) : new Double(bean92.getIva()));

			data = new Object[bean.getSize()][COLUMNS];
			for (int i = 0; i < bean.getSize(); i++) {
				data[i][CANTIDAD] = new Integer(bean.getDevueltasD(i));
				data[i][UNIDAD] = bean.getVchunidad(i);
				data[i][NO_PARTE] = bean.getVchnumparte(i);
				data[i][DESCRIPCION] = bean.getVchdescripcion(i);
				data[i][PRECIO] = new Double(bean.getDecprecioventaD(i));
				data[i][IMPORTE] = new Double(bean.getImporteD(i));

			}
			report.addParam(DESCUENTO, new Double(bean.getDescuento()));
			logger.debug("DATA:" + data);

			report.setHeaders(headers);
			report.setData(data);
			report.setReportType(ReportConstants.PDF_TYPE);
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
