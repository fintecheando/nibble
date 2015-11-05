<html>
<head>
<title>PVA 9.4 Reporte Mensual SAT</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<meta http-equiv="Content-Style-Type" content="text/css">
<meta http-equiv="Content-Script-Type" content="text/javascript">
<link href="lib/estilo.css" rel=STYLESHEET type=text/css>
<script type="text/javascript" src="lib/util.js"></script>

<script language=JavaScript>
var  popen = false;

function Popup(){
 popup=window.open("","Popup",'toolbar=0,location=0,directories=0,status=0,menubar=0,scrollbars=yes,resizable=yes,width=700,height=400');
 popen =true;
}

</script>

<script type="text/javascript">

var today = new Date();
var y=today.getFullYear();
var m=today.getMonth();
var maxY=4;

function fillSel(yy){
var selM = document.getElementById('selm');
while(selM.hasChildNodes()){selM.removeChild(selM.childNodes[0])}
var lim = (Number(yy)==y)?m+1:12;
for(var i=0;i<lim;i++){
var opt = document.createElement('option');
var v=i+1;v=(v<10)?'0'+v:v;
//opt.text = v;
opt.value = v;
//opt.setAttribute('value',v);

opt.appendChild(document.createTextNode(v));
selM.appendChild(opt);
}
}

onload=function(){
    var selY = document.getElementById('sely');
    var year =y-maxY;
    for (var i=maxY; i>=0;i--){
        var opt = document.createElement('option');
        //opt.setAttribute('value',year);
        opt.value = year;
        opt.appendChild(document.createTextNode(year));
        i==0?opt.selected=true:null;
        selY.appendChild(opt);
        year++;
    }
    fillSel(selY.options[selY.options.selectedIndex].value)
    //fillSel(selM.options[selM.options.selectedIndex].value)
}
</script>

</head>
<%@ include file="../main.jsp" %>
<body bgcolor="#FFFFFF" text="#000000" onunload="if(popen)popup.close();">
<form name=frm action="ReporteMensualSAT" method="post">
<table class="tablatext" width=750 align="center" cellpadding="0" cellspacing="0">
<tr>
	<td><img src="images/tpva4_4.GIF"></td>

        <th>
	<td><a href="../pvatx/hlp/pva4.0-4.4.htm" target="Ayuda Reporte de Clientes">Ayuda</a></td>
        </th>

</tr>
</table>
<br>
<table align="center" border="1" bordercolor="#364859" rules="cols" cellspacing="0" width="450" style="border-color:Black;border-width:1px;border-style:solid;font-family: arial; font-size: 11px;border-collapse:collapse;" class="tablatext">
	<tr class="titStyle">
	    <td colspan="2" align="center">Seleccionar Periodo</td>
	</tr>	
        <tr class="itemStyle">
          <td width="50%">
              <input type="radio" name="periodo" value="1" checked>Mes Anterior
          </td>
          <td width="50%">
             <input type="radio" name="periodo" value="2">Seleccionar Mes y Año
             <br>
             <select name="selm" id="selm" ></select>
             <select name="sely" id="sely" onchange="fillSel(this.value)"></select>
          </td>
        </tr>
</table>
<br>
<table align="center" border="1" bordercolor="#364859" rules="cols" cellspacing="0" width="450" style="border-color:Black;border-width:1px;border-style:solid;font-family: arial; font-size: 11px;border-collapse:collapse;" class="tablatext">
<tr class="titStyle">
	<td align=center><input type="submit" value="Generar" class=body></td>
</tr>
</table>
</form>
</body>
</html>
