/** Vector java bean entity **/
package dbbeans.ctaxcobrar;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Vector;


public class dbCTAXCOBRARs {
	private Connection conn;
	private ResultSet rSet;
	private PreparedStatement stmt;
	private Vector result;


	public dbCTAXCOBRARs() {
		super();
		result = new Vector();
	}
	public void setConnection(java.sql.Connection conn) {
		this.conn = conn;
	}
	public boolean find(String where) throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		dbCTAXCOBRAR CTAXCOBRAR;
		query.append("SELECT * ");
		query.append("FROM CTAXCOBRAR ");
		if (!where.equals("")){
			query.append("WHERE " + where );
		}
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		rSet = stmt.executeQuery();


		while (rSet.next()) {
			CTAXCOBRAR= new dbCTAXCOBRAR();
			CTAXCOBRAR.setIiddoctoorigen(rSet.getInt("iiddoctoorigen"));
			CTAXCOBRAR.setTiconsecsubdocto(rSet.getInt("ticonsecsubdocto"));
			CTAXCOBRAR.setTiidtipodocto(rSet.getInt("tiidtipodocto"));
			CTAXCOBRAR.setIidnocliente(rSet.getInt("iidnocliente"));
			CTAXCOBRAR.setTisfechamovimiento(rSet.getString("tisfechamovimiento"));
			CTAXCOBRAR.setTisfechavencimient(rSet.getString("tisfechavencimient"));
			CTAXCOBRAR.setIidcontradocto(rSet.getInt("iidcontradocto"));
			CTAXCOBRAR.setTiidtipocontradoc(rSet.getInt("tiidtipocontradoc"));
			CTAXCOBRAR.setDecdebe(rSet.getFloat("decdebe"));
			CTAXCOBRAR.setDechaber(rSet.getFloat("dechaber"));
			//CTAXCOBRAR.setBitmovsaldado(rSet.getBoolean("bitmovsaldado"));
			CTAXCOBRAR.setBitmovsaldado(rSet.getInt("bitmovsaldado"));
			result.addElement(CTAXCOBRAR);
		}
		rSet.close();
		stmt.close();
		return false;
	}
	public void setResult (Vector result){
		this.result = result;
	}
	public Vector getResult(){
		return this.result;
	}

	public Vector findByCliente(int iidcliente) throws Exception, java.sql.SQLException {

		Vector v = new Vector();
		StringBuffer query = new StringBuffer();
		dbCTAXCOBRARr CTAXCOBRAR;
		query.append("SELECT a.iiddoctoorigen,a.ticonsecsubdocto,a.tiidtipodocto,a.iidnocliente,a.tisfechamovimiento,a.tisfechavencimient,");
		query.append("a.iidcontradocto,a.tiidtipocontradoc,a.decdebe,a.dechaber,a.bitmovsaldado,b.vchdesccorta,c.vchdesccorta ");
		query.append("FROM CTAXCOBRAR a ");
		query.append("LEFT JOIN TIPODOCUMENTO c ON a.tiidtipocontradoc=c.tiidtipodocto ");		
		query.append("LEFT JOIN TIPODOCUMENTO b ON a.tiidtipodocto=b.tiidtipodocto ");
		query.append("WHERE a.iidnocliente=? ");		
		query.append("ORDER by a.tisfechamovimiento");		
		
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setInt(1,iidcliente);
		
		
		
		rSet = stmt.executeQuery();

		while (rSet.next()) {
			CTAXCOBRAR= new dbCTAXCOBRARr();
			CTAXCOBRAR.setIiddoctoorigen(rSet.getInt(1));
			CTAXCOBRAR.setTiconsecsubdocto(rSet.getInt(2));
			CTAXCOBRAR.setTiidtipodocto(rSet.getInt(3));
			CTAXCOBRAR.setIidnocliente(rSet.getInt(4));
			CTAXCOBRAR.setTisfechamovimiento(rSet.getString(5));
			CTAXCOBRAR.setTisfechavencimient(rSet.getString(6));
			CTAXCOBRAR.setIidcontradocto(rSet.getInt(7));
			CTAXCOBRAR.setTiidtipocontradoc(rSet.getInt(8));
			CTAXCOBRAR.setDecdebe(rSet.getFloat(9));
			CTAXCOBRAR.setDechaber(rSet.getFloat(10));
			CTAXCOBRAR.setBitmovsaldado(rSet.getBoolean(11));
			CTAXCOBRAR.setTipodocto(rSet.getString(12));
			CTAXCOBRAR.setTipocontradocto(rSet.getString(13));
			v.addElement(CTAXCOBRAR);
		}
		rSet.close();
		stmt.close();
		return v;
	}

	public Vector findByClienteFecha(int cliente, String fini, String ffin, boolean isSaldado) throws Exception, java.sql.SQLException {

		Vector v = new Vector();
		StringBuffer query = new StringBuffer();
		dbCTAXCOBRARr CTAXCOBRAR;
		query.append("SELECT a.iiddoctoorigen,a.ticonsecsubdocto,a.tiidtipodocto,a.iidnocliente,a.tisfechamovimiento,a.tisfechavencimient,");
		query.append("a.iidcontradocto,a.tiidtipocontradoc,a.decdebe,a.dechaber,a.bitmovsaldado,b.vchdesccorta ");
		query.append("FROM CTAXCOBRAR as a,TIPODOCUMENTO as b ");
		query.append("WHERE a.iidnocliente=? ");
		query.append("AND a.iidcontradocto = 0 ");		

		if (isSaldado)
			query.append("AND a.decdebe-a.dechaber=0 ");
		else
			query.append("AND a.decdebe-a.dechaber > 0 ");

		query.append("AND (a.tiidtipodocto=b.tiidtipodocto)");
		if (!fini.equalsIgnoreCase(""))
			query.append("AND (a.tisfechamovimiento >= ? AND a.tisfechamovimiento <= ?) ");

		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setInt(1,cliente);
		if (!fini.equalsIgnoreCase("")){
			
			stmt.setString(2,fini);
			stmt.setString(3,ffin);		
		}

		
		
		rSet = stmt.executeQuery();

		while (rSet.next()) {
			CTAXCOBRAR= new dbCTAXCOBRARr();
			CTAXCOBRAR.setIiddoctoorigen(rSet.getInt(1));
			CTAXCOBRAR.setTiconsecsubdocto(rSet.getInt(2));
			CTAXCOBRAR.setTiidtipodocto(rSet.getInt(3));
			CTAXCOBRAR.setIidnocliente(rSet.getInt(4));
			CTAXCOBRAR.setTisfechamovimiento(rSet.getString(5));
			CTAXCOBRAR.setTisfechavencimient(rSet.getString(6));
			CTAXCOBRAR.setIidcontradocto(rSet.getInt(7));
			CTAXCOBRAR.setTiidtipocontradoc(rSet.getInt(8));
			CTAXCOBRAR.setDecdebe(rSet.getFloat(9));
			CTAXCOBRAR.setDechaber(rSet.getFloat(10));
			CTAXCOBRAR.setBitmovsaldado(rSet.getBoolean(11));
			CTAXCOBRAR.setTipodocto(rSet.getString(12));
			v.addElement(CTAXCOBRAR);
		}
		rSet.close();
		stmt.close();
		return v;
	}

	public float findSaldoDocto(int iiddoctoorigen,int tipodocto) throws Exception, java.sql.SQLException {

		float saldo = 0;
		StringBuffer query = new StringBuffer();
		
		query.append("SELECT cxc.decdebe-cxc.dechaber as saldo ");
		query.append("FROM CTAXCOBRAR as cxc ");
		query.append("WHERE cxc.iiddoctoorigen = ? ");
		query.append("AND cxc.tiidtipodocto = ? ");		

		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setInt(1,iiddoctoorigen);
		stmt.setInt(2,tipodocto);

		
		
		rSet = stmt.executeQuery();

		if (rSet.next()) {
			saldo = rSet.getFloat("saldo");
		}
		rSet.close();
		stmt.close();
		return saldo;
	}
	
	public int findFacturaSaldada(int iiddoctoorigen,int tipodocto) throws Exception, java.sql.SQLException {

			int saldo = 0;
			StringBuffer query = new StringBuffer();
		
			query.append("SELECT bitmovsaldado as saldo ");
			query.append("FROM ctaxcobrar  ");
			query.append("WHERE iiddoctoorigen = ? ");
			query.append("AND tiidtipodocto = ? ");		

			stmt = conn.prepareStatement(query.toString().toLowerCase());
			stmt.setInt(1,iiddoctoorigen);
			stmt.setInt(2,tipodocto);

			
		
			rSet = stmt.executeQuery();

			if (rSet.next()) {
				saldo = rSet.getInt("saldo");
			}
			rSet.close();
			stmt.close();
			return saldo;
		}

	public float findPagosFactura(int iiddoctoorigen,int tipodocto) throws Exception, java.sql.SQLException {

			float saldo = 0;
			StringBuffer query = new StringBuffer();
		
			query.append("SELECT sum(cxc2.dechaber) as saldo  ");
			query.append("FROM ctaxcobrar as cxc2  ");
			query.append("WHERE cxc2.iidcontradocto =  ? ");
			query.append("AND cxc2.tiidtipodocto = ? ");		

			stmt = conn.prepareStatement(query.toString().toLowerCase());
			stmt.setInt(1,iiddoctoorigen);
			stmt.setInt(2,tipodocto);

			
		
			rSet = stmt.executeQuery();

			if (rSet.next()) {
				saldo = rSet.getFloat("saldo");
			}
			rSet.close();
			stmt.close();
			return saldo;
		}
	
	public float findSaldoContraDocto(int iidcontradocto , int tipodocto) throws Exception, java.sql.SQLException {

			float saldo = 0;
			StringBuffer query = new StringBuffer();
		
			query.append("SELECT sum(cxc.decdebe-cxc.dechaber) as saldo ");
			query.append("FROM ctaxcobrar as cxc ");
			query.append("WHERE cxc.iidcontradocto = ? ");
			query.append("AND cxc.tiidtipodocto = ? ");		
			
			stmt = conn.prepareStatement(query.toString().toLowerCase());
			stmt.setInt(1,iidcontradocto);
			stmt.setInt(2,tipodocto);
			
			
		
			rSet = stmt.executeQuery();

			if (rSet.next()) {
				saldo = rSet.getFloat("saldo");
			}
			rSet.close();
			stmt.close();
			return saldo;
		}

	public float findAbonosDocto(int iidcontradocto , int tipodocto) throws Exception, java.sql.SQLException {

				float saldo = 0;
				StringBuffer query = new StringBuffer();		
										
				query.append("SELECT sum(cxc2.dechaber) as saldo "); 
				query.append("FROM ctaxcobrar as cxc2 ");
				query.append("WHERE cxc2.iidcontradocto = ? ");
				query.append("AND cxc2.tiidtipodocto = ? ");
				
				stmt = conn.prepareStatement(query.toString().toLowerCase());
				stmt.setInt(1,iidcontradocto);
				stmt.setInt(2,tipodocto);
			
				
		
				rSet = stmt.executeQuery();

				if (rSet.next()) {
					saldo = rSet.getFloat("saldo");
				}
				rSet.close();
				stmt.close();
				return saldo;
			}

	public float findCargosDocto(int iiddoctoorigen , int tipodocto) throws Exception, java.sql.SQLException {

				float saldo = 0;
				StringBuffer query = new StringBuffer();
			
				query.append("SELECT cxc.decdebe as saldo ");
				query.append("FROM ctaxcobrar as cxc ");
				query.append("WHERE cxc.iiddoctoorigen = ? ");
				query.append("AND cxc.tiidtipodocto = ? ");
			
				stmt = conn.prepareStatement(query.toString().toLowerCase());
				stmt.setInt(1,iiddoctoorigen);
				stmt.setInt(2,tipodocto);
			
				
		
				rSet = stmt.executeQuery();

				if (rSet.next()) {
					saldo = rSet.getFloat("saldo");
				}
				rSet.close();
				stmt.close();
				return saldo;
			}


	public Vector findByDocto(int iiddoctoorigen,int tipodocto) throws Exception, java.sql.SQLException {

		Vector v = new Vector();
		StringBuffer query = new StringBuffer();
		dbCTAXCOBRARr CTAXCOBRAR;
		query.append("SELECT a.iiddoctoorigen,a.ticonsecsubdocto,a.tiidtipodocto,a.iidnocliente,a.tisfechamovimiento,a.tisfechavencimient,");
		query.append("a.iidcontradocto,a.tiidtipocontradoc,a.decdebe,a.dechaber,a.bitmovsaldado,b.vchdesccorta ");
		query.append("FROM CTAXCOBRAR as a,TIPODOCUMENTO as b ");
		query.append("WHERE ((a.iiddoctoorigen=? and a.tiidtipodocto=?)or(a.iidcontradocto =? and a.tiidtipocontradoc=?)) ");		
		query.append("AND (a.tiidtipodocto=b.tiidtipodocto)");
		
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setInt(1,iiddoctoorigen);
		stmt.setInt(2,tipodocto);
		stmt.setInt(3,iiddoctoorigen);
		stmt.setInt(4,tipodocto);
		
		
		
		rSet = stmt.executeQuery();

		while (rSet.next()) {
			CTAXCOBRAR= new dbCTAXCOBRARr();
			CTAXCOBRAR.setIiddoctoorigen(rSet.getInt(1));
			CTAXCOBRAR.setTiconsecsubdocto(rSet.getInt(2));
			CTAXCOBRAR.setTiidtipodocto(rSet.getInt(3));
			CTAXCOBRAR.setIidnocliente(rSet.getInt(4));
			CTAXCOBRAR.setTisfechamovimiento(rSet.getString(5));
			CTAXCOBRAR.setTisfechavencimient(rSet.getString(6));
			CTAXCOBRAR.setIidcontradocto(rSet.getInt(7));
			CTAXCOBRAR.setTiidtipocontradoc(rSet.getInt(8));
			CTAXCOBRAR.setDecdebe(rSet.getFloat(9));
			CTAXCOBRAR.setDechaber(rSet.getFloat(10));
			CTAXCOBRAR.setBitmovsaldado(rSet.getBoolean(11));
			CTAXCOBRAR.setTipodocto(rSet.getString(12));
			v.addElement(CTAXCOBRAR);
		}
		rSet.close();
		stmt.close();
		return v;
	}

	public Vector findByFechaTipoDoc(String fini, String ffin, int tipo) throws Exception, java.sql.SQLException {

		Vector v = new Vector();
		StringBuffer query = new StringBuffer();
		dbCTAXCOBRARr CTAXCOBRAR;
		query.append("SELECT a.iiddoctoorigen,a.iidcontradocto,a.tiidtipocontradoc,a.tisfechamovimiento,");
		query.append("a.dechaber,");
		query.append("b.vchdesctipodocto,");
		query.append("c.titipopago,");
		query.append("c.bitavisoing,");
		query.append("d.vchdesctipopago ");
		query.append("FROM CTAXCOBRAR a ");		
		query.append("LEFT JOIN TIPODOCUMENTO b ON a.tiidtipocontradoc=b.tiidtipodocto ");
		query.append("LEFT JOIN PAGO c ON a.iiddoctoorigen=c.iidpago ");
		query.append("LEFT JOIN TIPOPAGO d on c.titipopago=d.tiidtipopago ");
		//query.append("TIPODOCUMENTO b ");
		query.append("WHERE a.tiidtipodocto=? ");
		query.append("AND (a.tisfechamovimiento >= ? AND a.tisfechamovimiento <= ?) ");

		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setInt(1,tipo);
		stmt.setString(2,fini);
		stmt.setString(3,ffin);

		
		
		rSet = stmt.executeQuery();

		while (rSet.next()){
			CTAXCOBRAR= new dbCTAXCOBRARr();
			CTAXCOBRAR.setIiddoctoorigen(rSet.getInt(1));
			CTAXCOBRAR.setIidcontradocto(rSet.getInt(2));
			CTAXCOBRAR.setTiidtipocontradoc(rSet.getInt(3));			
			CTAXCOBRAR.setTisfechamovimiento(rSet.getString(4));
			CTAXCOBRAR.setDechaber(rSet.getFloat(5));		
			CTAXCOBRAR.setTipodocto(rSet.getString(6));
			CTAXCOBRAR.setVchtipopago(rSet.getString(7));
			CTAXCOBRAR.setBitavisoing(rSet.getBoolean(8));
			CTAXCOBRAR.setVchtipopago(rSet.getString(9));		
			v.addElement(CTAXCOBRAR);
		}
		rSet.close();
		stmt.close();
		return v;
	}
	
	public boolean getCuentasVencidasNoSaldadas(int iidnocliente, String hoy) throws Exception, java.sql.SQLException {		
		StringBuffer query = new StringBuffer();
		boolean result = false;
		query.append("SELECT *");
		query.append("FROM CTAXCOBRAR ");
		query.append("WHERE iidnocliente = ? ");
		query.append("AND IIDCONTRADOCTO=0 ");
		/* Autor: Victor Romero      
		  * Original: query.append("AND Tisfechavencimient  > ? ");
		  * Modificacion:  Se Cambia query.append("AND Tisfechavencimient < ? ");               
		  * Fecha de Modificacion: 04-02-2004       		 
		  */
		query.append("AND Tisfechavencimient < ? ");
		query.append("AND decdebe > dechaber ");

		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setInt(1,iidnocliente);
		stmt.setString(2,hoy);
		
		rSet = stmt.executeQuery();


		if (rSet.next()) 
			result = true;

		

		rSet.close();
		stmt.close();
		return result;
	}	

	public boolean getClienteDadoDeBaja(int iidnocliente) throws Exception, java.sql.SQLException {		
			StringBuffer query = new StringBuffer();
			boolean result = false;
			query.append("SELECT *");
			query.append("FROM cliente ");
			query.append("WHERE iidnocliente = ? ");
			query.append("AND bitdadodebaja=1 ");
			stmt = conn.prepareStatement(query.toString().toLowerCase());
			stmt.setInt(1,iidnocliente);
			rSet = stmt.executeQuery();

			if (rSet.next()) 
				result = true;

			

			rSet.close();
			stmt.close();
			return result;
		}	



	public float findSaldosIngresos(int cliente, String fini, String ffin) throws Exception, java.sql.SQLException {

		float abono = 0;
		StringBuffer query = new StringBuffer();
		dbCTAXCOBRARr CTAXCOBRAR;
		query.append("SELECT sum(dechaber)-sum(decdebe) as ingreso ");
		query.append("FROM CTAXCOBRAR ");
		query.append("WHERE iidnocliente=? ");
		query.append("AND iidcontradocto = -1 ");
		if (!fini.equalsIgnoreCase(""))
			query.append("AND (tisfechamovimiento >= ? AND tisfechamovimiento <= ?) ");

		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setInt(1,cliente);
		if (!fini.equalsIgnoreCase("")){
			
			stmt.setString(2,fini);
			stmt.setString(3,ffin);		
		}

		
		
		rSet = stmt.executeQuery();

		while (rSet.next()) {
			abono = rSet.getFloat("ingreso");
		}
		rSet.close();
		stmt.close();
		return abono;
	}
}
