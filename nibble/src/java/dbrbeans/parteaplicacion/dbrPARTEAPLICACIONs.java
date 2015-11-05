package dbrbeans.parteaplicacion;

import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Vector;

public class dbrPARTEAPLICACIONs {
	private Connection conn;
	private ResultSet rSet;
	private PreparedStatement stmt;
	private Vector result;

public dbrPARTEAPLICACIONs() {
	super();
	result = new Vector();
}
public boolean findByAplicacion(int iidaplicacion) throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		String vchnumparte = new String();

		dbrPARTEAPLICACION PARTE;
		query.append("SELECT p.*,ap.ticantidad ");
		query.append("FROM PARTE p,APLICACIONPARTE ap ");
		query.append("WHERE p.liidparte=ap.liidparte ");
		query.append("AND iidaplicacion=? ");

		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setInt(1,iidaplicacion);
			
		rSet = stmt.executeQuery();

		while (rSet.next()) {
			PARTE= new dbrPARTEAPLICACION();
			PARTE.setLiidparte(rSet.getLong("liidparte"));
			PARTE.setIidunidad(rSet.getInt("iidunidad"));
			PARTE.setIidimagen(rSet.getInt("iidimagen"));
			PARTE.setIidcategoria(rSet.getInt("iidcategoria"));
			PARTE.setVchnumparte(rSet.getString("vchnumparte"));
			PARTE.setVchnumparteequival(rSet.getString("vchnumparteequival"));
			PARTE.setDecpreciob100(rSet.getFloat("decpreciob100"));
			PARTE.setDecprecio(rSet.getFloat("decprecio"));
			PARTE.setImultiploempaque(rSet.getInt("imultiploempaque"));
			PARTE.setBitventaunitaria(rSet.getBoolean("bitventaunitaria"));
			PARTE.setBitdadodebaja(rSet.getBoolean("bitdadodebaja"));
			PARTE.setIminembarque(rSet.getInt("iminembarque"));
			PARTE.setFlalto(rSet.getFloat("flalto"));
			PARTE.setFllargo(rSet.getFloat("fllargo"));
			PARTE.setFlancho(rSet.getFloat("flancho"));
			PARTE.setFlpeso(rSet.getFloat("flpeso"));
			PARTE.setVchdescripciones(rSet.getString("vchdescripciones"));
			PARTE.setVchobservaciones(rSet.getString("vchobservaciones"));
			PARTE.setIidgrupo(rSet.getInt("iidgrupo"));
			PARTE.setIidlinea(rSet.getInt("iidlinea"));
			PARTE.setIidmarca(rSet.getInt("iidmarca"));
			PARTE.setFichatecnica(rSet.getString("fichatecnica"));
			PARTE.setTicantidad(rSet.getShort("ticantidad"));
			result.addElement(PARTE);
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

public boolean findByAplicacion(int iidaplicacion, int iidcategoria) throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		String vchnumparte = new String();

		dbrPARTEAPLICACION PARTE;
		query.append("SELECT p.*,ap.ticantidad ");
		query.append("FROM PARTE p,APLICACIONPARTE ap ");
		query.append("WHERE p.liidparte=ap.liidparte ");
		query.append("AND iidaplicacion=? ");
		query.append("AND p.iidcategoria=? ");

		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setInt(1,iidaplicacion);
		stmt.setInt(2,iidcategoria);
			
		rSet = stmt.executeQuery();

		while (rSet.next()) {
			PARTE= new dbrPARTEAPLICACION();
			PARTE.setLiidparte(rSet.getLong("liidparte"));
			PARTE.setIidunidad(rSet.getInt("iidunidad"));
			PARTE.setIidimagen(rSet.getInt("iidimagen"));
			PARTE.setIidcategoria(rSet.getInt("iidcategoria"));
			PARTE.setVchnumparte(rSet.getString("vchnumparte"));
			PARTE.setVchnumparteequival(rSet.getString("vchnumparteequival"));
			PARTE.setDecpreciob100(rSet.getFloat("decpreciob100"));
			PARTE.setDecprecio(rSet.getFloat("decprecio"));
			PARTE.setImultiploempaque(rSet.getInt("imultiploempaque"));
			PARTE.setBitventaunitaria(rSet.getBoolean("bitventaunitaria"));
			PARTE.setBitdadodebaja(rSet.getBoolean("bitdadodebaja"));
			PARTE.setIminembarque(rSet.getInt("iminembarque"));
			PARTE.setFlalto(rSet.getFloat("flalto"));
			PARTE.setFllargo(rSet.getFloat("fllargo"));
			PARTE.setFlancho(rSet.getFloat("flancho"));
			PARTE.setFlpeso(rSet.getFloat("flpeso"));
			PARTE.setVchdescripciones(rSet.getString("vchdescripciones"));
			PARTE.setVchobservaciones(rSet.getString("vchobservaciones"));
			PARTE.setIidgrupo(rSet.getInt("iidgrupo"));
			PARTE.setIidlinea(rSet.getInt("iidlinea"));
			PARTE.setIidmarca(rSet.getInt("iidmarca"));
			PARTE.setFichatecnica(rSet.getString("fichatecnica"));
			PARTE.setTicantidad(rSet.getShort("ticantidad"));
			result.addElement(PARTE);
		}
		rSet.close();
		stmt.close();
		return false;
	}
}
