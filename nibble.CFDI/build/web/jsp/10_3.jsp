<html>
<jsp:useBean id="bean103" class="pva.beans10.Bean103" scope="session"/>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link href="lib/estilo.css" rel=STYLESHEET type=text/css>
<title>PVA 10.3 Reporte de Ingresos</title>

<script language="JavaScript" src="lib/calendar2.js"></script>


<script type="text/javascript" src="lib/util.js"></script>
<script language="javascript">
var  popen = false;

function Popup(){
 popup=window.open("","Popup",'toolbar=0,location=0,directories=0,status=0,menubar=0,scrollbars=yes,resizable=yes,width=700,height=400');
 popen =true;
}

function Accion_onclick(valor){
	forma = document.frm;
	forma.accion.value = valor;
	if (valor == 3){
		forma.action = "ImprimeReporteIngresos";
		Popup();
		popup.focus();
		forma.target="Popup";
	}else{
		forma.action = "ReporteIngresos";
		forma.target = "_self";	
	}
	return true;
}

</script>	
</head>
<%@ include file="../main.jsp" %>
<body onunload="if(popen)popup.close();">
<form name=frm action="ReporteIngresos" method="POST">
<input type=hidden name="accion">
<table class="tablatext" width=750 align="center" cellpadding="0" cellspacing="0">
<tr>
	<td><img src="images/tpva10_3.GIF"></td>

        <th>
	<td><a href="../pvatx/hlp/pva10.0-10.3.htm" target="Ayuda Reporte de Ingresos">Ayuda</a></td>
        </th>

</tr>
</table>
<table align=center border=1 borderColor=#364859 cellSpacing=0 class=tablatext rules=cols style="BORDER-BOTTOM: black 1px solid; BORDER-COLLAPSE: collapse; BORDER-LEFT: black 1px solid; BORDER-RIGHT: black 1px solid; BORDER-TOP: black 1px solid; FONT-FAMILY: arial; FONT-SIZE: 11px" width=750>
	<TR class="itemStyle">
      <TD align="center"> 
        Fecha :<input name=fecha value="<%=bean103.getFecha()%>">
        <a href="javascript:cal5.popup();" onMouseOut="window.status='';return true;" onMouseOver="window.status='Seleccione Fecha';return true;"><img border=0 src="images/calendar.gif"></a>		
	  </TD>
	  
<SCRIPT LANGUAGE="JavaScript">

			var cal5 = new calendar2(document.forms['frm'].elements['fecha']);
			cal5.year_scroll = true;
			cal5.time_comp = false;
										
		</SCRIPT>
	  
    </TR>
    <TR class="titStyle"> 
      <TD align="center" colSpan=5> 
          <input type=submit value="Buscar" class="body" name="submit" onClick="return Accion_onclick(2)">
      </TD>
    </TR>
</table>
<br>
<% if (bean103.isDetalle()){%>
	<table width=750 align="center" border="1" bordercolor="#364859" rules="cols" cellspacing="0" width="750" style="border-color:Black;border-width:1px;border-style:solid;font-family: arial; font-size: 11px;border-collapse:collapse;" class="tablatext">
		<tr class="headerStyle">
			<td>Documento.</td>
		  	<td>Hora</td>
		  	<td>Tipo</td>
		  	<td>Aviso</td>
		  	<td>Monto</td>
		</tr>	
		<% String stilo;
		   for (int i = 0; i < bean103.sizeCxc(); i++) {
	 		 if (i % 2 == 0)stilo = "itemStyle";
			 else stilo = "alternatingItemStyle"; 
		 %>
			<tr class="<%=stilo%>">
			  <td align="center"><%=bean103.getVchDestipodocto(i)%> <%=bean103.getIidcontradocto(i)%></td>
			  <td><%=bean103.getTisfechamovimiento(i)%></td>	
			  <td><%=bean103.getVchDestipopago(i)%></td>
			  <td><%=bean103.getBitavisoing(i)%></td>
			  <td align="right"><%=bean103.getDechaber(i)%></td>
			</tr>
		<%}%>
	    <TR class="titStyle"> 
	      <TD align="center" colSpan=5> 
	          <input type=submit value="Imprimir" class="body" onClick="return Accion_onclick(3)">
	      </TD>
	    </TR>		
	</table>
<%}%>
</form>
</body>
</html>