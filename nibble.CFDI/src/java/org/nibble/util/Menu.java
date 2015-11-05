package org.nibble.util;

/**
 * Insert the type's description here. 
 * @author: 
 */
//import dbbeans.menu.*;
import dbbeans.menu.dbMENU;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;

import org.jdom.Element;
import org.jdom.output.XMLOutputter;

public class Menu {
	private java.util.Vector vctOpciones;
/**
 * Insert the method's description here. 
 */
public Menu() {

	vctOpciones = new java.util.Vector();	
}
/**
 * Insert the method's description here. 
 * @return java.util.Vector
 */
public java.util.Vector getOpciones() {
	return vctOpciones;
}
/**
 * Insert the method's description here. 
 * @param newOpciones java.util.Vector
 */
public void setOpciones(java.util.Vector newOpciones) {
	vctOpciones = newOpciones;
}
/**
 * Insert the method's description here. 
 * @return java.lang.String
 */
public String toString() {

	int nivel = 1, nnivel;
	int smenu = 0, nsmenu;
	

	
	String menu = "";
	menu += "startMainMenu(\"left.gif\",15,12,2,0,0)\n";

	for (int i=0 ; i < vctOpciones.size(); i++){
		dbMENU a = (dbMENU)vctOpciones.elementAt(i);
		nnivel = a.getNivel();
		nsmenu = a.getSmenu();

		if (nnivel != nivel) {
			menu += "\t\t\t endMainMenu(\"right.gif\",15,12)\n\n";
			nivel = nnivel;
		}

		if (nsmenu != smenu){
			if(smenu != 0) // Nivel 1
				menu += "\t\t\t endSubmenu(\""+smenu+"\");\n\n";
			menu += "\t\t\t startSubmenu(\""+nsmenu+"\",\"menu\","+a.getAncho()+");\n";
			smenu = nsmenu;
		}

		switch(nnivel){
		case 1:
				menu +="\t\t\t mainMenuItem(\""+a.getImagen()+"\",\".gif\",15,"+a.getAncho()+",\"javascript:;\",\"\",\""+a.getDescripcion()+"\",2,2,\"plain\");\n";
				break;
		case 2:
				menu +="\t\t\t submenuItem(\""+a.getDescripcion()+"\",\""+a.getUrl()+"\",\"_self\",\"plain\");\n";
				break;	
		}

	}
	menu += "\t\t\t endSubmenu(\""+smenu+"\");\n\n";
	
	return menu;
}

public String headerToXMLString() {

    String menu="";
    Element root = new Element( "div" );
    root.setAttribute("id","ddtopmenubar");
    root.setAttribute("class","mattblackmenu");
    // Add a new HEADER
    Element newHeader= new Element( "ul" );
    // Add a new MENU
    for (int i=0 ; i < vctOpciones.size(); i++){
        dbMENU a = (dbMENU)vctOpciones.elementAt(i);
        Element newMenu = new Element("li");
        Element newRef = new Element( "a" );
        if(a.getNivel()==1){
            newRef.setAttribute("href","");
            newRef.setAttribute("rel","ddsubmenu"+a.getIidmenu());
            newRef.setText(a.getDescripcion());
            newMenu.addContent(newRef);
            newHeader.addContent(newMenu);
        }
    }
    root.addContent(newHeader);
    XMLOutputter outputter = new XMLOutputter();
    menu = outputter.outputString(root);
    return menu;
}

public String menusToXMLString() {

    String menu="";
    //logger.info("SE CREA EL XML PARA LOS MENUS");
    XMLOutputter outputter = new XMLOutputter();
    // Add a new HEADER
    Element rootMenu = new Element( "menu" );
    Hashtable ht = new Hashtable();
    Hashtable htmenu = new Hashtable();
    //LOOP FOR ADDING SUB NODES
    for (int i=0 ; i < vctOpciones.size(); i++){
        dbMENU a = (dbMENU)vctOpciones.elementAt(i);
        // Add SUBMENU
        Element newSubUl= new Element( "ul" );
        Element newSubLi = new Element("li");
        Element newSubA = new Element( "a" );

        String id= "ddsubmenu"+new Short(a.getSmenu()).toString();
        //logger.info("LA LLAVE A BUSCAR EN EL HASHTBALE ES "+id);
        Element elemento = (Element)ht.get(id);

        if(a.getNivel()==3){
//            logger.info("NIVEL 3 ES "+a.getUrl()+" PARA EL MENU "+a.getIidmenu()+ " HIJO DE " +a.getSmenu() + " DESCRIPCION "+ a.getDescripcion());
//            logger.info("LA LLAVE A BUSCAR EN EL HASHTBALE ES NIVEL 3 "+id);
            //Element smenu = (Element)ht.get(id);
            elemento = (Element)ht.get(id);
            if(elemento!=null)
            {
                //logger.info("NO NULO SE COLOCA EL HIJO A "+ elemento.getText() +" CON ID "+id);
                newSubA.setAttribute("href",a.getUrl());
                newSubA.setText(a.getDescripcion());
                //logger.info("newSubA "+outputter.outputString(newSubA));
                newSubLi.addContent(newSubA);
                //logger.info("newSubLi "+outputter.outputString(newSubLi));
                elemento.addContent(newSubLi);
                //logger.info("elemento "+outputter.outputString(elemento));
                ht.put(id, elemento);
            }
            else{
                //logger.info("NULO SE COLOCA EL HIJO A CON ID "+id);
                newSubA.setAttribute("href",a.getUrl());
                newSubA.setText(a.getDescripcion());
                //logger.info("newSubA "+outputter.outputString(newSubA));
                newSubLi.addContent(newSubA);
                //logger.info("newSubLi "+outputter.outputString(newSubLi));
                newSubUl.addContent(newSubLi);
                //logger.info("newSubUl "+outputter.outputString(newSubUl));
                ht.put(id, newSubUl);
            }
        }
    }
    //logger.info("*************************************************************************");
    //LOOP FOR ADDING NODES
   for (int i=0 ; i < vctOpciones.size(); i++){
        dbMENU a = (dbMENU)vctOpciones.elementAt(i);
        Element newUl= new Element( "ul" );
        Element newLi = new Element("li");
        Element newA = new Element( "a" );
        if(a.getNivel()==2){
            // Add a new HEADER


            String id= "ddsubmenu"+new Short(a.getSmenu()).toString();
            String subId= "ddsubmenu"+new Integer(a.getIidmenu()).toString();
            Element elemento = (Element)ht.get(id);
            if(elemento!=null)
            {
                //logger.info("NO ES NULO LA REFERENCIA ES "+a.getUrl()+" PARA EL MENU "+a.getIidmenu()+ " DESCRIPCION "+ a.getDescripcion());
                Element elementoLi = (Element)ht.get(subId);
                if(elementoLi!=null)
                {
                    //logger.info("SE AGREGA EL SUBELEMENTO "+subId);
                    newLi.addContent(elementoLi);
                }
                newA.setAttribute("href",a.getUrl());
                newA.setText(a.getDescripcion());
                newLi.addContent(newA);
                elemento.addContent(newLi);
                //logger.info("elemento "+outputter.outputString(elemento) +" ID "+ id);
                htmenu.put(id, elemento);
                ht.put(id, elemento);
            }
            else{
                //logger.info("ES NULO LA REFERENCIA ES "+a.getUrl()+" PARA EL MENU "+a.getIidmenu()+ " DESCRIPCION "+ a.getDescripcion());
                Element elementoLi = (Element)ht.get(subId);
                if(elementoLi!=null)
                {
                    //logger.info("SE AGREGA EL SUBELEMENTO "+subId);
                    newLi.addContent(elementoLi);
                }
                newUl.setAttribute("id",id);
                newUl.setAttribute("class","ddsubmenustyle");
                newA.setAttribute("href",a.getUrl());
                newA.setText(a.getDescripcion());
                newLi.addContent(newA);
                newUl.addContent(newLi);
                //logger.info("newUl "+outputter.outputString(newUl) +" ID "+ id);
                htmenu.put(id, newUl);
                ht.put(id, newUl);
            }
        }

    }
    //rootMenu.addContent(newUl);
    String str;
    Set<String> set = htmenu.keySet();
    Iterator<String> itr = set.iterator();

    while (itr.hasNext()) {
      str = itr.next();
      rootMenu.addContent((Element)htmenu.get(str));
    }
    menu = outputter.outputString(rootMenu);
    return menu;
}

public String headerToString() {

	int nivel = 1, nnivel;
	int smenu = 0, nsmenu;
        String xmlx = headerToXMLString();
	//logger.info("SE TRAJO DEL XML PARA LOS HEADERS "+xmlx);
        String xmlx1 = menusToXMLString();
        //logger.info("SE TRAJO DEL XML PARA LOS MENUS "+xmlx);
	String menu = xmlx + xmlx1;

	return menu;
}

}
