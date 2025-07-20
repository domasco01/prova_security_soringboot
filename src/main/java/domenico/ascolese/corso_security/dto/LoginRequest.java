package domenico.ascolese.corso_security.dto;

import lombok.*;

@Getter @Setter @EqualsAndHashCode @AllArgsConstructor @NoArgsConstructor
public class LoginRequest {
    private String username;
    private String password;
}