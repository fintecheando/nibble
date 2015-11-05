package util.upload;

import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
 
public class CapturadorArchivos {

    
  /*
   * Constructor, recibe los siguientes parametros:
   *
   * String s       --> Una cadena de caracteres con los campos del formulario 
   *                    separados por espacios en blanco para crear un StringTokenizer 
   * String path    --> El path hasta el directorio donde se guardaron los archivos recibidos
   * PrintWriter po --> El escribidor en la web
   *
   * IMPORTANTE: Ninguno de los campos del formulario que se envia al servlet se puede llamar
   *             'filename'
   */
  public CapturadorArchivos() {

  }
  public byte[] capturarArchivo(HttpServletRequest request, int tam)throws Exception{

	int indexBoundary =request.getContentType().indexOf("boundary=");

	
  
	
    try {
      if (indexBoundary < 0) 
        throw new Exception("No se ha encontrado la cadena 'boundary=' en request.getContentType");
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
        if(length < 0) throw new Exception("No se han recibido los datos correctamente");
        inputLine = new String(tmpbuffer, 0, length);
        //Situamos el puntero en posicion de lectura del archivo
        if(inputLine.indexOf("filename") > 0) {        
          if((length = sis.readLine(tmpbuffer, 0, tmpbuffer.length)) < 0)
            throw new Exception("No se han recibido los datos correctamente");          
          inputLine = new String(tmpbuffer, 0, length);
          break;
		 }
      }
	    
      //Se establece el tamaño maximo del archivo
      byte fileBytes[] = new byte[tam];

      //Leemos la primera linea del archivo
      if (inputLine.indexOf("Content") >= 0){
        
        length = sis.readLine(tmpbuffer, 0, tmpbuffer.length);
        if(length < 0)throw new Exception("No se han recibido los datos correctamente");
        inputLine = new String(tmpbuffer, 0, length);
      }

      length = sis.readLine(tmpbuffer, 0, tmpbuffer.length);
      if(length < 0) throw new Exception("No se han recibido los datos correctamente");
      inputLine = new String(tmpbuffer, 0, length);
      int offset = 0;

      while(inputLine.indexOf(boundary) < 0) {
        System.arraycopy(tmpbuffer, 0, fileBytes, offset, length);
        offset += length;
        length = sis.readLine(tmpbuffer, 0, tmpbuffer.length);
        if(length < 0)
          throw new Exception("No se han recibido los datos correctamente ");
        if ( (offset+length)>tam)
          throw new Exception("El archivo que usted intenta mandar es mas grande de lo permitido"+
 							  "No se pueden enviar archivos mayores de "+tam+"bytes");
        inputLine = new String(tmpbuffer, 0, length);
      }
      return fileBytes;

//      RandomAccessFile raf = new RandomAccessFile(path, "rw");
//      raf.write(fileBytes, 0, offset-2);
//      raf.close();
    }catch (Exception ex){
      throw (ex);
    }
  }
  public String[] obtenerValores (String s, HttpServletRequest request)throws Exception {

	StringTokenizer campos = new StringTokenizer (s, " ");
    String valores[] = new String[campos.countTokens()];

    /*
     * Comprobamos que el metodo de peticion es POST
     * y que es una peticion multipart/form-data
     */
    try {
      if (!(request.getMethod().equals("POST") && request.getContentType().startsWith("multipart/form-data")))
        throw new Exception("El metodo no es POST o multipart/form-Data");

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
		
        if(inputLine.indexOf(boundary) < 0)throw new Exception("No se ha encontrado el boundary en la iteracion"+i);
	
        length = sis.readLine(tmpbuffer, 0, tmpbuffer.length);
        inputLine=new String(tmpbuffer, 0, length);

        if(inputLine.indexOf("filename") > 0)return valores;

        campo = new String(campos.nextElement().toString());
				
        //Leemos la siguiente linea, que sera un retorno de carro
        length = sis.readLine(tmpbuffer, 0, tmpbuffer.length);
        if(length < 0)
          throw new Exception("No se encuentra el retorno de carro");
		
        length = sis.readLine(tmpbuffer, 0, tmpbuffer.length);
        inputLine=new String(tmpbuffer, 0, length);
	    
        inputLine = inputLine.substring(0,inputLine.length()-2);
        if(length<0)throw new Exception("Faltan datos, transmision defectuosa");
    
        valores[i] = new String(inputLine);
      }
      
    }catch (Exception ex) {    
      throw(ex); 
    }
    return valores;
  }
}
