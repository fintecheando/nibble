package dbbeans.grupo;

import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Vector;


public class dbGRUPOs {
	protected Connection conn;
	protected ResultSet rSet;
	protected PreparedStatement stmt;
	protected Vector result;
	public dbGRUPOs() {
		super();
		result = new Vector();
	}
public int CountLineas(int iidusuario,int iidgrupo)
    throws Exception, java.sql.SQLException {
    StringBuffer query = new StringBuffer();
    int count=0;

    query.append("SELECT count(*) ");
    query.append("FROM FABRICANTELINEA a,LINEA b ");
    query.append("WHERE  a.iidlinea=b.iidlinea ");
    query.append("and  b.iidgrupo=? ");
    query.append("and  a.iidusuario=? ");

    stmt = conn.prepareStatement(query.toString().toLowerCase());
    
	stmt.setInt(1, iidgrupo);
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
		dbGRUPO GRUPO;
		query.append("SELECT * ");
		query.append("FROM GRUPO ");
		if (!where.equals("")){
			query.append("WHERE " + where );
		}
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		rSet = stmt.executeQuery();


		while (rSet.next()) {
			GRUPO= new dbGRUPO();
			GRUPO.setIidgrupo(rSet.getInt("iidgrupo"));
			GRUPO.setVchdescripcion(rSet.getString("vchdescripcion"));
			GRUPO.setIidcategoria(rSet.getInt("iidcategoria"));
			result.addElement(GRUPO);
		}
		rSet.close();
		stmt.close();
		return false;
	}
public Vector findByIidusuario(int iidusuario)
    throws Exception, java.sql.SQLException {
    StringBuffer query = new StringBuffer();
    Vector v = new Vector();

    dbGRUPO GRUPO;
    query.append("SELECT a.* ");
    query.append("FROM GRUPO a,FABRICANTEGRUPO b ");
    query.append("WHERE  a.iidgrupo=b.iidgrupo ");
    query.append("AND b.iidusuario=? ");

    stmt = conn.prepareStatement(query.toString().toLowerCase());
    
    stmt.setInt(1, iidusuario);

    rSet = stmt.executeQuery();

    while (rSet.next()) {
        GRUPO = new dbGRUPO();
        GRUPO.setIidgrupo(rSet.getInt("iidgrupo"));
        GRUPO.setVchdescripcion(rSet.getString("vchdescripcion"));
        GRUPO.setIidcategoria(rSet.getInt("iidcategoria"));
        v.addElement(GRUPO);
    }
    rSet.close();
    stmt.close();
    return v;
}
public Vector findByIidusuario(int iidusuario, int iidcategoria)
    throws Exception, java.sql.SQLException {
    StringBuffer query = new StringBuffer();
    Vector v = new Vector();

    dbGRUPO GRUPO;
    query.append("SELECT a.* ");
    query.append("FROM GRUPO a,FABRICANTEGRUPO b ");
    query.append("WHERE  a.iidgrupo=b.iidgrupo ");
    query.append("AND a.iidcategoria=? ");
    query.append("AND b.iidusuario=? ");

    stmt = conn.prepareStatement(query.toString().toLowerCase());
    
	stmt.setInt(1, iidcategoria);
    stmt.setInt(2, iidusuario);

    rSet = stmt.executeQuery();

    while (rSet.next()) {
        GRUPO = new dbGRUPO();
        GRUPO.setIidgrupo(rSet.getInt("iidgrupo"));
        GRUPO.setVchdescripcion(rSet.getString("vchdescripcion"));
        v.addElement(GRUPO);
    }
    rSet.close();
    stmt.close();
    return v;
}
public Vector findByNotIidusuario(int iidusuario, int iidcategoria)
    throws Exception, java.sql.SQLException {
    StringBuffer query = new StringBuffer();
    Vector v = new Vector();

    dbGRUPO GRUPO;
    query.append("SELECT a.* ");
    query.append("FROM GRUPO a ");
    query.append("LEFT JOIN FABRICANTEGRUPO b ");
    query.append("ON a.iidgrupo=b.iidgrupo AND iidusuario=? ");
    query.append("WHERE b.iidgrupo IS NULL AND a.iidcategoria=? ");

    stmt = conn.prepareStatement(query.toString().toLowerCase());
    
    stmt.setInt(1, iidusuario);
	stmt.setInt(2, iidcategoria);
    

    rSet = stmt.executeQuery();

    while (rSet.next()) {
        GRUPO = new dbGRUPO();
        GRUPO.setIidgrupo(rSet.getInt("iidgrupo"));
        GRUPO.setVchdescripcion(rSet.getString("vchdescripcion"));
        v.addElement(GRUPO);
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
