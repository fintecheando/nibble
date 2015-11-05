<html>
<jsp:useBean id="bean91f" class="pva.beans9.Bean91f" scope="session"/>
<head>
<title>PVA 9.1 Facturar</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link href="lib/estilo.css" rel=STYLESHEET type=text/css>
<link href="lib/selector.css" rel=STYLESHEET type=text/css>
<script language="JavaScript">
function agregar_onclick(form){
	form.accion.value=20;
	form.submit();
}
function refre(){
	dad = window.opener.document.frm;
	dad.accion.value=17;
	dad.submit();
	window.close();
}


</script>
</head>
<body>
<table class="tablatext" width=550 align="center" cellpadding="0" cellspacing="0">
<tr>
	<td><img src="images/tpva9_1.GIF"></td>

        <th>
	<td><a href="../pvatx/hlp/pva9.0-9.1.htm" target="Ayuda Facturar">Ayuda</a></td>
        </th>

</tr>
</table>
<br>
<br>
<div align="center">
<form name="frm" method="POST" action="Facturar">
<input type="hidden" name="accion" value=15>
<input type="hidden" name="accionPost" value=17>
<input type="hidden" name=iidaplicacion value="<%= request.getParameter("iidaplicacion") %>">
<table width=550 align="center" border="1" bordercolor="#364859" rules="cols" cellspacing="0" style="border-color:Black;border-width:1px;border-style:solid;font-family: arial; font-size: 11px;border-collapse:collapse;" class="tablatext">
  <tr class="headerStyle">
  	  <td width="20%">Cantidad adquirida</td>
	  <td width="40%">Numero de parte Equival</td>
	  <td>Proveedor</td>
    </tr>
	<% String stilo;
		   for (int i = 0; i < bean91f.getSize(); i++) {
	 		 if (i % 2 == 0)stilo = "itemStyle";
			 else stilo = "alternatingItemStyle"; 
  	%>
	  <tr class="<%=stilo%>">
	    <td align="right"><%=bean91f.getIcantidad(i)%></td> 
	    <td><%=bean91f.getVchnumparteequival(i)%></td>		
		<td><select name="iidproveedor<%= i %>" class="body">
		<%for (int j=0; j<bean91f.getProveeSize(i);j++){%>
			<option value="<%= j %>" <% if (bean91f.getProveeBitprovefavorito(i,j)){%> SELECTED <% } %>><%= bean91f.getProveeVchrazonsocial(i,j) %></option>
		<%}%> 
	</td>
    </tr>	
	<% }%>
</table>
<br>
<table width=550 align="center" border="1" bordercolor="#364859" rules="cols" cellspacing="0" style="border-color:Black;border-width:1px;border-style:solid;font-family: arial; font-size: 11px;border-collapse:collapse;" class="tablatext">
<tr class="titStyle">
	<td align=center><input type="button" value="Agregar" class=body onclick="agregar_onclick(this.form);" <%if (bean91f.getSize()==0){%>DISABLED<%}%>>
		<input type="button" value="Cancelar" class=body onclick="window.close()"></td>
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
