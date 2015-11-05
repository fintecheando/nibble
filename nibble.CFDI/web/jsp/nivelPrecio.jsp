<html>
<jsp:useBean id="bean4" class="org.nibble.service.vo.clientes.Bean4" scope="session"/>
<jsp:useBean id="bean42" class="org.nibble.service.vo.clientes.Bean42" scope="session"/>

<head>
<title>NC 4.2 Niveles de Precios</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link href="lib/estilo.css" rel=STYLESHEET type=text/css>
<script type="text/javascript" src="lib/util.js"></script>
<script language=JavaScript>

function nuevo_onclick(form){
	 var errors = '';
	var numErrors = 0;
	
	if (form.vchnivel.value==''){
		errors += '- Debe introducir el nivel.\n';
		numErrors++;
	}
	if (form.decutilidad.value==''){
		errors += '- Debe introducir el porcentaje de utilidad.\n';
		numErrors++;
	}
	if (form.decporccomision.value==''){
		errors += '- Debe introducir el porcentaje de comisión.\n';
		numErrors++;
	}

	if (numErrors) {
		errors = 'Se produjeron ' + numErrors + ' error(es)\n'+ errors;
		alert(errors);
		return false;
	}
	form.accion.value=2;
	return true;
}
function modificar_onclick(form){
	 var errors = '';
	var numErrors = 0;
	
	if (form.iidnivel.value==''){
		errors += '- Debe seleccionar un nivel de precio.\n';
		numErrors++;
	}
	if (form.vchnivel.value==''){
		errors += '- Debe introducir el nivel.\n';
		numErrors++;
	}
	if (form.decutilidad.value==''){
		errors += '- Debe introducir el porcentaje de utilidad.\n';
		numErrors++;
	}
	if (form.decporccomision.value==''){
		errors += '- Debe introducir el porcentaje de comisión.\n';
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
	
	if (form.iidnivel.value==''){
		errors += '- Debe seleccionar un nivel de precio.\n';
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
	form.iidnivel.value=str.substring(0,index);
	str =str.substring(index+1,str.length);
	index = str.indexOf('/');
	form.vchnivel.value=str.substring(0,index);
	str =str.substring(index+1,str.length);
	index = str.indexOf('/');
	form.decutilidad.value=str.substring(0,index);
	form.decporccomision.value=str.substring(index+1,str.length);
}
</script>
</head>
<%@ include file="../main.jsp" %>
<body bgcolor="#FFFFFF" text="#000000">
<form name=frm action="NivelPrecio" method="post">
<input type="hidden" name=accion>
<table class="tablatext" width=750 align="center" cellpadding="0" cellspacing="0">
<tr>
	<td><img src="images/tnc4_2.GIF"></td>

        <th>
	<td><a href="../nctx/hlp/nc4.0-4.2.htm" target="Ayuda Niveles de Precios">Ayuda</a></td>
        </th>

</tr>
</table>
<br>
<table align="center" border="1" bordercolor="#364859" rules="cols" cellspacing="0" width="750" style="border-color:Black;border-width:1px;border-style:solid;font-family: arial; font-size: 11px;border-collapse:collapse;" class="tablatext">
	<tr class="titStyle">
		<td>Categoría</td>
	<tr class="itemStyle">
		<td><select name="iidcatcliente" class="body" onchange="this.form.submit();">
		<%for (int i=0; i<bean4.getSize();i++){%>	
    		<option value="<%= bean4.getIidcatcliente(i) %>" <%if (bean4.getIidcatcliente(i)== bean42.getIidcatcliente()){%> SELECTED <%}%> ><%= bean4.getVchdescripcion(i) %></option>
		<%}%> 
      		</select>
		</td>
	</tr>
</table>
<br>
<table align="center" border="1" bordercolor="#364859" rules="cols" cellspacing="0" width="750" style="border-color:Black;border-width:1px;border-style:solid;font-family: arial; font-size: 11px;border-collapse:collapse;" class="tablatext">
<tr class="titStyle">
  	  <td width="25%">Sel</td>
	  <td width="25%">Nivel</td>
	  <td width="25%">% Utilidad</td>
	  <td width="25%">% Comisión</td>
</tr>
<% String stilo;
	for (int i = 0; i < bean42.getSize(); i++) {
	 	if (i % 2 == 0)stilo = "itemStyle";
		else stilo = "alternatingItemStyle"; 
	%>
    <tr class="<%=stilo%>">
 	<td width="25%"><input type=radio name=temp value="<%= bean42.getId(i) %>/<%= bean42.getNivel(i) %>/<%= bean42.getDecutilidad(i) %>/<%= bean42.getDecporccomision(i) %> " onclick="establecer(form, this.value)"> </td>
	<td width="25%"><%= bean42.getNivel(i) %></td>
	<td width="25%" align="right"><%= bean42.getDecutilidad(i) %></td>
	<td width="25%" align="right"><%= bean42.getDecporccomision(i) %></td>
   </tr>
<%}%> 
</table>
<br>
<table align="center" border="1" bordercolor="#364859" rules="cols" cellspacing="0" width="750" style="border-color:Black;border-width:1px;border-style:solid;font-family: arial; font-size: 11px;border-collapse:collapse;" class="tablatext">
	<tr class="titStyle">
    	<td width="33%">Nivel</td>
		<td width="33%">Utilidad</td>
		<td width="34%">Comisión</td>
	</tr>
	<tr class="itemStyle">
			<td><input type="text" name="vchnivel" class=body maxlength="2" size=2  ></td>
			<td><input type="text" name="decutilidad" class=body onkeypress="return numbersonly()"></td>
			<td><input type="text" name="decporccomision" class=body onkeypress="return numbersonly()"></td>
	</tr>
</table>
<br>
<table align="center" border="1" bordercolor="#364859" rules="cols" cellspacing="0" width="750" style="border-color:Black;border-width:1px;border-style:solid;font-family: arial; font-size: 11px;border-collapse:collapse;" class="tablatext">
<tr class="titStyle">
	<td align=center><input type="submit" value="Nuevo" class=body onclick="return nuevo_onclick(this.form)">
	<input type="submit" value="Modificar" class=body onclick="return modificar_onclick(this.form)" <%if (bean42.getSize()==0){%>DISABLED<%}%>>
	<input type="submit" value="Eliminar" class=body onclick="return eliminar_onclick(this.form)" <%if (bean42.getSize()==0){%>DISABLED<%}%>>
	</td>
</tr>
</table>
<br>
<table class="tablatext" width=750 align="center" cellpadding="0" cellspacing="0">
<tr>
	<td class=msg><%= bean42.getMsg() %></td>
</tr>
<input type="hidden" name="iidnivel"> 
</form>
</body>
</html>
