/** Vector java bean entity **/
package dbbeans.aplicacion;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Vector;


public class dbAPLICACIONs {
	private Connection conn;
	private ResultSet rSet;
	private PreparedStatement stmt;
	private Vector result;


	public dbAPLICACIONs() {
		super();
		result = new Vector();
	}
	public void setConnection(java.sql.Connection conn) {
		this.conn = conn;
	}
	public boolean find(String where) throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		dbAPLICACION APLICACION;
		query.append("SELECT * ");
		query.append("FROM APLICACION ");
		if (!where.equals("")){
			query.append("WHERE " + where );
		}
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		rSet = stmt.executeQuery();


		while (rSet.next()) {
			APLICACION= new dbAPLICACION();
			APLICACION.setIidaplicacion(rSet.getInt("iidaplicacion"));
			APLICACION.setSianio(rSet.getShort("sianio"));
			APLICACION.setVchmotor(rSet.getString("vchmotor"));
			APLICACION.setSicombustible(rSet.getShort("sicombustible"));
			APLICACION.setSitraccion(rSet.getShort("sitraccion"));
			APLICACION.setSitransmision(rSet.getShort("sitransmision"));
			APLICACION.setSialimcmbst(rSet.getShort("sialimcmbst"));
			APLICACION.setSiaspiracion(rSet.getShort("siaspiracion"));
			APLICACION.setIidmodelo(rSet.getInt("iidmodelo"));
			APLICACION.setIidplanta(rSet.getInt("iidplanta"));
			APLICACION.setSicilindros(rSet.getShort("sicilindros"));
			APLICACION.setIidsubmodelo(rSet.getInt("iidsubmodelo"));
			APLICACION.setSidesplazamiento(rSet.getShort("sidesplazamiento"));
			APLICACION.setVchhprpm(rSet.getString("vchhprpm"));
			APLICACION.setChnumpuertas(rSet.getString("chnumpuertas"));
			APLICACION.setBitdirhidraulica(rSet.getBoolean("bitdirhidraulica"));
			APLICACION.setBitaireacond(rSet.getBoolean("bitaireacond"));
			APLICACION.setBitdadodebaja(rSet.getBoolean("bitdadodebaja"));
			result.addElement(APLICACION);
		}
		rSet.close();
		stmt.close();
		return false;
	}
	public void setResult (Vector result){
		this.result = result;
	}
	public Vector getResult(){
		return this.result;
	}

/**
 * Insert the method's description here.
 * Creation date: (17/10/2002 03:33:27 p.m.)
 * @return boolean
 */
