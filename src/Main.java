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
            gameOver = false;
            playAgain = false;
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
            // they are taking turns
            do {
                do {
                    rowMove = SafeInputFileCopy.getRangedInt(in, "Player " + playerMark + ", enter the row you want play in from top to bottom ", 1, 3);
                    colMove = SafeInputFileCopy.getRangedInt(in, "Enter the column from left to right ", 1, 3);
                    rowMove = rowMove - 1;
                    colMove = colMove - 1;

                    validMove = isValidMove(rowMove, colMove);

                    if (!validMove)
                    {
                        System.out.println("Invalid Move!\n");
                    } else {
                        board[rowMove][colMove] = playerMark;
                        moveCount = moveCount + 1;
                    }

                } while (!validMove);

                // reset for next player
                System.out.println();
                showBoard(board);
                validMove = false;


                // Checking for wins or ties
                if (moveCount > 4)
                {

                    if((gameOver = isWin(playerMark)) == true)
                    {
                        System.out.println("\nCongratulations, player " + playerMark + " you won!!!");
                    }
                    else if (moveCount > 7)
                    {
                        if ((gameOver = isTie(board)) == true || moveCount == 8)
                        {
                            System.out.println("\nIt's a tie!!!");
                            gameOver = true;
                        }
                    }
                }

                // Toggle Between Players
                if (moveCount == 1 || moveCount == 3 || moveCount == 5 || moveCount == 7) {
                    playerMark = "O";
                } else {
                    playerMark = "X";
                }


            }while (!gameOver);

            playAgain = SafeInputFileCopy.getYNConfirm (in, "\nWould you like to play again?");

        } while (playAgain);
        System.out.println("Thanks for playing!");
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
        for(int row=0; row < ROW; row++)
        {
            for(int col= 0; col < COL; col++)
            {
                System.out.print(board[row][col]);
                if (col < COL -1)
                {
                    System.out.print("|");
                }
            }
            System.out.println();
        }
    }

    private static boolean isValidMove(int row, int col)
    {
        boolean validSpace = false;
        if(board[row][col].equals(" "))
        {
            validSpace = true;
        }
        return validSpace;
    }

    private static boolean isColWin(String player)
    {
        for (int row = 0; row < ROW; row++)
        {
            if (board[row][0].equals(player) && board[row][1].equals(player) && board[row][2].equals(player))
            {
                return true;
            }
        }
        return false;
    }

    private static boolean isRowWin(String player)
    {
        for (int col = 0; col < COL; col++)
        {
            if (board[0][col].equals(player) && board[1][col].equals(player) && board[2][col].equals(player))
            {
                return true;
            }
        }
        return false;
    }

    private static boolean isDiagonalWin(String player) // checks for a diagonal win for the specified player
    {
        if (board[0][0].equals(player) && board[1][1].equals(player) && board[2][2].equals(player))
        {
            return true;
        }
        else if (board[0][2].equals(player) && board[1][1].equals(player) && board[2][0].equals(player))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    private static boolean isWin(String player)
    {
        if (isColWin(player) || isRowWin(player) || isDiagonalWin(player))
        {
            return true;
        }
        return false;
    }

    private static boolean isTie(String[][] board)
    {
        for (int row = 0; row < ROW; row++)
        {
            if (board[row][0].equals("X") || board[row][1].equals("X") || board[row][2].equals("X"))
            {
                if (!board[row][0].equals("O") || !board[row][1].equals("O") || !board[row][2].equals("O"))
                {
                    return false;
                }
            }
        }
        for (int col = 0; col < COL; col++)
        {
            if (board[0][col].equals("X") || board[1][col].equals("X") || board[2][col].equals("X"))
            {
                if (!board[0][col].equals("O") || !board[0][col].equals("O") || !board[0][col].equals("O"))
                {
                    return false;
                }
            }
        }
        if (board[0][0].equals("X") || board[1][1].equals("X") || board[2][2].equals("X"))
        {
            if(!board[0][0].equals("O") || !board[1][1].equals("O") || !board[2][2].equals("O"))
            {
                return false;
            }
        }
        else if (board[0][2].equals("X") || board[1][1].equals("X") || board[2][0].equals("X"))
        {
            if (!board[0][2].equals("O") || !board[1][1].equals("O") || !board[2][0].equals("O"))
            {
                return false;
            }
        }
        return true;
    }

}
