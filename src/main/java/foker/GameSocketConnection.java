package foker;

import com.google.gson.Gson;

import foker.controller.ControllerGame;
import foker.gson.*;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft;
import org.java_websocket.handshake.ServerHandshake;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by samintha on 6/26/2017.
 */
public class GameSocketConnection extends WebSocketClient {

    public static boolean CONNECTION_OPENED;

    public static int PLAYER_ID;
    public static String PLAYER_NAME;
    public static PlayerReadyData PLAYER_READY_DATA;
    public static GameDataData GAME_DATA;
    public static boolean PLAYER_STARTED_GAME = false;

    GameSocketConnection(URI serverUri, Draft draft) {
        super(serverUri, draft);
    }

    GameSocketConnection(URI serverURI) {
        super(serverURI);
    }

    @Override
    public void onOpen(ServerHandshake serverHandshake) {
        CONNECTION_OPENED = true;
    }

    @Override
    public void onMessage(String message) {

        System.out.println("Server returned data:");
        System.out.println(message);

        boolean isJsonObject;
        JSONObject jsonObject = new JSONObject();
        Gson gson = new Gson();

        try {
            jsonObject = new JSONObject(message);
            isJsonObject = true;
        } catch (Exception e) {
            e.printStackTrace();
            isJsonObject = false;
        }

        if (isJsonObject && jsonObject.has("GAMEDATA")) {
            GAME_DATA = gson.fromJson(message, GameDataData.class);
        } else if (isJsonObject && jsonObject.has("PLAYERID")) {
            PlayerIdData playerIdData = gson.fromJson(message, PlayerIdData.class);
            PLAYER_ID = playerIdData.getPLAYERID().getPlayerId();
        } else if (isJsonObject && jsonObject.has("PLAYERNAME")) {
            PlayerNameData playerNameData = gson.fromJson(message, PlayerNameData.class);

            PLAYER_NAME = playerNameData.getPLAYERNAME().getPlayerName();

            //start next screen when name received from server
            Platform.runLater(
                    () -> {
                        // Update UI here.
                        try {
                            AnchorPane pane = FXMLLoader.load(getClass().getResource("/view/view_game_start.fxml"));
                            Main.root.getChildren().setAll(pane);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
            );
        } else if (isJsonObject && jsonObject.has("PLAYERSCONNECTED")) {
            PlayersConnectedData playersConnectedData = gson.fromJson(message, PlayersConnectedData.class);

            Platform.runLater(
                    () -> {
                        // Update UI here.
                        Stage dialogStage = new Stage();
                        dialogStage.initModality(Modality.WINDOW_MODAL);
                        Button button = new Button("OK");
                        button.setOnAction(e -> dialogStage.close());

                        VBox vbox = new VBox(new Text(playersConnectedData.getPLAYERSCONNECTED().getMessage()), button);
                        vbox.setAlignment(Pos.CENTER);
                        vbox.setPadding(new Insets(15));

                        dialogStage.setScene(new Scene(vbox));
                        dialogStage.show();
                    });

            PLAYER_STARTED_GAME = false;

        } else if (isJsonObject && jsonObject.has("PLAYERREADY")) {

            PLAYER_READY_DATA = gson.fromJson(message, PlayerReadyData.class);

            if (PLAYER_STARTED_GAME) {
                //start next screen when name received from server
                Platform.runLater(
                        () -> {
                            // Update UI here.
                            try {
                                AnchorPane pane = FXMLLoader.load(getClass().getResource("/view/view_game.fxml"));
                                Main.root.getChildren().setAll(pane);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                );
            }
        } else if (isJsonObject && jsonObject.has("ROUNDBEGIN")) {
            RoundBeginData roundBeginData = gson.fromJson(message, RoundBeginData.class);

            Platform.runLater(
                    () -> {
                        // Update UI here.
                        Stage dialogStage = new Stage();
                        dialogStage.initModality(Modality.WINDOW_MODAL);
                        Timer timer = new Timer();
                        timer.schedule(new TimerTask() {
                            @Override
                            public void run() {
                                Platform.runLater(dialogStage::close);
                            }
                        }, 3000);
                        VBox vbox = new VBox(new Text("Round " + roundBeginData.getROUNDBEGIN().getRoundNumber()));
                        vbox.setAlignment(Pos.CENTER);
                        vbox.setPadding(new Insets(15));

                        dialogStage.setScene(new Scene(vbox));
                        dialogStage.show();

                        //reset user changed cards in controller
                        ControllerGame.playerChangedCards = 0;
                    });
        } else if (isJsonObject && jsonObject.has("ROUNDEND")) {
            RoundEndData roundEndData = gson.fromJson(message, RoundEndData.class);

            Platform.runLater(
                    () -> {
                        // Update UI here.
                        Stage dialogStage = new Stage();
                        dialogStage.initModality(Modality.WINDOW_MODAL);
                        Timer timer = new Timer();
                        timer.schedule(new TimerTask() {
                            @Override
                            public void run() {
                                Platform.runLater(dialogStage::close);
                            }
                        }, 3000);
                        ROUNDEND roundend = roundEndData.getROUNDEND();
                        VBox vbox = new VBox(new Text("Round " + roundend.getRoundNumber() + " End.\nMaximum Score by " + roundend.getMaxScoredPlayer() + ".\nMaximum score " + roundend.getMaxScore()));
                        vbox.setAlignment(Pos.CENTER);
                        vbox.setPadding(new Insets(15));

                        dialogStage.setScene(new Scene(vbox));
                        dialogStage.show();

                        //set end the game
                        if (roundend.getRoundNumber() == 5)
                            ControllerGame.allRoundsEnd = true;

                    });
        } else if (isJsonObject && jsonObject.has("PLAYERCHANGECARDS")) {
            PlayerChangeCardsData playerChangeCardsData = gson.fromJson(message, PlayerChangeCardsData.class);

            Platform.runLater(
                    () -> {
                        // Update UI here.
                        Stage dialogStage = new Stage();
                        dialogStage.initModality(Modality.WINDOW_MODAL);
                        Timer timer = new Timer();
                        timer.schedule(new TimerTask() {
                            @Override
                            public void run() {
                                Platform.runLater(dialogStage::close);
                            }
                        }, 3000);
                        PLAYERCHANGECARDS playerchangecards = playerChangeCardsData.getPLAYERCHANGECARDS();
                        VBox vbox = new VBox(new Text("Message:  " + playerchangecards.getMessage()));
                        vbox.setAlignment(Pos.CENTER);
                        vbox.setPadding(new Insets(15));

                        dialogStage.setScene(new Scene(vbox));
                        dialogStage.show();

                        ControllerGame.playerCanChangeCards = playerchangecards.getIsAllowed();
                    });
        } else if (isJsonObject && jsonObject.has("GAMEEND")) {
            GameEndData gameEndData = gson.fromJson(message, GameEndData.class);

            Platform.runLater(
                    () -> {
                        // Update UI here.
                        Stage dialogStage = new Stage();
                        dialogStage.initModality(Modality.WINDOW_MODAL);
                        Timer timer = new Timer();
                        timer.schedule(new TimerTask() {
                            @Override
                            public void run() {
                                Platform.runLater(dialogStage::close);
                            }
                        }, 3000);
                        GAMEEND gameend = gameEndData.getGAMEEND();
                        VBox vbox = new VBox(new Text("Message:  " + gameend.getMessage()));
                        vbox.setAlignment(Pos.CENTER);
                        vbox.setPadding(new Insets(15));

                        dialogStage.setScene(new Scene(vbox));
                        dialogStage.show();

                        //head back to start screen
                        try {
                            AnchorPane pane = FXMLLoader.load(getClass().getResource("/view/view_game_start.fxml"));
                            Main.root.getChildren().setAll(pane);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        GAME_DATA = null;
                        PLAYER_READY_DATA = null;
                        PLAYER_STARTED_GAME = false;

                        if (ControllerGame.gameDataObserver != null)
                            ControllerGame.gameDataObserver.shutdownNow();
                    });
        } else if (isJsonObject && jsonObject.has("ERROR")) {
            ErrorData errorData = gson.fromJson(message, ErrorData.class);

            Platform.runLater(
                    () -> {
                        // Update UI here.
                        Stage dialogStage = new Stage();
                        dialogStage.initModality(Modality.WINDOW_MODAL);
                        Timer timer = new Timer();
                        timer.schedule(new TimerTask() {
                            @Override
                            public void run() {
                                Platform.runLater(dialogStage::close);
                            }
                        }, 2000);
                        VBox vbox = new VBox(new Text("Message:  " + errorData.getERROR()));
                        vbox.setAlignment(Pos.CENTER);
                        vbox.setPadding(new Insets(15));

                        dialogStage.setScene(new Scene(vbox));
                        dialogStage.show();
                    });
        } else if (isJsonObject && jsonObject.has("PLAYERSDISCONNECTED")) {
            PlayerDisconnectedData playerDisconnectedData = gson.fromJson(message, PlayerDisconnectedData.class);

            Platform.runLater(
                    () -> {
                        // Update UI here.
                        Stage dialogStage = new Stage();
                        dialogStage.initModality(Modality.WINDOW_MODAL);
                        Timer timer = new Timer();
                        timer.schedule(new TimerTask() {
                            @Override
                            public void run() {
                                Platform.runLater(dialogStage::close);
                            }
                        }, 2000);
                        VBox vbox = new VBox(new Text("Message:  " + playerDisconnectedData.getPLAYERSDISCONNECTED().getMessage()));
                        vbox.setAlignment(Pos.CENTER);
                        vbox.setPadding(new Insets(15));

                        dialogStage.setScene(new Scene(vbox));
                        dialogStage.show();
                    });
        }
    }


    @Override
    public void onClose(int code, String reason, boolean remote) {
        CONNECTION_OPENED = false;
        System.out.println("closed with exit code " + code + " additional info: " + reason);
    }

    @Override
    public void onError(Exception ex) {
        System.err.println("an error occurred:" + ex);
        ex.printStackTrace();
    }
}
