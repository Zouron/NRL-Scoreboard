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
		Team_18267684 teamList[] = new Team_18267684[Team_18267684.TOTAL_TEAMS];
		Round_18267684 nrlRounds[] = new Round_18267684 [Round_18267684.TOTAL_ROUNDS];
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
	public static void programStartUp(Team_18267684[] teamList, Round_18267684 [] nrlRounds ) throws FileNotFoundException{
		loadTeamFile(teamList);
		loadFixtureFile(nrlRounds);
		Round_18267684.currentRound = getRoundNumberFromUser();
		loadRoundResults(teamList, nrlRounds);
	}
	
	/**
	 * This method reads the teams.txt file and loads the information on the teams
	 * and stores it into an array.
	 * @param teams - The team array to hold information on the teams
	 * @throws FileNotFoundException
	 */
	public static void loadTeamFile(Team_18267684[] teams) throws FileNotFoundException {
		String teamLine;				//Contents of each line of the team text file
		String teamTokens[];		//Array of the contents after splitting them
		
		String teamsFileName = "teams.txt";
		File teamsFile = Validate_18267684.doesFileExist(teamsFileName);
		Scanner teamsInfo = new Scanner(teamsFile);
		int index=0;
		while(teamsInfo.hasNext()){
			teamLine = teamsInfo.nextLine();
			teamTokens = teamLine.split(",");
			teams[index] = new Team_18267684 (teamTokens[0], teamTokens[1], teamTokens[2]);
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
	public static void loadFixtureFile(Round_18267684[] rounds) throws FileNotFoundException{
		String fixtureLine;						//Contents of each line of the fixtures
		String fixtureToken[];				//Array of the contents after splitting the line
		int fixtureCount=0;						//This will be used to count how many fixtures in each round
		int roundNumber=1;
		//This size is only temporary to hold the fixtures in this round
		Fixture_18267684 fixtures[] = new Fixture_18267684[Fixture_18267684.MAX_FIXTURES];
		
		String fixtureFileName = "Fixtures.txt";
		File fixtureFile = Validate_18267684.doesFileExist(fixtureFileName);
		Scanner fixtureInfo = new Scanner(fixtureFile);
		while(fixtureInfo.hasNext()){
			fixtureLine = fixtureInfo.nextLine();
			fixtureToken = fixtureLine.split(",");
			
			//If the round number is the same as the rounds that we are counting, we initiate a round in our rounds array
			//and pass in the saved fixtures.
			if(Integer.parseInt(fixtureToken[0]) != roundNumber){
					Debug_18267684.log(fixtureCount);
				rounds[roundNumber-1] = new Round_18267684(roundNumber, fixtureCount, fixtures);
				roundNumber++;
				fixtureCount=0;
			}
			fixtures[fixtureCount] = new Fixture_18267684(roundNumber,fixtureToken);		
			fixtureCount++;
		}
		//Create another round of the remaining fixtures to avoid OBOB
		rounds[roundNumber-1] = new Round_18267684(roundNumber, fixtureCount, fixtures);
		fixtureInfo.close();
	}
	
	/**
	 * This returns a round number from the user
	 * @return - A round number
	 */
	public static int getRoundNumberFromUser(){
		return Validate_18267684.getValidNumber("Please enter the current round number", 1, 26);
	}
	
	/**
	 * This method loads the all the round files until the current round of the tournament as specified
	 * by the user. If the Round#.txt file doesn't exist for a round, then we tell the user that they need to manually
	 * input the results.
	 * @param teamList
	 * @param tournamentRound
	 * @throws FileNotFoundException
	 */
	public static void loadRoundResults(Team_18267684 teamList[], Round_18267684 tournamentRound[]) throws FileNotFoundException{
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
		while(currentResultFile<=Round_18267684.currentRound && resultsFile.exists()){
			Scanner resultsInfo = new Scanner(resultsFile);
			
			//Creating a temporary Result array to hold the contents of each text file
			Results_18267684 tempResults[] = new Results_18267684[tournamentRound[currentResultFile-1].getNumberOfFixtures()];
			//Loop that read the contents of each round file
			while(resultsInfo.hasNext()){
				resultLine = resultsInfo.nextLine();
				resultToken = resultLine.split(",");
				
				//Parsing the strings into type int
				matchNumber = Integer.parseInt(resultToken[0]);
				homeScore = Integer.parseInt(resultToken[1]);
				awayScore = Integer.parseInt(resultToken[2]);
				//Creating a new Result with each line
				tempResults[resultIndex] = new Results_18267684(matchNumber, homeScore, awayScore);
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
		
		Debug_18267684.log("current round(static):", Round_18267684.currentRound);
		//If we don't have the files to process the results up to the current round
		//the last file that we found is the current round
		if(currentResultFile< Round_18267684.currentRound){
			Display_18267684.out("Round results for " + (currentResultFile+1)+" - " + Round_18267684.currentRound+ " not found!!");
			Display_18267684.out("Results loaded till round "+ currentResultFile);
			Display_18267684.out("Please enter remaining round results from option 2 in the main menu");
			Round_18267684.currentRound = currentResultFile;
			Debug_18267684.log("current round(static)", Round_18267684.currentRound);
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
	public static void goToMainMenu(Team_18267684[] teams, Round_18267684[] rounds) throws FileNotFoundException{
		int max=6,min=1;
		Display_18267684.displayMenu(Menus_18267684.mainMenu);
		
		int userInput= Validate_18267684.getValidNumber("Select an option to continue", min, max);
		
		switch(userInput){
		case 1://OPTION 1 MATCH SCHEDULE
			int option2Max =2;
			Display_18267684.displayMenu(Menus_18267684.displayMatchOptions);
			userInput =Validate_18267684.getValidNumber("Select an option to continue", min, option2Max);
			if(userInput == 1){
				Display_18267684.viewAllRounds(rounds, teams);
				Display_18267684.anyKeyToContinue(Menus_18267684.enterForMainMenu);
				goToMainMenu(teams,rounds);
			}
			else {
				userInput=Validate_18267684.getValidNumber("Enter the round number you wish to view", min, Round_18267684.TOTAL_ROUNDS);
				boolean isFixture = false;
				Display_18267684.viewRound(rounds, userInput-1, isFixture,teams);
				Display_18267684.out("----------------------------------------------------------------------------------------------------------------\n");
				Display_18267684.anyKeyToContinue(Menus_18267684.enterForMainMenu);
				goToMainMenu(teams,rounds);
			}
			break;
			
		case 2://OPTION 2 ENTER ROUND RESULTS
			Debug_18267684.log(Round_18267684.currentRound);
			enterRoundResults(rounds, teams);
			Display_18267684.anyKeyToContinue(Menus_18267684.enterForMainMenu);
			goToMainMenu(teams,rounds);
			break;
			
		case 3://OPTION 3 DIPLAY LADDER
			displayLadder(teams);
			Display_18267684.anyKeyToContinue(Menus_18267684.enterForMainMenu);
			goToMainMenu(teams,rounds);
			break;
			
		case 4://OPTION 4 TEAM RESULTS
		  teamResults(rounds,teams);
		  Display_18267684.anyKeyToContinue(Menus_18267684.enterForMainMenu);
			goToMainMenu(teams,rounds);
		  break;
			
		case 5://OPTION 5 FIXTURES
			fixtureOptions(rounds,teams);
			break;
			
		case 6://OPTION 6 EXITS THE PROGRAM
			Display_18267684.out("Thank you for using this program");
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
	public static void fixtureOptions(Round_18267684 rounds[],Team_18267684 teams[]) throws FileNotFoundException{
		Display_18267684.displayMenu(Menus_18267684.fixtureOptions);
		int min=1, max=4;
		boolean isFixture = true;
		int userInput= Validate_18267684.getValidNumber("Select an option to continue", min, max);
		switch (userInput){
		case 1://OPTION 1 VIEW BY ROUNDS
			userInput=Validate_18267684.getValidNumber("Enter the round number you wish to view", 1, Round_18267684.TOTAL_ROUNDS);
			Display_18267684.viewRound(rounds, userInput-1, isFixture, teams);
			Display_18267684.out("-------------------------------------------------------------------------------------------------------------------------------\n");
			Display_18267684.anyKeyToContinue(Menus_18267684.enterForMainMenu);
			goToMainMenu(teams,rounds);
			break;
			
		case 2://OPTION 2 VIEW BY TEAMS
			int teamIndex = Validate_18267684.getTeamIndex("Please enter the name of the Team you wish to view.", teams);
			Debug_18267684.log("teamindex: ",teamIndex);
			Display_18267684.displayHeader(Menus_18267684.teamFixtureHeader);
			teamFixture(rounds, teams, teamIndex);
			Display_18267684.anyKeyToContinue(Menus_18267684.enterForMainMenu);
			goToMainMenu(teams,rounds);
			break;
			
		case 3://OPTION 3 VIEW BY VENUE
			String venue = Validate_18267684.getVenue("Please enter the name the stadium for the fixtures");
			Display_18267684.displayHeader(Menus_18267684.teamFixtureHeader);
			venueFixture(rounds,venue, teams);
			Display_18267684.anyKeyToContinue(Menus_18267684.enterForMainMenu);
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
	public static void venueFixture(Round_18267684 rounds[], String venue, Team_18267684 teamList[]){
		boolean noSuchStadium = true;			//To check if there are any fixtures at the stadium
		for( int i=0; i < rounds.length;i++){
			Fixture_18267684 roundFixture[] = new Fixture_18267684[rounds[i].getNumberOfFixtures()];
			roundFixture = rounds[i].getRoundFixture();
			
			for(int j=0;j<roundFixture.length;j++){
				if(roundFixture[j].getMatchVenue().toLowerCase().contains(venue.toLowerCase())){
					System.out.print("Round  " + (i+1)+ "\t");
					Display_18267684.displayFixture(roundFixture[j],true, teamList);
					noSuchStadium = false;
				}
			}			
		}
		//Display message if there are no fixtures at this stadium 
		if(noSuchStadium){
			Display_18267684.out("Sorry, this stadium either doesn't exist or there are no games played here this season " );
		}
		Display_18267684.out("---------------------------------------------------------------------------------------------------------------------------------------------------");
	}
	
	/**
	 * This method takes in the index of the team that is playing and displays all the games in which are playing.
	 * @param rounds - The array of our rounds
	 * @param teamList - The team list
	 * @param teamIndex - The index of the team that we want to view
	 */	
	public static void teamFixture(Round_18267684 rounds[], Team_18267684 teamList[], int teamIndex){
		int fixtureIndex=0;
		boolean hasTeamPlayed =false;
		
		for(int i=0;i<rounds.length;i++){
			Fixture_18267684 roundFixture[] = new Fixture_18267684[rounds[i].getNumberOfFixtures()];
			roundFixture = rounds[i].getRoundFixture();
			fixtureIndex=0;
			hasTeamPlayed = false;
			while(fixtureIndex<rounds[i].getNumberOfFixtures() && !hasTeamPlayed){
				if(roundFixture[fixtureIndex].getAwayTeam().equals(teamList[teamIndex].getTeamName())
						||roundFixture[fixtureIndex].getHomeTeam().equals(teamList[teamIndex].getTeamName())){
					System.out.print("Round  " + (i+1)+ "\t");
					Display_18267684.displayFixture(roundFixture[fixtureIndex],true,teamList);
					hasTeamPlayed=true;
				}
				fixtureIndex++;
			}
		}
		Display_18267684.out("---------------------------------------------------------------------------------------------------------------------------------------------------");
	}
	
	/**
	 * This method displays the results of the team for which the user chooses
	 * @param rounds -The array of rounds
	 * @param teams - The team list
	 */
	public static void teamResults(Round_18267684 rounds[], Team_18267684 teams[] ){
		//We get the index of the team the user wants to view the results for
		int userTeamIndex = Validate_18267684.getTeamIndex("Enter name of the team for which you would like to view the matches", teams);
	  int roundCount=1;
	  boolean teamHasPlayed;			//To check if the team has played this round
	  int teamPlayedIndex;				//The index of the team our users team played against
	  
	  String outcome="";					//Result of the game Win/Loss/Draw
	  Fixture_18267684 roundFixture[];			
	  String date="";
	  String teamPlayed="";
	  String score="";
	  
	  Display_18267684.out(teams[userTeamIndex].getTeamName() + " " + teams[userTeamIndex].getMascotName());
	  Display_18267684.out("Match results for rounds 1 to " + Round_18267684.currentRound);
	  Display_18267684.displayHeader(Menus_18267684.teamResultsHeader);
	  
		while(roundCount<=Round_18267684.currentRound)
		{
			teamHasPlayed = false;
			roundFixture = new Fixture_18267684[rounds[roundCount-1].getNumberOfFixtures()];
			roundFixture = rounds[roundCount-1].getRoundFixture();
			for (int i=0;i<roundFixture.length;i++)
			{
				if(teams[userTeamIndex].getTeamName().equals(roundFixture[i].getHomeTeam())){
					teamHasPlayed = true;
					teamPlayedIndex = Round_18267684.getTeamIndex(roundFixture[i].getAwayTeam(), teams);
					outcome = matchOutcome(roundFixture[i].getHomeTeamScore(),roundFixture[i].getAwayTeamScore());
					date = roundFixture[i].getMatchDate();
					teamPlayed = teams[teamPlayedIndex].getTeamName() + " " + teams[teamPlayedIndex].getMascotName();
					score = roundFixture[i].getHomeTeamScore() + " " + roundFixture[i].getAwayTeamScore();
				}
				if(teams[userTeamIndex].getTeamName().equals(roundFixture[i].getAwayTeam() )){
					teamHasPlayed = true;
					teamPlayedIndex = Round_18267684.getTeamIndex(roundFixture[i].getHomeTeam(), teams);
					outcome = matchOutcome(roundFixture[i].getAwayTeamScore(),roundFixture[i].getHomeTeamScore());
					date = roundFixture[i].getMatchDate();
					teamPlayed = teams[teamPlayedIndex].getTeamName() + " " + teams[teamPlayedIndex].getMascotName();
					score = roundFixture[i].getHomeTeamScore() + "-" + roundFixture[i].getAwayTeamScore();
				}
			}
			//Only display if the team has played
			if(teamHasPlayed){
				Display_18267684.teamResults(roundCount, date, teamPlayed, outcome, score);
			}
			roundCount++;
		}
		Display_18267684.out("-------------------------------------------------------------------------");
		Display_18267684.out("Total Competition points after " + Round_18267684.currentRound+ " rounds: " + teams[userTeamIndex].getTotalPoints());
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
	public static void enterRoundResults( Round_18267684 rounds[], Team_18267684 teams[]) throws FileNotFoundException{
		Display_18267684.out("We are currently in round " + Round_18267684.currentRound + " of this season.");
		Display_18267684.out("Please have the results of round "+ (Round_18267684.currentRound+1)+ " ready.");
		Display_18267684.anyKeyToContinue("Hit ENTER when you are ready to enter the results...");
		
		Results_18267684 userResults[] = new Results_18267684[rounds[Round_18267684.currentRound].getNumberOfFixtures()];	//Creating a placeholder for user to enter the results
		
		Fixture_18267684 roundFixture[] = new Fixture_18267684[rounds[Round_18267684.currentRound].getNumberOfFixtures()];	//Creating a placeholder for the fixtures of the round
		
		roundFixture = rounds[Round_18267684.currentRound].getRoundFixture();			//Copying the information of the fixture of the round
		getResultsFromUser(roundFixture,userResults);											//Getting the results from the user
		rounds[Round_18267684.currentRound].insertRoundResults(userResults, teams);//Inserting the user entered resutls to the array 
		createResultFile(userResults);																		//Creating the results file for the new round entered by the user
	}
	
	/**
	 * This method sorts the ladder and displays it at the same time.
	 * The order of the ladder isn't changed in the array but only displayed in the correct order
	 * We create another array of booleans to check if we have already displayed the element.
	 * If the element if displayed we don't look at it and we don't display it again.
	 * @param teamList
	 */
	public static void displayLadder( Team_18267684 teamList[])
	{
		int highestIndex =0;
		boolean isTeamDisplayed[] = new boolean[teamList.length]; /// An array of booleans to check to see if we have already displayed a particular element
		int startAt=0;				
		
		Display_18267684.displayHeader(Menus_18267684.ladderHeader);
			
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
			Display_18267684.teamLadder(teamList[highestIndex], (i+1));
			isTeamDisplayed[highestIndex] = true;
		}
		Display_18267684.out("-------------------------------------------------------------------------------------------------------------");
	}
	
	/**
	 * This method takes in the Results array that we created from the results enterd by the user
	 * and then create a new text file with it
	 * @param results
	 * @throws FileNotFoundException
	 */
	public static void createResultFile(Results_18267684 results[]) throws FileNotFoundException{
		String fileName = "Round" + (Round_18267684.currentRound+1) + ".txt";
		PrintWriter resultFile = new PrintWriter(fileName);
		
		for(int i=0; i<results.length;i++){
			resultFile.println(results[i].getMatchNumber() + ","
					+ results[i].getHomeTeamScore() + ","
					+ results[i].getAwayTeamScore());
		}
		Round_18267684.currentRound++;
		Debug_18267684.log(Round_18267684.currentRound);
		resultFile.close();
		
		Display_18267684.out("Thank you for entering the results for round " + (Round_18267684.currentRound));
	}
	
	/**
	 * This method receives an array of the results and stores the values as entetred by the user 
	 * @param fixture
	 * @param results - An array to store the results
	 */
	public static void getResultsFromUser(Fixture_18267684 fixture[],Results_18267684 results[]){
		String homeTeam;
		String awayTeam;
		int homeScore;
		int awayScore;
		for(int i=0; i <results.length;i++){
			
			homeTeam = fixture[i].getHomeTeam();
			awayTeam = fixture[i].getAwayTeam();
			
			Display_18267684.out("Match " + (i+1));
			Display_18267684.out(homeTeam + " vs " + awayTeam);
			//Asking the user to enter the scores
			homeScore = Validate_18267684.getValidNumber(("Enter the points scored by " + homeTeam+":"),0,100);
			awayScore = Validate_18267684.getValidNumber(("Enter the points scored by " + awayTeam+":"),0,100);
			results[i] = new Results_18267684(fixture[i].getMatchNumber(), homeScore,awayScore);	//Storing the results in the array of results				
		}
		
	}
	
}
