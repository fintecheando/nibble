package org.nibble.service.mostrador;

/**
 * Insert the type's description here.
 * Creation date: (10/22/2002 1:39:24 PM)
 * @author: 
 */
import dbbeans.planta.*;
import dbbeans.modelo.*;
import dbbeans.submodelo.*;
import dbbeans.combustible.*;
import dbbeans.traccion.*;
import dbbeans.transmision.*;
import dbbeans.alimentacion_combustible.*;
import dbbeans.aspiracion.*;
import dbbeans.cilindros.*;

import java.sql.Connection;
import pva.beans9.*;

public class BeanHelper91 {

	dbPLANTAs plantas;
    dbMODELOs modelos;
    dbSUBMODELOs smodelos;
    dbCOMBUSTIBLEs combustibles;
    dbTRACCIONs traccions;
    dbTRANSMISIONs transmisions;
    dbALIMENTACION_COMBUSTIBLEs alimentacions;
    dbASPIRACIONs aspiracions;
    dbCILINDROSs cilindros;

	private pva.beans9.Bean91e bean91e;
/**
 * BeanHelper constructor comment.
 */
public BeanHelper91() {
	super();
	bean91e = new Bean91e();
}
/**
 * Insert the method's description here.
 * Creation date: (11/6/2002 11:44:45 AM)
 * @return pva.beans9.Bean91e
 */
public pva.beans9.Bean91e getBean91e() {
	return bean91e;
}
/**
 * Insert the method's description here.
 * Creation date: (10/22/2002 3:51:58 PM)
 * @return nc.beans5.Bean5
 */
public void getData(Connection con) throws Exception{
        plantas = new dbPLANTAs();
        modelos = new dbMODELOs();
        smodelos = new dbSUBMODELOs();
		combustibles = new dbCOMBUSTIBLEs();
    	traccions = new dbTRACCIONs();
		transmisions = new dbTRANSMISIONs();
		alimentacions = new dbALIMENTACION_COMBUSTIBLEs();
		aspiracions = new dbASPIRACIONs();
		cilindros = new dbCILINDROSs();
               
        plantas.setConnection(con);      
        modelos.setConnection(con);
        smodelos.setConnection(con);
		combustibles.setConnection(con);
		traccions.setConnection(con);
		transmisions.setConnection(con);
		alimentacions.setConnection(con);
		aspiracions.setConnection(con);
		cilindros.setConnection(con);

		

        plantas.find(""); 	bean91e.setPlantas(plantas.getResult());

        
        modelos.find(""); 	bean91e.setModelos(modelos.getResult());
        smodelos.find("");	bean91e.setSmodelos(smodelos.getResult());
        combustibles.find(""); bean91e.setCombustibles(combustibles.getResult());
		traccions.find("");	   bean91e.setTraccions(traccions.getResult());
		transmisions.find(""); bean91e.setTransmisions(transmisions.getResult());
		alimentacions.find("");bean91e.setAlimentacions(alimentacions.getResult());
		aspiracions.find("");  bean91e.setAspiracions(aspiracions.getResult());
		cilindros.find("");	   bean91e.setCilindros(cilindros.getResult());
}
/**
 * Insert the method's description here.
 * Creation date: (11/6/2002 11:44:45 AM)
 * @param newBean91e pva.beans9.Bean91e
 */
public void setBean91e(pva.beans9.Bean91e newBean91e) {
	bean91e = newBean91e;
}
}
