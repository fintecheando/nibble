<html>
<head>
<title>Reimpresion de Facturas</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link href="lib/estilo.css" rel=STYLESHEET type=text/css>
<script type="text/javascript" src="lib/util.js"></script>
<script type="text/javascript" src="lib/engine/jsSQL.js"></script>
<script type="text/javascript" src="lib/tools/PrintOptions.js"></script>
<script type="text/javascript" src="lib/tools/FillOptions.js"></script>

</head>
<%@ include file="../main.jsp" %>
<body bgcolor="#FFFFFF" text="#000000" onLoad ="setFileName();">
<body bgcolor="#FFFFFF" text="#000000">
<form name=frm action="ReimprimirFactura" method="post">
<p>
Please specify a file, or a set of files:<br>
<input type="file" name="firstFile1" size="40">
</p>
<div>
<input type="submit" value="Reimprimir">
</div>
</form>

    </html>