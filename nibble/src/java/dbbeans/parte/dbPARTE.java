package dbbeans.parte;

import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Blob;


public class dbPARTE implements org.nibble.main.dbInterfase {
	private Connection conn;
	private ResultSet rSet;
	private PreparedStatement stmt;


	private long liidparte;
	private int iidunidad;
	private int iidimagen;
	private int iidcategoria;
	private String vchnumparte;
	private String vchnumparteequival;
	private float decpreciob100;
	private float decprecio;
	private int imultiploempaque;
	private boolean bitventaunitaria;
	private boolean bitdadodebaja;
	private int iminembarque;
	private float flalto;
	private float fllargo;
	private float flancho;
	private float flpeso;
	private String vchdescripciones;
	private String vchobservaciones;
	private int iidgrupo;
	private int iidlinea;
	private int iidmarca;
	private String fichatecnica;
	public void create() throws Exception, java.sql.SQLException {
		long maximo=0;
		StringBuffer query = new StringBuffer();
		query.append("SELECT MAX(LIIDPARTE) ");
		query.append("FROM PARTE ");
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		rSet = stmt.executeQuery();
		if (rSet.next()) {
			maximo=rSet.getLong(1);
		}
		maximo++;
		liidparte = maximo;
		query.setLength(0);
		query.append("INSERT INTO parte (liidparte,iidunidad,iidimagen,iidcategoria,vchnumparte,vchnumparteequival,decpreciob100,decprecio,imultiploempaque,bitventaunitaria,bitdadodebaja,iminembarque,flalto,fllargo,flancho,flpeso,vchdescripciones,vchobservaciones,iidgrupo,iidlinea,iidmarca,fichatecnica) ");
		query.append(" VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ");
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setLong(1,maximo);
		stmt.setInt(2,iidunidad);
		stmt.setInt(3,iidimagen);
		stmt.setInt(4,iidcategoria);
		stmt.setString(5,vchnumparte);
		stmt.setString(6,vchnumparteequival);
		stmt.setFloat(7,decpreciob100);
		stmt.setFloat(8,decprecio);
		stmt.setInt(9,imultiploempaque);
		stmt.setBoolean(10,bitventaunitaria);
		stmt.setBoolean(11,bitdadodebaja);
		stmt.setInt(12,iminembarque);
		stmt.setFloat(13,flalto);
		stmt.setFloat(14,fllargo);
		stmt.setFloat(15,flancho);
		stmt.setFloat(16,flpeso);
		stmt.setString(17,vchdescripciones);
		stmt.setString(18,vchobservaciones);
		stmt.setInt(19,iidgrupo);
		stmt.setInt(20,iidlinea);
		stmt.setInt(21,iidmarca);
		stmt.setString(22,fichatecnica);
		stmt.executeUpdate();
		rSet.close();
		stmt.close();;
	}
	public boolean getBitdadodebaja(){
		return this.bitdadodebaja;
	}
	public boolean getBitventaunitaria(){
		return this.bitventaunitaria;
	}
	public float getDecprecio(){
		return this.decprecio;
	}
	public float getDecpreciob100(){
		return this.decpreciob100;
	}
/**
 * Insert the method's description here.
 * Creation date: (10/18/2002 3:31:07 PM)
 * @return java.lang.String
 */
public java.lang.String getFichatecnica() {
	return fichatecnica;
}
	public float getFlalto(){
		return this.flalto;
	}
	public float getFlancho(){
		return this.flancho;
	}
	public float getFllargo(){
		return this.fllargo;
	}
	public float getFlpeso(){
		return this.flpeso;
	}
	public int getIidcategoria(){
		return this.iidcategoria;
	}
	public int getIidgrupo(){
		return this.iidgrupo;
	}
	public int getIidimagen(){
		return this.iidimagen;
	}
	public int getIidlinea(){
		return this.iidlinea;
	}
	public int getIidmarca(){
		return this.iidmarca;
	}
	public int getIidunidad(){
		return this.iidunidad;
	}
/**
 * Insert the method's description here.
 * Creation date: (12/6/2002 12:51:56 PM)
 */
public Blob getImagen(long liidparte)
    throws Exception, java.sql.SQLException {
    Blob blob=null;
    StringBuffer query = new StringBuffer();

	query.append("SELECT imagen FROM PARTE WHERE liidparte = ?");    
	stmt = conn.prepareStatement(query.toString().toLowerCase());

	stmt.setLong(1,liidparte);

    ResultSet rs = stmt.executeQuery();
    if (rs.next()) {
        blob = rs.getBlob(1);
    }
    return blob;
}
	public int getIminembarque(){
		return this.iminembarque;
	}
	public int getImultiploempaque(){
		return this.imultiploempaque;
	}
	public long getLiidparte(){
		return this.liidparte;
	}
	public String getVchdescripciones(){
		return this.vchdescripciones;
	}
	public String getVchnumparte(){
		return this.vchnumparte;
	}
	public String getVchnumparteequival(){
		return this.vchnumparteequival;
	}
	public String getVchobservaciones(){
		return this.vchobservaciones;
	}
	public boolean load() throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		query.append("SELECT * ");
		query.append("FROM PARTE ");
		query.append("WHERE liidparte=? ");
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setLong(1,liidparte);
		rSet = stmt.executeQuery();


