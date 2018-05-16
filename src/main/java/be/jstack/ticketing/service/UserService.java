package be.jstack.ticketing.service;

import be.jstack.ticketing.data.UserRepository;
import be.jstack.ticketing.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
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

    public void addUser(User user) {
        if (!findUserByUsername(user.getUsername()).isPresent()) {
            user.setPassword(encoder.encode(user.getPassword()));
            userRepository.save(user);
        }
    }

    public Optional<User> findUserByUsername(String username) {
        return userRepository.findByUsername(username);
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
}