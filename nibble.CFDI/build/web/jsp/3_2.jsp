<html>
<jsp:useBean id="bean32" class="pva.beans3.Bean32" scope="session"/>
<head>
<title>PVA 3.2 Promociones</title>
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
for (int i=0; i<bean32.getCategoriasSize();i++){
%>
categorias[c++] = new Array(<%= bean32.getCategoriasId(i) %>,"<%= bean32.getCategoriasDes(i) %>");
<%
}
%> 
c=0;
var grupos  = new Array();
grupos[c++] = new Array("iidgrupo","vchdescripcion", "iidcategoria");
grupos[c++] = new Array("int",   "String", "int");
<%
for (int i=0; i<bean32.getGruposSize();i++){
%>
grupos[c++] = new Array(<%= bean32.getGruposId(i) %>,"<%= bean32.getGruposDes(i) %>",<%= bean32.getGruposIdCategoria(i) %>);
<%
}
%> 
c=0;
var lineas  = new Array();
lineas[c++] = new Array("iidlinea","vchdescripcion", "iidgrupo");
lineas[c++] = new Array("int",   "String", "int");
<%
for (int i=0; i<bean32.getLineasSize();i++){
%>
lineas[c++] = new Array(<%= bean32.getLineasId(i) %>,"<%= bean32.getLineasDes(i) %>",<%= bean32.getLineasIdGrupo(i) %>);
<%
}
%> 
c=0;
var marcas  = new Array();
marcas[c++] = new Array("iidmarca","vchdescripcion", "iidlinea");
marcas[c++] = new Array("int",   "String", "int");
<%
for (int i=0; i<bean32.getMarcasSize();i++){
%>
marcas[c++] = new Array(<%= bean32.getMarcasId(i) %>,"<%= bean32.getMarcasDes(i) %>",<%= bean32.getMarcasIdLinea(i) %>);
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

function promocion_onclick(form){
    var errors = '';
	var numErrors = 0;
	
	if (form.categoria.value=='' || form.grupo.value=='' || form.linea.value=='' || form.marca.value=='') {
		errors += '- Debe seleccionar la clasificación completa.\n';
		numErrors++;
	}
	if (numErrors) {
		errors = 'Se produjo(eron) ' + numErrors + ' error(es)\n'+ errors;
		alert(errors);
		return false;
	}
	form.accion.value=1;
	return true;
}
</script>
<%@ include file="../main.jsp" %>
</head>
<body bgcolor="#FFFFFF" text="#000000">
<form name="frm" action="Promociones" method="Post">
	<input type=hidden name="accion">
	<table class="tablatext" width=750 align="center" cellpadding="0" cellspacing="0">
	
                <td><img src="images/tpva3_2.GIF"></td>
                
                <th>
                <td><a href="../pvatx/hlp/pva3.0-3.2.htm" target="Ayuda Promociones">Ayuda</a></td>
                </th>
	
        </table>
	<br>
	<table align=center border=1 borderColor=#364859 cellSpacing=0 class=tablatext rules=cols style="BORDER-BOTTOM: black 1px solid; BORDER-COLLAPSE: collapse; BORDER-LEFT: black 1px solid; BORDER-RIGHT: black 1px solid; BORDER-TOP: black 1px solid; FONT-FAMILY: arial; FONT-SIZE: 11px" width=750>
		<tr>
			<td colspan="4">Clasificacion</td>
		</tr>
		<tr>
	 	<tr class="titStyle">
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
		<tr class="titStyle">
			<td colspan="4" align="center"><input type="submit" class="body" value="Promociones" onclick="return promocion_onclick(this.form);"></td>
		</tr>
	</table>
</form>	
</body>
</html>