package hans.startup.petfinderbackend.responses;

import hans.startup.petfinderbackend.models.entities.User;
import lombok.Data;

import java.util.Map;

@Data
public class UserResponse {
    private String msg;
    private User user;
    private String email;
    private String token;
    private Map<String, Object> data;

    public UserResponse(String msg) {
        this.msg = msg;
    }

    public UserResponse(String msg, User user) {
        this.msg = msg;
        this.user = user;
    }

    public UserResponse(String token, String email) {
        this.token = token;
        this.email = email;
    }

    // Costruttore per messaggi e dati extra (claims)
    public UserResponse(String msg, Map<String, Object> data) {
        this.msg = msg;
        this.data = data;
    }
}