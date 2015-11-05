package dbbeans.clasificproveedor;

import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Vector;
import java.util.Hashtable;


public class dbCLASIFICPROVEEDORs {
	private Connection conn;
	private ResultSet rSet;
	private PreparedStatement stmt;
	private Vector result;


	public dbCLASIFICPROVEEDORs() {
		super();
		result = new Vector();
	}
	public boolean find(int iidcategoria, int iidgrupo, int iidlinea, int iidmarca) throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		dbCLASIFICPROVEEDOR CLASIFICPROVEEDOR;
		query.append("SELECT * ");
		query.append("FROM CLASIFICPROVEEDOR ");
		query.append("WHERE iidcategoria= ? AND iidgrupo= ? AND iidlinea= ? AND iidmarca= ? ");

		stmt = conn.prepareStatement(query.toString().toLowerCase());
		
		stmt.setInt(1, iidcategoria);
	    stmt.setInt(2, iidgrupo);
    	stmt.setInt(3, iidlinea);
    	stmt.setInt(4, iidmarca);

		rSet = stmt.executeQuery();


		while (rSet.next()) {
			CLASIFICPROVEEDOR= new dbCLASIFICPROVEEDOR();
			CLASIFICPROVEEDOR.setIidproveedor(rSet.getInt("iidproveedor"));
			CLASIFICPROVEEDOR.setIidcategoria(rSet.getInt("iidcategoria"));
			CLASIFICPROVEEDOR.setIidgrupo(rSet.getInt("iidgrupo"));
			CLASIFICPROVEEDOR.setIidlinea(rSet.getInt("iidlinea"));
			CLASIFICPROVEEDOR.setIidmarca(rSet.getInt("iidmarca"));
			CLASIFICPROVEEDOR.setIminimopedido(rSet.getInt("iminimopedido"));
			CLASIFICPROVEEDOR.setBitprovefavorito(rSet.getBoolean("bitprovefavorito"));
			CLASIFICPROVEEDOR.setDecfactorvaluainve(rSet.getFloat("decfactorvaluainve"));
			CLASIFICPROVEEDOR.setDecfactorcostovent(rSet.getFloat("decfactorcostovent"));
			result.addElement(CLASIFICPROVEEDOR);
		}
		rSet.close();
		stmt.close();
		return false;
	}
	public boolean find(String where) throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		dbCLASIFICPROVEEDOR CLASIFICPROVEEDOR;
		query.append("SELECT * ");
		query.append("FROM CLASIFICPROVEEDOR ");
		if (!where.equals("")){
			query.append("WHERE " + where );
		}
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		rSet = stmt.executeQuery();


		while (rSet.next()) {
			CLASIFICPROVEEDOR= new dbCLASIFICPROVEEDOR();
			CLASIFICPROVEEDOR.setIidproveedor(rSet.getInt("iidproveedor"));
			CLASIFICPROVEEDOR.setIidcategoria(rSet.getInt("iidcategoria"));
			CLASIFICPROVEEDOR.setIidgrupo(rSet.getInt("iidgrupo"));
			CLASIFICPROVEEDOR.setIidlinea(rSet.getInt("iidlinea"));
			CLASIFICPROVEEDOR.setIidmarca(rSet.getInt("iidmarca"));
			CLASIFICPROVEEDOR.setIminimopedido(rSet.getInt("iminimopedido"));
			CLASIFICPROVEEDOR.setBitprovefavorito(rSet.getBoolean("bitprovefavorito"));
			CLASIFICPROVEEDOR.setDecfactorvaluainve(rSet.getFloat("decfactorvaluainve"));
			CLASIFICPROVEEDOR.setDecfactorcostovent(rSet.getFloat("decfactorcostovent"));
			result.addElement(CLASIFICPROVEEDOR);
		}
		rSet.close();
		stmt.close();
		return false;
	}
	public Hashtable findByClasificacion( int iidcategoria, int iidgrupo, int iidlinea, int iidmarca) throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		Hashtable result = new Hashtable();
		

		query.append("SELECT iidproveedor, decfactorvaluainve ");
		query.append("FROM CLASIFICPROVEEDOR ");
		query.append("WHERE iidcategoria= ? AND iidgrupo= ? AND iidlinea= ? AND iidmarca= ? ");

		stmt = conn.prepareStatement(query.toString().toLowerCase());

		stmt.setInt(1, iidcategoria);
	    stmt.setInt(2, iidgrupo);
    	stmt.setInt(3, iidlinea);
    	stmt.setInt(4, iidmarca);

    	rSet = stmt.executeQuery();


		while (rSet.next()) {
			result.put(new Integer(rSet.getInt("iidproveedor")),new Float(rSet.getFloat("decfactorvaluainve")));
		}
		rSet.close();
		stmt.close();
		return result;
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
