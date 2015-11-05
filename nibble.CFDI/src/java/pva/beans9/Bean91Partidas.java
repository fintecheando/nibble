package pva.beans9;


/**
 * Insert the type's description here.
 * Creation date: (11/4/2002 7:11:13 PM)
 * @author:
 */
public class Bean91Partidas {
    private long liidparte;
    private java.lang.String prefijo;
    private java.lang.String numero;
    private java.lang.String sufijo;
    private java.lang.String medida;
    private java.lang.String vchdescripcion;
	private java.lang.String vchunidaddescripcion;
    private int iidnivel;
    private int cantidad;
    private float precioUnitario;
    private float importe;
    private int iexistencia;
    private float decprecioB100;
    private float decprecio;
    private int iidproveedor;
    private java.lang.String vchrazonsocial;
    private boolean existenciaMayorCantidad;
    private java.lang.String vchnumparte;
    private java.lang.String vchnivel;
    private int folio;

    /**
     * Bean91Partidas constructor comment.
     */
    public Bean91Partidas() {
        super();
    }

    /**
     * Insert the method's description here.
     * Creation date: (11/4/2002 7:31:24 PM)
     * @return int
     */
    public int getCantidad() {
        return cantidad;
    }

    /**
     * Insert the method's description here.
     * Creation date: (11/8/2002 12:39:15 PM)
     * @return float
     */
    public float getDecprecio() {
        return decprecio;
    }

    /**
     * Insert the method's description here.
     * Creation date: (11/8/2002 12:38:47 PM)
     * @return float
     */
    public float getDecprecioB100() {
        return decprecioB100;
    }

    /**
     * Insert the method's description here.
     * Creation date: (11/6/2002 7:03:28 PM)
     * @return int
     */
    public int getIexistencia() {
        return iexistencia;
    }

    /**
     * Insert the method's description here.
     * Creation date: (11/4/2002 7:30:51 PM)
     * @return int
     */
    public int getIidnivel() {
        return iidnivel;
    }

    /**
     * Insert the method's description here.
     * Creation date: (11/19/2002 11:10:51 AM)
     * @return int
     */
    public int getIidproveedor() {
        return iidproveedor;
    }

    /**
     * Insert the method's description here.
     * Creation date: (11/4/2002 7:32:16 PM)
     * @return float
     */
    public float getImporte() {
        return importe;
    }

    /**
     * Insert the method's description here.
     * Creation date: (11/4/2002 7:11:44 PM)
     * @return long
     */
    public long getLiidparte() {
        return liidparte;
    }

    /**
     * Insert the method's description here.
     * Creation date: (11/4/2002 7:17:25 PM)
     * @return java.lang.String
     */
    public java.lang.String getMedida() {
        return medida;
    }

    /**
     * Insert the method's description here.
     * Creation date: (11/4/2002 7:16:38 PM)
     * @return java.lang.String
     */
    public java.lang.String getNumero() {
        return numero;
    }

    /**
     * Insert the method's description here.
     * Creation date: (11/4/2002 7:31:51 PM)
     * @return float
     */
    public float getPrecioUnitario() {
        precioUnitario = (float) (Math.round(precioUnitario*100.0f)/100.0f);
        return precioUnitario;
    }

    /**
     * Insert the method's description here.
     * Creation date: (11/4/2002 7:16:22 PM)
     * @return java.lang.String
     */
    public java.lang.String getPrefijo() {
        return prefijo;
    }

    /**
     * Insert the method's description here.
     * Creation date: (11/4/2002 7:16:59 PM)
     * @return java.lang.String
     */
    public java.lang.String getSufijo() {
        return sufijo;
    }

    /**
     * Insert the method's description here.
     * Creation date: (11/4/2002 7:17:58 PM)
     * @return java.lang.String
     */
    public java.lang.String getVchdescripcion() {
        return vchdescripcion;
    }

    /**
     * Insert the method's description here.
     * Creation date: (12/23/2002 1:01:52 PM)
     * @return java.lang.String
     */
    public java.lang.String getVchnivel() {
        return vchnivel;
    }

    /**
     * Insert the method's description here.
     * Creation date: (12/23/2002 12:40:31 PM)
     * @return java.lang.String
     */
    public java.lang.String getVchnumparte() {
        return vchnumparte;
    }

    /**
     * Insert the method's description here.
     * Creation date: (11/20/2002 11:56:06 AM)
     * @return java.lang.String
     */
    public java.lang.String getVchrazonsocial() {
        return vchrazonsocial;
    }

    /**
     * Insert the method's description here.
     * Creation date: (11/20/2002 12:15:04 PM)
     * @return boolean
     */
    public boolean isExistenciaMayorCantidad() {
        return existenciaMayorCantidad;
    }

    /**
     * Insert the method's description here.
     * Creation date: (11/4/2002 7:31:24 PM)
     * @param newCantidad int
     */
    public void setCantidad(int newCantidad) {
        cantidad = newCantidad;
    }

