/**
 * The class BinarySearchTree is a data structure that sorts data in such a way
 * that a root is established, and data items larger than the root will be
 * placed to the right of the root, and data items smaller than the root will be
 * placed left of the root. Smaller subtrees with sub-roots will be created and
 * this process keeps happening as long as more data items can be attached to
 * the tree. BinarySearchTree has a private Node class inside that assists in
 * creating new nodes every time a new country can be attached to a tree.
 * Additionally, this class can insert, delete, and find tree nodes. There are
 * additional methods within the class that can print the nodes in a particular
 * order and establish references to nodes, such as the helperFindParent and
 * helperFindSuccessor methods. Finally, key words used throughout the class are
 * left child, right child, parent, current, root, and successor.
 * 
 * @author - <Kymberlee Sables>
 * @version - <Last updated on April 8, 2020>
 */

public class BinarySearchTree {
	/**
	 * The class Node is a private class inside the BinarySearchTree class, and is
	 * necessary in order to create individual nodes and attach them to a binary
	 * search tree. Each node holds the data of a country's name and GDP per Capita.
	 * This class simply initializes the variables name and gdpPerCapita, as well as
	 * creates variables to reference a node's left child and right child.
	 * 
	 * @author - <Kymberlee Sables>
	 * @version - <Last updated on April 8, 2020>
	 */

	private class Node {
		/**
		 * data fields/variables
		 */
		String name;
		double gdpPerCapita;
		Node leftChild;
		Node rightChild;

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
		 * the BinarySearchTree class. There are no parameters or return values.
		 */
		public void printNode() {
			System.out.printf("%-35s%,20.2f\n", name, gdpPerCapita);
		}
	}

	/**
	 * data fields/variables
	 */
	Node root;
	Node current;
	Node parent;
	Node successor;

	/**
	 * This is the default constructor for the BinarySearchTree class, which creates
	 * and empty tree. This is done by setting the root of the tree equal to null.
	 * There are no parameters or return values.
	 */
	public BinarySearchTree() {
		root = null;
	}

	/**
	 * This method inserts country objects in a tree, which includes the data of
	 * their names and GDP per Capitas. This method functions by creating a new node
	 * for every country inserted into the tree. If the tree is empty, the first
	 * node inserted becomes the root. However, if the tree is not empty, a walker
	 * called current is created to keep track of the position on the tree, and the
	 * parent follows behind. If the country's name being inserted is less than the
	 * current node's name, then it will travel left. If higher, then it will travel
	 * right. This will keep happening until current is null. When this occurs, the
	 * country node will be inserted either as the parent's left child or right
	 * child. There are no return values.
	 * 
	 * @param name         - Country's name compared to current's name and to be
	 *                     inserted.
	 * @param gdpPerCapita - Country's corresponding GDP per Capita to be inserted.
	 */
	public void insert(String name, double gdpPerCapita) {
		Node CountryNode = new Node(name, gdpPerCapita);
		if (root == null) {
			root = CountryNode;
		} else {
			current = root;
			while (true) {
				parent = current;
				if (name.compareTo(current.name) < 0) {
					current = current.leftChild;
					if (current == null) {
						parent.leftChild = CountryNode;
						return;
					}
				} else {
					current = current.rightChild;
					if (current == null) {
						parent.rightChild = CountryNode;
						return;
					}
				}
			}
		}
	}

	/**
	 * This method searches for nodes within the tree based on a country's name. The
	 * walker current starts at the root. While current is not null, if the name
	 * being searched is less than current's name, it will travel left. Otherwise,
	 * current will travel right. This is done until the name is or is not found. If
	 * the name is found, then the method will return the country's corresponding
	 * GDP per Capita. If the name is not found, the method will print a "not found"
	 * message and return -1.0. Additionally, a counter was added to the method in
	 * order to count and print the number nodes visited while searching for the
	 * name.
	 * 
	 * @param name - Country's name to be found in the tree.
	 * @return current.gdpPerCapita - returns corresponding GDP per Capita if found.
	 * @return -1.0 - Returns this value if name is not found.
	 */
	public double find(String name) {
		int counter = 0;
		current = root;
		try {
			while (current != null) {
				if (name.compareTo(current.name) < 0) {
					current = current.leftChild;
					counter++;
				} else if (name.compareTo(current.name) > 0) {
					current = current.rightChild;
					counter++;
				} else {
					System.out.printf("%s was found with GDP per Capita %.2f\n", name, current.gdpPerCapita);
					System.out.println(counter + " " + "nodes visited\n");
					return current.gdpPerCapita;
				}
			}
			System.out.printf("%s was not found\n", name);
			System.out.println(counter + " " + "nodes visited\n");
		} catch (NullPointerException e) {
			return current.gdpPerCapita;
		}
		return -1.0;
	}

