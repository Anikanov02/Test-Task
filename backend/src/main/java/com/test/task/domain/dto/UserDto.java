package com.test.task.domain.dto;

import com.test.task.domain.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
    private Long id;

    @NotBlank(message = "firstName should be not blank")
    private String firstName;

    @NotBlank(message = "lastName should be not blank")
    private String lastName;

    @NotBlank(message = "fathersName should be not blank")
    private String fathersName;

    @NotBlank(message = "email should be not blank")
    private String email;

    @NotBlank(message = "password should be not blank")
    private String password;

    @NotNull(message = "dateOfBirth should be not null")
    private LocalDate dateOfBirth;

    @NotBlank(message = "phoneNumber should be not blank")
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
