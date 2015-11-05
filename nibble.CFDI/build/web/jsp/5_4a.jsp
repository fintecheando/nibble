<html>
<jsp:useBean id="bean53" class="org.nibble.service.vo.proveedores.Bean53" scope="session"/>

<head>
<title>NC 5.4 Estructuras de compra</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link href="lib/estilo.css" rel=STYLESHEET type=text/css>
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
 var m=f.iidcategoria;
 var s=f.iidgrupo;
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
 var m=f.iidgrupo;
 var s=f.iidlinea;
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
 var m=f.iidlinea;
 var s=f.iidmarca;
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
	DelOptions(form.iidgrupo);
	DelOptions(form.iidlinea);
	DelOptions(form.iidmarca);
}
function checar(form){
    var errors = '';
	var numErrors = 0;
	
	if (form.iidcategoria.value=='' || form.iidgrupo.value=='' || form.iidlinea.value=='' || form.iidmarca.value=='') {
		errors += '- Debe seleccionar la clasificación completa.\n';
		numErrors++;
	}
	if (numErrors) {
		errors = 'Se produjeron ' + numErrors + ' error(es)\n'+ errors;
		alert(errors);
		return false;
	}
	return true;
}
function proveedores_onclick(form){
	if (!checar(form))
		return false;
	form.accion.value=2;
	return true;
}

</script>
</head>
<%@ include file="../main.html" %>
<body bgcolor="#FFFFFF" text="#000000">
<form name=frm action="EstructurasCompra" method="post">
<input type=hidden name="accion">
<table class="tablatext" width=750 align="center" cellpadding="0" cellspacing="0">
<tr>
	<td><img src="images/tnc5_4.GIF"></td>

        <th>
	<td><a href="../nctx/hlp/nc5.0-5.4.htm" target="Ayuda Estructuras de Compra">Ayuda</a></td>
        </th>

</tr>
</table>
<br>
<table align="center" border="1" bordercolor="#364859" rules="cols" cellspacing="0" width="750" style="border-color:Black;border-width:1px;border-style:solid;font-family: arial; font-size: 11px;border-collapse:collapse;" class="tablatext">
<tr>
	<td>Clasificacion</td>
</tr>
<tr class="titStyle">
	<td width="25%">Categoria</td>
	<td width="25%">Grupo</td>
	<td width="25%">Linea</td>
	<td width="25%">Marca</td>
</tr>
<tr class="itemStyle">
	<td><select id="master" name="iidcategoria" class="body" onchange="FillSlave();FillSlave2();FillSlave3();">
	  		<option value="" id="master1"></option>
    		<script type="text/javascript"> PrintOptions( categorias, "iidcategoria", "vchdescripcion"); </script>
    	</select>
	</td>
	<td><select id="slave" name="iidgrupo" class="body" onchange="FillSlave2();FillSlave3();">
  		</select>
	</td>
	<td><select id="sslave" name="iidlinea" class="body" onchange="FillSlave3();">
  		</select>
	</td>
	<td><select id="ssslave" name="iidmarca" class="body">
  		</select>
	</td>
</tr>
</table>
<br>
<table align="center" border="1" bordercolor="#364859" rules="cols" cellspacing="0" width="750" style="border-color:Black;border-width:1px;border-style:solid;font-family: arial; font-size: 11px;border-collapse:collapse;" class="tablatext">
	<tr class="titStyle">
		<td align=center><input type="submit" value="Ver proveedores" class=body onclick="return proveedores_onclick(this.form)">
		<input type="reset" name="bReset" value="Restablecer Criterios" class=body onclick="reestablecer(this.form)">
	</td>
</tr>
</table>
</form>

</body>
</html>
