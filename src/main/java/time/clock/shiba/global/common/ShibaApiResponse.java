package time.clock.shiba.global.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.io.Serializable;

@JsonPropertyOrder({
        "status",
        "message",
        "data"
})
@Data
public class ShibaApiResponse implements Serializable {

    @JsonIgnore
    private static final long serialVersionUID = 7702134516418120340L;

    @JsonProperty("status")
    private ShibaStatus status;

    @JsonProperty("message")
    private String message;

    @JsonProperty("data")
    private Object data;

    public ShibaApiResponse(ShibaStatus status, String message, Object data){
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public ShibaApiResponse(ShibaStatus status, String message){
        this.status = status;
        this.message = message;
    }
}
