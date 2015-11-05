package org.nibble.util;

/**
 * Da el formato adecuado a los caracteres invalidos. 
 */
import java.util.Vector;
import java.util.StringTokenizer;
import java.text.DecimalFormat;
public class Formato {
/** 
 */
public Formato() {
	
}
/**
 * Realiza el Formato. 
 * @return java.lang.String 
 */
public static String formateoNumerico(double dblValor, String strMascara) {
    String result = null;
    DecimalFormat decimales= new DecimalFormat(strMascara);
    result= decimales.format(dblValor);
    return result;
}
/**
 * Cambia en una cadena los caracteres inv�lidos por su correcto equivalente
 *
 * @param str cadena a reemplazar los caracteres inv�lidos
 *
 * @return cadena corregida 
 */
public static String htmlToOracle(String cad) {

		String strSrch ="", strTmp="", str=cad;

		int pos;

		Vector car  = new Vector();
		car.addElement("�");
		car.addElement("�");
		car.addElement("�");
		car.addElement("�");
		car.addElement("�");
		car.addElement("�");
		car.addElement("�");
		car.addElement("�");

		Vector rep  = new Vector();
		rep.addElement("&#241;");
		rep.addElement("&#209;");
		rep.addElement("&#225;");
		rep.addElement("&#233;");
		rep.addElement("&#237;");
		rep.addElement("&#243;");
		rep.addElement("&#250;");
		rep.addElement("&#191;");
		 
		for(int c =0; c < car.size(); c++){
			strSrch = car.elementAt(c).toString();
			pos = str.indexOf(strSrch);
			strTmp="";
			while(pos != -1){
				strTmp += str.substring(0,pos).concat(rep.elementAt(c).toString());
				str = str.substring(pos+1);
				pos = str.indexOf(strSrch);
			}
 			str = strTmp.concat(str);	
		}
	
			str = str.replace('�', 'A');
			str = str.replace('�', 'E');
			str = str.replace('�', 'I');
			str = str.replace('�', 'O');
			str = str.replace('�', 'U');
	 
  	return str;
}
/**
 * Remplaza espacios por el caracter que se indica.
 * @return java.lang.String
 * @param to char
 * @param with char
 * @param cad java.lang.String 
 */
public static String replace(String with, String cad) {

	String cadrep = "";
		
	StringTokenizer st = new StringTokenizer(cad);
    while (st.hasMoreTokens()) {
	    
        cadrep+=st.nextToken()+with;
        
    }

    return cadrep;
 
}
/**
 * Asigna el formato de tipo monetario a una cantidad
 *
 * @param money, cantidad
 * @param format, tipo de formato
 *
 * @return cantidad formateada con el s�mbolo de dinero y separada en miles 
 */
public static String toMoney(double money,String format) {

	DecimalFormat myFormatter = new DecimalFormat(format);
	String output = "$"+myFormatter.format(money);
	
	return output;
}
/**
 * Asigna el formato de tipo monetario a una cadena con una cantidad
 *
 * @param money, cadena con la cantidad
 * @param format, tipo de formato
 *
 * @return cantidad formateada con el s�mbolo de dinero y separada en miles 
 */
public static String toMoney(double money) {

	DecimalFormat myFormatter = new DecimalFormat("#########0.00");
	return myFormatter.format(money);
}

public static String toPercentage(double percentage) {
	DecimalFormat myFormatter = new DecimalFormat("#0.00");
	return myFormatter.format(percentage);
}
 
public static String toMoney(String money,String format) {

	DecimalFormat myFormatter = new DecimalFormat(format);
	String output = "";

	try
	{
		double cantidad= new Double(money).doubleValue();
		output = "$"+myFormatter.format(cantidad);
	}
	catch(NumberFormatException e)
	{
	  System.out.println("El valor "+money+" no se puede convertir a formato money exception: "+e.toString());
	}
	
	return output;
}
}
