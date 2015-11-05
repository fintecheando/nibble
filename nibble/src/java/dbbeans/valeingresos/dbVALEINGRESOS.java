/** java bean entity **/
package dbbeans.valeingresos;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class dbVALEINGRESOS implements org.nibble.main.dbInterfase {
	private Connection conn;
	private ResultSet rSet;
	private PreparedStatement stmt;


	private int iidvaleingresos;
	private int iidvale;
	private int iidavisoingreso;
	private float decimporte;


	public void create() throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		query.append("INSERT INTO VALEINGRESOS (iidvaleingresos,iidvale,iidavisoingreso,decimporte) ");
		query.append(" VALUES(?,?,?,?) ");
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setInt(1,iidvaleingresos);
		stmt.setInt(2,iidvale);
		stmt.setInt(3,iidavisoingreso);
		stmt.setFloat(4,decimporte);
		stmt.executeUpdate();
		stmt.close();;
	}
	public boolean load() throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		query.append("SELECT * ");
		query.append("FROM VALEINGRESOS ");
		query.append("WHERE iidvaleingresos=? ");
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setInt(1,iidvaleingresos);
		rSet = stmt.executeQuery();


		if (rSet.next()) {
			this.iidvale = rSet.getInt("iidvale");
			this.iidavisoingreso = rSet.getInt("iidavisoingreso");
			this.decimporte = rSet.getFloat("decimporte");
		}
		else
			throw new Exception ("No se encontro el registro");
		rSet.close();
		stmt.close();
		return false;
	}
	public void remove() throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		query.append("DELETE FROM VALEINGRESOS ");
		query.append("WHERE iidvaleingresos=? ");
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setInt(1,iidvaleingresos);
		stmt.executeUpdate();
		stmt.close();
	}
	public void store() throws SQLException {
		StringBuffer query = new StringBuffer();
		query.append("UPDATE VALEINGRESOS SET iidvale=? , iidavisoingreso=? , decimporte=? ");
		query.append("WHERE iidvaleingresos=? ");
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setInt(1,iidvale);
		stmt.setInt(2,iidavisoingreso);
		stmt.setFloat(3,decimporte);
		stmt.setInt(4,iidvaleingresos);
		stmt.executeUpdate();
		stmt.close();
	}
	public void setConnection(java.sql.Connection conn) {
		this.conn = conn;
	}
	public void setIidvaleingresos(int iidvaleingresos){
		this.iidvaleingresos=iidvaleingresos;
	}
	public int getIidvaleingresos(){
		return this.iidvaleingresos;
	}
	public void setIidvale(int iidvale){
		this.iidvale=iidvale;
	}
	public int getIidvale(){
		return this.iidvale;
	}
	public void setIidavisoingreso(int iidavisoingreso){
		this.iidavisoingreso=iidavisoingreso;
	}
	public int getIidavisoingreso(){
		return this.iidavisoingreso;
	}
	public void setDecimporte(float decimporte){
		this.decimporte=decimporte;
	}
	public float getDecimporte(){
		return this.decimporte;
	}
}
