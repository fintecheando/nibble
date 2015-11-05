// jsSQL rel 1.2
//
// JavaScript Structured Query Language Engine
//
// - jsSQL  (JavaScript Structured Query Language Engine)
//
// written in JavaScript1.2
// tested with IE 4+ e NS 4.6+, NS6, Mozilla
//
// conceived and first realized by:
//
//    Federico Sasso
//    Via P.Nenni, 3/2
//    Genova - Italy
//    federicosasso@technologist.com
//
// Author's notes:
//
// The jsSQL Engine is the backbone of the the jsSQL Framework, that provides web
// authors a way to develop dynamic web pages that interact with small databases,
// thanks to a client-side programming framework - the jsSQL Framework - compatible
// with the most part of the browsers available at present.
// The jsSQL Engine is a library of functions implementing the engine of a reduced
// dialet of the Structured Query Language, the jsSQL, able to manage jsSQL tables.
// See the official web site of The jsSQL Project for further documentation.
//
// The engine now uses row[1] (field type), that does heavily effect the OrderBy()
// and Where() functions.
// Supported types at the moment are "String", "Integer", "Float", "Percent",
// "Boolean", "hh:nn:ss", "hh:nn", "dd/mm/yyyy", "mm/dd/yyyy", "URL", "e-mail",
// "Hyperlink" and "Image".
//
//
// Versions:
//
// - jsSQL  rel 1.2 (dec 2000)
// - jsSQL  rel 1.1 (nov 2000)
// - jsSQL  rel 1.0 (mar 2000)
// - jsSQL  rel 0.9 (mar 2000)
// - jsSQL  rel 0.1 (nov 1999)
// 
///////////////////////////////////////////////////////////////////////////////////




// IMPORTANT:
//
// All the service variables used inside the bodies of the framework functions
// have to explicitly be defined inside the functions themselves using the
// 'var' keyword.
// This to avoid that a variable that should be local to a function could override
// the value of a framework user's global variable with the same name.
// Besides it also significantly increases performances.

// GLOBAL VARS
// But the following few variables are defined outside the functions bodies and users
// have therefor to be careful not to override them, nor to change them.
// Unfortunatly JavaScript1.2 does not implement the 'const' keyword, even if it
// reserves it for future implementations.

var HEADROWS=2; // const, number of rows composing the header
                // used inside the jsSQL Framework
                //   row[0] fields names 
                //   row[1] fields types

var jsSQL_ver="rel 1.2.20010307"; // const, to update at every single change of the file

var jsSQL_Engine_DEBUG=false; // setting its value to true after linking the engine
                              // makes error traps alert


///////////////////////////////////////////////////////////////////////////////////
//                                 mare nostrum                                  //
///////////////////////////////////////////////////////////////////////////////////






// returns a copy of a jsSQL table or query.
// it is equivalent to the following SQL query:
//
//   aCopy = SQL( SELECT * FROM aTable )
//
// example of use:
//
//   aCopy = new Array();
//   aCopy = From( myTable );
//
function From(aTable)
{
 var i,j;
 var itsCopy=new Array();
 for(i=0;i<aTable.length;i++)
 {
  itsCopy[i]=new Array(aTable[0].length);
  for(j=0;j<aTable[0].length;j++)itsCopy[i][j]=aTable[i][j];
 }
 return itsCopy;
}





