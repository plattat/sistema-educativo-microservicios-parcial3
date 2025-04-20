package com.example.matriculas_servicio.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.List;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<SimpleGrantedAuthority> authorities = List.of(new SimpleGrantedAuthority("ROLE_USER"));
        return new User(username, "", authorities);
    }
}
