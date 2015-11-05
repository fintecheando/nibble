/** java bean entity **/
package dbbeans.pago;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class dbPAGO implements org.nibble.main.dbInterfase {
	private Connection conn;
	private ResultSet rSet;
	private PreparedStatement stmt;


	private int iidpago;
	private int iidnocliente;
	private int iidusuario;
	private int iidmodulo;
	private String tisfechapago;
	private int titipopago;
	private float decimporte;
	private boolean bitabono;
	private String vchletrafolio;
	private int inumerofolio;
	private String vchconcepto;
	private boolean bitavisoing;


	public void create() throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		query.append("INSERT INTO PAGO (iidnocliente,iidusuario,iidmodulo,tisfechapago,titipopago,decimporte,bitabono,vchletrafolio,inumerofolio,vchconcepto,bitavisoing) ");
		query.append(" VALUES(?,?,?,?,?,?,?,?,?,?,?) ");
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setInt(1,iidnocliente);
		stmt.setInt(2,iidusuario);
		stmt.setInt(3,iidmodulo);
		stmt.setString(4,tisfechapago);
		stmt.setInt(5,titipopago);
		stmt.setFloat(6,decimporte);
		stmt.setBoolean(7,bitabono);
		stmt.setString(8,vchletrafolio);
		stmt.setInt(9,inumerofolio);
		stmt.setString(10,vchconcepto);
		stmt.setBoolean(11,bitavisoing);
		stmt.executeUpdate();
		stmt.close();;
	}
	public boolean load() throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		query.append("SELECT * ");
		query.append("FROM PAGO ");
		query.append("WHERE iidpago=? ");
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setInt(1,iidpago);
		rSet = stmt.executeQuery();


		if (rSet.next()) {
			this.iidnocliente = rSet.getInt("iidnocliente");
			this.iidusuario = rSet.getInt("iidusuario");
			this.iidmodulo = rSet.getInt("iidmodulo");
			this.tisfechapago = rSet.getString("tisfechapago");
			this.titipopago = rSet.getInt("titipopago");
			this.decimporte = rSet.getFloat("decimporte");
			this.bitabono = rSet.getBoolean("bitabono");
			this.vchletrafolio = rSet.getString("vchletrafolio");
			this.inumerofolio = rSet.getInt("inumerofolio");
			this.vchconcepto = rSet.getString("vchconcepto");
			this.bitavisoing = rSet.getBoolean("bitavisoing");
		}
		else
			throw new Exception ("No se encontro el registro");
		rSet.close();
		stmt.close();
		return false;
	}
	public void remove() throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		query.append("DELETE FROM PAGO ");
		query.append("WHERE iidpago=? ");
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setInt(1,iidpago);
		stmt.executeUpdate();
		stmt.close();
	}
	public void store() throws SQLException {
		StringBuffer query = new StringBuffer();
		query.append("UPDATE PAGO SET iidnocliente=? , iidusuario=? , iidmodulo=? , tisfechapago=? , titipopago=? , decimporte=? , bitabono=? , vchletrafolio=? , inumerofolio=? , vchconcepto=? , bitavisoing=? ");
		query.append("WHERE iidpago=? ");
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setInt(1,iidnocliente);
		stmt.setInt(2,iidusuario);
		stmt.setInt(3,iidmodulo);
		stmt.setString(4,tisfechapago);
		stmt.setInt(5,titipopago);
		stmt.setFloat(6,decimporte);
		stmt.setBoolean(7,bitabono);
		stmt.setString(8,vchletrafolio);
		stmt.setInt(9,inumerofolio);
		stmt.setString(10,vchconcepto);
		stmt.setBoolean(11,bitavisoing);
		stmt.setInt(12,iidpago);
		stmt.executeUpdate();
		stmt.close();
	}
	public void setConnection(java.sql.Connection conn) {
		this.conn = conn;
	}
	public void setIidpago(int iidpago){
		this.iidpago=iidpago;
	}
	public int getIidpago(){
		return this.iidpago;
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
	public void setTisfechapago(String tisfechapago){
		this.tisfechapago=tisfechapago;
	}
	public String getTisfechapago(){
		return this.tisfechapago;
	}
	public void setTitipopago(int titipopago){
		this.titipopago=titipopago;
	}
	public int getTitipopago(){
		return this.titipopago;
	}
	public void setDecimporte(float decimporte){
		this.decimporte=decimporte;
	}
	public float getDecimporte(){
		return this.decimporte;
	}
	public void setBitabono(boolean bitabono){
		this.bitabono=bitabono;
	}
	public boolean getBitabono(){
		return this.bitabono;
	}
	public void setVchletrafolio(String vchletrafolio){
		this.vchletrafolio=vchletrafolio;
	}
	public String getVchletrafolio(){
		return this.vchletrafolio;
	}
	public void setInumerofolio(int inumerofolio){
		this.inumerofolio=inumerofolio;
	}
	public int getInumerofolio(){
		return this.inumerofolio;
	}
	public void setVchconcepto(String vchconcepto){
		this.vchconcepto=vchconcepto;
	}
	public String getVchconcepto(){
		return this.vchconcepto;
	}
	public void setBitavisoing(boolean bitavisoing){
		this.bitavisoing=bitavisoing;
	}
	public boolean getBitavisoing(){
		return this.bitavisoing;
	}

	public boolean findIidpago() throws Exception, java.sql.SQLException {
		boolean found = false;
		StringBuffer query = new StringBuffer();
		query.append("SELECT * FROM PAGO ");
		query.append("WHERE iidnocliente=? ");
		query.append("AND iidusuario=? ");
		query.append("AND iidmodulo=? ");
		query.append("AND tisfechapago=? ");
		query.append("AND titipopago=? ");
		query.append("AND decimporte=? ");

		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setInt(1,iidnocliente);
		stmt.setInt(2,iidusuario);
		stmt.setInt(3,iidmodulo);
		stmt.setString(4,tisfechapago);
		stmt.setInt(5,titipopago);
		stmt.setFloat(6,decimporte);
		rSet = stmt.executeQuery();

		if (rSet.next()) {
			this.iidpago = rSet.getInt("iidpago");
			found = true;
		}

		rSet.close();
		stmt.close();
		return found;
	}
}
