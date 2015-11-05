<html>
<jsp:useBean id="bean" class="org.nibble.service.vo.administracion.sistema.Bean31" scope="request"/>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link href="lib/estilo.css" rel=STYLESHEET type=text/css>
<link href="lib/selector.css" rel=STYLESHEET type=text/css>
<title>NC 3.1 Configuración del punto de venta</title>
<script language="javascript">
function modificar_onclick(form){
	var errors = '';
 	var numErrors = 0;
	sel = false;
	for (i=0;i<form.calculo.length;i++)
		if (form.calculo[i].checked)
			sel = true;
	if (!sel){
		errors += '- Debe seleccionar el cálculo de utilidad.\n';
		numErrors++;
	}
	if (form.iva.value==''){
		errors += '- Debe introducir el monto del I.V.A.\n';
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

</script>	
</head>
<%@ include file="../main.jsp" %>
<body>
<form name=frm action="ConfiguracionSistema" method="POST">
<input type=hidden name="accion">
<table class="tablatext" width=750 align="center" cellpadding="0" cellspacing="0">
<tr>
	<td><img src="images/tnc3_1.GIF"></td>

        <th>
	<td></td>
        </th>

</tr>
</table>
<br>
<table align=center border=1 borderColor=#364859 cellSpacing=0 class=tablatext rules=cols style="BORDER-BOTTOM: black 1px solid; BORDER-COLLAPSE: collapse; BORDER-LEFT: black 1px solid; BORDER-RIGHT: black 1px solid; BORDER-TOP: black 1px solid; FONT-FAMILY: arial; FONT-SIZE: 11px" width=750>
	<tr>
		<td colspan="2">Configuración para los puntos de venta</td>
	</tr>
	<tr class="titStyle">
		<td width="40%">I.V.A. aplicable</td>
		<td width="60%">Cálculo de utilidades</td>
	</tr>
	<tr>
   		<td width="40%"><input type="text" name="iva" value="<%=bean.getDecivaaplicable()%>"></td>
		<td width="60%">
   		  <input type="radio" name="calculo" value="0" <% if(!bean.getBitutilsobreventa()){%>checked<%}%>>Sobre costo
		  <input type="radio" name="calculo" value="1" <% if(bean.getBitutilsobreventa()){%>checked<%}%>>Sobre venta		  
	    </td>
	</tr>        
</table>
</form>
</body>
</html>
