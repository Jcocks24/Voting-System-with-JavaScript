package EpicVotingSystem;
import java.io.*;


import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.*;
import java.text.DecimalFormat;

public class VotingInterface {
	
	 private VotingController vc;
	    private Staff theStaff;
	    private Candidate theCandidate;
	    private Admin theAdmin;
	    private int loginAttempts = 0;
	    
	    Date date = new Date();
	    String DateAsString = date.toString();
	    String startDate = "2015-05-01";
        String endDate = "2015-05-07";

	    private BufferedReader in = new BufferedReader( new InputStreamReader( System.in ));
	    
	    DecimalFormat df = new DecimalFormat ("#.##");
	    DecimalFormat df2 = new DecimalFormat ("#");

		//Here is the start of your program. 
	   public static void main(String[] args)
	    {
	        VotingInterface vi = new VotingInterface();
	        vi.start();
	    }

		//This methods is complete and does not require change.
	    public void start()
	    {
	        vc = new VotingController();
	        commenceVoting();
	    }
	  //=======================================================================
	    
	  //=======================================================================
		//This method helps to manage admin session after a successfull login
		 private boolean manageAdmin()
	     {
	        boolean adminQuit = false;
	        boolean systemQuit = false;
	        
	        //Displays admin name after successful login
	        System.out.printf("\nWelcome %s\n", theAdmin.getName());

	        //Admin can choose to display voting results, end voting, update, add, remove or view accounts
	        while (!adminQuit){
	            System.out.print("\nTo display voting results enter \"D\"\nTo end voting enter \"E\"\nTo update accounts enter \"U\"\nTo add accounts enter \"A\"\nTo remove accounts enter \"R\"\nTo view accounts enter \"V\"\nTo quit enter \"Q\"\n");
	            
	            String input = getInput();
	            System.out.println("");
	            //Displays voting results
	            if (input.equalsIgnoreCase("D")){
	                adminQuit = true;
	                printVoteResults();
	            }
	            //Stops system
	            else if(input.equalsIgnoreCase("E")){
	                adminQuit = true;
	                systemQuit = true;
	                System.out.println("Voting System Closed");
	            }
	            //Allows admin to update accounts
	            else if(input.equalsIgnoreCase("U")){
	                adminQuit = true;
	                updateAccount();
	            }
	            //Allows admin to add accounts
	            else if(input.equalsIgnoreCase("A")){
	                adminQuit = true;
	                addAccount();
	            }
	            //Allows admin to remove accounts
	            else if(input.equalsIgnoreCase("R")){
	                adminQuit = true;
	                removeAccount();
	            }
	            //Allows admin to view accounts
	            else if(input.equalsIgnoreCase("V")){
	                adminQuit = true;
	                viewAccount();
	            }
	            //Allows admin to quit 
	            else if(input.equalsIgnoreCase("Q")){
	                adminQuit = true;
	                commenceVoting();
	            }
	            else{
	                System.out.print("Cannot understand your input, please re-enter : \n\n");
	                manageAdmin();
	            }
	        }
	        return systemQuit;
	     }
		 
		 //validates administrator username & password. This method is complete and does not require additional code.
	     public void validateAdmin()
	     {
	    	 try
		    	{
			    	System.out.print("To login please enter your admin username: ");
			    	String username = getInput();
			    	theAdmin = vc.getAdmin(username);
			    	
			    	if (new String(theAdmin.getUsername()).equals(username))
			    	{
			    		System.out.print("Please confirm by entering your password: ");
			    		String password = getInput();
			    		
			    		if (new String(theAdmin.getPassword()).equals(password))
			    		{
			    			manageAdmin();
			    		}		
			    		else 
			    		{
			    			System.out.println("Incorrect password, please try again.\n");
			    			validateAdmin();
			    		}
			    	}
		    	}
		        catch (Exception e) {
		            System.out.println("Incorrect username, please try again.\n");
		            validateAdmin();
		        }
	     }
	   //=======================================================================
	     
	   //=======================================================================
	     public void help()
	     {
	    	 System.out.print("\nThere is no help.\n");
	    	 System.out.print("To quit enter \"Q\"\n");
	    	 String input = getInput();
	    	 System.out.println("");
	    	 if(input.equalsIgnoreCase("Q"))
	    	 {
                commenceVoting();
	         }
	    	 else
	    	 {
	    		 System.out.print("\nYou did not enter \"Q\"\n");
	    		 help();
	    	 }
	     }
	   //=======================================================================
	     
