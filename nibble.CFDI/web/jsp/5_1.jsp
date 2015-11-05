<html>
<jsp:useBean id="bean5" class="org.nibble.service.vo.proveedores.Bean5" scope="session"/>
<jsp:useBean id="beanMsg" class="get.BeanMsg" scope="session"/>

<head>
<title>NC 5.1 Alta de proveedores</title>
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
for (int i=0; i<bean5.getPaissSize();i++){
%>
paiss[c++] = new Array(<%= bean5.getPaissId(i) %>,"<%= bean5.getPaissDes(i) %>");
<%
}
%> 
c=0;
var estados  = new Array();
estados[c++] = new Array("iidestado",   "vchnombre", "siidpais");
estados[c++] = new Array("int",   "String", "int");
<%
for (int i=0; i<bean5.getEstadosSize();i++){
%>
estados[c++] = new Array(<%= bean5.getEstadoId(i) %>,"<%= bean5.getEstadoDes(i) %>",<%= bean5.getEstadoIdPais(i) %>);
<%
}
%> 
c=0;
var municipios  = new Array();
municipios[c++] = new Array("iidmunicipio",   "vchnombre", "iidestado");
municipios[c++] = new Array("int",   "String", "int");
<%
for (int i=0; i<bean5.getMunicipiosSize();i++){
%>
municipios[c++] = new Array(<%= bean5.getMunicipioId(i) %>,"<%= bean5.getMunicipioDes(i) %>",<%= bean5.getMunicipioIdEstado(i) %>);
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

function agregar_onclick(form){
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
	if (form.vchcurp.value=='') {
		errors += '- Debe introducir la CURP.\n';
		numErrors++;
	}        	
	if (form.vchtel1.value=='') {
		errors += '- Debe introducir el teléfono 1.\n';
		numErrors++;
	}        	
	if (form.vchtel2.value=='') {
		errors += '- Debe introducir el teléfono 2.\n';
		numErrors++;
	}
	if (form.vchfax.value=='') {
		errors += '- Debe introducir el fax.\n';
		numErrors++;
	}
	if (form.vchmail.value=='') {
		errors += '- Debe introducir el mail\n';
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
	if (form.vchcp.value=='') {
		errors += '- Debe introducir el cp\n';
		numErrors++;
	}        	
	if (form.siidpais.value=='' || form.iidestado.value=='' || form.iidmunicipio.value=='') {
		errors += '- Debe seleccionar la ubicación completa.\n';
		numErrors++;
	}
	if (!(form.back[0].checked) && !(form.back[1].checked)){
		errors += '- Debe seleccionar si acepta o no back order.\n';
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

</script>
</head>
<%@ include file="../main.jsp" %>
<body bgcolor="#FFFFFF" text="#000000">
<form name=frm action="Proveedores" method="post">
<input type=hidden name="accion">
<table class="tablatext" width=750 align="center" cellpadding="0" cellspacing="0">
<tr>
	<td><img src="images/tnc5_1.GIF"></td>

        <th>
	<td><a href="../nctx/hlp/nc5.0-5.1.htm" target="Ayuda Alta de Proveedores">Ayuda</a></td>
        </th>

</tr>
</table>
<br>
<table align="center" border="1" bordercolor="#364859" rules="cols" cellspacing="0" width="750" style="border-color:Black;border-width:1px;border-style:solid;font-family: arial; font-size: 11px;border-collapse:collapse;" class="tablatext">
<tr class="titStyle">
	<td width="50%">Nombre</td>
	<td width="50%">Razón Social</td>
</tr>
<tr class="itemStyle">
	<td><input type=text name="vchnombre" class="body" size=60 maxlength="60"></td>
	<td><input type=text name="vchrazonsocial" class="body" size=60 maxlength="60"></td>
</tr>
<tr class="titStyle">
	<td width="50%">RFC</td>
	<td width="50%">C.U.R.P</td>
</tr>
<tr class="itemStyle">
	<td><input type=text name="vchrfc" class="body" size=15 maxlength="15"></td>
	<td><input type=text name="vchcurp" class="body" size=20 maxlength="20"></td>
</tr>
<tr class="titStyle">
	<td width="50%">Teléfono 1</td>
	<td width="50%">Teléfono 2</td>
</tr>
<tr class="itemStyle">
	<td><input type=text name="vchtel1" class="body" size=25 maxlength="25"></td>
	<td><input type=text name="vchtel2" class="body" size=25 maxlength="25"></td>
</tr>
<tr class="titStyle">
	<td width="50%">Fax</td>
	<td width="50%">Email</td>
</tr>
<tr class="itemStyle">
	<td><input type=text name="vchfax" class="body" size=25 maxlength="25"></td>
	<td><input type=text name="vchmail" class="body" size=70 maxlength="80"></td>
</tr>
<tr class="titStyle">
	<td width="50%">Colonia</td>
	<td width="50%">Calle y Número</td>
</tr>
<tr class="itemStyle">
	<td><input type=text name="vchcolonia" class="body" size=20 maxlength="20"></td>
	<td><input type=text name="vchcalle" class="body" size=60 maxlength="60"></td>
</tr>
<tr class="titStyle">
	<td width="50%">Código Postal </td>
	<td width="50%">Back order </td>
</tr>
<tr class="itemStyle">
	<td><input type=text name="vchcp" class="body" size=5 maxlength="5"  onkeypress="return numbersonly(event)"></td>
	<td><input type=radio name="back" value=Si>Si <input type=radio name="back" value=No>No  </td>
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
		<td><select id="master" name="siidpais" class="body" onchange="FillSlave();FillSlave2();">
			<%for (int i=0; i<bean5.getPaissSize();i++){%>
			<option value="<%= bean5.getPaissId(i) %>" <%if (bean5.getPAIS_DEFAULT()==bean5.getPaissId(i)){%>selected<%}%> ><%= bean5.getPaissDes(i) %></option>
			<%}%> 
      		</select>
		</td>
		<td><select id="slave" name="iidestado" class="body" onchange="FillSlave2();">
	    	<option value=""></option>
    		<script type="text/javascript"> 
			var statecities = Where( estados, "siidpais", "=", <%= bean5.getPAIS_DEFAULT()%> );
			PrintOptions( statecities, "iidestado", "vchnombre"); 
			</script>
  			</select>
		</td>
		<td><select id="sslave" name="iidmunicipio" class="body">
  			</select>
		</td>
	</tr>
</table>
<br>
<table align="center" border="1" bordercolor="#364859" rules="cols" cellspacing="0" width="750" style="border-color:Black;border-width:1px;border-style:solid;font-family: arial; font-size: 11px;border-collapse:collapse;" class="tablatext">
	<tr class="titStyle">
		<td align=center><input type="submit" value="Agregar" class=body onclick="return agregar_onclick(this.form)"></td>
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
