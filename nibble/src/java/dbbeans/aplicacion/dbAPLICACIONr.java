package dbbeans.aplicacion;

import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;


public class dbAPLICACIONr {
	private Connection conn;
	private ResultSet rSet;
	private PreparedStatement stmt;


	private int iidaplicacion;
	private short sianio;
	private String vchmotor;
	private String sicombustible;
	private String sitraccion;
	private String sitransmision;
	private String sialimcmbst;
	private String siaspiracion;
	private String iidmodelo;
	private String iidplanta;
	private String sicilindros;
	private String iidsubmodelo;
	private String sidesplazamiento;
	private String vchhprpm;
	private String chnumpuertas;
	private boolean bitdirhidraulica;
	private boolean bitaireacond;
	private boolean bitdadodebaja;


	public boolean getBitaireacond(){
		return this.bitaireacond;
	}
	public boolean getBitdadodebaja(){
		return this.bitdadodebaja;
	}
	public boolean getBitdirhidraulica(){
		return this.bitdirhidraulica;
	}
	public String getChnumpuertas(){
		return this.chnumpuertas;
	}
	public int getIidaplicacion(){
		return this.iidaplicacion;
	}
	public String getIidmodelo(){
		return this.iidmodelo;
	}
	public String getIidplanta(){
		return this.iidplanta;
	}
	public String getIidsubmodelo(){
		return this.iidsubmodelo;
	}
	public String getSialimcmbst(){
		return this.sialimcmbst;
	}
	public short getSianio(){
		return this.sianio;
	}
	public String getSiaspiracion(){
		return this.siaspiracion;
	}
	public String getSicilindros(){
		return this.sicilindros;
	}
	public String getSicombustible(){
		return this.sicombustible;
	}
	public String getSidesplazamiento(){
		return this.sidesplazamiento;
	}
	public String getSitraccion(){
		return this.sitraccion;
	}
	public String getSitransmision(){
		return this.sitransmision;
	}
	public String getVchhprpm(){
		return this.vchhprpm;
	}
	public String getVchmotor(){
		return this.vchmotor;
	}
	public void setBitaireacond(boolean bitaireacond){
		this.bitaireacond=bitaireacond;
	}
	public void setBitdadodebaja(boolean bitdadodebaja){
		this.bitdadodebaja=bitdadodebaja;
	}
	public void setBitdirhidraulica(boolean bitdirhidraulica){
		this.bitdirhidraulica=bitdirhidraulica;
	}
	public void setChnumpuertas(String chnumpuertas){
		this.chnumpuertas=chnumpuertas;
	}
	public void setConnection(java.sql.Connection conn) {
		this.conn = conn;
	}
	public void setIidaplicacion(int iidaplicacion){
		this.iidaplicacion=iidaplicacion;
	}
	public void setIidmodelo(String iidmodelo){
		this.iidmodelo=iidmodelo;
	}
	public void setIidplanta(String iidplanta){
		this.iidplanta=iidplanta;
	}
	public void setIidsubmodelo(String iidsubmodelo){
		this.iidsubmodelo=iidsubmodelo;
	}
	public void setSialimcmbst(String sialimcmbst){
		this.sialimcmbst=sialimcmbst;
	}
	public void setSianio(short sianio){
		this.sianio=sianio;
	}
	public void setSiaspiracion(String siaspiracion){
		this.siaspiracion=siaspiracion;
	}
	public void setSicilindros(String sicilindros){
		this.sicilindros=sicilindros;
	}
	public void setSicombustible(String sicombustible){
		this.sicombustible=sicombustible;
	}
	public void setSidesplazamiento(String sidesplazamiento){
		this.sidesplazamiento=sidesplazamiento;
	}
	public void setSitraccion(String sitraccion){
		this.sitraccion=sitraccion;
	}
	public void setSitransmision(String sitransmision){
		this.sitransmision=sitransmision;
	}
	public void setVchhprpm(String vchhprpm){
		this.vchhprpm=vchhprpm;
	}
	public void setVchmotor(String vchmotor){
		this.vchmotor=vchmotor;
	}
	public boolean load() throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		query.append("select a.iidaplicacion,a.sianio,a.vchmotor,"+
        	   "a.vchhprpm,b.vchdescripcion,"+
        	   "c.vchdescripcion,d.vchdescripcion,e.vchdescripcion,"+
        	   "f.vchdescripcion,g.vchdescripcion,h.vchdescripcion,"+
        	   "i.vchdescripcion,j.vchdescripcion "+
        	   "from aplicacion as a,"+
           	   "planta as b,"+
           	   "modelo as c,"+
        	   "submodelo as d,"+
        	   "cilindros as e,"+
        	   "combustible as f,"+
        	   "aspiracion as g,"+
        	   "alimentacion_combustible as h,"+
        	   "traccion as i,"+
        	   "transmision as j ");
		query.append("WHERE a.iidplanta=b.iidplanta AND a.iidmodelo=c.iidmodelo AND "+
		"a.iidsubmodelo=d.iidsubmodelo AND a.sicilindros=e.sicilindros AND "+
		"a.sicombustible=f.sicombustible AND a.siaspiracion=g.siaspiracion AND "+
		"a.sialimcmbst=h.sialimcmbst AND a.sitraccion=i.sitraccion AND "+ 
		"a.sitransmision=j.sitransmision "); 
		query.append("AND iidaplicacion=? ");

		

		stmt = conn.prepareStatement(query.toString().toLowerCase());
		
		stmt.setInt(1,iidaplicacion);
		
		rSet = stmt.executeQuery();


		if (rSet.next()) {
			setIidaplicacion(rSet.getInt(1));
			setSianio(rSet.getShort(2));
			setVchmotor(rSet.getString(3));
			setVchhprpm(rSet.getString(4));
			setIidplanta(rSet.getString(5));
			setIidmodelo(rSet.getString(6));
			setIidsubmodelo(rSet.getString(7));
			setSicilindros(rSet.getString(8));			
			setSicombustible(rSet.getString(9));
			setSiaspiracion(rSet.getString(10));
			setSialimcmbst(rSet.getString(11));			
			setSitraccion(rSet.getString(12));
			setSitransmision(rSet.getString(13));
		}
		else
			throw new Exception ("No se encontro el registro");


		
			
		rSet.close();
		stmt.close();
		return false;
	}
}
