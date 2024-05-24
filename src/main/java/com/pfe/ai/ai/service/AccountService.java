package com.pfe.ai.ai.service;

import com.pfe.ai.ai.model.Role;
import com.pfe.ai.ai.model.User;

import java.util.List;

public interface AccountService {
    Role addNewRole(Role role);
    User loadUserByUsername(String username);
    List<User> listUsers();
    User addRoleToUser(String username, String roleName);
    User findByUsername(String username);
}
