package org.nibble.util;

/**
 * Procesa las Altas y Mofificaciones de Fechas. 
 */

import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
 
public class Fecha {
/**
 * 
 */
public Fecha() {
	super();
}
/**
 * Regresa la fecha en formato DMA. 
 * @param strFecha java.lang.String
 * @param strSeparador java.lang.String
 */
public static String amdToDMA(String strFecha, String strSeparador) {

    String result = null;
    String dia, mes, anio;
    anio = strFecha.substring(0, 4);
    mes = strFecha.substring(4, 6);
    dia = strFecha.substring(6, 8);
    result = dia + strSeparador + mes + strSeparador+anio;
    return result;

}
/**
 * Insert the method's description here.
 * Creation date: (23/08/2002 11:02:00 a.m.)
 * @return java.lang.String
 * @param dia int
 * @param mes int
 * @param anio int
 */
public static String biWeekly() {
    String result = null, dia, mes, anio;
    int intDia;
    Calendar fecha = Calendar.getInstance();

    intDia = fecha.get(Calendar.DAY_OF_MONTH);
    fecha.set(fecha.get(Calendar.YEAR), fecha.get(Calendar.MONTH), 1);
    if (intDia > 15) {
        fecha.add(Calendar.MONTH, 1);
        fecha.add(Calendar.DAY_OF_MONTH, -1);
    }

    dia = String.valueOf(fecha.get(Calendar.DAY_OF_MONTH));
    dia = ((dia.length() == 1) ? "0" + dia : dia);
    mes = String.valueOf(fecha.get(Calendar.MONTH) + 1);
    mes = ((mes.length() == 1) ? "0" + mes : mes);
    return result = dia + "/" + mes + "/" + String.valueOf(fecha.get(Calendar.YEAR));
}
/**
 * Convierte una cadena de fecha del formato  dd-mm-aaaa al formato aaaa-mm-dd
 *
 * @param fecha, fecha a formater
 * @return fecha formateada
 */
public static  String convertOracleToDMA(String fecha) {

		String dd,mm,aa;
		int pos;

		fecha = fecha.substring(0,fecha.indexOf(" "));
		
		 		pos=fecha.indexOf("-",0);
			  aa=fecha.substring(0,pos);
		
		    fecha=fecha.substring(pos+1,fecha.length());
		
		    pos=fecha.indexOf("-",0);
		
		    mm=fecha.substring(0,pos);
	  		dd=fecha.substring(pos+1,fecha.length());
	  		
		return dd+"/"+mm+"/"+aa;
}
/**
 * retorna la fecha con formato "aa+mm+dd".
 * @return int
 * @param strFecha java.lang.String
 */
public static int fchValor(String fecha) {

		int dd,mm,aa;
		int pos;

		fecha = fecha.substring(0,fecha.indexOf(" "));
		
		 		pos=fecha.indexOf("-",0);
			  aa = new Integer(fecha.substring(0,pos)).intValue();
		
		    fecha=fecha.substring(pos+1,fecha.length());
		
		    pos=fecha.indexOf("-",0);
		
		    mm = new Integer(fecha.substring(0,pos)).intValue();
	  		dd = new Integer(fecha.substring(pos+1,fecha.length())).intValue();

	return aa+mm+dd;
}
/**
 * Insert the method's description here.
 * Creation date: (23/08/2002 11:02:00 a.m.)
 * @return java.lang.String
 * @param dia int
 * @param mes int
 * @param anio int
 */
public static String firstDayOfPreviousMonth() {
    String result = null, dia, mes;
    Calendar fecha = Calendar.getInstance();
    fecha.set(fecha.get(Calendar.YEAR), fecha.get(Calendar.MONTH), 1);
    fecha.add(Calendar.MONTH,-1);
    dia = String.valueOf(fecha.get(Calendar.DAY_OF_MONTH));
    dia = ((dia.length() == 1) ? "0" + dia : dia);
    mes = String.valueOf(fecha.get(Calendar.MONTH)+1);
    mes = ((mes.length() == 1) ? "0" + mes : mes);
    return result =dia+"/" + mes + "/" + String.valueOf(fecha.get(Calendar.YEAR));
}
/**
 * Da formato al SQL.
 * Creation date: (19/04/02 05:00:28 p.m.)
 * @return java.lang.String
 * @param strFecha java.lang.String
*/
public static String formatMySQL(String strFecha) {
    String result = null;
    /* Autor: Victor Romero
     * Modificacion: Se cambia el Formato de la fecha dd/MM/yyyy HH:mm:ss por dd/MM/yyyy 
     * Fecha de Modificacion: 29-01-2004
     */ 
     SimpleDateFormat fm = new SimpleDateFormat("yyyyMMddHHmmss");
     SimpleDateFormat fmOut = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
     
    //SimpleDateFormat fm = new SimpleDateFormat("yyyyMMdd");
    //SimpleDateFormat fmOut = new SimpleDateFormat("dd/MM/yyyy");
    
    try {
        Date fecha = fm.parse(strFecha);
        result = fmOut.format(fecha);
    } catch (Exception e) {
        result = null;
    }

    return result;
}

/* Autor: Victor Romero
	 * Modificacion: Se agrega el metodo para cambiar el Formato de la fecha 
	 * a dd/MM/yyyy  
	 * Fecha de Modificacion: 05-02-2004
	 */ 
public static String formatMySQLddMMyyyy(String strFecha) {
	String result = null;
	
	 SimpleDateFormat fm = new SimpleDateFormat("yyyyMMddHHmmss");
	 SimpleDateFormat fmOut = new SimpleDateFormat("dd/MM/yyyy");    
    
	try {
		Date fecha = fm.parse(strFecha);
		result = fmOut.format(fecha);
	} catch (Exception e) {
		result = null;
	}

	return result;
}

public static String formatoReporteFacturaElectronica(Date todayDate){
        //Date todayDate = new Date();
        SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy@HH:mm:ss");
        String currentDateTime = fmt.format(todayDate).replace('@', ' ');
        
        return currentDateTime;
    }


public static String formatoFacturaElectronica(){
        Date todayDate = new Date();
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd@HH:mm:ss");
        String currentDateTime = fmt.format(todayDate).replace('@', 'T');
        
        return currentDateTime;
    }

public static String formatoFacturaElectronica(Date todayDate){
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd@HH:mm:ss");
        String currentDateTime = fmt.format(todayDate).replace('@', 'T');
        
        return currentDateTime;
    }


public static GregorianCalendar formatoDateFacturaElectronica(){        
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd@HH:mm:ss");
        GregorianCalendar currentDateTime = new GregorianCalendar();
        
        return currentDateTime;
    }

public static GregorianCalendar formatoDateFacturaElectronicaCorreccion(){        
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd@HH:mm:ss");
        GregorianCalendar currentDateTime = new GregorianCalendar(2011,4,2,22,11,4);
        
        return currentDateTime;
    }


public static int getCurrentYear(){
        Calendar fecha = Calendar.getInstance();
        
        int ano = fecha.get(Calendar.YEAR);
        return ano;
    }

public static int getCurrentMonth(){        
        Calendar fecha = Calendar.getInstance();
        
        int mes = fecha.get(Calendar.MONTH) +1;
        return mes;
    }

public static String formatReal (String strFecha, String strFormato){
	 String result = null;
	 
	 SimpleDateFormat fm = new SimpleDateFormat (strFormato);
	 SimpleDateFormat fmOut = new SimpleDateFormat("dd/MM/yyyy");
	 
	 try{
	 	Date fecha = fm.parse(strFecha);
	 	result = fmOut.format(fecha);
	 } catch (Exception e) {
	 	result = null;
	 }
	return result;
}


/**
 * Da formato al SQL. 
 * @return java.lang.String
 * @param strFecha java.lang.String
*/
public static String formatSQL(String strFecha) {
    String result = null;
    SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat fmOut = new SimpleDateFormat("dd/MM/yyyy");
    try {
        Date fecha = fm.parse(strFecha);
        result = fmOut.format(fecha);
    } catch (Exception e) {
        result = null;
    }

    return result;
}
/**
 * Obtiene la fecha actual con formato dd-mm-yyyy
 * @param ninguno
 * @return fecha actual 
 */
public static String getCurrentDate() {


	// Fecha de hoy
	Calendar calendar = Calendar.getInstance();
	SimpleDateFormat fm= new SimpleDateFormat("dd/MM/yyyy");
	String fecha=fm.format(calendar.getTime());

	return fecha;

}
/**
 * Obtiene la fecha actual con formato dd-mm-yyyy
 * @param ninguno
 * @return fecha actual
 */
public static String getCurrentDate(String strFormato) {


	// Fecha de hoy
	Calendar calendar = Calendar.getInstance();
	SimpleDateFormat fm= new SimpleDateFormat(strFormato);
	String fecha=fm.format(calendar.getTime()); 

	return fecha;

}
/**
 * Retorna la fecha con respecto al nombre del dia de la semana correspondiente por medio de "strDay".
 * @return java.lang.String
 * @param intFecha int
 */
public static String getFecha(int intDay) {
	String strDay = null;
	switch (intDay) {
		case 1 :
			strDay = new String("Domingo");
			break;
		case 2 :
			strDay = new String("Lunes");
			break;
		case 3 :
			strDay = new String("Martes");
			break;
		case 4 :
			strDay = new String("Miercoles");
			break;
		case 5 :
			strDay = new String("Jueves");
			break;
		case 6 :
			strDay = new String("Viernes");
			break;
		case 7 :
			strDay = new String("Sabado");
			break;
	}
	return strDay;
}
/**
 * Insert the method's description here.
 * Creation date: (25/05/2002 08:19:24 p.m.)
 * @return int
 * @param strFecha java.lang.String
 */
public static String getFecha(String strFecha,int dias,String strFormato) {

	//	La fecha debe estar en la forma AAAAMMDD
	
  	Calendar fechac = Calendar.getInstance();

	int yy = new Integer(strFecha.substring(0, 4)).intValue();
	int mm = new Integer(strFecha.substring(4, 6)).intValue();
	int dd = new Integer(strFecha.substring(6, strFecha.length())).intValue();
	//int dd = new Integer(strFecha.substring(6, 8)).intValue();
	fechac.set(yy, mm-1,dd);

	fechac.add(Calendar.DATE,dias);

	SimpleDateFormat fm= new SimpleDateFormat(strFormato);
	
	String fecha=fm.format(fechac.getTime()); 


	return fecha;
}
/**
 * Insert the method's description here. 
 * @return java.lang.String
 * @param dia int
 * @param mes int
 * @param anio int
 */
public static boolean halfMont() {
    boolean result = false;
    int intDia;
    Calendar fecha = Calendar.getInstance();
    intDia = fecha.get(Calendar.DAY_OF_MONTH);
    if (intDia > 15)
        result = true;
    return result;
}
/**
 * Insert the method's description here. 
 * @return java.lang.String
 * @param dia int
 * @param mes int
 * @param anio int
 */
public static String lastDayOfPreviousMonth() {
    String result = null, dia, mes;
    Calendar fecha = Calendar.getInstance();
    fecha.set(fecha.get(Calendar.YEAR), fecha.get(Calendar.MONTH), 1);
    fecha.add(Calendar.DAY_OF_MONTH,-1);
    dia = String.valueOf(fecha.get(Calendar.DAY_OF_MONTH));
    dia = ((dia.length() == 1) ? "0" + dia : dia);
    mes = String.valueOf(fecha.get(Calendar.MONTH)+1);
    mes = ((mes.length() == 1) ? "0" + mes : mes);
    return result =dia+"/" + mes + "/" + String.valueOf(fecha.get(Calendar.YEAR));
}
/**
 * Insert the method's description here. 
 * @return java.lang.String
 * @param dia int
 * @param mes int
 * @param anio int
 */
public static String monthToDay() {
    String result = null, dia="", mes="";
    
    Calendar fecha = Calendar.getInstance();
    fecha.set(fecha.get(Calendar.YEAR), fecha.get(Calendar.MONTH), 1);
    dia = String.valueOf(fecha.get(Calendar.DAY_OF_MONTH));
    dia = ((dia.length() == 1) ? "0" + dia : dia);
    mes = String.valueOf(fecha.get(Calendar.MONTH)+1);
    mes = ((mes.length() == 1) ? "0" + mes : mes);

    result =dia+"/" + mes + "/" + String.valueOf(fecha.get(Calendar.YEAR));
    
    return result;
}
/**
 * Obtiene el nombre del dia de la semana y lo retorna en "strDay"
 * @return java.lang.String
 * @param intDay int
 */
public static  String obtenDia(int intDay) {
	String strDay = null;
	switch (intDay) {
		case 0 :
			strDay = new String("Ninguno");
			break;
		case 1 :
			strDay = new String("Lunes");
			break;
		case 2 :
			strDay = new String("Martes");
			break;
		case 3 :
			strDay = new String("Miercoles");
			break;
		case 4 :
			strDay = new String("Jueves");
			break;
		case 5 :
			strDay = new String("Viernes");
			break;
		case 6 :
			strDay = new String("Sabado");
			break;
		case 7 :
			strDay = new String("Domingo");
			break;
	}
	return strDay;
}
/**
 * Obtiene el nombre del dia de la semana y lo retorna en "strDia"
 * @return java.lang.String
 * @param strFecha java.lang.String
 */
public static String obtenDia(String strFecha) {
	String strDia = null;
	int intDia, dd, mm, yy;
	int pos;
	Calendar fecha = Calendar.getInstance();


	//parse fecha

	yy = new Integer(strFecha.substring(0, 4)).intValue();
	mm = new Integer(strFecha.substring(4, 6)).intValue();
	dd = new Integer(strFecha.substring(6, strFecha.length())).intValue();
	//put values in calendar
	fecha.set(yy, mm-1,dd);

	intDia = fecha.get(Calendar.DAY_OF_WEEK);
	strDia = getFecha(intDia);
	return strDia;
}

/**
 * Da formato al SQL. 
 * @return java.lang.String
 * @param strFecha java.lang.String
*/
public static String formatToMySQL(String strFecha) {
    String result = null;
    SimpleDateFormat fm = new SimpleDateFormat("dd/MM/yyyy");
    SimpleDateFormat fmOut = new SimpleDateFormat("yyyyMMdd");
    try {
        Date fecha = fm.parse(strFecha);
        result = fmOut.format(fecha);
    } catch (Exception e) {
        result = null;
    }

    return result;
}

/**
 * Obtiene el nombre del dia de la semana y lo retorna en "strDay"
 * @return java.lang.String
 * @param intDay int
 */
public static String obtenDia(String fecha, String formato) throws Exception {
    java.text.DateFormat format = new SimpleDateFormat(formato);
    Calendar calendar = Calendar.getInstance();
    Date date = format.parse(fecha);
    calendar.setTime(date);

    int intDay = calendar.get(Calendar.DAY_OF_WEEK);

    String strDay = null;

    switch (intDay) {
        case 1 :
            strDay = new String("Domingo");
            break;
        case 2 :
            strDay = new String("Lunes");
            break;
        case 3 :
            strDay = new String("Martes");
            break;
        case 4 :
            strDay = new String("Miercoles");
            break;
        case 5 :
            strDay = new String("Jueves");
            break;
        case 6 :
            strDay = new String("Viernes");
            break;
        case 7 :
            strDay = new String("Sabado");
            break;
    }
    return strDay;
}

/**
 * Insert the method's description here. 
 * @return int
 * @param strFecha java.lang.String
 */
public static int getdias(String strFecha) {

//	La fecha debe estar en la forma AAAAMMDD
	
  	Calendar fechac = Calendar.getInstance();

	int yy = new Integer(strFecha.substring(0, 4)).intValue();
	int mm = new Integer(strFecha.substring(4, 6)).intValue();
	int dd = new Integer(strFecha.substring(6, strFecha.length())).intValue();
	fechac.set(yy, mm-1,dd);

	return fechac.get(Calendar.DAY_OF_YEAR);
}

/**
 * Insert the method's description here. 
 * @return int
 * @param strFecha java.lang.String
 */
public static int getdiferencia(String strFecha,String strFechaF) {

	int res = 0;

	int dia  = Fecha.getdias(strFecha);
	int diaf = Fecha.getdias(strFechaF);

	int yy  = Fecha.getyear(strFecha);
	int yyf = Fecha.getyear(strFechaF);

    if (yyf > yy) {

		dia = (365*(yyf-yy))-dia;
		res = dia+diaf;
  
	}else{
	    res = diaf-dia;
    }

	return res;
}

/**
 * Insert the method's description here. 
 * @return int
 * @param strFecha java.lang.String
 */
public static int getyear(String strFecha) {

//	La fecha debe estar en la forma AAAAMMDD
	
  	Calendar fechac = Calendar.getInstance();

	int yy = new Integer(strFecha.substring(0, 4)).intValue();
	int mm = new Integer(strFecha.substring(4, 6)).intValue();
	int dd = new Integer(strFecha.substring(6, strFecha.length())).intValue();
	fechac.set(yy, mm-1,dd);

	return fechac.get(Calendar.YEAR);
}

/**
 * Insert the method's description here. 
 * @return int
 * @param strFecha java.lang.String
 */
public static boolean mayor(String strHoy,String strDia) {

	//	La fecha debe estar en la forma yyyyMMddHHmm
	
  	Calendar fechah = Calendar.getInstance();
  	Calendar fecha = Calendar.getInstance();

	int yyh = new Integer(strHoy.substring(0, 4)).intValue();
	int mmh = new Integer(strHoy.substring(4, 6)).intValue();
	int ddh = new Integer(strHoy.substring(6, 8)).intValue();
	int hhh = new Integer(strHoy.substring(8, 10)).intValue();
	int mih = new Integer(strHoy.substring(10,12)).intValue();
	fechah.set(yyh,mmh-1,ddh,hhh,mih);

	int yy = new Integer(strDia.substring(0, 4)).intValue();
	int mm = new Integer(strDia.substring(4, 6)).intValue();
	int dd = new Integer(strDia.substring(6, 8)).intValue();
	int hh = new Integer(strDia.substring(8, 10)).intValue();
	int mi = new Integer(strDia.substring(10,12)).intValue();
	fecha.set(yy,mm-1,dd,hh,mi);
	

	return fechah.after(fecha);
}
/** Calcula la diferencia de fechas en dias
 * Insert the method's description here. 
 * @return int
 * @param strFecha java.lang.String
 */
public static int getdiferencia(String strFecha,String strFechaF,String strFormato)
    throws Exception {
	    
    java.text.SimpleDateFormat sdfmt = new java.text.SimpleDateFormat(strFormato);
    java.util.Date dt1 = null, dt2 = null;
    dt1 = sdfmt.parse(strFecha);
    dt2 = sdfmt.parse(strFechaF);
    long diff = Math.abs(dt2.getTime() - dt1.getTime());
    
    return (int) (diff / 86400000);        //24 * 60 * 60 * 1000
}
/**
 * Resta dias a la fecha en el formato strFormato 
 * @return int
 * @param strFecha java.lang.String
 */
public static String MinusDiasFecha(int dias,String fecha,String strFormato) throws Exception{
 	java.text.DateFormat format = new SimpleDateFormat(strFormato);
	Calendar calendar = Calendar.getInstance();
    Date date = format.parse(fecha);
    calendar.setTime(date);

	calendar.add(Calendar.DATE,dias * (-1));

	SimpleDateFormat fm= new SimpleDateFormat(strFormato);

	return fm.format(calendar.getTime());
}
/**
 * Insert the method's description here. 
 * @return int
 * @param strFecha java.lang.String
 */
public static String addDiasFecha(String hoy, int diaDeLaSemana,String strFormato) throws Exception{
	Calendar calendar = Calendar.getInstance();	
	 /* Modificacion: Se corrige el aumento en los dias para efectuar la suma dias del vencimiento del docto
	 * Se comentan las lineas	 	
	 */
	  	SimpleDateFormat fmOut = new SimpleDateFormat("yyyyMMddHHmmss");
     	Date HOY = fmOut.parse(hoy);	
       	calendar.setTime(HOY);	 							 
	 	
	calendar.add(Calendar.DATE,diaDeLaSemana);
	SimpleDateFormat fm= new SimpleDateFormat(strFormato);	
	String fecha=fm.format(calendar.getTime());	

	return fecha;
}

public static String agregarDiasFecha(String hoy, int diaDeLaSemana,String strFormato) throws Exception{
	Calendar calendar = Calendar.getInstance();
	/* Modificacion: Se corrige el aumento en los dias para efectuar la suma dias del vencimiento del docto
	 * Se comentan las lineas
		//SimpleDateFormat fmOut = new SimpleDateFormat("yyyyMMddHHmmss");
		//Date HOY = fmOut.parse(hoy);	
		//calendar.setTime(HOY);	 							 	 
	 */	
	calendar.add(Calendar.DATE,diaDeLaSemana);
	SimpleDateFormat fm= new SimpleDateFormat(strFormato);	
	String fecha=fm.format(calendar.getTime());	

	return fecha;
}

/**
 * Insert the method's description here. 
 * @return int
 * @param strFecha java.lang.String
 */
public static String addDiasSemanaFecha(String hoy,int diaDeLaSemana,String strFormato) throws Exception{
	Calendar calendar = Calendar.getInstance();
	SimpleDateFormat fmOut = new SimpleDateFormat("yyyyMMddHHmmss");
    Date HOY = fmOut.parse(hoy);
    
	calendar.setTime(HOY);

	int numero;
    if (calendar.get(Calendar.DAY_OF_WEEK) == 1)
    	numero = 7;
    else
    	numero = calendar.get(Calendar.DAY_OF_WEEK)-1;

    

    int dias = 7 - numero + diaDeLaSemana;
	
	calendar.add(Calendar.DATE,dias);

	SimpleDateFormat fm= new SimpleDateFormat(strFormato);
	
	String fecha=fm.format(calendar.getTime());

	return fecha;
}
}
