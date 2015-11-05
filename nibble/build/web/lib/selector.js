<!--

var oPrevRow=0;
var oLastRow=0;
	
function HighlightRow(){
	window.event.cancelBubble=true;
	oRow = window.event.srcElement.parentElement;

	if(oLastRow && oLastRow != oRow){
		DeselectRow();
	}
	if(oRow.Typ){
		oRow.className = oRow.Typ + "On";
		oLastRow=oRow;
		}
	else {
		oLastRow = 0;
	}
}
		
function DeselectRow(){
	if (oLastRow.Typ == "Selected"){
		oLastRow.className = oLastRow.Typ;
	}
	else {
		oLastRow.className = oLastRow.Typ + "Off";
	}
}
    
function SelectRow(){
	oRow = window.event.srcElement.parentElement;
	
	var oRows = oRow.parentElement.parentElement.rows;
		
	if (oRow.tagName == "TR"){
		oPrevRow.Typ = "Sel";
		oPrevRow.className = oPrevRow.Typ + "Off";
		oPrevRow = oRow;
	}
	if (oRow.Typ == "Sel"){
		oRow.Typ ="Selected";
		oRow.className = oRow.Typ;
		
   
   if ( oRows )
   {
      var nRows = oRows.length;
      // Find the clicked row

      for ( var i=0; i<nRows; i++ )
      {
	  	 	  
         if ( oRows[i] == oRow )
         {   
		//	document.frm.temp.value = oRows.item(i).cells.item(0).innerText;		
            return;
         }
      }
   }
   

	}
}
