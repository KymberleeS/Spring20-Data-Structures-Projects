
/**
 * COP 3530: Project 5 - Hash Tables  
 * 
 * <p>The class Project 5 is a program that utilizes a hash table in order to store the country objects. Each country is
 * given a calculated key in order to quickly access their data. The only data inserted into the hash table are the countries' 
 * names and GDP per Capitas.<p>
 * 
 * <p>The program functions by first reading and parsing the file of countries. The country objects are then set and inserted into
 * an array. After creating an array of country objects, a hash table is created through the HashTable class. While iterating through
 * the array, each country's name and GDP per Capita are inserted into the hash table by calculating their key. Their key is then used 
 * in the hash function to insert them into a specified calculated index. This was done without modifying the original contents of the file.
 * After the countries were inserted into the hash table, it is printed out by calling the display method. Countries are then deleted and searched
 * for by calling the delete and find methods. The table is displayed once more after the modifications were made. Finally, the program calls the
 * printFreeAndCollisions in order to display the amount of collisions and free spaces that lie within the hash table.<p>
 *  
 * <p>Input/Output Descriptions:
 * file containing Countries (input) - user must give the program the proper name of the file.
 * file containing Countries (output) - improper input leads the program to exit. Proper input proceeds to read the number of records, parse the file, and 
 * 										continue the program.
 * 
 * delete (input) - manually inputing the name of a country to delete from the tree.
 * delete (output)- if the country is found, it will be deleted.
 * 
 * find (input) - manually inputing the name of a country to be searched from the tree.
 * find (output) - if the country is found, its GDP per Capita will be returned. Otherwise, a message will print saying the country was not found
 *				   and returns -1.0.
 * <p>
 * 
 * @author - <Kymberlee Sables>
 * @version - <Last updated on April 19, 2020>
 */

/**
 * Imports                     
 */
import java.util.Scanner;      
import java.io.*;

public class Project5 {
	public static void main(String[] args) throws IOException, FileNotFoundException {
		System.out.println("COP 3530 Project 5");
		System.out.printf("Name: Kymberlee Sables (n01364866)\n\n");
		System.out.printf("******************************************\n\n");
		System.out.println("***Hash Tables***");

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
			System.out.printf("\nThere were %d country records read into the hash table.\n\n", lineCount);

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
		 * Creating a new Hash Table.
		 */
		HashTable CountryTable = new HashTable();
		
		/**
		 * Inserting country objects and their data regarding their names and GDP per Capitas into
		 * the hash table.
		 */
		for (int i = 0; i < countries.length; i++) {
			CountryTable.insert(countries[i].getName(), countries[i].getGDPperCapita());
		}
		
		/**
		 *Printing out the hash table by calling the display method.
		 */
		System.out.println("Hash Table Contents:");
		System.out.println("------------------------------------------------------------");
		CountryTable.display();
		
		System.out.printf("\n\n");
		
		/**
		 * Deleting the countries Cyprus, Kazakhstan, Hungary and Japan by calling the
		 * delete method.
		 */
		CountryTable.delete("Cyprus");
		CountryTable.delete("Kazakhstan");
		CountryTable.delete("Hungary");
		CountryTable.delete("Japan");
		
		System.out.printf("\n\n");
		
		/**
		 * Searching the hash table for the countries Costa Rica, North Cyprus, and
		 * Hungary by calling the find method.. If the countries were found, a message
		 * will print and show their GDP per Capita. Otherwise, another message will
		 * print saying that the country was not found in the table.
		 */
		double GDP = 0;
		
		GDP = CountryTable.find("Costa Rica");
		if (GDP != -1.0) {
			System.out.printf("Costa Rica was found with a GDP per Capita of %,.2f.\n", GDP);
		}
		
		GDP = CountryTable.find("North Cyprus");
		if (GDP != -1.0) {
			System.out.printf("North Cyprus was found with a GDP per Capita of %,.2f.\n", GDP);
		}
		
		GDP = CountryTable.find("Hungary");
		if (GDP != -1.0) {
			System.out.printf("Hungary was found with a GDP per Capita of %,.2f.\n", GDP);
		}
		
		System.out.printf("\n\n");
		
		/**
		 * Deleting the countries Zambia, Canada, Egypt, Yemen, and India by calling the
		 * delete method again.
		 */
		CountryTable.delete("Zambia");
		CountryTable.delete("Canada");
		CountryTable.delete("Egypt");
		CountryTable.delete("Yemen");
		CountryTable.delete("India");
		
		System.out.printf("\n\n");
		
		/**
		 * Printing out the resulting hash table by calling the display method again.
		 */
		System.out.println("Hash Table Contents:");
		System.out.println("------------------------------------------------------------");
		CountryTable.display();
		
		System.out.printf("\n\n");
		
		/**
		 * Printing out a message indicating how many collisions and free spaces are in the
		 * hash table by calling the printFreeAndCollisions method.
		 */
		CountryTable.printFreeAndCollisons();
		
		/**
		 * Closing the scanners and exiting the program.
		 */
		scanRecords.close();
		input.close();

		System.exit(0);
	}
}
