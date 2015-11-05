<html>
<jsp:useBean id="bean101" class="pva.beans10.Bean101" scope="session"/>
<head>
	<title>PVA 10.1.1 Historial del Documento</title>
	<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
	<link href="lib/estilo.css" rel=STYLESHEET type=text/css>
</head>
<body>
	<table width="100%" align="center" border="1" bordercolor="#364859" rules="cols" cellspacing="0" width="750" style="border-color:Black;border-width:1px;border-style:solid;font-family: arial; font-size: 11px;border-collapse:collapse;" class="tablatext">
		<tr class="headerStyle">
			<td>Documento</td>
		  	<td>Referencia</td>
		  	<td>Emisión</td>			
		  	<td>Vencimiento</td>
		  	<td>Importe</td>
		  	<td>Estado</td>
		</tr>	
		<% String stilo;
		   for (int i = 0; i < bean101.sizeCxc(); i++) {
	 		 if (i % 2 == 0)stilo = "itemStyle";
			 else stilo = "alternatingItemStyle"; 
		 %>
			<tr class="<%=stilo%>">
			  <td><%=bean101.getVchDestipodocto(i)%> <%=bean101.getIiddoctoorigen(i)%></td>
			  <td><%if (bean101.getIidcontradocto(i)>0){%> <%=bean101.getIidcontradocto(i)%> <%}%></td>
			  <td><%=bean101.getTisfechamovimiento(i)%></td>	
			  <td><%=bean101.getTisfechavencimient(i)%></td>
			  <td align="right"><%if (bean101.getIidcontradocto(i)>0){%><%=bean101.getDecsaldo(i)%><%}else{%><%=bean101.getDecdebe(i)%><%}%></td>
			  <td align="right"><%if (bean101.getIidcontradocto(i)==0){if (bean101.isVencido(i)){%>VENCIDO<%}else{%>X VENCER<%}}%></td>
			</tr>
		<%}%>
	</table>
</body>
</html>
