/**
 * COP 3530: Project 2 - Stacks and Priority Queues  
 * 
 * <p>The class Project 2 is a program that sorts Country objects into five distinct groups based on their
 * GDP per Capita. The groups consist of poor, fair, good, very good, and excellent. Once the countries are
 * sorted, each group, starting from poor, is emptied and added to one whole stack, with the poor group at
 * the bottom and the excellent group at the very top. The contents for each group and the whole stack is printed
 * and the program will terminate.<p>
 * 
 * <p>In summary, the program functions by first creating the five priority queues needed to store the Country
 * objects. The program then reads and parses the file given through user input, then storing the contents into
 * an array of Country objects. Afterwards, the program iterates through the Country array and insert its contents 
 * into five different groups based on GDP per Capita. Each of the queues' contents are printed. Following that, the queues
 * are emptied one at a time, pushed into a whole stack and printed.<p>
 *  
 * <p>Input/Output Descriptions:
 * file containing Countries (input) - user must give the program the proper name of the file.
 * file containing Countries (output) - improper input leads the program to exit. proper input proceeds to read the number of records, parse the file, and 
 * 										continue the program.
 * <p>
 * 
 * @author - <Kymberlee Sables>
 * @version - <Last updated on February 19, 2020>
 */

/**
 * Imports
 */
import java.util.Scanner; 
import java.io.*;

public class Project2 {
	public static void main(String[] args) throws IOException, FileNotFoundException {
		System.out.println("COP 3530 Project 2");
		System.out.printf("Name: Kymberlee Sables (n01364866)\n\n");
		System.out.printf("******************************************\n\n");
		System.out.println("***Stacks and Priority Queues***");

		/**
		 * Creating five categories of priority queues based on GDP per Capita.
		 */
		Priority Poor = new Priority(100);
		Priority Fair = new Priority(100);
		Priority Good = new Priority(100);
		Priority vGood = new Priority(100);
		Priority Excellent = new Priority(100);

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
		 * Inserting the Country objects into five different priority queues based on
		 * their GDP per Capita.
		 */
		for (int i = 0; i < countries.length; i++) {
			if (countries[i].getGDPperCapita() < 1000) {
				countries[i].toString();
				Poor.Insert(countries[i]);
			} else if (countries[i].getGDPperCapita() >= 1000 && countries[i].getGDPperCapita() < 5000) {
				countries[i].toString();
				Fair.Insert(countries[i]);
			} else if (countries[i].getGDPperCapita() >= 5000 && countries[i].getGDPperCapita() < 20000) {
				countries[i].toString();
				Good.Insert(countries[i]);
			} else if (countries[i].getGDPperCapita() >= 20000 && countries[i].getGDPperCapita() < 50000) {
				countries[i].toString();
				vGood.Insert(countries[i]);
			} else if (countries[i].getGDPperCapita() >= 50000) {
				countries[i].toString();
				Excellent.Insert(countries[i]);
			}
		}

		/**
		 * Printing out the priority queue contents for each category. Printing the
		 * queue contents result in emptying the queues.
		 */
		System.out.println("POOR Priority Queue Contents:");
		System.out.printf("Name\t\t\t\t   Code\t\t\tCapitol\t\t    Population\t\tGDPperCapita\t    HappinessRank\n");
		System.out.printf(
				"---------------------------------------------------------------------------------------------------------------------------------------\n");
		Poor.printQueue();

		System.out.printf("\n\n");

		System.out.println("FAIR Priority Queue Contents:");
		System.out.printf("Name\t\t\t\t   Code\t\t\tCapitol\t\t    Population\t\tGDPperCapita\t    HappinessRank\n");
		System.out.printf(
				"---------------------------------------------------------------------------------------------------------------------------------------\n");
		Fair.printQueue();

		System.out.printf("\n\n");

		System.out.println("GOOD Priority Queue Contents:");
		System.out.printf("Name\t\t\t\t   Code\t\t\tCapitol\t\t    Population\t\tGDPperCapita\t    HappinessRank\n");
		System.out.printf(
				"---------------------------------------------------------------------------------------------------------------------------------------\n");
		Good.printQueue();

		System.out.printf("\n\n");

		System.out.println("VERY GOOD Priority Queue Contents:");
		System.out.printf("Name\t\t\t\t   Code\t\t\tCapitol\t\t    Population\t\tGDPperCapita\t    HappinessRank\n");
		System.out.printf(
				"---------------------------------------------------------------------------------------------------------------------------------------\n");
		vGood.printQueue();

		System.out.printf("\n\n");

		System.out.println("EXCELLENT Priority Queue Contents:");
		System.out.printf("Name\t\t\t\t   Code\t\t\tCapitol\t\t    Population\t\tGDPperCapita\t    HappinessRank\n");
		System.out.printf(
				"---------------------------------------------------------------------------------------------------------------------------------------\n");
		Excellent.printQueue();

		System.out.printf("\n\n");

		/**
		 * Re-inserting the Country objects into the specified priority queues, since
		 * printing them out previously emptied the queues.
		 */
		Stack CountryStack = new Stack(countries.length);

		for (int i = 0; i < countries.length; i++) {
			if (countries[i].getGDPperCapita() < 1000) {
				countries[i].toString();
				Poor.Insert(countries[i]);
			} else if (countries[i].getGDPperCapita() >= 1000 && countries[i].getGDPperCapita() < 5000) {
				countries[i].toString();
				Fair.Insert(countries[i]);
			} else if (countries[i].getGDPperCapita() >= 5000 && countries[i].getGDPperCapita() < 20000) {
				countries[i].toString();
				Good.Insert(countries[i]);
			} else if (countries[i].getGDPperCapita() >= 20000 && countries[i].getGDPperCapita() < 50000) {
				countries[i].toString();
				vGood.Insert(countries[i]);
			} else if (countries[i].getGDPperCapita() >= 50000) {
				countries[i].toString();
				Excellent.Insert(countries[i]);
			}
		}

		/**
		 * while not empty, removing the priority queues' contents in order and one at a
		 * time, and pushing the contents into a stack.
		 */
		while (!Poor.isEmpty()) {
			CountryStack.Push(Poor.Remove());
		}

		while (!Fair.isEmpty()) {
			CountryStack.Push(Fair.Remove());
		}

		while (!Good.isEmpty()) {
			CountryStack.Push(Good.Remove());
		}

		while (!vGood.isEmpty()) {
			CountryStack.Push(vGood.Remove());
		}

		while (!Excellent.isEmpty()) {
			CountryStack.Push(Excellent.Remove());
		}

		/**
		 * Printing out the stack contents.
		 */
		System.out.println("Stack Contents:");
		System.out.printf("Name\t\t\t\t   Code\t\t\tCapitol\t\t    Population\t\tGDPperCapita\t    HappinessRank\n");
		System.out.printf(
				"---------------------------------------------------------------------------------------------------------------------------------------\n");
		CountryStack.printStack();

		/**
		 * Closing the scanners and exiting the program.
		 */
		scanRecords.close();
		input.close();

		System.exit(0);
	}
}
