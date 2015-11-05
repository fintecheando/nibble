<html>
<jsp:useBean id="bean91" class="pva.beans9.Bean91" scope="session"/>
<jsp:useBean id="bean42" class="pva.beans4.Bean42" scope="session"/>
<jsp:useBean id="bean117" class="pva.beans11.Bean117" scope="session"/>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link href="lib/estilo.css" rel=STYLESHEET type=text/css>
<title>11.7 Vales de Caja</title>
<script type="text/javascript" src="lib/util.js"></script>

<script language="javascript">
var popen = false;
<%if  (bean117.isWinpopup()) {%> popup=window.open("ValesCaja?accion=4","","toolbar=0,location=0,directories=0,status=0,menubar=0,scrollbars=yes,resizable=yes,width=600,height=300");
 popen = true;
<% } %>

function calcula(){
	forma = document.frm;
	total=0;
	for (i = 0; i < forma.length; i++){
	  if (forma.elements[i].name.substring(0,2)=="ab"){
	  	if (forma.elements[i].value != "")
	  		total+=parseFloat(forma.elements[i].value);
	  }
	}
	forma.total.value=total;
}

function Accion_onclick(valor){
	var errors = '';
 	var numErrors = 0;
	forma = document.frm;
	forma.accion.value = valor;
	return true;
}

function Accion_onclick2(valor){
	var errors = '';
 	var numErrors = 0;
	forma = document.frm;
	forma.accion.value = valor;
	return true;
}

function Accion_onclick3(valor){
	var errors = '';
 	var numErrors = 0;
	forma = document.frm;
	forma.accion.value = 1;
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
</script>	

</head>
<%@ include file="../main.jsp" %>
<body>
<form name=frm action="ValesCaja" method="POST">
<input type=hidden name="accion">
<table class="tablatext" width=750 align="center" cellpadding="0" cellspacing="0">
<tr>
	<td><img src="images/tpva11_7.GIF"></td>
<!--
        <th>
	<td><a href="../pvatx/hlp/pva11.0-11.7.htm" target="Ayuda Vales de Caja">Ayuda</a></td>
        </th>
-->
</tr>
</table>
<table align=center border=1 borderColor=#364859 cellSpacing=0 class=tablatext rules=cols style="BORDER-BOTTOM: black 1px solid; BORDER-COLLAPSE: collapse; BORDER-LEFT: black 1px solid; BORDER-RIGHT: black 1px solid; BORDER-TOP: black 1px solid; FONT-FAMILY: arial; FONT-SIZE: 11px" width=750>
    <tr> 
      <TD colSpan=2>Selección de Cliente</TD>
      <td  align=right>Usuario: <%= bean91.getUsuario() %></td>
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
			 <option value=<%= bean42.getId(i)%> <%if (bean117.getCliente()==bean42.getId(i)){%> SELECTED <%}%>> <%= bean42.getId(i) %></option>
			 <%}%>
		</select>
      <TD width="50%"> 
		<select name="rscliente" class="body" onChange="rscliente_onchange()">
			 <%for (int i=0; i<bean42.getClientesSize();i++){%>
			 <option value=<%= bean42.getId(i)%> <%if (bean117.getCliente()==bean42.getId(i)){%> SELECTED <%}%>><%= bean42.getRazonsocial(i) %></option>
			 <%}%>
		</select>
      </TD>
      <TD width="35%"> 
			<select name="rfcliente" class="body" onChange="rfcliente_onchange()">
			 <%for (int i=0; i<bean42.getClientesSize();i++){%>
			  <option value=<%= bean42.getId(i)%> <%if (bean117.getCliente()==bean42.getId(i)){%> SELECTED <%}%>><%= bean42.getRfc(i) %></option>
			 <%}%>
			</select>  
      </TD>
    </TR>
    <TR class="titStyle"> 
      <TD align="center" colSpan=5> 
          <input type=submit value="Buscar" class="body" name="submit" onClick="return Accion_onclick(2)">
      </TD>
    </TR>
  </TABLE>
<br>
<% if (bean117.isDetalle()){%>
	<table width=750 align="center" border="1" bordercolor="#364859" rules="cols" cellspacing="0" width="750" style="border-color:Black;border-width:1px;border-style:solid;font-family: arial; font-size: 11px;border-collapse:collapse;" class="tablatext">
		<tr class="headerStyle">
			<td>Ingreso</td>
		  	<td>Fecha</td>
		  	<td>Importe</td>
		  	<td>Saldo</td>
		  	<td>Abono</td>
		  	<td>Saldar</td>			
		</tr>	
		<% String stilo;
		   for (int i = 0; i < bean117.getIngresosSize(); i++) {
	 		 if (i % 2 == 0)stilo = "itemStyle";
			 else stilo = "alternatingItemStyle"; 
		 %>
			<tr class="<%=stilo%>">
			  <td align="center"><%=bean117.getIidavisoingreso(i)%></td>
			  <td><%=bean117.getTisfecha(i)%></td>	
			  <td align="right"><%=bean117.getDecdebe(i)%></td>
			  <td align="right"><%=bean117.getDecsaldo(i)%></td>
			  <td align="center"><input type="text" name="ab<%=bean117.getIidavisoingreso(i)%>" onkeyup="if (this.value > <%=bean117.getSaldo(i)%>){alert('No puede abonar más de lo que debe'); this.value=''; this.focus();}if (document.frm.c<%=bean117.getIidavisoingreso(i)%>.checked) document.frm.c<%=bean117.getIidavisoingreso(i)%>.checked = false; calcula();" onkeypress="return numbersonly()"></td>
			  <td align="center"><input type="checkbox" name="c<%=bean117.getIidavisoingreso(i)%>" onClick="if (this.checked){document.frm.ab<%=bean117.getIidavisoingreso(i)%>.value = <%=bean117.getSaldo(i)%>}else{document.frm.ab<%=bean117.getIidavisoingreso(i)%>.value = ''} calcula();"></td>			  
			</tr>
		<%}%>
	<tr class="tititemStyle">
		<td colspan="6" align="center">Total:<input type="text" name="total" readonly="true"></td>	
	</tr>
	<tr class="titStyle">
		<td colspan="6" align="center">
	     <input type="submit" class="body" value="Generar Vale" name="btnVale" onClick="return Accion_onclick2(3)">
         <input type="reset" class="body" value="Cancelar" name="btnReset">
		</td>
	</tr>
	</table>
	<input type="hidden" name="idvale" value="<%=bean117.getIdvale()%>">
	<input type="hidden" name="importevale" value="<%=bean117.getImporte()%>">
<%}%>
</form>
</body>
</html>