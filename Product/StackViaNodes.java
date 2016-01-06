
/**
 * A stack of characters implemented via an array
 * 
 * @author Tony Liang
 * @version 1.1 2014-10-29
 */
public class StackViaNodes
{
    private static final String EXCEPTION_EMPTY_STACK = "This stack is empty";
    
    // instance variables 
    private LinkedList data;
    private int size;
    private Node top;

    /**
     * Constructs a stack of string.
     * 
     * @param head the head of the linked list
     */
    public StackViaNodes()
    {
        size = 0;
        top = null;
    }// end of constructor (StackViaArray)

    /**
     * Returns the number of elements on this stack.
     * 
     * @return the number of elements on this stack
     */
    public int getSize()
    {
        if (data.getHead() == null) return 0;
        return size;
    }// end of method getSize()
    
    /**
     * Returns <code>true</code> if the stack is empty, otherwise <code>false</code>.
     * 
     * @return <code>true</code> if this stack is empty, otherwise <code>false</code>.
     */
    public boolean isEmpty()
    {
        return size == 0;
    }// end of method isEmpty()
    
    /**
     * Returns a string representation of this stack.
     * 
     * @return a string representation of this stack
     */
    public String toString()
    {
        return this.getClass()
          + "["
          + "Top: " + top.toString()
          + "Size: " + size
          + "]";
    }// end of method toString()
    
    /**
     * Pops the top character from the stack. 
     * 
     * @return the top character from the stack
     */
    public Object pop() throws EmptyStackException
    {
        if (size == 0) throw new EmptyStackException(EXCEPTION_EMPTY_STACK);
        Node current = top;
        top = top.getNext();
        size--;
        return current;
        
    }// end of method pop()
    
    /**
     * Pushes the string to the top of the stack.
     * 
     * @param the string to be pushed to the top of the stack
     */
    public void push(Board string)
    {
        top = new Node (string, top);
        size++;
    }//end of method push()
}// end of class StackViaArray