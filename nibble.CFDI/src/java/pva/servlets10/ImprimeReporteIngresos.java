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

import pva.beans10.Bean103;

import org.nibble.util.Celda;

public class ImprimeReporteIngresos extends org.nibble.main.MainServlet {
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
public void performTask(javax.servlet.http.HttpServletRequest request,javax.servlet.http.HttpServletResponse response){

    try {
        addressType = 1;
        initConnection();

        Bean103 bean103 = (Bean103) ses_usr.getAttribute("bean103");

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
        document.addSubject("Reporte de Ingresos");

        HeaderFooter header = new HeaderFooter(new Phrase("AAIDA Reporte de Ingresos", font), false);
        header.setBorder(0);
        document.setHeader(header);

        HeaderFooter footer = new HeaderFooter(new Phrase("P�gina: ", font), true);
        footer.setBorder(0);
        document.setFooter(footer);

        document.open();

        // Encabezado

        document.add(new Phrase(org.nibble.util.Fecha.getCurrentDate("dd/MM/yyyy"), fontHeader));
        

        Table head = new Table(5);

        int headerwidths[] = { 20,20,20,20,20 };
        head.setWidths(headerwidths);
        head.setWidth(100);

        head.setWidths(headerwidths);
        head.setWidth(100);
       
        head.addCell(new Celda("Documento", fontHeader,bgHeadColor,0).getCell());
        head.addCell(new Celda("Hora", fontHeader,bgHeadColor,0).getCell());
        head.addCell(new Celda("Tipo", fontHeader,bgHeadColor,0).getCell());
        head.addCell(new Celda("Aviso",fontHeader,bgHeadColor,0).getCell());        
        head.addCell(new Celda("Monto",fontHeader,bgHeadColor,0).getCell());
        
        for (int i=0; i < bean103.sizeCxc(); i++){
			if (i % 2 == 0)color = bgColor;
		 	else color = bgAltColor;         
			head.addCell(new Celda(bean103.getVchDestipodocto(i)+" "+bean103.getIidcontradocto(i), font,color,0).getCell());
			head.addCell(new Celda(bean103.getTisfechamovimiento(i), font,color,0).getCell());
			head.addCell(new Celda(bean103.getVchDestipopago(i), font,color,0).getCell());
			head.addCell(new Celda(bean103.getBitavisoing(i), font,color,0).getCell());
			head.addCell(new Celda(bean103.getDechaber(i), font,color,0).getCell());
        }

        document.add(head);

        document.close();

        response.setContentType("application/pdf");
        response.setContentLength(baos.size());
        ServletOutputStream out = response.getOutputStream();
        baos.writeTo(out);
        out.flush();

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
