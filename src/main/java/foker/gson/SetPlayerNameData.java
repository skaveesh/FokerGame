package foker.gson;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SetPlayerNameData {

@SerializedName("SETPLAYERNAME")
@Expose
private SETPLAYERNAME sETPLAYERNAME;

public SETPLAYERNAME getSETPLAYERNAME() {
return sETPLAYERNAME;
}

public void setSETPLAYERNAME(SETPLAYERNAME sETPLAYERNAME) {
this.sETPLAYERNAME = sETPLAYERNAME;
}

}