// at the moment the only compare operators ('op') available are
//   equal (default)       ("=")
//   not equal             ("<>")
//   minor than            ("<")
//   greater than          (">")
//   minor or equal than   ("<=")
//   greater or equal than (">=")
//   LIKE                  ("LIKE")
//   NOT LIKE              ("NOT LIKE")
//   BETWEEN               ("BETWEEN")
//   NOT BETWEEN           ("NOT BETWEEN")
//   IS NULL               ("IS NULL")
//   IS NOT NULL           ("IS NOT NULL")
//
// at the moment supported data types are
//   "String" (default)
//   "Integer"
//   "Float"
//   "Percent"
//   "dd/mm/yyyy"
//   "mm/dd/yyyy"
//   "Boolean"
//   "hh:nn:ss"
//   "hh:nn"
//   "URL"         //should work as "String"
//   "e-mail"      //should work as "String"
//   "Hyperlink"   //should work as "String"
//   "Image"       //should work as "String"
//
// returns a table having as its records the records of aTable where wfield=wvalue
//
// it is equivalent to the following SQL statement:
//
//   wTable = SQL( "SELECT * FROM aTable WHERE wfield " + op + "wvalue;" )
//
//   where 'op' is the choosen operator
//
// prototype:
//   Where( aTable, wfield, op, wvalue [, wvalue2] )
//
// examples of use:
//
//   wTable = new Array();
//   wTable = Where( cheques, "bank", "=", "American Bank" )
//   wTable = Where( cheques, "amount", ">", "150000" );
//   wTable = Where( cheques, "amount", ">=", "300000" );
//   wTable = Where( cheques, "amount", "<", "300000" );
//   wTable = Where( cheques, "amount", "<=", "300000" );
//   wTable = Where( cheques, "amount", "<>", "300000" );
//   wTable = Where( cheques, "bank", "LIKE", "/roma/i" );
//   wTable = Where( cheques, "bank", "NOT LIKE", "/roma/i" );
//   wTable = Where( cheques, "amount", "BETWEEN", "50000", "300000" );
//   wTable = Where( cheques, "amount", "NOT BETWEEN", "50000", "300000" );
//   wTable = Where( cheques, "bank", "IS NULL", "" );
//   wTable = Where( cheques, "bank", "IS NOT NULL", "" );
//   wTable = Where( authors, "author", "IN", inTable);
//   wTable = Where( authors, "author", "NOT IN", inTable);
//
// Note: when operator is "IN" or "NOT IN", the second operand is a jsSQL table, and NOT a string
//
// updated: 20001108 by fred - now uses sys_getFieldIndex
// updated: 20001111 by fred - now supports "IS NULL" and "IS NOT NULL" operators
// updated: 20001111 by fred - now supports "IN" and "NOT IN" operators
//
function Where(aTable,wfield,op,wvalue)
{
 var i,j,js_op,opnd1,opnd2,opnd3,tmpstr,checkstr;
 var nfields=aTable[0].length;
 var index=sys_getFieldIndex(aTable,wfield);

 var wTable=new Array();

 for(i=0;i<HEADROWS;i++) // copies the header
 {
  wTable[i]=new Array(nfields);
  for(j=0;j<nfields;j++)wTable[i][j]=aTable[i][j];
 }

 if(aTable.length>HEADROWS)
 {
  if(op!="IS NULL" && op!="IS NOT NULL" && op!="IN" && op!="NOT IN") // improves performances when condition is false
  {
   // here it differentiates based on the specified operand
   switch (aTable[1][index]) //checks data type
   {
    case "Boolean" :
     wvalue=wvalue.toLowerCase()
     if(wvalue=="true" || wvalue=="yes" || wvalue=="on" || wvalue=="1" || wvalue=="vero" || wvalue=="sì" || wvalue=="M")
       opnd2="1";
     else opnd2="0"
     break;
    case "String" :
     opnd2="wvalue";
     break;
    case "Integer" :
     opnd2="parseInt(wvalue)";
     break;
    case "Percent" : //handled as a "Float"
    case "Float" :
     opnd2="parseFloat(wvalue)";
     break;
    case "dd/mm/yyyy":
     opnd2="Date.UTC(wvalue.substring(6,10),wvalue.substring(3,5),wvalue.substring(0,2))";
     break;
    case "mm/dd/yyyy":
     opnd2="Date.UTC(wvalue.substring(6,10),wvalue.substring(2,0),wvalue.substring(3,5))";
     break;
    case "hh:nn:ss":
     opnd2="parseInt(((wvalue.substring(0,2)*60)+wvalue.substring(3,5)*60)+wvalue.substring(6,8))";
     break;
    case "hh:nn":
     opnd2="parseInt((wvalue.substring(0,2)*60)+wvalue.substring(3,5)*60)";
     break;
    default:  // "String"
     opnd2="wvalue";
   }

   switch(op)
   {
    case "=" :
     js_op="==";
     break;
    case "<>" :
     js_op="!=";
     break;
    case "<" :
     js_op="<";
     break;
    case ">" :
     js_op=">";
     break;
    case ">=" :
     js_op=">=";
     break;
    case "<=" :
     js_op="<=";
     break;
    case "LIKE" : //same as  "NOT LIKE"
    case "NOT LIKE" :
     js_op="";
     opnd2="";
     break;
    case "BETWEEN" : //same as  "NOT BETWEEN"
    case "NOT BETWEEN" :
     js_op="";
     if(aTable[1][index]=="String")    opnd3=arguments[4];
     if(aTable[1][index]=="Integer")   opnd3="parseInt(Where.arguments[4])";
     if(aTable[1][index]=="Float" || aTable[1][index]=="Percent")opnd3="parseFloat(Where.arguments[4])";
     if(aTable[1][index]=="dd/mm/yyyy")opnd3="Date.UTC(Where.arguments[4].substring(6,10),Where.arguments[4].substring(3,5),Where.arguments[4].substring(0,2))";
     if(aTable[1][index]=="mm/dd/yyyy")opnd3="Date.UTC(Where.arguments[4].substring(6,10),Where.arguments[4].substring(2,0),Where.arguments[4].substring(3,5))";
     if(aTable[1][index]=="hh:nn:ss")  opnd3="parseInt(((Where.arguments[4].substring(0,2)*60)+Where.arguments[4].substring(3,5)*60)+Where.arguments[4].substring(6,8))";
     if(aTable[1][index]=="hh:nn")     opnd3="parseInt((Where.arguments[4].substring(0,2)*60)+Where.arguments[4].substring(3,5)*60)";
     break;
    default:
     js_op="==";
   }
  }// if not in ("IS NULL", "IS NOT NULL", "IN", "NOT IN") // improves performances when condition is false

  for(i=HEADROWS;i<aTable.length;i++)
  {
   if(op!="IS NULL" && op!="IS NOT NULL" && op!="IN" && op!="NOT IN") // improves performances when condition is false
   {
    // here it differentiates based on the specified operand

    switch(aTable[1][index]) //checks data type
    {
     case "Boolean" :
       var avalue=aTable[i][index].toLowerCase()
       if(avalue=="true" || avalue=="yes" || avalue=="on" || avalue=="1" || avalue=="vero" || avalue=="sì" || avalue=="M")
         opnd1="1";
       else
         opnd1="0"
       break;
     case "String" :
       opnd1="aTable[i][index]";
       if(op=="LIKE") // in this case 'wvalue' is a RegExp (Regular Expression)
         opnd1="aTable[i][index].search(" + wvalue + ")!=-1";
       if(op=="NOT LIKE") // in this case 'wvalue' is a RegExp
         opnd1="aTable[i][index].search(" + wvalue + ")==-1";
       break;
     case "Integer" :
       opnd1="parseInt(aTable[i][index])";
       break;
     case "Percent" : //handled as a "Float"
     case "Float" :
       opnd1="parseFloat(aTable[i][index])";
       break;
     case "dd/mm/yyyy":
       opnd1="Date.UTC(aTable[i][index].substring(6,10),aTable[i][index].substring(3,5),aTable[i][index].substring(0,2))";
       break;
     case "mm/dd/yyyy":
       opnd1="Date.UTC(aTable[i][index].substring(6,10),aTable[i][index].substring(2,0),aTable[i][index].substring(3,5))";
       break;
     case "hh:nn:ss":
       opnd1="parseInt(((aTable[i][index].substring(0,2)*60)+aTable[i][index].substring(3,5)*60)+aTable[i][index].substring(6,8))";
       break;
     case "hh:nn":
       opnd1="parseInt((aTable[i][index].substring(0,2)*60)+aTable[i][index].substring(3,5)*60)";
       break;
     default:  // "String"
       opnd1="aTable[i][index]";
    }
   }// if not in ("IS NULL", "IS NOT NULL", "IN", "NOT IN") // improves performances when condition is false

   if(op=="BETWEEN")    checkstr="("+opnd1+" >= "+opnd2+""+") && ("+opnd1+" <= "+opnd3+")";
   if(op=="NOT BETWEEN")checkstr="("+opnd1+" < "+opnd2+""+") || ("+opnd1+" > "+opnd3+")";
   if(op!="BETWEEN" && op!="NOT BETWEEN")checkstr=opnd1+js_op+opnd2;
   if(op=="IS NULL")    checkstr="aTable[i][index]==\"\"";
   if(op=="IS NOT NULL")checkstr="aTable[i][index]!=\"\"";
   if(op=="IN")         checkstr="sys_In(aTable[i][index],wvalue)";
   if(op=="NOT IN")     checkstr="!sys_In(aTable[i][index],wvalue)";

   if(eval(checkstr))
   {
    var nrec=wTable.length;
    wTable[nrec]=new Array(nfields);
    for(j=0;j<nfields;j++)wTable[nrec][j]=aTable[i][j];
   }
  }
 }

 // now wTable contains the records of aTable where wfield=wvalue
 return wTable;
}





