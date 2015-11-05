package dbrbeans.parteunidad;

import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Vector;


public class dbrPARTEs {
	private Connection conn;
	private ResultSet rSet;
	private PreparedStatement stmt;
	private Vector result;


	public dbrPARTEs() {
		super();
		result = new Vector();
	}
	public boolean findByPar(int iidcategoria, int iidgrupo, int iidlinea,int iidmarca, String prefijo, String numero, String sufijo, String medida, boolean bitdadodebaja) throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		dbrPARTE PARTE;

		String vchnumparte = general.Help.getVchnumparte(prefijo,numero,sufijo,medida);
		String vchnumpartePattern = general.Help.getVchnumparte("","","","");

		query.append("SELECT p.*,u.vchdescripcion as vchunidad ");
		query.append("FROM PARTE p, UNIDAD u ");
		query.append("WHERE p.iidunidad=u.iidunidad ");
		query.append("AND p.bitdadodebaja = ? ");
		
		if (iidcategoria > 0)
			query.append("AND p.iidcategoria = ? ");
		if (iidgrupo > 0)
			query.append("AND p.iidgrupo = ? ");
		if (iidlinea > 0)
			query.append("AND p.iidlinea = ? ");
		if (iidmarca > 0)
			query.append("AND p.iidmarca = ? ");
		if (!vchnumparte.equalsIgnoreCase(vchnumpartePattern))
			query.append("AND vchnumparte like ? ");

		stmt = conn.prepareStatement(query.toString().toLowerCase());

		stmt.setBoolean(1,bitdadodebaja);
		
		int i=1;

		if (iidcategoria > 0)
			stmt.setInt(++i,iidcategoria);
		if (iidgrupo > 0)
			stmt.setInt(++i,iidgrupo);
		if (iidlinea > 0)
			stmt.setInt(++i,iidlinea);
		if (iidmarca > 0)
			stmt.setInt(++i,iidmarca);
		if (!vchnumparte.equalsIgnoreCase(vchnumpartePattern))
			stmt.setString(++i,vchnumparte);
		
		rSet = stmt.executeQuery();

		while (rSet.next()) {
			PARTE= new dbrPARTE();
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
			PARTE.setVchunidad(rSet.getString("vchunidad"));
			result.addElement(PARTE);
		}
		rSet.close();
		stmt.close();
		return false;
	}
	public boolean findByPar(int iidcategoria, int iidgrupo, int iidlinea,int iidmarca, String vchnumparte, boolean bitdadodebaja) throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		dbrPARTE PARTE;

		String vchnumpartePattern = general.Help.getVchnumparte("","","","");
	
		query.append("SELECT p.*,u.vchdescripcion as vchunidad ");
		query.append("FROM PARTE p, UNIDAD u ");
		query.append("WHERE p.iidunidad=u.iidunidad ");
		query.append("AND p.bitdadodebaja = ? ");

		if (iidcategoria > 0)
			query.append("AND iidcategoria = ? ");
		if (iidgrupo > 0)
			query.append("AND iidgrupo = ? ");
		if (iidlinea > 0)
			query.append("AND iidlinea = ? ");
		if (iidmarca > 0)
			query.append("AND iidmarca = ? ");
		if (!vchnumparte.equalsIgnoreCase(vchnumpartePattern))
			query.append("AND vchnumparte like ? ");


		stmt = conn.prepareStatement(query.toString().toLowerCase());

		stmt.setBoolean(1,bitdadodebaja);
		
		int i=1;

		if (iidcategoria > 0)
			stmt.setInt(++i,iidcategoria);
		if (iidgrupo > 0)
			stmt.setInt(++i,iidgrupo);
		if (iidlinea > 0)
			stmt.setInt(++i,iidlinea);
		if (iidmarca > 0)
			stmt.setInt(++i,iidmarca);
		if (!vchnumparte.equalsIgnoreCase(vchnumpartePattern))
			stmt.setString(++i,vchnumparte);
		
		rSet = stmt.executeQuery();

		while (rSet.next()) {
			PARTE= new dbrPARTE();
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
			PARTE.setVchunidad(rSet.getString("vchunidad"));
			result.addElement(PARTE);
		}
		rSet.close();
		stmt.close();
		return false;
	}
	public int getCountByVchnumparte(String vchnumparte) throws Exception, java.sql.SQLException {
		int count = 0;
		StringBuffer query = new StringBuffer();
	
		query.append("SELECT COUNT(*) ");
		query.append("FROM PARTE ");
		query.append("WHERE vchnumparte like ? ");

		stmt = conn.prepareStatement(query.toString().toLowerCase());

		stmt.setString(1,vchnumparte);

		rSet = stmt.executeQuery();

		if (rSet.next()) 
			count = rSet.getInt(1);
		rSet.close();
		stmt.close();
		
		return count;
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
