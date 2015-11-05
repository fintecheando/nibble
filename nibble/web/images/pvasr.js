if(typeof(loc)=="undefined"||loc==""){var loc="";if(document.body&&document.body.innerHTML){var tt=document.body.innerHTML.toLowerCase();var last=tt.indexOf("pvasr.js\"");if(last>0){var first=tt.lastIndexOf("\"",last);if(first>0&&first<last)loc=document.body.innerHTML.substr(first+1,last-first-1);}}}

var bd=0
document.write("<style type=\"text/css\">");
document.write("\n<!--\n");
document.write(".pvasr_menu {border-color:black;border-style:solid;border-width:"+bd+"px 0px "+bd+"px 0px;background-color:#003366;position:absolute;left:0px;top:0px;visibility:hidden;}");
document.write("a.pvasr_plain:link, a.pvasr_plain:visited{text-align:left;background-color:#003366;color:#ffffff;text-decoration:none;border-color:black;border-style:solid;border-width:0px "+bd+"px 0px "+bd+"px;padding:2px 0px 2px 0px;cursor:hand;display:block;font-size:7pt;font-family:Arial, Helvetica, sans-serif;}");
document.write("a.pvasr_plain:hover, a.pvasr_plain:active{background-color:#cccccc;color:#000000;text-decoration:none;border-color:black;border-style:solid;border-width:0px "+bd+"px 0px "+bd+"px;padding:2px 0px 2px 0px;cursor:hand;display:block;font-size:7pt;font-family:Arial, Helvetica, sans-serif;}");
document.write("\n-->\n");
document.write("</style>");

var fc=0x000000;
var bc=0xcccccc;
if(typeof(frames)=="undefined"){var frames=0;}

startMainMenu("pvasr_left.gif",15,12,2,0,0)
mainMenuItem("1",".gif",15,84,"javascript:;","","PVA 1.0 Sistema",2,2,"pvasr_plain");
mainMenuItem("2",".gif",15,93,"javascript:;","","PVA 2.0 Seguridad",2,2,"pvasr_plain");
mainMenuItem("3",".gif",15,113,"javascript:;","","PVA 3.0 Administración",2,2,"pvasr_plain");
mainMenuItem("4",".gif",15,84,"javascript:;","","PVA 4.0 Clientes",2,2,"pvasr_plain");
mainMenuItem("5",".gif",15,97,"javascript:;","","PVA 8.0 Inventarios",2,2,"pvasr_plain");
mainMenuItem("6",".gif",15,93,"javascript:;","","PVA 9.0 Mostrador",2,2,"pvasr_plain");
mainMenuItem("7",".gif",15,74,"javascript:;","","PVA 10.0 Caja",2,2,"pvasr_plain");
mainMenuItem("8",".gif",15,86,"javascript:;","","PVA 11.0 Crédito",2,2,"pvasr_plain");
endMainMenu("pvasr_right.gif",15,12)

startSubmenu("8","pvasr_menu",139);
submenuItem("PVA 11.1 Bonificaciones","PreBonifica?accion=1","","pvasr_plain");
submenuItem("PVA 11.2 Notas de Cargo","NotaCargo?accion=1","","pvasr_plain");
submenuItem("PVA 11.3 Estados de cuenta","EstadoCuenta?accion=1","","pvasr_plain");
submenuItem("PVA 11.4 Antiguedad de saldos","AntiguedadSaldos","","pvasr_plain");
submenuItem("PVA 11.5 Aviso de Ingreso","AvisoIngreso?accion=1","","pvasr_plain");
submenuItem("PVA 11.6 Aplicar Ingresos","AplicaIngreso?accion=1","","pvasr_plain");
endSubmenu("8");

startSubmenu("7","pvasr_menu",139);
submenuItem("PVA 10.1 Pagos","Pagos?accion=1","","pvasr_plain");
submenuItem("PVA 10.2 Corte de Caja","CorteCaja?accion=1","","pvasr_plain");
submenuItem("PVA 10.3 Reporte de Ingresos","ReporteIngresos?accion=1","","pvasr_plain");
endSubmenu("7");

startSubmenu("6","pvasr_menu",106);
submenuItem("PVA 9.1 Facturar","Facturar","","pvasr_plain");
submenuItem("PVA 9.2 Devoluciones","Devoluciones","","pvasr_plain");
submenuItem("PVA 9.3 Cambios","Cambios","","pvasr_plain");
endSubmenu("6");

startSubmenu("5","pvasr_menu",195);
submenuItem("PVA 8.1 Cierre Diario","javascript:;","","pvasr_plain");
submenuItem("PVA 8.2 Consulta de movimientos","ConsultaMovimientosInventario","","pvasr_plain");
submenuItem("PVA 8.3 Ajuste físico","AjusteFisico","","pvasr_plain");
submenuItem("PVA 8.4 Valuación de Inventarios","javascript:;","","pvasr_plain");
submenuItem("PVA 8.5 Registrar histórico de consumos","javascript:;","","pvasr_plain");
submenuItem("PVA 8.6 Dias de Inventario en almacén","DiasInventario","","pvasr_plain");
submenuItem("PVA 8.7 Reporte de toma de inventario","javascript:;","","pvasr_plain");
submenuItem("PVA 8.8 Reporte de existencias","PreReporteInventario?action=ReporteExistencias&imagen=images/tpva8_8.GIF&title=PVA 8.8 Reporte de Existencias","","pvasr_plain");
submenuItem("PVA 8.9 Reporte de Valuación de inventario","javascript:;","","pvasr_plain");
endSubmenu("5");

startSubmenu("4","pvasr_menu",234);
submenuItem("PVA 4.1 Alta de Clientes","Clientes","","pvasr_plain");
submenuItem("PVA 4.2 Cambios a clientes y condiciones de crédito","Clientes?accion=3","","pvasr_plain");
submenuItem("PVA 4.4 Reporte de Clientes","PreReporteClientes","","pvasr_plain");
endSubmenu("4");

startSubmenu("3","pvasr_menu",113);
submenuItem("PVA 3.1 Configuración","Configuracion?accion=0","","pvasr_plain");
submenuItem("PVA 3.2 Promociones","Promociones?accion=0","","pvasr_plain");
endSubmenu("3");

startSubmenu("2","pvasr_menu",93);
submenuItem("PVA 2.2 Usuarios","Usuarios?accion=1","_self","pvasr_plain");
endSubmenu("2");

startSubmenu("1","pvasr_menu",104);
submenuItem("PVA 1.1 Salir Sistema","Logout","_self","pvasr_plain");
endSubmenu("1");

loc="";
