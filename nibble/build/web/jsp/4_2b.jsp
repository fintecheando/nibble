<html>
<jsp:useBean id="bean42b" class="pva.beans4.Bean42b" scope="session"/>
<jsp:useBean id="bean42" class="pva.beans4.Bean42" scope="session"/>
<jsp:useBean id="beanresult42b" class="pva.beans4.BeanResult42b" scope="session"/>
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
var categorias  = new Array();
categorias[c++] = new Array("iidcatcliente",   "vchdescripcion");
categorias[c++] = new Array("int",   "String");
<%
for (int i=0; i<bean42b.getCategoriasClienteSize();i++){
%>
categorias[c++] = new Array(<%= bean42b.getCategoriasClienteId(i) %>,"<%= bean42b.getCategoriasClienteDes(i) %>");
<%
}
%> 
c=0;
var niveles  = new Array();
niveles[c++] = new Array("iidnivel",   "vchnivel", "iidcatcliente");
niveles[c++] = new Array("int",   "String", "int");
<%
for (int i=0; i<bean42b.getNivelesSize();i++){
%>
niveles[c++] = new Array(<%= bean42b.getNivelesId(i) %>,"<%= bean42b.getNivelesDes(i) %>",<%= bean42b.getNivelesIdCat(i) %>);
<%
}
%> 
c=0;
var semana  = new Array();
semana[c++] = new Array("iidia",  "des");
semana[c++] = new Array("int",   "String");
semana[c++] = new Array(1,'Lunes');
semana[c++] = new Array(2,'Martes');
semana[c++] = new Array(3,'Miercoles');
semana[c++] = new Array(4,'Jueves');
semana[c++] = new Array(5,'Viernes');
semana[c++] = new Array(6,'Sabado');
semana[c++] = new Array(7,'Domingo');

