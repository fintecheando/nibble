<html>
<jsp:useBean id="bean102" class="pva.beans10.Bean102" scope="session"/>
<jsp:useBean id="bean91" class="pva.beans9.Bean91" scope="session"/>
<jsp:useBean id="bean42" class="pva.beans4.Bean42" scope="session"/>
<jsp:useBean id="bean117" class="pva.beans11.Bean117" scope="session"/>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link href="lib/estilo.css" rel=STYLESHEET type=text/css>
<title>Vale de Caja</title>
</head>
<body>

<table align=center border=1 borderColor=#364859 cellSpacing=0 class=tablatext rules=cols style="BORDER-BOTTOM: black 1px solid; BORDER-COLLAPSE: collapse; BORDER-LEFT: black 1px solid; BORDER-RIGHT: black 1px solid; BORDER-TOP: black 1px solid; FONT-FAMILY: arial; FONT-SIZE: 11px" width=580>
    <TR> 
      <TD colSpan=3 align=center>Vale de Caja</TD>
    </TR>
    
    <TR class="titStyle"> 
      <TD width="50%">Datos del Cliente</TD>
      <TD width="30%"></TD>
      <TD width="20%">No. de Vale: <%=bean117.getIdvale()%></TD>
    </TR>
    
    <TR> 
      <TD width="50%"> 
      <p>Razon Social: <%= bean42.getRazonsocial((bean117.getIdCliente())-1) %></p>
      <p>RFC: <%= bean42.getRfc((bean117.getIdCliente())-1)%></p>
      </TD>
      <TD width="30%"> 
      </TD>
      <TD width="20%"> 
      <p>Fecha: <%=bean117.getFecha()%></p>
      </TD>
    </TR>
    
    <TR class="titStyle"> 
      <TD width="50%"> 
      <p>Numero de Cliente </p>
      </TD>
      <TD colSpan=3 align=center>
      <p>Importe<p> 
      </TD>
    </TR>
    
    <TR> 
      <TD width="50%"> 
      	<p><%=bean117.getIdCliente()%></p>
      </TD>
	  <TD colSpan=3 align=center>
      	<p><%=bean117.getImporteVale()%></p>
      </TD>
    </TR>    
    
    <TR class="titStyle"> 
      <TD width="50%"> 
      <p>Usuario </p>
      </TD>
      <TD colSpan=3 align=center>
      <p>Firma del Cliente<p> 
      </TD>
    </TR>

    <TR> 
      <TD width="50%"> 
      <%= bean91.getUsuario() %>
      </TD>      
      <TD colSpan=3 align=center>
    	<BR><BR>
      </TD>
    </TR>    

  	<tr class="titStyle">
		<td colspan="6" align="center">
		     <input type="button" class="body" value="Imprimir Vale" onClick="javascript:window.print();">
	         <input type="button" class="body" value="Cerrar Ventana" onClick="javascript:window.close();">
		</td>
	</tr>
	
  </TABLE>  
  
</body>
</html>