package foker.controller;

import com.google.gson.Gson;
import foker.GameSocketConnection;
import foker.StartConnection;
import foker.core.PlayerPosition;
import foker.gson.*;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by samintha on 6/27/2017.
 */
public class ControllerGame implements Initializable {
    private boolean allPlayersReady = true;
    private List<GAMEDATA> gameData = new ArrayList<>();
    public static int playerChangedCards = 0;
    public static boolean playerCanChangeCards = false;
    public static boolean allRoundsEnd = false;

    public static ScheduledExecutorService gameDataObserver;

//    @FXML
//    private AnchorPane anchorPane;

    public Button buttonCard1;
    public Button buttonCard2;
    public Button buttonCard3;
    public Button buttonCard4;
    public Button buttonCard5;

    public Label labelMyName;
    public Label labelScore;

    @FXML
    public AnchorPane paneGameCard;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //clear anchor pane when starting
        paneGameCard.getChildren().clear();

        //when initializing set round end data to...
        allRoundsEnd = false;

        //hide all cards
        buttonCard1.setVisible(false);
        buttonCard2.setVisible(false);
        buttonCard3.setVisible(false);
        buttonCard4.setVisible(false);
        buttonCard5.setVisible(false);


        int playerCount = 0;

        List<PlayerPosition> playerPositionList = new ArrayList<>();
        List<PLAYERREADY> otherPlayersList = new ArrayList<>();

        for (int i = 0; i < GameSocketConnection.PLAYER_READY_DATA.getPLAYERREADY().size(); i++) {

            //add other players ids to a list
            if (GameSocketConnection.PLAYER_ID != GameSocketConnection.PLAYER_READY_DATA.getPLAYERREADY().get(i).getPlayerId()) {
                otherPlayersList.add(GameSocketConnection.PLAYER_READY_DATA.getPLAYERREADY().get(i));
            }

            playerCount++;

            if (!GameSocketConnection.PLAYER_READY_DATA.getPLAYERREADY().get(i).getPlayerReady())
                allPlayersReady = false;

        }

        //define positions for players without player playing
        switch (playerCount - 1) {
            case 1:
                playerPositionList.add(new PlayerPosition(otherPlayersList.remove(0).getPlayerId(), 300.0, 30.0));
                break;
            case 2:
                playerPositionList.add(new PlayerPosition(otherPlayersList.remove(0).getPlayerId(), 150.0, 50.0));
                playerPositionList.add(new PlayerPosition(otherPlayersList.remove(0).getPlayerId(), 450.0, 50.0));
                break;
            case 3:
                playerPositionList.add(new PlayerPosition(otherPlayersList.remove(0).getPlayerId(), 150.0, 50.0));
                playerPositionList.add(new PlayerPosition(otherPlayersList.remove(0).getPlayerId(), 300.0, 30.0));
                playerPositionList.add(new PlayerPosition(otherPlayersList.remove(0).getPlayerId(), 450.0, 50.0));
                break;
            case 4:
                playerPositionList.add(new PlayerPosition(otherPlayersList.remove(0).getPlayerId(), 150.0, 100.0));
                playerPositionList.add(new PlayerPosition(otherPlayersList.remove(0).getPlayerId(), 150.0, 50.0));
                playerPositionList.add(new PlayerPosition(otherPlayersList.remove(0).getPlayerId(), 450.0, 50.0));
                playerPositionList.add(new PlayerPosition(otherPlayersList.remove(0).getPlayerId(), 450.0, 100.0));
                break;
            case 5:
                playerPositionList.add(new PlayerPosition(otherPlayersList.remove(0).getPlayerId(), 150.0, 100.0));
                playerPositionList.add(new PlayerPosition(otherPlayersList.remove(0).getPlayerId(), 150.0, 50.0));
                playerPositionList.add(new PlayerPosition(otherPlayersList.remove(0).getPlayerId(), 300.0, 30.0));
                playerPositionList.add(new PlayerPosition(otherPlayersList.remove(0).getPlayerId(), 450.0, 50.0));
                playerPositionList.add(new PlayerPosition(otherPlayersList.remove(0).getPlayerId(), 450.0, 100.0));
                break;
        }


