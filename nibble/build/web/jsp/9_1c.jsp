<html>
<jsp:useBean id="bean91b" class="pva.beans9.Bean91b" scope="session"/>

<head>
<title>PVA 9.1 Facturar</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link href="lib/estilo.css" rel=STYLESHEET type=text/css>
<script language=JavaScript>

function aceptar_onclick(form){
	dad = window.opener.document.frm;
	
	if (form.iidnocliente.value==''){
		alert ('Debe seleccionar un cliente')
		return false;
	}
	window.opener.document.frm.iidnocliente.value=document.frm.iidnocliente.value;
	window.opener.document.frm.accion.value=4;
	window.opener.document.frm.submit();
	window.close();
}
function selec(i){
	document.frm.temp[i].checked=true
	document.frm.iidnocliente.value=document.frm.temp[i].value;
}

</script>
</head>
<body bgcolor="#FFFFFF" text="#000000">
<form name=frm>
<input type="hidden" name=accion>
<input type="hidden" name=iidnocliente>
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
   	  <td width="10%">Sel</td>
	  <td width="10%">Id</td>
	  <td width="40%">Raz&oacute;n Social</td>
	  <td width="40%">RFC</td>
 </tr>

<% String stilo;
		   for (int i = 0; i < bean91b.getClientesSize(); i++) {
	 		 if (i % 2 == 0)stilo = "itemStyle";
			 else stilo = "alternatingItemStyle"; 
  %>
  <tr class="<%=stilo%>">
  	<td width="10%"><input type=radio name=temp value="<%= bean91b.getIidnocliente(i) %>" onclick="this.form.iidnocliente.value=this.value"></td>
    <td width="10%"><a href="javascript:selec(<%=i%>)" class="ligas"><%= bean91b.getIidnocliente(i) %></a></td>
	<td width="40%"><a href="javascript:selec(<%=i%>)" class="ligas"><%= bean91b.getVchrazonsocial(i) %></a></td>
	<td width="40%"><a href="javascript:selec(<%=i%>)" class="ligas"><%= bean91b.getVchrfc(i) %></a></td>
   </tr>
<%
}
%> 
</table>
<br>
<table width=550 align="center" border="1" bordercolor="#364859" rules="cols" cellspacing="0" style="border-color:Black;border-width:1px;border-style:solid;font-family: arial; font-size: 11px;border-collapse:collapse;" class="tablatext">
<tr class="titStyle">
	<td align=center><input type="button" value="Aceptar" class=body onclick="aceptar_onclick(this.form)" >	</td>
</tr>
</table>
<table align="center" width="75%">
	<tr><td><a href="javascript:history.go(-1);" class="ligas"><< Regresar</a></td></tr>
</table>
</form>
</body>
</html>
