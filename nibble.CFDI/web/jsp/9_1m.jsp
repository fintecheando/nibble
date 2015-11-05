<html>
<jsp:useBean id="bean4" class="pva.beans4.Bean4" scope="session"/>

<head>
<title>PVA 4.1 Alta de clientes</title>
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
 actualiza();
}
function actualiza(){
var f=document.forms["frm"];
if (f.siidpais.length > 0 )
	f.vchpais.value  = f.siidpais.options[f.siidpais.selectedIndex].text;
else
	f.vchpais.value  = '';
if (f.iidestado.length > 0 )
	f.vchestado.value  = f.iidestado.options[f.iidestado.selectedIndex].text;
else
	f.vchestado.value  = '';
if (f.iidmunicipio.length > 0 )
	f.vchmunicipio.value  = f.iidmunicipio.options[f.iidmunicipio.selectedIndex].text;
else
	f.vchmunicipio.value  = '';

}

function agregar_onclick(form){
    var errors = '';
	var numErrors = 0;
	if (form.vchrazonsocial.value=='') {
		errors += '- Debe inrtoducir la Razon Social.\n';
		numErrors++;
	}        	
	if (form.vchrfc.value=='') {
		errors += '- Debe inrtoducir el RFC.\n';
		numErrors++;
	}        	
	if (form.vchcolonia.value=='') {
		errors += '- Debe inrtoducir la colonia\n';
		numErrors++;
	}        	
	if (form.vchcalle.value=='') {
		errors += '- Debe inrtoducir la calle y numero\n';
		numErrors++;
	}        	
	if (form.chcodigop.value=='') {
		errors += '- Debe inrtoducir el cp\n';
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
	form.accion.value=22;
	return true;
}

</script>
</head>
<body bgcolor="#FFFFFF" text="#000000">
<form name=frm action="Facturar">
<input type=hidden name="accion">
<input type="hidden" name="accionPost" value=18>
<table class="tablatext" width=550 align="center" cellpadding="0" cellspacing="0">
<tr>
	<td><img src="images/tpva9_1.GIF"></td>

        <th>
	<td><a href="../pvatx/hlp/pva9.0-9.1.htm" target="Ayuda Facturar">Ayuda</a></td>
        </th>

</tr>
</table>
<br>
<table align="center" border="1" bordercolor="#364859" rules="cols" cellspacing="0" width="550" style="border-color:Black;border-width:1px;border-style:solid;font-family: arial; font-size: 11px;border-collapse:collapse;" class="tablatext">
<tr class="titStyle"> 
	<td>Raz&oacute;n Social</td>
</tr>
<tr class="itemStyle">
	<td colsapn=2 colspan=2><input type=text name="vchrazonsocial" class="body" size=60 maxlength="60"></td>
</tr>
<tr class="titStyle">
	<td>RFC</td>
</tr>
<tr class="itemStyle">
	<td><input type=text name="vchrfc" class="body" size=15 maxlength="15"></td>
</tr>
<tr class="titStyle"> 
	<td>Colonia</td>
</tr>
<tr class="itemStyle">
	<td><input type=text name="vchcolonia" class="body" size=20 maxlength="20"></td>
</tr>
<tr class="titStyle"> 
	<td>Calle y numero</td>
</tr>
<tr class="itemStyle">
	<td><input type=text name="vchcalle" class="body" size=60 maxlength="60"></td>
</tr>
<tr class="titStyle"> 
	<td>C&oacute;digo Postal </td>
</tr>
	<td><input type=text name="chcodigop" class="body" size=10 maxlength="10"></td>
</tr>
</table>
<br>
<table align="center" border="1" bordercolor="#364859" rules="cols" cellspacing="0" width="550" style="border-color:Black;border-width:1px;border-style:solid;font-family: arial; font-size: 11px;border-collapse:collapse;" class="tablatext">
<tr class="titStyle"> 
	    	<td width="33%">Pais</td>
			<td width="33%">Estado</td>
			<td width="34%">Municipio</td>
		</tr>
		<tr class="itemStyle">
			<td><select id="master" name="siidpais" class="body" onchange="FillSlave();FillSlave2();">
				<option value="" id="master1"></option>
    			<script type="text/javascript"> PrintOptions( paiss, "siidpais", "vchnompais"); </script>
      			</select>
			</td>
			<td><select id="slave" name="iidestado" class="body" onchange="FillSlave2();">
  				</select>
			</td>
			<td><select id="sslave" name="iidmunicipio" class="body"  onchange="actualiza();">
  				</select>
			</td>
		</tr>
</table>
<br>
<table align="center" border="1" bordercolor="#364859" rules="cols" cellspacing="0" width="550" style="border-color:Black;border-width:1px;border-style:solid;font-family: arial; font-size: 11px;border-collapse:collapse;" class="tablatext">
<tr class="titStyle"> 
	<td align=center><input type="submit" value="Aceptar" class=body onclick="return agregar_onclick(this.form)"></td>
</tr>
</table>
<input type=hidden name="vchpais">
<input type=hidden name="vchestado">
<input type=hidden name="vchmunicipio">

</form>
</body>
</html>
