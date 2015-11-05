<html>
<jsp:useBean id="bean102" class="pva.beans10.Bean102" scope="session"/>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link href="lib/estilo.css" rel=STYLESHEET type=text/css>
<title>PVA 10.2 Corte de Caja</title>

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
		forma.action = "ImprimeCorteCaja";
		Popup();
		popup.focus();
		forma.target="Popup";
	}else{
		forma.action = "CorteCaja";
		forma.target = "_self";	
	}
	return true;
}
/*
function Accion_onclick(valor){
	forma = document.frm;
	forma.accion.value = valor;
	return true;
}
*/
</script>	
</head>
<%@ include file="../main.jsp" %>
			
<body onunload="if(popen)popup.close();">

<form name=frm action="CorteCaja" method="POST">
<input type=hidden name="accion">
<table class="tablatext" width=750 align="center" cellpadding="0" cellspacing="0">
<tr>
	<td><img src="images/tpva10_2.GIF"></td>
</tr>
</table>
<table align=center border=1 borderColor=#364859 cellSpacing=0 class=tablatext rules=cols style="BORDER-BOTTOM: black 1px solid; BORDER-COLLAPSE: collapse; BORDER-LEFT: black 1px solid; BORDER-RIGHT: black 1px solid; BORDER-TOP: black 1px solid; FONT-FAMILY: arial; FONT-SIZE: 11px" width=750>
	<TR class="itemStyle">
      <TD align="center"> 
        Fecha de Corte:<input name=fecha value="<%=bean102.getFecha()%>">
        <a href="javascript:cal5.popup();"  onMouseOver="window.status='Seleccione Fecha';return true;"><img border=0 src="images/calendar.gif"></a>        
	  </TD>
	  
<SCRIPT LANGUAGE="JavaScript">

			var cal5 = new calendar2(document.forms['frm'].elements['fecha']);
			cal5.year_scroll = true;
			cal5.time_comp = false;
										
		</SCRIPT>
	  
    </TR>
    <TR class="titStyle"> 
      <TD align="center" colSpan=5> 
          <input type=submit value="Aceptar" class="body" name="submit" onClick="return Accion_onclick(2)">
      </TD>
    </TR>
