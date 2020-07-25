/**
 * The class Queue is a data structure that sorts data in the order FIFO (first
 * in, first out). The program utilizes this class in order to insert and remove
 * at the front of the queue. Additionally, this class can also insert and
 * remove elements at the end of the queue. The class can also print the queue
 * and check whether the queue is empty or not. Additionally, the Queue class is
 * implemented using a Doubly Link List. Key words used throughout the
 * implementation is data, next, previous, as well as front, end, and current.
 * These words allow easy iteration through the queue, especially if the program
 * needs to remove or insert objects in between the queue aside from the front
 * and the end.
 * 
 * @author - <Kymberlee Sables>
 * @version - <Last updated on March 10, 2020>
 */

class Queue {
	/**
	 * variables (data fields)
	 */
	private DoublyLink front;
	private DoublyLink end;
	private DoublyLink current;

	/**
	 * This is the default constructor for the Queue class, which Java provides for
	 * the program. The constructor is what initializes instances (objects) of a
	 * class. There are no parameters or return values.
	 */
	public Queue() {
		this.front = null;
		this.end = null;
		this.current = null;
	}

	/**
	 * This method adds a Country object to the end of the queue. Once the element
	 * is added, the "old" end becomes the previous value and the new element
	 * becomes the "new" end. Additionally, next value in front of end is set to
	 * null. There are no return values.
	 * 
	 * @param element - Country object being inserted at the end of the queue.
	 */
	public void insertEnd(Country element) {
		DoublyLink CountryQueueLink = new DoublyLink(element);
		if (isEmpty()) {
			front = end = CountryQueueLink;
			end.next = null;
			front.previous = null;
		} else {
			end.next = CountryQueueLink;
			CountryQueueLink.previous = end;
			end = CountryQueueLink;
			end.next = null;
		}
	}

	/**
	 * This method adds a Country object to the front of the queue. Once the element
	 * is added, the "old" front becomes the next value and the new element becomes
	 * the "new" front. There are no return values.
	 * 
	 * @param element - Country object being inserted at the front of the queue.
	 */
	public void insertFront(Country element) {
		DoublyLink CountryQueueLink = new DoublyLink(element);
		if (isEmpty()) {
			end = CountryQueueLink;
		} else {
			front.previous = CountryQueueLink;
		}
		CountryQueueLink.next = front;
		front = CountryQueueLink;
	}

	/**
	 * This method removes a Country object from the end of the queue. The removed
	 * object is set in a temporary variable while the program re-references the end
	 * to the previous value within the queue. There are no parameters.
	 * 
	 * @return temp - has the data of the element being removed from the front.
	 */
	public Country removeEnd() {
		Country temp = null;
		try {
			if (isEmpty()) {
				System.out.println("The queue is empty.");
			} else if (front != end) {
				end = end.previous;
				end.next = null;
				temp = end.data;
			} else {
				end = front = null;
				temp = end.data;
			}
		} catch (NullPointerException e) {
			;
		}
		return temp;
	}

	/**
	 * This method removes a Country object from the front of the queue. The removed
	 * object is set in a temporary variable while the program re-references the
	 * front to the next value within the queue. There are no parameters.
	 * 
	 * @return temp - has the data of the element being removed from the front.
	 */
	public Country removeFront() {
		Country temp = null;
		try {
			if (isEmpty()) {
				System.out.println("The queue is empty.");
			} else if (front != end) {
				front.previous = null;
				temp = front.data;
				front = front.next;
			} else {
				front = end = null;
				temp = front.data;
			}
		} catch (NullPointerException e) {
			;
		}
		return temp;
	}

	/**
	 * This method deletes country objects of a queue if their GDP per Capitas fall
	 * within a certain range. This is done by defining the min and max of the
	 * interval. If values were found within the interval, the country objects are
	 * removed from the queue. Otherwise the country objects will not be deleted.
	 * Additionally, if values within the interval were found, the program will
	 * print out a message stating that values were found and deleted. Otherwise,
	 * the programs will print a message stating that there no values found within
	 * the interval and nothing will be deleted. There are no return values.
	 * 
	 * @param min - initializes the min value of the interval.
	 * @param max - initializes the max value of the interval.
	 */
	public void intervalDelete(Double min, Double max) {
		current = front;
		boolean DataFound = false;
		int TrueCounter = 0;

		if (isEmpty()) {
			System.out.println("The queue is empty.");
		}

		try {
			while (isFull()) {
				if (current.data.getGDPperCapita() >= min && current.data.getGDPperCapita() <= max) {
					DataFound = true;
					if (DataFound == true) {
						TrueCounter++;
					}

					if (current == front) {
						removeFront();
					} else {
						current.previous.next = current.next;
					}

					if (current == end) {
						removeEnd();
					} else {
						current.next.previous = current.previous;
					}
				}
				current = current.next;
			}
		} catch (NullPointerException e) {
			;
		}

		if (TrueCounter >= 1) {
			System.out.println("*****Values within the interval were found and deleted.*****");
			System.out.println("");
		} else {
			System.out.println("*****No values within the interval were found.*****");
			System.out.println("");
		}
	}

	/**
	 * This method prints the queue in order from front to end. Front is first set
	 * to current. If the queue is empty, a message will be printed stating that the
	 * queue is empty. While the value in front of current is not null, the data of
	 * the current will be printed. The queue is iterated through by setting the
	 * next value to current. There are no parameters or return values.
	 */
	public void printQueue() {
		current = front;

		if (isEmpty()) {
			System.out.println("The queue is empty.");
		}
		while (current.next != null) {
			System.out.println(current.data);
			current = current.next;
		}
	}

	/**
	 * This method checks if the queue is empty by checking if the front value is
	 * null or not. There are no parameters.
	 * 
	 * @return front == null - If the front is null, then the queue is empty.
	 */
	public boolean isEmpty() {
		return front == null;
	}

	/**
	 * This method checks if the queue is full by checking if the end value is null
	 * or not. There are no parameters.
	 * 
	 * @return end != null - If the end is not null, then the queue is full.
	 */
	public boolean isFull() {
		return end != null;
	}
}

/**
 * The class DoublyLink is necessary in order to implement a Doubly Link for the
 * class Queue. This class simply initializes data, next, and previous so that
 * they may be used within the Queue class when implementing a Doubly Link.
 * 
 * @author - <Kymberlee Sables>
 * @version - <Last updated on March 10, 2020>
 */
class DoublyLink {
	/**
	 * variables (data fields)
	 */
	Country data;
	DoublyLink next;
	DoublyLink previous;

	/**
	 * This is the default constructor for the SinglyLink class provided by Java.
	 * There are no parameters or return values.
	 */
	public DoublyLink() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * This constructor for the DoublyLink class sets the data field "data" to a
	 * specified value. Additionally, next and previous are set to null.
	 * 
	 * @param d - country objects that initializes data.
	 */
	public DoublyLink(Country d) {
		this.data = d;
		next = null;
		previous = null;
	}
}
