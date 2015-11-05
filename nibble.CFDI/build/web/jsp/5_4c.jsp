<html>
<jsp:useBean id="bean54" class="org.nibble.service.vo.proveedores.Bean54" scope="session"/>

<head>
<title>NC 5.4 Estructuras de compra</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link href="lib/estilo.css" rel=STYLESHEET type=text/css>
<script type="text/javascript" src="lib/util.js"></script>
<script language=JavaScript>

function nuevo_onclick(form){
    var errors = '';
	var numErrors = 0;
	
	if (form.vchdescripcion.value=='') {
		errors += '- Debe introducir la descripción.\n';
		numErrors++;
	}
	if (form.porcentaje.value=='') {
		errors += '- Debe introducir el porcentaje.\n';
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
function modifica_onclick(form){
    var errors = '';
	var numErrors = 0;
	
	if (form.vchleyenda.value==''){
		errors += '- Debe seleccionar un descuento.\n';
		numErrors++;
	}
	if (form.porcentaje.value=='') {
		errors += '- Debe introducir el porcentaje.\n';
		numErrors++;
	}        
	if (numErrors) {
		errors = 'Se produjeron ' + numErrors + ' error(es)\n'+ errors;
		alert(errors);
		return false;
	}
	form.accion.value=5;
	return true;
}
function eliminar_onclick(form){
    var errors = '';
	var numErrors = 0;
	
	if (form.vchleyenda.value==''){
		errors += '- Debe seleccionar un descuento.\n';
		numErrors++;
	}
	if (numErrors) {
		errors = 'Se produjeron ' + numErrors + ' error(es)\n'+ errors;
		alert(errors);
		return false;
	}
	form.accion.value=6;
	return true;
}
function pronto_onclick(form){
	form.porcentaje.value = prompt("Porcentaje",""); 
	if (form.porcentaje.value=='' || form.porcentaje.value=='null'){
		alert('descuento no valido');
		return false;
		}
	form.accion.value=7;
	return true;
}
function costo_onclick(form){
	form.costo.value = prompt("Definir porcentaje de descuento costo base 100","<%= bean54.getPorcentajeCostoValuacion() %>"); 
	if (form.costo.value=='null') // cierra el prompt
		return false;
	if (form.costo.value==''){
		alert('Porcentaje de descuento costo base 100 no valido');
		return false;
		}
	form.accion.value=8;
	return true;
}
function establecer(form,str){
	index = str.indexOf('/');
	form.vchleyenda.value=str.substring(0,index);
	form.vchdescripcion.value=str.substring(0,index);
	form.porcentaje.value=str.substring(index+1,str.length);
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
	<td><a href="../nctx/hlp/nc5.0-5.4.htm#agregar" target="Ayuda Estructuras de Compra">Ayuda</a></td>
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
	<tr class="titStyle">
	    	<td>Proveedor</td>
			<td colspan=3><%= request.getParameter("vchrazonsocial") %></td>
	</tr>
	<tr class="titStyle">
	    	<td>Factor de costo venta Actual</td>
			<td><%= bean54.getFactorActual() %></td>
	    	<td>Porcentaje de costo venta Actual</td>
			<td><%= bean54.getPorcentajeActual() %></td>
	</tr>
</table>
<br>
<table align="center" border="1" bordercolor="#364859" rules="cols" cellspacing="0" width="750" style="border-color:Black;border-width:1px;border-style:solid;font-family: arial; font-size: 11px;border-collapse:collapse;" class="tablatext">
<tr class="titStyle">
	  <td width="80%">Descripción</td>
	  <td width="20%">Porcentaje</td>
</tr>
<tr class="itemStyle">
	<td width="80%"><input type=text name="vchdescripcion" class="body" size=70></td>
	<td width="20%"><input type=text name="porcentaje" class="body" size=4 onkeypress="return numbersonly()" ></td>
</tr>
</table>
<br>
<table align="center" border="1" bordercolor="#364859" rules="cols" cellspacing="0" width="750" style="border-color:Black;border-width:1px;border-style:solid;font-family: arial; font-size: 11px;border-collapse:collapse;" class="tablatext">
<tr class="titStyle">
	<td align=center><input type="submit" value="Nuevo" class=body onclick="return nuevo_onclick(this.form)">	
			<input type="submit" value="Modificar" class=body onclick="return modifica_onclick(this.form)" <%if (bean54.getVDesccompraSize()==0) {%>DISABLED <%}%>>
			<input type="submit" value="Eliminar" class=body onclick="return eliminar_onclick(this.form)" <%if (bean54.getVDesccompraSize()==0) {%>DISABLED <%}%>>
			<input type="submit" value="Pronto pago" class=body onclick="return pronto_onclick(this.form)">
	</td>
</tr>
</table>
<br>
<table align="center" border="1" bordercolor="#364859" rules="cols" cellspacing="0" width="750" style="border-color:Black;border-width:1px;border-style:solid;font-family: arial; font-size: 11px;border-collapse:collapse;" class="tablatext">
<tr class="titStyle">
  	  <td width="10%">Sel</td>
	  <td width="70%">Descripci&oacute;n</td>
	  <td width="10%">Factor</td>
	  <td width="10%">Descuento</td>
 </tr>
<% String stilo;
	for (int i = 0; i < bean54.getVDesccompraSize(); i++) {
	 	if (i % 2 == 0)stilo = "itemStyle";
		else stilo = "alternatingItemStyle"; 
	%>
    <tr class="<%=stilo%>">
 	<td width="10%"><input type=radio name=temp value="<%= bean54.getVchleyenda(i) %>/<%= bean54.getProcentaje(i) %>" onclick="establecer(form, this.value)"></td>
    <td width="70%"><%= bean54.getVchleyenda(i) %></td>
	<td width="10%" align="right"><%= bean54.getDecfactor(i) %></td>
	<td width="10%" align="right"><%= bean54.getProcentaje(i) %></td>
   </tr>
<%}%> 
</table>
<br>
<table align="center" border="1" bordercolor="#364859" rules="cols" cellspacing="0" width="550" style="border-color:Black;border-width:1px;border-style:solid;font-family: arial; font-size: 11px;border-collapse:collapse;" class="tablatext">
<tr class="titStyle">
	  <td width="33%"></td>
	  <td width="33%">Factor</td>
	  <td width="34%">Porcentaje</td>
</tr>
<tr class="itemStyle">
  	<td width="33%">Costo de valuaci&oacute;n</td>
	<td width="33%" align="right"><%= bean54.getFactorCostoValuacion() %></td>
	<td width="34%" align="right"><%= bean54.getPorcentajeCostoValuacion() %></td>
</tr>
<tr class="titStyle">
	  <td width="33%"></td>
	  <td width="33%">Factor</td>
	  <td width="34%">Porcentaje</td>
</tr>
<tr class="itemStyle">
  	<td width="33%">Descuento por pronto pago</td>
	<td width="33%" align="right"><%= bean54.getFactorProntoPago() %></td>
	<td width="33%" align="right"><%= bean54.getPorcentajeProntoPago() %></td>
</tr>
<tr class="titStyle">
	  <td width="33%"></td>
	  <td width="33%">Factor</td>
	  <td width="34%">Porcentaje</td>
</tr>
<tr class="itemStyle">
  	<td width="33%">Costo de adquisici&oacute;n</td>
	<td width="33%" align="right"><%= bean54.getFactorCostoAdquisicion() %></td>
	<td width="34%" align="right"><%= bean54.getPorcentajeCosotAdquisicion() %></td>
</tr>
</table>
<br>
<table align="center" border="1" bordercolor="#364859" rules="cols" cellspacing="0" width="750" style="border-color:Black;border-width:1px;border-style:solid;font-family: arial; font-size: 11px;border-collapse:collapse;" class="tablatext">
<tr class="titStyle">
	<td align=center><input type="submit" value="Porcentaje de descuento costo base 100" class=body onclick="return costo_onclick(this.form)">	
	</td>
</tr>
</table>
<br>
<table align="center" width="750">
	<tr><td><a href="javascript:document.frm.accion.value=2;document.frm.submit();" class="ligas"><< Regresar</a></td></tr>
</table>
<input type="hidden" name=costo>
<input type="hidden" name=costoventa value=<%= bean54.getFactorCostoValuacion() %>>
<input type="hidden" name=vchleyenda>
<input type="hidden" name=vchrazonsocial value="<%= request.getParameter("vchrazonsocial") %>">
<input type="hidden" name=iidproveedor value=<%= request.getParameter("iidproveedor") %>>
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