// returns a table sorted over a field
//
// example
// SELECT * FROM authors ORDER BY author ASC
//
// in the given example 'orderby' is "author"
//
// it is equivalent to the following SQL query:
//
// aTable = SQL( SELECT * FROM myTable ORDER BY orderby [ASC | DESC] )
//
//
// prototype:
//
//   OrderBy( myTable, orderby, ["ASC" | "DESC"] )
//
//
// the third parameter is optional, if unspecified its default value is "ASC"
//
//
// example of use:
//
//   oTable = new Array();
//   oTable = OrderBy( authors , "author", "DESC" );
//
//
// Note: the sort type depends on the data type of the "orderby" field.
//       supported types at the moment are "String", "Integer", "Float", "Percent", "Boolean"
//       "hh:nn:ss", "hh:nn", "dd/mm/yyyy" and "mm/dd/yyyy".
//       "Boolean" is a fortunate case: 'False'<'True', '0'<'1', 'No'<'Yes', 'No'<'Si',
//       'Off'<'On','F'<'M' and 'Falso'<'Vero'
//       and can so be treated as a "String", as the time formats.
//       
// Note: the alphabetical sorting used with field of type "String", "Boolean", "hh:nn:ss" and
//       "hh:nn" is quicker because uses the native JavaScript array.sort() method with a
//       little trick to make it work with 2D-arrays, in the other cases the sorting is less
//       performing because implemented with a coded bubblesort.
//
// updated: 20001024 by fred - Added support to "URL" and "e-mail" data types
// updated: 20001108 by fred - now uses sys_getFieldIndex
// updated: 20001216 by fred - Added support to "Hyperlink" and "Image" data types
//
function OrderBy(myTable,orderby)
{
 var i,j,byType;
 var asc=true;
 if(OrderBy.arguments.length>2 && OrderBy.arguments[2]=="DESC")asc=false;
 var nrecords=myTable.length-HEADROWS;
 var nfields=myTable[0].length;

 // saves names and type of the fields
 var head=new Array(HEADROWS);
 for(i=0;i<HEADROWS;i++)
 {
  head[i]=new Array(nfields);
  for(j=0;j<nfields;j++)head[i][j]=myTable[i][j];
 }
 
 var oTable=new Array();
 for(i=0;i<myTable.length-HEADROWS;i++)
 {
  oTable[i]=new Array(nfields);
  for(j=0;j<nfields;j++)oTable[i][j]=myTable[i+HEADROWS][j];
 }
 
 var by=sys_getFieldIndex(myTable,orderby); //index of the "orderby" field
 byType=myTable[1][by]; // saves the field type to sort properly
 
 // seen that a compare function to pass to the sort() method doesn't seem to
 // work with 2D Arrays (same and undocumented in both the MS and NS implementations),
 // and seen that the sort() method used with 2D-arrays in both the implementations
 // alphabetically sorts the records over the concatenated fields, I gave up creating
 // a compare function for the records and used a little trick:
 // I swap the field [0] with the field [by], I sort the table, and then swap again ;-)
 function swapFields(by)
 {
  var i,swap;
  if(by!=0)
   for(i=0;i<oTable.length;i++)
   {
    swap=oTable[i][0];
    oTable[i][0]=oTable[i][by];
    oTable[i][by]=swap;
   }
 }

 ////////////////////////////////////// 
 if(byType=="String" || byType=="hh:nn:ss" || byType=="hh:nn" || byType=="Boolean" || byType=="URL" || byType=="e-mail" || byType=="Hyperlink" || byType=="Image") //most performing sort method
 {
  swapFields(by);
  oTable.sort();
  swapFields(by);
 }
 else
 {
  switch(byType)
  {
   case "Integer":
    for(var i=0;i<(oTable.length-1);i++)
     for(var j=i+1;j<oTable.length;j++)
      if(parseInt(oTable[j][by])<parseInt(oTable[i][by]))
      {
       var dummy=oTable[i];
       oTable[i]=oTable[j];
       oTable[j]=dummy;
      }
    break;
   case "Percent":
    var perc_i,perc_j,perc_i_str,perc_j_str,achar;
    for(var i=0;i<(oTable.length-1);i++)
    {
     perc_i_str='';
     //I want jsSQL to keep the decimal place holder as the one used by user's tables
     //but parseFloat wants a dot ('.'), so I have to swap it
     for(var ac=0;ac<oTable[i][by].length;ac++)
     {
      achar=oTable[i][by].substring(ac,ac+1)
      if(achar==',')perc_i_str+='.';
      else perc_i_str+=achar;
     }
     perc_i=parseFloat(perc_i_str);

     for(var j=i+1;j<oTable.length;j++)
     {
      perc_j_str='';
      //I want jsSQL to keep the decimal place holder as the one used by user's tables
      //but parseFloat wants a dot ('.'), so I have to swap it
      for(var ac=0;ac<oTable[j][by].length;ac++)
      {
       achar=oTable[j][by].substring(ac,ac+1)
       if(achar==',')perc_j_str+='.';
       else perc_j_str+=achar;
      }
      perc_j=parseFloat(perc_j_str);
      if(perc_j<perc_i)
      {
       var dummy=oTable[i];
       oTable[i]=oTable[j];
       oTable[j]=dummy;
      }
     }
    }
    break;
   case "Float":
    for(var i=0;i<(oTable.length-1);i++)
     for(var j=i+1;j<oTable.length;j++)
      if(parseFloat(oTable[j][by])<parseFloat(oTable[i][by]))
      {
       var dummy=oTable[i];
       oTable[i]=oTable[j];
       oTable[j]=dummy;
      }
    break;
   case "dd/mm/yyyy":
    var dd1,mm1,yyyy1,dd2,mm2,yyyy2;
    for(var i=0;i<(oTable.length-1);i++)
     for(var j=i+1;j<oTable.length;j++)
     {
      dd1=oTable[j][by].substring(0,2)
      dd2=oTable[i][by].substring(0,2)
      mm1=oTable[j][by].substring(3,5)
      mm2=oTable[i][by].substring(3,5)
      yyyy1=oTable[j][by].substring(6,10)
      yyyy2=oTable[i][by].substring(6,10)
      if(yyyy1+mm1+dd1<yyyy2+mm2+dd2)
      {
       var dummy=oTable[i];
       oTable[i]=oTable[j];
       oTable[j]=dummy;
      } 
     }   
    break;
   case "mm/dd/yyyy":
    var dd1,mm1,yyyy1,dd2,mm2,yyyy2;
    for(var i=0;i<(oTable.length-1);i++)
     for(var j=i+1;j<oTable.length;j++)
     {
      dd1=oTable[j][by].substring(3,5)
      dd2=oTable[i][by].substring(3,5)
      mm1=oTable[j][by].substring(0,2)
      mm2=oTable[i][by].substring(0,2)
      yyyy1=oTable[j][by].substring(6,10)
      yyyy2=oTable[i][by].substring(6,10)
      if(yyyy1+mm1+dd1<yyyy2+mm2+dd2)
      {
       var dummy=oTable[i];
       oTable[i]=oTable[j];
       oTable[j]=dummy;
      } 
     }   
    break;
   //cases "Boolean", "hh:nn:ss" and "hh:nn" handled as "String"

   case "": break;
   default: ;
  }
 }
 ////////////////////////////////////// 

 
 // reverses the all if ASC
 if(asc!=false)oTable.reverse();
 
 var nrec;
 // attachs the header to the bottom (and after reverses the table)
 for(i=HEADROWS-1;i>=0;i--)
 {
  nrec=oTable.length;
  oTable[nrec]=new Array(nfields);
  for(j=0;j<nfields;j++)oTable[nrec][j]=head[i][j];
 }
 oTable.reverse();

 //now the table is sorted with the desired criteria
 return oTable;
}




