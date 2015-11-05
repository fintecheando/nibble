<html>
<jsp:useBean id="bean111" class="pva.beans11.Bean111" scope="request"/>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link href="lib/estilo.css" rel=STYLESHEET type=text/css>
<title>PVA 11.2 Notas de Cargo</title>
<script type="text/javascript" src="lib/util.js"></script>
<script language="javascript">
function Accion_onclick(valor){
	var errors = '';
 	var numErrors = 0;
	forma = document.frm;

	if (forma.total.value=="" || forma.total.value==0){
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
}

</script>
</head>
<%@ include file="../main.jsp" %>
<body>
<form name=frm action="Bonifica" method="POST">
<input type=hidden name="accion">
<input type=hidden name="idcliente" value="<%=request.getParameter("idcliente")%>">
<input type=hidden name="idfactura" value="<%=bean111.getFactura()%>">
<table class="tablatext" width=750 align="center" cellpadding="0" cellspacing="0">
<tr>
	<td><img src="images/tpva11_1.GIF"></td>

        <th>
	<td><a href="../pvatx/hlp/pva11.0-11.1.htm" target="Ayuda Bonificaciones">Ayuda</a></td>
        </th>

</tr>
</table>
<br>
<table width=750 align="center" border="1" bordercolor="#364859" rules="cols" cellspacing="0" width="750" style="border-color:Black;border-width:1px;border-style:solid;font-family: arial; font-size: 11px;border-collapse:collapse;" class="tablatext">
	<tr class="headerStyle">
		<td>No.Cliente</td>
		<td>Razón Social</td>
	</tr>	
	<tr class="itemStyle">
		<td><%=request.getParameter("idcliente")%></td>
		<td>Nombre</td>
	</tr>		
</table>
<br>
<table width=750 align="center" border="1" bordercolor="#364859" rules="cols" cellspacing="0" width="750" style="border-color:Black;border-width:1px;border-style:solid;font-family: arial; font-size: 11px;border-collapse:collapse;" class="tablatext">
	<tr class="headerStyle">
		<td>Docto.</td>
		<td>Emisión</td>
		<td>Vencimiento</td>
		<td>Importe</td>
		<td>Saldo</td>
		<td>Bonificación</td>
		<td>Saldar</td>
	</tr>	
	<tr class="itemStyle">
	  <td align="center">FAC <%=bean111.getFactura()%></td>
	  <td><%=bean111.getTisfechamovimiento()%></td>	
	  <td><%=bean111.getTisfechavencimient()%></td>
	  <td align="right"><%=bean111.getDebe()%></td>
	  <td align="right"><%=bean111.getSaldoq()%></td>
	  <td align="center"><input type="text" name="ab<%=bean111.getFactura()%>" onkeyup="if (this.value > <%=bean111.getSaldo()%>){alert('No puede abonar más de lo que debe'); this.value=''; this.focus();}if (document.frm.c<%=bean111.getFactura()%>.checked) document.frm.c<%=bean111.getFactura()%>.checked = false; calcula();" onkeypress="return numbersonly()"></td>
	  <input type=hidden name="saldado<%=bean111.getFactura()%>" value="0">
	  <td align="center">
	  <input type="checkbox" name="c<%=bean111.getFactura()%>" onClick="if (this.checked){document.frm.ab<%=bean111.getFactura()%>.value = <%=bean111.getSaldo()%>}else{document.frm.ab<%=bean111.getFactura()%>.value = ''} calcula();
	  if (this.checked){document.frm.saldado<%=bean111.getFactura()%>.value='1'};				   	  
	  ">
	  </td>
	</tr>
</table>
<br>
<table align=center border=1 borderColor=#364859 cellSpacing=0 class=tablatext rules=cols style="BORDER-BOTTOM: black 1px solid; BORDER-COLLAPSE: collapse; BORDER-LEFT: black 1px solid; BORDER-RIGHT: black 1px solid; BORDER-TOP: black 1px solid; FONT-FAMILY: arial; FONT-SIZE: 11px" width=750>
    <TR class="titStyle"> 
      <TD colspan="2">Descripción</TD>
    </TR>
    <TR> 
      <TD align="center" width="353"><TEXTAREA class="body" cols=50 name="concepto" rows=4></TEXTAREA></TD>
      <TD width="395">
	    <table width="100%" border="0" class=tablatext>
          <tr class="alternatingItemStyle">
            <td width="42%">Total Bonificación</td>
            <td width="58%"><input type="text" name="total" readonly="true"></td>
          </tr>
        </table>
      </TD>
    </TR>
	<TR class="titStyle">
	 <TD colspan=2 align="center">
	  	<input type="Submit" class="body" value="Realizar Bonificación" onClick="return Accion_onclick(2)">
	  	<input type="Reset" class="body" value="Cancelar">
	  </TD>
	</TR>
</TABLE>
</form>
</body>
</html>
