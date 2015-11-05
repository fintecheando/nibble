if(typeof(loc)=="undefined"||loc==""){var loc="";if(document.body&&document.body.innerHTML){var tt=document.body.innerHTML.toLowerCase();var last=tt.indexOf("pvacr.js\"");if(last>0){var first=tt.lastIndexOf("\"",last);if(first>0&&first<last)loc=document.body.innerHTML.substr(first+1,last-first-1);}}}

var bd=0
document.write("<style type=\"text/css\">");
document.write("\n<!--\n");
document.write(".pvacr_menu {border-color:black;border-style:solid;border-width:"+bd+"px 0px "+bd+"px 0px;background-color:#003366;position:absolute;left:0px;top:0px;visibility:hidden;}");
document.write("a.pvacr_plain:link, a.pvacr_plain:visited{text-align:left;background-color:#003366;color:#ffffff;text-decoration:none;border-color:black;border-style:solid;border-width:0px "+bd+"px 0px "+bd+"px;padding:2px 0px 2px 0px;cursor:hand;display:block;font-size:7pt;font-family:Arial, Helvetica, sans-serif;}");
document.write("a.pvacr_plain:hover, a.pvacr_plain:active{background-color:#cccccc;color:#000000;text-decoration:none;border-color:black;border-style:solid;border-width:0px "+bd+"px 0px "+bd+"px;padding:2px 0px 2px 0px;cursor:hand;display:block;font-size:7pt;font-family:Arial, Helvetica, sans-serif;}");
document.write("\n-->\n");
document.write("</style>");

var fc=0x000000;
var bc=0xcccccc;
if(typeof(frames)=="undefined"){var frames=0;}

startMainMenu("pvacr_left.gif",15,12,2,0,0)
mainMenuItem("pvacr_b1",".gif",15,84,"javascript:;","","PVA 1.0 Sistema",2,2,"pvacr_plain");
mainMenuItem("pvacr_b2",".gif",15,93,"javascript:;","","PVA 9.0 Mostrador",2,2,"pvacr_plain");
mainMenuItem("pvacr_b3",".gif",15,74,"javascript:;","","PVA 10.0 Caja",2,2,"pvacr_plain");
mainMenuItem("pvacr_b4",".gif",15,86,"javascript:;","","PVA 11.0 Crédito",2,2,"pvacr_plain");
endMainMenu("pvacr_right.gif",15,12)

startSubmenu("pvacr_b4","pvacr_menu",132);
submenuItem("PVA 11.1 Bonificaciones","javascript:;","","pvacr_plain");
submenuItem("PVA 11.2 Notas de Cargo","javascript:;","","pvacr_plain");
submenuItem("PVA 11.3 Estados de cuenta","javascript:;","","pvacr_plain");
endSubmenu("pvacr_b4");

startSubmenu("pvacr_b3","pvacr_menu",80);
submenuItem("PVA 10.1 Pagos","javascript:;","","pvacr_plain");
endSubmenu("pvacr_b3");

startSubmenu("pvacr_b2","pvacr_menu",106);
submenuItem("PVA 9.1 Facturar","javascript:;","","pvacr_plain");
submenuItem("PVA 9.2 Devoluciones","javascript:;","","pvacr_plain");
submenuItem("PVA 9.3 Cambios","javascript:;","","pvacr_plain");
endSubmenu("pvacr_b2");

startSubmenu("pvacr_b1","pvacr_menu",104);
submenuItem("PVA 1.1 Salir Sistema","Logout","_self","pvacr_plain");
endSubmenu("pvacr_b1");

loc="";
