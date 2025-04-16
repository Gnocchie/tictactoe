package de.lmu.bio.ifi;

import de.lmu.bio.ifi.TTT_Window.CustomPaintLabel;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


public class GUI extends Application implements EventHandler<MouseEvent> {
    TicTacToe t = new TicTacToe(3);
    Label label = new Label("Press any space to start");
    Button reset = new Button("Reset");
    Button buttonAi = new Button("Enable AI");
    AI bot = null;
    CustomPaintLabel[][] grid = new CustomPaintLabel[3][3];

    public void start(Stage window) {

        BorderPane mainLayout = new BorderPane();
        GridPane layout = new GridPane();

        for(int x=0;x<3;x++){       // Create playable squares
            for(int y=0;y<3;y++){
                CustomPaintLabel c = new CustomPaintLabel(x,y);
                grid[x][y] = c;
                c.addEventHandler(MouseEvent.ANY, this);
                c.setPrefSize(200,200);
                layout.add(c, x, y);
            }
        }

        EventHandler<MouseEvent> onClick = mouseEvent -> {      // Reset Button
           t = new TicTacToe(3);
           if (bot != null) bot = new AI(t);
           for(CustomPaintLabel[] cRow : grid) {        // Go through each square to reset board
               for(CustomPaintLabel c : cRow) {
                 c.clear();
               }
           }
           label.setText("Press any space to start");};
        reset.addEventHandler(MouseEvent.MOUSE_CLICKED, onClick);

        EventHandler<MouseEvent> onClick2 = mouseEvent -> {      // AI Button
            if (bot == null) {
                bot = new AI(t);
                buttonAi.setText("Disable AI");
            }
            else {
                bot = null;
                buttonAi.setText("Enable AI");
            }

            };
        buttonAi.addEventHandler(MouseEvent.MOUSE_CLICKED, onClick2);

        layout.setGridLinesVisible(true);
        mainLayout.setTop(this.label);
        mainLayout.setBottom(this.reset);
        mainLayout.setRight(this.buttonAi);
        mainLayout.setCenter(layout);

        window.setScene(new Scene(mainLayout,600,700));
        window.show();
    }
    public static void main (String[] args){
        launch(args);
    }
    public void handle(MouseEvent e) {
        if(e.getEventType()==MouseEvent.MOUSE_CLICKED){
            if(e.getSource() instanceof CustomPaintLabel && !t.OVER){
                CustomPaintLabel src = (CustomPaintLabel)e.getSource();

                if(src.paint(t.getTURN_P1())){
                    t.makeMove(t.getTURN_P1(), src.x, src.y);       // Player Move
                    updateStatus();

                    if(bot != null && !t.OVER) {        // Bot Move
                        int[] coord = bot.makeMove();
                        grid[coord[0]][coord[1]].paint(bot.P1);
                        updateStatus();
                    }

                } else System.out.println("Invalid Move");      // Prompt for moves made on an occupied square

            }
        }
        // else System.out.println(e.getEventType());
    }
    public void updateStatus(){
        GameStatus status = t.gameStatus();
        if(status == GameStatus.PLAYER_1_WON) label.setText("Player 1 Wins");
        if (status == GameStatus.PLAYER_2_WON) label.setText("Player 2 Wins");
        if (status == GameStatus.DRAW) label.setText("What a surprise, its a draw!");
        if (status == GameStatus.RUNNING){
            if(t.TURN_P1) label.setText("Player 1's turn");
            else label.setText("Player 2's turn");
        }
    }
}