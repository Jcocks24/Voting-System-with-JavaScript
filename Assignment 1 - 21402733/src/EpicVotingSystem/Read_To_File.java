package EpicVotingSystem;

import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;

public class Read_To_File {
	
	public static void main(String[] args)
	{
		//Declare a scanner and a reader
		Scanner scanner = new Scanner(System.in);
		BufferedReader read = null;
		
		//Read the file line by line
		String line;
		
		//Prompts the user to enter the file to read
		System.out.println("Please enter the file name to read: ");
		
		//Uses try catch as a block 
		try 
		{
			//Initiates the reader
			read = new BufferedReader (new FileReader ("E:\\Semester 2\\Software Development Methods - USB\\workspace\\Assignment 1 - 21402733\\" + scanner.next()));
			
		}
		//Run exception file not found
		catch (FileNotFoundException fnfx)
		{
			System.out.println(fnfx.getMessage() + "The file was not found");
			System.exit(0);
		}
		
		//Uses try catch as a block for error reading line
		try
		{
			while ((line = read.readLine()) !=null)
			{
				System.out.println(line);
			}
		}
		
		//Initiate IOException if successful
		catch (IOException ioex)
		{
			System.out.println(ioex.getMessage() + "Error reading the file.");
		}
		
		finally 
		{
			System.exit(0);
		}
		
	}

}
