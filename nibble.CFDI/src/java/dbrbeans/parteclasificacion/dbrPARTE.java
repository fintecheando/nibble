package dbrbeans.parteclasificacion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class dbrPARTE extends dbbeans.parte.dbPARTE {
    private Connection conn;
    private ResultSet rSet;
    private PreparedStatement stmt;
    private java.lang.String vchcategoria;
    private java.lang.String vchgrupo;
    private java.lang.String vchlinea;
    private java.lang.String vchmarca;
	private java.lang.String vchunidaddesc;
    public dbrPARTE() {
        super();
    }

    public java.lang.String getVchcategoria() {
        return vchcategoria;
    }

    /**
     * Insert the method's description here.
     * Creation date: (11/4/2002 12:33:02 PM)
     * @return java.lang.String
     */
    public java.lang.String getVchgrupo() {
        return vchgrupo;
    }

    /**
     * Insert the method's description here.
     * Creation date: (11/4/2002 12:33:17 PM)
     * @return java.lang.String
     */
    public java.lang.String getVchlinea() {
        return vchlinea;
    }

    /**
     * Insert the method's description here.
     * Creation date: (11/4/2002 12:33:31 PM)
     * @return java.lang.String
     */
    public java.lang.String getVchmarca() {
        return vchmarca;
    }

    public boolean load() throws Exception, java.sql.SQLException {
        StringBuffer query = new StringBuffer();

        query.append(
            "SELECT p.*,c.vchdescripcion as vchcategoria,g.vchdescripcion as vchgrupo,l.vchdescripcion as vchlinea,m.vchnombre as vchmarca, u.vchdescripcion as vchunidaddescripcion ");
        query.append("FROM PARTE p, categoria c, grupo g, linea l, marca m, unidad u ");
        query.append(
            "WHERE p.iidcategoria=c.iidcategoria AND p.iidgrupo=g.iidgrupo AND p.iidlinea=l.iidlinea AND p.iidmarca=m.iidmarca AND p.iidunidad=u.iidunidad ");
        query.append("AND p.liidparte = ? ");
        
        stmt = conn.prepareStatement(query.toString().toLowerCase());

        stmt.setLong(1, getLiidparte());

        rSet = stmt.executeQuery();

        if (rSet.next()) {
            setIidunidad(rSet.getInt("iidunidad"));
            setIidimagen(rSet.getInt("iidimagen"));
            setIidcategoria(rSet.getInt("iidcategoria"));
            setVchnumparte(rSet.getString("vchnumparte"));
            setVchnumparteequival(rSet.getString("vchnumparteequival"));
            setDecpreciob100(rSet.getFloat("decpreciob100"));
            setDecprecio(rSet.getFloat("decprecio"));
            setImultiploempaque(rSet.getInt("imultiploempaque"));
            setBitventaunitaria(rSet.getBoolean("bitventaunitaria"));
            setBitdadodebaja(rSet.getBoolean("bitdadodebaja"));
            setIminembarque(rSet.getInt("iminembarque"));
            setFlalto(rSet.getFloat("flalto"));
            setFllargo(rSet.getFloat("fllargo"));
            setFlancho(rSet.getFloat("flancho"));
            setFlpeso(rSet.getFloat("flpeso"));
            setVchdescripciones(rSet.getString("vchdescripciones"));
            setVchobservaciones(rSet.getString("vchobservaciones"));
            setIidgrupo(rSet.getInt("iidgrupo"));
            setIidlinea(rSet.getInt("iidlinea"));
            setIidmarca(rSet.getInt("iidmarca"));
            setFichatecnica(rSet.getString("fichatecnica"));
            setVchcategoria(rSet.getString("vchcategoria"));
            setVchgrupo(rSet.getString("vchgrupo"));
            setVchlinea(rSet.getString("vchlinea"));
            setVchmarca(rSet.getString("vchmarca"));
            setVchunidaddesc(rSet.getString("vchunidaddescripcion"));
        } else {
            throw new Exception("No se encontro el registro");
        }

        rSet.close();
        stmt.close();

        return false;
    }

    public boolean loadByVchnumparteEquival()
        throws Exception, java.sql.SQLException {
        StringBuffer query = new StringBuffer();

        query.append(
            "SELECT p.*,c.vchdescripcion as vchcategoria,g.vchdescripcion as vchgrupo,l.vchdescripcion as vchlinea,m.vchnombre as vchmarca, u.vchdescripcion as vchunidaddescripcion ");
        query.append("FROM PARTE p, categoria c, grupo g, linea l, marca m, unidad u ");
        query.append(
            "WHERE p.iidcategoria=c.iidcategoria AND p.iidgrupo=g.iidgrupo AND p.iidlinea=l.iidlinea AND p.iidmarca=m.iidmarca AND p.iidunidad=u.iidunidad ");
        query.append("AND p.vchnumparteequival LIKE ? ");

        
        stmt = conn.prepareStatement(query.toString().toLowerCase());

        stmt.setString(1, getVchnumparteequival());

        rSet = stmt.executeQuery();

        if (rSet.next()) {
            setLiidparte(rSet.getLong("liidparte"));
            setIidunidad(rSet.getInt("iidunidad"));
            setIidimagen(rSet.getInt("iidimagen"));
            setIidcategoria(rSet.getInt("iidcategoria"));
            setVchnumparte(rSet.getString("vchnumparte"));
            setVchnumparteequival(rSet.getString("vchnumparteequival"));
            setDecpreciob100(rSet.getFloat("decpreciob100"));
            setDecprecio(rSet.getFloat("decprecio"));
            setImultiploempaque(rSet.getInt("imultiploempaque"));
            setBitventaunitaria(rSet.getBoolean("bitventaunitaria"));
            setBitdadodebaja(rSet.getBoolean("bitdadodebaja"));
            setIminembarque(rSet.getInt("iminembarque"));
            setFlalto(rSet.getFloat("flalto"));
            setFllargo(rSet.getFloat("fllargo"));
            setFlancho(rSet.getFloat("flancho"));
            setFlpeso(rSet.getFloat("flpeso"));
            setVchdescripciones(rSet.getString("vchdescripciones"));
            setVchobservaciones(rSet.getString("vchobservaciones"));
            setIidgrupo(rSet.getInt("iidgrupo"));
            setIidlinea(rSet.getInt("iidlinea"));
            setIidmarca(rSet.getInt("iidmarca"));
            setFichatecnica(rSet.getString("fichatecnica"));
            setVchcategoria(rSet.getString("vchcategoria"));
            setVchgrupo(rSet.getString("vchgrupo"));
            setVchlinea(rSet.getString("vchlinea"));
            setVchmarca(rSet.getString("vchmarca"));
			setVchunidaddesc(rSet.getString("vchunidaddescripcion"));
        } else {
            throw new Exception("No se encontro el registro");
        }

        rSet.close();
        stmt.close();

        return false;
    }

    public void setConnection(java.sql.Connection conn) {
        this.conn = conn;
    }

    /**
     * Insert the method's description here.
     * Creation date: (11/4/2002 12:32:46 PM)
     * @param newVchcategoria java.lang.String
     */
    public void setVchcategoria(java.lang.String newVchcategoria) {
        vchcategoria = newVchcategoria;
    }

    /**
     * Insert the method's description here.
     * Creation date: (11/4/2002 12:33:02 PM)
     * @param newVchgrupo java.lang.String
     */
    public void setVchgrupo(java.lang.String newVchgrupo) {
        vchgrupo = newVchgrupo;
    }

    /**
     * Insert the method's description here.
     * Creation date: (11/4/2002 12:33:17 PM)
     * @param newVchlinea java.lang.String
     */
    public void setVchlinea(java.lang.String newVchlinea) {
        vchlinea = newVchlinea;
    }

    /**
     * Insert the method's description here.
     * Creation date: (11/4/2002 12:33:31 PM)
     * @param newVchmarca java.lang.String
     */
    public void setVchmarca(java.lang.String newVchmarca) {
        vchmarca = newVchmarca;
    }

	/**
	 * @return
	 */
	public java.lang.String getVchunidaddesc() {
		return vchunidaddesc;
	}

	/**
	 * @param string
	 */
	public void setVchunidaddesc(java.lang.String string) {
		vchunidaddesc = string;
	}

}
