
/**
 * COP 3530: Project 4 - Binary Search Trees   
 * 
 * <p>The class Project 4 is a program that sorts countries by utilizing a binary search tree. The program creates one binary search tree
 * and inserts the countries from the array. However, the only data inserted into the binary search tree regarding the countries are their
 * names and GDP per Capitas.<p>
 * 
 * <p>The program functions by first reading and parsing the file of countries. The country objects are then set and inserted into
 * an array. After creating an array of country objects, a binary search tree is created using the BinarySearchTree class. While iterating
 * through the array of country objects, each country along with their name and GDP per Capita are inserted into a node and attached to the 
 * tree. This is done without modifying the contents of the original file. Once all the countries are inserted, the method printInOrder is
 * called by the class and prints the list of countries in alphabetical order. Afterwards, the class calls the delete method and deletes 3
 * countries from the tree. The tree is then printed in pre-order traversal by calling the printPreOrder method. The class then calls the find
 * method to search for 4 countries to see if they are within the tree. Afterwards, the class calls the delete method one more time to delete
 * 5 more countries from the list. The class then calls the printPostOrder method to print the remaining contents of the tree in post-order 
 * traversal. Finally the class calls the printTopFive and printBottomFive methods and prints 5 countries with the highest GDP per Capitas and
 * 5 countries with the lowest GDP per Capitas from the tree.<p>
 *  
 * <p>Input/Output Descriptions:
 * file containing Countries (input) - user must give the program the proper name of the file.
 * file containing Countries (output) - improper input leads the program to exit. Proper input proceeds to read the number of records, parse the file, and 
 * 										continue the program.
 * 
 * delete (input) - manually inputing the name of a country to delete from the tree.
 * delete (output)- if the country is found, it will be deleted. Otherwise, a message will print saying the country was not found.
 * 
 * find (input) - manually inputing the name of a country to be searched from the tree.
 * find (output) - if the country is found, its GDP per Capita will be returned. Otherwise, a message will print saying the country was not found
 *				   and returns -1.0. In both scenarios, the number of nodes visited will be counted.
 * <p>
 * 
 * @author - <Kymberlee Sables>
 * @version - <Last updated on April 8, 2020>
 */

/**
 * Imports             
 */
import java.util.Scanner;
import java.io.*;

public class Project4 {
	public static void main(String[] args) throws IOException, FileNotFoundException {
		System.out.println("COP 3530 Project 4");
		System.out.printf("Name: Kymberlee Sables (n01364866)\n\n");
		System.out.printf("******************************************\n\n");
		System.out.println("***Binary Search Trees***");

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
		 * Creating a new binary search tree, where all the countries will be inserted,
		 * along with the data holding their names and GDP per Capitas.
		 */
		BinarySearchTree CountrySearchTree = new BinarySearchTree();

		for (int i = 0; i < countries.length; i++) {
			CountrySearchTree.insert(countries[i].getName(), countries[i].getGDPperCapita());
		}

		/**
		 * Calling the method printInOrder and printing the tree in in-order traversal.
		 */
		System.out.println("Inorder Traversal:\n");
		System.out.printf("%-35s%20s\n", "Name", "GDP Per Capita");
		System.out.println("-----------------------------------------------------------------");
		CountrySearchTree.printInOrder(CountrySearchTree.root);

		System.out.printf("\n\n\n");

		/**
		 * Calling the delete method and removing the countries Australia, Greece, and
		 * Norway from the tree.
		 */
		CountrySearchTree.delete("Australia");
		CountrySearchTree.delete("Greece");
		CountrySearchTree.delete("Norway");

		System.out.printf("\n\n\n");

		/**
		 * Calling the printPreOrder method and printing the tree in pre-order
		 * traversal.
		 */
		System.out.println("Preorder Traversal:\n");
		System.out.printf("%-35s%20s\n", "Name", "GDP Per Capita");
		System.out.println("-----------------------------------------------------------------");
		CountrySearchTree.printPreOrder(CountrySearchTree.root);

		System.out.printf("\n\n\n");

		/**
		 * Calling the find method and searching for the countries Sri Lanka, North
		 * Cyprus, Greece, and Australia.
		 */
		CountrySearchTree.find("Sri Lanka");
		CountrySearchTree.find("North Cyprus");
		CountrySearchTree.find("Greece");
		CountrySearchTree.find("Australia");

		System.out.printf("\n\n");

		/**
		 * Calling the delete method again and removing the countries Malawi, Somalia,
		 * Canada, Tunisia, and New Zealand.
		 */
		CountrySearchTree.delete("Malawi");
		CountrySearchTree.delete("Somalia");
		CountrySearchTree.delete("Canada");
		CountrySearchTree.delete("Tunisia");
		CountrySearchTree.delete("New Zealand");

		System.out.printf("\n\n\n");

		/**
		 * Calling the printPostOrder method and printing the tree in post-order
		 * traversal.
		 */
		System.out.println("Postorder Traversal:\n");
		System.out.printf("%-35s%20s\n", "Name", "GDP Per Capita");
		System.out.println("-----------------------------------------------------------------");
		CountrySearchTree.printPostOrder(CountrySearchTree.root);

		System.out.printf("\n\n\n");

		/**
		 * Calling the printBottomFive method and printing the five countries with the
		 * lowest GDP per Capitas in the tree.
		 */
		System.out.println("Bottom five countries regarding GDP per Capita:\n");
		System.out.printf("%-35s%20s\n", "Name", "GDP Per Capita");
		System.out.println("-----------------------------------------------------------------");
		CountrySearchTree.printBottomFive();

		System.out.printf("\n\n\n");

		/**
		 * Calling the printTopFive method and printing the five countries with the
		 * highest GDP per Capitas in the tree.
		 */
		System.out.println("Top five countries regarding GDP per Capita:\n");
		System.out.printf("%-35s%20s\n", "Name", "GDP Per Capita");
		System.out.println("-----------------------------------------------------------------");
		CountrySearchTree.printTopFive();

		/**
		 * Closing the scanners and exiting the program.
		 */
		scanRecords.close();
		input.close();

		System.exit(0);
	}
}
