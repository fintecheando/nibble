<html>
<jsp:useBean id="bean52" class="org.nibble.service.vo.proveedores.Bean52" scope="session"/>

<head>
<title>NC 5.4 Estructuras de compra</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link href="lib/estilo.css" rel=STYLESHEET type=text/css>
<script language=JavaScript>

function estructuras_onclick(form){
    var errors = '';
	var numErrors = 0;
	
	if (form.iidproveedor.value==''){
		errors += '- Debe seleccionar un proveedor.\n';
		numErrors++;
	}
	if (numErrors) {
		errors = 'Se produjeron ' + numErrors + ' error(es)\n'+ errors;
		alert(errors);
		return false;
	}
	form.accion.value=3;
	return true;
}
function establecer(form,str){
	index = str.indexOf('/');
	form.vchrazonsocial.value=str.substring(index+1,str.length);
	form.iidproveedor.value=str.substring(0,index);
}
</script>
</head>
<%@ include file="../main.jsp" %>
<body bgcolor="#FFFFFF" text="#000000">
<form name=frm action="EstructurasCompra" method="post">
<input type="hidden" name=accion>
<table class="tablatext" width=750 align="center" cellpadding="0" cellspacing="0">
<tr>
	<td><img src="images/tnc5_4.GIF"></td>
        
        <th>
	<td><a href="../nctx/hlp/nc5.0-5.4.htm#definir" target="Ayuda Estructuras de Compra">Ayuda</a></td>
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
	<td colspan=3>Asociados</td>
</tr>
<tr class="titStyle">
	<td width="10%">Sel</td>
	<td width="10%">Id</td>
	<td width="80%">Razón Social</td>
</tr>
<% String stilo;
	for (int i = 0; i < bean52.getProveedoresSize(); i++) {
	 	if (i % 2 == 0)stilo = "itemStyle";
		else stilo = "alternatingItemStyle"; 
	%>
    <tr class="<%=stilo%>">
    <td width="10%"><input type=radio name=temp value="<%= bean52.getId(i) %>/<%= bean52.getRazonsocial(i) %>" onclick="establecer(form, this.value)"></td>
    <td width="10%"><%= bean52.getId(i) %></td>
	<td width="80%"><%= bean52.getRazonsocial(i) %></td>
   </tr>
<%}%> 
</table>
<br>
<table align="center" border="1" bordercolor="#364859" rules="cols" cellspacing="0" width="750" style="border-color:Black;border-width:1px;border-style:solid;font-family: arial; font-size: 11px;border-collapse:collapse;" class="tablatext">
<tr class="titStyle">
	<td align=center><input type="submit" value="Ver Estructuras de compra" class=body onclick="return estructuras_onclick(this.form)" <%if (bean52.getProveedoresSize()==0) {%>DISABLED <%}%>></td>
</tr>
</table>
<br>
<table align="center" width="750">
	<tr><td><a href="PreEstructurasCompra" class="ligas"><< Regresar</a></td></tr>
</table>
<input type="hidden" name=iidproveedor>
<input type="hidden" name="vchrazonsocial">
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
