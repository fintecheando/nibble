package org.nibble.util;

import java.text.SimpleDateFormat;

import java.util.Calendar;


/**
 * DOCUMENT ME!
 * 
 */
public class UtilDate {
    /** Constante que define el patron estandar de presentacion de fechas */
    private static final String PATRON_FECHA_DEFAULT = "dd/MM/yyyy";

    /**
     * Convertidor de fechas a String con el patron 'dd/MM/yyyy'
     *
     * @param dt La fecha a convertir
     *
     * @return La cadena transformada
     */
    public static String convertDateToString(java.util.Date dt) {
        return convertDateToString(dt, PATRON_FECHA_DEFAULT);
    }

    /**
     * Convertidor de fechas a un patron dado
     *
     * @param dt La fecha a convertir
     * @param pattern El patron de conversion (por ejemplo 'dd-MM-yyyy')
     *
     * @return La cadena transformada
     */
    public static String convertDateToString(java.util.Date dt, String pattern) {
        String formattedString = null;

        try {
            SimpleDateFormat formatter = new SimpleDateFormat(pattern);
            formattedString = formatter.format(dt);
        } catch (Exception e) {
            formattedString = "-";
        }

        return formattedString;
    }

    /**
     * convertSqlUtil Realiza la conversion un java.util.Date a java.sql.Date
     *
     * @param d java.util.Date
     *
     * @return java.util.Date
     */
    public static java.sql.Date convertSqlUtil(java.util.Date d) {
        java.sql.Date res = new java.sql.Date(new java.util.Date().getTime());

        if (d != null) {
            res = new java.sql.Date(d.getTime());
        }

        return res;
    }

    /**
     * Convertidor de cadenas con el patron 'dd/MM/yyyy' a fechas
     *
     * @param stringDate La cadena a transformar
     *
     * @return La fecha transformada
     */
    public static java.util.Date convertStringToDate(String stringDate) {
        return convertStringToDate(stringDate, PATRON_FECHA_DEFAULT);
    }

    /**
     * Convertidor de cadenas que siguen un patr�n dado a fechas
     *
     * @param stringDate La cadena a transformar
     * @param pattern El patron de conversion (por ejemplo 'dd/MM/yyyy')
     *
     * @return La fecha transformada
     */
    public static java.util.Date convertStringToDate(String stringDate, String pattern) {
        java.util.Date date = null;

        try {
            SimpleDateFormat formatter = new SimpleDateFormat(pattern);
            formatter.setLenient(true);
            date = formatter.parse(stringDate);
        } catch (Exception e) {
            date = new java.util.Date();
        }

        return date;
    }

    /**
     * Obtiene la fecha actual en un java.util.Date
     *
     * @return fecha actual
     */
    public static java.util.Date getDate() {
        Calendar calendar = Calendar.getInstance();

        return calendar.getTime();
    }

    /**
     * Insert the method's description here. Creation date: (25/05/2002 08:19:24 p.m.)
     *
     * @param yy java.lang.String
     * @param mm DOCUMENT ME!
     * @param dd DOCUMENT ME!
     *
     * @return java.util.Date
     */
    public static java.util.Date getDate(int yy, int mm, int dd) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(yy, mm - 1, dd);

