/** Vector java bean entity **/
package dbbeans.avisoingreso;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.Vector;


public class dbAVISOINGRESOs {
    private Connection conn;
    private ResultSet rSet;
    private PreparedStatement stmt;
    private Vector result;

    public dbAVISOINGRESOs() {
        super();
        result = new Vector();
    }

    public void setConnection(java.sql.Connection conn) {
        this.conn = conn;
    }

    public boolean find(String where) throws Exception, java.sql.SQLException {
        StringBuffer query = new StringBuffer();
        dbAVISOINGRESO AVISOINGRESO;
        query.append("SELECT * ");
        query.append("FROM AVISOINGRESO ");

        if (!where.equals("")) {
            query.append("WHERE " + where);
        }

        stmt = conn.prepareStatement(query.toString().toLowerCase());
        rSet = stmt.executeQuery();

        while (rSet.next()) {
            AVISOINGRESO = new dbAVISOINGRESO();
            AVISOINGRESO.setIidavisoingreso(rSet.getInt("iidavisoingreso"));
            AVISOINGRESO.setIidnocliente(rSet.getInt("iidnocliente"));
            AVISOINGRESO.setIidusuario(rSet.getInt("iidusuario"));
            AVISOINGRESO.setIidmodulo(rSet.getInt("iidmodulo"));
            AVISOINGRESO.setTitipopago(rSet.getInt("titipopago"));
            AVISOINGRESO.setTisfecha(rSet.getString("tisfecha"));
            AVISOINGRESO.setDecdebe(rSet.getFloat("decdebe"));
            AVISOINGRESO.setDechaber(rSet.getFloat("dechaber"));
            AVISOINGRESO.setVchconcepto(rSet.getString("vchconcepto"));
            result.addElement(AVISOINGRESO);
        }

        rSet.close();
        stmt.close();

        return false;
    }

    public void setResult(Vector result) {
        this.result = result;
    }

    public Vector getResult() {
        return this.result;
    }

    /**
     * Insert the method's description here.
     * Creation date: (11/12/2002 12:43:01 p.m.)
     * @return java.util.Vector
     */
    public Vector findByCliente(int cliente)
        throws Exception, java.sql.SQLException {
        Vector v = new Vector();
        StringBuffer query = new StringBuffer();
        dbAVISOINGRESO AVISOINGRESO;
        query.append("SELECT * FROM AVISOINGRESO ");
        query.append("WHERE iidnocliente=? ");
        query.append("AND decdebe-dechaber > 0 ");
        query.append("Order by tisfecha ");

        stmt = conn.prepareStatement(query.toString().toLowerCase());
        stmt.setInt(1, cliente);

        

        rSet = stmt.executeQuery();

        while (rSet.next()) {
            AVISOINGRESO = new dbAVISOINGRESO();
            AVISOINGRESO.setIidavisoingreso(rSet.getInt("iidavisoingreso"));
            AVISOINGRESO.setIidnocliente(rSet.getInt("iidnocliente"));
            AVISOINGRESO.setIidusuario(rSet.getInt("iidusuario"));
            AVISOINGRESO.setIidmodulo(rSet.getInt("iidmodulo"));
            AVISOINGRESO.setTitipopago(rSet.getInt("titipopago"));
            AVISOINGRESO.setTisfecha(rSet.getString("tisfecha"));
            AVISOINGRESO.setDecdebe(rSet.getFloat("decdebe"));
            AVISOINGRESO.setDechaber(rSet.getFloat("dechaber"));
            AVISOINGRESO.setVchconcepto(rSet.getString("vchconcepto"));
            v.addElement(AVISOINGRESO);
        }

        rSet.close();
        stmt.close();

        return v;
    }

    public float findSaldosIngresos(int cliente, String fini, String ffin)
        throws Exception, java.sql.SQLException {
        float abono = 0;
        StringBuffer query = new StringBuffer();
		dbAVISOINGRESO AVISOINGRESO;;
        query.append("SELECT sum(dechaber)-sum(decdebe) as ingreso ");
        query.append("FROM AVISOINGRESO ");
        query.append("WHERE iidnocliente=? ");

        if (!fini.equalsIgnoreCase("")) {
            query.append(
                "AND (tisfecha >= ? AND tisfecha <= ?) ");
        }

        stmt = conn.prepareStatement(query.toString().toLowerCase());
        stmt.setInt(1, cliente);

        if (!fini.equalsIgnoreCase("")) {
           
            stmt.setString(2, fini);
            stmt.setString(3, ffin);
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
