<html>

<jsp:useBean id="bean53" class="org.nibble.service.vo.proveedores.Bean53" scope="session"/>

<head>
<title>NC 6.3 Personalizar Catálogo</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link href="lib/estilo.css" rel=STYLESHEET type=text/css>
<link href="lib/selector.css" rel=STYLESHEET type=text/css>
<script type="text/javascript" src="lib/engine/jsSQL.js"></script>
<script type="text/javascript" src="lib/tools/PrintOptions.js"></script>
<script type="text/javascript" src="lib/tools/FillOptions.js"></script>

<script type="text/javascript">
var c=0;
var categorias  = new Array();
categorias[c++] = new Array("iidcategoria",   "vchdescripcion");
categorias[c++] = new Array("int",   "String");
<%
for (int i=0; i<bean53.getCategoriasSize();i++){
%>
categorias[c++] = new Array(<%= bean53.getCategoriasId(i) %>,"<%= bean53.getCategoriasDes(i) %>");
<%
}
%> 
c=0;
var grupos  = new Array();
grupos[c++] = new Array("iidgrupo",   "vchdescripcion", "iidcategoria");
grupos[c++] = new Array("int",   "String", "int");
<%
for (int i=0; i<bean53.getGruposSize();i++){
%>
grupos[c++] = new Array(<%= bean53.getGruposId(i) %>,"<%= bean53.getGruposDes(i) %>",<%= bean53.getGruposIdCategoria(i) %>);
<%
}
%> 
c=0;
var lineas  = new Array();
lineas[c++] = new Array("iidlinea",   "vchdescripcion", "iidgrupo");
lineas[c++] = new Array("int",   "String", "int");
<%
for (int i=0; i<bean53.getLineasSize();i++){
%>
lineas[c++] = new Array(<%= bean53.getLineasId(i) %>,"<%= bean53.getLineasDes(i) %>",<%= bean53.getLineasIdGrupo(i) %>);
<%
}
%> 
c=0;
var marcas  = new Array();
marcas[c++] = new Array("iidmarca",   "vchdescripcion", "iidlinea");
marcas[c++] = new Array("int",   "String", "int");
<%
for (int i=0; i<bean53.getMarcasSize();i++){
%>
marcas[c++] = new Array(<%= bean53.getMarcasId(i) %>,"<%= bean53.getMarcasDes(i) %>",<%= bean53.getMarcasIdLinea(i) %>);
<%
}
%> 


function FillSlave()
{
 var f=document.forms["frm"];
 var m=f.categoria;
 var s=f.grupo;
 var code
 if (m.length>0)
 	 code=m.options[m.selectedIndex].value;
 else code="";
 
 if (code=="") DelOptions(s);
 else
 {
  var statecities = Where( grupos, "iidcategoria", "=", code );
  FillOptions( statecities, s, 'iidgrupo', 'vchdescripcion', false );
  statecities = null;
 }
}

function FillSlave2()
{
 var f=document.forms["frm"];
 var m=f.grupo;
 var s=f.linea;
 var code;
 if (m.length>0)
  	code=m.options[m.selectedIndex].value;
 else code="";
 if (code=="") DelOptions(s);
 else
 {
  var muns = Where( lineas, "iidgrupo", "=", code );
  FillOptions( muns, s, 'iidlinea', 'vchdescripcion', false );
  muns = null;
 }
}

function FillSlave3()
{
 var f=document.forms["frm"];
 var m=f.linea;
 var s=f.marca;
 var code;
 if (m.length>0)
 	code=m.options[m.selectedIndex].value;
 else
 	code="";
 if (code=="") DelOptions(s);
 else
 {
  var asents = Where( marcas, "iidlinea", "=", code );
  FillOptions( asents, s, 'iidmarca', 'vchdescripcion', false );
  asents = null;
 }
}
function reestablecer(form){
	DelOptions(form.grupo);
	DelOptions(form.linea);
	DelOptions(form.marca);
}
function agregar_onclick(form){
    var errors = '';
	var numErrors = 0;
	
	if (form.categoria.value=='' || form.grupo.value=='' || form.linea.value=='' || form.marca.value=='') {
		errors += '- Debe seleccionar la clasificación completa.\n';
		numErrors++;
	}
	if (numErrors) {
		errors = 'Se produjeron ' + numErrors + ' error(es)\n'+ errors;
		alert(errors);
		return false;
	}
	form.accion.value=8;
	return true;
}


