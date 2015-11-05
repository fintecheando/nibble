<html>
<jsp:useBean id="bean91" class="pva.beans9.Bean91" scope="session"/>
<head>
<title>PVA 9.1 Facturar</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link href="lib/estilo.css" rel=STYLESHEET type=text/css>
<script type="text/javascript" src="lib/util.js"></script>
<script language=JavaScript>
var  popen = false;

<%if  (bean91.isPopupCliente()) {%>
 popup=window.open("Facturar?accion=2","","toolbar=0,location=0,directories=0,status=0,menubar=0,scrollbars=yes,resizable=yes,width=700,height=400");
 popen = true;
<% } %>
<%if  (bean91.isPopupBuscarParte()) {%> popup=window.open("Facturar?accion=5","","toolbar=0,location=0,directories=0,status=0,menubar=0,scrollbars=yes,resizable=yes,width=700,height=400");
 popen = true;
<% } %>
<%if  (bean91.isPopupParte()) {%> popup=window.open("Facturar?accion=7","","toolbar=0,location=0,directories=0,status=0,menubar=0,scrollbars=yes,resizable=yes,width=700,height=400");
 popen = true;
<% } %>
<%if  (bean91.isPopupAplicacion()) {%> popup=window.open("Facturar?accion=13","","toolbar=0,location=0,directories=0,status=0,menubar=0,scrollbars=yes,resizable=yes,width=700,height=400");
 popen = true;
<% } %>
<%if  (bean91.isPopupBuscaAplicacion()) {%> popup=window.open("Facturar?accion=11","","toolbar=0,location=0,directories=0,status=0,menubar=0,scrollbars=yes,resizable=yes,width=700,height=400");
 popen = true;
<% } %>
<%if  (bean91.isPopupLogin()) {%> popup=window.open("Facturar?accion=19","","toolbar=0,location=0,directories=0,status=0,menubar=0,scrollbars=yes,resizable=yes,width=700,height=400");
 popen = true;
<% } %>
<%if  (bean91.isPopupClienteConIVA()) {%> popup=window.open("Facturar?accion=21","","toolbar=0,location=0,directories=0,status=0,menubar=0,scrollbars=yes,resizable=yes,width=700,height=400");
 popen = true;
<% } %>
<%if  (bean91.isPopupImprimir()) {%> popup=window.open("ImprimirFactura","","toolbar=0,location=0,directories=0,status=0,menubar=0,scrollbars=yes,resizable=yes,width=700,height=400");
 popen = true;
<% } %>
function imagen_onclick(){
	popup=window.open("html/imagen.html","","toolbar=0,location=0,directories=0,status=0,menubar=0,scrollbars=yes,resizable=yes,width=700,height=400");
 	popen = true;
}
function precio_onclick(){
	popup=window.open("jsp/9_1o.jsp","","toolbar=0,location=0,directories=0,status=0,menubar=0,scrollbars=yes,resizable=yes,width=700,height=400");
 	popen = true;
}

function sugerir_parte_onclick(){
	popup=window.open("html/partesugerida.html","","toolbar=0,location=0,directories=0,status=0,menubar=0,scrollbars=yes,resizable=yes,width=700,height=400");
 	popen = true;
}

function Facturar_onclick(){
	document.frm.accion.value=18;
	document.frm.submit();
}
function eliminar_onclick(form){
	if (form.i.value==''){
		alert('Seleccione una partida');
		return false;
		}
	form.accion.value=10
	return true;
}
function calculate(form){
	str = form.iidnivel.options[form.iidnivel.selectedIndex].value;
	
	// Obtener el nivel de utilidad
	index = str.indexOf('/');
	index2 = str.indexOf('/',index+1)
	nivel = str.substring(index+1,index2);
	nivel = parseFloat(nivel);
	
	// Obtener el precio deacuerdo al proveedor seleccionado
	if (form.iidproveedor.length == 0){
		alert("Introduzca el numero de parte y presione buscar")
		form.cantidad.value='';
		return
		}
		
	str = form.iidproveedor.options[form.iidproveedor.selectedIndex].value;
	index = str.indexOf('/');
	precio = str.substring(index+1,str.length);
	form.precio.value= precio;
	cantidad = 0;
	if (form.cantidad.value!='')
		cantidad=parseFloat(form.cantidad.value)
	// Calcula utilidad
	//alert ('cantidad='+cantidad+' precio='+precio+' nivel='+nivel);
	<%if (!bean91.isBitutilsobreventa()) {%>
		precionUnitario= formatAsMoney(precio * (1+nivel/100));
	<%}else{%>
		precionUnitario= formatAsMoney(precio / (1-nivel/100));
	<%}%>
	<%if (bean91.isMostradorSinIVA()) {%>
		// Calcular precio mas IVA si es cliente sin IVA desglozado
		precionUnitario = precionUnitario * (1 + form.iva.value/100);
	<%}%>		
	form.importe.value = formatAsMoney(cantidad * precionUnitario);
	
}
function checar_cantidad(name){
	forma = document.frm;
	// Obtener i de existenciai
	i = name.substring(8,name.length);
	existencia = eval("forma.existencia"+i);
	cantidad = eval("forma.cantidad"+i);
	
	if (parseInt(existencia.value) < parseInt(cantidad.value)){
		alert('La cantidad a vender no puede ser mayor a la exitencia');
		cantidad.value='';
	}
	
}

