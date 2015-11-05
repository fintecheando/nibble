<html>
<jsp:useBean id="bean42" class="pva.beans4.Bean42" scope="session"/>
<jsp:useBean id="bean101" class="pva.beans10.Bean101" scope="session"/>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link href="lib/estilo.css" rel=STYLESHEET type=text/css>
<title>PVA 10.1 Pagos</title>

<script language="JavaScript" src="lib/calendar2.js"></script>


<script type="text/javascript" src="lib/util.js"></script>
<script language="javascript">
var
  popen = false;
  
function Popup(ref){
 popup=window.open(ref,"Popup","toolbar=0,location=0,directories=0,status=0,menubar=0,scrollbars=yes,resizable=yes,width=600,height=400");
 popen = true;
}

function detalle(accion,docto,tipo,cliente)
{
 ref = "Pagos?accion="+accion+"&doc="+docto+"&tipo="+tipo+"&idcliente="+cliente;
 Popup(ref);
 popup.focus();
}

function tipopago_onclick(valor,foco){
	if (valor == "1"){
		document.frm.banco.disabled = true;
		document.frm.cheque.disabled = true;
		document.frm.fcob.disabled = true;
		document.frm.pago.disabled = false;
		document.frm.cambio.disabled = false;
		document.frm.bancot.disabled = true;
		document.frm.tarjeta.disabled = true;	
		if (foco) {
			document.frm.pago.focus();
			document.frm.pago.select();
		}
	}
	if (valor == "2"){
		document.frm.banco.disabled = false;
		document.frm.cheque.disabled = false;
		document.frm.fcob.disabled = false;
		document.frm.pago.disabled = true;
		document.frm.cambio.disabled = true;
		document.frm.bancot.disabled = true;
		document.frm.tarjeta.disabled = true;			
		if (foco) document.frm.banco.focus();
	}
	if (valor == "3"){
		document.frm.banco.disabled = true;
		document.frm.cheque.disabled = true;
		document.frm.fcob.disabled = true;
		document.frm.pago.disabled = true;
		document.frm.cambio.disabled = true;
		document.frm.bancot.disabled = false;
		document.frm.tarjeta.disabled = false;			
		if (foco) document.frm.bancot.focus();
	}
}

function Accion_onclick(valor){
	var errors = '';
 	var numErrors = 0;
	forma = document.frm;

	if (valor == 3){ // Realiza pago
		if (forma.tipopago[0].checked){	 // Efectivo
			if (forma.total.value=="" || forma.total.value==0){
				errors += '- Para registrar un pago debe abonar al menos a un documento\n';
				numErrors++;	
			}
			if (forma.pago.value=="" || forma.pago.value==0 || (parseFloat(forma.pago.value) < parseFloat(forma.total.value))){
				errors += '- No ha proporcionado el monto del pago o el monto proporcionado es menor al total a cobrar\n';
				numErrors++;	
			}
		}
		if (forma.tipopago[1].checked){ // Cheque
			if (forma.total.value=="" || forma.total.value==0){
				errors += '- Para registrar un pago debe abonar al menos a un documento\n';
				numErrors++;	
			}
			if (forma.banco.value==""){
				errors += '- Para registrar el pago debe indicar el Banco\n';
				numErrors++;	
			}
			if (forma.cheque.value==""){
				errors += '- Se requiere el número de cheque\n';
				numErrors++;	
			}
			if (forma.fcob.value==""){
				errors += '- Se requiere la fecha de cobro del cheque\n';
				numErrors++;	
			}		
		}
		if (forma.tipopago[2].checked){ // Tarjeta Crédito
			if (forma.total.value=="" || forma.total.value==0){
				errors += '- Para registrar un pago debe abonar al menos a un documento\n';
				numErrors++;	
			}
			if (forma.bancot.value==""){
				errors += '- Para registrar el pago debe indicar el Banco\n';
				numErrors++;	
			}
			if (forma.tarjeta.value==""){
				errors += '- Se requiere el número de tarjeta de crédito\n';
				numErrors++;	
			}
		}
		
		if (numErrors) {
			errors = 'Se produjeron ' + numErrors + ' error(es)\n'+ errors;
			alert(errors);
			return false;
		}		
	}
	forma.accion.value = valor;
	return true;
}

