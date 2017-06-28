package foker.gson;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PlayersConnectedData {

@SerializedName("PLAYERSCONNECTED")
@Expose
private PLAYERSCONNECTED pLAYERSCONNECTED;

public PLAYERSCONNECTED getPLAYERSCONNECTED() {
return pLAYERSCONNECTED;
}

public void setPLAYERSCONNECTED(PLAYERSCONNECTED pLAYERSCONNECTED) {
this.pLAYERSCONNECTED = pLAYERSCONNECTED;
}

}