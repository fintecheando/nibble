package dbrbeans.pedidoproveedor;

/**
 * Insert the type's description here.
 * Creation date: (10/28/2002 3:13:28 PM)
 * @author: 
 */
public class dbrPEDIDOPROVEEDOR {
	private int iidproveedor;
	private String vchrazonsocial;

	private int iidpedido;
/**
 * dbrPEDIDOPROVEEDOR constructor comment.
 */
public dbrPEDIDOPROVEEDOR() {
	super();
}
/**
 * Insert the method's description here.
 * Creation date: (10/28/2002 3:16:36 PM)
 * @return int
 */
public int getIidpedido() {
	return iidpedido;
}
	public int getIidproveedor(){
		return this.iidproveedor;
	}
	public String getVchrazonsocial(){
		return this.vchrazonsocial;
	}
/**
 * Insert the method's description here.
 * Creation date: (10/28/2002 3:16:36 PM)
 * @param newIidpedido int
 */
public void setIidpedido(int newIidpedido) {
	iidpedido = newIidpedido;
}
	public void setIidproveedor(int iidproveedor){
		this.iidproveedor=iidproveedor;
	}
	public void setVchrazonsocial(String vchrazonsocial){
		this.vchrazonsocial=vchrazonsocial;
	}
}
