package pva.servlets4;

/**
 * Insert the type's description here.
 * Creation date: (11/28/2002 4:02:29 PM)
 * @author: 
 */

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


import dbrbeans.clientepaisestadomunicipio.*;

import java.util.Vector;
import org.nibble.util.Fecha;
import org.nibble.util.ReportConstants;
import org.nibble.util.ReportTemplate;

public class ReporteClientes extends org.nibble.main.MainServlet {
	
	private static Log logger = LogFactory.getLog(ReporteClientes.class);
		
	private static final int ID= 0;

	/** DOCUMENT ME!  */
	private static final int NOMBRE= 1;

	/** DOCUMENT ME!  */
	private static final int RAZON_SOCIAL = 2;

	/** DOCUMENT ME!  */
	private static final int RFC = 3;

	/** DOCUMENT ME!  */
	private static final int CALLE= 4;

	/** DOCUMENT ME!  */
	private static final int COLONIA= 5;

	private static final int CP = 6;
	/** DOCUMENT ME!  */
	private static final int EMAIL = 7;
	
	private static final int TEL1 = 8;
	
	private static final int TEL2 = 9;
	
	private static final int FAX = 10;
	
	private static final int CURP = 11;
	
	private static final int LIM_CREDITO = 12;
	
	private static final int CRED_UTILIZADO = 13;
	
	private static final int DIA_PAGO = 14;
	
	private static final int P_DIAS = 15;
	
	private static final int CREDITO = 16;
	
	private static final int PAIS = 17;
	
	private static final int ESTADO = 18;
	
	private static final int MUNICIPIO = 19;
	
	private static final int CATEGORIA = 20;
	
	private static final int NIVEL = 21;
	
	private static final int COLUMNS = 22;
        
