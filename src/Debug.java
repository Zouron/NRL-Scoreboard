/**
 * Student ID: 18267684
 * Name: Jason Rego
 * Campus: Campbelltown
 * Tutor Name: Paul Davies
 * Class Day: Friday
 * Class Time: 11:00 AM
 */
public class Debug {
	
	/**
	 * I created this class for the purposes of testing my code.
	 * As there are debug lines throughout my code i have left it in the project.
	 * You can change the value of DEBUD_MODE to turn it off or on.
	 */
	
	private static final boolean DEBUG_MODE = false;
	
	public static void log(String message, int number){
		if(DEBUG_MODE)
			System.out.println("DEBUG: "+ message+ " " + number);
	}
	
	public static void log(String message){
		if(DEBUG_MODE)
			System.out.println("DEBUG: "+ message);
	}
	
	public static void log(int number){
		if(DEBUG_MODE)
			System.out.println("DEBUG: "+ number);
	}

}
