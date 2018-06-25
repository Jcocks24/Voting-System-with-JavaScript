package EpicVotingSystem;
import java.io.*;
import java.util.*;

public class VotingController {
	
	//Create an Arraylist read & store staff & candidate data from file
    public ArrayList<Staff> staffs = new ArrayList<Staff>();
    public ArrayList<Candidate> candidates = new ArrayList<Candidate>();
    public ArrayList<Admin> admins = new ArrayList<Admin>();

   //Type to access individual staff & candidate from array list
    private Staff theStaff;
    private Candidate theCandidate;
    private Admin theAdmin;
    
    //Staff voted
    public double numberOfVoted = 0;
    
    //Staff votes percentage
    public double percentVoted = 0;

    //VotingController constructor
    public VotingController()
    {
        loadStaffData();
        loadCandidateData();
        loadAdminData();
    }
  //=======================================================================

  //=======================================================================
    //Returns a staff if found in the staffs ArrayList
    public Staff getStaff(int id)
    {
        Iterator<Staff> it = staffs.iterator();
        while(it.hasNext())
        {
            theStaff = (Staff) it.next();
            if(theStaff.getId()== id)
            {
                return theStaff;
            }
        }
        return null;
    }
    
    public Staff getStaffHasVoted(int id)
    {
        Iterator<Staff> it = staffs.iterator();
        while(it.hasNext())
        {
            theStaff = (Staff) it.next();
            if(theStaff.hasVoted() == 1)
            {
                System.out.print(theStaff.getId() + "\t" + theStaff.getName() + "\t\t" + theStaff.hasVoted() + "\t" + theStaff.getDate() + "\n");
            }
        }
        return null;
    }
    
    public Staff getStaffNotVoted(int id)
    {
        Iterator<Staff> it = staffs.iterator();
        while(it.hasNext())
        {
            theStaff = (Staff) it.next();
            if(theStaff.hasVoted() == 0)
            {
                System.out.print(theStaff.getId() + "\t" + theStaff.getName() + "\n");
            }
        }
        return null;
    }
    
    //Returns the candidate if found in the candidates ArrayList
    public Candidate getCandidate(int candidateCode)
    {
    	Iterator<Candidate> it = candidates.iterator();
        while(it.hasNext())
        {
            theCandidate = (Candidate) it.next();
            if(theCandidate.getCandidateCode()== candidateCode)
            {
                return theCandidate;
            }
        }
        return null;
    }
    
  //Returns the admin if found in the admin ArrayList
    public Admin getAdmin(String adminUserName)
    {
	    Iterator<Admin> it = admins.iterator();
	    while(it.hasNext())
	    {
		    theAdmin = (Admin) it.next();
		    
		    if(theAdmin.getUsername().equals(adminUserName))
		    {
		    	
		    return theAdmin;
		    }
	    }
	    return null;
    }
  //=======================================================================
    
  //=======================================================================
    //returns the collection of candidates
    public ArrayList<Candidate> getCandidates()
    {
        return candidates;
    }
    
    //Returns the collection of admins
    public ArrayList<Admin> getAdmins()
    {
		return admins;
	}
    
  //Returns the collection of admins
    public ArrayList<Staff> getStaffs()
    {
		return staffs;
	}

    //returns total number of staffs in the collection
    public int getTotalVoters()
    {
        return staffs.size();
    }
    
    //returns total number of candidates in the collection
    public int getTotalCandidates()
    {
        return candidates.size();
    }
  //=======================================================================

  //=======================================================================
    //every staff vote must be saved to file
    public void recordVote(String date)
    {
    	//Sets staff vote and date of voting to staff text file,
    	//adds vote of chosen candidate to text file then calls save staff and candidate data methods
        theStaff.setVoted();
        theCandidate.addVote();
        theStaff.setDate(date);
        saveStaffData();//save to file
        saveCandidateData();//save to file
    }
  //=================================================================

  //=================================================================
    //Saves staff data to text file
    public void saveStaffData()
    {
        try
        {
            BufferedWriter writer = new  BufferedWriter(new FileWriter("staff.txt"));
            Iterator it = staffs.iterator();
            String staffDetails;
            while(it.hasNext())
            {
                theStaff = (Staff) it.next();
                staffDetails = theStaff.getId() + "," + theStaff.getName() + "," + theStaff.hasVoted() + "," + theStaff.getPassword() + "," + theStaff.getDate() + "\n";
                writer.write(staffDetails);
            }
            writer.close();
        }
        catch(IOException e)
        {
            System.out.println(e);
        }
    }
    
	//Saves candidate data to text file
    public void saveCandidateData()
    {
    	try
        {
            BufferedWriter writer = new  BufferedWriter(new FileWriter("candidates.txt"));
            Iterator it = candidates.iterator();
            String candidateDetails;
            while(it.hasNext())
            {
                theCandidate = (Candidate) it.next();
                candidateDetails = theCandidate.getCandidateCode() + "," + theCandidate.getName() + "," + theCandidate.getVotes() + "\n";
                writer.write(candidateDetails);
            }
            writer.close();
        }
        catch(IOException e)
        {
            System.out.println(e);
        }
    }
    
    //Saves admin data to text file
    public void saveAdminData()
    {
        try
        {
            BufferedWriter writer = new  BufferedWriter(new FileWriter("admin.txt"));
            Iterator it = admins.iterator();
            String adminDetails;
            while(it.hasNext())
            {
                theAdmin = (Admin) it.next();
                adminDetails = theAdmin.getId() + "," + theAdmin.getName() + "," + theAdmin.getUsername() + "," + theAdmin.getPassword() + "\n";
                writer.write(adminDetails);
            }
            writer.close();
        }
        catch(IOException e)
        {
            System.out.println(e);
        }
    }
    //=================================================================
	
