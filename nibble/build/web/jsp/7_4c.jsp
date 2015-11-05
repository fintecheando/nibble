<html>
<jsp:useBean id="bean74c" class="org.nibble.service.vo.pedidos.Bean74c" scope="session"/>
<jsp:useBean id="bean74" class="org.nibble.service.vo.pedidos.Bean74" scope="session"/>

<head>
<title>NC 6.4 Consulta, Recepción y Modificación a Pedidos</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link href="lib/estilo.css" rel=STYLESHEET type=text/css>
<script language=JavaScript>
function guardar_onclick(){
	var errors = '';
	var numErrors = 0;
	forma=document.frm;
	size = parseInt(forma.size.value);
	
	for (i=0 ; i<size;i++){
		cantidad = eval("forma.cantidad"+i);
		if (cantidad==null)
			continue;
		pedida = eval("forma.pedida"+i);
		tiestatus = eval("forma.tiestatus"+i);	
	
		if (cantidad.value!='')
			icantidad = parseInt(cantidad.value);
		else
			icantidad = 0;
		ipedida = parseInt(pedida.value);
		itiestatus = parseInt(tiestatus[tiestatus.selectedIndex].value);
		descripcion = tiestatus[tiestatus.selectedIndex].text;
		
		partida = i+1;

		if (icantidad > 0 && ipedida > icantidad && itiestatus != <%= bean74c.getIDPARCIALMENTE() %> && itiestatus != <%= bean74c.getIDCANCELADO() %> ){
			errors += '- 1 El estatus no puede ser '+descripcion+' para la partida ' + partida + '\n';
			numErrors++;
		}
		if (ipedida <= icantidad && itiestatus != <%= bean74c.getIDRECIBIDO() %> && itiestatus != <%= bean74c.getIDCANCELADO() %>){
			errors += '-  2 El estatus no puede ser '+descripcion+' para la partida ' + partida + '\n';
			numErrors++;
		}
		if (ipedida < icantidad && itiestatus != <%= bean74c.getIDPARCIALMENTE() %> && itiestatus != <%= bean74c.getIDCANCELADO() %> && itiestatus != <%= bean74c.getIDRECIBIDO() %>){
			errors += '- 3 El estatus no puede ser '+descripcion+' para la partida ' + partida + '\n';
			numErrors++;
		}
	}
	if (numErrors) {
		errors = 'Se produjeron ' + numErrors + ' error(es)\n'+ errors;
		alert(errors);
		return false;
	}
	return true;
}

function descuento(){
	forma=document.frm;
    forma.tdesc.value=(parseFloat(forma.total.value)*parseFloat(forma.descu.value))/100;	
	forma.tdesc.value=formatAsMoney(forma.tdesc.value);
}

function cambia(name){
	forma=document.frm;
	// cantidad__
	i = name.substring(8,name.length);
	cantidad = eval("forma.cantidad"+i);
	pedida = eval("forma.pedida"+i);
	costo = eval ("forma.costo"+i);
	
	if (cantidad.value!='')
		icantidad = parseInt(cantidad.value);
	else
		icantidad = 0;
	
	ipedida = parseInt(pedida.value);
	total = cantidad.value * costo.value;
	eval ("forma.tp"+i+".value="+ total);
	if (icantidad == 0)
		sel(i,<%= bean74c.getIDSINSURTIR() %>);
	else if (icantidad < ipedida)
		sel(i,<%= bean74c.getIDPARCIALMENTE() %>);
	else if (icantidad >= ipedida)
		sel(i,<%= bean74c.getIDRECIBIDO() %>);
	
	totaliva=0;
	total=0;
	totaltp=0;
	for (i = 0; i < forma.length; i++){
	  if (forma.elements[i].name.substring(0,8)=="cantidad"){
	  	if (forma.elements[i].value != "")
	  		total+=parseFloat(forma.elements[i].value);
	  }
	  if (forma.elements[i].name.substring(0,2)=="tp"){
	  	if (forma.elements[i].value != "")
	  		totaltp+=parseFloat(forma.elements[i].value);
	  }
	}
	forma.iva.value=(totaltp*forma.factoriva.value)/100;
	forma.crt.value=total;
	forma.stp.value=totaltp;
	forma.total.value=(totaltp+parseFloat(forma.iva.value));
	descuento();
	forma.total.value=formatAsMoney(forma.total.value);
	forma.iva.value=formatAsMoney(forma.iva.value);
	
}
function sel(i,valor){
	forma=document.frm;
	tiestatus_i = eval("forma.tiestatus"+i);	
	for (a=0; a < tiestatus_i.length; a++)
		if (tiestatus_i[a].value == valor)
			tiestatus_i[a].selected=true;
}
function spam_onclick(){
	forma=document.frm;
	factura = forma.factura.value;
	remision = forma.remision.value;
	i=0;
	while ((facturai = eval("forma.factura"+i)) != null){
		facturai.value = factura;
		remisioni = eval("forma.remision"+i);
		remisioni.value = remision;
		i ++;
	}
function formatAsMoney(mnt) {
    mnt -= 0;
    mnt = (Math.round(mnt*100))/100;
    return (mnt == Math.floor(mnt)) ? mnt + '.00' 
              : ( (mnt*10 == Math.floor(mnt*10)) ? 
                       mnt + '0' : mnt);
}
}

