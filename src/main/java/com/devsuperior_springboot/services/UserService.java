package com.devsuperior_springboot.services;

import com.devsuperior_springboot.entities.Role;
import com.devsuperior_springboot.entities.User;
import com.devsuperior_springboot.projections.UserDetailsProjection;
import com.devsuperior_springboot.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<UserDetailsProjection> result = repository.searchUserAndRolesByEmail(username);
        if (result.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }

        User user = new User();
        user.setEmail(result.get(0).getUsername());
        user.setPassword(result.get(0).getPassword());
        result.forEach(projection -> user.getRoles().add(new Role(projection.getRoleId(), projection.getAuthority())));

        return user;
    }
}
