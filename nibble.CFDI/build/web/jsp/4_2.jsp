<html>
<jsp:useBean id="bean4" class="pva.beans4.Bean4" scope="session"/>
<jsp:useBean id="bean42" class="pva.beans4.Bean42" scope="session"/>
<jsp:useBean id="beanresult42" class="pva.beans4.BeanResult42" scope="session"/>
<jsp:useBean id="beanMsg" class="get.BeanMsg" scope="session"/>
<head>
<title>PVA Cambios a clientes y Condiciones de Crédito</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link href="lib/estilo.css" rel=STYLESHEET type=text/css>
<script type="text/javascript" src="lib/util.js"></script>
<script type="text/javascript" src="lib/engine/jsSQL.js"></script>
<script type="text/javascript" src="lib/tools/PrintOptions.js"></script>
<script type="text/javascript" src="lib/tools/FillOptions.js"></script>

<script type="text/javascript">
var c=0;
var paiss  = new Array();
paiss[c++] = new Array("siidpais",   "vchnompais");
paiss[c++] = new Array("int",   "String");
<%
for (int i=0; i<bean4.getPaissSize();i++){
%>
paiss[c++] = new Array(<%= bean4.getPaissId(i) %>,"<%= bean4.getPaissDes(i) %>");
<%
}
%> 
c=0;
var estados  = new Array();
estados[c++] = new Array("iidestado",   "vchnombre", "siidpais");
estados[c++] = new Array("int",   "String", "int");
<%
for (int i=0; i<bean4.getEstadosSize();i++){
%>
estados[c++] = new Array(<%= bean4.getEstadoId(i) %>,"<%= bean4.getEstadoDes(i) %>",<%= bean4.getEstadoIdPais(i) %>);
<%
}
%> 
c=0;
var municipios  = new Array();
municipios[c++] = new Array("iidmunicipio",   "vchnombre", "iidestado");
municipios[c++] = new Array("int",   "String", "int");
<%
for (int i=0; i<bean4.getMunicipiosSize();i++){
%>
municipios[c++] = new Array(<%= bean4.getMunicipioId(i) %>,"<%= bean4.getMunicipioDes(i) %>",<%= bean4.getMunicipioIdEstado(i) %>);
<%
}
%> 

function FillSlave()
{
 var f=document.forms["frm"];
 var m=f.siidpais;
 var s=f.iidestado;
 var code
 if (m.length>0)
 	 code=m.options[m.selectedIndex].value;
 else code="";
 if (code=="") DelOptions(s);
 else
 {
  var statecities = Where( estados, "siidpais", "=", code );
  FillOptions( statecities, s, 'iidestado', 'vchnombre', false );
  statecities = null;
 }
}

function FillSlave2()
{
 var f=document.forms["frm"];
 var m=f.iidestado;
 var s=f.iidmunicipio;
 var code;
 if (m.length>0)
  	code=m.options[m.selectedIndex].value;
 else code="";
 if (code=="") DelOptions(s);
 else
 {
  var muns = Where( municipios, "iidestado", "=", code );
  FillOptions( muns, s, 'iidmunicipio', 'vchnombre', false );
  muns = null;
 }
}

function modificar_onclick(form){
    var errors = '';
	var numErrors = 0;
	if (form.vchnombre.value=='') {
		errors += '- Debe introducir el nombre.\n';
		numErrors++;
	}        	
	if (form.vchrazonsocial.value=='') {
		errors += '- Debe introducir la Razón Social.\n';
		numErrors++;
	}        	
	if (form.vchrfc.value=='') {
		errors += '- Debe introducir el RFC.\n';
		numErrors++;
	}
	if (form.vchcolonia.value=='') {
		errors += '- Debe introducir la colonia\n';
		numErrors++;
	}        	
	if (form.vchcalle.value=='') {
		errors += '- Debe introducir la calle y número\n';
		numErrors++;
	}        	
	if (form.chcodigop.value=='') {
		errors += '- Debe introducir el C.P.\n';
		numErrors++;
	}        	
	if (form.siidpais.value=='' || form.iidestado.value=='' || form.iidmunicipio.value=='') {
		errors += '- Debe seleccionar la ubicación completa.\n';
		numErrors++;
	}
	if (numErrors) {
		errors = 'Se produjeron ' + numErrors + ' error(es)\n'+ errors;
		alert(errors);
		return false;
	}
	form.accion.value=5;
	return true;
}
function checa(){
	forma = document.frm;
	if  (forma.iidnoclienteTemp.value != forma.iidnocliente.value)
		forma.accion.value=4;	
}
</script>
</head>
<%@ include file="../main.jsp" %>
<body bgcolor="#FFFFFF" text="#000000">
<form name=frm action="Clientes"  method="post"  onsubmit="return checa()">
<input type=hidden name="accion">
<input type=hidden name="iidnoclienteTemp" value=<%= beanresult42.getIidnocliente() %>> 
<table class="tablatext" width=750 align="center" cellpadding="0" cellspacing="0">
<tr>
	<td><img src="images/tpva4_2.GIF"></td>

        <th>
	<td><a href="../pvatx/hlp/pva4.0-4.2.htm" target="Ayuda camb. cli. y cond. cred.">Ayuda</a></td>
        </th>

