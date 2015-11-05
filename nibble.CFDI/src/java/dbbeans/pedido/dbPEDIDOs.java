/** Vector java bean entity **/
package dbbeans.pedido;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Vector;


public class dbPEDIDOs {
	private Connection conn;
	private ResultSet rSet;
	private PreparedStatement stmt;
	private Vector result;


	public dbPEDIDOs() {
		super();
		result = new Vector();
	}
	public void setConnection(java.sql.Connection conn) {
		this.conn = conn;
	}
	public boolean find(String where) throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		dbPEDIDO PEDIDO;
		query.append("SELECT * ");
		query.append("FROM PEDIDO ");
		if (!where.equals("")){
			query.append("WHERE " + where );
		}
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		rSet = stmt.executeQuery();


		while (rSet.next()) {
			PEDIDO= new dbPEDIDO();
			PEDIDO.setIidpedido(rSet.getInt("iidpedido"));
			PEDIDO.setIidproveedor(rSet.getInt("iidproveedor"));
			PEDIDO.setTisfechelabpedido(rSet.getString("tisfechelabpedido"));
			PEDIDO.setSinototalpartidas(rSet.getShort("sinototalpartidas"));
			PEDIDO.setSinototpartpedpend(rSet.getShort("sinototpartpedpend"));
			PEDIDO.setSinopartsurtpar(rSet.getShort("sinopartsurtpar"));
			PEDIDO.setSinopartcanceladas(rSet.getShort("sinopartcanceladas"));
			PEDIDO.setSinopartrecibidas(rSet.getShort("sinopartrecibidas"));
			PEDIDO.setTiestatus(rSet.getInt("tiestatus"));
			result.addElement(PEDIDO);
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

	public boolean findByStatus(int tiestatus) throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		dbPEDIDO PEDIDO;
		query.append("SELECT * ");
		query.append("FROM PEDIDO ");
		query.append("WHERE tiestatus= ? ");

		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setInt(1,tiestatus);
		
		rSet = stmt.executeQuery();


		while (rSet.next()) {
			PEDIDO= new dbPEDIDO();
			PEDIDO.setIidpedido(rSet.getInt("iidpedido"));
			PEDIDO.setIidproveedor(rSet.getInt("iidproveedor"));
			PEDIDO.setTisfechelabpedido(rSet.getString("tisfechelabpedido"));
			PEDIDO.setSinototalpartidas(rSet.getShort("sinototalpartidas"));
			PEDIDO.setSinototpartpedpend(rSet.getShort("sinototpartpedpend"));
			PEDIDO.setSinopartsurtpar(rSet.getShort("sinopartsurtpar"));
			PEDIDO.setSinopartcanceladas(rSet.getShort("sinopartcanceladas"));
			PEDIDO.setSinopartrecibidas(rSet.getShort("sinopartrecibidas"));
			PEDIDO.setTiestatus(rSet.getInt("tiestatus"));
			result.addElement(PEDIDO);
		}
		rSet.close();
		stmt.close();
		return false;
	}

	public int getBackOrder(long liidparte, int iidproveedor, int IDSINSURTIR, int IDPARCIALMENTE) throws Exception, java.sql.SQLException {
		int result=0;
		StringBuffer query = new StringBuffer();

		query.append("SELECT sum(icantpedida)-sum(icantrecibida) ");
		query.append("FROM PEDIDO p, PEDIDOPARTIDAS pp ");
		query.append("WHERE p.iidpedido=pp.iidpedido ");
		query.append("AND pp.liidparte=? AND p.iidproveedor=? ");
		query.append("AND (pp.tiestatus=? OR pp.tiestatus=?) ");
		query.append("AND pp.icantpedida > pp.icantrecibida ");

		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setLong(1,liidparte);
		stmt.setInt(2,iidproveedor);
		stmt.setInt(3,IDSINSURTIR);
		stmt.setInt(4,IDPARCIALMENTE);
		
		rSet = stmt.executeQuery();

		if (rSet.next()) {
			result = rSet.getInt(1);
		}
		rSet.close();
		stmt.close();
		return result;
	}
}
