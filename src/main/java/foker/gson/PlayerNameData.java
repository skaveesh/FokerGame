package foker.gson;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PlayerNameData {

@SerializedName("PLAYERNAME")
@Expose
private PLAYERNAME pLAYERNAME;

public PLAYERNAME getPLAYERNAME() {
return pLAYERNAME;
}

public void setPLAYERNAME(PLAYERNAME pLAYERNAME) {
this.pLAYERNAME = pLAYERNAME;
}

}