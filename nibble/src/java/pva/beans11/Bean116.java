package pva.beans11;

/**
 * Insert the type's description here.
 * Creation date: (11/12/2002 01:17:44 p.m.)
 * @author: 
 */
import dbbeans.avisoingreso.*;
 
public class Bean116 {
	private java.util.Vector vctIngresos;
	private boolean blnDetalle;
	private int intCliente;
/**
 * Insert the method's description here.
 * Creation date: (11/12/2002 04:51:07 p.m.)
 * @return int
 */
public int getCliente() {
	return intCliente;
}
	public String getDecdebe(int index){

		dbAVISOINGRESO a = (dbAVISOINGRESO) vctIngresos.elementAt(index);
		return org.nibble.util.Formato.formateoNumerico(a.getDecdebe(),"##,###,##0.00");

	}
	public String getDechaber(int index){

		dbAVISOINGRESO a = (dbAVISOINGRESO) vctIngresos.elementAt(index);
		return org.nibble.util.Formato.formateoNumerico(a.getDechaber(),"##,###,##0.00");

	}

	public float getSaldo(int index){

		dbAVISOINGRESO a = (dbAVISOINGRESO) vctIngresos.elementAt(index);
		return a.getDecdebe()-a.getDechaber();

	}
	
	public String getDecsaldo(int index){

		dbAVISOINGRESO a = (dbAVISOINGRESO) vctIngresos.elementAt(index);
		return org.nibble.util.Formato.formateoNumerico(a.getDecdebe()-a.getDechaber(),"##,###,##0.00");

	}
	public int getIidavisoingreso(int index){
		
		dbAVISOINGRESO a = (dbAVISOINGRESO) vctIngresos.elementAt(index);
		return a.getIidavisoingreso();
		
	}
/**
 * Insert the method's description here.
 * Creation date: (11/12/2002 01:24:47 p.m.)
 * @return java.util.Vector
 */
public int getIngresosSize() {
	return vctIngresos.size();
}
	public int getTipopago(int index){

		dbAVISOINGRESO a = (dbAVISOINGRESO) vctIngresos.elementAt(index);
		return a.getTitipopago();

	}
	public String getTisfecha(int index){
		
		dbAVISOINGRESO a = (dbAVISOINGRESO) vctIngresos.elementAt(index);
		return org.nibble.util.Fecha.formatMySQL(a.getTisfecha());
	}
/**
 * Insert the method's description here.
 * Creation date: (11/12/2002 01:46:00 p.m.)
 * @return boolean
 */
public boolean isDetalle() {
	return blnDetalle;
}
/**
 * Insert the method's description here.
 * Creation date: (11/12/2002 04:51:07 p.m.)
 * @param newCliente int
 */
public void setCliente(int newCliente) {
	intCliente = newCliente;
}
/**
 * Insert the method's description here.
 * Creation date: (11/12/2002 01:46:00 p.m.)
 * @param newDetalle boolean
 */
public void setDetalle(boolean newDetalle) {
	blnDetalle = newDetalle;
}
/**
 * Insert the method's description here.
 * Creation date: (11/12/2002 01:24:47 p.m.)
 * @param newIngresos java.util.Vector
 */
public void setIngresos(java.util.Vector newIngresos) {
	vctIngresos = newIngresos;
}
}
