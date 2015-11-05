<html>
<jsp:useBean id="vBean71his" class="java.util.Vector" scope="request"/>
<%@page import="org.nibble.service.vo.pedidos.Bean71his,java.util.Vector"%>
<head>
<title>NC 7.1his Historico</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link href="lib/estilo.css" rel=STYLESHEET type=text/css>
<script language=JavaScript>

function cerrar_onclick(){
 	window.close()
}

</script>
</head>

<body bgcolor="#FFFFFF" text="#000000" >
<form name=frm >

<br>
<table align="center" border="1" bordercolor="#364859" rules="cols" cellspacing="0" width="750" style="border-color:Black;border-width:1px;border-style:solid;font-family: arial; font-size: 11px;border-collapse:collapse;" class="tablatext">
<tr>
	<td colspan=10>Historico</td>
</tr>
<tr class="titStyle"> 
  	  
	  <td width="10%">Id Parte</td>
	  <td width="20%">Proveedor</td>
	  <td width="10%">Mes 1</td>
	  <td width="10%">Mes 2</td>
	  <td width="10%">Mes 3</td>
	  <td width="10%">Mes 4</td>
	  <td width="10%">Mes 5</td>
	  <td width="10%">Mes 6</td>
	  <td width="10%">Total</td>
 </tr>
<% String stilo;
   int total;
	for (int i = 0; i < vBean71his.size(); i++) {
		Bean71his bean71his = (Bean71his) vBean71his.elementAt (i);
	 	if (i % 2 == 0)stilo = "itemStyle";
		else stilo = "alternatingItemStyle"; 
%>
	<tr class="<%=stilo%>">
 
      <td width="10%"><%= bean71his.getLiidparte() %></td>
	  <td width="20%"><%= bean71his.getVchNombre()%></td>
	<% Vector vConsumo= bean71his.getConsumo();
		total = 0;
		for (int j=0;j<vConsumo.size();j++){
		    total += ((Integer)vConsumo.elementAt(j)).intValue();
	%>

	  <td width="10%"><%= vConsumo.elementAt(j)%></td>
	<%}	
	  
	%>
	<td width="10%"><%= total%></td>
   </tr>
<%
}
%> 
</table>
<br>
<table align="center" border="1" bordercolor="#364859" rules="cols" cellspacing="0" width="750" style="border-color:Black;border-width:1px;border-style:solid;font-family: arial; font-size: 11px;border-collapse:collapse;" class="tablatext">
	<tr class="titStyle">
		<td align=center><input type="button" value="Cerrar" class=body onClick="cerrar_onclick();">
      </td>
	</tr>
</table>
<br>


</form>
</body>
</html>

