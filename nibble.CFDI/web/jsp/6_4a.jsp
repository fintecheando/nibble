<html>
<jsp:useBean id="bean64" class="org.nibble.service.vo.partes.Bean64" scope="session"/>

<head>
<title>NC 6.4 Cambio Precios</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link href="lib/estilo.css" rel=STYLESHEET type=text/css>
<script language=JavaScript src="lib/util.js"></script>
<script language=JavaScript>

function modificar_onclick(){
	document.frm.accion.value=2;
	return true;
}
</script>
</head>
<%@ include file="../main.jsp" %>
<body bgcolor="#FFFFFF" text="#000000">
<form name=frm action="PartesPrecioB100" method="post">
<input type="hidden" name=accion>
<table class="tablatext" width=750 align="center" cellpadding="0" cellspacing="0">
<tr>
	<td><img src="images/tnc6_4.gif"></td>
</tr>
</table>
<br>
<table align="center" border="1" bordercolor="#364859" rules="cols" cellspacing="0" width="750" style="border-color:Black;border-width:1px;border-style:solid;font-family: arial; font-size: 11px;border-collapse:collapse;" class="tablatext">
	<tr>
			<td colspan=4>Clasificación</td>
	</tr>
    <tr class="titStyle">
	    	<td width="25%">Categoría</td>
			<td width="25%">Grupo</td>
			<td width="25%">Línea</td>
			<td width="25%">Marca</td>
	</tr>
    <tr class="itemStyle">
			<td><%= request.getParameter("vchcategoria") %></td>
			<td><%= request.getParameter("vchgrupo") %></td>
			<td><%= request.getParameter("vchlinea") %></td>
			<td><%= request.getParameter("vchmarca") %></td>
    </tr>
</table>
<br>
<table align="center" border="1" bordercolor="#364859" rules="cols" cellspacing="0" width="750" style="border-color:Black;border-width:1px;border-style:solid;font-family: arial; font-size: 11px;border-collapse:collapse;" class="tablatext">
<tr class="titStyle">
	<td width="33%">Número de parte</td>
	<td width="33%">Precio Base 100</td>
	<td width="34%">Nuevo Precio Base 100</td>
 </tr>
<% String stilo;
	for (int i = 0; i < bean64.getPartesSize(); i++) {
	 	if (i % 2 == 0)stilo = "itemStyle";
		else stilo = "alternatingItemStyle"; 
	%>
    <tr class="<%=stilo%>">
	<td width="33%"><%= bean64.getVchnumparte(i)%></td>
	<td width="33%" align="right"><%= bean64.getDecpreciob100STR(i)%></td>
	<td width="34%"><input type=text size=10 class="numero" name="T<%= bean64.getLiidparte(i)%>" onkeypress="return numbersonly()"></td>
   </tr>
<%
}
%> 
</table>
<br>
<table align="center" border="1" bordercolor="#364859" rules="cols" cellspacing="0" width="750" style="border-color:Black;border-width:1px;border-style:solid;font-family: arial; font-size: 11px;border-collapse:collapse;" class="tablatext">
<tr class="titStyle">
	<td align=center><input type="submit" value="Modificar" class=body onclick="return modificar_onclick()" <%if (bean64.getPartesSize()==0){%>DISABLED<%}%>></td>
</tr>
</table>
<br>
<table align="center" width="750">
	<tr><td><a href="PreParteCambiosB100" class="ligas"><< Regresar</a></td></tr>
</table>
<input type="hidden" name=iidcategoria value="<%= request.getParameter("iidcategoria") %>">
<input type="hidden" name=iidgrupo value="<%= request.getParameter("iidgrupo") %>">
<input type="hidden" name=iidlinea value="<%= request.getParameter("iidlinea") %>">
<input type="hidden" name=iidmarca value="<%= request.getParameter("iidmarca") %>">
<input type="hidden" name=prefijo value="<%= request.getParameter("prefijo") %>">
<input type="hidden" name=numero value="<%= request.getParameter("numero") %>">
<input type="hidden" name=sufijo value="<%= request.getParameter("sufijo") %>">
<input type="hidden" name=medida value="<%= request.getParameter("medida") %>">
<input type=hidden name="vchcategoria" value="<%= request.getParameter("vchcategoria") %>">
<input type=hidden name="vchgrupo" value="<%= request.getParameter("vchgrupo") %>">
<input type=hidden name="vchlinea" value="<%= request.getParameter("vchlinea") %>">
<input type=hidden name="vchmarca" value="<%= request.getParameter("vchmarca") %>">
</form>
</body>
</html>
