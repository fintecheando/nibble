/*
 * Copyright (c) 2011.
 * Author: conam.rec@gmail.com.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package net.sf.jme;

import java.math.BigInteger;

/**
 *
 * <p>Clase que provee dos metodos estaticos como punto de entrada para
 * realizar la conversion del monto escrito de un numero.</p>
 * 
 * <p> Ejemplo : <br>String monto = Monto.aLetras(8390);<br> Retornara "ocho  mil  trescientos  noventa" <br>
 * 
 * <br>String monto = Monto.aLetras(212942460); <br> 
 *  Retornara el String "doscientos  doce  millones  novecientos  cuarenta  y  dos  mil  cuatrocientos  sesenta" 
 * 
 * </p>
 *
 * @author conam.rec@gmail.com.
 * @version 1.0
 *
 */

public final class Monto {

	/** Constante que almacena el maximo valor que puede ser
	 * evaluado, 10^12 - 1.
	 */
	public static final BigInteger MAX_VALOR = new BigInteger("999999999999");

	/** Constante que almacena el minimo valor que puede ser 
	 * evaluado, 0.
	 */
	public static final BigInteger MIN_VALOR = BigInteger.ZERO;


	private Monto() { }


	/**
	 * Convierte el entero especificado a su cantidad en letras.
	 * @param numero String con el numero entero positivo para generar
	 * el monto escrito.
	 * @return String con el monto escrito del numero.
	 */
	public static String aLetras(String numero) {
		BigInteger numbi = new BigInteger(numero);

		if ((numbi.compareTo(MIN_VALOR) == -1) || (numbi.compareTo(MAX_VALOR) == 1)) {
			String msj = "Numero "+ numero + " fuera del rango " +
					MIN_VALOR+" - " + MAX_VALOR;
			throw new IllegalArgumentException(msj);
		}

		int nDigitos = numero.length();

		//Se obtiene la cantidad de Ternas.
		int nTernas = (int) Math.ceil((double) nDigitos / 3.0);

		Terna[] ternas = new Terna[nTernas];

		int indTerna = 0;
		int indDigito = 0;

		StringBuffer numeroInverso = new StringBuffer(numero).reverse();

		StringBuffer monto = new StringBuffer();

		while (indTerna < nTernas) {
			ternas[indTerna] = new Terna(indTerna);

			while ((indDigito < nDigitos)
					&& (ternas[indTerna].getCantidad() < Terna.MAX_DIGITOS)) {

				int digito = Integer.parseInt(""+numeroInverso.charAt(indDigito));
				ternas[indTerna].agrega(digito);
				indDigito++;
			}

			ternas[indTerna].corregir();
			if (!((ternas[indTerna].esNula()) && (ternas[indTerna].getIndice() % 2 != 0))) {
				monto.insert(0, ternas[indTerna].toString());
			}

			indTerna++;

		}

		return monto.toString();
	}

	/**
	 * Convierte el entero especificado a su cantidad en letras.
	 * @param numero Entero positivo para generar el monto escrito.
	 * @return String con el monto escrito del numero.
	 */
	public static String aLetras(int numero) {
		return aLetras(String.valueOf(numero));
	}

}
