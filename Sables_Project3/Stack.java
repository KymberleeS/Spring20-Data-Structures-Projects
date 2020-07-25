/**
 * The class Stack is a data structure that sorts data in the order LIFO (last
 * in, first out). The program utilizes this class in order to push, pop, and
 * print the data according to the Stack structure. Additionally, the Stack
 * class can check whether a created stack is empty or full. Furthermore, the
 * Stack class is implemented using a double ended Singly Linked list. Key words
 * used throughout the implementation is data, next, top, and bottom. Data holds
 * the actual information, while next points to the address of another link
 * holding data. Top and bottom keep track of the top and bottom of the stack.
 * With a stack, a double ended Singly Link is useful since the top and bottom
 * can be used to easily iterate through the stack while either pushing or
 * popping elements.
 *
 * @author - <Kymberlee Sables>
 * @version - <Last updated on March 10, 2020>
 */

class Stack {
	/**
	 * variables (data fields)
	 */
	private SinglyLink bottom;
	private SinglyLink top;

	/**
	 * This is the default constructor for the Stack class, which Java provides for
	 * the program. The constructor is what initializes instances (objects) of a
	 * class. There are no parameters or return values.
	 */
	public Stack() {
		this.top = null;
		this.bottom = null;
	}

	/**
	 * This method pushes country objects into the stack. If the stack is empty to
	 * begin with, the country object is the top. To keep pushing values, the bottom
	 * becomes the next value that the element is referencing. Finally, the current
	 * element becomes the new bottom. There are no return values.
	 * 
	 * @param element - the country object to be pushed to the stack.
	 */
	public void Push(Country element) {
		SinglyLink CountryStackLink = new SinglyLink(element);
		if (isEmpty()) {
			top = CountryStackLink;
		}
		CountryStackLink.next = bottom;
		bottom = CountryStackLink;
	}

	/**
	 * This method pops (removes) country objects from the stack, starting from the
	 * top of the stack. Data is stored in a temporary variable, while the method
	 * re-points towards the data next in the stack. There are no parameters.
	 * 
	 * @return temp - has the data of the element being popped.
	 */
	public Country Pop() {
		Country temp = null;
		try {
			temp = bottom.data;
			if (bottom.next == null) {
				top = null;
			}
			bottom = bottom.next;
		} catch (NullPointerException e) {
			;
		}
		return temp;
	}

	/**
	 * This method prints the stack in order from top to bottom. In order to print
	 * the stack without losing the values, a temporary stack is created. The
	 * elements are popped, printed, then pushed back into the original stack. This
	 * way, the stack can always be reprinted without always pushing values
	 * beforehand. There are no return values.
	 * 
	 * @param element - object to be popped, printed, then pushed.
	 */
	public void printStack(Country element) {
		Stack temp = new Stack();
		while (!isEmpty()) {
			element = Pop();
			System.out.println(element);
			temp.Push(element);
		}
		while (!temp.isEmpty()) {
			Push(temp.Pop());
		}
	}

	/**
	 * This method checks if the stack is empty by checking if the top value is null
	 * or not. There are no parameters.
	 * 
	 * @return top == null - If the top of the stack is null, then the stack is
	 *         empty.
	 */
	public boolean isEmpty() {
		return top == null;
	}

	/**
	 * This method checks if the stack is full by checking if the bottom value is
	 * null or not. There are no parameters.
	 * 
	 * @return bottom != null - If the bottom of the stack is not null, then the
	 *         stack is filled.
	 */
	public boolean isFull() {
		return bottom != null;
	}
}

/**
 * The class SinglyLink is necessary in order to implement a double ended Singly
 * Link for the class Stack. This class simply initializes data and next so that
 * they may be used within the Stack class when implementing a double ended
 * Singly Link.
 * 
 * @author - <Kymberlee Sables>
 * @version - <Last updated on March 10, 2020>
 */
class SinglyLink {
	/**
	 * variables (data fields)
	 */
	Country data;
	SinglyLink next;

	/**
	 * This is the default constructor for the SinglyLink class provided by Java.
	 * There are no parameters or return values.
	 */
	public SinglyLink() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * This constructor for the SinglyLink class sets the data field "data" to a
	 * specified value. Additionally, next is set to null.
	 * 
	 * @param d - Country object that initializes data.
	 */
	public SinglyLink(Country d) {
		this.data = d;
		next = null;
	}
}
