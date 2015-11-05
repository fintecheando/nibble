package dbrbeans.clientepaisestadomunicipio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


/**
 * DOCUMENT ME!
 *
 * @author $author$
 * @version $Revision$
 */
public class dbrCLIENTE extends dbbeans.cliente.dbCLIENTE {

    private Connection conn;
    private ResultSet rSet;
    private PreparedStatement stmt;

    private String vchpaisdes;
    private String vchestadodes;
    private String vchmunicipiodes;
    private String vchcategoriades;
    private String vchniveldes;
    private String vchviades;
    private String vchnumexterior;
    private String vchnuminterior;
    private String vchlocalidad;
    private float decutilidad;

    /**
     * Insert the method's description here.
     * Creation date: (11/8/2002 12:12:45 PM)
     * @return float
     */
    public float getDecutilidad() {
        return decutilidad;
    }

    /**
     * Insert the method's description here.
     * Creation date: (11/1/2002 4:08:17 PM)
     * @return java.lang.String
     */
    public java.lang.String getVchcategoriades() {
        return vchcategoriades;
    }

    /**
     * Insert the method's description here.
     * Creation date: (11/1/2002 3:50:42 PM)
     * @return java.lang.String
     */
    public java.lang.String getVchestadodes() {
        return vchestadodes;
    }

    /**
     * Insert the method's description here.
     * Creation date: (11/1/2002 3:56:00 PM)
     * @return java.lang.String
     */
    public java.lang.String getVchmunicipiodes() {
        return vchmunicipiodes;
    }

    /**
     * Insert the method's description here.
     * Creation date: (11/1/2002 4:13:14 PM)
     * @return java.lang.String
     */
    public java.lang.String getVchniveldes() {
        return vchniveldes;
    }

    /**
     * Insert the method's description here.
     * Creation date: (11/1/2002 3:50:18 PM)
     * @return java.lang.String
     */
    public java.lang.String getVchpaisdes() {
        return vchpaisdes;
    }

