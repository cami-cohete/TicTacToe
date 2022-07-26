import java.util.Scanner;

public class Main {

    private static final int ROW = 3;
    private static final int COL = 3;
    private static String board [][] = new String[ROW][COL];

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        boolean gameOver = false;
        boolean playAgain = false;

        do {
            // Resets for the Full Game
            int moveCount = 0;
            boolean validMove = false;
            String playerMark = "X";
            int rowMove = 0;
            int colMove = 0;

            System.out.println("Lets Play Tic Tac Toe!");
            System.out.println();
            clearBoard(board);
            showBoard(board);

            // Player input their Coordinates
            do {
                do {
                    rowMove = SafeInputFileCopy.getRangedInt(in, "Player " + playerMark + ", enter the row you want play in from top to bottom ", 1, 3);
                    colMove = SafeInputFileCopy.getRangedInt(in, "Enter the column from left to right ", 1, 3);
                    rowMove = rowMove - 1;
                    colMove = colMove - 1;
                    validMove = isValidMove(rowMove, colMove);

                    if (validMove = false)
                    {
                        System.out.println("Invalid Move!\n");
                    } else {
                        board[rowMove][colMove] = playerMark;
                        moveCount = moveCount + 1;
                    }
                } while (validMove);

                // reset for next player
                System.out.println();
                showBoard(board);
                validMove = false;

                // Toggle Between Players
                if (moveCount == 1 || moveCount == 3 || moveCount == 5 || moveCount == 7) {
                    playerMark = "O";
                } else {
                    playerMark = "X";
                }

            }while (!gameOver);
        } while (playAgain);

    }


    //Helper Methods
    private static void clearBoard(String[][] board) // sets all the board elements to a space
    {
        for(int row = 0; row < ROW; row++)
        {
            for(int col = 0; col < COL; col++)
            {
                board[row][col] = " "; // make this cell a space
            }
        }
    }

    private static void showBoard(String[][] board) // show board to players
    {
        for(int row=0; row < ROW ; row++)
        {
            for(int col= 0; col < COL - 1; col++)
            {
                System.out.print(board[row][col] + "|");
            }
            System.out.println();
        }
    }

    private static boolean isValidMove(int row, int col)
    {
        boolean retVal = false;
        if(board[row][col].equals(" "))  // is it a space?
            retVal = true;
        return retVal;
    }
}
