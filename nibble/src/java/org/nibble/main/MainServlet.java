package org.nibble.main;

import org.nibble.util.Contexto;
import org.nibble.util.GenerateReport;
import org.nibble.util.ReportConstants;
import org.nibble.util.ReportTemplate;

import java.io.IOException;

import java.sql.Connection;

import javax.naming.Context;
import javax.naming.InitialContext;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import javax.sql.DataSource;

import javax.transaction.UserTransaction;

import org.apache.log4j.Logger;

/**
 * DOCUMENT ME!
 *
 * @author $author$
 * @version $Revision: 1.1 $
 */
public class MainServlet extends HttpServlet {
	static Logger logger= Logger.getLogger(MainServlet.class);
	/** DOCUMENT ME!  */
	protected HttpSession ses_usr;

	/** DOCUMENT ME!  */
	protected String strAddress;

	/** DOCUMENT ME!  */
	protected boolean blnHtml;

	/** DOCUMENT ME!  */
	protected byte addressType;

	/** DOCUMENT ME!  */
	protected BeanError error;

	/** DOCUMENT ME!  */
	protected BeanSeguridad seguridad;

	/** DOCUMENT ME!  */
	private int id_usuario;

	/** DOCUMENT ME!  */
	protected int grupo;

	/** DOCUMENT ME!  */
	protected int sistema;

	/** DOCUMENT ME!  */
	private int perfil;

	/** DOCUMENT ME!  */
	protected String strPoolName;

	/** DOCUMENT ME!  */
	protected Connection con;

	/** DOCUMENT ME!  */
	protected int idModulo;

	/** DOCUMENT ME!  */
	private Context ctx = null;

	/** DOCUMENT ME!  */
	private UserTransaction utx = null;

	/** DOCUMENT ME!  */
	protected boolean commit = true;

	/** DOCUMENT ME!  */
	private ServletContext contexto;

