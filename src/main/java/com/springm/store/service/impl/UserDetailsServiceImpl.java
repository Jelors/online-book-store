package com.springm.store.service.impl;

import com.springm.store.exception.UserNotFoundException;
import com.springm.store.model.User;
import com.springm.store.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(
                        "User with email: " + email + " not found!"));
    }

    public Long getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            throw new IllegalStateException("User is not authenticated");
        }

        User user = (User) authentication.getPrincipal();
        return user.getId();
    }

    public User getCurrentUser() {
        Long userId = getCurrentUserId();
        return userRepository.findByUserId(userId)
                .orElseThrow(() -> new UserNotFoundException(
                        "User with id " + userId + " not found!"
                ));
    }

}
