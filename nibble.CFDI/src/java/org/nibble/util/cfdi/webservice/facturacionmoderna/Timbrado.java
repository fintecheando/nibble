/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.nibble.util.cfdi.webservice.facturacionmoderna;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import org.apache.log4j.Logger;

import wsconnectionfm.WSConecFM;

/**
 *
 * @author victor
 */
public class Timbrado {
    
  static Logger logger= Logger.getLogger(Timbrado.class);

  private String rfcEmisor;
  private String pathPreFactura;
  private String pathFactura;
  private Boolean generarPDF;
  private Boolean generarCBB;
  private Boolean generarTXT;
  
  public void getTimbrado() throws IOException {
    // TODO code application logic here
    WSConecFM connect = new WSConecFM();
    
    
    /* Configurar las variables de conexion
     * puede cambiar:
     * 1.- Url de conexion con soap
     * 2.- Usuario de conexion
     * 3.- Contraseña de Usuario
     * 4.- Indicar si desea generar el pdf
     * 5.- Indicar si desea generar el txt
     * 6.- Indicar si desea genera el cbb (Activarlo, desactiva generar pdf)
     */
    connect.setRfcEmisor(this.rfcEmisor);
    //String pathdir = System.getProperty("user.dir");

    /* EL parametro enviado al metodo timbrar puede ser:
     * un archivo xml, un layot txt o un string que contenga el xml
     */
    //String r = connect.timbrar(pathdir+"/ejemplos/factura_A_70.xml");
    String r = connect.timbrar(this.pathPreFactura);

    if (connect.error) {
      logger.error(r);
    }
    else {
      String xml = new String(new sun.misc.BASE64Decoder().decodeBuffer(connect.strXml));
      try {
          File archivo = new File(this.pathFactura +".xml");
          FileWriter escribir = new FileWriter(archivo);
          escribir.write(xml);
          escribir.close();
        }
        catch(Exception e) {
          logger.error(e.getMessage());
      }
      if (this.generarPDF) {
        OutputStream out;
        try {
          byte[] b = new sun.misc.BASE64Decoder().decodeBuffer(connect.strPdf);
          out = new FileOutputStream(this.pathFactura +".pdf");
          out.write(b, 0, b.length);
          out.close();
        }catch (Exception e) {
          logger.error(e.getMessage());
        }
      }
      if (this.generarCBB) {
        OutputStream out;
        try {
          byte[] b = new sun.misc.BASE64Decoder().decodeBuffer(connect.strCbb);
          out = new FileOutputStream(this.pathFactura +".png");
          out.write(b, 0, b.length);
          out.close();
        }catch (Exception e) {
          logger.error(e.getMessage());
        }
      }
      if (this.generarTXT) {
        String txt = new String(new sun.misc.BASE64Decoder().decodeBuffer(connect.strTxt));
        try {
          File archivo = new File(this.pathFactura +".txt");
          FileWriter escribir = new FileWriter(archivo);
          escribir.write(txt);
          escribir.close();
        }
        catch(Exception e) {
          logger.error(e.getMessage());
        }
      }
      logger.error(r);
      logger.debug("El comprobante lo encuentra en: "+this.pathFactura );
    }
  }

    /**
     * @return the rfcEmisor
     */
    public String getRfcEmisor() {
        return rfcEmisor;
    }

    /**
     * @param rfcEmisor the rfcEmisor to set
     */
    public void setRfcEmisor(String rfcEmisor) {
        this.rfcEmisor = rfcEmisor;
    }

    /**
     * @return the pathPreFactura
     */
    public String getPathPreFactura() {
        return pathPreFactura;
    }

    /**
     * @param pathPreFactura the pathPreFactura to set
     */
    public void setPathPreFactura(String pathPreFactura) {
        this.pathPreFactura = pathPreFactura;
    }

    /**
     * @return the pathFactura
     */
    public String getPathFactura() {
        return pathFactura;
    }

    /**
     * @param pathFactura the pathFactura to set
     */
    public void setPathFactura(String pathFactura) {
        this.pathFactura = pathFactura;
    }

    /**
     * @return the generarPDF
     */
    public Boolean getGenerarPDF() {
        return generarPDF;
    }

    /**
     * @param generarPDF the generarPDF to set
     */
    public void setGenerarPDF(Boolean generarPDF) {
        this.generarPDF = generarPDF;
    }

    /**
     * @return the generarCBB
     */
    public Boolean getGenerarCBB() {
        return generarCBB;
    }

    /**
     * @param generarCBB the generarCBB to set
     */
    public void setGenerarCBB(Boolean generarCBB) {
        this.generarCBB = generarCBB;
    }

    /**
     * @return the generarTXT
     */
    public Boolean getGenerarTXT() {
        return generarTXT;
    }

    /**
     * @param generarTXT the generarTXT to set
     */
    public void setGenerarTXT(Boolean generarTXT) {
        this.generarTXT = generarTXT;
    }
}