	   //=======================================================================
	     //Admin can choose between adding a candidate, staff or admin account
		 public void addAccount()
		 {
			 System.out.println("You can add candidate, staff and admin accounts.\n");
			 System.out.print("To add candidate account enter \"C\"\nTo add staff account enter \"S\"\nTo add admin account enter \"A\"\nTo quit enter \"Q\"\n");
			 String input = getInput();
			 System.out.println("");
				if(input.equalsIgnoreCase("C")){
	                addCandidate(theCandidate);
	            }
	            else if(input.equalsIgnoreCase("S")){
	                addStaff(theStaff);
	            }
	            else if(input.equalsIgnoreCase("A")){
	                addAdmin(theAdmin);
	            }
	            else if(input.equalsIgnoreCase("Q")){
	                commenceVoting();
	            }
	            else
				 {
					 System.out.println("Cannot understand your input, please re-enter : \n");
		             addAccount();
				 }
		 }
		 
		//Admin can choose between updating a candidate, staff or admin account
		 public void updateAccount()
		 {
			 System.out.println("You can update candidate, staff and admin accounts.\n");
			 System.out.print("To update candidate account enter \"C\"\nTo update staff account enter \"S\"\nTo update admin account enter \"A\"\nTo quit enter \"Q\"\n");
			 System.out.println("");
	            String input = getInput();
	            if (input.equalsIgnoreCase("C")){
	            	System.out.println("Enter candidate ID: ");
	            	input = getInput();
	            	updateCandidate(vc.getCandidate(Integer.parseInt(input)));
	            }
	            else if(input.equalsIgnoreCase("S")){
	            	System.out.println("Enter staff ID: ");
	            	input = getInput();
	            	updateStaff(vc.getStaff(Integer.parseInt(input)));
	            }
	            else if(input.equalsIgnoreCase("A")){
	            	System.out.println("Enter admin username: ");
	            	input = getInput();
	            	updateAdmin(vc.getAdmin(input));
	            }
	            else if(input.equalsIgnoreCase("Q")){
	                commenceVoting();
	            }
				else
				{
					System.out.print("Cannot understand your input, please re-enter\n\n");
		            updateAccount();
				}
		 }
		 
		//Admin can choose between removing a candidate, staff or admin account
		 public void removeAccount()
		 {
			 System.out.println("You can remove candidate, staff and admin accounts.\n");
			 System.out.print("To remove candidate account enter \"C\"\nTo remove staff account enter \"S\"\nTo remove admin account enter \"A\"\nTo quit enter \"Q\"\n");
			 String input = getInput();
			 System.out.println("");
				if(input.equalsIgnoreCase("C")){
					removeCandidate(theCandidate);
	            }
	            else if(input.equalsIgnoreCase("S")){
	            	removeStaff(theStaff);
	            }
	            else if(input.equalsIgnoreCase("A")){
	            	removeAdmin(theAdmin);
	            }
	            else if(input.equalsIgnoreCase("Q")){
	                commenceVoting();
	            }
			    else
				{
			    	System.out.print("Cannot understand your input, please re-enter : \n");
		            removeAccount();
				}
		 }
		 
		//Admin can choose to view the list of candidate, staff or admin accounts
		 public void viewAccount()
		 {
			 System.out.println("You can view candidate, staff and admin accounts.\n");
			 System.out.print("To view candidate accounts enter \"C\"\nTo view staff accounts enter \"S\"\nTo view admin accounts enter \"A\"\nTo quit enter \"Q\"\n");
			 String input = getInput();
			 System.out.println("");
				if(input.equalsIgnoreCase("C")){
					viewCandidate();
	            }
	            else if(input.equalsIgnoreCase("S")){
	            	viewStaff();
	            }
	            else if(input.equalsIgnoreCase("A")){
	            	viewAdmin();
	            }
	            else if(input.equalsIgnoreCase("Q")){
	                commenceVoting();
	            }
	            else
				{
				    System.out.print("Cannot understand your input, please re-enter : \n");
		            viewAccount();
				}
		 }
		//=======================================================================
		 
		//=======================================================================
		 //screen input reader. This method is complete and working ok.
	     private String getInput()
	     {
	        String theInput = "";

	        try
	        {
	            theInput = in.readLine().trim();
	        }
	        catch(IOException e)
	        {
	            System.out.println(e);
	        }
	        return theInput;
	     }
	   //=======================================================================
		 
