package org.nibble.util;

/**
 * Llena de Espacios una Cadena. 
 */
public class FillString {
/**
 * FillString constructor comment.
 
 */
public FillString() {
	super();
}
/**
 * Llena de espacios en blanco una cadena.
 * @return java.lang.String
 * @param intNumber int
 * @param strString java.lang.String
 
 */
public static String blank(int intNumber) {
    String result = "";
    if (intNumber > 0)
        for (int i = 0; i < intNumber; i++)
            result = result + " ";
    return result;
}
/**
 * Llena de espacios el lado izquierdo de una cadena.
 * @return java.lang.String
 * @param intNumber int
 * @param strString java.lang.String
 */
public static String leftFill(int intNumber, String strString, String strChar) {
    String result = strString;
    for (int i = strString.length(); i < intNumber; i++)
        result = strChar+result;
    return result;
}
}
