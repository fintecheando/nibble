package pva.servlets4;

import org.nibble.main.BeanError;
import org.nibble.main.BeanUtil;
import java.sql.Connection;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;

import dbbeans.cliente.*;
import get.BeanMsg;
import pva.beans4.*;

/**
 * DOCUMENT ME!
 *
 * @author $author$
 * @version $Revision$
 */
public class Clientes extends HttpServlet{
// main.MainServlet {
	private int idModulo;
    private int IDCLIENTESINIVA;
    private int IDCLIENTECONIVA;
    private short PAIS_DEFAULT;
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
        PAIS_DEFAULT = new Short(getServletConfig().getInitParameter("PAIS_DEFAULT")).shortValue();
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

            BeanHelper hlp;
            BeanHelper42 hlp42 = new BeanHelper42();
            BeanMsg beanMsg = new BeanMsg();
            beanMsg.setMsg("");

            dbCLIENTE cliente = new dbCLIENTE();
            cliente.setConnection(con);

            if (request.getParameter("accion") != null) {
                accion = new Integer(request.getParameter("accion")).intValue();
            }

            int iidnocliente = 0;

            if ((request.getParameter("iidnocliente") != null) && !request.getParameter("iidnocliente").equals("")) {
                iidnocliente = new Integer(request.getParameter("iidnocliente")).intValue();
            }

            String vchnombre = request.getParameter("vchnombre");
            String vchrazonsocial = request.getParameter("vchrazonsocial");
            String vchrfc = request.getParameter("vchrfc");
            String vchcurp = request.getParameter("vchcurp");
            boolean bcredito = false;

            if (request.getParameter("bcredito") != null) {
                bcredito = true;
            }

            String vchtel1 = request.getParameter("vchtel1");
            String vchtel2 = request.getParameter("vchtel2");
            String vchfax = request.getParameter("vchfax");
            boolean bitdadodebaja = false;

            if (request.getParameter("bitdadodebaja") != null) {
                bitdadodebaja = true;
            }

            String vchmail = request.getParameter("vchmail");
            String vchcolonia = request.getParameter("vchcolonia");
            String vchcalle = request.getParameter("vchcalle");
            String vchnumeroexterior = null;
            String vchnumerointerior = null;
            String vchlocalidad = null;

            if ((request.getParameter("vchnumeroexterior") != null) && !request.getParameter("vchnumeroexterior").equals("")) {
                vchnumeroexterior = request.getParameter("vchnumeroexterior");
            }
            if ((request.getParameter("vchnumerointerior") != null) && !request.getParameter("vchnumerointerior").equals("")) {
                vchnumerointerior = request.getParameter("vchnumerointerior");
            }            
            if ((request.getParameter("vchlocalidad") != null) && !request.getParameter("vchlocalidad").equals("")) {
                vchlocalidad = request.getParameter("vchlocalidad");
            }
            String chcodigop = request.getParameter("chcodigop");
            boolean bitaceptabackorder = false;

            if ((request.getParameter("back") != null) && request.getParameter("back").equalsIgnoreCase("Si")) {
                bitaceptabackorder = true;
            }

            short siidpais = 0;

            if ((request.getParameter("siidpais") != null) && !request.getParameter("siidpais").equals("")) {
                siidpais = new Short(request.getParameter("siidpais")).shortValue();
            }

            int iidestado = 0;

            if ((request.getParameter("iidestado") != null) && !request.getParameter("iidestado").equals("")) {
                iidestado = new Integer(request.getParameter("iidestado")).intValue();
            }

            int iidmunicipio = 0;

            if ((request.getParameter("iidmunicipio") != null) && !request.getParameter("iidmunicipio").equals("")) {
                iidmunicipio = new Integer(request.getParameter("iidmunicipio")).intValue();
            }

            cliente.setIidnocliente(iidnocliente);

