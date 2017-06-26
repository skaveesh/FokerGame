package foker.gson;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GAMEDATA {

@SerializedName("playerId")
@Expose
private Integer playerId;
@SerializedName("playerName")
@Expose
private String playerName;
@SerializedName("playerReady")
@Expose
private Boolean playerReady;
@SerializedName("score")
@Expose
private Integer score;
@SerializedName("numberOfChangedCards")
@Expose
private Integer numberOfChangedCards;
@SerializedName("playerHand")
@Expose
private List<PlayerHand> playerHand = null;

public Integer getPlayerId() {
return playerId;
}

public void setPlayerId(Integer playerId) {
this.playerId = playerId;
}

public String getPlayerName() {
return playerName;
}

public void setPlayerName(String playerName) {
this.playerName = playerName;
}

public Boolean getPlayerReady() {
return playerReady;
}

public void setPlayerReady(Boolean playerReady) {
this.playerReady = playerReady;
}

public Integer getScore() {
return score;
}

public void setScore(Integer score) {
this.score = score;
}

public Integer getNumberOfChangedCards() {
return numberOfChangedCards;
}

public void setNumberOfChangedCards(Integer numberOfChangedCards) {
this.numberOfChangedCards = numberOfChangedCards;
}

public List<PlayerHand> getPlayerHand() {
return playerHand;
}

public void setPlayerHand(List<PlayerHand> playerHand) {
this.playerHand = playerHand;
}

}