<html>
<jsp:useBean id="bean116b" class="pva.beans11.Bean116b" scope="session"/>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link href="lib/estilo.css" rel=STYLESHEET type=text/css>
<title>PVA 11.6 Aplicación de Ingresos</title>
<script language="JavaScript" src="lib/datepicker.js"></script>
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

function Accion_onclick(valor){
	var errors = '';
 	var numErrors = 0;
	forma = document.frm;

	if (valor == 3){ // Realiza pago
			if (forma.total.value=="" || forma.total.value==0){
				errors += '- Para registrar un pago debe abonar al menos a un documento\n';
				numErrors++;	
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
	forma.ingreso.value =formatAsMoney(parseFloat(forma.impingreso.value)-total);
}

</script>	
</head>
<%@ include file="../main.jsp" %>
<body>
<form name=frm action="IngresosPagos" method="POST">
<input type=hidden name="accion">
<input type=hidden name="idcliente" value="<%=bean116b.getIdcliente()%>">
<input type=hidden name="idavisoingreso" value="<%=bean116b.getIidavisoingreso()%>">
<table class="tablatext" width=750 align="center" cellpadding="0" cellspacing="0">
<tr>
	<td><img src="images/tpva11_6.GIF"></td>

        <th>
	<td><a href="../pvatx/hlp/pva11.0-11.6.htm" target="Ayuda Aplicar Ingresos">Ayuda</a></td>
        </th>

</tr>
</table>
<br>
<table width=750 align="center" border="1" bordercolor="#364859" rules="cols" cellspacing="0" width="750" style="border-color:Black;border-width:1px;border-style:solid;font-family: arial; font-size: 11px;border-collapse:collapse;" class="tablatext">
	<tr class="headerStyle">
		<td>Cliente</td>
		<td>Aviso Ingreso No.</td>
		<td>Importe Ingreso</td>
	    <td>Saldo Ingreso</td>
		<td>Total aplicado</td>
	</tr>
	<tr>
	  <td><%=bean116b.getIdcliente()%></td>
	  <td><%=bean116b.getIidavisoingreso()%></td>
      <td><input type="text" name="impingreso" readonly="true" value="<%=bean116b.getSaldoIngresoq()%>"></td>	  
      <td><input type="text" name="ingreso" readonly="true" value="<%=bean116b.getSaldoIngresoq()%>"></td>
      <td><input type="text" name="total" readonly="true"></td>
	</tr>
</table>
<br>
<input type="hidden" name="ingresoc" readonly="true""></td>
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
	   for (int i = 0; i < bean116b.sizeCxc(); i++) {
		 if (i % 2 == 0)stilo = "itemStyle";
		 else stilo = "alternatingItemStyle"; 
	 %>
	<tr class="<%=stilo%>">
	  <td align="center"><%=bean116b.getVchDestipodocto(i)%> <%=bean116b.getIiddoctoorigen(i)%></td>
	  <td><%=bean116b.getTisfechamovimiento(i)%></td>	
	  <td><%=bean116b.getTisfechavencimient(i)%></td>
	  <td align="right"><%=bean116b.getDecdebe(i)%></td>
	  <td align="right"><%if (bean116b.isVencido(i)){%><font color="#ff0000"><%}else{%><font color="#000000"><%}%><%=bean116b.getDecsaldo(i)%></font></td>
	  <td align="center">
	  <input type="text" name="ab<%=bean116b.getIiddoctoorigen(i)%>_<%=bean116b.getTiconsecsubdocto(i)%>_<%=bean116b.getTiidtipodocto(i)%>" 
	    onfocus=" if(parseFloat(document.frm.ingreso.value) != parseFloat(document.frm.ingresoc.value)){
					document.frm.ingresoc.value=document.frm.ingreso.value;					
				   }
				"
	  	onkeyup="			
		    error = false;
			msg ='';
		    if (parseFloat(this.value) > parseFloat(document.frm.ingresoc.value)){
			 this.value=''; 
			 msg = 'No puede abonar más que el saldo del ingreso'; 
			 error = true;			 			 
			}
		    if (parseFloat(this.value) > <%=bean116b.getDecsaldoq(i)%>){
			 this.value=''; 
			 msg = 'No puede abonar más de lo que debe'; 
			 error = true;
			} 
			if (document.frm.c<%=bean116b.getIiddoctoorigen(i)%>.checked) 
				document.frm.c<%=bean116b.getIiddoctoorigen(i)%>.checked = false;            
			calcula();
			if (error) alert(msg);
			"
			onkeypress="return numbersonly()"></td> 
<!--	  <td align="center"><input type="text" name="ab<%=bean116b.getIiddoctoorigen(i)%>_<%=bean116b.getTiconsecsubdocto(i)%>_<%=bean116b.getTiidtipodocto(i)%>" onfocus=" if(parseFloat(document.frm.ingreso.value) > 0)document.frm.ingresoc.value=document.frm.ingreso.value; else document.frm.ingresoc.value=this.value;" onkeyup="if (parseFloat(this.value) > <%=bean116b.getDecsaldoq(i)%>){alert('No puede abonar más de lo que debe'); this.value=''; this.focus();} if (parseFloat(this.value) > parseFloat(document.frm.ingresoc.value)){alert('No puede abonar más que el saldo del ingreso'); this.value=document.frm.ingresoc.value; this.focus();}if (document.frm.c<%=bean116b.getIiddoctoorigen(i)%>.checked) document.frm.c<%=bean116b.getIiddoctoorigen(i)%>.checked = false; calcula();" onkeypress="return numbersonly()"></td-->
	  <td align="center">
      <input type=hidden name="saldado<%=bean116b.getIiddoctoorigen(i)%>" value="0">
	  <input type="checkbox" name="c<%=bean116b.getIiddoctoorigen(i)%>" 
	   onClick=" if (this.checked){
	               if(<%=bean116b.getDecsaldoq(i)%> > parseFloat(document.frm.ingreso.value)) 
				   	document.frm.ab<%=bean116b.getIiddoctoorigen(i)%>_<%=bean116b.getTiconsecsubdocto(i)%>_<%=bean116b.getTiidtipodocto(i)%>.value = document.frm.ingreso.value; 
				   else 
				    document.frm.ab<%=bean116b.getIiddoctoorigen(i)%>_<%=bean116b.getTiconsecsubdocto(i)%>_<%=bean116b.getTiidtipodocto(i)%>.value = <%=bean116b.getDecsaldoq(i)%>
				   }
				   else{
				     document.frm.ab<%=bean116b.getIiddoctoorigen(i)%>_<%=bean116b.getTiconsecsubdocto(i)%>_<%=bean116b.getTiidtipodocto(i)%>.value = ''
				   } 
				   calcula();
				   if (this.checked){document.frm.saldado<%=bean116b.getIiddoctoorigen(i)%>.value='1'};				   
				   ">
	 </td>
	 
<!--  <td align="center"><input type="checkbox" name="c<%=bean116b.getIiddoctoorigen(i)%>" onfocus=" if(parseFloat(document.frm.ingreso.value) > 0)document.frm.ingresoc.value=document.frm.ingreso.value; else document.frm.ingresoc.value=document.frm.ab<%=bean116b.getIiddoctoorigen(i)%>_<%=bean116b.getTiconsecsubdocto(i)%>_<%=bean116b.getTiidtipodocto(i)%>.value;" onClick="if (this.checked){if(<%=bean116b.getDecsaldoq(i)%> > parseFloat(document.frm.ingresoc.value)) document.frm.ab<%=bean116b.getIiddoctoorigen(i)%>_<%=bean116b.getTiconsecsubdocto(i)%>_<%=bean116b.getTiidtipodocto(i)%>.value = document.frm.ingresoc.value; else document.frm.ab<%=bean116b.getIiddoctoorigen(i)%>_<%=bean116b.getTiconsecsubdocto(i)%>_<%=bean116b.getTiidtipodocto(i)%>.value = <%=bean116b.getDecsaldoq(i)%>}else{document.frm.ab<%=bean116b.getIiddoctoorigen(i)%>_<%=bean116b.getTiconsecsubdocto(i)%>_<%=bean116b.getTiidtipodocto(i)%>.value = ''} calcula();"></td-->
	</tr>
	<%}%>
	<tr class="titStyle">
		<td colspan="7" align="center">
	     <input type="submit" class="body" value="Pago" name="btnPago" onClick="return Accion_onclick(3)">
         <input type="reset" class="body" value="Cancelar" name="btnReset">
		</td>
	</tr>
</table>
</form>
</body>
</html>