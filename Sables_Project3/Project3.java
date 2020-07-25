
/**
 * COP 3530: Project 3 - Linked Lists  
 * 
 * <p>The class Project 3 is a program that sorts Country objects in a specific manner utilizing the implementation of linked lists
 * (both Singly and Doubly linked lists). The program defines five groups (poor, fair, good, very good, excellent) based on their 
 * GDP per Capitas. However, the class only requires Country objects that have poor, good, or excellent GDP per Capitas.<p>
 * 
 * <p>The program functions by first reading and parsing the file of countries. The country objects are then set and inserted into
 * an array. After creating an array of country objects, a stack is created using the stack class. While iterating through the array
 * of country objects, countries with poor, good, or excellent GDP per Capitas are pushed to the created stack while the other country
 * objects are excluded. The stack is then printed without modifying the input of the given file. After printing the stack, a queue is 
 * created through the queue class. While the stack is not empty, its values are popped and inserted in the front of the queue, then the 
 * next value is popped and inserted in the end of the queue. This alternating pattern continues until the stack is emptied and the queue 
 * is filled. The queue is then printed. After the queue is printed, a method called intervalDelete is called and removes country objects
 * that have GDP per Capitas within a given interval. Afterwards, the program prints the queue after intervalDelete is applied. Finally, 
 * the programs removes the queue contents, alternating from removing the front then the end, and pushes it back into the stack. The output
 * of the final stack is printed and the program exits.    <p>
 *  
 * <p>Input/Output Descriptions:
 * file containing Countries (input) - user must give the program the proper name of the file.
 * file containing Countries (output) - improper input leads the program to exit. Proper input proceeds to read the number of records, parse the file, and 
 * 										continue the program.
 * 
 * intervalDelete (input) - within the program, an interval of GDP per Capitas will be manually inputed.
 * intervalDelete (output) - improper input leads the program to have an error. Proper input will remove the country objects within the interval. Otherwise,
 * 							 no country objects will be deleted.
 * <p>
 * 
 * @author - <Kymberlee Sables>
 * @version - <Last updated on March 10, 2020>
 */

/**
 * Imports
 */
import java.util.Scanner;
import java.io.*;

public class Project3 {
	public static void main(String[] args) throws IOException, FileNotFoundException {
		System.out.println("COP 3530 Project 3");
		System.out.printf("Name: Kymberlee Sables (n01364866)\n\n");
		System.out.printf("******************************************\n\n");
		System.out.println("***Linked Lists***");

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
			System.out.printf("\nThere were %d records read.\n\n", lineCount);

			records.close();
			lines.close();
		}

		/**
		 * Creating an array instance of Country, in this case countries, in order to
		 * set and store the file's information. Additionally, this is where the file is
		 * parsed. This is also where the fields of countries are set.
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

		for (int i = 0; i < countries.length; i++) {
			countries[i].setGDPperCapita(
					Double.valueOf(countries[i].getGDP()) / Integer.valueOf(countries[i].getPopulation()));
		}

		/**
		 * Creating a Country Stack and pushing elements that only fall within the poor,
		 * good, or excellent GDP per Capitas.
		 */
		Stack CountryStackList = new Stack();

		for (int i = 0; i < countries.length; i++) {
			if (countries[i].getGDPperCapita() < 1000
					|| countries[i].getGDPperCapita() >= 5000 && countries[i].getGDPperCapita() < 20000
					|| countries[i].getGDPperCapita() >= 50000) {
				CountryStackList.Push(countries[i]);
			}
		}

		/**
		 * Printing the Country Stack.
		 */
		System.out.println("Stack Contents containing POOR, GOOD, and EXCELLENT GDP per Capitas:");
		System.out.printf("Name\t\t\t\t   Code\t\t\tCapitol\t\t    Population\t\tGDPperCapita\t    HappinessRank\n");
		System.out.printf(
				"---------------------------------------------------------------------------------------------------------------------------------------\n");
		CountryStackList.printStack(countries[countries.length - 1]);

		/**
		 * Creating a Country Queue and filling it with elements as the stack is getting
		 * popped. The queue is specifically being filled by inserting values at the
		 * front first, then inserting values at the end. This is done until the stack
		 * is empty.
		 */
		Queue CountryQueueList = new Queue();

		while (!CountryStackList.isEmpty()) {
			CountryQueueList.insertFront(CountryStackList.Pop());
			CountryQueueList.insertEnd(CountryStackList.Pop());
		}

		System.out.printf("\n\n\n\n");

		/**
		 * Printing the Country Queue.
		 */
		System.out.println("Queue Contents containing POOR, GOOD, and EXCELLENT GDP per Capitas:");
		System.out.printf("Name\t\t\t\t   Code\t\t\tCapitol\t\t    Population\t\tGDPperCapita\t    HappinessRank\n");
		System.out.printf(
				"---------------------------------------------------------------------------------------------------------------------------------------\n");
		CountryQueueList.printQueue();

		System.out.printf("\n\n\n\n");

		/**
		 * Deleting Countries from the queue whose GDP per Capitas fall within a
		 * specific interval. The programs notifies the user if values were found and
		 * deleted, or if values were not found at all.
		 */
		CountryQueueList.intervalDelete(50000.00, 70000.00);

		System.out.printf("\n\n\n");

		/**
		 * Printing the Country Queue excluding the Countries whose GDP per Capitas fall
		 * within the specific interval.
		 */
		System.out.println("Queue Contents EXCLUDING GDP per Capitas between a given interval:");
		System.out.printf("Name\t\t\t\t   Code\t\t\tCapitol\t\t    Population\t\tGDPperCapita\t    HappinessRank\n");
		System.out.printf(
				"---------------------------------------------------------------------------------------------------------------------------------------\n");
		CountryQueueList.printQueue();

		System.out.printf("\n\n\n\n");

		/**
		 * Refilling the Country stack by removing the queue elements from the front
		 * then the end, alternating until the queue is empty.
		 */
		while (CountryQueueList.isFull()) {
			CountryStackList.Push(CountryQueueList.removeFront());
			CountryStackList.Push(CountryQueueList.removeEnd());
		}
		CountryStackList.Pop();

		/**
		 * Printing the final stack after pushing the queue elements.
		 */
		System.out.println(
				"Stack Contents after alternating from removing the front and removing the end of the previous queue:");
		System.out.printf("Name\t\t\t\t   Code\t\t\tCapitol\t\t    Population\t\tGDPperCapita\t    HappinessRank\n");
		System.out.printf(
				"---------------------------------------------------------------------------------------------------------------------------------------\n");
		CountryStackList.printStack(countries[countries.length - 1]);

		/**
		 * Closing the scanners and exiting the program.
		 */
		scanRecords.close();
		input.close();

		System.exit(0);
	}
}
