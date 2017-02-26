/**
 * Student ID: 18267684
 * Name: Jason Rego
 * Campus: Campbelltown
 * Tutor Name: Paul Davies
 * Class Day: Friday
 * Class Time: 11:00 AM
 */
import java.io.File;
import java.util.Scanner;


public class Validate {
	private static Scanner kb = new Scanner(System.in);
	
	/**
	 * This method checks to see if the file being read actually exits before reading it.
	 * If it does not exist, the user will be asked to enter the location of the file.
	 * @param fileName - The name of the file
	 * @return readFile - the new File if a new one had to be specified, the same if the file already exists.
	 */
	public static File doesFileExist(String fileName){
		File readFile = new File(fileName);
		while (!readFile.exists()){
			System.out.println("File " + fileName + " not found!!");
			System.out.println("Please specify file name and location");
			fileName = kb.nextLine();
			Debug.log("file name: " + fileName);
			readFile = new File (fileName);
		}
		return readFile;
	}
	
	/**
	 * This method asks the user to enter a team name, verifies it and then returns the index of that
	 * team as stored in the team list.
	 * @param message - The message that prompts the user to enter the name of the team
	 * @param teamList - The team list.
	 */
	public static int getTeamIndex(String message, Team teamList[]){
	  String userTeamName;
	  int teamIndex =-1;
	  int minLength=4;
	  do{
	    Display.out(message);
	    userTeamName = kb.next();
	    Debug.log("userteamName: "+ userTeamName);
	    if(userTeamName.length()>minLength){
	    	teamIndex = isATeam(userTeamName,teamList);
	    }
	    
	    if(teamIndex<0){
	      Display.out(userTeamName + " is not a valid team name");
	      Display.out("Please enter a valid team name that is longer than 5 characters");
	      userTeamName = kb.next();
	    }
	  }while(teamIndex<0);
	  
	  return teamIndex;
	}
	
	/**
	 * This method takes the user input and checks to see if it matches any of the teams listed
	 * in the team array.
	 * @param userInput - The string entered by the user
	 * @param teamList - The list of teams
	 * @return
	 */
	private static int isATeam(String userInput, Team teamList[] ){
	  int index=-1;
	  for(int i=0; i <teamList.length; i++){
	    if(userInput.equalsIgnoreCase(teamList[i].getTeamName()) 
	    		|| userInput.equalsIgnoreCase(teamList[i].getMascotName()) 
	    		|| (teamList[i].getMascotName()+teamList[i].getTeamName()).toLowerCase().contains(userInput) )
	      index=i;
	  }
	  return index;
	}
	
	/**
	 * This method takes in an integer input from the user and checks to see if it is with range.
	 * If it isn't we will ask the user to re-enter the number.
	 * @param message - Message to be displayed to ask the user to enter the number 
	 * @param min - The minimum range of our valid options
	 * @param max - The maximum range of out valid options
	 * @return - The user input that is valid
	 */
	public static int getValidNumber( String message,int min, int max){
		int userInput;
		do{
			System.out.println(message);
			userInput = kb.nextInt();
			if(userInput<min || userInput>max){
				System.out.println("Input is invalid.");
				System.out.printf("Please enter a value between %d and %d. ", min, max);
			}
		}while(userInput<min || userInput>max);
		
		return userInput;
	}

	public static String getVenue(String message){
		String venue;
		int minLength = 3;
		do{
			System.out.println(message);
			venue = kb.next();
			if(venue.length()<minLength){
				System.out.println("Name of the stadium must be alteast 3 charaters long");
			}
		}while(venue.length()<minLength);
		
		return venue;
	}
}
