package org.nibble.service.mostrador;

import dbbeans.configuracionpva.dbCONFIGURACIONPVA;
import java.io.FileWriter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


import org.nibble.main.BeanUtil;
import java.sql.Connection;
import java.util.Vector;
import org.nibble.dao.factura.FacturaDAO;
import org.nibble.main.MainServlet;
import org.nibble.util.Fecha;
import org.nibble.util.Formato;
import org.nibble.util.ReportConstants;

import org.nibble.vo.factura.FacturaClienteVO;
import org.nibble.vo.factura.FacturaVO;



/**
 * DOCUMENT ME!
 *
 * @author $author$
 *
 */
public class ReporteMensualSAT extends MainServlet {
    //	final String formato = "#########0.00";
    //	final int ROWS = 10;
    // Constantes para el llenado de la matrix de objectos	
	private static Log logger = LogFactory.getLog(ReporteMensualSAT.class);
    

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

        StringBuffer reporteMensualSAT = new StringBuffer();

        Connection con = null;
	BeanUtil bu = null;
        try {
            bu = new BeanUtil();
	    con = bu.getConn();

            FacturaVO facturas = new FacturaVO();
            FacturaDAO factura = new FacturaDAO();
            factura.setConnection(con);
            facturas.setConnection(con);

            dbCONFIGURACIONPVA configuracionpva = new dbCONFIGURACIONPVA();
            configuracionpva.setConnection(con);
            configuracionpva.setIidconfpva(1);
            configuracionpva.load();

            int mes = Fecha.getCurrentMonth();
            int ano = Fecha.getCurrentYear();

            int periodo = 1;

            if ((request.getParameter("periodo") != null) && (request.getParameter("periodo").length() > 0)) {
                periodo = new Integer(request.getParameter("periodo")).intValue();
            }

            /*
            Si se selecciona el reporte del mes anterior y el mes actual es enero,
            se genera el reporte para diciembre del año anterior
            */
            if (periodo ==1) {
                
                if (mes == 1 ){
                    mes = 12;
                    ano = ano -1;
                }
            }

            /*
            Si se selecciona un mes y año, se obtienen los valores de la forma.
            */
            if (periodo ==2) {
                
                if ((request.getParameter("selm") != null) && (request.getParameter("selm").length() > 0)) {
                    mes = new Integer(request.getParameter("selm")).intValue();
                    
                }
                
                if ((request.getParameter("sely") != null) && (request.getParameter("sely").length() > 0)) {
                    ano = new Integer(request.getParameter("sely")).intValue();
                    
                }
                
            }

            Vector fila = new Vector();
            
            fila = facturas.findByFecha(mes, ano);
            for (int i = 0; i < fila.size(); i++) {
            	factura =   (FacturaClienteVO)(fila.get(i));
                reporteMensualSAT.append("|");
                reporteMensualSAT.append(factura.getVchrfccliente());
                reporteMensualSAT.append("|");
                reporteMensualSAT.append(factura.getVchserie());
                reporteMensualSAT.append("|");
                reporteMensualSAT.append(factura.getIfolio());
                reporteMensualSAT.append("|");
                reporteMensualSAT.append(factura.getInumaprobacion());
                reporteMensualSAT.append("|");
                reporteMensualSAT.append(factura.getVchfechahora());
                reporteMensualSAT.append("|");
                reporteMensualSAT.append(Formato.formateoNumerico(factura.getDecmontooperacion(), "#########0.00"));
                reporteMensualSAT.append("|");
                reporteMensualSAT.append(Formato.formateoNumerico(factura.getDecmontoimpuesto(), "#########0.00"));
                reporteMensualSAT.append("|");
                reporteMensualSAT.append(factura.getVchestadocomprobante());
                reporteMensualSAT.append("|");
                reporteMensualSAT.append(factura.getVchefectocomprobante());
                reporteMensualSAT.append("|");
                reporteMensualSAT.append(factura.getVchpedimento());
                reporteMensualSAT.append("|");
                reporteMensualSAT.append(factura.getVchfechapedimento());
                reporteMensualSAT.append("|");
                reporteMensualSAT.append(factura.getVchaduana());
                reporteMensualSAT.append("|");
                //if (i < fila.size()-1)
                    reporteMensualSAT.append("\n");
            }

            bu.finalizeTransaction(con);
            addressType = 0;
            initConnection();
            String Mes;
            if (mes < 10)
            {
                Mes = "0" + new Integer(mes).toString();
            }
            else
                Mes = new Integer(mes).toString();
            String filename = "1"+ configuracionpva.getVchrfc() + Mes + ano +".txt";
            String file = "/opt/nibble/reportes/"+filename;
            FileWriter fw = new FileWriter(file);
            fw.append(reporteMensualSAT);
            fw.flush();
            fw.close();           

            request.setAttribute(ReportConstants.REPORTE_MENSUAL_SAT, file);
            request.setAttribute("filename",filename);            
            
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
