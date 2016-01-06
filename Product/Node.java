import java.lang.Throwable;
import java.lang.Object;

/**
 * A node in an abstract data strcture.
 * 
 * @author Tony Liang
 * @version 1.0 2014-10-23 
 */
public class Node 
{
    //instance fields
    private Object nodeData;
    private Node nextNode;

    /**
     * Creates a node with default characteristics.
     */
    public Node()
    {
        nextNode = null;
        nodeData = "";
    }// end of constructor Node()
    
    /**
     * Creates a node with specified characteristics.
     * 
     * @param newNodeData the data to be stored in this node
     * @param nextNode the node that follows this node
     */
    public Node(Object newNodeData, Node nextNode)
    {
        nodeData = newNodeData;
        
        if (nextNode != null)
        {
            this.nextNode = nextNode;
        }
        else
        {
            this.nextNode = null;
        }// end of if (nextNode != null)
    }// end of constructor Node (Object newNodeData, Node nextNode)
    
    /*
     * Accessors
     */
    
    /**
     * Indicates whether another Node has data identical to this node's data.
     * 
     * @param otherNode the object whose data is compared to this node's data
     * @return <code>true</code> if the objects have identical data or <code>false</code> otherwise
     */
    public boolean equals(Object otherNode)
    {
        //Can the other entry point to null?
        if (otherNode == null) return false;
        //Is the other entry a Node?
        if (this.getClass() != otherNode.getClass()) return false;
        
        Node other = (Node) otherNode;
        
        //Is the other entry a reference to this entry?
        if (this == other) return true;
        if (!this.getData().equals(other.getData())) return false;
        if (this.getNext() != other.getNext()) return false;
        
        return true;
    }// end of method equals()
    
    /**
     * Returns the data stored in this node.
     * 
     * @return the data of this node
     */
    public Object getData()
    {
        return nodeData;
    }// end of method getData()
    
    /**
     * Returns the node that follows this node.
     * 
     * @return the node which follows this node
     */
    public Node getNext()
    {
        return nextNode;
    }// end of method getNextNode()
    
    /**
     * Returns a string representation of this node.
     * 
     * @return a string representation of this node
     */
    public String toString ()
    {
        return getClass().getName()
        + "["
        + "data: " + nodeData
        + ", next node: " + this.nextNode
        + "]";
    }// end of method toString()
    
    /*
     * Mutators
     */
    
    /**
     * Sets the data to be stored in this node.
     * 
     * @param the data to be stored in this node
     */
    public void setData (Object newNodeData)
    {
        nodeData = newNodeData;    
    }// end of method setData (Object newNodeData)
    
    /**
     * Sets the node that follows this node.
     * 
     * @param the node that follows this node
     */
    public void setNext (Node nextNode)
    {
        this.nextNode = (Node) nextNode;
    }// end of method setNextNode (Object nextNode)
}// end of class Node 
