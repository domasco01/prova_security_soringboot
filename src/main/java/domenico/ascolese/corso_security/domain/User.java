package domenico.ascolese.corso_security.domain;

import jakarta.persistence.*;
import lombok.*;

@Getter @Setter @EqualsAndHashCode @NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String username;

    private String password;

    private String role; // esempio: "ROLE_USER", "ROLE_ADMIN"

    private String nome;

    private String cognome;

    private String codiceFiscale;

}