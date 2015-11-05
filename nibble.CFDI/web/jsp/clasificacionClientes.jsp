<html>
<jsp:useBean id="bean4" class="org.nibble.service.vo.clientes.Bean4" scope="session"/>

<head>
<title>NC 4.1 Categorías</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link href="lib/estilo.css" rel=STYLESHEET type=text/css>
<script language=JavaScript>

function nuevo_onclick(form){
	if (form.vchdescripcion.value==''){
		alert ('Debe introducir la descripción')
		return false;
	}
	form.accion.value=2;
	return true;
}
function modificar_onclick(form){
	 var errors = '';
	var numErrors = 0;
	
	if (form.iidcatcliente.value==''){
		errors += '- Debe seleccionar una categoría.\n';
		numErrors++;
	}
	if (form.vchdescripcion.value==''){
		errors += '- Debe introducir la descripción.\n';
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
function eliminar_onclick(form){
 var errors = '';
	var numErrors = 0;
	
	if (form.iidcatcliente.value==''){
		errors += '- Debe seleccionar una categoría.\n';
		numErrors++;
	}

	if (numErrors) {
		errors = 'Se produjeron ' + numErrors + ' error(es)\n'+ errors;
		alert(errors);
		return false;
	}

	form.accion.value=4;
	return true;
}
function establecer(form,str){
	index = str.indexOf('/');
	form.vchdescripcion.value=str.substring(index+1,str.length);
	form.iidcatcliente.value=str.substring(0,index);
}
</script>
</head>
<%@ include file="../main.jsp" %>
<body bgcolor="#FFFFFF" text="#000000">
<form name=frm action="ClasificacionCliente" method="post">
<input type="hidden" name=accion>
<table class="tablatext" width=750 align="center" cellpadding="0" cellspacing="0">
<tr>
	<td><img src="images/tnc4_1.GIF"></td>

        <th>
	<td><a href="../nctx/hlp/nc4.0-4.1.htm" target="Ayuda Cats. de Clientes">Ayuda</a></td>
        </th>

</tr>
</table>
<br>
<table align="center" border="1" bordercolor="#364859" rules="cols" cellspacing="0" width="750" style="border-color:Black;border-width:1px;border-style:solid;font-family: arial; font-size: 11px;border-collapse:collapse;" class="tablatext">
<tr class="titStyle">
  	  <td width="10%">Sel</td>
	  <td width="90%">Descripción</td>
 </tr>
<% String stilo;
	for (int i = 0; i < bean4.getSize(); i++) {
	 	if (i % 2 == 0)stilo = "itemStyle";
		else stilo = "alternatingItemStyle"; 
	%>
    <tr class="<%=stilo%>">
 	<td width="10%"><input type=radio name=temp value="<%= bean4.getIidcatcliente(i) %>/<%= bean4.getVchdescripcion(i) %>" onclick="establecer(form, this.value)"> </td>
	<td width="90%"><%= bean4.getVchdescripcion(i) %></td>
   </tr>
<%
}
%> 
</table>
<br>
<table align="center" border="1" bordercolor="#364859" rules="cols" cellspacing="0" width="750" style="border-color:Black;border-width:1px;border-style:solid;font-family: arial; font-size: 11px;border-collapse:collapse;" class="tablatext">
<tr class="titStyle">
	<td width="100%">Descripción</td>
<tr class="itemStyle">
	<td><input type="text" name="vchdescripcion" size=30 maxlength="25" class=body></td>
</tr>
</table>
<br>
<table align="center" border="1" bordercolor="#364859" rules="cols" cellspacing="0" width="750" style="border-color:Black;border-width:1px;border-style:solid;font-family: arial; font-size: 11px;border-collapse:collapse;" class="tablatext">
<tr class="titStyle">
	<td align=center><input type="submit" value="Nuevo" class=body onclick="return nuevo_onclick(this.form)">
	<input type="submit" value="Modificar" class=body onclick="return modificar_onclick(this.form)" <%if (bean4.getSize()==0){%>DISABLED<%}%>>
	<input type="submit" value="Eliminar" class=body onclick="return eliminar_onclick(this.form)" <%if (bean4.getSize()==0){%>DISABLED<%}%>>
	</td>
</tr>
</table>
<input type="hidden" name="iidcatcliente">
</form>
</body>
</html>
