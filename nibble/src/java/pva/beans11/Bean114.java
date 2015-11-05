package pva.beans11;

/**
 * Insert the type's description here.
 * Creation date: (09/12/2002 07:33:39 p.m.)
 * @author: 
 */
import pva.beans11.BeanHelper114;
 
public class Bean114 {
	
	private java.util.Vector vct;
	private java.lang.String strFecha;
	private boolean calculado = false;
	private float saldo;
/**
 * Insert the method's description here.
 * Creation date: (10/12/2002 12:10:38 p.m.)
 * @return java.lang.String
 */
public java.lang.String getFecha() {
	return strFecha;
}
/**
 * Insert the method's description here.
 * Creation date: (12/11/2002 02:04:47 p.m.)
 * @return int
 */
public int getIdcliente(int i) {
	BeanHelper114 a = (BeanHelper114) vct.elementAt(i);	
	return a.getIdcliente();
}
/**
 * Insert the method's description here.
 * Creation date: (09/12/2002 09:24:36 p.m.)
 * @return java.lang.String
 */
public java.lang.String getNombreCliente(int i) {
	BeanHelper114 a = (BeanHelper114) vct.elementAt(i);	
	return a.getNombreCliente();
}
/**
 * Insert the method's description here.
 * Creation date: (06/12/2002 07:45:50 p.m.)
 * @return float
 */
public String getPSx1_7() {
	float sum = 0;
	for (int i=0; i < vct.size(); i++){
		BeanHelper114 a = (BeanHelper114) vct.elementAt(i);
		sum += a.getDSx1_7();
	}
	float Saldo = calculado ? saldo :getTDSaldo();
	return org.nibble.util.Formato.formateoNumerico((sum/Saldo)*100,"##0.00");
}
/**
 * Insert the method's description here.
 * Creation date: (06/12/2002 07:45:50 p.m.)
 * @return float
 */
public String getPSx30_45() {
	float sum = 0;
	for (int i=0; i < vct.size(); i++){
		BeanHelper114 a = (BeanHelper114) vct.elementAt(i);
		sum += a.getDSx30_45();
	}
	float Saldo = calculado ? saldo :getTDSaldo();
	return org.nibble.util.Formato.formateoNumerico((sum/Saldo)*100,"##0.00");
}
/**
 * Insert the method's description here.
 * Creation date: (06/12/2002 07:45:50 p.m.)
 * @return float
 */
public String getPSx46_60() {
	float sum = 0;
	for (int i=0; i < vct.size(); i++){
		BeanHelper114 a = (BeanHelper114) vct.elementAt(i);
		sum += a.getDSx46_60();
	}
	float Saldo = calculado ? saldo :getTDSaldo();
	return org.nibble.util.Formato.formateoNumerico((sum/Saldo)*100,"##0.00");
}
/**
 * Insert the method's description here.
 * Creation date: (06/12/2002 07:45:50 p.m.)
 * @return float
 */
public String getPSx61() {
	float sum = 0;
	for (int i=0; i < vct.size(); i++){
		BeanHelper114 a = (BeanHelper114) vct.elementAt(i);
		sum += a.getDSx61();
	}
	float Saldo = calculado ? saldo :getTDSaldo();
	return org.nibble.util.Formato.formateoNumerico((sum/Saldo)*100,"##0.00");
}
/**
 * Insert the method's description here.
 * Creation date: (06/12/2002 07:45:50 p.m.)
 * @return float
 */
public String getPSx8_29() {
	float sum = 0;
	for (int i=0; i < vct.size(); i++){
		BeanHelper114 a = (BeanHelper114) vct.elementAt(i);
		sum += a.getDSx8_29();
	}
	float Saldo = calculado ? saldo :getTDSaldo();
	return org.nibble.util.Formato.formateoNumerico((sum/Saldo)*100,"##0.00");
}
/**
 * Insert the method's description here.
 * Creation date: (06/12/2002 07:45:50 p.m.)
 * @return float
 */
public String getPSxvencer() {
	float sum = 0;
	for (int i=0; i < vct.size(); i++){
		BeanHelper114 a = (BeanHelper114) vct.elementAt(i);
		sum += a.getDSxvencer();
	}

	float Saldo = calculado ? saldo :getTDSaldo();
	return org.nibble.util.Formato.formateoNumerico((sum/Saldo)*100,"##0.00");
}
/**
 * Insert the method's description here.
 * Creation date: (06/12/2002 07:45:50 p.m.)
 * @return float
 */
public String getSaldo(int i) {
	BeanHelper114 a = (BeanHelper114) vct.elementAt(i);
	return org.nibble.util.Formato.formateoNumerico(a.getDSxvencer()+a.getDSx1_7()+a.getDSx8_29()+a.getDSx30_45()+a.getDSx46_60()+a.getDSx61(),"##,###,##0.00");
}
/**
 * Insert the method's description here.
 * Creation date: (09/12/2002 01:53:06 p.m.)
 * @return float
 */
public String getSx1_7(int i) {
	BeanHelper114 a = (BeanHelper114) vct.elementAt(i);
	return org.nibble.util.Formato.formateoNumerico(a.getDSx1_7(),"##,###,##0.00");
}
/**
 * Insert the method's description here.
 * Creation date: (09/12/2002 01:55:54 p.m.)
 * @return float
 */
public String getSx30_45(int i) {
	BeanHelper114 a = (BeanHelper114) vct.elementAt(i);
	return org.nibble.util.Formato.formateoNumerico(a.getDSx30_45(),"##,###,##0.00");
}
/**
 * Insert the method's description here.
 * Creation date: (09/12/2002 01:57:05 p.m.)
 * @return float
 */
public String getSx46_60(int i) {
	BeanHelper114 a = (BeanHelper114) vct.elementAt(i);
	return org.nibble.util.Formato.formateoNumerico(a.getDSx46_60(),"##,###,##0.00");
}
/**
 * Insert the method's description here.
 * Creation date: (09/12/2002 02:01:54 p.m.)
 * @return float
 */
public String getSx61(int i) {
	BeanHelper114 a = (BeanHelper114) vct.elementAt(i);
	return org.nibble.util.Formato.formateoNumerico(a.getDSx61(),"##,###,##0.00");
}
/**
 * Insert the method's description here.
 * Creation date: (09/12/2002 01:55:25 p.m.)
 * @return float
 */
public String getSx8_29(int i) {
	BeanHelper114 a = (BeanHelper114) vct.elementAt(i);
	return org.nibble.util.Formato.formateoNumerico(a.getDSx8_29(),"##,###,##0.00");
}
/**
 * Insert the method's description here.
 * Creation date: (09/12/2002 01:53:06 p.m.)
 * @return float
 */
public String getSxvencer(int i) {
	BeanHelper114 a = (BeanHelper114) vct.elementAt(i);
	return org.nibble.util.Formato.formateoNumerico(a.getDSxvencer(),"##,###,##0.00");
}
/**
 * Insert the method's description here.
 * Creation date: (06/12/2002 07:45:50 p.m.)
 * @return float
 */
public float getTDSaldo() {
	float sum = 0;
	for (int i=0; i < vct.size(); i++){
		BeanHelper114 a = (BeanHelper114) vct.elementAt(i);
		sum += (a.getDSxvencer()+a.getDSx1_7()+a.getDSx8_29()+a.getDSx30_45()+a.getDSx46_60()+a.getDSx61());
	}
	calculado = true;
	saldo = sum;
	
	return sum;
}
/**
 * Insert the method's description here.
 * Creation date: (06/12/2002 07:45:50 p.m.)
 * @return float
 */
public String getTSaldo() {
	float sum = 0;
	for (int i=0; i < vct.size(); i++){
		BeanHelper114 a = (BeanHelper114) vct.elementAt(i);
		sum += (a.getDSxvencer()+a.getDSx1_7()+a.getDSx8_29()+a.getDSx30_45()+a.getDSx46_60()+a.getDSx61());
	}
	return org.nibble.util.Formato.formateoNumerico(sum,"##,###,##0.00");
}
/**
 * Insert the method's description here.
 * Creation date: (06/12/2002 07:45:50 p.m.)
 * @return float
 */
public String getTSx1_7() {
	float sum = 0;
	for (int i=0; i < vct.size(); i++){
		BeanHelper114 a = (BeanHelper114) vct.elementAt(i);
		sum += a.getDSx1_7();
	}
	return org.nibble.util.Formato.formateoNumerico(sum,"##,###,##0.00");
}
/**
 * Insert the method's description here.
 * Creation date: (06/12/2002 07:45:50 p.m.)
 * @return float
 */
public String getTSx30_45() {
	float sum = 0;
	for (int i=0; i < vct.size(); i++){
		BeanHelper114 a = (BeanHelper114) vct.elementAt(i);
		sum += a.getDSx30_45();
	}
	return org.nibble.util.Formato.formateoNumerico(sum,"##,###,##0.00");
}
/**
 * Insert the method's description here.
 * Creation date: (06/12/2002 07:45:50 p.m.)
 * @return float
 */
public String getTSx46_60() {
	float sum = 0;
	for (int i=0; i < vct.size(); i++){
		BeanHelper114 a = (BeanHelper114) vct.elementAt(i);
		sum += a.getDSx46_60();
	}
	return org.nibble.util.Formato.formateoNumerico(sum,"##,###,##0.00");
}
/**
 * Insert the method's description here.
 * Creation date: (06/12/2002 07:45:50 p.m.)
 * @return float
 */
public String getTSx61() {
	float sum = 0;
	for (int i=0; i < vct.size(); i++){
		BeanHelper114 a = (BeanHelper114) vct.elementAt(i);
		sum += a.getDSx61();
	}
	return org.nibble.util.Formato.formateoNumerico(sum,"##,###,##0.00");
}
/**
 * Insert the method's description here.
 * Creation date: (06/12/2002 07:45:50 p.m.)
 * @return float
 */
public String getTSx8_29() {
	float sum = 0;
	for (int i=0; i < vct.size(); i++){
		BeanHelper114 a = (BeanHelper114) vct.elementAt(i);
		sum += a.getDSx8_29();
	}
	return org.nibble.util.Formato.formateoNumerico(sum,"##,###,##0.00");
}
/**
 * Insert the method's description here.
 * Creation date: (06/12/2002 07:45:50 p.m.)
 * @return float
 */
public String getTSxvencer() {
	float sum = 0;
	for (int i=0; i < vct.size(); i++){
		BeanHelper114 a = (BeanHelper114) vct.elementAt(i);
		sum += a.getDSxvencer();
	}

	return org.nibble.util.Formato.formateoNumerico(sum,"##,###,##0.00");
}
/**
 * Insert the method's description here.
 * Creation date: (10/12/2002 12:10:38 p.m.)
 * @param newFecha java.lang.String
 */
public void setFecha(java.lang.String newFecha) {
	strFecha = newFecha;
}
/**
 * Insert the method's description here.
 * Creation date: (09/12/2002 09:15:22 p.m.)
 * @param newVct java.util.Vector
 */
public void setVct(java.util.Vector newVct) {
	vct = newVct;
}
/**
 * Insert the method's description here.
 * Creation date: (09/12/2002 09:15:22 p.m.)
 * @return java.util.Vector
 */
public int Vctsize() {
	return vct.size();
}
}
