package com.example.service.auth.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DataLoginDTO(
        @NotNull
        @Email
        String login,
        @NotNull
        @Pattern(regexp = "(?=.*[}{!@#$%&,.^?~=+\\-_\\/*\\-+.\\|])(?=.*[a-zA-Z])(?=.*[0-9]).{8,}")
        String password

) {
}
