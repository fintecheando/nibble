package dbrbeans.movimieninventario;

import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Vector;

public class dbrMOVIMIENINVENTARIOs {
	private Connection conn;
	private ResultSet rSet;
	private PreparedStatement stmt;
	private Vector result;

public dbrMOVIMIENINVENTARIOs() {
	super();
	result = new Vector();
}
public boolean findByPar(String fechaini,String fechafin,int tipoMovimiento,String prefijo,String numero,String sufijo, String medida, int categoria, int grupo, int linea, int marca) throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		
		String vchnumparte = general.Help.getVchnumparte(prefijo,numero,sufijo,medida);
		String vchnumpartePattern = general.Help.getVchnumparte("","","","");

		dbrMOVIMIENINVENTARIO dbrMovimientoinventario;
		query.append("SELECT m.*,p.vchnumparte,t.vchdesctipomov,u.vchnombreusuario, pro.vchrazonsocial ");
		query.append("FROM MOVIMIENINVENTARIO m, PARTE p, TIPODEMOVIMIENTO t, USUARIOS u, PROVEEDOR pro ");
		query.append("WHERE m.liidparte=p.liidparte ");
		query.append("AND m.titipomov=t.titipomov ");
		query.append("AND m.iidusuario=u.iidusuario ");
		query.append("AND m.iidproveedor=pro.iidproveedor ");
		if (fechaini.length()>0)
			query.append("AND m.tisfechamov BETWEEN ? AND ? ");
		if (tipoMovimiento>0)
			query.append("AND m.titipomov=? ");
		if (!vchnumparte.equals(vchnumpartePattern))
			query.append("AND p.vchnumparte like ? ");
		if (categoria>0)
			query.append("AND p.iidcategoria=? AND p.iidgrupo=? AND iidlinea=? AND iidmarca=? ");
		query.append("ORDER BY m.titipomov, m.tisfechamov ");

		stmt = conn.prepareStatement(query.toString().toLowerCase());
			
		int i=1;

		if (fechaini.length()>0){
			stmt.setString(i++,fechaini);
			stmt.setString(i++,fechafin);
		}
		if (tipoMovimiento>0)
			stmt.setInt(i++,tipoMovimiento);
		if (!vchnumparte.equals(vchnumpartePattern))
			stmt.setString(i++,vchnumparte);
		if (categoria>0){
			stmt.setInt(i++,categoria);
			stmt.setInt(i++,grupo);
			stmt.setInt(i++,linea);
			stmt.setInt(i++,marca);
		}

		rSet = stmt.executeQuery();

		while (rSet.next()) {
			dbrMovimientoinventario= new dbrMOVIMIENINVENTARIO();
			dbrMovimientoinventario.setVchnumparte(rSet.getString("vchnumparte"));
			dbrMovimientoinventario.setTitipomov(rSet.getInt("titipomov"));
			dbrMovimientoinventario.setTipodemovimiento(rSet.getString("vchdesctipomov"));
			dbrMovimientoinventario.setFechaMovimiento(rSet.getString("tisfechamov"));
			dbrMovimientoinventario.setNombreUsuario(rSet.getString("vchnombreusuario"));
			dbrMovimientoinventario.setCantidad(rSet.getInt("icantidad"));
			dbrMovimientoinventario.setReferencia(rSet.getString("iidreferencia"));
			dbrMovimientoinventario.setVchrazonsocial(rSet.getString("vchrazonsocial"));
			result.addElement(dbrMovimientoinventario);
		}
		rSet.close();
		stmt.close();
		return false;
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