// executes the INNER JOIN of two tables
//
// it is equivalent to the following SQL query:
//
//   jnedTable = SQL( TableA INNER JOIN TableB ON Table1.flda = Table1.fldb )
//
// or to the equivalent:
//
//   jnedTable = SQL( SELECT * FROM TableA, TableB WHERE Table1.flda = Table1.fldb )
//
//
// example. I want to have an idea of the delivery times (cheque_date - delivery_date)
//     of the goods I buy from my suppliers, based over supplier and bank.
//     I'm interested to the fields:
//
//     cheques.cheque_date, cheques.bank, purchases.delivery_date, purchases.supplier
//
//     I execute the following SQL query:
//
//     cheques INNER JOIN purchases ON cheques.ID = purchases.ID_cheque
//
//     equivalent to:
//
//     SELECT * FROM cheques, purchases WHERE cheques.ID = purchases.ID_cheque
//
// Note: the relation has to be 1 to 1
// Note: If some field of the two tables happen to have the same nome,
//       at the moment they will have in the final table as well (to avoid)
//
// example of use:
// jnedTable = new Array();
// jnedTable = InnerJoin( cheques, purchases, "ID", "ID_cheque" )
//
// updated: 20001108 by fred - now uses sys_getFieldIndex
//
function InnerJoin(TableA,TableB,flda,fldb)
{
 var i,j,rec_A,rec_B;
 var irow=0; //index of the records of the new table
 var n_fields_a=TableA[0].length;
 var n_fields_b=TableB[0].length;
 var i_flda=sys_getFieldIndex(TableA,flda);
 var i_fldb=sys_getFieldIndex(TableB,fldb);

 // create the new table
 var aTable=new Array();
 
 // writes the header
 for(i=0;i<HEADROWS;i++)
 {
  aTable[i]=new Array(n_fields_a+n_fields_a);
  for(j=0;j<n_fields_a;j++)aTable[i][j]=TableA[i][j];
  for(j=0;j<n_fields_b;j++)aTable[i][n_fields_a+j]=TableB[i][j];
 }
 
 // writes the table body
 for(rec_A=HEADROWS;rec_A<TableA.length;rec_A++)
 {
  var aflda=TableA[rec_A][i_flda];
  var afldb;
  var exists_in_B=false;

  for(rec_B=HEADROWS;rec_B<TableB.length;rec_B++)
  {
   afldb=TableB[rec_B][i_fldb];
   if(aflda==afldb)
   {
    exists_in_B=true;
    break; // if exists, exists unique (1 to 1 relation)
   }
  }

  if(exists_in_B)
  {
   var nrec=aTable.length;
   aTable[nrec]=new Array(n_fields_a+n_fields_a);
   for(i=0;i<n_fields_a;i++)aTable[nrec][i]=TableA[rec_A][i];
   for(i=0;i<n_fields_b;i++)aTable[nrec][n_fields_a+i]=TableB[rec_B][i];
  }
 }
 // now the table contains the desired join
 return aTable;
}



// makes a selection over the columns of a table
//
// it is eqivalent to the following SQL query:
//
//   selTable = SQL( SELECT fld1, ..., fldn FROM aTable )
//
// example: SELECT ID, bank, amount FROM cheques
//
// example of use:
//
//   selTable = new Array();
//   selTable = Select( cheques, "ID", "bank", "amount" )
//
// Note: the names of the fields to select are read in the 'arguments' of the function
// Note: the fields to select can also be required in an order different from the one in
//       the original table, and repeated more than once in the arguments
//
// updated:  20000409 by fred - now supports data type
// modified: 20001111 by fred - now uses sys_
//
function Select(aTable)
{
 // Important: here have to be defined all the local variables not defined elsewhere
 var i,j,arg;

 var nfields=Select.arguments.length-1;
 var n_fields_a=aTable[0].length;
 var nrecs=aTable.length;

 // create the new table
 var selTable=new Array();

 // writes in the header the fields names
 selTable[0]=new Array(nfields);
 for(i=0;i<nfields;i++)selTable[0][i]=Select.arguments[i+1];

 // in a monodimensional index saves the positions of the fields in the
 // original table (they can be required also unorderer)
 var index=new Array();
 for(i=0;i<nfields;i++)index[i]=sys_getFieldIndex(aTable,Select.arguments[i+1]);

 // writes in the header the field types
 selTable[1]=new Array(nfields);
 for(i=0;i<nfields;i++)selTable[1][i]=aTable[1][index[i]];

 // writes the records in the new table
 if(nrecs>HEADROWS)
  for(j=HEADROWS;j<nrecs;j++)
  {
   selTable[j]=new Array(nfields);
   for(i=0;i<nfields;i++)selTable[j][i]=aTable[j][index[i]];
  }

 // now the table contains the desired select
 return selTable;
}




