package foker.gson;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RoundBeginData {

@SerializedName("ROUNDBEGIN")
@Expose
private ROUNDBEGIN rOUNDBEGIN;

public ROUNDBEGIN getROUNDBEGIN() {
return rOUNDBEGIN;
}

public void setROUNDBEGIN(ROUNDBEGIN rOUNDBEGIN) {
this.rOUNDBEGIN = rOUNDBEGIN;
}

}