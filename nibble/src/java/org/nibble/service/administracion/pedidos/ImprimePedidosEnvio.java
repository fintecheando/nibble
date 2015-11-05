// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ImprimePedidosEnvio.java

package org.nibble.service.administracion.pedidos;

import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;
import dbrbeans.pedidopartidas.dbrPEDIDOPARTIDAS;
import dbrbeans.pedidopartidas.dbrPEDIDOPARTIDASs;
import dbrbeans.proveedorpais.dbrPROVEEDOR;
import java.awt.Color;
//import java.io.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Vector;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.nibble.main.MainServlet;
import org.nibble.service.vo.pedidos.Bean73c;
import org.nibble.util.Celda;
import org.nibble.util.Formato;
//import javax.servlet.*;
//import javax.servlet.http.*;
//import main.*;
//import nc.beans7.Bean73c;
//import util.Celda;
//import util.Formato;

public class ImprimePedidosEnvio extends MainServlet
{

    public ImprimePedidosEnvio()
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

    public String getServletInfo()
    {
        return super.getServletInfo();
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
            initConnection();
            super.seguridad.setConnection(super.con);
            if(!super.seguridad.tienePermiso(super.sistema, (new Integer(super.ses_usr.getAttribute("grupo").toString())).intValue(), super.idModulo))
                throw new Exception("No tiene permiso a este m\363dulo");
            dbrPROVEEDOR proveedor = new dbrPROVEEDOR();
            dbrPEDIDOPARTIDASs pedidopartidas = new dbrPEDIDOPARTIDASs();
            proveedor.setConnection(super.con);
            pedidopartidas.setConnection(super.con);
            Bean73c bean73c = (Bean73c)super.ses_usr.getAttribute("bean73c");
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
            document.addSubject("Envio de Pedidos");
            HeaderFooter header = new HeaderFooter(new Phrase("AAIDA Envio de Pedidos", font), false);
            header.setBorder(0);
            document.setHeader(header);
            HeaderFooter footer = new HeaderFooter(new Phrase("P\341gina: ", font), true);
            footer.setBorder(0);
            document.setFooter(footer);
            document.open();
            proveedor.setIidproveedor(bean73c.getIidproveedor());
            proveedor.load();
            Table head = new Table(2);
            int headerwidths[] = {
                90, 10
            };
            head.setWidths(headerwidths);
            head.setWidth(100F);
            head.setPadding(1.0F);
            head.setSpacing(0.0F);
            StringBuffer pro = new StringBuffer();
            pro.append(proveedor.getVchnombre());
            pro.append("\nRFC: ");
            pro.append(proveedor.getVchrfc());
            pro.append(" TEL:");
            pro.append(proveedor.getVchtel1());
            pro.append(" FAX:");
            pro.append(proveedor.getVchfax());
            pro.append("\nDIRECCION:");
            pro.append(proveedor.getVchcalle());
            pro.append(" ");
            pro.append(proveedor.getVchcolonia());
            pro.append(" ");
            pro.append(proveedor.getVchmunicipio());
            pro.append(" ");
            pro.append(proveedor.getVchmunicipio());
            head.addCell((new Celda("Proveedor", fontHeader, bgHeadColor, 1)).getCell());
            head.addCell((new Celda("No. de pedido", fontHeader, bgHeadColor, 1)).getCell());
            head.addCell((new Celda(pro.toString(), font, bgColor, 1)).getCell());
            head.addCell((new Celda(String.valueOf(bean73c.getIidpedido()), font, bgColor, 2)).getCell());
            Table body = new Table(5);
            int headerwidths2[] = {
                20, 20, 20, 20, 20
            };
            body.setWidths(headerwidths2);
            body.setWidth(100F);
            body.setPadding(1.0F);
            body.setSpacing(0.0F);
            body.addCell((new Celda("No. de parte", fontHeader, bgHeadColor, 0)).getCell());
            body.addCell((new Celda("Descripci\363n", fontHeader, bgHeadColor, 0)).getCell());
            body.addCell((new Celda("Cantidad", fontHeader, bgHeadColor, 0)).getCell());
            body.addCell((new Celda("Costo B100", fontHeader, bgHeadColor, 0)).getCell());
            body.addCell((new Celda("Total", fontHeader, bgHeadColor, 0)).getCell());
            pedidopartidas.findByPedido(bean73c.getIidpedido());
            Vector v = pedidopartidas.getResult();
            float suma = 0.0F;
            for(int i = 0; i < v.size(); i++)
            {
                Color color;
                if(i % 2 == 0)
                    color = bgColor;
                else
                    color = bgAltColor;
                dbrPEDIDOPARTIDAS pp = (dbrPEDIDOPARTIDAS)v.elementAt(i);
                suma += (float)pp.getIcantpedida() * pp.getDecpreciob100();
                body.addCell((new Celda(pp.getVchnumparte(), font, color, 1)).getCell());
                body.addCell((new Celda(pp.getVchdescripciones(), font, color, 1)).getCell());
                body.addCell((new Celda(String.valueOf(pp.getIcantpedida()), font, color, 2)).getCell());
                body.addCell((new Celda(Formato.formateoNumerico(pp.getDecpreciob100(), "#########0.00"), font, color, 2)).getCell());
                body.addCell((new Celda(Formato.formateoNumerico(pp.getDecpreciob100() * (float)pp.getIcantpedida(), "#########0.00"), font, color, 2)).getCell());
            }

            document.add(head);
            document.add(body);
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
        finally
        {
            finalizeConnection();
        }
    }
}