    /**
     * Insert the method's description here.
     * Creation date: (11/1/2002 4:19:07 PM)
     * @return java.lang.String
     */
    public java.lang.String getVchviades() {
        return vchviades;
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     *
     * @throws Exception DOCUMENT ME!
     * @throws java.sql.SQLException DOCUMENT ME!
     */
    public boolean load() throws Exception, java.sql.SQLException {
    	boolean found = false;
    	StringBuffer query = new StringBuffer();

        query.append("SELECT c.*,p.vchnompais,e.vchnombre as estado,m.vchnombre as municipio, ");
        query.append("cc.vchdescripcion,np.vchnivel,np.decutilidad, ve.vchdescviaembarque ");
        query.append("FROM CLIENTE c ");
        query.append("LEFT JOIN PAIS p ON c.siidpais=p.siidpais ");
        query.append("LEFT JOIN ESTADO e ON c.iidestado=e.iidestado ");
        query.append("LEFT JOIN MUNICIPIO m ON c.iidmunicipio=m.iidmunicipio ");
        query.append("LEFT JOIN CATEGORIACLIENTE cc ON c.iidcatcliente=cc.iidcatcliente ");
        query.append("LEFT JOIN NIVELDEPRECIO np ON c.iidnivel=np.iidnivel ");
        query.append("LEFT JOIN VIADEEMBARQUE ve ON c.siidviaembarque=ve.siidviaembarque ");
        query.append("WHERE c.iidnocliente=? ");      
        query.append("ORDER BY c.iidnocliente ");
        stmt = conn.prepareStatement(query.toString().toLowerCase());
        stmt.setInt(1, getIidnocliente());

        rSet = stmt.executeQuery();

        if (rSet.next()) { 
        	found = true;       
            setSiidpais(rSet.getShort("siidpais"));
            setSiidviaembarque(rSet.getShort("siidviaembarque"));
            setIidnivel(rSet.getInt("iidnivel"));
            setIidcatcliente(rSet.getInt("iidcatcliente"));
            setVchrfc(rSet.getString("vchrfc"));
            setVchnombre(rSet.getString("vchnombre"));
            setVchrazonsocial(rSet.getString("vchrazonsocial"));
            setVchcalle(rSet.getString("vchcalle"));
            setVchcolonia(rSet.getString("vchcolonia"));
            setChcodigop(rSet.getString("chcodigop"));
            setVchmail(rSet.getString("vchmail"));
            setVchdescregion(rSet.getString("vchdescregion"));
            setVchdescestado(rSet.getString("vchdescestado"));
            setVchtel1(rSet.getString("vchtel1"));
            setVchtel2(rSet.getString("vchtel2"));
            setVchfax(rSet.getString("vchfax"));
            setVchcurp(rSet.getString("vchcurp"));
            setBitdadodebaja(rSet.getBoolean("bitdadodebaja"));
            setDeclimitecredito(rSet.getFloat("declimitecredito"));
            setDeccreditutilizado(rSet.getFloat("deccreditutilizado"));
            setSidiadepago(rSet.getShort("sidiadepago"));
            setBsuceptiblecredito(rSet.getBoolean("bsuceptiblecredito"));
            setSiplazodiaspago(rSet.getShort("siplazodiaspago"));
            setIidestado(rSet.getInt("iidestado"));
            setIidmunicipio(rSet.getInt("iidmunicipio"));
            setVchpaisdes(rSet.getString("vchnompais"));
            setVchestadodes(rSet.getString("estado"));
            setVchmunicipiodes(rSet.getString("municipio"));
            setVchcategoriades(rSet.getString("vchdescripcion"));
            setVchniveldes(rSet.getString("vchnivel"));
            setDecutilidad(rSet.getFloat("decutilidad"));
            setVchviades(rSet.getString("vchdescviaembarque"));
            setVchnumexterior(rSet.getString("vchnumexterior"));
            
            setVchnuminterior(rSet.getString("vchnuminterior"));
            setVchlocalidad(rSet.getString("vchlocalidad"));
            
        }
        rSet.close();
        stmt.close();
		
		if (!found)throw new Exception("Cliente no encontrado");
        return found;
    }

    /**
     * DOCUMENT ME!
     *
     * @param conn DOCUMENT ME!
     */
    public void setConnection(java.sql.Connection conn) {
        this.conn = conn;
    }

    /**
     * Insert the method's description here.
     * Creation date: (11/8/2002 12:12:45 PM)
     * @param newDecutilidad float
     */
    public void setDecutilidad(float newDecutilidad) {
        decutilidad = newDecutilidad;
    }

    /**
     * Insert the method's description here.
     * Creation date: (11/1/2002 4:08:17 PM)
     * @param newVchcategoriades java.lang.String
     */
    public void setVchcategoriades(java.lang.String newVchcategoriades) {
        vchcategoriades = newVchcategoriades;
    }

    /**
     * Insert the method's description here.
     * Creation date: (11/1/2002 3:50:42 PM)
     * @param newVchestadodes java.lang.String
     */
    public void setVchestadodes(java.lang.String newVchestadodes) {
        vchestadodes = newVchestadodes;
    }

    /**
     * Insert the method's description here.
     * Creation date: (11/1/2002 3:56:00 PM)
     * @param newVchmunicipiodes java.lang.String
     */
    public void setVchmunicipiodes(java.lang.String newVchmunicipiodes) {
        vchmunicipiodes = newVchmunicipiodes;
    }

    /**
     * Insert the method's description here.
     * Creation date: (11/1/2002 4:13:14 PM)
     * @param newVchniveldes java.lang.String
     */
    public void setVchniveldes(java.lang.String newVchniveldes) {
        vchniveldes = newVchniveldes;
    }

    /**
     * Insert the method's description here.
     * Creation date: (11/1/2002 3:50:18 PM)
     * @param newVchpaisdes java.lang.String
     */
    public void setVchpaisdes(java.lang.String newVchpaisdes) {
        vchpaisdes = newVchpaisdes;
    }

    /**
     * Insert the method's description here.
     * Creation date: (11/1/2002 4:19:07 PM)
     * @param newVchviades java.lang.String
     */
    public void setVchviades(java.lang.String newVchviades) {
        vchviades = newVchviades;
    }

    /**
     * @return the vchnumexterior
     */
    public String getVchnumexterior() {
        
        return vchnumexterior;
    }

    /**
     * @param vchnumexterior the vchnumexterior to set
     */
    public void setVchnumexterior(String vchnumexterior) {
        
        this.vchnumexterior = vchnumexterior;
    }

    /**
     * @return the vchnuminterior
     */
    public String getVchnuminterior() {
        return vchnuminterior;
    }

    /**
     * @param vchnuminterior the vchnuminterior to set
     */
    public void setVchnuminterior(String vchnuminterior) {
        this.vchnuminterior = vchnuminterior;
    }

    /**
     * @return the vchlocalidad
     */
    public String getVchlocalidad() {
        return vchlocalidad;
    }

    /**
     * @param vchlocalidad the vchlocalidad to set
     */
    public void setVchlocalidad(String vchlocalidad) {
        this.vchlocalidad = vchlocalidad;
    }
}
