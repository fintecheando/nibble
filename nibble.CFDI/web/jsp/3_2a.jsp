<html>
<jsp:useBean id="bean32" class="pva.beans3.Bean32" scope="session"/>
<head>
<title>PVA 3.2 Promociones</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link href="lib/estilo.css" rel=STYLESHEET type=text/css>
<link href="lib/selector.css" rel=STYLESHEET type=text/css>

<script language="JavaScript" src="lib/calendar2.js"></script>

<script type="text/javascript" src="lib/util.js"></script>
<script language="javascript">
function nuevo_onclick(form){
 var errors = '';
 var numErrors = 0;
	if (form.vchdescripcion.value==''){
		errors += '- Debe introducir la descripción de la promoción.\n';
		numErrors++;
	}
	if (form.decvolumencompra.value==''){
		errors += '- Debe introducir el monto mínimo de compra.\n';
		numErrors++;
	}
	if (form.sinopiezas.value==''){
		errors += '- Debe introducir la cantidad mínima a comprar.\n';
		numErrors++;
	}
	if (form.iexistencias.value==''){
		errors += '- Debe introducir el número de existencias.\n';
		numErrors++;
	}
	if (numErrors) {
		errors = 'Se produjeron ' + numErrors + ' error(es)\n'+ errors;
		alert(errors);
		return false;
	}
	form.accion.value=2;
	return true;
}

function modificar_onclick(form){
	var errors = '';
 	var numErrors = 0;
	if (form.iidpromocion.value==''){
		errors += '- Debe seleccionar una promoción.\n';
		numErrors++;
	}
	if (form.vchdescripcion.value==''){
		errors += '- Debe introducir la descripción de la promoción.\n';
		numErrors++;
	}
	if (form.decvolumencompra.value==''){
		errors += '- Debe introducir el monto mínimo de compra.\n';
		numErrors++;
	}
	if (form.sinopiezas.value==''){
		errors += '- Debe introducir la cantidad mínima a comprar.\n';
		numErrors++;
	}
	if (form.iexistencias.value==''){
		errors += '- Debe introducir el número de existencias.\n';
		numErrors++;
	}
	if (numErrors) {
		errors = 'Se produjeron ' + numErrors + ' error(es)\n'+ errors;
		alert(errors);
		return false;
	}
	form.accion.value=3;
	return true;
}

function eliminar_onclick(form){
 	var errors = '';
	var numErrors = 0;
	if (form.iidpromocion.value==''){
		errors += '- Debe seleccionar una promocion.\n';
		numErrors++;
	}
	if (numErrors) {
		errors = 'Se produjeron ' + numErrors + ' error(es)\n'+ errors;
		alert(errors);
		return false;
	}
	form.accion.value=4;
	return true;
}

function establecer(form,str){
	index = str.indexOf('/',0);
	form.iidpromocion.value = str.substring(0,index);
	inde = str.indexOf('/',index+1);
	form.decvolumencompra.value = str.substring(index+1,inde);
	ind = str.indexOf('/',inde+1);
	form.sinopiezas.value = str.substring(inde+1,ind);
	ins = str.indexOf('/',ind+1);
	form.iexistencias.value = str.substring(ind+1,ins);
	i = str.indexOf('/',ins+1);
	form.vchdescripcion.value = str.substring(ins+1,i);
	ii = str.indexOf('-',i+1);
	form.tisfechainicio.value =str.substring((str.length-21),(str.length-11));	
	form.tisfechafin.value = str.substring(str.length-10);
}

</script>
</head>
<%@ include file="../main.jsp" %>
<body>
<form name=frm action="Promociones" method="POST">
<input type=hidden name="accion">
<input type=hidden name="iidpromocion">
<input type=hidden name="categoria" value="<%=request.getParameter("categoria")%>">
<input type=hidden name="grupo" value="<%=request.getParameter("grupo")%>">
<input type=hidden name="linea" value="<%=request.getParameter("linea")%>">
<input type=hidden name="marca" value="<%=request.getParameter("marca")%>">
<table class="tablatext" width=750 align="center" cellpadding="0" cellspacing="0">
<tr>
	<td><img src="images/tpva3_2.GIF"></td>

        <th>
        <td><a href="../pvatx/hlp/pva3.0-3.2.htm#pasos" target="Ayuda Promociones">Ayuda</a></td>
        </th>
