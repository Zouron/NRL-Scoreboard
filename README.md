# NRL-Scoreboard
##A. Requirements
  1. ALL instructions given in this document MUST be followed in order to be **eligible** for full marks for the project.This document has seven (7) pages.
  2. This project is **NOT** a group assignment; collusion, plagiarism, cheating of any kind is not acceptable. As part of your submission you MUST certify that all work submitted is your own. If you cannot honestly certify that the work is your own then do not submit the project. Breaches of the Academic Misconduct policy will be dealt with according to the university policy (see the learning guide for more information).
  3. All project submissions will be checked for academic misconduct by the use of the MOSS program from Stanford University. Details on MOSS can be obtained from the MOSS web site http://theory.stanford.edu/~aiken/moss/
  **For the problem definition described in section B you must**
  4. include your student id at the end of all filenames for all java code file. Two classes have been identified in section B as being required as part of your solution. Do not modify the names of these classes except for adding your student id to the end of the filename. Other Java files will be needed as part of your solution. All Java code files that are used in this project MUST have your student id appended to the filename. For example Team_########.java;
  5. include your authorship details at the top of **each** file in code comments (see item 3.1 in Section C of this document for details);
  6. adhere to the coding standard as identified in the Google Java Style Guide (see Section C of this document for details);
  7. ensure that standard Input/Output are used in all code segments, **do not** use Swing;
  8. ensure that your java code is appropriately modularised for the given problem definition. That is, you need to write appropriate classes and methods to solve the problem;
  9. not use any dynamic data structures such as arraylists. If you are unsure if you can use a particular data structure check with the unit coordinator first;
  10. reference all sources that you used for inspiration of your solution as per Section D of this document;
  11. Ensure that your java code compiles and runs in Eclipse 4.3.2 (Kepler)

##B. Project Details
```
**Note to 300903 students:** The specifications for this project are based upon the project for 300581 but with some modifications. The differences between the two project specifications are:
  * Menu Item 5 – Fixtures (see section B(ii))
  * Third required class – Round (see section B(iv))
  * Section G – Marking Criteria & Standards - In assessing your submission I will be looking for a higher level of abstraction and code reusability. Hence, the choice and implementation of classes beyond the minimum required in section B(iv) is essential. Also of importance is the implementation and use of generalised methods where possible.
```
**_B(i) - Background information and description_**
The National Rugby League (NRL) is a Rugby League competition played by professional players in sixteen teams from three states of Australia and New Zealand. The competition is played during autumn and spring for twenty-six rounds of matches with the top eight ranked teams then advancing to a finals series culminating in a Grand Final to determine the competition winner. The twenty-six competition rounds have been completed for 2015 with the final rankings of each team shown in Appendix 1. The ranking of each team on a weekly basis produces what is commonly known as the competition ladder.

For each round of the competition a series of matches are played between the sixteen teams. There are normally eight (8) matches played each round1. Hence, each round produces eight winning and eight losing teams (unless there is one or more drawn matches). At the conclusion of each round the results from each match are gathered to produce the competition ladder in which each team is ranked according to the total number of competition points achieved to date (see Appendix 2 for a description of how competition points are achieved). Hence, at the completion of any given round the competition ladder may have different team rankings depending upon the total number of wins, losses and drawn matches for each team. For teams that are on the same number of competition points a ‘score differential’ is used to determine the correct rank (see Appendix 3 for an explanation).

In this programming project you will write an object-oriented menu-driven java program that is able to generate the competition ladder for any chosen round within the regular twenty-six round competition as described above. To do this your program will need to read data from the keyboard and from certain text files in secondary storage, store the data in appropriate data structures using objects, sort the data, and write output data to both the screen and to secondary storage. The specific functional requirements are described in section B(ii). The text files that are to be used for this programming project are described in section B(iii). The classes that must be used as a minimum are described in section B(iv).

**_B(ii) - Program Requirements/Functionality_**
The Java program **must**
  1. be object-oriented utilising the classes described in section B (iv) **as a minimum**. Other classes may also be needed to solve the program requirements;
  2.  be menu-driven. The main menu must have the following menu items:
    1. Display Match Schedule
    2. Enter Round Results
    3. Display Ladder
    4. Team Results
    5. Fixtures
    6. Exit Program

  3. be able to process the defined text files. The text files and their formats are described in section B (iii).

