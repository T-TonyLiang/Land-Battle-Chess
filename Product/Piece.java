
/**
 * A luzhanqi piece.
 * 
 * @author Tony Liang
 * @version 1.1 2015-01-10
 */
public class Piece implements Comparable
{
    //constants
    private static final String EXCEPTION_CAST_ERROR = "otherPiece must be a Piece";
    private static final String EXCEPTION_NULL_POINTER_ERROR = "otherPiece may not be null";
    
    private final String IMAGE_BOMB = "Images\\Bomb.png";
    private final String IMAGE_CAPTAIN = "Images\\Captain.png";
    private final String IMAGE_COLONEL = "Images\\Colonel.png";
    private final String IMAGE_ENGINEER = "Images\\Engineer.png";
    private final String IMAGE_FIELD_MARSHALL = "Images\\Field Marshall.png";
    private final String IMAGE_FLAG = "Images\\Flag.png";
    private final String IMAGE_GENERAL = "Images\\General.png";
    private final String IMAGE_LANDMINE = "Images\\Landmine.png";
    private final String IMAGE_LIEUTENANT_GENERAL = "Images\\Lieutenant General.png";
    private final String IMAGE_LIEUTENANT = "Images\\Lieutenant.png";
    private final String IMAGE_MAJOR_GENERAL = "Images\\Major General.png";
    private final String IMAGE_MAJOR = "Images\\Major.png";
    
    //ranks of the pieces
    private static final int RANK_BOMB = -1;
    private static final int RANK_CAPTAIN = 2;
    private static final int RANK_COLONEL = 4;
    private static final int RANK_ENGINEER = 0;
    private static final int RANK_FIELD_MARSHALL = 8;
    private static final int RANK_FLAG = -2;
    private static final int RANK_GENERAL = 7;
    private static final int RANK_LANDMINE = 9;
    private static final int RANK_LIEUTENANT = 1;
    private static final int RANK_LIEUTENANT_GENERAL = 6;
    private static final int RANK_MAJOR = 3;
    private static final int RANK_MAJOR_GENERAL = 5;
    
    //instance fields
    private int rank; 
    private String image_location;
    private int side;
    private String name;
    private int uniqueID;
    
    
    /**
     * Constucts a piece with the specified rank and posisition on the screen where it is to be displayed.
     * 
     * @param side the side this piece belongs to. 0 for left side and 1 for the right side of the board. 
     * @param rank the rank of the piece
     */
    public Piece(int side, int rank, int uniqueID)
    {
        if (rank <= RANK_LANDMINE && rank >= RANK_FLAG)
        {
            this.rank = rank;
            //Determine by rank, the image this piece will be represented by
            if (rank == RANK_ENGINEER)
            {
                image_location = IMAGE_ENGINEER;
                name = "Engineer";
            }// end of if (rank == 0)
            else if (rank == RANK_LIEUTENANT)
            {
                image_location = IMAGE_LIEUTENANT;
                name = "Lieutenant";
            }// end of else if (rank == 1)
            else if (rank == RANK_CAPTAIN)
            {
                name = "Captain";
                image_location = IMAGE_CAPTAIN;
            }// end of else if (rank == 2)
            else if (rank == RANK_MAJOR)
            {
                image_location = IMAGE_MAJOR;
                name = "Major";
            }// end of else if (rank == 3)
            else if (rank == RANK_COLONEL)
            {
                image_location = IMAGE_COLONEL;
                name = "Colonel";
            }// end of else if (rank == 4)
            else if (rank == RANK_MAJOR_GENERAL)
            {
                image_location = IMAGE_MAJOR_GENERAL;
                name = "Major General";
            }// end of else if (rank == 5)
            else if (rank == RANK_LIEUTENANT_GENERAL) 
            {
                image_location = IMAGE_LIEUTENANT_GENERAL;
                name = "Lieutenant General";
            }// end of else if (rank == 6)
            else if (rank == RANK_GENERAL) 
            {
                image_location = IMAGE_GENERAL;
                name = "General";
            }// end of else if (rank == 7)
            else if (rank == RANK_FIELD_MARSHALL)
            {
                image_location = IMAGE_FIELD_MARSHALL;
                name = "Field Marshall";
            }// end of else if (rank == 8)
            else if (rank == RANK_BOMB)
            {
                name = "Bomb";
                image_location = IMAGE_BOMB;
            }// end of else if (rank == -1)
            else if (rank == 9) 
            {   
                image_location = IMAGE_LANDMINE;
                name = "Landmine";
            }// end of else if (rank == 9)
            else if (rank == -2) 
            {
                image_location = IMAGE_FLAG;
                name = "Flag";
            }// end of else if (rank == -2)
            
            this.uniqueID = uniqueID;
            
            if (side == 1 || side == 0) this.side = side;
        }//end of if (rank < 12 && rank > -4)
    }//end of constructor Piece(int rank, int x_coordinate, int y_coordinate, int width, int height)
    
