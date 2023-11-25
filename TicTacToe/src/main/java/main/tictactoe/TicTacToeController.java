package main.tictactoe;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.util.Arrays;
import java.util.stream.IntStream;

public class TicTacToeController {
    private char currentPlayer = 'X';
    private char[][] board = new char[3][3];
    @FXML
    public Button button00, button01, button02, button10, button11, button12, button20, button21, button22;

    //
    private boolean showButton = false;
    @FXML
    private Label message;
    @FXML
    private Button resetButton;

    private int countX;
    private int countY;

    //


    @FXML
    private void initialize() {
        initialiseBoard();
    }

    public void initialiseBoard() {
        for (int i = 0; i < 3; i++) {
            Arrays.fill(board[i], ' ');
        }
    }

    public void logic(ActionEvent event) {
        Button clickedButton = (Button) event.getSource();

        int row = Integer.parseInt(clickedButton.getId().substring(6, 7));
        int column = Integer.parseInt(clickedButton.getId().substring(7, 8));

        if (board[row][column] == ' ' && !isGameOver()) {

            //that is how we ensure that the right player is playing
            board[row][column] = currentPlayer;
            clickedButton.setText(String.valueOf(currentPlayer));

            if (checkForWinner()) {
                System.out.println("Player " + currentPlayer + " won!");
                resetGame();
            } else if (isBoardFull()) {
                System.out.println("It is a tie!");
                resetGame();
            } else {
                //checks if the current player is X
                //if true then it becomes O
                //if false it becomes X
                currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
            }
        }
    }

    public boolean isGameOver() {
        return checkForWinner() || isBoardFull();
    }

    public boolean checkForWinner() {
        //row
        for(int i = 0; i < 3; i++){
            if(board[i][0] == currentPlayer && board[i][1] == currentPlayer && board[i][2] == currentPlayer){
                resetGame();
                System.out.println("test");
                return true;
            }
        }
        for(int j = 0; j < 3; j++){
            if(board[0][j] == currentPlayer && board[1][j] == currentPlayer && board[2][j] == currentPlayer){
                System.out.println("Horizontal win!");
                return true;
            }
        }
        if(board[0][0] == currentPlayer && board[1][1] == currentPlayer && board[2][2] == currentPlayer){
//            message.setText("You won!");
            resetGame();
            return true;
        }
        if(board[0][2] == currentPlayer && board[1][1] == currentPlayer && board[2][0] == currentPlayer){
            System.out.println("Right-Left Diagonal win!");
            resetGame();
            return true;
        }
        return false;
    }

    public boolean isBoardFull() {
        long emptySpacesCount = Arrays.stream(board)

                //we create a stream of characters in each row
                .flatMapToInt(row -> new String(row).chars())

                //used to keep only the empty spaces
                .filter(cell -> cell == ' ')

                .count();

        return emptySpacesCount == 0;
    }

    public void resetGame() {
        initialiseBoard();

        //creates a stream of integers from 0 to 2 (inclusive) for the outer loop
        IntStream.range(0, 3)
                //iterates through each row and creates another stream of integers
                .forEach(i -> IntStream.range(0, 3)
                        //iterate through the columns and reset each button to ""
                        .forEach(j -> getButton(i, j).setText("")));

        currentPlayer = 'X';
    }

    private Button getButton(int row, int col) {
        if (row == 0) {
            if (col == 0) {
                return button00;
            } else if (col == 1) {
                return button01;
            } else if (col == 2) {
                return button02;
            }
        } else if (row == 1) {
            if (col == 0) {
                return button10;
            } else if (col == 1) {
                return button11;
            } else if (col == 2) {
                return button12;
            }
        } else if (row == 2) {
            if (col == 0) {
                return button20;
            } else if (col == 1) {
                return button21;
            } else if (col == 2) {
                return button22;
            }
        }
        return null;
    }
}

