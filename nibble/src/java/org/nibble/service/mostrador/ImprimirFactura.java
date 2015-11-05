package org.nibble.service.mostrador;

import dbbeans.configsistema.dbCONFIGSISTEMA;
import dbbeans.configuracionpva.dbCONFIGURACIONPVA;
import java.awt.image.BufferedImage;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import pva.beans9.Bean91;
import pva.beans9.Bean91P;

import org.nibble.main.BeanUtil;
import java.sql.Connection;

import org.nibble.util.Fecha;
import org.nibble.util.ReportConstants;
import org.nibble.util.ReportTemplate;

//import mx.bigdata.sat.cfd.CFDv2;
import mx.bigdata.sat.cfd.CFDv22;
import org.nibble.util.CFDFactory;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;
import mx.bigdata.sat.cfd.v22.schema.Comprobante;
import mx.bigdata.sat.security.KeyLoaderEnumeration;
import mx.bigdata.sat.security.factory.KeyLoaderFactory;
import org.nibble.main.MainServlet;

/**
 * DOCUMENT ME!
 *
 * @author $author$
 *
 */
public class ImprimirFactura extends MainServlet {
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
        private static final String METODO_PAGO = "METODO_PAGO";
        private static final String CUENTA_PAGO = "CUENTA_PAGO";            
        private static final String CADENA_ORIGINAL = "CADENA_ORIGINAL";
        private static final String SELLO_DIGITAL_EMISOR = "SELLO_DIGITAL_EMISOR";
        private static final String DOM_EMISOR = "DOM_EMISOR";
        private static final String DOM_RECEPTOR = "DOM_RECEPTOR";
        private static final Object LOGO = "LOGO";
        private static final String MOTIVO_DESCUENTO = "MOTIVO_DESCUENTO";
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
        String[] headers = {
            "FOLIO", "CANTIDAD", "UNIDAD", "NO_PARTE", "DESCRIPCION", "NIVEL", "PRECIO",
            "IMPORTE"
        };
        Object[][] data;
        ReportTemplate report;
        Connection con = null;
	BeanUtil bu = null;
        try {
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

            Bean91 bean91 = (Bean91) ses_usr.getAttribute("bean912");
            Bean91P bean91p = (Bean91P) ses_usr.getAttribute("bean91p");

            CFDFactory cfdFactory = new CFDFactory();
            cfdFactory.setConfiguracionpva(configuracionpva);
            cfdFactory.setConfigsistema(configsistema);
            cfdFactory.setBean91(bean91);
            cfdFactory.setBean91p(bean91p);
            
            

            CFDv22 cfd = new CFDv22(cfdFactory.createComprobante());
            
            //PrivateKey key = KeyLoader.loadPKCS8PrivateKey(new FileInputStream("C:/nibble/facturas/ssl/sello.key"), "2010RODRIGUEZ");
            //X509Certificate cert = KeyLoader.loadX509Certificate(new FileInputStream("C:/nibble/facturas/ssl/sello.cer"));
            
             //PrivateKey key = KeyLoader.loadPKCS8PrivateKey(new FileInputStream("C:/nibble/facturas/ssl/sello.key"), "2010RODRIGUEZ");
            PrivateKey key = KeyLoaderFactory.createInstance(
                                KeyLoaderEnumeration.PRIVATE_KEY_LOADER,
                                new FileInputStream("/opt/nibble/ssl/emisor/sello.key"),
                                //"12345678a").getKey();
                                "2010RODRIGUEZ").getKey();            
            
            //X509Certificate cert = KeyLoader.loadX509Certificate(new FileInputStream("C:/nibble/facturas/ssl/sello.cer"));
            
            X509Certificate cert = KeyLoaderFactory.createInstance(
                                KeyLoaderEnumeration.PUBLIC_KEY_LOADER,
                                new FileInputStream("/opt/nibble/ssl/emisor/sello.cer")
                                ).getKey();
            
            Comprobante sellado = cfd.sellarComprobante(key, cert);
            
            cfd.validar();
            cfd.verificar();
            FileOutputStream newOut = new FileOutputStream("/opt/nibble/xml/factura_"+ configuracionpva.getVchserie()+"_" +bean91.getFolio() +".xml");
            cfd.guardar(newOut);
            
            
            float totalFactura;
            float total = 0;
            float ivaMonto = 0;
            float descuentoMonto = 0;

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
            report.addParam(METODO_PAGO,sellado.getMetodoDePago());
            report.addParam(CUENTA_PAGO,sellado.getNumCtaPago());
            report.addParam(CADENA_ORIGINAL, cfd.getCadenaOriginal().toString());
            report.addParam(SELLO_DIGITAL_EMISOR,sellado.getSello());
            String numIntPrint ="";
            if (sellado.getReceptor().getDomicilio().getNoInterior()==null || 
                    sellado.getReceptor().getDomicilio().getNoInterior() == "NULL")
                 numIntPrint="";
            else
                numIntPrint = sellado.getReceptor().getDomicilio().getNoInterior();
            
            String localidadPrint ="";
            if (sellado.getEmisor().getDomicilioFiscal().getLocalidad()== null || 
                    sellado.getEmisor().getDomicilioFiscal().getLocalidad().equalsIgnoreCase("NULL") ||
                    sellado.getEmisor().getDomicilioFiscal().getLocalidad().equals("NULL") ||
                    sellado.getEmisor().getDomicilioFiscal().getLocalidad().equals("") ||
                    sellado.getEmisor().getDomicilioFiscal().getLocalidad().isEmpty())
                 localidadPrint="";
            else
                localidadPrint = sellado.getEmisor().getDomicilioFiscal().getLocalidad();
            
            report.addParam(DOM_EMISOR, sellado.getEmisor().getDomicilioFiscal().getCalle() +" "                    
                    + sellado.getEmisor().getDomicilioFiscal().getNoExterior() + " "
                    + numIntPrint +", "
                    + sellado.getEmisor().getDomicilioFiscal().getColonia() +", "
                    + localidadPrint +", "
                    + sellado.getEmisor().getDomicilioFiscal().getMunicipio() +", "
                    + sellado.getEmisor().getDomicilioFiscal().getEstado() +", "
                    + sellado.getEmisor().getDomicilioFiscal().getPais() +", C.P. "
                    + sellado.getEmisor().getDomicilioFiscal().getCodigoPostal());

            report.addParam(FECHA, Fecha.formatoFacturaElectronica(sellado.getFecha()));
            report.addParam(LOGO,"/opt/nibble/imagenes/logo_vla.png");
            report.addParam(RAZON_SOCIAL, bean91p.getClienteRazonSocial());
            String numInt ="";
            if (sellado.getReceptor().getDomicilio().getNoInterior()==null || 
                    sellado.getReceptor().getDomicilio().getNoInterior() == "NULL")
                 numInt="";
            else
                numInt = sellado.getReceptor().getDomicilio().getNoInterior();
            String localidad ="";
            if (sellado.getReceptor().getDomicilio().getLocalidad()==null || 
                    sellado.getReceptor().getDomicilio().getLocalidad() == "NULL")
                 localidad="";
            else
                localidad = sellado.getReceptor().getDomicilio().getLocalidad();
            String domicilioReceptor = sellado.getReceptor().getDomicilio().getCalle() +" "
                    + sellado.getReceptor().getDomicilio().getNoExterior() +", "
                    + numInt +", "
                    + sellado.getReceptor().getDomicilio().getColonia() +", "
                    + localidad +", "
                    + sellado.getReceptor().getDomicilio().getMunicipio() +", "
                    + sellado.getReceptor().getDomicilio().getEstado() +", "
                    + sellado.getReceptor().getDomicilio().getPais() +", C.P. "
                    + sellado.getReceptor().getDomicilio().getCodigoPostal();
            domicilioReceptor = domicilioReceptor.toUpperCase().replaceAll(", ,", ",");
            
                    
                    
            report.addParam(DOM_RECEPTOR, (domicilioReceptor));
            report.addParam(R_F_C, bean91p.getClienteRFC());            
            report.addParam(F_IVA,
                bean91.isMostradorSinIVA() ? new Double(0.0)
                                           : new Double(bean91.getIva()));
            report.addParam(DESCUENTO, new Double(bean91.getMontodescuento()));
            report.addParam(MOTIVO_DESCUENTO, bean91.getMotivoDescuento());
            
            
            data = new Object[bean91.getVPartidasSize()][COLUMNS];

            for (int i = 0; i < bean91.getVPartidasSize(); i++) {
            	data[i][FOLIO] =  new Integer (bean91.getVPartidasFolio(i));
                data[i][CANTIDAD] = new Integer(bean91.getVPartidasCantidad(i));
                data[i][UNIDAD] = bean91.getVPartidasUnidadDesc(i); 
                data[i][NO_PARTE] = bean91.getVPartidasVchnumparte(i);
                data[i][DESCRIPCION] = bean91.getVPartidasDescripcion(i);
                data[i][NIVEL] = bean91.getVPartidasVchnivel(i);
                data[i][PRECIO] = new Double(bean91.getVPartidasPrecioUnitario(
                            i));
                data[i][IMPORTE] = new Double(bean91.getVPartidasPrecioUnitario(
                            i) * bean91.getVPartidasCantidad(i));
                logger.debug(data[i]);
            }

            report.setData(data);
            request.setAttribute(ReportConstants.REPORT_REQUEST, report);
            String filename = "FACTURA_"+bean91p.getClienteRazonSocial()+ "_"
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
