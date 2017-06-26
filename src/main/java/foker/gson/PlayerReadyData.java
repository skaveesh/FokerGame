package foker.gson;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PlayerReadyData {

@SerializedName("PLAYERREADY")
@Expose
private List<PLAYERREADY> pLAYERREADY = null;

public List<PLAYERREADY> getPLAYERREADY() {
return pLAYERREADY;
}

public void setPLAYERREADY(List<PLAYERREADY> pLAYERREADY) {
this.pLAYERREADY = pLAYERREADY;
}

}