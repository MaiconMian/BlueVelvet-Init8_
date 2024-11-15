package com.bluevelvet.controller;

import com.bluevelvet.DTO.LoginResponseDTO;
import com.bluevelvet.DTO.RegisterDTO;
import com.bluevelvet.model.Role;
import com.bluevelvet.repository.RoleRepository;
import com.bluevelvet.repository.UserRepository;
import com.bluevelvet.security.TokenService;
import com.bluevelvet.service.RoleService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.bluevelvet.DTO.AuthenticationDTO;
import com.bluevelvet.DTO.LoginResponseDTO;
import com.bluevelvet.model.User;

import javax.management.remote.JMXAuthenticator;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("auth")
public class AuthenticationController {

    private static final Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleService roleService;
    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login (@RequestBody @Valid AuthenticationDTO authenticationDTO) {

        var usernamepassword = new UsernamePasswordAuthenticationToken(authenticationDTO.email(), authenticationDTO.password());

        try {
            var auth = this.authenticationManager.authenticate(usernamepassword);

            var token = tokenService.generateToken((User) auth.getPrincipal());

            return ResponseEntity.ok(new LoginResponseDTO(token));

        } catch (Exception e) {
            return ResponseEntity.status(401).build();
        }
    }

    @PostMapping("/register")
    public ResponseEntity register (@RequestBody @Valid RegisterDTO registerDTO) {
        logger.debug("Registering new user with email: {}", registerDTO.email());

        if(this.userRepository.findByEmail(registerDTO.email()) != null) {
            logger.warn("User with email {} already exists", registerDTO.email());
            return ResponseEntity.badRequest().build();
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(registerDTO.password());
        logger.debug("Password encrypted for user {}", registerDTO.email());

        User newuser = new User();
        newuser.setName(registerDTO.name());
        newuser.setLastName(registerDTO.lastName());
        newuser.setEmail(registerDTO.email());
        newuser.setPassword(encryptedPassword);
        newuser.setStatus(true);

        List<Role> roles = new ArrayList<>();

        logger.debug("Saving user {}", newuser.getEmail());
        this.userRepository.save(newuser);

        registerDTO.Roles().forEach(roleId -> {
            roleService.getRoleById(roleId).ifPresent(role -> {
                newuser.getRoles().add(role);
                role.getUsers().add(newuser);
                roleService.saveRole(role);
                logger.debug("Assigned role {} to user {}", role.getName(), newuser.getEmail());
            });
        });

        logger.info("User {} registered successfully", newuser.getEmail());

        return ResponseEntity.ok().build();
    }
}
