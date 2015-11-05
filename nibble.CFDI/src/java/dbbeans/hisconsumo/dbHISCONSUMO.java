/** java bean entity **/
package dbbeans.hisconsumo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class dbHISCONSUMO implements org.nibble.main.dbInterfase {
	private Connection conn;
	private ResultSet rSet;
	private PreparedStatement stmt;

	private long liidparte;
	private int iidproveedor;
	private int mm;
	private int yy;
	private long cantidad;

	public void create() throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		query.append(
			"INSERT INTO HISCONSUMO (liidparte,iidproveedor,mm,yy,cantidad) ");
		query.append(" VALUES(?,?,?,?,?) ");
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setLong(1, liidparte);
		stmt.setInt(2, iidproveedor);
		stmt.setInt(3, mm);
		stmt.setInt(4, yy);
		stmt.setLong(5, cantidad);
		stmt.executeUpdate();
		stmt.close();
		;
	}
	public boolean load() throws Exception, java.sql.SQLException {
		boolean result = false;
		StringBuffer query = new StringBuffer();
		query.append("SELECT cantidad ");
		query.append("FROM HISCONSUMO ");
		query.append(
			"WHERE liidparte=?  and iidproveedor=?  and mm=? and yy=? ");
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setLong(1, liidparte);
		stmt.setInt(2, iidproveedor);
		stmt.setInt(3, mm);
		stmt.setInt(4, yy);
		rSet = stmt.executeQuery();
		if (rSet.next()) {
			this.cantidad = rSet.getLong("cantidad");
			result = true;
		}

		rSet.close();
		stmt.close();
		return result;
	}
	public void remove() throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		query.append("DELETE FROM HISCONSUMO ");
		query.append(
			"WHERE liidparte=?  and iidproveedor=?  and mm=? and yy=? ");
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setLong(1, liidparte);
		stmt.setInt(2, iidproveedor);
		stmt.setInt(3, mm);
		stmt.setInt(4, yy);
		stmt.executeUpdate();
		stmt.close();
	}
	public void store() throws SQLException {
		StringBuffer query = new StringBuffer();
		query.append("UPDATE HISCONSUMO SET cantidad=? ");
		query.append(
			"WHERE liidparte=?  and iidproveedor=?  and mm=? and yy=? ");
		stmt = conn.prepareStatement(query.toString().toLowerCase());

		stmt.setLong(1, cantidad);
		stmt.setLong(2, liidparte);
		stmt.setInt(3, iidproveedor);
		stmt.setInt(4, mm);
		stmt.setInt(5, yy);
		stmt.executeUpdate();
		stmt.close();
	}

	public int calcIndiceConsumo(int[] meses, int[] anios) throws SQLException {
		int result = 0;
		StringBuffer query = new StringBuffer();
		query.append("SELECT SUM(CANTIDAD) FROM HISCONSUMO WHERE ");
		query.append("mm in(");
		for (int i = 0; i < meses.length; i++) {
			query.append(meses[i]);
			if ((i + 1) == meses.length) {
				query.append(")");
			} else {
				query.append(",");
			}
		}
		query.append("and yy in(");
		for (int i = 0; i < anios.length; i++) {
			query.append(anios[i]);
			if ((i + 1) == anios.length) {
				query.append(")");
			} else {
				query.append(",");
			}
		}
		rSet = stmt.executeQuery();
		if (rSet.next()) {
			result = rSet.getInt(0);
		}

		rSet.close();
		stmt.close();
		return result;
	}

    public Vector calcConsumoMensual(int meses[], int anios[])
        throws SQLException
    {
        StringBuffer query = new StringBuffer();
        Vector v = new Vector();
        query.append("SELECT CANTIDAD ");
        query.append("FROM HISCONSUMO WHERE ");
        query.append("mm in(");
        for(int i = 0; i < meses.length; i++)
        {
            query.append(meses[i]);
            if(i + 1 == meses.length)
                query.append(") ");
            else
                query.append(",");
        }

        query.append("and yy in(");
        for(int i = 0; i < anios.length; i++)
        {
            query.append(anios[i]);
            if(i + 1 == anios.length)
                query.append(") ");
            else
                query.append(",");
        }

        query.append("and liidparte=? and iidproveedor=?");
        
        stmt = conn.prepareStatement(query.toString().toLowerCase());
        stmt.clearParameters();
        stmt.setLong(1, liidparte);
        stmt.setInt(2, iidproveedor);
        for(rSet = stmt.executeQuery(); rSet.next(); v.addElement(new Integer(rSet.getInt("cantidad"))));
        return v;
    }
	
	public void setConnection(java.sql.Connection conn) {
		this.conn = conn;
	}
	public void setLiidparte(long liidparte) {
		this.liidparte = liidparte;
	}
	public long getLiidparte() {
		return this.liidparte;
	}
	public void setIidproveedor(int iidproveedor) {
		this.iidproveedor = iidproveedor;
	}
	public int getIidproveedor() {
		return this.iidproveedor;
	}
	public void setMm(int mm) {
		this.mm = mm;
	}
	public int getMm() {
		return this.mm;
	}
	public void setYy(int yy) {
		this.yy = yy;
	}
	public int getYy() {
		return this.yy;
	}
	public void setCantidad(long cantidad) {
		this.cantidad = cantidad;
	}
	public long getCantidad() {
		return this.cantidad;
	}
}
