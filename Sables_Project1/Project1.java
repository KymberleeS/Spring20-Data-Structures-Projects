/**
 * COP 3530: Project 1 - Array Searches and Sorts
 * 
 * <p>The class, Project1, shows the creation of a Country searcher and sorter and provides different
 * options to sort and search through the countries. The countries can be sorted by their name, happiness
 * rank, and GDP, each implementing a different sorting algorithm. Additionally, the program can print 
 * an all countries report based on how they are sorted. The program also offers an option to print an
 * individual country report, which implements different searching algorithms based on if the array of 
 * countries is sorted or not.<p>
 * 
 *  <p>In summary, the program functions by first prompting the user for the file with all the countries.
 *  Once the file is recognized, the program reads the number of records the file contains and places them
 *  in an array of objects. Once the file has been parsed, the user is given multiple options on how they 
 *  want to read, print, search, and sort the countries.<p>
 *  
 *  <p>Input/Output Descriptions:
 *  file containing Countries (input) - user must give the program the proper name of the file.
 *  file containing Countries (output) - improper input leads the program to exit. proper input proceeds to read the number of records and parse the file.
 *  
 *  Option menu 1 (input) - user must input the proper value.
 *  Option menu 1 (output) - improper input re-prompts the user for input, proper input prints an all countries report.
 *  
 *  Option menu 2 (input) - user must input the proper value.
 *  Option menu 2 (output) - improper input re-prompts the user for input, proper input sorts by name.
 *  
 *  Option menu 3 (input) - user must input the proper value.
 *  Option menu 3 (output) - improper input re-prompts the user for input, proper input sorts by happiness rank.
 *  
 *  Option menu 4 (input) - user must input the proper value.
 *  Option menu 4 (output) - improper input re-prompts the user for input, proper input sorts by GDP.
 *  
 *  Option menu 5 (input) - user must input the proper value.
 *  Option menu 5 (output) - improper input re-prompts the user for input, proper input prompts for a country name. Proper input
 *  prints an individual country report, improper input prints that the country does not exist.
 *  
 *  Option menu 6 (input) - user must input the proper value.
 *  Option menu 6 (output) - improper input re-prompts the user for input, proper input closes the program.<p>
 * 
 * @author - <Kymberlee Sables>
 * @version - <Last updated on January 20, 2020>
 */

/**
 * Imports
 */
import java.util.Scanner;
import java.util.InputMismatchException;
import java.io.*;

