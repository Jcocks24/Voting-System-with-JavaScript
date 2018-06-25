package EpicVotingSystem;

public class Staff {
	
	int id;
    String name;
    int voted; //has the staff voted
    String password;
    String date = null;

    public Staff(int id, String name, int voted, String password, String date)
    {
            this.id = id;
            this.name = name;
            this.voted = voted;
            this.password = password;
            this.date = date;
    }

    public void setId(int id)
    {
       this.id = id;
    }

    public void setName(String name)
    {
            this.name = name;
    }

    public void setVoted()
    {
            this.voted = 1;
    }
    
    public void setPassword(String password)
    {
       this.password = password;
    }
    
    public void setDate(String date)
    {
       this.date = date;
    }
    

    public int getId()
    {
       return id;
    }

    public String getName()
    {
            return name;
    }

    public int hasVoted()
    {
            return voted;
    }
    
    public String getPassword()
    {
            return password;
    }
    
    public String getDate()
    {
            return date;
    }
}
