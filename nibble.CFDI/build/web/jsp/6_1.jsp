<html>
<jsp:useBean id="bean53" class="org.nibble.service.vo.proveedores.Bean53" scope="session"/>
<jsp:useBean id="beanMsg" class="get.BeanMsg" scope="session"/>

<head>
<title>Captura de Productos</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link href="lib/estilo.css" rel=STYLESHEET type=text/css>
<script type="text/javascript" src="lib/util.js"></script>
<script type="text/javascript" src="lib/engine/jsSQL.js"></script>
<script type="text/javascript" src="lib/tools/PrintOptions.js"></script>
<script type="text/javascript" src="lib/tools/FillOptions.js"></script>

<script type="text/javascript">
var c=0;
var categorias  = new Array();
categorias[c++] = new Array("iidcategoria",   "vchdescripcion");
categorias[c++] = new Array("int",   "String");
<%for (int i=0; i<bean53.getCategoriasSize();i++){%>
categorias[c++] = new Array(<%= bean53.getCategoriasId(i) %>,"<%= bean53.getCategoriasDes(i) %>");
<%}%> 
c=0;
var grupos  = new Array();
grupos[c++] = new Array("iidgrupo",   "vchdescripcion", "iidcategoria");
grupos[c++] = new Array("int",   "String", "int");
<%for (int i=0; i<bean53.getGruposSize();i++){%>
grupos[c++] = new Array(<%= bean53.getGruposId(i) %>,"<%= bean53.getGruposDes(i) %>",<%= bean53.getGruposIdCategoria(i) %>);
<%}%> 
c=0;
var lineas  = new Array();
lineas[c++] = new Array("iidlinea",   "vchdescripcion", "iidgrupo");
lineas[c++] = new Array("int",   "String", "int");
<%for (int i=0; i<bean53.getLineasSize();i++){%>
lineas[c++] = new Array(<%= bean53.getLineasId(i) %>,"<%= bean53.getLineasDes(i) %>",<%= bean53.getLineasIdGrupo(i) %>);
<%}%> 
c=0;
var marcas  = new Array();
marcas[c++] = new Array("iidmarca",   "vchdescripcion", "iidlinea");
marcas[c++] = new Array("int",   "String", "int");
<%for (int i=0; i<bean53.getMarcasSize();i++){%>
marcas[c++] = new Array(<%= bean53.getMarcasId(i) %>,"<%= bean53.getMarcasDes(i) %>",<%= bean53.getMarcasIdLinea(i) %>);
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
function agregar_onclick(form){
    var errors = '';
	var numErrors = 0;
	
	if (form.iidcategoria.value=='' || form.iidgrupo.value=='' || form.iidlinea.value=='' || form.iidmarca.value=='') {
		errors += '- Debe seleccionar la clasificación completa.\n';
		numErrors++;
	}
	if (form.fllargo.value=='' || form.flalto.value=='' || form.flancho.value=='' || form.flpeso.value=='') {
		errors += '- Debe introducir las Dimensiones de empaque.\n';
		numErrors++;
	}        
	if (form.decpreciob100.value=='') {
		errors += '- Debe introducir el precio base 100.\n';
		numErrors++;
	}        
	if (form.imultiploempaque.value=='') {
		errors += '- Debe introducir el múltiplo d empaque.\n';
		numErrors++;
	}        
	if (form.iminembarque.value=='') {
		errors += '- Debe introducir el mínimo de embarque.\n';
		numErrors++;
	}        
	if (!(form.bitventaunitaria[0].checked) && !(form.bitventaunitaria[1].checked)){
		errors += '- Debe seleccionar la opción de venta unitaria.\n';
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
<form name=frm action="Partes" method="post">
<input type=hidden name="accion">
<table class="tablatext" width=750 align="center" cellpadding="0" cellspacing="0">
<tr>
	<td><img src="images/tnc6_1.GIF"></td>

        <th>
            <td></td>
        </th>

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
	<tr class="itemStyle">
		<td><select id="master" name="iidcategoria" class="body" onchange="FillSlave();FillSlave2();FillSlave3();">
	    	<option value="1">PROPIO</option>
      		</select>
		</td>
		<td><select id="slave" name="iidgrupo" class="body" onchange="FillSlave2();FillSlave3();">
	    	<option value=""></option>
    		<script type="text/javascript"> 
			var statecities = Where( grupos, "iidcategoria", "=", 1 );
			PrintOptions( statecities, "iidgrupo", "vchdescripcion"); 
			</script>
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
    </td>
</tr>
</table>
<br>
<table align="center" border="1" bordercolor="#364859" rules="cols" cellspacing="0" width="750" style="border-color:Black;border-width:1px;border-style:solid;font-family: arial; font-size: 11px;border-collapse:collapse;" class="tablatext">
	<tr>
	<td width="50%" colspan=4>Número de Producto</td>
	<td width="50%" colspan=4>Dimensiones de empaque</td>
	</tr>
	<tr class="titStyle">
	  <td colspan=4>SKU</td>
	  <td >Largo</td>
	  <td >Alto</td>
	  <td >Ancho</td>
	  <td >Peso</td>
	</tr>
	<tr class="itemStyle">	
	<td colspan=4><input type=text name="vchnumparte" class="body" size=16></td>
	<td><input type=text name="fllargo" class="body" size=4 onkeypress="return numbersonly()" ></td>
	<td><input type=text name="flalto" class="body" size=4 onkeypress="return numbersonly()"></td>
	<td><input type=text name="flancho" class="body" size=4 onkeypress="return numbersonly()"></td>
	<td><input type=text name="flpeso" class="body" size=4 onkeypress="return numbersonly()"></td>
	</tr>
</table>
<br>
<table align="center" border="1" bordercolor="#364859" rules="cols" cellspacing="0" width="750" style="border-color:Black;border-width:1px;border-style:solid;font-family: arial; font-size: 11px;border-collapse:collapse;" class="tablatext">
	<tr class="titStyle">
	  <td >Unidad</td>
  	  <td >Precio Base 100</td>
	  <td >Múltiplo Empaque</td>
	  <td >Mínimo embarque</td>
	  <td >Venta unitaria</td>
	</tr>
	<tr class="itemStyle">
		<td> <select id="iidunidad" name="iidunidad" class="body">
			<%for (int i=0; i<bean53.getUnidadsSize();i++){%>
			<option value=<%= bean53.getUnidadsId(i) %>><%= bean53.getUnidadsDes(i) %></option>
			<%}%> 
  			</select> 
		</td>
		<td> <input type=text name="decpreciob100" class="body" size=10 onkeypress="return numbersonly()"> </td>
		<td> <input type=text name="imultiploempaque" class="body" size=5 onkeypress="return numbersonly()"> </td>
		<td> <input type=text name="iminembarque" class="body" size=5 onkeypress="return numbersonly()"> </td>
		<td> <input type=radio name="bitventaunitaria" value=Si>Si <input type=radio name="bitventaunitaria" value=No>No  </td>
    </tr>
</table>
<br>
<table align="center" border="1" bordercolor="#364859" rules="cols" cellspacing="0" width="750" style="border-color:Black;border-width:1px;border-style:solid;font-family: arial; font-size: 11px;border-collapse:collapse;" class="tablatext">
<tr class="titStyle">
	<td>Descripción</td>
</tr>
<tr class="itemStyle">
	<td><textarea name="vchdescripciones" rows="4" cols="100" class="body"></textarea></td>
</tr>
<tr class="titStyle">
	<td>Observaciones</td>
</tr>
<tr class="itemStyle">
	<td><textarea name="vchobservaciones" rows="4" cols="100" class="body"></textarea></td>
</tr>
<tr class="titStyle">	
	<td>Ficha Técnica</td>
</tr>
<tr class="itemStyle">
	<td><textarea name="fichatecnica" rows="4" cols="100" class="body"></textarea></td>
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
