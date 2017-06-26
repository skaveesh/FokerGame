package foker.gson;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PLAYERREADY {

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

}