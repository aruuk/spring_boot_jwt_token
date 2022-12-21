package com.example.spring_boot_jwt_token.repository;

import com.example.spring_boot_jwt_token.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}