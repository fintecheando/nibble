/*
 * Created on Jan 31, 2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package pva.beans11;

import dbbeans.cliente.dbCLIENTE;
import dbbeans.notadecredito.dbNOTADECREDITO;

/**
 * @author Pedro
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class Bean111b {
	private dbCLIENTE dbcliente;
	private dbNOTADECREDITO dbnc;
	private double iva;
	private boolean popImprimir = false;
	private String fecha;

	/**
	 * @return
	 */
	public dbCLIENTE getDbcliente() {
		return dbcliente;
	}

	/**
	 * @return
	 */
	public dbNOTADECREDITO getDbnc() {
		return dbnc;
	}

	/**
	 * @return
	 */
	public double getIva() {
		return iva;
	}

	/**
	 * @param dbcliente
	 */
	public void setDbcliente(dbCLIENTE dbcliente) {
		this.dbcliente = dbcliente;
	}

	/**
	 * @param dbnotadecredito
	 */
	public void setDbnc(dbNOTADECREDITO dbnotadecredito) {
		dbnc = dbnotadecredito;
	}

	/**
	 * @param d
	 */
	public void setIva(double d) {
		iva = d;
	}

	/**
	 * @return
	 */
	public boolean isPopImprimir() {
		return popImprimir;
	}

	/**
	 * @param b
	 */
	public void setPopImprimir(boolean b) {
		popImprimir = b;
	}

	/**
	 * @return
	 */
	public String getFecha() {
		return fecha;
	}

	/**
	 * @param string
	 */
	public void setFecha(String string) {
		fecha = string;
	}

}
