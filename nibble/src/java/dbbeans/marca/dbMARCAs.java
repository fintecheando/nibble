package dbbeans.marca;

import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Vector;


public class dbMARCAs {
	private Connection conn;
	private ResultSet rSet;
	private PreparedStatement stmt;
	private Vector result;

	public dbMARCAs() {
		super();
		result = new Vector();
	}
	public boolean find(String where) throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		dbMARCA MARCA;
		query.append("SELECT * ");
		query.append("FROM MARCA ");
		if (!where.equals("")){
			query.append("WHERE " + where );
		}
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		rSet = stmt.executeQuery();


		while (rSet.next()) {
			MARCA= new dbMARCA();
			MARCA.setIidmarca(rSet.getInt("iidmarca"));
			MARCA.setIidlinea(rSet.getInt("iidlinea"));
			MARCA.setVchnombre(rSet.getString("vchnombre"));
			MARCA.setBitdadodebaja(rSet.getBoolean("bitdadodebaja"));
			MARCA.setImgmarcalogo(rSet.getString("imgmarcalogo"));
			result.addElement(MARCA);
		}
		rSet.close();
		stmt.close();
		return false;
	}
public Vector findByIidusuario(int iidusuario)
    throws Exception, java.sql.SQLException {
    StringBuffer query = new StringBuffer();
    Vector v = new Vector();

    dbMARCA MARCA;
    query.append("SELECT a.* ");
    query.append("FROM MARCA a,FABRICANTEMARCA b ");
    query.append("WHERE  a.iidmarca=b.iidmarca ");
    query.append("AND b.iidusuario=? ");

    stmt = conn.prepareStatement(query.toString().toLowerCase());
    
    stmt.setInt(1, iidusuario);

    rSet = stmt.executeQuery();

    while (rSet.next()) {
        MARCA = new dbMARCA();
        MARCA.setIidmarca(rSet.getInt("iidmarca"));
        MARCA.setVchnombre(rSet.getString("vchnombre"));
        MARCA.setIidlinea(rSet.getInt("iidlinea"));
        v.addElement(MARCA);
    }
    rSet.close();
    stmt.close();
    return v;
}
public Vector findByIidusuario(int iidusuario, int iidlinea)
    throws Exception, java.sql.SQLException {
    StringBuffer query = new StringBuffer();
    Vector v = new Vector();

    dbMARCA MARCA;
    query.append("SELECT a.* ");
    query.append("FROM MARCA a,FABRICANTEMARCA b ");
    query.append("WHERE  a.iidmarca=b.iidmarca ");
    query.append("AND a.iidlinea=? ");
    query.append("AND b.iidusuario=? ");

    stmt = conn.prepareStatement(query.toString().toLowerCase());
    
	stmt.setInt(1, iidlinea);
    stmt.setInt(2, iidusuario);

    rSet = stmt.executeQuery();

    while (rSet.next()) {
        MARCA = new dbMARCA();
        MARCA.setIidmarca(rSet.getInt("iidmarca"));
        MARCA.setVchnombre(rSet.getString("vchnombre"));
        v.addElement(MARCA);
    }
    rSet.close();
    stmt.close();
    return v;
}
public Vector findByNotIidusuario(int iidusuario, int iidlinea)
    throws Exception, java.sql.SQLException {
    StringBuffer query = new StringBuffer();
    Vector v = new Vector();

    dbMARCA MARCA;
    query.append("SELECT a.* ");
    query.append("FROM MARCA a ");
    query.append("LEFT JOIN FABRICANTEMARCA b ");
    query.append("ON a.iidmarca=b.iidmarca AND iidusuario=? ");
    query.append("WHERE b.iidmarca IS NULL AND a.iidlinea=? ");

    stmt = conn.prepareStatement(query.toString().toLowerCase());
    
	
    stmt.setInt(1, iidusuario);
    stmt.setInt(2, iidlinea);

    rSet = stmt.executeQuery();

    while (rSet.next()) {
        MARCA = new dbMARCA();
        MARCA.setIidmarca(rSet.getInt("iidmarca"));
        MARCA.setVchnombre(rSet.getString("vchnombre"));
        v.addElement(MARCA);
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
