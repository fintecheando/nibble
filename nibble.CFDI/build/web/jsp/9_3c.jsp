<html>
<jsp:useBean id="bean93d" class="pva.beans9.Bean93d" scope="session"/>
<head>
<title>PVA 9.3 Cambios</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link href="lib/estilo.css" rel=STYLESHEET type=text/css>
<link href="lib/selector.css" rel=STYLESHEET type=text/css>
<script language="JavaScript">

function aceptar_onclick(form){
	form.accion.value=8;
	form.submit();
}

</script>
</head>
<body>
<div align="center">
<form name="frm" method="POST" action="Cambios">
<input type="hidden" name="accion" value=5>
<input type="hidden" name=partida value="<%= request.getParameter("partida") %>">
<table class="tablatext" width=550 align="center" cellpadding="0" cellspacing="0">
<tr>
	<td><img src="images/tpva9_3.GIF"></td>
</tr>
</table>
<table align="center" border="1" bordercolor="#364859" rules="cols" cellspacing="0" width="550" style="border-color:Black;border-width:1px;border-style:solid;font-family: arial; font-size: 11px;border-collapse:collapse;" class="tablatext">
<tr class="titStyle">   
	  <td width="50%">Número de Parte Equival</td>
	  <td width="50%">Proveedor</td>
</tr>
<tr class="itemStyle">
	<td width="50%"><%= bean93d.getVchnumparteequival()%></td>
	<td width="50%"><select name="iidproveedor" class="body">
<%
for (int i=0; i<bean93d.getProveeSize();i++){
%>
				<option value="<%= i %>" <% if (bean93d.getProveeBitprovefavorito(i)){%> SELECTED <% } %>><%= bean93d.getProveeIexistencia(i) %> - <%= bean93d.getProveeVchrazonsocial(i) %></option>
<%
}
%>   		    </select>
	</td>
</tr>
</table>
<br>
<table align="center" border="1" bordercolor="#364859" rules="cols" cellspacing="0" width="550" style="border-color:Black;border-width:1px;border-style:solid;font-family: arial; font-size: 11px;border-collapse:collapse;" class="tablatext">
<tr class="titStyle">	
	<td align=center><input type="button" value="Aceptar" class=body onclick="aceptar_onclick(this.form)" <%if (bean93d.getProveeSize()==0){%> disabled<%}%> >	</td>
</tr>
</table>
<br>
<table align="center" width="550">
	<tr><td><a href="javascript: document.frm.submit();" class="ligas"><< Regresar</a></td></tr>
</table>
</form>	
</div>
</body>
</html>
