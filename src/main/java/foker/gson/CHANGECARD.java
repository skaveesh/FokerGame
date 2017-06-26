package foker.gson;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CHANGECARD {

@SerializedName("playerId")
@Expose
private Integer playerId;
@SerializedName("cardId")
@Expose
private Integer cardId;

public Integer getPlayerId() {
return playerId;
}

public void setPlayerId(Integer playerId) {
this.playerId = playerId;
}

public Integer getCardId() {
return cardId;
}

public void setCardId(Integer cardId) {
this.cardId = cardId;
}

}