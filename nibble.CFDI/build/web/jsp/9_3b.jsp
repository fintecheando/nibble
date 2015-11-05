<html>
<head>
<title>PVA 9.3 Cambios</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link href="lib/estilo.css" rel=STYLESHEET type=text/css>
<link href="lib/selector.css" rel=STYLESHEET type=text/css>
<script language="JavaScript">

</script>
</head>
<body>
<div align="center">
<form name="frm" method="POST" action="Cambios">
<input type="hidden" name="accion">
<input type="hidden" name=partida value="<%= request.getParameter("partida") %>">
<table class="tablatext" width=550 align="center" cellpadding="0" cellspacing="0">
<tr>
	<td><img src="images/tpva9_3.GIF"></td>
</tr>
</table>
<table align="center" border="1" bordercolor="#364859" rules="cols" cellspacing="0" width="550" style="border-color:Black;border-width:1px;border-style:solid;font-family: arial; font-size: 11px;border-collapse:collapse;" class="tablatext">
<tr class="titStyle">   
  	  <td width="25%">Prefijo</td>
	  <td width="25%">Número</td>
	  <td width="25%">Sufijo</td>
	  <td width="25%">Medida</td>
</tr>
<tr class="itemStyle">
	<td width="25%"><input type=text name="prefijo" class="body" size=6 value=""></td>
	<td width="25%"><input type=text name="numero" class="body" size=6 value=""></td>
	<td width="25%"><input type=text name="sufijo" class="body" size=6 value=""></td>
	<td width="25%"><input type=text name="medida" class="body" size=6 value=""></td>
</tr>
</table>
<br>
<table align="center" border="1" bordercolor="#364859" rules="cols" cellspacing="0" width="550" style="border-color:Black;border-width:1px;border-style:solid;font-family: arial; font-size: 11px;border-collapse:collapse;" class="tablatext">
<tr class="titStyle">	
	<td align="center" ><input type=submit name=buscar value=Buscar class=body onclick="this.form.accion.value=5"></td>
</tr>
</form>	
</body>
</html>
