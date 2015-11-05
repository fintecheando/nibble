package pva.beans4;

/**
 * Insert the type's description here.
 * Creation date: (10/23/2002 11:16:40 AM)
 * @author: 
 */
import dbbeans.pais.*;
import dbbeans.estado.*;
import dbbeans.municipio.*;
import dbbeans.cliente.*;

import java.sql.Connection;
import pva.beans4.BeanResult42;

public class BeanHelper42{
	private BeanResult42 beanresult42;
/**
 * BeaHelper52 constructor comment.
 */
public BeanHelper42() {
	super();
	beanresult42 = new BeanResult42();
}
/**
 * Insert the method's description here.
 * Creation date: (10/23/2002 11:19:33 AM)
 * @return nc.beans5.BeanResult52
 */
public BeanResult42 getBeanresult42() {
	return beanresult42;
}
/**
 * Insert the method's description here.
 * Creation date: (10/23/2002 11:21:35 AM)
 * @param iidcliente int
 * @param con java.sql.Connection
 */
public void getData(dbCLIENTE cliente, Connection con) throws Exception{
    beanresult42.setIidnocliente(cliente.getIidnocliente());
    beanresult42.setVchnombre(cliente.getVchnombre());
    beanresult42.setVchrazonsocial(cliente.getVchrazonsocial());
    beanresult42.setVchrfc(cliente.getVchrfc());
    beanresult42.setVchcurp(cliente.getVchcurp());
    beanresult42.setVchtel1(cliente.getVchtel1());
    beanresult42.setVchtel2(cliente.getVchtel2());
    beanresult42.setVchfax(cliente.getVchfax());
    beanresult42.setBitdadodebaja(cliente.getBitdadodebaja());
    beanresult42.setVchmail(cliente.getVchmail());
    beanresult42.setVchcolonia(cliente.getVchcolonia());
    beanresult42.setVchcalle(cliente.getVchcalle());
    beanresult42.setChcodigop(cliente.getChcodigop());
    beanresult42.setSiidpais(cliente.getSiidpais());
    beanresult42.setIidestado(cliente.getIidestado());
    beanresult42.setIidmunicipio(cliente.getIidmunicipio());
    beanresult42.setVchnumeroexterior(cliente.getVchnumeroexterior());
    beanresult42.setVchnumerointerior(cliente.getVchnumerointerior());
    beanresult42.setVchlocalidad(cliente.getVchlocalidad());
    dbPAISs paiss = new dbPAISs();
    paiss.setConnection(con);
    paiss.find("");
    dbESTADOs estados = new dbESTADOs();
    estados.setConnection(con);
    estados.findByPais(cliente.getSiidpais());
    dbMUNICIPIOs municipios = new dbMUNICIPIOs();
    municipios.setConnection(con);
    municipios.findByEstado(cliente.getIidestado());

    beanresult42.setVPaiss(paiss.getResult());
    beanresult42.setVEstados(estados.getResult());
    beanresult42.setVMunicipio(municipios.getResult());

}
/**
 * Insert the method's description here.
 * Creation date: (10/23/2002 11:19:33 AM)
 * @param newBeanresult52 nc.beans5.BeanResult52
 */
public void setBeanresult42(BeanResult42 newBeanresult42) {
	beanresult42 = newBeanresult42;
}
}