        Runnable gameDataSubscriber = () -> {
            Platform.runLater(
                    () -> {
                        //clear anchor pane whenever add data
                        paneGameCard.getChildren().clear();

                        //adding player names
                        if (GameSocketConnection.PLAYER_READY_DATA != null) {
                            for (PLAYERREADY playerReady : GameSocketConnection.PLAYER_READY_DATA.getPLAYERREADY()) {
                                if (playerReady.getPlayerId() == GameSocketConnection.PLAYER_ID) {
                                    labelMyName.setText(playerReady.getPlayerName());

                                } else {

                                    Label otherPlayerLabel;

                                    if (playerReady.getPlayerReady())
                                        otherPlayerLabel = new Label(playerReady.getPlayerName());
                                    else
                                        otherPlayerLabel = new Label(playerReady.getPlayerName() + " (Not Ready)");

                                    paneGameCard.getChildren().add(otherPlayerLabel);

                                    for (PlayerPosition playerPosition : playerPositionList) {
                                        if (playerReady.getPlayerId() == playerPosition.getPlayerId()) {
                                            AnchorPane.setLeftAnchor(otherPlayerLabel, playerPosition.getPositionX());
                                            AnchorPane.setTopAnchor(otherPlayerLabel, playerPosition.getPositionY());
                                        }
                                    }
                                }
                            }
                        }

                        //adding player cards
                        if (GameSocketConnection.GAME_DATA != null) {
                            gameData = GameSocketConnection.GAME_DATA.getGAMEDATA();

                            for (GAMEDATA playerGameData : gameData) {


                                if (playerGameData.getPlayerId() == GameSocketConnection.PLAYER_ID) {
                                    for (PlayerHand playerHand : playerGameData.getPlayerHand()) {

                                        if (playerHand.getCardId() == 1) editButtonData(buttonCard1, playerHand);

                                        else if (playerHand.getCardId() == 2) editButtonData(buttonCard2, playerHand);

                                        else if (playerHand.getCardId() == 3) editButtonData(buttonCard3, playerHand);

                                        else if (playerHand.getCardId() == 4) editButtonData(buttonCard4, playerHand);

                                        else if (playerHand.getCardId() == 5) editButtonData(buttonCard5, playerHand);
                                    }

                                    //update score
                                    labelScore.setText("Score: " + playerGameData.getScore());
                                } else {
                                    //other players data

                                    Label otherPlayerCard1 = new Label("");
                                    Label otherPlayerCard2 = new Label("");
                                    Label otherPlayerCard3 = new Label("");
                                    Label otherPlayerCard4 = new Label("");
                                    Label otherPlayerCard5 = new Label("");


                                    if (!allRoundsEnd) {

                                        if (playerGameData.getPlayerHand().get(0).getIsInitial()) {
                                            otherPlayerCard1.setText("");
                                            otherPlayerCard1.setText(playerGameData.getPlayerHand().get(0).getRank() + " " + playerGameData.getPlayerHand().get(0).getSuit());
                                        } else
                                            otherPlayerCard1.setText("");

                                        if (playerGameData.getPlayerHand().get(1).getIsInitial()) {
                                            otherPlayerCard2.setText("");
                                            otherPlayerCard2.setText(playerGameData.getPlayerHand().get(1).getRank() + " " + playerGameData.getPlayerHand().get(1).getSuit());
                                        } else
                                            otherPlayerCard2.setText("");

                                        for (PlayerPosition playerPosition : playerPositionList) {
                                            if (playerGameData.getPlayerId() == playerPosition.getPlayerId()) {
                                                AnchorPane.setLeftAnchor(otherPlayerCard1, playerPosition.getPositionX() - 40);
                                                AnchorPane.setTopAnchor(otherPlayerCard1, playerPosition.getPositionY() + 15);

                                                AnchorPane.setLeftAnchor(otherPlayerCard2, playerPosition.getPositionX() + 40);
                                                AnchorPane.setTopAnchor(otherPlayerCard2, playerPosition.getPositionY() + 15);
                                            }
                                        }

                                        try {
                                            paneGameCard.getChildren().add(otherPlayerCard1);
                                            paneGameCard.getChildren().add(otherPlayerCard2);
                                        } catch (IllegalArgumentException e) {
                                        }

                                    } else {

                                        otherPlayerCard1.setText(playerGameData.getPlayerHand().get(0).getRank() + " " + playerGameData.getPlayerHand().get(0).getSuit());
                                        otherPlayerCard2.setText(playerGameData.getPlayerHand().get(1).getRank() + " " + playerGameData.getPlayerHand().get(1).getSuit());
                                        otherPlayerCard3.setText(playerGameData.getPlayerHand().get(2).getRank() + " " + playerGameData.getPlayerHand().get(2).getSuit());
                                        otherPlayerCard4.setText(playerGameData.getPlayerHand().get(3).getRank() + " " + playerGameData.getPlayerHand().get(3).getSuit());
                                        otherPlayerCard5.setText(playerGameData.getPlayerHand().get(4).getRank() + " " + playerGameData.getPlayerHand().get(4).getSuit());

                                        for (PlayerPosition playerPosition : playerPositionList) {
                                            if (playerGameData.getPlayerId() == playerPosition.getPlayerId()) {
                                                AnchorPane.setLeftAnchor(otherPlayerCard1, playerPosition.getPositionX() - 90);
                                                AnchorPane.setTopAnchor(otherPlayerCard1, playerPosition.getPositionY() + 15);

                                                AnchorPane.setLeftAnchor(otherPlayerCard2, playerPosition.getPositionX() - 45);
                                                AnchorPane.setTopAnchor(otherPlayerCard2, playerPosition.getPositionY() + 15);

                                                AnchorPane.setLeftAnchor(otherPlayerCard3, playerPosition.getPositionX());
                                                AnchorPane.setTopAnchor(otherPlayerCard3, playerPosition.getPositionY() + 15);

                                                AnchorPane.setLeftAnchor(otherPlayerCard4, playerPosition.getPositionX() + 45);
                                                AnchorPane.setTopAnchor(otherPlayerCard4, playerPosition.getPositionY() + 15);

                                                AnchorPane.setLeftAnchor(otherPlayerCard5, playerPosition.getPositionX() + 90);
                                                AnchorPane.setTopAnchor(otherPlayerCard5, playerPosition.getPositionY() + 15);
                                            }
                                        }

                                        try {
                                            paneGameCard.getChildren().add(otherPlayerCard1);
                                            paneGameCard.getChildren().add(otherPlayerCard2);
                                            paneGameCard.getChildren().add(otherPlayerCard3);
                                            paneGameCard.getChildren().add(otherPlayerCard4);
                                            paneGameCard.getChildren().add(otherPlayerCard5);
                                        } catch (IllegalArgumentException e) {

                                        }
                                    }


                                }
                            }

                            //show all cards
                            buttonCard1.setVisible(true);
                            buttonCard2.setVisible(true);
                            buttonCard3.setVisible(true);
                            buttonCard4.setVisible(true);
                            buttonCard5.setVisible(true);


                        }
                    });
        };