// operates an OR over the rows of a table
// it is equivalent to the following SQL query:
//
//    TableC = TableA UNION TableB
//
// serves as well to realize the SQL OR statement
//
// example:  SELECT .. FROM ... WHERE ... OR ...
//
// Note: at the moment it doesn't allow the ALL option
// Note: the fields of the two tables have to be coincident and in the same order!
//
function Union(TableA,TableB)
{
 var i,j,k,recsA,recsB,nfields;
 recsA=TableA.length;
 recsB=TableB.length;
 nfields=TableA[0].length;

 var TableC=new Array();
 TableC=From(TableA)

 var arec=new Array(nfields);

 if(recsB>HEADROWS)
  for(i=HEADROWS;i<recsB;i++)
  {
   var inA=false;

   // copies in arec the current record of TableB
   for(k=0;k<nfields;k++)arec[k]=TableB[i][k];

   // compares this record with the ones in TableA
   if(recsA>HEADROWS)
    for(j=HEADROWS;j<recsA;j++)
    {
     var recseq=true;

     // not knowing which is the ID field, checks the equality over all the fields
     for(k=0;k<nfields;k++)
      if(arec[k]!=TableA[j][k])
      {
       recseq=false;
       break;
      }
     if(recseq)
     {
      inA=true;
      break; // two equal records have not to exist
     }
    }

   if(!inA)
   {
    var lenc=TableC.length;
    TableC[lenc]=new Array(nfields);
    for(k=0; k<nfields; k++)TableC[lenc][k]=TableB[i][k];
   }
  }

 return TableC;
}





// acts an AND over the rows of a table
// it is equivalent to the following SQL query:
//
//    TableC = TableA INTERSECT TableB
//
// serves as well to realize the SQL AND statement
//
// example:  SELECT .. FROM ... WHERE ... AND ...
//
// Note: at the moment it doesn't allow the ALL option
// Note: the fields of the two tables have to be coincident and in the same order!
//
function Intersect(TableA,TableB)
{
 var i,j,k,recsA,recsB,nfields;
 recsA=TableA.length;
 recsB=TableB.length;
 nfields=TableA[0].length;

 var TableC=new Array();
 // copies the header
 for(i=0;i<HEADROWS;i++)
 {
  TableC[i]=new Array(nfields);
  for(j=0;j<nfields;j++)TableC[i][j]=TableA[i][j];
 }

 var arec=new Array(nfields);
 if(recsB>HEADROWS)
  for(i=HEADROWS;i<recsB;i++)
  {
   var inA=false;
   // copies in arec the current record of TableB
   for(k=0;k<nfields;k++)arec[k]=TableB[i][k];

   // compare the record with the ones in TableA
   if(recsA>HEADROWS)
    for(j=HEADROWS;j<recsA;j++)
    {
     var recseq=true;
     // not knowing which is the ID field, checks the equality over all the fields
     for(k=0;k<nfields;k++)
      if(arec[k]!=TableA[j][k])
      {
       recseq=false;
       break;
      }
     if(recseq)
     {
      inA=true;
      break; // no two equal records have to exist
     }
    }
   if(inA)
   {
    var lenc=TableC.length;
    TableC[lenc]=new Array(nfields);
    for(k=0; k<nfields; k++)TableC[lenc][k]=TableB[i][k];
   }
  }
 return TableC;
}





// The RightJoin() function performs the Outer Right-Join between two tables,
// and is equivalent to the following SQL query:
//
// rjTable = SQL( SELECT *
//                FROM aTable
//                RIGHT JOIN rTable
//                ON on_a = on_r    )
//
// example:
//
// SELECT *
// FROM authors RIGHT JOIN books
// ON authors.ID = books.ID_author
//
// in the example above the resulting table will contain all the records of the
// 'books' table (right table rTable) and only the records of the 'authors'
// table (left table) satisfying the condition over the ON clause
//
// Note: It doesn't implement explicitly the SELECT DISTINCTROW, but since the selection
//       includes all the fields (SELECT *), the primary key will be included as well,
//       so duplicated record cannot exist.
//
// Note: At present the only operator available over the ON clause is the confront
//       operator. Future implementations may accept a further parameter to specify it.
//
// example of use:
//
//   rjTable = new Array();
//   rjTable = RightJoin( authors, books, "ID", "ID_author" )
//
// updated: 20001108 by fred - now uses sys_getFieldIndex
//
function RightJoin(lTable,rTable,on_l,on_r)
{
 // Important: here have to be defined all the local variables not defined elsewhere
 var i,j;
 var nrecs_l=lTable.length;
 var nrecs_r=rTable.length;
 var nflds_l=lTable[0].length;
 var nflds_r=rTable[0].length;
 var nfields=nflds_l + nflds_l;
 var index_on_l=sys_getFieldIndex(lTable,on_l);
 var index_on_r=sys_getFieldIndex(rTable,on_r);
 var rjTable=new Array();

 // writes the header
 for(i=0;i<HEADROWS;i++)
 {
  rjTable[i]=new Array(nfields);
  for(j=0;j<nflds_l;j++)rjTable[i][j]=lTable[i][j];
  for(j=0;j<nflds_r;j++)rjTable[i][j+nflds_l]=rTable[i][j];
 }

 // writes the table body
 if(nrecs_r>HEADROWS)
  for(j=HEADROWS;j<nrecs_r;j++)
  {
   rjTable[j]=new Array(nfields);

   // first writes the fiels of the records of lTable satisfying the condition over the ON
   var found_in_l=false;
   var index_in_l;

   for(i=HEADROWS;i<nrecs_l;i++)
    if(lTable[i][index_on_l]==rTable[j][index_on_r])
    {
     index_in_l=i;
     found_in_l=true;
     break;
    }
   if(found_in_l)for(i=0;i<nflds_l;i++)rjTable[j][i]=lTable[index_in_l][i];
   else          for(i=0;i<nflds_l;i++)rjTable[j][i]="";

   // then writes the fields of the records of rTable
   for(i=0; i<nflds_r; i++) rjTable[j][i+nflds_l]=rTable[j][i];
  }

 // now rjTable contains the result of the outer left join
 return rjTable;
}



