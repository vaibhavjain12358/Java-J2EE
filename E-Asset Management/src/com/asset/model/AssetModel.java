package com.asset.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.crypto.NullCipher;

import com.asset.controller.*;
import com.asset.dal.AssetDALConnection;


public class AssetModel 
{
	    PreparedStatement preparedStatement;
	    ResultSet resultSet;
	    Statement statement;
	    AssetDALConnection assetDALConnection = new AssetDALConnection();
	    ArrayList arrayListObject= null;
	   
	    
	    /*public boolean checkUserExist(String User,String Pass,String Type )
	    {
	           assetDALConnection.connect();
	           boolean flag=false;
	           try{
	        	   
	        	   preparedStatement = assetDALConnection.connection.prepareStatement("select * from Users where user_name=? and Password=? and type=?");
	               preparedStatement.setString(1, User);
	               preparedStatement.setString(2, Pass);
	               preparedStatement.setString(3, Type);
	               
	                
	                if (preparedStatement.executeUpdate() > 0) {
	                    flag = true;
	                }else
	                {
	                	System.out.println("User Doesnt Exist");
	                	
	            }

	           }catch (Exception e) {
	            System.out.println("error in Checking user exist " + e);
	        }
	       
	        return flag;
	           }*/

	    public ArrayList checkUserLogin(String username, String password,String userType) 
	    {
	    	 arrayListObject= new ArrayList();
	    	assetDALConnection.connect();
	    	System.out.println("here");
        try 
        {
        	preparedStatement = assetDALConnection.connection.prepareStatement("select * from Users where user_name=? and Password=? and type=?");
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, userType);
            resultSet = preparedStatement.executeQuery();
            
            
            if (resultSet.next())
            {
            	arrayListObject= new ArrayList();
            	
                 arrayListObject.add(resultSet.getString(1).trim());
                 arrayListObject.add(resultSet.getString(2).trim());
                 arrayListObject.add(resultSet.getString(3).trim());
            }
            
           
            else arrayListObject = null;

        } catch (Exception e) {
            System.out.println("error in userchecklogin" + e);

        }

