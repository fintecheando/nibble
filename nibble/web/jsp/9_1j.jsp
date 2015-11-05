<html>
<jsp:useBean id="bean91Parte" class="pva.beans9.Bean91Parte" scope="session"/>
<jsp:useBean id="bean82" class="pva.beans8.Bean82" scope="session"/>
<head>
<title>PVA 9.1 Facturar</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link href="lib/estilo.css" rel=STYLESHEET type=text/css>
<link href="lib/selector.css" rel=STYLESHEET type=text/css>
<script language="JavaScript">
function aceptar_onclick(form){
	dad = window.opener.document.frm;
	dad.accion.value=18;
	dad.user.value = form.user.value;
	dad.password.value = form.password.value;
	dad.submit();
	window.close();
}

</script>
</head>
<body>
<table class="tablatext" width=550 align="center" cellpadding="0" cellspacing="0">
<tr>
	<td><img src="images/tpva9_1.GIF"></td>

        <th>
	<td><a href="../pvatx/hlp/pva9.0-9.1.htm" target="Ayuda Facturar">Ayuda</a></td>
        </th>

</tr>
</table>
<br>
<br>
<div align="center">
<form name="frm" method="POST" action="Facturar">
<input type="hidden" name="accion" value=15>
<input type="hidden" name=iidaplicacion value="<%= request.getParameter("iidaplicacion") %>">
<table border="0" width="350" class="tablatext">
	<tr>
		<td colspan="17">El Cliente Seleccionado Ha Superado Su Limite de Credito y/o Tiene Documentos No Saldados Que Han Vencido. Autorización De Un Supervisor Requerida</td>
	</tr>
	<tr>
		<td colspan="17"><img src="images/spacer.gif" width="100%" height="1"></td>
	</tr>
    <tr bgcolor="#E8EEF9">
  	  <td>Nombre</td>
    </tr>
	<tr>
		<td colspan="17"><img src="images/spacer.gif" width="100%" height="1"></td>
	</tr>
	<tr>
	    <td><input type="text" name="user" class=body></td> 
    </tr>	
	<tr bgcolor="#E8EEF9">
	  <td>Passsword</td>
    </tr>
	<tr>
		<td colspan="17"><img src="images/spacer.gif" width="100%" height="1"></td>
	</tr>
		<td><input type="password" name="password" class=body></td> 
	  </tr>	
</table>
<br><br>
<table class="tablatext" width=550 align="center" cellpadding="0" cellspacing="0">
<tr>
	<td align=center><input type="button" value="Aceptar" onclick="aceptar_onclick(this.form);">
		<input type="button" value="Cancelar" onclick="window.close()"></td>
</tr>
</table>
</form>	
</div>
</body>
</html>
