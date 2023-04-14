package com.example.tictactoe;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;

public class TicTacToe extends Application {
    private Label playerXScoreLabel,playerOScoreLabel;
    private boolean playerXTurn = true;
    private Button buttons[][] = new Button[3][3];
    //private Label playerXScoreLabel, playerOScoreLabel;
    private int playerXScore = 0, playerOScore = 0;
    //private boolean playerXT
    private BorderPane createContent(){
        BorderPane root = new BorderPane();
        // Title
        //set padding method is adjust the boarder in  four directions.
        root.setPadding(new Insets(20));
        Label titleLabel = new Label("Tic Tac Toe");// To create Label with test Tic Tac Toe
        titleLabel.setStyle("-fx-font-size : 20pt; -fx -font-weight : bold;");// This method is used to set Styles to titleLabel
        root.setTop(titleLabel);// To set a titleLabel in the top of the root(Boarder Pane).
        BorderPane.setAlignment(titleLabel, Pos.CENTER);// This method is used to set a titleLabel in the middle of the BoarderPane.
        //Game Board
        GridPane gridPane = new GridPane();
        gridPane.setHgap(15);// This method is used to to set a horizontal gap between grids.

        gridPane.setVgap(15);// This method is used to set a vertical gap between grids.
        gridPane.setAlignment(Pos.CENTER);// this method is used to set grid in correct position
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Button button = new Button();
                button.setPrefSize(100,100);// This method is used to set the button size.
                button.setStyle("-fx-font-size : 32pt; -fx -font-weight : bold;");
                button.setOnAction(event->buttonClicked(button));
                buttons[i][j] = button;
                gridPane.add(button,j,i);// In this method we first add the column and next rows.

            }

        }

        root.setCenter(gridPane);// gridPane is add to the center of the BoarderPane.
        BorderPane.setAlignment(gridPane,Pos.CENTER);

        // Score
        HBox scoreBoard = new HBox(20);
        scoreBoard.setAlignment(Pos.CENTER);
        playerXScoreLabel = new Label("Player X : 0");
        playerXScoreLabel.setStyle("-fx-font-size : 14pt; -fx -font-weight : bold;");
        playerOScoreLabel = new Label("Player 0 : 0");
        playerOScoreLabel.setStyle("-fx-font-size : 14pt; -fx -font-weight : bold;");
        scoreBoard.getChildren().addAll(playerXScoreLabel,playerOScoreLabel);
        root.setBottom(scoreBoard);


        return root;

    }
    private void buttonClicked(Button button){
        if(button.getText().equals("")){
            if(playerXTurn){
                button.setText("X");
            }
            else{
                button.setText("0");
            }
            playerXTurn = !playerXTurn;
            checkWinner();

        }
        return;
    }
    private void checkWinner() {
        // row
        for (int row = 0; row < 3; row++) {
            if (buttons[row][0].getText().equals(buttons[row][1].getText())
                    && buttons[row][1].getText().equals(buttons[row][2].getText())
                    && !buttons[row][0].getText().isEmpty()) {
                // we will hava a winner
                String winner = buttons[row][0].getText();
                showWinnerDialog(winner);
                updateScore(winner);
                resetBoard();
                return;
            }
        }
        // col
        for (int col = 0; col < 3; col++) {
            if (buttons[0][col].getText().equals(buttons[1][col].getText())
                    && buttons[1][col].getText().equals(buttons[2][col].getText())
                    && !buttons[0][col].getText().isEmpty()) {
                // we will hava a winner
                String winner = buttons[0][col].getText();
                showWinnerDialog(winner);
                updateScore(winner);
                resetBoard();
                return;
            }

        }


        // diagnol
        if(buttons[0][0].getText().equals(buttons[1][1].getText())
                && buttons[1][1].getText().equals(buttons[2][2].getText())
                && !buttons[0][0].getText().isEmpty()){
            // we will hava a winner
            String winner = buttons[0][0].getText();
            showWinnerDialog(winner);
            updateScore(winner);
            resetBoard();
            return;
        }
        if(buttons[2][0].getText().equals(buttons[1][1].getText())
                && buttons[1][1].getText().equals(buttons[0][2].getText())
                && !buttons[2][0].getText().isEmpty()){
            // we will hava a winner
            String winner = buttons[2][0].getText();
            showWinnerDialog(winner);
            updateScore(winner);
            resetBoard();
            return;
        }
        boolean tie = true;
        for (Button row[] : buttons) {
            for (Button button : row) {
                if (button.getText().isEmpty()) {
                    tie = false;
                    break;
                }
            }
        }
        if (tie) {
            showTieDialog();
            resetBoard();
        }
    }
    private void showWinnerDialog(String winner){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Winner");
        alert.setContentText("Congratulations " + winner + "! You won the game");
        alert.setHeaderText("");
        alert.showAndWait();
    }
    private void showTieDialog(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Tie");
        alert.setContentText("Game Over ! It's a tie.");
        alert.setHeaderText("");
        alert.showAndWait();
    }
    private void updateScore(String winner){
        if(winner.equals("X")){
            playerXScore++;
            playerXScoreLabel.setText("Player X : "+playerXScore);
        }
        else{
            playerOScore++;
            playerOScoreLabel.setText("Player 0 : " + playerOScore);
        }
    }
    public void resetBoard(){
        for(Button row[]:buttons){
            for(Button button:row){
                button.setText("");
            }
        }
    }
    @Override
    public void start(Stage stage) throws IOException {

        Scene scene = new Scene(createContent());
        stage.setTitle("Tic Tac Toe");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}