function idcliente_onchange(){
	ind = document.frm.idcliente.selectedIndex;
	document.frm.rscliente.options[ind].selected = true;
	document.frm.rfcliente.options[ind].selected = true;
}

function rscliente_onchange(){
	ind = document.frm.rscliente.selectedIndex;
	document.frm.idcliente.options[ind].selected = true;
	document.frm.rfcliente.options[ind].selected = true;
}

function rfcliente_onchange(){
	ind = document.frm.rfcliente.selectedIndex;
	document.frm.idcliente.options[ind].selected = true;
	document.frm.rscliente.options[ind].selected = true;
}

function formatAsMoney(mnt) {
    mnt -= 0;
    mnt = (Math.round(mnt*100))/100;
    return (mnt == Math.floor(mnt)) ? mnt + '.00': ( (mnt*10 == Math.floor(mnt*10)) ? mnt + '0' : mnt);
}

function calcula(){
	forma = document.frm;
	total=0;
	for (i = 0; i < forma.length; i++){
	  if (forma.elements[i].name.substring(0,2)=="ab"){
	  	if (forma.elements[i].value != "")
	  		total+=parseFloat(forma.elements[i].value);
	  }
	}
	forma.total.value=formatAsMoney(total);
	forma.ingreso.value=formatAsMoney(total);	
}

function cambia(){
	forma = document.frm;
	if (forma.tipopago[0].checked){
		if (forma.pago.value != ""){
			total= parseFloat(forma.pago.value) - parseFloat(forma.total.value);
			if (total > 0)
				forma.cambio.value=formatAsMoney(total);
			else forma.cambio.value=formatAsMoney(0);	
		}else forma.cambio.value="0.00";
	}else{	
		forma.pago.value="0.00";
		forma.cambio.value="0.00";
	}
}

</script>	
</head>
<%@ include file="../main.jsp" %>
<body <% if (bean101.isDetalle()){%>onload="tipopago_onclick('1',false)"<%}%> onunload="if(popen)popup.close();">
<form name=frm action="Pagos" method="POST">

<input type=hidden name="accion">
<table class="tablatext" width=750 align="center" cellpadding="0" cellspacing="0">
<tr>
	<td><img src="images/tpva10_1.GIF"></td>

        <th>
	<td><a href="../pvatx/hlp/pva10.0-10.1.htm" target="Ayuda Pagos">Ayuda</a></td>
        </th>

</tr>
</table>
<table align=center border=1 borderColor=#364859 cellSpacing=0 class=tablatext rules=cols style="BORDER-BOTTOM: black 1px solid; BORDER-COLLAPSE: collapse; BORDER-LEFT: black 1px solid; BORDER-RIGHT: black 1px solid; BORDER-TOP: black 1px solid; FONT-FAMILY: arial; FONT-SIZE: 11px" width=750>
    <tr> 
      <TD colSpan=3>Selección de Cliente</TD>
      <TD colSpan=2>Fecha de búsqueda</TD>
    </TR>
    <TR class="titStyle"> 
      <TD width="11%">Número</TD>
      <TD width="26%">Razón Social</TD>
      <TD width="17%">R.F.C</TD>
      <TD width="23%">Desde:</TD>
      <TD width="23%">Hasta:</TD>
    </TR>
    <TR> 
      <TD width="11%"> 
		<select name="idcliente" class="body" onChange="idcliente_onchange()">
			 <%for (int i=0; i<bean42.getClientesSize();i++){%>
			 <option value=<%= bean42.getId(i)%> <%if (bean101.getIdcliente()==bean42.getId(i)){%> SELECTED <%}%>> <%= bean42.getId(i) %></option>
			 <%}%>
		</select>
      <TD width="26%"> 
		<select name="rscliente" class="body" onChange="rscliente_onchange()">
			 <%for (int i=0; i<bean42.getClientesSize();i++){%>
			 <option value=<%= bean42.getId(i)%> <%if (bean101.getIdcliente()==bean42.getId(i)){%> SELECTED <%}%>><%= bean42.getRazonsocial(i) %></option>
			 <%}%>
		</select>
      </TD>
      <TD width="17%"> 
			<select name="rfcliente" class="body" onChange="rfcliente_onchange()">
			 <%for (int i=0; i<bean42.getClientesSize();i++){%>
			  <option value=<%= bean42.getId(i)%> <%if (bean101.getIdcliente()==bean42.getId(i)){%> SELECTED <%}%>><%= bean42.getRfc(i) %></option>
			 <%}%>
			</select>  
      </TD>
      <TD width="23%"> 
        <input type=text name=fini>
                <a href="javascript:cal5.popup();;" onMouseOut="window.status='';return true;" onMouseOver="window.status='Seleccione Fecha';return true;"><img border=0 src="images/calendar.gif"></a>		
	  </TD>
      <TD width="23%"> 
        <input type=text name=ffin>
        <a href="javascript:cal6.popup();" onMouseOut="window.status='';return true;" onMouseOver="window.status='Seleccione Fecha';return true;"><img border=0 src="images/calendar.gif"></a>		
	  </TD>
	  
