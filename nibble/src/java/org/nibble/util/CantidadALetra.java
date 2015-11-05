package org.nibble.util;

import java.text.DecimalFormat;
import net.sf.jme.Monto;

public class CantidadALetra
{


	public CantidadALetra()
	{
	}
	public String Letras(double valor)
	{
		StringBuffer letras = new StringBuffer();
        letras.append("(");
		if(valor < (double)0)
		{
			valor = Math.abs(valor);
			letras.append("Menos ");
		}
		DecimalFormat df = new DecimalFormat("###########0.00");
		String moneda = df.format(valor);
		String pesos = moneda.substring(0, moneda.indexOf("."));
		String decimales = moneda.substring(moneda.indexOf(".") + 1);
		String parteRestante = pesos;
		String parte = null;
		int largo = 0;
		largo = parteRestante.length();
		if(largo > 9)
		{
			parte = parteRestante.substring(0, largo - 9);
			parteRestante = parteRestante.substring(largo - 9);
			largo = parteRestante.length();
			letras.append(String.valueOf(String.valueOf(parteALetras(parte))).concat("Billones "));
		}
		largo = parteRestante.length();
		if(largo > 6)
		{
			parte = parteRestante.substring(0, largo - 6);
			parteRestante = parteRestante.substring(largo - 6);
			largo = parteRestante.length();
			letras.append(String.valueOf(String.valueOf(parteALetras(parte))).concat("Millones "));
		}
		if(largo > 3)
		{
			parte = parteRestante.substring(0, largo - 3);
			parteRestante = parteRestante.substring(largo - 3);
			largo = parteRestante.length();
			letras.append(String.valueOf(String.valueOf(parteALetras(parte))).concat("Mil "));
		}
		parte = parteRestante;
		letras.append(String.valueOf(String.valueOf(parteALetras(parte))).concat("Pesos "));
		letras.append(String.valueOf(String.valueOf(decimales)).concat("/100 M.N.)"));
		return letras.toString().toUpperCase();
	}
/**
 * Insert the method's description here. 
 * @param args java.lang.String[]
 */
public static void main(String[] args) {
	CantidadALetra conver = new CantidadALetra();
        System.out.println(Monto.aLetras(55100));
        System.out.println(NumberToLetterConverter.convertNumberToLetter(55100));
	
        
	
}
	public String parteALetras(String valor)
	{
		DecimalFormat df = new DecimalFormat("000");
		String formato = df.format(Double.parseDouble(valor));
		StringBuffer parte = new StringBuffer();
		int digito = Integer.parseInt(formato.substring(0, 1));
		if(digito == 1)
			parte.append("Ciento ");
		if(digito == 2)
			parte.append("Doscientos ");
		if(digito == 3)
			parte.append("Trescientos ");
		if(digito == 4)
			parte.append("Cuatrocientos ");
		if(digito == 5)
			parte.append("Quinientos ");
		if(digito == 6)
			parte.append("Seiscientos ");
		if(digito == 7)
			parte.append("Setecientos ");
		if(digito == 8)
			parte.append("Ochocientos ");
		if(digito == 9)
			parte.append("Novecientos ");
		digito = Integer.parseInt(formato.substring(1, 2));
		if(digito == 1)
		{
			digito = Integer.parseInt(formato.substring(2, 3));
			if(digito == 0)
				parte.append("Diez ");
			if(digito == 1)
				parte.append("Once ");
			if(digito == 2)
				parte.append("Doce ");
			if(digito == 3)
				parte.append("Trece ");
			if(digito == 4)
				parte.append("Catorce ");
			if(digito == 5)
				parte.append("Quince ");
			if(digito == 6)
				parte.append("Diez y Seis ");
			if(digito == 7)
				parte.append("Diez y Siete ");
			if(digito == 8)
				parte.append("Diez y Ocho ");
			if(digito == 9)
				parte.append("Diez y Nueve ");
			return parte.toString();
		}
		if(digito == 2)
			parte.append("Veinte ");
		if(digito == 3)
			parte.append("Treinta ");
		if(digito == 4)
			parte.append("Cuarenta ");
		if(digito == 5)
			parte.append("Cincuenta ");
		if(digito == 6)
			parte.append("Sesenta ");
		if(digito == 7)
			parte.append("Setenta ");
		if(digito == 8)
			parte.append("Ochenta ");
		if(digito == 9)
			parte.append("Noventa ");
		digito = Integer.parseInt(formato.substring(2, 3));
		if(Integer.parseInt(formato.substring(1, 2)) > 0 && digito > 0)
			parte.append("y ");
		if(digito == 1)
			parte.append("Un ");
		if(digito == 2)
			parte.append("Dos ");
		if(digito == 3)
			parte.append("Tres ");
		if(digito == 4)
			parte.append("Cuatro ");
		if(digito == 5)
			parte.append("Cinco ");
		if(digito == 6)
			parte.append("Seis ");
		if(digito == 7)
			parte.append("Siete ");
		if(digito == 8)
			parte.append("Ocho ");
		if(digito == 9)
			parte.append("Nueve ");
		return parte.toString();
	}
}
