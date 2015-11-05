/** Vector java bean entity **/
package dbbeans.usuarios;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Vector;


public class dbUSUARIOSs {
	private Connection conn;
	private ResultSet rSet;
	private PreparedStatement stmt;
	private Vector result;


	public dbUSUARIOSs() {
		super();
		result = new Vector();
	}
	public void setConnection(java.sql.Connection conn) {
		this.conn = conn;
	}
	public boolean find(String where) throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		dbUSUARIOS USUARIOS;
		query.append("SELECT * ");
		query.append("FROM USUARIOS ");
		if (!where.equals("")){
			query.append("WHERE " + where );
		}
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		rSet = stmt.executeQuery();


		while (rSet.next()) {
			USUARIOS= new dbUSUARIOS();
			USUARIOS.setIidusuario(rSet.getInt("iidusuario"));
			USUARIOS.setIidsistema(rSet.getInt("iidsistema"));
			USUARIOS.setVchnombreusuario(rSet.getString("vchnombreusuario"));
			USUARIOS.setVchaliasusuario(rSet.getString("vchaliasusuario"));
			USUARIOS.setVchdescripcion(rSet.getString("vchdescripcion"));
			USUARIOS.setVchpasaporte(rSet.getString("vchpasaporte"));
			USUARIOS.setIidgrupo(rSet.getInt("iidgrupo"));
			USUARIOS.setChactivo(rSet.getString("chactivo"));
			USUARIOS.setVchpreferencias(rSet.getString("vchpreferencias"));
			USUARIOS.setInumticks(rSet.getInt("inumticks"));
			result.addElement(USUARIOS);
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

	public Vector findBySistema(int sistema) throws Exception, java.sql.SQLException {

		StringBuffer query = new StringBuffer();
		dbUSUARIOS USUARIOS;
		Vector v = new Vector();
		
		query.append("SELECT * FROM USUARIOS ");
		query.append("WHERE IIDSISTEMA = ? " );
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setInt(1,sistema);		
		rSet = stmt.executeQuery();


		while (rSet.next()) {
			USUARIOS= new dbUSUARIOS();
			USUARIOS.setIidusuario(rSet.getInt("iidusuario"));
			USUARIOS.setIidsistema(rSet.getInt("iidsistema"));
			USUARIOS.setVchnombreusuario(rSet.getString("vchnombreusuario"));
			USUARIOS.setVchaliasusuario(rSet.getString("vchaliasusuario"));
			USUARIOS.setVchdescripcion(rSet.getString("vchdescripcion"));
			USUARIOS.setVchpasaporte(rSet.getString("vchpasaporte"));
			USUARIOS.setIidgrupo(rSet.getInt("iidgrupo"));
			USUARIOS.setChactivo(rSet.getString("chactivo"));
			USUARIOS.setVchpreferencias(rSet.getString("vchpreferencias"));
			USUARIOS.setInumticks(rSet.getInt("inumticks"));
			v.addElement(USUARIOS);
		}
		rSet.close();
		stmt.close();
		return v;
	}
}
