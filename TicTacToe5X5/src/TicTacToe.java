import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class TicTacToe extends Application {

    private static final int SIZE = 5;  // 5x5 board
    private Button[][] board = new Button[SIZE][SIZE];
    private String currentPlayer = "X";  // Player X starts
    
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(5);
        grid.setVgap(5);
        
        // Set chartreuse green background for the grid
        grid.setStyle("-fx-background-color: chartreuse;");

        // Initialize the board
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                Button button = new Button();
                button.setMinSize(60, 60);
                button.setStyle("-fx-font-size: 24px;");
                final int r = row;  // Capture the row value for use
                final int c = col;  // Capture the col value for use
                button.setOnAction(_ -> handleButtonClick(r, c));  // Use captured values
                board[row][col] = button;
                grid.add(button, col, row);  // Correctly add the button to the grid
            }
        }

        Scene scene = new Scene(grid, 400, 400);
        primaryStage.setTitle("5x5 TicTacToe");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Handle the button click event
    private void handleButtonClick(int row, int col) {
        Button button = board[row][col];
        
        // If the button is already clicked, return
        if (!button.getText().isEmpty()) {
            return;
        }

        // Set the current player's mark and color onto the board
        button.setText(currentPlayer);
        if ("X".equals(currentPlayer)) {
            button.setStyle("-fx-font-size: 24px; -fx-text-fill: red;");
        } else if ("O".equals(currentPlayer)) {
            button.setStyle("-fx-font-size: 24px; -fx-text-fill: blue;");
        }

        // Check if the current player wins
        if (checkWin(row, col)) {
            showWinnerAlert();
            resetBoard();
            return;
        }

        // Switch player
        currentPlayer = currentPlayer.equals("X") ? "O" : "X";
    }

    // Check if the current player has won the game
    private boolean checkWin(int row, int col) {
        return checkDirection(row, col, 1, 0) ||  // Check horizontal
               checkDirection(row, col, 0, 1) ||  // Check vertical
               checkDirection(row, col, 1, 1) ||  // Check diagonal /
               checkDirection(row, col, 1, -1);   // Check diagonal \
    }

    // Check a given direction for a win
    private boolean checkDirection(int row, int col, int dRow, int dCol) {
        int count = 1;  // The current move counts as one
        String player = board[row][col].getText();

        // Check in the positive direction
        int r = row + dRow;
        int c = col + dCol;
        while (r >= 0 && r < SIZE && c >= 0 && c < SIZE && board[r][c].getText().equals(player)) {
            count++;
            r += dRow;
            c += dCol;
        }

        // Check in the negative direction
        r = row - dRow;
        c = col - dCol;
        while (r >= 0 && r < SIZE && c >= 0 && c < SIZE && board[r][c].getText().equals(player)) {
            count++;
            r -= dRow;
            c -= dCol;
        }

        // Return true if 5 in a row
        return count >= 5;
    }

    // Show the winner alert
    private void showWinnerAlert() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Game Over");
        alert.setHeaderText("Player " + currentPlayer + " wins!");
        alert.setContentText("Congratulations!");
        alert.showAndWait();
    }

    // Reset the board
    private void resetBoard() {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                board[row][col].setText("");
                board[row][col].setStyle("-fx-font-size: 24px;");
            }
        }
        currentPlayer = "X";  // X starts again
    }
}
