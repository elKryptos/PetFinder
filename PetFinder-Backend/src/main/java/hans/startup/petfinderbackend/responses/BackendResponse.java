package hans.startup.petfinderbackend.responses;

import hans.startup.petfinderbackend.models.entities.User;
import lombok.Data;

@Data
public class BackendResponse {
    private String msg;
    private User user;
    private String email;
    private String token;

    public BackendResponse(String msg) {
        super();
        this.msg = msg;
    }

    public BackendResponse(String msg, User user) {
        this.msg = msg;
        this.user = user;
    }

    public BackendResponse(String token, String email) {
        this.token = token;
        this.email = email;
    }

}
