package dbrbeans.parteclasificacion;

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
	
		query.append("SELECT p.*,c.vchdescripcion as vchcategoria,g.vchdescripcion as vchgrupo,l.vchdescripcion as vchlinea,m.vchnombre as vchmarca ");
		query.append("FROM PARTE p, categoria c, grupo g, linea l, marca m, unidad u ");
		query.append("WHERE p.iidcategoria=c.iidcategoria AND p.iidgrupo=g.iidgrupo AND p.iidlinea=l.iidlinea AND p.iidmarca=m.iidmarca AND p.iidunidad=u.iidunidad ");
		query.append("AND vchnumparteequival like ? AND p.bitdadodebaja = ? ");

		if (iidcategoria > 0)
			query.append("AND p.iidcategoria = ? ");
		if (iidgrupo > 0)
			query.append("AND p.iidgrupo = ? ");
		if (iidlinea > 0)
			query.append("AND p.iidlinea = ? ");
		if (iidmarca > 0)
			query.append("AND p.iidmarca = ? ");
		query.append(" ORDER BY p.vchnumparteequival");

		stmt = conn.prepareStatement(query.toString().toLowerCase());

		stmt.setString(1,vchnumparte.toString());
		stmt.setBoolean(2,bitdadodebaja);
		
		int i=2;

		if (iidcategoria > 0)
			stmt.setInt(++i,iidcategoria);
		if (iidgrupo > 0)
			stmt.setInt(++i,iidgrupo);
		if (iidlinea > 0)
			stmt.setInt(++i,iidlinea);
		if (iidmarca > 0)
			stmt.setInt(++i,iidmarca);
		
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
			PARTE.setVchcategoria(rSet.getString("vchcategoria"));
			PARTE.setVchgrupo (rSet.getString("vchgrupo"));
			PARTE.setVchlinea(rSet.getString("vchlinea"));
			PARTE.setVchmarca(rSet.getString("vchmarca"));
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

	public boolean findByPar(int iidcategoria, int iidgrupo, int iidlinea,int iidmarca, String vchnumparte, boolean bitdadodebaja) throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		dbrPARTE PARTE;
		
	
		query.append("SELECT p.*,c.vchdescripcion as vchcategoria,g.vchdescripcion as vchgrupo,l.vchdescripcion as vchlinea,m.vchnombre as vchmarca ");
		query.append("FROM PARTE p, categoria c, grupo g, linea l, marca m ");
		query.append("WHERE p.iidcategoria=c.iidcategoria AND p.iidgrupo=g.iidgrupo AND p.iidlinea=l.iidlinea AND p.iidmarca=m.iidmarca ");
		query.append("AND vchnumparteequival like ? AND p.bitdadodebaja = ? ");

		if (iidcategoria > 0)
			query.append("AND iidcategoria = ? ");
		if (iidgrupo > 0)
			query.append("AND iidgrupo = ? ");
		if (iidlinea > 0)
			query.append("AND iidlinea = ? ");
		if (iidmarca > 0)
			query.append("AND iidmarca = ? ");


		stmt = conn.prepareStatement(query.toString().toLowerCase());

		stmt.setString(1,vchnumparte);
		stmt.setBoolean(2,bitdadodebaja);
		
		int i=2;

		if (iidcategoria > 0)
			stmt.setInt(++i,iidcategoria);
		if (iidgrupo > 0)
			stmt.setInt(++i,iidgrupo);
		if (iidlinea > 0)
			stmt.setInt(++i,iidlinea);
		if (iidmarca > 0)
			stmt.setInt(++i,iidmarca);
		
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
			PARTE.setVchcategoria(rSet.getString("vchcategoria"));
			PARTE.setVchgrupo (rSet.getString("vchgrupo"));
			PARTE.setVchlinea(rSet.getString("vchlinea"));
			PARTE.setVchmarca(rSet.getString("vchmarca"));
			result.addElement(PARTE);
		}
		rSet.close();
		stmt.close();
		return false;
	}

	public int getCountByVchnumparteEquival(String vchnumparteequival) throws Exception, java.sql.SQLException {
		int count = 0;
		StringBuffer query = new StringBuffer();
	
		query.append("SELECT COUNT(*) ");
		query.append("FROM PARTE ");
		query.append("WHERE vchnumparteequival like ? ");

		stmt = conn.prepareStatement(query.toString().toLowerCase());

		stmt.setString(1,vchnumparteequival);

		rSet = stmt.executeQuery();

		if (rSet.next()) 
			count = rSet.getInt(1);
		rSet.close();
		stmt.close();
		
		return count;
	}
}
