/**
 * Student ID: 18267684
 * Name: Jason Rego
 * Campus: Campbelltown
 * Tutor Name: Paul Davies
 * Class Day: Friday
 * Class Time: 11:00 AM
 */
import java.util.Scanner;


public class Display {

  /**
   * This method takes in the options for the particular menu to be displayed
   * and prints them out.
   * @param options - a list of menu options
   */
	public static void displayMenu(String[] options){
		Display.out(options[0]);
		for(int i=1;i<options.length;i++){
			System.out.println((i)+". "+ options[i]);
		}
	}
	
	/**
	 * This method takes in the a particular header defined in the Menu class and displays it in a proper format
	 * @param header - from the Menu class
	 */
	public static void displayHeader( String [] header){
		for(int i=0; i < header.length; i++){
			System.out.println(header[i]);
		}
	}
	
	/**
	 * A shorter implementation of the java method
	 * @param message - message to be displayed
	 */
	public static void out(String message){
		System.out.println(message);
	}

	/**
	 * An overloaded method of the out method to take an int
	 * @param number - number to be displayed
	 */
	public static void out(int number){
		System.out.println(number);
	}
	
	/**
	 * This method goes through the all the rounds and calls another method to display each round
	 * @param round - An array of all our rounds
	 * @param teamList - The array of our teams
	 */
	public static void viewAllRounds(Round[] round, Team teamList[]){
		for(int i=0; i <Round.TOTAL_ROUNDS;i++ ){
			viewRound(round, i, false, teamList);
			Display.out("----------------------------------------------------------------------------------------------------------------\n");
		}
	}
	
	/**
	 * This method output title for the round and the round number
	 * @param roundNumber
	 */
	public static void roundTitle(int roundNumber){
		Display.out("Round " + roundNumber + " Matches");
	}
	
	/**
	 * This method displays the fixtures in any specified round. 
	 * @param round - All the rounds of the season
	 * @param index - The index of the round to be displayed
	 * @param isFixture - If the round is being displayed for the schedule or the fixture
	 * @param teamList - The list of the teams playing
	 */
	public static void viewRound(Round[] round,int index, boolean isFixture, Team teamList[]){
		roundTitle(index+1);
		
		//Displaying different header based on the view type
		if(isFixture){
			displayHeader(Menus.fixtureHeader);
		}
		else{
			displayHeader(Menus.roundHeader);
		}
		
		Fixture roundFixture[] = new Fixture[round[index].getNumberOfFixtures()];
		roundFixture = round[index].getRoundFixture();
		
		for(int i=0; i<roundFixture.length; i++){
			displayFixture(roundFixture[i], isFixture, teamList);			
		}
	}
	
	/**
	 * This method displays the details for a match of the season.
	 * If this method is called for the schedule no results are displayed.
	 * If this method is called from the fixtures options, we display the results if they have already played
	 * @param match - A particular fixture of the season 
	 * @param isFixture - If the display is for the fixtures for schedule
	 * @param teams - The list of teams
	 */
	public static void displayFixture(Fixture match, boolean isFixture, Team teams[]){
	  String score = "Not Played";
	  int teamIndex;
		//Get the team index for the home team
		teamIndex = Round.getTeamIndex(match.getHomeTeam(), teams);
		String homeTeam = match.getHomeTeam() + " " + teams[teamIndex].getMascotName();
		//Get the team index for the away team
		teamIndex =Round.getTeamIndex(match.getAwayTeam(), teams);
		String awayTeam = match.getAwayTeam() + " " + teams[teamIndex].getMascotName();
		
		System.out.print(match.getMatchDate() + singleTabSpace(match.getMatchDate()));
		System.out.print(homeTeam + tripleTabSpace(homeTeam) );
		System.out.print(awayTeam + tripleTabSpace(awayTeam) );
		System.out.print(match.getMatchVenue() +doubleTabSpace(match.getMatchVenue()) );
		System.out.print(match.getMatchTime() + "\t\t");
		
		//If the output is for the fixture display display the score, if it isn't
		if(isFixture){
			if(match.getAwayTeamScore() <0){
				Display.out(score);
			}
			else{
				score = match.getAwayTeamScore() + "-" + match.getHomeTeamScore();
				System.out.println(score);
			}
		}
		else{
			System.out.println("");
		}
	}
	
	/**
	 * This method displays the teams in a proper format for the team ladder display
	 * @param team - the list of teams
	 * @param rank - the rank of the team
	 */
	public static void teamLadder(Team team, int rank){
		String teamName = team.getTeamName() + " " + team.getMascotName();
		Display.out(rank + "\t" /*+  team.getTeamName() + doubleTabSpace(team.getTeamName())
								+ team.getMascotName() + singleTabSpace(team.getMascotName())*/
								+ teamName + tripleTabSpace(teamName)
								+ team.getGamesPlayed() + "\t"
								+ team.getGamesWon() + "\t"
								+ team.getGamesLost() + "\t"
								+ team.getGamesDrawn() + "\t"
								+ team.getByes() + "\t"
								+ team.getPointsScored() + "\t"
								+ team.getPointsCeded() + "\t"
								+ team.getTotalPoints());
		
		
	}
	
