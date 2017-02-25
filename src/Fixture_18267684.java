/**
 * Student ID: 18267684
 * Name: Jason Rego
 * Campus: Campbelltown
 * Tutor Name: Paul Davies
 * Class Day: Friday
 * Class Time: 11:00 AM
 */
public class Fixture_18267684 {
	
	public final static int MAX_FIXTURES =8; 
	
	private int roundNumber;   //The round the match is being played in
	private int matchNumber;   //The match number of the game
	private String homeTeam;   //The name of the home team
	private String awayTeam;   //The name of the away team
	private String matchDate;  //The date when the match is being played
	private String matchVenue; //The stadium where the match is held
	private String matchTime;  //The time the game of kicked-off
	
	private int homeTeamScore; //The goals scored by the home team
	private int awayTeamScore; //The goals scored by the away team
	
	/**
	 * This method gives us the home team score
	 * @return - The home team score
	 */
	public int getHomeTeamScore(){
		return homeTeamScore;
	}
	
	/**
	 * This method gives us the away team score
	 * @return - The away team score
	 */
	public int getAwayTeamScore(){
		return awayTeamScore;
	}
	
	/**
	 * This method gives us the round number
	 * @return - The round number
	 */
	public int getRoundNumber(){
		return roundNumber;
	}
	
	/**
	 * This method gives us the match number
	 * @return -The match number
	 */
	public int getMatchNumber(){
		return matchNumber;
	}
	
	/**
	 * This method returns the home team name
	 * @return - Home team name
	 */
	public String getHomeTeam(){
		return homeTeam;
	}
	
	/**
	 * This method returns the away team name
	 * @return - away team name
	 */
	public String getAwayTeam(){
		return awayTeam;
	}
	
	/**
	 * This method returns the date of the match
	 * @return - the match date
	 */
	public String getMatchDate(){
		return matchDate;
	}
	
	/**
	 * This method returns the venue the game is being played
	 * @return -The venue
	 */
	public String getMatchVenue(){
		return matchVenue;
	}
	
	/**
	 * This method returns the time when the game is going to be played
	 * @return - The kick-off time
	 */
	public String getMatchTime(){
		return matchTime;
	}
	
	/**
	 * This method sets the home team score
	 * @param score - The goals scored by the home team
	 */
	public void setHomeTeamScore(int score){
		homeTeamScore = score;
	}
	
	/**
	 * This method sets the away team score
	 * @param score - The goals scored by the away team
	 */
	public void setAwayTeamScore(int score){
		awayTeamScore = score;
	}
	
	/**
	 * This is a constructor that takes in a fixture and creates a new fixture with same values,
	 * essentially making a copy of this fixture
	 * @param newFixture - The fixture that is passed in and to be copied
	 */
	public Fixture_18267684(Fixture_18267684 newFixture){
		roundNumber = newFixture.roundNumber;
		matchNumber = newFixture.matchNumber;
		homeTeam = newFixture.homeTeam;
		awayTeam = newFixture.awayTeam;
		matchDate = newFixture.matchDate;
		matchVenue = newFixture.matchVenue;
		matchTime = newFixture.matchTime;
		
		homeTeamScore=-1;
		awayTeamScore=-1;
	}
	
	/**
	 * This constructor starts the takes in the round number and instantiates the fixture,
	 * with the data from the token string 	
	 * @param roundNo - The number of rounds
	 * @param token - A string array that has information about the fixture(match number, home team,
	 *               away team, venue, time, date)
	 */
	public Fixture_18267684(int roundNo, String token[]){
		roundNumber = roundNo;
		matchNumber = Integer.parseInt(token[1]);
		homeTeam = token[2];
		awayTeam = token [3];
		matchVenue = token[4];
		matchTime = token[5];
		matchDate = token[6];				
	}
	
	//not using this
	/*
	public Fixture (int round, int match, String home, String away, String date, String stadium, String time){
		
		//change these to 
		roundNumber = round;
		matchNumber = match;
		homeTeam = home;
		awayTeam = away;
		matchDate = date;
		matchVenue = stadium;
		matchTime = time;
		
		homeTeamScore=0;
		awayTeamScore=0;
		}*/

}
