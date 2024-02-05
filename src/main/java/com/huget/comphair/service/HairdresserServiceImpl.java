package com.huget.comphair.service;

import com.huget.comphair.exception.ResourceNotFoundException;
import com.huget.comphair.model.Hairdresser;
import com.huget.comphair.model.HairdresserType;
import com.huget.comphair.model.Role;
import com.huget.comphair.model.RoleType;
import com.huget.comphair.repository.HairdresserDetailsRepository;
import com.huget.comphair.repository.HairdresserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Slf4j
@Service
public class HairdresserServiceImpl implements HairdresserService {

    HairdresserRepository hairdresserRepository;
    HairdresserDetailsRepository hairdresserDetailsRepository;
    private final RoleService roleService;

    @Autowired
    public HairdresserServiceImpl(HairdresserRepository hairdresserRepository, HairdresserDetailsRepository hairdresserDetailsRepository, RoleService roleService) {
        this.hairdresserRepository = hairdresserRepository;
        this.hairdresserDetailsRepository = hairdresserDetailsRepository;
        this.roleService = roleService;
    }

    @Override
    public List<Hairdresser> getAllHairdressers(HairdresserType hairdresserType) {
        log.info("Fetching all hairdressers.");
        List<Hairdresser> hairdressers = new ArrayList<Hairdresser>();
        if (hairdresserType == null) {
            hairdresserRepository.findAll().forEach(hairdressers::add);
        } else {
            hairdresserRepository.findByHairdresserType(hairdresserType).forEach(hairdressers::add);
        }
        log.info("Getting all hairdressers (count): {}", hairdressers.size());
        return hairdressers;
    }

    @Override
    public Hairdresser getHairdresserById(long hairdresserId) {
        log.info("Getting hairdresser by id: {}", hairdresserId);
        Hairdresser hairdresser = hairdresserRepository.findById(hairdresserId)
                .orElseThrow(() -> new ResourceNotFoundException("Not found hairdresser with id = " + hairdresserId));
        log.info("Got hairdresser: {}", hairdresser);
        return hairdresser;
    }

    @Override
    public Hairdresser getHairdresserByEmail(String email) {
        log.info("Getting hairdresser by email: {}", email);
        Hairdresser hairdresser = hairdresserRepository.findByEmail(email);
        if (hairdresser == null) {
            throw new ResourceNotFoundException("Not found hairdresser with email = " + email);
        }
        log.info("Got hairdresser: {}", hairdresser);
        return hairdresser;
    }

    @Override
    public Hairdresser createHairdresser(Hairdresser hairdresser) {
        log.debug("[DETAILS] Provided hairdresser: {}", hairdresser);
        log.info("[ACTION] Creating a new user.");
        RoleType roleType = RoleType.ROLE_USER;
        Role role = roleService.findByName(roleType);
        Hairdresser hairdresserCreated = hairdresserRepository.save(new Hairdresser(
                hairdresser.getNick(),
                hairdresser.getHairdresserType(),
                hairdresser.getEmail(),
                hairdresser.getPassword()));
        try {
            hairdresserCreated.setRoles(Set.of(role));
        } catch (NullPointerException e) {
            throw new ResourceNotFoundException("No roles in database");
        }
        log.debug("[RESPONSE] New user created: {}", hairdresserCreated);
        log.info("[ACTION] User has been successfully created.");
        return hairdresserCreated;
    }

    @Override
    public Hairdresser updateHairdresser(long hairdresserId, @RequestBody Hairdresser hairdresser) {
        Hairdresser hairdresserUpdated = hairdresserRepository.findById(hairdresserId)
                .orElseThrow(() -> new ResourceNotFoundException("Not found Hairdresser with id = " + hairdresserId));
        hairdresserUpdated.setNick(hairdresser.getNick());
        hairdresserUpdated.setHairdresserType(hairdresser.getHairdresserType());
        hairdresserUpdated.setEmail(hairdresserUpdated.getEmail());
        hairdresserUpdated.setPassword(hairdresserUpdated.getPassword());
        hairdresserRepository.save(hairdresserUpdated);
        log.info("Hairdresser has been updated with id:{}", hairdresserId);
        return hairdresserUpdated;
    }

    @Override
    public void deleteHairdresser(long hairdresserId) {
        log.info("[ACTION] Deleting specified user.");
        log.debug("[REQUEST] Deleting user with id: {}", hairdresserId);
        if (hairdresserDetailsRepository.existsById(hairdresserId)) {
            hairdresserDetailsRepository.deleteById(hairdresserId);
        }
        hairdresserRepository.deleteById(hairdresserId);
        log.info("User with id {} has been deleted", hairdresserId);
    }

    @Override
    public void deleteAllHairdressers() {
        hairdresserRepository.deleteAll();
        log.info("All hairdressers has been deleted");
    }
}
