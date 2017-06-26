package foker.gson;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ROUNDEND {

@SerializedName("maxScoredPlayer")
@Expose
private String maxScoredPlayer;
@SerializedName("roundNumber")
@Expose
private Integer roundNumber;
@SerializedName("maxScore")
@Expose
private Integer maxScore;

public String getMaxScoredPlayer() {
return maxScoredPlayer;
}

public void setMaxScoredPlayer(String maxScoredPlayer) {
this.maxScoredPlayer = maxScoredPlayer;
}

public Integer getRoundNumber() {
return roundNumber;
}

public void setRoundNumber(Integer roundNumber) {
this.roundNumber = roundNumber;
}

public Integer getMaxScore() {
return maxScore;
}

public void setMaxScore(Integer maxScore) {
this.maxScore = maxScore;
}

}