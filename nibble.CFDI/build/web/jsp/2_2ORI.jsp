<html>
<jsp:useBean id="bean" class="pva.beans2.Bean22" scope="session"/>
<head>
<title>PVA 2.2 Usuarios</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link href="lib/estilo.css" rel=STYLESHEET type=text/css>
<script language="javascript">
function nuevo_onclick(form){
 var errors = '';
 var numErrors = 0;
	if (form.vchnombreusuario.value==''){
		errors += '- Debe introducir el nombre del usuario.\n';
		numErrors++;
	}
	if (form.vchaliasusuario.value==''){
		errors += '- Debe introducir el identificador del usuario.\n';
		numErrors++;
	}
	if (form.vchpasaporte.value==''){
		errors += '- Debe introducir la contraseña del usuario.\n';
		numErrors++;
	}
	if (form.vvchpasaporte.value==''){
		errors += '- Debe confirmar la contraseña del usuario.\n';
		numErrors++;
	}
	if (form.vvchpasaporte.value!= form.vchpasaporte.value){
		errors += '- Deben coincidir la contraseña y la confirmación\n';
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
	if (form.iidusuario.value==''){
		errors += '- Debe seleccionar un usuario.\n';
		numErrors++;
	}
	if (form.vchnombreusuario.value==''){
		errors += '- Debe introducir el nombre del usuario.\n';
		numErrors++;
	}
	if (form.vchaliasusuario.value==''){
		errors += '- Debe introducir el identificador del usuario.\n';
		numErrors++;
	}
	if (form.vchpasaporte.value==''){
		errors += '- Debe introducir la contraseña del usuario.\n';
		numErrors++;
	}
	if (form.vvchpasaporte.value==''){
		errors += '- Debe confirmar la contraseña del usuario.\n';
		numErrors++;
	}
	if (form.vvchpasaporte.value!= form.vchpasaporte.value){
		errors += '- Deben coincidir la contraseña y la confirmación\n';
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
	
	if (form.iidusuario.value==''){
		errors += '- Debe seleccionar un usuario.\n';
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
	index = str.indexOf('/',0);
	form.iidusuario.value = str.substring(0,index);
	inde = str.indexOf('/',index+1);
	form.iidgrupo.options[str.substring(index+1,inde)].selected = true;
	ind = str.indexOf('/',inde+1);
	form.vchaliasusuario.value = str.substring(inde+1,ind);
	ins = str.indexOf('/',ind+1);
	form.vchnombreusuario.value = str.substring(ind+1,ins);
	i = str.indexOf('/',ins+1);
	form.vchpasaporte.value = str.substring(ins+1,i);
	form.vvchpasaporte.value = str.substring(ins+1,i);
	ii = str.indexOf('/',i+1);
	if (str.substring(i+1,ii)=="S")
		form.habilitado.checked = true;
	else	
		form.habilitado.checked = false;
	form.vchdescripcion.value = str.substring(ii+1,str.length);
}

</script>
</head>
<%@ include file="../main.jsp" %>
<body>
<form name=frm action="Usuarios" method="POST">
<input type=hidden name="accion">
<input type=hidden name="iidusuario">
<table class="tablatext" width=750 align="center" cellpadding="0" cellspacing="0">
<tr>
	<td><img src="images/tpva2_2.GIF"></td>
</tr>
</table>
<br>
<table align=center border=1 borderColor=#364859 cellSpacing=0 class=tablatext rules=cols style="BORDER-BOTTOM: black 1px solid; BORDER-COLLAPSE: collapse; BORDER-LEFT: black 1px solid; BORDER-RIGHT: black 1px solid; BORDER-TOP: black 1px solid; FONT-FAMILY: arial; FONT-SIZE: 11px" width=750>
	<tr class="headerStyle">
		<td width="20%">Sel</td>
		<td width="40%">Usuario</td>
		<td width="40%">Descripción</td>		
	</tr>
	<% String stilo;
		for(int i=0; i < bean.getUsuariosSize(); i++){
		 if (i % 2 == 0)stilo = "itemStyle";
		 else stilo = "alternatingItemStyle"; 
	%>
	<tr class="<%=stilo%>">
		<td width="20%"><input type="radio" name="usuario" value="<%=bean.getIidusuario(i)%>/<%=bean.getIidgrupo(i)-1%>/<%=bean.getVchaliasusuario(i)%>/<%=bean.getVchnombreusuario(i)%>/<%=bean.getVchpasaporte(i)%>/<%=bean.getChactivo(i)%>/<%=bean.getVchdescripcion(i)%>" onclick="establecer(form, this.value)"></td>
		<td width="40%"><%=bean.getVchnombreusuario(i)%></td>
		<td width="40%"><%=bean.getVchdescripcion(i)%></td>
	</tr>
	<%}%>
</table>
<br>
<table align=center border=1 borderColor=#364859 cellSpacing=0 class=tablatext rules=cols style="BORDER-BOTTOM: black 1px solid; BORDER-COLLAPSE: collapse; BORDER-LEFT: black 1px solid; BORDER-RIGHT: black 1px solid; BORDER-TOP: black 1px solid; FONT-FAMILY: arial; FONT-SIZE: 11px" width=750>
	<tr class="headerStyle">
	 <td>Nombre usuario</td>
	 <td>Identificador</td>
	 <td>Grupo</td>
	</tr> 
	<tr>
	 <td><input type="text" name="vchnombreusuario"></td>
	 <td><input type="text" name="vchaliasusuario"></td>
	 <td>
	 	<select name="iidgrupo" class="body">
	     <%for(int i =0; i< bean.getGruposSize(); i++){%>
		   <option value="<%=bean.getGIidgrupo(i)-1%>"><%=bean.getGVchdescripcion(i)%></option>
		 <%}%>		  
  		</select>
	 </td>
	</tr>
	<tr class="titStyle">
	 <td>Contraseña</td>
	 <td>Verificar contraseña</td>
	 <td>Usuario habilitado</td>
	</tr>
	<tr>
	 <td><input type="password" name="vchpasaporte"></td>
	 <td><input type="password" name="vvchpasaporte"></td>
	 <td><input type="checkbox" name="habilitado"></td>
	</tr>
	<tr class="titStyle">
	 <td colspan="3">Descripción</td>
	</tr>
	<tr>
	 <td colspan="3"><input type="text" name="vchdescripcion"></td>
	</tr>	
	<tr class = "titStyle">
	 <td colspan="3" align="center">
	    <input type="submit" class="body" value="Crear" onclick="return nuevo_onclick(this.form)">
		<input type="submit" class="body" value="Modificar" onclick="return modificar_onclick(this.form)">
		<input type="submit" class="body" value="Eliminar" onclick="return eliminar_onclick(this.form)">
		<input type="reset" class="body" value="Reestablecer">
	 </td>
    </tr>
</table>
</form>
</body>
</html>
