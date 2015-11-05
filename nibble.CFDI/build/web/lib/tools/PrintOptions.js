// jsSQL Tools
//
// jsSQL Framework related tools
//
// - PrintOptions( aTable, value_field, label_field [, aTarget] )
//
// written in JavaScript1.2
// tested with IE 4.0 and NS 4.6
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
// example of use:
//
//
//   <script src="tools/PrintOptions.js" type="text/javascript"></script>
//
//   <select onChange="myfunct()">
//     <option value="">Select an option</option>
//     <script type="text/javascript">
//     <!--
//     PrintOptions( t, "Quartiere", "Quartiere" );
//     //-->
//     </script>
//   </select>
//
//
//
// Note: it needs to know the const (var) HEADROWS specified, so it has to be specified
//       if not imported from the jsSQL Engine
//
//
// Known issues:
//
//
// Versions:
//
// - PrintOptions( aTable, value_field, label_field [, aTarget] ) rel 1.0 (may 2000)
//
////////////////////////////////////////////////////////////////////////////////////////


var PrintOptions_ver = "rel 1.0"; // const
var PrintOptions_EngineCompliance = "jsSQL Engine rel 1.0 compliant"; // const

// not tested with a target
//
// prototype: PrintOptions( aTable, value_field, label_field [, aTarget] )
//       
// fills a <select> writing <option>s with couples of "value" and "label"
//
//       aTable:  obbligatory
//                the jsSQL table you want to print
//
//       value_field:  obbligatory
//                String. Name of the columns field containing the option values
//
//       label_field:  obbligatory
//                String. Name of the columns field containing the option labels
//
//       aTarget: facoltative
//                "frame.document" over wich the function will call write() and writeln()
//                It has to be specified as a string because of compatibility issues.
//                It has to be specified relative to the document including the *.js file
//                containing the definition of the PrintTable() function.
//
function PrintOptions( aTable, value_field, label_field, aTarget )
{
  // (EN) Important: here have to be defined all the local variables not defined elsewhere
  // (IT) Importante: qui vanno definite tutte le variabili locali non definite altrove
  var i, v, l;
  var doc;
  var nfields;
 
  if (aTarget=="" || aTarget==null)
    doc = document;
  else
    doc = eval(aTarget);

  nfields = aTable[0].length;

  // finds the index of the "value_field" field
  for (i=0; i < nfields; i++)
  {
    if (aTable[0][i]==value_field)
    {
      v = i;
      break;
    }
    // now the variable 'v' contains the integer index of the 'value_field' field
  }
  // finds the index of the "label_field" field
  for (i=0; i < nfields; i++)
  {
    if (aTable[0][i]==label_field)
    {
      l = i;
      break;
    }
    // now the variable 'l' contains the integer index of the 'label_field' field
  }
 
 
  for (i=HEADROWS; i < aTable.length; i++)
  {
     document.write("<option value=\"");
     document.write(aTable[i][v]);
     document.writeln("\">");
     document.write(aTable[i][l]);
     document.writeln("</option>");
  }

}