**Program Start Up**

When the java program starts it must perform the following file related operations:
  4. Read the data from the teams.txt file into computer memory into an appropriate array of objects (see section B (iii) for a description of the teams text file and section B (iv) for a description of the Team class);
  5. Read the data from the fixtures.txt file into computer memory into an appropriate array of objects (see section B (iii) for a description of the fixtures text file and section B (iv) for a description of the Fixtures class);
  6. Prompt the user for the current round number. Validate the round number to be in the range 1 (first round of the competition) to 26 (last round of the competition). Load into computer memory (into an appropriate array of objects) the results for each round up to and including the round number entered by the user. To do this the program will need to process each round’s result text file in turn. Note: If a text file for a round’s results does not exist then the program should process only up to that round rather than the round number input by the user. In such a situation the user should be notified which round file(s) were not loaded. The user may create the missing round results files through menu option 2 if they choose to do so.

After processing these files the program should display the main menu.

**Main Menu Item Functionality**
The required functionality for each menu item is described as follows:
  1. **Display Match Schedule** - when this menu option is selected the program should determine from the user if they want to view the schedule for all rounds of the competition or just a selected round. As a result of the users choice display the user’s selection of round(s) to the screen in a simple tabular format similar to that shown in Appendix 4.
  2. **Enter Round Results** - when this menu option is selected the program should allow the user to enter the match results for a particular round. That is, the program will need to display the details for each match in the given round and obtain the score for the home team and the away team for each match. The results obtained for the round must then be written to the round#.txt file (the file format to be used is described in section B (iii)). The round to beprocessed by the user should be the next round which should be determined automatically by the program (ie, if rounds 1 to 5 have already been loaded/entered then the next round to be processed would be round 6). The results of the round should also be applied to the Team objects array so that each Team object is up to date thus ensuring that any output generated via menu option 3 is correct.
  3. **Display Ladder** – when this menu item is selected the program should display to the screen the current competition ladder. This will show the ranking of each team according to the match/round results that have been loaded/entered. This menu item cannot be chosen by the user if no rounds have been loaded/entered. Example output for the competition ladder is shown in Appendix 1. Notes: (i) As per the example shown in Appendix 1 the ladder must be displayed such that the highest ranked team is displayed at the top of the output followed by the team ranked second, then third, and so on until the lowest ranked team is displayed last; (ii) the data items to be displayed in this output are shown in Appendix 1.
  4. **Team Results** – when this menu item is selected the program obtains from the user the name of an NRL team for which they wish to view full match results from round 1 to the current round of the competition. The output generated for this menu item will display to the screen. An example of the output required is shown in Appendix 5.
  5. **Fixtures** – This menu option is similar to that shown at http://www.smh.com.au/rugby-league/nrl-fixtures which allows users to choose between three different views of the NRL fixtures: Round, Team, Venue. The user needs to choose between these three options initially. Secondly they choose a round number, a team, or a venue. Based upon this second input the program is to display all of the fixtures (matches) that correspond. For example choosing a round would display all matches scheduled for the round as per the example output shown in Appendix 6. Choosing a team would display all matches for the team for all rounds as shown in the example output in Appendix 7. Choosing a venue would display all matches to be held at the venue for all rounds as shown in the example output in Appendix 8. Notes: i) if a match has not been played then no scores should be displayed in any of these outputs; ii) given that your program implementation is not via a GUI you will need to design the user interface to work for textual user input; iii) this menu item is to be implemented by students enrolled in 300903 PT Advanced only
  6. **Exit Program** – the program should terminate when this menu item is selected. The program should not terminate until this option is chosen. At other times the program should return to display the Main menu after completing the chosen menu option.

**_B(iii) - Text files to be processed_**

The data that is to be manipulated by your Java program in this programming project is contained in the text files **Teams.txt, Fixtures.txt, Round1.txt, Round2.txt,. . . Round26.txt.** Examples of these files are found in the zip file for this programming project. The data within these text files will need to be read into memory by your program so that it may be manipulated to solve many aspects of the required functionality of this programming project. The text files have been created to conform to a particular format. The format for each file is described below:

**File: Teams.txt**
This file contains details of all of the teams that played in the 2015 NRL competition. Each line within this file represents an NRL team, and has the following format:

   Team Name, Team Mascot, Home Ground

