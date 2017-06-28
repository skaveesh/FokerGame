package foker.controller;

import com.google.gson.Gson;
import foker.GameSocketConnection;
import foker.StartConnection;
import foker.gson.STARTGAME;
import foker.gson.StartGameData;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by samintha on 6/27/2017.
 */
public class ControllerGameStart implements Initializable {

    public Label labelPlayerName;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        labelPlayerName.setText("Player Name: " +GameSocketConnection.PLAYER_NAME);
    }

    public void startGameHandler(){
        STARTGAME startgame = new STARTGAME();
        startgame.setPlayerId(GameSocketConnection.PLAYER_ID);
        startgame.setStartGame(true);

        StartGameData startGameData = new StartGameData();
        startGameData.setSTARTGAME(startgame);

        Gson gson = new Gson();

        StartConnection.sendMessage(gson.toJson(startGameData));

        GameSocketConnection.PLAYER_STARTED_GAME = true;
    }
}
