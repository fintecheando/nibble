package pva.beans9;

/**
 * Insert the type's description here.
 * Creation date: (12/23/2002 2:47:20 PM)
 * @author:
 */
public class Bean91P {
	private int sinorengfac;
	private int IDCLIENTECONIVA;
	private int IDCLIENTESINIVA;
	private java.util.Vector Vfacturas;
	private dbrbeans.clientepaisestadomunicipio.dbrCLIENTE cliente;
	private java.lang.String fechaFacturacion;
	private java.lang.String fechaVencimiento;
        
        private String vchformapago;
        private String vchmetodopago;
        private String vchmcuentapago;

	/**
	 * Bean91P constructor comment.
	 */
	public Bean91P() {
		super();
	}

	/**
	 * Insert the method's description here.
	 * Creation date: (12/23/2002 3:01:57 PM)
	 * @return dbrbeans.clientepaisestadomunicipio.dbrCLIENTE
	 */
	public dbrbeans.clientepaisestadomunicipio.dbrCLIENTE getCliente() {
		return cliente;
	}

        public String getClienteNumExterior() {
		return (cliente.getVchnumexterior() == null)
			? ""
			: cliente.getVchnumexterior();
	}

        public String getClienteNumInterior() {
		return (cliente.getVchnuminterior() == null)
			? ""
			: cliente.getVchnuminterior();
	}

        public String getClienteLocalidad() {
		return (cliente.getVchlocalidad() == null)
			? ""
			: cliente.getVchlocalidad();
	}

	public String getClienteRazonSocial() {
		return (cliente.getVchrazonsocial() == null)
			? ""
			: cliente.getVchrazonsocial();
	}

	public String getClienteRFC() {
		return (cliente.getVchrfc() == null) ? "" : cliente.getVchrfc();
	}

	public String getClienteCalle() {
		return (cliente.getVchcalle() == null) ? "" : cliente.getVchcalle();
	}

	public String getClienteColonia() {
		return (cliente.getVchcolonia() == null) ? "" : cliente.getVchcolonia();
	}

	public String getClientePais() {
		return (cliente.getVchpaisdes() == null) ? "" : cliente.getVchpaisdes();
	}

	public String getClienteEstado() {
		return (cliente.getVchestadodes() == null)
			? ""
			: cliente.getVchestadodes();
	}

	public String getClienteMunicipio() {
		return (cliente.getVchmunicipiodes() == null)
			? ""
			: cliente.getVchmunicipiodes();
	}
	public String getClienteCodigoPostal() {
		return (cliente.getChcodigop() == null) ? "" : cliente.getChcodigop();
	}
	public String getClienteTelefono() {
		return (cliente.getVchtel1() == null) ? "" : cliente.getVchtel1();
	}

	public String getDatosGenerales() {
		StringBuffer datos = new StringBuffer();

		if (cliente.getIidcatcliente() == 0) {
			return "";
		}

		datos.append(
			(cliente.getVchnombre() == null) ? "" : cliente.getVchnombre());
		datos.append(", ");
		datos.append(
			(cliente.getVchrazonsocial() == null)
				? ""
				: cliente.getVchrazonsocial());
		datos.append(",\n ");
		datos.append((cliente.getVchrfc() == null) ? "" : cliente.getVchrfc());
		datos.append(", ");
		datos.append(
			(cliente.getVchcalle() == null) ? "" : cliente.getVchcalle());
		datos.append(", ");
		datos.append(
			(cliente.getVchcolonia() == null) ? "" : cliente.getVchcolonia());
		datos.append(",\n ");
		datos.append(
			(cliente.getVchpaisdes() == null) ? "" : cliente.getVchpaisdes());
		datos.append(", ");
		datos.append(
			(cliente.getVchestadodes() == null)
				? ""
				: cliente.getVchestadodes());
		datos.append(", ");
		datos.append(
			(cliente.getVchmunicipiodes() == null)
				? ""
				: cliente.getVchmunicipiodes());

		return datos.toString();
	}

	/**
	 * Insert the method's description here.
	 * Creation date: (12/23/2002 3:04:19 PM)
	 * @return java.lang.String
	 */
	public java.lang.String getFechaFacturacion() {
		return fechaFacturacion;
	}

	/**
	 * Insert the method's description here.
	 * Creation date: (12/23/2002 3:04:32 PM)
	 * @return java.lang.String
	 */
	public java.lang.String getFechaVencimiento() {
		return fechaVencimiento;
	}

