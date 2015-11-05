package general;

/**
 * Insert the type's description here.
 * Creation date: (12/11/2002 12:10:52 PM)
 * @author: 
 */
public class Help {
/**
 * Help constructor comment.
 */
public Help() {
	super();
}
/**
 * Insert the method's description here.
 * Creation date: (12/11/2002 12:14:07 PM)
 */
public static String getMedida(String vchnumparte) {
	return vchnumparte.substring(vchnumparte.lastIndexOf(".")+1,vchnumparte.length());
}
/**
 * Insert the method's description here.
 * Creation date: (12/11/2002 12:14:07 PM)
 */
public static String getNumero(String vchnumparte) {
	int indexof = vchnumparte.indexOf(".");
	return vchnumparte.substring(indexof+1,vchnumparte.indexOf(".",indexof+1));
}
/**
 * Insert the method's description here.
 * Creation date: (12/11/2002 12:14:07 PM)
 */
public static String getPrefijo(String vchnumparte) {
	return vchnumparte.substring(0,vchnumparte.indexOf("."));
}
/**
 * Insert the method's description here.
 * Creation date: (12/11/2002 12:14:07 PM)
 */
public static String getSufijo(String vchnumparte) {
	int indexof = vchnumparte.indexOf(".");
	indexof = vchnumparte.indexOf(".",indexof+1);
	return vchnumparte.substring(indexof+1,vchnumparte.indexOf(".",indexof+1));
}
/**
 * Insert the method's description here.
 * Creation date: (12/11/2002 12:14:07 PM)
 */
public static String getVchnumparte(String prefijo, String numero, String sufijo, String medida) {
    StringBuffer temp = new StringBuffer();

    temp.append(prefijo != null && prefijo.length() > 0 ? prefijo : "%");
    temp.append(".");
    temp.append(numero != null && numero.length() > 0 ? numero : "%");
    temp.append(".");
    temp.append(sufijo != null && sufijo.length() > 0 ? sufijo : "%");
    temp.append(".");
    temp.append(medida != null && medida.length() > 0 ? medida : "%");

    return temp.toString();
}
/**
 * Insert the method's description here.
 * Creation date: (12/11/2002 12:14:07 PM)
 */
public static String getVchnumparteEmpty(String prefijo, String numero, String sufijo, String medida) {
    StringBuffer temp = new StringBuffer();

    temp.append(prefijo != null && prefijo.length() > 0 ? prefijo : "");
    temp.append(".");
    temp.append(numero != null && numero.length() > 0 ? numero : "");
    temp.append(".");
    temp.append(sufijo != null && sufijo.length() > 0 ? sufijo : "");
    temp.append(".");
    temp.append(medida != null && medida.length() > 0 ? medida : "");

    return temp.toString();
}

/**
 * Insert the method's description here.
 * Creation date: (12/11/2002 12:14:07 PM)
 */
public static String getVchnumparteEmpty(String vchnumparte) {
    StringBuffer temp = new StringBuffer();
    temp.append(vchnumparte != null && vchnumparte.length() > 0 ? vchnumparte : "");
    return temp.toString();
}
/**
 * Insert the method's description here.
 * Creation date: (12/11/2002 12:14:07 PM)
 */
public void newMethod() {}
}
