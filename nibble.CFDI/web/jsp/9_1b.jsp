<html>
<head>
<title>PVA 9.1 Facturar</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link href="lib/estilo.css" rel=STYLESHEET type=text/css>
<script language=JavaScript>

function buscar_onclick(form){
	form.accion.value=3;
	return true;
}
</script>
</head>
<body bgcolor="#FFFFFF" text="#000000">
<form name=frm action="Facturar">
<input type="hidden" name=accion>
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
<table width=550 align="center" border="1" bordercolor="#364859" rules="cols" cellspacing="0" style="border-color:Black;border-width:1px;border-style:solid;font-family: arial; font-size: 11px;border-collapse:collapse;" class="tablatext">
<tr class="headerStyle">
  	  <td>Razon Social</td>
   	  <td>RFC</td>
</tr>
<tr class="itemStyle">
	<td><input type=text name=vchrazonsocial size=40 class=body></td>
	<td><input type=text name=vchrfc size=40 class=body></td>
</tr>
</table>
<br>
<table width=550 align="center" border="1" bordercolor="#364859" rules="cols" cellspacing="0" width="550" style="border-color:Black;border-width:1px;border-style:solid;font-family: arial; font-size: 11px;border-collapse:collapse;" class="tablatext">
<tr class="titStyle">
	<td align=center><input type="submit" value="Buscar" class=body onclick="return buscar_onclick(this.form)">	</td>
</tr>
</table>
</form>
</body>
</html>
