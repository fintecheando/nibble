/** java bean entity **/
package dbbeans.aplicacion;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class dbAPLICACION implements org.nibble.main.dbInterfase {
	private Connection conn;
	private ResultSet rSet;
	private PreparedStatement stmt;


	private int iidaplicacion;
	private short sianio;
	private String vchmotor;
	private short sicombustible;
	private short sitraccion;
	private short sitransmision;
	private short sialimcmbst;
	private short siaspiracion;
	private int iidmodelo;
	private int iidplanta;
	private short sicilindros;
	private int iidsubmodelo;
	private short sidesplazamiento;
	private String vchhprpm;
	private String chnumpuertas;
	private boolean bitdirhidraulica;
	private boolean bitaireacond;
	private boolean bitdadodebaja;


	public void create() throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		query.append("INSERT INTO proyecto (iidaplicacion,sianio,vchmotor,sicombustible,sitraccion,sitransmision,sialimcmbst,siaspiracion,iidmodelo,iidplanta,sicilindros,iidsubmodelo,sidesplazamiento,vchhprpm,chnumpuertas,bitdirhidraulica,bitaireacond,bitdadodebaja) ");
		query.append(" VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ");
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setInt(1,iidaplicacion);
		stmt.setShort(2,sianio);
		stmt.setString(3,vchmotor);
		stmt.setShort(4,sicombustible);
		stmt.setShort(5,sitraccion);
		stmt.setShort(6,sitransmision);
		stmt.setShort(7,sialimcmbst);
		stmt.setShort(8,siaspiracion);
		stmt.setInt(9,iidmodelo);
		stmt.setInt(10,iidplanta);
		stmt.setShort(11,sicilindros);
		stmt.setInt(12,iidsubmodelo);
		stmt.setShort(13,sidesplazamiento);
		stmt.setString(14,vchhprpm);
		stmt.setString(15,chnumpuertas);
		stmt.setBoolean(16,bitdirhidraulica);
		stmt.setBoolean(17,bitaireacond);
		stmt.setBoolean(18,bitdadodebaja);
		stmt.executeUpdate();
		rSet.close();
		stmt.close();;
	}
	public boolean load() throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		query.append("SELECT * ");
		query.append("FROM APLICACION ");
		query.append("WHERE iidaplicacion=? ");
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setInt(1,iidaplicacion);
		rSet = stmt.executeQuery();


		if (rSet.next()) {
			this.sianio = rSet.getShort("sianio");
			this.vchmotor = rSet.getString("vchmotor");
			this.sicombustible = rSet.getShort("sicombustible");
			this.sitraccion = rSet.getShort("sitraccion");
			this.sitransmision = rSet.getShort("sitransmision");
			this.sialimcmbst = rSet.getShort("sialimcmbst");
			this.siaspiracion = rSet.getShort("siaspiracion");
			this.iidmodelo = rSet.getInt("iidmodelo");
			this.iidplanta = rSet.getInt("iidplanta");
			this.sicilindros = rSet.getShort("sicilindros");
			this.iidsubmodelo = rSet.getInt("iidsubmodelo");
			this.sidesplazamiento = rSet.getShort("sidesplazamiento");
			this.vchhprpm = rSet.getString("vchhprpm");
			this.chnumpuertas = rSet.getString("chnumpuertas");
			this.bitdirhidraulica = rSet.getBoolean("bitdirhidraulica");
			this.bitaireacond = rSet.getBoolean("bitaireacond");
			this.bitdadodebaja = rSet.getBoolean("bitdadodebaja");
		}
		rSet.close();
		stmt.close();
		return false;
	}
	public void remove() throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		query.append("DELETE FROM APLICACION ");
		query.append("WHERE iidaplicacion=? ");
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setInt(1,iidaplicacion);
		stmt.executeUpdate();
		stmt.close();
	}
	public void store() throws SQLException {
		StringBuffer query = new StringBuffer();
		query.append("UPDATE APLICACION SET sianio=? , vchmotor=? , sicombustible=? , sitraccion=? , sitransmision=? , sialimcmbst=? , siaspiracion=? , iidmodelo=? , iidplanta=? , sicilindros=? , iidsubmodelo=? , sidesplazamiento=? , vchhprpm=? , chnumpuertas=? , bitdirhidraulica=? , bitaireacond=? , bitdadodebaja=? ");
		query.append("WHERE iidaplicacion=? ");
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setShort(1,sianio);
		stmt.setString(2,vchmotor);
		stmt.setShort(3,sicombustible);
		stmt.setShort(4,sitraccion);
		stmt.setShort(5,sitransmision);
		stmt.setShort(6,sialimcmbst);
		stmt.setShort(7,siaspiracion);
		stmt.setInt(8,iidmodelo);
		stmt.setInt(9,iidplanta);
		stmt.setShort(10,sicilindros);
		stmt.setInt(11,iidsubmodelo);
		stmt.setShort(12,sidesplazamiento);
		stmt.setString(13,vchhprpm);
		stmt.setString(14,chnumpuertas);
		stmt.setBoolean(15,bitdirhidraulica);
		stmt.setBoolean(16,bitaireacond);
		stmt.setBoolean(17,bitdadodebaja);
		stmt.setInt(18,iidaplicacion);
		stmt.executeUpdate();
		stmt.close();
	}
	public void setConnection(java.sql.Connection conn) {
		this.conn = conn;
	}
	public void setIidaplicacion(int iidaplicacion){
		this.iidaplicacion=iidaplicacion;
	}
	public int getIidaplicacion(){
		return this.iidaplicacion;
	}
	public void setSianio(short sianio){
		this.sianio=sianio;
	}
	public short getSianio(){
		return this.sianio;
	}
	public void setVchmotor(String vchmotor){
		this.vchmotor=vchmotor;
	}
	public String getVchmotor(){
		return this.vchmotor;
	}
	public void setSicombustible(short sicombustible){
		this.sicombustible=sicombustible;
	}
	public short getSicombustible(){
		return this.sicombustible;
	}
	public void setSitraccion(short sitraccion){
		this.sitraccion=sitraccion;
	}
	public short getSitraccion(){
		return this.sitraccion;
	}
	public void setSitransmision(short sitransmision){
		this.sitransmision=sitransmision;
	}
	public short getSitransmision(){
		return this.sitransmision;
	}
	public void setSialimcmbst(short sialimcmbst){
		this.sialimcmbst=sialimcmbst;
	}
	public short getSialimcmbst(){
		return this.sialimcmbst;
	}
	public void setSiaspiracion(short siaspiracion){
		this.siaspiracion=siaspiracion;
	}
	public short getSiaspiracion(){
		return this.siaspiracion;
	}
	public void setIidmodelo(int iidmodelo){
		this.iidmodelo=iidmodelo;
	}
	public int getIidmodelo(){
		return this.iidmodelo;
	}
	public void setIidplanta(int iidplanta){
		this.iidplanta=iidplanta;
	}
	public int getIidplanta(){
		return this.iidplanta;
	}
	public void setSicilindros(short sicilindros){
		this.sicilindros=sicilindros;
	}
	public short getSicilindros(){
		return this.sicilindros;
	}
	public void setIidsubmodelo(int iidsubmodelo){
		this.iidsubmodelo=iidsubmodelo;
	}
	public int getIidsubmodelo(){
		return this.iidsubmodelo;
	}
	public void setSidesplazamiento(short sidesplazamiento){
		this.sidesplazamiento=sidesplazamiento;
	}
	public short getSidesplazamiento(){
		return this.sidesplazamiento;
	}
	public void setVchhprpm(String vchhprpm){
		this.vchhprpm=vchhprpm;
	}
	public String getVchhprpm(){
		return this.vchhprpm;
	}
	public void setChnumpuertas(String chnumpuertas){
		this.chnumpuertas=chnumpuertas;
	}
	public String getChnumpuertas(){
		return this.chnumpuertas;
	}
	public void setBitdirhidraulica(boolean bitdirhidraulica){
		this.bitdirhidraulica=bitdirhidraulica;
	}
	public boolean getBitdirhidraulica(){
		return this.bitdirhidraulica;
	}
	public void setBitaireacond(boolean bitaireacond){
		this.bitaireacond=bitaireacond;
	}
	public boolean getBitaireacond(){
		return this.bitaireacond;
	}
	public void setBitdadodebaja(boolean bitdadodebaja){
		this.bitdadodebaja=bitdadodebaja;
	}
	public boolean getBitdadodebaja(){
		return this.bitdadodebaja;
	}
}
