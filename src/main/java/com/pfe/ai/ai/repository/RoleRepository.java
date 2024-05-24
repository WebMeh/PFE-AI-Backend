package com.pfe.ai.ai.repository;

import com.pfe.ai.ai.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByRoleName (String roleName);
}
