package com.pfe.ai.ai.service.impl;

import com.pfe.ai.ai.model.Role;
import com.pfe.ai.ai.model.User;
import com.pfe.ai.ai.repository.RoleRepository;
import com.pfe.ai.ai.repository.UserRepository;
import com.pfe.ai.ai.service.AccountService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;


    @Override
    public Role addNewRole(Role role) {
        Role existedRole = roleRepository.findByRoleName(role.getRoleName());
        if (existedRole == null) {
            return roleRepository.save(role);
        }
        return null;
    }

    @Override
    public User loadUserByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }

    @Override
    public List<User> listUsers() {
        return userRepository.findAll();
    }

    @Override
    public User addRoleToUser(String username, String roleName) {
        User user = userRepository.findByUsername(username).orElse(null);
        if (user == null) return  null;
        Role role = roleRepository.findByRoleName(roleName);
        if(role == null){
            throw new EntityNotFoundException("Role ||"+roleName+"|| not found ");
        }
        user.getRoles().add(role);
        return user;
    }


    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }

    @Override
    public User getUserById(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }

    @Override
    public User getUserByToken(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }


}
