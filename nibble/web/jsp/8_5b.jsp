<html>
<jsp:useBean id="bean83b" class="pva.beans8.Bean83b" scope="session"/>

<head>
<title>PVA 8.5 Registrar Histórico de consumos</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link href="lib/estilo.css" rel=STYLESHEET type=text/css>
<script type="text/javascript" src="lib/util.js"></script>
<script type="text/javascript">
function buscar_onclick(form){
	form.accion.value=3;
	return true;
}
</script>
</head>
<%@ include file="../main.jsp" %>
<body bgcolor="#FFFFFF" text="#000000">
<form name=frm action="RegistroConsumo" method="post">
<input type=hidden name="accion">
<table class="tablatext" width=750 align="center" cellpadding="0" cellspacing="0">
<tr>
	<td><img src="images/tpva8_5.GIF"></td>

        <th>
	<td><a href="../pvatx/hlp/pva8.0-8.5.htm#registrar" target="Ayuda Registrar Históricos de consumo">Ayuda</a></td>
        </th>
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
  	  <td width="20%">No. de parte</td>
	  <td width="40%">Proveedor</td>
	  <td width="20%">Histórico de consumo</td>
	  <td width="20%">Nuevo Histórico de consumo</td>
	<% String stilo;
	for (int i = 0; i < bean83b.getSize(); i++) {
	 	if (i % 2 == 0)stilo = "itemStyle";
		else stilo = "alternatingItemStyle"; 
	%>
    <tr class="<%=stilo%>">
 	<td width="20%"><%= bean83b.getVchnumparte(i) %></td>
    <td width="40%"><%= bean83b.getVchrazonsocial(i) %></td>
	<td width="20%" align="right"><%= bean83b.getIcantconsumo(i) %></td>
	<td width="20%" align="right"><input type=text name='consumo<%= i%>' size=10 maxlength=10 class=numero  onkeypress="return numbersonly()"></td>
   </tr>
<%
}
%> 
</table>
<br>
<table align="center" border="1" bordercolor="#364859" rules="cols" cellspacing="0" width="750" style="border-color:Black;border-width:1px;border-style:solid;font-family: arial; font-size: 11px;border-collapse:collapse;" class="tablatext">
<tr class="titStyle"> 	
		<td align=center><input type="submit" value="Modificar" class=body onclick="return buscar_onclick(this.form)">
	</td>
</tr>
</table>
<br>
<table align="center" width="750">
	<tr><td><a href="PreRegistroConsumo" class="ligas"><< Regresar</a></td></tr>
</table>
<input type="hidden" name=iidcategoria value="<%= request.getParameter("iidcategoria") %>">
<input type="hidden" name=iidgrupo value="<%= request.getParameter("iidgrupo") %>">
<input type="hidden" name=iidlinea value="<%= request.getParameter("iidlinea") %>">
<input type="hidden" name=iidmarca value="<%= request.getParameter("iidmarca") %>">
<input type=hidden name="vchcategoria" value="<%= request.getParameter("vchcategoria") %>">
<input type=hidden name="vchgrupo" value="<%= request.getParameter("vchgrupo") %>">
<input type=hidden name="vchlinea" value="<%= request.getParameter("vchlinea") %>">
<input type=hidden name="vchmarca" value="<%= request.getParameter("vchmarca") %>">

</form>
</html>
