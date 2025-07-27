package domenico.ascolese.corso_security;

import domenico.ascolese.corso_security.domain.User;
import domenico.ascolese.corso_security.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DemoApplication {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostConstruct
    public void init() {
        if (userRepository.findByUsername("admin").isEmpty()) {
            User user = new User();
            user.setUsername("admin");
            user.setPassword(passwordEncoder.encode("admin123"));
            user.setRole("ROLE_ADMIN");
            user.setNome("Domenico");
            user.setCognome("Ascolese");
            user.setCodiceFiscale("SCLDN12D2WERRC3");
            userRepository.save(user);
        }
    }
}