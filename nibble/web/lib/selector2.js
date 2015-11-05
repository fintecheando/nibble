<!--
var oLastRow=null;
	
function HighlightRow(oRow){
	if(oLastRow != oRow){
		DeselectRow();
	}	
	oRow.style.backgroundColor = '#004141';	
//	oLastRow=oRow.index;
//	alert(oRow.index);
}	
function DeselectRow(){
//	alert('AA');
	if (oLastRow)
		oLastRow.style.backgroundColor = '#FFFFFF';	
}
function SelectRow(oRow){
	oRow.style.backgroundColor = '#HAAAAA';
}
