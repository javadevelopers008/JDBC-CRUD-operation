package com.jdbc.crud;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class JDBC_CRUD_example {
 
	/*  for these example i am using these query to create a table  of employee 
	 * 
	 * CREATE TABLE `employee` (
		    `id` int(11) NOT NULL AUTO_INCREMENT,
		    `name` varchar(45) NOT NULL,
		    `department` varchar(45) NOT NULL,
		    PRIMARY KEY (`id`)
		);
		
		*
		*  again we could create the table using mysql and jdbc just add these code as one of the option
		*  in switch case
		*  
		*     result =  statementObject.executeUpdate(aboveQuery);
		*     
		*     if(result == 0) {
			    		        System.out.println("Table not created");
			    	        }else {
			    		        System.out.println("Table created");
			    	        }
			    	  
			                }
		*     
		*/
	 

	@SuppressWarnings("resource")
	public static void main(String[] args)
	  {
	    
		  Scanner input = null;
		  
		  int id=0;
		  String name = null , department = null;
		  Statement stmt = null;
		  Connection conn = null;
		  PreparedStatement pstmt= null;
		  ResultSet rs = null;
		  
		  input= new Scanner(System.in);
		  
		  int result = 0;
		  
		  int option=0;
		  int updateOption=0;
		  
		  String updatedName = null, updatedDepartment = null;
		  
		  try {
			      
			      //registering the jdbc driver
			      Class.forName("com.mysql.jdbc.Driver");
			      
			      //establishing the connection
			      conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/TEST", "root", "root");
			      
			      
			      System.out.println("Select onr of the following option");
			      System.out.println("1. see all the working emplyoees");
			      System.out.println("2. insert new  emplyoees info");
			      System.out.println("3. update employees info");
			      System.out.println("4. delete emplyoee");
			      
			      option = input.nextInt();
			      
			      switch(option) {
			      
			      case 1:  stmt = conn.createStatement();
			               
			               rs = stmt.executeQuery("select * from employee");
			               
			            // iterate through the java resultset
			               while (rs.next())
			               {
			                 id = rs.getInt("id");
			                 name = rs.getString("name");
			                 department = rs.getString("department");
			                
		                     // print the results
			                 System.out.format("%s, %s, %s\n", id, name, department);
			               }//while
			               
			               break;
			               
			      case 2:   if(input!= null) {
			    	   
			    	           System.out.println("Enter ID");
			    	           id = input.nextInt();
			    	   
			    	           System.out.println("Enter Name");
			    	           name = input.next();
			    	   
			    	           System.out.println("Enter Department");
			    	           department = input.next();
			    	   
			                 }//if
			      
			                 //create prepared statement object
			                 if(conn != null) {
			    	  
			    	              pstmt = conn.prepareStatement("insert into employee (id,name,department) values(?,?,?)");
			    	  
			    	              //set values to query params
			    	              pstmt.setInt(1,id);
			    	              pstmt.setString(2, name);
			    	              pstmt.setString(3, department);
			    	  
			    	        }//if
			      
			                if(pstmt != null) {
			    	  
			    	        //execute SQL Query
			    	        result = pstmt.executeUpdate();
			    	  
			    	        //process the result
			    	        if(result == 0) {
			    		        System.out.println("records not inserted");
			    	        }else {
			    		        System.out.println("records inserted");
			    	        }
			    	  
			                }//if
			                
			                break;
			               
			      case 3:   
			    	        System.out.println("what do you want to update");
			                System.out.println("press 5 to update  name");
			                System.out.println("press 6  to update  departname");
			                
			                updateOption = input.nextInt();
			                
			                switch(updateOption) {
			                
			                case 5:    System.out.println("please provide your ID number");
			                           id = input.nextInt(); 
			                	
			                	       System.out.println("please prvide the name you want to change");
			                	       updatedName = input.next();
			                	
			                	       //create prepared statement object
				                       if(conn != null) {
							    	  
					    	               pstmt = conn.prepareStatement(" UPDATE employee SET name = ? WHERE id=?");
					    	  
					    	               //set values to query params
					    	               pstmt.setString(1,updatedName);
					    	               pstmt.setInt(2, id);
					    	           
					    	  
					    	           }//if
					      
					                   if(pstmt != null) {
					    	  
					    	               //execute SQL Query
					    	               result = pstmt.executeUpdate();
					    	  
					    	               //process the result
					    	               if(result == 0) {
					    		               System.out.println("records not updated");
					    	               }else {
					    		               System.out.println("records updated");
					    	               }
					    	  
					                   }//if
					                   
					                   break;
					                   
			                  case 6:  System.out.println("please provide your ID number");
	                                   id = input.nextInt();
			                	  
			                	       System.out.println("please prvide the deaprtment you want to change");
	                	               updatedDepartment = input.next();
	                	
	                	               //create prepared statement object
		                               if(conn != null) {
					    	  
			    	                      pstmt = conn.prepareStatement(" UPDATE employee SET deaprtment = ? WHERE id=?");
			    	  
			    	                      //set values to query params
			    	                      pstmt.setString(1,updatedDepartment);
			    	                      pstmt.setInt(2, id);
			    	           
			    	  
			    	                   }//if
			      
			                           if(pstmt != null) {
			    	  
			    	                      //execute SQL Query
			    	                      result = pstmt.executeUpdate();
			    	  
			    	                     //process the result
			    	                         if(result == 0) {
			    		                        System.out.println("records not updated");
			    	                         }else {
			    		                        System.out.println("records updated");
			    	                         }
			    	  
			                                 }//if
			                           break;
			                }//switch for update
			            
			      case 4:  System.out.println("please provide the id of the employee whose  info you want to delete");
	                       id = input.nextInt();
               	
	                       //create prepared statement object
                           if(conn != null) {
   	  
                               pstmt = conn.prepareStatement(" delete from employee where id = ? ");
 
                               //set values to query params
                               pstmt.setInt(1,id);
                           }//if

                          if(pstmt != null) {
 
                          //execute SQL Query
                          result = pstmt.executeUpdate();
 
                          //process the result
                          if(result == 0) {
	                        System.out.println("records not deleted");
                          }else {
	                        System.out.println("records deleted");
                          }
 
                          }//if
                  
                           break;
                  
                 default : System.out.println(" please select one of the  option given above "); 
			             
			      
			      }//switch
			      
			      
			  
		  }catch(SQLException se) {
			  se.printStackTrace();
		  }
		  catch(ClassNotFoundException cnfe) {
			  cnfe.printStackTrace();
		  }
		  catch(Exception e) {
			  e.printStackTrace();
		  }
		  
		  finally {
			  
			  try {
				  if(stmt != null)
					  stmt.close();
			  }
			  catch(SQLException se) {
				  se.printStackTrace();
			  }
			  
			  try {
				  if(pstmt != null)
					  pstmt.close();
			  }
			  catch(SQLException se) {
				  se.printStackTrace();
			  }
			  
			  try {
				  if(conn != null)
					  conn.close();
			  }
			  catch(SQLException se) {
				  se.printStackTrace();
			  }
			  
			  try {
				  if(input != null)
					  input.close();
			  }
			  catch(Exception e) {
				  e.printStackTrace();
			  }
		  }//finally
		  
	  }//main
	
	}//JDBC_CRUD_example
