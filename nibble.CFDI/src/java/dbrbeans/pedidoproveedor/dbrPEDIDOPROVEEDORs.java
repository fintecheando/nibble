package dbrbeans.pedidoproveedor;

import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Vector;

public class dbrPEDIDOPROVEEDORs {
	private Connection conn;
	private ResultSet rSet;
	private PreparedStatement stmt;
	private Vector result;
	
public dbrPEDIDOPROVEEDORs() {
	super();
	result = new Vector();
}
public Vector findByStatusPedido(int tiestatus) throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		Vector v = new Vector();
		
		dbrPEDIDOPROVEEDOR pp;
		query.append("SELECT b.iidpedido,a.iidproveedor,a.vchrazonsocial ");
		query.append("FROM PROVEEDOR a, PEDIDO b ");
		query.append("WHERE  a.iidproveedor=b.iidproveedor and tiestatus=? ");
		
		stmt = conn.prepareStatement(query.toString().toLowerCase());

		stmt.setInt(1,tiestatus);
		
		rSet = stmt.executeQuery();

		while (rSet.next()) {
			pp= new dbrPEDIDOPROVEEDOR();
			pp.setIidpedido(rSet.getInt("iidpedido"));
			pp.setIidproveedor(rSet.getInt("iidproveedor"));
			pp.setVchrazonsocial(rSet.getString("vchrazonsocial"));
			v.addElement(pp);
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