public class Project1 {
	/**
	 * The purpose of the main method, specifically for the class Project1, is to
	 * read/parse the csv file, make an array of objects and set values, and handle
	 * the menu options/looping. Additionally, the main method is where external
	 * methods as well as the Country class is called as needed in order to perform
	 * certain functions.
	 * 
	 * @param args - houses all functions pertaining to reading and parsing the csv file.
	 * @throws IOException - catches all input/output errors
	 * @throws FileNotFoundException - catches all errors pertaining to a file's existence.
	 */
	public static void main(String[] args) throws IOException, FileNotFoundException {
		System.out.println("COP 3530 Project 1");
		System.out.printf("Name: Kymberlee Sables (n01364866)\n\n");
		System.out.printf("******************************************\n\n");
		System.out.println("***Array Searches and Sorts***");

		/**
		 * Opening a new file and scanner in order to read the file and count the number
		 * of records.
		 */
		Scanner input = new Scanner(System.in);
		System.out.printf("Enter the file name: ");

		String fileName = input.nextLine();
		File file = new File(fileName);

		int lineCount = -1;

		if (!file.exists()) {
			System.out.printf("File does not exist. Please try again later. ");
			System.exit(0);
		} else {
			InputStream records = new FileInputStream(file);
			BufferedReader lines = new BufferedReader(new InputStreamReader(records));

			while (lines.readLine() != null) {
				lineCount++;
			}
			System.out.printf("\nThere were %d records read.\n", lineCount);

			records.close();
			lines.close();
		}

		/**
		 * Creating an array instance of Country, in this case countries, in order to
		 * set and store the file's information. Additionally, this is where the file is
		 * parsed.
		 */
		Country[] countries = new Country[lineCount];

		Scanner scanRecords = new Scanner(file);

		int recordCount = 0;
		scanRecords.nextLine();
		while (scanRecords.hasNextLine()) {
			String[] fields = scanRecords.nextLine().split(",");
			countries[recordCount] = new Country();
			countries[recordCount].setName(fields[0]);
			countries[recordCount].setCode(fields[1]);
			countries[recordCount].setCapitol(fields[2]);
			countries[recordCount].setPopulation(fields[3]);
			countries[recordCount].setGDP(fields[4]);
			countries[recordCount].setHappinessRank(fields[5]);
			recordCount++;
		}

		/**
		 * Where the menu is created. The do-while loops re-prompts the user to enter a
		 * correct option.
		 */
		int option = 0;
		do {
			System.out.println("");
			System.out.println("***OPTIONS MENU***");
			System.out.println("1. Print an all countries report");
			System.out.println("2. Sort by Name");
			System.out.println("3. Sort by Happiness Rank");
			System.out.println("4. Sort by GDP per capita");
			System.out.println("5. Find and print a given country");
			System.out.println("6. Quit");

			System.out.printf("\nEnter an option: ");

			boolean validChoice = false;

			do {
				try {
					option = input.nextInt();
					if (option < 1 || option > 6)
						System.out.printf("Invalid option. Only enter options 1 through 6. Please try again: ");
					else
						validChoice = true;
				} catch (InputMismatchException e) {
					System.out.printf("Invalid option. Only enter options 1 through 6. Please try again: ");
					input.next();
				}
			} while (validChoice == false);

			/**
			 * The switch case stores the functions for when each option is selected from
			 * the menu. Within the switch case, each case calls an external method.
			 * Additionally, each case tells the user the action performed.
			 */
			switch (option) {
			case 1:
				printCountryReport(countries);
				break;
			case 2:
				sortName(countries, countries.length);
				System.out.println("Countries Sorted by Name.");
				break;
			case 3:
				sortHappinessRank(countries, countries.length);
				System.out.println("Countries sorted by Happiness Rank.");
				break;
			case 4:
				sortGDP(countries, countries.length);
				System.out.println("Countries sorted by GDP per Capita.");
				break;
			case 5:
				System.out.printf("Enter a country name: ");
				input.nextLine();
				String name = input.nextLine();
				printSpecificCountry(countries, name);
				break;
			case 6:
				System.out.println("Thank you, have a nice day!");
				System.exit(0);
				break;
			default:
				System.out.println("An error has occured. Please try again");
				break;
			}
		} while (option != 6);

		scanRecords.close();
		input.close();
	}

	/**
	 * Method 1 - This method is responsible for printing an all countries report in
	 * a neat, formatted way. A for loop is used to iterate through the array in
	 * order to print each item. There are no return values.
	 * 
	 * @param countries - Takes an array of objects (of type Country) as input to print the values.
	 */
	public static void printCountryReport(Country[] countries) {
		System.out.printf("Name\t\t\t\t   Code\t\t\tCapitol\t\t    Population\t\tGDP\t\t    HappinessRank\n");
		System.out.printf(
				"---------------------------------------------------------------------------------------------------------------------------------------\n");
		for (int i = 0; i < countries.length; i++) {
			System.out.printf("%-35s%-21s%-20s%-20s%-20s%-20s\n", countries[i].getName(), countries[i].getCode(),
					countries[i].getCapitol(), countries[i].getPopulation(), countries[i].getGDP(),
					countries[i].getHappinessRank());
		}
	}

	/**
	 * Method 2 - this method utilizes the bubble sort algorithm in order to sort
	 * the countries' names in alphabetical order. There are no return values.
	 * 
	 * @param countries - requires the countries object from main in order to access the values to sort.
	 * @param countriesLength - requires the total index number to know the number of iterations needed for the sorting algorithm.
	 */
	public static void sortName(Country[] countries, int countriesLength) {
		System.out.println("");
		System.out.println("Implementing Bubble Sort. . .");
		System.out.println("");
		for (int out = 0; out < countriesLength; out++) {
			for (int in = 0; in < countriesLength - 1 - out; in++) {
				if (countries[in].getName().compareTo(countries[in + 1].getName()) > 0) {
					Country temp = countries[in];
					countries[in] = countries[in + 1];
					countries[in + 1] = temp;
				}
			}
		}
	}