	   //=======================================================================
	    //Beginning method that asks if user is voter or admin
	    public void commenceVoting()
	    {
	    	System.out.print("Epic Construction \n");
	    	System.out.println("Welcome to our voting system interface.");
	    	System.out.printf("Today's date is: %s\n", date);
	    	System.out.print("\nAre you a 'voter', 'admin' or do you require 'help'? ");
	    	String theInput = getInput();
	    	
	    	//Checks user input and calls manageVote() if "voter", 
	    	//or calls validateAdmin() then manageAdmin() if "admin"
	    	if (theInput.equalsIgnoreCase ("voter"))
	    	{
	    		manageVote();
	    	}
	    	else if (theInput.equalsIgnoreCase ("admin"))
	    	{
	    		validateAdmin();
	    	}
	    	else if (theInput.equalsIgnoreCase ("help"))
	    	{
	    		help();
	    	}
	    	else
	    	{
	    		System.out.println("\nRequest not recognised, please try again.");
	    		System.out.println();
	    		commenceVoting();
	    	}
	    }
	    //=======================================================================
		
	    //=======================================================================
	    //Voter login 
	    public void manageVote()
	    {   	
	    	//3 failed login attempts takes the user back to the start of the program
	    	try 
	        {
			   if (loginAttempts == 3)
			   {
				   System.out.println("\nYou have excedeed your login attempts.");
				   commenceVoting();
			   }
			   else 
			   {
				   //User input for staff ID
				   System.out.print("Please enter your Staff ID to log in: ");
				   int id = Integer.parseInt(getInput());
				   if (vc.getStaff(id) != null)
				   {
					   //User input for staff password
					   theStaff = vc.getStaff(id);
					   System.out.print("Please confirm your Staff password: ");
					   String password = getInput();
					   
					   if (theStaff.getPassword().equals(password))
					   {
						   //Allows staff to login unless they have already voted
						   if (theStaff.hasVoted() == 0) 
						   {
				    		  displayVotingScreen();
				    	   }
				    	   else if (theStaff.hasVoted() == 1) 
				    	   {
				    		  System.out.printf("\nHello %s!\n", theStaff.getName());
				    		  System.out.print("You have already voted and cannot vote again.\n\n");
				    		  commenceVoting();
				    	   }
					   }
				       else
				       {
				    	   //If incorrect details, returns to staff login
						  System.out.print("\n\nInvalid detalis, please re-enter.\n");
						  loginAttempts++;
						  manageVote();
					   }
				   }
				   else 
				   {
					   //If ID not found, returns to staff login
					   System.out.println("Could not find the ID.\n");
					   loginAttempts++;
					   manageVote();
				   }
			   }
	        }
	    	catch (NumberFormatException ex)
			{
		       System.out.println("Could not find the Staff account.\n");
		       manageVote();
	        }
	     }
	    //=======================================================================


