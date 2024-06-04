package com.pfe.ai.ai.controller;

import com.pfe.ai.ai.model.Role;
import com.pfe.ai.ai.model.User;
import com.pfe.ai.ai.service.AccountService;
import com.pfe.ai.ai.system.Result;
import com.pfe.ai.ai.system.StatusCode;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
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

    @GetMapping("/byId/{userId}")
    public ResponseEntity<?> getUserById(@PathVariable Long userId){
        User user = accountService.getUserById(userId);
        if (user != null) {
            return ResponseEntity.ok(
                    new Result(
                            true, StatusCode.SUCCESS, "User by id: " + userId,
                            user
                    )
            );
        }
        return ResponseEntity.ok(
                new Result(
                        false, StatusCode.NOT_FOUND, "User Not found with id: " + userId
                )
        );
    }
    @GetMapping("/byUsername")
    public ResponseEntity<?> getUserByToken(@AuthenticationPrincipal UserDetails userDetails) {
        if(accountService.findByUsername(userDetails.getUsername()) != null) {
            return ResponseEntity.ok(
                    accountService.findByUsername(userDetails.getUsername())
            );
        }
        return ResponseEntity.ok("Sorry you are not a teacher");
    }

    @PostMapping("/new-role")
    public ResponseEntity<?> createRole(@RequestBody Role role){
        Role savedRole= accountService.addNewRole(role);
        if (savedRole != null) {
            return ResponseEntity.ok(savedRole);
        }
        return ResponseEntity.ok("Role "+role.getRoleName()+"already exists !");
    }
    @PostMapping("/add-role-to-user")
    public ResponseEntity<?> addRoleToUser(@AuthenticationPrincipal UserDetails userDetails,
                                           @RequestBody Role role)
    {
        return ResponseEntity.ok(
                accountService.addRoleToUser(
                        userDetails.getUsername(), role.getRoleName()
                )
        );
    }
}
