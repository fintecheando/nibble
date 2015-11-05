package org.nibble.util;

/**
 * Procesa la informacion de la Hora que es referente a los usuarios 
 
 */
 import java.util.Calendar;
 import java.text.SimpleDateFormat;
 import java.util.Date;

                                                                                                                                                                                                                                                                public class Hora {
/**
 * 
 * @author:  
 */
public Hora() {
	super();
}
/**
 * Retorna el formato del calendario e forma simple.
 * @return java.lang.String 
 */
public static String getCurrentime(String strFormato) {
	Calendar calendar = Calendar.getInstance();
	Date dtoday = calendar.getTime();
	SimpleDateFormat formatter = new SimpleDateFormat(strFormato);
	String strTime = formatter.format(dtoday);
	return strTime;
}
/**
 * Crea la hora en la BD y los retorna por medio de "intHoras"
 * @return int
 * @param dblhora double 
 */
public static int horas(double dblHora) {
	int intHoras = new Double(dblHora).intValue();
	return intHoras;
}
/**
 * Crea la hora en la BD y los retorna por medio de "intHoras"
 * @return int
 * @param dlbHora double 
 */
public static int minutos(double dblHora) {
	int intHoras, intMinutos;
	double dblMinutos = 0.0;
	intHoras = new Double(dblHora).intValue();
	dblMinutos = Math.rint((dblHora - intHoras) * 100);
	intMinutos = new Double(dblMinutos).intValue();
	return intMinutos;
}
/**
 * Obtiene las horas con sus respectivos minutos por medio de "strHora"
 * @return java.lang.String
 * @param dblHora double 
 */
public static String obtenHora(double dblHora) {
	String strHora = null;
	int intHoras, intMinutos;
	double dblMinutos = 0.0;
	Calendar calendario = Calendar.getInstance();
	SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
	Date dhora = null;
	intHoras = new Double(dblHora).intValue();
	dblMinutos = Math.rint((dblHora - intHoras) * 100);
	intMinutos = new Double(dblMinutos).intValue();
	calendario.set(Calendar.HOUR_OF_DAY, intHoras);
	calendario.set(Calendar.MINUTE, intMinutos);
	dhora = calendario.getTime();
	formatter.setCalendar(calendario);
	strHora = formatter.format(dhora);
	return strHora;
}
/**
 * Obtiene la Hora como Cadena
 * @return double
 * @param strHora java.lang.String 
 */
public static double obtenHora(String strHora) {
	double dblHora = 0;
	String strHoras = strHora.substring(0, 2);
	String strMinutos = strHora.substring(3, strHora.length());
	dblHora = new Double(strHoras + "." + strMinutos).doubleValue();
	return dblHora;
}
/**
 * Ejecuta la resta de las horas en su respectivo formato para manipularlas en las evaluaciones.
 * @return double
 * @param dblHora1 double
 * @param dblHora2 double 
 */
public static double restaHoras(double dblHora1, double dblHora2) {
	double result = 0.0;
		
	int intHoras = 0;
	int intMinutos = 0;
	int intHora1 = new Double(dblHora1).intValue();
	int intHora2 = new Double(dblHora2).intValue();
	double dblMinutos1 = Math.rint((dblHora1 - intHora1) * 100);
	double dblMinutos2 = Math.rint((dblHora2 - intHora2) * 100);
	int intMinutos1 = new Double(Math.abs(dblMinutos1)).intValue();
	int intMinutos2 = new Double(Math.abs(dblMinutos2)).intValue();

	intHoras = intHora2-intHora1;
	
	if (intMinutos1 == intMinutos2){
		intMinutos = 0;
	}

	if (intMinutos1 > intMinutos2){
		intMinutos = (60-intMinutos1) + intMinutos2;
		if (intHoras > 0) intHoras = intHoras-1;
	}

	if (intMinutos1 < intMinutos2){
		intMinutos = intMinutos2 - intMinutos1;
	}

	result = intHoras + (new Double(intMinutos).doubleValue() / 100);	

	return result;
}
/**
 * Envia el Separador.
 * @return java.lang.String
 * @param strHora java.lang.String
 * @param separador java.lang.String 
 */
public static String setSeparator(String strHora, String separador) {
    String horas,minutos, segundos,milisegundos;
    String result=null;
    horas=strHora.substring(0,2);
    minutos=strHora.substring(2,4);
    segundos=strHora.substring(4,6);
    milisegundos=strHora.substring(6,10);
    
    result=horas+separador+minutos+separador+segundos+milisegundos;
	
	return result;
}
/**
 * Ejecuta la suma de las horas en su respectivo formato para manipularlas en las evaluaciones.
 * @return double
 * @param dblHora1 double
 * @param dblHora2 double 
 */
public static double sumaHoras(double dblHora1, double dblHora2) {
	double result = 0.0;
	int intHora1 = new Double(dblHora1).intValue();
	int intHora2 = new Double(dblHora2).intValue();
	double dblMinutos1 = Math.rint((dblHora1 - intHora1) * 100);
	double dblMinutos2 = Math.rint((dblHora2 - intHora2) * 100);
	int intMinutos1 = new Double(Math.abs(dblMinutos1)).intValue();
	int intMinutos2 = new Double(Math.abs(dblMinutos2)).intValue();
	int intMinutos = intMinutos1 + intMinutos2;
	int intHoras = 0;
	//

	//check 60 minutos
	if (intMinutos >= 60) {
		intHoras = intMinutos / 60;
		intMinutos %= 60;
	}
	if ((intHora1 + intHora2 + intHoras) >= 24)
		result = intHora1 + intHora2 + intHoras - 24;
	else
		result = intHora1 + intHora2 + intHoras;

	//check minutos
	if (intMinutos != 0)
		result += (new Double(intMinutos).doubleValue() / 100);
	return result;
}
/**
 * Retorna la hora con su respectivo formato por medio de "strHora"
 * @return java.lang.String
 * @param dblHora double 
 */
public static String toString(double dblHora) {
	String strHora;
	int intHoras = horas(dblHora);
	int intMinutos = minutos(dblHora);
	strHora = String.valueOf(intHoras) + ":" + String.valueOf(intMinutos);
	return strHora;
}
}
