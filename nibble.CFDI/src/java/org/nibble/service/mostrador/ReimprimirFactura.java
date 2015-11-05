/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.nibble.service.mostrador;

import dbbeans.configsistema.dbCONFIGSISTEMA;
import dbbeans.configuracionpva.dbCONFIGURACIONPVA;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.sql.Connection;
import java.util.Enumeration;
//import mx.bigdata.sat.cfd.CFDv2;
import mx.bigdata.sat.cfd.CFDv22;
import mx.bigdata.sat.cfd.v22.schema.Comprobante;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.nibble.main.BeanUtil;





import org.nibble.main.MainServlet;
import org.nibble.util.Fecha;
import org.nibble.util.ReportConstants;
import org.nibble.util.ReportTemplate;

/**
 * DOCUMENT ME!
 *
 * @author $author$
 *
 */
public class ReimprimirFactura extends MainServlet {
    //	final String formato = "#########0.00";
    //	final int ROWS = 10;
    // Constantes para el llenado de la matrix de objectos
	private static Log logger = LogFactory.getLog(ImprimirFactura.class);
        /** DOCUMENT ME!  */
        private static final int FOLIO = 0;
        private static final int CANTIDAD = 1;
        /** DOCUMENT ME!  */
        private static final int UNIDAD = 2;
        /** DOCUMENT ME!  */
        private static final int NO_PARTE = 3;
        /** DOCUMENT ME!  */
        private static final int DESCRIPCION = 4;
        /** DOCUMENT ME!  */
        private static final int NIVEL = 5;
        /** DOCUMENT ME!  */
        private static final int PRECIO = 6;
        /** DOCUMENT ME!  */
        private static final int IMPORTE = 7;
        /** DOCUMENT ME!  */
        private static final int COLUMNS = 8;
        //Constates para los parametros del reporte
        /** DOCUMENT ME!  */
        private static final String FECHA = "FECHA";
        /** DOCUMENT ME!  */
        private static final String REFERENCIA = "REFERENCIA";
        /** DOCUMENT ME!  */
        private static final String FEC_VEN = "FEC_VENC";
        /** DOCUMENT ME!  */
        private static final String NO_CLIENTE = "NO_CLIENTE";
        /** DOCUMENT ME!  */
        private static final String VENDEDOR = "VENDEDOR";
        /** DOCUMENT ME!  */
        private static final String PEDIDO = "PEDIDO";
        /** DOCUMENT ME!  */
        private static final String CONDICIONES = "CONDICIONES";
        /** DOCUMENT ME!  */
        private static final String TRANSPORTE = "TRANSPORTE";
        private static final String RAZON_SOCIAL = "RAZON_SOCIAL";
	private static final String CALLE = "CALLE";
	private static final String COLONIA = "COLONIA";
	private static final String ESTADO = "ESTADO";
	private static final String TELEFONO = "TELEFONO";
	private static final String R_F_C = "R_F_C";
	private static final String C_P = "C_P";
	private static final String MUNICIPIO = "MUNICIPIO";
        private static final String SERIE_CERTIFICADO = "SERIE_CERTIFICADO";
        private static final String NUM_APROBACION = "NUM_APROBACION";
        private static final String ANO_APROBACION = "ANO_APROBACION";
        private static final String FOLIO_FAC = "FOLIO_FAC";
        private static final String SERIE = "SERIE";
        private static final String F_IVA = "F_IVA";
        private static final String DESCUENTO = "PDESCUENTO";
        private static final String NOM_EMISOR ="NOM_EMISOR";
        private static final String RFC_EMISOR = "RFC_EMISOR";
        private static final String FORMA_PAGO = "FORMA_PAGO";
        private static final String CADENA_ORIGINAL = "CADENA_ORIGINAL";
        private static final String SELLO_DIGITAL_EMISOR = "SELLO_DIGITAL_EMISOR";
        private static final String DOM_EMISOR = "DOM_EMISOR";
        private static final String DOM_RECEPTOR = "DOM_RECEPTOR";
        private static final Object LOGO = "LOGO";
        private BufferedImage logo;

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

