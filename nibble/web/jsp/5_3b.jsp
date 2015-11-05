<html>
<jsp:useBean id="bean52" class="org.nibble.service.vo.proveedores.Bean52" scope="session"/>
<jsp:useBean id="bean52a" class="org.nibble.service.vo.proveedores.Bean52" scope="session"/>

<head>
<title>NC 5.3 Asociación de Proveedores</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link href="lib/estilo.css" rel=STYLESHEET type=text/css>
<script language=JavaScript>

function asociar_onclick(form){
	sel = 0;
	for (i=0;i<form.elements.length;i++)
		if (form.elements[i].name.substring(0,1)=="C")
			if (form.elements[i].checked)
				sel ++;
		
	if (sel==0){
		alert ('Debe marcar al menos un proveedor')
		return false;
	}
	form.accion.value=3;
	return true;
}
</script>
</head>
<%@ include file="../main.jsp" %>
<body bgcolor="#FFFFFF" text="#000000">
<form name=frm action="AsociacionProveedorClas" method="post">
<input type="hidden" name=accion>
<table class="tablatext" width=750 align="center" cellpadding="0" cellspacing="0">
<tr>
	<td><img src="images/tnc5_3.GIF"></td>

        <th>
	<td><a href="../nctx/hlp/nc5.0-5.3.htm#asociar" target="Ayuda Asociación de Proveedores">Ayuda</a></td>
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
<tr>
	<td colspan=2>Asociados</td>
</tr>
<tr class="titStyle">
	<td width="20%">Id</td>
	<td width="80%">Raz&oacute;n Social</td>
</tr>
<% String stilo;
	for (int i = 0; i < bean52.getProveedoresSize(); i++) {
	 	if (i % 2 == 0)stilo = "itemStyle";
		else stilo = "alternatingItemStyle"; 
	%>
    <tr class="<%=stilo%>">
    <td width="20%"><%= bean52.getId(i) %></td>
	<td width="80%"><%= bean52.getRazonsocial(i) %></td>
   </tr>
<%}%> 
</table>
<br>
<table align="center" border="1" bordercolor="#364859" rules="cols" cellspacing="0" width="750" style="border-color:Black;border-width:1px;border-style:solid;font-family: arial; font-size: 11px;border-collapse:collapse;" class="tablatext">
<tr>
	<td colspan=3>No Asociados</td>
</tr>
<tr class="titStyle">
  	  <td width="10%">Sel</td>
	  <td width="10%">Id</td>
	  <td width="80%">Raz&oacute;n Social</td>
 </tr>
<% for (int i = 0; i < bean52a.getProveedoresSize(); i++) {
	 	if (i % 2 == 0)stilo = "itemStyle";
		else stilo = "alternatingItemStyle"; 
	%>
    <tr class="<%=stilo%>">
 	<td width="10%"><input type=checkbox name=C<%= bean52a.getId(i) %>></td>
    <td width="10%"><%= bean52a.getId(i) %></td>
	<td width="80%"><%= bean52a.getRazonsocial(i) %></td>
   </tr>
<%
}
%> 
</table>
<br>
<table align="center" border="1" bordercolor="#364859" rules="cols" cellspacing="0" width="750" style="border-color:Black;border-width:1px;border-style:solid;font-family: arial; font-size: 11px;border-collapse:collapse;" class="tablatext">
	<tr class="titStyle">
	<td align=center><input type="submit" value="Asociar" class=body onclick="return asociar_onclick(this.form)" <%if (bean52a.getProveedoresSize()==0) {%>DISABLED <%}%>  >	</td>
</tr>
</table>
<br>
<table align="center" width="750">
	<tr><td><a href="PreAsociacionProveedorClas" class="ligas"><< Regresar</a></td></tr>
</table>

<input type="hidden" name=iidcategoria value=<%= request.getParameter("iidcategoria") %>>
<input type="hidden" name=iidgrupo value=<%= request.getParameter("iidgrupo") %>>
<input type="hidden" name=iidlinea value=<%= request.getParameter("iidlinea") %>>
<input type="hidden" name=iidmarca value=<%= request.getParameter("iidmarca") %>>
<input type=hidden name="vchcategoria" value="<%= request.getParameter("vchcategoria") %>">
<input type=hidden name="vchgrupo" value="<%= request.getParameter("vchgrupo") %>">
<input type=hidden name="vchlinea" value="<%= request.getParameter("vchlinea") %>">
<input type=hidden name="vchmarca" value="<%= request.getParameter("vchmarca") %>">
</form>
</body>
</html>
