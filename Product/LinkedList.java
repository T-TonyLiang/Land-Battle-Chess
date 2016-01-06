
/**
 * Creates a Linked 
 * 
 * @author Tony Liang 
 * @version 1.0 2014-10-23
 */
public class LinkedList
{
    //intance fields
    private Node current;
    private Node head;
    private Node tail;
    
    /**
     * Constructs a LinkedList with default characteristics. 
     */
    public LinkedList()
    {
        head = null;
        tail = null;
    }// end of constructor LinkedList()
    
    /**
     * Initializes a linked list/
     * 
     * @param head the head of the linked list
     */
    public void initializeList(Node head)
    {
        //Can the list be empty?
        if (head != null)
        {
            this.head = head;
        }
        else
        {
            return; 
        }// end of if (head != null)
        current = head;
        //find the tail of the linked list
        while (current.getNext() != null)
        {
            tail = current.getNext();
            current = current.getNext();
        }// end of while (current.getNextNode() != null)
    }// end of method initializeList(Node head)
    
    /**
     * Returns the head of the linked list.
     * 
     * @return the head of the linked list
     */
    public Node getHead()
    {
        return head;
    }//end of method getHead()
    
    /**
     * Returns the tail of the linked list.
     * 
     * @return the tail of the linked list
     */
    public Node getTail()
    {
        return tail;
    }// end of method getTail()
    
    /**
     * Inserts a node at the head of the linked list. The entry must be a Node.
     * 
     * @param newHead the new head of the linked list
     */
    public void insertAtHead(Node newHead)
    {
        newHead.setNext(head);
        head = newHead;
    }// end of method insertAtHead(Node newHead)
    
    /**
     * Inserts a node at the tail of the linked list. The entry must be a Node.
     * 
     * @param newTail the new tail of the linked list
     */
    public void insertAtTail(Node newTail)
    {
        tail.setNext(newTail);
        tail = newTail;
    }// end of method insertAtTail (Node newTail)
    
    /**
     * Inserts a node in the middle of the linked list. Entry must be a Node and its next node should be an entry in a linked list. 
     * 
     * @param newNode the node to be inserted in the middle of the linked list
     */
    public void insertInMiddle (Node newNode, Node insertionPoint)
    {
        //Can the new node be null?
        if (newNode == null || insertionPoint == null)
        {
            return;
        }
        newNode.setNext(insertionPoint.getNext());
        insertionPoint.setNext(newNode);
        
    }// end of method insertInMiddle (Node newNode)
    
    /**
     * Returns a string representation of this linked list.
     * 
     * @return a string representation of this linked list
     */
    public String toString ()
    {
        return this.getClass().getName()
        + "["
        + head
        + "]";
    }// end of method toString()
}// end of class LinkedList
