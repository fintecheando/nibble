<html>
<jsp:useBean id="bean111b" class="pva.beans11.Bean111b" scope="session"/>
<jsp:useBean id="bean111" class="pva.beans11.Bean111" scope="request"/>
<head>

<script language=JavaScript>
var  popen = false;
<%
System.out.println("Entrando aqui al if");
request.setAttribute("bean111",bean111);
if  (bean111b.isPopImprimir()) {
%>

popup=window.open("/pvatx/ImprimirBonificacion","","toolbar=0,location=0,directories=0,status=0,menubar=0,scrollbars=yes,resizable=yes,width=700,height=400");
popen = true;
<%}%>

function redirect(){
 document.forms[0].submit();
}
</script>

</head>
<BODY onLoad="redirect()">

<FORM name="forma" method="POST" ACTION="/pvatx/PreBonifica">
<INPUT TYPE=HIDDEN NAME="accion" value="1">
</FORM>

</BODY>

</html>