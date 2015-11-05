<html>
<jsp:useBean id="bean73b" class="org.nibble.service.vo.pedidos.Bean73b" scope="session"/>

<head>
<title>NC 6.3 Envío de Pedidos</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link href="lib/estilo.css" rel=STYLESHEET type=text/css>
<script type="text/javascript" src="lib/util.js"></script>
<script language=JavaScript>
var  popen = false;

<%if  (bean73b.isPopupImprimir()) {%>
 popup=window.open("ImprimePedidosEnvio","","toolbar=0,location=0,directories=0,status=0,menubar=0,scrollbars=yes,resizable=yes,width=700,height=400");
 popen = true;
<% } %>

function guardar_onclick(form){
	form.accion.value=3;
	return true;
}
function eliminar_onclick(form){
	form.accion.value=4;
	return true;
}
function formatAsMoney(mnt) {
    mnt -= 0;
    mnt = (Math.round(mnt*100))/100;
    return (mnt == Math.floor(mnt)) ? mnt + '.00': ( (mnt*10 == Math.floor(mnt*10)) ? mnt + '0' : mnt);
}

function calculate(indice){
	forma = document.frm;
	costounitario = eval("forma.CU"+indice+".value");
	cantidad = eval("forma.T"+indice+".value");
	costo = parseFloat(cantidad*costounitario);
	
	eval("forma.CT"+indice+".value="+formatAsMoney(costo));
	total=0;
	for (i = 0; i < forma.length; i++){
	  if (forma.elements[i].name.substring(0,2)=="CT"){
	  	if (forma.elements[i].value != "")
	  		total+=parseFloat(forma.elements[i].value);
	  }
	}
	forma.total.value=formatAsMoney(total);
	
}
	
</script>
</head>
<%@ include file="../main.jsp" %>
<body bgcolor="#FFFFFF" text="#000000" onunload="if(popen)popup.close();">
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
	<td width="33%">Partes solicitadas</td>
	<td width="33%" colspan=2>Proveedor: <%= request.getParameter("vchrazonsocial") %></td>
</tr>
<tr class="titStyle">
	  <td width="20%">Número de parte</td>
	  <td width="20%">Fecha de solicitud</td>
	  <td width="20%">Cantidad solicitada</td>
	  <td width="20%">Costo Unitario</td>
	  <td width="20%">Costo Total</td>	  
 </tr>
<% String stilo;
   double total = 0.0;
	for (int i = 0; i < bean73b.getSize(); i++) {
	    total += (bean73b.getIcantpedida(i)*bean73b.getCostoUnitario(i));
	 	if (i % 2 == 0)stilo = "itemStyle";
		else stilo = "alternatingItemStyle"; 
	%>
  <input type=hidden name="CU<%= i %>" value=<%= bean73b.getCostoUnitario(i)%>>
  <tr class="<%=stilo%>">
    <td width="20%"><%= bean73b.getVchnumparte(i) %></td>
	<td width="20%"><%= bean73b.getDtfechasolicitud(i) %></td>
	<td width="20%" align="right"><input type=text class=numero name="T<%= i %>" value=<%= bean73b.getIcantpedida(i) %> onKeyUp="calculate('<%=i%>')" onkeypress="return numbersonly()"></td>
    <td width="20%" align="right"><%= bean73b.getCostoUnitario(i)%></td>
    <td width="20%" align="right"><input type=text disabled class=numero name="CT<%= i %>" value=<%= bean73b.getIcantpedida(i)*bean73b.getCostoUnitario(i) %>></td>
   </tr>
<%
}
%> 
 <tr>
   <td width="20%"></td>
   <td width="20%"></td>
   <td width="20%"></td>
   <td width="20%" align="right">Total</td>
   <td width="20%" align="right"><input type=text disabled class=numero name="total" value=<%=total %>></td>

 </tr> 
</table>
<br>
<table align="center" border="1" bordercolor="#364859" rules="cols" cellspacing="0" width="750" style="border-color:Black;border-width:1px;border-style:solid;font-family: arial; font-size: 11px;border-collapse:collapse;" class="tablatext">
	<tr class="titStyle">
		<td align=center><input type="submit" value="Guardar" class=body onclick="return guardar_onclick(this.form)" <%if (bean73b.getSize()==0) {%>DISABLED <%}%>>	
					<input type="submit" value="Eliminar pedido" class=body onclick="return eliminar_onclick(this.form)" <%if (bean73b.getSize()==0) {%>DISABLED <%}%>>
	</td>
</tr>
</table>
<br>
<table class="tablatext" width=750 align="center" cellpadding="0" cellspacing="0">
<tr>
	<td align=center><%= bean73b.getMsg() %></td>
</tr>
</table>
<br>
<table align="center" width="750">
	<tr><td><a href="javascript:document.frm.accion.value=1;document.frm.submit();" class="ligas"><< Regresar</a></td></tr>
</table>
<input type="hidden" name=iidpedido value=<%= request.getParameter("iidpedido") %>>
<input type="hidden" name=vchrazonsocial value=<%= request.getParameter("vchrazonsocial") %>>
</form>
</body>
</html>
