/** java bean entity **/
package dbbeans.configuracionpva;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class dbCONFIGURACIONPVA implements org.nibble.main.dbInterfase {
	private Connection conn;
	private ResultSet rSet;
	private PreparedStatement stmt;


	private int iidconfpva;
	private String chletrafactura;
	private int inumerofactura;
	private int inumeronc;
	private int inumeroncm;
	private int inumeronca;
	private String vchnombrepva;
	private String dtfechainstalacion;
	private short sinorengfac;
	private short sinorengncr;
	private short sinorengnca;
	private short sinorengavi;
        private String vchserie;
        private int infolio;
        private String vchrfc;
        private String vchrazonsocial;
        private String vchcalle;
        private String vchnumexterior;
        private String vchnuminterior;
        private String vchcolonia;
        private String vchlocalidad;
        private String vchmunicipio;
        private String vchestado;
        private String vchpais;
        private String vchcodigopostal;
        private int ianoaprobacion;
        private int inumaprobacion;

	public void create() throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		query.append("INSERT INTO CONFIGURACIONPVA (iidconfpva,chletrafactura,inumerofactura,inumeronc,inumeroncm,vchnombrepva,dtfechainstalacion,sinorengfac,sinorengncr,sinorengnca,sinorengavi,inumeronca) ");
		query.append(" VALUES(?,?,?,?,?,?,?,?,?,?,?) ");
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setInt(1,iidconfpva);
		stmt.setString(2,chletrafactura);
		stmt.setInt(3,inumerofactura);
		stmt.setInt(4,inumeronc);
		stmt.setInt(5,inumeroncm);
		stmt.setString(6,vchnombrepva);
		stmt.setString(7,dtfechainstalacion);
		stmt.setShort(8,sinorengfac);
		stmt.setShort(9,sinorengncr);
		stmt.setShort(10,sinorengnca);
		stmt.setShort(11,sinorengavi);
		stmt.setInt(12,inumeronca);
		stmt.executeUpdate();
		stmt.close();;
	}
	public boolean load() throws Exception, java.sql.SQLException {
		boolean result = false;
		StringBuffer query = new StringBuffer();
		query.append("SELECT * ");
		query.append("FROM CONFIGURACIONPVA ");
		query.append("WHERE iidconfpva=? ");
                
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setInt(1,iidconfpva);
		rSet = stmt.executeQuery(); 

		if (rSet.next()){
			
			result = true;
			this.chletrafactura = rSet.getString("chletrafactura");
			this.inumerofactura = rSet.getInt("inumerofactura");
			this.inumeronc = rSet.getInt("inumeronc");
			this.inumeroncm = rSet.getInt("inumeroncm");
			this.vchnombrepva = rSet.getString("vchnombrepva");
			this.dtfechainstalacion = rSet.getString("dtfechainstalacion");
			this.sinorengfac = rSet.getShort("sinorengfac");
			this.sinorengncr = rSet.getShort("sinorengncr");
			this.sinorengnca = rSet.getShort("sinorengnca");
			this.sinorengavi = rSet.getShort("sinorengavi");
			this.inumeronca = rSet.getInt("inumeronca");
                        this.vchserie = rSet.getString("vchserie");
                        this.vchrfc = rSet.getString("vchrfc");
                        this.infolio = rSet.getInt("infolio");
                        this.vchrazonsocial = rSet.getString("vchrazonsocial");
                        this.vchcalle = rSet.getString("vchcalle");
                        this.vchnumexterior = rSet.getString("vchnumexterior");                        
                        this.vchnuminterior = rSet.getString("vchnuminterior");                       
                        this.vchcolonia = rSet.getString("vchcolonia");
                        this.vchlocalidad = rSet.getString("vchlocalidad");
                        this.vchmunicipio = rSet.getString("vchmunicipio");
                        this.vchestado = rSet.getString("vchestado");
                        this.vchpais = rSet.getString("vchpais");
                        this.vchcodigopostal = rSet.getString("vchcodigopostal");
                        this.ianoaprobacion = rSet.getInt("ianoaprobacion");
                        this.inumaprobacion = rSet.getInt("inumaprobacion");
		}
		else
			throw new Exception ("No se encontro el registro");
		rSet.close();
		stmt.close();
		return result;
	}
	public void remove() throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		query.append("DELETE FROM CONFIGURACIONPVA ");
		query.append("WHERE iidconfpva=? ");
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setInt(1,iidconfpva);
		stmt.executeUpdate();
		stmt.close();
	}
	public void store() throws SQLException {
		StringBuffer query = new StringBuffer();
		query.append("UPDATE CONFIGURACIONPVA SET chletrafactura=? , inumerofactura=? , "
                        + "inumeronc=? , inumeroncm=? , vchnombrepva=? , dtfechainstalacion=? , "
                        + "sinorengfac=? , sinorengncr=? , sinorengnca=? , sinorengavi=?, inumeronca=?, "
                        + "vchrfc=? , vchrazonsocial=? , vchcalle=? , vchnumexterior=? , vchnuminterior=? , "
                        + "vchcolonia=? , vchlocalidad=? , vchmunicipio=? , vchestado=? , vchpais=? , "
                        + "vchcodigopostal=? , ianoaprobacion=? , inumaprobacion=? ");
		query.append(" WHERE iidconfpva=? ");

                

		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setString(1,chletrafactura);		
		stmt.setInt(2,inumerofactura);		
		stmt.setInt(3,inumeronc);
		stmt.setInt(4,inumeroncm);
		stmt.setString(5,vchnombrepva);
		stmt.setString(6,dtfechainstalacion);
		stmt.setShort(7,sinorengfac);		
		stmt.setShort(8,sinorengncr);		
		stmt.setShort(9,sinorengnca);		
		stmt.setShort(10,sinorengavi);
                stmt.setInt(11,inumeronca);
                stmt.setString(12,vchrfc);
                stmt.setString(13,vchrazonsocial);
                stmt.setString(14,vchcalle);
                stmt.setString(15,vchnumexterior);
                stmt.setString(16,vchnuminterior);
                stmt.setString(17,vchcolonia);
                stmt.setString(18,vchlocalidad);
                stmt.setString(19,vchmunicipio);
                stmt.setString(20,vchestado);
                stmt.setString(21,vchpais);
                stmt.setString(22,vchcodigopostal);
                stmt.setInt(23,ianoaprobacion);
                stmt.setInt(24,inumaprobacion);
		stmt.setInt(25,iidconfpva);
		stmt.executeUpdate();
		stmt.close();
	}
	public void setConnection(java.sql.Connection conn) {
		this.conn = conn;
	}
	public void setIidconfpva(int iidconfpva){
		this.iidconfpva=iidconfpva;
	}
	public int getIidconfpva(){
		return this.iidconfpva;
	}
	public void setChletrafactura(String chletrafactura){
		this.chletrafactura=chletrafactura;
	}
	public String getChletrafactura(){
		return this.chletrafactura;
	}
	public void setInumerofactura(int inumerofactura){
		this.inumerofactura=inumerofactura;
	}
	public int getInumerofactura(){
		return this.inumerofactura;
	}
	public void setInumeronc(int inumeronc){
		this.inumeronc=inumeronc;
	}
	public int getInumeronc(){
		return this.inumeronc;
	}
	public void setInumeroncm(int inumeroncm){
		this.inumeroncm=inumeroncm;
	}
	public int getInumeroncm(){
		return this.inumeroncm;
	}
	public void setVchnombrepva(String vchnombrepva){
		this.vchnombrepva=vchnombrepva;
	}
	public String getVchnombrepva(){
		return this.vchnombrepva;
	}
	public void setDtfechainstalacion(String dtfechainstalacion){
		this.dtfechainstalacion=dtfechainstalacion;
	}
	public String getDtfechainstalacion(){
		return this.dtfechainstalacion;
	}
	public void setSinorengfac(short sinorengfac){
		this.sinorengfac=sinorengfac;
	}
	public short getSinorengfac(){
		return this.sinorengfac;
	}
	public void setSinorengncr(short sinorengncr){
		this.sinorengncr=sinorengncr;
	}
	public short getSinorengncr(){
		return this.sinorengncr;
	}
	public void setSinorengnca(short sinorengnca){
		this.sinorengnca=sinorengnca;
	}
	public short getSinorengnca(){
		return this.sinorengnca;
	}
	public void setSinorengavi(short sinorengavi){
		this.sinorengavi=sinorengavi;
	}
	public short getSinorengavi(){
		return this.sinorengavi;
	}
	public int getInumeronca() {
		return inumeronca;
	}
	public void setInumeronca(int i) {
		inumeronca = i;
	}

    /**
     * @return the vchserie
     */
    public String getVchserie() {
        return vchserie;
    }

    /**
     * @param vchserie the vchserie to set
     */
    public void setVchserie(String vchserie) {
        this.vchserie = vchserie;
    }

    /**
     * @return the infolio
     */
    public int getInfolio() {
        return infolio;
    }

    /**
     * @param infolio the infolio to set
     */
    public void setInfolio(int infolio) {
        this.infolio = infolio;
    }

    /**
     * @return the vchrfc
     */
    public String getVchrfc() {
        return vchrfc;
    }

    /**
     * @param vchrfc the vchrfc to set
     */
    public void setVchrfc(String vchrfc) {
        this.vchrfc = vchrfc;
    }

    /**
     * @return the vchrazonsocial
     */
    public String getVchrazonsocial() {
        return vchrazonsocial;
    }

    /**
     * @param vchrazonsocial the vchrazonsocial to set
     */
    public void setVchrazonsocial(String vchrazonsocial) {
        this.vchrazonsocial = vchrazonsocial;
    }

    /**
     * @return the vchcalle
     */
    public String getVchcalle() {
        return vchcalle;
    }

    /**
     * @param vchcalle the vchcalle to set
     */
    public void setVchcalle(String vchcalle) {
        this.vchcalle = vchcalle;
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
     * @return the vchcolonia
     */
    public String getVchcolonia() {
        return vchcolonia;
    }

    /**
     * @param vchcolonia the vchcolonia to set
     */
    public void setVchcolonia(String vchcolonia) {
        this.vchcolonia = vchcolonia;
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

    /**
     * @return the vchmunicipio
     */
    public String getVchmunicipio() {
        return vchmunicipio;
    }

    /**
     * @param vchmunicipio the vchmunicipio to set
     */
    public void setVchmunicipio(String vchmunicipio) {
        this.vchmunicipio = vchmunicipio;
    }

    /**
     * @return the vchestado
     */
    public String getVchestado() {
        return vchestado;
    }

    /**
     * @param vchestado the vchestado to set
     */
    public void setVchestado(String vchestado) {
        this.vchestado = vchestado;
    }

    /**
     * @return the vchpais
     */
    public String getVchpais() {
        return vchpais;
    }

    /**
     * @param vchpais the vchpais to set
     */
    public void setVchpais(String vchpais) {
        this.vchpais = vchpais;
    }

    /**
     * @return the vchcodigopostal
     */
    public String getVchcodigopostal() {
        return vchcodigopostal;
    }

    /**
     * @param vchcodigopostal the vchcodigopostal to set
     */
    public void setVchcodigopostal(String vchcodigopostal) {
        this.vchcodigopostal = vchcodigopostal;
    }

     /**
     * @return the ianoaprobacion
     */
    public int getIanoaprobacion() {
        return ianoaprobacion;
    }

    /**
     * @param ianoaprobacion the ianoaprobacion to set
     */
    public void setIanoaprobacion(int ianoaprobacion) {
        this.ianoaprobacion = ianoaprobacion;
    }

    /**
     * @return the inumaprobacion
     */
    public int getInumaprobacion() {
        return inumaprobacion;
    }

    /**
     * @param inumaprobacion the inumaprobacion to set
     */
    public void setInumaprobacion(int inumaprobacion) {
        this.inumaprobacion = inumaprobacion;
    }

}
