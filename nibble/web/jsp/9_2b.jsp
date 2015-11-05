<html>
<jsp:useBean id="bean82" class="pva.beans8.Bean82" scope="session"/>

<head>
<title>PVA 8.2 Consulta a Movimientos de Inventario</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link href="lib/estilo.css" rel=STYLESHEET type=text/css>
<script language="JavaScript" src="lib/datepicker.js"></script>

<script type="text/javascript">
function buscar_onclick(form){
	if (!checar(form))
		return false;
	form.accion.value=2;
	return true;
}
</script>
</head>
<%@ include file="../main.jsp" %>
<body bgcolor="#FFFFFF" text="#000000">
<form name=frm action="Devoluciones" method="get">
<input type=hidden name="accion">
<table class="tablatext" width=750 align="center" cellpadding="0" cellspacing="0">
<tr>
	<td><img src="images/tpva9_2.GIF"></td>

        <th>
	<td><a href="../pvatx/hlp/pva9.0-9.2.htm" target="Ayuda Devoluciones">Ayuda</a></td>
        </th>

</tr>
</table>
<br><br>
</table>
<br>
<table align="center" border="1" bordercolor="#364859" rules="cols" cellspacing="0" width="550" style="border-color:Black;border-width:1px;border-style:solid;font-family: arial; font-size: 11px;border-collapse:collapse;" class="tablatext">
<tr class="titStyle"> 
	  <td width="25%">Desde</td>
	  <td width="25%">Hasta</td>
</tr>
<tr class="itemStyle">
	  <td><input type=text name="fechaini" class="body" size=10>
	  <a href="javascript:show_calendar('frm.fechaini');" onmouseover="window.status='Seleccione Fecha';return true;" onmouseout="window.status='';return true;"><img src="images/calendar.gif" border="0" class="."></a>
	  </td>
	  <td>
	  <input type=text name="fechafin" class="body" size=10>
	  <a href="javascript:show_calendar('frm.fechafin');" onmouseover="window.status='Seleccione Fecha';return true;" onmouseout="window.status='';return true;"><img src="images/calendar.gif" border="0" class="."></a>
	  </td>
</tr>
</table>
<br>
<table align="center" border="1" bordercolor="#364859" rules="cols" cellspacing="0" width="750" style="border-color:Black;border-width:1px;border-style:solid;font-family: arial; font-size: 11px;border-collapse:collapse;" class="tablatext">
<tr class="titStyle"> 	
		<td align=center><input type="submit" value="Buscar" class=body onclick="return buscar_onclick(this.form)">
		<input type="reset" name="bReset" value="Restablecer Criterios" class=body>
	</td>
</tr>
</table>
</form>

</body>
</html>
