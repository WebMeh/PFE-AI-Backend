package com.pfe.ai.ai.controller;

import com.pfe.ai.ai.model.Role;
import com.pfe.ai.ai.model.User;
import com.pfe.ai.ai.service.AccountService;
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
/* Pour React App
@CrossOrigin(origins = "http://localhost:5173")

 */
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
