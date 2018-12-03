package com.asset.controller;

import java.io.IOException;
import java.io.PrintWriter;

import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;

import com.asset.dal.AssetDALConnection;
import com.asset.model.AssetModel;




@WebServlet("/AssetLoginServlet")
public class AssetLoginServlet extends HttpServlet {
	 protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			PrintWriter out = response.getWriter();
			
			AssetDALConnection assetDALConnection = new AssetDALConnection();
			ArrayList arrayListobject= null;
			AssetModel assetModel= new AssetModel();
			HttpSession session= request.getSession();
			
			String userName = request.getParameter("userName");
			String userPassword = request.getParameter("userPass");
			String userType = request.getParameter("userType");
			

			String newUserName = request.getParameter("newUserName");
			String newUserPassword = request.getParameter("newUserPass");
			String newUserType =request.getParameter("newUserType");
			
			String btn = request.getParameter("btn");
			
	        
			if(btn.equals("Login"))
			{
				 try{
					 System.out.println("c1");
					 
//					 if(assetModel.checkUserExist(userName,userPassword,userType))
//					 {
					 
					 arrayListobject = assetModel.checkUserLogin(userName,userPassword,userType);
					 System.out.println(arrayListobject);
					
					    if(arrayListobject != null){
					    	
					    	 System.out.println("c2");
			            	 session = request.getSession();
                             session.setAttribute("userId",arrayListobject.get(0).toString());
                             session.setAttribute("userName",arrayListobject.get(1).toString());
                             session.setAttribute("userType",arrayListobject.get(2).toString());
                             System.out.println("yo");
                             
                             System.out.println((String)session.getAttribute("userName"));
                             System.out.println((String)session.getAttribute("userType"));
                             
        //if((String)session.getAttribute("userName")== "Admin" && (String)session.getAttribute("userType")== "admin")
         if(arrayListobject.get(1).toString().equals("Admin"))
                                   { 
        	               System.out.println("yo2");
                           RequestDispatcher reqd= request.getRequestDispatcher("adminProfile.jsp");
                           reqd.forward(request,response);
        	       }
                       else 
                           {          
                    	  
                                 System.out.println("yo1");
                           	     RequestDispatcher reqd= request.getRequestDispatcher("userProfile.jsp");
                                 reqd.forward(request,response);
                      
                           }
					 }
				 //} 
				     else
						 out.println("<script>alert(\"invalid Login Creditials \")</script>");
					 
					 out.println("<html>"
		           				+ "<Head>"
		           				+ "</head>"			
		           				+ "<Body>"
		           				+ "Kindly Login Again!"
		           				+ ""
		           				+ "<br />"
		           				+ "<a href=\"userLogin.jsp\">"
		           				+ "Profile" + "</a>"
				    		    + "</body>"
		           				+ "</html>");
					     
						    
				 }catch (Exception e)
				 {
					System.out.println("Error in User Check Login "  +  e); 
				 }
			}
			
			if(btn.equals("SIGNUP"))
			{
				 
					 try{
						 System.out.println(newUserType);
						 
						 if(newUserType.equals("USER"))
						 {if(assetModel.addNewUser(newUserName,newUserPassword,newUserType)==true)
						  {
			            	   out.println("<html>"
			           				+ "<Head>"
			           				+ "</head>"			
			           				+ "<Body>"
			           				+ "New User Added"
			           				+ ""
			           				+ "<br />"
			           				+ "<a href=\"Index.jsp\">"
			           				+ "Home Page" + "</a>"
					    		    + "</body>"
			           				+ "</html>");
			               }else
							 {	System.out.println("Error in adding");
	           			    	out.println("Adding Failed");
					 }
						 }
						 else{ if(newUserType.equals("ADMIN")) {
							 if(assetModel.checkAdmin(newUserType)==true)
						  {
							 
							 out.println("<html>"
				           				+ "<Head>"
				           				+ "</head>"			
				           				+ "<Body>"
				           				+ "ADMIN EXISTS!"
				           				+ ""
				           				+ "<br />"
				           				+ "<a href=\"Index.jsp\">"
				           				+ "Home Page" + "</a>"
						    		    + "</body>"
				           				+ "</html>");
									    }
							 else {if(assetModel.addNewUser(newUserName,newUserPassword,newUserType)==true)
							  {
				            	   out.println("<html>"
				           				+ "<Head>"
				           				+ "</head>"			
				           				+ "<Body>"
				           				+ "New Admin Added"
				           				+ ""
				           				+ "<br />"
				           				+ "<a href=\"Index.jsp\">"
				           				+ "Home Page" + "</a>"
						    		    + "</body>"
				           				+ "</html>");
				               }else
								 {	System.out.println("Error in adding");
		           			    	out.println("Adding Failed");
						 }
								 
							 }

						 }
						 } 
						 
					 } 
				 catch (Exception e)
				 {
					System.out.println("Error in Adding new User  "  +  e); 
				 }
			}
			if(btn.equals("viewUsers"))
			{
				try {
					arrayListobject = assetModel.viewUsers();
					
					System.out.println(arrayListobject);
					
					RequestDispatcher reqd=request.getRequestDispatcher("viewUsers.jsp");
                    request.setAttribute("aobj",arrayListobject);
                    reqd.include(request,response);
				}catch(Exception e)
				{
					System.out.println("error in view Assets in controller " + e);
					
				}
				
	 
	 }
	 }
}