        return calendar.getTime();
    }

    /**
     * Insert the method's description here. Creation date: (25/05/2002 08:19:24 p.m.)
     *
     * @param strDate java.lang.String
     * @param pattern DOCUMENT ME!
     *
     * @return int
     */
    public static int getDD(String strDate, String pattern) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(convertStringToDate(strDate, pattern));

        return calendar.get(Calendar.DATE);
    }

    /**
     * Insert the method's description here. Creation date: (25/05/2002 08:19:24 p.m.)
     *
     * @param strDate java.lang.String
     *
     * @return int
     */
    public static int getDD(String strDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(convertStringToDate(strDate));

        return calendar.get(Calendar.DATE);
    }

    /**
     * Insert the method's description here. Creation date: (25/05/2002 08:19:24 p.m.)
     *
     * @param strDate java.lang.String
     * @param pattern DOCUMENT ME!
     *
     * @return int
     */
    public static int getMM(String strDate, String pattern) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(convertStringToDate(strDate, pattern));

        return calendar.get(Calendar.MONTH) + 1;
    }

    /**
     * Insert the method's description here. Creation date: (25/05/2002 08:19:24 p.m.)
     *
     * @param strDate java.lang.String
     *
     * @return int
     */
    public static int getMM(String strDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(convertStringToDate(strDate));

        return calendar.get(Calendar.MONTH) + 1;
    }

    /**
     * Obtiene la fecha actual con formato dd/MM/yyyy
     *
     * @return fecha actual
     */
    public static String getStrDate() {
        Calendar calendar   = Calendar.getInstance();
        SimpleDateFormat fm = new SimpleDateFormat(PATRON_FECHA_DEFAULT);
        String date         = fm.format(calendar.getTime());

        return date;
    }

    /**
     * Insert the method's description here. Creation date: (25/05/2002 08:19:24 p.m.)
     *
     * @param yy DOCUMENT ME!
     * @param mm DOCUMENT ME!
     * @param dd DOCUMENT ME!
     * @param pattern DOCUMENT ME!
     *
     * @return String
     */
    public static String getStrDate(int yy, int mm, int dd, String pattern) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(yy, mm - 1, dd);

        SimpleDateFormat fm = new SimpleDateFormat(pattern);
        String date         = fm.format(calendar.getTime());

        return date;
    }

    /**
     * Obtiene la fecha actual con formato indicado
     *
     * @param pattern DOCUMENT ME!
     *
     * @return fecha actual
     */
    public static String getStrDate(String pattern) {
        Calendar calendar   = Calendar.getInstance();
        SimpleDateFormat fm = new SimpleDateFormat(pattern);
        String date         = fm.format(calendar.getTime());

        return date;
    }

    /**
     * Insert the method's description here. Creation date: (25/05/2002 08:19:24 p.m.)
     *
     * @param strDate java.lang.String
     *
     * @return int
     */
    public static int getYY(String strDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(convertStringToDate(strDate));

        return calendar.get(Calendar.YEAR);
    }

    /**
     * Regresa el dia de la semana, dada la fecha  DOMINGO, LUNES, MARTES, MIERCOLES, JUEVES, VIERNES, SABADO
     *
     * @param strDate Fecha
     *
     * @return String Dia de la semana
     */
    public static String getDAYOFWEEK(String strDate) {
        String dia        = null;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(convertStringToDate(strDate));

        switch (calendar.get(Calendar.DAY_OF_WEEK)) {
            case Calendar.SUNDAY:
                dia = "DOMINGO";

                break;

            case Calendar.MONDAY:
                dia = "LUNES";

                break;

            case Calendar.TUESDAY:
                dia = "MARTES";

                break;

            case Calendar.WEDNESDAY:
                dia = "MIERCOLES";

                break;

            case Calendar.THURSDAY:
                dia = "JUEVES";

                break;

            case Calendar.FRIDAY:
                dia = "VIERNES";

                break;

            case Calendar.SATURDAY:
                dia = "SABADO";

                break;
        }

        return dia;
    }

    /**
     * Regresa el dia de la semana, dada la fecha  DOMINGO, LUNES, MARTES, MIERCOLES, JUEVES, VIERNES, SABADO
     *
     * @param strDate Fecha
     *
     * @return String Dia de la semana
     */
    public static int getDAY(String strDate) {
        String dia        = null;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(convertStringToDate(strDate));

        return calendar.get(Calendar.DAY_OF_WEEK);
    }

    /**
     * Insert the method's description here. Creation date: (25/05/2002 08:19:24 p.m.)
     *
     * @param strDate java.lang.String
     * @param pattern DOCUMENT ME!
     *
     * @return int
     */
    public static int getYY(String strDate, String pattern) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(convertStringToDate(strDate, pattern));

        return calendar.get(Calendar.YEAR);
    }
}
