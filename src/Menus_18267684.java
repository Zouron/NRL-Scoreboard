/**
 * Student ID: 18267684
 * Name: Jason Rego
 * Campus: Campbelltown
 * Tutor Name: Paul Davies
 * Class Day: Friday
 * Class Time: 11:00 AM
 */
public class Menus_18267684 {
	
  //This array of strings contains the options for the main menu
	public static String mainMenu[] = {
		"==========MAIN MENU==========",
		"Display Match Schedule",
		"Enter Round Results",
		"Display Ladder",
		"Team Results",
		"Fixtures",
		"Exit Program"
	};
	
	//This array of strings is the header when displaying the schedule for the rounds (menu option 1)
	public static String roundHeader[] = {
	  "----------------------------------------------------------------------------------------------------------------",
	  "Date\t\tHome Team\t\t\tAway Team\t\t\tVenue\t\t\tKick-Off",
	  "----------------------------------------------------------------------------------------------------------------",
	};
	
	//This array of strings is the header used when displaying the team ladder( Menu option 3)
	public static String ladderHeader[] = {
	  "-------------------------------------------------------------------------------------------------------------",
	  "Rank\tTeam\t\t\t\tGames\tWon\tLost\tDraw\tBye\tScored\tCeded\tTotal Points",
	  "-------------------------------------------------------------------------------------------------------------"
	};
	
	//This array of strings is the header used when displaying the rounds for the fixture( Menu option 5, 1 - display by round) 
	public static String fixtureHeader[] = {
		"-------------------------------------------------------------------------------------------------------------------------------",
	  "Date\t\tHome Team\t\t\tAway Team\t\t\tVenue\t\t\tKick-Off\tResult",
	  "-------------------------------------------------------------------------------------------------------------------------------",
	};
	
	//This array of objects is the header used when displaying the fixture when viewed by teams( menu option 5,   2 - view by team)
	public static String teamFixtureHeader[] = {
		"---------------------------------------------------------------------------------------------------------------------------------------------------",
		"\t\tDate\t\tHome Team\t\t\tAway Team\t\t\tVenue\t\t\tKick-Off\tResult",
		"---------------------------------------------------------------------------------------------------------------------------------------------------"
	};
	
	//This array of objects is the header used when displaying the team results (Menu option 4)
	public static String teamResultsHeader[] ={
		"-------------------------------------------------------------------------",
		"Round\tDate\t\tTeam Played\t\t\tW/L/D\tScore",
		"-------------------------------------------------------------------------"
	};
		
	public static String enterForMainMenu = "Hit ENTER to return to the MAIN MENU";
	
	//This array of string contains the options when viewing the match schedule (Menu option 1)
	public static String displayMatchOptions[] = {
		"----------MATCH DISPLAY OPTIONS----------",
		"View all rounds of the competition",
		"View a selected round of the competition"
	};
	
	//This array of strings contains the menu options when the displaying the fixtures( menu option 5)
	public static String fixtureOptions[] = {
		"----------FIXTURE DISPLAY OPTIONS----------",
		"View fixtures by ROUNDS",
		"View fixtures by TEAMS",
		"View fixtures by VENUE",
		"Go back to Main Menu"
	};

}