</script>
</head>
<%@ include file="../main.jsp" %>
<body bgcolor="#FFFFFF" text="#000000">
<form name=frm action="Pedidos" method="get">
<input type="hidden" name=accion value=4>
<input type="hidden" name=factoriva value=15>
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
<tr class="itemStyle">
	<td colspan=3>Proveedor: <%= request.getParameter("vchrazonsocial") %></td>
	<td colspan=4>Elaborado: <%= request.getParameter("tisfechelabpedido") %></td>
	<td colspan=3>Pedido: <%= request.getParameter("iidpedido") %></td>
</tr>
<tr class="titStyle">
	  <td width="10%">Parte</td>
	  <td width="10%">Estatus</td>
	  <td width="4%">Cant. Orig</td>
	  <td width="4%">Cant. Pend</td>
	  <td width="10%">Cant. recibida</td>
	  <td width="10%">Fact. prov</td>
	  <td width="10%">Costo. Unit.</td>
	  <td width="10%">Total Partida</td>
	  <td width="12%">Ult. recep.</td>
	  <td width="15%">Comentarios</td>
 </tr>
<% String stilo;
	double stp=0.0;
	int cot=0,cpt=0,crt=0;
	for (int i = 0; i < bean74c.getSize(); i++) {
	 	if (i % 2 == 0)stilo = "itemStyle";
		else stilo = "alternatingItemStyle"; 
	%>
  <tr class="<%=stilo%>">
    <td width="10%"><%= bean74c.getVchnumparte(i) %></td>
	<td width="10%">
    <%if (bean74c.getTiestatus(i)!=bean74c.getIDCANCELADO() || bean74c.getTiestatus(i)!=bean74c.getIDRECIBIDO()){%>
	<select name="tiestatus<%= i %>" class="body">
			<%for (int j=0; j<bean74.getEstatusPedidosSize();j++){%>
				<option value=<%= bean74.getTiestatus(j) %> <%if (bean74.getTiestatus(j)==bean74c.getTiestatus(i)){%>SELECTED<%}%> ><%= bean74.getVchdescestatus(j) %></option>
			<%}%> 
	</select> 
	<%} else {%>
	<%= bean74c.getVchdescestatus(i) %>
	<%}%>
	</td>
	<input type=hidden name="pedida<%= i %>" value="<%= bean74c.getIcantpedida(i) %>">
	<%if (bean74c.getTiestatus(i)!=bean74c.getIDCANCELADO() || bean74c.getTiestatus(i)!=bean74c.getIDRECIBIDO() ){
		cot +=  bean74c.getIcantpedida(i);
		cpt += (bean74c.getIcantpedida(i) - bean74c.getIcantrecibida(i));
		crt += bean74c.getIcantrecibida(i);
		stp += (bean74c.getIcantrecibida(i) * bean74c.getVdeccostof(i));
	%>
	<td width="4%"><input type=text name="co<%= i %>" class="body" size=4" value="<%= bean74c.getIcantpedida(i) %>" disabled></td>
	<td width="4%"><input type=text name="cp<%= i %>" class="body" size=4" value="<%= bean74c.getIcantpedida(i) - bean74c.getIcantrecibida(i)%>" disabled></td>	
	<td width="5%"><input type=text name="cantidad<%= i %>" class="body" size=4" value="<%= bean74c.getIcantrecibida(i) %>" onkeyup="cambia(this.name)"></td>
	<td width="10%"><input type=text name="factura<%= i %>" class="body" size=4" value="<%= bean74c.getVchfactura(i) %>"></td>
    <td width="10%"><input type=text name="costo<%= i %>" class="body" size=4" value="<%= bean74c.getVdeccosto(i) %>"></td>	
<!--	<td width="10%"><input type=text name="remision<%= i %>" class="body" size=4" value="<%= bean74c.getVchremisionproveed(i) %>"></td> -->
	<td width="10%"><input type=text name="tp<%= i %>" class="body" size=4" value="<%= bean74c.getIcantrecibida(i) * bean74c.getVdeccostof(i)%>" disabled></td>
	<td width="12%"><%= bean74c.getDtfechultrecepcion(i) %></td>
	<td width="10%"><input type=text name="comentario<%= i %>" class="body" size=15" value="<%= bean74c.getVchcomentario(i) %>"></td>
	<%} else{
		cot +=  bean74c.getIcantpedida(i);
		cpt += (bean74c.getIcantpedida(i) - bean74c.getIcantrecibida(i));
		crt += bean74c.getIcantrecibida(i);
		stp += (bean74c.getIcantrecibida(i) * bean74c.getVdeccostof(i));
	%>
	
	
	<td width="4%"><input type=text name="co<%= i %>" class="body" size=4" value="<%= bean74c.getIcantpedida(i) %>" disabled></td>
	<td width="4%"><input type=text name="cp<%= i %>" class="body" size=4" value="<%= bean74c.getIcantpedida(i) - bean74c.getIcantrecibida(i)%>" disabled></td>	
	<td width="5%"><%= bean74c.getIcantrecibida(i) %></td>
	<td width="10%"><%= bean74c.getVchfactura(i) %></td>
	<td width="10%"><%= bean74c.getVdeccosto(i) %></td>
<!--	<td width="10%"><%= bean74c.getVchremisionproveed(i) %></td> -->
	<td width="10%"><input type=text name="tp<%= i %>" class="body" size=4" value="<%= bean74c.getIcantrecibida(i) * bean74c.getVdeccostof(i)%>"disabled></td>
	<td width="12%"><%= bean74c.getDtfechultrecepcion(i) %></td>
	<td width="10%"><%= bean74c.getVchcomentario(i) %></td>	
	<%}%>
   </tr>
<%
}
%> 
<tr class="itemStyle">
	    <td colspan=2  align=right>Subtotal</td>

		<td width="4%"><input type=text name="cot" class="body" size=4" value="<%=cot%>" disabled></td>
		<td width="4%"><input type=text name="cpt" class="body" size=4" value="<%=cpt%>" disabled></td>
		<td width="5%"><input type=text name="crt" class="body" size=4" value="<%=crt%>" disabled></td>
	    <td width="10%"></td>
	    <td width="10%"></td>
	    <td width="10%"><input type=text name="stp" class="body" size=4" value="<%=stp%>" disabled></td>
	    <td width="12%"></td>
	    <td width="10%"></td>
