/**
 * Student ID: 18267684
 * Name: Jason Rego
 * Campus: Campbelltown
 * Tutor Name: Paul Davies
 * Class Day: Friday
 * Class Time: 11:00 AM
 */
public class Team_18267684 {
	public static final int TOTAL_TEAMS=16;    //The maximum number of teams
	
	private String teamName;                 //The name of the team
	private String teamMascot;               //The mascots name
	private String homeGround;               //The teams home ground
	      
	private int gamesPlayed;         //Total number of games played
	private int gamesWon;            //Total number of games won
	private int gamesLost;           //Total number of games lost
	private int byes;                //Total byes awarded to the team
	private int pointsScored;        //Total goals scored by the team
	private int pointsCeded;         //Total goals ceded by the team
	
	//These are all the getters/ Accessors for the Team Class
	
	/**
	 * This method returns the name of the team
	 * @return the name of the team
	 */
	public String getTeamName(){
		return teamName;
	}
	
	/**
	 * This method returns the mascots name
	 * @return - The team mascot name
	 */
	public String getMascotName(){
		return teamMascot;
	}
	
	/**
	 * This method returns the home ground of the team
	 * @return - The home ground
	 */
	public String getHomeGround(){
		return homeGround;
	}
	
	/**
	 * This method returns the number of games played by the team
	 * @return - The number of games
	 */
	public int getGamesPlayed(){
		return gamesPlayed;
	}
	
	/**
	 * This method returns the number of games won by the team
	 * @return - The number of games won
	 */
	public int getGamesWon(){
		return gamesWon;
	}
	
	/**
	 * This method returns the number of games lost by the team 
	 * @return - The number of games won
	 */
	public int getGamesLost(){
		return gamesLost;
	}
	
	/**
	 * This method returns the number of games drawn by the team 
	 * @return - The number of games drawn
	 */
	public int getGamesDrawn(){
		return gamesPlayed - (gamesWon+gamesLost); 
	}
	
	/**
	 * This method returns the number of byes awarded to the team 
	 * @return The number of byes
	 */
	public int getByes(){
		return byes;
	}
	
	/**
	 * This method returns the total number of goals scored by the team
	 * @return - The total goals scored
	 */
	public int getPointsScored(){
		return pointsScored;
	}
	
	/**
	 * This method returns the total number of goals ceded by the team
	 * @return the total goals ceded
	 */
	public int getPointsCeded(){
		return pointsCeded;
	}
	
	/**
	 * This method calculates and returns the total competition points gained by the team
	 * @return The total number of competition points
	 */
	public int getTotalPoints(){
		return (gamesWon * 2)
				+ (gamesPlayed - (gamesWon + gamesLost)
				+ (byes*2));
	}
	
	/**
	 * This constructor creates a team with the specified name, mascot and home ground as the stadium
	 * @param name -the team name
	 * @param mascot - The teams mascot
	 * @param stadium - The home ground
	 */
	public Team_18267684 (String name, String mascot, String stadium){
		teamName = name;
		teamMascot = mascot;
		homeGround = stadium;
		gamesPlayed=0;
		gamesWon=0;
		gamesLost=0;
		byes=0;
		pointsScored =0;
		pointsCeded =0;
	}
	
	/**
	 * This method increments the number of games won and games played by the team
	 */
	public void addGamesWon(){
		gamesWon++;
		gamesPlayed++;
	}
	
	/**
	 * This method increments the number of games lost and games played by the team
	 */
	public void addGamesLost(){
		gamesLost++;
		gamesPlayed++;
	}
	
	/**
	 * This method increments the goals scored by the team 
	 * @param amount - Goals scored in the given match
	 */
	public void addPointsScored(int amount){
		pointsScored += amount;
	}
	
	/**
	 * This method increments the goals ceded by the team
	 * @param amount - Goals ceded by the team in the given match
	 */
	public void addPointsCeded(int amount){
		pointsCeded += amount;
	}
	
	
	/**
	 * This method awards the team a bye point
	 */
	public void awardByePoints(){
		byes++;
	}
	
	/**
	 * This method returns the point differential of the team
	 * the difference between the goals scored and goals ceded 
	 * @return the score differential
	 */
	public int getScoreDifferential(){
		return pointsScored-pointsCeded;
	}
	
	/**
	 * This method sets the stats for the team when they win a game
	 * @param forPoints - The goals they scored
	 * @param pointsAgainst - The goals they ceded
	 */
	public void setWinStats(int forPoints, int pointsAgainst){
		gamesWon++;
		gamesPlayed++;
		pointsScored+=forPoints;
		pointsCeded+=pointsAgainst;
	}
	
	/**
	 * This method sets the stats for the team when they draw a game
	 * @param pointsScored - points scored by both teams(will be the same when they draw)
	 */
	public void setDrawStats(int pointsScored){
		gamesPlayed++;
		pointsScored += pointsScored;
		pointsCeded += pointsScored;
	}
	
	/**
	 * this method sets the stats for the team when they lose a game
	 * @param forPoints - The goals they scored
	 * @param pointsAgainst - The goals they ceded
	 */
	public void setLossStats(int forPoints, int pointsAgainst){
		gamesLost++;
		gamesPlayed++;
		pointsScored+=forPoints;
		pointsCeded+=pointsAgainst;
	}
}