    /*
     * Accessors
     */
    
    /**
     * Compares another piece to this piece. Returns 1 if the collision between the pieces results in this piece victorious, 
     * 0 if both pieces are eliminated and -1 otherwise. 
     * 
     * @param otherPiece the piece to be comapared to this piece
     * @return 1 if the collision between the pieces results in this piece victorious, 0 if both pieces are eliminated and -1 otherwise
     */
    public int compareTo(Object otherPiece)
    {
        //Specified by the Comparable interface
        if (otherPiece == null) throw new NullPointerException(EXCEPTION_NULL_POINTER_ERROR);
        //is otherPiece a reference to this entry?
        if (this == otherPiece) return 0;
        //Is the other entry a Piece?
        if (otherPiece.getClass() != this.getClass()) throw new ClassCastException(EXCEPTION_CAST_ERROR);
        
        Piece other = (Piece) otherPiece;
        
        //Bombs are eliminated with any piece upon a collision
        if (other.getRank() == RANK_BOMB || this.getRank() == RANK_BOMB) return 0;
        //Engineers can remove landmines
        if (other.getRank() == RANK_ENGINEER && this.getRank() == RANK_LANDMINE) return -1;
        if (other.getRank() == RANK_LANDMINE && this.getRank() == RANK_ENGINEER) return 1;
        //otherwise, normal order of ranks are followed
        if (other.getRank() == this.getRank()) return 0;
        if (other.getRank() > this.getRank()) return -1;
        return 1;
    }//end of method compareTo(Piece otherPiece)
    
    /**
     * Indicates whether another piece, when collided with this piece, will result in both pieces being eliminated. 
     * Returns true if both pieces are eliminated and false otherwise.
     * 
     * @param otherPiece the piece to be compared with this piece
     * @return true if both pieces are eliminated and false otherwise
     */
    public boolean equals(Object otherPiece)
    {
        //is otherPiece a pointer to null?
        if (otherPiece == null) return false;
        //is otherPiece actually a piece?
        if (otherPiece.getClass() != this.getClass()) return false;
        //is otherPiece a reference to this entry?
        if (otherPiece == this) return true;
        
        Piece other = (Piece) otherPiece;
        
        //What happens if a piece is a bomb?
        if (other.getRank() == RANK_BOMB || this.getRank() == RANK_BOMB) return true;
        return other.getRank() == this.getRank();
    }//end of method equals(Object otherPiece)
    
    /**
     * Returns the unique ID number of this Piece.
     * 
     * @return the unique ID number of this piece
     */
    public int getID()
    {
        return uniqueID;
    }//end of method getID()
    
    /**
     * Returns the name of the image file.
     * 
     * @return name of the image file
     */
    public String getImageLocation()
    {
        return image_location;
    }//end of method getImageLocation()
    
    /**
     * Returns the name of this piece.
     * 
     * @return the name of this piece
     */
    public String getName()
    {
        return name;
    }// end of method getName()
    
    /**
     * Returns the rank of this piece.
     * 
     * @return the rank of this piece
     */
    public int getRank()
    {
        return rank;
    }//end of method getRank()
    
    /**
     * Returns the side of this piece.
     * 
     * @return the side of this piece
     */
    public int getSide()
    {
        return side;
    }//end of method getSide()
    
    /**
     * Returns a string representation of this piece.
     * 
     * @return a string representation of this piece
     */
    public String toString()
    {
        String pieceSide = "";
        if (side == 0) pieceSide = "red";
        if (side == 1) pieceSide = "blue";
        
        return "["
        + "Rank: " + name
        + "Side: " + pieceSide
        + "]";
    }//end of method toString()
}//end of class Piece()
