package foker.gson;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PLAYERNAME {

@SerializedName("playerName")
@Expose
private String playerName;

public String getPlayerName() {
return playerName;
}

public void setPlayerName(String playerName) {
this.playerName = playerName;
}

}