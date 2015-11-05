<html>
<jsp:useBean id="bean42" class="pva.beans4.Bean42" scope="session"/>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link href="lib/estilo.css" rel=STYLESHEET type=text/css>
<title>PVA 11.5 Avisos de Ingreso</title>

<script language="JavaScript" src="lib/calendar2.js"></script>


<script type="text/javascript" src="lib/util.js"></script>
<script language="javascript">

function Accion_onclick(valor){
	var errors = '';
 	var numErrors = 0;
	forma = document.frm;
	if (valor == 2){ // Realiza pago
		if (forma.tipopago[0].checked){	 // Efectivo
			if (forma.importe.value=="" || forma.importe.value==0){
				errors += '- Para registrar el aviso debe introducir el importe\n';
				numErrors++;	
			}
			if (forma.pago.value=="" || forma.pago.value==0 || (parseFloat(forma.pago.value) < parseFloat(forma.total.value))){
				errors += '- No ha proporcionado el monto del pago o el monto proporcionado es menor al total a cobrar\n';
				numErrors++;	
			}
		}
		if (forma.tipopago[1].checked){ // Cheque
			if (forma.importe.value=="" || forma.importe.value==0){
				errors += '- Para registrar el aviso debe introducir el importe\n';
				numErrors++;	
			}
			if (forma.banco.value==""){
				errors += '- Para registrar el pago debe indicar el Banco\n';
				numErrors++;	
			}
			if (forma.cheque.value==""){
				errors += '- Se requiere el n�mero de cheque\n';
				numErrors++;	
			}
			if (forma.fcob.value==""){
				errors += '- Se requiere la fecha de cobro del cheque\n';
				numErrors++;	
			}		
		}
		if (forma.tipopago[2].checked){ // Tarjeta Cr�dito
			if (forma.importe.value=="" || forma.importe.value==0){
				errors += '- Para registrar el aviso debe introducir el importe\n';
				numErrors++;	
			}
			if (forma.bancot.value==""){
				errors += '- Para registrar el pago debe indicar el Banco\n';
				numErrors++;	
			}
			if (forma.tarjeta.value==""){
				errors += '- Se requiere el n�mero de tarjeta de cr�dito\n';
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

function formatAsMoney(mnt) {
    mnt -= 0;
    mnt = (Math.round(mnt*100))/100;
    return (mnt == Math.floor(mnt)) ? mnt + '.00': ( (mnt*10 == Math.floor(mnt*10)) ? mnt + '0' : mnt);
}

function copia(valor){
	forma = document.frm;
	forma.total.value = formatAsMoney(valor);
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
<body>
<form name=frm action="AvisoIngreso" method="POST">
<input type=hidden name="accion">
<table class="tablatext" width=750 align="center" cellpadding="0" cellspacing="0">
<tr>
	<td><img src="images/tpva11_5.GIF"></td>

        <th>
	<td><a href="../pvatx/hlp/pva11.0-11.5.htm" target="Ayuda de Avisos de Ingreso">Ayuda</a></td>
        </th>

</tr>
</table>
<br>
<table align=center border=1 borderColor=#364859 cellSpacing=0 class=tablatext rules=cols style="BORDER-BOTTOM: black 1px solid; BORDER-COLLAPSE: collapse; BORDER-LEFT: black 1px solid; BORDER-RIGHT: black 1px solid; BORDER-TOP: black 1px solid; FONT-FAMILY: arial; FONT-SIZE: 11px" width=750>
    <TR> 
      <TD colSpan=3>Seleccion de Cliente</TD>
    </TR>
    <TR class="titStyle"> 
      <TD width="15%">Numero</TD>
      <TD width="50%">Razon Social</TD>
      <TD width="35%">R.F.C</TD>
    </TR>
    <TR> 
      <TD width="15%"> 
		<select name="idcliente" class="body" onChange="idcliente_onchange()">
			 <%for (int i=0; i<bean42.getClientesSize();i++){%>
			 <option value=<%=bean42.getId(i)%>> <%=bean42.getId(i)%></option>
			 <%}%>
		</select>
      <TD width="50%"> 
		<select name="rscliente" class="body" onChange="rscliente_onchange()">
			 <%for (int i=0; i<bean42.getClientesSize();i++){%>
			 <option value=<%= bean42.getId(i)%>><%= bean42.getRazonsocial(i) %></option>
			 <%}%>
		</select>
      </TD>
      <TD width="35%"> 
			<select name="rfcliente" class="body" onChange="rfcliente_onchange()">
			 <%for (int i=0; i<bean42.getClientesSize();i++){%>
			  <option value=<%= bean42.getId(i)%>><%= bean42.getRfc(i) %></option>
			 <%}%>
			</select>  
      </TD>
	</TR>
</TABLE>	
<br>
<table align=center border=1 borderColor=#364859 cellSpacing=0 class=tablatext rules=cols style="BORDER-BOTTOM: black 1px solid; BORDER-COLLAPSE: collapse; BORDER-LEFT: black 1px solid; BORDER-RIGHT: black 1px solid; BORDER-TOP: black 1px solid; FONT-FAMILY: arial; FONT-SIZE: 11px" width=750>
    <TR> 
      <TD colspan="2">Detalle</TD>
    </TR>
    <TR class="titStyle"> 
      <TD colspan="2">Concepto</TD>
    </TR>
    <TR> 
      <TD align="center" width="353"><TEXTAREA class="body" cols=50 name=concepto rows=4></TEXTAREA></TD>
      <TD width="395">
	    <table width="100%" border="0" class=tablatext>
          <tr class="alternatingItemStyle">
            <td width="42%">Importe</td>
            <td width="58%"><input type="text" name=importe onkeyup="copia(this.value)" onkeypress="return numbersonly()"></td>
          </tr>
        </table>
      </TD>
    </TR>
</TABLE>
<br>
<table align=center border=1 borderColor=#364859 cellSpacing=0 class=tablatext rules=cols style="BORDER-BOTTOM: black 1px solid; BORDER-COLLAPSE: collapse; BORDER-LEFT: black 1px solid; BORDER-RIGHT: black 1px solid; BORDER-TOP: black 1px solid; FONT-FAMILY: arial; FONT-SIZE: 11px" width=750>
    <tr class="headerStyle"> 
      <td width="220"><input type="radio" name="tipopago" value="1" onClick="tipopago_onclick(this.value,true)" checked>Efectivo</td>
      <td width="310"><input type="radio" name="tipopago" value="2" onClick="tipopago_onclick(this.value,true)">Cheque</td>
      <td width="220"><input type="radio" name="tipopago" value="3" onClick="tipopago_onclick(this.value,true)">Tarjeta Credito</td>
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
            <td width="74%"><input type="text" name="fcob">
            <a href="javascript:cal5.popup();" onMouseOut="window.status='';return true;" onMouseOver="window.status='Seleccione Fecha';return true;"><img border=0 src="images/calendar.gif" width="16" height="16"></a>		
            </td>
            
<SCRIPT LANGUAGE="JavaScript">

			var cal5 = new calendar2(document.forms['frm'].elements['fcob']);
			cal5.year_scroll = true;
			cal5.time_comp = false;
										
			</SCRIPT>
            
          </tr>
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
	<TR class="titStyle">
	 <TD colspan=3 align="center">
	  	<input type="Submit" class="body" value="Aceptar" onClick="return Accion_onclick(2)">
	  	<input type="Reset" class="body" value="Cancelar">
	  </TD>
	</TR>
 </table>	
 <table class="tablatext" width=750 align="center" cellpadding="0" cellspacing="0">
 <tr>
	<td><%= bean42.getMensaje() %></td>
 </tr>
</form>
</body>
</html>