	    //=======================================================================
	     //Voter can choose a candidate to vote for
	     public void displayVotingScreen()
	     {	    	 
	    	try
	    	{
		    	System.out.printf("\nWelcome %s \n", theStaff.getName());
		    	System.out.println("");
		    	
		    	//Asks if staff would to like to vote, 
		    	//otherwise returns them to to login screen
		    	System.out.print("Would you like to vote for a candidate 'Yes'?");
		    	System.out.println(" If 'No', exits program.");
		    	String theInput = getInput();
		    	System.out.println("\n");
		    	if (theInput.equalsIgnoreCase ("yes"))
		    	{
		    		getStaffVote();
		    	}
		    	else if (theInput.equalsIgnoreCase ("no"))
		    	{
		    		commenceVoting();
		    	}
	    	}
	    	catch (Exception e)
			 {
				 System.out.println("Cannot understand your input, please re-enter : \n\n");
				 displayVotingScreen();
			 }

	     }
	    //=======================================================================
		
			
	    //=======================================================================
	    //manages a staff vote 
	     private void getStaffVote()
	     { 
	    	//Displays candidate list
	    	System.out.print("Candidate list:\n");
	    	System.out.print("-----------------\n");
	    	System.out.printf("%s%10s", "Code", "Name");
	       	try
	        {	
	       		int i = vc.getCandidates().indexOf(0);
	       		for (i = 0; i < vc.getCandidates().size(); i++)
	       		{
	       			System.out.printf(("\n" + vc.candidates.get(i).candidateCode + "\t" + vc.candidates.get(i).name + "\n"));
	       		}
	        }
	        catch(Exception e)
	        {
	            System.out.println(e);
	        }
	    	 
	       	try
	       	{
	       	 //Instructs staff on how to vote for candidate
	    	 System.out.print("To choose a candidate you must select their code number from 1-5. \n");
	    	 System.out.print("Enter candidate code: ");
	    	 theCandidate = vc.getCandidate(Integer.parseInt(getInput()));
	    	 
	    	 //Asks staff to confirm candidate choice, 
	    	 //if incorrect choice, returns to candidate list
		     System.out.printf("\nYou have chosen %s, is this the candidate you wish to vote for? ", theCandidate.getName());
		     String theInput = getInput();
		    	if (theInput.equalsIgnoreCase ("yes"))
		    	{
		    		System.out.printf("Your vote has been confirmed on %s, thank you for voting.\n\n", date);
		    		vc.recordVote(DateAsString);
		    		commenceVoting();
		    	}
		    	else if (theInput.equalsIgnoreCase ("no"))
		    	{
		    		getStaffVote();
		    	}
	       	}
	       	catch (Exception e)
	       	{
	       		System.out.print("Incorrect input, please try again.\n");
	       		getStaffVote();
	       	}
	     }
		//=======================================================================
		
		
	    //=======================================================================
	     //prints out the voting results after a succsssfull admin login
	     public void printVoteResults()
	     {	 
        	try
	        {
        		System.out.print("\n--------------------------");
	       		System.out.print("\nVoting Report\n");
		    	System.out.print("--------------------------\n");
		    	System.out.printf("Total Staff: %s\n", vc.staffs.size());
		    	System.out.print("Total Voted: " + df2.format(vc.numberOfVoted) + "(" + df.format(vc.StaffVotesPercent()) + "%" + ")" );
		    	System.out.printf("\n\n%s%12s%12s", "Code", "Candidate", "Votes");	
		    	
	       		int i = vc.getCandidates().indexOf(0);
	       		for (i = 0; i < vc.getCandidates().size(); i++)
	       		{
	       			System.out.print("\n" + vc.candidates.get(i).candidateCode + "\t" + vc.candidates.get(i).name + "\t" + vc.candidates.get(i).votes + "(" + df.format((vc.candidates.get(i).votes/vc.numberOfVoted)*100) + "%" + ")" + "\n");
	       		}
	       		
	       			
		    	
	       		System.out.print("\n\nStaff Voted\n");
		    	System.out.print("--------------------------\n");
		    	System.out.printf("%s%13s%19s%10s", "ID", "Staff", "Votes", "Date" + "\n");
	       		
	       		vc.getStaffHasVoted(i);
	       		
	       		System.out.print("\nStaff Not Voted\n");
		    	System.out.print("--------------------------\n");
		    	System.out.printf("%s%13s", "ID", "Staff" + "\n");
	       		
	       		vc.getStaffNotVoted(i);
	       		
	       		System.out.print("\n\nTo quit enter \"Q\"\n");
				String input = getInput();
				System.out.println("");
	       		if(input.equalsIgnoreCase("Q")){
	                commenceVoting();
	            }
	        }
	        catch(Exception e)
	        {
	            System.out.println(e);
	        }
	    }
	   //=======================================================================
	     
	   //=======================================================================    
	     //Updates candidate account
	     public void updateCandidate(Candidate theCandidate)
	     {
	    	try 
	    	{
	    	 	System.out.printf("Candidate account is %s: \n", theCandidate.getName());
		     	System.out.print("Update the candidate name: ");
		     	String name = getInput();
		     	theCandidate.setName(name);
		     	vc.saveCandidateData();
		     	System.out.printf("The updated candidate name is %s \n", theCandidate.getName());
	    	}
		    catch (Exception e)
		    {
		    	System.out.print("Incorrect input, please try again.\n");
		    	updateAccount();
		    }
	     }
	     
	     //Updates staff account
	     public void updateStaff(Staff theStaff)
	     {
	    	try
	    	{
		     	System.out.printf("Staff account is %s: \n", theStaff.getName());
		     	System.out.print("Update the staff name: ");
		     	String name = getInput();
		     	theStaff.setName(name);
		     	vc.saveStaffData();
		     	System.out.printf("The updated staff name is %s \n", theStaff.getName());
	    	}
	    	catch (Exception e)
		    {
		    	System.out.print("Incorrect input, please try again.\n");
		    	updateAccount();
		    }
	     }
	     
