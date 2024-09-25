package hans.startup.petfinderbackend.responses;

import hans.startup.petfinderbackend.models.entities.Animal;
import hans.startup.petfinderbackend.models.entities.User;
import lombok.Data;

import java.util.Map;

@Data
public class BackendResponse {
    private String msg;
    private User user;
    private Animal animal;
    private String email;
    private String token;
    private Map<String, Object> data;

    public BackendResponse(String msg) {
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

    // Costruttore per messaggi e dati extra (claims)
    public BackendResponse(String msg, Map<String, Object> data) {
        this.msg = msg;
        this.data = data;
    }
}