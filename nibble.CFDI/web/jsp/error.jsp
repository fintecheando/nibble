<html>
<head>
 <title>Error en el sistema</title>
 <link href="lib/estilo.css" rel=STYLESHEET type=text/css>
 <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
 <jsp:useBean id="error" class="org.nibble.main.BeanError" scope="request" />
</head>
<body>
<br>
<br>
<div align="center">
  <table width="75%" border="0" bgcolor="#003366">
    <tr>
    <td>
       <table width="100%" border="0">
          <tr> 
            <td align="center" bgcolor="#003366"> 
              <font face="Arial, Helvetica, sans-serif" size="2" color="#FFFFFF">
			   Ha ocurrido la siguiente excepci&oacute;n en el sistema
			  </font>
          </td>
        </tr>
        <tr> 
            <td align ="center" bgcolor="#CCCCCC">
			 <font face="Arial, Helvetica, sans-serif" size="2">
			 <%=error.getErrorMessage()%>
			 </font>
			</td>
        </tr>
      </table>
    </td>
  </tr>
</table>
<table align="center" width="75%">
	<tr><td><a href="javascript:history.go(-1);" class="ligas"><< Regresar</a></td></tr>
</table>

</div>
</body>
</html>