package org.nibble.util;

/**
 * Procesa el mensaje de acuerdo al Idioma . 
 */
import java.util.Locale;
import java.util.ResourceBundle;
import java.text.MessageFormat;

public class Internacional {
	private String strIdioma;
	private String strInstancia;
	private Locale localizacion ;
    private ResourceBundle labels;
/**
 * Insert the method's description here.
 * @param idioma java.lang.String
 * @param instancia java.lang.String 
 */
public Internacional(String idioma, String instancia) {
    this.strIdioma = idioma;
    this.strInstancia = instancia;
    localizacion = new Locale(strIdioma, "");
    labels = ResourceBundle.getBundle(strInstancia, localizacion);
}
/**
 * Retorna el Mensaje.
 * @param item java.lang.String 
 */
public String getMessage(String item) {
    String result = null;
    result = labels.getString(item);
    return result;

}
/**
 * Retorna el Mensaje Formateado.
 * @return java.lang.String
 * @param item java.lang.String
 * @param argumentos java.lang.Object 
 */
public String getMessage(String item, Object[] argumentos) {
    String output = null;
    Object[] messageArguments = argumentos;

    MessageFormat formatter = new MessageFormat("");
    formatter.setLocale(localizacion);

    formatter.applyPattern(labels.getString(item));
    output = formatter.format(messageArguments);

    return output;
}
}
