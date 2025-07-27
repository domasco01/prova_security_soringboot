package domenico.ascolese.corso_security.dto;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private String username;

    private String password;

    private String nome;

    private String cognome;

    private String codiceFiscale;
}
