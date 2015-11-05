<html>
<jsp:useBean id="bean74" class="org.nibble.service.vo.pedidos.Bean74" scope="session"/>

<head>
<title>NC 6.4 Consulta, Recepción y Modificación a Pedidos</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link href="lib/estilo.css" rel=STYLESHEET type=text/css>
<script type="text/javascript" src="lib/util.js"></script>

<script language="JavaScript" src="lib/calendar2.js"></script>

<script language=JavaScript>
function buscar_onclick(form){
	form.accion.value=2;
	return true;
}
</script>
</head>
<%@ include file="../main.jsp" %>
<body bgcolor="#FFFFFF" text="#000000">
<form name=frm action="Pedidos" method="post">
<input type=hidden name="accion">
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
<tr>
	<td colspan=2>Búsqueda de pedidos</td>
</tr>
<tr class="titStyle">
   	<td width="50%">Número de pedido</td>
	<td width="50%">Estatus del pedido</td>
</tr>
<tr class="itemStyle">
	<td><input type=text name="iidpedido" class="body" size=4 onkeypress="return numbersonly()" >	</td>
	<td> <select name="tiestatus" class="body">
			<option value=""></option>
			<%for (int i=0; i<bean74.getEstatusPedidosSize();i++){%>
				<option value=<%= bean74.getTiestatus(i) %>><%= bean74.getVchdescestatus(i) %></option>
			<%}%> 
  		 </select> 
	</td>
</tr>
</table>
<br>
<table align="center" border="1" bordercolor="#364859" rules="cols" cellspacing="0" width="750" style="border-color:Black;border-width:1px;border-style:solid;font-family: arial; font-size: 11px;border-collapse:collapse;" class="tablatext">
<tr class="titStyle">
   	<td width="50%">Proveedor</td>
	<td width="50%" colspan=2>Fecha de elaboración del pedido</td>
</tr>
<tr class="itemStyle">
	<td> <select name="iidproveedor" class="body">
		 <option value=""></option>
		 <%for (int i=0; i<bean74.getProveedoresSize();i++){%>
			<option value=<%= bean74.getIidproveedor(i) %>><%= bean74.getVchrazonsocial(i) %></option>
		<%}%> 
  		</select> 
	</td>
	  <td><input type=text name="fechaini" class="body" size=10>
	  <a href="javascript:cal5.popup();" onmouseover="window.status='Seleccione Fecha';return true;" onmouseout="window.status='';return true;"><img src="images/calendar.gif" border="0" class="."></a>
	  </td>
	  <td>
	  <input type=text name="fechafin" class="body" size=10>
	  <a href="javascript:cal6.popup();" onmouseover="window.status='Seleccione Fecha';return true;" onmouseout="window.status='';return true;"><img src="images/calendar.gif" border="0" class="."></a>
	  </td>
	  
<SCRIPT LANGUAGE="JavaScript">

			var cal5 = new calendar2(document.forms['frm'].elements['fechaini']);
			cal5.year_scroll = true;
			cal5.time_comp = false;
			
			var cal6 = new calendar2(document.forms['frm'].elements['fechafin']);
			cal6.year_scroll = true;
			cal6.time_comp = false;					
		</SCRIPT>
	  
	  
</tr>
</table>
<br>
<table align="center" border="1" bordercolor="#364859" rules="cols" cellspacing="0" width="750" style="border-color:Black;border-width:1px;border-style:solid;font-family: arial; font-size: 11px;border-collapse:collapse;" class="tablatext">
	<tr class="titStyle">
	<td align=center><input type="submit" value="Buscar" class=body onclick="return buscar_onclick(this.form)"></td>
</tr>
</table>
</form>
</body>
</html>
