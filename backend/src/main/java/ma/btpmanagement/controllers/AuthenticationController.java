package ma.btpmanagement.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import ma.btpmanagement.dtos.auth.AuthResponse;
import ma.btpmanagement.dtos.auth.LoginRequest;
import ma.btpmanagement.dtos.auth.RegisterRequest;
import ma.btpmanagement.entites.User;
import ma.btpmanagement.enums.Role;
import ma.btpmanagement.repositories.UserRepository;
import ma.btpmanagement.security.JwtService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@Valid @RequestBody RegisterRequest request) {
        var email = request.email().trim().toLowerCase();
        if (userRepository.existsByEmail(email)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

        var user = User.builder()
                .email(email)
                .password(passwordEncoder.encode(request.password()))
                .role(Role.ROLE_ADMIN)
                .enabled(true)
                .build();
        var savedUser = userRepository.save(user);
        var userDetails = toUserDetails(savedUser);

        return ResponseEntity.status(HttpStatus.CREATED).body(authResponse(userDetails));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest request) {
        var email = request.email().trim().toLowerCase();
        var authentication = authenticationManager.authenticate(
                UsernamePasswordAuthenticationToken.unauthenticated(email, request.password())
        );
        return ResponseEntity.ok(authResponse((UserDetails) authentication.getPrincipal()));
    }

    private AuthResponse authResponse(UserDetails userDetails) {
        return new AuthResponse(jwtService.generateToken(userDetails), "Bearer", jwtService.getExpirationMs());
    }

    private UserDetails toUserDetails(User user) {
        return org.springframework.security.core.userdetails.User
                .withUsername(user.getEmail())
                .password(user.getPassword())
                .authorities(user.getRole().name())
                .disabled(!user.isEnabled())
                .build();
    }
}
