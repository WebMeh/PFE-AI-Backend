package com.pfe.ai.ai.controller;

import com.pfe.ai.ai.model.User;
import com.pfe.ai.ai.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:5173")
public class UserController {
    private final AccountService accountService;

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(){
        return ResponseEntity.ok(accountService.listUsers());
    }

    @GetMapping("/connected")
    public ResponseEntity<String> getConnectedUser(@AuthenticationPrincipal UserDetails userDetails){
        if(userDetails!=null) return  ResponseEntity.ok(userDetails.getUsername() );
        return ResponseEntity.ok("user is null");
    }

    @GetMapping("/byUsername")
    public ResponseEntity<User> getUserByToken(
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        return ResponseEntity.ok(
                accountService.findByUsername(userDetails.getUsername())
        );
    }
}
