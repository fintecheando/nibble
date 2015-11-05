<html>
<jsp:useBean id="bean93" class="pva.beans9.Bean93" scope="session"/>
<jsp:useBean id="popupImprimir" class="java.lang.Boolean" scope="session"/>
<head>
<title>PVA 9.3 Cambios</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link href="lib/estilo.css" rel=STYLESHEET type=text/css>
<script type="text/javascript" src="lib/util.js"></script>
<script language=JavaScript>
var  popen = false;

<%if  (popupImprimir.booleanValue()) {%> popup=window.open("ImprimirNotaCambio","","toolbar=0,location=0,directories=0,status=0,menubar=0,scrollbars=yes,resizable=yes,width=700,height=400");
 popen = true;
<% } %>

function recalculate(name){
	forma = document.frm;
	// cantidadi
	i = name.substring(8,name.length);
	
	importe = eval("forma.importe"+i);
	cantidad = eval("forma.cantidad"+i);
	precio = eval("forma.precio"+i);

	icantidad = 0;
	if (cantidad.value!='')
		icantidad = cantidad.value;
		
	importe.value = formatAsMoney(icantidad * precio.value);
	
	total=0;
	i=0;
	while ((importe = eval("forma.importe"+i)) != null){
		if (importe.value=='')
			importe=0;
		else 
			importe = importe.value;
		total+=parseFloat(importe);
		i ++;
	}
	forma.subtotal.value=formatAsMoney(total);
	recalculatetotal();
}

function checar(name){
	forma = document.frm;	
	// cantidadi
	i = name.substring(8,name.length);

	cant = eval("forma.cant"+i);
	cantidad = eval("forma.cantidad"+i);	
	
	icant = parseInt(cant.value);
	icantidad = parseInt(cantidad.value);
	if (icant < icantidad){
		alert('La cantidad devuelta no puede ser mayor de la vendida');
		cantidad.value='';
		}
}
	
function recalculatetotal(){
	forma = document.frm;
	<%if (bean93.isMostradorSinIVA()){%>
		forma.total.value = formatAsMoney(forma.subtotal.value);
	<%}else{%>
	forma.montodescuento.value = formatAsMoney(forma.subtotal.value * forma.descuento.value / 100);
	forma.montoiva.value = formatAsMoney((forma.subtotal.value - forma.montodescuento.value) * (forma.iva.value/100));
	forma.total.value = formatAsMoney(forma.subtotal.value - forma.montodescuento.value + parseFloat(forma.montoiva.value));
	<%}%>
}
function formatAsMoney(mnt) {
    mnt -= 0;
    mnt = (Math.round(mnt*100))/100;
    return (mnt == Math.floor(mnt)) ? mnt + '.00' 
              : ( (mnt*10 == Math.floor(mnt*10)) ? 
                       mnt + '0' : mnt);
}
function devolver_onclick(){
	document.frm.accion.value=3;
	document.frm.submit();
}
function buscar_onclick(name){
	document.frm.partida.value=name;
	ref = "Cambios?accion=4&partida="+document.frm.partida.value;
	Popup(ref);
}
var isAltKeyPressed = false;

function hotkey(eventname){
//	alert(eventname.keyCode);
   forma = document.frm;
   if(eventname.keyCode == 18) 
   	  	isAltKeyPressed = true;
   if(eventname.keyCode == 112 && isAltKeyPressed){  //F1
   	  	forma.iidfactura.focus();
		forma.iidfactura.select();
		}
   if(eventname.keyCode == 113 && isAltKeyPressed)  //F2
   	  	devolver_onclick();
   if(eventname.keyCode == 114 && isAltKeyPressed)  //F3
   	  	cancelar_onclick();
}
var
  popen = false;
  
function Popup(ref){
 popup=window.open(ref,"Popup","toolbar=0,location=0,directories=0,status=0,menubar=0,scrollbars=yes,resizable=yes,width=600,height=400");
 popen = true;
}