// performs the Outer Left-Join between two tables,
// and is equivalent to the following SQL query:
//
// ljTable = SQL( SELECT *
//                FROM lTable
//                LEFT JOIN rTable
//                ON on_a = on_r   )
//
// example:
//
// SELECT *
// FROM books LEFT JOIN authors
// ON books.ID_author = authors.ID
//
// in the given example the resulting table will contain all the records of the
// 'books' table (left table lTable) and only the records of the 'authors' table
// (right table) satisfying the condition over the ON
//
// Note: It doesn't implement explicitly the SELECT DISTINCTROW, but since the selection
//       includes all the fields (SELECT *), the primary key will be included as well,
//       so duplicated record cannot exist.
//
// Note: At present the only operator available over the ON clause is the confront
//       operator. Future implementations may accept a further parameter to specify it.
//
// example of use:
//
//   ljTable = new Array();
//   ljTable = LeftJoin( books, authors, "ID_author", "ID" )
//
function LeftJoin(lTable,rTable,on_l,on_r)
{
 var ljTable=new Array();
 ljTable=RightJoin(rTable,lTable,on_r,on_l);
 return ljTable;
}





// returns a single cell matrix containing the number of records of a table
// it is equivalent to the following SQL query:
//
//   cTable = SQL( SELECT COUNT(*) FROM aTable )
//
// example:
//
//   SELECT COUNT(*) FROM books
//
// Note: the function doesn't return an integer, but a single cell table having
//       the desired integer as the value of the cell.
//       This for compatibily with the SQL language
//
// updated:  20000409 by fred - writes "Integer" as data type
//
// example of use:
//
//   cTable = new Array();
//   cTable = Count( aTable )
//
function Count(aTable)
{
 var cTable=new Array();
 cTable[0]=new Array(1);
 cTable[0][0]="count";
 cTable[1]=new Array(1);
 cTable[1][0]="Integer";
 cTable[HEADROWS]=new Array(1);
 cTable[HEADROWS][0]=aTable.length-HEADROWS;
 return cTable;
}


// returns a single cell matrix containing the number of records of the
// aTable table where the 'afld' field is not null
//
// it is equivalent to the following SQL query:
//
//   cTable = SQL( SELECT COUNT( ALL afld ) FROM aTable )
//
// example:
//
//   SELECT COUNT( ALL IDautore ) FROM libri
//
// Note: the function doesn't return an integer, but a single cell table having
//       the desired integer as the value of the cell.
//       This for compatibily with the SQL language
//
// updated:  20000409 by fred - now supports data types
// modified: 20001108 by fred - now uses sys_getFieldIndex
//
// example of use:
//
//   cTable = new Array();
//   cTable = CountAll( "ID_author", books )
//
function CountAll(afld,aTable)
{
 // Important: here have to be defined all the local variables not defined elsewhere
 var i;
 var count=0;
 var n_recs=aTable.length;
 var n_fields=aTable[0].length;
 var i_fld=sys_getFieldIndex(aTable,afld);

 for(i=HEADROWS;i<n_recs;i++)
   if(aTable[i][i_fld]!="")count++;

 var cTable=new Array();
 cTable[0]=new Array(1);
 cTable[0][0]="count";
 cTable[1]=new Array(1);
 cTable[1][0]="Integer";
 cTable[HEADROWS]=new Array(1);
 cTable[HEADROWS][0]=count;
 return cTable;
}



// supported data types are: "Integer", "Float" and "Percent"
// could be an idea to decide it to support more data types
//
// returns a single cell matrix containing the maximum value of the 'afld' field of the
// records of aTable where the 'afld' field is not null
//
// it is equivalent to the following SQL query:
//
//   mTable = SQL( SELECT MAX( ALL afld ) FROM aTable )
//
// example.
//
//   SELECT MAX( amount ) FROM cheques
//
// Note: there is not difference between ALL and DISTINCT, so the function is only one
//
// Note: the function doesn't return an integer, but a single cell table having
//       the desired integer as the value of the cell.
//       This for compatibily with the SQL language
//
// updated:  20000419 by fred - now supports data types
// modified: 20001108 by fred - now uses sys_getFieldIndex
//
// example of use:
//
//   mTable = new Array();
//   mTable = Max( "amount", cheques )
//
function Max(afld,aTable)
{
 var i,max;
 var datatype,float_str,afloat,avalue,achar,max_str;
 var uses_comma=false;
 var n_recs=aTable.length;
 max=0;

 var n_fields=aTable[0].length;
 var i_fld=sys_getFieldIndex(aTable,afld);
 datatype=aTable[1][i_fld];

 if(n_recs-HEADROWS<1)max="";
 else
 {
  for(i=HEADROWS;i<n_recs;i++)
   if(aTable[i][i_fld]!="")
   {
    switch(datatype)
    {
     case "Integer":
       avalue=parseInt(aTable[i][i_fld]);
       break;
     case "Percent": //same as "Float"
     case "Float":
       float_str='';
       //I want jsSQL to keep the decimal place holder as the one used by user's tables
       //but parseFloat wants a dot ('.'), so I have to swap it
       for(var ac=0;ac<aTable[i][i_fld].length;ac++)
       {
        achar=aTable[i][i_fld].substring(ac,ac+1);
        if(achar==',')
        {
         float_str+='.';
         uses_comma=true;
        }
        else float_str+=achar;
       }
       afloat=parseFloat(float_str);
       avalue=afloat;
       break;
     default: ;//alert('default');
    }
    if(max=="" || avalue> max)max=avalue;
   }

  //now I have to swap them again
  if(uses_comma)
  {
    max_str=''+max+'';
    float_str='';
    for(var ac=0;ac<max_str.length;ac++)
    {
      achar=max_str.substring(ac,ac+1);
      if(achar=='.')float_str+=',';
      else float_str+=achar;
    }
    max=float_str;
  }
 }
 var mTable=new Array();
 mTable[0]=new Array(1);
 mTable[0][0]="max";
 mTable[1]=new Array(1);
 mTable[1][0]=datatype;
 mTable[HEADROWS]=new Array(1);
 mTable[HEADROWS][0]=max;
 return mTable;
}