		if (rSet.next()) {
			this.iidunidad = rSet.getInt("iidunidad");
			this.iidimagen = rSet.getInt("iidimagen");
			this.iidcategoria = rSet.getInt("iidcategoria");
			this.vchnumparte = rSet.getString("vchnumparte");
			this.vchnumparteequival = rSet.getString("vchnumparteequival");
			this.decpreciob100 = rSet.getFloat("decpreciob100");
			this.decprecio = rSet.getFloat("decprecio");
			this.imultiploempaque = rSet.getInt("imultiploempaque");
			this.bitventaunitaria = rSet.getBoolean("bitventaunitaria");
			this.bitdadodebaja = rSet.getBoolean("bitdadodebaja");
			this.iminembarque = rSet.getInt("iminembarque");
			this.flalto = rSet.getFloat("flalto");
			this.fllargo = rSet.getFloat("fllargo");
			this.flancho = rSet.getFloat("flancho");
			this.flpeso = rSet.getFloat("flpeso");
			this.vchdescripciones = rSet.getString("vchdescripciones");
			this.vchobservaciones = rSet.getString("vchobservaciones");
			this.iidgrupo = rSet.getInt("iidgrupo");
			this.iidlinea = rSet.getInt("iidlinea");
			this.iidmarca = rSet.getInt("iidmarca");
			this.fichatecnica = rSet.getString("fichatecnica");
		}
		rSet.close();
		stmt.close();
		return false;
	}
/**
 * Insert the method's description here.
 * Creation date: (12/6/2002 12:51:56 PM)
 */
public void newMethod() {}
	public void remove() throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		query.append("DELETE FROM PARTE ");
		query.append("WHERE liidparte=? ");
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setLong(1,liidparte);
		stmt.executeUpdate();
		stmt.close();
	}
	public void setBitdadodebaja(boolean bitdadodebaja){
		this.bitdadodebaja=bitdadodebaja;
	}
	public void setBitventaunitaria(boolean bitventaunitaria){
		this.bitventaunitaria=bitventaunitaria;
	}
	public void setConnection(java.sql.Connection conn) {
		this.conn = conn;
	}
	public void setDecprecio(float decprecio){
		this.decprecio=decprecio;
	}
	public void setDecpreciob100(float decpreciob100){
		this.decpreciob100=decpreciob100;
	}
/**
 * Insert the method's description here.
 * Creation date: (10/18/2002 3:31:07 PM)
 * @param newFichatecnica java.lang.String
 */
