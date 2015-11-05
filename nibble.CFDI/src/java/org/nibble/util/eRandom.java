package org.nibble.util;

/**
 * Ejecuta la generacion de numeros aleatorios apartir de un Sid. 
 */
import java.util.Random;
import java.util.Hashtable;
 
public class eRandom {
/**
 *  
 */
public eRandom() {
	super();
}
/**
 * Ejecuta la generacion de numeros aleatorios menores a un numero.
 * @return int 
 */
public static int random(int max) {

	Random dice= new Random ();
  int winner=dice.nextInt();
 	if(winner<0)  winner = (-winner);
  winner=(winner%max)+1;
  
	return winner;
}
/**
 * Ejecuta la generacion de un arreglo numeros aleatorio donde todos son diferentes hasta el rango maximo.
 * @return java.util.Hashtable
 * @param rangoMax int
 * @param numPreg int 
 */
public static Hashtable randomArray(int rangoMax, int numPreg) {

	Hashtable hs = new Hashtable();
	int i = 1;
	int r;
	
	while (i <= numPreg) {
			r = random(rangoMax);
			if(!(hs.contains(new Integer(r)))){
			  hs.put(new Integer(i), new Integer(r));
			  i++;
			}
	}
	
	return hs;
}
}