// supported data types are: "Integer", "Float" and "Percent"
// could be an idea to decide it to support more data types
//
// returns a single cell matrix containing the minimum value of the 'afld' field of the
// records of aTable where the 'afld' field is not null
//
// it is equivalent to the following SQL query:
//
//   mTable = SQL( SELECT MIN( ALL afld ) FROM aTable )
//
// example:
//
//   SELECT MIN( amount ) FROM cheques
//
// Note: there is not difference between ALL and DISTINCT, so the function is only one
//
// Note: the function doesn't return an integer, but a single cell table having
//       the desired integer as the value of the cell.
//       This for compatibily with the SQL language
//
// updated:  20000419 by fred - now supports data types
// modified: 20001108 by fred - now uses sys_getFieldIndex
//
// example of use:
//
//   mTable = new Array();
//   mTable = Min( "amount", cheques )
//
function Min(afld,aTable)
{
 var i,min;
 var datatype,float_str,afloat,avalue,achar,min_str;
 var uses_comma=false;
 var n_recs=aTable.length;
 min="";

 var n_fields=aTable[0].length;
 var i_fld=sys_getFieldIndex(aTable,afld);
 datatype=aTable[1][i_fld];

 if(n_recs-HEADROWS<1)min="";
 else
 {
  for(i=HEADROWS; i<n_recs; i++)
   if(aTable[i][i_fld]!="")
   {
    switch (datatype)
    {
     case "Integer":
       avalue=parseInt(aTable[i][i_fld]);
       break;
     case "Percent": //same as "Float"
     case "Float":
       float_str='';
       //I want jsSQL to keep the decimal place holder as the one used by user's tables
       //but parseFloat wants a dot ('.'), so I have to swap it
       for(var ac=0;ac<aTable[i][i_fld].length;ac++)
       {
        achar=aTable[i][i_fld].substring(ac,ac+1);
        if(achar==',')
        {
         float_str+='.';
         uses_comma=true;
        }
        else
         float_str+=achar;
       }
       afloat=parseFloat(float_str);
       avalue=afloat;
       break;
     default: ;
    }
    if(min=="" || avalue< min)min=avalue;
   }

  //now I have to swap them again
  if(uses_comma)
  {
    min_str=''+min+'';
    float_str='';
    for(var ac=0;ac<min_str.length;ac++)
    {
      achar=min_str.substring(ac,ac+1);
      if(achar=='.')float_str+=',';
      else float_str+=achar;
    }
    min=float_str;
  }
 }
 var mTable=new Array();
 mTable[0]=new Array(1);
 mTable[0][0]="min";
 mTable[1]=new Array(1);
 mTable[1][0]=datatype;
 mTable[HEADROWS]=new Array(1);
 mTable[HEADROWS][0]=min;
 return mTable;
}


// supported data types are: "Integer", "Float" and "Percent"
//
// returns a single cell matrix containing the average value of the 'afld' column
// of aTable
//
// it is equivalent to the following SQL query:
//
//   avgTable = SQL( SELECT AVG( ALL afld ) FROM aTable )
//
// example:
//
//   SELECT AVG( ALL amount ) FROM cheques
//
// prototype:
//
//   function Avg( afld, aTable )
//
//
// Note: there IS difference between ALL and DISTINCT, the function performs AVG ALL
//
// Note: the function doesn't return a value, but a single cell table having
//       the desired value as the value of the cell.
//       This for compatibily with the SQL language
//
// Note: if the 'afld' data type is "Integer", the returned data type is "Float",
//       otherwise the returned data type is the same as the one of the 'afld' field
//
// updated:  20000419 by fred -  now supports data types
// modified: 20001108 by fred - now uses sys_getFieldIndex
//
//
// example of use:
//   avgTable = new Array();
//   avgTable = Avg( "amount", cheques )
//
function Avg(afld,aTable)
{
 var i,sum,avg,count;
 var datatype,float_str,afloat,achar,avg_str;
 var uses_comma=false;
 var n_recs=aTable.length;
 sum=0;
 avg=0;
 count=0;

 // finds the index of the "afld" field
 var n_fields=aTable[0].length;
 var i_fld=sys_getFieldIndex(aTable,afld); //index of the "afld" field in aTable
 datatype=aTable[1][i_fld];

 if(n_recs-HEADROWS<1)avg="";
 else
 {
  for(i=HEADROWS;i<n_recs;i++)
   if(aTable[i][i_fld]!="")
   {
    switch(datatype)
    {
     case "Integer":
       sum+=parseInt(aTable[i][i_fld]);
       break;
     case "Percent": //same as "Float"
     case "Float":
       float_str='';
       //I want jsSQL to keep the decimal place holder as the one used by user's tables
       //but parseFloat wants a dot ('.'), so I have to swap it
       for(var ac=0;ac<aTable[i][i_fld].length;ac++)
       {
        achar=aTable[i][i_fld].substring(ac,ac+1);
        if(achar==',')
        {
         float_str+='.';
         uses_comma=true;
        }
        else float_str+=achar;
       }
       afloat=parseFloat(float_str);
       sum+=afloat;
       break;
     default: ;
    }
    count++;
   }

   if(count!=0)avg=sum/count;
   else avg=0;

  //now I have to swap them again
  if(uses_comma)
  {
   avg_str=''+avg+'';
   float_str='';
   for(var ac=0;ac<avg_str.length;ac++)
   {
    achar=avg_str.substring(ac,ac+1);
    if(achar=='.')float_str+=',';
    else float_str+=achar;
   }
   avg=float_str;
  }
 }

 var avgTable=new Array();
 avgTable[0]=new Array(1);
 avgTable[0][0]="avg";
 avgTable[1]=new Array(1);
 if(datatype=="Integer")datatype="Float";  //hey, it's an average!
 avgTable[1][0]=datatype;
 avgTable[HEADROWS]=new Array(1);
 avgTable[HEADROWS][0]=avg;
 return avgTable;
}



