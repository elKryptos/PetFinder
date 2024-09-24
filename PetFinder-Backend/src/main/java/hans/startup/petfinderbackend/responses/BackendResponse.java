package hans.startup.petfinderbackend.responses;

import hans.startup.petfinderbackend.models.entities.User;
import lombok.Data;

import java.util.Map;

@Data
public class BackendResponse {
    private String msg;
    private User user;
    private String email;
    private String token;
    private Map<String, Object> data;

    // Costruttore per messaggi semplici
    public BackendResponse(String msg) {
        this.msg = msg;
    }

    // Costruttore per messaggi e un utente
    public BackendResponse(String msg, User user) {
        this.msg = msg;
        this.user = user;
    }

    // Costruttore per token ed email
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