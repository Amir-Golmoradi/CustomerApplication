package dev.amir.masterclass.infrastructure.custom_actions;

public record CustomerRegistrationRequest(
        Integer id,
        String name,
        String email,
        String password,
        Integer age
) {
}
