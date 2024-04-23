import java.util.Scanner;

public class TicTacToe {
    private char[][] grid;
    private int rowNumber;
    private int columnNumber;
    private char markSelected;

    public TicTacToe() {
        grid = new char[3][3];
        // Fill the grid with blank spaces
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                grid[i][j] = ' ';
            }
        }
        rowNumber = -1;
        columnNumber = -1;
        markSelected = ' ';
    }
    public void displayWelcomeMessage() {
        System.out.println("Welcome to Tic Tac Toe!");
    }

    public void displayGrid() {
        System.out.println("-------------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(grid[i][j] + " | ");
            }
            System.out.println();
            System.out.println("-------------");
        }
    }

    public void startGame() {
        boolean gameOver = false;
        while (!gameOver) {
            gameOver = takeTurn();
        }
    }

    public boolean takeTurn() {
        determineMark();
        promptForRowAndColumn();
        Scanner scanner = new Scanner(System.in);
        if (grid[rowNumber][columnNumber] != ' ') {
            System.out.println("Cell already occupied. Please choose another cell.");
            return false;
        }
        grid[rowNumber][columnNumber] = markSelected;
        displayGrid();
        return checkForWinner();
    }

    private void promptForRowAndColumn() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Player " + markSelected + "'s turn.");

        boolean validInput = false;
        while (!validInput) {
            System.out.print("Enter row number (1-3): ");
            rowNumber = scanner.nextInt();
            System.out.print("Enter column number (1-3): ");
            columnNumber = scanner.nextInt();

            // Adjust indices to match array indexing
            rowNumber--;
            columnNumber--;

            // Check if the input is within the valid range and if the cell is empty
            if (rowNumber >= 0 && rowNumber < 3 && columnNumber >= 0 && columnNumber < 3 && grid[rowNumber][columnNumber] == ' ') {
                validInput = true;
            } else {
                System.out.println("Invalid input. Please enter row and column numbers within the range and choose an empty cell.");
            }
        }
    }
    private void determineMark() {
        if (markSelected == 'X')
            markSelected = 'O';
        else
            markSelected = 'X';
    }
    public boolean checkForWinner() {
        // Check rows
        for (int i = 0; i < 3; i++) {
            if (grid[i][0] == grid[i][1] && grid[i][1] == grid[i][2] && grid[i][0] != ' ')
                return true;
        }
        // Check columns
        for (int i = 0; i < 3; i++) {
            if (grid[0][i] == grid[1][i] && grid[1][i] == grid[2][i] && grid[0][i] != ' ')
                return true;
        }
        // Check diagonals
        if ((grid[0][0] == grid[1][1] && grid[1][1] == grid[2][2] && grid[0][0] != ' ')
                || (grid[0][2] == grid[1][1] && grid[1][1] == grid[2][0] && grid[0][2] != ' '))
            return true;
        return false;
    }
}
