package pva.servlets10;

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
import pva.beans10.Bean102;
import org.nibble.util.Celda;

import org.nibble.util.ReportConstants;
import org.nibble.util.ReportTemplate;

public class ImprimeCorteCaja extends org.nibble.main.MainServlet {
    
    /* 
     * Modificacion: Se agregan las variables del reporte.
     * Fecha: 21/02/04
     * Autor: Ernesto Ram�rez
     */
    
    private static final int TIPO_CORTE = 0;
    private static final int CANTIDAD = 1;
    private static final int NUM_DOC = 2;
    private static final int REFERENCIA = 3;
    private static final int NUM_CLIENTE = 4;
    private static final int NOMBRE = 5;
    private static final int IMPORTE = 6;
    private static final int DESCUENTO = 7;
    private static final int IVA = 8;
    private static final int TOTAL = 9;
    private static final int COLUMNAS = 10;
    
    // constantes para los par�metros del reporte
    private static String FECHA_ACTUAL = "FECHA_ACTUAL";
    private static String FECHA_CORTE = "FECHA_CORTE";
    
    private int j = 0;
    private int i = 0;
    
    
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
 * Process incoming requests for information
 * 
 * @param request Object that encapsulates the request to the servlet 
 * @param response Object that encapsulates the response from the servlet
 */
public void performTask(javax.servlet.http.HttpServletRequest request,javax.servlet.http.HttpServletResponse response){
    String[] headers = {
                        "TIPO_CORTE","CANTIDAD", "NUM_DOC", "REFERENCIA", "NUM_CLIENTE", "NOMBRE", "IMPORTE",
                        "DESCUENTO", "IVA", "TOTAL"
                        };
                        
    Object[][] data;
    ReportTemplate report;
    try {
        //addressType = 1; 21/02/04
        addressType = 0;
        initConnection();
        
        
        Bean102 bean102 = (Bean102) ses_usr.getAttribute("bean102");
       
        
        report = new ReportTemplate();
        report.setReportName("/corte_caja");
        report.setHeaders(headers);
	report.setReportType(ReportConstants.PDF_TYPE);
        
        report.addParam(FECHA_ACTUAL, org.nibble.util.Fecha.getCurrentDate("dd/MM/yyyy"));
        report.addParam(FECHA_CORTE, bean102.getFecha());
        
        int cont = bean102.getContadosize();
        int cred = bean102.getCreditosize();
        int dev = bean102.getDevolucionsize() ;
        int can = bean102.getCancelacionsize();
        int bon = bean102.getBonificacionsize();
        int car = bean102.getCargosize();
        
        int largo = cont + cred + dev + can + bon + car ;
        
        data = new Object[largo][COLUMNAS]; 
        
        // despliega el Corte de Ventas de Contado
        for(i=0; i < bean102.getContadosize(); i++) { 
            data[i][TIPO_CORTE] = new String("Ventas de contado:");   
            data[i][CANTIDAD] = String.valueOf(bean102.getContadosize());
            data[i][NUM_DOC] = String.valueOf(i+1);
            data[i][REFERENCIA] = String.valueOf(bean102.getIiddocto(i,1));   
            data[i][NOMBRE] = bean102.getNombrecliente(i,1); 
            data[i][NUM_CLIENTE] = String.valueOf(bean102.getIidcliente(i,1));
            data[i][IMPORTE] = new Double(bean102.getDecimporte(i,1));
            data[i][DESCUENTO] = new Double(bean102.getDecdesc(i,1));
            data[i][IVA] = new Double(bean102.getDeciva(i,1));
            data[i][TOTAL] = new Double(bean102.getDectotal(i, 1));
        }
        
       // despliega el Corte de Ventas de Cr�dito
       j = 0;
       for(i = cont; i < (cont+cred); i++) { 
            data[i][TIPO_CORTE] = new String("Ventas de credito:");   
            data[i][CANTIDAD] = String.valueOf(bean102.getCreditosize());
            data[i][NUM_DOC] = String.valueOf(j+1);
            data[i][REFERENCIA] = String.valueOf(bean102.getIiddocto(j,2));   
            data[i][NOMBRE] = bean102.getNombrecliente(j,2); 
            data[i][NUM_CLIENTE] = String.valueOf(bean102.getIidcliente(j,2));
            data[i][IMPORTE] = new Double(bean102.getDecimporte(j,2));
            data[i][DESCUENTO] = new Double(bean102.getDecdesc(j,2));
            data[i][IVA] = new Double(bean102.getDeciva(j,2));
            data[i][TOTAL] = new Double(bean102.getDectotal(j, 2));
            
            j++;
       }
       
        // despliega el Corte de Devoluciones
        j = 0;
        for(i = (cont+cred); i < (cont+cred+dev); i++) { 
            data[i][TIPO_CORTE] = new String("Devoluciones:");   
            data[i][CANTIDAD] = String.valueOf(bean102.getDevolucionsize());
            data[i][NUM_DOC] = String.valueOf(j+1);
            data[i][REFERENCIA] = String.valueOf(bean102.getIiddocto(j,3));   
            data[i][NOMBRE] = bean102.getNombrecliente(j,3); 
            data[i][NUM_CLIENTE] = String.valueOf(bean102.getIidcliente(j,3));
            data[i][IMPORTE] = new Double(bean102.getDecimporte(j,3));
            data[i][DESCUENTO] = new Double(bean102.getDecdesc(j,3));
            data[i][IVA] = new Double(bean102.getDeciva(j,3));
            data[i][TOTAL] = new Double(bean102.getDectotal(j, 3));
            
            j++;
        }

        // despliega el corte de Cancelaciones
        j = 0;
        for(i = (cont+cred+dev); i < (cont+cred+dev+can); i++) { 
            data[i][TIPO_CORTE] = new String("Cancelaciones:");   
            data[i][CANTIDAD] = String.valueOf(bean102.getCancelacionsize());
            data[i][NUM_DOC] = String.valueOf(j+1);
            data[i][REFERENCIA] = String.valueOf(bean102.getIiddocto(j,4));   
            data[i][NOMBRE] = bean102.getNombrecliente(j,4); 
            data[i][NUM_CLIENTE] = String.valueOf(bean102.getIidcliente(j,4));
            data[i][IMPORTE] = new Double(bean102.getDecimporte(j,4));
            data[i][DESCUENTO] = new Double(bean102.getDecdesc(j,4));
            data[i][IVA] = new Double(bean102.getDeciva(j,4));
            data[i][TOTAL] = new Double(bean102.getDectotal(j,4));
           
            j++;
        }   
        
        // despliega el Corte de Bonificaciones
        j = 0;
        for(i = (cont+cred+dev+can); i < (cont+cred+dev+can+bon); i++) { 
            data[i][TIPO_CORTE] = new String("Bonificaciones:");   
            data[i][CANTIDAD] = String.valueOf(bean102.getBonificacionsize());
            data[i][NUM_DOC] = String.valueOf(j+1);
            data[i][REFERENCIA] = String.valueOf(bean102.getIiddocto(j,5));   
            data[i][NOMBRE] = bean102.getNombrecliente(j,5); 
            data[i][NUM_CLIENTE] = String.valueOf(bean102.getIidcliente(j,5));
            data[i][IMPORTE] = new Double(bean102.getDecimporte(j,5));
            data[i][DESCUENTO] = new Double(bean102.getDecdesc(j,5));
            data[i][IVA] = new Double(bean102.getDeciva(j,5));
            data[i][TOTAL] = new Double(bean102.getDectotal(j, 5));
            
            j++;
        }
        
        // despliega el Corte de Cargos
        j = 0;
        for(i = (cont+cred+dev+can+bon); i < (cont+cred+dev+can+bon+car); i++) { 
            data[i][TIPO_CORTE] = new String("Cargos:");   
            data[i][CANTIDAD] = String.valueOf(bean102.getCargosize());
            data[i][NUM_DOC] = String.valueOf(j+1);
            data[i][REFERENCIA] = String.valueOf(bean102.getIiddocto(j,6));   
            data[i][NOMBRE] = bean102.getNombrecliente(j,6); 
            data[i][NUM_CLIENTE] = String.valueOf(bean102.getIidcliente(j,6));
            data[i][IMPORTE] = new Double(bean102.getDecimporte(j,6));
            data[i][DESCUENTO] = new Double(bean102.getDecdesc(j,6));
            data[i][IVA] = new Double(bean102.getDeciva(j,6));
            data[i][TOTAL] = new Double(bean102.getDectotal(j,6));
            
            j++;
        }
        
        report.setData(data);
	request.setAttribute(ReportConstants.REPORT_REQUEST, report);

/*
        Font font = new Font();
        Font fontHeader = new Font();
        font = FontFactory.getFont(FontFactory.COURIER, 10, Font.NORMAL);
        fontHeader = FontFactory.getFont(FontFactory.COURIER, 10, Font.BOLDITALIC);

        Color color;
        Color bgColor = new Color(0xFF, 0xFF, 0xFF);
        Color bgAltColor = new Color(0xEA, 0xED, 0xF1);
        Color bgHeadColor = new Color(0xE3, 0xEA, 0xF2);
        
        Document document = new Document(PageSize.LETTER, 25, 25, 15, 15);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PdfWriter.getInstance(document, baos);
        document.addAuthor("AAIDA");
        document.addSubject("Corte de Caja");

        HeaderFooter header = new HeaderFooter(new Phrase("AAIDA Corte de Caja", font), false);
        header.setBorder(0);
        document.setHeader(header);

        HeaderFooter footer = new HeaderFooter(new Phrase("P�gina: ", font), true);
        footer.setBorder(0);
        document.setFooter(footer);

        document.open();

        // Encabezado

        document.add(new Phrase(util.Fecha.getCurrentDate("dd/MM/yyyy"), fontHeader));
        
        Table head = new Table(8);

        int headerwidths[] = { 5,5,5,25,15,15,15,15 };
        head.setWidths(headerwidths);
        head.setWidth(100);
       
        head.addCell(new Celda("No.Doc", fontHeader,bgHeadColor,0).getCell());
        head.addCell(new Celda("Referencia", fontHeader,bgHeadColor,0).getCell());
        head.addCell(new Celda("No.Cliente", fontHeader,bgHeadColor,0).getCell());
        head.addCell(new Celda("Nombre",fontHeader,bgHeadColor,0).getCell());        
        head.addCell(new Celda("Importe",fontHeader,bgHeadColor,0).getCell());
        head.addCell(new Celda("Desc", fontHeader,bgHeadColor,0).getCell());
        head.addCell(new Celda("I.V.A",fontHeader,bgHeadColor,0).getCell());
        head.addCell(new Celda("Total", fontHeader,bgHeadColor,0).getCell());
        
        head.addCell(new Celda("Ventas de Contado ("+new Integer(bean102.getContadosize()).toString()+")", font,bgColor,1,8).getCell());      
	    for (int i = 0; i < bean102.getContadosize(); i++) {
			if (i % 2 == 0)color = bgColor;
		 	else color = bgAltColor; 
			 head.addCell(new Celda(new Integer(i+1).toString(),font,color,0).getCell());
			 head.addCell(new Celda(new Integer(bean102.getIiddocto(i,1)).toString(),font,color,0).getCell());
			 head.addCell(new Celda(new Integer(bean102.getIidcliente(i,1)).toString(),font,color,0).getCell());
			 head.addCell(new Celda(bean102.getNombrecliente(i,1),font,color,0).getCell());
			 head.addCell(new Celda(bean102.getDecimporte(i,1),font,color,2).getCell());
			 head.addCell(new Celda(bean102.getDecdesc(i,1),font,color,2).getCell());
			 head.addCell(new Celda(bean102.getDeciva(i,1),font,color,2).getCell());
			 head.addCell(new Celda(bean102.getDectotal(i,1),font,color,2).getCell());		 	 
        }

        head.addCell(new Celda("Ventas de Credito ("+new Integer(bean102.getCreditosize()).toString()+")", font,bgColor,1,8).getCell());      
	    for (int i = 0; i < bean102.getCreditosize(); i++) {
			if (i % 2 == 0)color = bgColor;
		 	else color = bgAltColor; 
			 head.addCell(new Celda(new Integer(i+1).toString(),font,color,0).getCell());
			 head.addCell(new Celda(new Integer(bean102.getIiddocto(i,2)).toString(),font,color,0).getCell());
			 head.addCell(new Celda(new Integer(bean102.getIidcliente(i,2)).toString(),font,color,0).getCell());
			 head.addCell(new Celda(bean102.getNombrecliente(i,2),font,color,0).getCell());
			 head.addCell(new Celda(bean102.getDecimporte(i,2),font,color,2).getCell());
			 head.addCell(new Celda(bean102.getDecdesc(i,2),font,color,2).getCell());
			 head.addCell(new Celda(bean102.getDeciva(i,2),font,color,2).getCell());
			 head.addCell(new Celda(bean102.getDectotal(i,2),font,color,2).getCell());		 	 
        }

        head.addCell(new Celda("Devoluciones ("+new Integer(bean102.getDevolucionsize()).toString()+")", font,bgColor,1,8).getCell());
	    for (int i = 0; i < bean102.getDevolucionsize(); i++) {
			if (i % 2 == 0)color = bgColor;
		 	else color = bgAltColor; 
			 head.addCell(new Celda(new Integer(i+1).toString(),font,color,0).getCell());
			 head.addCell(new Celda(new Integer(bean102.getIiddocto(i,3)).toString(),font,color,0).getCell());
			 head.addCell(new Celda(new Integer(bean102.getIidcliente(i,3)).toString(),font,color,0).getCell());
			 head.addCell(new Celda(bean102.getNombrecliente(i,3),font,color,0).getCell());
			 head.addCell(new Celda(bean102.getDecimporte(i,3),font,color,2).getCell());
			 head.addCell(new Celda(bean102.getDecdesc(i,3),font,color,2).getCell());
			 head.addCell(new Celda(bean102.getDeciva(i,3),font,color,2).getCell());
			 head.addCell(new Celda(bean102.getDectotal(i,3),font,color,2).getCell());		 	 
        }

        head.addCell(new Celda("Cancelaciones ("+new Integer(bean102.getCancelacionsize()).toString()+")", font,bgColor,1,8).getCell());
	    for (int i = 0; i < bean102.getCancelacionsize(); i++) {
			if (i % 2 == 0)color = bgColor;
		 	else color = bgAltColor; 
			 head.addCell(new Celda(new Integer(i+1).toString(),font,color,0).getCell());
			 head.addCell(new Celda(new Integer(bean102.getIiddocto(i,4)).toString(),font,color,0).getCell());
			 head.addCell(new Celda(new Integer(bean102.getIidcliente(i,4)).toString(),font,color,0).getCell());
			 head.addCell(new Celda(bean102.getNombrecliente(i,4),font,color,0).getCell());
			 head.addCell(new Celda(bean102.getDecimporte(i,4),font,color,2).getCell());
			 head.addCell(new Celda(bean102.getDecdesc(i,4),font,color,2).getCell());
			 head.addCell(new Celda(bean102.getDeciva(i,4),font,color,2).getCell());
			 head.addCell(new Celda(bean102.getDectotal(i,4),font,color,2).getCell());		 	 
        }
   
        head.addCell(new Celda("Bonificaciones ("+new Integer(bean102.getBonificacionsize()).toString()+")", font,bgColor,1,8).getCell());
	    for (int i = 0; i < bean102.getBonificacionsize(); i++) {
			if (i % 2 == 0)color = bgColor;
		 	else color = bgAltColor; 
			 head.addCell(new Celda(new Integer(i+1).toString(),font,color,0).getCell());
			 head.addCell(new Celda(new Integer(bean102.getIiddocto(i,5)).toString(),font,color,0).getCell());
			 head.addCell(new Celda(new Integer(bean102.getIidcliente(i,5)).toString(),font,color,0).getCell());
			 head.addCell(new Celda(bean102.getNombrecliente(i,5),font,color,0).getCell());
			 head.addCell(new Celda(bean102.getDecimporte(i,5),font,color,2).getCell());
			 head.addCell(new Celda(bean102.getDecdesc(i,5),font,color,2).getCell());
			 head.addCell(new Celda(bean102.getDeciva(i,5),font,color,2).getCell());
			 head.addCell(new Celda(bean102.getDectotal(i,5),font,color,2).getCell());		 	 
        }

        head.addCell(new Celda("Cargos("+new Integer(bean102.getCargosize()).toString()+")", font,bgColor,1,8).getCell());
	    for (int i = 0; i < bean102.getCargosize(); i++) {
			if (i % 2 == 0)color = bgColor;
		 	else color = bgAltColor; 
			 head.addCell(new Celda(new Integer(i+1).toString(),font,color,0).getCell());
			 head.addCell(new Celda(new Integer(bean102.getIiddocto(i,6)).toString(),font,color,0).getCell());
			 head.addCell(new Celda(new Integer(bean102.getIidcliente(i,6)).toString(),font,color,0).getCell());
			 head.addCell(new Celda(bean102.getNombrecliente(i,6),font,color,0).getCell());
			 head.addCell(new Celda(bean102.getDecimporte(i,6),font,color,2).getCell());
			 head.addCell(new Celda(bean102.getDecdesc(i,6),font,color,2).getCell());
			 head.addCell(new Celda(bean102.getDeciva(i,6),font,color,2).getCell());
			 head.addCell(new Celda(bean102.getDectotal(i,6),font,color,2).getCell());		 	 
        }
     
        document.add(head);

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
