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

import pva.beans8.Bean82b;
import org.nibble.util.Celda;

public class ReporteMovimientosInventario extends org.nibble.main.MainServlet {
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

    try {
        addressType = 1;
        Bean82b bean82b = (Bean82b) ses_usr.getAttribute("bean82b");

        Font font = new Font();
        Font fontHeader = new Font();
        font = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.NORMAL);
        fontHeader = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLDITALIC);

        Color color;
        Color bgColor = new Color(0xFF, 0xFF, 0xFF);
        Color bgAltColor = new Color(0xEA, 0xED, 0xF1);
        Color bgHeadColor = new Color(0xE3, 0xEA, 0xF2);
        
        Document document = new Document(PageSize.LETTER, 15, 15, 20, 20);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PdfWriter.getInstance(document, baos);
        document.addAuthor("AAIDA");
        document.addSubject("Reporte de Movimientos de Inventario.");

        HeaderFooter header = new HeaderFooter(new Phrase("Reporte de Movimientos de Inventario", font), false);
        header.setBorder(0);
        document.setHeader(header);

        HeaderFooter footer = new HeaderFooter(new Phrase("P�gina: ", font), true);
        footer.setBorder(0);
        document.setFooter(footer);

        document.open();
        
		Table datatable = new Table(6);
        int headerwidths[] = { 20, 20, 20 , 20, 10, 10 };        
        datatable.setWidths(headerwidths);
        datatable.setWidth(100);
        datatable.setPadding(1);
        datatable.setSpacing(0);  
        
        datatable.addCell(new Celda("No. de Parte",fontHeader,bgHeadColor,0).getCell());
        datatable.addCell(new Celda("Proveedor",fontHeader,bgHeadColor,0).getCell());
        datatable.addCell(new Celda("Fecha de Movimiento",fontHeader,bgHeadColor,0).getCell());
        datatable.addCell(new Celda("Nombre del Usuario",fontHeader,bgHeadColor,0).getCell());
        datatable.addCell(new Celda("Cantidad",fontHeader,bgHeadColor,0).getCell());
        datatable.addCell(new Celda("Referencia",fontHeader,bgHeadColor,0).getCell());
        datatable.endHeaders();

        int tipo=0;

        for (int i = 0; i < bean82b.getSize(); i++) {
	        if (i % 2 == 0)color = bgColor;
		 	else color = bgAltColor;

		 	if (bean82b.getTitipomov(i)!=tipo){
				datatable.addCell(new Celda("TIPO DE MOVIMIENTO: "+bean82b.getTipodemovimiento(i),font,color,1,6).getCell());
				tipo = bean82b.getTitipomov(i);
		 	}
		 	
			datatable.addCell(new Celda(bean82b.getVchnumparte(i),font,color,1).getCell());
			datatable.addCell(new Celda(bean82b.getVchrazonsocial(i),font,color,1).getCell());
			datatable.addCell(new Celda(bean82b.getFechaMovimiento(i),font,color,1).getCell());
			datatable.addCell(new Celda(bean82b.getNombreUsuario(i),font,color,1).getCell());
			datatable.addCell(new Celda(String.valueOf(bean82b.getCantidad(i)),font,color,2).getCell());
			datatable.addCell(new Celda(String.valueOf(bean82b.getReferencia(i)),font,color,2).getCell());
        }

        document.add(datatable);
        document.close();

        response.setContentType("application/pdf");
        response.setContentLength(baos.size());
        ServletOutputStream out = response.getOutputStream();
        baos.writeTo(out);
        out.flush();

    } catch (Throwable e) {
        e.printStackTrace();
        error.setErrorMessage(e.getMessage());
        blnHtml = false;
        strAddress = "/jsp/error.jsp";
    }
}
}
