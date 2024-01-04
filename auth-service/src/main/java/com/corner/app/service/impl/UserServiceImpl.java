package com.corner.app.service.impl;

import com.corner.app.entity.UserCredential;
import com.corner.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserDetailsService {
    @Autowired
    UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserCredential users =
                userRepository.findByEmail(email).orElseThrow(()-> new UsernameNotFoundException(
                        "User not found with email: "+email));
        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority(users.getRole().toString()));

        return new User(users.getEmail(), users.getPassword(), authorities);
    }

    public List<UserCredential> getAllUsers() {
        return userRepository.findAll();
    }

    public UserCredential getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow();
    }

    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }
}
