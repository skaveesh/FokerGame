package foker.gson;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GameEndData {

@SerializedName("GAMEEND")
@Expose
private GAMEEND gAMEEND;

public GAMEEND getGAMEEND() {
return gAMEEND;
}

public void setGAMEEND(GAMEEND gAMEEND) {
this.gAMEEND = gAMEEND;
}

}