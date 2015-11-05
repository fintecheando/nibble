<html>
<jsp:useBean id="bean82b" class="pva.beans8.Bean82b" scope="session"/>

<head>
<title>PVA 8.2 Consulta a Movimientos de Inventario</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link href="lib/estilo.css" rel=STYLESHEET type=text/css>
<script language=JavaScript>
var  popen = false;

function imprimir_onclick(form){
	popup=window.open("ReporteMovimientosInventario","",'toolbar=0,location=0,directories=0,status=0,menubar=0,scrollbars=yes,resizable=yes,width=700,height=400');
    popen =true;
}
</script>

</head>
<%@ include file="../main.jsp" %>
<body bgcolor="#FFFFFF" text="#000000" onunload="if(popen)popup.close();">
<table class="tablatext" width=750 align="center" cellpadding="0" cellspacing="0">
<tr>
	<td><img src="images/tpva8_2.GIF"></td>

        <th>
	<td><a href="../pvatx/hlp/pva8.0-8.2.htm#consulta" target="Ayuda Consulta a Movimientos de Inv.">Ayuda</a></td>
        </th>

</tr>
</table>
<br>
<br>
<table align="center" border="1" bordercolor="#364859" rules="cols" cellspacing="0" width="750" style="border-color:Black;border-width:1px;border-style:solid;font-family: arial; font-size: 11px;border-collapse:collapse;" class="tablatext">
<tr class="titStyle">
  	  <td width="10%">No. de parte</td>
	  <td width="10%">Proveedor</td>
	  <td width="20%">Tipo de movimiento</td>
	  <td width="20%">Fecha de movimiento</td>
	  <td width="20%">Nombre de usuario</td>
	  <td width="10%">Cantidad</td>
	  <td width="10%">Doc. referencia</td>
	<% String stilo;
	for (int i = 0; i < bean82b.getSize(); i++) {
	 	if (i % 2 == 0)stilo = "itemStyle";
		else stilo = "alternatingItemStyle"; 
	%>
    <tr class="<%=stilo%>">
 	<td width="10%"><%= bean82b.getVchnumparte(i) %></td>
	<td width="10%"><%= bean82b.getVchrazonsocial(i) %></td>	
    <td width="20%"><%= bean82b.getTipodemovimiento(i) %></td>
	<td width="20%"><%= bean82b.getFechaMovimiento(i) %></td>
	<td width="20%"><%= bean82b.getNombreUsuario(i) %></td>
	<td width="10%" align="right"><%= bean82b.getCantidad(i) %></td>
	<td width="10%" align="right"><%= bean82b.getReferencia(i) %></td>
   </tr>
<%
}
%> 
</table>
<br>
<table align="center" border="1" bordercolor="#364859" rules="cols" cellspacing="0" width="750" style="border-color:Black;border-width:1px;border-style:solid;font-family: arial; font-size: 11px;border-collapse:collapse;" class="tablatext">
<tr class="titStyle">
	<td align=center><input type="button" value="Imprimir" class=body onclick="imprimir_onclick()"></td>
</tr>
</table>
<br>
<table align="center" width="750">
	<tr><td><a href="ConsultaMovimientosInventario" class="ligas"><< Regresar</a></td></tr>
</table>
</html>