<SCRIPT LANGUAGE="JavaScript">

			var cal5 = new calendar2(document.forms['frm'].elements['fini']);
			cal5.year_scroll = true;
			cal5.time_comp = false;
			
			var cal6 = new calendar2(document.forms['frm'].elements['ffin']);
			cal6.year_scroll = true;
			cal6.time_comp = false;					
		</SCRIPT>
	  
    </TR>
    <TR class="titStyle"> 
      <TD align="center" colSpan=5> 
          <input type=submit value="Buscar" class="body" name="submit" onClick="return Accion_onclick(2)">
      </TD>
    </TR>
  </TABLE>
<br>
<% if (bean101.isDetalle()){%>
	<table width=750 align="center" border="1" bordercolor="#364859" rules="cols" cellspacing="0" width="750" style="border-color:Black;border-width:1px;border-style:solid;font-family: arial; font-size: 11px;border-collapse:collapse;" class="tablatext">
		<tr class="headerStyle">
			<td>Docto.</td>
		  	<td>Emisión</td>
		  	<td>Vencimiento</td>
		  	<td>Importe</td>
		  	<td>Saldo</td>
		  	<td>Abono</td>
		  	<td>Saldar</td>
		</tr>	
		<% String stilo;
		   for (int i = 0; i < bean101.sizeCxc(); i++) {
	 		 if (i % 2 == 0)stilo = "itemStyle";
			 else stilo = "alternatingItemStyle"; 
		 %>
			<tr class="<%=stilo%>">
			  <td align="center"><a href="javascript:detalle('4','<%=bean101.getIiddoctoorigen(i)%>','<%=bean101.getTiidtipodocto(i)%>','<%=bean101.getIdcliente()%>');" class="ligas"><%=bean101.getVchDestipodocto(i)%> <%=bean101.getIiddoctoorigen(i)%></a></td>
			  <td><%=bean101.getTisfechamovimiento(i)%></td>	
			  <td><%=bean101.getTisfechavencimient(i)%></td>
			  <td align="right"><%=bean101.getDecdebe(i)%></td>
			  <td align="right"><%if (bean101.isVencido(i)){%><font color="#ff0000"><%}else{%><font color="#000000"><%}%><%=bean101.getDecsaldo(i)%></font></td>
			  <td align="center"><input type="text" name="ab<%=bean101.getIiddoctoorigen(i)%>_<%=bean101.getTiconsecsubdocto(i)%>_<%=bean101.getTiidtipodocto(i)%>" onkeyup="if (this.value > <%=bean101.getDecsaldoq(i)%>){alert('No puede abonar más de lo que debe'); this.value=''; this.focus();}if (document.frm.c<%=bean101.getIiddoctoorigen(i)%>.checked) document.frm.c<%=bean101.getIiddoctoorigen(i)%>.checked = false; calcula(); cambia();" onkeypress="return numbersonly()"></td>
			  <input type=hidden name="saldado<%=bean101.getIiddoctoorigen(i)%>" value="0">
			  <td align="center"><input type="checkbox" name="c<%=bean101.getIiddoctoorigen(i)%>" onClick="if (this.checked){document.frm.ab<%=bean101.getIiddoctoorigen(i)%>_<%=bean101.getTiconsecsubdocto(i)%>_<%=bean101.getTiidtipodocto(i)%>.value = <%=bean101.getDecsaldoq(i)%>}else{document.frm.ab<%=bean101.getIiddoctoorigen(i)%>_<%=bean101.getTiconsecsubdocto(i)%>_<%=bean101.getTiidtipodocto(i)%>.value = ''} calcula(); cambia();if (this.checked){document.frm.saldado<%=bean101.getIiddoctoorigen(i)%>.value='1'};"></td>
			</tr>
		<%}%>
	</table>
	<br>
	<table align=center border=1 borderColor=#364859 cellSpacing=0 class=tablatext rules=cols style="BORDER-BOTTOM: black 1px solid; BORDER-COLLAPSE: collapse; BORDER-LEFT: black 1px solid; BORDER-RIGHT: black 1px solid; BORDER-TOP: black 1px solid; FONT-FAMILY: arial; FONT-SIZE: 11px" width=750>
    <tr class="headerStyle">
          <td width="220"><input type="radio" name="tipopago" value="1" onClick="tipopago_onclick(this.value,true)" checked>Efectivo</td>
          <td width="310"><input type="radio" name="tipopago" value="2" onClick="tipopago_onclick(this.value,true)">Cheque</td>
          <td width="220"><input type="radio" name="tipopago" value="3" onClick="tipopago_onclick(this.value,true)">Tarjeta Crédito</td>
        </tr>
    <tr> 
      <td width="220"> 
        <table width="100%" border="0" class="tablatext" cellpadding="0" cellspacing="0">
          <tr> 
            <td width="25%">Total</td>
            <td width="75%"><input type="text" name="total" readonly="true"></td>
          </tr>
          <tr> 
            <td width="25%">Pago</td>
            <td width="75%"><input type="text" name="pago"  onkeyup="cambia()" onkeypress="return numbersonly()"></td>
          </tr>
          <tr> 
            <td width="25%">Cambio</td>
            <td width="75%"><input type="text" name="cambio" readonly="true"></td>
          </tr>
        </table>
      </td>
      <td width="310"> 
        <table width="100%" border="0" class="tablatext" cellpadding="0" cellspacing="0">
          <tr> 
            <td width="26%">Banco</td>
            <td width="74%"><input type="text" name="banco"></td>
          </tr>
          <tr> 
            <td width="26%">No.Cheque</td>
            <td width="74%"><input type="text" name="cheque"></td>
          </tr>
          <tr> 
            <td width="26%">Fecha de Cobro</td>
            <td width="74%"><input type="text" name="fcob"><a href="javascript:cal7.popup();" onMouseOut="window.status='';return true;" onMouseOver="window.status='Seleccione Fecha';return true;"><img border=0 src="images/calendar.gif" width="16" height="16"></a></td>
          </tr>
	<SCRIPT LANGUAGE="JavaScript">
				
				var cal7 = new calendar2(document.forms['frm'].elements['fcob']);
				cal7.year_scroll = true;
				cal7.time_comp = false;		
							
			</SCRIPT>
        </table>
      </td>
      <td width="220">
        <table width="100%" border="0" class="tablatext" cellpadding="0" cellspacing="0">
          <tr> 
            <td width="26%">Banco</td>
            <td width="74%"><input type="text" name="bancot"></td>
          </tr>
          <tr> 
            <td width="26%">No.Tarjeta</td>
            <td width="74%"><input type="text" name="tarjeta"></td>
          </tr>
        </table>	  
	  </td>
    </tr>
	<tr class="alternatingItemStyle">
		<td colspan="3" align="center">Total Ingreso:<input type="text" name="ingreso" readonly="true"></td>
	</tr>
	<tr class="titStyle">
		<td colspan="3" align="center">
	     <input type="submit" class="body" value="Pago" name="btnPago" onClick="return Accion_onclick(3)">
         <input type="reset" class="body" value="Cancelar" name="btnReset">
		</td>
	</tr>
  </table>	
<%}%>
</form>
</body>
</html>