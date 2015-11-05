/** Vector java bean entity **/
package org.nibble.vo.factura;

import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Vector;
import org.nibble.dao.factura.FacturaDAO;


public class FacturaVO {
	private Connection conn;
	private ResultSet rSet;
	private PreparedStatement stmt;
	private Vector result;


	public FacturaVO() {
		super();
		result = new Vector();
	}
	public void setConnection(java.sql.Connection conn) {
		this.conn = conn;
	}
	public boolean find(String where) throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		FacturaDAO FACTURA;
		query.append("SELECT * ");
		query.append("FROM FACTURA ");
		if (!where.equals("")){
			query.append("WHERE " + where );
		}
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		rSet = stmt.executeQuery();


		while (rSet.next()) {
			FACTURA= new FacturaDAO();
			FACTURA.setIidfactura(rSet.getInt("iidfactura"));
			FACTURA.setIidnocliente(rSet.getInt("iidnocliente"));
			FACTURA.setIidusuario(rSet.getInt("iidusuario"));
			FACTURA.setTisfechafactura(rSet.getString("tisfechafactura"));
			FACTURA.setDecsubtotal(rSet.getFloat("decsubtotal"));
			FACTURA.setDecivatotal(rSet.getFloat("decivatotal"));
			FACTURA.setVchletra(rSet.getString("vchletra"));
			FACTURA.setInumero(rSet.getInt("inumero"));
			FACTURA.setDecdescglobal(rSet.getFloat("decdescglobal"));
			FACTURA.setTisfechavencimient(rSet.getString("tisfechavencimient"));
			FACTURA.setBitcredito(rSet.getBoolean("bitcredito"));
			FACTURA.setDecmontodesc(rSet.getFloat("decmontodesc"));
			result.addElement(FACTURA);
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

	public Vector findByClienteFecha(int cliente, String fini, String ffin) throws Exception, java.sql.SQLException {

		Vector v = new Vector();
		StringBuffer query = new StringBuffer();
		FacturaDAO FACTURA;
		query.append("select a.*, b.decdebe-b.dechaber as saldo from factura a ");
		query.append("left join ctaxcobrar b on a.iidfactura = b.iiddoctoorigen ");
		query.append("where b.tiidtipodocto=1 and (b.decdebe-b.dechaber > 0) ");
		query.append("and a.iidnocliente = ? ");
		if (!fini.equalsIgnoreCase(""))
			query.append("AND (a.tisfechafactura >= ? AND a.tisfechafactura <= ?)");
	
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setInt(1,cliente);
		if (!fini.equalsIgnoreCase("")){
			stmt.setString(2,fini);
			stmt.setString(3,ffin);		
		}

		
		rSet = stmt.executeQuery();

		while (rSet.next()) {
			FACTURA= new FacturaDAO();
			FACTURA.setIidfactura(rSet.getInt("iidfactura"));
			FACTURA.setIidnocliente(rSet.getInt("iidnocliente"));
			FACTURA.setIidusuario(rSet.getInt("iidusuario"));
			FACTURA.setTisfechafactura(rSet.getString("tisfechafactura"));
			FACTURA.setDecsubtotal(rSet.getFloat("decsubtotal"));
			FACTURA.setDecivatotal(rSet.getFloat("decivatotal"));
			FACTURA.setVchletra(rSet.getString("vchletra"));
			FACTURA.setInumero(rSet.getInt("inumero"));
			FACTURA.setDecdescglobal(rSet.getFloat("decdescglobal"));
			FACTURA.setTisfechavencimient(rSet.getString("tisfechavencimient"));
			FACTURA.setBitcredito(rSet.getBoolean("bitcredito"));
			FACTURA.setDecmontodesc(rSet.getFloat("decmontodesc"));
			v.addElement(FACTURA);
		}
		rSet.close();
		stmt.close();
		return v;
	}

	public Vector findByFecha(String fini, String ffin, boolean credito) throws Exception, java.sql.SQLException {

		Vector v = new Vector();
		StringBuffer query = new StringBuffer();
		FacturaClienteVO FACTURA;
		query.append("select a.*, b.vchnombre from factura a ");
		query.append("left join cliente b on a.iidnocliente = b.iidnocliente ");
		query.append("where a.bitcredito=? ");		
		if (!fini.equalsIgnoreCase(""))
			query.append("AND (a.tisfechafactura >= ? AND a.tisfechafactura <= ?)");
	
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		
		stmt.setBoolean(1,credito);
		if (!fini.equalsIgnoreCase("")){
			stmt.setString(2,fini);
			stmt.setString(3,ffin);		
		}

		
		rSet = stmt.executeQuery();

		while (rSet.next()) {
			FACTURA= new FacturaClienteVO();
			FACTURA.setIidfactura(rSet.getInt("iidfactura"));
			FACTURA.setIidnocliente(rSet.getInt("iidnocliente"));
			FACTURA.setIidusuario(rSet.getInt("iidusuario"));
			FACTURA.setTisfechafactura(rSet.getString("tisfechafactura"));
			FACTURA.setDecsubtotal(rSet.getFloat("decsubtotal"));
			FACTURA.setDecivatotal(rSet.getFloat("decivatotal"));
			FACTURA.setVchletra(rSet.getString("vchletra"));
			FACTURA.setInumero(rSet.getInt("inumero"));
			FACTURA.setDecdescglobal(rSet.getFloat("decdescglobal"));
			FACTURA.setTisfechavencimient(rSet.getString("tisfechavencimient"));
			FACTURA.setBitcredito(rSet.getBoolean("bitcredito"));
			FACTURA.setDecmontodesc(rSet.getFloat("decmontodesc"));
			FACTURA.setNombreCliente(rSet.getString("vchnombre"));
			v.addElement(FACTURA);
		}
		rSet.close();
		stmt.close();
		return v;
	}

        public Vector findByFecha(int mes, int ano) throws Exception, java.sql.SQLException {

		Vector v = new Vector();
		StringBuffer query = new StringBuffer();
		FacturaClienteVO FACTURA;
		query.append("SELECT vchrfccliente, vchserie, ifolio, inumaprobacion, "
                        + "vchfechahora, decmontooperacion, decmontoimpuesto, vchestadocomprobante, "
                        + "vchefectocomprobante, vchpedimento, vchfechapedimento, vchaduana "
                        + "FROM FACTURA f ");
		query.append("WHERE (YEAR(f.tisfechafactura) = ? AND MONTH(f.tisfechafactura) = ?)");

                //System.out.println("EL QUERY PARA TRAER EL REPORTE DEL SAT ES "+query.toString());
                
                

		stmt = conn.prepareStatement(query.toString().toLowerCase());
                stmt.setInt(1,ano);
                stmt.setInt(2,mes);

		
		rSet = stmt.executeQuery();

		while (rSet.next()) {
			FACTURA= new FacturaClienteVO();
			FACTURA.setVchrfccliente(rSet.getString("vchrfccliente"));
			FACTURA.setVchserie(rSet.getString("vchserie"));
			FACTURA.setIfolio(rSet.getInt("ifolio"));
			FACTURA.setInumaprobacion(rSet.getInt("inumaprobacion"));
			FACTURA.setVchfechahora(rSet.getString("vchfechahora"));
			FACTURA.setDecmontooperacion(rSet.getFloat("decmontooperacion"));
			FACTURA.setDecmontoimpuesto(rSet.getFloat("decmontoimpuesto"));
			FACTURA.setVchestadocomprobante(rSet.getString("vchestadocomprobante"));
			FACTURA.setVchefectocomprobante(rSet.getString("vchefectocomprobante"));
			FACTURA.setVchpedimento(rSet.getString("vchpedimento"));
			FACTURA.setVchfechapedimento(rSet.getString("vchfechapedimento"));
			FACTURA.setVchaduana(rSet.getString("vchaduana"));
			v.addElement(FACTURA);
		}
		rSet.close();
		stmt.close();
		return v;
	}
}