	/**
	 * This method deletes country nodes from the tree. The country is first
	 * searched, then deletion occurs in three cases. Case 1 occurs if the node
	 * being deleted has no children. In this case, the tree is simply traversed and
	 * deletes the node if it is found. Case 2 occurs when the node to be deleted
	 * has one child, either a left or right child. When this occurs, the remaining
	 * child will be reconnected to the deleted node's parent. Case 3 occurs when
	 * the node to be deleted has two children. In this case, the successor needs to
	 * be found to replace the node being deleted. There are no return values.
	 * 
	 * @param name - Country's name to be found and deleted.
	 */
	public void delete(String name) {
		try {
			current = root;
			while (current != null) {
				if (name.compareTo(current.name) < 0) {
					current = current.leftChild;
				} else if (name.compareTo(current.name) > 0) {
					current = current.rightChild;
				} else {
					break;
				}
			}
			current.name = name;
			parent = helperFindParent(root, name);
			if (current.leftChild == null && current.rightChild == null) {
				if (current == root) {
					root = null;
				} else if (current == parent.leftChild) {
					parent.leftChild = null;
				} else {
					parent.rightChild = null;
				}
			} else if (current.rightChild == null) {
				if (current == root) {
					root = current.leftChild;
				} else if (current == parent.leftChild) {
					parent.leftChild = current.leftChild;
				} else {
					parent.rightChild = current.leftChild;
				}
			} else if (current.leftChild == null) {
				if (current == root) {
					root = current.rightChild;
				} else if (current == parent.leftChild) {
					parent.leftChild = current.rightChild;
				} else {
					parent.rightChild = current.rightChild;
				}
			} else {
				successor = helperFindSuccessor(current);
				parent = helperFindParent(root, current.name);
				if (current == root) {
					root = successor;
				} else if (current == parent.leftChild) {
					parent.leftChild = successor;
				} else {
					parent.rightChild = successor;
				}
				successor.leftChild = current.leftChild;
			}
			System.out.println(name + " " + "has been deleted from the tree.");
		} catch (NullPointerException e) {
			;
		}
	}

	/**
	 * This method works recursively by first traversing the left side of the tree,
	 * visiting/printing the node, then traversing the right side of the tree (LNR).
	 * The result prints the tree in order. Additionally, in-order is the only
	 * traversal method that visits/prints the tree nodes in ascending order. There
	 * are no return values.
	 * 
	 * @param root - traversal starts from the root.
	 */
	public void printInOrder(Node root) {
		if (root == null) {
			return;
		}
		Node CurrentRoot = root;
		printInOrder(CurrentRoot.leftChild);
		CurrentRoot.printNode();
		printInOrder(CurrentRoot.rightChild);
	}

	/**
	 * This method works recursively by first visiting/printing the node, traversing
	 * the left side of the tree, then traversing the right side of the tree (NLR).
	 * The result prints the tree pre-order, which prints the root of the tree
	 * first. There are no return values.
	 * 
	 * @param root - traversal starts from the root.
	 */
	public void printPreOrder(Node root) {
		if (root == null) {
			return;
		}
		Node CurrentRoot = root;
		CurrentRoot.printNode();
		printPreOrder(CurrentRoot.leftChild);
		printPreOrder(CurrentRoot.rightChild);
	}

	/**
	 * This method works recursively by first traversing the left side of the tree,
	 * traversing the right side of the tree, then visits/prints the node (LRN). The
	 * result prints the tree post-order, which prints the root of the tree last.
	 * There are no return values.
	 * 
	 * @param root - traversal starts from the root.
	 */
	public void printPostOrder(Node root) {
		if (root == null) {
			return;
		}
		Node CurrentRoot = root;
		printPostOrder(CurrentRoot.leftChild);
		printPostOrder(CurrentRoot.rightChild);
		CurrentRoot.printNode();
	}

	/**
	 * This method prints the countries with the five lowest GDP per Capitas, given
	 * the range. There are are no parameters or return values.
	 */
	public void printBottomFive() {
		if (root == null) {
			return;
		}
		helperTopFiveBottomFive(root, 0.00, 510.00);
	}

