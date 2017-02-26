/**
 * Student ID: 18267684
 * Name: Jason Rego
 * Campus: Campbelltown
 * Tutor Name: Paul Davies
 * Class Day: Friday
 * Class Time: 11:00 AM
 */
public class Round {
	public static int currentRound = 0;						//The current round number
	public static final int TOTAL_ROUNDS = 26;		//The maximum number of rounds playable
	
	private int roundNumber;									//The round number
	private int numberOfFixtures;							//The number fixtures in the round
	private Fixture[] fixtureInformation;			//An array of fixtures that hold information on the matches/fixtures in the round
	private Results[] roundResults;						//The results of the fixtures/matches in this round
	
	/**
	 * This method returns the round number of the current round
	 * @return - The round number
	 */
	public int getRoundNumber(){  
		return roundNumber;
	}
	
	/**
	 * This method returns the number of fixtures contained in this round
	 * @return - The number of fixtures
	 */
	public int getNumberOfFixtures(){
		return numberOfFixtures;
	}
	
	/**
	 * This is a constructor that creates a round object with the values passed in the parameter list
	 * @param round - The round number
	 * @param fixtureSize - The number of fixtures in the round
	 * @param tempFixtures - An array of fixtures, that contain information on each match/fixture in the round
	 */
	public Round(int round, int fixtureSize, Fixture[] tempFixtures){
		roundNumber = round;
		numberOfFixtures =  fixtureSize;
		fixtureInformation = new Fixture[numberOfFixtures];
		for(int i=0; i< numberOfFixtures; i++){
			//tempFixtures is of size 8, by using the fixtureSize we create the fixture of 
			//the appropriate size to allow for less that 8 fixtures in a round
			fixtureInformation[i] = new Fixture(tempFixtures[i]);
		}
		roundResults = new Results[numberOfFixtures];
	}
	
	/**
	 * This method takes an array of Results of a particular round( the round this methos is called on)
	 * and then appends the results to the round fixtures and also updates the teams' stats. 
	 * @param newResults - An array of Results of the round
	 * @param teamList - A list of the teams
	 */
	public void insertRoundResults(Results newResults[], Team teamList[]){
		//An array of booleans created to check if a team has played in this round
		boolean hasTeamPlayed[] = new boolean[Team.TOTAL_TEAMS];
		int homeTeamIndex;
		int awayTeamIndex;
		
		for(int i=0; i < newResults.length; i++ ){
			roundResults[i] = new Results(newResults[i]);								//Copying the new results into this round
			int homeTeamScore=roundResults[i].getHomeTeamScore();
			int awayTeamScore = roundResults[i].getAwayTeamScore();
			
			fixtureInformation[i].setHomeTeamScore(homeTeamScore);
			fixtureInformation[i].setAwayTeamScore(awayTeamScore);			
			homeTeamIndex = getTeamIndex(fixtureInformation[i].getHomeTeam(),teamList);
			awayTeamIndex = getTeamIndex(fixtureInformation[i].getAwayTeam(),teamList);
			
			//Telling our tracking array of booleans that the team has played a game in this round
			hasTeamPlayed[homeTeamIndex]=true;
			hasTeamPlayed[awayTeamIndex]=true;
			
			if(homeTeamScore>awayTeamScore){
				teamList[homeTeamIndex].setWinStats(homeTeamScore,awayTeamScore);
				teamList[awayTeamIndex].setLossStats(awayTeamScore, homeTeamScore);
			}
			else if(homeTeamScore<awayTeamScore){
				teamList[homeTeamIndex].setLossStats(homeTeamScore, awayTeamScore);
				teamList[awayTeamIndex].setWinStats(awayTeamScore, homeTeamScore);
			}
			else{
				teamList[homeTeamIndex].setDrawStats(homeTeamScore);
				teamList[awayTeamIndex].setDrawStats(awayTeamScore);
			}
			//Debug messages set to check if reading correctly ( activate in Debug class)
			Debug.log("away index",awayTeamIndex);
			Debug.log("away score", awayTeamScore);
			Debug.log("home index" ,homeTeamIndex);
			Debug.log("home score",homeTeamScore);
			Debug.log("home points",teamList[homeTeamIndex].getPointsScored());
			Debug.log(teamList[homeTeamIndex].getTeamName(), teamList[homeTeamIndex].getTotalPoints());
		}
		
		//We go through the array of boolean and if a team hasn't played we award them a bye
		for(int i=0; i <hasTeamPlayed.length; i++){
			if(!hasTeamPlayed[i]){
				teamList[i].awardByePoints();
				Debug.log("Bye awarded to :" +teamList[i].getTeamName());
			}
		}
	}

	/**
	 * This method takes in a team name and returns the index of
	 * the team name in the list of teams
	 * @param teamName - The name of the team (excluding the mascot)
	 * @param teamList - The list of the teams to chekc against
	 * @return - index of of the team in the list
	 */
	public static int getTeamIndex(String teamName, Team teamList[]){
		int index=0;
		for(int i=0; i<teamList.length; i++){
			if(teamName.equals(teamList[i].getTeamName()))
				index=i;
		}
		return index;
	}
	
	/**
	 * This method returns information of all the fixtures in this round
	 * @return - The fixture information
	 */
	public Fixture[] getRoundFixture(){
		return fixtureInformation;
	}
	
	/**
	 * Method created to test the program
	 */
	public void showFixtures(){
		for(int i=0; i<numberOfFixtures; i++){
			Debug.log(fixtureInformation[i].getHomeTeam());
		}
	}

}