        //start fetching data every second
        gameDataObserver = Executors.newScheduledThreadPool(1);
        gameDataObserver.scheduleAtFixedRate(gameDataSubscriber, 0, 1, TimeUnit.SECONDS);


        //define buttons to change cards


        buttonCard1.setOnAction(event -> sendChangeCardData(1));

        buttonCard2.setOnAction(event -> sendChangeCardData(2));

        buttonCard3.setOnAction(event -> sendChangeCardData(3));

        buttonCard4.setOnAction(event -> sendChangeCardData(4));

        buttonCard5.setOnAction(event -> sendChangeCardData(5));

    }

    private void sendChangeCardData(int cardId) {
        if (playerCanChangeCards && playerChangedCards < 3) {
            CHANGECARD changecard = new CHANGECARD();
            changecard.setPlayerId(GameSocketConnection.PLAYER_ID);
            changecard.setCardId(cardId);
            ChangeCardData changeCardData = new ChangeCardData();
            changeCardData.setCHANGECARD(changecard);
            Gson gson = new Gson();
            StartConnection.sendMessage(gson.toJson(changeCardData));
        }
    }

    private static void editButtonData(Button button, PlayerHand playerHand) {
        button.setText(playerHand.getRank() + " " + playerHand.getSuit());

        if (playerHand.getIsInitial())
            button.setBackground(new Background(new BackgroundFill(
                    Color.YELLOW, new CornerRadii(5), Insets.EMPTY)));
        else
            button.setBackground(new Background(new BackgroundFill(
                    Color.LIGHTGRAY, new CornerRadii(5), Insets.EMPTY)));
    }
}
