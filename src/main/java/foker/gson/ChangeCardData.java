package foker.gson;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ChangeCardData {

@SerializedName("CHANGECARD")
@Expose
private CHANGECARD cHANGECARD;

public CHANGECARD getCHANGECARD() {
return cHANGECARD;
}

public void setCHANGECARD(CHANGECARD cHANGECARD) {
this.cHANGECARD = cHANGECARD;
}

}