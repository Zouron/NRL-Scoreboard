/**
 * Student ID: 18267684
 * Name: Jason Rego
 * Campus: Campbelltown
 * Tutor Name: Paul Davies
 * Class Day: Friday
 * Class Time: 11:00 AM
 */
import java.io.File;
import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main_Program {
	/**
	 * The main method of this program. Contains the methods do the startup and menu	
	 * @param args
	 * @throws FileNotFoundException
	 */
	public static void main(String[] args) throws FileNotFoundException {
		Team teamList[] = new Team[Team.TOTAL_TEAMS];
		Round nrlRounds[] = new Round [Round.TOTAL_ROUNDS];
		programStartUp(teamList, nrlRounds);
		goToMainMenu(teamList,nrlRounds);
	}
	
	/**
	 * This method does the program start-up by calling other methods to load files
	 * required for this program
	 * @param teamList - The list of teams
	 * @param nrlRounds - The list of all the rounds
	 * @throws FileNotFoundException
	 */
	public static void programStartUp(Team[] teamList, Round [] nrlRounds ) throws FileNotFoundException{
		loadTeamFile(teamList);
		loadFixtureFile(nrlRounds);
		Round.currentRound = getRoundNumberFromUser();
		loadRoundResults(teamList, nrlRounds);
	}
	
	/**
	 * This method reads the teams.txt file and loads the information on the teams
	 * and stores it into an array.
	 * @param teams - The team array to hold information on the teams
	 * @throws FileNotFoundException
	 */
	public static void loadTeamFile(Team[] teams) throws FileNotFoundException {
		String teamLine;				//Contents of each line of the team text file
		String teamTokens[];		//Array of the contents after splitting them
		
		String teamsFileName = "teams.txt";
		File teamsFile = Validate.doesFileExist(teamsFileName);
		Scanner teamsInfo = new Scanner(teamsFile);
		int index=0;
		while(teamsInfo.hasNext()){
			teamLine = teamsInfo.nextLine();
			teamTokens = teamLine.split(",");
			teams[index] = new Team (teamTokens[0], teamTokens[1], teamTokens[2]);
			index++;
		}
		teamsInfo.close();
	}
	
	/**
	 * This method reads the Fixtures.txt file and stores in the information in an array of rounds.
	 * 
	 * @param rounds
	 * @throws FileNotFoundException
	 */
	public static void loadFixtureFile(Round[] rounds) throws FileNotFoundException{
		String fixtureLine;						//Contents of each line of the fixtures
		String fixtureToken[];				//Array of the contents after splitting the line
		int fixtureCount=0;						//This will be used to count how many fixtures in each round
		int roundNumber=1;
		//This size is only temporary to hold the fixtures in this round
		Fixture fixtures[] = new Fixture[Fixture.MAX_FIXTURES];
		
		String fixtureFileName = "Fixtures.txt";
		File fixtureFile = Validate.doesFileExist(fixtureFileName);
		Scanner fixtureInfo = new Scanner(fixtureFile);
		while(fixtureInfo.hasNext()){
			fixtureLine = fixtureInfo.nextLine();
			fixtureToken = fixtureLine.split(",");
			
			//If the round number is the same as the rounds that we are counting, we initiate a round in our rounds array
			//and pass in the saved fixtures.
			if(Integer.parseInt(fixtureToken[0]) != roundNumber){
					Debug.log(fixtureCount);
				rounds[roundNumber-1] = new Round(roundNumber, fixtureCount, fixtures);
				roundNumber++;
				fixtureCount=0;
			}
			fixtures[fixtureCount] = new Fixture(roundNumber,fixtureToken);		
			fixtureCount++;
		}
		//Create another round of the remaining fixtures to avoid OBOB
		rounds[roundNumber-1] = new Round(roundNumber, fixtureCount, fixtures);
		fixtureInfo.close();
	}
	
	/**
	 * This returns a round number from the user
	 * @return - A round number
	 */
	public static int getRoundNumberFromUser(){
		return Validate.getValidNumber("Please enter the current round number", 1, 26);
	}
	
	/**
	 * This method loads the all the round files until the current round of the tournament as specified
	 * by the user. If the Round#.txt file doesn't exist for a round, then we tell the user that they need to manually
	 * input the results.
	 * @param teamList
	 * @param tournamentRound
	 * @throws FileNotFoundException
	 */
	public static void loadRoundResults(Team teamList[], Round tournamentRound[]) throws FileNotFoundException{
		int currentResultFile=1;			//Starts reading from the first file
		int resultIndex=0;						//The line number we are reading in each round text file
		int matchNumber;
		int homeScore;
		int awayScore;
		String resultLine;						//The contents of each line of the text file
		String resultToken[];					//Array of the contents after splitting the line
		String resultFileName = "Round" + currentResultFile + ".txt";
		File resultsFile = new File(resultFileName);
		
		//Main loop that does through all the Round text files
		while(currentResultFile<=Round.currentRound && resultsFile.exists()){
			Scanner resultsInfo = new Scanner(resultsFile);
			
			//Creating a temporary Result array to hold the contents of each text file
			Results tempResults[] = new Results[tournamentRound[currentResultFile-1].getNumberOfFixtures()];
			//Loop that read the contents of each round file
			while(resultsInfo.hasNext()){
				resultLine = resultsInfo.nextLine();
				resultToken = resultLine.split(",");
				
				//Parsing the strings into type int
				matchNumber = Integer.parseInt(resultToken[0]);
				homeScore = Integer.parseInt(resultToken[1]);
				awayScore = Integer.parseInt(resultToken[2]);
				//Creating a new Result with each line
				tempResults[resultIndex] = new Results(matchNumber, homeScore, awayScore);
				resultIndex++;
			}
			resultIndex=0;
			//Adding the tempResults to the main tournament round array
			tournamentRound[currentResultFile-1].insertRoundResults(tempResults, teamList);
			resultsInfo.close();
			
			//Moving on to the next Round text file
			resultFileName = "Round" + (currentResultFile+1)+".txt";
			resultsFile = new File(resultFileName);
			if(resultsFile.exists()){
				currentResultFile++;
			}
		}
		
		Debug.log("current round(static):", Round.currentRound);
		//If we don't have the files to process the results up to the current round
		//the last file that we found is the current round
		if(currentResultFile< Round.currentRound){
			Display.out("Round results for " + (currentResultFile+1)+" - " + Round.currentRound+ " not found!!");
			Display.out("Results loaded till round "+ currentResultFile);
			Display.out("Please enter remaining round results from option 2 in the main menu");
			Round.currentRound = currentResultFile;
			Debug.log("current round(static)", Round.currentRound);
		}
	}

	/**
	 * This method runs and displays the main menu of the program.
	 * Other parts of the program as accessed from this method.
	 * This method is also called again within it to make the program loop back to the main menu
	 * @param teams - The list of teams
	 * @param rounds - The list of all the rounds
	 * @throws FileNotFoundException
	 */
	public static void goToMainMenu(Team[] teams, Round[] rounds) throws FileNotFoundException{
		int max=6,min=1;
		Display.displayMenu(Menus.mainMenu);
		
		int userInput= Validate.getValidNumber("Select an option to continue", min, max);
		
		switch(userInput){
		case 1://OPTION 1 MATCH SCHEDULE
			int option2Max =2;
			Display.displayMenu(Menus.displayMatchOptions);
			userInput =Validate.getValidNumber("Select an option to continue", min, option2Max);
			if(userInput == 1){
				Display.viewAllRounds(rounds, teams);
				Display.anyKeyToContinue(Menus.enterForMainMenu);
				goToMainMenu(teams,rounds);
			}
			else {
				userInput=Validate.getValidNumber("Enter the round number you wish to view", min, Round.TOTAL_ROUNDS);
				boolean isFixture = false;
				Display.viewRound(rounds, userInput-1, isFixture,teams);
				Display.out("----------------------------------------------------------------------------------------------------------------\n");
				Display.anyKeyToContinue(Menus.enterForMainMenu);
				goToMainMenu(teams,rounds);
			}
			break;
			
		case 2://OPTION 2 ENTER ROUND RESULTS
			Debug.log(Round.currentRound);
			enterRoundResults(rounds, teams);
			Display.anyKeyToContinue(Menus.enterForMainMenu);
			goToMainMenu(teams,rounds);
			break;
			
		case 3://OPTION 3 DIPLAY LADDER
			displayLadder(teams);
			Display.anyKeyToContinue(Menus.enterForMainMenu);
			goToMainMenu(teams,rounds);
			break;
			
		case 4://OPTION 4 TEAM RESULTS
		  teamResults(rounds,teams);
		  Display.anyKeyToContinue(Menus.enterForMainMenu);
			goToMainMenu(teams,rounds);
		  break;
			
		case 5://OPTION 5 FIXTURES
			fixtureOptions(rounds,teams);
			break;
			
		case 6://OPTION 6 EXITS THE PROGRAM
			Display.out("Thank you for using this program");
			//System.exit(0);
			break;
		}
	}
	
	/**
	 * This is the method that displays the options for the different view available in
	 * the fixtures options.
	 * @param rounds
	 * @param teams
	 * @throws FileNotFoundException
	 */
	public static void fixtureOptions(Round rounds[],Team teams[]) throws FileNotFoundException{
		Display.displayMenu(Menus.fixtureOptions);
		int min=1, max=4;
		boolean isFixture = true;
		int userInput= Validate.getValidNumber("Select an option to continue", min, max);
		switch (userInput){
		case 1://OPTION 1 VIEW BY ROUNDS
			userInput=Validate.getValidNumber("Enter the round number you wish to view", 1, Round.TOTAL_ROUNDS);
			Display.viewRound(rounds, userInput-1, isFixture, teams);
			Display.out("-------------------------------------------------------------------------------------------------------------------------------\n");
			Display.anyKeyToContinue(Menus.enterForMainMenu);
			goToMainMenu(teams,rounds);
			break;
			
		case 2://OPTION 2 VIEW BY TEAMS
			int teamIndex = Validate.getTeamIndex("Please enter the name of the Team you wish to view.", teams);
			Debug.log("teamindex: ",teamIndex);
			Display.displayHeader(Menus.teamFixtureHeader);
			teamFixture(rounds, teams, teamIndex);
			Display.anyKeyToContinue(Menus.enterForMainMenu);
			goToMainMenu(teams,rounds);
			break;
			
		case 3://OPTION 3 VIEW BY VENUE
			String venue = Validate.getVenue("Please enter the name the stadium for the fixtures");
			Display.displayHeader(Menus.teamFixtureHeader);
			venueFixture(rounds,venue, teams);
			Display.anyKeyToContinue(Menus.enterForMainMenu);
			goToMainMenu(teams,rounds);
			break;
			
		case 4:
			//goToMainMenu(teams,rounds);
			break;
		}
	}
	
	/**
	 * This method takes in the the name of the venue and displays the fixtures that are played at that venue
	 * @param rounds - The array of rounds
	 * @param venue - The venue entered by the user
	 * @param teamList - The list of teams
	 */
	public static void venueFixture(Round rounds[], String venue, Team teamList[]){
		boolean noSuchStadium = true;			//To check if there are any fixtures at the stadium
		for( int i=0; i < rounds.length;i++){
			Fixture roundFixture[] = new Fixture[rounds[i].getNumberOfFixtures()];
			roundFixture = rounds[i].getRoundFixture();
			
			for(int j=0;j<roundFixture.length;j++){
				if(roundFixture[j].getMatchVenue().toLowerCase().contains(venue.toLowerCase())){
					System.out.print("Round  " + (i+1)+ "\t");
					Display.displayFixture(roundFixture[j],true, teamList);
					noSuchStadium = false;
				}
			}			
		}
		//Display message if there are no fixtures at this stadium 
		if(noSuchStadium){
			Display.out("Sorry, this stadium either doesn't exist or there are no games played here this season " );
		}
		Display.out("---------------------------------------------------------------------------------------------------------------------------------------------------");
	}
	
	/**
	 * This method takes in the index of the team that is playing and displays all the games in which are playing.
	 * @param rounds - The array of our rounds
	 * @param teamList - The team list
	 * @param teamIndex - The index of the team that we want to view
	 */	
	public static void teamFixture(Round rounds[], Team teamList[], int teamIndex){
		int fixtureIndex=0;
		boolean hasTeamPlayed =false;
		
		for(int i=0;i<rounds.length;i++){
			Fixture roundFixture[] = new Fixture[rounds[i].getNumberOfFixtures()];
			roundFixture = rounds[i].getRoundFixture();
			fixtureIndex=0;
			hasTeamPlayed = false;
			while(fixtureIndex<rounds[i].getNumberOfFixtures() && !hasTeamPlayed){
				if(roundFixture[fixtureIndex].getAwayTeam().equals(teamList[teamIndex].getTeamName())
						||roundFixture[fixtureIndex].getHomeTeam().equals(teamList[teamIndex].getTeamName())){
					System.out.print("Round  " + (i+1)+ "\t");
					Display.displayFixture(roundFixture[fixtureIndex],true,teamList);
					hasTeamPlayed=true;
				}
				fixtureIndex++;
			}
		}
		Display.out("---------------------------------------------------------------------------------------------------------------------------------------------------");
	}
	
	/**
	 * This method displays the results of the team for which the user chooses
	 * @param rounds -The array of rounds
	 * @param teams - The team list
	 */
	public static void teamResults(Round rounds[], Team teams[] ){
		//We get the index of the team the user wants to view the results for
		int userTeamIndex = Validate.getTeamIndex("Enter name of the team for which you would like to view the matches", teams);
	  int roundCount=1;
	  boolean teamHasPlayed;			//To check if the team has played this round
	  int teamPlayedIndex;				//The index of the team our users team played against
	  
	  String outcome="";					//Result of the game Win/Loss/Draw
	  Fixture roundFixture[];			
	  String date="";
	  String teamPlayed="";
	  String score="";
	  
	  Display.out(teams[userTeamIndex].getTeamName() + " " + teams[userTeamIndex].getMascotName());
	  Display.out("Match results for rounds 1 to " + Round.currentRound);
	  Display.displayHeader(Menus.teamResultsHeader);
	  
		while(roundCount<=Round.currentRound)
		{
			teamHasPlayed = false;
			roundFixture = new Fixture[rounds[roundCount-1].getNumberOfFixtures()];
			roundFixture = rounds[roundCount-1].getRoundFixture();
			for (int i=0;i<roundFixture.length;i++)
			{
				if(teams[userTeamIndex].getTeamName().equals(roundFixture[i].getHomeTeam())){
					teamHasPlayed = true;
					teamPlayedIndex = Round.getTeamIndex(roundFixture[i].getAwayTeam(), teams);
					outcome = matchOutcome(roundFixture[i].getHomeTeamScore(),roundFixture[i].getAwayTeamScore());
					date = roundFixture[i].getMatchDate();
					teamPlayed = teams[teamPlayedIndex].getTeamName() + " " + teams[teamPlayedIndex].getMascotName();
					score = roundFixture[i].getHomeTeamScore() + " " + roundFixture[i].getAwayTeamScore();
				}
				if(teams[userTeamIndex].getTeamName().equals(roundFixture[i].getAwayTeam() )){
					teamHasPlayed = true;
					teamPlayedIndex = Round.getTeamIndex(roundFixture[i].getHomeTeam(), teams);
					outcome = matchOutcome(roundFixture[i].getAwayTeamScore(),roundFixture[i].getHomeTeamScore());
					date = roundFixture[i].getMatchDate();
					teamPlayed = teams[teamPlayedIndex].getTeamName() + " " + teams[teamPlayedIndex].getMascotName();
					score = roundFixture[i].getHomeTeamScore() + "-" + roundFixture[i].getAwayTeamScore();
				}
			}
			//Only display if the team has played
			if(teamHasPlayed){
				Display.teamResults(roundCount, date, teamPlayed, outcome, score);
			}
			roundCount++;
		}
		Display.out("-------------------------------------------------------------------------");
		Display.out("Total Competition points after " + Round.currentRound+ " rounds: " + teams[userTeamIndex].getTotalPoints());
	}
	
	public static String matchOutcome(int score1, int score2){
		if(score1>score2)
			return "Win";
		else if (score2>score1)
			return "Loss";
		else
			return "Draw";
	}
	
	/**
	 * This method prompts the user to enter the results for each match of the next round
	 * @param rounds
	 * @param teams
	 * @throws FileNotFoundException
	 */
	public static void enterRoundResults( Round rounds[], Team teams[]) throws FileNotFoundException{
		Display.out("We are currently in round " + Round.currentRound + " of this season.");
		Display.out("Please have the results of round "+ (Round.currentRound+1)+ " ready.");
		Display.anyKeyToContinue("Hit ENTER when you are ready to enter the results...");
		
		Results userResults[] = new Results[rounds[Round.currentRound].getNumberOfFixtures()];	//Creating a placeholder for user to enter the results
		
		Fixture roundFixture[] = new Fixture[rounds[Round.currentRound].getNumberOfFixtures()];	//Creating a placeholder for the fixtures of the round
		
		roundFixture = rounds[Round.currentRound].getRoundFixture();			//Copying the information of the fixture of the round
		getResultsFromUser(roundFixture,userResults);											//Getting the results from the user
		rounds[Round.currentRound].insertRoundResults(userResults, teams);//Inserting the user entered resutls to the array 
		createResultFile(userResults);																		//Creating the results file for the new round entered by the user
	}
	
	/**
	 * This method sorts the ladder and displays it at the same time.
	 * The order of the ladder isn't changed in the array but only displayed in the correct order
	 * We create another array of booleans to check if we have already displayed the element.
	 * If the element if displayed we don't look at it and we don't display it again.
	 * @param teamList
	 */
	public static void displayLadder( Team teamList[])
	{
		int highestIndex =0;
		boolean isTeamDisplayed[] = new boolean[teamList.length]; /// An array of booleans to check to see if we have already displayed a particular element
		int startAt=0;				
		
		Display.displayHeader(Menus.ladderHeader);
			
		for(int i=0; i<teamList.length; i++ ){
			//if we have already displayed the first index move to the next one
			while(isTeamDisplayed[startAt]){
				startAt++;
			}
			//Assume the starting index is the highest
			highestIndex = startAt;
			for(int j=startAt; j<teamList.length; j++){
				//if we find an element that is higher or equal
				if(teamList[j].getTotalPoints() >= teamList[highestIndex].getTotalPoints()){				
					//if it isn't already displayed
					if(!isTeamDisplayed[j]){
						//Test if they are equal then we need to see their score differential
						if(teamList[j].getTotalPoints() == teamList[highestIndex].getTotalPoints()){
							if( teamList[j].getScoreDifferential()> teamList[highestIndex].getScoreDifferential()){
								highestIndex = j;					//The element we are looking at is the highest
							}
						}else{
							//if the element is not equal it must be the highest element
							highestIndex =j;
						}
					}
				}
			}
			Display.teamLadder(teamList[highestIndex], (i+1));
			isTeamDisplayed[highestIndex] = true;
		}
		Display.out("-------------------------------------------------------------------------------------------------------------");
	}
	
	/**
	 * This method takes in the Results array that we created from the results enterd by the user
	 * and then create a new text file with it
	 * @param results
	 * @throws FileNotFoundException
	 */
	public static void createResultFile(Results results[]) throws FileNotFoundException{
		String fileName = "Round" + (Round.currentRound+1) + ".txt";
		PrintWriter resultFile = new PrintWriter(fileName);
		
		for(int i=0; i<results.length;i++){
			resultFile.println(results[i].getMatchNumber() + ","
					+ results[i].getHomeTeamScore() + ","
					+ results[i].getAwayTeamScore());
		}
		Round.currentRound++;
		Debug.log(Round.currentRound);
		resultFile.close();
		
		Display.out("Thank you for entering the results for round " + (Round.currentRound));
	}
	
	/**
	 * This method receives an array of the results and stores the values as entetred by the user 
	 * @param fixture
	 * @param results - An array to store the results
	 */
	public static void getResultsFromUser(Fixture fixture[],Results results[]){
		String homeTeam;
		String awayTeam;
		int homeScore;
		int awayScore;
		for(int i=0; i <results.length;i++){
			
			homeTeam = fixture[i].getHomeTeam();
			awayTeam = fixture[i].getAwayTeam();
			
			Display.out("Match " + (i+1));
			Display.out(homeTeam + " vs " + awayTeam);
			//Asking the user to enter the scores
			homeScore = Validate.getValidNumber(("Enter the points scored by " + homeTeam+":"),0,100);
			awayScore = Validate.getValidNumber(("Enter the points scored by " + awayTeam+":"),0,100);
			results[i] = new Results(fixture[i].getMatchNumber(), homeScore,awayScore);	//Storing the results in the array of results				
		}
		
	}
	
}
