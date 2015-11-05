package pva.servlets4;

import dbbeans.cliente.dbCLIENTE;
import dbbeans.cliente.dbCLIENTEs;
import dbbeans.niveldeprecio.dbNIVELDEPRECIOs;
import get.BeanMsg;
import org.nibble.main.BeanError;
import org.nibble.main.BeanUtil;
import java.sql.Connection;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import pva.beans4.Bean42;
import pva.beans4.BeanHelper42b;
import pva.beans4.BeanResult42b;

/*import dbbeans.cliente.*;
import dbbeans.niveldeprecio.*;
import get.BeanMsg;
import pva.beans4.*;*/
/**
 * DOCUMENT ME!
 *
 * @author $author$
 * @version $Revision$
 */
public class ClientesCondComerciales extends HttpServlet{ 
//main.MainServlet {
	
	private int idModulo;
    private int IDCLIENTESINIVA;
	private int IDCLIENTECONIVA;

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
		idModulo = (getServletConfig().getInitParameter("idModulo") != null)
						? new Integer(getServletConfig().getInitParameter("idModulo")).intValue() : (-1);
        IDCLIENTESINIVA = new Integer(getServletConfig().getInitParameter("IDCLIENTESINIVA")).intValue();
        IDCLIENTECONIVA = new Integer(getServletConfig().getInitParameter("IDCLIENTECONIVA")).intValue();
    }

    /**
     * DOCUMENT ME!
     *
     * @param request DOCUMENT ME!
     * @param response DOCUMENT ME!
     */
    public void performTask(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) {
		Connection con = null;
		HttpSession ses_usr = null;
		BeanUtil bu = null;
		RequestDispatcher dispatcher = null;
		String strAddress = null;
		try {
			ses_usr = request.getSession(false);
			bu = new BeanUtil();		
			con = bu.getConn();		
			bu.setSession(ses_usr);
		
			if (!bu.getSessionValues())throw new Exception("Por motivos de seguridad la sesi�n ha expirado\n Registrese nuevamente");
			if (!bu.getAcceso(idModulo,con))throw new Exception("No tiene permiso a este m�dulo");

            int accion = 1;

/*            
            initConnection();
            seguridad.setConnection(con);
            if (!seguridad.tienePermiso(sistema, new Integer(ses_usr.getAttribute("grupo").toString()).intValue(), idModulo)) {
                throw new Exception("No tiene permiso a este m�dulo");
            }
*/
            BeanHelper hlp;
            BeanHelper42b hlp42b = new BeanHelper42b();
            BeanMsg beanMsg = new BeanMsg();

            dbCLIENTE cliente = new dbCLIENTE();
            dbNIVELDEPRECIOs nps = new dbNIVELDEPRECIOs();

            cliente.setConnection(con);
            nps.setConnection(con);

            beanMsg.setMsg("");

            if (request.getParameter("accion") != null) {
                accion = new Integer(request.getParameter("accion")).intValue();
            }

            int iidnocliente = 0;

            if ((request.getParameter("iidnocliente") != null) && !request.getParameter("iidnocliente").equals("")) {
                iidnocliente = new Integer(request.getParameter("iidnocliente")).intValue();
            }

            int iidnivel = 0;

            if ((request.getParameter("iidnivel") != null) && (request.getParameter("iidnivel").length() > 0)) {
                iidnivel = new Integer(request.getParameter("iidnivel")).intValue();
            }

            int iidcatcliente = 0;

            if ((request.getParameter("iidcatcliente") != null) && (request.getParameter("iidcatcliente").length() > 0)) {
                iidcatcliente = new Integer(request.getParameter("iidcatcliente")).intValue();
            }

            short siidviaembarque = 0;

            if ((request.getParameter("siidviaembarque") != null) && (request.getParameter("siidviaembarque").length() > 0)) {
                siidviaembarque = new Short(request.getParameter("siidviaembarque")).shortValue();
            }

            float declimitecredito = 0;

            if ((request.getParameter("declimitecredito") != null) && (request.getParameter("declimitecredito").length() > 0)) {
                declimitecredito = new Float(request.getParameter("declimitecredito")).floatValue();
            }

            float deccreditutilizado = 0;

            if ((request.getParameter("deccreditutilizado") != null) && (request.getParameter("deccreditutilizado").length() > 0)) {
                deccreditutilizado = new Float(request.getParameter("deccreditutilizado")).floatValue();
            }

            short sidiadepago = 0;

            if ((request.getParameter("sidiadepago") != null) && (request.getParameter("sidiadepago").length() > 0)) {
                sidiadepago = new Short(request.getParameter("sidiadepago")).shortValue();
            }

            short siplazodiaspago = 0;

            if ((request.getParameter("siplazodiaspago") != null) && (request.getParameter("siplazodiaspago").length() > 0)) {
                siplazodiaspago = new Short(request.getParameter("siplazodiaspago")).shortValue();
            }

            boolean bitdadodebaja = false;

            if ((request.getParameter("bitdadodebaja") != null) && request.getParameter("bitdadodebaja").equalsIgnoreCase("Si")) {
                bitdadodebaja = true;
            }

            boolean bsuceptiblecredito = false;

            if (
                (request.getParameter("bsuceptiblecredito") != null)
                    && request.getParameter("bsuceptiblecredito").equalsIgnoreCase("Si")) {
                bsuceptiblecredito = true;
            }

            cliente.setIidnocliente(iidnocliente);

            switch (accion) {
                // 4.2 Condiciones comerciales
                case 1:
                    hlp42b.getData(con);

                    Bean42 bean42 = new Bean42();
                    dbCLIENTEs clientes = new dbCLIENTEs();
                    clientes.setConnection(con);
                    clientes.find("");
                    bean42.setVClientes(clientes.getResult());
                    ses_usr.setAttribute("bean42b", hlp42b.getBean42b());
                    ses_usr.setAttribute("bean42", bean42);

                    try {
                        cliente.load();
                    } catch (Exception e) {
                    }

                    break;

                case 4:
                    cliente.load();

                    break;

                // 4.2 Modificar clientes condiciones comerciales
                case 5:
                    cliente.load();

                    if ((cliente.getIidnocliente() != IDCLIENTECONIVA) && (cliente.getIidnocliente() != IDCLIENTESINIVA)) {
                        cliente.setIidnivel(iidnivel);
                        cliente.setIidcatcliente(iidcatcliente);
                        cliente.setSiidviaembarque(siidviaembarque);
                        cliente.setDeclimitecredito(declimitecredito);
                        cliente.setDeccreditutilizado(deccreditutilizado);
                        cliente.setSidiadepago(sidiadepago);
                        cliente.setSiplazodiaspago(siplazodiaspago);
                        cliente.setBsuceptiblecredito(bsuceptiblecredito);
                        cliente.setBitdadodebaja(bitdadodebaja);
                    }

                    cliente.setIidnivel(iidnivel);
                    cliente.store();
                    beanMsg.setMsg("Se modific� al cliente " + cliente.getVchnombre());

                    break;
            }

            BeanResult42b beanresult42b = new BeanResult42b();

            beanresult42b.setIidnocliente(cliente.getIidnocliente());
            beanresult42b.setIidcatcliente(cliente.getIidcatcliente());
            beanresult42b.setIidnivel(cliente.getIidnivel());
            beanresult42b.setSiidviaembarque(cliente.getSiidviaembarque());
            beanresult42b.setDeclimitecredito(cliente.getDeclimitecredito());
            beanresult42b.setDeccreditutilizado(cliente.getDeccreditutilizado());
            beanresult42b.setSidiadepago(cliente.getSidiadepago());
            beanresult42b.setSiplazodiaspago(cliente.getSiplazodiaspago());
            beanresult42b.setBsuceptiblecredito(cliente.getBsuceptiblecredito());
            beanresult42b.setBitdadodebaja(cliente.getBitdadodebaja());

            nps.findByIidcatcliente(cliente.getIidcatcliente());
            beanresult42b.setVNiveles(nps.getResult());

            if ((beanresult42b.getIidnocliente() == IDCLIENTECONIVA) || (beanresult42b.getIidnocliente() == IDCLIENTESINIVA)) {
                beanresult42b.setMostrador(true);
            }

            ses_usr.setAttribute("beanresult42b", beanresult42b);
            ses_usr.setAttribute("beanMsg", beanMsg);
            strAddress = "/jsp/4_2b.jsp";
        } catch (Throwable e) {
			bu.setCommit(false);
			e.printStackTrace();
			BeanError error = new BeanError();
			error.setErrorMessage(e.getMessage());
			request.setAttribute("error",error);
			strAddress =  "/jsp/error.jsp";
		} finally {
			try{
				bu.finalizeTransaction(con);
				dispatcher = getServletContext().getRequestDispatcher(strAddress);
				dispatcher.forward(request, response);    		
			}catch (Exception ex){
				System.out.println("FATAL:"+ex.getMessage());
			}
		}
    }
}
