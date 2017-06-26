package foker.gson;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class STARTGAME {

@SerializedName("playerId")
@Expose
private Integer playerId;
@SerializedName("startGame")
@Expose
private Boolean startGame;

public Integer getPlayerId() {
return playerId;
}

public void setPlayerId(Integer playerId) {
this.playerId = playerId;
}

public Boolean getStartGame() {
return startGame;
}

public void setStartGame(Boolean startGame) {
this.startGame = startGame;
}

}