package foker.gson;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PlayerDisconnectedData {

@SerializedName("PLAYERSDISCONNECTED")
@Expose
private PLAYERSDISCONNECTED pLAYERSDISCONNECTED;

public PLAYERSDISCONNECTED getPLAYERSDISCONNECTED() {
return pLAYERSDISCONNECTED;
}

public void setPLAYERSDISCONNECTED(PLAYERSDISCONNECTED pLAYERSDISCONNECTED) {
this.pLAYERSDISCONNECTED = pLAYERSDISCONNECTED;
}

}