package com.bluevelvet.service;

import com.bluevelvet.model.User;
import com.bluevelvet.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import com.bluevelvet.repository.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username);
    }

}
