/*
 *
 *
 */
package org.nibble.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * This class have the reponsabilites to generate web reports an return a bytes
 * array, this class used utilities based in jasper-reports.
 * 
 */
public class GenerateReport {
    /** Context */
    private Contexto context;
	private static Log logger = LogFactory.getLog(GenerateReport.class);
    /** ReportTemplate Object */
    private ReportTemplate report;
    private String filepath;

    /**
     * Creates a new GenerateReport object.
     *
     * @param newReport New ReportTemplate
     */
    public GenerateReport(ReportTemplate newReport) {
        this.report = newReport;
    }

    public GenerateReport() {
        
    }

    /**
     * Load subReports in the MasterReports
     *
     * @param sReport MasterReport
     *
     * @throws JRException Exception throws by jasper
     * @throws IOException DOCUMENT ME!
     */
    private void loadSubReport(ReportTemplate sReport)
        throws JRException, IOException {
        for (int i = 0; i < sReport.getSubReportsSize(); i++) {
            ReportTemplate subReport = sReport.getSubReport(i);

            if (subReport.getSubReportsSize() > 0) {
                loadSubReport(subReport);
            }

            InputStream isSub = context.getResourceAsStream(ReportConstants.REPORT_PATH
                    + subReport.getReportName() + ReportConstants.JASPER_EXT);
            JasperReport jrSubReport = (JasperReport) JRLoader.loadObject(isSub);
            sReport.addParam(subReport.getSubReportName(), jrSubReport);
            isSub.close();

            if (subReport.getData() != null) {
                if (subReport.getDsName() != null) {
                    sReport.addParam(subReport.getDsName(),
                        new WebAppDataSource(subReport.getHeaders(),
                            subReport.getData()));
                } else {
                    throw new JRException("Not found datasource name");
                }
            }
        }
    }

    /**
     * Fills and execute Report in PDF Format
     *
     * @return Bytes
     *
     * @throws JRException Exception throws by jasper
     * @throws IOException DOCUMENT ME!
     */
    private byte[] generatePDF() throws JRException {
        byte[] bytes = null;
        JasperPrint jp = null;
        InputStream is = null;
       // File fileReport = null;
        try {
			loadSubReport(report);
		
            logger.debug("Despues de subreportes");
            logger.debug(ReportConstants.REPORT_PATH
            + report.getReportName() + ReportConstants.JASPER_EXT);
            is = context.getResourceAsStream(ReportConstants.REPORT_PATH
                    + report.getReportName() + ReportConstants.JASPER_EXT);
            // fileReport= new File(context.getResourceAsString(ReportConstants.REPORT_PATH
            //+ report.getReportName() + ReportConstants.JASPER_EXT));
            logger.debug("Before to fill report");
            jp =        JasperFillManager.fillReport(is, report.getParams(),
                    new WebAppDataSource(report.getHeaders(), report.getData()));
                logger.debug("report filled");
            bytes = JasperExportManager.exportReportToPdf(jp);
		} 
        catch (Exception  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.debug(e.getMessage());
		   throw new JRException(e.getMessage());
			
		}    


        return bytes;
    }

    /**
     * Fills and execute Report in direct to Device printer
     *
     * @return byte array with object to display an depends of ContextType
     *
     * @throws JRException Excetion when generate report
     * @throws IOException File exception
     */
    private byte[] generatePRN() throws JRException, IOException {
        byte[] objInBytes = null;
        JasperPrint jasperPrint = null;
        loadSubReport(report);

        InputStream is = context.getResourceAsStream(ReportConstants.REPORT_PATH
                + report.getReportName() + ReportConstants.JASPER_EXT);

        JasperReport jasperReport = (JasperReport) JRLoader.loadObject(is);

        jasperPrint = JasperFillManager.fillReport(jasperReport,
                report.getParams(),
                new WebAppDataSource(report.getHeaders(), report.getData()));

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(jasperPrint);
        oos.flush();
        objInBytes = baos.toByteArray();
        oos.close();
        is.close();
        baos.close();

        return objInBytes;
    }

    /**
     * Generate Report is based in Report Template Object
     *
     * @param newContext ServletContext from FrontCotroller
     *
     * @return Report represented in bytes
     *
     * @throws JRException Excetion when generate report
     * @throws IOException File exception
     */
    public byte[] generateReport(Contexto newContext)
        throws JRException, IOException {
        byte[] result = null;
        this.context = newContext;

        switch (report.getReportType()) {
        case ReportConstants.PDF_TYPE:
            result = generatePDF();

            break;

        case ReportConstants.PRN_TYPE:
            result = generatePRN();

            break;
        }

        return result;
    }

    /**
     * Get Report Co
     *
     * @return Report Content Type
     */
    public String getReportContent() {
        String result = null;

        switch (report.getReportType()) {
        case ReportConstants.PDF_TYPE:
            result = ReportConstants.CONTENT_TYPE_PDF;

            break;

        case ReportConstants.PRN_TYPE:
            result = ReportConstants.CONTENT_TYPE_OBJECT;

            break;

        default:
            result = ReportConstants.CONTENT_TYPE;

            break;
        }

        return result;
    }
    
    private byte[] generateTXT() throws JRException, IOException {
        File file=new File(this.filepath);
        InputStream is = new FileInputStream(file);

        // Get the size of the file
        long length = file.length();

        // You cannot create an array using a long type.
        // It needs to be an int type.
        // Before converting to an int type, check
        // to ensure that file is not larger than Integer.MAX_VALUE.
        if (length > Integer.MAX_VALUE) {
            // File is too large
        }

        // Create the byte array to hold the data
        byte[] bytes = new byte[(int)length];

        // Read in the bytes
        int offset = 0;
        int numRead = 0;
        while (offset < bytes.length
               && (numRead=is.read(bytes, offset, bytes.length-offset)) >= 0) {
            offset += numRead;
        }

        // Ensure all the bytes have been read in
        if (offset < bytes.length) {
            throw new IOException("Could not completely read file "+file.getName());
        }
        // Close the input stream and return bytes
        is.close();
        return bytes;

    }

    public byte[] generateReporteMensualSAT(Contexto newContext, String filepath)
        throws JRException, IOException {
        byte[] result = null;
        this.context = newContext;
        this.filepath = filepath;
        result = generateTXT();
        return result;
    }
}
