<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
  <title>PVA 2.2 Usuarios</title>
  <meta http-equiv="Content-Type"
 content="text/html; charset=iso-8859-1">
  <link href="lib/estilo.css" rel="STYLESHEET" type="text/css">
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
<body>
<jsp:usebean id="bean" class="pva.beans2.Bean22" scope="session"> &lt;%@
include file="../main.jsp" %&gt; </jsp:usebean>
<form name="frm" action="Usuarios" method="post"> <input type="hidden"
 name="accion"> <input type="hidden" name="iidusuario">
  <table class="tablatext" border ="3"
 style="width: 750px; text-align: left; margin-left: auto; margin-right: auto;"
 cellpadding="0" cellspacing="0">
    <tbody>
    
      <tr>
      
        <th>
	<td><img src="images/tpva2_2.GIF"></td>
	</th>
	
	<th>
	<td><a href="../html/help/pva2.0-2.2.htm">Columna2</a></td>
	</th>

      </tr>
            
      <tr> 
         <td><a href="../html/help/pva2.0-2.2.htm">Ayuda</a></td>
      </tr>
      
    </tbody>
  </table>
  <br>

  <table align="center" border="1" bordercolor="#364859" cellspacing="0"
 class="tablatext" rules="cols"
 style="border: 1px solid black; border-collapse: collapse; font-family: arial; font-size: 11px;"
 width="750">
	<tbody>
      <tr class="headerStyle">
		<td width="20%">Sel</td>
		<td width="40%">Usuario</td>
		<td width="40%">Descripci&oacute;n</td>
		 	</tr>
	<tr class="&lt;%=stilo%&gt;">
		<td width="20%"><input type="radio" name="usuario"
 value="&lt;%=bean.getIidusuario(i)%&gt;/&lt;%=bean.getIidgrupo(i)-1%&gt;/&lt;%=bean.getVchaliasusuario(i)%&gt;/&lt;%=bean.getVchnombreusuario(i)%&gt;/&lt;%=bean.getVchpasaporte(i)%&gt;/&lt;%=bean.getChactivo(i)%&gt;/&lt;%=bean.getVchdescripcion(i)%&gt;"
 onclick="establecer(form, this.value)"></td>
		<td width="40%">&lt;%=bean.getVchnombreusuario(i)%&gt;</td>
		<td width="40%">&lt;%=bean.getVchdescripcion(i)%&gt;</td>
	</tr>
	
    </tbody>
  </table>
  <br>
  <table align="center" border="1" bordercolor="#364859" cellspacing="0"
 class="tablatext" rules="cols"
 style="border: 1px solid black; border-collapse: collapse; font-family: arial; font-size: 11px;"
 width="750">
	<tbody>
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
        <option value="&lt;%=bean.getGIidgrupo(i)-1%&gt;">&lt;%=bean.getGVchdescripcion(i)%&gt;</option>
        </select>
	 </td>
	</tr>
	<tr class="titStyle">
	 <td>Contrase&ntilde;a</td>
	 <td>Verificar contrase&ntilde;a</td>
	 <td>Usuario habilitado</td>
	</tr>
	<tr>
	 <td><input type="password" name="vchpasaporte"></td>
	 <td><input type="password" name="vvchpasaporte"></td>
	 <td><input type="checkbox" name="habilitado"></td>
	</tr>
	<tr class="titStyle">
	 <td colspan="3">Descripci&oacute;n</td>
	</tr>
	<tr>
	 <td colspan="3"><input type="text" name="vchdescripcion"></td>
	</tr>
	 	<tr class="titStyle">
	 <td colspan="3" align="center"> 	 <input type="submit" class="body"
 value="Crear" onclick="return nuevo_onclick(this.form)"> 		<input
 type="submit" class="body" value="Modificar"
 onclick="return modificar_onclick(this.form)"> 		<input type="submit"
 class="body" value="Eliminar"
 onclick="return eliminar_onclick(this.form)"> 		<input type="reset"
 class="body" value="Reestablecer"> 	 </td>
      </tr>
    </tbody>
  </table>
</form>
</body>
</html>