	     //Updates admin account
	     public void updateAdmin(Admin theAdmin)
	     {
	    	try
	    	{
		     	System.out.printf("Admin account is %s: \n", theAdmin.getName());
		     	System.out.print("Update the admin name: ");
		     	String name = getInput();
		     	theAdmin.setName(name);
		     	vc.saveAdminData();
		     	System.out.printf("The updated admin name is %s \n", theAdmin.getName());
	    	}
	    	catch (Exception e)
		    {
		    	System.out.print("Incorrect input, please try again.\n");
		    	updateAccount();
		    }
	     }
	   //=======================================================================
	     
	   //=======================================================================  
	     //Adds a candidate account unless the code matches an existing code
	     public void addCandidate(Candidate theCandidate)
	     {
	     	System.out.print("Enter new candidate code: ");
	     	int newCode = (Integer.parseInt(getInput()));
	     	
	     	if (vc.checkCandidateId(newCode) == false)
	     	{
	     		System.out.print("That candidate code already exists.");
	     		manageAdmin();
	     	}
	     	else
	     	{
		     	System.out.print("Enter new candidate name: ");
		     	String newName = getInput();
		     	
		     	int newVotes = (Integer.parseInt("0"));
		     	
		     	vc.addCandidateData(newCode, newName, newVotes);
	     	}
	     	
	     	System.out.print("\nA new candidate account has been added");
	     }
	     
	     //Adds a staff account unless the ID matches an existing ID
	     public void addStaff(Staff theStaff)
	     {
	     	System.out.print("Enter new staff ID: ");
	     	int newId = (Integer.parseInt(getInput()));
	     	
	     	if (vc.checkStaffId(newId) == false)
	     	{
	     		System.out.print("That staff id already exists.");
	     		manageAdmin();
	     	}
	     	
	     	else
	     	{
		     	System.out.print("Enter new staff name: ");
		     	String newName = getInput();
		     	
		     	int newVotes = (Integer.parseInt("0"));
		     	
		     	System.out.print("Enter new staff password: ");
		     	String newPassword = getInput();
		     	
		     	String date = "N/A";
		     	
		     	vc.addStaffData(newId, newName, newVotes, newPassword, date);
	     	}
	     	
	     	System.out.print("\nA new staff account has been added");
	     }
	     
	     //Adds an admin account unless the ID matches an existing ID
	     public void addAdmin(Admin theAdmin)
	     {
	     	System.out.print("Enter new admin ID: ");
	     	int newId = (Integer.parseInt(getInput()));
	     	
	     	if (vc.checkAdminId(newId) == false)
	     	{
	     		System.out.print("That admin id already exists.");
	     		addAdmin(theAdmin);
	     	}
	     	
	     	else
	     	{
		     	System.out.print("Enter new admin name: ");
		     	String newName = getInput();
		     	
		     	System.out.print("Enter new admin username: ");
		     	String newUsername = getInput();
		     	
		     	//If username already exists, returns to addAdmin()
		     	if (vc.checkAdminUsername(newUsername) == false)
		     	{
		     		System.out.print("That admin username already exists.\n\n");
		     		addAdmin(theAdmin);
		     	}
		     	else
		     	{
		     		System.out.print("Enter new admin password: ");
			     	String newPassword = getInput();
			     	
			     	vc.addAdminData(newId, newName, newUsername, newPassword);
		     	}
	     	}
	     	
	     	System.out.print("\nA new admin account has been added");
	     }
	    //=======================================================================
	     
	    //=======================================================================
	     //Removes a candidate account from the text file
	     public void removeCandidate(Candidate theCandidate)
	     {
	    	 System.out.print("Enter candidate code for the account you want to delete: ");
	    	 theCandidate = vc.getCandidate(Integer.parseInt(getInput()));
	    	 
	    	 System.out.printf("The account you want to remove is %s\n", theCandidate.getName());
	    	 
	    	 System.out.print("\nDo you wish to remove this account: ");
	    	 String theInput = getInput();
	    	 if (theInput.equalsIgnoreCase("Yes"))
	    	 {
	    		 vc.removeCandidateData();
	    	 }
	    	 else if (theInput.equalsIgnoreCase("No"))
	    	 {
	    		 removeAccount();
	    	 }
	     }
	     
