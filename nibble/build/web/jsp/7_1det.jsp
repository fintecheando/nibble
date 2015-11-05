<html>
<jsp:useBean id="bean71" class="org.nibble.service.vo.pedidos.Bean71" scope="session"/>

<head>
<%if (bean71.isBitsalidareciente()){%>
<title>NC 7.1 Sugerencia de resurtido por ventas recientes</title>
<%}else{%>
<title>NC 7.2 Sugerencia de todas las partes por resurtir</title>
<%}%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link href="lib/estilo.css" rel=STYLESHEET type=text/css>
<script language=JavaScript>
function checar(name){
	forma = document.frm;
	// i = pedir____;
	i = name.substring(5,name.length);
	forma.i.value=i;
	cantidad = eval("forma.cantidad"+i);
	forma.cantidad.value=cantidad.value;
	if (cantidad.value==''||cantidad.value=='0'){
		alert ('- Debe introducir una cantidad valida o mayor a cero.');
		return false;
	}
	forma.accion.value=5;
	forma.submit();
}
function eliminar(name){
	forma = document.frm;
	// i = eliminar____;
	i = name.substring(8,name.length);
	forma.i.value=i;
	forma.accion.value=6;
	forma.submit();
}

</script>

</head>
<%@ include file="../main.jsp" %>
<body bgcolor="#FFFFFF" text="#000000">
<form name=frm action="SugerenciaPedido" method="post">
<input type="hidden" name=accion>
<input type="hidden" name=i>
<input type="hidden" name=cantidad>
<table class="tablatext" width=750 align="center" cellpadding="0" cellspacing="0">
<tr>
<%if (bean71.isBitsalidareciente()){%>
	<td><img src="images/tnc7_1.GIF"></td>
<%}else{%>
	<td><img src="images/tnc7_2.GIF"></td>
<%}%>
</tr>
</table>
<br>
<table align="center" border="1" bordercolor="#364859" rules="cols" cellspacing="0" width="750" style="border-color:Black;border-width:1px;border-style:solid;font-family: arial; font-size: 11px;border-collapse:collapse;" class="tablatext">
	<tr>
			<td colspan=4>Clasificación</td>
	</tr>
    <tr class="titStyle">
	    	<td width="25%">Categoría</td>
			<td width="25%">Grupo</td>
			<td width="25%">Línea</td>
			<td width="25%">Marca</td>
	</tr>
     <tr class="itemStyle">
			<td><%= request.getParameter("vchcategoria") %></td>
			<td><%= request.getParameter("vchgrupo") %></td>
			<td><%= request.getParameter("vchlinea") %></td>
			<td><%= request.getParameter("vchmarca") %></td>
	</tr>
</table>
<br>
<table align="center" border="1" bordercolor="#364859" rules="cols" cellspacing="0" width="750" style="border-color:Black;border-width:1px;border-style:solid;font-family: arial; font-size: 11px;border-collapse:collapse;" class="tablatext">
	<tr class="titStyle" align="left">
		<td>No.Parte: <%= bean71.getDvchparte(bean71.getIndice(0)) %></td>		
	</tr>
</table>
<br>
<table align="center" border="1" bordercolor="#364859" rules="cols" cellspacing="0" width="750" style="border-color:Black;border-width:1px;border-style:solid;font-family: arial; font-size: 11px;border-collapse:collapse;" class="tablatext">
<tr class="titStyle">
	  <td>Proveedor</td>
	  <td>Dias Inventario</td>
	  <td>M.Empaque</td>
	  <td>M.Embarque</td>	  
	  <td>Existencia</td>
	  <td>Consumo</td>
	  <td>Indice de resurtido</td>
	  <td>Back order</td>
	  <td>No enviados</td>
	  <td>Sugiere</td>
	  <td>Costo</td>	  
	  <td>Cantidad pedida</td>	  
	  <td>Pedir</td>	  
	  <td>&nbsp;</td>
	<% String stilo;
	for (int i = 0; i < bean71.getVindicessize(); i++) {
		int index = bean71.getIndice(i);
	 	if (i % 2 == 0)stilo = "itemStyle";
		else stilo = "alternatingItemStyle"; 
	%>
    <tr class="<%=stilo%>">
	<td><%= bean71.getDvchproveedor(index) %></td>
	<td align="right"><%= bean71.getDdiasinventario(index) %></td>
	<td align="right"><%= bean71.getDmultempaque(index) %></td>	
    <td align="right"><%= bean71.getDmembarque(index) %></td>
	<td align="right"><%= bean71.getDexistencia(index) %></td>
	<td align="right"><%= bean71.getDconsumo(index) %></td>
	<td align="right"><%= bean71.getDindiceresurtido(index) %></td>
	<td align="right"><%= bean71.getDbackorder(index) %></td>
	<td align="right"><%= bean71.getDnoenviados(index) %></td>
	<td align="right"><%= bean71.getDsugiere(index) %></td>
	<td align="right"><%= bean71.getDcosto(index) %></td>
	<td align="right"><%= bean71.getDcantidad(index) %></td>	
	<td><input type="text" name=cantidad<%=index%> size=4 class=numero value="<%= bean71.getDsugiere(index) %>"></td>
	<td><input type="button" name=pedir<%=index%> value=Pedir class=body onclick='checar(this.name)'></td>
	<input type="hidden" name="parte" value="<%= bean71.getDiidprovedor(index) %>">
	<!--td width="10%"><input type="button" name=eliminar<%=i %> value=Borrar class=body onclick='eliminar(this.name)'></td-->
   </tr>
<%}%> 
</table>
<br>
<table align="center" width="750">
<%if (bean71.isBitsalidareciente()){%>
	<tr><td><a href="PreSugerenciaPedidoReciente" class="ligas"><< Regresar</a></td></tr>
<%}else{%>
	<tr><td><a href="PreSugerenciaPedido" class="ligas"><< Regresar</a></td></tr>
<%}%>
</table>
<input type="hidden" name=iidcategoria value=<%= request.getParameter("iidcategoria") %>>
<input type="hidden" name=iidgrupo value=<%= request.getParameter("iidgrupo") %>>
<input type="hidden" name=iidlinea value=<%= request.getParameter("iidlinea") %>>
<input type="hidden" name=iidmarca value=<%= request.getParameter("iidmarca") %>>
<input type=hidden name="vchcategoria" value="<%= request.getParameter("vchcategoria") %>">
<input type=hidden name="vchgrupo" value="<%= request.getParameter("vchgrupo") %>">
<input type=hidden name="vchlinea" value="<%= request.getParameter("vchlinea") %>">
<input type=hidden name="vchmarca" value="<%= request.getParameter("vchmarca") %>">

</form>
</html>