package foker.gson;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PLAYERCHANGECARDS {

@SerializedName("isAllowed")
@Expose
private Boolean isAllowed;
@SerializedName("message")
@Expose
private String message;

public Boolean getIsAllowed() {
return isAllowed;
}

public void setIsAllowed(Boolean isAllowed) {
this.isAllowed = isAllowed;
}

public String getMessage() {
return message;
}

public void setMessage(String message) {
this.message = message;
}

}