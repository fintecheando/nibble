<html>
<jsp:useBean id="bean42" class="pva.beans4.Bean42" scope="session"/>
<jsp:useBean id="bean116" class="pva.beans11.Bean116" scope="session"/>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link href="lib/estilo.css" rel=STYLESHEET type=text/css>
<title>11.6 Aplica Ingresos</title>
<script language="JavaScript" src="lib/datepicker.js"></script>
<script type="text/javascript" src="lib/util.js"></script>
<script language="javascript">

function Accion_onclick(valor){
	var errors = '';
 	var numErrors = 0;
	forma = document.frm;
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
</script>	
</head>
<%@ include file="../main.jsp" %>
<body>
<form name=frm action="AplicaIngreso" method="POST">
<input type=hidden name="accion">
<table class="tablatext" width=750 align="center" cellpadding="0" cellspacing="0">
<tr>
	<td><img src="images/tpva11_6.GIF"></td>

        <th>
	<td><a href="../pvatx/hlp/pva11.0-11.6.htm" target="Ayuda Aplicar Ingresos">Ayuda</a></td>
        </th>

</tr>
</table>
<table align=center border=1 borderColor=#364859 cellSpacing=0 class=tablatext rules=cols style="BORDER-BOTTOM: black 1px solid; BORDER-COLLAPSE: collapse; BORDER-LEFT: black 1px solid; BORDER-RIGHT: black 1px solid; BORDER-TOP: black 1px solid; FONT-FAMILY: arial; FONT-SIZE: 11px" width=750>
    <tr> 
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
			 <option value=<%= bean42.getId(i)%> <%if (bean116.getCliente()==bean42.getId(i)){%> SELECTED <%}%>> <%= bean42.getId(i) %></option>
			 <%}%>
		</select>
      <TD width="50%"> 
		<select name="rscliente" class="body" onChange="rscliente_onchange()">
			 <%for (int i=0; i<bean42.getClientesSize();i++){%>
			 <option value=<%= bean42.getId(i)%> <%if (bean116.getCliente()==bean42.getId(i)){%> SELECTED <%}%>><%= bean42.getRazonsocial(i) %></option>
			 <%}%>
		</select>
      </TD>
      <TD width="35%"> 
			<select name="rfcliente" class="body" onChange="rfcliente_onchange()">
			 <%for (int i=0; i<bean42.getClientesSize();i++){%>
			  <option value=<%= bean42.getId(i)%> <%if (bean116.getCliente()==bean42.getId(i)){%> SELECTED <%}%>><%= bean42.getRfc(i) %></option>
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
<% if (bean116.isDetalle()){%>
	<table width=750 align="center" border="1" bordercolor="#364859" rules="cols" cellspacing="0" width="750" style="border-color:Black;border-width:1px;border-style:solid;font-family: arial; font-size: 11px;border-collapse:collapse;" class="tablatext">
		<tr class="headerStyle">
			<td>Ingreso</td>
		  	<td>Fecha</td>
		  	<td>Importe</td>
		  	<td>Saldo</td>
		</tr>	
		<% String stilo;
		   for (int i = 0; i < bean116.getIngresosSize(); i++) {
	 		 if (i % 2 == 0)stilo = "itemStyle";
			 else stilo = "alternatingItemStyle"; 
		 %>
			<tr class="<%=stilo%>">
			  <td align="center"><a href="IngresosPagos?accion=1&idcliente=<%=bean116.getCliente()%>&iidavisoingreso=<%=bean116.getIidavisoingreso(i)%>&saldo=<%=bean116.getSaldo(i)%>" class="ligas"><%=bean116.getIidavisoingreso(i)%></a></td>
			  <td><%=bean116.getTisfecha(i)%></td>	
			  <td align="right"><%=bean116.getDecdebe(i)%></td>
			  <td align="right"><%=bean116.getDecsaldo(i)%></td>
			</tr>
		<%}%>
	</table>
<%}%>
</form>
</body>
</html>