<html>
<jsp:useBean id="bean114" class="pva.beans11.Bean114" scope="session"/>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link href="lib/estilo.css" rel=STYLESHEET type=text/css>
<title>PVA 11.4 Antiguedad de Saldos</title>
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
	if (valor == 1){
		forma.action = "ImprimeAntiguedadSaldos";
		Popup();
		popup.focus();
		forma.target="Popup";
	}
	return true;
}

</script>
</head>
<%@ include file="../main.jsp" %>
<body onunload="if(popen)popup.close();">
<form name=frm action="AntiguedadSaldos" method="POST">
<input type=hidden name="accion">
<table class="tablatext" width=750 align="center" cellpadding="0" cellspacing="0">
<tr>
	<td><img src="images/tpva11_4.GIF"></td>

        <th>
	<td><a href="../pvatx/hlp/pva11.0-11.4.htm" target="Ayuda Antiguedad de Saldos">Ayuda</a></td>
        </th>

</tr>
</table>
<br>
<table width="750" align="center" border="1" bordercolor="#364859" rules="cols" cellspacing="0" width="750" style="border-color:Black;border-width:1px;border-style:solid;font-family: arial; font-size: 11px;border-collapse:collapse;" class="tablatext">
	<tr class="itemStyle">
		<td colspan="8">Fecha:<%=bean114.getFecha()%></td>	
	</tr>
	<tr class="titStyle">
		<td width="16%">Cliente</td>
		<td width="12%">Saldo</td>
	  	<td width="12%">X Vencer</td>		
		<td width="12%">1 a 7 Dias</td>
		<td width="12%">8 a 29 Dias</td>
		<td width="12%">30 a 45 Dias</td>			
		<td width="12%">46 a 60 Dias</td>
	  	<td width="12%">más de 61 Dias</td>
	</tr>	
	<% String stilo;
	for (int i = 0; i < bean114.Vctsize(); i++) {
		if (i % 2 == 0)stilo = "itemStyle";
		else stilo = "alternatingItemStyle"; 
	%>
		<tr class="<%=stilo%>">
			<td width="16%"><%=bean114.getNombreCliente(i)%></td>		
			<td width="12%" align="right"><%=bean114.getSaldo(i)%></td>				
			<td width="12%" align="right"><%=bean114.getSxvencer(i)%></td>		
			<td width="12%" align="right"><%=bean114.getSx1_7(i)%></td>
		  	<td width="12%" align="right"><%=bean114.getSx8_29(i)%></td>
		  	<td width="12%" align="right"><%=bean114.getSx30_45(i)%></td>			
		  	<td width="12%" align="right"><%=bean114.getSx46_60(i)%></td>
		  	<td width="12%" align="right"><%=bean114.getSx61(i)%></td>
		</tr>			
	<%}%>		
</table>
<br>
<table width="750" align="center" border="1" bordercolor="#364859" rules="cols" cellspacing="0" width="750" style="border-color:Black;border-width:1px;border-style:solid;font-family: arial; font-size: 11px;border-collapse:collapse;" class="tablatext">
	<tr class="tititemStyle">
		<td width="16%">TOTALES</td>		
		<td width="12%" align="right"><%=bean114.getTSaldo()%></td>				
		<td width="12%" align="right"><%=bean114.getTSxvencer()%></td>		
		<td width="12%" align="right"><%=bean114.getTSx1_7()%></td>
	  	<td width="12%" align="right"><%=bean114.getTSx8_29()%></td>
	  	<td width="12%" align="right"><%=bean114.getTSx30_45()%></td>			
	  	<td width="12%" align="right"><%=bean114.getTSx46_60()%></td>
	  	<td width="12%" align="right"><%=bean114.getTSx61()%></td>
	</tr>			
	<tr class="tititemStyle">
		<td width="16%">PORCENTAJES</td>		
		<td width="12%" align="right">&nbsp;</td>			
		<td width="12%" align="right"><%=bean114.getPSxvencer()%>%</td>
		<td width="12%" align="right"><%=bean114.getPSx1_7()%>%</td>
	  	<td width="12%" align="right"><%=bean114.getPSx8_29()%>%</td>
	  	<td width="12%" align="right"><%=bean114.getPSx30_45()%>%</td>		
	  	<td width="12%" align="right"><%=bean114.getPSx46_60()%>%</td>
	  	<td width="12%" align="right"><%=bean114.getPSx61()%>%</td>
	</tr>				
	<tr class="titStyle">
		<td colspan="8" align="center">
			<input type=submit value="Imprimir" class="body" onClick="return Accion_onclick(1)">
		</td>
	</tr>
</table>
</body>
</html>
