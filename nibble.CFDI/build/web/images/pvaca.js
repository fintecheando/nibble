if(typeof(loc)=="undefined"||loc==""){var loc="";if(document.body&&document.body.innerHTML){var tt=document.body.innerHTML.toLowerCase();var last=tt.indexOf("pvaca.js\"");if(last>0){var first=tt.lastIndexOf("\"",last);if(first>0&&first<last)loc=document.body.innerHTML.substr(first+1,last-first-1);}}}

var bd=0
document.write("<style type=\"text/css\">");
document.write("\n<!--\n");
document.write(".pvaca_menu {border-color:black;border-style:solid;border-width:"+bd+"px 0px "+bd+"px 0px;background-color:#003366;position:absolute;left:0px;top:0px;visibility:hidden;}");
document.write("a.pvaca_plain:link, a.pvaca_plain:visited{text-align:left;background-color:#003366;color:#ffffff;text-decoration:none;border-color:black;border-style:solid;border-width:0px "+bd+"px 0px "+bd+"px;padding:2px 0px 2px 0px;cursor:hand;display:block;font-size:7pt;font-family:Arial, Helvetica, sans-serif;}");
document.write("a.pvaca_plain:hover, a.pvaca_plain:active{background-color:#cccccc;color:#000000;text-decoration:none;border-color:black;border-style:solid;border-width:0px "+bd+"px 0px "+bd+"px;padding:2px 0px 2px 0px;cursor:hand;display:block;font-size:7pt;font-family:Arial, Helvetica, sans-serif;}");
document.write("\n-->\n");
document.write("</style>");

var fc=0x000000;
var bc=0xcccccc;
if(typeof(frames)=="undefined"){var frames=0;}

startMainMenu("pvaca_left.gif",15,12,2,0,0)
mainMenuItem("pvaca_b1",".gif",15,84,"javascript:;","","PVA 1.0 Sistema",2,2,"pvaca_plain");
mainMenuItem("pvaca_b2",".gif",15,74,"javascript:;","","PVA 10.0 Caja",2,2,"pvaca_plain");
endMainMenu("pvaca_right.gif",15,12)

startSubmenu("pvaca_b2","pvaca_menu",112);
submenuItem("PVA 10.1 Pagos","javascript:;","","pvaca_plain");
submenuItem("PVA 10.2 Corte de Caja","javascript:;","","pvaca_plain");
endSubmenu("pvaca_b2");

startSubmenu("pvaca_b1","pvaca_menu",104);
submenuItem("PVA 1.1 Salir Sistema","Logout","_self","pvaca_plain");
endSubmenu("pvaca_b1");

loc="";
