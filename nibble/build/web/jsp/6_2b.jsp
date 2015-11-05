<html>
<jsp:useBean id="bean62" class="org.nibble.service.vo.partes.Bean62" scope="session"/>

<head>
<title>NC 6.2 Modificación de Partes</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link href="lib/estilo.css" rel=STYLESHEET type=text/css>
<script language=JavaScript>

function modificar_onclick(form){
	if (form.liidparte.value==''){
		alert ('Seleccione una parte')
		return false;
	}
	form.accion.value=5;
	return true;
}
</script>
</head>

<body bgcolor="#FFFFFF" text="#000000">
<form name=frm action="Partes" method="post">
<input type="hidden" name=accion>
<%@ include file="../main.jsp" %>
<table class="tablatext" width=750 align="center" cellpadding="0" cellspacing="0">
<tr>
	<td><img src="images/tnc6_2.GIF"></td>

        <th>
	<td></td>
        </th>

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
	  <td width="20%">Sel</td>
	  <td width="80%">Número de parte</td>
 	</tr>
<% String stilo;
	for (int i = 0; i < bean62.getPartesSize(); i++) {
	 	if (i % 2 == 0)stilo = "itemStyle";
		else stilo = "alternatingItemStyle"; 
	%>
    <tr class="<%=stilo%>">
    <td width="20%"><input type="radio" name="temp" value="<%= bean62.getLiidparte(i)%>" onclick="form.liidparte.value=this.value" ></td>
	<td width="80%"><%= bean62.getVchnumparte(i)%></td>
   </tr>
<%
}
%> 
</table>
<br>
<table align="center" border="1" bordercolor="#364859" rules="cols" cellspacing="0" width="750" style="border-color:Black;border-width:1px;border-style:solid;font-family: arial; font-size: 11px;border-collapse:collapse;" class="tablatext">
	<tr class="titStyle">
	<td align=center><input type="submit" value="Modificar" class=body onclick="return modificar_onclick(this.form)">	</td>
</tr>
</table>
<br>
<table align="center" width="750">
	<tr><td><a href="PreParteModifica" class="ligas"><< Regresar</a></td></tr>
</table>
<input type="hidden" name=liidparte >
<input type="hidden" name=iidcategoria value=<%= request.getParameter("iidcategoria") %>>
<input type="hidden" name=iidgrupo value=<%= request.getParameter("iidgrupo") %>>

<script LANGUAGE="JavaScript">

    str1 = "<%= request.getParameter("iidlinea")%>"
    str2 = "null";
	var a = String.compare(str1, str2);
    if ( a ==0 ) {
		form.iidlinea.value="";
    }
    else {
		form.iidlinea.value=str1;
    }

</SCRIPT>


<input type="hidden" name=iidlinea >

<script LANGUAGE="JavaScript">

    str11 = "<%= request.getParameter("iidmarca") %>"
    str22 = "null";
	var b = String.compare(str11, str22);
    if ( b ==0 ) {
		form.iidmarca.value="";
    }
    else {
		form.iidmarca.value=str11;
    }

</SCRIPT>


<input type="hidden" name=iidmarca >
<input type=hidden name="vchcategoria" value="<%= request.getParameter("vchcategoria") %>">
<input type=hidden name="vchgrupo" value="<%= request.getParameter("vchgrupo") %>">
<input type=hidden name="vchlinea" value="<%= request.getParameter("vchlinea") %>">
<input type=hidden name="vchmarca" value="<%= request.getParameter("vchmarca") %>">

</form>
</body>
</html>