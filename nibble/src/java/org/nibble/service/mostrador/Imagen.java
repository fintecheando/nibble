package org.nibble.service.mostrador;

import dbbeans.parte.*;

import pva.beans9.Bean91;

import java.sql.*;

import javax.servlet.*;


/**
 * DOCUMENT ME!
 *
 * @author $author$
 * @version $Revision$
 */
public class Imagen extends org.nibble.main.MainServlet {
    /**
     * DOCUMENT ME!
     *
     * @param request DOCUMENT ME!
     * @param response DOCUMENT ME!
     *
     * @throws javax.servlet.ServletException DOCUMENT ME!
     * @throws java.io.IOException DOCUMENT ME!
     */
    public void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response)
            throws javax.servlet.ServletException, java.io.IOException {
        performTask(request, response);
    }

    /**
     * DOCUMENT ME!
     *
     * @param request DOCUMENT ME!
     * @param response DOCUMENT ME!
     *
     * @throws javax.servlet.ServletException DOCUMENT ME!
     * @throws java.io.IOException DOCUMENT ME!
     */
    public void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response)
            throws javax.servlet.ServletException, java.io.IOException {
        performTask(request, response);
    }

    /**
     * DOCUMENT ME!
     */
    public void init() {
        super.init();
    }

    /**
     * DOCUMENT ME!
     *
     * @param request DOCUMENT ME!
     * @param response DOCUMENT ME!
     */
    public void performTask(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) {
        try {
            int accion = 1;
            addressType = 1;
            initConnection();

            dbPARTE parte = new dbPARTE();
            parte.setConnection(con);

            Bean91 bean91 = (Bean91) ses_usr.getAttribute("bean91");

            // recupera bytes de imagen para la parte
            Blob blob = parte.getImagen(bean91.getLiidparte());

            if (blob == null) {
                throw new Exception("No hay imagen");
            }

            int length = (int) blob.length();
            byte[] _blob = blob.getBytes(1, length);

            response.setContentType("image/jpeg");
            response.setContentLength((int) blob.length());

            ServletOutputStream out = response.getOutputStream();

            // escrtibe flujo de bytes al ServletOutputStream
            out.write(_blob);
            out.flush();
        } catch (Throwable e) {
            commit = false;
            addressType = 0;
            e.printStackTrace();
            System.out.println(e);
            error.setErrorMessage(e.getMessage());
            blnHtml = false;
            strAddress = "/jsp/error.jsp";
        } finally {
            finalizeConnection();
        }
    }
}