function recalculate(name){
	forma = document.frm;
	if (name.indexOf('nivel')>=0)
		i = name.substring(5,name.length);
	else
		i = name.substring(8,name.length);
	
	importe = eval("forma.importe"+i);
	cantidad = eval("forma.cantidad"+i);
	precio = eval("forma.precio"+i);
	costo = eval("forma.costo"+i);
	nivel = eval("forma.nivel"+i);
	
	str = nivel.options[nivel.selectedIndex].value;
	index = str.indexOf('/');
	index2= str.indexOf('/',index+1);
	nivel = str.substring(index+1,index2);
	nivel = parseFloat(nivel);
	icantidad = 0;
	if (cantidad.value!='')
		icantidad = cantidad.value;
		
	<%if (!bean91.isBitutilsobreventa()) {%>
		precio.value = formatAsMoney(costo.value * (1+nivel/100));
	<%}else{%>
		precio.value = formatAsMoney(costo.value / (1-nivel/100));
	<%}%>
	<%if (bean91.isMostradorSinIVA()) {%>
		// Calcular precio mas IVA si es cliente sin IVA desglozado
		precio.value = formatAsMoney(precio.value * (1 + forma.iva.value/100));
	<%}%>		

	
	importe.value = formatAsMoney(icantidad * precio.value);
	
	total=0;
	i=0;
	while ((importe = eval("forma.importe"+i)) != null){
		
		total+=parseFloat(importe.value);
		i ++;
	}
	forma.subtotal.value=formatAsMoney(total);
	recalculatetotal();
}
	
