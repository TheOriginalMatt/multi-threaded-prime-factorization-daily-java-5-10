# Prime Factorization
## Daily Java Project for: 5/10/2020

### Description:
A multi-threaded approach to solving the prime factorization of an `int`

### Daily Java Projects
I'm creating a small Java project each day to help me learn Java. These aren't rigorously tested, but instead exist just so I can get a better understanding of some portion of Java.

### Points of Interest

+ Learned `.join()` on a thread doesn't wait for all children threads to join
+ Learned that `ConcurrentLinkedQueue` is a really simple way to track all child threads to wait for them to join
+ Actually learned how to multi-thread in Java, instead of the simple examples people put online

### Using `PrimeFactorizaton`
+ Example: Calculating the prime factorization of 19434 and printing them as a multiplication problem:

```
public class Parent {
    public static void main(String []args) throws NumberFormatException{

        CalculateThread c = new CalculateThread("thread-1", 19434);

        c.start();
        try {
            c.join();
            c.getNode().printTree();

        } catch (Exception e) {
            e.printStackTrace();
        }           
    }
}
```
+ Or use it completely within the terminal
    + Example: List the prime factorization for 1-10,000:
        + `$java Parent list 10000`
    + Example: List the prime factorization for 283:
        + `$java Parent single 283`

### Documentation

+ `CalculateThread` - Creates child threads to solve the prime factorization of a given number

    + `CalculateThread(String threadName, FactorNode compositeNumber)`
        + Creates a CalculateThread with the given thread name and calculates based on the number stored in the given FactorNode
    + `CalculateThread(String threadName, int compositeNumber)`
        + Creates a CalculateThread with the given thread name and calculates based on the number stored in the given int
    + `public void start()`
        + Creates the proper Thread object, and runs `run()`
    + `public void join() throws Exception`
        + Joins this CalculateThread's thread and all other child threads
    + `public FactorNode getNode()`
        + Returns the factor node being worked on. Works even if the constructor is given an integer

+ `FactorNode` - Stores the prime factorization and all composite numbers to get there in a binary tree
    
    + `FactorNode(int number)`
        + Creates a factor node with number being stored in this node
    + `public void setNumber(int number)`
        + Sets the number to be stored in this node
    + `public int getNumber()`
        + Gets the number stored in this node
    + `public boolean isPrime()`
        + Returns whether the number stored in this node is prime or not
    + `public void setFirstFactor(int factor)`
        + Sets the first factor child of this node
    + `public FactorNode getFirstFactor()`
        + Gets the first factor child of this node
    + `public void setSecondFactor(int factor)`
        + Sets the Second factor child of this node
    + `public FactorNode getSecondFactor()`
        + Gets the Second factor child of this node
    + `public void printTree()`
        + Returns the prime factorization of the number stored in this node, displayed as a multiplication problem. So if `getNumber()` == 25, it will print `25=5x5`

