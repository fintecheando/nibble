/*
 */
package org.nibble.util;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

//import dori.jasper.engine.JRDataSource;
//import dori.jasper.engine.JRException;
//import dori.jasper.engine.JRField;


/**
 * This class create a DataSource that is used from Jasper Reports
 * 
 */
public class WebAppDataSource implements JRDataSource {
	private static Log logger = LogFactory.getLog(WebAppDataSource.class);
	   
    /** Variable for the index! */
    private int index = -1;

    /** Column Header */
    private String[] dsHeaders;

    /** Data that simulates a Table */
    private Object[][] dataSource;

    /**
     * Creates a new CustomTableModel object.
     *
     * @param headers String[] Headers to the table
     * @param data Matrix Object
     */
    public WebAppDataSource(String[] headers, Object[][] data) {
        this.dataSource = data;
        this.dsHeaders = headers;
    }

    /**
     * Return the next element from DataSource
     *
     * @return Return the next element from DataSource
     *
     * @throws JRException if index is not bound
     */
    public boolean next() throws JRException {
        index++;

        return (index < dataSource.length);
    }

    /**
     * Get Field Object
     *
     * @param field Name of field that you want to retrive
     *
     * @return REturn a object field
     *
     * @throws JRException Only if field not exist
     */
    public Object getFieldValue(JRField field) throws JRException {
        Object value = null;
        int i = 0;

        String fieldName = field.getName();
        logger.debug("Field:"+fieldName);
        for (i = 0; i < dsHeaders.length; i++) {
            if (dsHeaders[i].equals(fieldName)) {
				logger.debug("Header:"+dsHeaders[i]);
                break;
            }
        }
        value = dataSource[index][i];

        return value;
    }
}
