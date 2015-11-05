<html>
<jsp:useBean id="bean42" class="pva.beans4.Bean42" scope="session"/>
<jsp:useBean id="bean113" class="pva.beans11.Bean113" scope="session"/>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link href="lib/estilo.css" rel=STYLESHEET type=text/css>
<title>PVA 11.3 Estados de cuenta</title>
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
		forma.action = "ImprimeEstadoCuenta";
		Popup();
		popup.focus();
		forma.target="Popup";
	}else{
		forma.action = "EstadoCuenta";
		forma.target = "_self";
	}
	return true;
}

function idcliente_onchange(){
	ind = document.frm.idcliente.selectedIndex;
	document.frm.rscliente.options[ind].selected = true;
	document.frm.rfcliente.options[ind].selected = true;
}

function rscliente_onchange(){
	ind = document.frm.rscliente.selectedIndex;
	document.frm.idcliente.options[ind].selected = true;
	document.frm.rfcliente.options[ind].selected = true;
}

function rfcliente_onchange(){
	ind = document.frm.rfcliente.selectedIndex;
	document.frm.idcliente.options[ind].selected = true;
	document.frm.rscliente.options[ind].selected = true;
}

</script>
</head>
<%@ include file="../main.jsp" %>
<body onunload="if(popen)popup.close();">
<form name=frm action="EstadoCuenta" method="POST">
<input type=hidden name="accion">
<table class="tablatext" width=750 align="center" cellpadding="0" cellspacing="0">
<tr>
	<td><img src="images/tpva11_3.GIF"></td>

        <th>
	<td><a href="../pvatx/hlp/pva11.0-11.3.htm" target="Ayuda Estados de Cuenta">Ayuda</a></td>
        </th>

</tr>
</table>
<br>
<table align=center border=1 borderColor=#364859 cellSpacing=0 class=tablatext rules=cols style="BORDER-BOTTOM: black 1px solid; BORDER-COLLAPSE: collapse; BORDER-LEFT: black 1px solid; BORDER-RIGHT: black 1px solid; BORDER-TOP: black 1px solid; FONT-FAMILY: arial; FONT-SIZE: 11px" width=750>
    <TR> 
      <TD colSpan=3>Selección de Cliente</TD>
    </TR>
    <TR class="titStyle"> 
      <TD width="15%">Número</TD>
      <TD width="50%">Razón Social</TD>
      <TD width="35%">R.F.C</TD>
    </TR>
    <TR> 
      <TD width="15%"> 
		<select name="idcliente" class="body" onChange="idcliente_onchange()">
			 <%for (int i=0; i<bean42.getClientesSize();i++){%>
			 <option value=<%=bean42.getId(i)%> <%if (bean113.getIdcliente()==bean42.getId(i)){%> SELECTED <%}%>> <%=bean42.getId(i)%></option>
			 <%}%>
		</select>
      <TD width="50%"> 
		<select name="rscliente" class="body" onChange="rscliente_onchange()">
			 <%for (int i=0; i<bean42.getClientesSize();i++){%>
			 <option value=<%= bean42.getId(i)%> <%if (bean113.getIdcliente()==bean42.getId(i)){%> SELECTED <%}%>><%= bean42.getRazonsocial(i) %></option>
			 <%}%>
		</select>
      </TD>
      <TD width="35%"> 
			<select name="rfcliente" class="body" onChange="rfcliente_onchange()">
			 <%for (int i=0; i<bean42.getClientesSize();i++){%>
			  <option value=<%= bean42.getId(i)%> <%if (bean113.getIdcliente()==bean42.getId(i)){%> SELECTED <%}%>><%= bean42.getRfc(i) %></option>
			 <%}%>
			</select>  
      </TD>
	</TR>
    <TR class="titStyle"> 
      <TD align="center" colSpan=5> 
          <input type=submit value="Buscar" class="body" name="submit" onClick="return Accion_onclick(2)">
      </TD>
    </TR>
