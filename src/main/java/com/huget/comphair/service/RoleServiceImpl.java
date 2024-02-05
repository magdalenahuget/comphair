package com.huget.comphair.service;

import com.huget.comphair.model.Role;
import com.huget.comphair.model.RoleType;
import com.huget.comphair.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService{

    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role findByName(RoleType roleTypeName) {
        return roleRepository.findByName(roleTypeName);
    }
}
