package domenico.ascolese.corso_security.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @EqualsAndHashCode
public class JwtAuthResponse {
    private String accessToken;
    private String tokenType = "Bearer";
    public JwtAuthResponse(String accessToken) {
        this.accessToken = accessToken;
    }
}