package com.khushi.producthub.contoller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.khushi.producthub.dto.AuthRequestDTO;
import com.khushi.producthub.dto.AuthResponseDTO;
import com.khushi.producthub.dto.LoginAuthRequestDTO;
import com.khushi.producthub.entity.User;
import com.khushi.producthub.repository.UserRepository;
import com.khushi.producthub.security.JwtUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

	private final AuthenticationManager authenticationManager;
	private final JwtUtil jwtUtil;
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	@PostMapping("/register")
	public ResponseEntity<String> register(@Valid @RequestBody AuthRequestDTO request) {

		if (userRepository.findByUsername(request.getUsername()).isPresent()) {
			return ResponseEntity.badRequest().body("Username already exists");
		}

		User user = User.builder().username(request.getUsername()).realPassword(request.getPassword())
				.password(passwordEncoder.encode(request.getPassword())).build();

		userRepository.save(user);

		return ResponseEntity.ok("User registered successfully");
	}

	@PostMapping("/login")
	public ResponseEntity<AuthResponseDTO> login(@Valid @RequestBody LoginAuthRequestDTO request) {
		authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

		String token = jwtUtil.generateToken(request.getUsername());

		return ResponseEntity.ok(new AuthResponseDTO(token));
	}
}
