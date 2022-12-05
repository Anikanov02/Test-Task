package com.test.task.backend.service;

import com.test.task.backend.domain.dto.UserDto;
import com.test.task.backend.domain.model.User;
import com.test.task.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User createUser(UserDto userData) {
        final User user = User.builder()
                .id(userData.getId())
                .firstName(userData.getFirstName())
                .lastName(userData.getLastName())
                .fathersName(userData.getFathersName())
                .email(userData.getEmail())
                .password(passwordEncoder.encode(userData.getPassword()))
                .dateOfBirth(userData.getDateOfBirth())
                .phoneNumber(userData.getPhoneNumber())
                .build();
        return userRepository.save(user);
    }
    public User getUserById(long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new RuntimeException(String.format("No user found with id %d", userId)));
    }

    public User getUserByPhoneNumber(String phone) {
        return userRepository.findByPhoneNumber(phone).orElseThrow(() -> new RuntimeException(String.format("No user found with phone %s", phone)));
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException(String.format("No user found with email %s", email)));
    }

    public User updateUser(long id, UserDto userData) {
        final User user = getUserById(id);

        user.setFirstName(userData.getFirstName());
        user.setLastName(userData.getLastName());
        user.setFathersName(userData.getFathersName());
        user.setDateOfBirth(userData.getDateOfBirth());
        user.setPhoneNumber(userData.getPhoneNumber());

        if (userData.getPassword() != null && userData.getPassword().length() > 0) {
            user.setPassword(passwordEncoder.encode(userData.getPassword()));
        }

        return userRepository.save(user);
    }

    public void deleteUser(long userId) {
        userRepository.deleteById(userId);
    }
}
