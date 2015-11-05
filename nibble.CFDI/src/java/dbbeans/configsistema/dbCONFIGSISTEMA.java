/** java bean entity **/
package dbbeans.configsistema;

import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class dbCONFIGSISTEMA implements org.nibble.main.dbInterfase {
	private Connection conn;
	private ResultSet rSet;
	private PreparedStatement stmt;


	private int iidconfsistema;
	private float decparidaddolar;
	private float decivaaplicable;
	private boolean bitutilsobreventa;
	private String vchdirproperties;
	private String vchcredentialfile;
	private String vchuipropertifile;
	private int ictemostradorsiva;
	private int ictemostradorciva;
	private String vchpaisdefault;
	private String vchestadodefault;
	private String vchviaembdef;
	private String vchnivelpreciodef;
	private int imodoredondeo;
	private int ipresicdivision;
	private boolean bitredondear;
	private int iprecision;   

	public void create() throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		query.append("INSERT INTO CONFIGSISTEMA (iidconfsistema,decparidaddolar,decivaaplicable,bitutilsobreventa,vchdirproperties,vchcredentialfile,vchuipropertifile,ictemostradorsiva,ictemostradorciva,vchpaisdefault,vchestadodefault,vchviaembdef,vchnivelpreciodef,imodoredondeo,ipresicdivision,bitredondear,iprecision) ");
		query.append(" VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ");
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setInt(1,iidconfsistema);
		stmt.setFloat(2,decparidaddolar);
		stmt.setFloat(3,decivaaplicable);
		stmt.setBoolean(4,bitutilsobreventa);
		stmt.setString(5,vchdirproperties);
		stmt.setString(6,vchcredentialfile);
		stmt.setString(7,vchuipropertifile);
		stmt.setInt(8,ictemostradorsiva);
		stmt.setInt(9,ictemostradorciva);
		stmt.setString(10,vchpaisdefault);
		stmt.setString(11,vchestadodefault);
		stmt.setString(12,vchviaembdef);
		stmt.setString(13,vchnivelpreciodef);
		stmt.setInt(14,imodoredondeo);
		stmt.setInt(15,ipresicdivision);
		stmt.setBoolean(16,bitredondear);
		stmt.setInt(17,iprecision);
		stmt.executeUpdate();
		stmt.close();
	}
	public boolean load() throws Exception, java.sql.SQLException {
		boolean found = false;
		StringBuffer query = new StringBuffer();
		query.append("SELECT * ");
		query.append("FROM CONFIGSISTEMA ");
		query.append("WHERE iidconfsistema=? ");
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setInt(1,iidconfsistema);
		rSet = stmt.executeQuery();


		if (rSet.next()) {
			found = true;
			this.decparidaddolar = rSet.getFloat("decparidaddolar");
			this.decivaaplicable = rSet.getFloat("decivaaplicable");
			this.bitutilsobreventa = rSet.getBoolean("bitutilsobreventa");
			this.vchdirproperties = rSet.getString("vchdirproperties");
			this.vchcredentialfile = rSet.getString("vchcredentialfile");
			this.vchuipropertifile = rSet.getString("vchuipropertifile");
			this.ictemostradorsiva = rSet.getInt("ictemostradorsiva");
			this.ictemostradorciva = rSet.getInt("ictemostradorciva");
			this.vchpaisdefault = rSet.getString("vchpaisdefault");
			this.vchestadodefault = rSet.getString("vchestadodefault");
			this.vchviaembdef = rSet.getString("vchviaembdef");
			this.vchnivelpreciodef = rSet.getString("vchnivelpreciodef");
			this.imodoredondeo = rSet.getInt("imodoredondeo");
			this.ipresicdivision = rSet.getInt("ipresicdivision");
			this.bitredondear = rSet.getBoolean("bitredondear");
			this.iprecision = rSet.getInt("iprecision");
		}
		rSet.close();
		stmt.close();
		return found;
	}
	public void remove() throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		query.append("DELETE FROM CONFIGSISTEMA ");
		query.append("WHERE iidconfsistema=? ");
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setInt(1,iidconfsistema);
		stmt.executeUpdate();
		stmt.close();
	}
	public void store() throws SQLException {
		StringBuffer query = new StringBuffer();
		query.append("UPDATE CONFIGSISTEMA SET decparidaddolar=? , decivaaplicable=? , "
                        + "bitutilsobreventa=? , vchdirproperties=? , vchcredentialfile=? , "
                        + "vchuipropertifile=? , ictemostradorsiva=? , ictemostradorciva=? , "
                        + "vchpaisdefault=? , vchestadodefault=? , vchviaembdef=? , vchnivelpreciodef=? , "
                        + "imodoredondeo=? , ipresicdivision=? , bitredondear=? , iprecision=? ");
		query.append("WHERE iidconfsistema=? ");

		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setFloat(1,decparidaddolar);
		stmt.setFloat(2,decivaaplicable);
		stmt.setBoolean(3,bitutilsobreventa);
		stmt.setString(4,vchdirproperties);
		stmt.setString(5,vchcredentialfile);
		stmt.setString(6,vchuipropertifile);
		stmt.setInt(7,ictemostradorsiva);
		stmt.setInt(8,ictemostradorciva);
		stmt.setString(9,vchpaisdefault);
		stmt.setString(10,vchestadodefault);
		stmt.setString(11,vchviaembdef);
		stmt.setString(12,vchnivelpreciodef);
		stmt.setInt(13,imodoredondeo);
		stmt.setInt(14,ipresicdivision);
		stmt.setBoolean(15,bitredondear);
		stmt.setInt(16,iprecision);
                stmt.setInt(17, iidconfsistema);
		stmt.executeUpdate();
		stmt.close();
	}
	public void setConnection(java.sql.Connection conn) {
		this.conn = conn;
	}
	public void setIidconfsistema(int iidconfsistema){
		this.iidconfsistema=iidconfsistema;
	}
	public int getIidconfsistema(){
		return this.iidconfsistema;
	}
	public void setDecparidaddolar(float decparidaddolar){
		this.decparidaddolar=decparidaddolar;
	}
	public float getDecparidaddolar(){
		return this.decparidaddolar;
	}
	public void setDecivaaplicable(float decivaaplicable){
		this.decivaaplicable=decivaaplicable;
	}
	public float getDecivaaplicable(){
		return this.decivaaplicable;
	}
	public void setBitutilsobreventa(boolean bitutilsobreventa){
		this.bitutilsobreventa=bitutilsobreventa;
	}
	public boolean getBitutilsobreventa(){
		return this.bitutilsobreventa;
	}
	public void setVchdirproperties(String vchdirproperties){
		this.vchdirproperties=vchdirproperties;
	}
	public String getVchdirproperties(){
		return this.vchdirproperties;
	}
	public void setVchcredentialfile(String vchcredentialfile){
		this.vchcredentialfile=vchcredentialfile;
	}
	public String getVchcredentialfile(){
		return this.vchcredentialfile;
	}
	public void setVchuipropertifile(String vchuipropertifile){
		this.vchuipropertifile=vchuipropertifile;
	}
	public String getVchuipropertifile(){
		return this.vchuipropertifile;
	}
	public void setIctemostradorsiva(int ictemostradorsiva){
		this.ictemostradorsiva=ictemostradorsiva;
	}
	public int getIctemostradorsiva(){
		return this.ictemostradorsiva;
	}
	public void setIctemostradorciva(int ictemostradorciva){
		this.ictemostradorciva=ictemostradorciva;
	}
	public int getIctemostradorciva(){
		return this.ictemostradorciva;
	}
	public void setVchpaisdefault(String vchpaisdefault){
		this.vchpaisdefault=vchpaisdefault;
	}
	public String getVchpaisdefault(){
		return this.vchpaisdefault;
	}
	public void setVchestadodefault(String vchestadodefault){
		this.vchestadodefault=vchestadodefault;
	}
	public String getVchestadodefault(){
		return this.vchestadodefault;
	}
	public void setVchviaembdef(String vchviaembdef){
		this.vchviaembdef=vchviaembdef;
	}
	public String getVchviaembdef(){
		return this.vchviaembdef;
	}
	public void setVchnivelpreciodef(String vchnivelpreciodef){
		this.vchnivelpreciodef=vchnivelpreciodef;
	}
	public String getVchnivelpreciodef(){
		return this.vchnivelpreciodef;
	}
	public void setImodoredondeo(int imodoredondeo){
		this.imodoredondeo=imodoredondeo;
	}
	public int getImodoredondeo(){
		return this.imodoredondeo;
	}
	public void setIpresicdivision(int ipresicdivision){
		this.ipresicdivision=ipresicdivision;
	}
	public int getIpresicdivision(){
		return this.ipresicdivision;
	}
	public void setBitredondear(boolean bitredondear){
		this.bitredondear=bitredondear;
	}
	public boolean getBitredondear(){
		return this.bitredondear;
	}
	public void setIprecision(int iprecision){
		this.iprecision=iprecision;
	}
	public int getIprecision(){
		return this.iprecision;
	}

   

   
   
}