	/**
	 * Method 3 - this method utilizes the selection sort algorithm in order to sort
	 * the countries' happiness rank in ascending numerical order. There are no
	 * return values.
	 * 
	 * @param countries - requires the countries object from main in order to access the values to sort.
	 * @param countriesLength - requires the total index number to know the number of iterations needed for the sorting algorithm.
	 */
	public static void sortHappinessRank(Country[] countries, int countriesLength) {
		System.out.println("");
		System.out.println("Implementing Selection Sort. . .");
		System.out.println("");
		for (int out = 0; out < countriesLength - 1; out++) {
			int smallest = out;
			for (int in = out + 1; in < countriesLength; in++) {
				if (Integer.valueOf((countries[in].getHappinessRank()))
						.compareTo(Integer.valueOf((countries[smallest].getHappinessRank()))) < 0) {
					smallest = in;
				}
			}
			if (smallest != out) {
				Country temp = countries[smallest];
				countries[smallest] = countries[out];
				countries[out] = temp;
			}
		}
	}

	/**
	 * Method 4 - this method utilizes the insertion sort algorithm in order to sort
	 * the countries' GDP in ascending numerical order. There are no return values.
	 * 
	 * @param countries - requires the countries object from main in order to access the values to sort.
	 * @param countriesLength - requires the total index number to know the number of iterations needed for the sorting algorithm.
	 */
	public static void sortGDP(Country[] countries, int countriesLength) {
		System.out.println("");
		System.out.println("Implementing Insertion Sort. . .");
		System.out.println("");
		int out = 1;
		int in;
		while (out < countriesLength) {
			Country temp = countries[out];
			in = out - 1;
			while (in >= 0 && (Double.valueOf(countries[in].getGDP()).compareTo(Double.valueOf(temp.getGDP()))) > 0) {
				countries[in + 1] = countries[in];
				in--;
			}
			countries[in + 1] = temp;
			out++;
		}
	}

	/**
	 * Method 5 - this method utilizes both the binary and sequential search
	 * algorithms depending if the array is alphabetically sorted or not. It takes
	 * user input of type String and sets that input in a new object of type
	 * Country. The input is then used as a "target" for both searches to see if the
	 * user's input exist by calling the "compareCountry" method. If the user's
	 * input exists, an individual country report is printed by calling the
	 * "printCountryObject" method. Otherwise, both search algorithms will let the
	 * user know that the input does not exist.
	 * 
	 * @param countries - requires the countries object from main as input in order to search and compare with the user's input.
	 * @param name - requires a user input of type string from main in order to compare it to each of the values of the array.
	 */
	public static void printSpecificCountry(Country[] countries, String name) {
		Country countryName = new Country();

		Integer index = null;
		for (int i = 0; i < countries.length; i++) {
			if (countries[i].getName().equals(name)) {
				countryName.setName(name);
				index = i;
			}
		}

		boolean sortedArray = true;
		for (int i = 0; i < countries.length - 1; i++) {
			if (countries[i].compareCountry(countries[i + 1]) > 0) {
				sortedArray = false;
			}
		}

		if (sortedArray == true) {
			System.out.println("");
			System.out.println("Implementing Binary Search. . .");
			int lower = 0;
			int upper = countries.length - 1;
			int middle;

			try {
				if (lower <= upper) {
					middle = (lower + upper) / 2;
					if (countries[middle].compareCountry(countryName) == 0) {
						countries[index].printCountryObject();
					} else if (countries[middle].compareCountry(countryName) > 0) {
						upper = middle - 1;
						countries[index].printCountryObject();
					} else if (countries[middle].compareCountry(countryName) < 0) {
						lower = middle + 1;
						countries[index].printCountryObject();
					}
				}
			} catch (java.lang.NullPointerException e) {
				System.out.println("");
				System.out.println("That country does not exist.");
			}
		} else {
			System.out.println("");
			System.out.println("Implementing Sequential Search. . .");
			boolean countryExists = false;
			for (int i = 0; i < countries.length; i++) {
				if (countries[i].compareCountry(countryName) == 0) {
					countryExists = true;
					countries[index].printCountryObject();
				}
			}
			if (countryExists == false) {
				System.out.println("");
				System.out.println("That country does not exist.");
			}
		}
	}
}