    //=================================================================
    //loads staff names from file.
    public void loadStaffData()
    {	
    	try
        {
    		 //loads staff data from text file to arraylist
             String fileName = "staff.txt";
             File theFile = new File(fileName);
             BufferedReader reader = new BufferedReader(new FileReader(theFile));

             String staffData;

             while((staffData = reader.readLine())!= null)
             {
                 String[] staffDetails = staffData.split(",");
                 int id = Integer.parseInt(staffDetails[0]);
                 String name = (staffDetails[1]);
                 int voted = Integer.parseInt(staffDetails[2]);
                 String password = (staffDetails[3]);
                 String date = (staffDetails[4]);
                 theStaff = new Staff(id, name, voted, password, date);
                 staffs.add(theStaff);
                 
                 if (theStaff.hasVoted() == 1)
                 {
                	 numberOfVoted++;
                 }
             }
             
             reader.close();
         }
         catch(IOException e)
         {
             System.out.println("Error! There was a problem with loading candidate names from file");
         }
    }
    
    public void loadCandidateData()
    {
    	//Loads candidate data from text file to arraylist
        try
        {
             String fileName = "candidates.txt";
             File theFile = new File(fileName);
             BufferedReader reader = new BufferedReader(new FileReader(theFile));

             String candidateData;

             while((candidateData = reader.readLine())!= null)
             {
                 String[] candidateDetails = candidateData.split(",");
                 int code = Integer.parseInt(candidateDetails[0]);
                 int votes = Integer.parseInt(candidateDetails[2]);
                 theCandidate = new Candidate(code, candidateDetails[1], votes);
                 candidates.add(theCandidate);
             }
             reader.close();
         }
         catch(IOException e)
         {
             System.out.println("Error! There was a problem with loading candidate names from file");
         }
    }

    public void loadAdminData()
    {
    	//Loads admin data from text file to arraylist
    	try
        {
             String fileName = "admin.txt";
             File theFile = new File(fileName);
             BufferedReader reader = new BufferedReader(new FileReader(theFile));

             String adminData;

             while((adminData = reader.readLine())!= null)
             {
                 String[] adminDetails = adminData.split(",");
                 int id = Integer.parseInt(adminDetails[0]);
                 String name = (adminDetails[1]);
                 String username = (adminDetails[2]);
                 String password = (adminDetails[3]);
                 theAdmin = new Admin(id, name, username, password);
                 admins.add(theAdmin);
             }
             reader.close();
         }
         catch(IOException e)
         {
             System.out.println("Error! There was a problem with loading candidate names from file");
         }
    }   
  //=======================================================================
    
  //=======================================================================
	//creates a new candidate object and adds to the candidate array list, then saves the candidate text file
    public void addCandidateData(int newCode, String newName, int newVotes)
    {
        theCandidate = new Candidate(newCode, newName, newVotes);
        candidates.add(theCandidate);
        
        saveCandidateData();
    }
    
	//creates a new candidate object and adds to the candidate array list, then saves the candidate text file
    public void addStaffData(int newId, String newName, int newVotes, String newPassword, String date)
    {
        theStaff = new Staff(newId, newName, newVotes, newPassword, date);
        staffs.add(theStaff);
        
        saveStaffData();
    }
    
	//creates a new candidate object and adds to the candidate array list, then saves the candidate text file
    public void addAdminData(int newId, String newName, String newUsername, String newPassword)
    {
        theAdmin = new Admin(newId, newName, newUsername, newPassword);
        admins.add(theAdmin);
        
        saveAdminData();
    }
  //=======================================================================
    
  //=======================================================================
    //Removes candidate account, then saves candidate text file
    public void removeCandidateData()
    {
    	candidates.remove(theCandidate);
    	saveCandidateData();
    	System.out.print("The account has been removed.");
    }
    
    //Removes staff account, then saves staff text file
    public void removeStaffData()
    {
    	staffs.remove(theStaff);
    	saveStaffData();
    	System.out.print("The account has been removed.");
    }
    
    //Removes admin account, then saves admin admin text file
    public void removeAdminData()
    {
    	admins.remove(theAdmin);
    	saveAdminData();
    	System.out.print("The account has been removed.");
    }
  //=======================================================================
    
  //=======================================================================
    public boolean checkCandidateId(int newCode)
    {
   	 	Iterator it = candidates.iterator();
        while(it.hasNext())
        {
            theCandidate = (Candidate) it.next();
            if(theCandidate.getCandidateCode()== newCode)
            {
                return false;
            }
        }
        return true;
    }
    
    public boolean checkAdminId(int newID)
    {
   	 	Iterator it = admins.iterator();
        while(it.hasNext())
        {
            theAdmin = (Admin) it.next();
            if(theAdmin.getId()== newID)
            {
                return false;
            }
        }
        return true;
    }
    
    public boolean checkAdminUsername(String newUsername)
    {
   	 	Iterator it = admins.iterator();
        while(it.hasNext())
        {
            theAdmin = (Admin) it.next();
            if(theAdmin.getUsername().equals(newUsername))
            {
                return false;
            }
        }
        return true;
    }
    
    public boolean checkStaffId(int newID)
    {
   	 	Iterator it = staffs.iterator();
        while(it.hasNext())
        {
            theStaff = (Staff) it.next();
            if(theStaff.getId()== newID)
            {
                return false;
            }
        }
        return true;
    }
  //=======================================================================  
    
  //======================================================================= 
    public double StaffVotesPercent()
    {
    	percentVoted = (numberOfVoted/staffs.size())*100;
    	return percentVoted;
    }
}
