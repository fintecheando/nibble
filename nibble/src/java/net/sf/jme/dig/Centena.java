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
 * La clase Centena representa a un digito que es la centena.
 * Primer digito (de izquierda a derecha) de un grupo de tres digitos.
 *
 * @author conam.rec@gmail.com.
 * @version 1.0
 */
public final class Centena extends Digito {

	/**
	 * Construye una Centena con el digito especificado.
	 * @param digito Entero entre 0 - 9.
	 */
	public Centena(int digito) {
		super(digito);
	}

	/**
	 * Construye una Centena por defecto.
	 */
	public Centena() {
		this(0);
	}

	/**
	 * Establece el nombre de esta Centena a partir del digito especificado.
	 * @param digito Digito entre 0 - 9.
	 */
	public void setNombre(int digito) {
		nombre = CENTENAS[digito];
	}

}
