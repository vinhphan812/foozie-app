package vn.edu.huflit.foozie_app.Models;

import com.google.gson.annotations.SerializedName;

public class ResponseDTO {
    private final String REQUEST_ERROR = "CALL_REQUEST_ERROR";
    @SerializedName("success")
    public Boolean success;
    @SerializedName("data") // data is a object string
    public Object data;
    @SerializedName("message")
    public String message;
    public Boolean isInvalid() throws Exception {

        if(this == null || this != null && !this.success)
            throw new Exception(message != null ? message : REQUEST_ERROR);
        return true;
    }
}
