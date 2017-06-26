package foker.gson;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ROUNDBEGIN {

@SerializedName("roundNumber")
@Expose
private Integer roundNumber;

public Integer getRoundNumber() {
return roundNumber;
}

public void setRoundNumber(Integer roundNumber) {
this.roundNumber = roundNumber;
}

}