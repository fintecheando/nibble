/*
 * Copyright (c) 2011 
 * Author : conam.rec@gmail.com.
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

package net.sf.jme.dig;


/**
 *
 * La clase abstracta Digito es la superclase de Unidad, Decena y Centena.
 * Un objeto Digito es un par digito - nombre.
 *
 * @author conam.rec@gmail.com.
 * @version 1.0
 */
public abstract class Digito {

	/**.*/
	public static final int MIN_VALOR = 0;

	/**.*/
	public static final int MAX_VALOR = 9;


	/** Nombres que tiene un digito que es unidad. */
	protected static final String[] UNIDADES = {
		" ", " uno ", " dos ",
		" tres ", " cuatro ", " cinco ",
		" seis ", " siete ", " ocho ", " nueve "};


	/** Nombres que tiene un digito que es decena. */
	protected static final String[] DECENAS = {
		" ", " dieci", " veinti", " treinta ",
		" cuarenta ", " cincuenta ", " sesenta ",
		" setenta ", " ochenta ", " noventa "};


	/** Nombres que tiene un digito que es centena. */
	protected static final String[] CENTENAS = {
		" ", " ciento ", " doscientos ", " trescientos ",
		" cuatrocientos ", " quinientos ", " seiscientos ",
		" setecientos ", " ochocientos ", " novecientos "};


	/** Entero con el digito. */
	protected int digito;


	/** Nombre del digito. */
	protected String nombre;

	/**
	 * Crea un objeto Digito con el entero especificado.
	 * @param digito Entero entre 0 - 9.
	 */
	public Digito(int digito) {
		setDigito(digito);
		setNombre(digito);
	}

	/**
	 * Crea un objeto Digito por defecto.
	 */
	public Digito() {
		this(MIN_VALOR);
	}

	/**
	 * Establece el nombre de este Digito a partir del entero especificado.
	 * @param digito Entero entre 0 - 9.
	 */
	public abstract void setNombre(int digito);

	/**
	 * Cambia el nombre de este Digito.
	 * @param nombre String con el nombre del Digito.
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Retorna el nombre del Digito.
	 * @return String con el nombre del Digito.
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Cambia el digito por dig.
	 * @param dig Entero entre 0 - 9
	 */
	public void setDigito(int dig) {

		if ((dig < MIN_VALOR) || (MAX_VALOR < dig)) {
			throw new IllegalArgumentException(dig + " No esta entre " + MIN_VALOR + " y " + MAX_VALOR);
		}
		this.digito = dig;
	}

	/**
	 * Devuelve el digito.
	 * @return Entero entre 0 - 9.
	 */
	public int getDigito() {
		return digito;
	}

}
