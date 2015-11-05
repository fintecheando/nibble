/** Vector java bean entity **/
package dbbeans.cliente;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Vector;


public class dbCLIENTEs {
	private Connection conn;
	private ResultSet rSet;
	private PreparedStatement stmt;
	private Vector result;


	public dbCLIENTEs() {
		super();
		result = new Vector();
	}
	public void setConnection(java.sql.Connection conn) {
		this.conn = conn;
	}
	public boolean find(String where) throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		dbCLIENTE CLIENTE;
		query.append("SELECT * ");
		query.append("FROM CLIENTE ");
		if (!where.equals("")){
			query.append("WHERE " + where );
		}
		//query.append("ORDER BY vchnombre ");
                
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		rSet = stmt.executeQuery();


		while (rSet.next()) {
			CLIENTE= new dbCLIENTE();
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
                        CLIENTE.setVchnumeroexterior(rSet.getString("vchnumexterior"));
                        CLIENTE.setVchnumerointerior(rSet.getString("vchnuminterior"));
                        
                        CLIENTE.setVchlocalidad(rSet.getString("vchlocalidad"));
			result.addElement(CLIENTE);
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

	public boolean findByPar(String vchrazonsocial, String vchrfc) throws Exception, java.sql.SQLException {
		boolean ban=false;
		String where= new String();
		
		StringBuffer query = new StringBuffer();
		dbCLIENTE CLIENTE;
		query.append("SELECT * ");
		query.append("FROM CLIENTE ");
		
		if (vchrazonsocial != null && vchrazonsocial.length()>0){
			where+= " vchrazonsocial like '"+vchrazonsocial+"%' ";
			ban=true;
		}
		if (vchrfc != null && vchrfc.length()>0){
			if (ban)
				where+= " AND ";
			where+= " vchrfc like '"+ vchrfc +"%' " ;
		}
		if (where.length()>0)
			query.append("WHERE "+ where);
		
		//query.append("ORDER BY vchnombre ");
		

		stmt = conn.prepareStatement(query.toString().toLowerCase());
	
		
		rSet = stmt.executeQuery();


		while (rSet.next()) {
			CLIENTE= new dbCLIENTE();
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
			result.addElement(CLIENTE);
		}
		rSet.close();
		stmt.close();
		return false;
	}
}
