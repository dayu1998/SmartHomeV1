package com.antra.smart_home_v1.controller;

import com.antra.smart_home_v1.api.request.LoginRequest;
import com.antra.smart_home_v1.api.request.SignUpRequest;
import com.antra.smart_home_v1.api.response.JwtAuthenticationResponse;
import com.antra.smart_home_v1.api.response.UserResponse;
import com.antra.smart_home_v1.config.security.JwtTokenProvider;
import com.antra.smart_home_v1.dao.UserRepository;
import com.antra.smart_home_v1.dao.UserRoleRepository;
import com.antra.smart_home_v1.domain.RoleName;
import com.antra.smart_home_v1.domain.User;
import com.antra.smart_home_v1.domain.UserRole;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.HashSet;
import java.util.Set;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/auth")
@Api(value = "Signin",tags={"Signin"})
public class LoginController {

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;

	@Autowired
	UserRoleRepository roleRepository;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	JwtTokenProvider tokenProvider;

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						loginRequest.getUsernameOrEmail(),
						loginRequest.getPassword()
				)
		);

		SecurityContextHolder.getContext().setAuthentication(authentication);

		String jwt = tokenProvider.generateToken(authentication);
		return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest newUser) {
		if(userRepository.existsByUsername(newUser.getUsername())) {
			return new ResponseEntity(new UserResponse(false, "Username is already taken!"),
					HttpStatus.BAD_REQUEST);
		}

		if(userRepository.existsByEmail(newUser.getEmail())) {
			return new ResponseEntity(new UserResponse(false, "Email Address already in use!"),
					HttpStatus.BAD_REQUEST);
		}

		Set<UserRole> roles = new HashSet<>();
		UserRole role = new UserRole(RoleName.ROLE_USER);
		roles.add(role);
		if (newUser.getAdmin()) {
			roles.add(new UserRole(RoleName.ROLE_ADMIN));
		}
		User user = new User(newUser.getName(),newUser.getUsername(),newUser.getEmail()
				,passwordEncoder.encode(newUser.getPassword()),
				roles);
		roles.stream().forEach(r -> r.setUser(user));
		User result = userRepository.save(user);

		URI location = ServletUriComponentsBuilder
				.fromCurrentContextPath().path("/api/users/{username}")
				.buildAndExpand(result.getUsername()).toUri();

		return ResponseEntity.created(location).body(new UserResponse(true, "User registered successfully"));
	}
}
