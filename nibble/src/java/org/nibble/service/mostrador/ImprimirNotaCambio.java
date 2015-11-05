package org.nibble.service.mostrador;

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

import pva.beans9.*;

import org.nibble.util.Formato;
import org.nibble.util.Fecha;
import org.nibble.util.Celda;
import org.nibble.util.CantidadALetra;

import dbbeans.usuarios.*;

public class ImprimirNotaCambio extends org.nibble.main.MainServlet {
	final String formato = "#########0.00";
	final int ROWS = 10;
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
        initConnection();

        dbUSUARIOS usuario = new dbUSUARIOS();
        
        usuario.setConnection(con);      

        Bean93p bean = (Bean93p) ses_usr.getAttribute("bean");
        
        CantidadALetra conver = new CantidadALetra();

        usuario.setIidusuario(bean.getIidusuario());
        usuario.setIidsistema(3);
        usuario.load();

        float totalFactura;
        float total = 0;
        float ivaMonto = 0;
        float descuentoMonto = 0;

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
        document.addSubject("Nota de Cambio.");

        HeaderFooter header =
            new HeaderFooter(new Phrase("AAIDA Nota de Cambio", font), false);
        header.setBorder(0);
        document.setHeader(header);

        HeaderFooter footer = new HeaderFooter(new Phrase("P�gina: ", font), true);
        footer.setBorder(0);
        document.setFooter(footer);

        document.open();

        // Encabezado

        Table head = new Table(2);

        int headerwidths[] = { 70,30};
        head.setWidths(headerwidths);
        head.setWidth(100);

        head.addCell(new Celda(bean.getDatosGenerales(),fontHeader,bgColor,1).getCell());
        head.addCell(new Celda(Fecha.formatMySQL(bean.getFecha())+ " (Ref."+ bean.getReferencia() +")",fontHeader,bgColor,2).getCell());
        
        head.addCell(new Celda("Cambio fac."+bean.getIidfactura(),fontHeader,bgColor, 0, 2).getCell());

        document.add(head);

        head = new Table(3);

        int headerwidths3[] = { 30,30,40};
        head.setWidths(headerwidths3);
        head.setWidth(100);

        head.addCell(new Celda(String.valueOf(bean.getIidnocliente()),fontHeader,bgColor,3).getCell());
        head.addCell(new Celda(usuario.getVchnombreusuario(),fontHeader,bgColor,3).getCell());
        head.addCell(new Celda(bean.getVchviades(),fontHeader,bgColor,3).getCell());		
        document.add(head);

        // Cuerpo

        Table datatable = new Table(6);

        datatable.setPadding(1);
        datatable.setSpacing(0);

        int headerwidths1[] = { 25, 25, 5, 5 , 20, 20 };
        datatable.setWidths(headerwidths1);
        datatable.setWidth(100);

        datatable.addCell(new Celda("N�mero parte",fontHeader,bgHeadColor,0).getCell());
        datatable.addCell(new Celda("N�m.parte Cambio",fontHeader,bgHeadColor,0).getCell());
        datatable.addCell(new Celda("Nivel",fontHeader,bgHeadColor,0).getCell());
        datatable.addCell(new Celda("Can",fontHeader,bgHeadColor,0).getCell());
        datatable.addCell(new Celda("Precio",fontHeader,bgHeadColor,0).getCell());
        datatable.addCell(new Celda("Importe",fontHeader,bgHeadColor,0).getCell());
        
        //  Imprime  la Nota de Cr�diro Detalle
            totalFactura = 0;
            int i;
            for (i = 0;
                i < bean.getSize();
                i++) {
  	            if (i % 2 == 0)color = bgColor;
		 		else color = bgAltColor;

		 		datatable.addCell(new Celda(bean.getVchnumparte(i),font,color,1).getCell());
		 		datatable.addCell(new Celda(bean.getVchnumparteCambio(i),font,color,1).getCell());
		 		datatable.addCell(new Celda(bean.getVchnivel(i),font,color,1).getCell());
		 		datatable.addCell(new Celda(String.valueOf(bean.getIcantidad(i)),font,color,1).getCell());
		 		datatable.addCell(new Celda(Formato.formateoNumerico(bean.getDecprecio(i),formato),font,color,2).getCell());
		 		datatable.addCell(new Celda(Formato.formateoNumerico(bean.getDecimporte(i),formato),font,color,2).getCell());

                totalFactura += bean.getDecimporte(i);
            }

            document.add(datatable);

             // Llenar espacios para escribir el total
            for (int a=i; a<ROWS; a++)
	            document.add(new Paragraph("\n"));


            Table foot = new Table(3);

            int headerwidths2[] = { 50, 25,25 };
            foot.setWidths(headerwidths2);
            foot.setWidth(100);

            if (bean.getIDCLIENTESINIVA() == bean.getIidnocliente()) 
	            total = totalFactura;
	        else{
            	descuentoMonto = totalFactura * bean.getDecdescglobal() / 100;
            	ivaMonto = (totalFactura - descuentoMonto) * (bean.getIva() / 100);
            	total = totalFactura - descuentoMonto + ivaMonto;
	        }

            // Foot

            foot.addCell(new Celda(conver.Letras(total),font,bgColor,1,3).getCell());

            foot.addCell(new Celda("Subtotal  ",font,bgColor,2,2).getCell());
			foot.addCell(new Celda(Formato.formateoNumerico(totalFactura, formato),font,bgColor,2).getCell());

			if (bean.getIDCLIENTESINIVA() != bean.getIidnocliente()) {
	           	foot.addCell(new Celda("Descuento  ",font,bgColor,2,2).getCell());
            	foot.addCell(new Celda(Formato.formateoNumerico(descuentoMonto, formato),font,bgColor,2).getCell());

                foot.addCell(new Celda("IVA  ",font,bgColor,2,2).getCell());
            	foot.addCell(new Celda(Formato.formateoNumerico(ivaMonto, formato),font,bgColor,2).getCell());

			}
			
            foot.addCell(new Celda("Total  ",font,bgColor,2,2).getCell());
            foot.addCell(new Celda(Formato.formateoNumerico(total, formato),font,bgColor,2).getCell());

            
        document.add(foot);

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
