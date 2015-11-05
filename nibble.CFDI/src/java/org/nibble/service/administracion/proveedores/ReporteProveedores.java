// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ReporteProveedores.java

package org.nibble.service.administracion.proveedores;

import dbrbeans.proveedorpais.dbrPROVEEDOR;
import dbrbeans.proveedorpais.dbrPROVEEDORs;
import java.io.IOException;
import java.util.Vector;
import javax.servlet.*;
import javax.servlet.http.*;
//import main.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.nibble.main.MainServlet;
import org.nibble.util.Fecha;
import org.nibble.util.ReportTemplate;
//import util.Fecha;
//import util.ReportTemplate;

public class ReporteProveedores extends MainServlet
{

    public ReporteProveedores()
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
        String headers[] = {
            "ID", "NOMBRE", "RAZON_SOCIAL", "RFC", "CURP", "CALLE", "CP", "PAIS", "ESTADO", "COLONIA", 
            "MUNICIPIO", "EMAIL", "TEL1", "TEL2", "FAX", "B_ORDER", "CREDITO"
        };
        try
        {
            super.addressType = 0;
            initConnection();
            super.seguridad.setConnection(super.con);
            if(!super.seguridad.tienePermiso(super.sistema, (new Integer(super.ses_usr.getAttribute("grupo").toString())).intValue(), super.idModulo))
                throw new Exception("No tiene permiso a este m\363dulo");
            dbrPROVEEDORs proveedores = new dbrPROVEEDORs();
            proveedores.setConnection(super.con);
            int delno = 0;
            if(request.getParameter("delno") != null && request.getParameter("delno").length() > 0)
                delno = (new Integer(request.getParameter("delno"))).intValue();
            int alno = 0;
            if(request.getParameter("alno") != null && request.getParameter("alno").length() > 0)
                alno = (new Integer(request.getParameter("alno"))).intValue();
            proveedores.findById(delno, alno);
            Vector result = proveedores.getResult();
            ReportTemplate report = new ReportTemplate();
            report.setReportName("/proveedores");
            report.setHeaders(headers);
            report.setReportType(0);
            report.addParam("FECHA", Fecha.getCurrentDate("dd/MM/yyyy"));
            Object data[][] = new Object[result.size()][17];
            for(int i = 0; i < result.size(); i++)
            {
                dbrPROVEEDOR proveedor = (dbrPROVEEDOR)result.elementAt(i);
                data[i][0] = String.valueOf(proveedor.getIidproveedor());
                data[i][1] = proveedor.getVchnombre();
                data[i][2] = proveedor.getVchrazonsocial();
                data[i][3] = proveedor.getVchrfc();
                data[i][4] = proveedor.getVchcurp();
                data[i][5] = proveedor.getVchcalle();
                data[i][6] = proveedor.getVchcp();
                data[i][7] = proveedor.getVchpais();
                data[i][8] = proveedor.getVchestado();
                data[i][9] = proveedor.getVchcolonia();
                data[i][10] = proveedor.getVchmunicipio();
                data[i][11] = proveedor.getVchmail();
                data[i][12] = proveedor.getVchtel1();
                data[i][13] = proveedor.getVchtel2();
                data[i][14] = proveedor.getVchfax();
                if(proveedor.getBitaceptabackorder())
                    data[i][15] = new String("SI");
                else
                    data[i][15] = new String("NO");
                if(proveedor.getBcredito())
                    data[i][16] = new String("SI");
                else
                    data[i][16] = new String("NO");
            }

            report.setData(data);
            request.setAttribute("report", report);
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

    private static Log logger;
    private static final int ID = 0;
    private static final int NOMBRE = 1;
    private static final int RAZON_SOCIAL = 2;
    private static final int RFC = 3;
    private static final int CURP = 4;
    private static final int CALLE = 5;
    private static final int CP = 6;
    private static final int PAIS = 7;
    private static final int ESTADO = 8;
    private static final int COLONIA = 9;
    private static final int MUNICIPIO = 10;
    private static final int EMAIL = 11;
    private static final int TEL1 = 12;
    private static final int TEL2 = 13;
    private static final int FAX = 14;
    private static final int B_ORDER = 15;
    private static final int CREDITO = 16;
    private static final int COLUMNS = 17;
    private static final String FECHA = "FECHA";

    static 
    {
        logger = LogFactory.getLog(ReporteProveedores.class);
    }
}
