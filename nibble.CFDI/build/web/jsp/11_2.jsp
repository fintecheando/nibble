<html>
<jsp:useBean id="bean42" class="pva.beans4.Bean42" scope="session"/>
<jsp:useBean id="bean112" class="pva.beans11.Bean112" scope="session"/>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link href="lib/estilo.css" rel=STYLESHEET type=text/css>
<title>PVA 11.2 Notas de Cargo</title>

<script language="JavaScript" src="lib/calendar2.js"></script>

<script type="text/javascript" src="lib/util.js"></script>
<script language="javascript">
var popen = false;

<%if  (bean112.isImprimir()) {%> popup=window.open("ImprimirNotaCargo","","toolbar=0,location=0,directories=0,status=0,menubar=0,scrollbars=yes,resizable=yes,width=700,height=400");
 popen = true;
<% } %>
function Accion_onclick(valor){
	var errors = '';
 	var numErrors = 0;
	forma = document.frm;

	if (forma.importe.value=="" || forma.importe.value==0){
		errors += '- Para registrar un pago debe abonar al menos a un documento\n';
		numErrors++;	
	}
	if (numErrors) {
		errors = 'Se produjeron ' + numErrors + ' error(es)\n'+ errors;
		alert(errors);
		return false;
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
	if (forma.importe.value != ""){
		ivam = parseFloat(forma.importe.value) * (parseFloat(forma.ivacal.value)/100);
		if (ivam > 0) forma.ivalocal.value=formatAsMoney(ivam);
		else forma.ivalocal.value=formatAsMoney(0);	
	}else forma.ivalocal.value=formatAsMoney(0);
}

</script>
</head>
<%@ include file="../main.jsp" %>
<body>

<form name=frm action="NotaCargo" method="POST">
<input type=hidden name="accion" value="2">
<input type=hidden name="ivacal" value="<%=bean112.getIva()%>">
<table class="tablatext" width=750 align="center" cellpadding="0" cellspacing="0">
<tr>
	<td><img src="images/tpva11_2.GIF"></td>

        <th>
	<td><a href="../pvatx/hlp/pva11.0-11.2.htm" target="Ayuda Notas de Cargo">Ayuda</a></td>
        </th>

</tr>
</table>
<br>
<table align=center border=1 borderColor=#364859 cellSpacing=0 class=tablatext rules=cols style="BORDER-BOTTOM: black 1px solid; BORDER-COLLAPSE: collapse; BORDER-LEFT: black 1px solid; BORDER-RIGHT: black 1px solid; BORDER-TOP: black 1px solid; FONT-FAMILY: arial; FONT-SIZE: 11px" width=750>
    <TR> 
      <TD colSpan=3>Selección de Cliente</TD>
    </TR>
    <TR class="titStyle"> 
      <TD width="15%">Número</TD>
      <TD width="50%">Razón Social</TD>
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
      <TD colspan="2">Detalle de Nota de Cargo</TD>
    </TR>
    <TR class="titStyle"> 
      <TD colspan="2">Concepto </TD>
    </TR>
    <TR> 
      <TD align="center" width="353"><TEXTAREA class="body" cols=50 name=concepto rows=4></TEXTAREA></TD>
      <TD width="395">
	    <table width="100%" border="0" class=tablatext>
          <tr class="alternatingItemStyle">
            <td width="42%">Importe</td>
            <td width="58%"><input type="text" name=importe onkeyup="calcula()" onkeypress="return numbersonly()"></td>
          </tr>
          <tr class="itemStyle">
            <td width="42%">I.V.A.</td>
            <td width="58%"><input type="text" name=ivalocal readonly="true"></td>
          </tr>
          <tr class="alternatingItemStyle">
            <td width="42%">Fecha de Vencimiento</td>
            <td width="58%"><input type="text" name=fven value="<%=bean112.getFecha()%>">
            <a href="javascript:cal5.popup();" onMouseOut="window.status='';return true;" onMouseOver="window.status='Seleccione Fecha';return true;"><img border=0 src="images/calendar.gif"></a>		
			</td>
			
			<SCRIPT LANGUAGE="JavaScript">

			var cal5 = new calendar2(document.forms['frm'].elements['fven']);
			cal5.year_scroll = true;
			cal5.time_comp = false;
										
			</SCRIPT>
			
			
          </tr>
        </table>
      </TD>
    </TR>
	<TR class="titStyle">
	 <TD colspan=2 align="center">
	  	<input type="Submit" class="body" value="Realizar Cargo" onClick="return Accion_onclick(2)">
	  	<input type="Reset" class="body" value="Cancelar">
	  </TD>
	</TR>
</TABLE>
</form>

</body>
</html>