</script>
</head>
<%@ include file="../main.html" %>
<body bgcolor="#FFFFFF" text="#000000"> 
<form name=frm action="Partes">
<input type=hidden name="accion">
<table class="tablatext" width=750 align="center" cellpadding="0" cellspacing="0">
<tr>
	<td><img src="images/tnc6_3.GIF"></td>

        <th>
	<td><a href="../nctx/hlp/nc6.0-6.3.htm" target="Ayuda Personalizar Catálogo">Ayuda</a></td>
        </th>

</tr>
</table>
<br><br>
<table class="tablatext" width=750 align="center" cellpadding="0" cellspacing="0">
<tr>
	<td>Clasificacion</td>
</tr>
<tr>
	<td width="11%"><img src="images/spacer.gif" width="100%" height="1"></td>
</tr>
</table>
<table class="tablatext" width=750 align="center" cellpadding="0" cellspacing="0">
<tr>
	<td colspan="6">
	 <table class="tablatext" border="0" width="100%" cellpadding="0" cellspacing="0">
	 	<tr bgcolor="#E8EEF9">
	    	<td width="25%">Categoria</td>
			<td width="25%">Grupo</td>
			<td width="25%">Linea</td>
			<td width="25%">Marca</td>
		</tr>
		<tr>
			<td><select id="master" name="categoria" class="body" onchange="FillSlave();FillSlave2();FillSlave3();">
	    		<option value="" id="master1"></option>
    			<script type="text/javascript"> PrintOptions( categorias, "iidcategoria", "vchdescripcion"); </script>
      			</select>
			</td>
			<td><select id="slave" name="grupo" class="body" onchange="FillSlave2();FillSlave3();">
  				</select>
			</td>
			<td><select id="sslave" name="linea" class="body" onchange="FillSlave3();">
  				</select>
			</td>
			<td><select id="ssslave" name="marca" class="body">
  				</select>
			</td>
		</tr>
	 </table>
	</td>
</tr>
<tr>
 <td colspan ="6" width="11%">
	<img src="images/spacer.gif" width="100%" height="1">
 </td>
</tr>
<tr>
<td colspan="6">&nbsp;</td>
</tr>
</table>
<table class="tablatext" width=750 align="center" cellpadding="0" cellspacing="0">
<tr>
	<td width="50%">Numero de parte</td>
	<td width="50%"></td>
</tr>
<tr>
	<td colspan=2 width="11%"><img src="images/spacer.gif" width="100%" height="1"></td>
</tr>
</table>
<table class="tablatext" width=750 align="center" cellpadding="0" cellspacing="0">
<tr bgcolor="#E8EEF9">
	  <td >Prefijo</td>
	  <td >Numero</td>
	  <td >Sufijo</td>
	  <td >Medida</td>
</tr>
<tr>
	<td><input type=text name="prefijo" class="body" size=4></td>
	<td><input type=text name="numero" class="body" size=4></td>
	<td><input type=text name="sufijo" class="body" size=4></td>
	<td><input type=text name="medida" class="body" size=4></td>
</tr>
<tr>
 <td colspan ="8">
	<img src="images/spacer.gif" width="100%" height="1">
 </td>
</tr>
<tr>
<td colspan="6">&nbsp;</td>
</tr>
</table>
<table class="tablatext" width=750 align="center" cellpadding="0" cellspacing="0">
<tr>
	<td align=center><input type="submit" value="Buscar" onclick="return agregar_onclick(this.form)">
		<input type="reset" name="bReset" value="Restablecer Criterios" onclick="reestablecer(this.form)">
	</td>
</tr>
</table>
</form>

</body>
</html>
