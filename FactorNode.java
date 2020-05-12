/*
 * Name: FactorNode
 *
 * Description: Data type for the binary tree to support calculation
 */


import java.util.concurrent.TimeUnit;

public class FactorNode {
	private FactorNode firstFactor  = null;
	private FactorNode secondFactor = null;
	private int number;
	private boolean isPrime;
	private int currentSize = 1;

    /*
     * Name: FactorNode
     * 
     * Description: Constructor for the class
     *
     * Arguments:
     *  int - number - the number to be stored in this node of the tree
     *
     * Returns
     *  N/A
     */
	FactorNode(int number) {
		this.setNumber(number);
	}

    /*
     * Name: setNumber
     * 
     * Description: Setter function for this.number
     *
     * Arguments:
     *  int - number - the number to be stored in this node of the tree
     *
     * Returns
     *  N/A
     */
	public void setNumber(int number) {
		this.number = number;
		this.setPrime();

	}

    /*
     * Name: getNumber()
     * 
     * Description: Getter function for this.number
     *
     * Arguments:
     *  N/A
     *
     * Returns
     *  int - the number stored at this node
     */
	public int getNumber() {
		return this.number;
	}

    /*
     * Name: setPrime()
     * 
     * Description: Setter function for this.isPrime; Calculates if 
     *      this.number is prime or not
     *
     * Arguments:
     *  N/A
     *
     * Returns
     *  N/A
     */
	private void setPrime() {
		this.isPrime = true;
		for (int i = 2; i < (this.getNumber()/2)+1; i++) {
			if (((float)this.getNumber() / (float)i) % 1 == 0) {
				this.isPrime = false;
				break;
			}
		}
	}

    /*
     * Name: isPrime()
     * 
     * Description: Getter function for this.isPrime
     *
     * Arguments:
     *  N/A
     *
     * Returns
     *  boolean - true if this.number is prime
     *          - false otherwise
     */
	public boolean isPrime() {
		return this.isPrime;
	}

    /*
     * Name: setFirstFactor()
     * 
     * Description: Setter function for this.firstFactor
     *
     * Arguments:
     *  int - factor - int to be stored in the first child of this node
     *
     * Returns
     *  N/A
     */
	public void setFirstFactor(int factor) {
		this.firstFactor = new FactorNode(factor);
	}

    /*
     * Name: getFirstFactor()
     * 
     * Description: Getter function for this.firstFactor
     *
     * Arguments:
     *  N/A
     *
     * Returns
     *  FactorNode - reference to the first child of this node
     */
	public FactorNode getFirstFactor() {
		return this.firstFactor;
	}

    /*
     * Name: setSecondFactor()
     * 
     * Description: Setter function for this.secondFactor
     *
     * Arguments:
     *  int - factor - the value to be stored in th second child of this node
     *
     * Returns
     *  N/A
     */
	public void setSecondFactor(int factor) {
		this.secondFactor = new FactorNode(factor);
	}

    /*
     * Name: getSecondFactor()
     * 
     * Description: Getter function for this.secondFactor
     *
     * Arguments:
     *  N/A
     *
     * Returns
     *  FactorNode - reference to the second child of this node
     */
	public FactorNode getSecondFactor() {
		return this.secondFactor;
	}

	public void printNode() {

		if (this.isPrime()){

			System.out.println("Prime......"+this.getNumber());

		} else {

			System.out.println("Composite.."+this.getNumber());

		}
	}


    /*
     * Name: printTree()
     * 
     * Description: Prints the factors of the subtree with this node as the
     *      root (i.e 100=2x2x5x5)
     *
     * Arguments:
     *  N/A
     *
     * Returns
     *  N/A
     */
	public void printTree() {
		String tree = this.getTree();

		if (tree.length() > 1) {
			tree = tree.substring(0, tree.length()-1);
		} else {
			tree = this.getNumber()+"x1";
		}

		System.out.println(this.getNumber()+"="+tree);
	}

    /*
     * Name: getTree
     * 
     * Description: Recursive helper function for printTree
     *
     * Arguments:
     *  N/A
     *
     * Returns
     *  string - the factors of the current subtree
     */
	private String getTree() {

		String subtree = new String("");

		try {

			if(this.getFirstFactor().isPrime()) {
				subtree += this.getFirstFactor().getNumber()+"x";
			}
				
			subtree += this.getFirstFactor().getTree();

			
		} catch(Exception e) {}

		try {

			if(this.getSecondFactor().isPrime()) {
				subtree += this.getSecondFactor().getNumber()+"x";
			}
				
			subtree += this.getSecondFactor().getTree();

		} catch (Exception e) {}


		return subtree;
	}
}