package org.nibble.service.mostrador;

/**
 * Insert the type's description here.
 * Creation date: (12/5/2002 11:50:55 AM)
 * @author: 
 */
public class Calcula extends BeanHelper91 {
/**
 * Calcula constructor comment.
 */
public Calcula() {
	super();
}
/**
 * Calculo de precio sobre costo o venta	
 * Creation date: (12/5/2002 11:52:05 AM)
 * @return float
 * @param precio float
 * @param utilidad float
 * @param bitSobreVenta boolean
 */
public static float precio(
    float precio,
    float utilidad,
    boolean bitSobreVenta,
    boolean mostradorSinIVA,
    float iva) {
	float result;
    if (!bitSobreVenta)
        // Sobre Costo
        result = precio * (1 + utilidad / 100);
    else
        // Sobre venta
        result = precio / (1 - utilidad / 100);
    result = Calcula.redondeo(result);
    if (mostradorSinIVA)
	    result = result * (1 + iva/100);    
    return Calcula.redondeo(result);
}
/**
 * Calculo de precio sobre costo o venta	
 * Creation date: (12/5/2002 11:52:05 AM)
 * @return float
 */
public static float redondeo(float precio) {
	return (float) Math.round(precio*100) / 100;
}
}