</tr>
<tr class="itemStyle">
	    <td colspan=5></td>
	    <td width="10%"></td>
	    <td width="10%">IVA</td>
	    <td width="10%"><input type=text name="iva" class="body" size=4" value="0" disabled></td>
	    <td width="12%"></td>
	    <td width="10%"></td>
</tr>
<tr class="itemStyle">
		<td colspan=5></td>
	    <td width="10%"></td>
	    <td width="10%">TOTAL</td>
	    <td width="10%"><input type=text name="total" class="body" size=4" value="0" disabled></td>
	    <td width="12%"></td>
	    <td width="10%"></td>
</tr>
<tr class="itemStyle">
	    <td colspan=5></td>
	    <td width="10%"></td>
	    <td width="10%">%D.P.P</td>
	    <td width="10%"></td>
	    <td width="12%">Vencimiento</td>
	    <td width="10%">Vencimiento P.P.</td>
</tr>
<tr class="itemStyle">
	    <td colspan=5 align=right>
		<input type=radio name="rem" class="body" size=4" value="0" checked>Factura <input type=radio name="rem" class="body" size=4" value="1">Remision</td>
	    <td width="10%"><input type=text name="factur" class="body" size=4" value="0" ></td>
	    <td width="10%"><input type=text name="descu" class="body" size=4" value="0" onkeyup="descuento()" ></td>
	    <td width="10%"><input type=text name="tdesc" class="body" size=4" value="0" disabled></td>
	    <td width="12%"><input type=text name="venc" class="body" size=12" value="0" disabled></td>
	    <td width="10%"><input type=text name="vencpp" class="body" size=12" value="0" disabled></td>
</tr>

</table>
<br>
<table align="center" border="1" bordercolor="#364859" rules="cols" cellspacing="0" width="750" style="border-color:Black;border-width:1px;border-style:solid;font-family: arial; font-size: 11px;border-collapse:collapse;" class="tablatext">
	<tr class="titStyle">
		<td align=center><input type="submit" value="Modificar" class=body onclick="return guardar_onclick()">	</td>
	</tr>
</table>
<br>
<table align="center" border="1" bordercolor="#364859" rules="cols" cellspacing="0" width="750" style="border-color:Black;border-width:1px;border-style:solid;font-family: arial; font-size: 11px;border-collapse:collapse;" class="tablatext">
<tr class="titStyle">
	<td width="10%">Fact. prov.</td>
	<td width="10%">Rem. prov</td>
	<td width="80%"></td>
</tr>
<tr class="itemStyle">		
	<td> <input type=text name="factura" class="body" size=4></td>
	<td> <input type=text name="remision" class="body" size=4></td>
	<td> <input type="button" value="Spam" class=body onclick="return spam_onclick()"></td>
</tr>
</table>
<br>
<table align="center" width="750">
	<tr><td><a href="javascript:document.frm.accion.value=2;document.frm.submit();" class="ligas"><< Regresar</a></td></tr>
</table>
<input type="hidden" name="size" value="<%= bean74c.getSize() %>" > 
<input type="hidden" name="iidpedido" value="<%= request.getParameter("iidpedido") %>"> 
<input type="hidden" name="vchrazonsocial" value="<%= request.getParameter("vchrazonsocial") %>">
<input type="hidden" name="tisfechelabpedido" value="<%= request.getParameter("tisfechelabpedido") %>">

</form>
</body>
</html>