function FillSlave()
{
 var f=document.forms["frm"];
 var m=f.iidcatcliente;
 var s=f.iidnivel;
 var code
 if (m.length>0)
 	 code=m.options[m.selectedIndex].value;
 else code="";
 if (code=="") DelOptions(s);
 else
 {
  var temp = Where( niveles, "iidcatcliente", "=", code );
  FillOptions( temp, s, 'iidnivel', 'vchnivel', false );
  temp = null;
 }
}
function semana_onclick(form){
	form.siplazodiaspago.disabled=true;
	form.siplazodiaspago.value=0;
  	FillOptions( semana, form.sidiadepago, 'iidia', 'des', false );
}
function dias_onclick(form){
	form.siplazodiaspago.disabled=false;
	DelOptions(form.sidiadepago);
}
function modificar_onclick(form){
    var errors = '';
	var numErrors = 0;
	if (form.iidcatcliente.value=='') {
		errors += '- Debe seleccionar la categoría del cliente.\n';
		numErrors++;
	}
	if (form.iidnivel.value=='') {
		errors += '- Debe seleccionar el nivel de precio.\n';
		numErrors++;
	}
	if (form.siidviaembarque.value=='') {
		errors += '- Debe seleccionar la vía de embarque.\n';
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
<form name=frm action="ClientesCondComerciales" method="post"  onsubmit="return checa()">
<input type=hidden name="accion">
<input type=hidden name="iidnoclienteTemp" value=<%= beanresult42b.getIidnocliente() %>>
<table class="tablatext" width=750 align="center" cellpadding="0" cellspacing="0">
<tr>
	<td><img src="images/tpva4_2.GIF"></td>

        <th>
	<td><a href="../pvatx/hlp/pva4.0-4.2.htm#condiciones" target="Ayuda camb. cli. y cond. cred.">Ayuda</a></td>
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
			<td><input type=text name="iidnocliente" class="body" size=2 value=<%= beanresult42b.getIidnocliente() %>><input type=button value="Buscar" class="body" onclick="this.form.accion.value=4;this.form.submit()"></td>
			<td><select name="razonsocial" class="body" onchange="this.form.accion.value=4;this.form.iidnocliente.value=this.form.razonsocial.options[this.form.razonsocial.selectedIndex].value;form.submit()">
			<%for (int i=0; i<bean42.getClientesSize();i++){%>
				<option value=<%= bean42.getId(i) %> <% if (beanresult42b.getIidnocliente()==bean42.getId(i)){%> SELECTED <% } %>><%= bean42.getRazonsocial(i) %></option>
			<%}%>   		    
				</select>
			</td>
			<td><select name="rfc" class="body" onchange="this.form.accion.value=4;this.form.iidnocliente.value=this.form.rfc.options[this.form.rfc.selectedIndex].value;form.submit()">
			<%for (int i=0; i<bean42.getClientesSize();i++){%>
				<option value=<%= bean42.getId(i) %> <% if (beanresult42b.getIidnocliente()==bean42.getId(i)){%> SELECTED <% } %>><%= bean42.getRfc(i) %></option>
			<%}%>   		    
				</select>
			</td>
		</tr>
</table>
<br>
<table class="tablatext" width=750 align="center" cellpadding="0" cellspacing="0">
<tr><td>
<input type=button value="Datos generales" class="body" onclick="this.form.action='Clientes';this.form.accion.value=4;form.submit()">
</td></tr>
</table>
<br>
<table align="center" border="1" bordercolor="#364859" rules="cols" cellspacing="0" width="750" style="border-color:Black;border-width:1px;border-style:solid;font-family: arial; font-size: 11px;border-collapse:collapse;" class="tablatext">
<tr class="titStyle"> 
	<td>Categoría de cliente</td>
	<td>Nivel de precio</td>
	<td>Vía de embarque</td>
</tr>
<tr class="itemStyle">
	<td><select name="iidcatcliente" class="body" onchange="FillSlave()" <% if  (beanresult42b.isMostrador()){%> DISABLED <% } %>>
			<option value=0></option>
			<%for (int i=0; i<bean42b.getCategoriasClienteSize();i++){%>
			<option value=<%= bean42b.getCategoriasClienteId(i) %> <% if  (beanresult42b.getIidcatcliente()== bean42b.getCategoriasClienteId(i)){%> SELECTED <% } %>><%= bean42b.getCategoriasClienteDes(i) %></option>
			<%}%>   	
      		</select>
	</td>
	<td><select name="iidnivel" class="body">
		    <option value=0></option>
			<%for (int i=0; i<beanresult42b.getNivelesSize();i++){%>
			<option value=<%= beanresult42b.getNivelesId(i) %> <% if  (beanresult42b.getIidnivel()== beanresult42b.getNivelesId(i)){%> SELECTED <% } %>><%= beanresult42b.getNivelesDes(i) %></option>
			<%}%>
      		</select>
	</td>
	<td><select name="siidviaembarque" class="body" onchange="FillSlave()" <% if  (beanresult42b.isMostrador()){%> DISABLED <% } %>>
			<%for (int i=0; i<bean42b.getViasSize();i++){%>
			<option value=<%= bean42b.getViasId(i) %> <% if  (beanresult42b.getSiidviaembarque()== bean42b.getViasId(i)){%> SELECTED <% } %>><%= bean42b.getViasDes(i) %></option>
			<%}%>   	
      		</select>
	</td>
</tr>
</table>
<br>
<table align="center" border="1" bordercolor="#364859" rules="cols" cellspacing="0" width="750" style="border-color:Black;border-width:1px;border-style:solid;font-family: arial; font-size: 11px;border-collapse:collapse;" class="tablatext">
<tr class="titStyle">
	<td width="50%">Límite de crédito</td>
	<td width="50%">Día de pago</td>
</tr>
<tr class="itemStyle">
	<td><input type=text name="declimitecredito" class="numero" size=25 maxlength="25" value="<%= beanresult42b.getDeclimitecredito() %>" <%if (!beanresult42b.getBsuceptiblecredito() || beanresult42b.isMostrador()){%> DISABLED <%}%>  onkeypress="return numbersonly()"></td>
	<% short dia = beanresult42b.getSidiadepago(); %>
	<td><input type=radio name=dias onclick="semana_onclick(this.form)" <% if (dia >0 ){ %> CHECKED <% } %> <% if  (beanresult42b.isMostrador()){%> DISABLED <% } %>>
		<select name="sidiadepago" class="body" <% if  (beanresult42b.isMostrador()){%> DISABLED <% } %>>
		<% if (dia >0 ){ %>
		<option value=1 <%if (dia==1){%> SELECTED <%}%>>Lunes</option>
		<option value=2 <%if (dia==2){%> SELECTED <%}%>>Martes</option>
		<option value=3 <%if (dia==3){%> SELECTED <%}%>>Miercoles</option>
		<option value=4 <%if (dia==4){%> SELECTED <%}%>>Jueves</option>
		<option value=5 <%if (dia==5){%> SELECTED <%}%>>Viernes</option>
		<option value=6 <%if (dia==6){%> SELECTED <%}%>>Sabado</option>
		<option value=7 <%if (dia==7){%> SELECTED <%}%>>Domingo</option>
		<% } %>
		</select>
	</td>	
</tr>
<tr class="titStyle"> 
	<td width="50%">Crédito utilizado</td>
	<td width="50%">Plazo de pago (días)</td>
</tr>
<tr class="itemStyle">
	<td><input type=text name="deccreditutilizado" class="numero" size=25 maxlength="25" value="<%= beanresult42b.getDeccreditutilizado() %>" DISABLED></td>
	<td><input type=radio name=dias onclick="dias_onclick(this.form);" <% if (beanresult42b.getSiplazodiaspago() >0 ){ %> CHECKED <% } %> <% if  (beanresult42b.isMostrador()){%> DISABLED <% } %>>
	<input type=text name="siplazodiaspago" class="numero" size=10 maxlength="10" value="<%= beanresult42b.getSiplazodiaspago() %>" <% if (beanresult42b.getSiplazodiaspago() ==0 ){ %> DISABLED <% } %> onkeypress="return numbersonly()"></td>
</tr>
<tr class="titStyle">
	<td width="50%">Suceptible de crédito</td>
	<td width="50%">Dado de baja</td>
</tr>
<tr class="itemStyle">
	<td><input type=radio name="bsuceptiblecredito" class="body" value="Si" <%if (beanresult42b.getBsuceptiblecredito()){%> CHECKED <%}%> onclick="this.form.declimitecredito.disabled=false" <% if  (beanresult42b.isMostrador()){%> DISABLED <% } %>> Si <input type=radio name="bsuceptiblecredito" class="body" value="No" <%if (!beanresult42b.getBsuceptiblecredito()){%> CHECKED <%}%> onclick="this.form.declimitecredito.value=0;this.form.declimitecredito.disabled=true;" <% if  (beanresult42b.isMostrador()){%> DISABLED <% } %>> No </td>
	<td><input type=radio name="bitdadodebaja" class="body" value="Si" <%if (beanresult42b.getBitdadodebaja()){%> CHECKED <%}%> <% if  (beanresult42b.isMostrador()){%> DISABLED <% } %>> Si <input type=radio name="bitdadodebaja" class="body" value="No" <%if (!beanresult42b.getBitdadodebaja()){%> CHECKED <%}%> <% if  (beanresult42b.isMostrador()){%> DISABLED <% } %>> No </td>
</tr>
</table>
<br>
<table align="center" border="1" bordercolor="#364859" rules="cols" cellspacing="0" width="750" style="border-color:Black;border-width:1px;border-style:solid;font-family: arial; font-size: 11px;border-collapse:collapse;" class="tablatext">
<tr class="titStyle"> 
	<td align=center><input type="submit" value="Modificar" class=body onclick="return modificar_onclick(this.form)"></td>
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