</tr>
</table>
<br>
<table align=center border=1 borderColor=#364859 cellSpacing=0 class=tablatext rules=cols style="BORDER-BOTTOM: black 1px solid; BORDER-COLLAPSE: collapse; BORDER-LEFT: black 1px solid; BORDER-RIGHT: black 1px solid; BORDER-TOP: black 1px solid; FONT-FAMILY: arial; FONT-SIZE: 11px" width=750>
	<tr>
		<td colspan="4">Clasificación</td>
	</tr>
	<tr class="titStyle">
		<td>Categoria</td>
		<td>Grupo</td>		
		<td>Linea</td>		
		<td>Marca</td>		
	</tr>
	<tr>
		<td><%=bean32.getIidcategoria()%></td>
		<td><%=bean32.getIidgrupo()%></td>		
		<td><%=bean32.getIidlinea()%></td>		
		<td><%=bean32.getIidmarca()%></td>		
	</tr>	
</table>
<br>
<table align=center border=1 borderColor=#364859 cellSpacing=0 class=tablatext rules=cols style="BORDER-BOTTOM: black 1px solid; BORDER-COLLAPSE: collapse; BORDER-LEFT: black 1px solid; BORDER-RIGHT: black 1px solid; BORDER-TOP: black 1px solid; FONT-FAMILY: arial; FONT-SIZE: 11px" width=750>
	<tr class="headerStyle">
		<td width="20%">Sel</td>
		<td width="80%">Descripción de la Promoción</td>		
	</tr>
	<% String stilo;
		for(int i=0; i < bean32.getPromocionSize(); i++){
	 		 if (i % 2 == 0)stilo = "itemStyle";
			 else stilo = "alternatingItemStyle"; 
	%>
	<tr class="<%=stilo%>">
		<td width="20%"><input type="radio" name="promocion" value="<%=bean32.getPiidpromocion(i)%>/<%=bean32.getPdecvolumencompra(i)%>/<%=bean32.getPsinopiezas(i)%>/<%=bean32.getPiexistencias(i)%>/<%=bean32.getPvchdescripcion(i)%>/<%=bean32.getPtisfechainicio(i)%>/<%=bean32.getPtisfechafin(i)%>" onclick="establecer(form, this.value)"></td>
		<td width="80%"><%=bean32.getPvchdescripcion(i)%></td>
	</tr>
	<%}%>
</table>
<br>
<table align=center border=1 borderColor=#364859 cellSpacing=0 class=tablatext rules=cols style="BORDER-BOTTOM: black 1px solid; BORDER-COLLAPSE: collapse; BORDER-LEFT: black 1px solid; BORDER-RIGHT: black 1px solid; BORDER-TOP: black 1px solid; FONT-FAMILY: arial; FONT-SIZE: 11px" width=750>
	<tr class="headerStyle">
	 <td colspan="3">Descripción de la Promoción</td>
	</tr> 
	<tr>
	 <td colspan="3"><input type="text" name="vchdescripcion"></td>
	</tr>
	<tr class="titStyle">
	 <td>Monto mínimo de compra</td>
	 <td>Cantidad mínima a comprar</td>
	 <td>Número de existencias</td>
	</tr>
	<tr>
	 <td><input type="text" name="decvolumencompra"></td>
	 <td><input type="text" name="sinopiezas"></td>
	 <td><input type="text" name="iexistencias"></td>
	</tr>
	<tr class="titStyle">
	 <td>Fecha de inicio</td>
	 <td colspan="2">Fecha de termino</td>	 
	</tr>
	<tr>
	 <td>
	 	<input type="text" name="tisfechainicio">
        <a href="javascript:cal5.popup();" onMouseOut="window.status='';return true;" onMouseOver="window.status='Seleccione Fecha';return true;"><img border=0 src="images/calendar.gif"></a>
	 </td>
	 <td colspan="2">
	 	<input type="text" name="tisfechafin">
		<a href="javascript:cal6.popup();" onMouseOut="window.status='';return true;" onMouseOver="window.status='Seleccione Fecha';return true;"><img border=0 src="images/calendar.gif"></a>		
	 </td>	 
	 
<SCRIPT LANGUAGE="JavaScript">

			var cal5 = new calendar2(document.forms['frm'].elements['tisfechainicio']);
			cal5.year_scroll = true;
			cal5.time_comp = false;
			
			var cal6 = new calendar2(document.forms['frm'].elements['tisfechafin']);
			cal6.year_scroll = true;
			cal6.time_comp = false;					
		</SCRIPT>
	 
	</tr>	
	<tr class="titStyle">
	 <td colspan="3" align=center>
	    <input type="submit" class="body" value="Crear" onclick="return nuevo_onclick(this.form)">
		<input type="submit" class="body" value="Modificar" onclick="return modificar_onclick(this.form)">
		<input type="submit" class="body" value="Eliminar" onclick="return eliminar_onclick(this.form)">
		<input type="reset" class="body" value="Reestablecer">
	 </td>
    </tr>
</table>
</form>
</body>
</html>
