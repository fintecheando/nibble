// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ImprimePedidos.java

package org.nibble.service.administracion.pedidos;

//import com.lowagie.text.*;
import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.HeaderFooter;
import com.lowagie.text.PageSize;
import com.lowagie.text.Phrase;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.PdfWriter;
import java.awt.Color;
//import java.io.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import org.nibble.main.MainServlet;
import org.nibble.service.vo.pedidos.Bean74b;
import org.nibble.util.Celda;
import org.nibble.util.Fecha;
//import main.BeanError;
//import main.MainServlet;
//import nc.beans7.Bean74b;
//import util.Celda;
//import util.Fecha;

public class ImprimePedidos extends MainServlet
{

    public ImprimePedidos()
    {
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {
        performTask(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {
        performTask(request, response);
    }

    public void init()
    {
        super.init();
    }

    public void performTask(HttpServletRequest request, HttpServletResponse response)
    {
        try
        {
            super.addressType = 1;
            Bean74b bean74b = (Bean74b)super.ses_usr.getAttribute("bean74b");
            Font font = new Font();
            Font fontHeader = new Font();
            font = FontFactory.getFont("Helvetica", 10F, 0);
            fontHeader = FontFactory.getFont("Helvetica", 10F, 3);
            Color bgColor = new Color(255, 255, 255);
            Color bgAltColor = new Color(234, 237, 241);
            Color bgHeadColor = new Color(227, 234, 242);
            Document document = new Document(PageSize.LETTER, 25F, 25F, 15F, 15F);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            PdfWriter.getInstance(document, baos);
            document.addAuthor("AAIDA");
            document.addSubject("Reporte de Pedidos");
            HeaderFooter header = new HeaderFooter(new Phrase("AAIDA Reporte de Pedidos", font), false);
            header.setBorder(0);
            document.setHeader(header);
            HeaderFooter footer = new HeaderFooter(new Phrase("P\341gina: ", font), true);
            footer.setBorder(0);
            document.setFooter(footer);
            document.open();
            document.add(new Phrase(Fecha.getCurrentDate("dd/MM/yyyy"), fontHeader));
            Table head = new Table(9);
            int headerwidths[] = {
                10, 20, 10, 10, 10, 10, 10, 10, 10
            };
            head.setWidths(headerwidths);
            head.setWidth(100F);
            head.setPadding(1.0F);
            head.setSpacing(0.0F);
            head.addCell((new Celda("No. pedido", fontHeader, bgHeadColor, 0)).getCell());
            head.addCell((new Celda("Proveedor", fontHeader, bgHeadColor, 0)).getCell());
            head.addCell((new Celda("Estatus", fontHeader, bgHeadColor, 0)).getCell());
            head.addCell((new Celda("Fecha", fontHeader, bgHeadColor, 0)).getCell());
            head.addCell((new Celda("No.Partidas", fontHeader, bgHeadColor, 0)).getCell());
            head.addCell((new Celda("P.Surtidas", fontHeader, bgHeadColor, 0)).getCell());
            head.addCell((new Celda("P.Sin surtir", fontHeader, bgHeadColor, 0)).getCell());
            head.addCell((new Celda("P.Surtido Parcial", fontHeader, bgHeadColor, 0)).getCell());
            head.addCell((new Celda("P.Canceladas", fontHeader, bgHeadColor, 0)).getCell());
            for(int i = 0; i < bean74b.getSize(); i++)
            {
                Color color;
                if(i % 2 == 0)
                    color = bgColor;
                else
                    color = bgAltColor;
                head.addCell((new Celda(String.valueOf(bean74b.getIidpedido(i)), font, color, 2)).getCell());
                head.addCell((new Celda(bean74b.getVchrazonsocial(i), font, color, 1)).getCell());
                head.addCell((new Celda(bean74b.getVchdescestatus(i), font, color, 1)).getCell());
                head.addCell((new Celda(bean74b.getTisfechelabpedido(i), font, color, 1)).getCell());
                head.addCell((new Celda(String.valueOf(bean74b.getSinototalpartidas(i)), font, color, 2)).getCell());
                head.addCell((new Celda(String.valueOf(bean74b.getSinopartrecibidas(i)), font, color, 2)).getCell());
                head.addCell((new Celda(String.valueOf(bean74b.getSinototpartpedpend(i)), font, color, 2)).getCell());
                head.addCell((new Celda(String.valueOf(bean74b.getSinopartsurtpar(i)), font, color, 2)).getCell());
                head.addCell((new Celda(String.valueOf(bean74b.getSinopartcanceladas(i)), font, color, 2)).getCell());
            }

            document.add(head);
            document.close();
            response.setContentType("application/pdf");
            response.setContentLength(baos.size());
            javax.servlet.ServletOutputStream out = response.getOutputStream();
            baos.writeTo(out);
            out.flush();
        }
        catch(Throwable e)
        {
            super.addressType = 0;
            e.printStackTrace();
            super.error.setErrorMessage(e.getMessage());
            super.blnHtml = false;
            super.strAddress = "/jsp/error.jsp";
        }
    }
}
