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
	i = name.substring(5,name.length);
	forma.i.value=i;
	forma.accion.value=4;
	forma.submit();
}
 function openWindow(liidparte,hisconsumo,mm,yy) {

    url="HistoricoConsumo?liidparte="+liidparte+"&hisconsumo="+hisconsumo+"&mm="+mm+"&yy="+yy;

    window.open(url,"","toolbar=0,location=0,directories=0,status=0,menubar=0,scrollbars=yes,resizable=yes,width=450,height=200");
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
<tr class="titStyle">
  	  <td>No. de parte</td>
	  <td>Existencia</td>
	  <td>Consumo</td>
	  <td>Indice de resurtido</td>
	  <td>Back order</td>
	  <td>No enviados</td>
	  <td>Sugiere</td>
	  <td>Cantidad Pedida</td>	  
	  <td>&nbsp;</td>
	<% String stilo;
	for (int i = 0; i < bean71.getVconcentradosize(); i++) {
	 	if (i % 2 == 0)stilo = "itemStyle";
		else stilo = "alternatingItemStyle"; 
	%>
    <tr class="<%=stilo%>">
 	<td><a href="javascript:openWindow('<%= bean71.getCliidparte(i)%>','<%= bean71.getChconsumo(i)%>','<%= bean71.getCmm(i)%>','<%= bean71.getCyy(i)%>');" class="ligas"><%= bean71.getCvchparte(i)%></a></td>
	<td align="right"><%= bean71.getCexistencia(i) %></td>
    <td align="right"><%= bean71.getCconsumo(i) %></td>
	<td align="right"><%= bean71.getCindiceresurtido(i) %></td>
	<td align="right"><%= bean71.getCbackorder(i) %></td>
	<td align="right"><%= bean71.getCnoenviados(i) %></td>
	<td align="right"><%= bean71.getCsugiere(i) %></td>
	<td align="right"><%= bean71.getCcantidad(i) %></td>	
	<td align="center"><input type="button" name=pedir<%=i%> value=Pedir class=body onclick='checar(this.name)'></td>
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
