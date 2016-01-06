
/**
 * A luzhanqi board that stores the position of each piece.
 * 
 * @author Tony Liang
 * @version 1.1 2015-01-03
 */
public class Board
{
    //constants
    private static final int COLUMN_B = 1;
    private static final int COLUMN_C = 2;
    private static final int COLUMN_D = 3;
    
    private static final int NUMBER_OF_COLUMNS = 5;
    private static final int NUMBER_OF_ROWS = 12;
    
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
    
    private static final int ROW_3 = 2;
    private static final int ROW_4 = 3;
    private static final int ROW_5 = 4;
    private static final int ROW_12 = 7;
    private static final int ROW_13 = 8;
    private static final int ROW_14 = 9;
    
    //instance fields
    private Piece[][] board = new Piece [NUMBER_OF_COLUMNS][NUMBER_OF_ROWS];
    /**
     * Constucts a board with default characteristics.
     */
    public Board()
    {
        for (int columnNumber = 0; columnNumber < NUMBER_OF_COLUMNS; columnNumber++)
        {
            for (int rowNumber = 0; rowNumber < NUMBER_OF_ROWS; rowNumber++)
            {
                board[columnNumber][rowNumber] = null;
            }// end of for (int rowNumber = 0; rowNumber < NUMBER_OF_ROWS; rowNumber++)
        }// end of for (int columnNumber = 0; columnNumber < NUMBER_OF_COLUMNS; columnNumber++)
    }// end of constructor Board()
    
    /**
     * Constructs a board that is identical to the another board.
     * 
     * @param board the other board to be made identical to
     */
    public Board(Board board)
    {
        Piece[][] otherBoard = board.getBoard();
        for (int columnNumber = 0; columnNumber < NUMBER_OF_COLUMNS; columnNumber++)
        {
            for (int rowNumber = 0; rowNumber < NUMBER_OF_ROWS; rowNumber++)
            {
                this.board[columnNumber][rowNumber] = otherBoard[columnNumber][rowNumber];
            }// end of for (int rowNumber = 0; rowNumber < NUMBER_OF_ROWS; rowNumber++)
        }// for (int columnNumber = 0; columnNumber < NUMBER_OF_COLUMNS; columnNumber++)
    }// end of constructor Board(Board boa)
    
    /*
     * Accessors
     */
    
    /**
     * Returns the board of this Board.
     * 
     * @return a 2D array of Pieces representing the board of this Luzhanqi game
     */
    public Piece[][] getBoard()
    {
        return board;
    }// end of method getBoard()
    
    /**
     * Returns the piece at the specified x and y coordinates.
     * 
     * @param x the x coordinate of the piece
     * @param y the y coordinate of the piece
     * @return the Piece at board location x and y
     */
    public Piece getPiece(int x, int y)
    {
        return board[x][y];
    }// end of method getPiece(int x, int y)
    
    /*
     * Mutators
     */
    
    /**
     * Adds a piece to the board, returns true if the piece is added correctly, false otherwise. 
     * 
     * @param piece the piece to be added
     * @param locationx the x coordinate of the location the piece is to be added in
     * @param locationy the y coordinate of the location the piece is to be added in
     * @return true if the piece is added correctly, otherwise false
     */
    public boolean addPiece(Piece piece, int locationx, int locationy)
    {
        //add the piece if there is no existing piece at that location
        if (board[locationx][locationy] == null) 
        {
            board[locationx][locationy] = piece;
            return true;
        }// end of if (board[locationx][locationy] == null) 
        return false;
    }// end of addPiece(Piece piece, int locationx, int locationy)
    
    /**
     * Moves piece from <code>locationx1</code>,<code>locationy1</code> to <code>locationx2</code>, 
     * <code>locationy2</code> and collides with the piece (if existing) at <code>locationx2</code>, 
     * <code>locationy2</code>. Returns true if the piece was moved successfully, false otherwise.
     * 
     * @param locationx1 the x coordinate of the location of the piece to be moved
     * @param locationy1 the y coordiante of the location of the piece to be moved
     * @param locationx2 the x coordinate of the destination location
     * @param locationy2 the y coordinate of the destination location
     * @return true if the piece is moved successfully, false otherwise.
     */
    public boolean movePiece(int locationx1, int locationy1, int locationx2, int locationy2)
    {
        //is there a piece at the destination location?
        if (board[locationx2][locationy2] != null)
        {
            //can you collide with pieces of the same side?
            if (board[locationx1][locationy1].getSide() != board[locationx2][locationy2].getSide()) 
            {
                //can a piece collide with another piece at a campsite?
                if ((locationx2 == COLUMN_B || locationx2 == COLUMN_D) && (locationy2 == ROW_3 || locationy2 == ROW_5 || locationy2 == ROW_12 || locationy2 == ROW_14)) return false;    
                else if (locationx2 == COLUMN_C && (locationy2 == ROW_4 || locationy2 == ROW_13)) return false; 
                //find which piece is greater and make adjustments to the board accordingly
                if (board[locationx1][locationy1].compareTo(board[locationx2][locationy2]) > 0)
                {
                    //if the selected piece is greater than the destination piece, then the selected piece moves to destination
                    removePiece(locationx2, locationy2);
                    addPiece(board[locationx1][locationy1], locationx2, locationy2);
                    removePiece(locationx1, locationy1);
                }// end of if (board[locationx1][locationy1].compareTo(board[locationx2][locationy2]) > 0)
                else if (board[locationx1][locationy1].compareTo(board[locationx2][locationy2]) < 0)
                {
                    //if the selected piece is smaller, then the selected piece is eliminated
                    removePiece(locationx1, locationy1);
                }// end of else if (board[locationx1][locationy1].compareTo(board[locationx2][locationy2]) < 0)
                else if (board[locationx1][locationy1].compareTo(board[locationx2][locationy2]) == 0)
                {
                    //if both pieces are equal, then both pieces are removed
                    removePiece(locationx1, locationy1);
                    removePiece(locationx2, locationy2);
                }// end of else if (board[locationx1][locationy1].compareTo(board[locationx2][locationy2]) == 0)
            }
            else 
            {
                //if both pieces are from the same side
                return false;
            }// end of if (board[locationx1][locationy1].getSide() != board[locationx2][locationy2].getSide()) 
        }
        else 
        {
            //add piece to destination
            addPiece(board[locationx1][locationy1], locationx2, locationy2);
            removePiece(locationx1, locationy1);
        }// end of if (board[locationx2][locationy2] != null)
        return true;
    }// end of method movePiece(int locationx1, int locationy1, int locationx2, int locationy2)
    
    /**
     * Removes the piece at the specified location from the board
     * 
     * @param locationx the x coordinate of the location where the piece is to be removed
     * @param locationy the y coordinate of the location where the piece is to be removed
     */
    public void removePiece(int locationx, int locationy)
    {
        if (board[locationx][locationy] != null) board[locationx][locationy] = null;
    }// end of removePiece(int locationx, int locationy)
}// end of class Board()
