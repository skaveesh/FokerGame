package foker.gson;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PlayerIdData {

@SerializedName("PLAYERID")
@Expose
private PLAYERID pLAYERID;

public PLAYERID getPLAYERID() {
return pLAYERID;
}

public void setPLAYERID(PLAYERID pLAYERID) {
this.pLAYERID = pLAYERID;
}

}