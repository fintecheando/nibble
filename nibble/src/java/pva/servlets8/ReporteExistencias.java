package pva.servlets8;

import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;
import dbrbeans.inventario.*;
import org.nibble.util.Celda;
import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.util.Vector;
import javax.servlet.*;

/**
 * DOCUMENT ME!
 *
 * @author $author$
 * @version $Revision$
 */
public class ReporteExistencias extends org.nibble.main.MainServlet {
    /**
     * Process incoming HTTP GET requests
     *
     * @param request Object that encapsulates the request to the servlet
     * @param response Object that encapsulates the response from the servlet
     */
    public void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response)
            throws javax.servlet.ServletException, java.io.IOException {
        performTask(request, response);
    }

    /**
     * Process incoming HTTP POST requests
     *
     * @param request Object that encapsulates the request to the servlet
     * @param response Object that encapsulates the response from the servlet
     */
    public void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response)
            throws javax.servlet.ServletException, java.io.IOException {
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
    public void performTask(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) {
        try {
            addressType = 1;
            
            initConnection();
            seguridad.setConnection(con);
            if (!seguridad.tienePermiso(sistema, new Integer(ses_usr.getAttribute("grupo").toString()).intValue(), idModulo)) {
                throw new Exception("No tiene permiso a este m�dulo");
            }

            dbrINVENTARIOSs inventarios = new dbrINVENTARIOSs();
            inventarios.setConnection(con);

            int iidcategoria = 0;

            if ((request.getParameter("iidcategoria") != null) && (request.getParameter("iidcategoria").length() > 0)) {
                iidcategoria = new Integer(request.getParameter("iidcategoria")).intValue();
            }

            int iidgrupo = 0;

            if ((request.getParameter("iidgrupo") != null) && (request.getParameter("iidgrupo").length() > 0)) {
                iidgrupo = new Integer(request.getParameter("iidgrupo")).intValue();
            }

            int iidlinea = 0;

            if ((request.getParameter("iidlinea") != null) && (request.getParameter("iidlinea").length() > 0)) {
                iidlinea = new Integer(request.getParameter("iidlinea")).intValue();
            }

            int iidmarca = 0;

            if ((request.getParameter("iidmarca") != null) && (request.getParameter("iidmarca").length() > 0)) {
                iidmarca = new Integer(request.getParameter("iidmarca")).intValue();
            }

            inventarios.findByClasificacion(iidcategoria, iidgrupo, iidlinea, iidmarca);

            Vector result = inventarios.getResult();

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
            document.addSubject("Reporte de Existencias.");

            HeaderFooter header = new HeaderFooter(new Phrase("AAIDA Reporte de Existencias", font), false);
            header.setBorder(0);
            document.setHeader(header);

            HeaderFooter footer = new HeaderFooter(new Phrase("P�gina: ", font), true);
            footer.setBorder(0);
            document.setFooter(footer);

            document.open();

            Table head = new Table(4);
            int[] headerwidths = { 25, 25, 25, 25 };
            head.setWidths(headerwidths);
            head.setWidth(100);
            head.setPadding(1);
            head.setSpacing(0);

            head.addCell(new Celda("Categor�a", fontHeader, bgHeadColor, 0).getCell());
            head.addCell(new Celda("Grupo", fontHeader, bgHeadColor, 0).getCell());
            head.addCell(new Celda("L�nea", fontHeader, bgHeadColor, 0).getCell());
            head.addCell(new Celda("Marca", fontHeader, bgHeadColor, 0).getCell());

            head.addCell(new Celda(request.getParameter("vchcategoria"), font, bgColor, 1).getCell());
            head.addCell(new Celda(request.getParameter("vchgrupo"), font, bgColor, 1).getCell());
            head.addCell(new Celda(request.getParameter("vchlinea"), font, bgColor, 1).getCell());
            head.addCell(new Celda(request.getParameter("vchmarca"), font, bgColor, 1).getCell());

            Table datatable = new Table(5);
            int[] headerwidths2 = { 20, 20, 20, 20, 20 };
            datatable.setWidths(headerwidths2);
            datatable.setWidth(100);
            datatable.setPadding(1);
            datatable.setSpacing(0);

            datatable.addCell(new Celda("N�mero de parte", fontHeader, bgHeadColor, 0).getCell());
            datatable.addCell(new Celda("Raz�n social", fontHeader, bgHeadColor, 0).getCell());
            datatable.addCell(new Celda("Descripci�n", fontHeader, bgHeadColor, 0).getCell());
            datatable.addCell(new Celda("Exitencia.", fontHeader, bgHeadColor, 0).getCell());
            datatable.addCell(new Celda("Ubicaci�n.", fontHeader, bgHeadColor, 0).getCell());
            datatable.endHeaders();

            dbrINVENTARIO inventario;

            for (int i = 0; i < result.size(); i++) {
                if ((i % 2) == 0) {
                    color = bgColor;
                } else {
                    color = bgAltColor;
                }

                inventario = (dbrINVENTARIO) result.elementAt(i);

                datatable.addCell(new Celda(inventario.getVchnumparte(), font, color, 1).getCell());
                datatable.addCell(new Celda(inventario.getVchrazonsocial(), font, color, 1).getCell());
                datatable.addCell(new Celda(inventario.getVchdescripcion(), font, color, 1).getCell());
                datatable.addCell(new Celda(String.valueOf(inventario.getIexistencia()), font, color, 2).getCell());
                datatable.addCell(
                    new Celda((inventario.getVchubicacion() == null) ? "" : inventario.getVchubicacion(), font, color, 2).getCell());
            }

            document.add(head);
            document.add(datatable);
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
