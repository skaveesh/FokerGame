package foker.gson;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RoundEndData {

@SerializedName("ROUNDEND")
@Expose
private ROUNDEND rOUNDEND;

public ROUNDEND getROUNDEND() {
return rOUNDEND;
}

public void setROUNDEND(ROUNDEND rOUNDEND) {
this.rOUNDEND = rOUNDEND;
}

}