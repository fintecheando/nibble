package pva.servlets8;

/**
 * Insert the type's description here.
 * Creation date: (11/28/2002 4:02:29 PM)
 * @author: 
 */
import java.awt.Color;
import java.io.ByteArrayOutputStream;
import javax.servlet.*;

import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;

import dbrbeans.inventario.*;
import dbbeans.clasificproveedor.*;

import java.util.Vector;
import org.nibble.util.Formato;
import org.nibble.util.Celda;
import org.nibble.service.mostrador.Calcula;

import org.nibble.util.ReportConstants;
import org.nibble.util.ReportTemplate;

public class ReporteValuacionInventario extends org.nibble.main.MainServlet {
    
    /* 
     * Modificacion: Se agregan las variables del reporte.
     * Fecha: 19/02/04
     * Autor: Ernesto Ram�rez
     */
    private static final int NUM_PARTE = 0;
    private static final int RAZON_SOCIAL = 1;
    private static final int EXISTENCIA = 2;
    private static final int VALUACION = 3;
    private static final int TOTAL = 4;
    private static final int COLUMNAS = 5;
    
    // constantes para los par�metro del reporte
    private static final String CATEGORIA = "CATEGORIA";
    private static final String GRUPO = "GRUPO";
    private static final String LINEA = "LINEA";
    private static final String MARCA = "MARCA";
    private static final String SUM = "SUM";
    
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
public void performTask(
    javax.servlet.http.HttpServletRequest request,
    javax.servlet.http.HttpServletResponse response) {
        
    String[] headers = { 
                        "NUM_PARTE", "RAZON_SOCIAL", "EXISTENCIA", "VALUACION", "TOTAL"
                        };
    Object[][] data;
    ReportTemplate report;

    try {
        //addressType = 1; 19/02/04
        addressType = 0;
        initConnection();

        seguridad.setConnection(con);
	if(!seguridad.tienePermiso(sistema,new Integer(ses_usr.getAttribute("grupo").toString()).intValue(),idModulo))throw new Exception("No tiene permiso a este m�dulo");		
        
	int accion=1;
		
        dbrINVENTARIOSs inventarios = new dbrINVENTARIOSs();
	dbCLASIFICPROVEEDORs cps = new dbCLASIFICPROVEEDORs();
        
        inventarios.setConnection(con);
        cps.setConnection(con);
        
        report = new ReportTemplate();
        report.setReportName("/val_xparte");
        report.setHeaders(headers);
	report.setReportType(ReportConstants.PDF_TYPE);
        
        report.addParam(CATEGORIA,  request.getParameter("vchcategoria"));
        report.addParam(GRUPO, request.getParameter("vchgrupo"));
        report.addParam(LINEA, request.getParameter("vchlinea"));
        report.addParam(MARCA, request.getParameter("vchmarca"));
     
        if (request.getParameter("accion") != null
            && request.getParameter("accion").length() > 0)
            accion = new Integer(request.getParameter("accion")).intValue();
   
	int iidcategoria = 0;
        if (request.getParameter("iidcategoria") != null
            && request.getParameter("iidcategoria").length() > 0)
            iidcategoria = new Integer(request.getParameter("iidcategoria")).intValue();
        int iidgrupo = 0;
        if (request.getParameter("iidgrupo") != null
            && request.getParameter("iidgrupo").length() > 0)
            iidgrupo = new Integer(request.getParameter("iidgrupo")).intValue();
        int iidlinea = 0;
        if (request.getParameter("iidlinea") != null
            && request.getParameter("iidlinea").length() > 0)
            iidlinea = new Integer(request.getParameter("iidlinea")).intValue();
        int iidmarca = 0;
        if (request.getParameter("iidmarca") != null
            && request.getParameter("iidmarca").length() > 0)
            iidmarca = new Integer(request.getParameter("iidmarca")).intValue();

        inventarios.findByClasificacion(iidcategoria,iidgrupo,iidlinea,iidmarca);

        java.util.Hashtable proveedores = cps.findByClasificacion(iidcategoria,iidgrupo,iidlinea,iidmarca);

        Vector result = inventarios.getResult();
        
        data = new Object[result.size()][COLUMNAS];
        
        dbrINVENTARIO inventario;
        Float valuacionFloat;
        float valuacion;
        float total;
        float sum=0;
        
        for (int i = 0; i<result.size(); i++) {
            inventario = (dbrINVENTARIO) result.elementAt(i);
            
            data[i][NUM_PARTE] = inventario.getVchnumparte();
            data[i][RAZON_SOCIAL] = inventario.getVchrazonsocial();
            data[i][EXISTENCIA] = String.valueOf(inventario.getIexistencia());
            
            valuacionFloat = (Float) proveedores.get(new Integer(inventario.getIidproveedor()));
            valuacion = Calcula.redondeo(valuacionFloat.floatValue() * inventario.getDecpreciob100());
            total = Calcula.redondeo(valuacion * inventario.getIexistencia());
            sum += total;
            
            data[i][VALUACION] = String.valueOf(valuacion);
            data[i][TOTAL] = String.valueOf(total);
        }
        
        report.addParam(SUM, String.valueOf(sum));
        report.setData(data);
        
        if (accion == 1)  
            request.setAttribute(ReportConstants.REPORT_REQUEST, report);
       
/*
        Font font = new Font();
        Font fontHeader = new Font();
        font = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.NORMAL);
        fontHeader = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLDITALIC);

        Color color;
        Color bgColor = new Color(0xFF, 0xFF, 0xFF);
        Color bgAltColor = new Color(0xEA, 0xED, 0xF1);
        Color bgHeadColor = new Color(0xE3, 0xEA, 0xF2);

        Document document = new Document(PageSize.LETTER, 25, 25, 15, 15);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PdfWriter.getInstance(document, baos);
        document.addAuthor("AAIDA");
        document.addSubject("Reporte de Valuacion de Inventarios.");

        HeaderFooter header = new HeaderFooter(new Phrase("AAIDA Reporte de Valuacion de Inventarios.",font), false);
        header.setBorder(0);
        document.setHeader(header);

        HeaderFooter footer = new HeaderFooter(new Phrase("P�gina: ",font), true);
        footer.setBorder(0);
        document.setFooter(footer);

        document.open();

        Table head = new Table(4);
        int headerwidths[] = { 25, 25, 25, 25 };
        head.setWidths(headerwidths);
        head.setWidth(100);
        head.setPadding(1);
        head.setSpacing(0);
        
        head.addCell(new Celda("Categor�a",fontHeader,bgHeadColor,0).getCell());
        head.addCell(new Celda("Grupo",fontHeader,bgHeadColor,0).getCell());
        head.addCell(new Celda("L�nea",fontHeader,bgHeadColor,0).getCell());
        head.addCell(new Celda("Marca",fontHeader,bgHeadColor,0).getCell());

        head.addCell(new Celda(request.getParameter("vchcategoria"),font,bgColor,1).getCell());
        head.addCell(new Celda(request.getParameter("vchgrupo"),font,bgColor,1).getCell());
        head.addCell(new Celda(request.getParameter("vchlinea"),font,bgColor,1).getCell());
        head.addCell(new Celda(request.getParameter("vchmarca"),font,bgColor,1).getCell());
        
        
        Table datatable = new Table(5);
        int headerwidths2[] = { 20, 20, 20, 20, 20 };
        datatable.setWidths(headerwidths2);
        datatable.setWidth(100);
        datatable.setPadding(1);
        datatable.setSpacing(0);

      
        datatable.addCell(new Celda("N�mero de parte",fontHeader,bgHeadColor,0).getCell());
        datatable.addCell(new Celda("Raz�n social",fontHeader,bgHeadColor,0).getCell());
        datatable.addCell(new Celda("Exitencia",fontHeader,bgHeadColor,0).getCell());
        datatable.addCell(new Celda("Valuaci�n",fontHeader,bgHeadColor,0).getCell());
        datatable.addCell(new Celda("Total",fontHeader,bgHeadColor,0).getCell());
        datatable.endHeaders();

        dbrINVENTARIO inventario;
        Float valuacionFloat;
        float valuacion;
        float total;
        float sum=0;
        
        for (int i = 0; i < result.size(); i++) {
	        if (i % 2 == 0)color = bgColor;
		 	else color = bgAltColor;
		 	
            inventario = (dbrINVENTARIO) result.elementAt(i);
            
			datatable.addCell(new Celda(inventario.getVchnumparte(),font,color,1).getCell());
			datatable.addCell(new Celda(inventario.getVchrazonsocial(),font,color,1).getCell());
			datatable.addCell(new Celda(String.valueOf(inventario.getIexistencia()),font,color,2).getCell());

			valuacionFloat = (Float) proveedores.get(new Integer(inventario.getIidproveedor()));
			valuacion = Calcula.redondeo(valuacionFloat.floatValue() * inventario.getDecpreciob100());
			total = Calcula.redondeo(valuacion * inventario.getIexistencia());
			sum += total;

			datatable.addCell(new Celda(Formato.formateoNumerico(valuacion,"#########0.00"),font,color,2).getCell());
			datatable.addCell(new Celda(Formato.formateoNumerico(total,"#########0.00"),font,color,2).getCell());
        }

        Table foot = new Table(2);
        int headerwidths3[] = { 80, 20 };
        foot.setWidths(headerwidths3);
        foot.setWidth(100);
        foot.setPadding(1);
        foot.setSpacing(0);

        foot.addCell(new Celda("Total",fontHeader,bgHeadColor,2).getCell());
        foot.addCell(new Celda(Formato.formateoNumerico(sum,"#########0.00"),fontHeader,bgHeadColor,2).getCell());

        document.add(head);
  
        if (accion == 1)
        document.add(datatable);
        document.add(foot);
        document.close();

        response.setContentType("application/pdf");
        response.setContentLength(baos.size());
        ServletOutputStream out = response.getOutputStream();
        baos.writeTo(out);
        out.flush();
*/
       
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
