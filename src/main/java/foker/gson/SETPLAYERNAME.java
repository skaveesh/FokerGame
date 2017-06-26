package foker.gson;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SETPLAYERNAME {

@SerializedName("playerId")
@Expose
private Integer playerId;
@SerializedName("playerName")
@Expose
private String playerName;

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

}