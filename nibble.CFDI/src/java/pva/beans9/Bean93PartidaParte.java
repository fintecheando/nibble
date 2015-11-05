package pva.beans9;

/**
 * Insert the type's description here.
 * Creation date: (11/20/2002 5:51:40 PM)
 * @author: 
 */
public class Bean93PartidaParte {
	public dbrbeans.parteclasificacion.dbrPARTE parte;
	public dbrbeans.proveedorinventario.dbrPROVEEDOR proveedor;
/**
 * Bean93PartidaParte constructor comment.
 */
public Bean93PartidaParte() {
	super();
	parte = new dbrbeans.parteclasificacion.dbrPARTE();
	proveedor = new dbrbeans.proveedorinventario.dbrPROVEEDOR();
}
/**
 * Insert the method's description here.
 * Creation date: (11/21/2002 11:19:24 AM)
 * @return dbrbeans.parteclasificacion.dbrPARTE
 */
public dbrbeans.parteclasificacion.dbrPARTE getParte() {
	return parte;
}
/**
 * Insert the method's description here.
 * Creation date: (11/21/2002 11:20:05 AM)
 * @return dbbeans.proveedor.dbPROVEEDOR
 */
public dbrbeans.proveedorinventario.dbrPROVEEDOR getProveedor() {
	return proveedor;
}
/**
 * Insert the method's description here.
 * Creation date: (11/21/2002 11:19:24 AM)
 * @param newParte dbrbeans.parteclasificacion.dbrPARTE
 */
public void setParte(dbrbeans.parteclasificacion.dbrPARTE newParte) {
	parte = newParte;
}
/**
 * Insert the method's description here.
 * Creation date: (11/21/2002 11:20:05 AM)
 * @param newProveedor dbbeans.proveedor.dbPROVEEDOR
 */
public void setProveedor(dbrbeans.proveedorinventario.dbrPROVEEDOR newProveedor) {
	proveedor = newProveedor;
}
}
