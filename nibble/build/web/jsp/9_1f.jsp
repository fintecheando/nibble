<html>
<jsp:useBean id="bean91d" class="pva.beans9.Bean91d" scope="session"/>

<head>
<title>PVA 9.1 Facturar</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link href="lib/estilo.css" rel=STYLESHEET type=text/css>
<script language=JavaScript>

function aceptar_onclick(form){
	dad = window.opener.document.frm;
	
	if (form.liidparte.value==''){
		alert ('Debe seleccionar una parte')
		return false;
	}
	dad.liidparte.value=document.frm.liidparte.value;
	dad.accion.value=8;
	dad.submit();
	window.close();
}
function selec(i){
	document.frm.temp[i].checked=true
	document.frm.liidparte.value=document.frm.temp[i].value;
	aceptar_onclick(document.frm);
}

</script>
</head>
<body bgcolor="#FFFFFF" text="#000000">
<form name=frm action="Facturar" method="post">
<input type="hidden" name=accion value=5>
<input type="hidden" name=liidparte>
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
<table width=550 align="center" border="1" bordercolor="#364859" rules="cols" cellspacing="0" style="border-color:Black;border-width:1px;border-style:solid;font-family: arial; font-size: 11px;border-collapse:collapse;" class="tablatext">
  <tr class="headerStyle">
	  <td width="5%">Sel</td>
	  <td width="20%">Número de parte Equival</td>
	  <td width="15%">Categoría</td>
	  <td width="15%">Grupo</td>
	  <td width="15%">Línea</td>
	  <td width="15%">Marca</td>
 </tr>
 <% String stilo;
		   for (int i = 0; i < (bean91d.getPartesSize()); i++) {
	 		 if (i % 2 == 0)stilo = "itemStyle";
			 else stilo = "alternatingItemStyle"; 
  %>
  
  <tr class="<%=stilo%>">
    <td width="5%"><input type="radio" name="temp" value="<%= bean91d.getLiidparte(i)%>" onclick="this.form.liidparte.value=this.value"></td>
	<td width="20%"><a href="javascript:selec(<%=i%>)" class="ligas"><%= bean91d.getVchnumparteequival(i)%></a></td>
	<td width="15%"><%= bean91d.getVchcategoria(i)%></td>
	<td width="15%"><%= bean91d.getVchgrupo(i)%></td>
	<td width="15%"><%= bean91d.getVchlinea(i)%></td>
	<td width="15%"><%= bean91d.getVchmarca(i)%></td>
   </tr>
<%
}
%> 
</table>
<br>
<table width=550 align="center" border="1" bordercolor="#364859" rules="cols" cellspacing="0" style="border-color:Black;border-width:1px;border-style:solid;font-family: arial; font-size: 11px;border-collapse:collapse;" class="tablatext">
<tr class="titStyle">
	<td align=center><input type="button" value="Aceptar" class=body onclick="return aceptar_onclick(this.form)">	</td>
</tr>
</table>
<br>
<table align="center" width="550">
	<tr><td><a href="javascript: document.frm.submit();" class="ligas"><< Regresar</a></td></tr>
</table>
</form>
</body>
</html>
