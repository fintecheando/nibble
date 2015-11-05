/*
 *
 *
 */
package org.nibble.util;

import java.io.InputStream;

import javax.servlet.ServletContext;


/**
 * This class is a wrapper about Servlet Context
 *
 * @author adavalos
 */
public class Contexto {
    /** DOCUMENT ME! */
    private ServletContext context;

    /**
     * Creates a new Contexto object.
     *
     * @param newContext NewContext
     */
    public Contexto(ServletContext newContext) {
        this.context = newContext;
    }

    /**
     * Returns the resource located at the named path as an InputStream object.
     *
     * @param resource Resource to find
     *
     * @return InputStream resource
     */
    public InputStream getResourceAsStream(String resource) {
        InputStream result = null;
        result = context.getResourceAsStream(resource);
        return result;
    }
    
	public String getResourceAsString(String resource) {
		String result = null;
		
		result = context.getRealPath(resource);
		
		return result;
	}

}
