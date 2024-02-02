package com.huget.comphair.security.service;

import com.huget.comphair.model.Hairdresser;
import com.huget.comphair.model.Role;
import com.huget.comphair.repository.HairdresserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    HairdresserRepository hairdresserRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        log.info("Loading user...");
        //TODO: delete sout when security developed
        log.info("Email = " + email);
        Hairdresser hairdresser = hairdresserRepository.findByEmail(email);
        List<SimpleGrantedAuthority> roles = new ArrayList<>();
        for (Role role : hairdresser.getRoles()) {
            roles.add(new SimpleGrantedAuthority(role.getName().name()));
        }
        User userDetails = new User(hairdresser.getEmail(), hairdresser.getPassword(), roles);
        log.info("Loading user completed successfully.");
        return userDetails;
    }
}
