/**
 * The HashTable class is a data structure that functions in a way that each
 * item inserted has a corresponding key, and that key can be used in a hash
 * function to directly access the data object. For this class specifically, a
 * country's key is calculated by converting the characters in a country's name
 * to their integer values and adding the total. The hash function, which finds
 * the index of the country, works by taking the country key and taking the
 * modulus result after dividing it with the size of the array. Additionally,
 * the HashTable class implements separate chaining, which uses double-ended
 * singly linked lists. By implementing separate chaining, countries with the
 * same key go in the same index in the form of a list. In order for the class
 * to function, a private class called Node lies within the class, as well as
 * methods to insert, delete, and search for country objects within the hash
 * table. Lastly, private helper methods such as finding the key and using the
 * hash function is included in order to quickly access the elements within the
 * table.
 * 
 * @author - <Kymberlee Sables>
 * @version - <Last updated on April 19, 2020>
 */

public class HashTable {
	/**
	 * The class Node is a private class inside the class HashTable, and is
	 * necessary for implementing Separate Chaining, which puts elements with the
	 * same key into the same index of an array. Specifically, this class creates
	 * nodes for implementing double-ended singly linked lists to be inserted in the
	 * array.
	 * 
	 * @author - <Kymberlee Sables>
	 * @version - <Last updated on April 19, 2020>
	 */
	
	private class Node {
		/**
		 * data fields/variables
		 */
		String name;
		double gdpPerCapita;
		Node nextNode;

		/**
		 * This constructor for the Node class sets the data fields name and
		 * gdpPerCapita to a specified value. There are no return values.
		 * 
		 * @param name         - initializes a country's name.
		 * @param gdpPerCapita - initializes a country's GDP per Capita.
		 */
		public Node(String name, double gdpPerCapita) {
			this.name = name;
			this.gdpPerCapita = gdpPerCapita;
		}

		/**
		 * The method printNode for the Node class prints a country's name and GDP per
		 * Capita in a specific format. This method can be used multiple times within
		 * the HashTable class. There are no parameters or return values.
		 */
		public void printNode() {
			System.out.printf("%-35s%,20.2f\n", name, gdpPerCapita);
		}
	}
	/**
	 * data fields/variables
	 */
	private Node[] CountryHashArray;
	private int HashArraySize;
	private Node front;
	private Node end;
	private int collisions = 0;
	private int freeSpaces = 0;
	
	/**
	 * This constructor for HashTable is a no-arg constructor that sets the array
	 * size and sets the array to have that specific size. There are no parameters
	 * or return values.
	 */
	public HashTable() {
		HashArraySize = 311;
		CountryHashArray = new Node[HashArraySize];
	}
	
	/**
	 * This method inserts a country along with their names and GDP per Capitas into
	 * the hash table by creating a new node. The country is inserted into a
	 * specific index based on its key, calculated by using the hash function. If
	 * the index of the array is null, the country is simply inserted. Otherwise,
	 * the country is attached at the end of the singly linked list within the index
	 * of the array. There are no return values.
	 * 
	 * @param country - Country's name to be inserted.
	 * @param gdpPerCapita - Country's GDP per Capita to be inserted.
	 */
	public void insert(String country, double gdpPerCapita) {
		Node CountryNode = new Node(country, gdpPerCapita);

		int CountryKey = getCountryKey(country);
		int index = CountryHashFunction(CountryKey);

		if (CountryHashArray[index] == null) {
			CountryHashArray[index] = CountryNode;
		} else {
			CountryNode.nextNode = CountryHashArray[index];
			CountryHashArray[index] = CountryNode;
		}
	}
	
	/**
	 * This method finds a specific country given their name. The key and index of
	 * the country is first calculated in order to go directly to where the location
	 * of the array is. If the country was found, its GDP per Capita will be
	 * returned. Otherwise, the list within the index will be searched until the
	 * country is found. If the country was not found, then a message will print
	 * indicating that the country was not found.
	 * 
	 * @param country - Country's name to be searched.
	 * @return CountryHashArray[index].gdpPerCapita - Country's GDP per Capita to be returned if found.
	 * @return -1.0 - Returns this value if not found.
	 */
	public double find(String country) {
		int CountryKey = getCountryKey(country);
		int index = CountryHashFunction(CountryKey);
		
		while (CountryHashArray[index] != null) {
			if (CountryHashArray[index].name.compareTo(country) == 0) {
				return CountryHashArray[index].gdpPerCapita;
			} else {
				CountryHashArray[index] = CountryHashArray[index].nextNode;
			}
		}
		System.out.println(country + " " + "was not found.");
		return -1.0;
	}
	
