<html>
<jsp:useBean id="bean82" class="pva.beans8.Bean82" scope="session"/>

<head>
<title>PVA 8.2 Consulta a Movimientos de Inventario</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link href="lib/estilo.css" rel=STYLESHEET type=text/css>
<script type="text/javascript" src="lib/engine/jsSQL.js"></script>
<script type="text/javascript" src="lib/tools/PrintOptions.js"></script>
<script type="text/javascript" src="lib/tools/FillOptions.js"></script>

<script language="JavaScript" src="lib/calendar2.js"></script>



<script type="text/javascript">
var c=0;
var categorias  = new Array();
categorias[c++] = new Array("iidcategoria",   "vchdescripcion");
categorias[c++] = new Array("int",   "String");
<%for (int i=0; i<bean82.getCategoriasSize();i++){%>
categorias[c++] = new Array(<%= bean82.getCategoriasId(i) %>,"<%= bean82.getCategoriasDes(i) %>");
<%}%> 
c=0;
var grupos  = new Array();
grupos[c++] = new Array("iidgrupo",   "vchdescripcion", "iidcategoria");
grupos[c++] = new Array("int",   "String", "int");
<%for (int i=0; i<bean82.getGruposSize();i++){%>
grupos[c++] = new Array(<%= bean82.getGruposId(i) %>,"<%= bean82.getGruposDes(i) %>",<%= bean82.getGruposIdCategoria(i) %>);
<%}%> 
c=0;
var lineas  = new Array();
lineas[c++] = new Array("iidlinea",   "vchdescripcion", "iidgrupo");
lineas[c++] = new Array("int",   "String", "int");
<%for (int i=0; i<bean82.getLineasSize();i++){%>
lineas[c++] = new Array(<%= bean82.getLineasId(i) %>,"<%= bean82.getLineasDes(i) %>",<%= bean82.getLineasIdGrupo(i) %>);
<%}%> 
c=0;
var marcas  = new Array();
marcas[c++] = new Array("iidmarca",   "vchdescripcion", "iidlinea");
marcas[c++] = new Array("int",   "String", "int");
<%for (int i=0; i<bean82.getMarcasSize();i++){%>
marcas[c++] = new Array(<%= bean82.getMarcasId(i) %>,"<%= bean82.getMarcasDes(i) %>",<%= bean82.getMarcasIdLinea(i) %>);
<%}%> 


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
<form name=frm action="ConsultaMovimientosInventario" method="post">
<input type=hidden name="accion">
<table class="tablatext" width=750 align="center" cellpadding="0" cellspacing="0">
<tr>
	<td><img src="images/tpva8_2.GIF"></td>
        
        <th>
	<td><a href="../pvatx/hlp/pva8.0-8.2.htm" target="Ayuda Consulta a Movimientos de Inv.">Ayuda</a></td>
        </th>

</tr>
</table>
<br>
<table align="center" border="1" bordercolor="#364859" rules="cols" cellspacing="0" width="750" style="border-color:Black;border-width:1px;border-style:solid;font-family: arial; font-size: 11px;border-collapse:collapse;" class="tablatext">
<tr class="titStyle"> 
	  <td width="25%">Desde</td>
	  <td width="25%">Hasta</td>
	  <td width="50%">Tipo de Movimiento</td>
</tr>
<tr class="itemStyle">
	  <td><input type=text name="fechaini" class="body" size=10>
	  <a href="javascript:cal5.popup();"onMouseOut="window.status='';return true;" onMouseOver="window.status='Seleccione Fecha';return true;"><img border=0 src="images/calendar.gif" class="."></a>
	  </td>
	  <td>
	  <input type=text name="fechafin" class="body" size=10>
	  	  <a href="javascript:cal6.popup();" onMouseOut="window.status='';return true;" onMouseOver="window.status='Seleccione Fecha';return true;"><img border=0 src="images/calendar.gif" class="."></a>

<SCRIPT LANGUAGE="JavaScript">

			var cal5 = new calendar2(document.forms['frm'].elements['fechaini']);
			cal5.year_scroll = true;
			cal5.time_comp = false;
			
			var cal6 = new calendar2(document.forms['frm'].elements['fechafin']);
			cal6.year_scroll = true;
			cal6.time_comp = false;					
		</SCRIPT>
	  	  
	  </td>
	  <td><select name="titipomov" class="body">
				<option value=""></option>
			<%for (int i=0; i<bean82.getTipoMovimientoSize();i++){%>
				<option value=<%= bean82.getTipoMovimientoId(i) %>><%= bean82.getTipoMovimientoDes(i) %></option>
			<%}%>   		    
				</select>
			</td>
</tr>
</table>
<br>
<table align="center" border="1" bordercolor="#364859" rules="cols" cellspacing="0" width="750" style="border-color:Black;border-width:1px;border-style:solid;font-family: arial; font-size: 11px;border-collapse:collapse;" class="tablatext">
<tr> 
	<td colspan=4>Número de parte</td>
</tr>
<tr class="titStyle"> 
	  <td >Prefijo</td>
	  <td >Número</td>
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
