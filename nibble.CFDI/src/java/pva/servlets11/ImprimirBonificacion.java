/*
 * Created on Feb 1, 2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package pva.servlets11;

import org.nibble.main.BeanUtil;
import org.nibble.main.MainServlet;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import pva.beans11.Bean111b;
import org.nibble.util.Fecha;
import org.nibble.util.ReportConstants;
import org.nibble.util.ReportTemplate;
import dbbeans.usuarios.dbUSUARIOS;
import dbbeans.municipio.dbMUNICIPIO;
import dbbeans.categoriacliente.dbCATEGORIACLIENTE;
import dbbeans.estado.dbESTADO;
/**
 * @author Pedro
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class ImprimirBonificacion extends MainServlet {
	private static Log logger = LogFactory.getLog(ImprimirBonificacion.class);
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
		try{
		addressType = 0;
		initConnection();

		dbUSUARIOS usuario = new dbUSUARIOS();

		usuario.setConnection(con);

		Bean111b bean = (Bean111b) ses_usr.getAttribute("bean111b");
		ses_usr.removeAttribute("bean111b");
		ses_usr = request.getSession(false);
		BeanUtil bu = new BeanUtil();
		con = bu.getConn();
		bu.setSession(ses_usr);
   		
   		dbESTADO dbe= new dbESTADO();
   		dbe.setIidestado(bean.getDbcliente().getIidestado());
   		dbe.setConnection(con);
   		dbe.load();
   		
        dbMUNICIPIO	dbm = new dbMUNICIPIO();
        dbm.setIidmunicipio(bean.getDbcliente().getIidmunicipio());
        dbm.setConnection(con);
        dbm.load();
        
        dbCATEGORIACLIENTE dbcat= new dbCATEGORIACLIENTE();
        dbcat.setIidcatcliente(bean.getDbcliente().getIidcatcliente());
        dbcat.setConnection(con);
        dbcat.load();
         
		//			usuario.setIidusuario(bean.getDbcliente().));
		//			usuario.setIidsistema(3);
		//			usuario.load();

		ReportTemplate report = new ReportTemplate();
		report.setReportName("/nota_bonificacion");
		report.addParam(
			FECHA,
			Fecha.formatReal(bean.getFecha(), "yyyyMMddHHmmss"));
		report.addParam(FACTU_NO, new Integer(bean.getDbnc().getIidfactura()));
		report.addParam(
			REFERENCIA,
			String.valueOf(bean.getDbnc().getIidnotacredito()));
		report.addParam(
			NO_CLIENTE,
			String.valueOf(bean.getDbnc().getIidnocliente()));
		report.addParam(VENDEDOR, String.valueOf(bu.getId_usuario()));
		report.addParam(RAZON_SOCIAL, bean.getDbcliente().getVchrazonsocial());
		report.addParam(CALLE, bean.getDbcliente().getVchcalle());
		report.addParam(COLONIA, bean.getDbcliente().getVchcolonia());
		report.addParam(ESTADO, dbe.getVchnombre());
		report.addParam(TELEFONO, bean.getDbcliente().getVchtel1());
		report.addParam(R_F_C, bean.getDbcliente().getVchrfc());
		report.addParam(C_P, bean.getDbcliente().getChcodigop());
		report.addParam(MUNICIPIO, dbm.getVchnombre());
		report.addParam(CATEGORIA, dbcat.getVchdescripcion());
		report.addParam(F_IVA, new Double(bean.getIva()));

		data = new Object[1][COLUMNS];

		data[0][CANTIDAD] = null;
		data[0][UNIDAD] = null;
		data[0][NO_PARTE] = null;
		data[0][DESCRIPCION] = bean.getDbnc().getVchconcepto();
		data[0][PRECIO] = new Double(bean.getDbnc().getDecimporte());
		data[0][IMPORTE] = new Double(bean.getDbnc().getDecimporte());
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
		}finally {
		finalizeConnection();
		}
	}
}