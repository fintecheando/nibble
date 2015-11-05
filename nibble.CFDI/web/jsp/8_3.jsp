<html>
<jsp:useBean id="bean83" class="pva.beans8.Bean83" scope="session"/>

<head>
<title>PVA 8.3 Ajuste físico de inventario</title>
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
for (int i=0; i<bean83.getCategoriasSize();i++){
%>
categorias[c++] = new Array(<%= bean83.getCategoriasId(i) %>,"<%= bean83.getCategoriasDes(i) %>");
<%
}
%> 
c=0;
var grupos  = new Array();
grupos[c++] = new Array("iidgrupo",   "vchdescripcion", "iidcategoria");
grupos[c++] = new Array("int",   "String", "int");
<%
for (int i=0; i<bean83.getGruposSize();i++){
%>
grupos[c++] = new Array(<%= bean83.getGruposId(i) %>,"<%= bean83.getGruposDes(i) %>",<%= bean83.getGruposIdCategoria(i) %>);
<%
}
%> 
c=0;
var lineas  = new Array();
lineas[c++] = new Array("iidlinea",   "vchdescripcion", "iidgrupo");
lineas[c++] = new Array("int",   "String", "int");
<%
for (int i=0; i<bean83.getLineasSize();i++){
%>
lineas[c++] = new Array(<%= bean83.getLineasId(i) %>,"<%= bean83.getLineasDes(i) %>",<%= bean83.getLineasIdGrupo(i) %>);
<%
}
%> 
c=0;
var marcas  = new Array();
marcas[c++] = new Array("iidmarca",   "vchdescripcion", "iidlinea");
marcas[c++] = new Array("int",   "String", "int");
<%
for (int i=0; i<bean83.getMarcasSize();i++){
%>
marcas[c++] = new Array(<%= bean83.getMarcasId(i) %>,"<%= bean83.getMarcasDes(i) %>",<%= bean83.getMarcasIdLinea(i) %>);
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
	
	if (numErrors) {
		errors = 'Se produjeron ' + numErrors + ' error(es)\n'+ errors;
		alert(errors);
		return false;
	}
	return true;
}
function buscar_onclick(form){
	if (!checar(form))
		return false;
	form.accion.value=2;
	return true;
}
</script>
</head>
<%@ include file="../main.jsp" %>
<body bgcolor="#FFFFFF" text="#000000">
<form name=frm action="AjusteFisico" method="post">
<input type=hidden name="accion">
<table class="tablatext" width=750 align="center" cellpadding="0" cellspacing="0">
<tr>
	<td><img src="images/tpva8_3.GIF"></td>

        <th>
	<td><a href="../pvatx/hlp/pva8.0-8.3.htm" target="Ayuda Ajuste Físico al Inv.">Ayuda</a></td>
        </th>

</tr>
</table>
<br>    <!--<br>
        </table>
        <br>-->
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
		<tr>
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
		<td align=center><input type="submit" value="Buscar" class=body onclick="return buscar_onclick(this.form)">
		<input type="reset" name="bReset" value="Restablecer Criterios" class=body onclick="reestablecer(this.form)">
	</td>
</tr>
</table>
</form>

</body>
</html>
