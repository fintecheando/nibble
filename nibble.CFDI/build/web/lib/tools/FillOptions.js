// jsSQL Tools
//
// jsSQL Framework related tools
//
// - FillOptions( aTable, aSel, vfield, tfield, leavefirst )
//
// written in JavaScript1.2
//
// conceived and first realized by:
//
//    Federico Sasso
//    Via P.Nenni, 3/2
//    Genova - Italy
//    federicosasso@technologist.com
//
//
// Author notes:
//
//   fills a <select> writing <option>s with couples of "value" and "label"
//
// Note: it needs to know the const (var) HEADROWS specified, so it has to be specified
//       if not imported from the jsSQL Engine
//
// Known issues:
//   NS4.x needs an history.go(0) refresh to display more than one option when pulling
//   down after filling. This is obtained with an "if (document.layers)" (NS6 does not
//   suffer this problem). This causes the page to be reprinted, and can take long when
//   it uses several "document.write()" due to another (!) notorious NS4.x bug.
//   If this bugs you more than the single options pulldown with mini scrollbar, please
//   do comment the line "if (document.layers) history.go(0);" at the end of the function
//
// Versions:
//
// - FillOptions( aTable, aSel, vfield, tfield, leavefirst ) rel 1.0 (dec 2000)
//
////////////////////////////////////////////////////////////////////////////////////////


var FillOptions_ver = "rel 1.0"; // const
var FilltOptions_EngineCompliance = "jsSQL Engine rel 1.1 compliant"; // const

function FillOptions( aTable, aSel, vfield, tfield, leavefirst )
{
 var fv, ft;
 var l=aTable.length-HEADROWS;
 var opts=aSel.options;
 var vi, ti;
 vi=FillOptions_getFieldIndex(aTable,vfield);
 ti=FillOptions_getFieldIndex(aTable,tfield);
 if (vi==-1 || ti==-1) return false;
 if (opts.length>0)
 {
   fv=opts[0].value;
   ft=opts[0].text;
 }
 else if (!leavefirst)
   fv=ft="";
 DelOptions( aSel )
 if (leavefirst)
 {
   opts.length=1;
   opts[0]=new Option(ft,fv,true,true);
 }
 var i=start=0;
 if (leavefirst) start=1;
 opts.length+=l;
 while (i<l)
 {
   opts[i+start]=new Option(aTable[i+HEADROWS][ti],aTable[i+HEADROWS][vi]);
   i++;
 }
 if (document.layers) history.go(0);
}

function DelOptions( aSel )
{
 var opts=aSel.options;
 var l=opts.length;
 var i=0;
 while (i<l)
   opts[i++]=null;
 opts.length = 0;
}


//                                  Table,  String
function FillOptions_getFieldIndex( aTable, aField ) // as Integer
{
  var i, found;

  i=0;
  found=false;
  while ( i<aTable[0].length && !found)
  {
    if (aTable[0][i]==aField)
       found=true;   // i is index of the "aField" field in aTable
    else
       i++;
  }
 
  if (found)
     return i;
  else
  {
     if (jsSQL_Engine_DEBUG)
        alert ("Error: field named \'" + aField + "\' not found.")
     return -1; // error: Field not found
  }
}
