package be.jstack.ticketing.service;

import be.jstack.ticketing.data.UserRepository;
import be.jstack.ticketing.entity.User;
import be.jstack.ticketing.util.constants.Authority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.util.Collections.emptyList;

@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public User addUser(User user) {
        if (!findUserByUsername(user.getUsername()).isPresent()) {
            user.setPassword(encoder.encode(user.getPassword()));
            return userRepository.save(user);
        }
        return null;
    }

    public Optional<User> findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    Optional<User> findUserById(String userId) {
        return userRepository.findById(userId);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optUser = userRepository.findByUsername(username);
        if (!optUser.isPresent()) {
            throw new UsernameNotFoundException(username);
        } else {
            User user = optUser.get();
            return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), emptyList());
        }
    }

    Optional<User> getCurrentLoggedInUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return findUserByUsername(username);
    }

    public User addAuthorityToUser(String username, String authority) {
        Optional<User> user = findUserByUsername(username);
        if (user.isPresent()) {
            user.get().addAuthority(Authority.valueOf(authority.toUpperCase()));
            return userRepository.save(user.get());
        }
        return null;
    }

    public User removeAuthorityForUser(String username, String authority) {
        Optional<User> user = findUserByUsername(username);
        if (user.isPresent()) {
            user.get().removeAuthority(Authority.valueOf(authority.toUpperCase()));
            return userRepository.save(user.get());
        }
        return null;
    }
}