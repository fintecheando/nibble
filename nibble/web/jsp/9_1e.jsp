<html>
<jsp:useBean id="bean91c" class="pva.beans9.Bean91c" scope="session"/>

<head>
<title>PVA 9.1 Facturar</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link href="lib/estilo.css" rel=STYLESHEET type=text/css>
<script language=JavaScript>

function aceptar_onclick(form){
	dad = window.opener.document.frm;
	
	if (form.liidparte.value==''){
		alert ('Debe seleccionar una parte')
		return false;
	}
	dad.liidparte.value=document.frm.liidparte.value;
	dad.accion.value=8;
	dad.submit();
	window.close();
}
</script>
</head>

<body bgcolor="#FFFFFF" text="#000000">
<form name=frm action="Facturar">
<input type="hidden" name=liidparte>
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
<table class="tablatext" width=550 align="center" cellpadding="0" cellspacing="0">
<tr>
	<td width="11%"><img src="images/spacer.gif" width="100%" height="1"></td>
</tr>
</table>
  <table border="0" bgcolor="#E8EEF9" width="550" cellspacing="0" cellpadding="0" align="center" class="tablatext">
  <tr>
	  <td width="20%">Sel</td>
	  <td width="80%">Numero de parte</td>
 </tr>
</table>
<table class="tablatext" width=550 align="center" cellpadding="0" cellspacing="0">
<tr>
	<td width="11%"><img src="images/spacer.gif" width="100%" height="1"></td>
</tr>
</table>
<table border="0" width="550" class="tablatext">
<%
for (int i=0; i<bean91c.getPartesSize();i++){
%>
  <tr>
    <td width="20%"><input type="radio" name="temp" value="<%= bean91c.getLiidparte(i)%>" onclick="this.form.liidparte.value=this.value"></td>
	<td width="80%"><%= bean91c.getVchnumparte(i)%></td>
   </tr>
<%
}
%> 
</table>
<br><br>
<table class="tablatext" width=550 align="center" cellpadding="0" cellspacing="0">
<tr>
	<td align=center><input type="button" value="Aceptar" onclick="return aceptar_onclick(this.form)">	</td>
</tr>
</table>
</form>
</body>
</html>
