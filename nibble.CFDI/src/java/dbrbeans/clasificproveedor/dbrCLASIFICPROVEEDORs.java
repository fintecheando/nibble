package dbrbeans.clasificproveedor;

import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Vector;

public class dbrCLASIFICPROVEEDORs {
	private Connection conn;
	private ResultSet rSet;
	private PreparedStatement stmt;
	private Vector result;

public dbrCLASIFICPROVEEDORs() {
	super();
	result = new Vector();
}
public Vector findByClasificacion(int iidcategoria, int iidgrupo, int iidlinea, int iidmarca) throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		Vector v = new Vector();
		
		dbrCLASIFICPROVEEDOR cp;
		query.append("SELECT a.iidproveedor,a.vchrazonsocial,b.bitprovefavorito,b.iminimopedido ");
		query.append("FROM PROVEEDOR a,CLASIFICPROVEEDOR b ");
		query.append("WHERE  a.iidproveedor=b.iidproveedor and iidcategoria=? and iidgrupo=? and iidgrupo=? and iidmarca=? ");
		
		stmt = conn.prepareStatement(query.toString().toLowerCase());

		stmt.setInt(1,iidcategoria);
		stmt.setInt(2,iidgrupo);
		stmt.setInt(3,iidlinea);
		stmt.setInt(4,iidmarca);
		
		rSet = stmt.executeQuery();


		while (rSet.next()) {
			cp= new dbrCLASIFICPROVEEDOR();
			cp.setIidproveedor(rSet.getInt("iidproveedor"));
			cp.setVchrazonsocial(rSet.getString("vchrazonsocial"));
			cp.setBitprovefavorito(rSet.getBoolean("bitprovefavorito"));
			cp.setIminimopedido(rSet.getInt("iminimopedido"));
			v.addElement(cp);
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
