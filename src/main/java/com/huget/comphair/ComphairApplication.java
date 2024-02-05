package com.huget.comphair;

import com.huget.comphair.repository.RoleRepository;
import com.huget.comphair.service.RoleService;
import com.huget.comphair.service.RoleServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class ComphairApplication {

    public static void main(String[] args) {
//        SpringApplication.run(ComphairApplication.class, args);

        ConfigurableApplicationContext context = SpringApplication.run(ComphairApplication.class, args);
        RoleService roleService = context.getBean(RoleService.class);
        roleService.createRoles();
    }

}
