package foker.controller;

import com.google.gson.Gson;
import foker.GameSocketConnection;
import foker.StartConnection;
import foker.gson.SETPLAYERNAME;
import foker.gson.SetPlayerNameData;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ControllerNameChange {
    public TextField textFieldSetPlayerName;


    @FXML
    public void handleChangeName() {
        String playerName = textFieldSetPlayerName.getText();

        if (!playerName.trim().equals("")) {
            SETPLAYERNAME setplayername = new SETPLAYERNAME();
            setplayername.setPlayerId(GameSocketConnection.PLAYER_ID);
            setplayername.setPlayerName(playerName);

            SetPlayerNameData setPlayerNameData = new SetPlayerNameData();
            setPlayerNameData.setSETPLAYERNAME(setplayername);

            Gson gson = new Gson();

            StartConnection.sendMessage(gson.toJson(setPlayerNameData));

        } else {
            Stage dialogStage = new Stage();
            dialogStage.initModality(Modality.WINDOW_MODAL);
            Button button = new Button("OK");
            button.setOnAction(e->dialogStage.close());

            VBox vbox = new VBox(new Text("Enter a valid name"),button );
            vbox.setAlignment(Pos.CENTER);
            vbox.setPadding(new Insets(15));

            dialogStage.setScene(new Scene(vbox));
            dialogStage.show();
        }
    }
}
