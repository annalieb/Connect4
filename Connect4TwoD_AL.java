/**
 * Write a one-player program, Connect4TwoD_YI.java using 2D array. Display at a every move the board as shown 
 * in the example above. Include a prompt to continue playing. In your documentation include your strategy 
 * behind your implementation.
 * 
 * Note: I was on the choir trip during the hard due date
 *
 * @Anna Lieb
 * @Java version 1.8.0 - 2/6/19
 */
import java.util.Scanner;
public class Connect4TwoD_AL
{
    public static void main(String [] args){
        int ROWS = 6;
        int COLUMNS = 7;
        boolean playing = true; 
        while (playing) {
            boolean newGame = true;
            String [][] board = new String[ROWS][COLUMNS]; // creates the original board
            for (int i = 0; i < ROWS; i++){
                for (int j = 0; j < COLUMNS; j++){
                    board[i][j] = " "; // fill with open space
                }
            }
            drawBoard(board);
            while (newGame) {
                //player moves
                Scanner scan = new Scanner(System.in);
                System.out.println("Which column? (1-7) ");
                int moveCol = scan.nextInt();
                int lowestRow = 5;
                while (board[lowestRow][moveCol - 1] != " ") lowestRow--;
                board = updateBoard(board, lowestRow, moveCol - 1, "R");
                drawBoard(board);

                //computer moves
                System.out.println("Computer's move: ");
                moveCol = computerMove(board);
                lowestRow = 5;
                while (board[lowestRow][moveCol] != " ") lowestRow--;
                board = updateBoard(board, lowestRow, moveCol, "Y");
                drawBoard(board);

                //checks if the game is over
                if (checkWin(board) == "Player"){
                    System.out.println("Congratulations, you won!");
                    playing = repeat();
                    newGame = false;
                }
                if (checkWin(board) == "Computer"){
                    System.out.println("The computer wins!");
                    playing = repeat();
                    newGame = false;
                }
                if (checkWin(board) == "Full Board"){
                    System.out.println("The board was filled. It's a tie!");
                    playing = repeat();
                    newGame = false;
                }
            }
        }
        System.out.println("Game over ");
    }

    /** Updates the board
     */
    public static String [][] updateBoard(String [][] board, int moveRow, int moveCol, String player)
    {
        board[moveRow][moveCol] = player;
        return board;
    }

