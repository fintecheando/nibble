package pva.servlets11;

/**
 * Insert the type's description here.
 * Creation date: (11/28/2002 4:02:29 PM)
 * @author: 
 */
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import pva.beans11.Bean113;
import org.nibble.util.ReportConstants;
import org.nibble.util.ReportTemplate;
import dbbeans.categoriacliente.dbCATEGORIACLIENTE;
import dbbeans.cliente.dbCLIENTE;
import dbbeans.estado.dbESTADO;
import dbbeans.municipio.dbMUNICIPIO;
import org.nibble.util.UtilDate;
public class ImprimeEstadoCuenta extends org.nibble.main.MainServlet {

	private static Log logger = LogFactory.getLog(ImprimeEstadoCuenta.class);
	private static final int DOC = 0;

	/** DOCUMENT ME!  */
	private static final int REF = 1;

	/** DOCUMENT ME!  */
	private static final int EMISION = 2;

	/** DOCUMENT ME!  */
	private static final int VENC = 3;

	/** DOCUMENT ME!  */
	private static final int CARGO = 4;

	/** DOCUMENT ME!  */
	private static final int ABONO = 5;

	private static final int STATUS = 6;
	/** DOCUMENT ME!  */
	private static final int COLUMNS = 7;

	//Constates para los parametros del reporte

	/** DOCUMENT ME!  */
	private static final String LIMITE = "LIMITE";

	/** DOCUMENT ME!  */
	private static final String SALDO = "SALDO";

	/** DOCUMENT ME!  */
	private static final String DISPONIBLE = "DISPONIBLE";

	/** DOCUMENT ME!  */
	private static final String POR_VENCER = "POR_VENCER";

	/** DOCUMENT ME!  */
	private static final String SALDO_ING = "SALDO_ING";

	/** DOCUMENT ME!  */
	private static final String RAZON_SOCIAL = "RAZON_SOCIAL";

	private static final String CALLE = "CALLE";

	private static final String COLONIA = "COLONIA";

	private static final String ESTADO = "ESTADO";

	private static final String TELEFONO = "TELEFONO";

	private static final String R_F_C = "R_F_C";

	private static final String C_P = "C_P";

	private static final String MUNICIPIO = "MUNICIPIO";

	private static final String PLAZO = "PLAZO";
	/** DOCUMENT ME!  */
	private static final String CAT_CLIENTE = "CAT_CLIENT";

	private static final String X_VENC = "X_VENC";

	private static final String X_VENC_POR = "X_VENC_POR";

	private static final String P1_7_DIAS = "P1_7_DIAS";

	private static final String P1_7_D_POR = "P1_7_D_POR";

	private static final String P8_29_DIAS = "P8_29_DIAS";

	private static final String P8_29_D_POR = "P8_29_D_POR";

	private static final String P30_45_DIAS = "P30_45_DIAS";

	private static final String P30_45_D_POR = "P30_45_D_POR";

	private static final String P46_60_DIAS = "P46_60_DIAS";

	private static final String P46_60_D_POR = "P46_60_D_POR";

	private static final String MAS_61 = "MAS_61";

	private static final String MAS_61_POR = "MAS_61_POR";

	private static final String TOT_VENC = "TOT_VENC";

	private static final String FECHA = "FECHA";
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
			{ "DOC", "REF", "EMISION", "VENC", "CARGO", "ABONO", "STATUS" };
		Object[][] data;
		ReportTemplate report;
		try {
			addressType = 0;
			initConnection();

			Bean113 bean113 = (Bean113) ses_usr.getAttribute("bean113");

			dbCLIENTE dbc = new dbCLIENTE();
			dbc.setIidnocliente(bean113.getIdcliente());
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

			dbCATEGORIACLIENTE dbcat = new dbCATEGORIACLIENTE();
			dbcat.setIidcatcliente(dbc.getIidcatcliente());
			dbcat.setConnection(con);
			dbcat.load();

			report = new ReportTemplate();
			report.setReportName("/estado_cuenta");
			report.setHeaders(headers);
			report.setReportType(ReportConstants.PDF_TYPE);

			report.addParam(RAZON_SOCIAL, dbc.getVchrazonsocial());
			report.addParam(CALLE, dbc.getVchcalle());
			report.addParam(COLONIA, dbc.getVchcolonia());
			report.addParam(ESTADO, dbe.getVchnombre());
			report.addParam(R_F_C, dbc.getVchrfc());
			report.addParam(MUNICIPIO, dbm.getVchnombre());
			report.addParam(TELEFONO, dbc.getVchtel1());
			report.addParam(C_P, dbc.getChcodigop());
			report.addParam(LIMITE, bean113.getLimite());
			report.addParam(SALDO, bean113.getSaldo());
			report.addParam(DISPONIBLE, bean113.getDisponible());
			report.addParam(POR_VENCER, bean113.getSxvencer());
			report.addParam(SALDO_ING, bean113.getIngreso());
			report.addParam(PLAZO, new Integer(dbc.getSiplazodiaspago()));
			report.addParam(CAT_CLIENTE, dbcat.getVchdescripcion());
			report.addParam(X_VENC, bean113.getSxvencer());
			report.addParam(X_VENC_POR, bean113.getPxvencer());
			report.addParam(P1_7_DIAS, bean113.getSx1_7());
			report.addParam(P1_7_D_POR, bean113.getPx1_7());
			report.addParam(P8_29_DIAS, bean113.getSx8_29());
			report.addParam(P8_29_D_POR, bean113.getPx8_29());
			report.addParam(P30_45_DIAS, bean113.getSx30_45());
			report.addParam(P30_45_D_POR, bean113.getPx30_45());
			report.addParam(P46_60_DIAS, bean113.getSx46_60());
			report.addParam(P46_60_D_POR, bean113.getPx46_60());
			report.addParam(MAS_61, bean113.getSx61());
			report.addParam(MAS_61_POR, bean113.getPx61());
			report.addParam(TOT_VENC, bean113.getVencido());
			logger.info("Fecha de hoy" + UtilDate.getStrDate());
			report.addParam(FECHA, UtilDate.getStrDate());

			data = new Object[bean113.sizeCxc()][COLUMNS];
			String linea = "";
			for (int i = 0; i < bean113.sizeCxc(); i++) {
				data[i][DOC] =	bean113.getVchDestipodocto(i)+ " "+ bean113.getIiddoctoorigen(i);
				if (bean113.getIidcontradocto(i) > 0)
					linea =	bean113.getVchDestipocontradocto(i)	+ " "+ bean113.getIidcontradocto(i);
				else
					linea = "";
				data[i][REF] = linea;
				data[i][EMISION] = bean113.getTisfechamovimiento(i);
				data[i][VENC] = bean113.getTisfechavencimient(i);
				data[i][CARGO] = new Double(bean113.getDecImporteCargoEdodo(i));
				data[i][ABONO] = new Double(bean113.getDecImporteAbonoEdodo(i));
				String strEsVencido;
				if ((bean113.getVchDestipodocto(i).equalsIgnoreCase("FAC")
					== true)
					&& (bean113.getisVencido(i) == true))
					strEsVencido = "Vencido";
				else
					strEsVencido = "";
				data[i][STATUS] = strEsVencido;

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
