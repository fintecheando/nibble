<html>
<jsp:useBean id="bean91Parte" class="pva.beans9.Bean91Parte" scope="session"/>
<jsp:useBean id="bean82" class="pva.beans8.Bean82" scope="session"/>
<head>
<title>PVA 9.1 Facturar</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link href="lib/estilo.css" rel=STYLESHEET type=text/css>
<link href="lib/selector.css" rel=STYLESHEET type=text/css>
<script type="text/javascript" src="lib/util.js"></script>
<script language="JavaScript">
function agregar_onclick(form){
	form.accion.value=16;
	form.submit();
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
<input type="hidden" name=iidaplicacion value="<%= request.getParameter("iidaplicacion") %>">
<table width=550 align="center" border="1" bordercolor="#364859" rules="cols" cellspacing="0" style="border-color:Black;border-width:1px;border-style:solid;font-family: arial; font-size: 11px;border-collapse:collapse;" class="tablatext">
  <tr class="headerStyle">
	<td width="30%">Categoría </td>
	<td>
			<select name="iidcategoria" class="body" onchange="this.form.submit()">
			<option value=""></option>
		<%for (int i=0; i<bean82.getCategoriasSize();i++){%>
			<option value="<%= bean82.getCategoriasId(i) %>" <% if (bean91Parte.getIidcategoria()==bean82.getCategoriasId(i)){%> SELECTED <%}%>><%= bean82.getCategoriasDes(i) %></option>
		<%}%> 
	</td>
	</tr>
</table>
<br>
<table width=550 align="center" border="1" bordercolor="#364859" rules="cols" cellspacing="0" style="border-color:Black;border-width:1px;border-style:solid;font-family: arial; font-size: 11px;border-collapse:collapse;" class="tablatext">
  <tr class="headerStyle">  	  
  	  <td>Cantidad adquirida</td>
	  <td>Número de parte Equival</td>
	  <td>Descripción</td>
	  <td>Cantidad sugerida</td>
    </tr>
	<% String stilo;
		   for (int i = 0; i < bean91Parte.getPartesSize(); i++) {
	 		 if (i % 2 == 0)stilo = "itemStyle";
			 else stilo = "alternatingItemStyle"; 
  	%>
	  <tr class="<%=stilo%>">
	    <td><input type="text" name="cantidad<%=bean91Parte.getLiidparte(i)%>" class=numero onkeypress="return numbersonly()"></td> 
	    <td><%=bean91Parte.getVchnumparteequival(i)%></td>
	    <td><%=bean91Parte.getVchdescripcion(i)%></td>
	    <td align="right"><%=bean91Parte.getTicantidad(i)%></td>
	  </tr>	
	<% }%>
</table>
<br><br>
<table width=550 align="center" border="1" bordercolor="#364859" rules="cols" cellspacing="0" style="border-color:Black;border-width:1px;border-style:solid;font-family: arial; font-size: 11px;border-collapse:collapse;" class="tablatext">
<tr class="titStyle">
	<td align=center><input type="button" value="Agregar" class=body onclick="agregar_onclick(this.form);" <%if (bean91Parte.getPartesSize()==0){%>DISABLED<%}%>>
		<input type="button" value="Cancelar" class=body onclick="window.close()"></td>
</tr>
</table>
<br>
<table align="center" width="550">
	<tr><td><a href="javascript: document.frm.accion.value=12;document.frm.submit();" class="ligas"><< Regresar</a></td></tr>
</table>
</form>	
</div>
</body>
</html>