</tr>
</table>
<br><br>
<table align="center" border="1" bordercolor="#364859" rules="cols" cellspacing="0" width="750" style="border-color:Black;border-width:1px;border-style:solid;font-family: arial; font-size: 11px;border-collapse:collapse;" class="tablatext">
		<tr class="titStyle"> 
	    	<td width="33%">Número</td>
			<td width="33%">Razón Social</td>
			<td width="34%">R.F.C</td>
		</tr>
		<tr class="itemStyle">
			<td><input type=text name="iidnocliente" class="body" size=2 value=<%= beanresult42.getIidnocliente() %>><input type=button value="Buscar" class="body" onclick="this.form.accion.value=4;this.form.submit()" ></td>
			<td><select name="razonsocial" class="body" onchange="this.form.accion.value=4;this.form.iidnocliente.value=this.form.razonsocial.options[this.form.razonsocial.selectedIndex].value;form.submit()">
			<%for (int i=0; i<bean42.getClientesSize();i++){%>
				<option value=<%= bean42.getId(i) %> <% if (beanresult42.getIidnocliente()==bean42.getId(i)){%> SELECTED <% } %>><%= bean42.getRazonsocial(i) %></option>
			<%}%>   		
				</select>    
			</td>
			<td><select name="rfc" class="body" onchange="this.form.accion.value=4;this.form.iidnocliente.value=this.form.rfc.options[this.form.rfc.selectedIndex].value;form.submit()">
			<%for (int i=0; i<bean42.getClientesSize();i++){%>
				<option value=<%= bean42.getId(i) %> <% if (beanresult42.getIidnocliente()==bean42.getId(i)){%> SELECTED <% } %>><%= bean42.getRfc(i) %></option>
			<%}%>   		    
				</select>
			</td>
		</tr>
</table>
<br>
<table class="tablatext" width=750 align="center" cellpadding="0" cellspacing="0">
<tr><td>
<input type=button value="Condiciones Comerciales" class="body" onclick="this.form.action='ClientesCondComerciales';this.form.accion.value=1;form.submit()">
</td></tr>
</table>
<br>
<table align="center" border="1" bordercolor="#364859" rules="cols" cellspacing="0" width="750" style="border-color:Black;border-width:1px;border-style:solid;font-family: arial; font-size: 11px;border-collapse:collapse;" class="tablatext">
<tr class="titStyle"> 
	<td width="50%" colspan=2>Nombre</td>
	<td width="50%" colspan=2>Razón Social</td>
</tr>
<tr class="itemStyle">
	<td colspan=2><input type=text name="vchnombre" class="body" size=60 maxlength="60" value="<%= beanresult42.getVchnombre() %>" <% if  (beanresult42.isMostrador()){%> DISABLED <% } %>></td>
	<td colspan=2><input type=text name="vchrazonsocial" class="body" size=60 maxlength="60" value="<%= beanresult42.getVchrazonsocial() %>" <% if  (beanresult42.isMostrador()){%> DISABLED <% } %>></td>
</tr>
<tr class="titStyle">
	<td width="25%">RFC</td>
	<td width="25%">C.U.R.P</td>
	<td width="25%">Teléfono 1</td>
	<td width="25%">Teléfono 2</td>
</tr>
<tr class="itemStyle">
	<td><input type=text name="vchrfc" class="body" size=15 maxlength="15" value="<%= beanresult42.getVchrfc() %>" <% if  (beanresult42.isMostrador()){%> DISABLED <% } %>></td>
	<td><input type=text name="vchcurp" class="body" size=20 maxlength="20" value="<%= beanresult42.getVchcurp() %>" <% if  (beanresult42.isMostrador()){%> DISABLED <% } %>></td>
	<td><input type=text name="vchtel1" class="body" size=25 maxlength="25" value="<%= beanresult42.getVchtel1() %>" <% if  (beanresult42.isMostrador()){%> DISABLED <% } %>></td>
	<td><input type=text name="vchtel2" class="body" size=25 maxlength="25" value="<%= beanresult42.getVchtel2() %>" <% if  (beanresult42.isMostrador()){%> DISABLED <% } %>></td>
</tr>
<tr class="titStyle">
	<td width="50%" colspan=2>Fax</td>
	<td width="50%" colspan=2>Email</td>
</tr>
<tr class="itemStyle">
	<td colspan=2><input type=text name="vchfax" class="body" size=25 maxlength="25" value="<%= beanresult42.getVchfax() %>" <% if  (beanresult42.isMostrador()){%> DISABLED <% } %>></td>
	<td colspan=2><input type=text name="vchmail" class="body" size=70 maxlength="80" value="<%= beanresult42.getVchmail() %>" <% if  (beanresult42.isMostrador()){%> DISABLED <% } %>></td>