	/**
	 * This method takes in the string to be output and calculates the correct amount of tab
	 * spaces required to give a neat output with one tab space
	 * @param output - string we just displayed
	 * @return - the number of tab spaces required
	 */
	private static String singleTabSpace(String output){
		if(output.length()<8)
			return "\t\t";
		else
			return "\t";			
	}
	
	/**
	 * This method takes in the sting to be output and calculates the correct amount of tab
   * spaces required to give a neat output with two tab spaces
	 * @param output
	 * @return
	 */
	private static String doubleTabSpace(String output){
		if(output.length()<=7)
			return "\t\t\t";
		else if(output.length()<=15)
			return "\t\t";
		else
			return "\t";
	}
	
	/**
	 * This method takes in the sting to be output and calculates the correct amount of tab
   * spaces required to give a neat output with three tab spaces
	 * @param output
	 * @return
	 */
	private static String tripleTabSpace( String output){
		if(output.length()<=15)
			return "\t\t\t";
		else if(output.length()<=22)
			return "\t\t";
		else return "\t";
	}
	
	
	/**THE OTHER THREE OUTPUT TAB SPACE WERE SUFFICIENT
   * This method takes in the sting to be output and calculates the correct amount of tab
   * spaces required to give a neat output with four tab spaces
   * @param output
   * @return
   */
	private static String fourTabSpace( String output){
		if(output.length()<8)
			return "\t\t\t\t";
		else if(output.length()<16)
			return "\t\t\t";
		else if(output.length()<24)
			return "\t\t";
		else return "\t";
	}
	
	/**
	 * This method will be used to prompt the user to hit any key to continue the program
	 * @param message - Message to be displayed to the user
	 */
	public static void anyKeyToContinue(String message){
		Display.out(message);
		Scanner kb = new Scanner(System.in);
		kb.nextLine();
		
	}

	/**
	 * This method takes in the the details for a particular team and displays them.
	 * This is called for the Menu Option 4: Team Results
	 * @param roundNumber - The round number
	 * @param date - The date of the match
	 * @param teamPlayed - The team they played against
	 * @param outcome - Win,Loss or Draw
	 * @param score - The result of the match(home score - away score)
	 */
	public static void teamResults(int roundNumber, String date, String teamPlayed,String outcome, String score ){
		System.out.print(roundNumber + "\t");
		System.out.print(date+ singleTabSpace(date));
		System.out.print(teamPlayed + tripleTabSpace(teamPlayed));
		System.out.print(outcome + "\t");
		System.out.println(score);
	}
}


/**THESE ARE VARIOUS ATTEMPTS AT GETTING THE CORRECTLY FORMATTED OUTPUT THAT DIDN'T WORK VERY WELL
/*
	*public static void viewRound(Round round[], int index){
	*  Display.out("Round " + (index+1) + " Matches");
	*  //Display.displayMenu(Menus.roundHeader);
	  
	*  Display.out("-------------------------------------------------------------------------------------------------");
	 * Display.out("Date\t\tHome Team\t\tAway Team\t\tVenue\t\t\tKick-Off");
	  Display.out("-------------------------------------------------------------------------------------------------");
	  *
	  Fixture roundFixture[] = new Fixture[round[index].getNumberOfFixtures()];
	  roundFixture = round[index].getRoundFixture();
	  
	  //StringBuilder displayLine = new StringBuilder("");
	  
	  
	  for (int i=0; i< roundFixture.length;i++){
	    StringBuilder displayLine = new StringBuilder(roundFixture[i].getMatchDate() +"                                ");
	    //displayLine.insert(0, roundFixture[i].getMatchDate());
	    displayLine.insert(roundFixture[i].getMatchDate().length()+2, roundFixture[i].getHomeTeam());
	    displayLine.insert(12+roundFixture[i].getHomeTeam().length()+2, roundFixture[i].getHomeTeam());
	    displayLine.insert(43, roundFixture[i].getAwayTeam());
	    displayLine.insert(43 + roundFixture[i].getAwayTeam().length()+2, roundFixture[i].getAwayTeam());
	    displayLine.insert(77, roundFixture[i].getMatchVenue());
	    displayLine.insert(92, roundFixture[i].getMatchTime());
	    System.out.println(displayLine);
	  }
	  
	}
	
	
	//These are iterations of how to display the 
	/*
	String line = (roundFixture[i].getMatchDate()+"\t"
			+roundFixture[i].getHomeTeam() 
			+roundFixture[i].getAwayTeam()
			+roundFixture[i].getMatchVenue()
			+roundFixture[i].getMatchTime());
	System.out.println(line);
	
	
	System.out.print(roundFixture[i].getMatchDate() + "\t");
	System.out.print(roundFixture[i].getHomeTeam() + "\t");
	System.out.print(roundFixture[i].getAwayTeam() + "\t");
	System.out.print(roundFixture[i].getMatchVenue() + "\t");
	System.out.println(roundFixture[i].getMatchTime() + "\t");
	
	
	
	System.out.println(roundFixture[i].getMatchDate() + "\t"
						+roundFixture[i].getHomeTeam() + "\t"
						+roundFixture[i].getAwayTeam() + "\t"
						+roundFixture[i].getMatchVenue() + "\t"
						+roundFixture[i].getMatchTime() + "\t");
*/