where each data item is separated by a comma (,).

A brief explanation of each of these data items:

   **Team Name:** the name of the NRL team
   **Team Mascot:** the mascot or emblem or commonly used name for the NRL team
   **Home Ground:** the sports ground where the NRL team play their home games

One (1) Teams.txt file has been provided in the project zip file.

**File: Fixtures.txt**

This file contains the details of all matches for each round of the NRL competition. This information is generated by the NRL prior to the start of the competition hence the results of each match are not known at the time this fixtures text file is created. Each line within the file represents a match (fixture/game), and has the following format:

   Round Number, Match Number, Home Team, Away Team, Venue, Time, Date

where each data item is separated by a comma (,).

A brief explanation of each of these data items:

  **Round Number:** numeric value indicating the round of the competition  
  **Match Number:** unique numeric value identifying the match (this value is unique across all rounds)  
  **Home Team:** name of team playing at home ground in this match  
  **Away Team:** name of team playing away from home ground in this match  
  **Venue:** name of the ground or stadium where the match is played (normally this will be the Home Team’s home ground)  
  **Time:** time of kickoff (start) for the match  
  **Date:** date of the match

**Important Notes:** Most rounds of the NRL competition have eight (8) matches. However, some rounds may have less than eight (8) matches since some rounds are played as a split round. Your program needs to be able to cater for this. Your program cannot assume which rounds will be split nor how many matches will be in the split rounds. The maximum number of matches per round is eight (8).

One (1) Fixtures.txt file has been provided in the project zip file.

**Files: Round#.txt**

At the **completion** of each round a results file is created. The file indicates the scores for each match in the round. The results file for each round is named Round#.txt where # is the round number. Hence, there can be up to 26 such files; one for each round of the competition. The files would be named consecutively Round1.txt, Round2.txt, …. up to Round26.txt. Each file contains a full record of the match results for the given round.

**Important Notes:** at the start of the competition none of the match results are known and as indicated above the results of the round are not produced until after completion of the round. Hence, if a result file for a round does not exist then that round has not yet been completed.

Each line within each file represents an individual match result, and has the following format:

   MatchNumber, Score1, Score2

where each data item is separated by a comma.

A brief explanation of each of these data items:

   Match Number: unique numeric value identifying the match
   Score1: score achieved by the home team in the match
   Score2: score achieved by the away team in the match

Thirteen (13) Round#.txt files have been provided in the project zip file.

**Please note** that for the purpose of marking the project the number of lines of data and the data values in the text files will be replaced with different data by the marker. This is to ensure that your solution has not relied upon specific data values or the number of lines in the text files to work. You should therefore test your program with different data files before submission.

When reading the text files into memory the data should be read into **appropriate** array(s) of objects. The classes for these objects are briefly outlined in section B(iv).

**_B(iv) - Required Classes_**

To write your solution for this assignment it is a requirement that you write appropriate code for at least the following java Classes:
  1. Team
  2. Fixture
  3. Round (this class is to be implemented by students enrolled in 300903 PT Advanced only).

These classes are described in general terms as follows:
  1. Team class: the Team class represents an individual NRL team. The Team class needs to store data for the Team Name, Team Mascot, Home Ground name, Rank, accumulated results for each match played during the season which includes number of games played, number of games won, number of games lost, number of byes, points scored For, points scored Against, total competition points. As well as the normal methods thatshould be created for a class (eg, constructors, mutators, and accessors) you will need to decide upon other appropriate methods for this class based upon the general requirements of the assignment specification.
  2. Fixture class: the Fixture class represents an individual match in the NRL competition. The Fixture class needs to store data for the match number, round number, Home Team name, Away Team name, match date, match venue, match time, Home team score, Away team score. As well as the normal methods that should be created for a class (eg, constructors, mutators, and accessors) you will need to decide upon other appropriate methods for this class based upon the general requirements of the assignment specification.
  3. Round class (this class is to be implemented by students enrolled in 300903 PT Advanced only): the Round class represents a complete Round of matches from the NRL competition. The Round class needs to store data for the Round Number, Number of Fixtures to be played, Fixture information for the round, results for the round. The fixture information and the results for the round should be implemented as an aggregation of classes.

Apart from the above classes it is quite likely that you will also need to write other classes depending upon your solution method.