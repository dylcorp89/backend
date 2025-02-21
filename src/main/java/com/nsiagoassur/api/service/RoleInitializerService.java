package com.nsiagoassur.api.service;

import com.nsiagoassur.api.model.Role;
import com.nsiagoassur.api.repository.RoleRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service

public class RoleInitializerService {

	@Autowired
    private  RoleRepository roleRepository ;

    @PostConstruct
    public void initRoles() {
        List<String> defaultRoles = Arrays.asList("Admin", "Amazone");

        for (String roleName : defaultRoles) {
            if (roleRepository.findByLibelle(roleName).isEmpty()) {
            	Role role = new Role();
            	role.setLibelle(roleName);
            	
                roleRepository.save(role); // Utilisation du constructeur
            }
        }
    }
}
