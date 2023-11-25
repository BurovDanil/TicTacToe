module main.tictactoe {
    requires javafx.controls;
    requires javafx.fxml;
            
                            
    opens main.tictactoe to javafx.fxml;
    exports main.tictactoe;
}