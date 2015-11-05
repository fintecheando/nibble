package org.nibble.util;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
 
public class CapturadorArchivos {

    
  /*
   * Constructor, recibe los siguientes par�metros:
   *
   * String s       --> Una cadena de caracteres con los campos del formulario 
   *                    separados por espacios en blanco para crear un StringTokenizer 
   * String path    --> El path hasta el directorio donde se guardaron los archivos recibidos
   * PrintWriter po --> El escribidor en la web
   *
   * IMPORTANTE: Ninguno de los campos del formulario que se env�a al servlet se puede llamar 
   *             'filename'
   */
  public CapturadorArchivos() {

  }
  public void capturarArchivo(HttpServletRequest request, String path, int tam)throws Exception{

	int indexBoundary =request.getContentType().indexOf("boundary=");
	
    try {
      if (indexBoundary < 0) 
        throw new Exception("String 'boundary=' not found in request.getContentType");
    }catch (Exception ex) {
	  throw (ex);
    }
    
    String boundary = request.getContentType().substring(indexBoundary+9);
	
    try{

      ServletInputStream sis =request.getInputStream();
      String inputLine;
      int length;
      int iteracion=0;
      byte tmpbuffer[] = new byte[8192];
    
      while (true){
        iteracion++;
        length = sis.readLine(tmpbuffer, 0, tmpbuffer.length);    
        if(length < 0) throw new Exception("Data not received correctly");
        inputLine = new String(tmpbuffer, 0, length);
        //Situamos el puntero en posici�n de lectura del archivo
        if(inputLine.indexOf("filename") > 0) {        
          if((length = sis.readLine(tmpbuffer, 0, tmpbuffer.length)) < 0)
            throw new Exception("Data not received correctly");          
          inputLine = new String(tmpbuffer, 0, length);
          break;
		 }
      }
	    
      //Se establece el tama�o m�ximo del archivo
      byte fileBytes[] = new byte[tam];

      //Leemos la primera linea del archivo
      if (inputLine.indexOf("Content") >= 0){
        
        length = sis.readLine(tmpbuffer, 0, tmpbuffer.length);
        if(length < 0)throw new Exception("Data not received correctly");
        inputLine = new String(tmpbuffer, 0, length);
      }

      length = sis.readLine(tmpbuffer, 0, tmpbuffer.length);
      if(length < 0) throw new Exception("Data not received correctly");
      inputLine = new String(tmpbuffer, 0, length);
      int offset = 0;

      while(inputLine.indexOf(boundary) < 0) {
        System.arraycopy(tmpbuffer, 0, fileBytes, offset, length);
        offset += length;
        length = sis.readLine(tmpbuffer, 0, tmpbuffer.length);
        if(length < 0)
          throw new Exception("Data not received correctly");
        if ( (offset+length)>tam)
          throw new Exception("The file is bigger than allowed "+ tam+"bytes");
        inputLine = new String(tmpbuffer, 0, length);
      }

      RandomAccessFile raf = new RandomAccessFile(path, "rw");
      raf.write(fileBytes, 0, offset-2);
      raf.close();
    }catch (Exception ex){
      throw (ex);
    }
  }
  public String[] obtenerValores (String s, HttpServletRequest request)throws Exception {

	StringTokenizer campos = new StringTokenizer (s, " ");
    String valores[] = new String[campos.countTokens()];

    /*
     * Comprobamos que el m�todo de petici�n es POST
     * y que es una petici�n multipart/form-data
     */
    try {
      if (!(request.getMethod().equals("POST") && request.getContentType().startsWith("multipart/form-data")))
        throw new Exception("El m�todo no es POST o multipart/form-Data");

      int indexBoundary = request.getContentType().indexOf("boundary=");
	    
      if (indexBoundary < 0) 
        throw new Exception("No se ha encontrado la cadena 'boundary=' en request.getContentType");
	    
      String boundary = request.getContentType().substring(indexBoundary+9);
      String inputLine, campo;    
      ServletInputStream sis = request.getInputStream();
      byte tmpbuffer[] = new byte[8192];
      int length;    
      int numeroDatos = campos.countTokens();

	    
      for(int i = 0; i < numeroDatos;i++) {
        //linea del boundary
        length = sis.readLine(tmpbuffer, 0, tmpbuffer.length);
        inputLine = new String(tmpbuffer, 0, length);
		
        if(inputLine.indexOf(boundary) < 0)throw new Exception("No se ha encontrado el boundary en la iteraci�n"+i);
	
        length = sis.readLine(tmpbuffer, 0, tmpbuffer.length);
        inputLine=new String(tmpbuffer, 0, length);

        if(inputLine.indexOf("filename") > 0)return valores;

        campo = new String(campos.nextElement().toString());
				
        //Leemos la siguiente linea, que ser� un retorno de carro
        length = sis.readLine(tmpbuffer, 0, tmpbuffer.length);
        if(length < 0)
          throw new Exception("No se encuentra el retorno de carro");
		
        length = sis.readLine(tmpbuffer, 0, tmpbuffer.length);
        inputLine=new String(tmpbuffer, 0, length);
	    
        inputLine = inputLine.substring(0,inputLine.length()-2);
        if(length<0)throw new Exception("Faltan datos, transmisi�n defectuosa");
    
        valores[i] = new String(inputLine);
      }
      
    }catch (Exception ex) {    
      throw(ex); 
    }
    return valores;
  }
}
