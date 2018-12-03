<%@ page language="java" contentType="text/html; charset=ISO-8859-1" 
    pageEncoding="ISO-8859-1"%>
    <%@page import="java.util.ArrayList"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User page</title>
<link rel="stylesheet" href="css/bootstrap/bootstrap-3.3.7.css">
<script src="css/bootstrap/jQuery-3.2.1.js"></script>
<script src="css/bootstrap/bootstrap-3.3.7.js"></script>
</head>
<body>
   <% if(session.getAttribute("userId")==null)
             {
             RequestDispatcher reqd;
             reqd=request.getRequestDispatcher("UserLogin.jsp");
             reqd.forward(request, response);
             }
          else
             {%>

              <B>Welcome <%=(String)session.getAttribute("userName")%></B>

          <%}%>
          
         <!--  <a href=AssetController?btn=viewAssets&id=<%=(String)session.getAttribute("userId")%>>View Assets Available</a> -->
          
           <div align="center">
<div class="well" style="width:70%">
           <h1>Search Asset</h1>

        <form action="AssetController" >
              <table>
                        <tr> <td>Select Asset </td>
                     <td> 
                     	
                     
                          <select name="availableAsset" >
                          <option value="Laptop">LAPTOPS</option>
                          <option value="Books">BOOKS</option>
                          <option value="USB Cables">USB Cables</option>
                          
                          </select>
                     </td>
                     </tr>
                        <tr>
                                <td colspan="2"><input type="submit" name = "btn" value="Search Assets" class="btn btn-success"/></td>
                        </tr>
                        
                </table>
        </form>
          
          <a href="AssetController?btn=Return Asset&userId=<%=(String)session.getAttribute("userId")%>" class="btn btn-success">RETURN ASSET</a>

		   <p>       


</body>
</html>