    /**
     * Insert the method's description here.
     * Creation date: (11/8/2002 12:39:15 PM)
     * @param newDecprecio float
     */
    public void setDecprecio(float newDecprecio) {
        decprecio = newDecprecio;
    }

    /**
     * Insert the method's description here.
     * Creation date: (11/8/2002 12:38:47 PM)
     * @param newDecprecioB100 float
     */
    public void setDecprecioB100(float newDecprecioB100) {
        decprecioB100 = newDecprecioB100;
    }

    /**
     * Insert the method's description here.
     * Creation date: (11/20/2002 12:15:04 PM)
     * @param newExistenciaMayorCantidad boolean
     */
    public void setExistenciaMayorCantidad(boolean newExistenciaMayorCantidad) {
        existenciaMayorCantidad = newExistenciaMayorCantidad;
    }

    /**
     * Insert the method's description here.
     * Creation date: (11/6/2002 7:03:28 PM)
     * @param newIexistencia int
     */
    public void setIexistencia(int newIexistencia) {
        iexistencia = newIexistencia;
    }

    /**
     * Insert the method's description here.
     * Creation date: (11/4/2002 7:30:51 PM)
     * @param newIidnivel int
     */
    public void setIidnivel(int newIidnivel) {
        iidnivel = newIidnivel;
    }

    /**
     * Insert the method's description here.
     * Creation date: (11/19/2002 11:10:51 AM)
     * @param newIidproveedor int
     */
    public void setIidproveedor(int newIidproveedor) {
        iidproveedor = newIidproveedor;
    }

    /**
     * Insert the method's description here.
     * Creation date: (11/4/2002 7:32:16 PM)
     * @param newImporte float
     */
    public void setImporte(float newImporte) {
        importe = newImporte;
    }

    /**
     * Insert the method's description here.
     * Creation date: (11/4/2002 7:11:44 PM)
     * @param newLiidparte long
     */
    public void setLiidparte(long newLiidparte) {
        liidparte = newLiidparte;
    }

    /**
     * Insert the method's description here.
     * Creation date: (11/4/2002 7:17:25 PM)
     * @param newMedida java.lang.String
     */
    public void setMedida(java.lang.String newMedida) {
        medida = newMedida;
    }

    /**
     * Insert the method's description here.
     * Creation date: (11/4/2002 7:16:38 PM)
     * @param newNumero java.lang.String
     */
    public void setNumero(java.lang.String newNumero) {
        numero = newNumero;
    }

    /**
     * Insert the method's description here.
     * Creation date: (11/4/2002 7:31:51 PM)
     * @param newPrecioUnitario float
     */
    public void setPrecioUnitario(float newPrecioUnitario) {
        precioUnitario = newPrecioUnitario;
    }

    /**
     * Insert the method's description here.
     * Creation date: (11/4/2002 7:16:22 PM)
     * @param newPrefijo java.lang.String
     */
    public void setPrefijo(java.lang.String newPrefijo) {
        prefijo = newPrefijo;
    }

    /**
     * Insert the method's description here.
     * Creation date: (11/4/2002 7:16:59 PM)
     * @param newSufijo java.lang.String
     */
    public void setSufijo(java.lang.String newSufijo) {
        sufijo = newSufijo;
    }

    /**
     * Insert the method's description here.
     * Creation date: (11/4/2002 7:17:58 PM)
     * @param newVchdescripcion java.lang.String
     */
    public void setVchdescripcion(java.lang.String newVchdescripcion) {
        vchdescripcion = newVchdescripcion;
    }

    /**
     * Insert the method's description here.
     * Creation date: (12/23/2002 1:01:52 PM)
     * @param newVchnivel java.lang.String
     */
    public void setVchnivel(java.lang.String newVchnivel) {
        vchnivel = newVchnivel;
    }

    /**
     * Insert the method's description here.
     * Creation date: (12/23/2002 12:40:31 PM)
     * @param newVchnumparte java.lang.String
     */
    public void setVchnumparte(java.lang.String newVchnumparte) {
        vchnumparte = newVchnumparte;
    }

    /**
     * Insert the method's description here.
     * Creation date: (11/20/2002 11:56:06 AM)
     * @param newVchrazonsocial java.lang.String
     */
    public void setVchrazonsocial(java.lang.String newVchrazonsocial) {
        vchrazonsocial = newVchrazonsocial;
    }

    /**
     * @return
     */
    public int getFolio() {
        return folio;
    }

    /**
     * @param i
     */
    public void setFolio(int i) {
        folio = i;
    }
	/**
	 * @return
	 */
	public java.lang.String getVchunidaddescripcion() {
		return vchunidaddescripcion;
	}

	/**
	 * @param string
	 */
	public void setVchunidaddescripcion(java.lang.String string) {
		vchunidaddescripcion = string;
	}

}
