<html>
<jsp:useBean id="bean" class="dbbeans.configuracionpva.dbCONFIGURACIONPVA" scope="request"/>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link href="lib/estilo.css" rel=STYLESHEET type=text/css>
<title>PVA 3.1 ConfiguraciÃ³n</title>
<script language="javascript">
function modificar_onclick(form){
	var errors = '';
 	var numErrors = 0;
	if (form.vchrazonsocial.value==''){
		errors += '- Debe introducir el nombre de la empresa\n';
		numErrors++;
	}
	if (form.vchserie.value==''){
		errors += '- Debe introducir la letra actual para facturacion\n';
		numErrors++;
	}
	if (form.inumerofactura.value==''){
		errors += '- Debe introducir el numero actual para facturacion\n';
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
<form name=frm action="Configuracion" method="POST">
<input type=hidden name="accion">
<table class="tablatext" width=750 align="center" cellpadding="0" cellspacing="0">
<tr>
	<td><img src="images/tpva3_1.GIF"></td>

       
</tr>
</table>
<br>
<table align=center border=1 borderColor=#364859 cellSpacing=0 class=tablatext rules=cols style="BORDER-BOTTOM: black 1px solid; BORDER-COLLAPSE: collapse; BORDER-LEFT: black 1px solid; BORDER-RIGHT: black 1px solid; BORDER-TOP: black 1px solid; FONT-FAMILY: arial; FONT-SIZE: 11px" width=750>
	<tr>
		<td colspan="4">Configuraci&oacute;n General</td>
	</tr>
	<tr class="titStyle">
		<td width="25%">Nombre de la Empresa</td>
                <td width="25%">R.F.C.</td>
		<td width="25%">Calle</td>
		<td width="25%">Núm. Exterior</td>
	</tr>
	<tr>
            <td width="25%"><input type="text" name="vchrazonsocial" value="<%=bean.getVchrazonsocial()%>"></td>
            <td width="25%"><input type="text" name="vchrfc" value="<%=bean.getVchrfc()%>"></td>
            <td width="25%"><input type="text" name="vchcalle" value="<%=bean.getVchcalle()%>"></td>
            <td width="25%"><input type="text" name="vchnumexterior" value="<%=bean.getVchnumexterior()%>"></td>
	</tr>
        <tr class="titStyle">
		<td width="25%">Núm. Interior</td>
                <td width="25%">Colonia</td>
		<td width="25%">Localidad</td>
		<td width="25%">Municipio o Delegación</td>
	</tr>
	<tr>
            <td width="25%"><input type="text" name="vchnuminterior" value="<%=bean.getVchnuminterior()%>"></td>
            <td width="25%"><input type="text" name="vchcolonia" value="<%=bean.getVchcolonia()%>"></td>
            <td width="25%"><input type="text" name="vchlocalidad" value="<%=bean.getVchlocalidad()%>"></td>
            <td width="25%"><input type="text" name="vchmunicipio" value="<%=bean.getVchmunicipio()%>"></td>
	</tr>
        <tr class="titStyle">
		<td colspan="2">Estado</td>
                <td width="25%">Pais</td>
		<td width="25%">Codigo Postal</td>		
	</tr>
	<tr>
            <td colspan="2"><input type="text" name="vchestado" value="<%=bean.getVchestado()%>"></td>
            <td width="25%"><input type="text" name="vchpais" value="<%=bean.getVchpais()%>"></td>
            <td width="25%"><input type="text" name="vchcodigopostal" value="<%=bean.getVchcodigopostal()%>"></td>
	</tr>
	<tr class="titStyle">
		<td width="25%">Serie de Facturaci&oacute;n</td>
		<td width="25%">N&uacute;mero de Folio de Facturaci&oacute;n</td>
		<td width="25%">N&uacute;mero Nota de Cr&eacute;dito</td>
		<td width="25%">N&uacute;mero Nota de Cargo</td>		
	</tr>
	<tr>
		<td width="25%"><input type="text" name="vchserie" value="<%=bean.getVchserie()%>"></td>
		<td width="25%"><input type="text" name="inumerofactura" value="<%=bean.getInumerofactura()%>"></td>
		<td width="25%"><input type="text" name="inumerocredito" value="<%=bean.getInumeronc()%>"></td>
		<td width="25%"><input type="text" name="inumerocargo" value="<%=bean.getInumeronca()%>"></td>			
	</tr>
        <tr class="titStyle">
		<td colspan="2">Numero de Aprobacion</td>
                <td colspan="2">Año de Aprobacion</td>
	</tr>
	<tr>
            <td colspan="2"><input type="text" name="inumaprobacion" value="<%=bean.getInumaprobacion()%>"></td>
            <td colspan="2"><input type="text" name="ianoaprobacion" value="<%=bean.getIanoaprobacion()%>"></td>
	</tr>
	<tr class="titStyle">
		<td colspan="4" align="center">
			<input type="submit" class="body" value="Modificar" onclick="return modificar_onclick(this.form)">
			<input type="reset" class="body" value="Reestablecer">
		</td>
	</tr>	
</table>
</form>
</body>
</html>
