<html>
<jsp:useBean id="bean91e" class="pva.beans9.Bean91e" scope="session"/>
<head>
<title>PVA 9.1 Facturar</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link href="lib/estilo.css" rel=STYLESHEET type=text/css>
<link href="lib/selector.css" rel=STYLESHEET type=text/css>
<script language=JavaScript src="lib/selector.js"></script>
<script language=JavaScript src="lib/util.js"></script>
<script type="text/javascript" src="lib/engine/jsSQL.js"></script>
<script type="text/javascript" src="lib/tools/PrintOptions.js"></script>
<script type="text/javascript" src="lib/tools/FillOptions.js"></script>
<script type="text/javascript">
var c=0;
var planta  = new Array();
planta[c++] = new Array("iidplanta","vchdescripcion");
planta[c++] = new Array("int","String");
<%for (int i = 0; i < bean91e.getPlantasSize(); i++){%>
planta[c++] = new Array(<%= bean91e.getPlantaId(i)%>,"<%=bean91e.getPlantaDes(i)%>");
<%}%> 
c=0;
var modelo  = new Array();
modelo[c++] = new Array("iidmodelo","vchdescripcion","iidplanta");
modelo[c++] = new Array("int","String","int");
<%
 int planta = 0;
  for (int i = 0; i < bean91e.getModelosSize(); i++){
   if(planta != bean91e.getModeloPId(i)){%>
    modelo[c++] = new Array("","",<%=bean91e.getModeloPId(i)%>);
 <% planta = bean91e.getModeloPId(i);
   }%>
  modelo[c++] = new Array(<%= bean91e.getModeloId(i)%>,"<%=bean91e.getModeloDes(i)%>",<%=bean91e.getModeloPId(i)%>);
<%}%> 
c=0;
var submodelo  = new Array();
submodelo[c++] = new Array("iidsmodelo","vchdescripcion","iidmodelo");
submodelo[c++] = new Array("int","String","int");
<%
	int modelo = 0;
	for (int i = 0; i < bean91e.getSmodelosSize(); i++){
 	 if (modelo != bean91e.getSmodeloMId(i)){%>
		submodelo[c++] = new Array("","",<%=bean91e.getSmodeloMId(i)%>);	 
   <% modelo = bean91e.getSmodeloMId(i);
     }%>
     submodelo[c++] = new Array(<%=bean91e.getSmodeloId(i)%>,"<%=bean91e.getSmodeloDes(i)%>",<%=bean91e.getSmodeloMId(i)%>);
<%}%> 

function FillSlave(){
 var f=document.forms["frm"];
 var m=f.master;
 var s=f.slave;
 var code;
 if (m.length>0) code=m.options[m.selectedIndex].value;
 else code="";
 if (code=="") DelOptions(s);
 else{
  var plantas = Where( modelo, "iidplanta", "=", code );
  FillOptions( plantas, s, 'iidmodelo', 'vchdescripcion', false );
  plantas = null;
 }
}

function FillSlave2(){
 var f=document.forms["frm"];
 var m=f.slave;
 var s=f.sslave;
 var code;
 if (m.length>0) code=m.options[m.selectedIndex].value;
 else code="";
 if (code=="") DelOptions(s);
 else{
  var modelos = Where( submodelo, "iidmodelo", "=", code );
  FillOptions( modelos, s, 'iidsmodelo', 'vchdescripcion', false );
  modelos = null;
 }
}
</script>
</head>
<body bgcolor="#FFFFFF" text="#000000">
<table class="tablatext" width=750 align="center" cellpadding="0" cellspacing="0">
<tr>
	<td><img src="images/tpva9_1.GIF"></td>

        <th>
	<td><a href="../pvatx/hlp/pva9.0-9.1.htm" target="Ayuda Facturar">Ayuda</a></td>
        </th>

</tr>
</table>
<br>
<br>
<form name=frm action="Facturar" method="POST">
<input type=hidden name="accion" value=12>
<table width=750 align="center" border="1" bordercolor="#364859" rules="cols" cellspacing="0" style="border-color:Black;border-width:1px;border-style:solid;font-family: arial; font-size: 11px;border-collapse:collapse;" class="tablatext">
<tr>
	<td colspan=3>Vehículo</td>
</tr>
		<tr class="headerStyle">
	    	<td width="33%">Planta</td>
			<td width="33%">Modelo</td>
			<td width="34%">SubModelo</td>
		</tr>
		<tr class="itemStyle">
			<td>
				<select id="master" name="master" class="body" onchange="FillSlave();FillSlave2();">
			    <option value="" id="master1"></option>
		    	<script type="text/javascript"> PrintOptions(planta,"iidplanta","vchdescripcion");</script>
		      	</select>
			</td>	
			<td>
				<select id="slave" name="slave" class="body" onchange="FillSlave2();">
		  		</select>
			</td>	
			<td>
		  		<select id="sslave" name="sslave" class="body">
		  		</select>
		</td>
		</tr>
