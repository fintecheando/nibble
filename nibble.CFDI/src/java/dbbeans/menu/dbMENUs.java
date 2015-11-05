/** Vector java bean entity **/
package dbbeans.menu;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Vector;


public class dbMENUs {
	private Connection conn;
	private ResultSet rSet;
	private PreparedStatement stmt;
	private Vector result;


	public dbMENUs() {
		super();
		result = new Vector();
	}
	public void setConnection(java.sql.Connection conn) {
		this.conn = conn;
	}
	public boolean find(String where) throws Exception, java.sql.SQLException {
		StringBuffer query = new StringBuffer();
		dbMENU MENU;
		query.append("SELECT * ");
		query.append("FROM MENU ");
		if (!where.equals("")){
			query.append("WHERE " + where );
		}
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		rSet = stmt.executeQuery();


		while (rSet.next()) {
			MENU= new dbMENU();
			MENU.setIidmenu(rSet.getInt("iidmenu"));
			MENU.setIidsistema(rSet.getShort("iidsistema"));
			MENU.setDescripcion(rSet.getString("descripcion"));
			MENU.setUrl(rSet.getString("url"));
			MENU.setNivel(rSet.getShort("nivel"));
			MENU.setImagen(rSet.getString("imagen"));
			MENU.setSmenu(rSet.getShort("smenu"));
			MENU.setAncho(rSet.getInt("ancho"));			
			result.addElement(MENU);
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

	public Vector findByGrupo(int grupo, int sistema) throws Exception, java.sql.SQLException {

		Vector v = new Vector();
		StringBuffer query = new StringBuffer();
		dbMENU MENU;
		query.append("SELECT b.* ");
		query.append("FROM GRUPOSMENU a ");
		query.append("LEFT JOIN MENU b ON a.iidmenu=b.iidmenu and a.iidsistema=b.iidsistema ");
		query.append("WHERE a.iidgrupo=? and a.iidsistema=? ");
		query.append("Order by b.iidmenu");

		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setInt(1,grupo);
		stmt.setInt(2,sistema);

		//System.out.println("Query ejecutado para traer el menu: "+query.toString().toLowerCase());
        
		rSet = stmt.executeQuery();

		while (rSet.next()) {
			MENU= new dbMENU();
			MENU.setIidmenu(rSet.getInt("iidmenu"));

			MENU.setIidsistema(rSet.getShort("iidsistema"));
			MENU.setDescripcion(rSet.getString("descripcion"));
			MENU.setUrl(rSet.getString("url"));
			MENU.setNivel(rSet.getShort("nivel"));
			MENU.setImagen(rSet.getString("imagen"));
			MENU.setSmenu(rSet.getShort("smenu"));
			MENU.setAncho(rSet.getInt("ancho"));
			v.addElement(MENU);
		}
		rSet.close();
		stmt.close();
		return v;
	}

    public int getResultSetSize(ResultSet resultSet) {
        int size = -1;

        try {
            resultSet.last();
            size = resultSet.getRow();
            resultSet.beforeFirst();
        } catch(SQLException e) {
            return size;
        }

        return size;
    }
}