            switch (accion) {
                // 4.1 Alta clientes
                case 1:
                    hlp = new BeanHelper();
                    hlp.getData(con);
                    hlp.getBean4().setPAIS_DEFAULT(PAIS_DEFAULT);
                    ses_usr.setAttribute("bean4", hlp.getBean4());
                    strAddress = "/jsp/4_1.jsp";

                    break;

                //4.1  Alta clientes
                case 2:
                    cliente.setVchnombre(vchnombre.toUpperCase());
                    cliente.setVchrazonsocial(vchrazonsocial.toUpperCase());
                    cliente.setVchrfc(vchrfc.toUpperCase());
                    cliente.setVchcurp(vchcurp.toUpperCase());
                    cliente.setVchtel1(vchtel1.toUpperCase());
                    cliente.setVchtel2(vchtel2.toUpperCase());
                    cliente.setVchfax(vchfax.toUpperCase());
                    cliente.setBitdadodebaja(bitdadodebaja);
                    cliente.setVchmail(vchmail.toUpperCase());
                    cliente.setVchcolonia(vchcolonia.toUpperCase());
                    cliente.setVchcalle(vchcalle.toUpperCase());
                    cliente.setChcodigop(chcodigop);
                    cliente.setSiidpais(siidpais);
                    cliente.setIidestado(iidestado);
                    cliente.setIidmunicipio(iidmunicipio);
                    cliente.setVchnumeroexterior(vchnumeroexterior.toUpperCase());
                    cliente.setVchnumerointerior(vchnumerointerior.toUpperCase());
                    cliente.setVchlocalidad(vchlocalidad.toUpperCase());
                    
                    cliente.setIidnivel(8);
                        cliente.setIidcatcliente(1);
                        cliente.setSiidviaembarque(new Integer(1).shortValue());
                        cliente.setDeclimitecredito(0);
                        cliente.setDeccreditutilizado(0);
                        cliente.setSidiadepago(new Integer(0).shortValue());
                        cliente.setSiplazodiaspago(new Integer(0).shortValue());
                        cliente.setBsuceptiblecredito(true);
                        
                    
                    cliente.create();
                    beanMsg.setMsg("Se creo el cliente " + cliente.getVchnombre() +" ID Cliente " +cliente.getIidnocliente());
                    strAddress = "/jsp/4_1.jsp";

                    break;

                // 4.2  Modificar clientes datos generales
                case 3:
                    hlp = new BeanHelper();
                    hlp.getData(con);
                    ses_usr.setAttribute("bean4", hlp.getBean4());

                    Bean42 bean42 = new Bean42();
                    dbCLIENTEs clientes = new dbCLIENTEs();
                    clientes.setConnection(con);
                    clientes.find("");
                    bean42.setVClientes(clientes.getResult());
                    ses_usr.setAttribute("bean42", bean42);
                    strAddress = "/jsp/4_2.jsp";

                    break;

                // 4.2 Modificar clientes datos generales
                case 4:
                    cliente.load();
                    hlp42.getData(cliente, con);

                    BeanResult42 beanresult42 = hlp42.getBeanresult42();

                    if ((beanresult42.getIidnocliente() == IDCLIENTECONIVA) || (beanresult42.getIidnocliente() == IDCLIENTESINIVA)) {
                        beanresult42.setMostrador(true);
                    }

                    ses_usr.setAttribute("beanresult42", beanresult42);
                    strAddress = "/jsp/4_2.jsp";

                    break;

                // 4.2 Modificar clientes datos generales
                case 5:
                    cliente.load();
                    cliente.setVchnombre(vchnombre);
                    cliente.setVchrazonsocial(vchrazonsocial);
                    cliente.setVchrfc(vchrfc);
                    cliente.setVchcurp(vchcurp);
                    cliente.setVchtel1(vchtel1);
                    cliente.setVchtel2(vchtel2);
                    cliente.setVchfax(vchfax);
                    cliente.setBitdadodebaja(bitdadodebaja);
                    cliente.setVchmail(vchmail);
                    cliente.setVchcolonia(vchcolonia);
                    cliente.setVchcalle(vchcalle);
                    cliente.setChcodigop(chcodigop);
                    cliente.setSiidpais(siidpais);
                    cliente.setIidestado(iidestado);
                    cliente.setIidmunicipio(iidmunicipio);
                    cliente.setVchnumeroexterior(vchnumeroexterior);
                    cliente.setVchnumerointerior(vchnumerointerior);
                    cliente.setVchlocalidad(vchlocalidad);
                    cliente.store();
                    hlp42.getData(cliente, con);
                    beanMsg.setMsg("Se modific� el cliente " + cliente.getVchnombre());
                    ses_usr.setAttribute("beanresult42", hlp42.getBeanresult42());
                    strAddress = "/jsp/4_2.jsp";

                    break;
            }

            ses_usr.setAttribute("beanMsg", beanMsg);
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
