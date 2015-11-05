package pva.servlets4;

import org.nibble.main.BeanError;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;

public class PreReporteClientes extends HttpServlet{ 
//main.MainServlet {
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
     *
     * @param request DOCUMENT ME!
     * @param response DOCUMENT ME!
     */
    public void performTask(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) {
		String strAddress = null;
		RequestDispatcher dispatcher = null;
        try {
            strAddress = "/jsp/4_4.jsp";
		} catch (Throwable e) {
			e.printStackTrace();

			BeanError error = new BeanError();
			error.setErrorMessage(e.getMessage());
			request.setAttribute("error", error);
			strAddress = "/jsp/error.jsp";
		} finally {
			try {
				dispatcher = getServletContext().getRequestDispatcher(strAddress);
				dispatcher.forward(request, response);
			} catch (Exception ex) {
				System.out.println("FATAL:" + ex.getMessage());
			}
		}
    }
}
