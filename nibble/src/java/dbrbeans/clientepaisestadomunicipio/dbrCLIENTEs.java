package dbrbeans.clientepaisestadomunicipio;

import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Vector;

public class dbrCLIENTEs {
	private Connection conn;
	private ResultSet rSet;
	private PreparedStatement stmt;
	private Vector result;
	
public dbrCLIENTEs() {
	super();
	result = new Vector();
}
public boolean findById(int delno, int alno) throws Exception, java.sql.SQLException {	
		StringBuffer query = new StringBuffer();
		dbrCLIENTE CLIENTE;
		query.append("SELECT c.*,p.vchnompais,e.vchnombre as estado,m.vchnombre as municipio,cc.vchdescripcion,np.vchnivel,np.decutilidad, ve.vchdescviaembarque ");
		query.append("FROM CLIENTE c,PAIS p,ESTADO e,MUNICIPIO m, CATEGORIACLIENTE cc,NIVELDEPRECIO np, VIADEEMBARQUE ve ");
		query.append("WHERE c.siidpais=p.siidpais AND c.iidestado=e.iidestado AND c.iidmunicipio=m.iidmunicipio AND c.iidcatcliente=cc.iidcatcliente AND c.iidnivel=np.iidnivel AND c.siidviaembarque=ve.siidviaembarque ");
                
		
		if (delno>0)
			query.append("AND c.iidnocliente BETWEEN ? AND ? ");              
                query.append("ORDER BY c.iidnocliente ");
                
		stmt = conn.prepareStatement(query.toString().toLowerCase());

		if (delno>0){
			stmt.setInt(1,delno);
			stmt.setInt(2,alno);
		}
		
		rSet = stmt.executeQuery();


		while (rSet.next()) {
			CLIENTE= new dbrCLIENTE();
			CLIENTE.setIidnocliente(rSet.getInt("iidnocliente"));
			CLIENTE.setSiidpais(rSet.getShort("siidpais"));
			CLIENTE.setSiidviaembarque(rSet.getShort("siidviaembarque"));
			CLIENTE.setIidnivel(rSet.getInt("iidnivel"));
			CLIENTE.setIidcatcliente(rSet.getInt("iidcatcliente"));
			CLIENTE.setVchrfc(rSet.getString("vchrfc"));
			CLIENTE.setVchnombre(rSet.getString("vchnombre"));
			CLIENTE.setVchrazonsocial(rSet.getString("vchrazonsocial"));
			CLIENTE.setVchcalle(rSet.getString("vchcalle"));
			CLIENTE.setVchcolonia(rSet.getString("vchcolonia"));
			CLIENTE.setChcodigop(rSet.getString("chcodigop"));
			CLIENTE.setVchmail(rSet.getString("vchmail"));
			CLIENTE.setVchdescregion(rSet.getString("vchdescregion"));
			CLIENTE.setVchdescestado(rSet.getString("vchdescestado"));
			CLIENTE.setVchtel1(rSet.getString("vchtel1"));
			CLIENTE.setVchtel2(rSet.getString("vchtel2"));
			CLIENTE.setVchfax(rSet.getString("vchfax"));
			CLIENTE.setVchcurp(rSet.getString("vchcurp"));
			CLIENTE.setBitdadodebaja(rSet.getBoolean("bitdadodebaja"));
			CLIENTE.setDeclimitecredito(rSet.getFloat("declimitecredito"));
			CLIENTE.setDeccreditutilizado(rSet.getFloat("deccreditutilizado"));
			CLIENTE.setSidiadepago(rSet.getShort("sidiadepago"));
			CLIENTE.setBsuceptiblecredito(rSet.getBoolean("bsuceptiblecredito"));
			CLIENTE.setSiplazodiaspago(rSet.getShort("siplazodiaspago"));
			CLIENTE.setIidestado(rSet.getInt("iidestado"));
			CLIENTE.setIidmunicipio(rSet.getInt("iidmunicipio"));
			CLIENTE.setVchpaisdes(rSet.getString("vchnompais"));
			CLIENTE.setVchestadodes(rSet.getString("estado"));
			CLIENTE.setVchmunicipiodes(rSet.getString("municipio"));
			CLIENTE.setVchcategoriades(rSet.getString("vchdescripcion"));
			CLIENTE.setVchniveldes(rSet.getString("vchnivel"));
			CLIENTE.setDecutilidad(rSet.getFloat("decutilidad") );
			CLIENTE.setVchviades(rSet.getString("vchdescviaembarque"));
			result.addElement(CLIENTE);
		}
		rSet.close();
		stmt.close();
		return false;
	}
public boolean findByPar(String vchrazonsocial, String vchrfc) throws Exception, java.sql.SQLException {	
		StringBuffer query = new StringBuffer();
		dbrCLIENTE CLIENTE;
		query.append("SELECT c.*,p.vchnompais,e.vchnombre as estado,m.vchnombre as municipio,cc.vchdescripcion,np.vchnivel,np.decutilidad, ve.vchdescviaembarque ");
		query.append("FROM CLIENTE c,PAIS p,ESTADO e,MUNICIPIO m, CATEGORIACLIENTE cc,NIVELDEPRECIO np, VIADEEMBARQUE ve ");
		query.append("WHERE c.siidpais=p.siidpais AND c.iidestado=e.iidestado AND c.iidmunicipio=m.iidmunicipio AND c.iidcatcliente=cc.iidcatcliente AND c.iidnivel=np.iidnivel AND c.siidviaembarque=ve.siidviaembarque ");
		
		if (vchrazonsocial != null && vchrazonsocial.length()>0)
			query.append(" vchrazonsocial like '"+vchrazonsocial+"%' ");
		if (vchrfc != null && vchrfc.length()>0)
			query.append(" AND vchrfc like '"+ vchrfc +"%' ");
			

		

		stmt = conn.prepareStatement(query.toString().toLowerCase());
	
		
		rSet = stmt.executeQuery();


		while (rSet.next()) {
			CLIENTE= new dbrCLIENTE();
			CLIENTE.setIidnocliente(rSet.getInt("iidnocliente"));
			CLIENTE.setSiidpais(rSet.getShort("siidpais"));
			CLIENTE.setSiidviaembarque(rSet.getShort("siidviaembarque"));
			CLIENTE.setIidnivel(rSet.getInt("iidnivel"));
			CLIENTE.setIidcatcliente(rSet.getInt("iidcatcliente"));
			CLIENTE.setVchrfc(rSet.getString("vchrfc"));
			CLIENTE.setVchnombre(rSet.getString("vchnombre"));
			CLIENTE.setVchrazonsocial(rSet.getString("vchrazonsocial"));
			CLIENTE.setVchcalle(rSet.getString("vchcalle"));
			CLIENTE.setVchcolonia(rSet.getString("vchcolonia"));
			CLIENTE.setChcodigop(rSet.getString("chcodigop"));
			CLIENTE.setVchmail(rSet.getString("vchmail"));
			CLIENTE.setVchdescregion(rSet.getString("vchdescregion"));
			CLIENTE.setVchdescestado(rSet.getString("vchdescestado"));
			CLIENTE.setVchtel1(rSet.getString("vchtel1"));
			CLIENTE.setVchtel2(rSet.getString("vchtel2"));
			CLIENTE.setVchfax(rSet.getString("vchfax"));
			CLIENTE.setVchcurp(rSet.getString("vchcurp"));
			CLIENTE.setBitdadodebaja(rSet.getBoolean("bitdadodebaja"));
			CLIENTE.setDeclimitecredito(rSet.getFloat("declimitecredito"));
			CLIENTE.setDeccreditutilizado(rSet.getFloat("deccreditutilizado"));
			CLIENTE.setSidiadepago(rSet.getShort("sidiadepago"));
			CLIENTE.setBsuceptiblecredito(rSet.getBoolean("bsuceptiblecredito"));
			CLIENTE.setSiplazodiaspago(rSet.getShort("siplazodiaspago"));
			CLIENTE.setIidestado(rSet.getInt("iidestado"));
			CLIENTE.setIidmunicipio(rSet.getInt("iidmunicipio"));
			CLIENTE.setVchpaisdes(rSet.getString("vchnompais"));
			CLIENTE.setVchestadodes(rSet.getString("estado"));
			CLIENTE.setVchmunicipiodes(rSet.getString("municipio"));
			CLIENTE.setVchcategoriades(rSet.getString("vchdescripcion"));
			CLIENTE.setVchniveldes(rSet.getString("vchnivel"));
			CLIENTE.setDecutilidad(rSet.getFloat("decutilidad") );
			CLIENTE.setVchviades(rSet.getString("vchdescviaembarque"));
			result.addElement(CLIENTE);
		}
		rSet.close();
		stmt.close();
		return false;
	}
public Vector getResult(){
	return this.result;
}
public void setConnection(java.sql.Connection conn) {
	this.conn = conn;
}
public void setResult (Vector result){
	this.result = result;
}
}
