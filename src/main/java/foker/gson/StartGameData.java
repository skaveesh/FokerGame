package foker.gson;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StartGameData {

@SerializedName("STARTGAME")
@Expose
private STARTGAME sTARTGAME;

public STARTGAME getSTARTGAME() {
return sTARTGAME;
}

public void setSTARTGAME(STARTGAME sTARTGAME) {
this.sTARTGAME = sTARTGAME;
}

}