package pva.beans4;


/**
 * Insert the type's description here.
 * Creation date: (10/22/2002 6:35:43 PM)
 * @author:
 */
import dbbeans.cliente.*;


public class Bean42 {
    public java.util.Vector vClientes;
    public String mensaje;

    /**
     * Bean52 constructor comment.
     */
    public Bean42() {
        vClientes = new java.util.Vector();
    }

    /**
     * Insert the method's description here.
     * Creation date: (10/22/2002 6:37:49 PM)
     * @return int
     */
    public int getClientesSize() {
        return vClientes.size();
    }

    /**
     * Insert the method's description here.
     * Creation date: (10/22/2002 6:39:17 PM)
     * @return int
     * @param index int
     */
    public int getId(int index) {
        dbCLIENTE a = (dbCLIENTE) vClientes.elementAt(index);

        return a.getIidnocliente();
    }

    /**
     * Insert the method's description here.
     * Creation date: (10/22/2002 6:39:17 PM)
     * @return int
     * @param index int
     */
    public String getRazonsocial(int index) {
        dbCLIENTE a = (dbCLIENTE) vClientes.elementAt(index);

        return (a.getVchrazonsocial() == null) ? "" : a.getVchrazonsocial();
    }

    /**
     * Insert the method's description here.
     * Creation date: (10/22/2002 6:39:17 PM)
     * @return int
     * @param index int
     */
    public String getRfc(int index) {
        dbCLIENTE a = (dbCLIENTE) vClientes.elementAt(index);

        return (a.getVchrfc() == null) ? "" : a.getVchrfc();
    }

    /**
     * Insert the method's description here.
     * Creation date: (10/30/2002 5:43:07 PM)
     * @return java.util.Vector
     */
    public java.util.Vector getVClientes() {
        return vClientes;
    }

    /**
     * Insert the method's description here.
     * Creation date: (10/30/2002 5:43:07 PM)
     * @param newVClientes java.util.Vector
     */
    public void setVClientes(java.util.Vector newVClientes) {
        vClientes = newVClientes;
    }

    /**
     * @return
     */
    public String getMensaje() {
        return (mensaje == null) ? "" : mensaje;
    }

    /**
     * @param string
     */
    public void setMensaje(String string) {
        mensaje = string;
    }
}
