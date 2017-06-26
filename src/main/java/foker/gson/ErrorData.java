package foker.gson;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ErrorData {

@SerializedName("ERROR")
@Expose
private String eRROR;

public String getERROR() {
return eRROR;
}

public void setERROR(String eRROR) {
this.eRROR = eRROR;
}

}