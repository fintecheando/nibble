package org.nibble.util;

/**
 * Insert the type's description here.
 * Creation date: (16/12/2002 08:32:07 p.m.)
 * @author: 
 */
import java.awt.Color;

import com.lowagie.text.*;
 
public class Celda {

	private Chunk chunk;
    private Cell cell;

/**
 * Celda constructor comment.
 */
public Celda(String txt, Font fontHeader, Color bgcolor, int Valign) {
	cell = new Cell();
	chunk = new Chunk(txt,fontHeader);
	
    cell.add(chunk);
    switch (Valign){
	    case 0:
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);	    
	    		break;
	    case 1:
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);	    
	    		break;
	    case 2:	
	    		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
	    		break;
    }
  	cell.setBackgroundColor(bgcolor);

}
/**
 * Insert the method's description here. 
 * @return com.lowagie.text.Cell
 */
public Cell getCell() {
	return this.cell;
}

/**
 * Celda constructor comment.
 */
public Celda(String txt, Font fontHeader, Color bgcolor, int Valign, int colspan) {
	cell = new Cell();
	chunk = new Chunk(txt,fontHeader);
	
    cell.add(chunk);

    cell.setColspan(colspan);
    switch (Valign){
	    case 0:
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);	    
	    		break;
	    case 1:
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);	    
	    		break;
	    case 2:	
	    		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
	    		break;
    }
  	cell.setBackgroundColor(bgcolor);

}
}