// supported data types are: "Integer", "Float" and "Percent"
//
// returns a single cell matrix containing the sum of the fields values
// in the 'afld' column of aTable
//
// it is equivalent to the following SQL query:
//
//   sumTable = SQL( SELECT SUM( ALL afld ) FROM aTable )
//
// example:
//
//   SELECT SUM( importo ) FROM assegni
//
// Note: there IS difference between ALL and DISTINCT, the function performs SUMM ALL
//
// Note: the function doesn't return a value, but a single cell table having
//       the desired value as the value of the cell.
//       This for compatibily with the SQL language
//
// Note: the returned data type is the same as the one of the 'afld' field
//
// update:  20000419 by fred - now supports data types
// modified: 20001108 by fred - now uses sys_getFieldIndex
//
// example of use:
//
//   sumTable = new Array();
//   sumTable = Sum( "amount", cheques )
//
function Sum(afld,aTable)
{
 var i,sum,datatype,float_str,afloat,achar,sum_str;
 var uses_comma=false;
 var n_recs=aTable.length;
 sum=0;
 
 var n_fields=aTable[0].length;
 var i_fld=sys_getFieldIndex(aTable,afld);
 datatype=aTable[1][i_fld];

 if(n_recs-HEADROWS<1)sum="";
 else
 {
  for(i=HEADROWS;i<n_recs;i++)
   if(aTable[i][i_fld]!="")
   {
    switch(datatype)
    {
     case "Integer":
       sum+=parseInt(aTable[i][i_fld]);
       break;
     case "Percent": //same as "Float"
     case "Float":
       float_str='';
       //I want jsSQL to keep the decimal place holder as the one used by user's tables
       //but parseFloat wants a dot ('.'), so I have to swap it
       for(var ac=0;ac<aTable[i][i_fld].length;ac++)
       {
        achar=aTable[i][i_fld].substring(ac,ac+1);
        if(achar==',')
        {
          float_str+='.';
          uses_comma=true;
        }
        else float_str+=achar;
       }
       afloat=parseFloat(float_str);
       sum+=afloat;
       break;
     default: ;
    }
   }
  
  //now I have to swap them again
  if(uses_comma)
  {
   sum_str=''+sum+'';
   float_str='';
   for(var ac=0;ac<sum_str.length;ac++)
   {
    achar=sum_str.substring(ac,ac+1);
    if(achar=='.')float_str+=',';
    else float_str+=achar;
   }
   sum=float_str;
  }
 }

 var sumTable=new Array();
 sumTable[0]=new Array(1);
 sumTable[0][0]="sum";
 sumTable[1]=new Array(1);
 sumTable[1][0]=datatype;
 sumTable[HEADROWS]=new Array(1);
 sumTable[HEADROWS][0]=sum;
 return sumTable;
}





// tested, to improve:
// it would be nice if it could make the distinct also over more fields
//
// returns a single column table containing the values of the "afld" field in the records
// of aTable where the "afld" value is distinct
//
// Note: at the moment it accepts only one field
//
// modified: 20001108 by fred - now uses sys_getFieldIndex
//
function Distinct(afld,aTable)
{
 var i,j,k,d_recs;
 var tot_recs=aTable.length;
 var n_fields=aTable[0].length;
 var i_fld=sys_getFieldIndex(aTable,afld);

 var dTable=new Array();
 // copies the header
 for(i=0;i<HEADROWS;i++)
 {
  dTable[i]=new Array(1);
  dTable[i][0]=aTable[i][i_fld];
 }

 var arec;
 if(tot_recs>HEADROWS)
  for(i=HEADROWS;i<tot_recs;i++)
  {
   var in_d=false;

   // copies in arec the current record of dTable
   arec=aTable[i][i_fld];

   // compares the record with the ones already present in dTable
   d_recs=dTable.length;

   if(tot_recs>HEADROWS && arec!=null && arec!="")
    for(j=HEADROWS;j<d_recs;j++)
     if(arec==dTable[j][0]) // checks equality over the "afld" field
     {
      in_d=true;
      break; // no two equal records have to exist
     }

   if((!in_d) && (arec!=null) && (arec!=""))
   {
    var lend=dTable.length;
    dTable[lend]=new Array(1);
    dTable[lend][0]=arec;
   }
  }
 return dTable;
}






// renames a field of a jsSQL table or query.
// it is equivalent to the following SQL query:
//
//   AsTable = SQL( SELECT ... , oldfldname AS newfldname, ... FROM aTable )
//
// where in place of the dots are all the other fields in the original sequence
//
// example of use:
//
//   As( "ID", "ID_cheque", Cheques );
//
//
// Note1: the function is particulary useful in case of need to join table having fields
//        with the same name. In that case the join the join would return a jsSQL table
//        having two columns with the same name, and you wouldn't be able to refer to the
//        second any more, since the first would be found first, with unpredictible
//        results. Using the As() function would in this case avoid the problem.
//
// Note2: Unlike the traditional jsSQL function, the As() function effects the table itself.
//        Of course, the changes are effecting only the jsSQL table in memory in the browser,
//        and not its definition in the script file defining it.
//        The As() function is not properly a function, but a procedure, as it doesn't return
//        a result (in JavaScript the keyword 'procedure' does not exist).
//        
//        The reason why the As() function is a procedure and not a function returning a copy
//        of the table is for pure efficiency, no one would use it otherwise, and would override
//        with an equivalent custom function.
//
// Note3: In case the name of the field is not found in the table fiels, no changes are done.
//
// Note4: It is by the way good use in the jsSQL environment to avoid naming
//        fields with names used in related tables as well.
//
// created: 20001008 by fred
// modified: 20001108 by fred - now uses sys_ , fault tolerant.
//
function As(oldfldname,newfldname,aTable)
{
 var i=sys_getFieldIndex(aTable,oldfldname);
 if(i!=-1)aTable[0][i]=newfldname;
}




// returns a jsSQL table containing the first n record of the original table.
// it is equivalent to the following (non standard) SQL query:
//
//   aTop = SQL( SELECT TOP n * FROM aTable )
//
//   where n is an Integer>0
//
// example of use:
//
//   aTop = new Array();
//   aCopy = From( 5, myTable );
//
function Top(n,aTable)
{
 var i,j;
 var tTable=new Array();
 if(n<0)n=0;
 var max=n+HEADROWS;
 if(max>aTable.length)max=aTable.length;
 for(i=0;i<max;i++)
 {
  tTable[i]=new Array(aTable[0].length);
  for(j=0;j<aTable[0].length;j++)tTable[i][j]=aTable[i][j];
 }
 return tTable;
}



///////////////////////////////////////////////////////////////////////////////////
//                                 sys_  internals                               //
///////////////////////////////////////////////////////////////////////////////////

// finds the index of a field in a Table
// created: 20001108 by fred
//
//                          Table,String
function sys_getFieldIndex(aTable,aField) // as Integer
{
 var i=0;
 var found=false;
 while(i<aTable[0].length && !found)
 {
  if(aTable[0][i]==aField)found=true; // i is index of the "aField" field in aTable
  else i++;
 }
 if(found)return i;
 else
 {
  if(jsSQL_Engine_DEBUG)alert("Error: field named \'"+aField+"\' not found.")
  return -1; // error: Field not found
 }
}



// returns true if aField is in the single column table aTable
// created: 20001111 by fred
//
//               String,      Table
function sys_In(aFieldValue,aTable) // as Integer
{
 var i=HEADROWS-1;
 var found=false;
 while(i<aTable.length && !found)
 {
  if(""+aTable[i][0]+""==""+aFieldValue+"") //compares two strings
    found=true;
  else i++;
 }
 return found;
}




///////////////////////////////////////////////////////////////////////////////////
//                                 hic sunt leones                               //
///////////////////////////////////////////////////////////////////////////////////


