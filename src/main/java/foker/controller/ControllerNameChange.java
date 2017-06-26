package foker.controller;

import com.google.gson.Gson;
import foker.GameSocketConnection;
import foker.StartConnection;
import foker.gson.SETPLAYERNAME;
import foker.gson.SetPlayerNameData;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class ControllerNameChange {
    public Button buttonChangePlayerName;
    public TextField textFieldSetPlayerName;

    public void handleChangeName(){
        String playerName = textFieldSetPlayerName.getText();

        if(!playerName.trim().equals("")){
            SETPLAYERNAME setplayername = new SETPLAYERNAME();
            setplayername.setPlayerId(GameSocketConnection.PLAYER_ID);
            setplayername.setPlayerName(playerName);

            SetPlayerNameData setPlayerNameData = new SetPlayerNameData();
            setPlayerNameData.setSETPLAYERNAME(setplayername);

            Gson gson = new Gson();

        StartConnection.sendMessage(gson.toJson(setPlayerNameData));
            System.out.println(gson.toJson(setPlayerNameData));

        }else{
            System.out.println("enter valid name");
        }
    }
}
