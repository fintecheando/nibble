<html>
<jsp:useBean id="color" class="java.lang.String" scope="session"/>
<jsp:useBean id="menu" class="java.lang.String" scope="session"/>
<jsp:useBean id="menualterno" class="java.lang.String" scope="session"/>
<head>
<title>NIBBLE</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
</head>
<body bgcolor="#FFFFFF" text="#000000">
<table width="800" border="0" cellspacing="0" cellpadding="0" align="center">
  <tr> 
    <td colspan="2"><img src="images/<%=color%>.png" width="750" height="43"></td>
  </tr>
  <tr> 
	<td align ="right" height="4">
    <link rel="stylesheet" type="text/css" href="lib/ddlevelsmenu-base.css" />
    <link rel="stylesheet" type="text/css" href="lib/ddlevelsmenu-topbar.css" />
    <link rel="stylesheet" type="text/css" href="lib/ddlevelsmenu-sidebar.css" />
    <script type="text/javascript" src="lib/ddlevelsmenu.js"></script>     
      <script type="text/javascript">
        ddlevelsmenu.setup("ddtopmenubar", "topbar")
        </script>
        <%= menualterno %>
    </td>
  </tr>
</table>
</body>
</html>
