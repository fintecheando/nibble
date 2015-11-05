<html>
<jsp:useBean id="bean53" class="org.nibble.service.vo.proveedores.Bean53" scope="session"/>

<head>
<title>NC 6.2 Modificación de Partes</title>
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
	form.accion.value=4;
	return true;
}
</script>
</head>
<%@ include file="../main.jsp" %>
<body bgcolor="#FFFFFF" text="#000000">
<form name=frm action="Partes" method="get">
<input type=hidden name="accion">
<table class="tablatext" width=750 align="center" cellpadding="0" cellspacing="0">
<tr>
	<td><img src="images/tnc6_2.GIF"></td>

        <th>
	<td><a href="../nctx/hlp/nc6.0-6.2.htm" target="Ayuda Modificacion de Partes">Ayuda</a></td>
        </th>

</tr>
</table>
</table>
<br>
<table align="center" border="1" bordercolor="#364859" rules="cols" cellspacing="0" width="750" style="border-color:Black;border-width:1px;border-style:solid;font-family: arial; font-size: 11px;border-collapse:collapse;" class="tablatext">
	<tr>
		<td colspan=4>Clasificacion</td>
	</tr>
	<tr class="titStyle">
    	<td width="25%">Categoria</td>
		<td width="25%">Grupo</td>
		<td width="25%">Linea</td>
		<td width="25%">Marca</td>
	</tr>
	<tr class="itemStyle">
		<td><select id="master" name="categoria" class="body" onchange="FillSlave();FillSlave2();FillSlave3();">
	   		<option value="1">PROPIO</option>
    		</select>
		</td>
		<td><select id="slave" name="grupo" class="body" onchange="FillSlave2();FillSlave3();">
	   		<option value=""></option>
    		<script type="text/javascript"> 
			var statecities = Where( grupos, "iidcategoria", "=", 1 );
			PrintOptions( statecities, "iidgrupo", "vchdescripcion"); 
			</script>
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
<br>
<table align="center" border="1" bordercolor="#364859" rules="cols" cellspacing="0" width="750" style="border-color:Black;border-width:1px;border-style:solid;font-family: arial; font-size: 11px;border-collapse:collapse;" class="tablatext">
	<tr>
		<td width="50%" colspan=4>Numero de parte</td>
	</tr>
	<tr class="titStyle">
	  	<td >Prefijo</td>
	  	<td >Numero</td>
	  	<td >Sufijo</td>
	  	<td >Medida</td>
	</tr>
	<tr class="itemStyle">
		<td><input type=text name="prefijo" class="body" size=4></td>
		<td><input type=text name="numero" class="body" size=4></td>
		<td><input type=text name="sufijo" class="body" size=4></td>
		<td><input type=text name="medida" class="body" size=4></td>
	</tr>
</table>
<table align="center" border="1" bordercolor="#364859" rules="cols" cellspacing="0" width="750" style="border-color:Black;border-width:1px;border-style:solid;font-family: arial; font-size: 11px;border-collapse:collapse;" class="tablatext">
	<tr class="titStyle">
	<td align=center><input type="submit" value="Buscar" class=body onclick="return agregar_onclick(this.form)">
		<input type="reset" name="bReset" value="Restablecer Criterios" class=body onclick="reestablecer(this.form)">
	</td>
</tr>
</table>
</form>

</body>
</html>
