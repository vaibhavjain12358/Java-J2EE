package com.asset.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.asset.dal.AssetDALConnection;
import com.asset.model.AssetModel;

  @WebServlet("/AssetController")
public class AssetController extends HttpServlet {
	 protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			PrintWriter out = response.getWriter();
			
			AssetDALConnection assetDALConnection = new AssetDALConnection();
			ArrayList arrayListobject= new ArrayList();
			AssetModel assetModel= new AssetModel();
			HttpSession session= request.getSession();
			
			String category = request.getParameter("category");
			String stock = request.getParameter("stock");
			String availability = request.getParameter("availability");
			String id = request.getParameter("id");
			String userAssetId = request.getParameter("assetId");
			String userId = request.getParameter("userId");
			
			String selectAvailableAsset = request.getParameter("availableAsset");
			
			
			
			
			
			
	 
			String btn = request.getParameter("btn");
	 
			if(btn.equals("Add Asset"))
			{
				 try{
					 int rowsInserted = assetModel.addAsset(category,stock,availability);
					 
					 if (rowsInserted > 0) {
							out.println("ASSET ADDED SUCCESSFULLY!");
						} else {
							out.println("FAILED ADDING ASSET!");
						}
				 }catch(Exception e)
				 {
					 System.out.println("error in adding asset " + e);
				 }
			}
			
			if(btn.equals("Delete"))
			{
				 try{
					 int rowsDeleted = assetModel.deleteAsset(id);
					 
					 if (rowsDeleted > 0) {
							out.println("DELETED ASSET SUCCESSFULLY!");
					
				arrayListobject = assetModel.getAsset(id); // retrieving and storing values in Array list 
				
				String assetId = (String) arrayListobject.get(0); //Retrieving values from array list to pass to addHistory function
				String assetCategory = (String) arrayListobject.get(1);
				String assetStock = (String) arrayListobject.get(2);
				String assetAvailability = (String) arrayListobject.get(3);
				
				int rowsInserted = assetModel.addHistoryAsset(assetId,assetCategory,assetStock,assetAvailability);
				
				if (rowsInserted > 0) {
					out.println("ASSET ADDED SUCCESSFULLY to ASSET HISTORY!");
				} else {
					out.println("FAILED ADDING ASSET to ASSET HISTORY!");
				}
			
			}
					 else {
							out.println("FAILED Deleting ASSET!");
						}
				 }catch(Exception e)
				 {
					 System.out.println("error in deleting asset " + e);
				 }
			}
			
			if(btn.equals("viewAssets"))
			{
				try {
					arrayListobject = assetModel.viewAssets();
					System.out.println(arrayListobject);
					RequestDispatcher reqd=request.getRequestDispatcher("viewAssets.jsp");
                    request.setAttribute("aobj",arrayListobject);
                    reqd.include(request,response);
				}catch(Exception e)
				{
					System.out.println("error in view Assets in controller " + e);
					
				}
				
	 
	 }

