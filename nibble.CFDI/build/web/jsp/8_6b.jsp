<html>
<jsp:useBean id="bean86" class="pva.beans8.Bean86" scope="session"/>
<head>
<title>PVA 8.6 Días de Inventario en Almacén</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link href="lib/estilo.css" rel=STYLESHEET type=text/css>
<script type="text/javascript" src="lib/util.js"></script>
<script language=JavaScript>
function modificar_onclick(form){
	if (form.nodiasinventario.value==''){
		alert ('Debe introducir el número de dias del inventario');
		return false;
	}
	form.accion.value=3;
	return true;
}
</script>
</head>
<%@ include file="../main.jsp" %>
<body bgcolor="#FFFFFF" text="#000000">
<form name=frm action="DiasInventario" method="post">
<input type="hidden" name=accion>
<table class="tablatext" width=750 align="center" cellpadding="0" cellspacing="0">
<tr>
	<td><img src="images/tpva8_6.GIF"></td>

        <th>
	<td><a href="../pvatx/hlp/pva8.0-8.6.htm#pasos" target="Ayuda Días de Inv. en Almac.">Ayuda</a></td>
        </th>

</tr>
</table>
<br>
<table align="center" border="1" bordercolor="#364859" rules="cols" cellspacing="0" width="750" style="border-color:Black;border-width:1px;border-style:solid;font-family: arial; font-size: 11px;border-collapse:collapse;" class="tablatext">
	<tr>
			<td colspan=4>Clasificación</td>
	</tr>
	<tr class="titStyle">
	    	<td width="25%">Categoría</td>
			<td width="25%">Grupo</td>
			<td width="25%">Línea</td>
			<td width="25%">Marca</td>
	</tr>
	<tr class="itemStyle">
			<td><%= request.getParameter("vchcategoria") %></td>
			<td><%= request.getParameter("vchgrupo") %></td>
			<td><%= request.getParameter("vchlinea") %></td>
			<td><%= request.getParameter("vchmarca") %></td>
	</tr>
</table>
<br>
<table width=750 align="center" border="1" bordercolor="#364859" rules="cols" cellspacing="0" style="border-color:Black;border-width:1px;border-style:solid;font-family: arial; font-size: 11px;border-collapse:collapse;" class="tablatext">
<tr class="headerStyle">
  	  <td>No. de días de inventario</td>
	  <td>No. de días de inventario nuevo</td>
</tr>
<tr class="itemStyle">
	<td><%= bean86.getInodiasinventario() %></td>
    <td><input type=text name='nodiasinventario' class=body onkeypress="return numbersonly()"></td>
</tr>
</table>
<br>  
<table width=750 align="center" border="1" bordercolor="#364859" rules="cols" cellspacing="0" width="550" style="border-color:Black;border-width:1px;border-style:solid;font-family: arial; font-size: 11px;border-collapse:collapse;" class="tablatext">
<tr class="titStyle">
	<td align=center><input type="submit" value="Modificar" class=body onclick="return modificar_onclick(this.form)">	</td>
</tr>
</table>
<br>
<table width=750 align="center" >
<tr>
	<td><a href="PreDiasInventario" class=ligas><< Regresar</a></td>
</tr>
</table>

<input type="hidden" name=iidcategoria value="<%= request.getParameter("iidcategoria")%>">
<input type="hidden" name=iidgrupo value="<%= request.getParameter("iidgrupo")%>">
<input type="hidden" name=iidlinea value="<%= request.getParameter("iidlinea")%>">
<input type="hidden" name=iidmarca value="<%= request.getParameter("iidmarca")%>">

<input type=hidden name="vchcategoria" value="<%= request.getParameter("vchcategoria")%>">
<input type=hidden name="vchgrupo" value="<%= request.getParameter("vchgrupo")%>">
<input type=hidden name="vchlinea" value="<%= request.getParameter("vchlinea")%>">
<input type=hidden name="vchmarca" value="<%= request.getParameter("vchmarca")%>">

</form>
</body>
</html>