    /** Draws the board
     */
    public static void drawBoard(String [][] board)
    {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++){
                System.out.print("|" + board[i][j]);
            }
            System.out.println("|");
        }
        System.out.println("---------------");
    }

    /** Checks if the computer or player has won, or if the board is full 
     */
    public static String checkWin(String [][] board)
    {
        //checks if board is full
        boolean fullBoard = true;
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++){
                if (board[i][j] == " ") fullBoard = false;
            }
        }

        String winner = "None";
        // checks diagonals going down
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++){
                if (board[i][j] == "Y" && board[i + 1][j + 1] == "Y" &&  board[i + 2][j + 2] == "Y" &&  board[i + 3][j + 3] == "Y") {
                    winner = "Computer";
                }
                if (board[i][j] == "R" && board[i + 1][j + 1] == "R" &&  board[i + 2][j + 2] == "R" &&  board[i + 3][j + 3] == "R") {
                    winner = "Player";
                }
            }
        }
        // checks diagonals going up
        for (int i = 5; i > 2; i--) {
            for (int j = 0; j < 4; j++){
                if (board[i][j] == "Y" && board[i - 1][j + 1] == "Y" &&  board[i - 2][j + 2] == "Y" &&  board[i - 3][j + 3] == "Y") {
                    winner = "Computer";
                }
                if (board[i][j] == "R" && board[i - 1][j + 1] == "R" &&  board[i - 2][j + 2] == "R" &&  board[i - 3][j + 3] == "R") {
                    winner = "Player";
                }
            }
        }
        // checks verticals
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 7; j++){
                if (board[i][j] == "Y" && board[i + 1][j] == "Y" &&  board[i + 2][j] == "Y" &&  board[i + 3][j] == "Y") {
                    winner = "Computer";
                }
                if (board[i][j] == "R" && board[i + 1][j] == "R" &&  board[i + 2][j] == "R" &&  board[i + 3][j] == "R") {
                    winner = "Player";
                }
            }
        }
        // checks horizonals
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 4; j++){
                if (board[i][j] == "Y" && board[i][j + 1] == "Y" &&  board[i][j + 2] == "Y" &&  board[i][j + 3] == "Y") {
                    winner = "Computer";
                }
                if (board[i][j] == "R" && board[i][j + 1] == "R" &&  board[i][j + 2] == "R" &&  board[i][j + 3] == "R") {
                    winner = "Player";
                }
            }
        }
        return winner;
    }

    /** Decides the computer's move
     * NOTE: can only detect 3 non-consecutive pieces diagonally, vertically, or horizontally.
     * Computer also has some blind spots if player moves in a certain way
     */
    public static int computerMove(String [][] board) 
    {
        // checks if computer or player is close to winning vertically
        for (int i = 5; i > 2; i--) {
            for (int j = 0; j < 7; j++){
                if (board[i][j] == "Y" && board[i - 1][j] == "Y" &&  board[i - 2][j] == "Y") {
                    if (board[i - 3][j] == " ") { 
                        System.out.println("vert"); // used to track computer's logic
                        return j;
                    }
                }
                if (board[i][j] == "R" && board[i - 1][j] == "R" &&  board[i - 2][j] == "R") {
                    if (board[i - 3][j] == " ") { 
                        System.out.println("vert");
                        return j;
                    }
                }
            }
        }
        // checks if computer or player is close to winning horizontally
        for (int i = 5; i >= 0; i--) {
            for (int j = 0; j < 4; j++){ // a blindspot here if player has three in a row on rightmost horizontal
                if (board[i][j] == "Y" && board[i][j + 1] == "Y" &&  board[i][j + 2] == "Y") {
                    int lowestRow = 5;
                    while (board[lowestRow][j + 3] != " ") lowestRow--;
                    if (lowestRow == i && board[i][j + 3] == " "){ 
                        System.out.println("horiz"); // used to track computer's logic
                        return (j + 3);
                    }
                }
                if (board[i][j] == "R" && board[i][j + 1] == "R" &&  board[i][j + 2] == "R") {
                    int lowestRow = 5;
                    while (board[lowestRow][j + 3] != " ") lowestRow--;
                    if (lowestRow == i && board[i][j + 3] == " "){ 
                        System.out.println("horiz");
                        return (j + 3);
                    }
                }
            }
        }
        // checks if computer or player is close to winning diagonally going down
        for (int i = 5; i > 2; i--) {
            for (int j = 6; j > 2; j--){
                if (board[i][j] == "Y" && board[i - 1][j - 1] == "Y" &&  board[i - 2][j - 2] == "Y") {
                    int lowestRow = 5;
                    while (board[lowestRow][j - 3] != " ") lowestRow--;
                    if (lowestRow == (i - 3) && board[i - 3][j - 3] == " ") { 
                        System.out.println("diag dn"); // used to track computer's logic
                        return (j - 3);
                    }
                }
                if (board[i][j] == "R" && board[i - 1][j - 1] == "R" &&  board[i - 2][j - 2] == "R") {
                    int lowestRow = 5;
                    while (board[lowestRow][j - 3] != " ") lowestRow--;
                    if (lowestRow == (i - 3) && board[i - 3][j - 3] == " ") { 
                        System.out.println("diag dn");
                        return (j - 3);
                    }
                }
            }
        }
        // checks if computer or player is close to winning diagonally going up
        for (int i = 5; i > 2; i--) {
            for (int j = 0; j < 4; j++){
                if (board[i][j] == "Y" && board[i - 1][j + 1] == "Y" &&  board[i - 2][j + 2] == "Y") {
                    int lowestRow = 5;
                    while (board[lowestRow][j + 3] != " ") lowestRow--;
                    if (lowestRow == (i - 3) && board[i - 3][j + 3] == " ") { 
                        System.out.println("diag up"); // used to track computer's logic
                        return (j + 3);
                    }
                }
                if (board[i][j] == "R" && board[i - 1][j + 1] == "R" &&  board[i - 2][j + 2] == "R") {
                    int lowestRow = 5;
                    while (board[lowestRow][j + 3] != " ") lowestRow--;
                    if (lowestRow == (i - 3) && board[i - 3][j + 3] == " ") { 
                        System.out.println("diag up");
                        return (j + 3);
                    }
                }
            }
        }
        return (int) (Math.random()*7); // if all else fails, plays random move
    }

    /** Determines if the player wants to play again
     */
    public static boolean repeat()
    {
        Scanner scan = new Scanner(System.in);
        boolean playAgain = false;
        System.out.println("Do you want to play again? (y/n) ");
        String playAgainInput = scan.next();
        if ((playAgainInput).equals("y")) playAgain = true;
        return playAgain;
    }
}
/** Input/output
 * Unfinished run shows vertical checking 
| | | | | | | |
| | | | | | | |
| | | | | | | |
| | | | | | | |
| | | | | | | |
| | | | | | | |
---------------
Which column? (1-7) 
2
| | | | | | | |
| | | | | | | |
| | | | | | | |
| | | | | | | |
| | | | | | | |
| |R| | | | | |
---------------
Computer's move: 
| | | | | | | |
| | | | | | | |
| | | | | | | |
| | | | | | | |
| | | | | | | |
| |R| | | |Y| |
---------------
Which column? (1-7) 
2
| | | | | | | |
| | | | | | | |
| | | | | | | |
| | | | | | | |
| |R| | | | | |
| |R| | | |Y| |
---------------
Computer's move: 
| | | | | | | |
| | | | | | | |
| | | | | | | |
| | | | | | | |
| |R| | | | | |
| |R| |Y| |Y| |
---------------
Which column? (1-7) 
2
| | | | | | | |
| | | | | | | |
| | | | | | | |
| |R| | | | | |
| |R| | | | | |
| |R| |Y| |Y| |
---------------
Computer's move: 
vert
| | | | | | | |
| | | | | | | |
| |Y| | | | | |
| |R| | | | | |
| |R| | | | | |
| |R| |Y| |Y| |
---------------
Which column? (1-7) 
 * 
 * 
 * 
 * 
 * Another simple run shows computer's limitations and the play again feature.
| | | | | | | |
| | | | | | | |
| | | | | | | |
| | | | | | | |
| | | | | | | |
| | | | | | | |
---------------
Which column? (1-7) 
1
| | | | | | | |
| | | | | | | |
| | | | | | | |
| | | | | | | |
| | | | | | | |
|R| | | | | | |
---------------
Computer's move: 
| | | | | | | |
| | | | | | | |
| | | | | | | |
| | | | | | | |
| | | | | | | |
|R| | | |Y| | |
---------------
Which column? (1-7) 
2
| | | | | | | |
| | | | | | | |
| | | | | | | |
| | | | | | | |
| | | | | | | |
|R|R| | |Y| | |
---------------
Computer's move: 
| | | | | | | |
| | | | | | | |
| | | | | | | |
| | | | | | | |
| |Y| | | | | |
|R|R| | |Y| | |
---------------
Which column? (1-7) 
4
| | | | | | | |
| | | | | | | |
| | | | | | | |
| | | | | | | |
| |Y| | | | | |
|R|R| |R|Y| | |
---------------
Computer's move: 
| | | | | | | |
| | | | | | | |
| | | | | | | |
| | | | | | | |
| |Y| | | | | |
|R|R| |R|Y|Y| |
---------------
Which column? (1-7) 
3
| | | | | | | |
| | | | | | | |
| | | | | | | |
| | | | | | | |
| |Y| | | | | |
|R|R|R|R|Y|Y| |
---------------
Congratulations, you won!
Do you want to play again? (y/n) 
n
 */