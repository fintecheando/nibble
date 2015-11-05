<html>
<jsp:useBean id="bean42" class="pva.beans4.Bean42" scope="session"/>
<jsp:useBean id="beanFac" class="pva.beans.BeanBuscaFactura" scope="session"/>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link href="lib/estilo.css" rel=STYLESHEET type=text/css>
<title><%=beanFac.getTitulo()%></title>

<script language="JavaScript" src="lib/calendar2.js"></script>

<script type="text/javascript" src="lib/util.js"></script>
<script language="javascript">

function Accion_onclick(valor){
	var errors = '';
 	var numErrors = 0;
	forma = document.frm;
	forma.accion.value = valor;
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
<body>
<form name=frm action="PreBonifica" method="POST">
<input type=hidden name="accion">
<table class="tablatext" width=750 align="center" cellpadding="0" cellspacing="0">
<tr>
	<td><img src="images/<%=beanFac.getImagen()%>"></td>
        
        <th>
	<td>
        <% if(beanFac.getImagen().matches("tpva11_1.GIF")) { %>    
            <a href="../pvatx/hlp/pva11.0-11.1.htm" target="Ayuda Bonificaciones">Ayuda</a>
        <% } else { %>
        
        <%}%>    
        </td>
        </th>

</tr>
</table>
<table align=center border=1 borderColor=#364859 cellSpacing=0 class=tablatext rules=cols style="BORDER-BOTTOM: black 1px solid; BORDER-COLLAPSE: collapse; BORDER-LEFT: black 1px solid; BORDER-RIGHT: black 1px solid; BORDER-TOP: black 1px solid; FONT-FAMILY: arial; FONT-SIZE: 11px" width=750>
    <tr> 
      <TD colSpan=3>Selección de Cliente</TD>
      <TD colSpan=2>Fecha de búsqueda</TD>
    </TR>
    <TR class="titStyle"> 
      <TD width="11%">Número</TD>
      <TD width="26%">Razón Social</TD>
      <TD width="17%">R.F.C</TD>
      <TD width="23%">Desde:</TD>
      <TD width="23%">Hasta:</TD>
    </TR>
    <TR> 
      <TD width="11%"> 
		<select name="idcliente" class="body" onChange="idcliente_onchange()">
			 <%for (int i=0; i<bean42.getClientesSize();i++){%>
			 <option value=<%= bean42.getId(i)%> <%if (beanFac.getCliente()==bean42.getId(i)){%> SELECTED <%}%>> <%= bean42.getId(i) %></option>
			 <%}%>
		</select>
      <TD width="26%"> 
		<select name="rscliente" class="body" onChange="rscliente_onchange()">
			 <%for (int i=0; i<bean42.getClientesSize();i++){%>
			 <option value=<%= bean42.getId(i)%> <%if (beanFac.getCliente()==bean42.getId(i)){%> SELECTED <%}%>><%= bean42.getRazonsocial(i) %></option>
			 <%}%>
		</select>
      </TD>
      <TD width="17%"> 
			<select name="rfcliente" class="body" onChange="rfcliente_onchange()">
			 <%for (int i=0; i<bean42.getClientesSize();i++){%>
			  <option value=<%= bean42.getId(i)%> <%if (beanFac.getCliente()==bean42.getId(i)){%> SELECTED <%}%>><%= bean42.getRfc(i) %></option>
			 <%}%>
			</select>  
      </TD>
      <TD width="23%">      		
      
        <input type="text" name=fini>
        <a href="javascript:cal5.popup();" onMouseOut="window.status='';return true;" onMouseOver="window.status='Seleccione Fecha';return true;"><img border=0 src="images/calendar.gif"></a>		
	  </TD>
      <TD width="23%"> 
        <input type="text" name=ffin>
        <a href="javascript:cal6.popup();" onMouseOut="window.status='';return true;" onMouseOver="window.status='Seleccione Fecha';return true;"><img border=0 src="images/calendar.gif"></a>		

	  </TD>
	  
<SCRIPT LANGUAGE="JavaScript">

			var cal5 = new calendar2(document.forms['frm'].elements['fini']);
			cal5.year_scroll = true;
			cal5.time_comp = false;
			
			var cal6 = new calendar2(document.forms['frm'].elements['ffin']);
			cal6.year_scroll = true;
			cal6.time_comp = false;					
		</SCRIPT>
	  
    </TR>
    <TR class="titStyle"> 
      <TD align="center" colSpan=5> 
          <input type=submit value="Buscar" class="body" name="submit" onClick="return Accion_onclick(2)">
      </TD>
    </TR>
  </TABLE>
<br>
<% if (beanFac.isDetalle()){%>
	<table width=750 align="center" border="1" bordercolor="#364859" rules="cols" cellspacing="0" width="750" style="border-color:Black;border-width:1px;border-style:solid;font-family: arial; font-size: 11px;border-collapse:collapse;" class="tablatext">
		<tr class="headerStyle">
			<td>Factura</td>
		  	<td>Emisión</td>
		  	<td>Vencimiento</td>
		  	<td>Importe</td>
			<td>I.V.A.</td>
			<td>Total</td>		
		</tr>	
		<% String stilo;
		   for (int i = 0; i < beanFac.getFacturasSize(); i++) {
	 		 if (i % 2 == 0)stilo = "itemStyle";
			 else stilo = "alternatingItemStyle"; 
		 %>
			<tr class="<%=stilo%>">
			  <td align="center"><a href="<%=beanFac.getLink()%>?idfactura=<%=beanFac.getIidfactura(i)%>&accion=1&idcliente=<%=request.getParameter("idcliente")%>" class="ligas"><%=beanFac.getIidfactura(i)%></a></td>
			  <td><%=beanFac.getTisfechafactura(i)%></td>	
			  <td><%=beanFac.getTisfechavencimient(i)%></td>
			  <td align="right"><%=beanFac.getDecsubtotal(i)%></td>
			  <td align="right"><%=beanFac.getDecivatotal(i)%></td>
			  <td align="right"><%=beanFac.getDectotal(i)%></td>
			</tr>
		<%}%>
	</table>
<%}%>
</form>
</body>
</html>