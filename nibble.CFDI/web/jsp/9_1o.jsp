<html>
<jsp:useBean id="bean91" class="pva.beans9.Bean91" scope="session"/>
<head>
<title>PVA 9.1 Facturar</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link href="../lib/estilo.css" rel=STYLESHEET type=text/css>
</head>
<body>
<table class="tablatext" width=550 align="center" cellpadding="0" cellspacing="0">
<tr>
	<td><img src="../images/tpva9_1.GIF"></td>

        <th>
	<td><a href="../pvatx/hlp/pva9.0-9.1.htm" target="Ayuda Facturar">Ayuda</a></td>
        </th>

</tr>
</table>
<br>
<table align="center" border="1" bordercolor="#364859" rules="cols" cellspacing="0" width="550" style="border-color:Black;border-width:1px;border-style:solid;font-family: arial; font-size: 11px;border-collapse:collapse;" class="tablatext">
<tr class="titStyle">
	    	<td width="50%">Precio de lista</td>
			<td width="50%" align="right"><%= bean91.getDecpreciob100STR() %></td>
		</tr>
</table>
<br>
<table align="center" border="1" bordercolor="#364859" rules="cols" cellspacing="0" width="550" style="border-color:Black;border-width:1px;border-style:solid;font-family: arial; font-size: 11px;border-collapse:collapse;" class="tablatext">
<tr class="titStyle">
	    	<td width="50%">Proveedor</td>
			<td width="50%">Costo</td>
		</tr>
	<% String stilo;
	for (int i = 0; i < bean91.getProveeSize(); i++) {
	 	if (i % 2 == 0)stilo = "itemStyle";
		else stilo = "alternatingItemStyle"; 
	%>
	<tr class="<%=stilo%>">
 	<td width="50%"><%= bean91.getProveeVchnombre(i) %></td>
	<td width="50%" align="right"><%= bean91.getProveeDecprecioSTR(i) %></td>    
	</tr>
	<% } %>
</table>
<br>
</body>
</html>
