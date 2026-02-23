package org.example.codetohire.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.codetohire.config.SecurityConfig;
import org.example.codetohire.entity.User;
import org.example.codetohire.enums.Role;
import org.example.codetohire.exception.UserAlreadyExistsException;
import org.example.codetohire.repository.userRepo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin (origins = "http://localhost:3000", allowCredentials = "true")
@RequestMapping("/api/auth")
@RequiredArgsConstructor

public class userController {


   private final userRepo userrepo;
   private final SecurityConfig securityConfig;

    @PostMapping("/signUp")
    public ResponseEntity<?> signUp( @Valid @RequestBody User user){

        if(userrepo.findByEmail(user.getEmail()).isPresent()){
           throw  new UserAlreadyExistsException("User already exists with this email");
        }
        user.setPassword(securityConfig.passwordEncoder2().encode(user.getPassword()));
        user.setRole(Role.STUDENT);
        userrepo.save(user);
        return ResponseEntity.ok("User has been created");
    }



}
