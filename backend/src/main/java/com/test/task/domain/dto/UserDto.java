package com.test.task.domain.dto;

import com.test.task.domain.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
    private Long id;

    private String firstName;

    private String lastName;

    private String fathersName;

    private String email;

    private String password;

    private LocalDate dateOfBirth;

    private String phoneNumber;
    public static UserDto convert(User user) {
        return UserDto.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .fathersName(user.getFathersName())
                .email(user.getEmail())
                .password(user.getPassword())
                .dateOfBirth(user.getDateOfBirth())
                .phoneNumber(user.getPhoneNumber())
                .build();
    }
}
