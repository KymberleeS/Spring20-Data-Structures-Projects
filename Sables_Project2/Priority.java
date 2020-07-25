/**
 * The Priority class for this program implements a priority queue of Country
 * objects using an array (of type country). Following priority in priority out,
 * this class places priority on the Country objects with the higher GDP per
 * Capita, placing it in the front. Inside the class include data fields,
 * constructors, and methods that are utilized in order to add these Country
 * objects into a priority queue based on GDP per Capita. Additionally, this
 * class has methods to check if the stack is empty or full.
 * 
 * @author - <Kymberlee Sables>
 * @version - <Last updated on February 19, 2020>
 */

class Priority {
	/**
	 * variables (data fields)
	 */
	private static final int DEFAULT_CAPACITY = 0;
	private Country[] GDPperCapitaPriority;
	private int size;
	private int numItems;

	/**
	 * This is the default constructor for the Priority class, which Java provides
	 * for the program. The constructor is what initializes instances (objects) of a
	 * class. There are no parameters or return values.
	 */
	public Priority() {
		this(DEFAULT_CAPACITY);
	}

	/**
	 * The purpose of this constructor is to create the stack array based on the
	 * provided size. There are no return values.
	 * 
	 * @param capacity - initializes the capacity of the array.
	 */
	public Priority(int capacity) {
		GDPperCapitaPriority = new Country[capacity];
		size = capacity;
		numItems = 0;
	}

	/**
	 * The purpose of this method is to take a Country object and Insert it into the
	 * priority queue, following priority in, priority out. Since this is a priority
	 * queue, each object will be inserted based on their GDP per Capita (the higher
	 * the GDP per Capita, the higher the priority). The run time of this method is
	 * O(N). If the queue is full, the program will print out a warning indicating
	 * that the queue is full. There are no return values.
	 * 
	 * @param element - initializes the Country object to be inserted into the
	 *                queue.
	 */
	public void Insert(Country element) {
		if (isFull()) {
			System.out.println("The Queue is full.");
		}
		int i;
		if (numItems == 0) {
			GDPperCapitaPriority[numItems++] = element;
		} else {
			for (i = numItems - 1; i >= 0; i--) {
				if (Double.valueOf(element.getGDPperCapita())
						.compareTo(Double.valueOf(GDPperCapitaPriority[i].getGDPperCapita())) < 0) {
					GDPperCapitaPriority[i + 1] = GDPperCapitaPriority[i];
				} else {
					break;
				}
			}
			GDPperCapitaPriority[i + 1] = element;
			numItems++;
		}
	}

	/**
	 * The purpose of this method is remove a Country object from the priority
	 * queue, following priority in priority out. The run time of this method is
	 * O(1). If the queue is empty, the program will print a message indication the
	 * queue is already empty. There are no parameters.
	 * 
	 * @return GDPperCapitaPriority[--numItems] - Returns the new element that
	 *         becomes the top priority of the queue;
	 */
	public Country Remove() {
		if (isEmpty()) {
			System.out.println("The queue is already empty.");
		}
		return GDPperCapitaPriority[--numItems];
	}

	/**
	 * The purpose of this method is to print the contents of the priority queue.
	 * This is accomplished by removing the contents of the queue based on priority
	 * and printing them out while it is still not empty. There are no parameters or
	 * return values.
	 */
	public void printQueue() {
		while (!isEmpty()) {
			Country element = Remove();
			System.out.println(element);
		}
	}

	/**
	 * The purpose of this method is to return true if the array is empty. There are
	 * no parameters.
	 * 
	 * @return numItems - returns 0 if the array is empty.
	 */
	public boolean isEmpty() {
		return numItems == 0;
	}

	/**
	 * The purpose of this method is to return true if the array is full. There are
	 * no parameters.
	 * 
	 * @return numItems == size; - returns if the array is full.
	 */
	public boolean isFull() {
		return numItems == size;
	}
}
