/** java bean entity **/
package dbbeans.cliente;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.apache.log4j.Logger;

public class dbCLIENTE implements org.nibble.main.dbInterfase {
	private Connection conn;
	private ResultSet rSet;
	private PreparedStatement stmt;
        static Logger logger= Logger.getLogger(dbCLIENTE.class);

	private int iidnocliente;
	private short siidpais;
	private short siidviaembarque;
	private int iidnivel;
	private int iidcatcliente;
	private String vchrfc;
	private String vchnombre;
	private String vchrazonsocial;
	private String vchcalle;
	private String vchcolonia;
	private String chcodigop;
	private String vchmail;
	private String vchdescregion;
	private String vchdescestado;
	private String vchtel1;
	private String vchtel2;
	private String vchfax;
	private String vchcurp;
	private boolean bitdadodebaja;
	private float declimitecredito;
	private float deccreditutilizado;
	private short sidiadepago;
	private boolean bsuceptiblecredito;
	private short siplazodiaspago;
	private int iidestado;
	private int iidmunicipio;
        private String vchnumeroexterior;
        private String vchnumerointerior;
        private String vchlocalidad;



	public void create() throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		query.append("INSERT INTO CLIENTE (siidpais,siidviaembarque,iidnivel,iidcatcliente,vchrfc,vchnombre,vchrazonsocial,vchcalle,vchcolonia,chcodigop,vchmail,vchdescregion,vchdescestado,vchtel1,vchtel2,vchfax,vchcurp,bitdadodebaja,declimitecredito,deccreditutilizado,sidiadepago,bsuceptiblecredito,siplazodiaspago,iidestado,iidmunicipio, vchnumexterior, vchnuminterior, vchlocalidad) ");
		query.append(" VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ");
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setShort(1,siidpais);
		stmt.setShort(2,siidviaembarque);
		stmt.setInt(3,iidnivel);
		stmt.setInt(4,iidcatcliente);
		stmt.setString(5,vchrfc);
		stmt.setString(6,vchnombre);
		stmt.setString(7,vchrazonsocial);
		stmt.setString(8,vchcalle);
		stmt.setString(9,vchcolonia);
		stmt.setString(10,chcodigop);
		stmt.setString(11,vchmail);
		stmt.setString(12,vchdescregion);
		stmt.setString(13,vchdescestado);
		stmt.setString(14,vchtel1);
		stmt.setString(15,vchtel2);
		stmt.setString(16,vchfax);
		stmt.setString(17,vchcurp);
		stmt.setBoolean(18, isBitdadodebaja());
		stmt.setFloat(19,declimitecredito);
		stmt.setFloat(20,deccreditutilizado);
		stmt.setShort(21,sidiadepago);
		stmt.setBoolean(22, isBsuceptiblecredito());
		stmt.setShort(23,siplazodiaspago);
		stmt.setInt(24,iidestado);
		stmt.setInt(25,iidmunicipio);
                stmt.setString(26,vchnumeroexterior);
                stmt.setString(27,vchnumerointerior);
                stmt.setString(28,vchlocalidad);
		stmt.executeUpdate();
		stmt.close();;
	}
	public boolean load() throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		query.append("SELECT * ");
		query.append("FROM CLIENTE ");
		query.append("WHERE iidnocliente=? ");
		//query.append("ORDER BY vchnombre ");
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setInt(1,iidnocliente);
		rSet = stmt.executeQuery();


		if (rSet.next()) {
			this.siidpais = rSet.getShort("siidpais");
			this.siidviaembarque = rSet.getShort("siidviaembarque");
			this.iidnivel = rSet.getInt("iidnivel");
			this.iidcatcliente = rSet.getInt("iidcatcliente");
			this.vchrfc = rSet.getString("vchrfc");
			this.vchnombre = rSet.getString("vchnombre");
			this.vchrazonsocial = rSet.getString("vchrazonsocial");
			this.vchcalle = rSet.getString("vchcalle");
			this.vchcolonia = rSet.getString("vchcolonia");
			this.chcodigop = rSet.getString("chcodigop");
			this.vchmail = rSet.getString("vchmail");
			this.vchdescregion = rSet.getString("vchdescregion");
			this.vchdescestado = rSet.getString("vchdescestado");
			this.vchtel1 = rSet.getString("vchtel1");
			this.vchtel2 = rSet.getString("vchtel2");
			this.vchfax = rSet.getString("vchfax");
			this.vchcurp = rSet.getString("vchcurp");
			this.bitdadodebaja = rSet.getBoolean("bitdadodebaja");
			this.declimitecredito = rSet.getFloat("declimitecredito");
			this.deccreditutilizado = rSet.getFloat("deccreditutilizado");
			this.sidiadepago = rSet.getShort("sidiadepago");
			this.bsuceptiblecredito = rSet.getBoolean("bsuceptiblecredito");
			this.siplazodiaspago = rSet.getShort("siplazodiaspago");
			this.iidestado = rSet.getInt("iidestado");
			this.iidmunicipio = rSet.getInt("iidmunicipio");
                        this.vchnumeroexterior = rSet.getString("vchnumexterior");
                        
                        this.vchnumerointerior = rSet.getString("vchnuminterior");
                        this.vchlocalidad = rSet.getString("vchlocalidad");
		}
		else
			throw new Exception ("No se encontro el registro");
		rSet.close();
		stmt.close();
		return false;
	}
	public void remove() throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		query.append("DELETE FROM CLIENTE ");
		query.append("WHERE iidnocliente=? ");
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setInt(1,iidnocliente);
		stmt.executeUpdate();
		stmt.close();
	}
	public void store() throws SQLException {
		StringBuffer query = new StringBuffer();
		query.append("UPDATE CLIENTE SET siidpais=? , siidviaembarque=? , iidnivel=? , iidcatcliente=? , vchrfc=? , vchnombre=? , vchrazonsocial=? , vchcalle=? , vchcolonia=? , chcodigop=? , vchmail=? , vchdescregion=? , vchdescestado=? , vchtel1=? , vchtel2=? , vchfax=? , vchcurp=? , bitdadodebaja=? , declimitecredito=? , deccreditutilizado=? , sidiadepago=? , bsuceptiblecredito=? , siplazodiaspago=? , iidestado=? , iidmunicipio=?,  vchnumexterior=?, vchnuminterior=?, vchlocalidad=? ");
		query.append("WHERE iidnocliente=? ");
                logger.debug(query.toString().toLowerCase());                
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setShort(1,siidpais);
		stmt.setShort(2,siidviaembarque);
		stmt.setInt(3,iidnivel);
		stmt.setInt(4,iidcatcliente);
		stmt.setString(5,vchrfc);
		stmt.setString(6,vchnombre);
		stmt.setString(7,vchrazonsocial);
		stmt.setString(8,vchcalle);
		stmt.setString(9,vchcolonia);
		stmt.setString(10,chcodigop);
		stmt.setString(11,vchmail);
		stmt.setString(12,vchdescregion);
		stmt.setString(13,vchdescestado);
		stmt.setString(14,vchtel1);
		stmt.setString(15,vchtel2);
		stmt.setString(16,vchfax);
		stmt.setString(17,vchcurp);
		stmt.setBoolean(18, isBitdadodebaja());
		stmt.setFloat(19,declimitecredito);
		stmt.setFloat(20,deccreditutilizado);
		stmt.setShort(21,sidiadepago);
		stmt.setBoolean(22, isBsuceptiblecredito());
		stmt.setShort(23,siplazodiaspago);
		stmt.setInt(24,iidestado);
		stmt.setInt(25,iidmunicipio);
		stmt.setString(26,vchnumeroexterior);
                stmt.setString(27,vchnumerointerior);
                stmt.setString(28,vchlocalidad);
                stmt.setInt(29,iidnocliente);
                logger.debug("EL ID DE CLIENTE ES " + iidnocliente);
                
		stmt.executeUpdate();
		stmt.close();
	}
	public void setConnection(java.sql.Connection conn) {
		this.conn = conn;
	}
	public void setIidnocliente(int iidnocliente){
		this.iidnocliente=iidnocliente;
	}
	public int getIidnocliente(){
		return this.iidnocliente;
	}
	public void setSiidpais(short siidpais){
		this.siidpais=siidpais;
	}
	public short getSiidpais(){
		return this.siidpais;
	}
	public void setSiidviaembarque(short siidviaembarque){
		this.siidviaembarque=siidviaembarque;
	}
	public short getSiidviaembarque(){
		return this.siidviaembarque;
	}
	public void setIidnivel(int iidnivel){
		this.iidnivel=iidnivel;
	}
	public int getIidnivel(){
		return this.iidnivel;
	}
	public void setIidcatcliente(int iidcatcliente){
		this.iidcatcliente=iidcatcliente;
	}
	public int getIidcatcliente(){
		return this.iidcatcliente;
	}
	public void setVchrfc(String vchrfc){
		this.vchrfc=vchrfc;
	}
	public String getVchrfc(){
		return this.vchrfc;
	}
	public void setVchnombre(String vchnombre){
		this.vchnombre=vchnombre;
	}
	public String getVchnombre(){
		return this.vchnombre;
	}
	public void setVchrazonsocial(String vchrazonsocial){
		this.vchrazonsocial=vchrazonsocial;
	}
	public String getVchrazonsocial(){
		return this.vchrazonsocial;
	}
	public void setVchcalle(String vchcalle){
		this.vchcalle=vchcalle;
	}
	public String getVchcalle(){
		return this.vchcalle;
	}
	public void setVchcolonia(String vchcolonia){
		this.vchcolonia=vchcolonia;
	}
	public String getVchcolonia(){
		return this.vchcolonia;
	}
	public void setChcodigop(String chcodigop){
		this.chcodigop=chcodigop;
	}
	public String getChcodigop(){
		return this.chcodigop;
	}
	public void setVchmail(String vchmail){
		this.vchmail=vchmail;
	}
	public String getVchmail(){
		return this.vchmail;
	}
	public void setVchdescregion(String vchdescregion){
		this.vchdescregion=vchdescregion;
	}
	public String getVchdescregion(){
		return this.vchdescregion;
	}
	public void setVchdescestado(String vchdescestado){
		this.vchdescestado=vchdescestado;
	}
	public String getVchdescestado(){
		return this.vchdescestado;
	}
	public void setVchtel1(String vchtel1){
		this.vchtel1=vchtel1;
	}
	public String getVchtel1(){
		return this.vchtel1;
	}
	public void setVchtel2(String vchtel2){
		this.vchtel2=vchtel2;
	}
	public String getVchtel2(){
		return this.vchtel2;
	}
	public void setVchfax(String vchfax){
		this.vchfax=vchfax;
	}
	public String getVchfax(){
		return this.vchfax;
	}
	public void setVchcurp(String vchcurp){
		this.vchcurp=vchcurp;
	}
	public String getVchcurp(){
		return this.vchcurp;
	}
	public void setBitdadodebaja(boolean bitdadodebaja){
		this.bitdadodebaja=bitdadodebaja;
	}
	public boolean getBitdadodebaja(){
		return this.isBitdadodebaja();
	}
	public void setDeclimitecredito(float declimitecredito){
		this.declimitecredito=declimitecredito;
	}
	public float getDeclimitecredito(){
		return this.declimitecredito;
	}
	public void setDeccreditutilizado(float deccreditutilizado){
		this.deccreditutilizado=deccreditutilizado;
	}
	public float getDeccreditutilizado(){
		return this.deccreditutilizado;
	}
	public void setSidiadepago(short sidiadepago){
		this.sidiadepago=sidiadepago;
	}
	public short getSidiadepago(){
		return this.sidiadepago;
	}
	public void setBsuceptiblecredito(boolean bsuceptiblecredito){
		this.bsuceptiblecredito=bsuceptiblecredito;
	}
	public boolean getBsuceptiblecredito(){
		return this.isBsuceptiblecredito();
	}
	public void setSiplazodiaspago(short siplazodiaspago){
		this.siplazodiaspago=siplazodiaspago;
	}
	public short getSiplazodiaspago(){
		return this.siplazodiaspago;
	}
	public void setIidestado(int iidestado){
		this.iidestado=iidestado;
	}
	public int getIidestado(){
		return this.iidestado;
	}
	public void setIidmunicipio(int iidmunicipio){
		this.iidmunicipio=iidmunicipio;
	}
	public int getIidmunicipio(){
		return this.iidmunicipio;
	}

    /**
     * @return the bitdadodebaja
     */
    public boolean isBitdadodebaja() {
        return bitdadodebaja;
    }

    /**
     * @return the bsuceptiblecredito
     */
    public boolean isBsuceptiblecredito() {
        return bsuceptiblecredito;
    }

    /**
     * @return the vchnumeroexterior
     */
    public String getVchnumeroexterior() {
        
        return vchnumeroexterior;
    }

    /**
     * @param vchnumeroexterior the vchnumeroexterior to set
     */
    public void setVchnumeroexterior(String vchnumeroexterior) {
        this.vchnumeroexterior = vchnumeroexterior;
    }

    /**
     * @return the vchnumerointerior
     */
    public String getVchnumerointerior() {
        return vchnumerointerior;
    }

    /**
     * @param vchnumerointerior the vchnumerointerior to set
     */
    public void setVchnumerointerior(String vchnumerointerior) {
        this.vchnumerointerior = vchnumerointerior;
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
