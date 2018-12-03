<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Admin page</title>
<link rel="stylesheet" href="css/bootstrap/bootstrap-3.3.7.css">
<script src="css/bootstrap/jQuery-3.2.1.js"></script>
<script src="css/bootstrap/bootstrap-3.3.7.js"></script>
</head>
<body>

<% if(session.getAttribute("userId")==null)
             {
             RequestDispatcher reqd;
             reqd=request.getRequestDispatcher("AdminLogin.html");
             reqd.forward(request, response);
             }
          else
             {%>

              <B>Welcome <%=(String)session.getAttribute("userName")%></B>

          <%}%>
     		<br>
       		<a href="addAsset.jsp" class="btn btn-success">ADD ASSET</a>
       		
       		
       		<br>
       		<b>REPORTS</b>
       		<br>
       		<a href="AssetLoginServlet?btn=viewUsers" class="btn btn-success">VIEW USERS</a>&nbsp&nbsp&nbsp&nbsp
       		<a href="AssetController?btn=viewAssets" class="btn btn-success">VIEW ASSETS</a>&nbsp&nbsp&nbsp&nbsp
       		<a href="AssetController?btn=viewBorrowedAssets" class="btn btn-success">VIEW BORROWED ASSETS</a> 
       
       		
       		
</body>
</html>