	     //Removes a staff account from the text file
	     public void removeStaff(Staff theStaff)
	     {
	    	 System.out.print("Enter ID for the account you want to delete: ");
	    	 theStaff = vc.getStaff(Integer.parseInt(getInput()));
	    	 
	    	 System.out.printf("The account you want to remove is %s\n", theStaff.getName());
	    	 
	    	 System.out.print("\nDo you wish to remove this account: ");
	    	 String theInput = getInput();
	    	 if (theInput.equalsIgnoreCase("Yes"))
	    	 {
	    		 vc.removeStaffData();
	    	 }
	    	 else if (theInput.equalsIgnoreCase("No"))
	    	 {
	    		 removeAccount();
	    	 }
	     }
	     
	     //Removes an admin account from the text file 
	     public void removeAdmin(Admin theAdmin)
	     {
	    	 System.out.print("Enter username for the account you want to delete: ");
	    	 theAdmin = vc.getAdmin(getInput());
	    	 
	    	 System.out.printf("The account you want to remove is %s\n", theAdmin.getName());
	    	 
	    	 System.out.print("\nDo you wish to remove this account: ");
	    	 String theInput = getInput();
	    	 if (theInput.equalsIgnoreCase("Yes"))
	    	 {
	    		 vc.removeAdminData();
	    	 }
	    	 else if (theInput.equalsIgnoreCase("No"))
	    	 {
	    		 removeAccount();
	    	 }
	     }
	     //=======================================================================
	     
		 //=======================================================================
	     //Displays candidate account list
	     public void viewCandidate()
	     {
	       	try
	        {
	       		System.out.print("\nCandidate list:");
		    	System.out.print("-----------------\n");
		    	System.out.printf("%s%10s%13s", "Code", "Name", "Votes");
		       	try
		        {	
		       		int i = vc.getCandidates().indexOf(0);
		       		for (i = 0; i < vc.getCandidates().size(); i++)
		       		{
		       			System.out.printf(("\n" + vc.candidates.get(i).candidateCode + "\t" + vc.candidates.get(i).name + "\t" + vc.candidates.get(i).votes + "\n"));
		       		}
		        }
		        catch(Exception e)
		        {
		            System.out.println(e);
		        }
	        }
	        catch(Exception e)
	        {
	            System.out.println(e);
	        }
	     }
	     
	     //Displays staff account list
	     public void viewStaff()
	     {
	       	try
	        {
	       		System.out.print("\n-----------------\n");
	       		System.out.print("Staff list:\n");
		    	System.out.print("-----------------\n");
		    	System.out.printf("Total Staff: %s\n", vc.staffs.size());
		    	System.out.print("Total Votes: \n\n");
		    	System.out.printf("%s%13s%12s%10s", "ID", "Name", "Voted", "Password");
		       	try
		        {	
		       		int i = vc.getStaffs().indexOf(0);
		       		for (i = 0; i < vc.getStaffs().size(); i++)
		       		{
		       			System.out.printf(("\n" + vc.staffs.get(i).id + "\t" + vc.staffs.get(i).name + "\t" + vc.staffs.get(i).voted + "\t" + vc.staffs.get(i).password + "\n"));
		       		}
		        }
		        catch(Exception e)
		        {
		            System.out.println(e);
		        }
	        }
	        catch(Exception e)
	        {
	            System.out.println(e);
	        }
	     }
	     
	   //Displays admin account list
	     public void viewAdmin()
	     {
		       	try
		        {
		       		System.out.print("\nAdmin list:\n");
			    	System.out.print("-----------------\n");
			    	System.out.printf("%s%12s%18s%20s", "ID", "Name", "Username", "Password");
			       	try
			        {	
			       		int i = vc.getAdmins().indexOf(0);
			       		for (i = 0; i < vc.getAdmins().size(); i++)
			       		{
			       			System.out.printf(("\n" + vc.admins.get(i).id + "\t" + vc.admins.get(i).name + "\t" + vc.admins.get(i).username + "\t" + vc.admins.get(i).password + "\n"));
			       		}
			        }
			        catch(Exception e)
			        {
			            System.out.println(e);
			        }
		        }
		        catch(Exception e)
		        {
		            System.out.println(e);
		        }
	     }
	   //=======================================================================

}
