import java.awt.Toolkit;
import java.awt.Container;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.Math;

/**
 * A luzhanqi (Land Battle Chess) game.
 * 
 * 
 * @author Tony Liang
 * @version 1.1 2015-01-04
 */
public class Game
{      
    /*
     * constants
     */
    //integer representing blue side
    private static final int BLUE_SIDE = 2;
    //difference between the user input coordinates and the array elements (Ex. When user inputs Row 11, it references index 6 of the array storing the information)
    private static final int BLUE_SIDE_DIFFERENCE = 5;
    //the first and last rows of the blue side on the board
    private static final int BLUE_SIDE_FRONTLINE = 11;
    private static final int BLUE_SIDE_LAST_ROW = 16;
    //x, y coordinates, width and height of the Luzhanqi board image
    private static final int[] BOARD_DISPLAY_COORDINATES = {-475,-150,1700,1000};
    //constants representing the array index of each column on the board, with the x coordinates for screen display
    private static final int COLUMN_A = 0;
    private static final int COLUMN_A_COORDINATE = -10;
    private static final int COLUMN_B = 1;
    private static final int COLUMN_B_COORDINATE = 95;
    private static final int COLUMN_C = 2;
    private static final int COLUMN_C_COORDINATE = 198;
    private static final int COLUMN_D = 3;
    private static final int COLUMN_D_COORDINATE = 301;
    private static final int COLUMN_E = 4;
    private static final int COLUMN_E_COORDINATE = 404;
    //number of lines that will be skipped to clear screen
    private static final int CLEAR_SCREEN = 30;
    //dimensions of the JFrame
    private static final int[] FRAME_SIZE = {1700, 1000};
    //image location for the luzhanqi board
    private static final String GAME_BOARD_FILE_NAME = "Images\\Board.png";
    //height of the images of the pieces that are to be displayed as JLabels
    private static final int LABEL_HEIGHT = 200;
    //constants representing the dimensions of the board, the amount of pieces, sides and players involved in the game
    private static final int NUMBER_OF_COLUMNS = 5;
    private static final int NUMBER_OF_PIECES_ON_ONE_SIDE = 25;
    private static final int NUMBER_OF_PLAYERS = 2;
    private static final int NUMBER_OF_ROWS = 12;
    private static final int NUMBER_OF_SIDES = 2;
    //piece numbers to be assigned to a piece of each side
    private static final int[] PIECE_NUMBER = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 
        15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25};
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
    //integer representing red side
    private static final int RED_SIDE = 1;
    //difference between input value and the array element number (Ex. User inputs row 6, and it is stored in index 5 of the array )
    private static final int RED_SIDE_DIFFERENCE = 1;
    //the first and last row of the red side territory on the board
    private static final int RED_SIDE_FRONTLINE = 6;
    private static final int RED_SIDE_LAST_ROW = 1;
    //constants representing the array index for each row on the board, with their y coordinates for screen display
    private static final int ROW_1 = 0;
    private static final int ROW_1_COORDINATE = -25;
    private static final int ROW_2 = 1;
    private static final int ROW_2_COORDINATE = 25;
    private static final int ROW_3 = 2;
    private static final int ROW_3_COORDINATE = 73;
    private static final int ROW_4 = 3;
    private static final int ROW_4_COORDINATE = 120;
    private static final int ROW_5 = 4;
    private static final int ROW_5_COORDINATE = 167;
    private static final int ROW_6 = 5;
    private static final int ROW_6_COORDINATE = 215;
    private static final int ROW_11 = 6;
    private static final int ROW_11_COORDINATE = 313;
    private static final int ROW_12 = 7;
    private static final int ROW_12_COORDINATE = 363;
    private static final int ROW_13 = 8;
    private static final int ROW_13_COORDINATE = 410;
    private static final int ROW_14 = 9;
    private static final int ROW_14_COORDINATE = 458;
    private static final int ROW_15 = 10;
    private static final int ROW_15_COORDINATE = 506;
    private static final int ROW_16 = 11;
    private static final int ROW_16_COORDINATE = 554;
    //total number of pieces
    private static final int TOTAL_NUMBER_OF_PIECES = 50;
    //width of the image of the pieces to be displayed on the screen
    private static final int LABEL_WIDTH = 200;
    //each piece is to be assigned a unique ID, this is a set of ID for one side
    private static final int[] UNIQUE_ID_SET = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 
        15, 16, 17, 18, 19, 20, 21, 22, 23, 24};
    //locations of the images representing unknown pieces
    private static final String UNKNOWN_BLUE_PIECE = "Images\\Blue.png";
    private static final String UNKNOWN_RED_PIECE = "Images\\Red.png";

    //variables
    private static Board board;
    private static boolean flag;
    private static JFrame frame;
    private static Container pane;
    private static Piece[][] piece;
    private static BufferedImage[] pieceIcon;
    private static JLabel[] pieceLabel;
    private static BufferedReader reader;
    private static StackViaNodes recordOfTurns;
    private static Piece selectedPiece;
    private static JLabel[] unknownPieceLabel;
    
    /**
     * Main method. Welcomes the user to the program, sets up the game and guides the user through the steps of the game. 
     * 
     * @param console_arguments not used in this method. 
     */
    public static void main (String[] console_arguments)
    {
        try
        {
            //how the user is to input information via console
            BufferedReader reader = new BufferedReader (new InputStreamReader(System.in));
            //how the record of each turn is to be stored
            recordOfTurns = new StackViaNodes();
            //display window for displaying the board with its pieces
            createJFrame();
            System.out.println("Welcome to Luzhanqi!\n\n");
            displayRules();
            reader.read();
            //guide user to being placing pieces on the board
            setUpBoard();
            //store board after both users are done placing the pieces on the board
            pushBoard();
            //flags have not been captured yet
            flag = true;
            //when does the game end?
            do
            {
                takeTurn();
            }
            while (flag);
            
            //allow players to analyze game outcome by displaying the remaining pieces on each side
            System.out.println("Press Enter to see the remaining Blue Side pieces");
            reader.readLine();
            displayBlueSideBoard();
            System.out.println("Press Enter to see the remaining Red Side pieces");
            reader.readLine();
            displayRedSideBoard();
            
            System.out.println("\nThank you for playing Luzhanqi (Chinese Land Battle Chess)");
            reader.close();
        }
        catch(IOException ioException)
        {
        }//end of catch (IOException ioException)
    }// end of main method
    
    /**
     * Checks if the flags have been captured or bombed. Returns 0 if red flag (Player 1) is missing or 1 if blue flag (Player 2) is missing, otherwise return -1
     * 
     * @return 0 if the red flag is missing, 1 if the blue flag is missing or -1 otherwise
     */
    public static int checkFlag()
    {
        //are the team flags present on the board?
        if (pieceEliminated(piece[0][0])) return 0;
        if (pieceEliminated(piece[1][0])) return 1;
        return -1;
    }// end of method checkFlag()
    
    /**
     * Displays a series of new lines to clear the terminal window so evidence of the previous player's movements are removed.
     */
    public static void clearScreen()
    {
        //how many new lines does it take to completely 'clear' the screen?
        for (int numberOfLinesToClear = 0; numberOfLinesToClear < CLEAR_SCREEN; numberOfLinesToClear ++)
        {
            System.out.println("\n\n\n\n");
        }// end of for (int numberOfLinesToClear = 0; numberOfLinesToClear < CLEAR_SCREEN; numberOfLinesToClear ++)
    }// end of method clearScreen()
    
    /**
     * Converts a letter that is a column on the board to the respective integer representing that column.
     * 
     * @param letter a capital letter that is a column on the board
     * @return an integer that represents a column on the board
     */
    public static int convertLetterToNumber(String letter)
    {
        if (letter.equals("A")) return COLUMN_A;
        else if (letter.equals("B")) return COLUMN_B;
        else if (letter.equals("C")) return COLUMN_C;
        else if (letter.equals("D")) return COLUMN_D;
        else if (letter.equals("E")) return COLUMN_E;
        //if letter doesnt match a column on the board
        return -1;
    }// end of convertLetterToNumber(String letter)
    
    /**
     * Converts String containing an integer that the user inputs as the y coordinate, (Ex. 6) to an integer representing the array index of that y coordinate.
     * 
     * @param integer a String the user inputs as the y coordinate of a piece. It should containing an integer to be converted
     * @return an integer representing the array index of that y coordinate
     */
    public static int convertYCoordinate(String integer)
    {
        //which side does the y coordinate belong to?
        if (Integer.parseInt(integer) <= RED_SIDE_FRONTLINE && Integer.parseInt(integer) >= RED_SIDE_LAST_ROW)
        {
            //red side inputs and their respective array indexes differ by 1
            return Integer.parseInt(integer) - RED_SIDE_DIFFERENCE;
        }
        if (Integer.parseInt(integer) <= BLUE_SIDE_LAST_ROW && Integer.parseInt(integer) >= BLUE_SIDE_FRONTLINE)
        {
            //blue side inputs and their respective array indexes differ by 5 (Ex. Row 15 is index 10 of the array)
            return Integer.parseInt(integer) - BLUE_SIDE_DIFFERENCE;
        }
        //if the y coordinate input from the user is not a valid row number?
        return -1;
    }// end of method convertYCoordinate(String integer)
    
    /**
     * Creates the JFrame and a content pane used for displaying the board and its pieces. 
     */
    public static void createJFrame()
    {
        //name of the window?
        frame = new JFrame("Luzhanqi");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //initialize pane
        pane = frame.getContentPane();
        pane.setLayout(null);
        
        //create dimensions of the frame
        frame.setSize(FRAME_SIZE[0],FRAME_SIZE[1]);
        frame.setVisible(true);
        //continue setting up required images for displaying on the frame
        initializeBoard();
    }
    
    /**
     * Creates the 50 pieces required for a game. 
     */
    public static void createPieces()
    {
        //store pieces in 2D array
        piece = new Piece[NUMBER_OF_SIDES][NUMBER_OF_PIECES_ON_ONE_SIDE]; 
        for (int sideOfPiece = 0; sideOfPiece < NUMBER_OF_SIDES; sideOfPiece++)
        {
            //create 25 pieces for blue and red side, each with a unique ID and rank. 
            piece[sideOfPiece][PIECE_NUMBER[0]]  = new Piece(sideOfPiece, RANK_FLAG,                 sideOfPiece * NUMBER_OF_PIECES_ON_ONE_SIDE + UNIQUE_ID_SET[0]);
            piece[sideOfPiece][PIECE_NUMBER[1]]  = new Piece(sideOfPiece, RANK_LANDMINE,             sideOfPiece * NUMBER_OF_PIECES_ON_ONE_SIDE + UNIQUE_ID_SET[22]);
            piece[sideOfPiece][PIECE_NUMBER[2]]  = new Piece(sideOfPiece, RANK_LANDMINE,             sideOfPiece * NUMBER_OF_PIECES_ON_ONE_SIDE + UNIQUE_ID_SET[23]);
            piece[sideOfPiece][PIECE_NUMBER[3]]  = new Piece(sideOfPiece, RANK_LANDMINE,             sideOfPiece * NUMBER_OF_PIECES_ON_ONE_SIDE + UNIQUE_ID_SET[24]);
            piece[sideOfPiece][PIECE_NUMBER[4]]  = new Piece(sideOfPiece, RANK_BOMB,                 sideOfPiece * NUMBER_OF_PIECES_ON_ONE_SIDE + UNIQUE_ID_SET[1]);
            piece[sideOfPiece][PIECE_NUMBER[5]]  = new Piece(sideOfPiece, RANK_BOMB,                 sideOfPiece * NUMBER_OF_PIECES_ON_ONE_SIDE + UNIQUE_ID_SET[2]);
            piece[sideOfPiece][PIECE_NUMBER[6]]  = new Piece(sideOfPiece, RANK_ENGINEER,             sideOfPiece * NUMBER_OF_PIECES_ON_ONE_SIDE + UNIQUE_ID_SET[3]);
            piece[sideOfPiece][PIECE_NUMBER[7]]  = new Piece(sideOfPiece, RANK_ENGINEER,             sideOfPiece * NUMBER_OF_PIECES_ON_ONE_SIDE + UNIQUE_ID_SET[4]);
            piece[sideOfPiece][PIECE_NUMBER[8]]  = new Piece(sideOfPiece, RANK_ENGINEER,             sideOfPiece * NUMBER_OF_PIECES_ON_ONE_SIDE + UNIQUE_ID_SET[5]);
            piece[sideOfPiece][PIECE_NUMBER[9]]  = new Piece(sideOfPiece, RANK_LIEUTENANT,           sideOfPiece * NUMBER_OF_PIECES_ON_ONE_SIDE + UNIQUE_ID_SET[6]);
            piece[sideOfPiece][PIECE_NUMBER[10]] = new Piece(sideOfPiece, RANK_LIEUTENANT,           sideOfPiece * NUMBER_OF_PIECES_ON_ONE_SIDE + UNIQUE_ID_SET[7]);
            piece[sideOfPiece][PIECE_NUMBER[11]] = new Piece(sideOfPiece, RANK_LIEUTENANT,           sideOfPiece * NUMBER_OF_PIECES_ON_ONE_SIDE + UNIQUE_ID_SET[8]);
            piece[sideOfPiece][PIECE_NUMBER[12]] = new Piece(sideOfPiece, RANK_CAPTAIN,              sideOfPiece * NUMBER_OF_PIECES_ON_ONE_SIDE + UNIQUE_ID_SET[9]);
            piece[sideOfPiece][PIECE_NUMBER[13]] = new Piece(sideOfPiece, RANK_CAPTAIN,              sideOfPiece * NUMBER_OF_PIECES_ON_ONE_SIDE + UNIQUE_ID_SET[10]);
            piece[sideOfPiece][PIECE_NUMBER[14]] = new Piece(sideOfPiece, RANK_CAPTAIN,              sideOfPiece * NUMBER_OF_PIECES_ON_ONE_SIDE + UNIQUE_ID_SET[11]);
            piece[sideOfPiece][PIECE_NUMBER[15]] = new Piece(sideOfPiece, RANK_MAJOR,                sideOfPiece * NUMBER_OF_PIECES_ON_ONE_SIDE + UNIQUE_ID_SET[12]);
            piece[sideOfPiece][PIECE_NUMBER[16]] = new Piece(sideOfPiece, RANK_MAJOR,                sideOfPiece * NUMBER_OF_PIECES_ON_ONE_SIDE + UNIQUE_ID_SET[13]);
            piece[sideOfPiece][PIECE_NUMBER[17]] = new Piece(sideOfPiece, RANK_COLONEL,              sideOfPiece * NUMBER_OF_PIECES_ON_ONE_SIDE + UNIQUE_ID_SET[14]);
            piece[sideOfPiece][PIECE_NUMBER[18]] = new Piece(sideOfPiece, RANK_COLONEL,              sideOfPiece * NUMBER_OF_PIECES_ON_ONE_SIDE + UNIQUE_ID_SET[15]);
            piece[sideOfPiece][PIECE_NUMBER[19]] = new Piece(sideOfPiece, RANK_MAJOR_GENERAL,        sideOfPiece * NUMBER_OF_PIECES_ON_ONE_SIDE + UNIQUE_ID_SET[16]);
            piece[sideOfPiece][PIECE_NUMBER[20]] = new Piece(sideOfPiece, RANK_MAJOR_GENERAL,        sideOfPiece * NUMBER_OF_PIECES_ON_ONE_SIDE + UNIQUE_ID_SET[17]);
            piece[sideOfPiece][PIECE_NUMBER[21]] = new Piece(sideOfPiece, RANK_LIEUTENANT_GENERAL,   sideOfPiece * NUMBER_OF_PIECES_ON_ONE_SIDE + UNIQUE_ID_SET[18]);
            piece[sideOfPiece][PIECE_NUMBER[22]] = new Piece(sideOfPiece, RANK_LIEUTENANT_GENERAL,   sideOfPiece * NUMBER_OF_PIECES_ON_ONE_SIDE + UNIQUE_ID_SET[19]);
            piece[sideOfPiece][PIECE_NUMBER[23]] = new Piece(sideOfPiece, RANK_GENERAL,              sideOfPiece * NUMBER_OF_PIECES_ON_ONE_SIDE + UNIQUE_ID_SET[20]);
            piece[sideOfPiece][PIECE_NUMBER[24]] = new Piece(sideOfPiece, RANK_FIELD_MARSHALL,       sideOfPiece * NUMBER_OF_PIECES_ON_ONE_SIDE + UNIQUE_ID_SET[21]);
            
        }// end of for (int sideOfPiece = 0; sideOfPiece < NUMBER_OF_SIDES; sideOfPiece++)
    }// end of method createPieces()
    
    /**
     * Displays all blue side pieces and all red side pieces undisclosed. 
     */
    public static void displayBlueSideBoard()
    {
        //get the current state of the board
        Piece[][] boardToDisplay = board.getBoard();
        //traverse through all the possible posts of the board
        for (int columnNumber = 0; columnNumber < NUMBER_OF_COLUMNS; columnNumber ++)
        {
            for (int rowNumber = 0; rowNumber < NUMBER_OF_ROWS; rowNumber++)
            {
                //remove the covering on any found blue side pieces and display the piece that is located on the board
                if (boardToDisplay[columnNumber][rowNumber] != null && boardToDisplay[columnNumber][rowNumber].getSide() == 1)
                {
                    //a piece's uniqueID can be used to find the JLabel for that piece. 
                    unknownPieceLabel[boardToDisplay[columnNumber][rowNumber].getID()].setBounds(0,0,0,0);
                    pieceLabel[boardToDisplay[columnNumber][rowNumber].getID()].setBounds(integerToXCoordinates(columnNumber), integerToYCoordinates(rowNumber),LABEL_WIDTH, LABEL_HEIGHT);
                }// end of if (boardToDisplay[columnNumber][rowNumber] != null && boardToDisplay[columnNumber][rowNumber].getSide() == 1)
                else if (boardToDisplay[columnNumber][rowNumber] != null && boardToDisplay[columnNumber][rowNumber].getSide() != 1)
                {
                    //cover blue side pieces
                    unknownPieceLabel[boardToDisplay[columnNumber][rowNumber].getID()].setBounds(integerToXCoordinates(columnNumber), integerToYCoordinates(rowNumber),LABEL_WIDTH, LABEL_HEIGHT);
                }// end of else if (boardToDisplay[columnNumber][rowNumber] != null && boardToDisplay[columnNumber][rowNumber].getSide() != 1)
            }// end of for (int rowNumber = 0; rowNumber < NUMBER_OF_ROWS; rowNumber++)
        }// end of for (int columnNumber = 0; columnNumber < NUMBER_OF_COLUMNS; columnNumber ++)
    }// end of method displayBlueSideBoard()
    
    /**
     * Determines which side of the board should be displayed and displays the board. 
     * 
     * @int sideToDisplay an integer that represents which side is to be displayed
     */
    public static void displayBoard(int sideToDisplay)
    {
       //refresh display
       displayBlueSideBoard();
       displayRedSideBoard();
       //display correct board
       if (sideToDisplay == BLUE_SIDE)
       {
          displayBlueSideBoard(); 
       }// end of if (sideToDisplay == BLUE_SIDE)
       if (sideToDisplay == RED_SIDE)
       {
          displayRedSideBoard(); 
       }// end of if (sideToDisplay == RED_SIDE)
    }// end of method displayBoard(int sideToDisplay)
    
    /**
     * Displays changes by removing the image of the pieces that has been eliminated.
     */
    public static void displayChanges()
    {
        //traverse through the board
        for (int sideNumber = 0; sideNumber < NUMBER_OF_SIDES; sideNumber++)
        {
            for (int pieceNumber = 0; pieceNumber < NUMBER_OF_PIECES_ON_ONE_SIDE; pieceNumber ++)
            {
                //image labels of found pieces at any position of the board are removed when the piece is eliminated
                if (pieceEliminated(piece[sideNumber][pieceNumber]))
                {
                    pieceLabel[piece[sideNumber][pieceNumber].getID()].setBounds(0,0,0,0);
                    unknownPieceLabel[piece[sideNumber][pieceNumber].getID()].setBounds(0,0,0,0);
                }
            }// end of for (int pieceNumber = 0; pieceNumber < NUMBER_OF_PIECES_ON_ONE_SIDE; pieceNumber ++)
        }// end of for (int sideNumber = 0; sideNumber < NUMBER_OF_SIDES; sideNumber++)
    }// end of method displayChanges()
    
    /**
     * Displays a board with all pieces undisclosed. 
     */
    public static void displayNeutralBoard()
    {
        //get the current state of the board
        Piece[][] boardToDisplay = board.getBoard();
        //traverse through all the posts of the board
        for (int columnNumber = 0; columnNumber < NUMBER_OF_COLUMNS; columnNumber ++)
        {
            for (int rowNumber = 0; rowNumber < NUMBER_OF_ROWS; rowNumber++)
            {
                if (boardToDisplay[columnNumber][rowNumber] != null)
                {
                    //display any found piece with the unknownPieceLabel
                    unknownPieceLabel[boardToDisplay[columnNumber][rowNumber].getID()].setBounds(integerToXCoordinates(columnNumber), integerToYCoordinates(rowNumber),LABEL_WIDTH, LABEL_HEIGHT);
                }// end of if (boardToDisplay[columnNumber][rowNumber] != null)
            }// end of for (int rowNumber = 0; rowNumber < NUMBER_OF_ROWS; rowNumber++)
        }// end of for (int columnNumber = 0; columnNumber < NUMBER_OF_COLUMNS; columnNumber ++)
    }// end of method displayNeutralBoard
    
    /**
     * Displays all red side pieces and leaves all blue side pieces undisclosed.
     */
    public static void displayRedSideBoard()
    {
        //get the current state of the board
        Piece[][] boardToDisplay = board.getBoard();
        //traverse through all posts of the board
        for (int columnNumber = 0; columnNumber < NUMBER_OF_COLUMNS; columnNumber ++)
        {
            for (int rowNumber = 0; rowNumber < NUMBER_OF_ROWS; rowNumber++)
            {
                //uncover red side pieces that were covered before and display them while covering blue side pieces
                if (boardToDisplay[columnNumber][rowNumber] != null && boardToDisplay[columnNumber][rowNumber].getSide() == 0)
                {
                    //a piece's uniqueID can be used to find the JLabel for that piece. 
                    unknownPieceLabel[boardToDisplay[columnNumber][rowNumber].getID()].setBounds(0,0,0,0);
                    pieceLabel[boardToDisplay[columnNumber][rowNumber].getID()].setBounds(integerToXCoordinates(columnNumber), integerToYCoordinates(rowNumber),LABEL_WIDTH, LABEL_HEIGHT);
                }// end of if (boardToDisplay[columnNumber][rowNumber] != null && boardToDisplay[columnNumber][rowNumber].getSide() == 0)
                else if ( boardToDisplay[columnNumber][rowNumber] != null && boardToDisplay[columnNumber][rowNumber].getSide() != 0)
                {
                    //cover blue side pieces
                    unknownPieceLabel[boardToDisplay[columnNumber][rowNumber].getID()].setBounds(integerToXCoordinates(columnNumber), integerToYCoordinates(rowNumber),LABEL_WIDTH, LABEL_HEIGHT);
                }// end of else if ( boardToDisplay[columnNumber][rowNumber] != null && boardToDisplay[columnNumber][rowNumber].getSide() != 0)
            }// end of for (int rowNumber = 0; rowNumber < NUMBER_OF_ROWS; rowNumber++)
        }// end of for (int columnNumber = 0; columnNumber < NUMBER_OF_COLUMNS; columnNumber ++)
    }// end of method displayRedSideBoard()
    
    /**
     * Displays the rules of the game.
     */
    public static void displayRules()
    {
        System.out.println ("Each player places their pieces, with markings indicating rank faced towards themselves, on a post. \nFlags must be placed in one of the headquarters; landmines must be placed in the columns 1,2,11 or 12.");
        System.out.println ("Players take turns, moving 1 piece per turn except landmines and the flag which cannot be moved.");
        System.out.println ("Pieces can move once to any connected post or campsite. \nPieces on the railroad can move any number of posts linearly, \ngiven that there are no pieces between the path of the piece and its destination.");
        System.out.println ("In a collision between pieces, the lower ranking piece is eliminated, \nif the attacker is successful, his piece is placed in the position of the eliminated defending piece. ");
        System.out.println ("Pieces on campsites cannot be attacked.");
        System.out.println ("If the Field Marshall is eliminated, the player must show the disposition of the flag.");
        System.out.println ("If an enemy piece successfully attacks a piece in headquarters, and it is not the flag, \nthe piece is rendered stationary in that position.");
        System.out.println ("Eliminated pieces remain unrevealed to the opponent.");
        System.out.println (" A player wins when the opponent flag is captured or bombed by an enemy piece.");
        System.out.println ("\nPress Enter to continue:");
    }// end of method displayRules()
    
    /**
     * Displays message if a team flag was captured.
     * 
     * @return true if a team flag is captured, otherwise false
     */
    public static boolean displayWinner()
    {
        //checkFlag returns 1 if blue team flag is eliminated, 2 is red side flag is eliminated or -1 otherwise. 
        if (checkFlag() == 1)
        {
            flag = false;
            System.out.println ("Congratulations! Player 1 (Red Side) has won!");
            return true;
        }
        else if (checkFlag() == 0)
        {
            flag = false;
            System.out.println("Congratulations! Player 2 (Blue Side) has won!");
            return true;
        }
        return false;
    }// end of method displayWinner()
    
    /**
     * Determines if the move is legal according to the rules, if so, the move is made and the method will return true. 
     * If the move is not legal, a message will be displayed and false will be returned.
     * 
     * @param sideNumber the side that the turn belongs to
     * @param currentXCoordinate the x coordinate of the piece that the user is trying to move
     * @param currentYCoordinate the y coordinate of the piece that the user is trying to move
     * @param destinationXCoordinate the x coordinate of the location the user wants the piece to move to
     * @param destinationYCoordinate the y coordinate of the location the user wants the piece to move to
     * @return true if the move is made, false if the move is illegal
     */
     public static boolean executeMove(int sideNumber, int currentXCoordinate, int currentYCoordinate, int destinationXCoordinate, int destinationYCoordinate)
    {
        boolean inputOK = false;
        //is the piece that is selected by the player their piece?
        if (selectedPiece.getSide() == sideNumber - 1)
        {
             //can landmines or pieces on headquarters be moved? 
             if (selectedPiece.getRank() != RANK_LANDMINE && !(isHeadquarter(currentXCoordinate, currentYCoordinate)))
             {
                  //you can move only diagonally if the current location or destination is a campsite
                  if ((isCampsite(destinationXCoordinate, destinationYCoordinate) || isCampsite(currentXCoordinate, currentYCoordinate))&& Math.abs(currentXCoordinate - destinationXCoordinate) <= 1 && Math.abs(currentYCoordinate - destinationYCoordinate) <= 1)
                  {
                       //make appropriate judgements on the ranks of the piece, change the board, and display the adjusted board. 
                       inputOK = board.movePiece(currentXCoordinate, currentYCoordinate, destinationXCoordinate, destinationYCoordinate);
                       displayChanges();
                  }// end of if ((isCampsite(destinationXCoordinate, destinationYCoordinate) || isCampsite(currentXCoordinate, currentYCoordinate))&& Math.abs(currentXCoordinate - destinationXCoordinate) <= 1 && Math.abs(currentYCoordinate - destinationYCoordinate) <= 1)
                  //if the movement doesnt involve a campsite, one may only move left, right, up, or down one block.
                  else if (Math.abs(currentXCoordinate - destinationXCoordinate) <= 1 && Math.abs(currentYCoordinate - destinationYCoordinate) == 0)
                  {
                       inputOK = board.movePiece(currentXCoordinate, currentYCoordinate, destinationXCoordinate, destinationYCoordinate);
                       displayChanges();
                  }// end of else if (Math.abs(currentXCoordinate - destinationXCoordinate) <= 1 && Math.abs(currentYCoordinate - destinationYCoordinate) == 0)
                  else if (Math.abs(currentXCoordinate - destinationXCoordinate) == 0 && Math.abs(currentYCoordinate - destinationYCoordinate) <= 1)
                  {
                       inputOK = board.movePiece(currentXCoordinate, currentYCoordinate, destinationXCoordinate, destinationYCoordinate);
                       displayChanges();
                  }// end of else if (Math.abs(currentXCoordinate - destinationXCoordinate) == 0 && Math.abs(currentYCoordinate - destinationYCoordinate) <= 1)
                  //if the piece and its destination are on railroads then the piece may move any number of posts up, down, left, or right
                  else if (onRailroad(currentXCoordinate, currentYCoordinate) && onRailroad(destinationXCoordinate, destinationYCoordinate))
                  {
                      //move horizontally or vertically? 
                      if (currentYCoordinate == destinationYCoordinate)
                       {
                           inputOK = movePieceOnRailroad(1, currentXCoordinate, destinationXCoordinate, destinationYCoordinate);
                           displayChanges();                  
                      }// end of if (currentYCoordinate == destinationYCoordinate)
                      else if (currentXCoordinate == destinationXCoordinate)
                      {
                           inputOK = movePieceOnRailroad(0, currentYCoordinate, destinationYCoordinate, destinationXCoordinate);
                           displayChanges();
                      }// end of else if (currentXCoordinate == destinationXCoordinate)
                  }
                  displayBoard(sideNumber);
                  if (inputOK == true) System.out.println("You have finished your turn, please press enter.");
                  if (inputOK == false) System.out.println("You have entered invalid information, please press enter to try again.");
             }
             else 
             {
                 System.out.println("Landmines and pieces on headquarters cannot be moved. Please try again.");
             }// end of if (selectedPiece.getRank() != RANK_LANDMINE && !(isHeadquarter(currentXCoordinate, currentYCoordinate)))
        }
        else
        {
            System.out.println("Please do not attempt to move your opponent's pieces. Press Enter to try again.");
        }// end of if (selectedPiece.getSide() == sideNumber - 1)
        return inputOK;
    }// end of method executeMove(int sideNumber, int currentXCoordinate, int currentYCoordinate, int destinationXCoordinate, int destinationYCoordinate)
    
    /**
     * Converts the integer value representing a x coordinate on the board to its coordinate on the screen where it should be displayed.
     * 
     * @param integer the intger value representing a x coordinate on the board
     * @return the x coordiantes on the screen where an image would be displayed
     */
    public static int integerToXCoordinates(int integer)
    {
        if (integer == 0) return COLUMN_A_COORDINATE;
        if (integer == 1) return COLUMN_B_COORDINATE;
        if (integer == 2) return COLUMN_C_COORDINATE;
        if (integer == 3) return COLUMN_D_COORDINATE;
        if (integer == 4) return COLUMN_E_COORDINATE;
        return 0;
    }// end of method integerToXCoordinates(int integer)
    
    /**
     * Converts the integer value representing a y coordinate on the board to its y coordinate on the screen where it should be displayed.
     * 
     * @param integer the intger value representing a y coordinate on the board
     * @return the y coordiantes on the screen where an image would be displayed
     */
    public static int integerToYCoordinates(int integer)
    {
        if (integer == 0) return ROW_1_COORDINATE; 
        if (integer == 1) return ROW_2_COORDINATE;
        if (integer == 2) return ROW_3_COORDINATE;
        if (integer == 3) return ROW_4_COORDINATE;
        if (integer == 4) return ROW_5_COORDINATE;
        if (integer == 5) return ROW_6_COORDINATE;
        
        if (integer == 6) return ROW_11_COORDINATE;
        if (integer == 7) return ROW_12_COORDINATE;
        if (integer == 8) return ROW_13_COORDINATE;
        if (integer == 9) return ROW_14_COORDINATE;
        if (integer == 10) return ROW_15_COORDINATE;
        if (integer == 11) return ROW_16_COORDINATE;
        return 0;
    }// end of method integerToYCoordinates(int integer)
    
    /**
     * Initializes JLabels of each piece and its cover as well as the board
     */
    public static void initializeBoard()
    {
        BufferedImage[] unknownPieceIcon;
        createPieces();
        //initialize array sizes
        pieceIcon = new BufferedImage [TOTAL_NUMBER_OF_PIECES];
        unknownPieceIcon = new BufferedImage[TOTAL_NUMBER_OF_PIECES];
        pieceLabel = new JLabel [TOTAL_NUMBER_OF_PIECES];
        unknownPieceLabel = new JLabel [TOTAL_NUMBER_OF_PIECES];
        String unknownPieceSide = UNKNOWN_RED_PIECE;
        //create JLabels for every piece and its cover according to the side that the piece belongs to
        for (int sideNumber = 0; sideNumber < NUMBER_OF_SIDES; sideNumber++)
        {
            for (int pieceNumber = 0; pieceNumber < NUMBER_OF_PIECES_ON_ONE_SIDE; pieceNumber++)
            {
                try
                {
                    if (sideNumber == 0)unknownPieceSide = UNKNOWN_RED_PIECE;
                    if (sideNumber == 1)unknownPieceSide = UNKNOWN_BLUE_PIECE;
                    //create JLabel of the piece's cover first so that it will be displayed on top of the actual image at all times
                    unknownPieceIcon[piece[sideNumber][pieceNumber].getID()] = ImageIO.read(new File(unknownPieceSide));
                    unknownPieceLabel[piece[sideNumber][pieceNumber].getID()] = new JLabel(new ImageIcon(unknownPieceIcon[piece[sideNumber][pieceNumber].getID()]));
                    //add label and make it invisible until it is displayed by a displayBoard() method
                    pane.add(unknownPieceLabel[piece[sideNumber][pieceNumber].getID()]);
                    unknownPieceLabel[piece[sideNumber][pieceNumber].getID()].setBounds(0,0,0,0);
                    
                    //create JLabel using the image location stored in every Piece's state
                    pieceIcon[piece[sideNumber][pieceNumber].getID()] = ImageIO.read(new File(piece[sideNumber][pieceNumber].getImageLocation()));
                    pieceLabel[piece[sideNumber][pieceNumber].getID()] = new JLabel(new ImageIcon(pieceIcon[piece[sideNumber][pieceNumber].getID()]));
                    //add label and make it invisible until it is displayed by a displayBoard() method
                    pane.add(pieceLabel[piece[sideNumber][pieceNumber].getID()]);
                    pieceLabel[piece[sideNumber][pieceNumber].getID()].setBounds(0,0,0,0);
                }
                catch (IOException exception)
                {
                }// end of catch (IOException exception)
            }// end of for (int pieceNumber = 0; pieceNumber < NUMBER_OF_PIECES_ON_ONE_SIDE; pieceNumber++)
        }// end of for (int sideNumber = 0; sideNumber < NUMBER_OF_SIDES; sideNumber++)
        
        try
        {
            //display background image so that it appears underneath all other images
            BufferedImage luzhanqiBoardImage = ImageIO.read(new File(GAME_BOARD_FILE_NAME));
            JLabel backgroundImage = new JLabel(new ImageIcon(luzhanqiBoardImage));
            pane.add(backgroundImage);
            backgroundImage.setBounds(BOARD_DISPLAY_COORDINATES[0], BOARD_DISPLAY_COORDINATES[1], BOARD_DISPLAY_COORDINATES[2], BOARD_DISPLAY_COORDINATES[3]);
        }
        catch (IOException exception)
        {
        }// end of catch(IOException exception)
    }// end of method initializeBoard()
    
    /**
     * Determines if the given location is a campsite. Returns true if the location is a campsite, otherwise false. 
     * 
     * @param xCoordinate the x coordinate of the location to be determined
     * @param yCoordinate the y coordinate of the location to be determined
     * @return true if the location is a campsite, false otherwise
     */
    public static boolean isCampsite(int xCoordinate, int yCoordinate)
    {
        //5 campsites on each side of the board, all appearing in columns B, C, D and the 3 middle rows of each side 
        if ((xCoordinate == COLUMN_B || xCoordinate == COLUMN_D) && (yCoordinate == ROW_3 || yCoordinate == ROW_5 || yCoordinate == ROW_12 || yCoordinate == ROW_14)) return true;    
        if (xCoordinate == COLUMN_C && (yCoordinate == ROW_4 || yCoordinate == ROW_13)) return true; 
        return false;
    }// end of method isCampsite(int xCoordinate, int yCoordinate)
    
    /**
     * Determines if the given location is a headquarter. Returns true if the location is a headquarter, otherwise false. 
     * 
     * @param xCoordinate the x coordinate of the location to be determined
     * @param yCoordinate the y coordinate of the location to be determined
     * @return true if the location is a headquarter, false otherwise
     */
    public static boolean isHeadquarter(int xCoordinate, int yCoordinate)
    {
        if ((xCoordinate == COLUMN_B || xCoordinate == COLUMN_D) && (yCoordinate == ROW_1 || yCoordinate == ROW_16)) return true;
        return false;
    }// end of method isHeadquarter(int xCoordinate, int yCoordinate)
    
    /**
     * Checks to see if a landmine can be placed in the given y coordinate. Returns true if the landmine can be placed there, otherwise false.
     * 
     * @param yCoordinate the y coordinate of the location to be checked
     * @return true if a landmine can be placed there, otherwise false
     */
    public static boolean landminePlacement(int yCoordinate)
    {
        if (yCoordinate == ROW_1 || yCoordinate == ROW_2 || yCoordinate == ROW_15 || yCoordinate == ROW_16) return true;
        return false;
    }// end of method landminePlacement(int yCoordinate)
    
     /**
     * Determines if this movement across the railroad is legal, if it is legal, the move will be made and true is returned. If the move is illegal, false is returned.
     * 
     * @param compareXorY an integer value representing the value to be compared, if the integer is 0, then the y coordinates will be compared. If the integer is 1, the x coordiantes will be compared
     * @param currentCoordinate the current x or y coordinate being compared to the destination coordinate
     * @param destinationCoordiante the destination x or y coordinate being compared to the current coordinate
     * @param constantCoordinate the x or y coordinate opposite to <code>currentCoordinate</code> and <code>destinationCoordinate</code>. Both the current location and the destination location would have this x or y coordinate because the movement must be linear (along x or y axis)
     */
    public static boolean movePieceOnRailroad(int compareXorY, int currentCoordinate, int destinationCoordinate, int constantCoordinate)
    {
        if (currentCoordinate > destinationCoordinate)
        {
            //are there any pieces in the way between this current piece and its destination along the specified coordinates?
            for (int postNumber = currentCoordinate - 1; postNumber >= destinationCoordinate; postNumber--)
            {
                //coordinates of the movement will depend on whether the x or y values are being compared. If x is being compared, the constantCoordinate is a y coordinate and vice versa.
                if (compareXorY == 0)
                {
                    //what if the posts in between arent on the railroad?
                    if (!onRailroad(constantCoordinate, postNumber)) return false;
                    //if there is a piece in between current and destination locations, the piece that has been selected will then attack that piece in the way of movement
                    if (board.getPiece(constantCoordinate, postNumber) != null)
                    {
                        return board.movePiece(constantCoordinate, currentCoordinate, constantCoordinate, postNumber);
                    }// end of if (board.getPiece(constantCoordinate, postNumber) != null)
                }// end of if (compareXorY == 0)
                else if (compareXorY == 1)
                {
                    if (!onRailroad(postNumber, constantCoordinate)) return false;
                    if (board.getPiece(postNumber, constantCoordinate) != null)
                    {
                        return board.movePiece(currentCoordinate, constantCoordinate, postNumber, constantCoordinate);
                    }// end of if (board.getPiece(postNumber, constantCoordinate) != null)
                }// end of else if (compareXorY == 1)
                //what if there are no pieces in between the current position and its destination
                if (postNumber == destinationCoordinate)
                {
                    if (compareXorY == 0)
                    {
                        return board.movePiece(constantCoordinate, currentCoordinate, constantCoordinate, destinationCoordinate);
                    }// end of if (compareXorY == 0)
                    else if (compareXorY == 1)
                    {
                        return board.movePiece(currentCoordinate, constantCoordinate, destinationCoordinate, constantCoordinate);
                    }// end of else if (compareXorY == 1)
                }// end of if (postNumber == destinationCoordinate)
            }// end of for (int postNumber = currentCoordinate - 1; postNumber >= destinationCoordinate; postNumber--)
        }// end of if (currentCoordinate > destinationCoordinate)
        else if (currentCoordinate < destinationCoordinate)
        {
            //pieces in between?
            for (int postNumber = currentCoordinate + 1; postNumber <= destinationCoordinate; postNumber++)
            {
                //coordinates depend on whether x or y is being compared
                if (compareXorY == 0)
                {
                    //if posts in between arent on railroads
                    if (!onRailroad(constantCoordinate, postNumber)) return false;
                    //if there is a piece in between
                    if (board.getPiece(constantCoordinate, postNumber) != null)
                    {
                        return board.movePiece(constantCoordinate, currentCoordinate, constantCoordinate, postNumber);
                    }// end of if (board.getPiece(constantCoordinate, postNumber) != null) 
                }// end of if (compareXorY == 0)
                else if (compareXorY == 1)
                {
                    if (!onRailroad(postNumber, constantCoordinate)) return false;
                    if (board.getPiece(postNumber, constantCoordinate) != null)
                    {
                        return board.movePiece(currentCoordinate, constantCoordinate, postNumber, constantCoordinate);
                    }// end of if (board.getPiece(constantCoordinate, postNumber) != null) 
                }// end of if (compareXorY == 1)
                //if there are no pieces in between
                if (postNumber == destinationCoordinate)
                {
                    if (compareXorY == 0)
                    {
                        return board.movePiece(constantCoordinate, currentCoordinate, constantCoordinate, destinationCoordinate);
                    }// end of if (compareXorY == 0)
                    else if (compareXorY == 1)
                    {
                        return board.movePiece(currentCoordinate, constantCoordinate, destinationCoordinate, constantCoordinate);
                    }// end of if (compareXorY == 1)
                }// end of if (postNumber == destinationCoordinate)
            }// end of for (int postNumber = currentCoordinate + 1; postNumber <= destinationCoordinate; postNumber++)
        }// end of if (currentCoordinate < destinationCoordinate)     
        return false;
    }// end of movePieceOnRailroad(int compareXorY, int currentCoordinate, int destinationCoordinate, int constantCoordinate)
    
    /**
     * Determines whether a location is on the railroad or not. Returns true if the location is on a railroad and false otherwise.
     * 
     * @param currentXCoordinate the x coordinate of the location to be determined
     * @param currentYCoordiante the y coordinate of the location to be determined
     * @return true if the location is on a railroad and false otherwise
     */
    public static boolean onRailroad(int currentXCoordinate, int currentYCoordinate)
    {
        //any position on row 2, 6, 11 or 15 is on a railroad
        if (currentYCoordinate == ROW_2 || currentYCoordinate == ROW_6 || currentYCoordinate == ROW_11 || currentYCoordinate == ROW_15) return true;
        //otherwise, any positions except the end positions on column A and E are also railroad locations
        else if ((currentXCoordinate == COLUMN_A && (currentYCoordinate != ROW_1 && currentYCoordinate != ROW_16)) || (currentXCoordinate == COLUMN_E && (currentYCoordinate != ROW_1 && currentYCoordinate != ROW_16))) return true;
        return false;
    }// end of method onRailroad(int currentXCoordinate, int currentYCoordinate)
    
    public static boolean onSide(int sideNumber, int yCoordinate)
    {
        if (sideNumber == 2 && yCoordinate <= 11 && yCoordinate >= 6) return true;
        if (sideNumber == 1 && yCoordinate <= 5 && yCoordinate >= 0) return true;
        return false;
    }
    
    /**
     * Determines whether the specified piece is eliminated. Returns true if the piece is eliminated, otherwise false.
     * 
     * @param piece the piece to be determined whether it is eliminated
     * @return true if the piece is eliminated, otherwise false.
     */
    public static boolean pieceEliminated (Piece piece)
    {
        Piece[][] piecesToCheck = board.getBoard();
        //traverse through all possible positions on the board to see if the piece exists on the board.
        for (int i = 0; i < NUMBER_OF_COLUMNS; i++)
        {
            for (int k = 0; k < NUMBER_OF_ROWS; k++)
            {
                if (piecesToCheck[i][k] == piece)
                {
                    return false;
                }// end of if (piecesToCheck[i][k] == piece)
            }// end of for (int k = 0; k < NUMBER_OF_ROWS; k++)
        }// end of for (int i = 0; i < NUMBER_OF_COLUMNS; i++)
        return true;
    }// end of method pieceEliminated (Piece piece)
    
    /**
     * Analyzes user input and calls executeMove(sideNumber, currentXCoordinate, currentYCoordinate, destinationXCoordinate, destinationYCoordinate)
     * 
     * @param sideNumber the side whose turn belongs to
     * @param pieceLocation the user input for the location of the piece that was to be moved
     * @param destination the user input for the location where the the piece is to go.
     */
    public static boolean processCoordinates (int sideNumber, String pieceLocation, String destination)
    {
        try
        {
            //split input to get x and y coordinates
            String[] split = pieceLocation.split(" ");
            int currentXCoordinate = convertLetterToNumber(split[0]);
            int currentYCoordinate = convertYCoordinate(split[1]);
                        
            String[] destinationCoordinate = destination.split(" ");
            int destinationXCoordinate = convertLetterToNumber(destinationCoordinate[0]);
            int destinationYCoordinate = convertYCoordinate(destinationCoordinate[1]);
            
            //there are only 4 places on the board where movement down or up a column is illegal
            if (currentYCoordinate == ROW_6 && (currentXCoordinate == COLUMN_B || currentXCoordinate == COLUMN_D) && destinationYCoordinate == ROW_11 && (destinationXCoordinate == COLUMN_B || destinationXCoordinate == COLUMN_D)) System.out.println("You have tried to move across the frontlines illegally. Please try again");
            else if (currentYCoordinate == ROW_11 && (currentXCoordinate == COLUMN_B || currentXCoordinate == COLUMN_D) && destinationYCoordinate == ROW_6 && (destinationXCoordinate == COLUMN_B || destinationXCoordinate == COLUMN_D)) System.out.println("You have tried to move across the frontlines illegally. Please try again");
            //if the destination and current location of the piece, do not involve the locations of 2 of these 4 places 
            else
            {
                 selectedPiece = board.getPiece(currentXCoordinate, currentYCoordinate);
                            
                 return executeMove(sideNumber, currentXCoordinate, currentYCoordinate, destinationXCoordinate, destinationYCoordinate);
            }// end of if (currentYCoordinate == ROW_6 && (currentXCoordinate == COLUMN_B || currentXCoordinate == COLUMN_D) && destinationYCoordinate == ROW_11 && (destinationXCoordinate == COLUMN_B || destinationXCoordinate == COLUMN_D))
        } 
        catch (NumberFormatException inputNumberFormatException)
        {
           System.out.println("Please enter appropriate values. Press Enter to continue");
        }// end of catch (NumberFormatException inputNumberFormatException)
         catch (ArrayIndexOutOfBoundsException inputArrayIndexOutOfBoundsException)
        {
           System.out.println("Please enter valid board coordinates (Ex. A 1) with a space in between. Press Enter to continue");
        }// end of catch (ArrayIndexOutOfBoundsException inputArrayIndexOutOfBoundsException)
        catch (NullPointerException inputNullPointerException)
        {
           System.out.println("There is no piece in the coordinates you specified. Press Enter to retry");
        }// end of catch (NullPointerException inputNullPointerException)
        //if a move had not been made by this stage,
        return false;
    }// end of method processCoordinates (int sideNumber, String pieceLocation, String destination)
    
    /**
     * Pushes the current board to the stack and creates a new board identical to the board that has just been pushed.
     */
    public static void pushBoard()
    {
        recordOfTurns.push(board);
        Board oldBoard = board;
        //create new board so future adjustments can be made after a player moves a piece during their turn
        board = new Board(oldBoard);
    }// end of method pushBoard()
    
    /**
     * Guides the players to begin setting up pieces.
     */
    public static void setUpBoard()
    {
        board = new Board();
        //repeat process for every player
        for (int sideNumber = 1; sideNumber <= NUMBER_OF_SIDES; sideNumber++)
        {    
            try
            {
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                //display the correct board to keep opponent pieces undisclosed
                displayBoard(sideNumber);
                System.out.println("Player " + sideNumber + " please get ready to place your pieces on the board: (Press Enter when ready)\n");
                reader.readLine();
                
                setUpPieces(sideNumber);
                
                System.out.println("Please press enter when you are finished");
                reader.readLine();
                //remove evidence of previous player's piece placements
                clearScreen();
                
                reader.close();
            }
            catch (IOException exception)
            {
            }// end of catch (IOException exception)
        }// end of for (int sideNumber = 1; sideNumber <= NUMBER_OF_SIDES; sideNumber++)
    }// end of method setUpBoard()
    
    /**
     * Guides the players to set up each piece in their desired placements legally.
     * 
     * @param sideNumber the side that is having their pieces set up
     * @return true 
     */
     public static void setUpPieces(int sideNumber)
    {
        boolean inputOK = false;
        try
        {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            //repeat for every one of the 25 pieces for each side
            for (int pieceNumber = 0; pieceNumber < NUMBER_OF_PIECES_ON_ONE_SIDE; pieceNumber++)
            {
                inputOK = false;
                do
                {
                    System.out.println("\nPlease input the coordinates of your desired placement of the " + piece[sideNumber - 1][pieceNumber].getName() + " (Ex: A 1)");
                    try
                    {
                         //split input to find the coordinates
                         String[] inputCoordinate = new String[2];
                         String inputLocation = reader.readLine();
                         inputCoordinate = inputLocation.split(" ");
                         
                         int coordinateX = convertLetterToNumber(inputCoordinate[0]);
                         int coordinateY = convertYCoordinate(inputCoordinate[1]);                             
                         
                         //pieces can only be placed on the player's side of the board
                         //flags can only be placed on headquarters
                         if (pieceNumber < PIECE_NUMBER[1] && isHeadquarter(coordinateX, coordinateY) && onSide(sideNumber, coordinateY)) inputOK = board.addPiece(piece[sideNumber - 1][pieceNumber],coordinateX, coordinateY);
                         //landmines can only be placed in 2 furtherest rows from the frontlines
                         else if (pieceNumber < PIECE_NUMBER[4] && pieceNumber > PIECE_NUMBER[0] && landminePlacement(coordinateY) && onSide(sideNumber, coordinateY)) inputOK = board.addPiece(piece[sideNumber - 1][pieceNumber],coordinateX, coordinateY);
                         //can other pieces be placed in a campsite?
                         else if (pieceNumber < PIECE_NUMBER[25] && pieceNumber > PIECE_NUMBER[3] && !(isCampsite(coordinateX, coordinateY)) && onSide(sideNumber, coordinateY)) 
                         {
                             System.out.println(sideNumber + "    " + coordinateY);
                            inputOK = board.addPiece(piece[sideNumber - 1][pieceNumber],coordinateX, coordinateY);
                        }
                         
                         displayBoard(sideNumber);
                         
                         if (inputOK == false)
                         {
                             System.out.println("Please input valid coordinates for the location of your " + piece[sideNumber - 1][pieceNumber].getName() + "\n");
                         }// end of if (inputOK == false)
                    }
                    catch (IOException ioException)
                    {
                        System.out.println("a");
                    }// end of catch (IOException ioException)
                    catch (ArrayIndexOutOfBoundsException outOfBounds)
                    {
                        System.out.println("asdf");
                    }// end of catch (ArrayIndexOutOfBounds outOfBounds)
                    catch (NumberFormatException numberFormat)
                    {
                        System.out.println("as");
                    }// end of catch (NumberFormatException numberFormat)
                    //users keep trying until the specified piece is added legally to the board
                }while (inputOK == false); // end of do while (inputOK == false)
            }// end of (int pieceNumber = 0; pieceNumber < NUMBER_OF_PIECES_ON_ONE_SIDE; pieceNumber++)
            reader.close();
        }
        catch (IOException exception)
        {
        }// end of catch (IOException exception)
    }// end of method setUpPieces(int sideNumber)
    
    /**
     * Guides the players to input coordinates of their desired move. 
     */
    public static void takeTurn()
    {
        //each player takes their turn
        takeTurn: for (int player = 1; player <= NUMBER_OF_PLAYERS; player ++)
        {
            //all peices are covered while players switch from looking at the screen
            displayNeutralBoard();
            //no clear screen, the player may see the move the other player moved in order to reason which piece was bigger and which piece was eliminated. The rank of the pieces remain disclosed.
            selectedPiece = null;
            boolean inputOK = false;
            try
            {
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                //player continues to input information until the movement is made (legal)
                do
                {    
                    System.out.println("\n\nPlayer" + player + " turn: (Press Enter when ready)");
                    reader.readLine();
                    //when the player is ready, the display will now change for the player
                    displayBoard(player);
                    //prompt for current location of the piece and destination
                    System.out.println("Please select a piece to move: ");
                    String currentLocation = reader.readLine();
                    String destination;
                    
                
                    System.out.println("Where would you like to move the piece?");
                    destination = reader.readLine();
                    //undo the move if either input is "return"
                    if (destination.equals("Return") || destination.equals("return") || currentLocation.equals("Return") || currentLocation.equals("return"))
                    {
                        try
                        {
                            //recall the last board pushed on the stack
                            board = (Board) (((Node)(recordOfTurns.pop())).getData());
                            displayBoard(player);
                            //because the last turn was recalled, it is no longer this player's turn.
                            continue takeTurn;
                        }
                        catch (EmptyStackException emptyStack)
                        {
                        }//end of catch (EmptyStackException emptyStack)
                    }// end of if (destination.equals("Return") || destination.equals("return") || currentLocation.equals("Return") || currentLocation.equals("return"))
                    //if a move wasn't recalled, then this board is stored as a new move is about to be made
                    pushBoard();
                    
                    //determine if the move is valid and execute
                    inputOK = processCoordinates(player, currentLocation, destination);
                    
                    reader.readLine();
                    
                    //if a flag is captured, should Player 2 continue to take his turn?
                    if (displayWinner()) return;
                }
                while (!inputOK); // end of dowhile (!inputOK)
                
                reader.close();
            }
            catch (IOException ioException)
            {
            }// end of catch (IOException ioException)
            catch (ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException)
            {
            }// end of catch (ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException)
        }//  for (int player = 1; player <= NUMBER_OF_PLAYERS; player ++)
    }// end of method takeTurn()
}// end of class Game()
