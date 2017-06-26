package foker.gson;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PLAYERID {

@SerializedName("playerId")
@Expose
private Integer playerId;

public Integer getPlayerId() {
return playerId;
}

public void setPlayerId(Integer playerId) {
this.playerId = playerId;
}

}