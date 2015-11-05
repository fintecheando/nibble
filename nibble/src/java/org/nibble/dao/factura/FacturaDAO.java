/** java bean entity **/
package org.nibble.dao.factura;

import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class FacturaDAO implements org.nibble.main.dbInterfase {

	private Connection conn;
	private ResultSet rSet;
	private PreparedStatement stmt;

	private int iidfactura;
	private int iidnocliente;
	private int iidusuario;
	private String tisfechafactura;
	private float decsubtotal;
	private float decivatotal;
	private String vchletra;
	private int inumero;
	private float decdescglobal;
	private String tisfechavencimient;
	private boolean bitcredito;
	private float decmontodesc;        
        private String vchrfccliente;
        private String vchserie;
        private int ifolio;
        private int inumaprobacion;
        private String vchfechahora;
        private float decmontooperacion;
        private float decmontoimpuesto;
        private String vchestadocomprobante;
        private String vchefectocomprobante;
        private String vchpedimento;
        private String vchfechapedimento;
        private String vchaduana;
        private String vchformapago;
        private String vchmetodopago;
        private String vchmcuentapago;



	public void create() throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		query.append("INSERT INTO FACTURA (iidfactura,iidnocliente,iidusuario,tisfechafactura,"
                        + "decsubtotal,decivatotal,vchletra,inumero,decdescglobal,"
                        + "tisfechavencimient,bitcredito,decmontodesc, vchrfccliente, "
                        + "vchserie, ifolio, inumaprobacion, vchfechahora, decmontooperacion, "
                        + "decmontoimpuesto, vchestadocomprobante, vchefectocomprobante, vchpedimento, "
                        + "vchfechapedimento, vchaduana, vchformapago, vchmetodopago,vchcuentapago)");
		query.append(" VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ");
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setInt(1,iidfactura);
		stmt.setInt(2,iidnocliente);
		stmt.setInt(3,iidusuario);
		stmt.setString(4,tisfechafactura);
		stmt.setFloat(5,decsubtotal);
		stmt.setFloat(6,decivatotal);
		stmt.setString(7,vchletra);
		stmt.setInt(8,inumero);
		stmt.setFloat(9,decdescglobal);
		stmt.setString(10,tisfechavencimient);
		stmt.setBoolean(11, isBitcredito());
		stmt.setFloat(12,decmontodesc);
                stmt.setString(13,vchrfccliente);
                stmt.setString(14,vchserie);
                stmt.setInt(15,ifolio);
                stmt.setInt(16,inumaprobacion);
                stmt.setString(17,vchfechahora);
                stmt.setFloat(18,decmontooperacion);
                stmt.setFloat(19,decmontoimpuesto);
                stmt.setString(20,vchestadocomprobante);
                stmt.setString(21,vchefectocomprobante);
                stmt.setString(22,vchpedimento);
                stmt.setString(23,vchfechapedimento);
                stmt.setString(24,vchaduana);
                stmt.setString(25,vchformapago);
                stmt.setString(26,vchmetodopago);
                stmt.setString(27,vchmcuentapago);
		stmt.executeUpdate();
		stmt.close();
	}
	public boolean load() throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		query.append("SELECT * ");
		query.append("FROM FACTURA ");
		query.append("WHERE iidfactura=? ");
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setInt(1,iidfactura);
		rSet = stmt.executeQuery();


		if (rSet.next()) {
			this.iidnocliente = rSet.getInt("iidnocliente");
			this.iidusuario = rSet.getInt("iidusuario");
			this.tisfechafactura = rSet.getString("tisfechafactura");
			this.decsubtotal = rSet.getFloat("decsubtotal");
			this.decivatotal = rSet.getFloat("decivatotal");
			this.vchletra = rSet.getString("vchletra");
			this.inumero = rSet.getInt("inumero");
			this.decdescglobal = rSet.getFloat("decdescglobal");
			this.tisfechavencimient = rSet.getString("tisfechavencimient");
			this.bitcredito = rSet.getBoolean("bitcredito");
			this.decmontodesc = rSet.getFloat("decmontodesc");
		}
		else
			throw new Exception ("No se encontro el registro");
		rSet.close();
		stmt.close();
		return false;
	}
	public void remove() throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		query.append("DELETE FROM FACTURA ");
		query.append("WHERE iidfactura=? ");
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setInt(1,iidfactura);
		stmt.executeUpdate();
		stmt.close();
	}
	public void store() throws SQLException {
		StringBuffer query = new StringBuffer();
		query.append("UPDATE FACTURA SET iidnocliente=? , iidusuario=? , tisfechafactura=? , decsubtotal=? , decivatotal=? , vchletra=? , inumero=? , decdescglobal=? , tisfechavencimient=? , bitcredito=? , decmontodesc=? ");
		query.append("WHERE iidfactura=? ");
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setInt(1,iidnocliente);
		stmt.setInt(2,iidusuario);
		stmt.setString(3,tisfechafactura);
		stmt.setFloat(4,decsubtotal);
		stmt.setFloat(5,decivatotal);
		stmt.setString(6,vchletra);
		stmt.setInt(7,inumero);
		stmt.setFloat(8,decdescglobal);
		stmt.setString(9,tisfechavencimient);
		stmt.setBoolean(10, isBitcredito());
		stmt.setFloat(11,decmontodesc);
		stmt.setInt(12,iidfactura);
		stmt.executeUpdate();
		stmt.close();
	}
	public void setConnection(java.sql.Connection conn) {
		this.conn = conn;
	}
	public void setIidfactura(int iidfactura){
		this.iidfactura=iidfactura;
	}
	public int getIidfactura(){
		return this.iidfactura;
	}
	public void setIidnocliente(int iidnocliente){
		this.iidnocliente=iidnocliente;
	}
	public int getIidnocliente(){
		return this.iidnocliente;
	}
	public void setIidusuario(int iidusuario){
		this.iidusuario=iidusuario;
	}
	public int getIidusuario(){
		return this.iidusuario;
	}
	public void setTisfechafactura(String tisfechafactura){
		this.tisfechafactura=tisfechafactura;
	}
	public String getTisfechafactura(){
		return this.tisfechafactura;
	}
	public void setDecsubtotal(float decsubtotal){
		this.decsubtotal=decsubtotal;
	}
	public float getDecsubtotal(){
		return this.decsubtotal;
	}
	public void setDecivatotal(float decivatotal){
		this.decivatotal=decivatotal;
	}
	public float getDecivatotal(){
		return this.decivatotal;
	}
	public void setVchletra(String vchletra){
		this.vchletra=vchletra;
	}
	public String getVchletra(){
		return this.vchletra;
	}
	public void setInumero(int inumero){
		this.inumero=inumero;
	}
	public int getInumero(){
		return this.inumero;
	}
	public void setDecdescglobal(float decdescglobal){
		this.decdescglobal=decdescglobal;
	}
	public float getDecdescglobal(){
		return this.decdescglobal;
	}
	public void setTisfechavencimient(String tisfechavencimient){
		this.tisfechavencimient=tisfechavencimient;
	}
	public String getTisfechavencimient(){
		return this.tisfechavencimient;
	}
	public void setBitcredito(boolean bitcredito){
		this.bitcredito=bitcredito;
	}
	public boolean getBitcredito(){
		return this.isBitcredito();
	}
	public void setDecmontodesc(float decmontodesc){
		this.decmontodesc=decmontodesc;
	}
	public float getDecmontodesc(){
		return this.decmontodesc;
	}

    /**
     * @return the bitcredito
     */
    public boolean isBitcredito() {
        return bitcredito;
    }

    /**
     * @return the vchfechahora
     */
    public String getVchfechahora() {
        return vchfechahora;
    }

    /**
     * @param vchfechahora the vchfechahora to set
     */
    public void setVchfechahora(String vchfechahora) {
        this.vchfechahora = vchfechahora;
    }

    /**
     * @return the vchrfccliente
     */
    public String getVchrfccliente() {
        return vchrfccliente;
    }

    /**
     * @param vchrfccliente the vchrfccliente to set
     */
    public void setVchrfccliente(String vchrfccliente) {
        this.vchrfccliente = vchrfccliente;
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
     * @return the ifolio
     */
    public int getIfolio() {
        return ifolio;
    }

    /**
     * @param ifolio the ifolio to set
     */
    public void setIfolio(int ifolio) {
        this.ifolio = ifolio;
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

    /**
     * @return the decmontooperacion
     */
    public float getDecmontooperacion() {
        return decmontooperacion;
    }

    /**
     * @param decmontooperacion the decmontooperacion to set
     */
    public void setDecmontooperacion(float decmontooperacion) {
        this.decmontooperacion = decmontooperacion;
    }

    /**
     * @return the decmontoimpuesto
     */
    public float getDecmontoimpuesto() {
        return decmontoimpuesto;
    }

    /**
     * @param decmontoimpuesto the decmontoimpuesto to set
     */
    public void setDecmontoimpuesto(float decmontoimpuesto) {
        this.decmontoimpuesto = decmontoimpuesto;
    }

    /**
     * @return the vchestadocomprobante
     */
    public String getVchestadocomprobante() {
        return vchestadocomprobante;
    }

    /**
     * @param vchestadocomprobante the vchestadocomprobante to set
     */
    public void setVchestadocomprobante(String vchestadocomprobante) {
        this.vchestadocomprobante = vchestadocomprobante;
    }

    /**
     * @return the vchefectocomprobante
     */
    public String getVchefectocomprobante() {
        return vchefectocomprobante;
    }

    /**
     * @param vchefectocomprobante the vchefectocomprobante to set
     */
    public void setVchefectocomprobante(String vchefectocomprobante) {
        this.vchefectocomprobante = vchefectocomprobante;
    }

    /**
     * @return the vchpedimento
     */
    public String getVchpedimento() {
        return vchpedimento;
    }

    /**
     * @param vchpedimento the vchpedimento to set
     */
    public void setVchpedimento(String vchpedimento) {
        this.vchpedimento = vchpedimento;
    }

    /**
     * @return the vchfechapedimento
     */
    public String getVchfechapedimento() {
        return vchfechapedimento;
    }

    /**
     * @param vchfechapedimento the vchfechapedimento to set
     */
    public void setVchfechapedimento(String vchfechapedimento) {
        this.vchfechapedimento = vchfechapedimento;
    }

    /**
     * @return the vchaduana
     */
    public String getVchaduana() {
        return vchaduana;
    }

    /**
     * @param vchaduana the vchaduana to set
     */
    public void setVchaduana(String vchaduana) {
        this.vchaduana = vchaduana;
    }

    /**
     * @return the vchformapago
     */
    public String getVchformapago() {
        return vchformapago;
    }

    /**
     * @param vchformapago the vchformapago to set
     */
    public void setVchformapago(String vchformapago) {
        this.vchformapago = vchformapago;
    }

    /**
     * @return the vchmetodopago
     */
    public String getVchmetodopago() {
        return vchmetodopago;
    }

    /**
     * @param vchmetodopago the vchmetodopago to set
     */
    public void setVchmetodopago(String vchmetodopago) {
        this.vchmetodopago = vchmetodopago;
    }

    /**
     * @return the vchmcuentapgo
     */
    public String getVchmcuentapago() {
        return vchmcuentapago;
    }

    /**
     * @param vchmcuentapgo the vchmcuentapgo to set
     */
    public void setVchmcuentapago(String vchmcuentapago) {
        this.vchmcuentapago = vchmcuentapago;
    }
}
