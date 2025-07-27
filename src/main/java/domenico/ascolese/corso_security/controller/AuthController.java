package domenico.ascolese.corso_security.controller;

import domenico.ascolese.corso_security.domain.User;
import domenico.ascolese.corso_security.dto.JwtAuthResponse;
import domenico.ascolese.corso_security.dto.LoginRequest;
import domenico.ascolese.corso_security.dto.UserDto;
import domenico.ascolese.corso_security.security.JwtTokenProvider;
import domenico.ascolese.corso_security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponse> authenticateUser(
            @RequestBody LoginRequest loginRequest) {

        // 1. Effettua l’autenticazione (signin)
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );

        // 2. Se ok, memorizza il contesto di sicurezza
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // 3. Genera il JWT
        String jwt = tokenProvider.generateToken(authentication);

        // 4. Restituisci token al client
        return ResponseEntity.ok(new JwtAuthResponse(jwt));
    }

//    @PostMapping("/register")
//    public ResponseEntity<UserDto> registrazione(@RequestBody UserDto userDto) {
//
//
//        return
//    }
}
