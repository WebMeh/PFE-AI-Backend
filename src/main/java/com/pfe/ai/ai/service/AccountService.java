package com.pfe.ai.ai.service;

import com.pfe.ai.ai.model.User;

import java.util.List;

public interface AccountService {
    User loadUserByUsername(String username);
    List<User> listUsers();

    User findByUsername(String username);
}
