package foker.gson;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PlayerHand {

@SerializedName("cardId")
@Expose
private Integer cardId;
@SerializedName("rank")
@Expose
private Integer rank;
@SerializedName("suit")
@Expose
private String suit;
@SerializedName("isInitial")
@Expose
private Boolean isInitial;

public Integer getCardId() {
return cardId;
}

public void setCardId(Integer cardId) {
this.cardId = cardId;
}

public Integer getRank() {
return rank;
}

public void setRank(Integer rank) {
this.rank = rank;
}

public String getSuit() {
return suit;
}

public void setSuit(String suit) {
this.suit = suit;
}

public Boolean getIsInitial() {
return isInitial;
}

public void setIsInitial(Boolean isInitial) {
this.isInitial = isInitial;
}

}