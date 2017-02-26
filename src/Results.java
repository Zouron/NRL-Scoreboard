/**
 * Student ID: 18267684
 * Name: Jason Rego
 * Campus: Campbelltown
 * Tutor Name: Paul Davies
 * Class Day: Friday
 * Class Time: 11:00 AM
 */
public class Results {
	private int matchNumber;         //The match number of the game
	private int homeTeamScore;       //The goals scored by the home team
	private int awayTeamScore;       //The goals scored by the away team
	
	/**
	 * This method returns the match number of the game
	 * @return -The match number of the 
	 */
	public int getMatchNumber(){
		return matchNumber;
	}
	
	/**
	 * This method returns goals scored by the home team
	 * @return The home team score
	 */
	public int getHomeTeamScore(){
		return homeTeamScore;
	}
	
	/**
	 * This method returns the goals scored by the away team
	 * @return - Away team score
	 */
	public int getAwayTeamScore(){
		return awayTeamScore;
	}
	
	/**
	 * This constructor creates the object with the given match number, home team score and away team score 
	 * @param match -the match number
	 * @param homeScore - The home team score
	 * @param awayScore - The away team score
	 */
	public Results(int match, int homeScore, int awayScore){
		matchNumber = match;
		homeTeamScore = homeScore;
		awayTeamScore = awayScore;
	}
	
	/**
	 * This constructor creates a copy of the results object passed into it 
	 * @param newResults - The object to be copied
	 */
	public Results( Results newResults){
		matchNumber = newResults.matchNumber;
		homeTeamScore = newResults.homeTeamScore;
		awayTeamScore = newResults.awayTeamScore;
	}
			
}