        // variables para los par�metros del reporte
        private static final String FECHA = "FECHA";

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
	String[] headers ={ 
		"ID", "NOMBRE", "RAZON_SOCIAL", "RFC", "CALLE", "COLONIA", "CP", "EMAIL", "TEL1","TEL2","FAX","CURP","LIM_CREDITO","CRED_UTILIZADO","DIA_PAGO",
		"P_DIAS","CREDITO","PAIS","ESTADO","MUNICIPIO","CATEGORIA", "NIVEL"
		 };
	Object[][] data;
	ReportTemplate report;
    try {
        addressType = 0;
        initConnection();

        seguridad.setConnection(con);
	  	if(!seguridad.tienePermiso(sistema,new Integer(ses_usr.getAttribute("grupo").toString()).intValue(),idModulo))throw new Exception("No tiene permiso a este m�dulo");		


        dbrCLIENTEs clientes = new dbrCLIENTEs();
        clientes.setConnection(con);

		int delno = 0;
		if (request.getParameter("delno") != null && request.getParameter("delno").length()>0)
			delno = new Integer(request.getParameter("delno")).intValue();
		int alno = 0;
		if (request.getParameter("alno") != null && request.getParameter("alno").length()>0)
			alno = new Integer(request.getParameter("alno")).intValue();

		clientes.findById(delno,alno);

		System.out.println("size = "+clientes.getResult().size());

		Vector result = clientes.getResult();


		report = new ReportTemplate();
		report.setReportName("/clientes");
		report.setHeaders(headers);
		report.setReportType(ReportConstants.PDF_TYPE);
                
                report.addParam(FECHA, org.nibble.util.Fecha.getCurrentDate("dd/MM/yyyy"));
       
		data = new Object[result.size()][COLUMNS];
		dbrCLIENTE cliente;
		for (int i = 0; i < result.size(); i++) {
			cliente = (dbrCLIENTE) result.elementAt(i);
			
			data[i][ID] = String.valueOf(cliente.getIidnocliente());
			data[i][NOMBRE] = cliente.getVchnombre();
			data[i][RAZON_SOCIAL] = cliente.getVchrazonsocial();
                        
                        data[i][RFC] = cliente.getVchrfc();
                        data[i][CALLE] = cliente.getVchcalle();
                        data[i][COLONIA] = cliente.getVchcolonia();
                        data[i][CP] = cliente.getChcodigop();
                        data[i][EMAIL] = cliente.getVchmail();
                        data[i][TEL1] = cliente.getVchtel1();
			data[i][TEL2] = cliente.getVchtel2();
                        data[i][FAX] = cliente.getVchfax();
                        data[i][CURP] = cliente.getVchcurp();
                        data[i][LIM_CREDITO] = new Double(cliente.getDeclimitecredito());
                        data[i][CRED_UTILIZADO] = new Double(cliente.getDeccreditutilizado());
                        data[i][DIA_PAGO] = Fecha.obtenDia(cliente.getSidiadepago());
                        data[i][P_DIAS] = String.valueOf(cliente.getSiplazodiaspago());
                        
                        if (cliente.getBsuceptiblecredito())
				data[i][CREDITO] = new String("SI");
			else
				data[i][CREDITO] = new String("NO");
                        
                        data[i][PAIS] = cliente.getVchpaisdes();
                        data[i][ESTADO] = cliente.getVchestadodes();
                        data[i][MUNICIPIO] = cliente.getVchmunicipiodes();
                        data[i][CATEGORIA] = cliente.getVchcategoriades();
                        data[i][NIVEL] = cliente.getVchniveldes();
                        
		}
		
		report.setData(data);
		request.setAttribute(ReportConstants.REPORT_REQUEST, report);


        
/*		Font font = new Font();
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
        document.addSubject("Reporte de Clientes.");

        HeaderFooter header = new HeaderFooter(new Phrase("AAIDA Reporte de Clientes",font), false);
        header.setBorder(0);
        document.setHeader(header);

        HeaderFooter footer = new HeaderFooter(new Phrase("P�gina: ",font), true);
        footer.setBorder(0);
        document.setFooter(footer);

        document.open();

        Table datatable = new Table(8);
      
        int headerwidths[] = { 10, 10, 10, 10, 10, 10, 10, 10 };
        datatable.setWidths(headerwidths);
        datatable.setWidth(100);
        datatable.setPadding(1);
        datatable.setSpacing(0);
        
        datatable.addCell(new Celda("Id",fontHeader,bgHeadColor,0).getCell());
		datatable.addCell(new Celda("Nombre",fontHeader,bgHeadColor,0,3).getCell());
		datatable.addCell(new Celda("Raz�n social",fontHeader,bgHeadColor,0,3).getCell());
		datatable.addCell(new Celda("RFC",fontHeader,bgHeadColor,0).getCell());
		datatable.addCell(new Celda("Calle",fontHeader,bgHeadColor,0,2).getCell());
		datatable.addCell(new Celda("Colonia",fontHeader,bgHeadColor,0,2).getCell());
		datatable.addCell(new Celda("CP",fontHeader,bgHeadColor,0).getCell());
		datatable.addCell(new Celda("email",fontHeader,bgHeadColor,0,2).getCell());
		datatable.addCell(new Celda("Tel 1",fontHeader,bgHeadColor,0).getCell());
		datatable.addCell(new Celda("Tel 2",fontHeader,bgHeadColor,0).getCell());
		datatable.addCell(new Celda("Fax",fontHeader,bgHeadColor,0).getCell());
		datatable.addCell(new Celda("CURP",fontHeader,bgHeadColor,0).getCell());
		datatable.addCell(new Celda("Lim. Cr�dito",fontHeader,bgHeadColor,0).getCell());
		datatable.addCell(new Celda("Cred. utiliz.",fontHeader,bgHeadColor,0).getCell());
		datatable.addCell(new Celda("D�a de pago",fontHeader,bgHeadColor,0).getCell());
		datatable.addCell(new Celda("P. dias",fontHeader,bgHeadColor,0).getCell());
		datatable.addCell(new Celda("Cr�dito",fontHeader,bgHeadColor,0).getCell());
		datatable.addCell(new Celda("Pais",fontHeader,bgHeadColor,0).getCell());
		datatable.addCell(new Celda("Estado",fontHeader,bgHeadColor,0,2).getCell());
		datatable.addCell(new Celda("Municipio",fontHeader,bgHeadColor,0,2).getCell());
		datatable.addCell(new Celda("Categoria",fontHeader,bgHeadColor,0,2).getCell());
		datatable.addCell(new Celda("Nivel",fontHeader,bgHeadColor,0).getCell()); 
		
        

        datatable.endHeaders();


        dbrCLIENTE cliente;

        for (int i = 0; i < result.size(); i++) {
	        if (i % 2 == 0)color = bgColor;
		 	else color = bgAltColor;
	        	
            cliente = (dbrCLIENTE) result.elementAt(i);

            datatable.addCell(new Celda(String.valueOf(cliente.getIidnocliente()),font,color,1).getCell());
            datatable.addCell(new Celda(cliente.getVchnombre(),font,color,1,3).getCell());
            datatable.addCell(new Celda(cliente.getVchrazonsocial(),font,color,1,3).getCell());
            datatable.addCell(new Celda(cliente.getVchrfc(),font,color,1).getCell());
            datatable.addCell(new Celda(cliente.getVchcalle(),font,color,1,2).getCell());
            datatable.addCell(new Celda(cliente.getVchcolonia(),font,color,1,2).getCell());
            datatable.addCell(new Celda(cliente.getChcodigop(),font,color,1).getCell());
            datatable.addCell(new Celda(cliente.getVchmail(),font,color,1,2).getCell());
            datatable.addCell(new Celda(cliente.getVchtel1(),font,color,1).getCell());
            datatable.addCell(new Celda(cliente.getVchtel2(),font,color,1).getCell());
            datatable.addCell(new Celda(cliente.getVchfax(),font,color,1).getCell());
            datatable.addCell(new Celda(cliente.getVchfax(),font,color,1).getCell());
            datatable.addCell(new Celda(Formato.formateoNumerico(cliente.getDeclimitecredito(),"#########0.00"),font,color,2).getCell());
            datatable.addCell(new Celda(Formato.formateoNumerico(cliente.getDeccreditutilizado(),"#########0.00"),font,color,2).getCell());
            datatable.addCell(new Celda(Fecha.obtenDia(cliente.getSidiadepago()),font,color,2).getCell());
            datatable.addCell(new Celda(String.valueOf(cliente.getSiplazodiaspago()),font,color,2).getCell());
			if (cliente.getBsuceptiblecredito())
				datatable.addCell(new Celda("SI",font,color,1).getCell());
			else
				datatable.addCell(new Celda("NO",font,color,1).getCell());

			datatable.addCell(new Celda(cliente.getVchpaisdes(),font,color,1).getCell());
			datatable.addCell(new Celda(cliente.getVchestadodes(),font,color,1,2).getCell());
			datatable.addCell(new Celda(cliente.getVchmunicipiodes(),font,color,1,2).getCell());
			datatable.addCell(new Celda(cliente.getVchcategoriades(),font,color,1,2).getCell());
			datatable.addCell(new Celda(cliente.getVchniveldes(),font,color,1).getCell());
           
        }

        document.add(datatable);
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
