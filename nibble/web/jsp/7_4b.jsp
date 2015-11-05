<html>
<jsp:useBean id="bean74b" class="org.nibble.service.vo.pedidos.Bean74b" scope="session"/>

<head>
<title>NC 6.4 Consulta, Recepción y Modificación a Pedidos</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link href="lib/estilo.css" rel=STYLESHEET type=text/css>
<script language=JavaScript>
var  popen = false;
function imprimir_onclick(form){
 	popup=window.open("ImprimePedidos","","toolbar=0,location=0,directories=0,status=0,menubar=0,scrollbars=yes,resizable=yes,width=700,height=400");
 	popen = true;
}

function detalle_onclick(form){
	if (form.iidpedido.value==''){
		alert ('Debe marcar un pedido')
		return false;
	}
	form.accion.value=3;
	return true;
}

function establecer(form,str){
	index = str.indexOf('/');
	form.iidpedido.value=str.substring(0,index);
	str =str.substring(index+1,str.length);
	index = str.indexOf('/');
	form.iidproveedor.value=str.substring(0,index);
	str =str.substring(index+1,str.length);
	index = str.indexOf('/');	
	form.vchrazonsocial.value =str.substring(0,index);
	form.tisfechelabpedido.value=str.substring(index+1,str.length);
}
</script>
</head>
<%@ include file="../main.jsp" %>
<body bgcolor="#FFFFFF" text="#000000" onunload="if(popen)popup.close();">
<form name=frm action="Pedidos" method="post">
<input type="hidden" name=accion>
<table class="tablatext" width=750 align="center" cellpadding="0" cellspacing="0">
<tr>
	<td><img src="images/tnc7_4.GIF"></td>

        <th>
	<td><a href="../nctx/hlp/nc7.0-7.4.htm" target="Ayuda Consulta, Recepción y Mod.">Ayuda</a></td>
        </th>

</tr>
</table>
<br>
<table align="center" border="1" bordercolor="#364859" rules="cols" cellspacing="0" width="750" style="border-color:Black;border-width:1px;border-style:solid;font-family: arial; font-size: 11px;border-collapse:collapse;" class="tablatext">
<tr>
	<td colspan=10>Pedidos</td>
</tr>
<tr class="titStyle"> 
  	  <td width="5%">Sel</td>
	  <td width="10%"># Pedido</td>
	  <td width="20%">Proveedor</td>
	  <td width="20%">Estatus</td>
	  <td width="10%">Fecha de Elaboración</td>
	  <td width="5%"># Partidas</td>
	  <td width="5%">Surtidas</td>
	  <td width="5%">Sin surtir</td>
	  <td width="5%">Surt parcial</td>
	  <td width="5%">Canceladas</td>
 </tr>
<% String stilo;
	for (int i = 0; i < bean74b.getSize(); i++) {
	 	if (i % 2 == 0)stilo = "itemStyle";
		else stilo = "alternatingItemStyle"; 
	%>
	<tr class="<%=stilo%>">
 	<td width="5%"><input type=radio name=temp value="<%= bean74b.getIidpedido(i) %>/<%= bean74b.getIidproveedor(i) %>/<%= bean74b.getVchrazonsocial(i) %>/<%= bean74b.getTisfechelabpedido(i) %>" onclick="establecer(form, this.value)"></td>
    <td width="10%"><%= bean74b.getIidpedido(i) %></td>
	<td width="20%"><%= bean74b.getVchrazonsocial(i) %></td>
	<td width="20%"><%= bean74b.getVchdescestatus(i) %></td>
	<td width="10%"><%= bean74b.getTisfechelabpedido(i) %></td>
	<td width="5%"><%= bean74b.getSinototalpartidas(i) %></td>
	<td width="5%"><%= bean74b.getSinopartrecibidas(i) %></td>
	<td width="5%"><%= bean74b.getSinototpartpedpend(i) %></td>
	<td width="5%"><%= bean74b.getSinopartsurtpar(i) %></td>
	<td width="5%"><%= bean74b.getSinopartcanceladas(i) %></td>
   </tr>
<%
}
%> 
</table>
<br>
<table align="center" border="1" bordercolor="#364859" rules="cols" cellspacing="0" width="750" style="border-color:Black;border-width:1px;border-style:solid;font-family: arial; font-size: 11px;border-collapse:collapse;" class="tablatext">
	<tr class="titStyle">
		<td align=center><input type="submit" value="Ver Detalle" class=body onclick="return detalle_onclick(this.form)">	
		                 <input type="button" value="Imprimir" class=body onclick="return imprimir_onclick(this.form)">
		</td>
	</tr>
</table>
<br>
<table align="center" width="750">
	<tr><td><a href="javascript:document.frm.accion.value=1;document.frm.submit();" class="ligas"><< Regresar</a></td></tr>
</table>
<input type="hidden" name="iidpedido"> 
<input type="hidden" name="iidproveedor">
<input type="hidden" name="vchrazonsocial">
<input type="hidden" name="tisfechelabpedido">
</form>
</body>
</html>
