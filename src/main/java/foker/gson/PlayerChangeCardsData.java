package foker.gson;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PlayerChangeCardsData {

@SerializedName("PLAYERCHANGECARDS")
@Expose
private PLAYERCHANGECARDS pLAYERCHANGECARDS;

public PLAYERCHANGECARDS getPLAYERCHANGECARDS() {
return pLAYERCHANGECARDS;
}

public void setPLAYERCHANGECARDS(PLAYERCHANGECARDS pLAYERCHANGECARDS) {
this.pLAYERCHANGECARDS = pLAYERCHANGECARDS;
}

}