</TABLE>	
<% if (bean113.isDetalle()){%>
<br>
<table align=center border=1 borderColor=#364859 cellSpacing=0 class=tablatext rules=cols style="BORDER-BOTTOM: black 1px solid; BORDER-COLLAPSE: collapse; BORDER-LEFT: black 1px solid; BORDER-RIGHT: black 1px solid; BORDER-TOP: black 1px solid; FONT-FAMILY: arial; FONT-SIZE: 11px" width=750>
    <TR> 
      <TD colspan="2">Detalle</TD>
    </TR>
    <TR class="titStyle"> 
      <TD colspan="2">Datos Generales</TD>
    </TR>
    <TR> 
      <TD align="center" width="353">
	  	<TEXTAREA class="body" cols=50 name=concepto rows=4><%=bean113.getDatosGenerales() %></TEXTAREA>
	 </TD>
     <TD width="395">
	    <table width="100%" border="0" class=tablatext>
          <tr class="alternatingItemStyle">
            <td width="42%">Limite</td>
            <td width="58%"><%=bean113.getLimite()%></td>
          </tr>
          <tr class="itemStyle">
            <td width="42%">Saldo</td>
            <td width="58%"><%=bean113.getSaldo()%></td>
          </tr>
          <tr class="alternatingItemStyle">
            <td width="42%">Disponible</td>
            <td width="58%"><%=bean113.getDisponible()%></td>
          </tr>
          <tr class="itemStyle">
            <td width="42%">Por Vencer</td>
            <td width="58%"><%=bean113.getSxvencer()%></td>
          </tr>	  
          <tr class="alternatingItemStyle">
            <td width="42%">Saldo Ingresos</td>
            <td width="58%"><%=bean113.getIngreso()%></td>
          </tr>	  		  
        </table>
     </TD>
    </TR>
    <TR> 
      <TD colspan="2">Vencido</TD>
    </TR>
    <TR> 
      <TD colspan="2">
		<table width="100%" align="center" border="1" bordercolor="#364859" rules="cols" cellspacing="0" width="750" style="border-color:Black;border-width:1px;border-style:solid;font-family: arial; font-size: 11px;border-collapse:collapse;" class="tablatext">
			<tr class="headerStyle">
			    <td>X Vencer</td>
				<td>1 a 7 Dias</td>
			  	<td>8 a 29 Dias</td>
			  	<td>30 a 45 Dias</td>			
			  	<td>46 a 60 Dias</td>
			  	<td>más de 61 Dias</td>
			  	<td>Total Vencido</td>
			</tr>	
			<tr class="itemStyle">
				<td align="right"><%=bean113.getSxvencer()%></td>			
				<td align="right"><%=bean113.getSx1_7()%></td>
			  	<td align="right"><%=bean113.getSx8_29()%></td>
			  	<td align="right"><%=bean113.getSx30_45()%></td>			
			  	<td align="right"><%=bean113.getSx46_60()%></td>
			  	<td align="right"><%=bean113.getSx61()%></td>
			  	<td align="right"><%=bean113.getVencido()%></td>
			</tr>			
			<tr class="tititemStyle">
				<td align="right"><%=bean113.getPxvencer()%>%</td>			
				<td align="right"><%=bean113.getPx1_7()%>%</td>
			  	<td align="right"><%=bean113.getPx8_29()%>%</td>
			  	<td align="right"><%=bean113.getPx30_45()%>%</td>			
			  	<td align="right"><%=bean113.getPx46_60()%>%</td>
			  	<td align="right"><%=bean113.getPx61()%>%</td>
			  	<td>&nbsp;</td>
			</tr>						
	    </table>
	  </TD>
    </TR>	
    <TR> 
      <TD colspan="2">Movimientos</TD>
    </TR>
	<TR>
		<TD colspan="2">
			<table width="100%" align="center" border="1" bordercolor="#364859" rules="cols" cellspacing="0" width="750" style="border-color:Black;border-width:1px;border-style:solid;font-family: arial; font-size: 11px;border-collapse:collapse;" class="tablatext">
				<tr class="headerStyle">
					<td>Documento</td>
				  	<td>Referencia</td>
				  	<td>Emisión</td>			
				  	<td>Vencimiento</td>
				  	<td>Cargo</td>
				  	<td>Abono</td>				  	
				  	<td>Estado</td>
				</tr>	
				<% String stilo;
				   for (int i = 0; i < bean113.sizeCxc(); i++) {
			 		 if (i % 2 == 0)stilo = "itemStyle";
					 else stilo = "alternatingItemStyle"; 
				 %>
					<tr class="<%=stilo%>">
					
					  <td><%=bean113.getVchDestipodocto(i)%> <%=bean113.getIiddoctoorigen(i)%></td>
					  <td><%if (bean113.getIidcontradocto(i)>0){%> <%=bean113.getVchDestipocontradocto(i)%> <%=bean113.getIidcontradocto(i)%> <%}%></td>
					  <td><%=bean113.getTisfechamovimiento(i)%></td>	
					  <td><%=bean113.getTisfechavencimient(i)%></td>
  					  <td align="right"><%=bean113.getDecImporteCargoEdo(i)%></td> 		  					  
  					  <td align="right"><%=bean113.getDecImporteAbonoEdo(i)%></td> 		
  					<% String strEsVencido; 
  					   if ( (bean113.getVchDestipodocto(i).equalsIgnoreCase("FAC") == true)&&(bean113.getisVencido(i) == true))strEsVencido = "Vencido";  					   
					 else strEsVencido = ""; 
    				 %>   					  
    			 	  <td align="right"><%=strEsVencido%></td>
					</tr>
				<%}%>
			</table>
		</TD>
	</TR>
	<TR class="titStyle"> 
	  <TD align="center" colSpan=2> 
          <input type=submit value="Imprimir" class="body" onClick="return Accion_onclick(3)">
      </TD>
    </TR>		
</table>	
<%}%>
</form>
</body>
</html>
