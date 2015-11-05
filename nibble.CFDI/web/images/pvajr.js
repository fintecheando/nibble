if(typeof(loc)=="undefined"||loc==""){var loc="";if(document.body&&document.body.innerHTML){var tt=document.body.innerHTML.toLowerCase();var last=tt.indexOf("pvajr.js\"");if(last>0){var first=tt.lastIndexOf("\"",last);if(first>0&&first<last)loc=document.body.innerHTML.substr(first+1,last-first-1);}}}

var bd=0
document.write("<style type=\"text/css\">");
document.write("\n<!--\n");
document.write(".pvajr_menu {border-color:black;border-style:solid;border-width:"+bd+"px 0px "+bd+"px 0px;background-color:#003366;position:absolute;left:0px;top:0px;visibility:hidden;}");
document.write("a.pvajr_plain:link, a.pvajr_plain:visited{text-align:left;background-color:#003366;color:#ffffff;text-decoration:none;border-color:black;border-style:solid;border-width:0px "+bd+"px 0px "+bd+"px;padding:2px 0px 2px 0px;cursor:hand;display:block;font-size:7pt;font-family:Arial, Helvetica, sans-serif;}");
document.write("a.pvajr_plain:hover, a.pvajr_plain:active{background-color:#cccccc;color:#000000;text-decoration:none;border-color:black;border-style:solid;border-width:0px "+bd+"px 0px "+bd+"px;padding:2px 0px 2px 0px;cursor:hand;display:block;font-size:7pt;font-family:Arial, Helvetica, sans-serif;}");
document.write("\n-->\n");
document.write("</style>");

var fc=0x000000;
var bc=0xcccccc;
if(typeof(frames)=="undefined"){var frames=0;}

startMainMenu("pvajr_left.gif",15,12,2,0,0)
mainMenuItem("pvajr_b1",".gif",15,84,"javascript:;","","PVA 1.0 Sistema",2,2,"pvajr_plain");
mainMenuItem("pvajr_b2",".gif",15,93,"javascript:;","","PVA 2.0 Seguridad",2,2,"pvajr_plain");
mainMenuItem("pvajr_b3",".gif",15,113,"javascript:;","","PVA 3.0 Administración",2,2,"pvajr_plain");
mainMenuItem("pvajr_b4",".gif",15,84,"javascript:;","","PVA 4.0 Clientes",2,2,"pvajr_plain");
mainMenuItem("pvajr_b5",".gif",15,97,"javascript:;","","PVA 8.0 Inventarios",2,2,"pvajr_plain");
mainMenuItem("pvajr_b6",".gif",15,93,"javascript:;","","PVA 9.0 Mostrador",2,2,"pvajr_plain");
mainMenuItem("pvajr_b7",".gif",15,74,"javascript:;","","PVA 10.0 Caja",2,2,"pvajr_plain");
mainMenuItem("pvajr_b8",".gif",15,86,"javascript:;","","PVA 11.0 Crédito",2,2,"pvajr_plain");
endMainMenu("pvajr_right.gif",15,12)

startSubmenu("pvajr_b8","pvajr_menu",132);
submenuItem("PVA 11.1 Bonificaciones","javascript:;","","pvajr_plain");
submenuItem("PVA 11.2 Notas de Cargo","javascript:;","","pvajr_plain");
submenuItem("PVA 11.3 Estados de cuenta","javascript:;","","pvajr_plain");
endSubmenu("pvajr_b8");

startSubmenu("pvajr_b7","pvajr_menu",139);
submenuItem("PVA 10.1 Pagos","javascript:;","","pvajr_plain");
submenuItem("PVA 10.2 Corte de Caja","javascript:;","","pvajr_plain");
submenuItem("PVA 10.3 Reporte de Ingresos","javascript:;","","pvajr_plain");
endSubmenu("pvajr_b7");

startSubmenu("pvajr_b6","pvajr_menu",106);
submenuItem("PVA 9.1 Facturar","javascript:;","","pvajr_plain");
submenuItem("PVA 9.2 Devoluciones","javascript:;","","pvajr_plain");
submenuItem("PVA 9.3 Cambios","javascript:;","","pvajr_plain");
endSubmenu("pvajr_b6");

startSubmenu("pvajr_b5","pvajr_menu",195);
submenuItem("PVA 8.1 Cierre Diario","javascript:;","","pvajr_plain");
submenuItem("PVA 8.2 Consulta de movimientos","javascript:;","","pvajr_plain");
submenuItem("PVA 8.7 Reporte de toma de inventario","javascript:;","","pvajr_plain");
submenuItem("PVA 8.8 Reporte de existencias","javascript:;","","pvajr_plain");
submenuItem("PVA 8.9 Reporte de Valuación de inventario","javascript:;","","pvajr_plain");
endSubmenu("pvajr_b5");

startSubmenu("pvajr_b4","pvajr_menu",234);
submenuItem("PVA 4.1 Alta de Clientes","javascript:;","","pvajr_plain");
submenuItem("PVA 4.2 Cambios a clientes y condiciones de crédito","javascript:;","","pvajr_plain");
submenuItem("PVA 4.4 Reporte de Clientes","javascript:;","","pvajr_plain");
endSubmenu("pvajr_b4");

startSubmenu("pvajr_b3","pvajr_menu",113);
submenuItem("PVA 3.1 Configuración","javascript:;","","pvajr_plain");
submenuItem("PVA 3.2 Promociones","javascript:;","","pvajr_plain");
endSubmenu("pvajr_b3");

startSubmenu("pvajr_b2","pvajr_menu",93);
submenuItem("PVA 2.2 Usuarios","Usuarios?accion=1","_self","pvajr_plain");
endSubmenu("pvajr_b2");

startSubmenu("pvajr_b1","pvajr_menu",104);
submenuItem("PVA 1.1 Salir Sistema","Logout","_self","pvajr_plain");
endSubmenu("pvajr_b1");

loc="";
