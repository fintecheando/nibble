<html>
<head>
<title>NC 5.7 Reporte de proveedores</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link href="lib/estilo.css" rel=STYLESHEET type=text/css>
<script type="text/javascript" src="lib/util.js"></script>
<script language=JavaScript>
var  popen = false;

function Popup(){
 popup=window.open("","Popup",'toolbar=0,location=0,directories=0,status=0,menubar=0,scrollbars=yes,resizable=yes,width=700,height=400');
 popen =true;
}

function generar_onclick(form){
	if (parseInt(form.delno.value) > parseInt(form.alno.value)){
		alert('El numero inicial no puede ser mayor al numero final')
		return false;
	}
	Popup();
	popup.focus();
	form.target="Popup";
	return true;
}
</script>
</head>
<%@ include file="../main.jsp" %>
<body bgcolor="#FFFFFF" text="#000000" onunload="if(popen)popup.close();" onload="document.frm.delno.focus();document.frm.delno.select()">
<form name=frm action="ReporteProveedores" method="post">
<table class="tablatext" width=750 align="center" cellpadding="0" cellspacing="0">
<tr>
	<td><img src="images/tnc5_7.GIF"></td>

        <th>
	<td><a href="../nctx/hlp/nc5.0-5.7.htm" target="Ayuda Reporte de Proveedores">Ayuda</a></td>
        </th>

</tr>
</table>
<br>
<table align="center" border="1" bordercolor="#364859" rules="cols" cellspacing="0" width="450" style="border-color:Black;border-width:1px;border-style:solid;font-family: arial; font-size: 11px;border-collapse:collapse;" class="tablatext">
	<tr class="titStyle">
	    <td width="50%">Del No.</td>
		<td width="50%">Al No.</td>
	</tr>
	<tr class="itemStyle">
		<td><input type=text name="delno" class="body" size=4 onkeypress="return numbersonly()"></td>
		<td><input type=text name="alno" class="body" size=4 onkeypress="return numbersonly()"></td>
	</tr>
</table>
<br>
<table align="center" border="1" bordercolor="#364859" rules="cols" cellspacing="0" width="450" style="border-color:Black;border-width:1px;border-style:solid;font-family: arial; font-size: 11px;border-collapse:collapse;" class="tablatext">
<tr class="titStyle">
	<td align=center><input type="submit" value="Generar" class=body onclick="return generar_onclick(this.form)"></td>
</tr>
</table>
</form>
</body>
</html>
