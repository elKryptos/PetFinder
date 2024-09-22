package hans.startup.petfinderbackend.responses;

import hans.startup.petfinderbackend.models.entities.User;
import lombok.Data;

@Data
public class BackendResponse {
    private String msg;
    private User user;

    public BackendResponse(String msg) {
        super();
        this.msg = msg;
    }

    public BackendResponse(String msg, User user) {
        super();
        this.msg = msg;
        this.user = user;
    }
}