	/**
	 * Insert the method's description here.	 
	 */
	protected void finalizeConnection() {
		try {
			if (commit) {
				utx.commit();
			} else {
				utx.rollback();
			}

			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * DOCUMENT ME!
	 *
	 * @param address DOCUMENT ME!
	 * @param request DOCUMENT ME!
	 * @param response DOCUMENT ME!
	 *
	 * @throws ServletException DOCUMENT ME!
	 * @throws IOException DOCUMENT ME!
	 */
	private void gotoPage(
		String address,
		HttpServletRequest request,
		HttpServletResponse response)
		throws ServletException, IOException {
                if (request.getAttribute(ReportConstants.REPORTE_MENSUAL_SAT) != null) {
                        String pathfile = (String)request.getAttribute(ReportConstants.REPORTE_MENSUAL_SAT);
                        String filename = (String)request.getAttribute("filename");
			ServletOutputStream ouputStream = response.getOutputStream();
			logger.debug("Entrando a REPORTE MENSUAL SAT");
			byte[] bytes;
			GenerateReport greport;
			response.setContentType(ReportConstants.CONTENT_TYPE_TXT_UTF8);

			greport = new GenerateReport();

			try {
				bytes = greport.generateReporteMensualSAT(new Contexto(contexto),pathfile);
				logger.debug("Se obtuvo del reporte");
				response.setContentLength(bytes.length);
				response.setContentType(ReportConstants.CONTENT_TYPE_TXT_UTF8);
				response.setHeader(ReportConstants.CONTENT_DISPOSITION,
					"attachment;filename=\""+filename+"\"");
				ouputStream.write(bytes, 0, bytes.length);
				ouputStream.flush();
				ouputStream.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				throw new ServletException(e.getMessage());
			}
		}
                else if(request.getAttribute(ReportConstants.REPORT_REQUEST) != null) {

                        String filename = (String)request.getAttribute("filename");

			ServletOutputStream ouputStream = response.getOutputStream();
			logger.debug("Entrando a reportes");
			byte[] bytes;
			GenerateReport greport;
			response.setContentType(ReportConstants.CONTENT_TYPE_PDF);

			greport =
				new GenerateReport(
					(ReportTemplate) request.getAttribute(
						ReportConstants.REPORT_REQUEST));

			try {
				bytes = greport.generateReport(new Contexto(contexto));
				logger.debug("Se obtuvo del reporte");
				response.setContentLength(bytes.length);
				response.setContentType(ReportConstants.CONTENT_TYPE_PDF);
                                if (filename!= null)
                                    response.setHeader(ReportConstants.CONTENT_DISPOSITION,
                                        "attachment;filename=\""+filename+"\"");
				ouputStream.write(bytes, 0, bytes.length);
				ouputStream.flush();
				ouputStream.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				throw new ServletException(e.getMessage());
			}
		} else {
			RequestDispatcher dispatcher =
				getServletContext().getRequestDispatcher(address);

			if (blnHtml) {
				dispatcher.include(request, response);
			} else {
				dispatcher.forward(request, response);
			}
		}
	}

	/**
	 * Insert the method's description here.
	 * Creation date: (9/4/2002 4:02:16 PM)
	 */
	public void init() {
		try {
			ctx = new InitialContext();
			utx = (UserTransaction) ctx.lookup("java:comp/UserTransaction");
			idModulo =
				(getServletConfig().getInitParameter("idModulo") != null)
					? new Integer(
						getServletConfig().getInitParameter("idModulo"))
						.intValue()
					: (-1);
			contexto = getServletConfig().getServletContext();
		} catch (Exception e) {
			System.out.println("BOOM - No context");
		}
	}

	/**
	 * Insert the method's description here.
	 * Creation date: (9/4/2002 4:50:24 PM)
	 */
	protected void initConnection() {
		try {
			utx.begin();

			DataSource ds =
				(DataSource) ctx.lookup("java:comp/env/jdbc/nibble");
			con = ds.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * DOCUMENT ME!
	 *
	 * @param request DOCUMENT ME!
	 * @param response DOCUMENT ME!
	 *
	 * @throws ServletException DOCUMENT ME!
	 * @throws IOException DOCUMENT ME!
	 */
	public final synchronized void service(
		HttpServletRequest request,
		HttpServletResponse response)
		throws ServletException, IOException {
		error = new BeanError();
		seguridad = new BeanSeguridad();
		getServletContext().setAttribute("error", error);

		try {
			this.ses_usr = request.getSession(false);

			if (ses_usr == null) {
				throw new RuntimeException("Sesi�n Terminada");
			} else {
				if (ses_usr.getAttribute("iidusuario") != null) {
					id_usuario =
						new Integer(
							ses_usr.getAttribute("iidusuario").toString())
							.intValue();
				} else {
					throw new Exception("Sesi�n corrupta");
				}

				if (ses_usr.getAttribute("grupo") != null) {
					grupo =
						new Integer(ses_usr.getAttribute("grupo").toString())
							.intValue();
				} else {
					throw new Exception("Sesi�n corrupta");
				}

				if (ses_usr.getAttribute("sistema") != null) {
					sistema =
						new Integer(ses_usr.getAttribute("sistema").toString())
							.intValue();
				} else {
					throw new Exception("Sesi�n corrupta");
				}

				super.service(request, response);

				System.out.println("strAdress=" + strAddress);

				if (addressType != 1) {
					gotoPage(strAddress, request, response);
				}
			}
		} catch (RuntimeException e) {
			e.printStackTrace();
			error.setErrorMessage(e.getMessage());
			blnHtml = false;
			gotoPage("/jsp/error.jsp", request, response);
		} catch (Exception e) {
			e.printStackTrace();
			error.setErrorMessage(e.getMessage());
			blnHtml = false;
			gotoPage("/jsp/error.jsp", request, response);
		}
	}
}
