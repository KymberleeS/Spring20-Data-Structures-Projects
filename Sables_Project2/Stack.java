/**
 * The Stack class for this program implements a stack of Country objects using
 * an array (of type country), following LIFO (last in, first out). Inside the
 * class include data fields, constructors, and methods that are utilized in
 * order to add these Country objects inside a stack. Once the stack is created,
 * this class can be used to push, pop, and print stack contents. Additionally,
 * this class has methods to check if the stack is empty or full.
 *
 * @author - <Kymberlee Sables>
 * @version - <Last updated on February 19, 2020>
 */

class Stack {
	/**
	 * variables (data fields)
	 */
	private Country[] CountryStack;
	private static final int DEFAULT_CAPACITY = 0;
	private int lastOut;
	private int size;

	/**
	 * This is the default constructor for the Stack class, which Java provides for
	 * the program. The constructor is what initializes instances (objects) of a
	 * class. There are no parameters or return values.
	 */
	public Stack() {
		this(DEFAULT_CAPACITY);
	}

	/**
	 * The purpose of this constructor is to create the stack array based on the
	 * provided size. There are no return values.
	 * 
	 * @param capacity - initializes the capacity of the array.
	 */
	public Stack(int capacity) {
		CountryStack = new Country[capacity];
		size = capacity;
		lastOut = -1;
	}

	/**
	 * The purpose of this method is to take a Country object and push it into the
	 * stack, following LIFO (last in, first out). Each Country object that gets
	 * pushed into the stack will be placed on top of the previous object. If the
	 * stack is full, the program will print out a warning indicating that a stack
	 * overflow has occurred. There are no return values.
	 * 
	 * @param element - initializes the Country object to be pushed on top of the
	 *                stack.
	 */
	public void Push(Country element) {
		if (isFull())
			System.out.println("A stack overflow has occurred.");
		CountryStack[++lastOut] = element;
	}

	/**
	 * The purpose of this method is to remove a Country object from the stack,
	 * starting from the top to the bottom, following LIFO (last in, first out). If
	 * the stack is empty, the program will print out a message indicating that the
	 * stack is already empty. There are no parameters.
	 * 
	 * @return CountryStack[lastOut--] - Returns the element that becomes the new
	 *         top of the Stack.
	 */
	public Country Pop() {
		if (isEmpty()) {
			System.out.println("The stack is already empty.");
		}
		return CountryStack[lastOut--];
	}

	/**
	 * The purpose of this method is to print the contents of the Stack. This is
	 * accomplished by popping the contents of the stack and printing them out while
	 * it is still not empty. There are no parameters or return values.
	 */
	public void printStack() {
		while (!isEmpty()) {
			Country element = Pop();
			System.out.println(element);
		}
	}

	/**
	 * The purpose of this method is to return true if the array is empty. There are
	 * no parameters.
	 * 
	 * @return lastOut == -1 - returns if the array is empty.
	 */
	public boolean isEmpty() {
		return lastOut == -1;
	}

	/**
	 * The purpose of this method is to return true if the array is full. There are
	 * no parameters.
	 * 
	 * @return lastOut == size - 1; - returns if the array is full.
	 */
	public boolean isFull() {
		return lastOut == size - 1;
	}
}