	/**
	 * Insert the method's description here.
	 * Creation date: (12/23/2002 2:48:14 PM)
	 * @return int
	 */
	public int getIDCLIENTECONIVA() {
		return IDCLIENTECONIVA;
	}

	/**
	 * Insert the method's description here.
	 * Creation date: (12/23/2002 2:48:38 PM)
	 * @return int
	 */
	public int getIDCLIENTESINIVA() {
		return IDCLIENTESINIVA;
	}

	/**
	 * Insert the method's description here.
	 * Creation date: (12/23/2002 2:49:02 PM)
	 * @return java.util.Vector
	 */
	public int getIidfactura(int index) {
		Integer a = (Integer) Vfacturas.elementAt(index);

		return a.intValue();
	}

	/**
	 * Insert the method's description here.
	 * Creation date: (12/23/2002 3:01:57 PM)
	 * @return dbrbeans.clientepaisestadomunicipio.dbrCLIENTE
	 */
	public int getIidnocliente() {
		return cliente.getIidnocliente();
	}

	/**
	 * Insert the method's description here.
	 * Creation date: (12/23/2002 2:47:50 PM)
	 * @return int
	 */
	public int getSinorengfac() {
		return sinorengfac;
	}

	public String getVchviades() {
		return cliente.getVchviades();
	}

	/**
	 * Insert the method's description here.
	 * Creation date: (12/23/2002 2:49:02 PM)
	 * @return java.util.Vector
	 */
	public java.util.Vector getVfacturas() {
		return Vfacturas;
	}

	/**
	 * Insert the method's description here.
	 * Creation date: (12/23/2002 3:01:57 PM)
	 * @param newCliente dbrbeans.clientepaisestadomunicipio.dbrCLIENTE
	 */
	public void setCliente(
		dbrbeans.clientepaisestadomunicipio.dbrCLIENTE newCliente) {
		cliente = newCliente;
	}

	/**
	 * Insert the method's description here.
	 * Creation date: (12/23/2002 3:04:19 PM)
	 * @param newFechaFacturacion java.lang.String
	 */
	public void setFechaFacturacion(java.lang.String newFechaFacturacion) {
		fechaFacturacion = newFechaFacturacion;
	}

	/**
	 * Insert the method's description here.
	 * Creation date: (12/23/2002 3:04:32 PM)
	 * @param newFechaVencimiento java.lang.String
	 */
	public void setFechaVencimiento(java.lang.String newFechaVencimiento) {
		fechaVencimiento = newFechaVencimiento;
	}

	/**
	 * Insert the method's description here.
	 * Creation date: (12/23/2002 2:48:14 PM)
	 * @param newIDCLIENTECONIVA int
	 */
	public void setIDCLIENTECONIVA(int newIDCLIENTECONIVA) {
		IDCLIENTECONIVA = newIDCLIENTECONIVA;
	}

	/**
	 * Insert the method's description here.
	 * Creation date: (12/23/2002 2:48:38 PM)
	 * @param newIDCLIENTESINIVA int
	 */
	public void setIDCLIENTESINIVA(int newIDCLIENTESINIVA) {
		IDCLIENTESINIVA = newIDCLIENTESINIVA;
	}

	/**
	 * Insert the method's description here.
	 * Creation date: (12/23/2002 2:47:50 PM)
	 * @param newSinorengfac int
	 */
	public void setSinorengfac(int newSinorengfac) {
		sinorengfac = newSinorengfac;
	}

	/**
	 * Insert the method's description here.
	 * Creation date: (12/23/2002 2:49:02 PM)
	 * @param newVfacturas java.util.Vector
	 */
	public void setVfacturas(java.util.Vector newVfacturas) {
		Vfacturas = newVfacturas;
	}

    /**
     * @return the vchformapago
     */
    public String getVchformapago() {
        return vchformapago;
    }

    /**
     * @param vchformapago the vchformapago to set
     */
    public void setVchformapago(String vchformapago) {
        this.vchformapago = vchformapago;
    }

    /**
     * @return the vchmetodopago
     */
    public String getVchmetodopago() {
        return vchmetodopago;
    }

    /**
     * @param vchmetodopago the vchmetodopago to set
     */
    public void setVchmetodopago(String vchmetodopago) {
        this.vchmetodopago = vchmetodopago;
    }

    /**
     * @return the vchmcuentapago
     */
    public String getVchmcuentapago() {
        return vchmcuentapago;
    }

    /**
     * @param vchmcuentapago the vchmcuentapago to set
     */
    public void setVchmcuentapago(String vchmcuentapago) {
        this.vchmcuentapago = vchmcuentapago;
    }
}
