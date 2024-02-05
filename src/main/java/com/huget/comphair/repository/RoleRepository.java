package com.huget.comphair.repository;

import com.huget.comphair.model.Role;
import com.huget.comphair.model.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName (RoleType roleName);
}
