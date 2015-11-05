<html>
<jsp:useBean id="bean73a" class="org.nibble.service.vo.pedidos.Bean73a" scope="session"/>

<head>
<title>NC 6.3 Envío de Pedidos</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link href="lib/estilo.css" rel=STYLESHEET type=text/css>
<script language=JavaScript>

function detalle_onclick(form){
	if (form.iidproveedor.value==''){
		alert ('Debe marcar un proveedor')
		return false;
	}
	form.accion.value=2;
	return true;
}
function establecer(form,str){
	index = str.indexOf('/');
	form.vchrazonsocial.value=str.substring(index+1,str.length);
	form.iidproveedor.value=str.substring(0,index);
}
</script>
</head>
<%@ include file="../main.jsp" %>
<body bgcolor="#FFFFFF" text="#000000">
<form name=frm action="PedidosEnvio" method="post">
<input type="hidden" name=accion>
<table class="tablatext" width=750 align="center" cellpadding="0" cellspacing="0">
<tr>
	<td><img src="images/tnc7_3.GIF"></td>

        <th>
	<td><a href="../nctx/hlp/nc7.0-7.3.htm" target="Ayuda Envío de Pedidos">Ayuda</a></td>
        </th>

</tr>
</table>
<br>
<table align="center" border="1" bordercolor="#364859" rules="cols" cellspacing="0" width="750" style="border-color:Black;border-width:1px;border-style:solid;font-family: arial; font-size: 11px;border-collapse:collapse;" class="tablatext">
<tr>
	<td colspan=3>Pedidos pendientes de envio</td>
</tr>
<tr class="titStyle">
  	  <td width="10%">Sel</td>
	  <td width="10%">Id</td>
	  <td width="80%">Raz&oacute;n Social</td>
</tr>
<% String stilo;
	for (int i = 0; i < bean73a.getSize(); i++) {
	 	if (i % 2 == 0)stilo = "itemStyle";
		else stilo = "alternatingItemStyle"; 
	%>
  <tr class="<%=stilo%>">
 	<td width="10%"><input type=radio name=temp value="<%= bean73a.getIidproveedor(i) %>/<%= bean73a.getVchrazonsocial(i) %>" onclick="establecer(form, this.value)"></td>
    <td width="10%"><%= bean73a.getIidproveedor(i) %></td>
	<td width="80%"><%= bean73a.getVchrazonsocial(i) %></td>
   </tr>
<%
}
%> 
</table>
<br>
<table align="center" border="1" bordercolor="#364859" rules="cols" cellspacing="0" width="750" style="border-color:Black;border-width:1px;border-style:solid;font-family: arial; font-size: 11px;border-collapse:collapse;" class="tablatext">
	<tr class="titStyle">
	<td align=center><input type="submit" value="Ver Detalle" class=body onclick="return detalle_onclick(this.form)">	</td>
</tr>
</table>
<input type="hidden" name="iidproveedor"> 
<input type="hidden" name="vchrazonsocial">
</form>
</body>
</html>
