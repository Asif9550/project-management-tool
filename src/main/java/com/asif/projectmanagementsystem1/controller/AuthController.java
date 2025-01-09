package com.asif.projectmanagementsystem1.controller;

import com.asif.projectmanagementsystem1.config.JwtProvider;
import com.asif.projectmanagementsystem1.model.Chat;
import com.asif.projectmanagementsystem1.model.Subscription;
import com.asif.projectmanagementsystem1.model.User;
import com.asif.projectmanagementsystem1.repository.UserRepository;
import com.asif.projectmanagementsystem1.request.LoginRequest;
import com.asif.projectmanagementsystem1.response.AuthResponse;
import com.asif.projectmanagementsystem1.service.CustomUserDetailsImpl;
import com.asif.projectmanagementsystem1.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CustomUserDetailsImpl customUserDetails;
    @Autowired
    private SubscriptionService subscriptionService;
    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> createUserHandler(@RequestBody User user) throws Exception {
        User isUserExist=userRepository.findByEmail(user.getEmail());
        if(isUserExist!=null){
            throw new Exception("Email already exists with another account");
        }
        User createdUser=new User();
        createdUser.setPassword(passwordEncoder.encode(user.getPassword()));
        createdUser.setEmail(user.getEmail());
        createdUser.setFullName(user.getFullName());
        User savedUser=userRepository.save(createdUser);
        subscriptionService.createSubscription(savedUser);
        Authentication authentication=new UsernamePasswordAuthenticationToken(user.getEmail(),user.getPassword());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt= JwtProvider.generateToken(authentication);
        System.out.println("Generated JWT Token: " + jwt);
        AuthResponse res=new AuthResponse();
        res.setMessage("Signup Success");
        res.setJwt(jwt);
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }
    @PostMapping("/signin")
    public ResponseEntity<AuthResponse> signin(@RequestBody LoginRequest loginRequest){
        String username=loginRequest.getEmail();
        String password=loginRequest.getPassword();
        Authentication authentication=authenticate(username,password);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt=JwtProvider.generateToken(authentication);
        AuthResponse res=new AuthResponse();
        res.setMessage("Signin Success");
        res.setJwt(jwt);
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    private Authentication authenticate(String username, String password) {
        UserDetails userDetails=customUserDetails.loadUserByUsername(username);
        if(userDetails==null){
            throw new BadCredentialsException("inavlid user");
        }
        if(!passwordEncoder.matches(password,userDetails.getPassword())){
            throw new BadCredentialsException("invalid password");
        }
        return new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
    }

}