	/**
	 * This method prints the countries with the five highest GDP per Capitas, given
	 * the range. There are no parameters or return values.
	 */
	public void printTopFive() {
		if (root == null) {
			return;
		}
		helperTopFiveBottomFive(root, 65000.00, 200000.00);
	}

	/**
	 * This a private helper method called by the delete method that assists in
	 * finding the successor when needing to delete a node concerning case 3. This
	 * method first finds the successor by first going to the node's right child
	 * then traversing all the way left. This method also helps with reconnecting
	 * the tree if the successor is not the right child of the node to be deleted.
	 * Otherwise, the delete method calls this method and works out reconnecting the
	 * tree if the successor is the right child.
	 * 
	 * @param temp - node to be deleted with two children.
	 * @return tempSuccessor - returns the successor that was found.
	 */
	private Node helperFindSuccessor(Node temp) {
		Node tempCurrent = temp.rightChild;
		Node tempSuccessor = null;
		Node tempSuccessorParent = null;
		while (tempCurrent != null) {
			tempSuccessorParent = tempSuccessor;
			tempSuccessor = tempCurrent;
			tempCurrent = tempCurrent.leftChild;
		}
		if (tempSuccessor != temp.rightChild) {
			tempSuccessorParent.leftChild = tempSuccessor.rightChild;
			tempSuccessor.rightChild = temp.rightChild;
		}
		return tempSuccessor;
	}

	/**
	 * This private helper method is called by the delete method and assists in
	 * finding the reference to the parent of the current node. This method was
	 * created, as no automatic parent references were established in earlier
	 * methods. This method is necessary, especially when reconnecting nodes of the
	 * tree. While the root is not null, if the name is less than the root's name,
	 * then the parent becomes the root and the root becomes the root's left child.
	 * Otherwise, the parent becomes the root, and the root becomes the root's right
	 * child.
	 * 
	 * @param tempRoot - finding the parent starts at the root of the tree.
	 * @param name     - finding the parent given the name of the country.
	 * @return tempParent - returns the parent found.
	 */
	private Node helperFindParent(Node tempRoot, String name) {
		if (tempRoot == null) {
			return null;
		}
		Node tempParent = null;
		while (tempRoot != null) {
			if (name.compareTo(tempRoot.name) < 0) {
				tempParent = tempRoot;
				tempRoot = tempRoot.leftChild;
			} else if (name.compareTo(tempRoot.name) > 0) {
				tempParent = tempRoot;
				tempRoot = tempRoot.rightChild;
			} else {
				break;
			}
		}
		return tempParent;
	}

	/**
	 * This helper method is called by the printBottomFive and printTopFive methods,
	 * and assists in finding the countries that lie within a range of GDP per
	 * Capitas, especially since the tree was ordered according to the countries'
	 * names. Traversal starts at the root. While the current node is not null, if
	 * current's left child is null, then the method prints the countries within a
	 * given range. Else, the current node goes to the right side. Otherwise,
	 * current's left child is not null and is set equal to the parent. As long as
	 * the parent's right child is not null and it does not equal current itself,
	 * then the parent will go right. However, if the parent's right child is null,
	 * then it will be set equal to current, and current becomes its left child.
	 * Otherwise, the method will print the countries within a given range. There
	 * are no return methods.
	 * 
	 * @param temp - starts at the root of the tree.
	 * @param dMin - provides the minimum value of the range.
	 * @param dMax - provides the maximum value of the range.
	 */
	private void helperTopFiveBottomFive(Node temp, double dMin, double dMax) {
		if (temp == null) {
			return;
		}
		Node tempCurrent = temp;
		while (tempCurrent != null) {
			if (tempCurrent.leftChild == null) {
				if (dMin <= tempCurrent.gdpPerCapita && dMax >= tempCurrent.gdpPerCapita) {
					tempCurrent.printNode();
				}
				tempCurrent = tempCurrent.rightChild;
			} else {
				Node tempParent = tempCurrent.leftChild;
				while (tempParent.rightChild != null && tempParent.rightChild != tempCurrent) {
					tempParent = tempParent.rightChild;
				}
				if (tempParent.rightChild == null) {
					tempParent.rightChild = tempCurrent;
					tempCurrent = tempCurrent.leftChild;
				} else {
					tempParent.rightChild = null;
					if (dMin <= tempCurrent.gdpPerCapita && dMax >= tempCurrent.gdpPerCapita) {
						tempCurrent.printNode();
					}
					tempCurrent = tempCurrent.rightChild;
				}
			}
		}
	}
}
