package org.example.codetohire.service;

import lombok.RequiredArgsConstructor;
import org.example.codetohire.entity.User;
import org.example.codetohire.repository.userRepo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class userService implements UserDetailsService {

    private  final  userRepo userRepo;

    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        System.out.println("Called with: " + email);

          User u =  userRepo.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("Username not found"));

           return org.springframework.security.core.userdetails.User
                   .builder()
                   .username(u.getEmail())
                   .password(u.getPassword())
                   .roles(u.getRole().name())
                   .build();
    }
}