</script>
</head>
<%@ include file="../main.jsp" %>
<body onunload="if(popen)popup.close();" onkeydown='hotkey(event)'>
<form name=frm action="Cambios" method="post">
<input type="hidden" name=accion>
<input type="hidden" name=partida>
<table class="tablatext" width=750 align="center" cellpadding="0" cellspacing="0">
<tr>
	<td><img src="images/tpva9_3.GIF"></td>
<!--
        <th>
	<td><a href="../pvatx/hlp/pva9.0-9.3.htm" target="Ayuda Cambios">Ayuda</a></td>
        </th>
-->
</tr>
</table>
<div align="center">
<table align="center" border="1" bordercolor="#364859" rules="cols" cellspacing="0" width="750" style="border-color:Black;border-width:1px;border-style:solid;font-family: arial; font-size: 11px;border-collapse:collapse;" class="tablatext">
<tr>
	<td colspan=3>Factura</td>
	<td align=right>Usuario: <%= bean93.getUsername() %></td>
</tr>
<tr class="titStyle"> 
  	  <td width="25%">N&uacute;mero</td>
  	  <td width="25%">Datos generales</td>
	  <td width="25%">Condiciones comerciales</td>
   	  <td width="25%" align=right>Total</td>
 </tr>
 <tr class="itemStyle">
  	<td width="25%">
		<table width="100%" class="tablatext" cellspacing="0" cellpadding="0">
			<tr><td><input type=text name='iidfactura' size=4 class=body value='<%= bean93.getIidfactura() %>'></td>
			    <td><input type=submit name=buscar value=Buscar class=body onclick="this.form.accion.value=2"></td>
			</tr>
			<tr><td>&nbsp;
			</td></tr>
			<tr class="titStyle">
  	  			<td colspan=2>Fecha / Hora de emisi&oacute;n</td>
			</tr>			
			 <tr class="itemStyle">
			<td colspan=2><%= bean93.getTisfechafactura() %></td>
			</tr>
		</table>
	</td>
    <td width="25%"><textarea name="datosgenerales" rows="7" cols="30" class="body" readonly=true><%= bean93.getDatosGenerales() %></textarea></td>
	<td width="25%"><textarea name="condicionescomerciales" rows="7" cols="30" class="body" readonly=true><%= bean93.getCondicionesComerciales() %></textarea></td>
	<td width="25%" align=right>
	<table class="tablatext" cellpadding="0">
	<tr><td align="right" colspan=2>Subtotal</td>
		<td><input type=text name="subtotal" size=15 class=numero readonly=true value=""></td>
	</tr>
	<tr><td align="right"><%if (!bean93.isMostradorSinIVA()){%>Descuento<%}%></td>
		<td><input type=<%if (bean93.isMostradorSinIVA()){%>hidden<%}else{%>text<%}%> name="descuento" size=5 class=numero readonly=true value="<%= bean93.getDecdescglobal() %>" onkeyup="recalculatetotal()" > </td>
		<td><input type=<%if (bean93.isMostradorSinIVA()){%>hidden<%}else{%>text<%}%> name="montodescuento" size=15 class=numero readonly=true value=""> </td>
	</tr>
	<tr><td align="right"><%if (!bean93.isMostradorSinIVA()){%>Iva<%}%></td>
		<td><input type=<%if (bean93.isMostradorSinIVA()){%>hidden<%}else{%>text<%}%> name="iva" size=5 class=numero readonly=true value="<%= bean93.getIva() %>"></td>
		<td><input type=<%if (bean93.isMostradorSinIVA()){%>hidden<%}else{%>text<%}%> name="montoiva" size=15 class=numero readonly=true value=""></td>
	</tr>

	<tr><td align="right" colspan=2>Total</td>
		<td><input type=text name="total" size=15 class=numero readonly=true value=""></td>
	</tr>		 
	</table>
	</td>
