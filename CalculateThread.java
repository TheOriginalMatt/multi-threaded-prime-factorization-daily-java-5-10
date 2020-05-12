/*
 * Name: CalculateThread
 *
 * Description: manages threads and factorization math
 */


import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;

public class CalculateThread implements Runnable {
	
	private FactorNode node;
	private String threadName;
	private Thread t;
	private static ConcurrentLinkedQueue threads = new ConcurrentLinkedQueue();

    /*
     * Name: CalculateThread()
     * 
     * Description: Constructor that accepts a thread name and a FactorNode
     *
     * Arguments:
     *  String     - threadName      - a string to be used as the name of the
     *       thread
     *  FactorNode - compositeNumber - stores a node containing the number to 
     *      calculate the prime factorization of
     *
     * Returns
     *  N/A
     */
	CalculateThread(String threadName, FactorNode compositeNumber) {
		this.node       = compositeNumber;
		this.threadName = threadName;
	}

    /*
     * Name: CalculateThread()
     * 
     * Description: Constructor that accepts a thread name and an int
     *
     * Arguments:
     *  String - threadName      - a string to be used as the name of the
     *       thread
     *  int    - compositeNumber - a number to calculate the prime 
     *      factorization of
     *
     * Returns
     *  N/A
     */
	CalculateThread(String threadName, int compositeNumber) {
		this.node       = new FactorNode(compositeNumber);
		this.threadName = threadName;
	}

    /*
     * Name: start()
     * 
     * Description: Handles spawning a thread, including tracking it in *        this.threads
     *
     * Arguments:
     *  N/A
     *
     * Returns
     *  N/A
     */
	public void start() {
		if (this.t == null) {
			this.t = new Thread(this, this.threadName);
			this.t.start();
			this.threads.add(this.t);
		}
	}

    /*
     * Name: join()
     * 
     * Description: Handles waiting for all child threads to finish before the
     *      code that calls it continues
     *
     * Arguments:
     *  N/A
     *
     * Returns
     *  N/A
     */
	public void join() throws Exception {

		while(!this.threads.isEmpty()) {
			Thread thread = (Thread)this.threads.poll();
			thread.join();		
		}
	}

    /*
     * Name: run()
     * 
     * Description: Calculates the smallest prime number that the composite
     *      number is divisible by, spawning a thread to do the same if
     *      the quotient is another composite number
     *
     * Arguments:
     *  N/A
     *
     * Returns
     *  N/A
     */
	public void run() {
		for (int i = 2; i < (this.getNode().getNumber()/2)+1; i++) {
			double quotient = (float)this.getNode().getNumber() / (float)i;

			if (quotient % 1 == 0) {
				this.getNode().setFirstFactor(i);
				this.getNode().setSecondFactor((int)quotient);
				break;
			}
		}

		if (!this.getNode().isPrime()) {
			if (!this.getNode().getFirstFactor().isPrime()) {
				String newThreadName = this.threadName+"-1";
				CalculateThread c = new CalculateThread(newThreadName, this.getNode().getFirstFactor());
				c.start();				
			} 

			if (!this.getNode().getSecondFactor().isPrime()) {
				String newThreadName = this.threadName+"-2";
				CalculateThread c = new CalculateThread(newThreadName, this.getNode().getSecondFactor());
				c.start();				
			}
		}
	}

    /*
     * Name: getNode
     * 
     * Description: Getter function for the FactorNode being worked on by the
     *      thread
     *
     * Arguments:
     *  N/A
     *
     * Returns
     *  N/A
     */
	public FactorNode getNode() {
		return this.node;
	}
}