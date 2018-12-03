<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="java.util.ArrayList"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Assets page</title>
<meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>

           
    <div class="container" style="width:70%">
	<table border="5" class="table">
		<tr class="success">
			<th>Asset_Id</th>
			<th>Category</th>
			<th>Stock</th>
			<th>Availability</th>
			<th>Flag</th>
			<th>Delete</th>
			

		</tr>
            <caption> Assets </caption>
            <%
                    ArrayList arrayListObject= new ArrayList();
                    int count;
                if(request.getAttribute("aobj")!=null)
                    {
                	arrayListObject= (ArrayList)request.getAttribute("aobj");
                        try
                                {
                                for (count=0;count<arrayListObject.size();count=count+5)
                    {
                        %>
            <tr class="info">
                <td><%=arrayListObject.get(count)%></td>
                <td><%=arrayListObject.get(count+1)%></td>
                <td><%=arrayListObject.get(count+2)%></td>
                <td><%=arrayListObject.get(count+3)%></td>
                <td><%=arrayListObject.get(count+4)%></td>
                <td><a href="AssetController?id=<%=arrayListObject.get(count)%>&btn=Delete">Delete</a></td>
            </tr>
           
                <% } }catch(Exception e)
                {
                    System.out.println("error is "+e);
                }

                    }    %>
        </table>

        </div>
</body>
</html>