function recalculatetotal(){
	forma = document.frm;
	forma.montodescuento.value = formatAsMoney(forma.subtotal.value * forma.descuento.value / 100);
	<%if (bean91.isMostradorSinIVA()) {%>
		forma.total.value = formatAsMoney(forma.subtotal.value);
	<%}else{%>		
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

var isAltKeyPressed = false;

function hotkey(eventname){

   forma = document.frm;
   if(eventname.keyCode == 18) 
   	  	isAltKeyPressed = true;
   if(eventname.keyCode == 112 && isAltKeyPressed)  //F1
   	  	forma.iidnocliente.focus();
   if(eventname.keyCode == 113 && isAltKeyPressed)  //F2
   	  	forma.prefijo.focus();
   if(eventname.keyCode == 114 && isAltKeyPressed)  //F3
   	  	forma.iidaplicacion.focus();
   if(eventname.keyCode == 116 && isAltKeyPressed)  //F5
   	  	forma.iidaplicacion.focus();
   if(eventname.keyCode == 117 && isAltKeyPressed)  //F6
		forma.cantidad.focus();
   if(eventname.keyCode == 118 && isAltKeyPressed)  //F7
		Facturar_onclick();
}
function aplicacion_onkeyup(eventname){
	forma = document.frm;
	if (event.keyCode == 13){
		forma.accion.value=14;
		forma.submit();
	}
}

</script>
</head>
<%@ include file="../main.jsp" %>
<body bgcolor="#FFFFFF" text="#000000" onunload="if(popen)popup.close();" onkeydown='hotkey(event)'>
<form name=frm action="Facturar" method="post">
<input type="hidden" name=accion>
<table class="tablatext" width=750 align="center" cellpadding="0" cellspacing="0">
<tr>
	<td><img src="images/tpva9_1.GIF"></td>

        <th>
	<td><a href="../pvatx/hlp/pva9.0-9.1.htm" target="Ayuda Facturar">Ayuda</a></td>
        </th>
</tr>
</table>
<table align=center border=1 borderColor=#364859 cellSpacing=0 class=tablatext rules=cols style="BORDER-BOTTOM: black 1px solid; BORDER-COLLAPSE: collapse; BORDER-LEFT: black 1px solid; BORDER-RIGHT: black 1px solid; BORDER-TOP: black 1px solid; FONT-FAMILY: arial; FONT-SIZE: 11px" width=750>
  <tr>
	<td colspan=3>Cliente</td>
	<td  align=right>Usuario: <%= bean91.getUsuario() %></td>
 </tr>
  <TR class="titStyle"> 
  	  <td width="25%">N&uacute;mero</td>
  	  <td width="25%">Datos generales</td>
	  <td width="25%">Condiciones comerciales</td>
	  <td width="25%">Condiciones de pago</td>
 </tr>
 <tr class="itemStyle">
    <td width="25%">
		<table class="tablatext" align="center" cellpadding="0" cellspacing="0">
		<tr><td><input type=text name='iidnocliente' size=4 class=body value='<%= bean91.getIidnocliente() %>'></td>
			<td><input type=submit name=buscar value=Buscar class=body onclick="this.form.accion.value=4"></td>
		</tr>
		</table>
	</td>	
    <td width="25%"><textarea name="datosgenerales" rows="4" cols="30" class="body" readonly=true><%= bean91.getDatosGenerales() %></textarea></td>
	<td width="25%"><textarea name="condicionescomerciales" rows="4" cols="30" class="body" readonly=true><%= bean91.getCondicionesComerciales() %></textarea></td>
	<td width="25%">
		<textarea name="condicionesdepago" rows="2" cols="30" class="body" readonly=true><%= bean91.getDiasDepago() %></textarea> 
		Crédito disponible 
		<input type=text name=creditodisponible size=10 class=numero readonly=true value=<%= bean91.getCreditoSTR() %> <% if (bean91.getCredito()<0){%>style={color="#ff0000"}<%}%>>
	</td>
 </tr>
</table>
<br>
<table align=center border=1 borderColor=#364859 cellSpacing=0 class=tablatext rules=cols style="BORDER-BOTTOM: black 1px solid; BORDER-COLLAPSE: collapse; BORDER-LEFT: black 1px solid; BORDER-RIGHT: black 1px solid; BORDER-TOP: black 1px solid; FONT-FAMILY: arial; FONT-SIZE: 11px" width=750>
 <TR class="titStyle"> 
  	  <td width="33%">Forma de Pago</td>
  	  <td width="33%">Metodo de Pago</td>
	  <td width="33%">Cuenta de Pago</td>	  
 </tr>
 <tr class="itemStyle">
          <td width="33%">
              <input type=text name="vchformapago" class="body" size=16 value="<%= bean91.getVchformapago()%>">
          </td>	
          <td width="33%">
              <input type=text name="vchmetodopago" class="body" size=16 value="<%= bean91.getVchmetodopago()%>">
          </td>	
          <td width="33%">
              <input type=text name="vchcuentapago" class="body" size=16 value="<%= bean91.getVchcuentapago()%>">
          </td>	
 </tr>
</table>
<br>
<table align="center" border="1" bordercolor="#364859" rules="cols" cellspacing="0" width="750" style="border-color:Black;border-width:1px;border-style:solid;font-family: arial; font-size: 11px;border-collapse:collapse;" class="tablatext">
<tr class="headerStyle">
	  <td width="20%">SKU</td>
	  <td width="10%"></td>
	  <td width="10%">Proveedor</td>
	  <td width="10%">Nivel</td>
	  <td width="5%">Imagen</td>
	  <td width="5%">Precio</td>
	  <td width="5%">Cantidad</td>
	  <td width="10%">Importe</td>
	  <td width="10%"></td>
	  <td width="10%"></td>
</tr>
<tr class="itemStyle">
	<td width="20%"><input type=text name="vchnumparte" class="body" size=16 value="<%= bean91.getVchnumparte()%>"></td>
	<td width="10%"><input type=submit name=buscar value=Buscar class=body onclick="this.form.accion.value=8"></td>
	<td width="10%"><select name="iidproveedor" class="body" onchange="calculate(this.form)">
				<%for (int i=0; i<bean91.getProveeSize();i++){%>
                                <option value="<%= i %>/<%= bean91.getProveeDecprecio(i) %>" <% if (bean91.getProveeBitprovefavorito(i)){%> SELECTED <% } %>><%= bean91.getProveeIidproveedor(i) %> - <%= bean91.getProveeVchnombre(i) %></option>
				<%}%>
				</select>
	</td>

	<td width="10%"><select name="iidnivel" class="body" onchange="calculate(this.form)">
				<%for (int i=0; i<bean91.getVNivelesSize();i++){%>
				<option value="<%= bean91.getIidnivel(i) %>/<%= bean91.getDecutilidad(i) %>/<%= bean91.getVchnivel(i) %>" <% if (bean91.getIidnivelCliente()==bean91.getIidnivel(i)){%> SELECTED <% } %>><%= bean91.getVchnivel(i) %></option>
				<%}%>
				</select>
	</td>
	<td width="5%"><input type=button name=IMG value=IMG class=body onclick="imagen_onclick()" <%if (bean91.getLiidparte()==0){%>DISABLED<%}%> ></td>
	<td width="5%"><input type=button name=IMG value=PRC class=body onclick="precio_onclick()" <%if (bean91.getLiidparte()==0){%>DISABLED<%}%> ></td>
	<td width="5%"><input type=text name="cantidad" class=numero size=4 maxlength="5" value="1"  onkeyup="calculate(this.form)" onkeypress="return numbersonly()"></td>
	<td width="10%"><input type=text name="importe" class="numero" readonly="true" size=15 value="<%= bean91.getPrecioSTR() %>"></td>
	<td width="10%"><input type=submit value=Agregar class=body onclick="this.form.accion.value=9" <%if (bean91.getProveeSize()==0){%>DISABLED<%}%>></td>
	<td width="10%"><input type=submit value=Eliminar class=body onclick="return eliminar_onclick(this.form)"></td>
</tr>
</table>
<table width=750 align="center" border="1" bordercolor="#364859" rules="cols" cellspacing="0" width="750" style="border-color:Black;border-width:1px;border-style:solid;font-family: arial; font-size: 11px;border-collapse:collapse;" class="tablatext">
<tr class="headerStyle">
	  <td width="50%">Descripción</td>
	  <td width="50%">Clasificación</td>
	  
</tr>
<tr class="itemStyle">
	<td width="50%"><textarea  rows="2" cols="60" class="body" readonly=true><%= bean91.getVchdescripciones() %></textarea>
		Observaciones<textarea  rows="2" cols="60" class="body" readonly=true><%= bean91.getVchobservaciones() %></textarea></td>
	<td width="50%"><textarea  rows="5" cols="60" class="body" readonly=true><%= bean91.getFichatecnica() %></textarea></td>
	
</tr>
</table>
<br>
<table width=750 align="center" border="1" bordercolor="#364859" rules="cols" cellspacing="0" width="750" style="border-color:Black;border-width:1px;border-style:solid;font-family: arial; font-size: 11px;border-collapse:collapse;" class="tablatext">
  <tr class="headerStyle">
  	  <td width="5%">Sel</td>
	  <td width="5%">Exis.</td>
	  <td width="20%" colspan=4 align=center >Número de parte</td>
	  <td width="15%">Descripción</td>
	  <td width="15%">Proveedor</td>
	  <td width="10%">Nivel</td>
	  <td width="10%">Cantidad</td>
	  <td width="10%">P. Unitario</td>
	  <td width="10%">Importe</td>
    </tr>
	<% String stilo;
	for (int i = 0; i < bean91.getVPartidasSize(); i++) {
	 	if (i % 2 == 0)stilo = "itemStyle";
		else stilo = "alternatingItemStyle"; 
	%>
    <tr class="<%=stilo%>">
  	<td width="5%"><input type=radio name=temp value="<%= i %>" onclick="this.form.i.value=this.value"></td>
	<td width="5%" <%if (bean91.getVPartidasExistenciaMayorCantidad(i)) {%>style={color="#ff0000"}<%}%>><%= bean91.getVPartidasExistencia(i) %><input type=hidden name="existencia<%= i%>" value="<%= bean91.getVPartidasExistencia(i) %>"></td>
    <td width="20%" colspan=4><%= bean91.getVchnumparte(i) %></td>
	<td width="15%"><%= bean91.getVPartidasDescripcion(i) %></td>
	<td width="15%"><%= bean91.getVPartidasVchrazonsocial(i) %></td>
	<td><select name="nivel<%= i%>" class="body" onchange="recalculate(this.name)">
<%
for (int j=0; j<bean91.getVNivelesSize();j++){
%>
				<option value="<%= bean91.getIidnivel(j) %>/<%= bean91.getDecutilidad(j) %>/<%= bean91.getVchnivel(j) %>" <% if (bean91.getVPartidasNivel(i)==bean91.getIidnivel(j)){%> SELECTED <% } %>><%= bean91.getVchnivel(j) %></option>
<%
}
%>   		    </select>
	</td>
	<td width="10%"><input type=text name="cantidad<%= i%>" class="numero" size=4 maxlength="5" value="<%= bean91.getVPartidasCantidad(i) %>" onkeyup="checar_cantidad(this.name);recalculate(this.name)" onkeypress="return numbersonly()"></td>
	<td width="10%">
		<input type=text name="precio<%= i%>" class="numero" readonly=true size=10 value="<%= bean91.getVPartidasPrecioUnitarioSTR(i) %>">
		<input type=hidden name="costo<%= i%>" value="<%= bean91.getVPartidasDecprecio(i) %>"></td>
	<td width="10%"><input type=text name="importe<%= i%>" class="numero" readonly=true size=15 value="<%= bean91.getVPartidasImporteSTR(i) %>"></td>

   </tr>
<%
}
%>

</table>
<table width=750 align="center" border="1" bordercolor="#364859" rules="cols" cellspacing="0" width="750" style="border-color:Black;border-width:1px;border-style:solid;font-family: arial; font-size: 11px;border-collapse:collapse;" class="tablatext">
<tr class="itemStyle">
	  <td width="40%"></td>
	  <td width="20%"></td>
	  <td width="40%" align=right></td>
</tr>
<tr class="itemStyle">
	<td width="40%"></td>
	<td width="20%"></td>
	<td width="40%" align=right>
	<table class="tablatext" cellpadding="0">
	<tr><td align="right" colspan=2>Subtotal</td>
		<td><input type=text name="subtotal" size=15 class=numero readonly=true value="<%= bean91.getSubtotalSTR() %>"></td>
	</tr>
	<tr><td align="right"><%if (!bean91.isMostradorSinIVA()){%>Descuento<%}%></td>
		<td><input type=<%if (bean91.isMostradorSinIVA()){%>hidden<%}else{%>text<%}%> name="descuento" size=5 class=numero value="<%= bean91.getDescuentoSTR() %>" onkeyup="recalculatetotal()" <%if (bean91.isMostradorConIVA()){%>DISABLED<%}%>> </td>
		<td><input type=<%if (bean91.isMostradorSinIVA()){%>hidden<%}else{%>text<%}%> name="montodescuento" size=15 class=numero readonly=true value="<%= bean91.getMontodescuentoSTR() %>"> </td>
	</tr>
	<tr><td align="right"><%if (!bean91.isMostradorSinIVA()){%>Iva<%}%></td>
		<td><input type=<%if (bean91.isMostradorSinIVA()){%>hidden<%}else{%>text<%}%> name="iva" size=5 class=numero readonly=true value="<%= bean91.getIvaSTR() %>"></td>
		<td><input type=<%if (bean91.isMostradorSinIVA()){%>hidden<%}else{%>text<%}%> name="montoiva" size=15 class=numero readonly=true value="<%= bean91.getMontoivaSTR() %>"></td>
	</tr>
	<tr><td align="right" colspan=2>Total</td>
		<td><input type=text name="total" size=15 class=numero readonly=true value="<%= bean91.getTotalSTR() %>"></td>
	</tr>
	</table>
	</td>
</tr>
</table>
<br>
<table width=750 align="center" border="1" bordercolor="#364859" rules="cols" cellspacing="0" width="750" style="border-color:Black;border-width:1px;border-style:solid;font-family: arial; font-size: 11px;border-collapse:collapse;" class="tablatext">
<tr class="titStyle">
	<td align=center><input type="button" class=body value="Facturar" onclick="return Facturar_onclick()" <%if (bean91.getVPartidasSize()==0){%>DISABLED<%}%>></td>
</tr>
</table>
<table class="tablatext" width=750 align="center" cellpadding="0" cellspacing="0">
<tr>
	<td><%= bean91.getMsg() %></td>
</tr>
</table>
<input type="hidden" name=precio value="<%= bean91.getDecprecio() %>">
<input type="hidden" name=liidparte>
<input type="hidden" name=i>
<input type="hidden" name="user">
<input type="hidden" name="password">
<input type="hidden" name="vchformapago">
<input type="hidden" name="vchmetodopago">
<input type="hidden" name="vchcuentapago">
</form>
</body>
</html>
