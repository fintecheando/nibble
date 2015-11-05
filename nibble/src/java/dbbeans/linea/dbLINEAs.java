package dbbeans.linea;

import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Vector;


public class dbLINEAs {
	protected Connection conn;
	protected ResultSet rSet;
	protected PreparedStatement stmt;
	protected Vector result;
	public dbLINEAs() {
		super();
		result = new Vector();
	}
public int CountMarcas(int iidusuario,int iidlinea)
    throws Exception, java.sql.SQLException {
    StringBuffer query = new StringBuffer();
    int count=0;

    query.append("SELECT count(*) ");
    query.append("FROM FABRICANTEMARCA a,MARCA b ");
    query.append("WHERE  a.iidmarca=b.iidmarca ");
    query.append("and  b.iidlinea=? ");
    query.append("and  a.iidusuario=? ");

    stmt = conn.prepareStatement(query.toString().toLowerCase());
    
	stmt.setInt(1, iidlinea);
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
		dbLINEA LINEA;
		query.append("SELECT * ");
		query.append("FROM LINEA ");
		if (!where.equals("")){
			query.append("WHERE " + where +" ORDER BY vchdescripcion DESC");
		}
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		rSet = stmt.executeQuery();


		while (rSet.next()) {
			LINEA= new dbLINEA();
			LINEA.setIidlinea(rSet.getInt("iidlinea"));
			LINEA.setVchdescripcion(rSet.getString("vchdescripcion"));
			LINEA.setIidgrupo(rSet.getInt("iidgrupo"));
			result.addElement(LINEA);
		}
		rSet.close();
		stmt.close();
		return false;
	}
public Vector findByIidusuario(int iidusuario)
    throws Exception, java.sql.SQLException {
    StringBuffer query = new StringBuffer();
    Vector v = new Vector();

    dbLINEA LINEA;
    query.append("SELECT a.* ");
    query.append("FROM LINEA a,FABRICANTELINEA b ");
    query.append("WHERE  a.iidlinea=b.iidlinea ");
    query.append("AND b.iidusuario=? ORDER BY vchdescripcion");

    stmt = conn.prepareStatement(query.toString().toLowerCase());
    
    stmt.setInt(1, iidusuario);

    rSet = stmt.executeQuery();

    while (rSet.next()) {
        LINEA = new dbLINEA();
        LINEA.setIidlinea(rSet.getInt("iidlinea"));
        LINEA.setVchdescripcion(rSet.getString("vchdescripcion"));
        LINEA.setIidgrupo(rSet.getInt("iidgrupo"));
        v.addElement(LINEA);
    }
    rSet.close();
    stmt.close();
    return v;
}
public Vector findByIidusuario(int iidusuario, int iidgrupo)
    throws Exception, java.sql.SQLException {
    StringBuffer query = new StringBuffer();
    Vector v = new Vector();

    dbLINEA LINEA;
    query.append("SELECT a.* ");
    query.append("FROM LINEA a,FABRICANTELINEA b ");
    query.append("WHERE  a.iidlinea=b.iidlinea ");
    query.append("AND a.iidgrupo=? ");
    query.append("AND b.iidusuario=? ORDER BY vchdescripcion DESC");

    stmt = conn.prepareStatement(query.toString().toLowerCase());
    
	stmt.setInt(1, iidgrupo);
    stmt.setInt(2, iidusuario);

    rSet = stmt.executeQuery();

    while (rSet.next()) {
        LINEA = new dbLINEA();
        LINEA.setIidlinea(rSet.getInt("iidlinea"));
        LINEA.setVchdescripcion(rSet.getString("vchdescripcion"));
        v.addElement(LINEA);
    }
    rSet.close();
    stmt.close();
    return v;
}
public Vector findByNotIidusuario(int iidusuario, int iidgrupo)
    throws Exception, java.sql.SQLException {
    StringBuffer query = new StringBuffer();
    Vector v = new Vector();

    dbLINEA LINEA;
    query.append("SELECT a.* ");
    query.append("FROM LINEA a ");
    query.append("LEFT JOIN FABRICANTELINEA b ");
    query.append("ON a.iidlinea=b.iidlinea AND iidusuario=? ");
    query.append("WHERE b.iidlinea IS NULL AND a.iidgrupo=? ORDER BY vchdescripcion DESC");

    stmt = conn.prepareStatement(query.toString().toLowerCase());
    
    stmt.setInt(1, iidusuario);
    stmt.setInt(2, iidgrupo);

    rSet = stmt.executeQuery();

    while (rSet.next()) {
        LINEA = new dbLINEA();
        LINEA.setIidlinea(rSet.getInt("iidlinea"));
        LINEA.setVchdescripcion(rSet.getString("vchdescripcion"));
        v.addElement(LINEA);
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