public boolean finder(javax.servlet.http.HttpServletRequest request) throws Exception, java.sql.SQLException{

	boolean found = false;
	dbAPLICACIONr APLICACION;

    try{

   		int iidplanta = -1;
		int iidmodelo = -1;
		int iidsubmodelo = -1;
		int ide = -1;
		int ia = -1;
		short sicilindros = -1;		
		String vchmotor;
		short sicombustible = -1;
		short sitraccion = -1;
		short sitransmision = -1;
		short sialimcmbst = -1;
		short siaspiracion = -1;
		short sidesplazamiento = -1;
		String vchhprpm;
		String chnumpuertas;
		boolean bitdirhidraulica = false;
		boolean bitaireacond = false;
	    
		String master 			= request.getParameter("master"); //planta
		String slave			= request.getParameter("slave");  //modelo
		String sslave			= request.getParameter("sslave"); //submodelo
		String de				= request.getParameter("de");
		String a				= request.getParameter("a");
		String cilindros		= request.getParameter("cilindros");
		String motor			= request.getParameter("motor");
		String combustible		= request.getParameter("combustible");
		String traccion			= request.getParameter("traccion");
		String transmision		= request.getParameter("transmision");
		String alimentacion		= request.getParameter("alimentacion");
		String aspiracion		= request.getParameter("aspiracion");
		String desplazamiento	= request.getParameter("desplazamiento");
		String potencia			= request.getParameter("potencia");
		String puertas			= request.getParameter("puertas");
		String direccion		= request.getParameter("direccion");
		String aire				= request.getParameter("aire");

/*
			APLICACION.setSidesplazamiento(rSet.getString(13));
			APLICACION.setChnumpuertas(rSet.getString(15));
			APLICACION.setBitdirhidraulica(rSet.getBoolean(16));
			APLICACION.setBitaireacond(rSet.getBoolean(17));
			APLICACION.setBitdadodebaja(rSet.getBoolean(18));
*/		 
        String query ="select a.iidaplicacion,a.sianio,a.vchmotor,"+
        	   "a.vchhprpm,b.vchdescripcion,"+
        	   "c.vchdescripcion,d.vchdescripcion,e.vchdescripcion,"+
        	   "f.vchdescripcion,g.vchdescripcion,h.vchdescripcion,"+
        	   "i.vchdescripcion,j.vchdescripcion "+
        	   "from aplicacion as a,"+
           	   "planta as b,"+
           	   "modelo as c,"+
        	   "submodelo as d,"+
        	   "cilindros as e,"+
        	   "combustible as f,"+
        	   "aspiracion as g,"+
        	   "alimentacion_combustible as h,"+
        	   "traccion as i,"+
        	   "transmision as j ";

        String Linea ="";
        
    	boolean bP = false,bM = false,bS = false,bF = false,
    	        bC = false,bMo = false,bCo = false,bTc = false,
				bTr = false,bA  = false,bAs = false,bD	= false,
				bPo	= false,bPu = false,bDi = true, bAi	= true,
				concat = false;
		
		int cont = 0;

		if (!master.equalsIgnoreCase("")){
			iidplanta = new Integer(master).intValue();
			Linea += "a.iidplanta = ?";
			bP = true;
    	}
		if (slave != null)
			if (!slave.equalsIgnoreCase("")){
				iidmodelo = new Integer(slave).intValue();
				if (!Linea.equalsIgnoreCase(""))Linea += " AND a.iidmodelo = ?";
				else Linea += "a.iidmodelo = ?";
				bM = true;
		    }
		if (sslave != null)			
		    if (!sslave.equalsIgnoreCase("")){
				iidsubmodelo = new Integer(sslave).intValue();
				if (!Linea.equalsIgnoreCase(""))Linea += " AND a.iidsubmodelo = ?";
				else Linea += "a.iidsubmodelo = ?";
				bS = true;
		    }
	    if (!de.equalsIgnoreCase("")){
		    ide = new Integer(de).intValue();
		    ia = new Integer(a).intValue();
			if (!Linea.equalsIgnoreCase(""))Linea += " AND (a.sianio >= ? AND a.sianio <= ?)";
			else Linea += "(a.sianio >= ? AND a.sianio <= ?)";
			bF = true;
		}
	    if (!cilindros.equalsIgnoreCase("")){
			sicilindros = new Integer(cilindros).shortValue();
			if (!Linea.equalsIgnoreCase(""))Linea += " AND a.sicilindros = ?";
			else Linea += "a.sicilindros = ?";
			bC = true;
	    }
	    if (!motor.equalsIgnoreCase("")){
			if (!Linea.equalsIgnoreCase(""))Linea += " AND a.vchmotor like ?";
			else Linea += "a.vchmotor like ?";
			bMo = true;
	    }
	    if (!combustible.equalsIgnoreCase("")){
			sicombustible = new Integer(combustible).shortValue();
			if (!Linea.equalsIgnoreCase(""))Linea += " AND a.sicombustible = ?";
			else Linea += "a.sicombustible = ?";
			bCo = true;
	    }
	    if (!traccion.equalsIgnoreCase("")){
			sitraccion = new Integer(traccion).shortValue();
			if (!Linea.equalsIgnoreCase(""))Linea += " AND a.sitraccion = ?";
			else Linea += "a.sitraccion = ?";
			bTc = true;
	    }
	    if (!transmision.equalsIgnoreCase("")){
			sitransmision = new Integer(transmision).shortValue();
			if (!Linea.equalsIgnoreCase(""))Linea += " AND a.sitransmision = ?";
			else Linea += "a.sitransmision = ?";
			bTr = true;
	    }
	    if (!alimentacion.equalsIgnoreCase("")){
			sialimcmbst = new Integer(alimentacion).shortValue();
			if (!Linea.equalsIgnoreCase(""))Linea += " AND a.sialimcmbst = ?";
			else Linea += "a.sialimcmbst = ?";
			bA = true;
	    }
	    if (!aspiracion.equalsIgnoreCase("")){
			siaspiracion = new Integer(aspiracion).shortValue();
			if (!Linea.equalsIgnoreCase(""))Linea += " AND a.siaspiracion = ?";
			else Linea += "a.siaspiracion = ?";
			bAs = true;
	    }
	    if (!desplazamiento.equalsIgnoreCase("")){
			sidesplazamiento = new Integer(desplazamiento).shortValue();
			if (!Linea.equalsIgnoreCase(""))Linea += " AND a.sidesplazamiento = ?";
			else Linea += "a.sidesplazamiento = ?";
			bD = true;
	    }
	    if (!potencia.equalsIgnoreCase("")){
			if (!Linea.equalsIgnoreCase(""))Linea += " AND a.vchhprpm like ?";
			else Linea += "a.vchhprpm like ?";
			bPo = true;
	    }
	    if (!puertas.equalsIgnoreCase("")){    
			if (!Linea.equalsIgnoreCase(""))Linea += " AND a.chnumpuertas = ?";
			else Linea += "a.chnumpuertas = ?";
			bPu = true;
	    }
	    
		if (!Linea.equalsIgnoreCase(""))Linea += " AND a.bitdirhidraulica = ?";
		else Linea += "a.bitdirhidraulica = ?";

		if (!Linea.equalsIgnoreCase(""))Linea += " AND a.bitaireacond = ?";
		else Linea += "a.bitaireacond = ?";
	    
		if (direccion.equalsIgnoreCase("S"))bitdirhidraulica = true;
	    if (aire.equalsIgnoreCase("S"))bitaireacond = true;
  	     
	    if (!Linea.equalsIgnoreCase("")) query += "WHERE "+Linea+" AND ";
	    else query+="WHERE ";
 	    
	    query +=
		"(a.iidplanta=b.iidplanta AND a.iidmodelo=c.iidmodelo AND "+
		"a.iidsubmodelo=d.iidsubmodelo AND a.sicilindros=e.sicilindros AND "+
		"a.sicombustible=f.sicombustible AND a.siaspiracion=g.siaspiracion AND "+
		"a.sialimcmbst=h.sialimcmbst AND a.sitraccion=i.sitraccion AND "+ 
		"a.sitransmision=j.sitransmision)"; 
	    	
	    
	    query += " ORDER BY a.iidaplicacion ";
	        
	    stmt = conn.prepareStatement(query);
	    cont = 0;

	    if (bP){
			cont ++;
			stmt.setInt(cont,iidplanta);	
	    }
	    if (bM){
    		cont ++;
	        stmt.setInt(cont,iidmodelo);
	    }
	    if (bS){
			cont++;
			stmt.setInt(cont,iidsubmodelo);
	    }
	    if (bF){
			cont++;
			stmt.setInt(cont,ide);
			cont++;
			stmt.setInt(cont,ia);			
	    }
	    if (bC){
			cont++;
			stmt.setShort(cont,sicilindros);	
	    }
	    if (bMo){
			cont++;
			stmt.setString(cont,motor+"%");	
	    }
	    if (bCo){
			cont++;
			stmt.setShort(cont,sicombustible);	
	    }
	    if (bTc){
			cont++;
			stmt.setShort(cont,sitraccion);	
	    }
	    if (bTr){
			cont++;
			stmt.setShort(cont,sitransmision);	
	    }
	    if (bA){
			cont++;
			stmt.setShort(cont,sialimcmbst);	
	    }
	    if (bAs){
			cont++;
			stmt.setShort(cont,siaspiracion);	
	    }
	    if (bD){
			cont++;
			stmt.setShort(cont,sidesplazamiento);	
	    }
	    if (bPo){
			cont++;
			stmt.setString(cont,potencia+"%");	
	    }
	    if (bPu){
			cont++;
			stmt.setString(cont,puertas);	
	    }        
	    if (bDi){
			cont++;
			stmt.setBoolean(cont,bitdirhidraulica);	
	    }
	    if (bAi){
			cont++;
			stmt.setBoolean(cont,bitaireacond);	
	    }
	    

       rSet = stmt.executeQuery();
        
		while (rSet.next()) {
			found = true;
			APLICACION= new dbAPLICACIONr();
			APLICACION.setIidaplicacion(rSet.getInt(1));
			APLICACION.setSianio(rSet.getShort(2));
			APLICACION.setVchmotor(rSet.getString(3));
			APLICACION.setVchhprpm(rSet.getString(4));
			APLICACION.setIidplanta(rSet.getString(5));
			APLICACION.setIidmodelo(rSet.getString(6));
			APLICACION.setIidsubmodelo(rSet.getString(7));
			APLICACION.setSicilindros(rSet.getString(8));			
			APLICACION.setSicombustible(rSet.getString(9));
			APLICACION.setSiaspiracion(rSet.getString(10));
			APLICACION.setSialimcmbst(rSet.getString(11));			
			APLICACION.setSitraccion(rSet.getString(12));
			APLICACION.setSitransmision(rSet.getString(13));
			result.addElement(APLICACION);
		}     
		 
        rSet.close();
         
    }catch (Exception e) {
	    
	    e.printStackTrace();
	    return false;
	}finally {
      try {
	    if(rSet != null)rSet.close();
        if(stmt != null)stmt.close();
        if(conn != null)conn.close();
      } catch (Exception ignore) {}
    }
	
	return found;
}
}
