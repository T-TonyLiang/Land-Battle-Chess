import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * The script for the video required by Criterion D.
 * 
 * @author Tony Liang
 * @version 1.1 2015-02-02
 */
public class CriterionDScript
{
   public static void main (String[] console_arguments)
   {
       try
       {
           BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
           System.out.println("Hello, I am going to demonstrate the Luzhanqi game for my ICS Internal Assesment. \n this window will be used as my commentary. \n the window below me is part of the game experience, where users input information to console.");
           reader.readLine();
           System.out.println("The program begins with the image of the board, a welcome message as well as the rules for the game.");
           reader.readLine();
           System.out.println("Players then set up pieces on their respective side of the board");
           reader.readLine();
           System.out.println("I will begin by entering invalid data for the placement of the flag. \nThis data is invalid because the flag which must be on headquarters (B 1 or D 1)");
           reader.readLine();
           System.out.println("The program has recognized the invalid data and I now have the opportunity to enter the data again.\n I will now demonstrate how the graphical display works when I place a piece on the board");
           reader.readLine();
           System.out.println("The image of the flag has correctly appeared. \nThe placement of the landmines must be on the 2 rows furthest from frontlines. \nIn this case it is rows 1 and 2.\n I will first enter invalid data, then valid data for the placement of all 3 landmines.");
           reader.readLine();
           System.out.println("The images of the landmines has correctly appeared. \nFor the placement of the next piece, I will enter an empty string, then miscellanous characters.\n I will also enter coordinates of a campsite, all of which is illegal.");
           reader.readLine();
           System.out.println("Finally I will enter a correct location and the display should change as a result.");
           reader.readLine();
           System.out.println("I will place the remaining red side pieces on the board, in no particular order.");
           reader.readLine();
           System.out.println("Now, I will demonstrate how the screen clears to dispose any evidence of pieces being placed.\nA neutral board will also be displayed so that Player 2 cannot view Player 1's pieces.");
           reader.readLine();
           System.out.println("Now, I will place player 2's flag and 3 landmines");
           reader.readLine();
           System.out.println("I will attempt to place Player 2's piece on a campsite of Player 1's side. \nAfter, I will also attempt to place a piece on top of an existing piece");
           reader.readLine();
           System.out.println("I will finishing placing Player 2's pieces and being the turn taking process");
           reader.readLine();
           System.out.println("Player 1 starts taking the turn. I will attempt to move the piece at A 6 to A 5, \nwhich already contains a piece belonging to red side");
           reader.readLine();
           System.out.println("I will also attempt to move the piece at B 6 to B 11, this time, \nthe move is illegal as there is no line connecting the 2 posts together");
           reader.readLine();
           System.out.println("Finally, I will make a legal move and move the Engineer at A 6 to the campsite. \nThis move is legal because the campsite is unoccupied and \n   there is a line on the board connecting the 2 posts together. \nNotice that the piece at A 5 is an engineer");
           reader.readLine();
           System.out.println("The neutral board displays again to allow for the transition between players looking at the screen.\n When player 2 begins the turn, the Blue side board displays, covering all red side pieces.\n there is no clear screen after a turn has been made. \nThis is so the second player can see the move the first player made \nand make an educated guess on the rank of the enemy piece, \nif it hasn't been eliminated yet");
           reader.readLine();
           System.out.println("I will now demonstrate a collision between 2 pieces. \nI will move the field marshall at A 11 to collide with the engineer at A 5. \nBecause the rank of the field marshall is greater than the engineer \n the field marshall is victorious and takes the position of the engineer.\nchanges are displayed on the board.");
           reader.readLine();
           System.out.println("Now back to player 1 turn, I will now bomb the blue side field marshall \n I will move the bomb at A 4 to the field marshall at A 5.\nboth pieces should be eliminated");
           reader.readLine();
           System.out.println("I will now demonstrate the undo functionality. \nI will now undo the moves until the beginning of the game, \nright after both sides has set up the board");
           reader.readLine();
           System.out.println("I continue taking turns for both sides");
           reader.readLine();
           System.out.println("I will now demonstrate the movement of a piece along a railroad, \n but first I will enter coordinates where both pieces are on the railroad\nhowever the piece cannot move linearly to its destination.");
           reader.readLine();
           System.out.println("Now I will input the coordinates of 2 pieces on the railroad and connected by a row, \nhowever, there is no railroad between these 2 pieces. \ntherefore the input is invalid and the move cannot be made");
           reader.readLine();
           System.out.println("I will now demonstrate the movement of a piece along a railroad where there is a enemy piece in between.");
           reader.readLine();
           System.out.println("Now i will demonstrate an illegal attempt to move diagonally\n where there is no lines connecting the 2 posts");
           reader.readLine();
           System.out.println("I will also demonstrate an illegal attempt to attack a piece on the campsite");
           reader.readLine();
           System.out.println("I will now continue playing the game, until the flag is captured");
           reader.readLine();
           System.out.println("I will now demonstrate the announcing of a winner after the flag is captured,");
           reader.readLine();
           System.out.println("Now players have a chance to see the opponent's remaining pieces and analyze outcomes of the game");
           reader.readLine();
           System.out.println("The program is now complete. This demonstration is complete as well. Thank you for your time");
       }
       catch (IOException ioException)
       {
       }// end of catch (IOException ioException)
   }//end of main method(String[] console_arguments)
}// end of class CriterionDScript()
