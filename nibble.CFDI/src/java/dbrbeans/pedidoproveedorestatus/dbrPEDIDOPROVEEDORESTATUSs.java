package dbrbeans.pedidoproveedorestatus;

import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Vector;

public class dbrPEDIDOPROVEEDORESTATUSs {
	private Connection conn;
	private ResultSet rSet;
	private PreparedStatement stmt;
	private Vector result;
	
public dbrPEDIDOPROVEEDORESTATUSs() {
	super();
	result = new Vector();
}
public Vector findByPar(int iidpedido, int tiestatus, int iidproveedor, String fechaini,String fechafin) throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		Vector v = new Vector();
		
		dbrPEDIDOPROVEEDORESTATUS pps;
		query.append("SELECT a.*,b.vchrazonsocial,c.vchdescestatus ");
		query.append("FROM PEDIDO a, PROVEEDOR b, ESTATUSPEDIDOS c ");
		query.append("WHERE  a.iidproveedor=b.iidproveedor and a.tiestatus=c.tiestatus ");
		if (iidpedido>0)
			query.append("AND a.iidpedido=? ");
		if (tiestatus>0)
			query.append("AND a.tiestatus=? ");
		if (iidproveedor>0)
			query.append("AND a.iidproveedor=? ");
		if (fechaini.length()>0)
			query.append("AND a.tisfechelabpedido BETWEEN ? and ? ");

		stmt = conn.prepareStatement(query.toString().toLowerCase());

		
		int i=1;
		if (iidpedido>0)
			stmt.setInt(i++,iidpedido);
		if (tiestatus>0)
			stmt.setInt(i++,tiestatus);
		if (iidproveedor>0)
			stmt.setInt(i++,iidproveedor);
		if (fechaini.length()>0){
			stmt.setString(i++,fechaini);
			stmt.setString(i++,fechafin);
		}
	
		rSet = stmt.executeQuery();

		while (rSet.next()) {
			pps= new dbrPEDIDOPROVEEDORESTATUS();
			pps.setIidpedido(rSet.getInt("iidpedido"));
			pps.setVchrazonsocial(rSet.getString("vchrazonsocial"));
			pps.setVchdescestatus(rSet.getString("vchdescestatus"));
			pps.setTisfechelabpedido(rSet.getString("tisfechelabpedido"));
			pps.setSinototalpartidas(rSet.getShort("sinototalpartidas"));
			pps.setSinototpartpedpend(rSet.getShort("sinototpartpedpend"));
			pps.setSinopartsurtpar(rSet.getShort("sinopartsurtpar"));
			pps.setSinopartcanceladas(rSet.getShort("sinopartcanceladas"));
			pps.setSinopartrecibidas(rSet.getShort("sinopartrecibidas"));
			
			v.addElement(pps);
		}
		rSet.close();
		stmt.close();
		return v;
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
