<html>
<head>
<title>PVA 9.1 Facturar</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link href="lib/estilo.css" rel=STYLESHEET type=text/css>
<link href="lib/selector.css" rel=STYLESHEET type=text/css>
<script language="JavaScript">
dad = window.opener.document.frm;
	dad.accion.value=<%= request.getParameter("accionPost") %>;
	dad.submit();
	window.close();
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
<input type="hidden" name="accion">
Cerrando
</form>	
</div>
</body>
</html>
