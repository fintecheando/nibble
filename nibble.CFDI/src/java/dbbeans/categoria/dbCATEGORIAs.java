package dbbeans.categoria;

import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Vector;


public class dbCATEGORIAs {
	private Connection conn;
	private ResultSet rSet;
	private PreparedStatement stmt;
	private Vector result;
	public dbCATEGORIAs() {
		super();
		result = new Vector();
	}
public int CountGrupos(int iidusuario,int iidcategoria)
    throws Exception, java.sql.SQLException {
    StringBuffer query = new StringBuffer();
    int count=0;

    query.append("SELECT count(*) ");
    query.append("FROM FABRICANTEGRUPO a,GRUPO b ");
    query.append("WHERE  a.iidgrupo=b.iidgrupo ");
    query.append("and  b.iidcategoria=? ");
    query.append("and  a.iidusuario=? ");

    stmt = conn.prepareStatement(query.toString().toLowerCase());
    
	stmt.setInt(1, iidcategoria);
    stmt.setInt(2, iidusuario);

    rSet = stmt.executeQuery();

    if (rSet.next()) {
	    count = rSet.getInt(1);
    }
    rSet.close();
    stmt.close();
    return count;
}
	public boolean find(String where) throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		dbCATEGORIA CATEGORIA;
		query.append("SELECT * ");
		query.append("FROM CATEGORIA ");
		if (!where.equals("")){
			query.append("WHERE " + where );
		}
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		rSet = stmt.executeQuery();


		while (rSet.next()) {
			CATEGORIA= new dbCATEGORIA();
			CATEGORIA.setIidcategoria(rSet.getInt("iidcategoria"));
			CATEGORIA.setVchdescripcion(rSet.getString("vchdescripcion"));
			result.addElement(CATEGORIA);
		}
		rSet.close();
		stmt.close();
		return false;
	}
public Vector findByIidusuario(int iidusuario)
    throws Exception, java.sql.SQLException {
    StringBuffer query = new StringBuffer();
    Vector v = new Vector();

    dbCATEGORIA CATEGORIA;
    query.append("SELECT a.* ");
    query.append("FROM CATEGORIA a,FABRICANTECATEGORIA b ");
    query.append(
        "WHERE  a.iidcategoria=b.iidcategoria and iidusuario=? ");

    stmt = conn.prepareStatement(query.toString().toLowerCase());

    stmt.setInt(1, iidusuario);

    rSet = stmt.executeQuery();

    while (rSet.next()) {
        CATEGORIA = new dbCATEGORIA();
        CATEGORIA.setIidcategoria(rSet.getInt("iidcategoria"));
        CATEGORIA.setVchdescripcion(rSet.getString("vchdescripcion"));
        v.addElement(CATEGORIA);
    }
    rSet.close();
    stmt.close();
    return v;
}
public Vector findByNotIidusuario(int iidusuario)
    throws Exception, java.sql.SQLException {
    StringBuffer query = new StringBuffer();
    Vector v = new Vector();

    dbCATEGORIA CATEGORIA;
    query.append("SELECT a.* ");
    query.append("FROM CATEGORIA a ");
    query.append("LEFT JOIN FABRICANTECATEGORIA b ");
    query.append("ON a.iidcategoria=b.iidcategoria and iidusuario=? ");
    query.append("WHERE b.iidcategoria IS NULL");

    stmt = conn.prepareStatement(query.toString().toLowerCase());

    stmt.setInt(1, iidusuario);

    rSet = stmt.executeQuery();

    while (rSet.next()) {
        CATEGORIA = new dbCATEGORIA();
        CATEGORIA.setIidcategoria(rSet.getInt("iidcategoria"));
        CATEGORIA.setVchdescripcion(rSet.getString("vchdescripcion"));
        v.addElement(CATEGORIA);
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
