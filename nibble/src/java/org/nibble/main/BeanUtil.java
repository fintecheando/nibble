/* 
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package org.nibble.main;

import java.sql.Connection;
import java.util.HashMap;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import javax.servlet.http.HttpSession;

import javax.sql.DataSource;

import javax.transaction.UserTransaction;
import org.apache.log4j.Logger;
import org.nibble.security.login.Login;


/**
 * @author jcanibe
 *
 * To change the template for this generated type comment go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class BeanUtil {
    /** commit */
    private boolean commit = true;

    /** conn */
    private Connection conn = null;

    /** ctx */
    private static Context ctx = null;
    
	/** DATA_SOURCE_NAME */ 
	private static final String DATA_SOURCE_NAME = "/env/jdbc/nibble";
	
	/** DATA_SOURCE_NAME */
	private static HashMap dataSources = new HashMap();

    /** utx */
    private UserTransaction utx = null;

    /** seguridad */
    private BeanSeguridad seguridad = null;

    /** session*/
    private HttpSession session = null;

    /** id_usuario*/
    private int id_usuario;

    /** grupo*/
    protected int grupo;

    /** sistema*/
    protected int sistema;

    /** address*/
    private String address;

    static Logger logger= Logger.getLogger(BeanUtil.class);
    
    /**
     * Creates a new BeanUtil object.
     */
    public BeanUtil() 
    {
        try 
        {
            if (ctx == null) 
            {
                try 
                {
                    Context initContext = new InitialContext();
                    Context envContext = (Context) initContext.lookup("java:comp");
                    ctx = envContext;
		} 
                catch (NamingException ne) 
                {
                    logger.debug("Error Getting Initial Context: <" + ne + ">");
                    throw new Exception(ne.toString());
		}
            }
            if (dataSources.get(DATA_SOURCE_NAME) == null) 
            {
                try 
                {
                    dataSources.put(DATA_SOURCE_NAME, ctx.lookup(DATA_SOURCE_NAME));
		} 
                catch (NamingException ne) 
                {
                    logger.debug("Error Getting DataSource: <" + ne + ">");
                    throw new Exception(ne.toString());
		}
            }
            utx = (UserTransaction) ctx.lookup("UserTransaction");            
            utx.begin();
        } 
        catch (Exception e) 
        {
            logger.error("[BeanUtil:Error]:" + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Obtiene las variables de sesi�n, si la sesi�n es nula
     * o no esta alguno de los atributos en esta regresa un valor false
     *
     * @return boolean
     */
    public boolean getSessionValues() {
        boolean result = false;

        if (session != null) {
            result = true;

            if (session.getAttribute("iidusuario") != null) {
                id_usuario = new Integer(session.getAttribute("iidusuario").toString()).intValue();
            }

            if (session.getAttribute("grupo") != null) {
                grupo = new Integer(session.getAttribute("grupo").toString()).intValue();
            }

            if (session.getAttribute("sistema") != null) {
                sistema = new Integer(session.getAttribute("sistema").toString()).intValue();
            }

            if ((sistema == 0) || (grupo == 0) || (id_usuario == 0)) {
                result = false;
            }
        }

        return result;
    }

    /**
     * DOCUMENT ME!
     *
     * @param idModulo DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public boolean getAcceso(int idModulo, Connection con) {
        boolean result = false;

        if (con != null) {
            seguridad = new BeanSeguridad();
            seguridad.setConnection(con);

            if (seguridad.tienePermiso(sistema, grupo, idModulo)) {
                result = true;
            }
        }

        return result;
    }

    /**
     * @return conn
     */
    public Connection getConn() {
		java.sql.Connection conn = null;

		try {
			conn = ((DataSource) dataSources.get(DATA_SOURCE_NAME)).getConnection();
            logger.debug("[CONN ID]="+conn.toString()+"[ISOPEN]");
		} catch (Exception e) {
            logger.error("ERROR AL OBTENER LA CONEXION"+e);
		}

		return conn;
    }

    /**
     * @param session
     */
    public void setSession(HttpSession session) {
        this.session = session;
    }

    /**
     * @return grupo
     */
    public int getGrupo() {
        return grupo;
    }

    /**
     * @return id_usuario
     */
    public int getId_usuario() {
        return id_usuario;
    }

    /**
     * @return sistema
     */
    public int getSistema() {
        return sistema;
    }

    /**
     * @param string
     */
    public void setAddress(String string) {
        address = string;
    }

    /**
     * @return commit
     */
    public boolean isCommit() {
        return commit;
    }

    /**
     * @param b
     */
    public void setCommit(boolean b) {
        commit = b;
    }

	/**
	 * @param utx
	 * @param con
	 */
	public void finalizeTransaction(java.sql.Connection localcon) {
		try{
            String idconn= localcon.toString();
			if (isCommit()){                
				utx.commit();	
			}else{                
				utx.rollback();
			}
			if (localcon != null){            
				localcon.close();
			}
			logger.debug("[CONN ID]="+idconn+"[ISCLOSED]="+localcon.isClosed());
		}catch(Exception e){
			logger.error("[ERROR][finalizeTransaction]:"+e.getMessage());                        
		}		
	}
	
}