        String firstFile1 = "";

        if(request.getParameterNames().hasMoreElements()){
            Enumeration enume = request.getParameterNames();
            System.out.println("EL PARAMETRO ES "+ enume.nextElement());
        }

		if ((request.getParameter("firstFile1") != null) && (request.getParameter("firstFile1").length() > 0)) {
			firstFile1 = request.getParameter("firstFile1");
		}
        System.out.println("EL ARCHIVO A CARGAR "+request.getParameter("firstFile1"));
        firstFile1 = request.getParameter("firstFile1");
        
        

        
         String[] headers = {
            "FOLIO", "CANTIDAD", "UNIDAD", "NO_PARTE", "DESCRIPCION", "NIVEL", "PRECIO",
            "IMPORTE"
        };
        Object[][] data;
        ReportTemplate report;
        Connection con = null;
	BeanUtil bu = null;
        try {

            Comprobante sellado = CFDv22.newComprobante(new FileInputStream("C:/nibble/facturas/xml/"+firstFile1));
            System.out.println("EL AÑO DE APROBACION ES "+sellado.getAnoAprobacion().toString());

            bu = new BeanUtil();
	    con = bu.getConn();

            dbCONFIGURACIONPVA configuracionpva = new dbCONFIGURACIONPVA();
            configuracionpva.setConnection(con);
            configuracionpva.setIidconfpva(1);
            configuracionpva.load();

            dbCONFIGSISTEMA configsistema = new dbCONFIGSISTEMA();
            configsistema.setConnection(con);
            configsistema.setIidconfsistema(1);
            configsistema.load();

            if (configuracionpva.load() == true){

            }
            bu.finalizeTransaction(con);
            addressType = 0;
            initConnection();
            CFDv22 cfd = new CFDv22(sellado);
            /*Bean91 bean91 = (Bean91) ses_usr.getAttribute("bean912");
            Bean91P bean91p = (Bean91P) ses_usr.getAttribute("bean91p");

            CFDFactory cfdFactory = new CFDFactory();
            cfdFactory.setConfiguracionpva(configuracionpva);
            cfdFactory.setConfigsistema(configsistema);
            cfdFactory.setBean91(bean91);
            cfdFactory.setBean91p(bean91p);
            PrivateKey key = KeyLoader.loadPKCS8PrivateKey(new FileInputStream("D:/ssl/sello.key"), "2010RODRIGUEZ");
            X509Certificate cert = KeyLoader.loadX509Certificate(new FileInputStream("D:/ssl/sello.cer"));
            Comprobante sellado = cfd.sellarComprobante(key, cert);

            cfd.validar();
            cfd.verificar();
            FileOutputStream newOut = new FileOutputStream("D:/facturas/factura_"+ configuracionpva.getVchserie()+"_" +bean91.getFolio() +".xml");
            cfd.guardar(newOut);


            float totalFactura;
            float total = 0;
            float ivaMonto = 0;
            float descuentoMonto = 0;
             */

            report = new ReportTemplate();
            report.setReportName("/factura");
            report.setHeaders(headers);
            report.setReportType(ReportConstants.PDF_TYPE);

            //incializacion de parametros
            report.addParam(SERIE_CERTIFICADO, sellado.getNoCertificado().toString());
            report.addParam(NUM_APROBACION,sellado.getNoAprobacion().toString());
            report.addParam(ANO_APROBACION,sellado.getAnoAprobacion().toString());
            report.addParam(FOLIO_FAC,sellado.getFolio().toString());
            report.addParam(SERIE,sellado.getSerie().toString());

            report.addParam(NOM_EMISOR, sellado.getEmisor().getNombre());
            report.addParam(RFC_EMISOR, sellado.getEmisor().getRfc());
            report.addParam(FORMA_PAGO,sellado.getFormaDePago());
            report.addParam(CADENA_ORIGINAL, cfd.getCadenaOriginal().toString());
            report.addParam(SELLO_DIGITAL_EMISOR,sellado.getSello());
            
            report.addParam(DOM_EMISOR, sellado.getEmisor().getDomicilioFiscal().getCalle() +" "
                    + sellado.getEmisor().getDomicilioFiscal().getNoExterior() +", "
                    + sellado.getEmisor().getDomicilioFiscal().getColonia() +", "
                    + sellado.getEmisor().getDomicilioFiscal().getLocalidad() +", "
                    + sellado.getEmisor().getDomicilioFiscal().getMunicipio() +", "
                    + sellado.getEmisor().getDomicilioFiscal().getEstado() +", "
                    + sellado.getEmisor().getDomicilioFiscal().getPais() +", C.P. "
                    + sellado.getEmisor().getDomicilioFiscal().getCodigoPostal());

            report.addParam(FECHA, Fecha.formatoFacturaElectronica(sellado.getFecha()));
            report.addParam(LOGO,"/opt/nibble/imagenes/logo_vla.png");
            report.addParam(RAZON_SOCIAL, sellado.getReceptor().getNombre());
            String localidadPrint ="";
            if (sellado.getReceptor().getDomicilio().getLocalidad()== null || 
                    sellado.getReceptor().getDomicilio().getLocalidad().equalsIgnoreCase("NULL") ||
                    sellado.getReceptor().getDomicilio().getLocalidad().equals("NULL") ||
                    sellado.getReceptor().getDomicilio().getLocalidad().equals("") ||
                    sellado.getReceptor().getDomicilio().getLocalidad().isEmpty())
                 localidadPrint="";
            else
                localidadPrint = sellado.getEmisor().getDomicilioFiscal().getLocalidad();
            
            report.addParam(DOM_RECEPTOR, (sellado.getReceptor().getDomicilio().getCalle() +" "
                    + sellado.getReceptor().getDomicilio().getNoExterior() +", "
                    + sellado.getReceptor().getDomicilio().getNoInterior() +", "
                    + sellado.getReceptor().getDomicilio().getColonia() +", "
                    + localidadPrint +", "
                    + sellado.getReceptor().getDomicilio().getMunicipio() +", "
                    + sellado.getReceptor().getDomicilio().getEstado() +", "
                    + sellado.getReceptor().getDomicilio().getPais() +", C.P. "
                    + sellado.getReceptor().getDomicilio().getCodigoPostal()).toUpperCase().replaceAll(", ,", ","));
            report.addParam(R_F_C, sellado.getReceptor().getRfc());
            report.addParam(F_IVA, new Double(sellado.getImpuestos().getTraslados().getTraslado().get(0).getTasa().toString()));
            report.addParam(DESCUENTO, new Double(0));

            data = new Object[sellado.getConceptos().getConcepto().size()][COLUMNS];

            for (int i = 0; i < sellado.getConceptos().getConcepto().size(); i++) {
            	data[i][FOLIO] =  new Integer(sellado.getFolio().toString());
                data[i][CANTIDAD] = new Integer(sellado.getConceptos().getConcepto().get(i).getCantidad().toString());
                data[i][UNIDAD] = sellado.getConceptos().getConcepto().get(i).getUnidad();
                data[i][NO_PARTE] = sellado.getConceptos().getConcepto().get(i).getNoIdentificacion();
                data[i][DESCRIPCION] = sellado.getConceptos().getConcepto().get(i).getDescripcion();
                data[i][NIVEL] = 1;
                data[i][PRECIO] = new Double(sellado.getConceptos().getConcepto().get(i).getValorUnitario().toString());
                data[i][IMPORTE] = new Double(sellado.getConceptos().getConcepto().get(i).getImporte().toString());
                logger.debug(data[i]);
            }

            report.setData(data);
            request.setAttribute(ReportConstants.REPORT_REQUEST, report);
            String filename = "FACTURA_"+sellado.getReceptor().getNombre()+ "_"
                    +sellado.getSerie().toString() +"_"+sellado.getFolio().toString()+".pdf";
            request.setAttribute("filename", filename);


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