</tr>
</table>
<br>
<table align="center" border="1" bordercolor="#364859" rules="cols" cellspacing="0" width="750" style="border-color:Black;border-width:1px;border-style:solid;font-family: arial; font-size: 11px;border-collapse:collapse;" class="tablatext">
  <tr class="headerStyle">
  	  <td width="5%">Devueltas</td>
	  <td width="20%" align=center >Número de parte</td>
	  <td width="10%">Descripción</td>
	  <td width="10%">Proveedor</td>
	  <td width="5%">Nivel</td>
	  <td width="5%">Venta</td>
	  <td width="10%">P. Unitario</td>
	  <td width="10%">Dev</td>
	  <td width="10%">Importe dev</td>
	  <td width="10%">Cambio</td>
  </tr>
  <tr class="headerStyle">
  	  <td width="5%">Cambio</td>
	  <td width="20%" align=center >Número de parte</td>
	  <td width="10%">Descripción</td>
	  <td width="10%">Proveedor</td>
	  <td width="5%"></td>
	  <td width="10%">Existencia</td>
	  <td width="10%">Precio</td>	  
	  <td colspan=5></td>
  </tr>
  <% for (int i = 0; i < bean93.getVPartidasSize(); i++) {	%>
   <tr class="itemStyle">
	<td width="5%" style={color="#ff0000"}><%if (bean93.getDevueltas(i)>0)%><%= bean93.getDevueltas(i) %></td>
    <td width="20%"><%= bean93.getVchnumparte(i) %></td>
	<td width="10%"><%= bean93.getVchdescripcion(i) %></td>
	<td width="15%"><%= bean93.getVchrazonsocial(i) %></td>
	<td width="5%"><%= bean93.getVchnivel(i) %></td>
	<td width="10%" align="right"><%= bean93.getIcantidadproducto(i) %><input type=hidden name="cant<%= i%>" value="<%= bean93.getIcantidadproducto(i) %>"></td>	
	<td width="10%" align="right"><%= bean93.getDecprecioventaSTR(i) %><input type=hidden name="precio<%= i%>" value="<%= bean93.getDecprecioventaSTR(i) %>"></td>
	<td width="5%"><input type=text name="cantidad<%= i%>" class="numero" size=5 value="" onkeyup="checar(this.name);recalculate(this.name)" onkeypress="return numbersonly()"></td>
	<td width="10%"><input type=text name="importe<%= i%>" class="numero" readonly=true size=15 value="" ></td>
	<td width="5%"><input type="button" name="<%= i%>" class=body value="Buscar" onclick="buscar_onclick(this.name)"></td>
  </tr>
  <tr class="alternatingItemStyle">	
  	<%if (bean93.getLiidpartecambio(i) > 0) {%>
	<td width="5%"></td>
	<td width="20%"><%= bean93.getVchnumpartecambio(i) %></td>
	<td width="10%"><%= bean93.getVchdescripcionesCambio(i) %></td>
	<td width="15%"><%= bean93.getVchrazonsocialcambio(i) %></td>
	<td width="5%"></td>
	<td width="10%" align="right"><%= bean93.getIexistenciacambio(i) %></td>
	<td width="10%" align="right"><%= bean93.getDecpreciocambioSTR(i) %></td>
	<td colspan=5></td>
	<%} else {%>
	<td colspan=10>&nbsp;</td>
	<%} %>
   </tr>
	<%} %> 
</table>
<br>
<table align="center" border="1" bordercolor="#364859" rules="cols" cellspacing="0" width="750" style="border-color:Black;border-width:1px;border-style:solid;font-family: arial; font-size: 11px;border-collapse:collapse;" class="tablatext">
<tr class="titStyle">
	<td align=center><input type="button" class=body value="Aplicar Cambios" onclick="return devolver_onclick()" <%if (bean93.getVPartidasSize()==0){%>DISABLED<%}%>>
	</td>
</tr>
</table>
<br>
<table class="tablatext" width=750 align="center" cellpadding="0" cellspacing="0">
<tr>
	<td align=center><%= bean93.getMsg() %></td>
</tr>
</table>
</form>
</body>
</html>
