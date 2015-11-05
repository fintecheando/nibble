/*
 * Copyright (c) 2011 
 * Author: conam.rec@gmail.com.
 *
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

import net.sf.jme.dig.Digito;
import net.sf.jme.dig.Unidad;
import net.sf.jme.dig.Decena;
import net.sf.jme.dig.Centena;

/**
 *
 * Clase que representa un conjunto de tres objetos Digito
 * XYZ, <br>Donde:<br>
 * X es la Centena,<br>
 * Y es la Decena, y<br>
 * Z es la Unidad.
 *
 * @author conam.rec@gmail.com.
 * @version 1.0
 *
 */
public final class Terna {

	/** Maxima cantidad de objetos Digito que puede contener una terna. */
	public static final int MAX_DIGITOS = 3;


	/** Sufijo que se postpone a la representacion de la Terna en String. */
	private static final String[] SUFIJOS = {"", " mil ", " millones ",
		" mil ", " billones ", " mil "};


	/** Objetos Digito de la Terna. */
	private Digito[] digitos = {new Centena(), new Decena(), new Unidad()};



	/** Indice de la Terna. Usado para identificar la Terna. */
	private int indice;


	/** Prefijo de la Terna. */
	private String sufijo;


	/** Indice de objetos Digito contenidos. */
	private int indDigito;



	/**
	 * Crea una Terna con el indice especificado.
	 * @param indice Indice de la Terna
	 */
	public Terna(int indice) {
		this.indice = indice;
		this.sufijo = SUFIJOS[indice];
		this.indDigito = MAX_DIGITOS - 1;
	}

	/**
	 * Agrega un digito al final de la Terna.
	 * El primero digito agregado sera la Unidad y el ultimo la Centena.
	 * @param digito Entero entre 0 - 9.
	 */
	public void agrega(int digito) {
		if (indDigito >= 0) {
			digitos[indDigito].setDigito(digito);
			//llamado polimorfico.
			digitos[indDigito--].setNombre(digito);
		}
	}

	/**
	 * Retorna la cantidad de digitos almacenados.
	 * @return Entero con la cantidad de Digitos contenidos.
	 */
	public int getCantidad() {
		return MAX_DIGITOS - (indDigito + 1);
	}

	/**
	 * Devuelve el indice de esta terna.
	 * @return Entero con el indice.
	 */
	public int getIndice() {
		return indice;
	}

	/**
	 * Corrige irregularidades en los nombres de los Digitos.
	 */
	public void corregir() {
		corregirUnidad();

		switch (getCantidad()) {
		case 3:
			corregirCentena();
		case 2:
			corregirDecena();
		}
	}

	/**
	 * Corrige el nombre del Digito Centena.
	 */
	private void corregirCentena() {
		int centena = digitos[0].getDigito();
		int decena = digitos[1].getDigito();
		int unidad = digitos[2].getDigito();

		if ((centena == 1) && (decena == 0) && (unidad == 0)) {
			digitos[0].setNombre(" cien ");
		}
	}

	/**
	 * Corrige el nombre del Digito Unidad de esta terna en caso de que<br>
	 * sea irregular.
	 */
	private void corregirUnidad() {
		int unidad = digitos[2].getDigito();

		if (indice > 0) {

			if (unidad == 1) {
				String str = " un ";

				if ((indice % 2 == 0) && (getCantidad() == 1)) {
					sufijo = sufijo.substring(0, sufijo.length() - 3) + " ";
				} else {
					str = (getCantidad() == 1) ? "" : str;
				}
				digitos[2].setNombre(str);
			}

		} else if ((unidad == 0) && (getCantidad() == 1)) {
			digitos[2].setNombre(" cero ");
		}
	}

	/**
	 * Corrige irregularidades en el nombre del Digito Decena.
	 */
	private void corregirDecena() {
		int decena = digitos[1].getDigito();
		int unidad = digitos[2].getDigito();

		if (decena > 2) {
			//Si la unidad es diferente de cero, entonces "decena y unidad".
			//ej: 35->Treinta y cinco. 89->Ochenta y nueve.
			String str = (unidad != 0) ? " y " : "";
			digitos[2].setNombre(str + digitos[2].getNombre());

		} else if (decena == 2) {

			if (unidad > 0) {
				//Si unidad > 0 --> veinti_unidad.
				digitos[2].setNombre(digitos[2].getNombre().substring(1));
			} else {
				//Si unidad == 0 --> veinte.
				digitos[1].setNombre(" veinte ");
			}

		} else if (decena == 1) {
			String nuevoNombre = digitos[2].getNombre().substring(1);
			if (unidad <= 5) {
				String []men6 = {" diez ", " once ", " doce ",
					" trece ", " catorce ", " quince "};

				digitos[1].setNombre(men6[unidad]);
				nuevoNombre = "";
			} /* else {
				digitos[2].setNombre(
				digitos[2].getNombre().substring(1));
			}*/
			digitos[2].setNombre(nuevoNombre);
		}
	}

	/**
	 * Verifica si la Terna es nula.<br>
	 * La Terna es nula si todos los digitos son cero.
	 * @return true si la Terna es nula, false en caso contrario.
	 */
	public boolean esNula() {
		for (Digito n : digitos) {
			if (n.getDigito() != 0) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Devuelve la representacion en String de esta Terna.
	 * @return String
	 */
	public String toString() {
		//Precondicion: La Terna debe estar corregida.
		String str = "";
		for (Digito dig : digitos) {
			str += dig.getNombre();
		}
		return str + sufijo;
	}

}
