if(typeof(loc)=="undefined"||loc==""){var loc="";if(document.body&&document.body.innerHTML){var tt=document.body.innerHTML.toLowerCase();var last=tt.indexOf("pvave.js\"");if(last>0){var first=tt.lastIndexOf("\"",last);if(first>0&&first<last)loc=document.body.innerHTML.substr(first+1,last-first-1);}}}

var bd=0
document.write("<style type=\"text/css\">");
document.write("\n<!--\n");
document.write(".pvave_menu {border-color:black;border-style:solid;border-width:"+bd+"px 0px "+bd+"px 0px;background-color:#003366;position:absolute;left:0px;top:0px;visibility:hidden;}");
document.write("a.pvave_plain:link, a.pvave_plain:visited{text-align:left;background-color:#003366;color:#ffffff;text-decoration:none;border-color:black;border-style:solid;border-width:0px "+bd+"px 0px "+bd+"px;padding:2px 0px 2px 0px;cursor:hand;display:block;font-size:7pt;font-family:Arial, Helvetica, sans-serif;}");
document.write("a.pvave_plain:hover, a.pvave_plain:active{background-color:#cccccc;color:#000000;text-decoration:none;border-color:black;border-style:solid;border-width:0px "+bd+"px 0px "+bd+"px;padding:2px 0px 2px 0px;cursor:hand;display:block;font-size:7pt;font-family:Arial, Helvetica, sans-serif;}");
document.write("\n-->\n");
document.write("</style>");

var fc=0x000000;
var bc=0xcccccc;
if(typeof(frames)=="undefined"){var frames=0;}

startMainMenu("pvave_left.gif",15,12,2,0,0)
mainMenuItem("pvave_b1",".gif",15,84,"","","PVA 1.0 Sistema",2,2,"pvave_plain");
mainMenuItem("pvave_b2",".gif",15,93,"","","PVA 9.0 Mostrador",2,2,"pvave_plain");
endMainMenu("pvave_right.gif",15,12)

startSubmenu("pvave_b2","pvave_menu",106);
submenuItem("PVA 9.1 Facturar","javascript:;","","pvave_plain");
submenuItem("PVA 9.2 Devoluciones","javascript:;","","pvave_plain");
submenuItem("PVA 9.3 Cambios","javascript:;","","pvave_plain");
endSubmenu("pvave_b2");

startSubmenu("pvave_b1","pvave_menu",104);
submenuItem("PVA 1.1 Salir Sistema","Logout","_self","pvave_plain");
endSubmenu("pvave_b1");

loc="";
