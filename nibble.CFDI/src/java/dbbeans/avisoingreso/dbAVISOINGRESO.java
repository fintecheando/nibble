/** java bean entity **/
package dbbeans.avisoingreso;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class dbAVISOINGRESO implements org.nibble.main.dbInterfase {
	private Connection conn;
	private ResultSet rSet;
	private PreparedStatement stmt;


	private int iidavisoingreso;
	private int iidnocliente;
	private int iidusuario;
	private int iidmodulo;
	private int titipopago;
	private String tisfecha;
	private float decdebe;
	private float dechaber;
	private String vchconcepto;


	public void create() throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		query.append("INSERT INTO AVISOINGRESO (iidavisoingreso,iidnocliente,iidusuario,iidmodulo,titipopago,tisfecha,decdebe,dechaber,vchconcepto) ");
		query.append(" VALUES(?,?,?,?,?,?,?,?,?) ");
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setInt(1,iidavisoingreso);
		stmt.setInt(2,iidnocliente);
		stmt.setInt(3,iidusuario);
		stmt.setInt(4,iidmodulo);
		stmt.setInt(5,titipopago);
		stmt.setString(6,tisfecha);
		stmt.setFloat(7,decdebe);
		stmt.setFloat(8,dechaber);
		stmt.setString(9,vchconcepto);
		stmt.executeUpdate();
		stmt.close();;
	}
	public boolean load() throws Exception, java.sql.SQLException {
		boolean found = false;
		StringBuffer query = new StringBuffer();
		query.append("SELECT * ");
		query.append("FROM AVISOINGRESO ");
		query.append("WHERE iidavisoingreso=? ");
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setInt(1,iidavisoingreso);
		rSet = stmt.executeQuery();


		if (rSet.next()) {
			found = true;
			this.iidnocliente = rSet.getInt("iidnocliente");
			this.iidusuario = rSet.getInt("iidusuario");
			this.iidmodulo = rSet.getInt("iidmodulo");
			this.titipopago = rSet.getInt("titipopago");
			this.tisfecha = rSet.getString("tisfecha");
			this.decdebe = rSet.getFloat("decdebe");
			this.dechaber = rSet.getFloat("dechaber");
			this.vchconcepto = rSet.getString("vchconcepto");
		}
//		else throw new Exception ("No se encontro el registro");
		rSet.close();
		stmt.close();
		return found;
	}
	public void remove() throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		query.append("DELETE FROM AVISOINGRESO ");
		query.append("WHERE iidavisoingreso=? ");
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setInt(1,iidavisoingreso);
		stmt.executeUpdate();
		stmt.close();
	}
	public void store() throws SQLException {
		StringBuffer query = new StringBuffer();
		query.append("UPDATE AVISOINGRESO SET iidnocliente=? , iidusuario=? , iidmodulo=? , titipopago=? , tisfecha=? , decdebe=? , dechaber=? , vchconcepto=? ");
		query.append("WHERE iidavisoingreso=? ");
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setInt(1,iidnocliente);
		stmt.setInt(2,iidusuario);
		stmt.setInt(3,iidmodulo);
		stmt.setInt(4,titipopago);
		stmt.setString(5,tisfecha);
		stmt.setFloat(6,decdebe);
		stmt.setFloat(7,dechaber);
		stmt.setString(8,vchconcepto);
		stmt.setInt(9,iidavisoingreso);
		stmt.executeUpdate();
		stmt.close();
	}
	public void setConnection(java.sql.Connection conn) {
		this.conn = conn;
	}
	public void setIidavisoingreso(int iidavisoingreso){
		this.iidavisoingreso=iidavisoingreso;
	}
	public int getIidavisoingreso(){
		return this.iidavisoingreso;
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
	public void setIidmodulo(int iidmodulo){
		this.iidmodulo=iidmodulo;
	}
	public int getIidmodulo(){
		return this.iidmodulo;
	}
	public void setTitipopago(int titipopago){
		this.titipopago=titipopago;
	}
	public int getTitipopago(){
		return this.titipopago;
	}
	public void setTisfecha(String tisfecha){
		this.tisfecha=tisfecha;
	}
	public String getTisfecha(){
		return this.tisfecha;
	}
	public void setDecdebe(float decdebe){
		this.decdebe=decdebe;
	}
	public float getDecdebe(){
		return this.decdebe;
	}
	public void setDechaber(float dechaber){
		this.dechaber=dechaber;
	}
	public float getDechaber(){
		return this.dechaber;
	}
	public void setVchconcepto(String vchconcepto){
		this.vchconcepto=vchconcepto;
	}
	public String getVchconcepto(){
		return this.vchconcepto;
	}

	public boolean findIidavisoingreso() throws Exception, java.sql.SQLException {
		boolean found = false;
		StringBuffer query = new StringBuffer();
		query.append("SELECT * FROM AVISOINGRESO ");
		query.append("WHERE iidnocliente=? ");
		query.append("AND iidusuario=? ");
		query.append("AND iidmodulo=? ");
		query.append("AND tisfecha=? ");

		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setInt(1,iidnocliente);
		stmt.setInt(2,iidusuario);
		stmt.setInt(3,iidmodulo);
		stmt.setString(4,tisfecha);
		rSet = stmt.executeQuery();

		if (rSet.next()) {
			this.iidavisoingreso = rSet.getInt("iidavisoingreso");
			found = true;
		}

		rSet.close();
		stmt.close();
		return found;
	}
}
