package hans.startup.petfinderbackend.responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response <TYPE> {
    private String msg;
    private TYPE type;

    public Response(String msg) {
        this.msg = msg;
    }

    public Response(TYPE type) {
        this.type = type;
    }

    public Response(String msg, TYPE type) {
        this.msg = msg;
        this.type = type;
    }
}
