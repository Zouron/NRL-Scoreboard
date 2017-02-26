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