public void setFichatecnica(java.lang.String newFichatecnica) {
	fichatecnica = newFichatecnica;
}
	public void setFlalto(float flalto){
		this.flalto=flalto;
	}
	public void setFlancho(float flancho){
		this.flancho=flancho;
	}
	public void setFllargo(float fllargo){
		this.fllargo=fllargo;
	}
	public void setFlpeso(float flpeso){
		this.flpeso=flpeso;
	}
	public void setIidcategoria(int iidcategoria){
		this.iidcategoria=iidcategoria;
	}
	public void setIidgrupo(int iidgrupo){
		this.iidgrupo=iidgrupo;
	}
	public void setIidimagen(int iidimagen){
		this.iidimagen=iidimagen;
	}
	public void setIidlinea(int iidlinea){
		this.iidlinea=iidlinea;
	}
	public void setIidmarca(int iidmarca){
		this.iidmarca=iidmarca;
	}
	public void setIidunidad(int iidunidad){
		this.iidunidad=iidunidad;
	}
	public void setIminembarque(int iminembarque){
		this.iminembarque=iminembarque;
	}
	public void setImultiploempaque(int imultiploempaque){
		this.imultiploempaque=imultiploempaque;
	}
	public void setLiidparte(long liidparte){
		this.liidparte=liidparte;
	}
	public void setVchdescripciones(String vchdescripciones){
		this.vchdescripciones=vchdescripciones;
	}
	public void setVchnumparte(String vchnumparte){
		this.vchnumparte=vchnumparte;
	}
	public void setVchnumparteequival(String vchnumparteequival){
		this.vchnumparteequival=vchnumparteequival;
	}
	public void setVchobservaciones(String vchobservaciones){
		this.vchobservaciones=vchobservaciones;
	}
	public void store() throws SQLException {
		StringBuffer query = new StringBuffer();
		query.append("UPDATE PARTE SET iidunidad=? , iidimagen=? , iidcategoria=? , vchnumparte=? , vchnumparteequival=? , decpreciob100=? , decprecio=? , imultiploempaque=? , bitventaunitaria=? , bitdadodebaja=? , iminembarque=? , flalto=? , fllargo=? , flancho=? , flpeso=? , vchdescripciones=? , vchobservaciones=? , iidgrupo=? , iidlinea=? , iidmarca=? , fichatecnica=? ");
		query.append("WHERE liidparte=? ");
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setInt(1,iidunidad);
		stmt.setInt(2,iidimagen);
		stmt.setInt(3,iidcategoria);
		stmt.setString(4,vchnumparte);
		stmt.setString(5,vchnumparteequival);
		stmt.setFloat(6,decpreciob100);
		stmt.setFloat(7,decprecio);
		stmt.setInt(8,imultiploempaque);
		stmt.setBoolean(9,bitventaunitaria);
		stmt.setBoolean(10,bitdadodebaja);
		stmt.setInt(11,iminembarque);
		stmt.setFloat(12,flalto);
		stmt.setFloat(13,fllargo);
		stmt.setFloat(14,flancho);
		stmt.setFloat(15,flpeso);
		stmt.setString(16,vchdescripciones);
		stmt.setString(17,vchobservaciones);
		stmt.setInt(18,iidgrupo);
		stmt.setInt(19,iidlinea);
		stmt.setInt(20,iidmarca);
		stmt.setString(21,fichatecnica);
		stmt.setLong(22,liidparte);
		stmt.executeUpdate();
		stmt.close();
	}
/**
 * Insert the method's description here.
 * Creation date: (12/6/2002 12:51:56 PM)
 */
public void setImagen(long liidparte, byte[] imagen)
    throws Exception, java.sql.SQLException {
    StringBuffer query = new StringBuffer();

	query.append("UPDATE PARTE set IMAGEN=? WHERE liidparte = ?");    
	stmt = conn.prepareStatement(query.toString().toLowerCase());        
       
	stmt.setBytes(1, imagen);
	stmt.setLong(2,liidparte);

	stmt.executeUpdate();
}	
}