			if(btn.equals("Search Assets"))
			{
				try {
					arrayListobject = assetModel.viewAvailableAssets(selectAvailableAsset);
					
					
					RequestDispatcher reqd=request.getRequestDispatcher("viewAvailableAssets.jsp");
                    request.setAttribute("aobj",arrayListobject);
                    reqd.include(request,response);
				}catch(Exception e)
				{
					System.out.println("error in view Available Assets in controller " + e);
					
				}
				
	 
	 }
			if(btn.equals("Borrow"))
			{
				try {
				int avail = Integer.parseInt(request.getParameter("availabilities"));
					 if(avail>0)
					{
						if(assetModel.getBorrowedAsset(userId)) 
						{
							
							out.println("<html>"
			           				+ "<Head>"
			           				+ "</head>"			
			           				+ "<Body>"
			           				+ "Kindly return the pending Assets first!"
			           				+ ""
			           				+ "<br />"
			           				+ "<a href=\"userProfile.jsp\">"
			           				+ "Profile" + "</a>"
					    		    + "</body>"
			           				+ "</html>");
						}else {
							int rowsInserted = assetModel.addUserBorrowedAsset(userId,userAssetId);
							if (rowsInserted > 0) {
								
								out.println("<html>"
				           				+ "<Head>"
				           				+ "</head>"			
				           				+ "<Body>"
				           				+ "ASSET ADDED SUCCESSFULLY!"
				           				+ "<br>"
				           				+ "<br>"
				           				+ "KINDLY RETURN ASSET WITHIN 15 DAYS "
				           				+ "<br>"
				           				+ "OTHERWISE FINE WILL BE IMPOSED, 50Rs/DAY"
				           				+ "<br>"
				           				+ "<br />"
				           				+ "<br>"
				           				+ "<a href=\"userProfile.jsp\">"
				           				+ "Profile" + "</a>"
						    		    + "</body>"
				           				+ "</html>");
								
								int rowsUpdated = assetModel.decrementAvailability(userAssetId, avail);
                                if (rowsUpdated > 0) {
                                        System.out.println("Availability Updated SUCCESSFULLY!");
                                } else {
                                        System.out.println("FAILED Updating Availability!");
                                }
							} else {
								out.println("FAILED ADDING ASSET!");
							}
						}
					}else {
						out.println("Reservation Placed!");
					}
					
				}catch(Exception e)
				{
					System.out.println("error in view Available Assets in controller " + e);
					
				}
			}
				if(btn.equals("Return Asset"))
				{
					try {
						System.out.println(userId);
						 if(assetModel.checkBorrowedAssetExist(userId))
							 
						 {
							 System.out.println(userId);
							  arrayListobject= assetModel.getBorrowedAssetToReturn(userId);
						 
						     RequestDispatcher reqd=request.getRequestDispatcher("viewAssetToBeReturned.jsp");
			                    request.setAttribute("aobj",arrayListobject);
			                    reqd.include(request,response);
							}
						 else
							 {out.println("<html>"
				           				+ "<Head>"
				           				+ "</head>"			
				           				+ "<Body>"
				           				+ "KINDLY BORROW ASSET FIRST"
				           				+ ""
				           				+ "<br />"
				           				+ "<a href=\"userProfile.jsp\">"
				           				+ "Profile" + "</a>"
						    		    + "</body>"
				           				+ "</html>");}
						 
					}catch(Exception e)
							{
								System.out.println("error in returning asset in controller " + e);
								
							}
							
				 
				 }
				
				if(btn.equals("Return"))
				{
					try {
						int avail = Integer.parseInt(request.getParameter("availabilities"));
					
						  int rowsInserted = assetModel.returnUserAsset(userId, userAssetId);
	                        if (rowsInserted > 0) {
	                                
	                                out.println("<html>"
					           				+ "<Head>"
					           				+ "</head>"			
					           				+ "<Body>"
					           				+ "ASSET RETURNED SUCCESSFULLY!"
					           				+ ""
					           				+ "<br />"
					           				+ "<a href=\"userProfile.jsp\">"
					           				+ "Profile" + "</a>"
							    		    + "</body>"
					           				+ "</html>");
	                                
	                                int rowsUpdated = assetModel.incrementAvailability(userAssetId,avail);
	                                if (rowsUpdated > 0) {
	                                        System.out.println("Availability Updated SUCCESSFULLY!");
	                                } else {
	                                        System.out.println("FAILED Updating Availability!");
	                                }
	                                
	                        } else {
	                                out.println("FAILED RETURNING ASSET!");
	                        }
	                        
	                }catch(Exception e)
					{
						System.out.println("error in returnUserAsset in controller " + e);
						
					}
	                        
	                }
				if(btn.equals("viewBorrowedAssets"))
				{
					try {
						arrayListobject = assetModel.viewBorrowedAssets();
						
						System.out.println(arrayListobject);
						
						RequestDispatcher reqd=request.getRequestDispatcher("viewBorrowedAssets.jsp");
	                    request.setAttribute("aobj",arrayListobject);
	                    reqd.include(request,response);
					}catch(Exception e)
					{
						System.out.println("error in viewBorrowedAssets in controller " + e);
						
					}
					
		 
		 }
						              
  }
  }
  