		return arrayListObject;
	}
	    
	   
	    public boolean addNewUser(String newUser,String newPass,String newType )
	    {
	           assetDALConnection.connect();
	           boolean flag=false;
	           try{
	        	   
	                preparedStatement=assetDALConnection.connection.prepareStatement("insert into USERS (USER_NAME,PASSWORD,TYPE) values (?,?,?)");
	                
	                preparedStatement.setString(1,newUser);
	                preparedStatement.setString(2,newPass);
	                preparedStatement.setString(3,newType);
	                
	                if (preparedStatement.executeUpdate() > 0) {
	                    flag = true;
	                }else
	                {
	                	System.out.println("No User added");
	                	}
	            }

	         catch (Exception e) {
	            System.out.println("error in saving User" + e);
	        }
	       
	        return flag;
	           }

	    public boolean checkAdmin(String newUserType)
	    {
	           assetDALConnection.connect();
	           boolean flag=false;
	           try{
	        	    statement = assetDALConnection.connection.createStatement();
	        	    resultSet = statement.executeQuery("Select * from users where type = '" + newUserType + "' ");
	        	    if (resultSet.next())
	    			{
	    				flag=true;
	    			}
	        	    else flag=false;
	           }catch(Exception e) {
		            System.out.println("error in checking Admin" + e);
		        }
		       
		        return flag;
		           }
	    		
	    public int addAsset(String category,String stock,String availability)
	    {
	       	 int recordsAffected = 0;
	     	 assetDALConnection.connect();
	          try{
	        	  preparedStatement=assetDALConnection.connection.prepareStatement("insert into assets(category,stock,availability) values(?,?,?)");
	                
	                preparedStatement.setString(1,category);
	                preparedStatement.setString(2,stock);
	                preparedStatement.setString(3,availability);
	                recordsAffected = preparedStatement.executeUpdate();
	                System.out.println("Asset Added!");
	  		} catch (Exception e) {
	  			System.out.println("ERROR SAVING ASSET : " + e);
	  		}
	    	return recordsAffected;
	    }
	    
	    public int deleteAsset(String id) {
			int recordsAffected = 0;
			assetDALConnection.connect();
			try {
				preparedStatement=assetDALConnection.connection.prepareStatement("update assets set flag='Disable' where asset_id=?");
				preparedStatement.setString(1, id);
				recordsAffected = preparedStatement.executeUpdate();
			} catch (Exception e) {
				System.out.println("ERROR DELETING ASSET : " + e);
			}
			return recordsAffected;
	    }
			
	       public ArrayList getAsset(String id) 
			    {
			    	assetDALConnection.connect();
			    	ArrayList arrayListObject= new ArrayList();
			    	
		        try 
		        {
		        	preparedStatement = assetDALConnection.connection.prepareStatement("select * from assets where asset_id=?");
		            preparedStatement.setString(1, id);
		           
		            resultSet = preparedStatement.executeQuery();

		            if (resultSet.next())
		            {
		            	 
		             	
		            	 arrayListObject.add(resultSet.getString(1).trim());
		                 arrayListObject.add(resultSet.getString(2).trim());
		                 arrayListObject.add(resultSet.getString(3).trim());
		                 arrayListObject.add(resultSet.getString(4).trim());
		                 arrayListObject.add(resultSet.getString(5).trim());
		                 
		            }
		            
		            resultSet.close();

		        } catch (Exception e) {
		            System.out.println("error in getting Asset " + e);

		        }

				return arrayListObject;
			}
			 public int addHistoryAsset(String assetId,String assetCategory,String assetStock,String assetAvailability)
			    {
			       	 int recordsAffected = 0;
			     	 assetDALConnection.connect();
			          try{
			        	  preparedStatement=assetDALConnection.connection.prepareStatement("insert into assetsHistory values(?,?,?,?)");
			                
			                preparedStatement.setString(1,assetId);
			                preparedStatement.setString(2,assetCategory);
			                preparedStatement.setString(3,assetStock);
			                preparedStatement.setString(4,assetAvailability);
			                recordsAffected = preparedStatement.executeUpdate();
			                System.out.println("Asset Added! to history asset table");
			  		} catch (Exception e) {
			  			System.out.println("ERROR SAVING ASSET to history table : " + e);
			  		}
			    	return recordsAffected;
			    }

			 public ArrayList viewAssets() 
			    {
			    	assetDALConnection.connect();
			    	 ArrayList arrayListObject= new ArrayList();
		        try 
		        {
		        	preparedStatement = assetDALConnection.connection.prepareStatement("select * from assets order by asset_id");
					resultSet = preparedStatement.executeQuery();
					 while(resultSet.next())
		              {
						
			            	
		                 arrayListObject.add(resultSet.getString(1).trim());
		                 arrayListObject.add(resultSet.getString(2).trim());
		                 arrayListObject.add(resultSet.getString(3).trim());
		                 arrayListObject.add(resultSet.getString(4).trim());
		                 arrayListObject.add(resultSet.getString(5).trim());
		                 
		            }
		            
		            resultSet.close();

		        } catch (Exception e) {
		            System.out.println("error in viewAsset " + e);

		        }

				return arrayListObject;
			}

			 public ArrayList viewAvailableAssets(String selectAvailableAsset) 
			    {
				 
			    	assetDALConnection.connect();
			    	ArrayList arrayListObject= new ArrayList();
		        try 
		        {
		        	preparedStatement = assetDALConnection.connection.prepareStatement("select * from assets where category=?");
		        	 preparedStatement.setString(1,selectAvailableAsset);
					resultSet = preparedStatement.executeQuery();
					 if(resultSet.next())
		              {
						 
			             
						 arrayListObject.add(resultSet.getString(1).trim());
		                 arrayListObject.add(resultSet.getString(2).trim());
		                 arrayListObject.add(resultSet.getString(3).trim());
		                 arrayListObject.add(resultSet.getString(4).trim());
		                
		            }
		            
		            resultSet.close();

		        } catch (Exception e) {
		            System.out.println("error in viewAsset " + e);

		        }

				return arrayListObject;
			}

			
			 public  boolean getBorrowedAsset(String userId) {
	                boolean flag=false;
	                assetDALConnection.connect();
	                try {
	                        
	                        PreparedStatement preparedStatement = assetDALConnection.connection.prepareStatement("select asset_id from Users_Assets where user_id=?");
	                        preparedStatement.setString(1, userId);
	                        ResultSet resultSet = preparedStatement.executeQuery();
	                        if (resultSet.next()) {
	                                flag=true;
	                                
	                        }
	                        else {
	                        	flag=false;
	                        	
	                        }
	                } catch (Exception e) {
	                        System.out.println(e);
	                }
	                return flag;
	        }
			 
			 public int addUserBorrowedAsset(String userId,String userAssetId)
			    {
			       	 int recordsAffected = 0;
			     	 assetDALConnection.connect();
			   try{
			      PreparedStatement preparedStatement = assetDALConnection.connection.prepareStatement("insert into Users_Assets(USER_ID, ASSET_ID)values(?,?)");
                  preparedStatement.setString(1, userId);
                  preparedStatement.setString(2, userAssetId);
                  recordsAffected = preparedStatement.executeUpdate();
			         
			      } catch (Exception e) {
	                        System.out.println("ERROR BORROWING ASSET : " + e);
	                }
	                return recordsAffected;
	        }
			          
			 public  int decrementAvailability(String userAssetId, int avail) {
	                int recordsAffected = 0;
	                assetDALConnection.connect();
	                try {
	                        
	                        PreparedStatement preparedStatement = assetDALConnection.connection.prepareStatement("update assets set availability=? where asset_id=?");
	                        preparedStatement.setInt(1, avail-1);
	                        preparedStatement.setString(2, userAssetId);
	                        recordsAffected = preparedStatement.executeUpdate();
	                } catch (Exception e) {
	                        System.out.println("ERROR UPDATING AVAILABILITY : " + e);
	                }
	                return recordsAffected;

	        }
			 
			 public ArrayList getBorrowedAssetToReturn(String userId) 
			    {
				 
			    	assetDALConnection.connect();
			    	ArrayList arrayListObject= new ArrayList();
			    	
		        try 
		        {
		        	preparedStatement = assetDALConnection.connection.prepareStatement("select asset_id,category,availability from assets natural join users_assets where user_id=?");
		        	preparedStatement.setString(1, userId);
					resultSet = preparedStatement.executeQuery();
					 if(resultSet.next())
		              {
						 
			            	
		                 arrayListObject.add(resultSet.getString(1).trim());
		                 arrayListObject.add(resultSet.getString(2).trim());
		                 arrayListObject.add(resultSet.getString(3).trim());
		                 
		                
		            }
		            
		            resultSet.close();

		        } catch (Exception e) {
		            System.out.println("error in return asset  " + e);

		        }

				return arrayListObject;
			}
			 public boolean checkBorrowedAssetExist(String userId) 
			    {
				 	boolean flag=false;
			    	assetDALConnection.connect();
			    	
		        try 
		        {
		        	preparedStatement = assetDALConnection.connection.prepareStatement("select asset_id,category,availability from assets natural join users_assets where user_id=?");
		        	 preparedStatement.setString(1, userId);
					resultSet = preparedStatement.executeQuery();
					 if(resultSet.next())
		              {
		                 flag=true;
		                
		            }
		            
		            resultSet.close();

		        } catch (Exception e) {
		            System.out.println("error in checkBorrowedAssetExist  " + e);

		        }

				return flag;
			}
			 public  int returnUserAsset(String userId,String assetId) {
	                int recordsAffected = 0;
	                assetDALConnection.connect();
	                try {
	                       
	                        PreparedStatement preparedStatement = assetDALConnection.connection.prepareStatement("delete from Users_Assets where user_id=? and asset_id=?");
	                        preparedStatement.setString(1, userId);
	                        preparedStatement.setString(2, assetId);
	                        recordsAffected = preparedStatement.executeUpdate();
	                        System.out.println("Asset Returned by user!");
	                } catch (Exception e) {
	                        System.out.println("ERROR Returning ASSET : " + e);
	                }
	                return recordsAffected;
	        }
			 
			 public  int incrementAvailability(String userAssetId, int avail) {
	                int recordsAffected = 0;
	                assetDALConnection.connect();
	                try {
	                        
	                        PreparedStatement preparedStatement = assetDALConnection.connection.prepareStatement("update assets set availability=? where asset_id=?");
	                        preparedStatement.setInt(1, avail+1);
	                        preparedStatement.setString(2, userAssetId);
	                        recordsAffected = preparedStatement.executeUpdate();
	                } catch (Exception e) {
	                        System.out.println("ERROR UPDATING AVAILABILITY : " + e);
	                }
	                return recordsAffected;

	        }
			public ArrayList viewUsers() 
			    {
			    	assetDALConnection.connect();
			        arrayListObject= new ArrayList();
		        try 
		        {
		     
		        	preparedStatement = assetDALConnection.connection.prepareStatement("select user_id,user_name from users where user_name != 'Admin'");
					resultSet = preparedStatement.executeQuery();
					 while(resultSet.next())
		              {
						 arrayListObject.add(resultSet.getString(1).trim());
		                 arrayListObject.add(resultSet.getString(2).trim());
		             }
		            
		            resultSet.close();

		        } catch (Exception e) {
		            System.out.println("error in viewAsset " + e);

		        }

				return arrayListObject;
			}
			
			public ArrayList viewBorrowedAssets() 
		    {
		    	assetDALConnection.connect();
		    	 ArrayList arrayListObject= new ArrayList();
	        try 
	        {
	     
	        	preparedStatement = assetDALConnection.connection.prepareStatement("select user_name,category,borrowing_date,expected_return_date from users natural join users_assets natural join assets");
				resultSet = preparedStatement.executeQuery();
				 while(resultSet.next())
	              {
					 arrayListObject.add(resultSet.getString(1).trim());
	                 arrayListObject.add(resultSet.getString(2).trim());
	                 arrayListObject.add(resultSet.getString(3).trim());
	                 arrayListObject.add(resultSet.getString(4).trim());
	                 
	             }
	            
	            resultSet.close();

	        } catch (Exception e) {
	            System.out.println("error in viewBorrowedAssets " + e);

	        }

			return arrayListObject;
		}
 }
			


	
		
	
	
	
	
	
	

