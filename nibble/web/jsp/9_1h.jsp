<html>
<jsp:useBean id="bean91Aplicacion" class="pva.beans9.Bean91Aplicacion" scope="session"/>
<head>
<title>PVA 9.1 Facturar</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link href="lib/estilo.css" rel=STYLESHEET type=text/css>
<link href="lib/selector.css" rel=STYLESHEET type=text/css>
<script language=JavaScript>

function aceptar_onclick(form){
	if (form.iidaplicacion.value==''){
		alert ('Debe seleccionar una aplicación')
		return false;
	}
	form.accion.value=15;
	return true;
}
function selec(i){
	document.frm.temp[i].checked=true
	document.frm.iidaplicacion.value=document.frm.temp[i].value;
}

</script>
</head>
<body>
<table class="tablatext" width=750 align="center" cellpadding="0" cellspacing="0">
<tr>
	<td><img src="images/tpva9_1.GIF"></td>

        <th>
	<td><a href="../pvatx/hlp/pva9.0-9.1.htm" target="Ayuda Facturar">Ayuda</a></td>
        </th>

</tr>
</table>
<br>
<div align="center">
<form name="frm" method="POST" action="Facturar">
<input type="hidden" name="accion" value=11>
<table width=550 align="center" border="1" bordercolor="#364859" rules="cols" cellspacing="0" style="border-color:Black;border-width:1px;border-style:solid;font-family: arial; font-size: 11px;border-collapse:collapse;" class="tablatext">
<tr class="headerStyle">
  	  <td>Sel</td>
	  <td>Aplicacion</td>
	  <td>Planta</td>
	  <td>Modelo</td>
	  <td>SubModelo</td>
	  <td>Año</td>
	  <td>Cilindros</td>
	  <td>Motor</td>
	  <td>Combustible</td>
	  <td>Tracción</td>
	  <td>Transmisión</td>
	  <td>Alimentación</td>
	  <td>Aspiración</td>
	  <td>Desplazamiento</td>
	  <td>Potencia</td>
	  <td>D.Hidráulica</td>
	  <td>A.A.</td>  
    </tr>
	<% String stilo;
		   for (int i = 0; i < bean91Aplicacion.getVSize(); i++) {
	 		 if (i % 2 == 0)stilo = "itemStyle";
			 else stilo = "alternatingItemStyle"; 
      %>
  	  <tr class="<%=stilo%>">
	    <td>
			<input type="radio" name="temp" value="<%=bean91Aplicacion.getAplicacion(i)%>" onclick="this.form.iidaplicacion.value=this.value">
		</td> 
	    <td><a href="javascript:selec(<%=i%>)" class="ligas"><%=bean91Aplicacion.getAplicacion(i)%></a></td>
	    <td><a href="javascript:selec(<%=i%>)" class="ligas"><%=bean91Aplicacion.getPlanta(i)%></a></td>
	    <td><%=bean91Aplicacion.getModelo(i)%></td>
	    <td><%=bean91Aplicacion.getSubmodelo(i)%></td>
	    <td><%=bean91Aplicacion.getYY(i)%></td>
	    <td><%=bean91Aplicacion.getCilindros(i)%></td>
	    <td><%=bean91Aplicacion.getMotor(i)%></td>
		<td><%=bean91Aplicacion.getCombustible(i)%></td>
	    <td><%=bean91Aplicacion.getTraccion(i)%></td>
	    <td><%=bean91Aplicacion.getTransmision(i)%></td>
	    <td><%=bean91Aplicacion.getAlimentacion(i)%></td>
	    <td><%=bean91Aplicacion.getAspiracion(i)%></td>	
	    <td><%=bean91Aplicacion.getDesplazamiento(i)%></td>
	    <td><%=bean91Aplicacion.getPotencia(i)%></td>
	    <td><%=bean91Aplicacion.getDireccion(i)%></td>
		<td><%=bean91Aplicacion.getAA(i)%></td>		
	  </tr>	
	<% }%>
</table>
<br>
<table width=550 align="center" border="1" bordercolor="#364859" rules="cols" cellspacing="0" style="border-color:Black;border-width:1px;border-style:solid;font-family: arial; font-size: 11px;border-collapse:collapse;" class="tablatext">
<tr class="titStyle">
	<td align=center><input type="submit" value="Partes" class=body onclick="return aceptar_onclick(this.form)">	</td>
</tr>
</table>
<br>
<table align="center" width="550">
	<tr><td><a href="javascript: document.frm.submit();" class="ligas"><< Regresar</a></td></tr>
</table>
 <input type="hidden" name="iidaplicacion">  
</form>	
</div>
</body>
</html>
