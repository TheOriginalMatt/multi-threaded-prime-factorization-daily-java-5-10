/*
 * Name: Parent
 *
 * Description: handles user input and calling CalculateThread
 */


public class Parent {
	public static void main(String []args) throws NumberFormatException{

		try {
			Integer.parseInt(args[1]);
		} catch (NumberFormatException e) {
			throw e;
		}

		if (new String("list").equals(args[0])) {
			for (int i = 1; i < Integer.parseInt(args[1])+1; i++) {
				factor("thread-1", i);	
			}
		} else if (new String("single").equals(args[0])) {
			factor("thread-1", Integer.parseInt(args[1]));
		}
	}

    /*
     * Name: factor()
     * 
     * Description: Creates a new instance of calculateThread and prints out
     *      the prime factorization as a multiplication number (i.e. 25=5x5)
     *
     * Arguments:
     *  String - threadName - the name to be given to the parent thread
     *  int    - number     - the number to be factored
     *
     * Returns
     *  N/A
     */
	private static void factor(String threadName, int number) {
		CalculateThread c = new CalculateThread(threadName, number);

		c.start();
		try {
			c.join();
			c.getNode().printTree();

		} catch (Exception e) {
			e.printStackTrace();
		}			
	}
}