	/**
	 * This method deletes a specific country given their name. The country's index
	 * is first calculated by finding its key and using the hash function.
	 * Afterwards, references are made to indicate the countries at the front and
	 * end of the list, as well as, the countries directly before the front and end
	 * of the list. These will act as walkers. Following, there are 3 cases to
	 * consider when deleting a country from the table. If the country to be deleted
	 * is the first in the list, simply make the next element in line the first. If
	 * the country to be deleted is the last in the list, simply make the element
	 * before the last by setting its next node equal to null. Finally, if the
	 * country to be deleted is in the middle, make set the walker before equal to
	 * the walker after, losing reference to the current element in the list. There
	 * are no return values.
	 * 
	 * @param country - Country's name to be deleted.
	 */
	public void delete(String country) {
		int CountryKey = getCountryKey(country);
		int index = CountryHashFunction(CountryKey);
		
		Node tempEnd = null;
		Node tempFront = null;
		
		front = CountryHashArray[index];
		end = front;
		while (end.nextNode != null) {
			tempEnd = end;
			end = end.nextNode;
		}

		if (front.name.compareTo(country) == 0) {
			CountryHashArray[index] = front.nextNode;
		} else if (end.name.compareTo(country) == 0) {
			tempEnd.nextNode = null;
		} else {
			if (front.name.compareTo(country) != 0) {
				tempFront = front;
				front = front.nextNode;
			}
			tempFront.nextNode = front.nextNode;
		}
		System.out.println(country + " has been deleted from the hash table.");
	}
	
	/**
	 * This method displays the hash table in a certain format. However, before
	 * printing the table, the elements in the hash table has been copied into 
	 * a temporary array to make sure references are not lost in the original 
	 * array. There are no parameters or return values.
	 */
	public void display() {
		Node[] temp = new Node[HashArraySize];
		
		for (int i = 0; i < HashArraySize; i++) {
			temp[i] = CountryHashArray[i];
		}
		
		for (int i = 0; i < HashArraySize; i++) {
			if (temp[i] == null) {
				System.out.printf("%3d. %5s\n", i, "Empty");
			} else {
				System.out.printf("%3d. ", i);
				temp[i].printNode();
				temp[i] = temp[i].nextNode;
				while (temp[i] != null) {
					System.out.printf("%5s", "");
					temp[i].printNode();
					temp[i] = temp[i].nextNode;
				}
			}
		}
	}
	
	/**
	 * This method counts how many collisions and free spaces occur in the hash
	 * table. It then prints a message with the total number collisions and free
	 * spaces. There are no parameters or return values.
	 */
	public void printFreeAndCollisons() {
		for (int i = 0; i < HashArraySize; i++) {
			if (CountryHashArray[i] == null) {
				freeSpaces++;
			} else {
				collisions++;
			}
		}
		System.out.println("The hash table has " + freeSpaces + " free spaces and " + collisions + " collisions.");
	}
	
	/**
	 * This private method is a helper method which is the hash function. This takes
	 * the calculated key takes the modulus result with the array size, giving the
	 * specific index of the array.
	 * 
	 * @param CountryKey - Key to be used in the function.
	 * @return CountryKey % HashArraySize - Specific index calculated and returned.
	 */
	private int CountryHashFunction(int CountryKey) {
		return CountryKey % HashArraySize;
	}
	
	/**
	 * This private method is another helper method that created a specific key
	 * based on a country's name. It takes every character in the country's name and
	 * converts it into its integer values and adds the total at the end.
	 * 
	 * @param country - Country's name to find the key for.
	 * @return num - returns the calculated key to input in the hash function.
	 */
	private int getCountryKey(String country) {
		int temp = 0;
		int num = 0;
		for (int i = 0; i < country.length(); i++) {
			temp = country.charAt(i);
			num += temp;
		}
		return num;
	}
}
