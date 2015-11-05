/** java bean entity **/
package dbbeans.marca;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class dbMARCA implements org.nibble.main.dbInterfase {
	private Connection conn;
	private ResultSet rSet;
	private PreparedStatement stmt;


	private int iidmarca;
	private int iidlinea;
	private String vchnombre;
	private boolean bitdadodebaja;
	private String imgmarcalogo;


	public void create() throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		query.append("INSERT INTO proyecto (iidmarca,iidlinea,vchnombre,bitdadodebaja,imgmarcalogo) ");
		query.append(" VALUES(?,?,?,?,?) ");
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setInt(1,iidmarca);
		stmt.setInt(2,iidlinea);
		stmt.setString(3,vchnombre);
		stmt.setBoolean(4,bitdadodebaja);
		stmt.setString(5,imgmarcalogo);
		stmt.executeUpdate();
		rSet.close();
		stmt.close();;
	}
	public boolean load() throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		query.append("SELECT * ");
		query.append("FROM MARCA ");
		query.append("WHERE iidmarca=? ");
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setInt(1,iidmarca);
		rSet = stmt.executeQuery();


		if (rSet.next()) {
			this.iidlinea = rSet.getInt("iidlinea");
			this.vchnombre = rSet.getString("vchnombre");
			this.bitdadodebaja = rSet.getBoolean("bitdadodebaja");
			this.imgmarcalogo = rSet.getString("imgmarcalogo");
		}
		rSet.close();
		stmt.close();
		return false;
	}
	public void remove() throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		query.append("DELETE FROM MARCA ");
		query.append("WHERE iidmarca=? ");
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setInt(1,iidmarca);
		stmt.executeUpdate();
		stmt.close();
	}
	public void store() throws SQLException {
		StringBuffer query = new StringBuffer();
		query.append("UPDATE MARCA SET iidlinea=? , vchnombre=? , bitdadodebaja=? , imgmarcalogo=? ");
		query.append("WHERE iidmarca=? ");
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setInt(1,iidlinea);
		stmt.setString(2,vchnombre);
		stmt.setBoolean(3,bitdadodebaja);
		stmt.setString(4,imgmarcalogo);
		stmt.setInt(5,iidmarca);
		stmt.executeUpdate();
		stmt.close();
	}
	public void setConnection(java.sql.Connection conn) {
		this.conn = conn;
	}
	public void setIidmarca(int iidmarca){
		this.iidmarca=iidmarca;
	}
	public int getIidmarca(){
		return this.iidmarca;
	}
	public void setIidlinea(int iidlinea){
		this.iidlinea=iidlinea;
	}
	public int getIidlinea(){
		return this.iidlinea;
	}
	public void setVchnombre(String vchnombre){
		this.vchnombre=vchnombre;
	}
	public String getVchnombre(){
		return this.vchnombre;
	}
	public void setBitdadodebaja(boolean bitdadodebaja){
		this.bitdadodebaja=bitdadodebaja;
	}
	public boolean getBitdadodebaja(){
		return this.bitdadodebaja;
	}
	public void setImgmarcalogo(String imgmarcalogo){
		this.imgmarcalogo=imgmarcalogo;
	}
	public String getImgmarcalogo(){
		return this.imgmarcalogo;
	}
}
