package com.huget.comphair.service;

import com.huget.comphair.model.Role;
import com.huget.comphair.model.RoleType;

public interface RoleService {

    Role findByName(RoleType roleTypeName);

    void createRoles();

}
