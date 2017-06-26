package foker.gson;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GameDataData {

@SerializedName("GAMEDATA")
@Expose
private List<GAMEDATA> gAMEDATA = null;

public List<GAMEDATA> getGAMEDATA() {
return gAMEDATA;
}

public void setGAMEDATA(List<GAMEDATA> gAMEDATA) {
this.gAMEDATA = gAMEDATA;
}

}