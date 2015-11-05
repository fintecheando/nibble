package org.nibble.service.mostrador;

import dbbeans.partesugerencia.*;
import org.nibble.util.*;

public class SugerirParte extends org.nibble.main.MainServlet {

public void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, java.io.IOException {
	performTask(request, response);
}
public void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, java.io.IOException {
	performTask(request, response);
}
public void init() {
	super.init();
}
/**
 * Crea un registro en dbPARTESUGERENCIA
 * Creation date: (11/28/2002 4:02:29 PM)
 * @author: 
 */
 
public void performTask(
    javax.servlet.http.HttpServletRequest request,
    javax.servlet.http.HttpServletResponse response) {
    try {
        initConnection();

        seguridad.setConnection(con);
	  	if(!seguridad.tienePermiso(sistema,new Integer(ses_usr.getAttribute("grupo").toString()).intValue(),idModulo))throw new Exception("No tiene permiso a este m�dulo");		

        addressType=1;
        dbPARTESUGERENCIA ps = new dbPARTESUGERENCIA();
        ps.setConnection(con);
        ps.setTisfecha(Fecha.getCurrentDate("yyyyMMddHHmmss"));       
        ps.setVchdescripcion(request.getParameter("descripcion"));
        ps.create();
    } catch (Throwable e) {
		commit = false;
        e.printStackTrace();
        error.setErrorMessage(e.getMessage());
        blnHtml = false;
        strAddress = "/jsp/error.jsp";
    } finally {
        finalizeConnection();
    }
}
}