</tr>
</table>
<br>
<table align="center" border="1" bordercolor="#364859" rules="cols" cellspacing="0" width="750" style="border-color:Black;border-width:1px;border-style:solid;font-family: arial; font-size: 11px;border-collapse:collapse;" class="tablatext">
<tr class="titStyle"> 
	<td width="40%">Colonia</td>
	<td width="40%">Calle</td>
	<td width="20%">Código Postal</td>
</tr>
<tr class="itemStyle">
	<td><input type=text name="vchcolonia" class="body" size=35 maxlength="20" value="<%= beanresult42.getVchcolonia() %>" <% if  (beanresult42.isMostrador()){%> DISABLED <% } %>></td>
	<td><input type=text name="vchcalle" class="body" size=35 maxlength="60" value="<%= beanresult42.getVchcalle() %>" <% if  (beanresult42.isMostrador()){%> DISABLED <% } %>></td>
	<td><input type=text name="chcodigop" class="body" size=5 maxlength="10" value="<%= beanresult42.getChcodigop() %>" <% if  (beanresult42.isMostrador()){%> DISABLED <% } %>></td>
</tr>
<tr class="titStyle">
	<td width="50%">Localidad</td>
	<td width="25%">Número Exterior</td>
        <td width="25%">Número Interior</td>
</tr>
<tr class="itemStyle">
        <td><input type=text name="vchlocalidad" class="body" size=35 maxlength="60" value="<%= beanresult42.getVchlocalidad() %>" <% if  (beanresult42.isMostrador()){%> DISABLED <% } %>></td>
	<td><input type=text name="vchnumeroexterior" class="body" size=15 maxlength="40" value="<%= beanresult42.getVchnumeroexterior() %>" <% if  (beanresult42.isMostrador()){%> DISABLED <% } %>></td>
	<td><input type=text name="vchnumerointerior" class="body" size=15 maxlength="40" value="<%= beanresult42.getVchnumerointerior() %>" <% if  (beanresult42.isMostrador()){%> DISABLED <% } %>></td>
</tr>
</table>
<br>
<table align="center" border="1" bordercolor="#364859" rules="cols" cellspacing="0" width="750" style="border-color:Black;border-width:1px;border-style:solid;font-family: arial; font-size: 11px;border-collapse:collapse;" class="tablatext">
<tr class="titStyle"> 
	    	<td width="33%">País</td>
			<td width="33%">Estado</td>
			<td width="34%">Municipio</td>
		</tr>
<tr class="itemStyle">
			<td><select id="master" name="siidpais" class="body" onchange="FillSlave();FillSlave2();" <% if  (beanresult42.isMostrador()){%> DISABLED <% } %>>
	    		<option value="" id="master1"></option>
				<%for (int i=0; i<beanresult42.getPaissSize();i++){%>
					<option value=<%= beanresult42.getPaissId(i) %> <% if  (beanresult42.getSiidpais()== beanresult42.getPaissId(i)){%> SELECTED <% } %>><%= beanresult42.getPaissDes(i) %></option>
				<%}%>   	
				    </select>
			</td>
			<td><select id="slave" name="iidestado" class="body" onchange="FillSlave2();" <% if  (beanresult42.isMostrador()){%> DISABLED <% } %>>
				<%for (int i=0; i<beanresult42.getEstadosSize();i++){%>
					<option value=<%= beanresult42.getEstadoId(i) %> <% if  (beanresult42.getIidestado()== beanresult42.getEstadoId(i)){%> SELECTED <% } %>><%= beanresult42.getEstadoDes(i) %></option>
				<%}%>   	
  				</select>
			</td>
			<td><select id="sslave" name="iidmunicipio" class="body" <% if  (beanresult42.isMostrador()){%> DISABLED <% } %>>
			<%for (int i=0; i<beanresult42.getMunicipiosSize();i++){%>
				<option value=<%= beanresult42.getMunicipioId(i) %> <% if  (beanresult42.getIidmunicipio()== beanresult42.getMunicipioId(i)){%> SELECTED <% } %>><%= beanresult42.getMunicipioDes(i) %></option>
			<%}%>   	
  				</select>
			</td>
</tr>
</table>
<br>
<table align="center" border="1" bordercolor="#364859" rules="cols" cellspacing="0" width="750" style="border-color:Black;border-width:1px;border-style:solid;font-family: arial; font-size: 11px;border-collapse:collapse;" class="tablatext">
<tr class="titStyle"> 
	<td align=center><input type="submit" value="Modificar" class=body onclick="return modificar_onclick(this.form)" <% if  (beanresult42.isMostrador()){%> DISABLED <% } %>></td>
</tr>
</table>
<br>
<table class="tablatext" width=750 align="center" cellpadding="0" cellspacing="0">
<tr>
	<td class=msg><%= beanMsg.getMsg() %></td>
</tr>
</table>
</form>
</body>
</html>