</table>
<br>
<% if (bean102.isDetalle()){%>
	
	<table width=750 align="center" border="1" bordercolor="#364859" rules="cols" cellspacing="0" width="750" style="border-color:Black;border-width:1px;border-style:solid;font-family: arial; font-size: 11px;border-collapse:collapse;" class="tablatext">
			
				<tr class="tititemStyle"> 
					<td colspan="8">
						Ventas de Contado (<%=bean102.getContadosize()%>)
					</td>
				</tr>
	<tr class="headerStyle">
				<td>No.Doc</td>
				<td>Referencia</td>
				<td>No.Cliente</td>
				<td>Nombre</td>
				<td>Importe</td>
				<td>Desc.</td>			
				<td>I.V.A.</td>
				<td>Total</td>
			</tr>
				<% 
					String stilo1;
					float TotalImporteContado = 0;
					float TotalIvaContado = 0;
					float TotalDescuentoContado = 0;
					float TotalTotalContado = 0;
				
				   for (int i = 0; i < bean102.getContadosize(); i++) {
					 if (i % 2 == 0)stilo1 = "itemStyle";
					 else stilo1 = "alternatingItemStyle"; 
				 %>
					<tr class="<%=stilo1%>">
					  <td align="center"><%=i+1%></td>
					  <td><%=bean102.getIiddocto(i,1)%></td>	
					  <td><%=bean102.getIidcliente(i,1)%></td>
					  <td><%=bean102.getNombrecliente(i,1)%></td>
			  
					  <td align="right"><%=bean102.getDecimporte(i,1)%></td>			  
					  <% TotalImporteContado = TotalImporteContado + bean102.getDecimporte(i,1); %>			  
  			  
					  <td align="right"><%=bean102.getDecdesc(i,1)%></td>
					  <% TotalDescuentoContado = TotalDescuentoContado + bean102.getDecdesc(i,1); %>				     			  
   			  
					  <td align="right"><%=bean102.getDeciva(i,1)%></td>
					  <% TotalIvaContado = TotalIvaContado + bean102.getDeciva(i,1); %>  			  
  			  
					  <td align="right"><%=bean102.getDectotal(i,1)%></td>			  			  			  
					  <% TotalTotalContado= TotalTotalContado+ bean102.getDectotal(i,1); %>
			  
					</tr>
				<%}%>
		
				<tr class="tititemStyle"> 
					<td colspan="8">				 
					</td>
				</tr>
				<tr>
					<td align="center" colspan="4">Totales Ventas de Contado</td>
					<td align="right">Importe: <%=TotalImporteContado%></td>
					<td align="right">Descuento: <%=TotalDescuentoContado%></td>
					<td align="right">IVA: <%=TotalIvaContado%></td>
					<td align="right">Total: <%=TotalTotalContado%></td>			  			  			  
			
				</tr>
		</table>
		
		<BR>
	
	
	<table width=750 align="center" border="1" bordercolor="#364859" rules="cols" cellspacing="0" width="750" style="border-color:Black;border-width:1px;border-style:solid;font-family: arial; font-size: 11px;border-collapse:collapse;" class="tablatext">	
		
		<tr class="tititemStyle"> 
			<td colspan="8">
				Ventas de Credito (<%=bean102.getCreditosize()%>)
			</td>
		</tr>
	<tr class="headerStyle">
				<td>No.Doc</td>
				<td>Referencia</td>
				<td>No.Cliente</td>
				<td>Nombre</td>
				<td>Importe</td>
				<td>Desc.</td>			
				<td>I.V.A.</td>
				<td>Total</td>
			</tr>
		<%
			String stilo; 
			float TotalImporte = 0;
			float TotalIva = 0;
			float TotalDescuento=0;
			float TotalTotal=0;
			for (int i = 0; i < bean102.getCreditosize(); i++) {
	 		 if (i % 2 == 0)stilo = "itemStyle";
			 else stilo = "alternatingItemStyle"; 
		 %>
			<tr class="<%=stilo%>">
			  <td align="center"><%=i+1%></td>
			  <td><%=bean102.getIiddocto(i,2)%></td>	
			  <td><%=bean102.getIidcliente(i,2)%></td>
			  <td><%=bean102.getNombrecliente(i,2)%></td>			  
			  
			  <td align="right"><%=bean102.getDecimporte(i,2)%></td>
			  <% TotalImporte = TotalImporte + bean102.getDecimporte(i,2); %>			  
			  
			  <td align="right"><%=bean102.getDecdesc(i,2)%></td>
   			  <% TotalDescuento = TotalDescuento + bean102.getDecdesc(i,2); %>				     			  
   			  
			  <td align="right"><%=bean102.getDeciva(i,2)%></td>
  			  <% TotalIva = TotalIva + bean102.getDeciva(i,2); %>  			  
  			  
			  <td align="right"><%=bean102.getDectotal(i,2)%></td>			  			  			  
			  <% TotalTotal= TotalTotal+ bean102.getDectotal(i,2); %>
			  
			</tr>
		<%}%>
		
		<tr class="tititemStyle"> 
			<td colspan="8"></td>
		</tr>
		<tr>
			<td align="center" colspan="4">Totales Ventas de Credito</td>			
			<td align="right">Importe: <%=TotalImporte%></td>
			<td align="right">Descuento: <%=TotalDescuento%></td>
			<td align="right">IVA: <%=TotalIva%></td>
			<td align="right">Total: <%=TotalTotal%></td>			  			  			  
		</tr>
	</table>
	
	<BR>
	<table width=750 align="center" border="1" bordercolor="#364859" rules="cols" cellspacing="0" width="750" style="border-color:Black;border-width:1px;border-style:solid;font-family: arial; font-size: 11px;border-collapse:collapse;" class="tablatext">
	
	<tr class="tititemStyle"> 
				<td colspan="8">
					Devoluciones (<%=bean102.getDevolucionsize()%>)
				</td>
	</tr>
	<tr class="headerStyle">
				<td>No.Doc</td>
				<td>Referencia</td>
				<td>No.Cliente</td>
				<td>Nombre</td>
				<td>Importe</td>
				<td>Desc.</td>			
				<td>I.V.A.</td>
				<td>Total</td>
	</tr>		
			
			<% 	float TotalImporteDevoluciones = 0;
				float TotalIvaDevoluciones = 0;
				float TotalDescuentoDevoluciones=0;
				float TotalTotalDevoluciones=0;
		
				for (int i = 0; i < bean102.getDevolucionsize(); i++) {
				 if (i % 2 == 0)stilo = "itemStyle";
				 else stilo = "alternatingItemStyle"; 
			 %>
				<tr class="<%=stilo%>">
				  <td align="center"><%=i+1%></td>
				  <td><%=bean102.getIiddocto(i,3)%></td>	
				  <td><%=bean102.getIidcliente(i,3)%></td>
				  <td><%=bean102.getNombrecliente(i,3)%></td>
			  
				  <td align="right"><%=bean102.getDecimporte(i,3)%></td>
				  <% TotalImporteDevoluciones = TotalImporteDevoluciones + bean102.getDecimporte(i,3); %>
			  
				  <td align="right"><%=bean102.getDecdesc(i,3)%></td>
				  <% TotalDescuentoDevoluciones = TotalDescuentoDevoluciones + bean102.getDecdesc(i,3); %>				     			  			  
   			  
				  <td align="right"><%=bean102.getDeciva(i,3)%></td>
				  <% TotalIvaDevoluciones = TotalIvaDevoluciones + bean102.getDeciva(i,3); %>  			  			  
  			  
				  <td align="right"><%=bean102.getDectotal(i,3)%></td>			  			  			  
				  <% TotalTotalDevoluciones= TotalTotalDevoluciones+ bean102.getDectotal(i,3); %>			  
			  
				</tr>
			<%}%>
		
			<tr class="tititemStyle"> 
				<td colspan="8"></td>
			</tr>
			<tr>
				<td align="center" colspan="4">	Totales Devoluciones</td>
				<td align="right">Importe: <%=TotalImporteDevoluciones%></td>
				<td align="right">Descuento: <%=TotalDescuentoDevoluciones%></td>
				<td align="right">IVA: <%=TotalIvaDevoluciones%></td>
				<td align="right">Total: <%=TotalTotalDevoluciones%></td>			  			  			  
			
			</tr>
	
	</table>
	<BR>
	
	<table width=750 align="center" border="1" bordercolor="#364859" rules="cols" cellspacing="0" width="750" style="border-color:Black;border-width:1px;border-style:solid;font-family: arial; font-size: 11px;border-collapse:collapse;" class="tablatext">
	<tr class="tititemStyle"> 
				<td colspan="8">
					Cancelaciones (<%=bean102.getCancelacionsize()%>)
				</td>
			</tr>
	<tr class="headerStyle">
					<td>No.Doc</td>
					<td>Referencia</td>
					<td>No.Cliente</td>
					<td>Nombre</td>
					<td>Importe</td>
					<td>Desc.</td>			
					<td>I.V.A.</td>
					<td>Total</td>
		</tr>		
		
		
			<%  
			   float TotalImporteCancelaciones = 0;
				float TotalIvaCancelaciones = 0;
				float TotalDescuentoCancelaciones=0;
				float TotalTotalCancelaciones=0;
				String stilo2;	
				 for (int i = 0; i < bean102.getCancelacionsize(); i++) {
				 if (i % 2 == 0)stilo2 = "itemStyle";
				 else stilo2 = "alternatingItemStyle"; 
			 %>
				<tr class="<%=stilo2%>">
				  <td align="center"><%=i+1%></td>
				  <td><%=bean102.getIiddocto(i,4)%></td>	
				  <td><%=bean102.getIidcliente(i,4)%></td>
				  <td><%=bean102.getNombrecliente(i,4)%></td>
			  
				  <td align="right"><%=bean102.getDecimporte(i,4)%></td>			  
				  <% TotalImporteCancelaciones = TotalImporteCancelaciones + bean102.getDecimporte(i,4); %>
  			  
				  <td align="right"><%=bean102.getDecdesc(i,4)%></td>
				  <% TotalDescuentoCancelaciones = TotalDescuentoCancelaciones + bean102.getDecdesc(i,4); %>				     			  			  
   			  
				  <td align="right"><%=bean102.getDeciva(i,4)%></td>
				  <% TotalIvaCancelaciones = TotalIvaCancelaciones + bean102.getDeciva(i,4); %>  			  			  
  			  
				  <td align="right"><%=bean102.getDectotal(i,4)%></td>			  			  			  
				  <% TotalTotalCancelaciones= TotalTotalCancelaciones+ bean102.getDectotal(i,4); %>			  
			  
				</tr>
				<%}%>
		
						<tr class="tititemStyle"> 
							<td colspan="8">
								
							</td>
						</tr>
						<tr>
							<td align="center" colspan="4">Totales Cancelaciones</td>
							<td align="right">Importe: <%=TotalImporteCancelaciones%></td>
							<td align="right">Descuento: <%=TotalDescuentoCancelaciones%></td>
							<td align="right">IVA: <%=TotalIvaCancelaciones%></td>
							<td align="right">Total: <%=TotalTotalCancelaciones%></td>			  			  			  
			
						</tr>
				
	</table>
		
	<BR>
	<table width=750 align="center" border="1" bordercolor="#364859" rules="cols" cellspacing="0" width="750" style="border-color:Black;border-width:1px;border-style:solid;font-family: arial; font-size: 11px;border-collapse:collapse;" class="tablatext">
	
	<tr class="tititemStyle"> 
				<td colspan="8">
					Bonificaciones (<%=bean102.getBonificacionsize()%>)
				</td>
			</tr>
	<tr class="headerStyle">
						<td>No.Doc</td>
						<td>Referencia</td>
						<td>No.Cliente</td>
						<td>Nombre</td>
						<td>Importe</td>
						<td>Desc.</td>			
						<td>I.V.A.</td>
						<td>Total</td>
			</tr>		
			
			<% 	
				float TotalImporteBonificaciones = 0;
				float TotalIvaBonificaciones = 0;
				float TotalDescuentoBonificaciones =0;
				float TotalTotalBonificaciones =0;
		
				 for (int i = 0; i < bean102.getBonificacionsize(); i++) {
				 if (i % 2 == 0)stilo = "itemStyle";
				 else stilo = "alternatingItemStyle"; 
			 %>
				<tr class="<%=stilo%>">
				  <td align="center"><%=i+1%></td>
				  <td><%=bean102.getIiddocto(i,5)%></td>	
				  <td><%=bean102.getIidcliente(i,5)%></td>
				  <td><%=bean102.getNombrecliente(i,5)%></td>
			  
				  <td align="right"><%=bean102.getDecimporte(i,5)%></td>
				  <% TotalImporteBonificaciones = TotalImporteBonificaciones + bean102.getDecimporte(i,5); %>
  			  
				  <td align="right"><%=bean102.getDecdesc(i,5)%></td>
				  <% TotalDescuentoBonificaciones = TotalDescuentoBonificaciones + bean102.getDecdesc(i,5); %>				     			  			  
   			  
				  <td align="right"><%=bean102.getDeciva(i,5)%></td>
				  <% TotalIvaBonificaciones = TotalIvaBonificaciones + bean102.getDeciva(i,5); %>  			  			  
  			  
				  <td align="right"><%=bean102.getDectotal(i,5)%></td>			  			  			  
				  <% TotalTotalBonificaciones = TotalTotalBonificaciones + bean102.getDectotal(i,5); %>			  
			  
			</tr>
			<%}%>		
			<tr class="tititemStyle"> 
				<td colspan="8"></td>
			</tr>
			<tr>
				<td align="center" colspan="4">Totales Bonificaciones</td>
				<td align="right">Importe: <%=TotalImporteBonificaciones%></td>
				<td align="right">Descuento: <%=TotalDescuentoBonificaciones%></td>
				<td align="right">IVA: <%=TotalIvaBonificaciones%></td>
				<td align="right">Total: <%=TotalTotalBonificaciones%></td>
			</tr>	
	</table>
	
	<BR>
	
	<table width=750 align="center" border="1" bordercolor="#364859" rules="cols" cellspacing="0" width="750" style="border-color:Black;border-width:1px;border-style:solid;font-family: arial; font-size: 11px;border-collapse:collapse;" class="tablatext">
	<tr class="tititemStyle"> 
		<td colspan="8">
			Cargos (<%=bean102.getCargosize()%>)
		</td>
	</tr>
	<tr class="headerStyle">
							<td>No.Doc</td>
							<td>Referencia</td>
							<td>No.Cliente</td>
							<td>Nombre</td>
							<td>Importe</td>
							<td>Desc.</td>			
							<td>I.V.A.</td>
							<td>Total</td>
				</tr>		
	<%
	 	 
		 float TotalImporteCargos = 0;
		 float TotalIvaCargos = 0;
		 float TotalDescuentoCargos =0;
		 float TotalTotalCargos =0; 
		String stilo3;	 
		 for (int i = 0; i < bean102.getCargosize(); i++) {
		 if (i % 2 == 0)stilo3 = "itemStyle";
		 else stilo3 = "alternatingItemStyle"; 
	 %>
		<tr class="<%=stilo3%>">
		  <td align="center"><%=i+1%></td>
		  
		  <td><%=bean102.getIiddocto(i,6)%></td>	
		  <td><%=bean102.getIidcliente(i,6)%></td>
		  <td><%=bean102.getNombrecliente(i,6)%></td>
			 
		  <td align="right"><%=bean102.getDecimporte(i,6)%></td>		  
  		<% TotalImporteCargos = TotalImporteCargos + bean102.getDecimporte(i,6); %>
  			  
		  <td align="right"><%=bean102.getDecdesc(i,6)%></td>
		  <% TotalDescuentoCargos= TotalDescuentoCargos + bean102.getDecdesc(i,6); %>				     			  			  
   			  
		  <td align="right"><%=bean102.getDeciva(i,6)%></td>
		  <% TotalIvaCargos = TotalIvaCargos + bean102.getDeciva(i,6);%>  			  			  
  			  
		  <td align="right"><%=bean102.getDectotal(i,6)%></td>			  			  			  
		  <% TotalTotalCargos = TotalTotalCargos + bean102.getDectotal(i,6); %>			  
			  
		</tr>
	<%}%>
		
	<tr class="tititemStyle"> 
		<td colspan="8">
			
		</td>
	</tr>
	<tr>
		<td align="center" colspan="4">Totales Cargos</td>
		
			
		<td align="right">Importe: <%=TotalImporteCargos%></td>
		<td align="right">Descuento: <%=TotalDescuentoCargos%></td>
		<td align="right">IVA:  <%=TotalIvaCargos%></td>
		<td align="right">Total: <%=TotalTotalCargos%> </td>			  			  			  
			
	</tr>
	</table>
	<BR>
	
	<table width=750 align="center" border="1" bordercolor="#364859" rules="cols" cellspacing="0" width="750" style="border-color:Black;border-width:1px;border-style:solid;font-family: arial; font-size: 11px;border-collapse:collapse;" class="tablatext">	
	    <TR class="titStyle"> 
	      <TD align="center" colSpan=8> 
	          <input type=submit value="Imprimir" class="body" onClick="return Accion_onclick(3)">
	      </TD>
	    </TR>		
	</table>
<%}%>
</form>
</body>
</html>