</table>
<br>
<table width=750 align="center" border="1" bordercolor="#364859" rules="cols" cellspacing="0" style="border-color:Black;border-width:1px;border-style:solid;font-family: arial; font-size: 11px;border-collapse:collapse;" class="tablatext"><tr bgcolor="#E8EEF9">
<tr class="headerStyle">
	  <td width="137">Año de</td>
	  <td colspan="2">A</td>
	  <td width="150">Cilindros</td>
	  <td width="139">Motor</td>
	  <td width="189">Combustible</td>
</tr>
<tr class="itemStyle">
 	  <td width="137"> 
        <input type=text name="de" class="body" size=4 maxlength="4" onkeypress="return numbersonly()"></td>
	  <td colspan ="2">
        <input type=text name="a" class="body" size=4 maxlength="4" onkeypress="return numbersonly()">
      </td>
	  <td width="150"> 
        <select class="body" name="cilindros">
 		<option value=""></option>
		<%for (int i = 0; i < bean91e.getCilindrosSize(); i++){%>
			<option value="<%= bean91e.getCilindroId(i)%>"><%=bean91e.getCilindroDes(i)%></option>
		<%}%> 		
		</select>
	</td>
	  <td width="139"> 
        <input type=text name="motor" class="body"></td>
	  <td width="189"> 
        <select class="body" name="combustible">
 		<option value=""></option>		
		<%for (int i = 0; i < bean91e.getCombustiblesSize(); i++){%>
			<option value="<%= bean91e.getCombustibleId(i)%>"><%=bean91e.getCombustibleDes(i)%></option>
		<%}%> 		
		</select>
	</td>
</tr>
<tr class="headerStyle">
	<td colspan="2">Tracción</td>
	  <td width="157">Transmisión</td>
	  <td colspan="2">Alimentación Combustible</td>
	  <td width="189">Aspiración</td>
</tr>
<tr class="itemStyle">
	<td colspan="2">
		<select class="body" name="traccion">
 		<option value=""></option>		
		<%for (int i = 0; i < bean91e.getTraccionsSize(); i++){%>
			<option value="<%= bean91e.getTraccionId(i)%>"><%=bean91e.getTraccionDes(i)%></option>
		<%}%> 		
		</select>	
	</td>
	  <td width="157"> 
        <select class="body" name="transmision">
 		<option value=""></option>		
		<%for (int i = 0; i < bean91e.getTransmisionsSize(); i++){%>
			<option value="<%= bean91e.getTransmisionId(i)%>"><%=bean91e.getTransmisionDes(i)%></option>
		<%}%> 			
  		</select> 
	</td>
	  <td colspan="2"> 
        <select class="body" name="alimentacion">
 		<option value=""></option>		
		<%for (int i = 0; i < bean91e.getAlimentacionsSize(); i++){%>
			<option value="<%= bean91e.getAlimentacionId(i)%>"><%=bean91e.getAlimentacionDes(i)%></option>
		<%}%> 			
  		</select> 
	</td>
	  <td width="189"> 
        <select class="body" name="aspiracion">
 		<option value=""></option>		
		<%for (int i = 0; i < bean91e.getAspiracionsSize(); i++){%>
			<option value="<%= bean91e.getAspiracionId(i)%>"><%=bean91e.getAspiracionDes(i)%></option>
		<%}%> 			
  		</select> 
	</td>
</tr>
<tr class="headerStyle">
	<td colspan="2">Desplazamiento</td>
	  <td width="157">Potencia HP/RPM</td>
	  <td width="150">Número de Puertas</td>
	  <td width="139">Dirección Hidráulica</td>
	  <td width="189">Aire Acondicionado</td>
</tr>
<tr class="itemStyle">
	<td colspan="2"><input type=text name="desplazamiento" class="body"></td>
	  <td width="157"> 
        <input type=text name="potencia" class="body"></td>
	  <td width="150"> 
        <input type=text name="puertas" class="body" size="4" maxlength="4" onkeypress="return numbersonly()">
      </td>
	  <td class="body"> 
        <input type="radio" name="direccion" value="N" checked>No 
        <input type="radio" name="direccion" value="S">Si
	 </td>
      <td class="body">
        <input type="radio" name="aire" value="N" checked>No 
        <input type="radio" name="aire" value="S">Si
	  </td>
</tr>
</tr>
<br>
<table width=750 align="center" border="1" bordercolor="#364859" rules="cols" cellspacing="0" style="border-color:Black;border-width:1px;border-style:solid;font-family: arial; font-size: 11px;border-collapse:collapse;" class="tablatext">
<tr class="titStyle">
	  <td align="center">
        <input type="submit" value="Muestra Aplicaciones" class=body name="bSubmit">
        <input type="reset" name="bReset" class=body value="Restablecer Criterios">
      </td>
</tr>
</table>
</form>
</body>
</html>
