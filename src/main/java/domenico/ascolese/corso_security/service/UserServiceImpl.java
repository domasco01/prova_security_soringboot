package domenico.ascolese.corso_security.service;

import domenico.ascolese.corso_security.domain.User;
import domenico.ascolese.corso_security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;


@Service("UserServiceImpl")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User registrazioneUtente(User user) {

        if (userRepository.existsByUsername(user.getUsername())) {
            throw new RuntimeException("Errore: Username già in uso!");
        }

        if (userRepository.existsByCodiceFiscale(user.getCodiceFiscale())) {
            throw new RuntimeException("Errore: Codice fiscale associato a un account già esistente!");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole("ROLE_USER");

        return userRepository.save(user);
    }
}
